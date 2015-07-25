/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NSSWare.MultiSync;

import static com.NSSWare.MultiSync.TestClass.getFlow;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.JsonFactory;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;


import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Grant
 */
public class GoogleDriveSyncer extends Syncer {

    private static GoogleAuthorizationCodeFlow flow = null;
    private static final JsonFactory JSON_FACTORY
            = JacksonFactory.getDefaultInstance();
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    private static final String REDIRECT_URI = "urn:ietf:wg:oauth:2.0:oob";
    private static final String CLIENT_ID = "389583572926-oolebtf76pb8ppfoi238u8242s772pnj.apps.googleusercontent.com";
    private static final String CLIENT_SECRET = "7Vi6AliTJQVbFhHCtL_-blTq";

    private static final List<String> REDIRECT_URIS = Arrays.asList("urn:ietf:wg:oauth:2.0:oob", "http://localhost");

    private static final String AUTH_URI = "https://accounts.google.com/o/oauth2/auth";
    private static final String TOKEN_URI = "https://accounts.google.com/o/oauth2/token";
    
    private static final List<String> DEFAULT_SCOPES = Arrays.asList(
            "https://www.googleapis.com/auth/drive.appfolder",
            "email",
            "profile");
    
    private String accessToken;
    
    Drive service;
    
    private List<String> appScopes = null;
    
    @Override
    public String getOAuth2URL() throws SyncerException, SyncerException.NetworkException {
        
        try {
            List<String> useScope = getScope();
            
            GoogleAuthorizationCodeRequestUrl urlBuilder = getFlow(useScope)
                    .newAuthorizationUrl()
                    .setRedirectUri(REDIRECT_URI);
            
            //urlBuilder.set("user_id", emailAddress);
            return urlBuilder.build();
        } catch (IOException e1) {
            throw new SyncerException.NetworkException(e1.getMessage(), e1.getCause());
        }
        catch(Exception e2)
        {
            throw new SyncerException(e2.getMessage(), e2.getCause());
        }
        
    }

    @Override
    public void finishOAuth2(String accessToken) throws SyncerException, SyncerException.NetworkException, SyncerException.BadRequestException {
        
        try {    
            List<String> useScope = getScope();
            
            GoogleAuthorizationCodeFlow flow = getFlow(useScope);
            GoogleTokenResponse response = flow
                    .newTokenRequest(accessToken)
                    .setRedirectUri(REDIRECT_URI)
                    .execute();
            Credential cred =  flow.createAndStoreCredential(response, null);
            
            accessToken = cred.getAccessToken();
            
            
            service = new Drive.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, cred)
                .setApplicationName("Test")
                .build();
            
        } 
        catch (com.google.api.client.auth.oauth2.TokenResponseException e1)
        {
            //If this happens, you most likely have the wrong key
            throw new SyncerException.BadRequestException(e1.getMessage(), e1.getCause());
        }
        catch (IOException e2) {
            //If this happens, there is something wrong with the network somewhere along the line
            throw new SyncerException.NetworkException(e2.getMessage(), e2.getCause());
        }
        catch(Exception e3)
        {
            throw new SyncerException(e3.getMessage(), e3.getCause());
        }
    }

    @Override
    public Metadata getMetadata(String path) {
    
        try {
           service.files().list().set("title", "= path");
        } catch (IOException ex) {
            Logger.getLogger(GoogleDriveSyncer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    
    }

    @Override
    public String getFileContent(String path) throws SyncerException.NetworkException, SyncerException.NotSignedInException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeContent(String path, String content) throws SyncerException.NetworkException, SyncerException.NotSignedInException {
        
        FileContent contents;
        contents = new FileContent("", new File(""));
        
        service.files().
    }

    @Override
    public void writeFile(String path, File file) throws SyncerException.NetworkException, SyncerException.NotSignedInException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteFile(String path) throws SyncerException.NetworkException, SyncerException.NotSignedInException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //Functions that jump through hoops for us.
    public static GoogleAuthorizationCodeFlow getFlow(List<String> scopes) throws IOException {
        if (flow == null) {

            GoogleClientSecrets.Details installed = new GoogleClientSecrets.Details();

            installed.setAuthUri(AUTH_URI);
            installed.setClientId(CLIENT_ID);
            installed.setClientSecret(CLIENT_SECRET);
            installed.setRedirectUris(REDIRECT_URIS);
            installed.setTokenUri(TOKEN_URI);

            GoogleClientSecrets clientSecret
                    = new GoogleClientSecrets();

            clientSecret.setInstalled(installed);

            flow = new GoogleAuthorizationCodeFlow.Builder(
                    HTTP_TRANSPORT, JSON_FACTORY, clientSecret, scopes)
                    .setAccessType("offline")
                    .setApprovalPrompt("force")
                    .build();
        }
        return flow;
    }

    /**
     * Determines the scope that should be used for requests
     * @return a List<String> of the scope to be used
     */
    private List<String> getScope() {

        List<String> useScope;

        if (appScopes == null) {
            useScope = DEFAULT_SCOPES;
        } else {
            useScope = appScopes;
        }

        return useScope;

    }
    
    
}

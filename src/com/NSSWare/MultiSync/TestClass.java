/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NSSWare.MultiSync;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.sun.org.apache.xpath.internal.operations.Plus;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Grant
 */
public class TestClass {

    private static GoogleAuthorizationCodeFlow flow = null;
    private static final JacksonFactory JSON_FACTORY
            = JacksonFactory.getDefaultInstance();
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    private static final String REDIRECT_URI = "urn:ietf:wg:oauth:2.0:oob";
    private static final String CLIENT_ID = "389583572926-oolebtf76pb8ppfoi238u8242s772pnj.apps.googleusercontent.com";
    private static final String CLIENT_SECRET = "7Vi6AliTJQVbFhHCtL_-blTq";

    private static final List<String> REDIRECT_URIS = Arrays.asList("urn:ietf:wg:oauth:2.0:oob", "http://localhost");

    private static final String AUTH_URI = "https://accounts.google.com/o/oauth2/auth";
    private static final String TOKEN_URI = "https://accounts.google.com/o/oauth2/token";

    private static final List<String> SCOPES = Arrays.asList(
            "https://www.googleapis.com/auth/drive.appfolder",
            "email",
            "profile");

    public static void main(String[] args) throws Exception {
        
        Scanner in = new Scanner(System.in);
        
        Syncer sync = new GoogleDriveSyncer();
        
        System.out.println(sync.getOAuth2URL());
        
        sync.finishOAuth2(in.nextLine());
        
        System.out.println("GOOD");
        
        /**openWebpage(new URI(getAuthorizationUrl("", "")));
        
        
        System.out.println("Access Token?: " + exchangeCode(in.nextLine()).getAccessToken());
        **/
    }

    public static GoogleAuthorizationCodeFlow getFlow() throws IOException {
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
                    HTTP_TRANSPORT, JSON_FACTORY, clientSecret, SCOPES)
                    .setAccessType("offline")
                    .setApprovalPrompt("force")
                    .build();
        }
        return flow;
    }

    /**
     * Retrieve the authorization URL.
     *
     * @param emailAddress User's e-mail address.
     * @param state State for the authorization URL.
     * @return Authorization URL to redirect the user to.
     * @throws IOException Unable to load client_secret.json.
     */
    public static String getAuthorizationUrl(String emailAddress, String state)
            throws IOException {
        GoogleAuthorizationCodeRequestUrl urlBuilder = getFlow()
                .newAuthorizationUrl()
                .setRedirectUri(REDIRECT_URI)
                .setState(state);
        urlBuilder.set("user_id", emailAddress);
        return urlBuilder.build();
    }
    
      /**
   * Exchange an authorization code for OAuth 2.0 credentials.
   *
   * @param authorizationCode Authorization code to exchange for OAuth 2.0
   *     credentials.
   * @return OAuth 2.0 credentials.
   * @throws CodeExchangeException An error occurred.
   */
  static Credential exchangeCode(String authorizationCode){
    try {
      GoogleAuthorizationCodeFlow flow = getFlow();
      GoogleTokenResponse response = flow
          .newTokenRequest(authorizationCode)
          .setRedirectUri(REDIRECT_URI)
          .execute();
      return flow.createAndStoreCredential(response, null);
    } catch (IOException e) {
      System.err.println("An error occurred: " + e);
    }
    return null;
  }

    public static void openWebpage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

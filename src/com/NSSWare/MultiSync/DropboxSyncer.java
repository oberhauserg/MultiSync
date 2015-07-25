/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NSSWare.MultiSync;

import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxAuthFinish;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWebAuthNoRedirect;
import java.io.File;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Grant
 */
public class DropboxSyncer extends Syncer {

    private String appKey, appSecret, accessToken = "";

    DbxRequestConfig config;

    public DropboxSyncer(String key, String secret, String clientIdentifier) {
        
        appKey = key;

        appSecret = secret;

        config = new DbxRequestConfig(clientIdentifier, Locale.getDefault().toString());

    }

    public DropboxSyncer(String key, String secret, String token, String clientIdentifier) {
        
        appKey = key;

        appSecret = secret;

        accessToken = token;

        config = new DbxRequestConfig(clientIdentifier, Locale.getDefault().toString());

    }

    private DropboxSyncer() {

    }

    @Override
    public String getOAuth2URL() throws SyncerException.NetworkException {

        DbxAppInfo appInfo = new DbxAppInfo(appKey, appSecret);

        DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);

        return webAuth.start();

    }

    @Override
    public void finishOAuth2(String token) throws SyncerException, SyncerException.NetworkException, SyncerException.BadRequestException {

        try {
            DbxAppInfo appInfo = new DbxAppInfo(appKey, appSecret);

            DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);

            DbxAuthFinish finish = webAuth.finish(token);

            accessToken = token;

        } catch (DbxException.BadRequest e1) {
            throw new SyncerException.BadRequestException(e1.getMessage(), e1.getCause());
        } catch (DbxException.ProtocolError e2) {
            throw new SyncerException.NetworkException(e2.getMessage(), e2.getCause());
        } catch (DbxException.NetworkIO e3) {
            throw new SyncerException.NetworkException(e3.getMessage(), e3.getCause());
        } catch (DbxException ex) {
            throw new SyncerException();
        }

    }

    @Override
    public Metadata getMetadata(String path) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getFileContent(String path) throws SyncerException.NetworkException, SyncerException.NotSignedInException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeContent(String path, String content) throws SyncerException.NetworkException, SyncerException.NotSignedInException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeFile(String path, File file) throws SyncerException.NetworkException, SyncerException.NotSignedInException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteFile(String path) throws SyncerException.NetworkException, SyncerException.NotSignedInException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NSSWare.MultiSync;

import java.io.File;

/**
 *
 * @author Grant
 */
abstract public class Syncer{
    
   public static enum WRITE_TYPE {FORCE, ADD};
   
   /**
    * Starts the OAuth 2.0 authentication flow for the service and returns 
    * the URL to visit to allow the user to acknowledge.
    * @return the URL to visit
     * @throws com.NSSWare.MultiSync.SyncerException.NetworkException
    */
   public abstract String getOAuth2URL() throws SyncerException, SyncerException.NetworkException;
   
   /**
    * Finishes the OAuth 2.0 authentication flow for the service by giving the 
    * resource server the access token.
    * 
     * @param accessToken The access token received from the authorization server
     * @throws com.NSSWare.MultiSync.SyncerException.NetworkException 
     * @throws com.NSSWare.MultiSync.SyncerException.BadRequestException 
    */
   public abstract void finishOAuth2(String accessToken) throws SyncerException, SyncerException.NetworkException, SyncerException.BadRequestException;
   
   /**
    * Gets and returns the Metadata for a resource on the content provider's server
    * @param path The path the resource resides at
    * @return The Metadata of the resource requested or <br> null if the resource does not exist
     * @throws com.NSSWare.MultiSync.SyncerException.NotSignedInException
     * @throws com.NSSWare.MultiSync.SyncerException.NetworkException
    */
   public abstract Metadata getMetadata(String path) throws SyncerException.NotSignedInException, SyncerException.NetworkException;
    
   /**
    * Gets the content of the file at the path.
    * @param path The path the file should be at on the cloud platform
    * @return the content if it is retrieved correctly <br> null if it doesn't exist 
    * @throws com.NSSWare.MultiSync.SyncerException.NetworkException
    * @throws com.NSSWare.MultiSync.SyncerException.NotSignedInException 
    */
   public abstract String getFileContent(String path) throws SyncerException.NetworkException, SyncerException.NotSignedInException;
   
   public abstract void writeContent(String path, String content) throws SyncerException.NetworkException, SyncerException.NotSignedInException;
   
   public abstract void writeFile(String path, File file) throws SyncerException.NetworkException, SyncerException.NotSignedInException;
   
   public abstract void deleteFile(String path) throws SyncerException.NetworkException, SyncerException.NotSignedInException; 
   
}

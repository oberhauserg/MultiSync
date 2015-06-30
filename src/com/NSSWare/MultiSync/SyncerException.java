/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NSSWare.MultiSync;

/**
 *
 * @author Grant
 */
public class SyncerException extends Exception {


    public SyncerException() {
        super();
    }

    public SyncerException(String message) {
        super(message);
    }

    public SyncerException(String message, Throwable cause) {
        super(message, cause);
    }

    public SyncerException(Throwable cause) {
        super(cause);
    }

    /**
     * Thrown when the volume we are syncing to is out of space
     */
    public static class OutOfSpaceException extends SyncerException {

        public OutOfSpaceException() {
            super();
        }

        public OutOfSpaceException(String message) {
            super(message);
        }

        public OutOfSpaceException(String message, Throwable cause) {
            super(message, cause);
        }

        public OutOfSpaceException(Throwable cause) {
            super(cause);
        }

    }

    /**
     * Thrown when a problem with the network causes the requested service to
     * fail
     */
    public static class NetworkException extends SyncerException {

        public NetworkException() {
            super();
        }

        public NetworkException(String message) {
            super(message);
        }

        public NetworkException(String message, Throwable cause) {
            super(message, cause);
        }

        public NetworkException(Throwable cause) {
            super(cause);
        }

    }

    /**
     * Thrown when a request fails due to not being signed in, or not being
     * signed in correctly
     */
    public static class NotSignedInException extends SyncerException {

        public NotSignedInException() {
            super();
        }

        public NotSignedInException(String message) {
            super(message);
        }

        public NotSignedInException(String message, Throwable cause) {
            super(message, cause);
        }

        public NotSignedInException(Throwable cause) {
            super(cause);
        }

    }

    /**
     * Thrown when the request sent to the server wasn't able to be processed
     * correctly
     */
    public static class BadRequestException extends SyncerException {

        public BadRequestException() {
            super();
        }

        public BadRequestException(String message) {
            super(message);
        }

        public BadRequestException(String message, Throwable cause) {
            super(message, cause);
        }

        public BadRequestException(Throwable cause) {
            super(cause);
        }

    }
    
    /**
     * Thrown when the request hit some sort of rate limit.
     */
    public static class RateLimitException extends SyncerException {

        public RateLimitException() {
            super();
        }

        public RateLimitException(String message) {
            super(message);
        }

        public RateLimitException(String message, Throwable cause) {
            super(message, cause);
        }

        public RateLimitException(Throwable cause) {
            super(cause);
        }

    }

}

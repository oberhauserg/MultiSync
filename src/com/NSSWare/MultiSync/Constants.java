package com.NSSWare.MultiSync;

/**
 * Contains all of the default settings for different cloud storage platforms
 *
 * @author Grant Oberhauser
 */
public class Constants {

    //-------------------------------
    //Google Drive specific constants
    //-------------------------------
    /**
     * This value signals to the Google Authorization Server that the
     * authorization code should be returned as a query string parameter to the
     * web server on the client. You may specify a port number without changing
     * the Google Developers Console configuration. To receive the authorization
     * code using this URL, your application must be listening on the local web
     * server. This is possible on many, but not all, platforms. If your
     * platform supports it, this is the recommended mechanism for obtaining the
     * authorization code.
     */
    private static final String GOOGLE_REDIRECT_URI_LOCALHOST = "http://localhost";

    /**
     * This value signals to the Google Authorization Server that the
     * authorization code should be returned in the title bar of the browser,
     * with the page text prompting the user to copy the code and paste it in
     * the application (as shown in the screenshot above). This is useful when
     * the client (such as a Windows application) cannot listen on an HTTP port
     * without significant client configuration.
     *
     * When you use this value, your application can then detect that the page
     * has loaded, and can read the title of the HTML page to obtain the
     * authorization code. It is then up to your application to close the
     * browser window if you want to ensure that the user never sees the page
     * that contains the authorization code. The mechanism for doing this varies
     * from platform to platform.
     *
     * If your platform doesn't allow you to detect that the page has loaded or
     * read the title of the page, you can have the user paste the code back to
     * your application, as prompted by the page text.
     */
    private static final String GOOGLE_REDIRECT_URI_COPY_PASTE = "urn:ietf:wg:oauth:2.0:oob";

    /**
     * This is identical to urn:ietf:wg:oauth:2.0:oob, but the text in the
     * confirmation page won't instruct the user to copy the authorization code,
     * but instead will simply ask the user to close the window.
     *
     * This is useful when your application reads the title of the HTML page (by
     * checking window titles on the desktop, for example) to obtain the
     * authorization code, but can't close the page on its own.
     */
    private static final String GOOGLE_REDIRECT_URI_AUTO = "urn:ietf:wg:oauth:2.0:oob:auto";

    private static final String CLIENT_ID = "389583572926-oolebtf76pb8ppfoi238u8242s772pnj.apps.googleusercontent.com";
    private static final String CLIENT_SECRET = "7Vi6AliTJQVbFhHCtL_-blTq";

}

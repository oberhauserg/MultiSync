# MultiSync
MultiSync library for Java. MultiSync helps developers get cloud storage solutions written quickly. No more jumping though a million hoops to deal with everything from OAuth to uploading and downloading files

----

MultiSync is built upon the idea of being easily expandable. We achieve this by using polymorphism. 

The parent class is the **Syncer** class. This class is abstract and defines a few functions that are *maditory* for child classes to override. 
To start off, the maditory functions are:

* getOAuth2URL() - This function is pretty self explainitory. It gets the url for the OAuth 2 authentication flow that the service gives back. This will most likely be downgraded from maditory to optional if this service expands into FTP services and the like.

* finishOAuth2(String accessToken) - This function gets the authentication token that the user copies and pastes into the program, or the program gets by using a redirect URI.

* getMetadata(String path) - This function gets the metadata from a path on the cloud storage service. It returns a Metadata object that contains feilds that most cloud storage services have available for metadata 

* getFileContent(String path) - This function gets the content of a file at the path and returns it as a String

* writeContent(String path, String content) - This function writes content to the specified file

* writeFile(String path, File file) - This function writes the content of a local file to the path specified on the cloud service.

* deleteFile(String path) - Deletes the specified path on the cloud storage service

This was done so that a developer doesn't have to ask if one of these functions will work, it just does. 

For example, a developer will be able to simply check which service to upload to, and easily upload. Like this:
   
    Syncer aSyncer; 
   
    if(useDropbox)
      aSyncer = new DropboxSyncer("someAppKey", "someAppSecret", "someAccessToken", "someClientIdentifier");
    else
      aSyncer = new GoogleDriveSyncer("someAppKey", "someAppSecret", "someAccessToken", "someClientIdentifier");
    
    aSyncer.writeFile("/Info.dat", new File("Info.dat"));

As you can see, this library makes snap decisions a breeze. That code compresses many lines of code into only 8 lines. 

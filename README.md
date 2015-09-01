# android_hostApp
The "hostApp" folder contains project files for the host-application. These project files are used for launching and deploying your Android Feature. Normally there is no need to change these files.

The "replacement" folder contains files and folders of the project that serves as a template for your Android Feature. This is the place where you will allocate your source code.

The "res" folder (in "replacement" folder) is used to store a user-generated content (images, audio files, text files, configuration files, etc.) that is required for the Feature you develop.

To properly register your Android Feature, the iBuildApp SDK requires that project files follow this convention:

Resource file names should start with Feature ID, i.e.

  
  replacement_small.png (image resource file)  
  replacement_config.xml (XML resource file)  

When uploading your Feature on iBuildApp.com website, the validation system will verify that all files are named in accordance with the file naming convention described above. If not verified, the Feature will not be uploaded!

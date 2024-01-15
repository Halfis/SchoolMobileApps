# Project 8 - Saving Data: Files

## Overview

This Android application, designed without a traditional user interface, focuses on storing a given message as a file in three different locations:

1. Internal storage (private mode)
2. External storage (public mode)
3. External storage (private mode)

Each operation prompts a confirmation message. To write to public external storage, the user must grant suitable permission. The result of the app's work, which includes stored files, can be observed using a file explorer such as Total Commander.

## Sample

1. **Saving in Internal Storage:**
   - The application saves the message in internal storage.

2. **Request for Permission to Write in Public External Storage:**
   - The app prompts the user for permission to write in public external storage.

3. **Disagreement Throws an Exception - Permission Denied:**
   - If the user denies permission, an exception is thrown.

4. **Granting Permission Saves the File in Public External Storage:**
   - Upon granting permission, the file is saved in public external storage.

5. **File Written in Private External Storage Regardless of Permission Decision:**
   - The app ensures the file is written in private external storage, regardless of the user's decision regarding permission.

6. **Storage Locations Details:**
   - Internal storage data is stored in `/data/data/Files/files` directory - access to which is restricted on phones without root permission.
   - Public external storage data is stored in a chosen directory inside `/mnt/sdcard/`, only if the user has granted permission.
   - Private external storage data is stored in a chosen directory inside `/mnt/sdcard/Android/data/Files/files/`.

## Resources

- [Data and File Storage Overview | Android Developers](https://developer.android.com/training/data-storage)
- [App Data and Files | Android Developers](https://developer.android.com/training/data-storage/files)
- [Android 6.0 Marshmallow: Cannot Write to SD Card - Stack Overflow](https://stackoverflow.com/questions/33198925/android-6-0-marshmallow-cannot-write-to-sd-card)

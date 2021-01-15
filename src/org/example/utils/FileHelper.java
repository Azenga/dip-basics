package org.example.utils;

import java.io.File;

public class FileHelper {

    public static String getFileExtension(File file) {

        return file.getName().substring(file.getName().lastIndexOf('.') + 1);
    }
}

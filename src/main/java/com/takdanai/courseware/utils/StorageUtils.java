package com.takdanai.courseware.utils;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

public class StorageUtils {
    public static File getResourceAsFile(String relativePath) throws FileNotFoundException {
        return ResourceUtils.getFile(relativePath);
    }

    public static String getFileType(String fileName) {
        var splitFileName = fileName.split("\\.");
        return splitFileName[splitFileName.length - 1];
    }
}

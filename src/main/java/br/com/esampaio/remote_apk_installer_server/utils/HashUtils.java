package br.com.esampaio.remote_apk_installer_server.utils;

import com.evanhoffman.messagedigest.SHA1;

import java.io.*;

public class HashUtils {
    public static void generateHashAndSave(File inputFile, File outputFile) {
        try {
            byte[] sha1 = SHA1.getSHA1(inputFile);
            FileUtils.saveBytesInFile(outputFile.getAbsolutePath(), sha1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static  byte[] generateHashAndSave(File inputFile) throws FileNotFoundException {
        byte[] sha1 = SHA1.getSHA1(inputFile);
        return sha1;
    }
}

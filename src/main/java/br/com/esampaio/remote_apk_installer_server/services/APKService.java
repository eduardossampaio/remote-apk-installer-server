package br.com.esampaio.remote_apk_installer_server.services;

import br.com.esampaio.remote_apk_installer_server.utils.APKUtils;
import br.com.esampaio.remote_apk_installer_server.utils.DateUtils;
import br.com.esampaio.remote_apk_installer_server.utils.FileUtils;
import br.com.esampaio.remote_apk_installer_server.utils.HashUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;

public class APKService {
    public static final String APK_BASE_DIR = "apks";
    public static final Integer BUFFER_SIZE = 1024 * 1024;

    public static void addNewAPK(String filePath) throws IOException {
        File sourceFile = new File(filePath);
        if (!sourceFile.exists()) {
            throw new FileNotFoundException("File not exists");
        }
        if(isAlreadyAdded(filePath)){
            throw new IllegalStateException("Apk is already added");
        }
        FileUtils.createDirIfNotExists(APK_BASE_DIR);
        String apkPackage = APKUtils.getApkPackage(filePath);
        String currentDate = DateUtils.toString(new Date(System.currentTimeMillis()), "dd-MM-yyyy-HH-mm");
        File apkDirectory = Paths.get(APK_BASE_DIR, apkPackage, currentDate).toFile();
        FileUtils.createDirIfNotExists(apkDirectory);
        HashUtils.generateHashAndSave(sourceFile, new File(apkDirectory, "checksum"));

        RandomAccessFile randomAccessFile = null;
        byte[] buffer = new byte[BUFFER_SIZE];
        randomAccessFile = new RandomAccessFile(sourceFile, "r");
        int bytesRead = 0;
        int indexFile = 0;
        while ((bytesRead = randomAccessFile.read(buffer)) > 0) {
            File partFile = new File(apkDirectory, "" + indexFile);
            FileUtils.saveBytesInFile(partFile.getAbsolutePath(), buffer, bytesRead);
            indexFile++;
        }
    }

    public static boolean isAlreadyAdded(String filePath) throws IOException {
        File sourceFile = new File(filePath);
        if (!sourceFile.exists()) {
            return false;
        }
        byte[] checksum = HashUtils.generateHashAndSave(sourceFile);
        String apkPackage = APKUtils.getApkPackage(filePath);
        File apkDirectory = new File(APK_BASE_DIR,apkPackage);
        for(File directory:apkDirectory.listFiles()){
            byte[] otherChecksum = FileUtils.readBytes(new File(directory,"checksum"));
            if(Arrays.equals(checksum,otherChecksum)){
                return true;
            }
        }
        return false;
    }
}

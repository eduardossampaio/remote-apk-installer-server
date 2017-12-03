package br.com.esampaio.remote_apk_installer_server.services;

import br.com.esampaio.remote_apk_installer_server.entities.ApkFile;
import br.com.esampaio.remote_apk_installer_server.utils.APKUtils;
import br.com.esampaio.remote_apk_installer_server.utils.DateUtils;
import br.com.esampaio.remote_apk_installer_server.utils.FileUtils;
import br.com.esampaio.remote_apk_installer_server.utils.HashUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.*;

public class APKService {
    public static final String APK_BASE_DIR = "apks";
    public static final Integer BUFFER_SIZE = 1024 * 1024;
    public static final String SOURCE_APK_FILE_NAME = "source";
    public static final String APK_CHECKSUM_FILE_NAME = "checksum";
    public static final String APK_ADDED_DATE_FORMAT = "dd-MM-yyyy-HH-mm";

    public static void addNewAPK(String filePath) throws IOException {
        File sourceFile = new File(filePath);
        if (!sourceFile.exists()) {
            throw new FileNotFoundException("File not exists");
        }
        FileUtils.createDirIfNotExists(APK_BASE_DIR);
        if(isAlreadyAdded(filePath)){
            throw new IllegalStateException("Apk is already added");
        }

        ApkFile apkFile = new ApkFile(filePath);
        String currentDate = DateUtils.toString(new Date(System.currentTimeMillis()), APK_ADDED_DATE_FORMAT);
        File apkDirectory = Paths.get(APK_BASE_DIR, apkFile.getPackageName(), currentDate).toFile();
        FileUtils.createDirIfNotExists(apkDirectory);
        HashUtils.generateHashAndSave(sourceFile, new File(apkDirectory, APK_CHECKSUM_FILE_NAME));
        FileUtils.copyFile(filePath,apkDirectory.getAbsolutePath(),SOURCE_APK_FILE_NAME);

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
        randomAccessFile.close();
    }

    public static boolean isAlreadyAdded(String filePath) throws IOException {
        File sourceFile = new File(filePath);
        if (!sourceFile.exists()) {
            return false;
        }
        byte[] checksum = HashUtils.generateHashAndSave(sourceFile);
        ApkFile apkFile = new ApkFile(filePath);
        File apkDirectory = new File(APK_BASE_DIR,apkFile.getPackageName());
        File[] subdir = apkDirectory.listFiles();
        if(subdir==null){
            return false;
        }
        for(File directory:subdir){
            byte[] otherChecksum = FileUtils.readBytes(new File(directory,APK_CHECKSUM_FILE_NAME));
            if(Arrays.equals(checksum,otherChecksum)){
                return true;
            }
        }
        return false;
    }

    public static List<ApkFile> listApks() throws IOException {
        File apkDirectory = new File(APK_BASE_DIR);
        List<ApkFile> apkFiles = new ArrayList<>();
        for(File packagesDirectory:apkDirectory.listFiles()){
            for(File versionDirectory:packagesDirectory.listFiles()) {
                File apkFile = new File(versionDirectory, SOURCE_APK_FILE_NAME);
                ApkFile apk = new ApkFile(apkFile);
                try {
                    apk.setAddedDate(DateUtils.toDate(versionDirectory.getName(),APK_ADDED_DATE_FORMAT));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                byte[] checksum = FileUtils.readBytes(new File(versionDirectory,APK_CHECKSUM_FILE_NAME));
                apk.setChecksum(new String(Base64.getEncoder().encode(checksum)));
                apkFiles.add(apk);
            }
        }
        return apkFiles;
    }
}

package br.com.esampaio.remote_apk_installer_server.services;

import br.com.esampaio.remote_apk_installer_server.entities.Apk;
import br.com.esampaio.remote_apk_installer_server.utils.APKUtils;
import br.com.esampaio.remote_apk_installer_server.utils.DateUtils;
import br.com.esampaio.remote_apk_installer_server.utils.FileUtils;
import br.com.esampaio.remote_apk_installer_server.utils.HashUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.*;

public class APKService {
    public static final String APK_CHECKSUM_FILE_NAME = "checksum";

    public static void addNewAPK(String filePath) throws IOException {
        File sourceFile = new File(filePath);
        if (!sourceFile.exists()) {
            throw new FileNotFoundException("File not exists");
        }
        if(isAlreadyAdded(filePath)){
            throw new IllegalStateException("Apk is already added");
        }
        APKDAO.addNewAPk(filePath);
    }

    public static boolean isAlreadyAdded(String filePath) throws IOException {
       return APKDAO.isAlreadyAdded(filePath);
    }

    public static List<Apk> listApks() throws IOException {
       return APKDAO.getAllAPks();
    }

    public static Apk getApkByChecksum(String checksum) throws IOException {
        return APKDAO.getApkByChecksum(checksum);
    }

}

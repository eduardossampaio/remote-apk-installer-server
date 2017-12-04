package br.com.esampaio.remote_apk_installer_server.services;

import br.com.esampaio.remote_apk_installer_server.entities.Apk;
import br.com.esampaio.remote_apk_installer_server.utils.APKUtils;
import br.com.esampaio.remote_apk_installer_server.utils.FileUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class APKDAO {
    private static final String APK_DATA_FILE = "apks.json";
    private static final String APK_FILES_DIR = "apks";

    static {
        try {
            FileUtils.createFileIfNotExists(APK_DATA_FILE);
            FileUtils.createDirIfNotExists(APK_FILES_DIR);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<Apk> getAllAPks() throws IOException {
        Gson gson = new Gson();
        byte[] jsonBytes = FileUtils.readBytes(new File(APK_DATA_FILE));
        Type listType = new TypeToken<ArrayList<Apk>>() {}.getType();
        List<Apk> apks = gson.fromJson(new String(jsonBytes), listType);
        if (apks == null) {
            apks = new ArrayList<>();
        }
        return apks;
    }

    public static void addNewAPk(String apkFile) throws IOException {
        Gson gson = new Gson();
        List<Apk> apks = getAllAPks();
        Apk newAPK = APKUtils.buildAPK(apkFile);
        apks.add(newAPK);
        String newJson = gson.toJson(apks);
        FileUtils.saveStringInFile(APK_DATA_FILE, newJson);
        FileUtils.copyFile(apkFile,APK_FILES_DIR,newAPK.getChecksum());
    }

    public static Apk getApkByChecksum(String checksum) throws IOException {
        List<Apk> apks = getAllAPks();
        for(Apk apk : apks){
            if(apk.getChecksum().equals(checksum)){
                return apk;
            }
        }
        return null;
    }

    public static File getApkFiles(Apk apk) throws IOException {
        File file = new File(APK_FILES_DIR,apk.getChecksum());
        if(file.exists()){
            return file;
        }else{
            throw new FileNotFoundException();
        }
    }

    public static boolean isAlreadyAdded(String apkFile) throws IOException {
        List<Apk> apks = getAllAPks();
        Apk apkToCompare = APKUtils.buildAPK(apkFile);
        for(Apk apk : apks){
            if(apk.equals(apkToCompare)){
                return true;
            }
        }
        return false;
    }
}

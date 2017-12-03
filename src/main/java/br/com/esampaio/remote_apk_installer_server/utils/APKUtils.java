package br.com.esampaio.remote_apk_installer_server.utils;

import net.dongliu.apk.parser.ApkFile;
import net.dongliu.apk.parser.bean.ApkMeta;

import java.io.File;

public class APKUtils {

    public static String getApkPackage(String filePath){
        try (ApkFile apkFile = new ApkFile(new File(filePath))) {
            ApkMeta apkMeta = apkFile.getApkMeta();
            return apkMeta.getPackageName();
        }catch (Exception e){

        }
        return null;
    }
}

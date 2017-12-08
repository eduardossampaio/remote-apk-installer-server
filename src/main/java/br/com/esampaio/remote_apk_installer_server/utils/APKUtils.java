package br.com.esampaio.remote_apk_installer_server.utils;

import br.com.esampaio.remote_apk_installer_server.entities.Apk;
import net.dongliu.apk.parser.bean.ApkMeta;
import net.dongliu.apk.parser.bean.Icon;

import java.io.File;
import java.util.Base64;
import java.util.Date;

public class APKUtils {
    public static Apk buildAPK(String filePath){
        return buildAPK(new File(filePath));
    }
    public static Apk buildAPK(File file){
        Apk buildedApk = new Apk();
        try (net.dongliu.apk.parser.ApkFile apkFile = new net.dongliu.apk.parser.ApkFile(file)) {
            ApkMeta apkMeta = apkFile.getApkMeta();
            String appName = apkMeta.getName();
            String packageName = apkMeta.getPackageName();
            String versionName = apkMeta.getVersionName();
            Long versionCode = apkMeta.getVersionCode();
            Icon iconFile = apkFile.getIconFile();
            byte[] iconBytes = iconFile.getData();
            String encodedIconFile = Base64.getUrlEncoder().encodeToString(iconBytes);
            byte[] checksum = HashUtils.generateHashAndSave(file);
            Date addedDate = new Date(System.currentTimeMillis());
            buildedApk.setAppName(appName);
            buildedApk.setPackageName(packageName);
            buildedApk.setVersionName(versionName);
            buildedApk.setVersionCode(versionCode);
            buildedApk.setChecksum(Base64.getUrlEncoder().encodeToString(checksum));
            buildedApk.setAddedDate(addedDate);
            buildedApk.setIconFile(encodedIconFile);
            return buildedApk;
        }catch (Exception e){
            throw new IllegalStateException("failed to create file");
        }
    }
}

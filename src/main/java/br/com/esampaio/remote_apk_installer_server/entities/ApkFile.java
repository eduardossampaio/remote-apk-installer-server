package br.com.esampaio.remote_apk_installer_server.entities;

import net.dongliu.apk.parser.bean.ApkMeta;

import java.io.File;
import java.util.Date;

public class ApkFile {
    private String appName;
    private String packageName;
    private String versionName;
    private Long versionCode;
    private Date addedDate;
    private String checksum;

    public ApkFile(String filePath){
        this(new File(filePath));
    }
    public ApkFile(File file){
        try (net.dongliu.apk.parser.ApkFile apkFile = new net.dongliu.apk.parser.ApkFile(file)) {
            ApkMeta apkMeta = apkFile.getApkMeta();
            appName = apkMeta.getName();
            packageName = apkMeta.getPackageName();
            versionName = apkMeta.getVersionName();
            versionCode = apkMeta.getVersionCode();
        }catch (Exception e){

        }

    }

    @Override
    public String toString() {
        return "ApkFile{" +
                "appName='" + appName + '\'' +
                ", packageName='" + packageName + '\'' +
                ", versionName='" + versionName + '\'' +
                ", versionCode=" + versionCode +
                ", addedDate=" + addedDate +
                ", checksum='" + checksum + '\'' +
                '}';
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public ApkFile(String packageName, String versionName, Long versionCode) {
        this.packageName = packageName;
        this.versionName = versionName;
        this.versionCode = versionCode;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public Long getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(Long versionCode) {
        this.versionCode = versionCode;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }
}

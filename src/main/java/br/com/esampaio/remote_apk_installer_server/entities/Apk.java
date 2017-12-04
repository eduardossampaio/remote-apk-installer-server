package br.com.esampaio.remote_apk_installer_server.entities;

import net.dongliu.apk.parser.bean.ApkMeta;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

public class Apk implements Serializable{
    private String appName;
    private String packageName;
    private String versionName;
    private Long versionCode;
    private Date addedDate;
    private String checksum;
    private String localFilePath;

    @Override
    public String toString() {
        return "Apk{" +
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Apk apk = (Apk) o;

        return checksum.equals(apk.checksum);
    }

    @Override
    public int hashCode() {
        return checksum.hashCode();
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public void setLocalFilePath(String localFilePath) {
        this.localFilePath = localFilePath;
    }

    public String getLocalFilePath() {
        return localFilePath;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
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

package br.com.esampaio.remote_apk_installer_server;

import br.com.esampaio.remote_apk_installer_server.entities.ApkFile;
import br.com.esampaio.remote_apk_installer_server.services.APKService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class RemoteApkInstallerServerApplication {

	public static void main(String[] args) {
        List<ApkFile> apkFiles = null;
        try {
            apkFiles = APKService.listApks();
            for(ApkFile apkFile : apkFiles){
                System.out.println(apkFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

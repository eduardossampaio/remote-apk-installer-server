package br.com.esampaio.remote_apk_installer_server;

import br.com.esampaio.remote_apk_installer_server.services.APKService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class RemoteApkInstallerServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RemoteApkInstallerServerApplication.class, args);
    }
}

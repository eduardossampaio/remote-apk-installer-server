package br.com.esampaio.remote_apk_installer_server.controllers.apk;

import br.com.esampaio.remote_apk_installer_server.controllers.entities.response.ResponseHeader;
import br.com.esampaio.remote_apk_installer_server.controllers.entities.response.ResponseModel;
import br.com.esampaio.remote_apk_installer_server.entities.ApkFile;
import br.com.esampaio.remote_apk_installer_server.services.APKService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController()
@RequestMapping("/apk")
public class APKController {


    @ResponseBody
    @RequestMapping(value = "/addNew", method = RequestMethod.POST)
    public ResponseModel<Void> addNewApk(@RequestBody AddAPKRequest request) {
        try{
            APKService.addNewAPK(request.file);
            return new ResponseModel<>(new ResponseHeader(0, "OK"), null);
        }catch (Exception e){
            return new ResponseModel<>(new ResponseHeader(1, e.getMessage()), null);
        }
    }

    @RequestMapping(path = "/download", method = RequestMethod.GET)
    public ResponseEntity<Resource> download(String checksum) throws IOException {

        File file = APKService.getApkFileByChecksum(checksum);
        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        return ResponseEntity.ok()
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }

    @RequestMapping(path = "/listAll")
    public ResponseModel<List<ApkFile>> listAll() throws IOException {
        try{
            List<ApkFile> apkFiles = APKService.listApks();
            return new ResponseModel<List<ApkFile>>(new ResponseHeader(0, "OK"), apkFiles);
        }catch (Exception e){
            return new ResponseModel<>(new ResponseHeader(1, e.getMessage()), null);
        }
    }
}

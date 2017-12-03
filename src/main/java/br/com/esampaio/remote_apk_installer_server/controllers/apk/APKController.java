package br.com.esampaio.remote_apk_installer_server.controllers.apk;

import br.com.esampaio.remote_apk_installer_server.controllers.entities.response.ResponseHeader;
import br.com.esampaio.remote_apk_installer_server.controllers.entities.response.ResponseModel;
import br.com.esampaio.remote_apk_installer_server.services.APKService;
import org.springframework.web.bind.annotation.*;

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

}

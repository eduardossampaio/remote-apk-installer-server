package br.com.esampaio.remote_apk_installer_server.controllers.device;

import br.com.esampaio.remote_apk_installer_server.controllers.entities.response.ResponseHeader;
import br.com.esampaio.remote_apk_installer_server.controllers.entities.response.ResponseModel;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/device")
public class DeviceController {

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel<RegisterDeviceParam> registerDevice(@RequestBody  RegisterDeviceParam registerDeviceParam ) {
        return new ResponseModel<RegisterDeviceParam>(new ResponseHeader(0),registerDeviceParam);
    }
}

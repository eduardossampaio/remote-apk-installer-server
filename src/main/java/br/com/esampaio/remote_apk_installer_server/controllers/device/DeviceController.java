package br.com.esampaio.remote_apk_installer_server.controllers.device;

import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/device")
public class DeviceController {

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public RegisterDeviceParam registerDevice(@RequestBody  RegisterDeviceParam registerDeviceParam ) {
        return registerDeviceParam;
    }
}

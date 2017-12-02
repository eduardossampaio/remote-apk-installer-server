package br.com.esampaio.remote_apk_installer_server.remote.apk.installer.server;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class TesteController {

    @RequestMapping("/teste")
    @ResponseBody
    String home() {
        return "Hello World!";
    }
}

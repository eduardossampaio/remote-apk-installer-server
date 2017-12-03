package br.com.esampaio.remote_apk_installer_server.controllers;

import net.dongliu.apk.parser.ApkFile;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class TestController {

    @RequestMapping("/test")
    @ResponseBody
    public String home() {
        return "Hello World!";
    }
}

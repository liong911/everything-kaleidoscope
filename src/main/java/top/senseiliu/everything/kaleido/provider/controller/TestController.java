package top.senseiliu.everything.kaleido.provider.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/demo")
public class TestController {

    @GetMapping("/get")
    public String get() throws Exception {
        return "demo";
    }

}

package com.ramesh.springboot.myfirstwebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @RequestMapping("say-hello")
    @ResponseBody
    public String sayHello() {
        return "Hello! what are you learning today ?";
    }

    @RequestMapping("say-hello-html")
    @ResponseBody
    public String sayHelloHtml() {
        return "<html>" +
                "<head>" +
                "<title> My first HTML page</title>" +
                "</head>" +
                "<body>" +
                "my first HTML page with body" +
                "</body>" +
                "</html>";
    }

    @RequestMapping("say-hello-jsp")
    public String sayHelloJSP() {
        return "sayHello";
    }
}

package com.alibaba.sls.demo.application.controller;

import com.alibaba.sls.demo.application.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@Autowired
	private HelloWorldService helloWorldService;

	@RequestMapping("/hello")
	public String sayHello() {
		return HelloWorldService.sayHello();
	}

}

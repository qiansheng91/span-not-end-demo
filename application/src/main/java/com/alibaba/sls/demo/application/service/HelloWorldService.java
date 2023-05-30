package com.alibaba.sls.demo.application.service;

import org.springframework.stereotype.Service;

@Service
public class HelloWorldService {

	public static String sayHello() {
		try {
			Thread.sleep(5 * 1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		return "Hello World!";
	}
}

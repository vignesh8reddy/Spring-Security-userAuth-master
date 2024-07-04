package com.springsec.practice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloResource {
	@GetMapping("/user")
	public String sayHello() {
		return "Hello user";
	}
	@GetMapping("/admin")
	public String sayHelloAdmin() {
		return "Hello admin";
	}
}

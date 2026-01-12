package com.ofss.main.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("greetingsapi")
public class GreetingsController {
	
	//http://localhost:8080/greetingsapi/greet
	@GetMapping("greet")
	public String greet() {
		return "Hello World";
	}
}

package com.ofss.main.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("greetingsapi")
public class GreetingsController {

	public GreetingsController() {
		log.info("GreetingsController object created");
	}

	@GetMapping("greet")
	public String greet() {
		log.info("greet() called");
		return "It is working !!";
	}

}

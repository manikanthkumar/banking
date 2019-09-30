package com.assign.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assign.entity.Counter;
import com.assign.service.CounterService;

@RestController
@RequestMapping("/counters")
public class CounterController {

	@Autowired
	private CounterService service;

	@PostMapping("/")
	public Counter createCounter(@RequestBody Counter counter) {
		return service.createCounter(counter);
	}

	@GetMapping("/")
	public List<Counter> getCounterInfo() {
		return service.getAllCounters();
	}
}

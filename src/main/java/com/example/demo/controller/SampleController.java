package com.example.demo.controller;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.SampleService;

@RestController
@RequestMapping(value = "/sample", produces = "application/json")
public class SampleController {
	
	@Autowired
	 SampleService sampleService;
	
	@GetMapping("smiles")
	ResponseEntity<?>   retrieveSmileData() {
	      List<LinkedHashMap<String, Object>> map = sampleService.retrieveSmileData();
		return ResponseEntity.ok(map);
	}
	
	@GetMapping("smiles/filter")
	ResponseEntity<?>   findSmileData(@RequestParam(value = "key") final String srcSmile) {
		List<LinkedHashMap<String,Object>> map = sampleService.findSmileData(srcSmile);
		return ResponseEntity.ok(map);
	}

}

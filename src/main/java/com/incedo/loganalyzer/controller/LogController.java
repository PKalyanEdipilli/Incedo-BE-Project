package com.incedo.loganalyzer.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.incedo.loganalyzer.model.Log;
import com.incedo.loganalyzer.service.LogService;

@RestController
@RequestMapping("/api")
public class LogController {

	@Autowired
	private LogService logService;

	@PostMapping("/addLog")
	public ResponseEntity<Log> addLog(@RequestBody Log log) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTimestamp = LocalDateTime.now().format(formatter);
		log.setTimestamp(formattedTimestamp);
		Log savedLog = logService.save(log);
		return new ResponseEntity<>(savedLog, HttpStatus.CREATED);
	}

	@GetMapping("/allLogs")
	public ResponseEntity<Object> getAllLogs() {
		Object logs = logService.getAllLogs();
		return new ResponseEntity<>(logs, HttpStatus.OK);
	}

	@GetMapping(path = "/searchByLog", params = "message")
	public ResponseEntity<Object> getLogsByMessage(@RequestParam String message) {
		Object logs = logService.getLogsByMessage(message);
		return new ResponseEntity<>(logs, HttpStatus.OK);
	}

	@DeleteMapping("/deleteAllLogs")
	public ResponseEntity<String> deleteAllLogs() {
		String message = logService.deleteAllLogs();
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
}
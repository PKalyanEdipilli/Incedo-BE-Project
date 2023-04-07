package com.incedo.loganalyzer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.incedo.loganalyzer.model.Log;
import com.incedo.loganalyzer.repository.LogRepository;

@Service
public class LogServiceImpl implements LogService {

	@Autowired
	private LogRepository logRepository;

	@Override
	public Log save(Log log) {
		return logRepository.save(log);
	}

	@Override
	public Object getAllLogs() {
		List<Log> logs = logRepository.findAll();
		if (logs.isEmpty()) {
			return "Empty logs";
		}
		return logs;
	}

	@Override
	public Object getLogsByMessage(String message) {
		List<Log> logs = logRepository.findAllByMessage(message);
		if (logs.isEmpty()) {
			return "No logs with message "+message;
		}
		return logs;
	}

	@Override
	public String deleteAllLogs() {
		List<Log> logs = logRepository.findAll();
		if (logs.isEmpty()) {
			return "Nothing to delete, no logs present";
		}
		logRepository.deleteAll();
		if (logRepository.findAll().isEmpty()) {
			return "All logs deleted";
		}
		return "Delete failed!";
	}
}

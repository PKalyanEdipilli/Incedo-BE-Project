package com.incedo.loganalyzer.service;

import com.incedo.loganalyzer.model.Log;

public interface LogService {

	Log save(Log log);

	Object getAllLogs();

	Object getLogsByMessage(String message);

	String deleteAllLogs();

}

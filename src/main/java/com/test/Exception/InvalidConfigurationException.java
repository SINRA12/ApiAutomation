package com.test.Exception;

import com.test.customReporter.CustomLogger;

public class InvalidConfigurationException extends RuntimeException {
	private static final long serialVersionUID = 8617043525402250600L;

	public InvalidConfigurationException(Exception e) {
		super(e);
		CustomLogger.logFail(e.getMessage());
	}

	public InvalidConfigurationException(String message) {
		super(message);
		CustomLogger.logFail(message);
	}
}

package com.test.Exception;

//
//Source code recreated from a .class file by IntelliJ IDEA
//(powered by FernFlower decompiler)
//


import com.test.customReporter.CustomLogger;

public class SSHExecutionException extends RuntimeException {
	public SSHExecutionException(Exception e) {
		super(e);
		CustomLogger.logFail(e.getMessage());
	}

	public SSHExecutionException(String message) {
		super(message);
		CustomLogger.logFail(message);
	}
}

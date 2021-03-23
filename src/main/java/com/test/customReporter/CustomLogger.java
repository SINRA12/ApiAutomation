package com.test.customReporter;


import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class CustomLogger {
	public CustomLogger() {
	}

	public static synchronized void logPass(String log) {
		if (ExtentManager.getTest().get() != null) {
			((ExtentTest) ExtentManager.getTest().get()).log(Status.PASS, log);
		}

	}

	public static synchronized void logFail(String log) {
		if (ExtentManager.getTest().get() != null) {
			((ExtentTest) ExtentManager.getTest().get()).log(Status.FAIL, log);
		}

	}

	public static synchronized void logSkip(String log) {
		if (ExtentManager.getTest().get() != null) {
			((ExtentTest) ExtentManager.getTest().get()).log(Status.SKIP,
					MarkupHelper.createLabel(log, ExtentColor.ORANGE));
		}

	}

	public static synchronized void logDebug(String log) {
		if (ExtentManager.getTest().get() != null) {
			((ExtentTest) ExtentManager.getTest().get()).log(Status.DEBUG,
					MarkupHelper.createLabel(log, ExtentColor.LIME));
		}

	}

	public static synchronized void logDebug(String log, ExtentColor color) {
		if (ExtentManager.getTest().get() != null) {
			((ExtentTest) ExtentManager.getTest().get()).log(Status.DEBUG, MarkupHelper.createLabel(log, color));
		}

	}

	public static synchronized void logWarning(String log) {
		if (ExtentManager.getTest().get() != null) {
			((ExtentTest) ExtentManager.getTest().get()).log(Status.WARNING, log);
		}

	}

	public static synchronized void logException(Status status, Throwable throwable) {
		if (ExtentManager.getTest().get() != null) {
			((ExtentTest) ExtentManager.getTest().get()).log(status, throwable);
		}

	}

	public static synchronized void logCategory(String category) {
		if (ExtentManager.getTest().get() != null) {
			((ExtentTest) ExtentManager.getTest().get()).assignCategory(new String[] { category });
		}

	}
}
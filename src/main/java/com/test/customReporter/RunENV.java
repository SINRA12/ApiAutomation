package com.test.customReporter;

//

//Source code recreated from a .class file by IntelliJ IDEA
//(powered by FernFlower decompiler)
//

public enum RunENV {
	STAGING("staging"), DEV("development"), QA("qa"), TESTRUN("testrun");

	private String name;

	private RunENV(String name) {
		this.name = name;
	}

	public static RunENV isValidEnv(String env) {
		RunENV[] var1 = values();
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			RunENV runEnv = var1[var3];
			if (runEnv.getName().equalsIgnoreCase(env)) {
				return runEnv;
			}
		}

		return null;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

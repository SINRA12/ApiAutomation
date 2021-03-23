package com.test.Listner;

//
//Source code recreated from a .class file by IntelliJ IDEA
//(powered by FernFlower decompiler)
//

import com.test.common.steps.AddTestCasesInDB;
import com.test.customReporter.ExtentManager;
import com.test.dto.TestCaseDto;
import com.test.global.GlobalData;
import com.test.Util.AutomationTracker;
import com.test.Util.annotations.Owner;
import com.test.Util.mysql.DBBase;
import com.test.Util.mysql.MYSQLManager;
import com.test.Util.redis.RedisClient;
import com.test.Util.redis.RedisConnectionManager;
import com.test.Util.reportpublisher.PublisherUtils;
import com.test.Util.ssh.SSHConnectionBase;
import com.test.Util.ssh.SSHConnectionManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestNGMethod;
import org.testng.annotations.Test;

public class SuiteListener implements ISuiteListener {
	private static final Logger LOGGER = LoggerFactory.getLogger(SuiteListener.class);
	private static final long suiteStartTime = System.currentTimeMillis();
	public static String componentName;
	By passPercentage_Lbl = By.xpath("//span[contains(text(),'test(s) passed')]");

	public SuiteListener() {
	}

	public void onStart(ISuite iSuite) {
		this.createNewExtentFolder();
		componentName = iSuite.getName().toUpperCase();
		if (System.getProperty("trackAutomationStatus", "false").equalsIgnoreCase("true")) {
			try {
				Set<String> packages = this.getAllPackages(iSuite);
				AutomationTracker.trackStatus(iSuite.getName(), (String[]) packages.toArray(new String[0]));
				Set<TestCaseDto> validTestCases = this.generateTestCases(iSuite,
						(String[]) packages.toArray(new String[0]));
				if (validTestCases.size() > 0) {
					this.updateValidTestCaseInDB(validTestCases);
				}
			} catch (Exception var4) {
				LOGGER.error(var4.getMessage());
			}
		}

	}

	private void updateValidTestCaseInDB(Set<TestCaseDto> validTestCases) {
		LOGGER.info("list of valid testcases to be updated in database" + validTestCases.size());
		AddTestCasesInDB addTests = new AddTestCasesInDB(componentName, validTestCases);
		addTests.callAPI();
	}

	public void onFinish(ISuite iSuite) {
		long suiteEndTime = System.currentTimeMillis();
		PublisherUtils.addTestResultForDashboard(iSuite, suiteStartTime, suiteEndTime);

		try {
			Iterator var4 = SSHConnectionManager.getInstance().getConnectionMap().values().iterator();

			while (var4.hasNext()) {
				SSHConnectionBase connection = (SSHConnectionBase) var4.next();
				connection.closeSSHConnection();
			}

			var4 = MYSQLManager.getInstance().getDbMap().values().iterator();

			while (var4.hasNext()) {
				DBBase dbBase = (DBBase) var4.next();
				dbBase.closeDbConnection();
			}

			var4 = RedisConnectionManager.getInstance().getRedisPoolMap().values().iterator();

			while (var4.hasNext()) {
				RedisClient client = (RedisClient) var4.next();
				client.closeConnection();
			}
		} catch (Exception var10) {
			LOGGER.error("Error Closing Client Connections");
		}

		LOGGER.info("---------- Starting Phantom JS Driver ------------");

		try {
			WebDriverManager.phantomjs().setup();
			WebDriver driver = new PhantomJSDriver();
			driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
			File file = new File("extentreport/extentreport.html");
			driver.get(file.getAbsolutePath());
			GlobalData.PASSPERCENTAGE = driver.findElement(this.passPercentage_Lbl).getAttribute("data-tooltip");
			ExtentManager.addExecutionDetails_ExtentReport();
			driver.get(file.getAbsolutePath());
			driver.manage().window().maximize();
			Thread.sleep(2000L);
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript(GlobalData.JS_OPENDASHBOARD_EXTENTREPORTS, new Object[0]);
			String filePath = "extentreport/screenshots/reportscreenshot.png";
			File f = (File) ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(f, new File(filePath));
			driver.quit();
		} catch (Exception var9) {
			var9.printStackTrace();
		}

	}

	private void createNewExtentFolder() {
		Path extentFolderPath = Paths.get("extentreport");
		Path screenshotFolderPath = Paths.get("extentreport" + File.separator + "screenshots");
		if (Files.exists(extentFolderPath, new LinkOption[0])) {
			try {
				this.deleteDirectoryRecursively(extentFolderPath);
			} catch (IOException var5) {
				var5.printStackTrace();
			}
		}

		try {
			Files.createDirectory(extentFolderPath);
			Files.createDirectory(screenshotFolderPath);
		} catch (IOException var4) {
			var4.printStackTrace();
		}

	}

	void deleteDirectoryRecursively(Path path) throws IOException {
		if (Files.isDirectory(path, new LinkOption[] { LinkOption.NOFOLLOW_LINKS })) {
			DirectoryStream<Path> entries = Files.newDirectoryStream(path);
			Throwable var3 = null;

			try {
				Iterator var4 = entries.iterator();

				while (var4.hasNext()) {
					Path entry = (Path) var4.next();
					this.deleteDirectoryRecursively(entry);
				}
			} catch (Throwable var13) {
				var3 = var13;
				try {
					throw var13;
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} finally {
				if (entries != null) {
					if (var3 != null) {
						try {
							entries.close();
						} catch (Throwable var12) {
							var3.addSuppressed(var12);
						}
					} else {
						entries.close();
					}
				}

			}
		}

		Files.delete(path);
	}

	private boolean checkIfValidTestcase(TestCaseDto testcase) {
		String description = testcase.getTestDescription();
		String expectedResult = testcase.getExpectedResult();
		return !description.isEmpty() && !expectedResult.isEmpty();
	}

	private Set<TestCaseDto> generateTestCases(ISuite result, String[] packageToScan) {
		HashSet validTestCases = new HashSet();

		try {
			Set<Method> methodsWithOwnerAnnotation = AutomationTracker
					.getMethodsWithSpecifiedAnnotation(result.getName().toUpperCase(), packageToScan, Owner.class);
			Iterator var5 = methodsWithOwnerAnnotation.iterator();

			while (var5.hasNext()) {
				Method method = (Method) var5.next();
				if (method.isAnnotationPresent(Test.class)) {
					Owner ownerAnnotation = (Owner) method.getAnnotation(Owner.class);
					Test testAnnotation = (Test) method.getAnnotation(Test.class);
					TestCaseDto testcase = new TestCaseDto();
					Class clazz = method.getDeclaringClass();
					testcase.setTestCaseName(clazz.getName() + "." + method.getName());
					testcase.setExpectedResult(ownerAnnotation.expectedResult());
					testcase.setTestDescription(ownerAnnotation.testDescription());
					testcase.setEmailId(ownerAnnotation.emailId());
					testcase.setTraceability(ownerAnnotation.traceability());
					testcase.setAutomated(ownerAnnotation.isAutomated());
					if (this.checkIfValidTestcase(testcase)) {
						testcase.setGroupsDependedUpon(testAnnotation.dependsOnGroups());
						testcase.setGroups(testAnnotation.groups());
						testcase.setIsEnabled(testAnnotation.enabled());
						validTestCases.add(testcase);
					}
				}
			}
		} catch (Exception var11) {
			LOGGER.info("no valid annotation present to update testcase data");
		}

		return validTestCases;
	}

	private boolean isAnnotationpresent(ITestNGMethod iTestNGMethod, Class clazz) {
		return iTestNGMethod.getConstructorOrMethod().getMethod().getAnnotation(clazz) != null;
	}

	public Set<String> getAllPackages(ISuite iSuite) {
		Set<String> packages = new HashSet();
		Iterator var3 = iSuite.getAllMethods().iterator();

		while (var3.hasNext()) {
			ITestNGMethod o = (ITestNGMethod) var3.next();
			packages.add(o.getRealClass().getPackage().getName());
		}

		return packages;
	}
}

package com.test.Util;

import com.test.common.steps.PublishStats;
import com.test.customReporter.CustomReporter;
import com.test.dingtalk.DingManager;
import com.test.Util.annotations.Owner;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.log4j.Logger;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.Scanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import org.testng.annotations.Test;

public class AutomationTracker {
	protected static final Logger LOGGER = Logger.getLogger(CustomReporter.class);
	protected static final String dingGroupKey = "18d2907fb6355b5798e522e16b70c0c787f071c8105d390033cb8b912d1b5d4b";
	protected static final String screenShotPath = "https://ih0.redbubble.net/image.348623933.1363/st%2Csmall%2C215x235-pad%2C210x230%2Cf8f8f8.lite-1u1.jpg";
	static Map<String, Integer> tcOwnerMap = new HashMap();
	private static Integer unKnownOnwerCount = 0;

	public AutomationTracker() {
	}

	public static void trackStatus(String projectName, String[] packageToScan) throws Exception {
		ConfigurationBuilder confBuilder = new ConfigurationBuilder();
		FilterBuilder filterBuilder = new FilterBuilder();
		URL testClassesURLTest = Paths.get("target/test-classes").toUri().toURL();
		URL testClassesURLMain = Paths.get("target/classes").toUri().toURL();
		URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { testClassesURLMain, testClassesURLTest },
				ClasspathHelper.staticClassLoader());
		String[] var7 = packageToScan;
		int var8 = packageToScan.length;

		for (int var9 = 0; var9 < var8; ++var9) {
			String packageName = var7[var9];
			confBuilder.addUrls(ClasspathHelper.forPackage(packageName, new ClassLoader[] { classLoader }));
			filterBuilder.include(FilterBuilder.prefix(packageName));
		}

		confBuilder.filterInputsBy(filterBuilder).setScanners(new Scanner[] { new MethodAnnotationsScanner() });
		Reflections reflections = new Reflections(confBuilder);
		Set<Method> methodsHavingTestAnnotation = reflections.getMethodsAnnotatedWith(Test.class);
		Iterator var14 = methodsHavingTestAnnotation.iterator();

		while (true) {
			while (true) {
				Owner owner;
				label32: do {
					while (var14.hasNext()) {
						Method method = (Method) var14.next();
						if (method.isAnnotationPresent(Owner.class)) {
							owner = (Owner) method.getAnnotation(Owner.class);
							continue label32;
						}

						unKnownOnwerCount = unKnownOnwerCount + 1;
					}

					LOGGER.info("Final Data is :::::::::::: " + tcOwnerMap);
					tcOwnerMap.put("unknown", unKnownOnwerCount);
					PublishStats obj = new PublishStats(projectName, tcOwnerMap);
					obj.publishData();
					if (System.getProperty("sendTestCountOnDing", "false").equalsIgnoreCase("true")) {
						DingManager.sendMarkDownMessage(
								"18d2907fb6355b5798e522e16b70c0c787f071c8105d390033cb8b912d1b5d4b", projectName,
								tcOwnerMap, unKnownOnwerCount,
								"https://ih0.redbubble.net/image.348623933.1363/st%2Csmall%2C215x235-pad%2C210x230%2Cf8f8f8.lite-1u1.jpg");
					}

					return;
				} while (!owner.isAutomated());

				if (null != owner.emailId() && !owner.emailId().isEmpty()) {
					register(owner.emailId().toLowerCase());
				} else {
					unKnownOnwerCount = unKnownOnwerCount + 1;
				}
			}
		}
	}

	public static void register(String ownerName) {
		if (!tcOwnerMap.containsKey(ownerName)) {
			tcOwnerMap.put(ownerName, 1);
		} else {
			int intermediate = (Integer) tcOwnerMap.get(ownerName);
			++intermediate;
			tcOwnerMap.put(ownerName, intermediate);
		}

	}

	public static Set<Method> getMethodsWithSpecifiedAnnotation(String projectName, String[] packageToScan, Class clazz)
			throws Exception {
		ConfigurationBuilder confBuilder = new ConfigurationBuilder();
		FilterBuilder filterBuilder = new FilterBuilder();
		URL testClassesURLTest = Paths.get("target/test-classes").toUri().toURL();
		URL testClassesURLMain = Paths.get("target/classes").toUri().toURL();
		URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { testClassesURLMain, testClassesURLTest },
				ClasspathHelper.staticClassLoader());
		String[] var8 = packageToScan;
		int var9 = packageToScan.length;

		for (int var10 = 0; var10 < var9; ++var10) {
			String packageName = var8[var10];
			confBuilder.addUrls(ClasspathHelper.forPackage(packageName, new ClassLoader[] { classLoader }));
			filterBuilder.include(FilterBuilder.prefix(packageName));
		}

		confBuilder.filterInputsBy(filterBuilder).setScanners(new Scanner[] { new MethodAnnotationsScanner() });
		Reflections reflections = new Reflections(confBuilder);
		Set<Method> methodsWithAnnotation = reflections.getMethodsAnnotatedWith(clazz);
		return methodsWithAnnotation;
	}
}

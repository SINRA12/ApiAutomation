package com.test.core;

//
//Source code recreated from a .class file by IntelliJ IDEA
//(powered by FernFlower decompiler)
//
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.core.http.HttpClient;
import com.test.core.http.HttpMethodType;
import com.test.core.http.HttpResponseStatusType;
import com.test.core.http.MimeType;
import com.test.core.http.RequestParams;
import com.test.Util.LogPublisher;
import com.test.Listner.SuiteListener;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.xml.HasXPath;

public abstract class AbstractApi extends HttpClient {
	protected static final Logger LOGGER = Logger.getLogger(AbstractApi.class);
	public RequestSpecification request;
	protected RequestParams requestParams = new RequestParams();
	private String clientName;

	public AbstractApi() {
		this.clientName = null != SuiteListener.componentName ? SuiteListener.componentName : "DRY_RUN";
		this.init(this.getClass());
		this.request = RestAssured.given();
		this.requestParams.setMimeType(MimeType.TEXT_PLAIN);
		this.requestParams.addHeader("Content-Type", MimeType.TEXT_PLAIN.getValue());
	}

	public AbstractApi(MimeType mimeType) {
		this.clientName = null != SuiteListener.componentName ? SuiteListener.componentName : "DRY_RUN";
		this.init(this.getClass());
		this.request = RestAssured.given();
		this.requestParams.setMimeType(mimeType);
		this.requestParams.addHeader("Content-Type", mimeType.getValue());
	}

	public AbstractApi(MimeType mimeType, String uri, HttpMethodType methodType) {
		this.clientName = null != SuiteListener.componentName ? SuiteListener.componentName : "DRY_RUN";
		this.requestParams.setUri(uri);
		this.requestParams.setMethodType(methodType);
		this.requestParams.setMimeType(mimeType);
		this.requestParams.addHeader("Content-Type", mimeType.getValue());
		this.request = RestAssured.given();
		this.init(this.getClass());
	}

	public AbstractApi(String uri, HttpMethodType methodType) {
		this.clientName = null != SuiteListener.componentName ? SuiteListener.componentName : "DRY_RUN";
		this.requestParams.setUri(uri);
		this.requestParams.setMethodType(methodType);
		this.request = RestAssured.given();
		this.requestParams.setMimeType(MimeType.TEXT_PLAIN);
		this.requestParams.addHeader("Content-Type", MimeType.TEXT_PLAIN.getValue());
		this.init(this.getClass());
	}

	private void init(Class clazz) {
		String prop = P.API.get(clazz.getSimpleName());
		if (null != prop && !prop.isEmpty()) {
			if (prop.contains(":")) {
				String[] methods = prop.split(":");
				this.requestParams.setMethodType(HttpMethodType.valueOf(methods[0]));
				this.requestParams.setUri(methods[1]);
			} else {
				this.requestParams.setMethodType(HttpMethodType.valueOf(prop));
			}
		} else if (null == this.requestParams.getUri() || this.requestParams.getUri().isEmpty()
				|| null == this.requestParams.getMethodType()) {
			throw new RuntimeException("Method type and path are not specified for: " + clazz.getSimpleName());
		}

	}

	public void setHeaders(String... headerKeyValues) {
		String[] var2 = headerKeyValues;
		int var3 = headerKeyValues.length;

		for (int var4 = 0; var4 < var3; ++var4) {
			String headerKeyValue = var2[var4];
			String[] header = headerKeyValue.split("=");
			this.requestParams.addHeader(header[0], header[1]);
		}

	}

	public void setHeader(String key, String value) {
		this.requestParams.addHeader(key, value);
	}

	public void removeHeader(String... headerKey) {
		String[] var2 = headerKey;
		int var3 = headerKey.length;

		for (int var4 = 0; var4 < var3; ++var4) {
			String s = var2[var4];
			this.requestParams.removeHeader(s);
		}

	}

	public void addUrlParameter(String key, Object value) {
		if (key != null && value != null) {
			this.requestParams.addQueryParam(key, value.toString());
		}

	}

	public void addUrlParameters(Map<Object, Object> queryParams) {
		if (queryParams != null && queryParams.size() != 0) {
			queryParams.forEach((key, value) -> {
				try {
					if (value instanceof Map || value instanceof List || value instanceof Set) {
						value = (new ObjectMapper()).writeValueAsString(value);
					}

					this.requestParams.addQueryParam(key.toString(), value.toString());
				} catch (Exception var4) {
					var4.printStackTrace();
				}

			});
		}

	}

	public void addUrlParameters(Object object) {
		if (object != null) {
			Map<Object, Object> queryParams = (Map) (new ObjectMapper()).convertValue(object, Map.class);
			this.addUrlParameters(queryParams);
		}

	}

	public void addParameter(String key, String value) {
		this.requestParams.addQueryParam(key, value.replace(" ", "%20"));
	}

	public void addParameterIfNotNull(String key, String value) {
		if (value != null) {
			this.addParameter(key, value.replace(" ", "%20"));
		}

	}

	public void addFormParameter(String key, String value) {
		if (value != null) {
			this.requestParams.addFormParam(key, value.replace(" ", "%20"));
		}

	}

	public void addMultipartFormData(Map<String, Object> map) {
		this.requestParams.setMultipartParam(map);
	}

	/** @deprecated */
	@Deprecated
	public void addMultipartFormData(String key, Object value) {
		this.addMultipartFormData(key, value, (String) null);
	}

	public void addMultipartFormData(String key, Object value, String mimeType) {
		if (value instanceof File) {
			this.request.multiPart(key, (File) value, mimeType);
		} else {
			this.request.multiPart(key, value, mimeType);
		}

	}

	public void addCookie(String key, String value) {
		this.requestParams.addCookie(key, value);
	}

	public void addCookies(Map<String, String> cookies) {
		this.requestParams.getCookies().putAll(cookies);
	}

	public void replaceUrlPlaceholder(String placeholder, String value) {
		String methodPath = null;
		if (value != null) {
			methodPath = this.requestParams.getUri().replace("${" + placeholder + "}", value);
		} else {
			methodPath = this.requestParams.getUri().replace("${" + placeholder + "}", "");
			methodPath = StringUtils.removeEnd(methodPath, "/");
		}

		this.requestParams.setUri(methodPath);
	}

	public void expectResponseStatus(HttpResponseStatusType status) {
		this.request.expect().statusCode(status.getCode());
		this.request.expect().statusLine(Matchers.containsString(status.getMessage()));
	}

	public <T> void expectResponseContains(Matcher<T> key, Matcher<T> value) {
		this.request.expect().body(key, new Matcher[] { value });
	}

	public void expectValueByXpath(String xPath, String value) {
		this.request.expect().body(Matchers.hasXPath(xPath), new Matcher[] { Matchers.containsString(value) });
	}

	public void expectValueByXpath(String xPath, String value1, String value2) {
		this.request.expect().body(Matchers.hasXPath(xPath),
				new Matcher[] { Matchers.anyOf(Matchers.containsString(value1), Matchers.containsString(value2)) });
	}

	public <T> void expectResponseContains(Matcher<T> value) {
		this.request.expect().body(value, new Matcher[0]);
	}

	public <T> void expectResponseContains(String key, Matcher<T> value) {
		this.request.expect().body(key, value, new Object[0]);
	}

	public <T> void expectResponseContainsXpath(String xPath) {
		this.request.expect().body(HasXPath.hasXPath(xPath), new Matcher[0]);
	}

	public Response callAPI() {
		this.requestParams.setResponse(HttpClient.send(this.request, this.requestParams));
		LogPublisher.logResponse(this.requestParams.getResponse().asString(), this.clientName);
		return this.requestParams.getResponse();
	}

	public void expectInResponse(Matcher<?> matcher) {
		this.request.expect().body(matcher, new Matcher[0]);
	}

	public void expectInResponse(String locator, Matcher<?> value) {
		this.request.expect().body(locator, value, new Object[0]);
	}

	public String getMethodPath() {
		return this.requestParams.getUri();
	}

	public void setMethodPath(String methodPath) {
		RestAssured.reset();
		this.requestParams.setUri(methodPath);
	}

	public RequestSpecification getRequest() {
		return this.request;
	}

	public void setRequestBody(String body) {
		this.requestParams.setRequestBody(body);
	}
}
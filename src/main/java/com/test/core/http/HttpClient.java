
//
//Source code recreated from a .class file by IntelliJ IDEA
//(powered by FernFlower decompiler)
//

package com.test.core.http;

import com.test.core.P;
import com.test.log.LoggingOutputStream;
import com.test.Util.LogPublisher;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.Map.Entry;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class HttpClient {
	protected static final Logger LOGGER = Logger.getLogger(HttpClient.class);
	private static boolean logRequest;
	private static boolean logResponse;

	public HttpClient() {
	}

	public static Response send(RequestSpecification request, RequestParams requestParams) {
		PrintStream ps = null;
		if (logRequest || logResponse) {
			ps = new PrintStream(new LoggingOutputStream(LOGGER, Level.INFO));
		}

		if (logRequest) {
			request.filter(new RequestLoggingFilter(ps));
		}

		if (logResponse) {
			request.filter(new ResponseLoggingFilter(ps));
		}

		Iterator var3 = requestParams.getHm().entrySet().iterator();

		Entry multiPart;
		while (var3.hasNext()) {
			multiPart = (Entry) var3.next();
			request.header((String) multiPart.getKey(), multiPart.getValue(), new Object[0]);
		}

		var3 = requestParams.getQs().entrySet().iterator();

		while (var3.hasNext()) {
			multiPart = (Entry) var3.next();
			request.queryParam((String) multiPart.getKey(), new Object[] { multiPart.getValue() });
		}

		var3 = requestParams.getFormParam().entrySet().iterator();

		while (var3.hasNext()) {
			multiPart = (Entry) var3.next();
			request.formParam((String) multiPart.getKey(), new Object[] { multiPart.getValue() });
		}

		var3 = requestParams.getCookies().entrySet().iterator();

		while (var3.hasNext()) {
			multiPart = (Entry) var3.next();
			request.cookie((String) multiPart.getKey(), multiPart.getValue(), new Object[0]);
		}

		var3 = requestParams.getMultipartParam().entrySet().iterator();

		while (var3.hasNext()) {
			multiPart = (Entry) var3.next();
			request.multiPart((String) multiPart.getKey(), multiPart.getValue());
		}

		String bodyString = requestParams.getRequestBody();
		String nullString = "null";
		if (null != bodyString && bodyString.length() != 0) {
			bodyString = bodyString.replaceAll("\"SET_NULL\"", nullString);
			bodyString = bodyString.replaceAll("SET_NULL", nullString);
			requestParams.setRequestBody(bodyString);
			request.body(bodyString);
		}

		LogPublisher.logRequestCurl(requestParams);

		try {
			Response var5;
			switch (requestParams.getMethodType()) {
			case HEAD:
				var5 = (Response) request.head(requestParams.getUri(), new Object[0]);
				return var5;
			case GET:
				var5 = (Response) request.get(requestParams.getUri(), new Object[0]);
				return var5;
			case PUT:
				var5 = (Response) request.put(requestParams.getUri(), new Object[0]);
				return var5;
			case POST:
				var5 = (Response) request.post(requestParams.getUri(), new Object[0]);
				return var5;
			case DELETE:
				var5 = (Response) request.delete(requestParams.getUri(), new Object[0]);
				return var5;
			case PATCH:
				var5 = (Response) request.patch(requestParams.getUri(), new Object[0]);
				return var5;
			default:
				throw new RuntimeException("MethodType is not specified for the API method: " + requestParams.getUri());
			}
		} finally {
			if (ps != null) {
				ps.close();
			}

		}
	}

	static {
		logRequest = P.CONFIG.getBoolean("logRequest");
		logResponse = P.CONFIG.getBoolean("logResponse");
	}
}

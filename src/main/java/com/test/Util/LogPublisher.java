package com.test.Util;

//
//Source code recreated from a .class file by IntelliJ IDEA
//(powered by FernFlower decompiler)
//


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.core.http.RequestParams;
import com.test.customReporter.CustomLogger;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;

public class LogPublisher {
protected static final Logger LOGGER = Logger.getLogger(LogPublisher.class);

public LogPublisher() {
}

public static void logRequestCurl(RequestParams rp) {
StringBuilder sb = new StringBuilder();
sb.append("curl ").append(" -X ").append(rp.getMethodType().name()).append(" ");
String url = rp.getUri();
String body = null;
sb.append("'").append(url);
boolean isFirstParam = false;
if (!url.contains("=")) {
isFirstParam = true;
}

Iterator var5 = rp.getQs().entrySet().iterator();

Entry hmEntrySet;
while(var5.hasNext()) {
hmEntrySet = (Entry)var5.next();
if (isFirstParam) {
sb.append("?").append((String)hmEntrySet.getKey()).append("=").append((String)hmEntrySet.getValue());
isFirstParam = false;
} else {
sb.append("&").append((String)hmEntrySet.getKey()).append("=").append((String)hmEntrySet.getValue());
}
}

sb.append("'").append(" ");
var5 = rp.getHm().entrySet().iterator();

while(var5.hasNext()) {
hmEntrySet = (Entry)var5.next();
sb.append("-H '").append((String)hmEntrySet.getKey()).append(":").append((String)hmEntrySet.getValue()).append("' ");
}

Iterator var12;
Entry es;
label70:
switch(rp.getMimeType()) {
case URL_ENCODED:
StringBuilder sbEncoded = new StringBuilder();
boolean isFirst = true;
var12 = rp.getFormParam().entrySet().iterator();

while(var12.hasNext()) {
es = (Entry)var12.next();
if (isFirst) {
sbEncoded.append((String)es.getKey()).append("=").append((String)es.getValue());
isFirst = false;
} else {
sbEncoded.append("&").append((String)es.getKey()).append("=").append((String)es.getValue());
}
}

sb.append(" -d ").append("'").append(sbEncoded.toString()).append("'");
break;
case MULTIPART_FORM:
var12 = rp.getMultipartParam().entrySet().iterator();

while(true) {
if (!var12.hasNext()) {
break label70;
}

es = (Entry)var12.next();
sb.append("-H '").append((String)es.getKey()).append("=").append(es.getValue().toString()).append("' ");
}
case APPLICATION_JSON:
body = rp.getRequestBody();

try {
if (null != body && !body.isEmpty()) {
ObjectMapper objectMapper = new ObjectMapper();
JsonNode jsonNode = (JsonNode)objectMapper.readValue(body, JsonNode.class);
body = jsonNode.toString();
sb.append(" -d ").append("'").append(body).append("'");
}
} catch (IOException var9) {
LOGGER.error("Error Parsing JSON :::::::::::::: " + url + ":::::::::::::::" + body);
}
break;
case TEXT_PLAIN:
body = rp.getRequestBody();
if (null != body && !body.isEmpty()) {
sb.append(" -d ").append("'").append(body).append("'");
}
case APPLICATION_XML:
body = rp.getRequestBody();
if (null != body && !body.isEmpty()) {
sb.append(" -d ").append("'").append(body).append("'");
}
}

if (rp.getMimeType().getValue().equalsIgnoreCase("application/xml")) {
CustomLogger.logPass("<textarea>" + sb.toString() + "</textarea>");
} else {
CustomLogger.logPass(sb.toString());
}

}

public static void logResponse(String resBody, String clientName) {
String truncatedResponse = truncateLargerResponse(resBody);
CustomLogger.logPass("[" + clientName + "] RESPONSE : [" + process(truncatedResponse) + "]");
}

private static String process(String inputString) {
if (inputString.contains("<html")) {
inputString = inputString.replaceAll("<", "&lt;");
inputString = inputString.replaceAll(">", "&gt;");
}

return inputString;
}

private static String truncateLargerResponse(String response) {
int length = 5000;
if (response.length() > length) {
response = response.substring(0, length) + " #### Response truncated too large string####";
}

String newResponse = response;
if (response.contains("<html")) {
for(Matcher m = Pattern.compile("[<]{1}[ -~]{1,1000}[>]{1}").matcher(response); m.find(); newResponse = newResponse.replaceFirst(m.group(), "")) {
}
}

return newResponse;
}
}

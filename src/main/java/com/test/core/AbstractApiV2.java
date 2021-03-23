package com.test.core;

//
//Source code recreated from a .class file by IntelliJ IDEA
//(powered by FernFlower decompiler)
//


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.builder.PropertiesProcessorMain;
import com.test.core.http.HttpMethodType;
import com.test.core.http.MimeType;
import com.test.Exception.InvalidConfigurationException;
import com.test.message.TemplateMessage;
import com.test.Util.misc.JsonUtils;
import com.test.validation.JsonKeywordsComparator;
import com.test.validation.JsonValidator;
import io.restassured.response.Response;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

public abstract class AbstractApiV2 extends AbstractApi {
private Properties properties;
private String rqPath;
private String rsPath;
private String actualRsBody;

public AbstractApiV2(String rqPath, String rsPath, Properties properties, MimeType mt) {
super(mt);
this.setHeader("Accept", "*/*");
if (properties != null) {
this.properties = PropertiesProcessorMain.processProperties(properties);
}

this.rqPath = rqPath;
this.rsPath = rsPath;
}

public AbstractApiV2(String rqPath, String rsPath, String propertiesPath, MimeType mt) {
super(mt);
this.setHeader("Accept", "*/*");
URL baseResource = ClassLoader.getSystemResource(propertiesPath);
if (baseResource != null) {
this.properties = new Properties();

try {
this.properties.load(baseResource.openStream());
} catch (IOException var7) {
throw new RuntimeException("Properties can't be loaded by path: " + propertiesPath, var7);
}

LOGGER.info("Base properties loaded: " + propertiesPath);
this.properties = PropertiesProcessorMain.processProperties(this.properties);
this.rqPath = rqPath;
this.rsPath = rsPath;
} else {
throw new RuntimeException("Properties can't be found by path: " + propertiesPath);
}
}

public AbstractApiV2(String rqPath, String rsPath, String propertiesPath) {
this(rqPath, rsPath, propertiesPath, MimeType.APPLICATION_JSON);
}

public AbstractApiV2(String rqPath, String rsPath, Properties properties) {
this(rqPath, rsPath, properties, MimeType.APPLICATION_JSON);
}

public AbstractApiV2(String rqPath, String rsPath) {
this(rqPath, rsPath, (Properties)null);
}

public AbstractApiV2(String rqPath, String rsPath, MimeType mt) {
this(rqPath, rsPath, (Properties)null, mt);
}

public AbstractApiV2(String rqPath, String rsPath, Properties properties, MimeType mt, String uri, HttpMethodType methodType) {
super(mt, uri, methodType);
this.setHeader("Accept", "*/*");
if (properties != null) {
this.properties = PropertiesProcessorMain.processProperties(properties);
}

this.rqPath = rqPath;
this.rsPath = rsPath;
}

public AbstractApiV2(String rqPath, String rsPath, String propertiesPath, MimeType mt, String uri, HttpMethodType methodType) {
super(mt, uri, methodType);
this.setHeader("Accept", "*/*");
URL baseResource = ClassLoader.getSystemResource(propertiesPath);
if (baseResource != null) {
this.properties = new Properties();

try {
this.properties.load(baseResource.openStream());
} catch (IOException var9) {
throw new RuntimeException("Properties can't be loaded by path: " + propertiesPath, var9);
}

LOGGER.info("Base properties loaded: " + propertiesPath);
this.properties = PropertiesProcessorMain.processProperties(this.properties);
this.rqPath = rqPath;
this.rsPath = rsPath;
} else {
throw new RuntimeException("Properties can't be found by path: " + propertiesPath);
}
}

public AbstractApiV2(String rqPath, String rsPath, Properties properties, String uri, HttpMethodType methodType) {
this(rqPath, rsPath, properties, MimeType.APPLICATION_JSON, uri, methodType);
}

public AbstractApiV2(String rqPath, String rsPath, String uri, HttpMethodType methodType) {
this(rqPath, rsPath, (Properties)null, uri, methodType);
}

public AbstractApiV2(String rqPath, String rsPath, MimeType mt, String uri, HttpMethodType methodType) {
this(rqPath, rsPath, (Properties)null, mt, uri, methodType);
}

public AbstractApiV2(String rqPath, String rsPath, String propertiesPath, String uri, HttpMethodType methodType) {
this(rqPath, rsPath, propertiesPath, MimeType.APPLICATION_JSON, uri, methodType);
}

public Response callAPI() {
return this.callAPI(false);
}

public Response callAPI(boolean isExtended) {
this.applyTemplate(isExtended);
Response rs = super.callAPI();
this.actualRsBody = rs.asString();
return rs;
}

public void addProperty(String key, Object value) {
if (this.properties == null) {
throw new RuntimeException("API method properties are not initialized!");
} else {
this.properties.put(key, value);
}
}

public void removeProperty(String key) {
if (this.properties == null) {
throw new RuntimeException("API method properties are not initialized!");
} else {
this.properties.remove(key);
}
}

public Properties getProperties() {
return this.properties;
}

public void validateResponse(JSONCompareMode mode, String... validationFlags) {
if (this.rsPath == null) {
throw new RuntimeException("Please specify rsPath to make Response body validation");
} else {
if (this.properties == null) {
this.properties = new Properties();
}

if (this.actualRsBody == null) {
throw new RuntimeException("Actual response body is null. Please make API call before validation response");
} else {
TemplateMessage tm = new TemplateMessage();
tm.setTemplatePath(this.rsPath);
tm.setPropertiesStorage(this.properties);
String expectedRs = tm.getMessageText();

try {
JSONAssert.assertEquals(expectedRs, this.actualRsBody, new JsonKeywordsComparator(mode, validationFlags));
} catch (JSONException var6) {
throw new RuntimeException(var6);
}
}
}
}

public void validateResponse(String... validationFlags) {
this.validateResponse(JSONCompareMode.NON_EXTENSIBLE, validationFlags);
}

public void validateResponseAgainstJSONSchema(String schemaPath) {
if (this.actualRsBody == null) {
throw new RuntimeException("Actual response body is null. Please make API call before validation response");
} else {
TemplateMessage tm = new TemplateMessage();
tm.setTemplatePath(schemaPath);
String schema = tm.getMessageText();
JsonValidator.validateJsonAgainstSchema(schema, this.actualRsBody);
}
}

public void setAuth(String jSessionId) {
this.addCookie("pfJSESSIONID", jSessionId);
}

private void applyTemplate(boolean isExtended) {
if (null != this.rqPath) {
TemplateMessage tm = new TemplateMessage();
tm.setTemplatePath(this.rqPath);
tm.setPropertiesStorage(this.properties);
if (isExtended) {
ObjectMapper objectMapper = new ObjectMapper();

try {
JsonNode rootNode = (JsonNode)objectMapper.readValue(tm.getMessageText(), JsonNode.class);
ObjectMapper mapper = new ObjectMapper();
Map cookies;
Iterator var7;
Entry cookie;
if (JsonUtils.isValidNode(rootNode, "qs")) {
cookies = (Map)mapper.convertValue(rootNode.get("qs"), Map.class);
var7 = cookies.entrySet().iterator();

while(var7.hasNext()) {
cookie = (Entry)var7.next();
this.requestParams.addQueryParam((String)cookie.getKey(), (String)cookie.getValue());
}
}

if (JsonUtils.isValidNode(rootNode, "hm")) {
cookies = (Map)mapper.convertValue(rootNode.get("hm"), Map.class);
var7 = cookies.entrySet().iterator();

while(var7.hasNext()) {
cookie = (Entry)var7.next();
this.requestParams.addHeader((String)cookie.getKey(), (String)cookie.getValue());
}
}

if (JsonUtils.isValidNode(rootNode, "formParam")) {
cookies = (Map)mapper.convertValue(rootNode.get("formParam"), Map.class);
var7 = cookies.entrySet().iterator();

while(var7.hasNext()) {
cookie = (Entry)var7.next();
this.requestParams.addFormParam((String)cookie.getKey(), (String)cookie.getValue());
}
}

if (JsonUtils.isValidNode(rootNode, "cookies")) {
cookies = (Map)mapper.convertValue(rootNode.get("cookies"), Map.class);
var7 = cookies.entrySet().iterator();

while(var7.hasNext()) {
cookie = (Entry)var7.next();
this.requestParams.addCookie((String)cookie.getKey(), (String)cookie.getValue());
}
}

if (JsonUtils.isValidNode(rootNode, "body")) {
this.requestParams.setRequestBody(rootNode.get("body").toString());
}
} catch (IOException var9) {
throw new InvalidConfigurationException(var9);
}
} else {
this.requestParams.setRequestBody(tm.getMessageText());
}
}

}
}
package com.test.validation;

//
//Source code recreated from a .class file by IntelliJ IDEA
//(powered by FernFlower decompiler)
//


import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingMessage;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import java.io.IOException;
import java.util.Iterator;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

public class JsonValidator {
private static final Logger LOGGER = Logger.getLogger(JsonValidator.class);

public JsonValidator() {
}

public static void validateJson(String expectedJson, String actualJson, JSONCompareMode jsonCompareMode) {
try {
JSONAssert.assertEquals(expectedJson, actualJson, new JsonKeywordsComparator(jsonCompareMode, new String[0]));
} catch (JSONException var4) {
throw new RuntimeException(var4);
}
}

public static void validateJsonAgainstSchema(String jsonSchema, String jsonData) {
JsonNode schemaNode;
try {
schemaNode = JsonLoader.fromString(jsonSchema);
} catch (IOException var16) {
throw new RuntimeException("Can not read schema from String: " + var16.getMessage(), var16);
}

JsonNode data;
try {
data = JsonLoader.fromString(jsonData);
} catch (IOException var15) {
throw new RuntimeException("Can not read json from String: " + var15.getMessage(), var15);
}

JsonSchemaFactory factory = JsonSchemaFactory.byDefault();

JsonSchema schema;
try {
schema = factory.getJsonSchema(schemaNode);
} catch (ProcessingException var14) {
throw new RuntimeException("Can not process schema", var14);
}

ProcessingReport report;
try {
report = schema.validate(data, true);
} catch (ProcessingException var13) {
throw new RuntimeException("Exception during processing of Json", var13);
}

if (report.isSuccess()) {
LOGGER.info("Validation against Json schema passed");
} else {
StringBuffer result = new StringBuffer("Validation against Json schema failed: \n");
Iterator itr = report.iterator();

while(itr.hasNext()) {
ProcessingMessage message = (ProcessingMessage)itr.next();
JsonNode json = message.asJson();
String instance = json.get("instance").get("pointer").asText();
String errorMsg = json.get("message").asText();
result.append("[");
result.append(instance);
result.append("]: ");
result.append(errorMsg);
result.append("\n");
}

throw new RuntimeException(result.toString());
}
}
}

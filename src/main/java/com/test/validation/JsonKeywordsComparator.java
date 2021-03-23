package com.test.validation;

//

//Source code recreated from a .class file by IntelliJ IDEA
//(powered by FernFlower decompiler)
//

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.ArrayUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.JSONCompareResult;
import org.skyscreamer.jsonassert.comparator.DefaultComparator;

public class JsonKeywordsComparator extends DefaultComparator {
private String[] validationFlags;

public JsonKeywordsComparator(JSONCompareMode mode, String... validationFlags) {
super(mode);
this.validationFlags = validationFlags;
}

public void compareValues(String prefix, Object expectedValue, Object actualValue, JSONCompareResult result) throws JSONException {
if (!JsonCompareKeywords.SKIP.getKey().equals(expectedValue.toString())) {
String actualStr;
if (expectedValue != null && expectedValue.toString().startsWith(JsonCompareKeywords.TYPE.getKey())) {
actualStr = expectedValue.toString().replace(JsonCompareKeywords.TYPE.getKey(), "");
if (!actualStr.equals(actualValue.getClass().getSimpleName())) {
result.fail(String.format("%s\nValue type '%s' doesn't match to expected type '%s'\n", prefix, actualValue.getClass().getSimpleName(), actualStr));
}
} else if (expectedValue != null && expectedValue.toString().startsWith(JsonCompareKeywords.REGEX.getKey())) {
if (!(actualValue instanceof Number) && !(actualValue instanceof String)) {
super.compareValues(prefix, expectedValue, actualValue, result);
} else {
actualStr = actualValue.toString();
String regex = expectedValue.toString().replace(JsonCompareKeywords.REGEX.getKey(), "");
Matcher m = Pattern.compile(regex).matcher(actualStr);
if (!m.find()) {
result.fail(String.format("%s\nActual value '%s' doesn't match to expected regex '%s'\n", prefix, actualStr, regex));
}
}
} else {
super.compareValues(prefix, expectedValue, actualValue, result);
}
}

}

public void compareJSONArray(String prefix, JSONArray expected, JSONArray actual, JSONCompareResult result) throws JSONException {
if ((this.validationFlags == null || this.validationFlags.length <= 0 || !ArrayUtils.contains(this.validationFlags, JsonCompareKeywords.ARRAY_CONTAINS.getKey() + prefix)) && expected.length() != actual.length()) {
result.fail(String.format("%s[]\nArrays length differs. Expected length=%d but actual length=%d\n", prefix, expected.length(), actual.length()));
} else {
JSONArray actualTmp = new JSONArray();

int i;
for(i = 0; i < actual.length(); ++i) {
actualTmp.put(actual.get(i));
}

for(i = 0; i < expected.length(); ++i) {
boolean isEquals = false;
if (!JSONObject.class.equals(expected.get(i).getClass())) {
this.compareJSONArrayForSimpleTypeWContains(prefix, expected, actual, result);
break;
}

JSONObject expectedValue = (JSONObject)expected.get(i);
JSONObject actValueMostlySimilar = (JSONObject)actualTmp.get(0);
int actValueMostlySimilarIndex = 0;
int minErrorsCount = 2147483647;

for(int j = 0; j < actualTmp.length(); ++j) {
JSONObject actualValue = (JSONObject)actualTmp.get(j);
JSONCompareResult tmpResult = new JSONCompareResult();
this.compareValues(prefix + "[" + i + "]", expectedValue, actualValue, tmpResult);
if (tmpResult.passed()) {
isEquals = true;
actValueMostlySimilarIndex = j;
break;
}

if (tmpResult.getFieldFailures().size() < minErrorsCount) {
minErrorsCount = tmpResult.getFieldFailures().size();
actValueMostlySimilar = actualValue;
actValueMostlySimilarIndex = j;
}
}

if (!isEquals) {
JSONCompareResult tmpResult = new JSONCompareResult();
super.compareJSON(prefix + "[" + i + "]", expectedValue, actValueMostlySimilar, tmpResult);
result.fail(tmpResult.getMessage());
}

JSONArray arrayAfterRemove = new JSONArray();

for(int i1 = 0; i1 < actualTmp.length(); ++i1) {
if (i1 != actValueMostlySimilarIndex) {
arrayAfterRemove.put(actualTmp.get(i1));
}
}

actualTmp = arrayAfterRemove;
}

}
}

private void compareJSONArrayForSimpleTypeWContains(String prefix, JSONArray expected, JSONArray actual, JSONCompareResult result) throws JSONException {
if (expected.length() != 1 || !JsonCompareKeywords.SKIP.getKey().equals(expected.get(0).toString())) {
for(int i = 0; i < expected.length(); ++i) {
boolean isEquals = false;

for(int j = 0; j < actual.length(); ++j) {
if (expected.get(i).equals(actual.get(j))) {
isEquals = true;
break;
}
}

if (!isEquals) {
result.fail(String.format("%s\nExpected array item '" + expected.get(i) + "' is missed in actual array\n", prefix));
}
}

}
}
}

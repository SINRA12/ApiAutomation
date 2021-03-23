package com.test.Util.misc;


import com.fasterxml.jackson.databind.JsonNode;

public class JsonUtils {
 public JsonUtils() {
 }

 public static boolean isValidNode(JsonNode rootNode, String attribute) {
 if (rootNode.has(attribute)) {
 JsonNode node = rootNode.get(attribute);
 if (null != node) {
 return true;
 }
 }

 return false;
 }
}

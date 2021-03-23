package com.test.Exception;


public class PlaceholderResolverException extends RuntimeException {
 private static final long serialVersionUID = -1666532382220155518L;

 public PlaceholderResolverException() {
 }

 public PlaceholderResolverException(String key) {
 super("Value not found by key '" + key + "'");
 }
}

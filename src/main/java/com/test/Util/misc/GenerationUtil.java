package com.test.Util.misc;
//
//Source code recreated from a .class file by IntelliJ IDEA
//(powered by FernFlower decompiler)
//

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.TimeZone;

public class GenerationUtil {
public static final String DEFAULT_TIME_ZONE = "Indian Standard Time";
private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
private static final Random RANDOM = new Random();

public GenerationUtil() {
}

public static String generateTime(String format, int offset, int calendarUnit, String timeZone) {
SimpleDateFormat sdf = new SimpleDateFormat(format);
Calendar calendar = Calendar.getInstance();
if (timeZone != null) {
sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
}

calendar.add(calendarUnit, offset);
return sdf.format(calendar.getTime());
}

public static String generateTime(String format, int offset, int calendarUnit) {
return generateTime(format, offset, calendarUnit, "Indian Standard Time");
}

private static String generateBase(int keySize) {
String base = "";

for(int i = 0; i < keySize; ++i) {
base = base + String.valueOf(RANDOM.nextInt(9));
}

return base;
}

public static String generateWord(int keySize) {
StringBuilder result = new StringBuilder();
String base = generateBase(keySize);
int position = RANDOM.nextInt("abcdefghijklmnopqrstuvwxyz".length() - 1);
int sign = -1;

for(int i = 0; i < keySize; ++i) {
int step = Integer.valueOf(base.substring(i, i + 1)) * sign;
if (position + step > 0 && position + step < "abcdefghijklmnopqrstuvwxyz".length() - 1) {
position += step;
} else {
position -= step;
}

result.append("abcdefghijklmnopqrstuvwxyz".charAt(position));
sign *= -1;
}

return result.toString();
}

public static String generateNumber(int keySize) {
StringBuilder result = new StringBuilder();

for(int i = 0; i < keySize; ++i) {
result.append(RANDOM.nextInt(10));
}

return result.toString();
}
}
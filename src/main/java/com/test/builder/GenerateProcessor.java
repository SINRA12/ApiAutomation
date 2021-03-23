package com.test.builder;

//
//Source code recreated from a .class file by IntelliJ IDEA
//(powered by FernFlower decompiler)
//


import com.test.Util.misc.GenerationUtil;
import java.util.Iterator;
import java.util.Properties;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenerateProcessor implements PropertiesProcessor {
public GenerateProcessor() {
}

public Properties process(Properties in) {
Properties out  = new Properties();
Iterator var3 = in.entrySet().iterator();

while(var3.hasNext()) {
Entry<Object, Object> entry = (Entry)var3.next();
Matcher wordMatcher = Pattern.compile(PropertiesKeywords.GENERATE_WORD_REGEX.getKey()).matcher(entry.getValue().toString());
Matcher numberMatcher = Pattern.compile(PropertiesKeywords.GENERATE_NUMBER_REGEX.getKey()).matcher(entry.getValue().toString());
Matcher dateMatcher = Pattern.compile(PropertiesKeywords.GENERATE_DATE_REGEX.getKey()).matcher(entry.getValue().toString());
String toReplace;
Matcher offsetMatcher;
String offset;
if (wordMatcher.find()) {
toReplace = wordMatcher.group();
offsetMatcher = Pattern.compile("\\d+").matcher(toReplace);
offsetMatcher.find();
offset = offsetMatcher.group();
out.put(entry.getKey(), entry.getValue().toString().replace(toReplace, GenerationUtil.generateWord(Integer.parseInt(offset))));
} else if (numberMatcher.find()) {
toReplace = numberMatcher.group();
offsetMatcher = Pattern.compile("\\d+").matcher(toReplace);
offsetMatcher.find();
offset = offsetMatcher.group();
out.put(entry.getKey(), entry.getValue().toString().replace(toReplace, GenerationUtil.generateNumber(Integer.parseInt(offset))));
} else if (dateMatcher.find()) {
toReplace = dateMatcher.group();
offsetMatcher = Pattern.compile("-{0,1}\\d+").matcher(entry.getValue().toString());
offsetMatcher.find();
offset = offsetMatcher.group();
Matcher formatMatcher = Pattern.compile("(?<=generate_date\\().*(?=;)").matcher(entry.getValue().toString());
formatMatcher.find();
String format = formatMatcher.group();
out.put(entry.getKey(), entry.getValue().toString().replace(toReplace, GenerationUtil.generateTime(format, Integer.parseInt(offset), 6)));
} else {
out.put(entry.getKey(), entry.getValue());
}
}

return out;
}
}
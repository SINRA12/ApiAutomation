package com.test.dingtalk;

//
//Source code recreated from a .class file by IntelliJ IDEA
//(powered by FernFlower decompiler)
//

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.Map;

public class MarkDownMessageDTO implements Serializable {
	@SerializedName("msgtype")
	@Expose
	private String msgtype = "markdown";
	@SerializedName("markdown")
	@Expose
	private Markdown markdown;

	public MarkDownMessageDTO(String title, String componentName, Integer testCasesNotHavingOwner,
			String screenshotPath, String text, Map<String, Integer> report) {
		this.setMarkdown(new Markdown(title, componentName, testCasesNotHavingOwner, screenshotPath, text, report));
	}

	public String getMsgtype() {
		return this.msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

	public Markdown getMarkdown() {
		return this.markdown;
	}

	public void setMarkdown(Markdown markdown) {
		this.markdown = markdown;
	}
}
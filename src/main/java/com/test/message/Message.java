package com.test.message;

//
//Source code recreated from a .class file by IntelliJ IDEA
//(powered by FernFlower decompiler)
//

public abstract class Message {
protected String messageText;

public Message() {
}

public Message(String messageText) {
this.messageText = messageText;
}

public String getMessageText() {
return this.messageText;
}

public void setMessageText(String messageText) {
this.messageText = messageText;
}
}

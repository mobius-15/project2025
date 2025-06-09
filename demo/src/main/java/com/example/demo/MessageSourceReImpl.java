package com.example.demo;

import java.util.Locale;

import org.springframework.context.support.DefaultMessageSourceResolvable;

public class MessageSourceReImpl implements MessageSourceResolvable {
	MessageSource messageSource;
	MessageSourceResolvable functionName=(MessageSourceResolvable) new DefaultMessageSourceResolvable(
			"functionName.userRegistration");
	String message=messageSource.getMessage(
			"result.succeed",
			new MessageSourceResolvable[] {functionName}
			,Locale.JAPANESE);


@Override
public String[] getCodes() {
	// TODO 自動生成されたメソッド・スタブ
	return null;
}

@Override
public Object[] getArguments() {
	// TODO 自動生成されたメソッド・スタブ
	return null;
}

@Override
public String getDefaultMessage() {
	// TODO 自動生成されたメソッド・スタブ
	return null;
}
}
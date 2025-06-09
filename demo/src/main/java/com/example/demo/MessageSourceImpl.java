package com.example.demo;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

public class MessageSourceImpl implements MessageSource{
	
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasenames("messages");
		messageSource.setDefaultEncoding("UTF-8");
		return (MessageSource) messageSource;
	}
	@Autowired
	MessageSource messageSource;
	public void printWelcomeMessage() {
		String message =messageSource.getMessage("result.succeed", new String[] {"Register"},Locale.JAPANESE);
		System.out.println(message);
	}
	@Override
	public String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	@Override
	public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	@Override
	public String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}

package com.example.demo;

import java.util.Locale;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;

public interface MessageSource {
	String getMessage(String code,Object[] args,Locale locale)throws NoSuchMessageException;
	String getMessage(String code,Object[] args,String defaultMessage,Locale locale);
	String getMessage(MessageSourceResolvable resolvable,Locale locale)throws NoSuchMessageException;
}

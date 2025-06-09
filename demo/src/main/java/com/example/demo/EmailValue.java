package com.example.demo;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.support.DefaultFormattingConversionService;

public class EmailValue {

	private String value;

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public String toString() {
		return getValue();
	}

public class StringToEmailValueConverter implements Converter<String,EmailValue>{
		@Override
		public EmailValue convert(String source) {
			EmailValue email=new EmailValue();
			email.setValue(source);
			return email;
		}
}
public ConversionService conversionService() {
	DefaultFormattingConversionService conversionService
	=new DefaultFormattingConversionService();
	conversionService.addConverter(new StringToEmailValueConverter());
	return conversionService;
	}
}


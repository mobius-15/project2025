package com.example.demo;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;



@Configuration
public class AppConfig2 {
	/*２つのPassEncoder定義。前者にLightweightアノテーションを付与*/
	@Bean(name="lightweight")
	@Lightweight
	PassEncoder sha256PassEncoder() {
		return new Sha256PassEncoder();
	}
	@Bean
	@Primary
	PassEncoder bcryptPassEncoder() {
		return new BCryptPassEncoder();
	}
	/*Qualifierアノテーションを付与したLightweightアノテーション実装*/
	@Target({ElementType.FIELD,ElementType.PARAMETER,ElementType.METHOD})
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@Inherited
	@Qualifier
	public @interface Lightweight{	}

}

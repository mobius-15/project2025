package com.example.demo;

public class PostConstruct {

	public interface BeanPostProcessor{
		//前処理
		Object postProcessBeforeInitialization(Object bean,String bearName);
		//後処理
		Object postProcessAfterIniitialization(Object bean,String bearName);
	}
}
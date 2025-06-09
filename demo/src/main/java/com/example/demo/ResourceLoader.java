package com.example.demo;

import org.springframework.util.ResourceUtils;

public interface ResourceLoader {
	String CLASSPATH_URL_PREFIX=ResourceUtils.CLASSPATH_URL_PREFIX;
	Resource getResource(String location);
	ClassLoader getClassLoader();/*Resourceオブジェクトを取得する場合、引数にリソースのロケーション*/
	
}

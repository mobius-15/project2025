package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	/*Bean(DIコンテナに登録するコンポーネント)ルックアップ(コンテナからBean取得)*/
	/*UserService userService=context.getBean(UserService.class);	//指定した型を持つBeanがコンテナ内に１つだけ*/
	UserService userService=context.getBean("userService",UserService.class);	//Beanがコンテナ内に複数
	/*UserService userService=(UserService)context.getBean("userService");	//名前指定、オブジェクト型のBeanをCast*/
	
	/*ApplicationContextの生成の実装例
	 * ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	 * ApplicationContext context = new AnnotationConfigApplicationContext("com.example.app");
	 * ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/applicationContext.xml");
	 * ApplicationContext context = new FileSystemXmlApplicationContext("./spring/applicationContext.xml");*/
}

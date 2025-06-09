package com.example.demo;

public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		UserRepository userRepository=new JdbcUserRepository();	/*未完成の場合はダミーを設定*/
		PassEncoder passEncoder=new BCryptPasswordEncoder();
		/*必要なコンポーネント設定：DI（依存性注入）*/
		UserService userService=new UserServiceAction(userRepository,passEncoder);
		/*DIコンテナ：DIの自動化→Spring Framework*/
		DemoApplication context=new DemoConfigApplicationContext(AppConfig class);
		UserService userService=context.getBean(UserService.class);
		
}
}

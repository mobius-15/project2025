package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.example.demo.Configuration.UserRepositoryImp;


@Component	/*Bean定義用のアノテーションが付与されたクラスをスキャンしてDIコンテナに登録(コンポーネントスキャン)
    インジェクションでもアノテーションを付与してDIコンテナで自動設定（オートワイヤリング）*/
@ComponentScan("com.example.demo")
@Configuration	/*コンストラクタに依存コンポーネントを直接設定*/
public class AppConfig {
	
	@Bean	
	UserRepository userRepository() {
		return new UserRepositoryImp();
	}
	@Bean
	PassEncoder passEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	PassEncoder sha256PassEncoder() {
		return new Sha256PassEncoder();
	}
	@Bean
	UserService userService() {
		return new UserServiceImp(userRepository(),passEncoder());
	}
	/*UserService userService(UserRepository userRepository,PassEncoder passEncoder) {	
		return new UserServiceImp(userRepository,passEncoder);
		メソッドの引数を追加する事で他のコンポーネントの参照が可能(インスタンスを別途Bean定義する必要)*/
	}	



package com.example.demo;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Configuration	/*分割したコンフィギュレーションクラスをインポート*/
@Import({DomainConfig.class,InfrastructureConfig.class})
public class AppConfig3 {

}
@Configuration	/*分割したコンフィギュレーションクラス*/
public class DomainConfig{
	@Bean
	UserService userService() {
		
	}
}
@Configuration
public class InfrastructureConfig{
	@Bean
	DataSource dataSource() {
		
	}
}
@Configuration	/*プロファイル名を指定したJava Configの実装例*/
@Profile("development")
public class DevConfig{
	
}
@Configuration
@Profile("production")
public class ProdConfig{
	
}
@Configuration	/*メソッドレベルにプロファイル名を指定*/
public class AppConfig4{
	@Bean(name="dataSource")
	@Profile("development")
	DataSource dataSourceForDev() {
		
	}
	@Bean(name="dataSource")
	@Profile("production")
	DataSource dataSourceForProd() {
		
	}
}
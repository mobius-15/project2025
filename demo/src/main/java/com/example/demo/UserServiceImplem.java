package com.example.demo;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/*ルックアップメソッドインジェクションを利用したBean取得
 * DIコンテナにはバイトコードを生成する機能（ルックアップメソッドインジェクション）*/
public class UserServiceImplem  implements UserService{
@Autowired
ApplicationContext context;

public void register(UserBean user,String rawPass) {
	PassEncoder passEncoder=passEncoder();
	String encodedPass=passEncoder.encoding(rawPass);
}
@Lookup
PassEncoder passEncoder() {
	return null;
	}
/*Scoped Proxyを有効にする実装例*/
@Bean
@Scope(value="request",proxyMode=ScopedProxyMode.INTERFACES)
PassEncoder passEncoder() {
	return new ThreadUnsafePassEncoder();
	}
}
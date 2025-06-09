package com.example.demo;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.SimpleThreadScope;

/*インターフェースベースProxy*/
public class PassEncoderProxy implements PassEncoder{
@Autowired
ApplicationContext context;
@Override
public String encoding(String rawPass) {
	PassEncoder passEncoder=context.getBean("passEncoder",PassEncoder.class);
	return passEncoder.encoding(rawPass);
	}
}
/*サブクラスベースProxy(method,classにfinal使用不可)*/
public class PassEncoderProxy2 extends ThreadUnsafePassEncoder{
	@Autowired
	ApplicationContext context;
	@Override
	public String encoding(String rawPass) {
	PassEncoder passEncoder=context.getBean("passEncoder",PassEncoder.class);
	return passEncoder.encoding(rawPass);
	}
@Bean	/*カスタムスコープ*/
static CustomScopeConfigurer cutomScopeConfigurer() {
	CustomScopeConfigurer configurer= new CustomScopeConfigurer();
	configurer.addScope("thread", new SimpleThreadScope);
	return configurer;
	}
}

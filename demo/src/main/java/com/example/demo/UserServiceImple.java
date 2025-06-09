package com.example.demo;

import jakarta.annotation.Resource;

/*Bean名がフィールド名、プロパティ名と一致するBeanをインジェクションする場合
 * @Resourceアノテーションのnameを省略するとBean名がフィールド名、プロパティ名と一致するBean
 * がインジェクション候補となる（名前によるAutowire）*/

public class UserServiceImple implements UserService{
	@Resource(name="sha256PassEncoder")
	PassEncoder passEncoder;
	
	@Resource
	PassEncoder sha256PassEncoder;
	
	@Resource
	public void setSha256PassEncoder(PassEncoder passEncoder) {
		this.passEncoder=passEncoder;
		/*コンストラクタインジェクションでResourceは使えない*/
	}
}

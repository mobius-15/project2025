package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
@Component  /*	@ConstructorProperties({"userRepository","passEncoder"})*/
public class UserServiceImpl implements UserService {
	private UserRepository userRepository;	
	private PassEncoder passEncoder;
	/*ログ出力のように、本質処理でない横断的関心事は分離する必要（アスペクト指向）*/
//	private static final Logger log=LoggerFactory.getLogger(UserServiceImpl.class);
	
	public UserServiceImpl(UserRepository userRepository,PassEncoder passEncoder) {
		
	}
	@Autowired	/*setterに付与する事
	(インジェクションを必要としない場合@Autowired(required=false)で例外回避)*/
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository=userRepository;	
	}
	@Autowired
	public void setPassEncoder(PassEncoder passEncoder) {
		this.passEncoder=passEncoder;
	}/*セッターインジェクション：コンポーネントがsetterを持つ場合、
	そのsetter引数に対して依存するコンポーネントを注入*/
	@Bean
	UserService userService() {	/*セッターインジェクションを行う引数を持ったConfiguration*/
		UserServiceImpl userService= new UserServiceImpl(null,null) ;
		userService.setUserRepository(userRepository);	/*依存コンポーネント*/
		userService.setPassEncoder(passEncoder);
		return userService;
	}
	@Override
	public void register(UserBean user, String rawPass) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	/*public UserBean findOne(String userName) {
		log.debug("Start:UserServiceImpl.findOne {}",userName);
		log.debug("End:UserServiceImpl.findOne {}",userBean);
		return userBean;
	}*/

}

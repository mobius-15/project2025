package com.example.demo;

import org.springframework.stereotype.Component;

@Component("userService")
public class UserServiceImp implements UserService{
	private final UserRepository userRepository;	
	private final PassEncoder passEncoder;
	/*コンストラクタでの実装クラス作成(結合度が高い：非推奨)*/
//	public UserServiceAction(javax.sql.DataSource dataSource){
	public UserServiceImp(UserRepository userRepository,PassEncoder passEncoder) {
		//データベース上のユーザー情報を操作する実装クラス
///		this.userRepository=new JdbcUserRepository(dataSource);	
		this.userRepository=userRepository;			/* UserServiceActionの外から実装を差替えやすくなる*/
		
		//BCryptアルゴリズムでハッシュ化する実装クラス
///		this.passEncoder=new BCryptPasswordEncoder();
		this.passEncoder=passEncoder;
	}
	public void register(UserBean user,String rawPass) {
		if(this.userRepository.countingUser(user.getUserName())>0) {
			/*ユーザ重複時の例外*/
			throw new UserAlreadyRegiseteredException();
		}
		/*rawパスワードはハッシュ化*/
		user.setPassword(this.passEncoder.encoding(rawPass));
		this.userRepository.save(user);
	}
}
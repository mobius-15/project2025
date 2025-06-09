package com.example.demo;

public interface UserRepository {
	//ユーザー保存
	UserBean save(UserBean user);
	//ユーザーカウント
	int countingUser(String userName);
}

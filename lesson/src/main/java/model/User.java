package model;

import java.io.Serializable;

public class User implements Serializable{
	private String shain_code;
	private String password;

public User() {}	//コンストラクタ
public User(String shain_code,String password) {
	this.shain_code=shain_code;
	this.password=password;
}
public String getShain_code() {
	return shain_code;
}
public void setShain_code(String shain_code) {
	this.shain_code = shain_code;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
}

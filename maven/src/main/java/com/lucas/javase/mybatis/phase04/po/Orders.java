package com.lucas.javase.mybatis.phase04.po;


//注意事项：该POJO类一般由逆向工程生成，每次生成都会覆盖
public class Orders {

	private int id;
	private int user_id;
	private String number;
	private String note;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	
	
}

package com.lucas.javase.mybatis.phase04.po;


//符合设计原则：对修改关闭、对扩展开放
public class OrdersExt extends Orders {

	// 关联用户信息
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}

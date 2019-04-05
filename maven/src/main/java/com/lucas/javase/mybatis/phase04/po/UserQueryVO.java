package com.lucas.javase.mybatis.phase04.po;


import java.util.List;

/**
 * 用于传参的包装POJO
 * @author think
 *
 */
public class UserQueryVO {

	private User user;
	
	//批量ID
	private List<Integer> idList;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Integer> getIdList() {
		return idList;
	}

	public void setIdList(List<Integer> idList) {
		this.idList = idList;
	}
	
	
	
}

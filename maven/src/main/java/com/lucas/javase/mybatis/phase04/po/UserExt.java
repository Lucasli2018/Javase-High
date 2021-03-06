package com.lucas.javase.mybatis.phase04.po;


import java.util.List;

/**
 * 用于结果映射的扩展POJO
 * 
 * @author think
 *
 */
public class UserExt extends User {

	private static final long serialVersionUID = 1L;
	private List<Orders> orders;

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

}

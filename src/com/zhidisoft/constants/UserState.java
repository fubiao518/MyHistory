package com.zhidisoft.constants;

import java.util.ArrayList;
import java.util.List;

public enum UserState {

	/**
	 * 可用状态
	 */
	ENABLE("1","可用状态"),
	/**
	 * 禁用状态
	 */
	DISABLE("0","禁用状态"),
	/**
	 * 离职状态
	 */
	DIMISSION("-1","离职状态"),
	
	/**
	 * 退休状态
	 */
	RETIREMENT("-2","退休状态"),
	/**
	 * 休假状态
	 */
	VACATION("-3","休假状态");
	
	private UserState(String code,String text){
		this.code = code;
		this.text = text;
	}
	
	private String code;
	
	private String text;
	
	public String getCode(){
		return code;
	}
	
	public String getText(){
		return text;
	}
	
	public static List<UserState> getList(){
		List<UserState> list = new ArrayList<UserState>();
		list.add(ENABLE);
		list.add(DISABLE);
		list.add(DIMISSION);
		list.add(VACATION);
		list.add(RETIREMENT);
		return list;
	}
	
	public static UserState getUserState(String code){
		for(UserState state : getList()){
			if(state.getCode().equals(code)){
				return state;
			}
		}
		return null;
	}
	
	
}

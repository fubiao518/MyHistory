package com.zhidisoft.constants;

import java.util.ArrayList;
import java.util.List;

public enum PermissionType {
	/**
	 * 超级管理员权限
	 */
	SUPERADMIN("1","超级管理员"),
	/**
	 * 普通管理员
	 */
	ADMIN("2","管理员"),
	/**
	 * 税务专员
	 */
	TAXER("3","税务专员");
	
	
	private PermissionType(String code,String text){
		this.code = code;
		this.text = text;
	}
	
	private String code;
	
	private String text;

	public String getCode() {
		return code;
	}

	public String getText() {
		return text;
	}
	
	public static List<PermissionType> getList(){
		List<PermissionType> list = new ArrayList<PermissionType>();
		list.add(SUPERADMIN);
		list.add(ADMIN);
		list.add(TAXER);
		return list;
	}
	
	public static PermissionType getPermissionType(String code){
		for(PermissionType type : getList()){
			if(type.getCode().equals(code)){
				return type;
			}
		}
		return null;
	}
	
	public String toString(){
		return "PermissionType [code="+code+",text="+text+"]";
	}

}

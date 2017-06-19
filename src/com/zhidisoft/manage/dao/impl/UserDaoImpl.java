package com.zhidisoft.manage.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhidisoft.manage.dao.BaseDao;
import com.zhidisoft.manage.entity.TaxSource;
import com.zhidisoft.manage.entity.User;
import com.zhidisoft.util.BeanUtil;
import com.zhidisoft.util.DBUtil;

public class UserDaoImpl implements BaseDao<User>{

	private DBUtil db = new DBUtil();
	
	
	/**
	 * 用户登录的方法
	 */
	public User queryByName(String username){
		String sql = "select * from tb_user where username=?";
		User user = null;
		Object[] parm = {username};
		List<Map<String, String>> users = db.query(sql,parm);
		Map<String, String> map = new HashMap<String,String>();
		if (users != null && users.size() == 1) {
			map = users.get(0);
			user = new User();
			BeanUtil.mapToBean(user, map);
		}
		return user;
	}
	@Override
	public List<User> getAll() {
		String sql = "SELECT * FROM tb_user";
		List<Map<String, String>> list = DBUtil.query(sql);
		List<User> users = new ArrayList<User>();
		User user = null;
		if (list != null && list.size() > 0) {
			for (Map<String, String> map : list) {
				user = new User();
				BeanUtil.mapToBean(user, map);
				users.add(user);
			}
		}
		
		return users;
	}

	@Override
	public User getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(User t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(User user) {
		String sql = "UPDATE tb_user SET PASSWORD=? WHERE username = ?";
		Object[] args = {user.getPassword(),user.getUsername()};
		boolean state = db.update(sql, args);
		return state;
	}
	
}

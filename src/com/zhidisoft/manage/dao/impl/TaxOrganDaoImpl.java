package com.zhidisoft.manage.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhidisoft.manage.dao.BaseDao;
import com.zhidisoft.manage.entity.TaxOrgan;
import com.zhidisoft.util.BeanUtil;
import com.zhidisoft.util.DBUtil;

public class TaxOrganDaoImpl implements BaseDao<TaxOrgan>{

	@Override
	public List<TaxOrgan> getAll() {
		String sql = "SELECT * FROM tb_tax_organ";
		List<Map<String, String>> list = DBUtil.query(sql);
		List<TaxOrgan> organs = new ArrayList<TaxOrgan>();
		TaxOrgan organ = null;
		if (list != null && list.size() > 0) {
			for(Map<String, String> map:list){
				organ = new TaxOrgan();
				BeanUtil.mapToBean(organ, map);
				organs.add(organ);
			}
		}
		return organs;
	}

	@Override
	public TaxOrgan getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(TaxOrgan t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(TaxOrgan t) {
		// TODO Auto-generated method stub
		return false;
	}
//	public static void main(String[] args) {
//		TaxOrganDaoImpl dao = new TaxOrganDaoImpl();
//		dao.getAll();
//	}
}

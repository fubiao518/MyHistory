package com.zhidisoft.manage.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhidisoft.manage.dao.BaseDao;
import com.zhidisoft.manage.entity.Industry;
import com.zhidisoft.manage.entity.Payer;
import com.zhidisoft.util.BeanUtil;
import com.zhidisoft.util.DBUtil;

public class IndustryDaoImpl implements BaseDao<Industry>{

	@Override
	public List<Industry> getAll() {
		String sql = "SELECT * FROM tb_industry";
		List<Map<String, String>> list = DBUtil.query(sql);
		List<Industry> industrys = new ArrayList<Industry>();
		Industry industry = null;
		if (list != null && list.size() > 0) {
			for(Map<String, String> map:list){
				industry = new Industry();
				BeanUtil.mapToBean(industry, map);
				industrys.add(industry);
			}
		}
		return industrys;
	}

	@Override
	public Industry getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(Industry t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Industry t) {
		// TODO Auto-generated method stub
		return false;
	}

}

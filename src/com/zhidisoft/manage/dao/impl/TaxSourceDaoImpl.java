package com.zhidisoft.manage.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhidisoft.manage.dao.BaseDao;
import com.zhidisoft.manage.entity.TaxOrgan;
import com.zhidisoft.manage.entity.TaxSource;
import com.zhidisoft.util.BeanUtil;
import com.zhidisoft.util.DBUtil;

public class TaxSourceDaoImpl implements BaseDao<TaxSource>{

	@Override
	public List<TaxSource> getAll() {
		String sql = "SELECT * FROM tb_tax_source";
		List<Map<String, String>> list = DBUtil.query(sql);
		List<TaxSource> sources = new ArrayList<TaxSource>();
		TaxSource source = null;
		if (list != null && list.size() > 0) {
			for(Map<String, String> map:list){
				source = new TaxSource();
				BeanUtil.mapToBean(source, map);
				sources.add(source);
			}
		}
		return sources;
		
	}

	@Override
	public TaxSource getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(TaxSource t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(TaxSource t) {
		// TODO Auto-generated method stub
		return false;
	}

}

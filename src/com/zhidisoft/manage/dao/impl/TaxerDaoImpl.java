package com.zhidisoft.manage.dao.impl;

import java.util.List;
import java.util.Map;

import com.zhidisoft.manage.dao.BaseDao;
import com.zhidisoft.manage.entity.Taxer;
import com.zhidisoft.util.DBUtil;

public class TaxerDaoImpl implements BaseDao<Taxer> {

	@Override
	public List<Taxer> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Taxer getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(Taxer t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Integer id) {
		String sql = "DELETE FROM tb_taxer WHERE id=?";
		boolean state = DBUtil.update(sql, id);
		return state;
	}

	@Override
	public boolean update(Taxer t) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 获取数据的总条数
	 * 
	 * @return
	 */
	public int getCount() {
		String sql = "select count(1) c from tb_taxer";
		List<Map<String, String>> list = DBUtil.query(sql);
		int count = 0;
		if (list != null && list.size() == 1) {
			count = Integer.parseInt(list.get(0).get("c"));
		}
		return count;
	}
	
	/**
	 * 分页查询
	 * @param pageNumber
	 * @param pageSize
	 * @param taxerName
	 * @return
	 */
	public List<Map<String, String>> getListByMap(int pageNumber, int pageSize, String taxerName) {
		StringBuilder sb = new StringBuilder();
		// sql语句
		sb.append(
				"SELECT tb.*,tto.organName FROM tb_taxer tb LEFT JOIN tb_tax_organ tto ON tb.organId=tto.id where 1=1");
		// 如果有查询参数就拼接在后面
		if (taxerName != null && taxerName.length() > 0) {
			// 办税专员名字用模糊查询
			sb.append(" AND tb.taxerName  LIKE '%" + taxerName + "%'");
		}
		// 拼上limit子句
		sb.append(" limit ?,?");
		List<Map<String, String>> list = DBUtil.query(sb.toString(), (pageNumber - 1) * pageSize, pageSize);
		return list;
	}
	
	/**
	 * 是否存在子记录
	 * @return
	 */
	public boolean isHaveTaxOrgan(Integer id) {
		String sql = "SELECT COUNT(1) c FROM tb_taxer tt RIGHT JOIN tb_tax_source tts ON tt.id=tts.approverId WHERE tt.id=? GROUP BY tt.id ";
		List<Map<String, String>> list = DBUtil.query(sql, id);
		
		if (list != null && !list.isEmpty()) {
			Integer count = Integer.parseInt(list.get(0).get("c"));
			if (count > 0 ) {
				return true;
			}
		}
		return false;
	}
}

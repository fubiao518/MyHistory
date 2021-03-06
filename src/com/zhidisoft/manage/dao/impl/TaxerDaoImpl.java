package com.zhidisoft.manage.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhidisoft.manage.dao.BaseDao;
import com.zhidisoft.manage.entity.TaxOrgan;
import com.zhidisoft.manage.entity.Taxer;
import com.zhidisoft.util.BeanUtil;
import com.zhidisoft.util.DBUtil;

public class TaxerDaoImpl implements BaseDao<Taxer> {

	@Override
	public List<Taxer> getAll() {
		String sql = "SELECT tb.*,tto.organName FROM tb_taxer tb LEFT JOIN tb_tax_organ tto ON tb.organId=tto.id";
		List<Map<String, String>> list = DBUtil.query(sql);
		List<Taxer> taxers = new ArrayList<Taxer>();
		Taxer taxer = null;
		if (list != null && list.size() > 0) {
			for(Map<String, String> map:list){
				taxer = new Taxer();
				BeanUtil.mapToBean(taxer, map);
				taxers.add(taxer);
			}
		}
		return taxers;
		
	}

	@Override
	public Taxer getById(Integer id) {
		String sql = "SELECT * FROM tb_taxer WHERE id=?";
		Object[] args = {id};
		Taxer taxer = null;
		List<Map<String, String>> list = DBUtil.query(sql, args);
		Map<String, String> map = new  HashMap<String, String>();
		if (list != null && list.size() == 1) {
			map = list.get(0);
			taxer = new Taxer();
			BeanUtil.mapToBean(taxer, map);
		}
		return taxer;
	}
	/**
	 * 添加操作
	 */
	@Override
	public boolean add(Taxer t) {
		String sql = "INSERT INTO tb_taxer (taxerCode,taxerName,mobile,address,sex,birthday,email,organId,state,mgr,admin,recordDate,recordUserId) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] args = {t.getTaxerCode(),t.getTaxerName(),t.getMobile(),t.getAddress(),t.getSex(),t.getBirthday(),t.getEmail(),t.getOrganId(),t.getState(),t.getMgr(),t.getAdmin(),t.getRecordDate(),t.getRecordUserId()};
		int count = DBUtil.insert(sql, args);
		if (count > 0) {
			return true;
		}
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
		String sql = "update tb_taxer set mobile=?,address=?,email=?,organId=?,state=?,mgr=?,recordUserId=? where taxerName=?";
		Object[] args = {t.getMobile(),t.getAddress(),t.getEmail(),t.getOrganId(),t.getState(),t.getMgr(),t.getRecordUserId(),t.getTaxerName()};
		
		return DBUtil.update(sql, args);
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

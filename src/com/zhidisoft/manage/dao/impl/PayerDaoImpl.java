package com.zhidisoft.manage.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhidisoft.manage.dao.BaseDao;
import com.zhidisoft.manage.entity.Payer;
import com.zhidisoft.manage.entity.TaxSource;
import com.zhidisoft.manage.entity.Taxer;
import com.zhidisoft.util.BeanUtil;
import com.zhidisoft.util.DBUtil;

public class PayerDaoImpl implements BaseDao<Payer>{

	@Override
	public List<Payer> getAll() {
		String sql = "SELECT * FROM tb_tax_payer";
		List<Map<String, String>> list = DBUtil.query(sql);
		List<Payer> payers = new ArrayList<Payer>();
		Payer payer = null;
		if (list != null && list.size() > 0) {
			for(Map<String, String> map:list){
				payer = new Payer();
				BeanUtil.mapToBean(payer, map);
				payers.add(payer);
			}
		}
		return payers;
	}

	@Override
	public Payer getById(Integer id) {
		String sql = "SELECT * FROM tb_tax_payer WHERE id=?";
		Object[] args = {id};
		Payer payer = null;
		List<Map<String, String>> list = DBUtil.query(sql, args);
		Map<String, String> map = new  HashMap<String, String>();
		if (list != null && list.size() == 1) {
			map = list.get(0);
			payer = new Payer();
			BeanUtil.mapToBean(payer, map);
		}
		return payer;
	}

	@Override
	public boolean add(Payer t) {
		String sql = "INSERT INTO tb_tax_payer (payerCode,payerName,bizAddress,taxOrganid,industryId,bizScope,invoiceType,bizAddressPhone,legalPerson,legalIdCard,finaceName,finaceIdCard,taxerName,recordDate,userId) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] args = {t.getPayerCode(),t.getPayerCode(),t.getBizAddress(),t.getTaxOrganid(),t.getIndustryId(),t.getBizScope(),t.getInvoiceType(),t.getBizAddressPhone(),t.getLegalPerson(),t.getLegalIdCard(),t.getFinaceName(),t.getFinaceIdCard(),t.getTaxerName(),t.getRecordDate(),t.getUserId()};
		int count = DBUtil.insert(sql, args);
		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteById(Integer id) {
		String sql = "DELETE FROM tb_tax_payer WHERE id=?";
		boolean state = DBUtil.update(sql, id);
		return state;
	}

	@Override
	public boolean update(Payer t) {
		
		return false;
	}
	
	/**
	 * 获取数据的总条数
	 * 
	 * @return
	 */
	public int getCount() {
		String sql = "select count(1) c from tb_tax_payer";
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
	public List<Map<String, String>> getListByMap(int pageNumber, int pageSize, String payerName,String payerCode) {
		StringBuilder sb = new StringBuilder();
		List<Map<String, String>> list = null;
		// sql语句
		sb.append(
				"SELECT tp.*,tto.organName,ti.`industryName` FROM tb_tax_payer tp LEFT JOIN tb_tax_organ tto ON tp.taxOrganid=tto.id LEFT JOIN tb_industry ti ON tp.`industryId`=ti.`id` WHERE 1=1");
		// 如果有查询参数就拼接在后面
		if (payerName != null && payerName.length() > 0) {
			// 办税人名字用模糊查询
			sb.append(" AND tp.payerName  LIKE '%" + payerName + "%'");
		}
		if (payerCode != null && payerCode.trim().length() > 0) {
			sb.append(" AND tp.payerCode="+payerCode);
		}
		// 拼上limit子句
		sb.append(" limit ?,?");
		list = DBUtil.query(sb.toString(), (pageNumber - 1) * pageSize, pageSize);
		return list;
	}

}

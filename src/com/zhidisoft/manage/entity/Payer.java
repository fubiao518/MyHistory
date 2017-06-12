package com.zhidisoft.manage.entity;

import java.util.Date;

public class Payer {

	private Integer id;// 主键
	private String payerCode;// 1 纳税人识别号
	private String payerName;// 2 纳税人名称
	private String bizAddress; // 3 生产经营地址
	private Integer taxOrganid; // 4 所属税务机关
	private Integer industryId; // 5 行业
	private String bizScope; // 6 经营范围
	private String invoiceType; // 7 票种核定
	private String legalPerson; // 8 法人代表
	private String legalIdCard; // 9 身份号码
	private String legalMobile; // 10 手机号码
	private String legalIdCardImageURL; // 11 法人代表身份证扫描图片
	private String finaceName; // 12 财务人员
	private String finaceIdCard; // 13 财务人员身份号码
	private String finaceMobile; // 14 财务人员手机号码
	private String finaceIdCardImageURL; // 15 财务人员身份证扫描图片
	private String taxerName; // 16 办税人员
	private String taxerIdCard; // 17 办税人员身份号码
	private String taxerMobile; // 18 办税人员手机号码
	private String taxerIdCardImageURL; // 19 办税人员身份证扫描图片
	private String bizAddressPhone; // 20 生产经营地电话
	private String recordDate; // 21 录入日期
	private Integer userId; // 录入人员id

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPayerCode() {
		return payerCode;
	}

	public void setPayerCode(String payerCode) {
		this.payerCode = payerCode;
	}

	public String getPayerName() {
		return payerName;
	}

	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}

	public String getBizAddress() {
		return bizAddress;
	}

	public void setBizAddress(String bizAddress) {
		this.bizAddress = bizAddress;
	}

	public Integer getTaxOrganid() {
		return taxOrganid;
	}

	public void setTaxOrganid(Integer taxOrganid) {
		this.taxOrganid = taxOrganid;
	}

	public Integer getIndustryId() {
		return industryId;
	}

	public void setIndustryId(Integer industryId) {
		this.industryId = industryId;
	}

	public String getBizScope() {
		return bizScope;
	}

	public void setBizScope(String bizScope) {
		this.bizScope = bizScope;
	}

	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getLegalIdCard() {
		return legalIdCard;
	}

	public void setLegalIdCard(String legalIdCard) {
		this.legalIdCard = legalIdCard;
	}

	public String getLegalMobile() {
		return legalMobile;
	}

	public void setLegalMobile(String legalMobile) {
		this.legalMobile = legalMobile;
	}

	public String getLegalIdCardImageURL() {
		return legalIdCardImageURL;
	}

	public void setLegalIdCardImageURL(String legalIdCardImageURL) {
		this.legalIdCardImageURL = legalIdCardImageURL;
	}

	public String getFinaceName() {
		return finaceName;
	}

	public void setFinaceName(String finaceName) {
		this.finaceName = finaceName;
	}

	public String getFinaceIdCard() {
		return finaceIdCard;
	}

	public void setFinaceIdCard(String finaceIdCard) {
		this.finaceIdCard = finaceIdCard;
	}

	public String getFinaceMobile() {
		return finaceMobile;
	}

	public void setFinaceMobile(String finaceMobile) {
		this.finaceMobile = finaceMobile;
	}

	public String getFinaceIdCardImageURL() {
		return finaceIdCardImageURL;
	}

	public void setFinaceIdCardImageURL(String finaceIdCardImageURL) {
		this.finaceIdCardImageURL = finaceIdCardImageURL;
	}

	public String getTaxerName() {
		return taxerName;
	}

	public void setTaxerName(String taxerName) {
		this.taxerName = taxerName;
	}

	public String getTaxerIdCard() {
		return taxerIdCard;
	}

	public void setTaxerIdCard(String taxerIdCard) {
		this.taxerIdCard = taxerIdCard;
	}

	public String getTaxerMobile() {
		return taxerMobile;
	}

	public void setTaxerMobile(String taxerMobile) {
		this.taxerMobile = taxerMobile;
	}

	public String getTaxerIdCardImageURL() {
		return taxerIdCardImageURL;
	}

	public void setTaxerIdCardImageURL(String taxerIdCardImageURL) {
		this.taxerIdCardImageURL = taxerIdCardImageURL;
	}

	public String getBizAddressPhone() {
		return bizAddressPhone;
	}

	public void setBizAddressPhone(String bizAddressPhone) {
		this.bizAddressPhone = bizAddressPhone;
	}

	public String getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}

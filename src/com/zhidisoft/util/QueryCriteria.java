package com.zhidisoft.util;
/**
 * 查询条件工具类
 * @author 
 *
 */
public class QueryCriteria {
	
	//用于存储条件语句
	private StringBuffer criteria = new StringBuffer();
	
	
	public QueryCriteria(){
		
	}
	/**
	 * 添加条件表达式，将会使用and将该表达式和已经存在的表达式连接起来
	 * @param ex
	 * @return
	 */
	public QueryCriteria add(Expression ex){
		if(ex.getExpression().length()>0){
			if(criteria.length()>0){
				criteria.append(" and ");
			}
			criteria.append(ex.getExpression());
		}
		return this;
	}
	
	/**
	 * 添加条件表达式，将会使用or将该表达式和已经存在的表达式连接起来
	 * @param ex
	 * @return
	 */
	public QueryCriteria or(Expression ex){
		if(ex.getExpression().length()>0){
			if(criteria.length()>0){
				criteria.append(" or ");
			}
			criteria.append(ex.getExpression());
		}
		return this;
	}
	
	public boolean isEmpty(){
		return this.criteria.length()>0 ? false : true;
	}
	
	public String toString(){
		return criteria.toString();
	}
	
}

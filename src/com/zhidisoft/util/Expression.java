package com.zhidisoft.util;


public class Expression {
	
	private StringBuffer expression = new StringBuffer();
	
	private Expression(){
		
	}
	
	/**
	 * 拼接多个与运算
	 * @param ex
	 * @return
	 */
	public static Expression and(Expression ... ex){
		Expression and = new Expression();
		
		if(ex!=null && ex.length>1){
			and.getExpression().append("(");
			for(int i=0;i<ex.length;i++){
				and.getExpression().append(ex[i].getExpression());
				if(i!=ex.length-1){
					and.getExpression().append(" and ");
				}
			}
			and.getExpression().append(")");
		}
		return and;
	}
	
	/**
	 * 拼接多个或运算
	 * @param ex
	 * @return
	 */
	public static Expression or(Expression ... ex){
		Expression or = new Expression();
		
		if(ex!=null && ex.length>1){
			or.getExpression().append("(");
			for(int i=0;i<ex.length;i++){
				or.getExpression().append(ex[i].getExpression());
				if(i!=ex.length-1){
					or.getExpression().append(" or ");
				}
			}
			or.getExpression().append(")");
		}else{
			throw new RuntimeException("当使用and方法时Expression类型参数个数必须大于或等于2个");
		}
		return or;
	}
	
	/**
	 * 非运算
	 * @param ex
	 * @return
	 */
	public static Expression not(Expression ex){
		Expression not = new Expression();
		if(ex!=null){
			not.getExpression().append("not( ");
			not.getExpression().append(ex.getExpression());
			not.getExpression().append(" )");
		}
		return not;
	}
	
	/**
	 * in运算
	 * @param name
	 * @param value
	 * @return
	 */
	public static Expression in(String name,String...value){
		Expression ex = new Expression();
	
		if(value!=null && value.length>0){
			ex.getExpression().append(name);
			ex.getExpression().append(" in (");
			for(String v : value){
				ex.getExpression().append("'");
				ex.getExpression().append(v);
				ex.getExpression().append("',");
			}
			ex.getExpression().deleteCharAt(ex.getExpression().length()-1);
			ex.getExpression().append(")");
		}else{
			throw new RuntimeException("当使用or方法时Expression类型参数个数必须大于或等于2个");
		}
		return ex;
	}
	
	/**
	 * is null运算
	 * @param name
	 * @return
	 */
	public static Expression isNull(String name){
		Expression ex = new Expression();
		ex.getExpression().append(name);
		ex.getExpression().append(" is null");
		return ex;
	}
	
	/**
	 * is not null运算
	 * @param name
	 * @return
	 */
	public static Expression isNotNull(String name){
		Expression ex = new Expression();
		ex.getExpression().append(name);
		ex.getExpression().append(" is not null");
		return ex;
	}
	
	/**
	 * like 运算
	 * @param name
	 * @param value
	 * @return
	 */
	public static Expression like(String name,String value){
		Expression ex = new Expression();
		if(value!=null && !value.isEmpty()){
			ex.getExpression().append(name);
			ex.getExpression().append(" like '%");
			ex.getExpression().append(value);
			ex.getExpression().append("%'");
		}
		return ex;
	}
	
	/**
	 * 比较运算，该方法为内部通用方法，请调用顶层比较运算的方法如lt、gt等
	 * @param operator
	 * @param name
	 * @param value
	 * @return
	 */
	protected static Expression compare(CompareType operator,String name,String value){
		Expression ex = new Expression();
		if(value!=null && !value.isEmpty()){
			ex.getExpression().append(name);
			ex.getExpression().append(" ");
			ex.getExpression().append(operator.getValue());
			ex.getExpression().append(" '");
			ex.getExpression().append(value);
			ex.getExpression().append("'");
		}
		return ex;
	}
	
	/**
	 * >运算
	 * @param name
	 * @param value
	 * @return
	 */
	public static Expression gt(String name,String value){
		return compare(CompareType.GT, name, value);
	}
	
	/**
	 * <运算
	 * @param name
	 * @param value
	 * @return
	 */
	public static Expression lt(String name,String value){
		return compare(CompareType.LT, name, value);
	}
	
	/**
	 * >=运算
	 * @param name
	 * @param value
	 * @return
	 */
	public static Expression ge(String name,String value){
		return compare(CompareType.GE, name, value);
	}
	
	/**
	 * <=运算
	 * @param name
	 * @param value
	 * @return
	 */
	public static Expression le(String name,String value){
		return compare(CompareType.LE, name, value);
	}
	
	/**
	 * =运算
	 * @param name
	 * @param value
	 * @return
	 */
	public static Expression eq(String name,String value){
		return compare(CompareType.EQ, name, value);
	}
	
	/**
	 * !=运算
	 * @param name
	 * @param value
	 * @return
	 */
	public static Expression ne(String name,String value){
		return compare(CompareType.NE, name, value);
	}
	
	/**
	 * between and运算
	 * @param name
	 * @param begin
	 * @param end
	 * @return
	 */
	public static Expression between(String name,String begin,String end){
		Expression ex = new Expression();
		if(begin!=null && !begin.isEmpty() && end!=null && !end.isEmpty()){
			ex.getExpression().append(name);
			ex.getExpression().append(" between '");
			ex.getExpression().append(begin);
			ex.getExpression().append("' and '");
			ex.getExpression().append(end);
			ex.getExpression().append("'");
		}
		return ex;
	}

	public StringBuffer getExpression() {
		return expression;
	}

	public void setExpression(StringBuffer expression) {
		this.expression = expression;
	}
	
	@SuppressWarnings("all")
	private enum CompareType{
		
		EQ("="),GT(">"),LT("<"),GE(">="),LE("<="),NE("!=");
		
		private String value;
		
		private CompareType(String value){
			this.value = value;
		}
		
		public String getValue(){
			return this.value;
		}
	}

}

package com.synnex.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * 公共的封装的父类：当前页数，一页显示的条数,拼接2条sql(count,*),查询条件(来自于子类)
 * 
 */
public abstract class BaseQuery {
	// int Integer? int默认值0,Integer默认值是null
	private int currentPage = 1;// 当前页码
	private int pageSize = 10;// 一页显示的条数
	private StringBuilder countHql;
	private StringBuilder hql;
	private StringBuilder whereHql;
	private List paramList = new ArrayList();// 装子类传入的参数值:hql里面的?

	// 通过子类传入对象的简单类名
	public BaseQuery(String entityClassName) {
		countHql = new StringBuilder("SELECT COUNT(o) FROM " + entityClassName + " o");
		hql = new StringBuilder("SELECT o FROM " + entityClassName + " o");
		whereHql = new StringBuilder();
	}

	// 封装查询条件(来自于子类)
	// select o from Employee o where o.name like ? and o.email like ? and
	// between ? and ?
	protected abstract void customQuery();// 让子类实现，添加子类的查询条件countHql,hql,paramList

	// 拼接hql什么时候用where(第一次放入条件)，什么时候用and(第2-n次放入条件)
	protected void addQuery(String hqlWhere, Object... objects) {// 直接让子类调用：把查询条件，参数封装到countHql,hql,paramList
		if (paramList.size() == 0) {// 什么时候用where(第一次放入条件)
			hql.append(" WHERE ");// select o from Employee o where
			countHql.append(" WHERE ");
			whereHql.append(" WHERE ");
		} else {// 什么时候用and(第2-n次放入条件)
			hql.append(" AND ");// select o from Employee o where o.name like ?
								// and
			countHql.append(" AND ");
			whereHql.append(" AND ");
		}
		hql.append(hqlWhere);// o.name like ?
		countHql.append(hqlWhere);
		whereHql.append(hqlWhere);

		// 添加参数
		// paramList.add(objects);//错误的
		paramList.addAll(Arrays.asList(objects));
	}

	// 保证addWhere()只能调用一次
	private boolean flag = false;

	private void builderWhere() {
		if (!flag) {
			customQuery();
			flag = true;
		}
	}

	// 添加了一个whereHql，主要处理查询报表
	public String getWhereHql() {
		builderWhere();
		return whereHql.toString();
	}

	// 发出hql的时候需要调用:BaseDao
	public String getCountHql() {
		builderWhere();
		return countHql.toString();
	}

	public String getHql() {
		builderWhere();
		return hql.toString();
	}

	public List getParamList() {
		builderWhere();
		return paramList;
	}

	// 获取jsp页面传入的参数：当前页数，一页显示的条数,回显当前页数，一页显示的条数
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}

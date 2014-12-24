package com.synnex.query;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * 查询子类：处理查询条件，参数
 * 
 */
public class TermQuery extends BaseQuery {

	// 关键字查询，通用字段的查询
	private String searchKey;

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	// 定制sql
	@Override
	public void customQuery() {
		// 拼SQL，添加参数列表
		if (StringUtils.isNotBlank(this.getSearchKey())) {
			addQuery(" (o.name=?)  ", this.getSearchKey());
		}
	}

	public TermQuery() {
		super("Term");
	}

}

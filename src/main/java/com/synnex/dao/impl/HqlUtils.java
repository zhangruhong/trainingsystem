package com.synnex.dao.impl;

import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.hibernate.Hibernate; before hibernate3.5

/**
 * @author jennifert 2015-1-8
 *
 */
public class HqlUtils {

	public static final String ASCENDING = "ASC";
	public static final String DESCENDING = "DESC";
	private String baseSql;
	private List wheres;
	private List values;
	private List types;
	private List orders;
	private List groups;

	private Map<String, Object> params;

	private int begin = -1;
	private int size;

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public HqlUtils() {
		this("", ((List) (new ArrayList())), ((List) (new ArrayList())), ((List) (new ArrayList())), ((List) (new ArrayList(1))),
				new HashMap<String, Object>(), ((List) (new ArrayList(1))));
	}

	public HqlUtils(String baseSql, Map<String, Object> params, List groups) {
		this(baseSql, ((List) (new ArrayList())), ((List) (new ArrayList())), ((List) (new ArrayList())), ((List) (new ArrayList(1))), params, groups);
	}

	public HqlUtils(String baseSql, int initCapacity, Map<String, Object> params, List groups) {
		this(baseSql, ((List) (new ArrayList(initCapacity))), ((List) (new ArrayList(initCapacity))), ((List) (new ArrayList(initCapacity))),
				((List) (new ArrayList(1))), params, groups);
	}

	public HqlUtils(String baseSql, List wheres, List values, List types, Map<String, Object> params, List groups) {
		this(baseSql, wheres, values, types, ((List) (new ArrayList(1))), params, groups);
	}

	public HqlUtils(String baseSql, List wheres, List values, List types, List orders, Map<String, Object> params, List groups) {
		this.baseSql = baseSql;
		this.wheres = wheres;
		this.values = values;
		this.types = types;
		this.orders = orders;
		this.params = params;
		this.groups = groups;
	}

	public String getAndClause() {
		return getAndClause(wheres, orders);
	}

	public String getOrClause() {
		return getOrClause(wheres, orders);
	}

	public String getWhereClause() {
		return getWhereClause(wheres, groups, orders);
	}

	public String getHql() {
		if (baseSql.toLowerCase().contains("where")) {
			return (new StringBuilder(String.valueOf(baseSql))).append(" ").append(getAndClause()).toString();
		} else {
			return (new StringBuilder(String.valueOf(baseSql))).append(" ").append(getWhereClause()).toString();
		}
	}

	public Object[] getValues() {
		return values.toArray();
	}

	public void setValues(List values) {
		this.values = values;
	}

	public Type[] getTypes() {
		return (Type[]) types.toArray(new Type[0]);
	}

	public void setTypes(List types) {
		this.types = types;
	}

	// 按位置设值
	public HqlUtils add(String where, Object value) {
		return add(where, value, ((Type) (StandardBasicTypes.STRING)));
	}

	// 按参数名称设值
	public HqlUtils add(String where, Object value, String paramName) {
		return add(where, value, paramName, ((Type) (StandardBasicTypes.STRING)));
	}

	public HqlUtils addInt0(String where, int value) {
		if (value > 0) {
			add(where, Integer.valueOf(value), StandardBasicTypes.INTEGER);
		}
		return this;
	}

	// 按顺序设值
	public HqlUtils add(String where, Object value, Type type) {
		if (!isEmptyCondition(value)) {
			wheres.add(where);
			values.add(value);
			types.add(type);
		}
		return this;
	}

	// 按参数名称设值
	public HqlUtils add(String where, Object value, String paramName, Type type) {
		if (!isEmptyCondition(value)) {
			wheres.add(where);
			values.add(value);
			types.add(type);
			params.put(paramName, value);
		}
		return this;
	}

	public HqlUtils addLike(String where, Object value) {
		return addLike(where, value, ((Type) (StandardBasicTypes.STRING)));
	}

	public HqlUtils addLike(String where, Object value, String paramName) {
		return addLike(where, value, paramName, ((Type) (StandardBasicTypes.STRING)));
	}

	public HqlUtils addLike(String where, Object value, Type type) {
		if (!isEmptyCondition(value)) {
			add(where, (new StringBuilder("%")).append(value).append("%").toString(), type);
		}
		return this;
	}

	public HqlUtils addLike(String where, Object value, String paramsName, Type type) {
		if (!isEmptyCondition(value)) {
			add(where, (new StringBuilder("%")).append(value).append("%").toString(), paramsName, type);
		}
		return this;
	}

	public HqlUtils addOr(String where, Object value) {
		return addOr(where, value, ((Type) (StandardBasicTypes.STRING)));
	}

	public HqlUtils addOr(String where, Object value, Type type) {
		if (!isEmptyCondition(value)) {
			add((new StringBuilder("OR ")).append(where).toString(), value, type);
		}
		return this;
	}

	public HqlUtils addAnd(String where, Object value) {
		return addAnd(where, value, ((Type) (StandardBasicTypes.STRING)));
	}

	public HqlUtils addAnd(String where, Object value, Type type) {
		if (!isEmptyCondition(value)) {
			add((new StringBuilder("AND ")).append(where).toString(), value, type);
		}
		return this;
	}

	public HqlUtils addOrLike(String where, Object value) {
		return addOrLike(where, value, ((Type) (StandardBasicTypes.STRING)));
	}

	public HqlUtils addOrLike(String where, Object value, String paramName) {
		return addOrLike(where, value, paramName, ((Type) (StandardBasicTypes.STRING)));
	}

	public HqlUtils addOrLike(String where, Object value, Type type) {
		if (!isEmptyCondition(value)) {
			add((new StringBuilder("OR ")).append(where).toString(), (new StringBuilder("%")).append(value).append("%").toString(), type);
		}
		return this;
	}

	public HqlUtils addOrLike(String where, Object value, String paramsName, Type type) {
		if (!isEmptyCondition(value)) {
			add((new StringBuilder("OR ")).append(where).toString(), (new StringBuilder("%")).append(value).append("%").toString(), paramsName, type);
		}
		return this;
	}

	public HqlUtils addAndLike(String where, Object value, Type type) {
		if (!isEmptyCondition(value)) {
			add((new StringBuilder("AND ")).append(where).toString(), (new StringBuilder("%")).append(value).append("%").toString(), type);
		}
		return this;
	}

	public HqlUtils addOrder(String name) {
		return addOrder(name, "ASC");
	}

	public HqlUtils addOrder(String name, String policy) {
		if (isEmptyCondition(name)) {
			return this;
		}
		if (isEmptyCondition(policy)) {
			policy = "ASC";
		}
		orders.add((new StringBuilder(String.valueOf(name))).append(" ").append(policy).toString());
		return this;
	}

	public HqlUtils addGroup(String name) {
		groups.add(name);
		return this;
	}

	public HqlUtils addWhere(String where) {
		wheres.add(where);
		return this;
	}

	public static boolean isEmptyCondition(Object arg) {
		if (arg == null)
			return true;
		if (arg instanceof String) {
			if (arg.equals(""))
				return true;
			if (arg.equals("%"))
				return true;
		}
		if (((arg instanceof Integer) || (arg instanceof Long)) && arg.toString().equals("-1")) {
			return true;
		} else {
			return (arg instanceof Long) && ((Long) arg).longValue() == 111111111111L;
		}
	}

	public static String getWhereClause(List wheres, List orders) {
		StringBuilder where = new StringBuilder();
		String segment = "";
		if (wheres.size() > 0) {
			where.append(" WHERE");
			for (int i = 0; i < wheres.size(); i++) {
				segment = (String) wheres.get(i);
				if (i > 0 && segment.indexOf("OR ") < 0 && segment.indexOf("AND ") < 0) {
					where.append(" AND");
				}
				where.append(' ').append(segment);
			}
		}
		if (orders.size() > 0) {
			where.append(" ORDER BY");
			for (int i = 0; i < orders.size(); i++) {
				segment = (String) orders.get(i);
				if (i > 0) {
					where.append(",");
				}
				where.append(' ').append(segment);
			}
		}
		return where.toString();
	}

	public static String getWhereClause(List wheres, List groups, List orders) {
		StringBuilder where = new StringBuilder();
		String segment = "";
		if (wheres.size() > 0) {
			where.append(" WHERE");
			for (int i = 0; i < wheres.size(); i++) {
				segment = (String) wheres.get(i);
				if (i > 0 && segment.indexOf("OR ") < 0 && segment.indexOf("AND ") < 0) {
					where.append(" AND");
				}
				where.append(' ').append(segment);
			}
		}
		if (!groups.isEmpty()) {
			where.append(" GROUP BY");
			for (int i = 0; i < groups.size(); i++) {
				segment = (String) groups.get(i);
				if (i > 0)
					where.append(",");
				where.append(' ').append(segment);
			}
		}
		if (orders.size() > 0) {
			where.append(" ORDER BY");
			for (int i = 0; i < orders.size(); i++) {
				segment = (String) orders.get(i);
				if (i > 0)
					where.append(",");
				where.append(' ').append(segment);
			}

		}
		return where.toString();
	}

	public static String getAndClause(List wheres, List orders) {
		StringBuilder where = new StringBuilder();
		String segment = "";
		if (wheres.size() > 0) {
			for (int i = 0; i < wheres.size(); i++) {
				segment = (String) wheres.get(i);
				if (i == 0) {
					if (segment.indexOf("OR ") < 0 && segment.indexOf("AND ") < 0)
						where.append(" AND");
				} else if (i > 0 && segment.indexOf("OR ") < 0 && segment.indexOf("AND ") < 0)
					where.append(" AND");
				where.append(' ').append(segment);
			}

		}
		if (orders.size() > 0) {
			where.append(" ORDER BY");
			for (int i = 0; i < orders.size(); i++) {
				segment = (String) orders.get(i);
				if (i > 0) {
					where.append(",");
				}
				where.append(' ').append(segment);
			}

		}
		return where.toString();
	}

	public static String getOrClause(List wheres, List orders) {
		StringBuilder where = new StringBuilder();
		String segment = "";
		if (wheres.size() > 0) {
			where.append(" AND (");
			for (int i = 0; i < wheres.size(); i++) {
				segment = (String) wheres.get(i);
				if (i > 0 && segment.indexOf("OR ") < 0 && segment.indexOf("AND ") < 0) {
					where.append(" OR");
				}
				where.append(' ').append(segment);
			}
			where.append(")");
		}
		if (orders.size() > 0) {
			where.append(" ORDER BY");
			for (int i = 0; i < orders.size(); i++) {
				segment = (String) orders.get(i);
				if (i > 0) {
					where.append(",");
				}
				where.append(' ').append(segment);
			}

		}
		return where.toString();
	}
}

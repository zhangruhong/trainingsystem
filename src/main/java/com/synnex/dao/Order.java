package com.synnex.dao;

/**
 * @author Jeniss Dec 3, 2014 9:52:29 PM
 * @tags Order mapping to a filed ASC or DESC
 */
public class Order {

	private String field;
	private boolean asc = true;

	private Order() {
	}

	/**
	 * @tags Create an instance of Order ASC.
	 * @return Return an ASC instance of Order
	 */
	public static Order asc(String field) {
		return create(field, true);
	}

	/**
	 * @tags Create an instance of Order DESC.
	 * @return Return a DESC instance of Order
	 */
	public static Order desc(String field) {
		return create(field, false);
	}

	/**
	 * @tags Create an instance of order
	 * @return Return an Order instance(If true, is ASC, else false, is DESC)
	 */
	protected static Order create(String field, boolean asc) {
		Order instance = new Order();
		instance.field = field;
		instance.asc = asc;
		return instance;
	}

	public String getField() {
		return field;
	}

	public boolean isAsc() {
		return asc;
	}

	public String getOrder() {
		return asc ? "ASC" : "DESC";
	}
}

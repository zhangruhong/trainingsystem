package com.synnex.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 封装查询的结果
 * 
 */
public class PageResult<T> {
	private int currentPage;// 当前页码
	private int pageSize;// 一页显示的条数
	private int totalRows;// 总的记录数
	private int totalPages;// 总的页数,计算出来
	private List<T> rows = new ArrayList<T>();

	public PageResult() {

	}

	public PageResult(int currentPage, int pageSize, int totalRows) {
		// 作一些额外处理:
		// 1.处理负数
		this.currentPage = currentPage < 0 ? 1 : currentPage;
		this.pageSize = pageSize < 0 ? 10 : pageSize;
		this.totalRows = totalRows;

		this.totalPages = (this.totalRows + this.pageSize - 1) / this.pageSize;

		// 3.处理当前页码大于总的页数
		this.currentPage = this.currentPage > this.totalPages ? this.totalPages : this.currentPage;

	}

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

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	// =================EasyUI-dayagrid兼容=========================
	public Integer getTotal() {
		System.out.println(totalPages);
		return totalRows;
	}

}

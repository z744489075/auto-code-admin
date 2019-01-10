package com.zengtengpeng.common.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 分页控件 DTO
 * 
 * @author zengtp
 * 
 */
public class Page implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6879980366025579382L;

	private int total;// 总条数

	private boolean doAount = true; // 是否查询总数 默认是true，首先查询总数。

	private List rows; // 返回页面的数据

	private int endIndex = 11;

	private int beginIndex = 0;

	private int page = 1;

	private int pageSize = 10;
	
	private Integer totalPage;//总页数

	private String startDate;//开始时间
	private String endDate;//结束时间

	private Long updateUserId;//更新用户id

	private String createTimeValue;//创建时间格式化

	private Boolean isPaging=true;//是否分页 true分页

    private String month;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Boolean getIsPaging() {
		return isPaging;
	}

	public void setIsPaging(Boolean paging) {
		isPaging = paging;
	}

	public String getCreateTimeValue() {
		return createTimeValue;
	}

	public void setCreateTimeValue(String createTimeValue) {
		this.createTimeValue = createTimeValue;
	}

	public Long getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
		this.countEndAndIndex();
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		this.countEndAndIndex();
	}

	public boolean isDoAount() {
		return doAount;
	}

	public void setDoAount(boolean doAount) {
		this.doAount = doAount;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.totalPage=(int) Math.ceil((double)total/pageSize);
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List lists) {
		if (lists != null && lists.size() > 0) {
			Object row = lists.get(0);
			if (row != null && row instanceof String) {
				setPageSize((Integer.valueOf((String) row)));
			}
		}
		this.rows = lists;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public int getBeginIndex() {
		return beginIndex;
	}

	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}
	
	
	private void countEndAndIndex(){
		//计算oracle 分页所用到的 开始和结束  rownum
			/* this.endIndex = (this.page * this.pageSize +1);
			 this.beginIndex = (this.page-1) * this.pageSize + 1;*/
		//计算mysql
		this.beginIndex = (this.page-1) * this.pageSize;
	}

}

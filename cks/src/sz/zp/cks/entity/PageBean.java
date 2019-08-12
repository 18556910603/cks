package sz.zp.cks.entity;

import java.util.ArrayList;
import java.util.List;

public class PageBean {
	private int pageSize;
	private int pageCode;
	private int allCount;
	private int allPages;
	private List datas=new ArrayList();
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageCode() {
		return pageCode;
	}
	public void setPageCode(int pageCode) {
		this.pageCode = pageCode;
	}
	public int getAllCount() {
		return allCount;
	}
	public void setAllCount(int allCount) {
		this.allCount = allCount;
	}
	public int getAllPages() {
		
		return (allCount-1)/pageSize+1;
	}
	public void setAllPages(int allPages) {
		this.allPages = allPages;
	}
	public List getDatas() {
		return datas;
	}
	public void setDatas(List datas) {
		this.datas = datas;
	}
	public PageBean(int pageSize, int pageCode, int allCount, int allPages, List datas) {
		super();
		this.pageSize = pageSize;
		this.pageCode = pageCode;
		this.allCount = allCount;
		this.allPages = allPages;
		this.datas = datas;
	}
	public PageBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}

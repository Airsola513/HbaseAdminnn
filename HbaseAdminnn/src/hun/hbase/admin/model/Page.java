package hun.hbase.admin.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Page {
	List<ArrayList<String>> arrayList = new ArrayList<ArrayList<String>>();
	public int size=10;
	public int currentpage=0;
	
	/**
	 * 返回下一页的offset
	 * @return
	 */
	public int nextpage(){
		return currentpage*size;
	}
	/**
	 * 返回上一个offset
	 * @return
	 */
	public int prevpage(){
		return (currentpage-1)*size;
	}
	

	public List<ArrayList<String>> getArrayList() {
		return arrayList;
	}
	public void setArrayList(List<ArrayList<String>> arrayList) {
		this.arrayList = arrayList;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}
	@Override
	public String toString() {
		return "Page [arrayList=" + arrayList + ", size=" + size
				+ ", currentpage=" + currentpage + "]";
	}

	
}

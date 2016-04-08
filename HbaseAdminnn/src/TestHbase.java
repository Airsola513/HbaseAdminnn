


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import hun.hbase.admin.dao.HbaseDao;
import hun.hbase.admin.dao.HbaseDaoImpl;
import hun.hbase.admin.model.Page;
import hun.hbase.admin.util.HbaseUtil;

import org.junit.Before;
import org.junit.Test;

public class TestHbase {
	
	private static HbaseDao hDao;
	
	@Before
	public void setUp() throws Exception{
	    hDao = new HbaseDaoImpl();
	}
	@Test
	public void add_table() throws Exception {
		boolean b = hDao.create_table("Table42", "info");
		System.out.println(b);
	}
	@Test
	public void get_table(){
		Map<String,String> list = hDao.get_alltable();
		
		System.out.println(list.get("users"));
		
		
	}
	@Test
	public void get_data(){
		Page page = new Page();
		List<ArrayList<String>> list = hDao.get_data_fortable("users", page);
		//System.out.println(list.size());
		for (ArrayList<String> arrayList : list) {
			System.out.println(arrayList.toString());
		}
	}
	public void test_connection(){
		System.out.println(HbaseUtil.getConnection());
	}
	@Test
	public void test(){
		//map里面是无序的
		Map<String,String> map = new HashMap<String,String>();
		map.put("a", "1");
		map.put("b", "2");
		map.put("c", "3");
		map.put("d", "2");
		Set<String> set = map.keySet();
		for (String string : set) {
			System.out.println(map.get(string));
		}
	}
	
}

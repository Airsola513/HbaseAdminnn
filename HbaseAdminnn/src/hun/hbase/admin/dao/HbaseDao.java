package hun.hbase.admin.dao;

import hun.hbase.admin.model.Cell;
import hun.hbase.admin.model.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * hbase表操作
 * @author hun
 * @Description: TODO
 * @date 2016年3月9日 下午9:21:00
 */
public interface HbaseDao {
	public boolean create_table(final String tablename,final String info);
	public boolean drop_table(final String tablename);
	public Map<String,String> get_alltable();
	public List<ArrayList<String>> get_data_fortable(final String tablename,Page page);
	public void add_data_totable(ArrayList<Map<String,String>> arrayList, final String tablename);
	public boolean addCell(List<Cell> cells);

}

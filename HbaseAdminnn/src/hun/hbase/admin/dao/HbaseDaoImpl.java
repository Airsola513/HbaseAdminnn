package hun.hbase.admin.dao;

import hun.hbase.admin.model.Cell;
import hun.hbase.admin.model.Page;
import hun.hbase.admin.util.HbaseUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class HbaseDaoImpl implements HbaseDao {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(HbaseDaoImpl.class);

	/**
	 * 列的版本数为1
	 */
	@Override
	public boolean create_table(final String tablename, final String info) {
		try {
			Admin admin = HbaseUtil.getConnection().getAdmin();
			TableName tableName2 = TableName.valueOf(tablename);
			HTableDescriptor tDescriptor = new HTableDescriptor(tableName2);
			if (admin.tableExists(tableName2)) {
				return false;
			} else {
				HColumnDescriptor hDescriptor = new HColumnDescriptor(info);
				hDescriptor.setMaxVersions(1);
				tDescriptor.addFamily(hDescriptor);
				admin.createTable(tDescriptor);
				LOGGER.info("创建表成功");
				return true;
			}
		} catch (IOException e) {
			LOGGER.info(HbaseUtil.getIP()
					+ "------遇到过这种情况，有时IP值丢失-------------------------------------------------------------------------------");
			e.printStackTrace();
			return false;
		} finally {
			try {
				HbaseUtil.getConnection().getAdmin().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			;
		}

	}

	@Override
	public boolean drop_table(final String tablename) {
		try {
			Admin admin = HbaseUtil.getConnection().getAdmin();
			TableName tableName2 = TableName.valueOf(tablename);
			if (admin.tableExists(tableName2)) {
				admin.disableTable(tableName2);
				admin.deleteTable(tableName2);
				LOGGER.info("删除表成功");
				return true;
			}
			return false;

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				HbaseUtil.getConnection().getAdmin().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			;
		}

	}

	@Override
	public Map<String,String> get_alltable() {
		Admin admin;
		Map<String,String> list = new HashMap<String, String>();
		try {
			admin = HbaseUtil.getConnection().getAdmin();
			HTableDescriptor[] tDescriptors = admin.listTables();
			for (HTableDescriptor hTableDescriptor : tDescriptors) {
				list.put(hTableDescriptor.getNameAsString(),hTableDescriptor.getColumnFamilies()[0].getNameAsString());
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				HbaseUtil.getConnection().getAdmin().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			;
		}
	}

	/**
	 * 
	 */
	@SuppressWarnings("deprecation")
	@Override
	public List<ArrayList<String>> get_data_fortable(final String tablename,
			Page page) {

		try {
			Table table = HbaseUtil.getConnection().getTable(
					TableName.valueOf(tablename));
			List<ArrayList<String>> arrayList = new ArrayList<ArrayList<String>>();
			Scan s = new Scan();
			// s.setStartRow(Bytes.toBytes(page.nextpage()));
			// s.setStopRow(Bytes.toBytes(10+page.nextpage()*10));
			// s.setStartRow(Bytes.toBytes(2));
			// s.setStopRow(Bytes.toBytes(4));

			ResultScanner scanner = table.getScanner(s);
			for (Result result : scanner) {// 按行去遍历
				ArrayList<String> list = new ArrayList<String>();
				// System.out.println(result.size());
				for (KeyValue kv : result.raw()) {
					long time = kv.getTimestamp();
					StringBuffer sb = new StringBuffer()
							.append(Bytes.toString(kv.getRow())).append("\t")
							.append(Long.toString(time)).append("\t")
							.append(Bytes.toString(kv.getFamily()))
							.append("\t")
							.append(Bytes.toString(kv.getQualifier()))
							.append("\t").append(Bytes.toString(kv.getValue()));
					list.add(sb.toString());

				}
				arrayList.add(list);
			}
			LOGGER.info("数据获取完成-----------------------------------------------------------------------");
			return arrayList;
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			HbaseUtil.CloseHbase();
		}
		return null;

	}

	/**
	 * 主键列必须为key,暂时只支持一个列族。ArrayList里面就是放一条条的非关系型数据 key,0 a,1 b,2 c,3
	 */
	@Override
	public void add_data_totable(ArrayList<Map<String, String>> arrayList,
			final String tablename) {
		String cf = "user_info";
		List<Put> ps = new ArrayList<Put>();
		try {
			Table table = HbaseUtil.getConnection().getTable(
					TableName.valueOf(tablename));
			for (Map<String, String> map : arrayList) {
				Put p = new Put(Bytes.toBytes(map.get("key")));
				map.remove("key");
				// 随机遍历Map
				Set<String> set = map.keySet();
				for (String string : set) {
					p.addColumn(Bytes.toBytes(cf), Bytes.toBytes(string),
							Bytes.toBytes(map.get(string)));
				}
				ps.add(p);
			}
			// 批量插入
			table.put(ps);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			HbaseUtil.CloseHbase();
		}

	}

	@Override
	public boolean addCell(List<Cell> cells) {
		List<Put> ps = new ArrayList<Put>();
		try {
			Table table = HbaseUtil.getConnection().getTable(
					TableName.valueOf(cells.get(0).getTablename0()));
			for (Cell cell : cells) {
				Put p = new Put(Bytes.toBytes(cell.getKey0()));
				p.addColumn(Bytes.toBytes(cell.getFamily0()),
						Bytes.toBytes(cell.getQualifier0()),
						Bytes.toBytes(cell.getValue0()));
				ps.add(p);
			}
			table.put(ps);
			LOGGER.info("数据插入成功----------------------------------------------------------------------------------------");
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			HbaseUtil.CloseHbase();
		}

	}

}

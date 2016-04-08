package hun.hbase.admin.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * 
 * @author huanghuiyuan
 *
 */
public final class HbaseUtil {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(HbaseUtil.class);
	private static String quorm = "hbase.zookeeper.quorum";
	private static String IP = null;
	private static Connection connection;

	private HbaseUtil() {
	}
	public static void setIP( String iP) {
		IP = iP;
	}
	public static String getIP(){
		return IP;
	}

	public static Connection getConnection() {
		try {
			Configuration conf = HBaseConfiguration.create();
			conf.addResource("hbase-site.xml");
			conf.addResource("core-site.xml");
			conf.set(quorm, IP);
			conf.set("hbase.zookeeper.property.clientPort", "2181");
			connection = ConnectionFactory.createConnection(conf);
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.debug(e.getMessage());
			return null;
		}
	}

	public static void CloseHbase() {
		try {
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

package hun.hbase.admin.model;

public class Cell {
	private String key0;
	private String Family0;
	private String Qualifier0;
	private String value0;
	private String tablename0;
	public String getKey0() {
		return key0;
	}
	public void setKey0(String key0) {
		this.key0 = key0;
	}
	public String getFamily0() {
		return Family0;
	}
	public void setFamily0(String family0) {
		Family0 = family0;
	}
	public String getQualifier0() {
		return Qualifier0;
	}
	public void setQualifier0(String qualifier0) {
		Qualifier0 = qualifier0;
	}
	public String getValue0() {
		return value0;
	}
	public void setValue0(String value0) {
		this.value0 = value0;
	}
	public String getTablename0() {
		return tablename0;
	}
	public void setTablename0(String tablename0) {
		this.tablename0 = tablename0;
	}
	@Override
	public String toString() {
		return "Cell [key0=" + key0 + ", Family0=" + Family0 + ", Qualifier0="
				+ Qualifier0 + ", value0=" + value0 + ", tablename0="
				+ tablename0 + "]";
	}
	
	
	
	

}

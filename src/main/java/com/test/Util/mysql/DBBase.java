package com.test.Util.mysql;

//
//Source code recreated from a .class file by IntelliJ IDEA
//(powered by FernFlower decompiler)
//
import com.test.customReporter.CustomLogger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import javax.sql.DataSource;
import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.KeyedObjectPoolFactory;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.log4j.Logger;

public class DBBase {
	protected static final Logger LOGGER = Logger.getLogger(DBBase.class);
	private static GenericObjectPool gPool = null;
	protected DBConfig conf;
	private DataSource ds = null;

	protected DBBase(DBConfig dbConfig) {
		this.conf = dbConfig;

		try {
			this.setUpPool();
		} catch (Exception var3) {
			LOGGER.error("Error Creating JDBC Connection Pool :::: " + var3);
			System.exit(1);
		}

	}

	private void setUpPool() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		gPool = new GenericObjectPool();
		gPool.setMaxActive(20);
		ConnectionFactory cf = new DriverManagerConnectionFactory(this.connectionURL(), this.conf.getuName(),
				this.conf.getPassword());
		new PoolableConnectionFactory(cf, gPool, (KeyedObjectPoolFactory) null, (String) null, false, true);
		this.ds = new PoolingDataSource(gPool);
	}

	private String connectionURL() {
		return "jdbc:mysql://" + this.conf.getHost() + ":" + this.conf.getPort() + "/" + this.conf.getSchemaName()
				+ "?autoReconnect=true&useSSL=false&zeroDateTimeBehavior=convertToNull";
	}

	public void closeDbConnection() {
		try {
			if (null != gPool) {
				gPool.close();
			}
		} catch (Exception var2) {
			CustomLogger.logFail("Error closing DB connection with " + this.conf);
		}

	}

	public int getSingleInt(String query) throws SQLException {
		Connection con = this.ds.getConnection();
		PreparedStatement stmnt = con.prepareStatement(query);
		ResultSet records = stmnt.executeQuery();
		con.close();
		int temp = 0;
		if (records.next()) {
			temp = records.getInt(1);
		}

		return temp;
	}

	public ResultSet getData(String value, String col, String table) throws SQLException {
		String query = "SELECT * FROM " + table + " WHERE " + col + " = '" + value + "'";
		return this.executeQuery(query);
	}

	public ResultSet getData(String table, String col1, String value1, String col2, String value2) throws SQLException {
		String query = "SELECT * FROM " + table + " WHERE " + col1 + " = '" + value1 + "' AND " + col2 + " = '" + value2
				+ "'";
		return this.executeQuery(query);
	}

	public ResultSet getData(String table, Map<String, String> whereClause) throws SQLException {
		boolean first = true;
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM ").append(table).append(" WHERE ");

		for (Iterator var5 = whereClause.entrySet().iterator(); var5.hasNext(); first = false) {
			Entry<String, String> s = (Entry) var5.next();
			sb.append(first ? "" : " AND ").append((String) s.getKey()).append(" = '").append((String) s.getValue())
					.append("'");
		}

		return this.executeQuery(sb.toString());
	}

	public int deleteRecord(String col, String value, String table) throws SQLException {
		String dQuery = "DELETE FROM " + table + " where " + col + "='" + value + "'";
		return this.executeUpdate(dQuery);
	}

	public int updateValue(String table, String col, String colValue, String uCol, String uValue) throws SQLException {
		String query = "UPDATE " + table + " SET " + uCol + " = '" + uValue + "' WHERE " + col + " = " + colValue;
		return this.executeUpdate(query);
	}

	public int updateValue(String table, String col, String colValue, String uCol, String uValue, String col2,
			String value2) throws SQLException {
		String query = "UPDATE " + table + " SET " + uCol + " = '" + uValue + "' WHERE " + col + " = " + colValue
				+ " AND " + col2 + " = " + value2;
		return this.executeUpdate(query);
	}

	public ResultSet executeQuery(String query) throws SQLException {
		Connection con = this.ds.getConnection();
		PreparedStatement stmnt = con.prepareStatement(query);
		CustomLogger.logPass("Query to be executed is ::::::::::: " + query);
		ResultSet rs = stmnt.executeQuery();
		return rs;
	}

	public int executeUpdate(String query) throws SQLException {
		Connection con = this.ds.getConnection();
		PreparedStatement stmnt = con.prepareStatement(query);
		CustomLogger.logPass("Query to be executed is ::::::::::: " + query);
		int code = stmnt.executeUpdate();
		con.close();
		return code;
	}

	public boolean closeConnection(ResultSet rs) throws SQLException {
		if (null != rs) {
			if (null != rs.getStatement()) {
				if (null != rs.getStatement().getConnection()) {
					rs.getStatement().getConnection().close();
				}

				rs.getStatement().close();
			}

			rs.close();
		}

		return true;
	}
}
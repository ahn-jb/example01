package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DbExample {
	
	public static Connection dbConn() {
		Db db = new DbImplOracle();
		return db.dbConn();
	}
	
	public static void dbConnClose(ResultSet rs,PreparedStatement pstmt, Connection conn) {
		Db db = new DbImplOracle();
		db.dbConnClose(rs, pstmt, conn);
	}
	
}

package model.dao.memo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import db.DbExample;
import model.dto.memo.MemoDTO;

public class MemoDAO {

	Connection conn= DbExample.dbConn();
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public int setInsert(MemoDTO dto) {
		int result = 0;
		try {
			String sql = "insert into memo values(seq_memo.nextval,?,?,sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getContent());
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			 e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<MemoDTO> getSelectAll(){
		ArrayList<MemoDTO> list = new ArrayList<>();
		try {
			String sql = "select * from memo order by no asc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemoDTO dto =new MemoDTO();
				dto.setNo(rs.getInt("no"));
				dto.setWriter(rs.getString("writer"));
				dto.setContent(rs.getString("content"));
				dto.setRegi_date(rs.getDate("regi_date"));
				list.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}
	
	
	public int getTotalRecord() {
		int count = 0;
		try {
			String sql="select count(*) from memo";
			
			pstmt = conn.prepareStatement(sql);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public ArrayList<MemoDTO> getSelectMemo(int startRecord,int lastRecord){
		ArrayList<MemoDTO> list = new ArrayList<>();
		try {
			String basicSql ="";
			basicSql += "select * from memo where no > 0 order by no desc";
			String sql = "";
			sql += "select * from (select A.*, Rownum Rnum from ("+basicSql+") A) where Rnum >=? and Rnum<=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRecord);
			pstmt.setInt(2, lastRecord);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemoDTO dto =new MemoDTO();
				dto.setNo(rs.getInt("no"));
				dto.setWriter(rs.getString("writer"));
				dto.setContent(rs.getString("content"));
				dto.setRegi_date(rs.getDate("regi_date"));
				list.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}
}

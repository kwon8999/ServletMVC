package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Dto.BoardDto;
import util.DbConnection;

public class BoardData {
	
	public int BoardAdd(int lvl, String subject, String write,String worddate, String context, String ip){
		int result = 0;
		int cnt = 1;
		StringBuffer sql = new StringBuffer();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DbConnection.getConnection();
			sql.append("INSERT INTO BOARDLIST(LVL, WORDNUM, TITLE, WORDNICK, WORDDATE, REC, CONTEXT, IP)");
			sql.append("values(?, WORDNUM.NEXTVAL, ?, ?, ?, 0, ?, ?)");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(cnt++, lvl);
			pstmt.setString(cnt++, subject);
			pstmt.setString(cnt++, write);
			pstmt.setString(cnt++, worddate);
			pstmt.setString(cnt++, context);
			pstmt.setString(cnt++, ip);
			
			System.out.println(lvl+" "+subject+""+write+" "+worddate+" "+context +" "+ ip);
			
			rs = pstmt.executeQuery();
			if(rs.next()) result = 1;
			
		} catch (Exception e) {
			System.out.println("Board Add Err : " + e.getMessage());
		}finally{
			DbConnection.CloseConn(conn, pstmt, rs);
		}
		
		
		return result;
	}
	
	public List<BoardDto> selectBoard(){
		List<BoardDto> list = new ArrayList<BoardDto>();
		StringBuffer sql = new StringBuffer();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DbConnection.getConnection();
			sql.append("  SELECT F.* FROM");
			sql.append("    (SELECT E.*, ROW_NUMBER () OVER(ORDER BY WORDNUM DESC) AS RNUM ");
			sql.append("         FROM (                                                    ");
			sql.append("             SELECT LVL, WORDNUM, TITLE, WORDNICK,                 ");
			sql.append("             WORDDATE, REC, CONTEXT, IP FROM BOARDLIST             ");
			sql.append("          ) e                                                      ");
			sql.append("      ) F                                                          ");
			sql.append("   WHERE RNUM BETWEEN 1 AND 10                                     ");
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				BoardDto dto = new BoardDto();
				dto.setLvl(rs.getInt("LVL"));
				dto.setWordNum(rs.getInt("WORDNUM"));
				dto.setTitle(rs.getString("TITLE"));
				dto.setWordNick(rs.getString("WORDNICK"));
				dto.setWordDate(rs.getString("WORDDATE"));
				dto.setRec(rs.getInt("REC"));
				dto.setContext(rs.getString("CONTEXT"));
				dto.setIp(rs.getString("IP"));
				
				list.add(dto);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			DbConnection.CloseConn(conn, pstmt, rs);
		}
		return list;
	}
	
	
	public List<BoardDto> ExcelBoard(){
		List<BoardDto> list = new ArrayList<BoardDto>();
		StringBuffer sql = new StringBuffer();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DbConnection.getConnection();

			sql.append("    SELECT E.*, ROW_NUMBER () OVER(ORDER BY WORDNUM ASC) AS RNUM ");
			sql.append("         FROM (                                                    ");
			sql.append("             SELECT LVL, WORDNUM, TITLE, WORDNICK,                 ");
			sql.append("             WORDDATE, REC, CONTEXT, IP FROM BOARDLIST             ");
			sql.append("          ) e                                                      ");

			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				BoardDto dto = new BoardDto();
				dto.setLvl(rs.getInt("LVL"));
				dto.setWordNum(rs.getInt("WORDNUM"));
				dto.setTitle(rs.getString("TITLE"));
				dto.setWordNick(rs.getString("WORDNICK"));
				dto.setWordDate(rs.getString("WORDDATE"));
				dto.setRec(rs.getInt("REC"));
				dto.setContext(rs.getString("CONTEXT"));
				dto.setIp(rs.getString("IP"));
				
				list.add(dto);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			DbConnection.CloseConn(conn, pstmt, rs);
		}
		return list;
	}
}

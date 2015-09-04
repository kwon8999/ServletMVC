package dao;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DbConnection;

public class UserData {
	
	public String testSHA256(String str){//SHA-256 암호화
		String SHA = null;
		
		try {
			MessageDigest sh = MessageDigest.getInstance("SHA-256");
			sh.update(str.getBytes());
			byte byteData[] = sh.digest();
			StringBuffer sb = new StringBuffer();
			
			for(int i = 1 ; i < byteData.length ; i++){
				sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
			}
			SHA = sb.toString();
		} catch (Exception e) {
			e.printStackTrace(); 
			SHA = null; 
		}
		
		return SHA;
	}

	//유저 가입
	public int insertDataSource(int lvl, String userId, String pwd, String name, String tel, String email, String nick){
		int value = 0;
		
		StringBuffer sql = new StringBuffer();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			int num = 1;
			conn = DbConnection.getConnection();
			
			sql.append(" insert into USR values(?, ?, ?, ?, ?, ?, ?) ");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(num++, lvl);
			pstmt.setString(num++, userId);
			pstmt.setString(num++, testSHA256(pwd));
			pstmt.setString(num++, name);
			pstmt.setString(num++, tel);
			pstmt.setString(num++, email);
			pstmt.setString(num++, nick);
			
			value = pstmt.executeUpdate();
			
			if(value > 0){
				conn.commit();
			}else{
				conn.rollback();
			}
		} catch (Exception e) {
			System.out.println("회원가입 에러 : " + e.getMessage());
		} finally{
			DbConnection.CloseConn(conn, pstmt, rs);
		}
		
		
		return value;
	}
	
	//아이디 체크
	public int idCheckMethod(String id){
		int check = 0;
		
		StringBuffer sql = new StringBuffer();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DbConnection.getConnection();
			sql.append(" SELECT USERID FROM USR WHERE USERID = ? ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if(rs.next()) check=1;
		} catch (Exception e) {
			System.out.println("idCheckMethod err : " + e);
		} finally{
			DbConnection.CloseConn(conn, pstmt, rs);
		}
		return check;
	}
	
	//유저 로그인
	public String userLogin(String id, String pwd){
		
		StringBuffer sql = new StringBuffer();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String nick = null;
		try {
			
		conn = DbConnection.getConnection();	
		sql.append(" SELECT USERID, PASSWD, NICK, NAME, TEL, EMAIL FROM USR WHERE USERID=? AND PASSWD=? ");
		pstmt = conn.prepareStatement(sql.toString());
		pstmt.setString(1, id);
		pstmt.setString(2, testSHA256(pwd));
		rs = pstmt.executeQuery();
		
		if(rs.next()) nick = rs.getString("nick")+","+rs.getString("NAME")+","+rs.getString("TEL")+","+rs.getString("EMAIL");
		} catch (Exception e) {
			System.out.println("userLogin Err :" + e);
		} finally{
			DbConnection.CloseConn(conn, pstmt, rs);
		}
		System.out.println("nick =" + nick);
		return nick;
	}
	
	//유저 업데이트
	public int UpdateUser(String uid, String name, String tel, String email, String nick){
		int i = 1;
		StringBuffer sql = new StringBuffer();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DbConnection.getConnection();
			sql.append(" UPDATE USR SET NAME=?, TEL=?, EMAIL=?, NICK=? WHERE USERID=? ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(i++, name);
			pstmt.setString(i++, tel);
			pstmt.setString(i++, email);
			pstmt.setString(i++, nick);
			pstmt.setString(i++, uid);
			i = pstmt.executeUpdate();
			System.out.println("asd   " + i);
			if(i > 0){
				conn.commit();
			}else{
				conn.rollback();
			}
		} catch (Exception e) {
			System.out.println("User Update Err : " + e.getMessage());
		} finally{
			DbConnection.CloseConn(conn, pstmt, rs);
		}
		
		return i;
	}
}

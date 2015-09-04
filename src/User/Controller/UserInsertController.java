package User.Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserData;


@SuppressWarnings("serial")
public class UserInsertController extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		UserAdd(req, resp);
	}
	
	private void UserAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("text/html; charset=UTF-8");
			
			PrintWriter out = resp.getWriter();
			String uid = req.getParameter("inputId");
			String pwd = req.getParameter("inputPwd");
			String nm = req.getParameter("inputNm");
			String tel = req.getParameter("inputTel");
			String email = req.getParameter("inputEmail");
			String nick = req.getParameter("inputNick");
			int lvl = 5; 
			
			System.out.println("inputTel :  " + tel);
			UserData data = new UserData();
			
			if(data.insertDataSource(lvl, uid, pwd, nm, tel, email, nick) == 1){
				out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
		        out.println("<!--");
		        out.println("  alert('회원가입이 완료 되었습니다.');");
		        out.println("  window.close();");
		        out.println("//-->");
		        out.println("</SCRIPT>");
			}else{
				out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
		        out.println("<!--");
		        out.println("  alert('회원가입이 실패 하였습니다.');");
		        out.println("  return false;");
		        out.println("//-->");
		        out.println("</SCRIPT>");
			}

	}
}

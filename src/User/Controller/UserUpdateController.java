package User.Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserData;


@SuppressWarnings("serial")
public class UserUpdateController extends HttpServlet{

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		UserUpdate(req, resp);
	}
	
	private void UserUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8"); 
		
		HttpSession session = req.getSession();
		PrintWriter out = resp.getWriter();
		String uid = req.getParameter("uid");
		String nm = req.getParameter("inputNm");
		String tel = req.getParameter("inputTel");
		String email = req.getParameter("inputEmail");
		String nick = req.getParameter("inputNick");
		
		UserData data = new UserData();
		if(data.UpdateUser(uid, nm, tel, email, nick) > 0){
			session.setAttribute("nick", nick);
			session.setAttribute("uid", uid);
			session.setAttribute("name", nm);
			session.setAttribute("tel", tel);
			session.setAttribute("email", email);
			
			System.out.println("변경된 session : " + session.getAttribute("email"));
			
			out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
	        out.println("<!--");
	        out.println("  alert('회원정보 수정이 완료 되었습니다.');");
	        out.println("  window.close();");
	        out.println("//-->");
	        out.println("</SCRIPT>");
		}else{
			out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
	        out.println("<!--");
	        out.println("  alert('회원 정보수정이 실패 하였습니다.');");
	        out.println("  return false;");
	        out.println("//-->");
	        out.println("</SCRIPT>");
		}
	}
}

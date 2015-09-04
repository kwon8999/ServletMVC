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
public class LoginController extends HttpServlet{

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		LoginProccess(req, resp);
	}
	
	private void LoginProccess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = req.getSession();
		PrintWriter out = resp.getWriter();
		String nick = null;
		String uid = req.getParameter("uid");
		String pwd = req.getParameter("pwd");
		
		UserData data = new UserData();
		nick = data.userLogin(uid, pwd);
		if(nick != null){
			String re[] = new String(nick).split(",");
			session.setAttribute("nick", re[0]);
			session.setAttribute("uid", uid);
			session.setAttribute("name", re[1]);
			session.setAttribute("tel", re[2]);
			session.setAttribute("email", re[3]);
			
			out.println(true);
		}else{
			out.println(false);
		}
	}
}

package Board.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardData;


@SuppressWarnings("serial")
public class BoardController extends HttpServlet{
	
	BoardData data = new BoardData();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Board(req, resp);
	}
	
	private void Board(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		int lvl = Integer.parseInt(req.getParameter("lvl"));
		String title = req.getParameter("title");
		String wordNick = req.getParameter("wordNick");
		String context = req.getParameter("context");
		String ip = req.getParameter("Ip");
		
		Date d = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddkkmm");
		String day = sd.format(d);
		
		PrintWriter out = resp.getWriter();
		
		if(data.BoardAdd(lvl, title, wordNick, day, context, ip) > 0){
			out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
			out.println("<--");
			out.println("  alert('등록되었습니다.');");
			out.println("-->");
			out.println("</SCRIPT>");
			RequestDispatcher dispatcher = req.getRequestDispatcher("/");
			dispatcher.forward(req, resp);
		}else{
			out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
			out.println("<--");
			out.println("  alert('오류가 발생되었습니다.');");
			out.println("  location.href='/';");
			out.println("-->");
			out.println("</SCRIPT>");
		}

	}
	
}

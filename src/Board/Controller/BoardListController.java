package Board.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dto.BoardDto;
import dao.BoardData;

@SuppressWarnings("serial")
public class BoardListController extends HttpServlet{

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		BoardList(req, resp);
	}
	
	private void BoardList(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		BoardData data = new BoardData();
		List<BoardDto> list = data.selectBoard();
//		System.out.println("list : " + list.size());
		req.setAttribute("list", list);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/Main.jsp");
		dispatcher.forward(req, resp);
	}
}

package Excel.Controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import Dto.BoardDto;
import dao.BoardData;


@SuppressWarnings("serial")
public class ExcelDownloadController extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Exceldownload(req, resp);
	}
	
	private void Exceldownload(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		BoardData data = new BoardData();
		List<BoardDto> list = data.ExcelBoard();
		Workbook xls = new HSSFWorkbook();
		ServletOutputStream sos = null;
		
		try {
			Sheet sheet = xls.createSheet("Excel");

			sheet.setColumnWidth(3, 5000);						//시트 길이조정  첫번째는 cell , 두번째는 크기
			sheet.setColumnWidth(5, 5000);
			sheet.setColumnWidth(7, 5000);
			
	//		CellStyle style = xls.createCellStyle();
			
	//		style.setWrapText(true);
	//		style.setFillBackgroundColor(HSSFColor.BLUE.index);
	//		style.setFillForegroundColor(HSSFColor.RED.index);
	//		style.setFillPattern(CellStyle.BIG_SPOTS);
			
			Row row = null;
			Cell cell = null;
			
			row = sheet.createRow(1);
			
			cell = row.createCell(2);
			cell.setCellValue("번호");
	
			cell = row.createCell(3);
			cell.setCellValue("제목");
			
			cell = row.createCell(4);
			cell.setCellValue("글쓴이");
			
			cell = row.createCell(5);
			cell.setCellValue("날짜");
			
			cell = row.createCell(6);
			cell.setCellValue("조회수");
			
			cell = row.createCell(7);
			cell.setCellValue("IP");
			
			for(int i = 0 ; i < list.size(); i++){
				
				BoardDto dto = list.get(i);
				row = sheet.createRow(i+2);					//세로 칸
				
				cell = row.createCell(2);					//가로 칸
				cell.setCellValue(dto.getWordNum());		
				
				cell = row.createCell(3);
				cell.setCellValue(dto.getTitle());
				
				cell = row.createCell(4);
				cell.setCellValue(dto.getWordNick());
				
				cell = row.createCell(5);
				cell.setCellValue(dto.getWordDate());
				
				cell = row.createCell(6);
				cell.setCellValue(dto.getRec());
				
				cell = row.createCell(7);
				cell.setCellValue(dto.getIp());
			}
			
			
			resp.setHeader("Content-Disposition", "attachment; filename="+ URLEncoder.encode("ExcelDownload.xls", "UTF-8"));
			resp.setContentType("application/vnd.ms.excel");
			sos = resp.getOutputStream();
			xls.write(sos);
			xls.close();
			sos.flush();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally{
			if(sos != null) sos.close();
		}
	}
}

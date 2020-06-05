package net.ezens.pointnix.poi;

import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExcelService {
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	public void selectExcelList(HttpServletResponse res) {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		SXSSFWorkbook wb = new SXSSFWorkbook(100);
		Sheet sheet = wb.createSheet();
		
		try {
			sqlSession.select("queryname", "", new ResultHandler<ExcelVO>() {
				@Override
				public void handleResult(ResultContext<? extends ExcelVO> context) {
					ExcelVO vo = context.getResultObject();
					Row row = sheet.createRow(context.getResultCount() - 1);
					Cell cell = null;
					cell = row.createCell(0);
					cell.setCellValue(vo.getArgs1().toString());
					cell = row.createCell(1);
					cell.setCellValue(vo.getArgs2().toString());
					cell = row.createCell(2);
					cell.setCellValue(vo.getArgs3().toString());
				}
			});
			
			res.setHeader("Set-Cookie", "fileDownload=true; path=/");
			res.setHeader("Content-Disposition", String.format("attachment; filename=\"test.xlsx\""));
			wb.write(res.getOutputStream());
			
		} catch(Exception e) {
			res.setHeader("Set-Cookie", "fileDownload=false; path=/");
			res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			res.setHeader("Content-Type", "text/html; charset=utf-8");
			
			OutputStream out = null;
			try {
				out = res.getOutputStream();
				byte[] data = new String("fail").getBytes();
				out.write(data, 0, data.length);
			} catch(Exception ex) {
				e.printStackTrace();
			} finally {
				if(out != null) try { out.close(); } catch(Exception ex) {}
			}
		} finally {
			sqlSession.close();
			
			wb.dispose();
			
			try { wb.close(); } catch(Exception e) {}
		}
	}
}

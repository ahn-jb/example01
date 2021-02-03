package controller.memo;



import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.dao.memo.MemoDAO;
import model.dto.memo.MemoDTO;



@WebServlet("/memo_servlet/*")
public class MemoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String path = request.getContextPath();
		String url = request.getRequestURL().toString();
		String page = "/main/main.jsp";
		
		String pageNumber_;
		pageNumber_ = request.getParameter("pageNumber");
		if(pageNumber_ == null || pageNumber_.trim().equals("")) {
			pageNumber_="1";
		}
//		char imsi;
//		for(int i=0; i<pageNumber_.length(); i++) {
//			 imsi = pageNumber_.charAt(i);
//		}
		int pageNumber = Integer.parseInt(pageNumber_);
		if(url.indexOf("write.do") != -1) {
			MemoDAO dao = new MemoDAO();
//			String no_ = request.getParameter("no");
//			int no = Integer.parseInt(no_);
			request.setAttribute("menu_gubun", "memo_write");
			request.setAttribute("pageNumber",pageNumber);
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		}else if(url.indexOf("writeProc.do") != -1) {
			MemoDAO dao = new MemoDAO();
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");
			
			MemoDTO dto = new MemoDTO();
			dto.setWriter(writer);
			dto.setContent(content);
			
			int result = dao.setInsert(dto);
			if(result >0) {
				System.out.println("등록되었습니다.");
			}else {
				System.out.println("결과코드: " +result);
			}
			
		}else if(url.indexOf("list.do") != -1) {
			MemoDAO dao = new MemoDAO();
			int pageSize = 5;
			int blockSize= 10;
			int totalRecord = dao.getTotalRecord();
			int number =totalRecord - pageSize * (pageNumber-1);
			int startRecord = pageSize * (pageNumber -1) +1;
			int lastRecord = pageSize * pageNumber;
			int totalPage =0;
			int startPage =1;
			int lastPage = 1;
			if(totalRecord>0) {
				totalPage = totalRecord / pageSize +(totalRecord%pageSize == 0? 0:1);
				startPage = (pageNumber/blockSize - (pageNumber % blockSize !=0 ? 0:1))*blockSize +1 ;
				lastPage = startPage + blockSize - 1;
				if(lastPage > totalPage) {	
					lastPage = totalPage;
				}
			}
			ArrayList<MemoDTO> list = dao.getSelectMemo(startRecord, lastRecord);
			String temp = "/memo/list.jsp";
			request.setAttribute("list", list);
			request.setAttribute("pageNumber",pageNumber);
			request.setAttribute("pageSize",pageSize);
			request.setAttribute("blockSize",blockSize);
			request.setAttribute("totalRecord",totalRecord);
			request.setAttribute("number",number);
			request.setAttribute("startRecord",startRecord);
			request.setAttribute("lastRecord",lastRecord);
			request.setAttribute("totalPage",totalPage);
			request.setAttribute("startPage",startPage);
			request.setAttribute("lastPage",lastPage);
			RequestDispatcher rd = request.getRequestDispatcher(temp);
			rd.forward(request, response);
			
		}
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}

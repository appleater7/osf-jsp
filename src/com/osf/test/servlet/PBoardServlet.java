package com.osf.test.servlet;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.osf.test.service.PBoardService;
import com.osf.test.service.impl.PBoardServiceImpl;

public class PBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static String savePath = "D:\\study\\workspace\\osf-jsp\\WebContent\\upload";
    private PBoardService pbs = new PBoardServiceImpl();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		uri = uri.replace("/pboard/", "");
		if ("list".equals(uri)) {
			request.setAttribute("pBoardList", pbs.selectPBoardList());
			RequestDispatcher rd = request.getRequestDispatcher("/views/photo-board/list.jsp");
			rd.forward(request, response);
			return;
		}else {
			try {
				int pbNum = Integer.parseInt(uri);
				request.setAttribute("pBoard", pbs.selectPBoard(pbNum));
				RequestDispatcher rd = request.getRequestDispatcher("/views/photo-board/view.jsp");
				rd.forward(request, response);
				return;
			} catch(NumberFormatException e){
				throw new ServletException("상세 조회는 번호조회만 가능합니다.");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		uri = uri.replace("/pboard/", "");
		if ("insert".equals(uri)) {
			DiskFileItemFactory dfiFactory = new DiskFileItemFactory();
			// DFIF tmp 디렉토리를 설정하고 용량을 설정하는 역할
			// Factory 는 특별한 의미가 있어서 변수명에서도 부각시켜야 함
			
//			dfiFactory.setRepository(new File(System.getProperty("java.io.tmpdir")));
			String tmpPath = System.getProperty("java.io.tmpdir");
			File tmpFile = new File(tmpPath);
			dfiFactory.setRepository(tmpFile);
			dfiFactory.setSizeThreshold(10*1024*1024);
			
			ServletFileUpload sfu = new ServletFileUpload(dfiFactory);
			sfu.setFileSizeMax(20*1024*1024); // 파일이 한건 최대
			sfu.setSizeMax(20*1024*1024); // 전체가 사이즈 맥스
			try {
				List<FileItem> fileList = sfu.parseRequest(request);
				Map<String, String> pBoard = new HashMap<>();
				for(int i = 0; i < fileList.size(); i++) {
					FileItem fi = fileList.get(i);
					if (fi.isFormField()) {
						pBoard.put(fi.getFieldName(), fi.getString("utf-8"));
					}else {
						String rFileName = fi.getName();
						String extName = rFileName.substring(rFileName.lastIndexOf(".")+1);
						String fileName = System.currentTimeMillis() + "";
						File saveFile = new File(savePath + "\\" + fileName + "." + extName);
						pBoard.put("pb_real_path", rFileName);
						pBoard.put("pb_file_path", "/upload/" + fileName + "." + extName);
						fi.write(saveFile);
					}
				}
				if (pbs.insertBoard(pBoard) == 1) {
					request.setAttribute("msg", "집중해라");
					request.setAttribute("url", "/views/photo-board/insert.jsp");
					RequestDispatcher rd = request.getRequestDispatcher("/views/result.jsp");
					rd.forward(request, response);
					return;
				}else {
					
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if ("update".equals(uri)) {
			
		}else if ("delete".equals(uri)) {
			
		}else {
			
		}
	}
	public static void main(String[] args) {

	}
}

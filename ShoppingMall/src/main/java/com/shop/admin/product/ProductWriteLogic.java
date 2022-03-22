package com.shop.admin.product;

import java.io.IOException;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.shop.command.Command;
import com.shop.dao.ProductDAO;
import com.shop.dto.ProductDTO;

@MultipartConfig(
		fileSizeThreshold=1024*1024,
		maxFileSize=1024*1024*50, // 하나의 파일 50메가
		maxRequestSize=1024*1024*50*5 //전체 용량
)


public class ProductWriteLogic implements Command{
	public void execute(HttpServletRequest request,HttpServletResponse response) {
		ProductDTO pdto = null;
		boolean result = false;
		String uploadPath = request.getServletContext().getRealPath("/admin/uploadFile");
		 
	 	int maxSize = 1024*1024*10; // 10Mb
	 	String filename = "";
	 	String originFile = "";
	 	
	 	MultipartRequest multi = null;
		try {
			multi = new MultipartRequest(request, uploadPath, maxSize, "utf-8",new DefaultFileRenamePolicy());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	 	
 	 	ProductDAO pdao = ProductDAO.getInstance();
 	 	result = pdao.registerProduct(multi);
		
		request.setAttribute("state", "write");
		request.setAttribute("result", result);
	}

}

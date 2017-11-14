package com.catapp.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.catapp.action.ChemData;
import com.catapp.entity.FileInfo;

/**
 * Servlet implementation class Download_Internal_CServlet
 */
@WebServlet("/Download_Internal_CServlet")
public class Download_Internal_CServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Download_Internal_CServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	
		
		String[] cellLines = request.getParameterValues("cellNames");
		String[] assay = request.getParameterValues("assayLines");
		System.out.println("cellLines::"+cellLines.length+"assay::"+assay.length);
		cellLines=cellLines[0].split(",");
		assay=assay[0].split(",");
		System.out.println("cellLines::"+cellLines.length+"assay::"+assay.length);


		ArrayList<String> assayList = new ArrayList<String>();
		ArrayList<String> cellList = new ArrayList<String>();


		
		for (String as : assay) {
			System.out.println("as:: " + as);
			assayList.add(as);

		}

		for (String cl : cellLines) {
			System.out.println("cl:: " + cl);
			cellList.add(cl);

		}

		
		
		
		// for table after cell and assay//
		/*ArrayList<FileInfo> fileInfoList = new ChemData().getFileInfo(cellList, assayList);
		System.out.println("fileInfoList::"+fileInfoList);
		
		request.setAttribute("fileInfo", fileInfoList);
	    request.getRequestDispatcher("/WEB-INF/catAppDownloadPage.jsp").forward(request, response);
	  */  
	    /* phenotypes */
	    
	    HashMap<String, ArrayList<String>> phenoList=new ChemData().getAssayPhenoList(cellList, assayList);
	    System.out.println("phenoList::"+phenoList);
		
		request.setAttribute("phenoList", phenoList);
	    request.getRequestDispatcher("/WEB-INF/catAppDownloadPage.jsp").forward(request, response);
	    


		// getServletContext().getRequestDispatcher("/WEB-INF/Download_Internal_C.jsp").forward(request,
		// response);
	}

}
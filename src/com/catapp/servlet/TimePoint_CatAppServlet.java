package com.catapp.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.catapp.action.ChemData;

/**
 * Servlet implementation class TimePoint_CatAppServlet
 */
@WebServlet("/TimePoint_CatApp")
public class TimePoint_CatAppServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TimePoint_CatAppServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("  TimePoint_CatAppServlet starts ");

		String[] cellLines = request.getParameterValues("lCM");
		String[] assay = request.getParameterValues("lAssay");
		
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

		HashMap<String, ArrayList<String>> timeList = new ChemData().getTimePoint(cellList,assayList);


		request.setAttribute("timeList", timeList);
		System.out.println("  TimePoint_CatAppServlet ends ");

		request.getRequestDispatcher("/WEB-INF/catAppDownloadPage.jsp").forward(request, response);
	}

}

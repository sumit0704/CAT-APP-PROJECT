package com.catapp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.catapp.action.ChemData;
import com.catapp.connection.DBConnection;

/**
 * Servlet implementation class Download_Internal2
 */
@WebServlet("/Download_Internal")
public class Download_Internal extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Download_Internal() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	/*
	 * protected void service(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { // TODO Auto-generated
	 * method stub System.out.println("\n  Download_Internal, Servlet" );
	 * ArrayList<String> all_cell_lines = new ArrayList<>(Arrays.asList("CM",
	 * "HEP", "ENDO", "HUV", "Neur", "Macro", "A375", "A549", "HepG2", "HepaRG",
	 * "MCF7", "HT29", "LN229", "HEK10205f", "HLMVEC", "HMePC", "SH-SY5Y"));
	 * ArrayList<String> selected_cell_lines = new ArrayList<String>();
	 * 
	 * for(int x = 0; x < 17; x = x + 1) { // System.out.println("cell lines: "+
	 * cell_lines.get(x)); String received_value =
	 * request.getParameter(all_cell_lines.get(x)); // receiving the post value
	 * if (received_value != null){ System.out.println("received value: "+
	 * received_value); selected_cell_lines.add(received_value); } }
	 * 
	 * request.setAttribute("selected_cell_lines", selected_cell_lines); //
	 * submit value to jsp page:
	 * 
	 * ArrayList<String> England_cell_lines = new
	 * ArrayList<>(Arrays.asList("A375", "A549", "HepG2", "HepaRG", "MCF7",
	 * "HT29", "LN229", "HEK10205f", "HLMVEC", "HMePC", "SH-SY5Y"));
	 * request.setAttribute("England_cell_lines", England_cell_lines); // submit
	 * value to jsp page.
	 * 
	 * // String received_value = request.getParameter("CM"); // receiving the
	 * post value // request.setAttribute("CM", received_value); // submit value
	 * to following page:
	 * 
	 * // System.out.println("Download_Internal, Servlet: " + received_value);
	 * 
	 * 
	 * getServletContext().getRequestDispatcher("/WEB-INF/Download_Internal.jsp"
	 * ).forward(request, response);
	 * 
	 * }
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		try {
			

			ArrayList<String> selected_cell_lines = new ArrayList<String>();

			String[] cellLines = request.getParameterValues("cellNames");

			System.out.println("cellLines::" + cellLines);
			for (String cell : cellLines) {
				System.out.println("cell:: " + cell);
				selected_cell_lines.add(cell);

			}

			HashMap<String, ArrayList<String>> assayList = new ChemData().getCellAssayList(selected_cell_lines);
			System.out.println("assayList::" + assayList);

			request.setAttribute("assayList", assayList);
			request.getRequestDispatcher("/WEB-INF/catAppDownloadPage.jsp").forward(request, response);

 		} catch (Exception e) {

		}
	}
}

package com.catapp.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.catapp.action.CatAppConstants;
import com.catapp.action.ChemData;

/**
 * Servlet implementation class DownloadConcaweAsIs
 */
@WebServlet("/DownloadConcaweAsIs")
public class DownloadConcaweAsIs extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DownloadConcaweAsIs() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String downloadType = request.getParameter("fileRadio");
		System.out.println("downloadType::" + downloadType);

		XSSFWorkbook workbook = null;
		if (CatAppConstants.downloadTypeAsIS.equals(downloadType)) {
			String[] lFiles = request.getParameterValues("cas");
			String lFilePath = "C:\\Users\\CATAPP\\serverfiles\\ConcaweFiles";
			try {
				if (lFiles.length > 0) {
					for (int i = 0; i < lFiles.length; i++) {
						File lFile = new File(lFilePath + "\\" + lFiles[i]);

						response.setHeader("Content-disposition", "attachment; filename="
								+ CatAppConstants.fileNameAnalyticalDownload + System.currentTimeMillis() + ".xlsx");
						response.setContentType("xlsx");
						OutputStream out = response.getOutputStream();

						FileInputStream in = new FileInputStream(lFile);
						byte[] buffer = new byte[4096];
						int length;
						while ((length = in.read(buffer)) > 0) {
							out.write(buffer, 0, length);
						}
						in.close();
						out.flush();
						out.close();
					}

				}
			} catch (Exception e) {

			}
		}

		else if (CatAppConstants.downloadTypeMolecular.equals(downloadType)) {
			System.out.println("downloadTypeMolecular");

			String molecularClass = request.getParameter("molecularSelect");
			String cas[]=request.getParameterValues("cas");
			String casNumbers=cas[0];

			workbook = getMolecularData(molecularClass, casNumbers);
			if (null != workbook) {
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "attachment; filename="
						+ CatAppConstants.fileNameAnalyticalDownload + System.currentTimeMillis() + ".xlsx");
				workbook.write(response.getOutputStream());
				workbook.close();
			} else {
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();

				out.println("<html>");
				out.println("<head>");
				out.println("<title>No data found</title>");
				out.println("</head>");
				out.println("<body bgcolor=\"white\">");
				out.println("<p>No data found</p>");
				out.println("</body>");
				out.println("</html>");
			}

		} else if (CatAppConstants.downloadTypeCarbon.equals(downloadType)) {
			System.out.println("downloadTypeCarbon");

			String carbonClass = request.getParameter("carbonSelect");
			String cas[]=request.getParameterValues("cas");
			String casNumbers=cas[0];

			workbook = getCarbonData(carbonClass, casNumbers);
			if (null != workbook) 
			{
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ CatAppConstants.fileNameAnalyticalDownload + System.currentTimeMillis() + ".xlsx");
			workbook.write(response.getOutputStream());
			workbook.close();
			}
			else
			{
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();

				out.println("<html>");
				out.println("<head>");
				out.println("<title>No data found</title>");
				out.println("</head>");
				out.println("<body bgcolor=\"white\">");
				out.println("<p>No data found</p>");
				out.println("</body>");
				out.println("</html>");
			}
		}

	}

	private XSSFWorkbook getCarbonData(String carbonClass, String casNumbers) {
		System.out.println("Start of method getCarbonData");
		HashMap<String, String> carbonMap = new ChemData().getCarbonData(carbonClass, casNumbers);

		XSSFWorkbook workbook;
		if(null!=carbonMap && carbonMap.size()!=0 )
		workbook = createCarbonExcel(carbonClass, casNumbers, carbonMap);
		
		else return null;
		System.out.println("End of method getCarbonData");

		return workbook;
	}

	@SuppressWarnings("deprecation")
	private XSSFWorkbook createCarbonExcel(String carbonClass, String casNumbers, HashMap<String, String> carbonMap) {
		System.out.println("Start of method createCarbonExcel");

		File lFile = null;
		FileInputStream fis = null;
		XSSFWorkbook workbook = null;

		try {
			
			
		   lFile = new File("C:/Users/CATAPP/serverfiles/templates/Excel By Carbon.xlsx");
			
			fis = new FileInputStream(lFile);
			workbook = new XSSFWorkbook(fis);
			XSSFSheet mySheet = workbook.getSheetAt(0);

			XSSFRow lRow1 = mySheet.getRow(1);
			XSSFCell lCell = lRow1.getCell(1);
			lCell.setCellValue("CAS " + casNumbers);
			XSSFRow lRow2 = mySheet.getRow(2);
			XSSFCell lCell1 = lRow2.getCell(1);
			lCell1.setCellValue(carbonMap.get("sample"));

			XSSFFont font = workbook.createFont();
			font.setBold(true);

			CellStyle style = workbook.createCellStyle();
			style.setFont(font);
			style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
			style.setFillPattern(CellStyle.BIG_SPOTS);

			/*
			 * lRow = sheet.createRow(1); XSSFRow lRow2 = sheet.createRow(2);
			 * XSSFCell cell2=null; int i=0;
			 */
			carbonMap.remove("sample");
			carbonMap.remove("C-nr");
			int i = 6;
			for (String key : carbonMap.keySet()) {

				XSSFRow lRow3 = mySheet.createRow(i);
				XSSFCell lCell2 = lRow3.createCell(0);
				if (key.equals("RowSum")) {
					i--;
					/*
					 * lCell2.setCellValue("Total Sum of C-nr value");
					 * lCell2.setCellStyle(style);
					 */
				} else {
					lCell2.setCellValue(key);
				}

				XSSFCell lCell3 = lRow3.createCell(1);
				if (key.equals("RowSum")) {
					// lCell3.setCellValue(Double.parseDouble(carbonMap.get(key))+"%");
				} else {

					lCell3.setCellValue(Double.parseDouble(carbonMap.get(key)));
				}

				/*
				 * cell = lRow.createCell(i); cell2 = lRow2.createCell(i);
				 * cell.setCellValue(key);
				 * 
				 * cell2.setCellValue(Double.parseDouble(carbonMap.get(key)));
				 * i++;
				 */
				i++;
			}
			XSSFRow lRow4 = mySheet.createRow(i);
			XSSFCell lCell2 = lRow4.createCell(0);
			lCell2.setCellValue("Total Sum of C-nr value");
			lCell2.setCellStyle(style);
			XSSFCell lCell3 = lRow4.createCell(1);
			lCell3.setCellValue(Double.parseDouble(carbonMap.get("RowSum")) + "%");
			/*
			 * cell = lRow.createCell(i); cell2 = lRow2.createCell(i);
			 * cell.setCellValue("sum");
			 * 
			 * cell2.setCellValue(Double.parseDouble(carbonMap.get("sum")));
			 */

			System.out.println("end of method createCarbonExcel");

		} catch (Exception e) {

		}
		return workbook;

	}

	private XSSFWorkbook getMolecularData(String molecularClass, String casNumbers) {
		System.out.println("Start of method getMolecularData");

		List<HashMap<String, String>> molecularList = new ChemData().getMolecularData(molecularClass, casNumbers);

		XSSFWorkbook workbook = null;
		try {
			if(null!=molecularList && molecularList.size()!=0)
			workbook = createMolecularExcel(molecularClass, casNumbers, molecularList);
			
			else return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("end of method getMolecularData");

		return workbook;

	}

	@SuppressWarnings("deprecation")
	private XSSFWorkbook createMolecularExcel(String molecularClass, String casNumbers,
			List<HashMap<String, String>> molecularList) throws IOException {
		System.out.println("end of method createMolecularExcel");

		File lFile = new File("C:/Users/CATAPP/serverfiles/templates/Excel By Molecular Class.xlsx");
		FileInputStream fis = new FileInputStream(lFile);
		XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);
		XSSFSheet mySheet = myWorkBook.getSheetAt(0);
		XSSFRow lRow1 = mySheet.getRow(1);
		XSSFCell lCell = lRow1.getCell(1);
		lCell.setCellValue("CAS " + casNumbers);
		XSSFRow lRow2 = mySheet.getRow(2);
		XSSFCell lCell1 = lRow2.getCell(1);
		lCell1.setCellValue(molecularList.get(0).get("sample"));
		XSSFRow lRow3 = mySheet.getRow(3);
		XSSFCell lCell2 = lRow3.getCell(1);
		lCell2.setCellValue(molecularClass);

		XSSFRow lRowClass = mySheet.getRow(6);
		XSSFCell lCellClass = lRowClass.getCell(1);
		lCellClass.setCellValue(molecularClass);

		// File lFile = new
		// File("C:/Users/sharm/serverfiles/templates/molecularExcel.xlsx");
		// FileInputStream fis = new FileInputStream(lFile);
		XSSFRow lRow = null;
		XSSFCell cell = null;

		// int i = 2;
		int lLastRow = 0;
		boolean lPercentFlag = false;
		for (HashMap<String, String> map : molecularList) {
			if (null == mySheet.getRow(Integer.parseInt(map.get("row")) + 4)) {
				lRow = mySheet.createRow(Integer.parseInt(map.get("row")) + 4);
			} else {
				lRow = mySheet.getRow(Integer.parseInt(map.get("row")) + 4);
			}

			if (CatAppConstants.attributeCNR.equals(map.get("attribute"))) {
				cell = lRow.createCell(0);
				if (map.get("value").equals("Total")) {
					lLastRow = lRow.getRowNum();
					cell.setCellValue("Total For " + molecularClass);
					XSSFFont font = myWorkBook.createFont();
					font.setBold(true);
					lPercentFlag = true;
					CellStyle style = myWorkBook.createCellStyle();
					style.setFont(font);
					style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
					style.setFillPattern(CellStyle.BIG_SPOTS);
					cell.setCellStyle(style);

				} else {
					if (lLastRow != 0 && lLastRow == lRow.getRowNum()) {
						cell.setCellValue(Double.parseDouble(map.get("value")) + "%");
					} else {
						cell.setCellValue(Double.parseDouble(map.get("value")));
					}

				}
			} else {
				cell = lRow.createCell(1);
				if (lLastRow != 0 && lLastRow == lRow.getRowNum()) {
					cell.setCellValue(Double.parseDouble(map.get("value")) + "%");
				} else {
					cell.setCellValue(Double.parseDouble(map.get("value")));
				}

			}

		}
		System.out.println("end of method createMolecularExcel");

		return myWorkBook;

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doPost");
		doGet(request, response);
	}

}
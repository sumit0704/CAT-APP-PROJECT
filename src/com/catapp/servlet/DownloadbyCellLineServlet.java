package com.catapp.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.catapp.action.ChemData;
import com.catapp.connection.DBConnection;

/**
 * Servlet implementation class DownloadbyCellLineServlet
 */
@WebServlet("/DownloadbyCellLineServlet")
public class DownloadbyCellLineServlet extends HttpServlet {
	public static final Logger logger = Logger.getLogger(DownloadbyCellLineServlet.class.toString());
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadbyCellLineServlet() {
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

		
		Connection lConn			= null;
		PreparedStatement lPstmt    = null;
		ResultSet lRst              = null;
		String lCellLineName		= null;
		String lPhenoTypeName		= null;
		try{
			String lTimePoint= request.getParameter("timepoint");
			String lCellLine = request.getParameter("cellLine");
			String [] lPhenotype =request.getParameterValues("phenotype");
			String lPhenoString="";
			String lButtonClick="";
			if(request.getParameter("download")!=null){
				lButtonClick="Download";
			}else if(request.getParameter("json")!=null){
				lButtonClick="json";
			}	
			
		File lFile = new File ("C:/Users/CATAPP/serverfiles/templates/Excel By CellLine.xlsx");
		lConn=new DBConnection().getConnection(); 
		FileInputStream fis = new FileInputStream(lFile);
		XSSFWorkbook myWorkBook = new XSSFWorkbook (fis);
		XSSFSheet mySheet = myWorkBook.getSheet("ExcelByCellLine");
		 
		StringBuilder lBuilder = new StringBuilder("select b.timepoint,a.thousandx,a.hunderedx,a.tenx,a.onex ")
								 .append(",d.name as phenotype,c.name as cellline,e.name as chem_name,e.cas_number,e.cat_app_id,")
								 .append(" a.point_of_departure,b.phenotype from processed_readings_details a join ")
								 .append(" processed_readings_header b")
								 .append(" on a.header_id=b.entity_id join celllines c on c.tag=b.cellline join phenotypes d on ")
								 .append(" d.tag=b.phenotype join chemical_cas_concawe_mapping e on e.name=a.chemical_id ")
								 .append(" where  c.tag=? and b.timepoint=? and d.tag in ( "+ new DownloadbyCompoundServlet().generateQs(lPhenotype.length)+" )");
		
		lPstmt=lConn.prepareStatement(lBuilder.toString());
		
		lPstmt.setString(1, lCellLine);
		lPstmt.setString(2, new ChemData().getTimePoints().get(Long.parseLong(lTimePoint)));
		int j=3;
		for(int i=0;i<lPhenotype.length;i++){
			if(i==0){
				lPhenoString=lPhenotype[i];
			}else{
				lPhenoString=lPhenoString+","+lPhenotype[i];
			}
			lPstmt.setString(j, lPhenotype[i].toString());
			j++;
		}
		lRst=lPstmt.executeQuery();
		int i=7;
		while (lRst.next()){
			if(lCellLineName==null){
				lCellLineName=lRst.getString("cellline");
			}if(lPhenoTypeName==null){
				lPhenoTypeName=lRst.getString("phenotype");
			}
			XSSFRow lRow =mySheet.createRow(i);
			XSSFCell lPheno=lRow.createCell(0);
			lPheno.setCellValue(lRst.getString("phenotype"));
			
			
			XSSFCell lChemName=lRow.createCell(1);
			lChemName.setCellValue(lRst.getString("chem_name"));
			
			XSSFCell lCasNumber=lRow.createCell(2);
			lCasNumber.setCellValue(lRst.getString("cas_number"));
			
			XSSFCell lCatAppId=lRow.createCell(3);
			lCatAppId.setCellValue(lRst.getString("cat_app_id"));
			
			XSSFCell lPODCell=lRow.createCell(4);
			lPODCell.setCellValue(lRst.getString("point_of_departure"));
			
			XSSFCell l1000XCell=lRow.createCell(5);
			l1000XCell.setCellValue(lRst.getString("thousandx"));
			XSSFCell l100XCell=lRow.createCell(6);
			l100XCell.setCellValue(lRst.getString("hunderedx"));
			XSSFCell l10XCell=lRow.createCell(7);
			l10XCell.setCellValue(lRst.getString("tenx"));
			XSSFCell l1XCell=lRow.createCell(8);
			l1XCell.setCellValue(lRst.getString("onex"));
			i++;
		}
		XSSFRow lRow1 =mySheet.getRow(1);
		XSSFCell lCell =lRow1.getCell(1);
		lCell.setCellValue(lCellLineName);
		XSSFRow lRow2 =mySheet.getRow(2);
		XSSFCell lCell1 =lRow2.getCell(1);
		lCell1.setCellValue(lPhenoString);
		XSSFRow lRow3 =mySheet.getRow(3);
		XSSFCell lCell2 =lRow3.getCell(1);
		lCell2.setCellValue(new ChemData().getTimePoints().get(Long.parseLong(lTimePoint)));
		
		if(lButtonClick=="json"){
			JSONObject json = new JSONObject();

		    // Iterate through the rows.
		    JSONArray rows = new JSONArray();
				
				for ( Iterator<Row> rowsIT = mySheet.rowIterator(); rowsIT.hasNext(); )
			    {
					Row row = rowsIT.next();
			        JSONObject jRow = new JSONObject();

			        // Iterate through the cells.
			        JSONArray cells = new JSONArray();
			        for ( Iterator<Cell> cellsIT = row.cellIterator(); cellsIT.hasNext(); )
			        {
			            Cell cell = cellsIT.next();
			            cells.add(cell.getStringCellValue());
			        }
			        jRow.put( "cell", cells );
			        rows.add( jRow );
			    }

			    // Create the JSON.
			    json.put( "rows", rows );
			    response.setContentType("text/x-json;charset=UTF-8");
				response.setHeader("Content-Disposition", "attachment; filename=\"File.json");
				String json1 = json.toJSONString();
				response.getWriter().write(json1);
				myWorkBook.close();
			    
			    return;
			}
		
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=\"File.xlsx");
		myWorkBook.write(response.getOutputStream());
		myWorkBook.close();
		
			
		}catch (IOException e){
			logger.error("Error Occured While downloading file---->DownloadbyCompound");
			
		}
		catch (SQLException e){
			logger.error("Error Occured While downloading file---->DownloadbyCompound");
			
		}
		finally{
			try{
				lConn.close();
				lPstmt.close();
				lRst.close();
			}catch(Exception e){
				logger.error("Error Occured While closing connection---->DownloadbyCompound");
			}
		}
		
	
	}

}

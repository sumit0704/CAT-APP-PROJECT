package com.catapp.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
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

import com.catapp.action.CatAppConstants;
import com.catapp.connection.DBConnection;

/**
 * Servlet implementation class DownloadbyCompoundServlet
 */
@WebServlet("/DownloadbyCompoundServlet")
public class DownloadbyCompoundServlet extends HttpServlet {
	//public static final Logger logger = Logger.getLogger(DownloadbyCompoundServlet.class.toString());
	private static final long serialVersionUID = 1L;
	public static final Logger logger = Logger.getLogger(DownloadbyCompoundServlet.class.toString());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadbyCompoundServlet() {
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
		// TODO Auto-generated method stub

		
		Connection lConn			= null;
		PreparedStatement lPstmt    = null;
		ResultSet lRst              = null;
		String lCellLineName		= null;
		String lPhenoTypeName		= null;
		String lCatAppId			= null;
		String lCASNumber			= null;
		String lButtonClick			= null;	
		
		try{
			String lCompound= request.getParameter("chemicals");
			String lCellLine = request.getParameter("cellLines");
			String [] lPhenotype =request.getParameterValues("phenotypes");
			if(request.getParameter("download")!=null){
				lButtonClick="Download";
			}else if(request.getParameter("json")!=null){
				lButtonClick="json";
			}	
				File lFile = new File ("C:/Users/CATAPP/serverfiles/templates/Excel By Compound.xlsx");
		lConn=new DBConnection().getConnection(); 
		String lPhenoString="";
		FileInputStream fis = new FileInputStream(lFile);
		XSSFWorkbook myWorkBook = new XSSFWorkbook (fis);
		XSSFSheet mySheet = myWorkBook.getSheet("ExcelByCompound");
		 
		StringBuilder lBuilder = new StringBuilder("select b.timepoint,a.thousandx,a.hunderedx,a.tenx,a.onex ")
								 .append(",d.name as phenotype,c.name as cellline,e.name,e.cat_app_id,")
								 .append(" a.point_of_departure,b.phenotype from processed_readings_details a join ")
								 .append(" processed_readings_header b")
								 .append(" on a.header_id=b.entity_id join celllines c on c.tag=b.cellline join phenotypes d on ")
								 .append(" d.tag=b.phenotype join chemical_cas_concawe_mapping e on e.name=a.chemical_id ")
								 .append(" where c.tag=? and e.cas_number=? and d.tag in ( " +generateQs(lPhenotype.length)+ " ) order by b.timepoint ");
		
		lPstmt=lConn.prepareStatement(lBuilder.toString());
		
		lPstmt.setString(1, lCellLine);
		lPstmt.setString(2, lCompound);
		/*lPstmt.setString(3, lPhenotype[0]);
		lPstmt.setString(4, lPhenotype[1]);*/
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
		logger.error("Pheno String is----->"+lPhenoString);
		logger.error("Query String is----->"+lBuilder);
		logger.error("Query String is----->"+lCellLine);
		logger.error("Query String is----->"+lCASNumber);
		
		//lPstmt.setString(3, lPhenoString);
		lRst=lPstmt.executeQuery();
		int i=9;
		while (lRst.next()){
			if(lCellLineName==null){
				lCellLineName=lRst.getString("cellline");
			}if(lPhenoTypeName==null){
				lPhenoTypeName=lPhenoString;
			}if (lCASNumber==null){
				lCASNumber=lRst.getString("name");
			}if (lCatAppId==null){
				lCatAppId =lRst.getString("cat_app_id");
			}
			XSSFRow lRow =mySheet.createRow(i);
			XSSFCell lPhenotyPeCell=lRow.createCell(0);
			lPhenotyPeCell.setCellValue(lRst.getString("phenotype"));
			XSSFCell lTimePointCell=lRow.createCell(1);
			lTimePointCell.setCellValue(lRst.getString("timepoint"));
			
			BigDecimal a1 = new BigDecimal(lRst.getString("thousandx"));
			BigDecimal roundOff1 = a1.setScale(2, BigDecimal.ROUND_HALF_EVEN);
			
			BigDecimal a2 = new BigDecimal(lRst.getString("hunderedx"));
			BigDecimal roundOff2 = a2.setScale(2, BigDecimal.ROUND_HALF_EVEN);

			BigDecimal a3 = new BigDecimal(lRst.getString("tenx"));
			BigDecimal roundOff3 = a3.setScale(2, BigDecimal.ROUND_HALF_EVEN);

			BigDecimal a4 = new BigDecimal(lRst.getString("onex"));
			BigDecimal roundOff4 = a4.setScale(2, BigDecimal.ROUND_HALF_EVEN);

			BigDecimal a5 = new BigDecimal(lRst.getString("point_of_departure"));
			BigDecimal roundOff5 = a5.setScale(2, BigDecimal.ROUND_HALF_EVEN);

			
			
			XSSFCell l1000XCell=lRow.createCell(2);
			l1000XCell.setCellValue(roundOff1.doubleValue());
			XSSFCell l100XCell=lRow.createCell(3);
			l100XCell.setCellValue(roundOff2.doubleValue());
			XSSFCell l10XCell=lRow.createCell(4);
			l10XCell.setCellValue(roundOff3.doubleValue());
			XSSFCell l1XCell=lRow.createCell(5);
			l1XCell.setCellValue(roundOff4.doubleValue());
			XSSFCell lPODCell=lRow.createCell(6);
			lPODCell.setCellValue(roundOff5.doubleValue());
			i++;
		}
		XSSFRow lRow1 =mySheet.getRow(1);
		XSSFCell lCell =lRow1.getCell(1);
		lCell.setCellValue(lCompound);
		XSSFRow lRow2 =mySheet.getRow(2);
		XSSFCell lCell1 =lRow2.getCell(1);
		lCell1.setCellValue(lCASNumber);
		XSSFRow lRow3 =mySheet.getRow(3);
		XSSFCell lCell2 =lRow3.getCell(1);
		lCell2.setCellValue(lCatAppId);
		XSSFRow lRow4 =mySheet.getRow(4);
		XSSFCell lCell3 =lRow4.getCell(1);
		lCell3.setCellValue(lCellLineName);
		XSSFRow lRow5 =mySheet.getRow(5);
		XSSFCell lCell4 =lRow5.getCell(1);
		lCell4.setCellValue(lPhenoTypeName);
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
				response.setHeader("Content-Disposition", "attachment; filename="+CatAppConstants.fileNameCatAppDownload+System.currentTimeMillis()+".json");
				String json1 = json.toJSONString();
				response.getWriter().write(json1);
				myWorkBook.close();
			    
			    return;
			}

		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename="+CatAppConstants.fileNameCatAppDownload+System.currentTimeMillis()+".xlsx");
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
				
			}
		}
		
	
	}
  public String generateQs(int size){
	  String Q="?";
	  String lReturn="";
	  for(int i=0;i<size;i++){
		  if(i==0){
			  lReturn=Q;
		  }else{
			  lReturn=lReturn+","+Q;
		  }
	  }
	  return lReturn;
  }
}

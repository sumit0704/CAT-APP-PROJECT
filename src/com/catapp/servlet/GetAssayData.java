package com.catapp.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.catapp.action.ChemData;
import com.catapp.connection.DBConnection;

/**
 * Servlet implementation class GetAssayData
 */
@WebServlet("/GetAssayData")
public class GetAssayData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final Logger logger = Logger.getLogger(GetAssayData.class.toString());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAssayData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String lChemical = request.getParameter("ch");
		String lCellLine = request.getParameter("cl");
		String lPhenotype = request.getParameter("ph");
		String lTimePoint = request.getParameter("tp");
		java.sql.PreparedStatement lPst = null;
		ResultSet lRst	= null;
		Connection lConn = null;
		try{
			lConn= new DBConnection().getConnection();
			StringBuilder lQuery= new StringBuilder("select b.chemical_id,b.thousandx,b.hunderedx,b.tenx,b.onex,b.point_of_departure ");
			lQuery.append(" From processed_readings_header a ");
			lQuery.append(" join processed_readings_details b");
			lQuery.append(" on a.entity_id=b.header_id join ");
			lQuery.append(" chemical_cas_concawe_mapping c ");
			lQuery.append(" on c.name=b.chemical_id where ");
			lQuery.append(" c.cas_number=? and a.cellline=? and  a.timepoint=? and a.phenotype=? ");
			lPst=lConn.prepareStatement(lQuery.toString());
			lPst.setString(1, lChemical);
			lPst.setString(2, lCellLine);
			lPst.setString(4, lPhenotype);
			lPst.setString(3, new ChemData().getTimePoints().get(Long.parseLong(lTimePoint)));
			
			lRst = lPst.executeQuery();
			StringBuilder lXMLBuilder = new StringBuilder();
			lXMLBuilder.append("<doseresponse>");
			while(lRst.next()){
				BigDecimal a = new BigDecimal(lRst.getString(2));
				BigDecimal roundOff = a.setScale(2, BigDecimal.ROUND_HALF_EVEN);
				BigDecimal a1 = new BigDecimal(lRst.getString(3));
				BigDecimal roundOff1 = a1.setScale(2, BigDecimal.ROUND_HALF_EVEN);
				BigDecimal a2 = new BigDecimal(lRst.getString(4));
				BigDecimal roundOff2 = a2.setScale(2, BigDecimal.ROUND_HALF_EVEN);
				BigDecimal a3 = new BigDecimal(lRst.getString(5));
				BigDecimal roundOff3 = a3.setScale(2, BigDecimal.ROUND_HALF_EVEN);
				BigDecimal a4 = new BigDecimal(lRst.getString(6));
				BigDecimal roundOff4 = a4.setScale(2, BigDecimal.ROUND_HALF_EVEN);
				lXMLBuilder.append("<dose>");
				lXMLBuilder.append("<thousandx>" + roundOff+"</thousandx>");
				lXMLBuilder.append("<hunderedx>" + roundOff1 +"</hunderedx>");
				lXMLBuilder.append("<tenx>"    +   roundOff2+"</tenx>");
				lXMLBuilder.append("<onex>" + roundOff3+"</onex>");
				lXMLBuilder.append("<pod>" + roundOff4+"</pod>");
				lXMLBuilder.append("</dose>");
			}
			lPst.clearParameters();
			lRst=null;
			StringBuilder lQuery1= new StringBuilder("select b.control_tag,b.value1,b.value2,b.value3,b.value4,b.value5,b.value6 From processed_readings_header a ");
			lQuery1.append(" join control_readings b on a.entity_id=b.header_id");
			lQuery1.append(" where a.cellline=? and a.timepoint=? and a.phenotype=? ");
			lPst=lConn.prepareStatement(lQuery1.toString());
			lPst.setString(1, lCellLine);
			lPst.setString(2, new ChemData().getTimePoints().get(Long.parseLong(lTimePoint)));
			lPst.setString(3, lPhenotype);
			
			lRst = lPst.executeQuery();
			String lValues= null;
			while(lRst.next()){
				String lValue1=lRst.getString(2);
				String lValue2=lRst.getString(3);
				String lValue3=lRst.getString(4);
				String lValue4=lRst.getString(5);
				String lValue5=lRst.getString(6);
				String lValue6=lRst.getString(7);
				
				BigDecimal roundOff1=null;
				BigDecimal roundOff2=null;
				BigDecimal roundOff3=null;
				BigDecimal roundOff4=null;
				BigDecimal roundOff5=null;
				BigDecimal roundOff6=null;
				BigDecimal a1 = new BigDecimal(lValue1);
				roundOff1 = a1.setScale(2, BigDecimal.ROUND_HALF_EVEN);
				
				BigDecimal a2 = new BigDecimal(lValue2);
				roundOff2 = a2.setScale(2, BigDecimal.ROUND_HALF_EVEN);
				
				BigDecimal a3 = new BigDecimal(lValue3);
				roundOff3 = a3.setScale(2, BigDecimal.ROUND_HALF_EVEN);
				
				BigDecimal a4 = new BigDecimal(lValue4);
				 roundOff4 = a4.setScale(2, BigDecimal.ROUND_HALF_EVEN);
				 if(lValue5!=null){
					 
					 BigDecimal a5 = new BigDecimal(lValue5);
					 roundOff5 = a5.setScale(2, BigDecimal.ROUND_HALF_EVEN);
				 }
				 if(lValue6!=null){
					 
					 BigDecimal a6 = new BigDecimal(lValue6);
					 roundOff6 = a6.setScale(2, BigDecimal.ROUND_HALF_EVEN);
				 }
				
				
				if(lValues==null){
					if(roundOff1!=null){
						lValues=roundOff1+","+roundOff2+","+roundOff3+","+roundOff4;
						if(roundOff5!=null){
							lValues=lValues+","+roundOff5;
						}else if(roundOff6!=null){
							lValues=lValues+","+roundOff6;
						}
						
					}
					
				}else{
					
					lValues=lValues+","+roundOff1+","+roundOff2+","+roundOff3+","+roundOff4;
					if(roundOff5!=null){
						lValues=lValues+","+roundOff5;
					}else if(roundOff6!=null){
						lValues=lValues+","+roundOff6;
					}
				}
				
			}
			lXMLBuilder.append("<control>");
			lXMLBuilder.append("<pointone>" + lValues+"</pointone>");
			lXMLBuilder.append("</control>");
			
			lXMLBuilder.append("</doseresponse>");
			
			response.setContentType("text/xml");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(lXMLBuilder.toString());
			
		}
		catch(Exception e){
			logger.error("Error Occured in getting assaydata",e);
		}finally{
			try{
				if(lConn!=null){
					lConn.close();
				}
			}catch(Exception e1){
				logger.error("Unable to close connection",e1);
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

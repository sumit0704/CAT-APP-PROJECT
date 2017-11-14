package com.catapp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
 * Servlet implementation class ViewFilesForDownload
 */
@WebServlet("/ViewFilesForDownload")
public class ViewFilesForDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(ViewFilesForDownload.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewFilesForDownload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String [] lSelectedCM 		= 	null;
		String lQueryfilter     	=   null;
		Connection lConn 			=   null;
		PreparedStatement lPst		=	null;
		ResultSet lRst				=   null;
		

		try{
			String lDownloadType=request.getParameter("lDT");
			 
			lConn = new DBConnection().getConnection();
			if(request.getParameter("lCM")!=null && request.getParameter("lCM").trim().length()>0 ){
				lSelectedCM= request.getParameter("lCM").toString().split(",");
				lQueryfilter = " and cell_line_id in (" +new ChemData().generateQForparameter(lSelectedCM.length)+")";
			}
			
			String [] lAssayName  	= 	null;
			if(request.getParameter("lAN")!=null && request.getParameter("lAN").trim().length()>0){
				lAssayName =	request.getParameter("lAN").toString().split(",");
				if(lQueryfilter!=null && lQueryfilter.trim().length()>0) {
					lQueryfilter =lQueryfilter + " and assay_type in (" + new ChemData().generateQForparameter(lAssayName.length)+")";
				}else{
					lQueryfilter = " and assay_type in (" +new ChemData().generateQForparameter(lAssayName.length)+")";
				}
				
			}
			
			String [] lPhenoType    =   null;
			if(request.getParameter("lPT")!=null && request.getParameter("lPT").trim().length()>0){
				lPhenoType =request.getParameter("lPT").toString().split(",");
				if(lQueryfilter!=null && lQueryfilter.trim().length()>0) {
					lQueryfilter =lQueryfilter + " and phenotype_id in (" + new ChemData().generateQForparameter(lPhenoType.length)+")";
				}else{
					lQueryfilter = " and phenotype_id in (" +new ChemData().generateQForparameter(lPhenoType.length)+")";
				}
			}
			
			String [] lTimePoint	=	null;
			if(request.getParameter("lTP")!=null && request.getParameter("lTP").trim().length()>0){
				lTimePoint= request.getParameter("lTP").toString().split(",");
				if(lQueryfilter!=null && lQueryfilter.trim().length()>0) {
					lQueryfilter =lQueryfilter + " and timepoint in (" + new ChemData().generateQForparameter(lTimePoint.length)+")";
				}else{
					lQueryfilter = " and timepoint in (" +new ChemData().generateQForparameter(lTimePoint.length)+")";
				}
				
			}

			String [] lDilution	=	null;
			if(request.getParameter("lDil")!=null && request.getParameter("lDil").trim().length()>0){
				lDilution= request.getParameter("lDil").toString().split(",");
				if(lQueryfilter!=null && lQueryfilter.trim().length()>0) {
					lQueryfilter =lQueryfilter + " and dilution in (" + new ChemData().generateQForparameter(lDilution.length)+")";
				}else{
					lQueryfilter = " and dilution in (" +new ChemData().generateQForparameter(lDilution.length)+")";
				}
				
			}
			
			
			StringBuilder lBuilder = new StringBuilder ("select entity_id, cell_line_id,assay_type,dilution,timepoint,file_name,phenotype_id From file_info where file_category= " +lDownloadType);
				lPst = lConn.prepareStatement(lBuilder.toString()+lQueryfilter);
				
				int lParameterStartCount =1;
				int lLoopOverCount =1;
				String lTestString="";
				if(lSelectedCM!=null && lSelectedCM.length>0){
					for(int j=0;j<lSelectedCM.length;j++){
						if(lLoopOverCount ==0){
							lPst.setString(lParameterStartCount,lSelectedCM[0]);
						}else{
							if(lTestString.trim().length()>0){
								lTestString=lTestString+","+lSelectedCM[j];
							}else{
								lTestString=lSelectedCM[j];
							}
							lPst.setString(j+1, lSelectedCM[j]);
						}
						lLoopOverCount++;
					}
					
				}
				if(lAssayName!=null && lAssayName.length>0){
					
					for(int j=0;j<lAssayName.length;j++){
						
						if(lLoopOverCount==0){
							lPst.setString(lParameterStartCount, lAssayName[0]);
						}else{
							if(lTestString.trim().length()>0){
								lTestString=lTestString+","+lAssayName[j];
							}else{
								lTestString=lAssayName[j];
							}
							lPst.setString(lLoopOverCount, lAssayName[j]);
						}
						lLoopOverCount++;
					}
					
				}
				if(lPhenoType!=null && lPhenoType.length>0){

					for(int j=0;j<lPhenoType.length;j++){
						
						if(lLoopOverCount==0){
							lPst.setString(lParameterStartCount,lPhenoType[0]);
						}else{
							if(lTestString.trim().length()>0){
								lTestString=lTestString+","+lPhenoType[j];
							}else{
								lTestString=lPhenoType[j];
							}
							lPst.setString(lLoopOverCount,lPhenoType[j]);
						}
						lLoopOverCount++;
					}
					
				}
				if(lTimePoint!=null && lTimePoint.length>0){

					for(int j=0;j<lTimePoint.length;j++){
						
						if(lLoopOverCount==0){
							lPst.setString(lParameterStartCount, lTimePoint[0]);
						}else{
							if(lTestString.trim().length()>0){
								lTestString=lTestString+","+lTimePoint[j];
							}else{
								lTestString=lTimePoint[j];
							}
							lPst.setString(lLoopOverCount, lTimePoint[j]);
						}
						lLoopOverCount++;
					}
					
				}
				if(lDilution!=null && lDilution.length>0){

					for(int j=0;j<lDilution.length;j++){
						
						if(lLoopOverCount==0){
							
							lPst.setString(lParameterStartCount, lDilution[0]);
						}else{
							if(lTestString.trim().length()>0){
								lTestString=lTestString+","+lDilution[j];
							}else{
								lTestString=lDilution[j];
							}
							lPst.setString(lLoopOverCount, lDilution[j]);
						}
						lLoopOverCount++;
					}
					
				}
				
				lRst = lPst.executeQuery();
		
			StringBuilder lXMLBuilder = new StringBuilder();
			lXMLBuilder.append("<filelist>");
			
			while(lRst.next()){
				lXMLBuilder.append("<file>");
				lXMLBuilder.append("<entity_id>" + lRst.getString("entity_id")+"</entity_id>");
				lXMLBuilder.append("<cell>" + lRst.getString("cell_line_id")+"</cell>");
				if(lDownloadType.equals("3")){
					lXMLBuilder.append("<filename>" + lRst.getString("file_name")+"</filename>");
				
				}else{
					
					lXMLBuilder.append("<assay>" + lRst.getString("assay_type") +"</assay>");
					if(lDownloadType.equals("1")){
						lXMLBuilder.append("<dilution>" + lRst.getString("dilution") +"</dilution>");
						
					}else if(lDownloadType.equals("2")){
						lXMLBuilder.append("<Phenotype>" + lRst.getString("phenotype_id") +"</Phenotype>");
					}
					lXMLBuilder.append("<timepoint>" + lRst.getString("timepoint") +"</timepoint>");
					lXMLBuilder.append("<filename>" + lRst.getString("file_name")+"</filename>");
					
				}
				
				lXMLBuilder.append("</file>");
				
			}
			
			lXMLBuilder.append("</filelist>");
			response.setContentType("text/xml");
			response.setHeader("Cache-Control", "no-cache");
			/*out.print(lXMLBuilder.toString());
			out.close();*/
			response.getWriter().write(lXMLBuilder.toString());
			/*request.setAttribute("xls", pFileListXls);
			request.setAttribute("img", pFileListImg);
			request.setAttribute("flat", pFileListFlat);*/
			
		} catch(Exception e){
			LOGGER.error("Error Occured while searching for files", e);
		}
	
		//request.getRequestDispatcher("/WEB-INF/catAppDownloadPage.jsp").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/*protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}*/

}


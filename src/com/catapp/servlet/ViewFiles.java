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
 * Servlet implementation class ViewFiles
 */
@WebServlet(name = "ViewFilesServlet", urlPatterns = { "/ViewFilesServlet" })
public class ViewFiles extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(ViewFiles.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewFiles() {
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
			
			String [] lPlateDesign	=	null;
			if(request.getParameter("lPD")!=null && request.getParameter("lPD").trim().length()>0){
				lPlateDesign= request.getParameter("lPD").toString().split(",");
				if(lQueryfilter!=null && lQueryfilter.trim().length()>0) {
					lQueryfilter =lQueryfilter + " and plate_id in (" + new ChemData().generateQForparameter(lPlateDesign.length)+")";
				}else{
					lQueryfilter = " and plate_id in (" +new ChemData().generateQForparameter(lPlateDesign.length)+")";
				}
				
			}
			
			
			StringBuilder lBuilder = new StringBuilder ("select * From file_info where rowstate!=-1 ");
				lPst = lConn.prepareStatement(lBuilder.toString()+lQueryfilter);
				
				int lParameterStartCount =1;
				int lLoopOverCount =1;
				if(lSelectedCM!=null && lSelectedCM.length>0){
					for(int j=0;j<lSelectedCM.length;j++){
						if(lLoopOverCount ==0){
							lPst.setLong(lParameterStartCount, Long.parseLong(lSelectedCM[0]));
						}else{
							lPst.setLong(j+1, Long.parseLong(lSelectedCM[j]));
						}
						lLoopOverCount++;
					}
					
				}
				if(lAssayName!=null && lAssayName.length>0){
					
					for(int j=0;j<lAssayName.length;j++){
						
						if(lLoopOverCount==0){
							lPst.setLong(lParameterStartCount, Long.parseLong(lAssayName[0]));
						}else{
							lPst.setLong(lLoopOverCount, Long.parseLong(lAssayName[j]));
						}
						lLoopOverCount++;
					}
					
				}
				if(lPhenoType!=null && lPhenoType.length>0){

					for(int j=0;j<lPhenoType.length;j++){
						
						if(lLoopOverCount==0){
							lPst.setLong(lParameterStartCount, Long.parseLong(lPhenoType[0]));
						}else{
							lPst.setLong(lLoopOverCount, Long.parseLong(lPhenoType[j]));
						}
						lLoopOverCount++;
					}
					
				}
				if(lPlateDesign!=null && lPlateDesign.length>0){

					for(int j=0;j<lPlateDesign.length;j++){
						
						if(lLoopOverCount==0){
							lPst.setLong(lParameterStartCount, Long.parseLong(lPlateDesign[0]));
						}else{
							lPst.setLong(lLoopOverCount, Long.parseLong(lPlateDesign[j]));
						}
						lLoopOverCount++;
					}
					
				}

				lRst = lPst.executeQuery();
		
			StringBuilder lXMLBuilder = new StringBuilder();
			lXMLBuilder.append("<filelist>");
			
			while(lRst.next()){
				
				lXMLBuilder.append("<file>");
				lXMLBuilder.append("<type>" + lRst.getString("file_type")+"</type>");
				lXMLBuilder.append("<filepath>" + lRst.getString("entity_id") +"</filepath>");
				lXMLBuilder.append("<filename>" + lRst.getString("file_name")+"."+lRst.getString("file_type")+"</filename>");
				lXMLBuilder.append("<uploadtime>" + lRst.getString("logged_date")+"</uploadtime>");
				if(lRst.getString("description")!=null && lRst.getString("description").trim().length()>0){
					lXMLBuilder.append("<desc>" + lRst.getString("description") +"</desc>");
					
				}else{
					lXMLBuilder.append("<desc>"+"N/A"+"</desc>");
				}
				lXMLBuilder.append("</file>");
				/*HashMap<String,String>lMap = new HashMap<String,String>();
				lMap.put(lRst.getString("file_name"), lRst.getString("file_path"));
				if(lRst.getString("file_type")!=null 
						&& lRst.getString("file_type").equals("xls") ){
					pFileListXls.add(lMap);
					
				}else if (lRst.getString("file_type")!=null 
						&& lRst.getString("file_type").equals("img")){
					pFileListImg.add(lMap);
				}else{
					pFileListFlat.add(lMap);
				}*/
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

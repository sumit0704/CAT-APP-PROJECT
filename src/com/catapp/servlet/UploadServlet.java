package com.catapp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.catapp.action.ChemData;
import com.catapp.connection.DBConnection;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	public static final Logger logger = Logger.getLogger(UploadServlet.class.toString());
	private static final long serialVersionUID = 1L;
	
	
	

    /**
     * Default constructor. 
     */
    public UploadServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service() throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	/*	PrintWriter out = response.getWriter();
		out.println("View and Download Page<br> From ViewDownloadServlet.java: Hello Java!");
		
		getServletContext().getRequestDispatcher("/WEB-INF/contact.jsp").forward(request, response);
		// This line is go to "/WEB-INF/Adminpage.jsp" with all the parameters.
*/		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection lConn = null;
		
		try{
			lConn= new DBConnection().getConnection();
			HashMap<Long,String>lPhenoMap =  new ChemData().getNamesofInputs("phenotypes",lConn);
			HashMap<Long,String>lAssayMap =  new ChemData().getNamesofInputs("assaynames",lConn);
			HashMap<Long,String>lCellMap  =  new ChemData().getNamesofInputs("celllines",lConn);
			HashMap<Long,String>lTimMap   =  new ChemData().getTimePoints();
			request.setAttribute("pheno", lPhenoMap);
			request.setAttribute("assay", lAssayMap);
			request.setAttribute("cell", lCellMap);
			request.setAttribute("time", lTimMap);
			HttpSession session=request.getSession(false);
			if(session!=null){
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/Upload.jsp");
			rd.forward(request, response);
			}
			else{
				request.setAttribute("error","Current session is lost. Please log in");
				request.getRequestDispatcher("/LoadDataForHome").include(request, response);  
				}
			
		}catch(Exception e){
			logger.error("Error Occured while dispatching the request",e);
		}finally{
			if(lConn!=null){
				try{
					lConn.close();
				}catch(Exception e){
					logger.error("Error Occured while closing the connection",e);
				}
			}
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

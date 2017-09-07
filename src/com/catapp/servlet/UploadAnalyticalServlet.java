package com.catapp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.catapp.action.ChemData;
import com.catapp.connection.DBConnection;

/**
 * Servlet implementation class UploadAnalyticalServlet
 */
@WebServlet("/uploadAnalytical")
public class UploadAnalyticalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadAnalyticalServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException
	{

		
		HttpSession session=request.getSession(false);
		Connection lConn   = null;
		try{
			if(session!=null){
				lConn=new DBConnection().getConnection();
				HashMap<String,String> casNumberMap=new ChemData().getCasNames(lConn, 3L);
				request.setAttribute("casnumber", casNumberMap);
				System.out.println("casNumberMap::"+casNumberMap.size());
				request.getServletContext().getRequestDispatcher("/WEB-INF/uploadAnalytical.jsp").forward(request, response);
			}
		
		else{
			request.setAttribute("error","Current session is lost. Please log in");
			request.getRequestDispatcher("/LoadDataForHome").include(request, response);  
			}
		}catch(Exception e){
			
		}
		finally{
			try{
				if(lConn!=null){
					lConn.close();
				}
			}catch(Exception e){
				
			}
		}
	      }
	}



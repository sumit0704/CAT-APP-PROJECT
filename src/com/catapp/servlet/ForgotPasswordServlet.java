package com.catapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.catapp.action.Login;
import com.catapp.connection.DBConnection;
import com.catapp.entity.User;  
@WebServlet(value="/ForgotPasswordServlet")
public class ForgotPasswordServlet extends HttpServlet {  
	private static final Logger LOGGER = Logger.getLogger(ForgotPasswordServlet.class);
        /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		protected void doGet(HttpServletRequest request, HttpServletResponse response)  
                                throws ServletException, IOException {  
			
			Connection lConn 				= null;
			PreparedStatement lPstmt        = null;
			ResultSet lRst					= null;
			try{

				lConn = new DBConnection().getConnection();
				
				System.out.println("It entered");
				String lPassword = request.getParameter("password");
				String ltemppassword = request.getParameter("temppassword");
				
				//String lParameter = request.getParameter("test_id");
				String lParameter=request.getQueryString().split("test_id=")[1].split("&pwd")[0];
				lParameter=lParameter.replaceAll("%3D", "=");
				lParameter=URLDecoder.decode(lParameter,"UTF-8");
				//request.getParameterNames()
				boolean lChange=false;
				Long lUserId=null;
				boolean lPasswordFlag=false;
				boolean lPassExpired=false;
				StringBuilder lQueryBuilder=new StringBuilder("select * From temporarypassword where encryptedtext=? and rowstate!=-1");
				lPstmt=lConn.prepareStatement(lQueryBuilder.toString());
				lPstmt.setString(1, lParameter);
				lRst=lPstmt.executeQuery();
				while(lRst.next()){
					System.out.println("It entered in first");
					if(lRst.getString("password").trim().equals(ltemppassword.trim())){
						lChange=true;
						lUserId=lRst.getLong("user_id");
					}else{
						lPasswordFlag=true;
					}
					if(lRst.getString("expired").equals("Y")){
						lPassExpired=true;
					}
					
				}
				if(lChange && !lPassExpired){
					System.out.println("It entered in Second");
					lPstmt.clearParameters();
					String lQuery = "update users set Password=? where entity_id=?";
					lPstmt=lConn.prepareStatement(lQuery);
					lPstmt.setString(1, Login.generateHash("PWD"+lPassword));
					lPstmt.setLong(2, lUserId);
					lPstmt.executeUpdate();
					lPstmt.clearParameters();
					
					request.getRequestDispatcher("/WEB-INF/index.jsp").include(request, response);
					String lInsertQuery="update temporarypassword set expired='Y' where encryptedtext=? ";
					lPstmt=lConn.prepareStatement(lInsertQuery);
					lPstmt.setString(1, lParameter);
					lPstmt.executeUpdate();
					PrintWriter out = response.getWriter();  
					response.setContentType("text/html");  
					out.println("<script type=\"text/javascript\">");  
					out.println("alert('Password Changed Successfully.');");  
					out.println("</script>");
					request.getRequestDispatcher("/WEB-INF/index.jsp").include(request, response);
					
				}else{
					if(lPasswordFlag){
						PrintWriter out = response.getWriter();  
						response.setContentType("text/html");  
						out.println("<script type=\"text/javascript\">");  
						out.println("alert('Temporary password is incorrect.');");  
						out.println("</script>");
						request.setAttribute("user_id", lParameter);
						request.getRequestDispatcher("/WEB-INF/changePassword.jsp").include(request, response);
						
					}else if(lPassExpired){
						PrintWriter out = response.getWriter();  
						response.setContentType("text/html");  
						out.println("<script type=\"text/javascript\">");  
						out.println("alert('The temporary password has expired.Please click on forgot password link again on the home page.');");  
						out.println("</script>");
						request.getRequestDispatcher("/WEB-INF/index.jsp").include(request, response);

					}
				}
			}catch(Exception e)
			{
				LOGGER.error("Exception occurred::",e);

				e.printStackTrace();
			}finally{
				try{
					if(lConn!=null){
						lConn.close();
					}
				}catch(Exception c){
					c.printStackTrace();

				}
			}
			
           
    }  
		/*@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(req, resp);
		}*/
		public static boolean pAuthenticateEmail(String pEmail,Connection pConn){
			
			PreparedStatement lPstmnt = null;
			ResultSet		  lRst	  = null;
			boolean lFlag			  =false;
			try{
				StringBuilder lBuilder = new StringBuilder("select entity_id from users where email=? and rowstate!=-1 and is_active='Y'");
				lPstmnt =pConn.prepareStatement(lBuilder.toString());
				lPstmnt.setString(1, pEmail);
				lRst=lPstmnt.executeQuery();
				while(lRst.next()){
					lFlag=true;
				}
			}catch (Exception e){
				LOGGER.error("Error Occured while authenticating email",e);
			}finally{
				try{
					if(lPstmnt!=null){
						lPstmnt.close();
					}
				}catch(Exception p){
					LOGGER.error("Error Occured while closing pstmnt",p);
				}
			}
			return lFlag;
		}
		public HashMap<String,String>  pAuthenticateAnswers(HashMap<Long,String> pAnswers,Long pUserId,Connection pConn){
			
			LOGGER.debug("Start of pAuthenticateAnswers::"+ pUserId);
			PreparedStatement lPstmnt = null;
			ResultSet		  lRst	  = null;
			
			HashMap<String,String>pMap = new HashMap<String,String>();
			try{
				StringBuilder lBuilder = new StringBuilder("select b.security_question,a.answer from security_questions_answers a join security_questions b");
										 lBuilder.append("  on a.question_id=b.entity_id where a.user_id=? and a.rowstate!=-1 and a.is_active='Y'");
				
				lPstmnt =pConn.prepareStatement(lBuilder.toString());
				lPstmnt.setLong(1, pUserId);
				lRst=lPstmnt.executeQuery();
				
				while(lRst.next()){
					pMap.put(lRst.getString(1), lRst.getString(2));
				}
				
			}catch (Exception e){
				LOGGER.error("Error Occured while authenticating email",e);
			}finally{
				try{
					if(lPstmnt!=null){
						lPstmnt.close();
					}
				}catch(Exception p){
					LOGGER.error("Error Occured while closing pstmnt",p);
				}
			}
		//	LOGGER.info("End of method pAuthenticateAnswers:: iFlag::"+lFlag);

			return pMap;
		}
}  
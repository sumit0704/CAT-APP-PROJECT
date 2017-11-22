package com.catapp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.catapp.action.SendEmail;
import com.catapp.connection.DBConnection;
import com.catapp.entity.User;  
@WebServlet(value="/RequestAccessServlet")
public class RequestAccessServlet extends HttpServlet {  
	private static final Logger LOGGER = Logger.getLogger(RequestAccessServlet.class);
	    /**
	 * 
	 */
	 static Cipher cipher;  
	private static final long serialVersionUID = 1L;

		/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;

		protected void doPost(HttpServletRequest request, HttpServletResponse response)  
	                    throws ServletException, IOException {  
			Connection lConn=null;
			
	        try{
	        	String forgotEmail=request.getParameter("forgotEmail").toString();   
	        	HttpSession session=request.getSession();  
	        	session.setAttribute("forgotEmail",forgotEmail);
	        	 lConn = new DBConnection().getConnection();
	        	/* String lQuest1=null;
	        	 String lQuest2=null;
	        	 String lQuest3=null;*/
	        	boolean lVailidityflag=ForgotPasswordServlet.pAuthenticateEmail(forgotEmail, lConn);
	        	if(lVailidityflag){
	        		User lUser =new LoginServlet().fetchUserDetails(forgotEmail, lConn);
	        		String uuid = UUID.randomUUID().toString();
	        		
	        		 KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
				     keyGenerator.init(128);
				     SecretKey secretKey = keyGenerator.generateKey();
				     cipher = Cipher.getInstance("AES");
				     String plainText = lUser.getEntityId().toString();
				     String encryptedText = encrypt(plainText, secretKey);
				     
	        		StringBuilder lInsertQuery = new StringBuilder( "insert into temporarypassword (user_id, password, expired, logged_date, rowstate,secretkey,encryptedtext)");
					lInsertQuery.append(" VALUES(?,?,?,?,?,?,?) ");
					PreparedStatement lPst			= null;
					lPst= lConn.prepareStatement(lInsertQuery.toString());
					lPst.setLong(1, lUser.getEntityId());
					lPst.setString(2, uuid);
					lPst.setString(3, "N");
					lPst.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
					lPst.setInt(5, 1);
					lPst.setString(6, secretKey.toString());
					lPst.setString(7,encryptedText);
					lPst.execute();
					
					

	        		String pBody=" Your temporary password for this transaction is " +uuid+ ". This password will expire in two days.\n Use the link below to reset the password using the temporary password.\n https://catappdata.com/ChangePassword?user_id="+encryptedText;
	        		SendEmail.sendEmail(lUser.getEmail(), "Change Password", pBody);
	        		request.getRequestDispatcher("/WEB-INF/ForPassPage.jsp?page=4").include(request, response);
	        	//	HashMap<Long,String>pSQMap = new LoadDataForHome().getSecurityQuestionsForUsers(lUser.getEntityId());
	        		
	        		
	        		
	        		/*request.setAttribute("secqu", pSQMap);
	        		request.getSession().setAttribute("user", lUser);
	        		if(pSQMap.isEmpty()){
	        			request.getRequestDispatcher("/WEB-INF/ForPassPage.jsp?page=3").include(request, response);

	        		}else{
	        			int i=0;
	        			for(Long lKey:pSQMap.keySet()){
	        				if(i==0){
	        					lQuest1=pSQMap.get(lKey);
	        				}else if(i==1){
	        					lQuest2=pSQMap.get(lKey);
	        				}else if(i==2){
	        					lQuest3=pSQMap.get(lKey);
	        				}
	        				i++;
	        			}
	        			request.setAttribute("qu1", lQuest1);
	        			request.setAttribute("qu2", lQuest2);
	        			request.setAttribute("qu3", lQuest3);
	        			request.getRequestDispatcher("/WEB-INF/ForPassPage.jsp?page=1").include(request, response);
	        		}*/
	        		
	        	}else{
	        		request.getRequestDispatcher("/WEB-INF/ForPassPage.jsp?page=5").include(request, response);
	        	}
	        	
	        }catch(Exception e){
	        	LOGGER.error("Error Occured while redirecting the request",e);
	        }finally{
	        	try{
	        		if(lConn!=null){
	        			lConn.close();
	        		}
	        	}catch(Exception e1){
	        		LOGGER.error("Error Occured while closing the connection",e1);
	        	}
	        }
	       
	        
	    }  
		
		 public static String encrypt(String plainText, SecretKey secretKey)
		            throws Exception {
		        byte[] plainTextByte = plainText.getBytes();
		        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		        byte[] encryptedByte = cipher.doFinal(plainTextByte);
		        Base64.Encoder encoder = Base64.getEncoder();
		        String encryptedText = encoder.encodeToString(encryptedByte);
		        return encryptedText;
		    }

		    public static String decrypt(String encryptedText, SecretKey secretKey)
		            throws Exception {
		        Base64.Decoder decoder = Base64.getDecoder();
		        byte[] encryptedTextByte = decoder.decode(encryptedText);
		        cipher.init(Cipher.DECRYPT_MODE, secretKey);
		        byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
		        String decryptedText = new String(decryptedByte);
		        return decryptedText;
		    }
	}  



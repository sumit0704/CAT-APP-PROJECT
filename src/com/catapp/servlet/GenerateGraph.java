package com.catapp.servlet;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import com.catapp.action.ChemData;
import com.catapp.action.shellCommands;

import sun.misc.BASE64Decoder;

/**
 * Servlet implementation class GenerateGraph
 */
@WebServlet("/GenerateGraph")
public class GenerateGraph extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	private static final String FILE_HEADER = "Chemical,CellLine,Phenotype,TimePoint";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenerateGraph() {
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
		try{
			String lFileName="C:\\Users\\CATAPP\\Logic\\Query Parameters.csv";
			File lFile = new File(lFileName);
			if(lFile.exists()){
				lFile.delete();
			}
			File lImageFile1 = new File("C:\\Users\\CATAPP\\Logic\\Figs\\generated_graph1.png");
			if(lImageFile1.exists()){
				lImageFile1.delete();
			}
			
			FileWriter fileWriter = null;
			 fileWriter = new FileWriter(lFileName);
			 fileWriter.append(FILE_HEADER.toString());
			 fileWriter.append(NEW_LINE_SEPARATOR);
			 fileWriter.append(lChemical);
			 fileWriter.append(COMMA_DELIMITER);
			 fileWriter.append(lCellLine);
			 fileWriter.append(COMMA_DELIMITER);
			 fileWriter.append(lPhenotype);
			 fileWriter.append(COMMA_DELIMITER);
			 fileWriter.append(new ChemData().getTimePoints().get(Long.parseLong(lTimePoint)));
			 fileWriter.flush();
			 fileWriter.close();
			 String R_command = "cmd.exe /c C:\\\"Program Files\"\\R\\R-3.3.3\\bin\\Rscript C:\\Users\\CATAPP\\Logic\\Script_Data.R";
				
				shellCommands obj = new shellCommands();
				obj.executeCommand(R_command);
				
				String lImageURL="C:\\Users\\CATAPP\\Logic\\Figs\\generated_graph1.png";
			//	response.setContentType("image/png");
				
				File lImageFile = new File(lImageURL);
				if(!lImageFile.exists()){
					lImageURL= "C:\\Users\\CATAPP\\Logic\\Figs\\no_data.png";
				}
					
				BufferedImage bImage = ImageIO.read(new File(lImageURL));//give the path of an image
			      ByteArrayOutputStream baos = new ByteArrayOutputStream();
			        // ImageIO.write( bImage, "jpg", baos );
			      ImageIO.write( bImage, "png", baos );
			      baos.flush();
			      byte[] imageInByteArray = baos.toByteArray();
			      byte[] imageByte;
			      String b64 = DatatypeConverter.printBase64Binary(imageInByteArray);
			      
			  
			      BASE64Decoder decoder = new BASE64Decoder();
			      imageByte = decoder.decodeBuffer(b64);
			      
			      
			      baos.close();
					response.setContentType("text/plain");
					response.getWriter().write(b64);
				
			
		}catch(Exception e){
			
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

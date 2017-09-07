package cat.response;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.catapp.action.shellCommands;
import com.catapp.connection.DBConnection;
import com.catapp.entity.User;

/**
 * Servlet implementation class column_3
 */
@WebServlet("/column_3")
public class column_3Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public column_3Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("column_3Servlet"); 
		

		String chemical_n = request.getParameter("chemical");	// receiving the post value
		request.setAttribute("chemical", chemical_n);			// submit vlue to following page:
			
		String endpoint_n = request.getParameter("endpoint");	// receiving the post value
		request.setAttribute("endpoint", endpoint_n);			// submit vlue to following page:
		
		String chemical_properties = request.getParameter("chemical_properties");	// receiving the post value
		request.setAttribute("chemical_properties", chemical_properties);			// submit vlue to following page:
		
		String endpoint_data = request.getParameter("endpoint_data");	// receiving the post value
		request.setAttribute("endpoint_data", endpoint_data);			// submit vlue to following page:
		
		
		
		System.out.println("column_3Servlet: " + chemical_properties); 
		
		
		
		String endpoint_string = "";
		switch(endpoint_n){
			case "1": endpoint_string = "Peak_freq_90min"; break;
			case "2": endpoint_string = "Peak_freq_24hr"; break;
			case "3": endpoint_string = "Cardio_Total_Cell_24h"; break;
			case "4": endpoint_string = "HUVEC_total_cell"; break;
			case "5": endpoint_string = "HUVEC_tube_area"; break;
			case "6": endpoint_string = "HUVEC_Mito_24h"; break;
			case "7": endpoint_string = "Peak_freq_90min_Fly_Fly"; break;
			
		}
		
	
		
		System.out.println("endpoint_string: " + endpoint_string); 
		
		String R_command = "cmd.exe /c C:\\\"Program Files\"\\R\\R-2.15.1\\bin\\Rscript C:\\Users\\ssingh\\Desktop\\4_R\\Demonstration\\";
		R_command +=  endpoint_string + "\\Script_Data.R";
		
		
		System.out.println(R_command); 
		
		shellCommands obj = new shellCommands();
		String output = obj.executeCommand(R_command);

		System.out.println(output);
		
		
		
		getServletContext().getRequestDispatcher("/WEB-INF/Response/Column_3.jsp").forward(request, response);
	}

}
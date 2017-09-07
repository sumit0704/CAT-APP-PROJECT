package cat.response;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class responseServlet
 */
@WebServlet("/responseServlet")
public class responseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public responseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("before jsp"); 
		
    	HttpSession session = request.getSession(false);
    	
    	
		if(session != null){
			System.out.println("session != null"); 
			getServletContext().getRequestDispatcher("/WEB-INF/Response/Response.jsp").forward(request, response);
			
		}else{
			getServletContext().getRequestDispatcher("/LogoutServlet").forward(request, response);
			}
		
	}

}
package lab2;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/ControllerServlet")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ControllerServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		try {
		String X = request.getParameter("X");
		String Y = request.getParameter("Y");
		String R = request.getParameter("R");
		if (X==null || Y==null ||R==null || X=="" || Y=="" || R=="" ) {
			RequestDispatcher dispatcher; 
		    dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response); 
		} else { 
			RequestDispatcher dispatcher; 
			dispatcher = request.getRequestDispatcher("/AreaCheckServlet");
			dispatcher.forward(request, response);
		}	
		}
		catch (NumberFormatException e) {
			RequestDispatcher dispatcher; 
		    dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response); 
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

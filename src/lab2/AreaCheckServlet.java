package lab2;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AreaCheckServlet
 */
@WebServlet("/AreaCheckServlet")
public class AreaCheckServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ServletConfig config;
	private LinkedList<Point> points = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AreaCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init (ServletConfig config) throws ServletException {
    	this.config = config;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		if ((points = (LinkedList<Point>)session.getAttribute("points")) == null) {
			points = new LinkedList<Point>();
			config.getServletContext().setAttribute("listpoint", points);
		}
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		response.getWriter().append("Served at: ").append(request.getContextPath());
		points.add(new Point(Double.parseDouble(request.getParameter("X")), Double.parseDouble(request.getParameter("Y")), Double.parseDouble(request.getParameter("R"))));
		checkArea(points.getLast());
		String title = "Results";
		String docType =
		"<!doctype html public \"-//w3c//dtd html 4.0 " +
		"transitional//en\">\n";
		out.println(docType +
				"<html>\n" +
				"<head><title>" + title + "</title>\n" +
				"<link rel=\"stylesheet\" href=\"style.css\" type=\"text/css\">" + 
				"</head>\n" +
				"<body bgcolor=\"#f0f0f0\">\n" +
				"<h1 align=\"center\">" + title + "</h1>\n" +
				"<table id=\"content\">\n" +
				"<tr><th>X</th><th>Y</th><th>R</th><th>Hit?</th></tr>\n");
		for (int i = 0; i<points.size(); i++) {
			out.println("<tr><td>" + points.get(i).getX() + "</td>" + "<td>" + points.get(i).getY() + "</td>" + "<td>" + points.get(i).getR() + "</td>" + "<td>" + points.get(i).getHit() + "</td></tr>\n");
		}
		out.println(
				"</table>\n" +
				"<form method = \"get\" action=\"\">\n" +
				"<input type=\"submit\" value=\"Back\">" +
				"</form>" +
				"</body></html>");
		session.setAttribute("points", points);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public boolean checkArea(Point checkPoint) {
		boolean hit = false;
		if (checkRound(checkPoint) || checkTriangle(checkPoint) || checkRectangle(checkPoint)) {
			hit = true;
			checkPoint.setHit(hit);
			
		} 
		
		return hit;
	}
	
	public boolean checkRound(Point p){
		 if ((p.X*p.X + p.Y*p.Y <= p.R*p.R) && (p.X <= 0) && (p.Y <= 0))
		        return true;
		    else
		    	return false;
	}
	
	public boolean checkRectangle(Point p){
		if ((Math.abs(p.X)<= p.R/2) && (p.Y <= p.R) && (p.X <= 0) && (p.Y >= 0))
	        return true;
	    else
	        return false;
	}
	
	public boolean checkTriangle(Point p){
		if (p.X >= 0 && p.Y >= 0) {
	        double r1 = (p.R - p.X) * ((p.R / 2) - 0) - (0 - p.R) * (0 - p.Y);
	        double r2 = (0 - p.X) * (0 - (p.R / 2)) - (0 - 0) * (p.R / 2 - p.Y);
	        double r3 = (0 - p.X) * (0 - 0) - (p.R - 0) * (0 - p.Y);
	        if ((r1 > 0 && r2 > 0 && r3 > 0) || (r1 < 0 && r2 < 0 && r3 < 0) || r1 == 0 || r2 == 0 || r3 == 0)
	            return true;
	        else
	            return false;
	    } else
	        return false;
	}

}

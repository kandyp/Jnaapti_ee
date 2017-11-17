package pack1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Add
 */
@WebServlet("/Add")
public class Add extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Add() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("GET Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String add = request.getParameter("addname");
		new Init();
		Statement s = null;
		Connection conn = Connect.getConnection();
		try {
			s = conn.createStatement();
			s.execute("use " + Connect.dbName);
			String query = "INSERT INTO LIST (NAME) VALUES(?)";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
		    preparedStmt.setString (1, add);
		    preparedStmt.execute();
			
			
			s.close();
		}catch(Exception e) {
			e.printStackTrace();}
		
		/*RequestDispatcher rd=request.getRequestDispatcher("/Serv1");  			  
		rd.forward(request, response);
	*/
		response.sendRedirect(request.getContextPath());
	}

}

package pack1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import pack1.Connect;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.*;

/**
 * Servlet implementation class Serv1
 */
@WebServlet("/Serv1")
public class Serv1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Serv1() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		new Init();
		ResultSet rs = null;

		Connection conn = Connect.getConnection();
		try {
			Statement s = conn.createStatement();
			s.execute("use " + Connect.dbName);
			rs = s.executeQuery("SELECT * FROM LIST ORDER BY mt DESC");

			request.setCharacterEncoding("utf8");
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			response.setContentType("text/html"); 
			
			JSONArray ja = new JSONArray();
			while(rs.next()) {
			
			JSONObject jo = new JSONObject();
			jo.put("id", rs.getInt("ID"));
			jo.put("name", rs.getString("NAME"));
			SimpleDateFormat sdf = new SimpleDateFormat("E HH:mm:ss dd MMM yyyy");
			jo.put("ct", sdf.format(rs.getTimestamp("CT")));
			jo.put("mt", sdf.format(rs.getTimestamp("MT")));
			ja.add(jo);
			
			}
			
			out.print("<html>\r\n" + 
					"<head>\r\n" + 
					"<title>Jnaapti Web App Building in EE</title>"
					+ "<script>"
					+ "var data = "
					+  ja.toJSONString()
					+ ";"
					+ "</script>");
			  
	        
			
			s.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher rd=request.getRequestDispatcher("/home.html");  
        rd.include(request, response);  
                      
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		doGet(request, response);
	}

}

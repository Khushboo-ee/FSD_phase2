package p1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet ("/subT")
public class sub_teachers extends HttpServlet{
	private String url = "jdbc:mysql://localhost:3305/p2_project1";
	private String user = "root";
	private String pwd = "root";
	Connection con;
	PreparedStatement pstmt;
	
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{

		resp.setContentType("text/html");

		PrintWriter writer = resp.getWriter();

		String s1 = req.getParameter("cls");
			int Cls = Integer.parseInt(s1);
		String Section = req.getParameter("sec");
		String Sub = req.getParameter("sub");
		String TeacherName = req.getParameter("tname");
		String s2 = req.getParameter("tid");
			int TeacherID = Integer.parseInt(s1);
		
		int x;

		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("hi1");
			con = DriverManager.getConnection(url, user, pwd);
			System.out.println(con);
			String sql = "insert into sub_teachers values (?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, Cls);
			pstmt.setString(2, Section);
			pstmt.setString(3, Sub);
			pstmt.setString(4, TeacherName);
			pstmt.setInt(5, TeacherID);
		

			x = pstmt.executeUpdate();

			if (x > 0)
			{
				writer.println("<h3>Data stored successfully.</h3>");
				HttpSession session = req.getSession(true);
				session.setAttribute("cls", Cls);

				req.getRequestDispatcher("sub_teachers.html").forward(req,resp);
				
			} 
			else
			{
				writer.println("<h3>Data storage failed.</h3>");
				writer.println("<a href='/LA_attempt04/sub_teachers.html'>Back</a>");
			}



			pstmt.close();
			con.close();
		}

		catch (SQLException e)
		{

			e.printStackTrace();
		}

		catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

package p1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet ("/rpt")
public class Report extends HttpServlet{
	private String url = "jdbc:mysql://localhost:3305/p2_project1";
	private String user = "root";
	private String pwd = "root";
	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{

		resp.setContentType("text/html");

		PrintWriter out = resp.getWriter();

		String s1 = req.getParameter("cls");
			int Cls = Integer.parseInt(s1);
		String Section = req.getParameter("sec");
		
		int x;

		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("hi1");
			con = DriverManager.getConnection(url, user, pwd);
			System.out.println(con);
			
			String sql = "select * from reports where class=? and section=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, Cls);
			pstmt.setString(2, Section);
			
			 out.print ("<table width=50% border=1>");
	            out.print ("<caption>Student Report:</caption>");
			
			ResultSet res = pstmt.executeQuery(sql);
			
			ResultSetMetaData rsmd = res.getMetaData();

			int total = rsmd.getColumnCount ();
			
            out.print ("<tr>");
            for (int i = 1; i <= total; i++)
         {
             out.print ("<th>" + rsmd.getColumnName (i) + "</th>");
         }
            out.print ("</tr>");
            /* Printing result */
            while (res.next ())
         {
             out.print (" <tr><td>" + res.getInt (1) + "</td><td>"+ res.getString (2) + "</td><td>" +  res.getString (3) + "</td><td> " +res.getString (4)+"</td><td>" +res.getInt (3) + "</td></tr>");
         }
            out.print ("</table>");
        }
        catch (Exception e2)
        {
            e2.printStackTrace ();
        }
        finally
        {
            out.close ();
        }
    }
}


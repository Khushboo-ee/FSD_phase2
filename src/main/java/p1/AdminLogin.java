package p1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet ("/login")
public class AdminLogin extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String s1 = req.getParameter("password");
		String s2 = req.getParameter("username");
		String s3 = req.getParameter("name");
		//PrintWriter writer = resp.getWriter();
		/*System.out.println("Password: "+s1);
		System.out.println("username: "+s2);
		System.out.println("Name: "+s3);*/
		
		if(s1!=null&&s1.equalsIgnoreCase("pwd12345")&&s2!=null&&s2.equalsIgnoreCase("admin")) {
			
			HttpSession session = req.getSession(true);
			session.setAttribute("password", s2);

			req.getRequestDispatcher("adminPage1.html").forward(req,resp);
			
		}
		else {
			
			req.getRequestDispatcher("loginFail.html").forward(req,resp);
		}

	/*	HttpSession session = req.getSession(true);

		ModelLogin ml = new ModelLogin();
		boolean b = ml.verify();
		System.out.println(b);

		if (b == true) {
			session.setAttribute("password", ml.getPassword());
			session.setAttribute("username", ml.getUsername());
			// writer.print("");
			resp.sendRedirect("/LA_attemp04/home.html");

		} else {
			resp.sendRedirect("/LA_attempt04/loginFail.html");
		}*/

	}

}

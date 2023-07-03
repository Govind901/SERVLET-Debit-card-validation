package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.google.gson.Gson;

import Connections.connectionn;
import Data.Otpvalidationn;
import Pojo.OtpvalPojo;

/**
 * Servlet implementation class Otpval
 */
public class Otpval extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Gson gson=new Gson();
       Otpvalidationn obje=new Otpvalidationn();
       JSONObject ob1=new JSONObject();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Otpval() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext context=request.getServletContext();
		connectionn obj=(connectionn)context.getAttribute("dbcon");
		try {
			Connection conn=obj.getConnection();
			OtpvalPojo object=gson.fromJson(request.getReader(), OtpvalPojo.class);
			PrintWriter out=response.getWriter();
			String str=obje.otpval(object, conn);
			response.setContentType("application/JSON");
			ob1.put("message",str);
			out.println(ob1);
		} catch (SQLException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

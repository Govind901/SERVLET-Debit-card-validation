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
import com.google.gson.JsonObject;

import Connections.connectionn;
import Data.CardValidationn;
import Pojo.CardvalidationnPojo;

/**
 * Servlet implementation class CardVal
 */
public class CardVal extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Gson gson=new Gson();
	CardValidationn obje=new CardValidationn();
	JSONObject JSONObject=new JSONObject();
    /**
     * Default constructor. 
     */
    public CardVal() {
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
		ServletContext sc=request.getServletContext();
		connectionn obj=(connectionn)sc.getAttribute("dbcon");
		try {
			Connection conn=obj.getConnection();
			CardvalidationnPojo object=gson.fromJson(request.getReader(), CardvalidationnPojo.class);
			PrintWriter out=response.getWriter();
			String str=obje.validation(object, conn); 
			response.setContentType("application/JSON");		
			JSONObject.put("message",str);
			out.println(JSONObject);
		} catch (SQLException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

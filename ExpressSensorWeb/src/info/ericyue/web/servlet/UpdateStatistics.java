package info.ericyue.web.servlet;

import info.ericyue.web.util.DBUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateStatistics
 */
@WebServlet("/UpdateStatistics")
public class UpdateStatistics extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateStatistics() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out= response.getWriter();
		String id=request.getParameter("id");
		String item=request.getParameter("item");
		String mode=request.getParameter("mode");
		String update=new String();
		if(mode.equals("up")){
			update="UPDATE statistics SET "+item+" = "+item+"+1 where id="+id;
		}
		else{
			update="UPDATE statistics SET "+item+" = "+item+"-1 where id="+id;
		}
		DBUtil util=new DBUtil();
		Connection conn=util.openConnection();
		try{
			PreparedStatement pstmt = conn.prepareStatement(update);
			int result=pstmt.executeUpdate();
			if(result>0){
				out.print("1");
			}
			else 
				out.print("0");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
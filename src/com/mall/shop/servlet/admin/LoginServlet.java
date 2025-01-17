package com.mall.shop.servlet.admin;

import com.mall.shop.bean.Admin;
import com.mall.shop.dao.AdminDao;
import com.mall.shop.dao.impl.AdminDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/jsp/admin/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mainPath="index.jsp";
		String loginPath="login.jsp";

		String username=request.getParameter("username");
		String password=request.getParameter("password");
		Admin admin=new Admin(username, password);
		AdminDao ud=new AdminDaoImpl();

		List<String> list=new ArrayList<String>();
		if(username==null) {
			list.add("username can not be null");
		}
		if(password==null) {
			list.add("password can not be null");
		}
		if(list.size()==0) {
			Admin admin1 = ud.userLogin(admin);
			if(admin1!=null) {
				request.getSession().setAttribute("adminUser",admin1 );
				request.getSession().setAttribute("adminId",admin1.getId());
				response.sendRedirect(mainPath);
				return;
			}else {
				list.add("error username or password !please input again");
			}
		}
		request.setAttribute("infoList", list);
		request.getRequestDispatcher(loginPath).forward(request, response);


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

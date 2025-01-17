package com.mall.shop.servlet.admin;

import com.mall.shop.bean.Admin;
import com.mall.shop.bean.PageBean;
import com.mall.shop.dao.AdminDao;
import com.mall.shop.dao.impl.AdminDaoImpl;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/jsp/admin/AdminManageServlet")
public class AdminManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ADMINLIST_PATH="adminManage/adminList.jsp";//用户列表页面Address
	private static final String ADMINADD_PATH="adminManage/adminAdd.jsp";//Add User页面Address
	private static final String ADMINEDIT_PATH="adminManage/adminEdit.jsp";//用户Edit页面Address

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		switch(action){
		case "list":
			adminList(request,response);
			break;
		case "add":
			adminAdd(request,response);
			break;
		case "update":
			adminUpdate(request,response);
			break;
		case "edit":
			adminEdit(request,response);
			break;
		case "del":
			adminDel(request,response);
			break;
		case "batDel":
			adminBatDel(request,response);
			break;
		case "find":
			adminFind(request,response);
			break;
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	//查询用户列表
	private void adminList(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		AdminDao ad=new AdminDaoImpl();
		String page=request.getParameter("page");
		int curPage=1;
		if(page!=null){
			curPage=Integer.parseInt(page);
		}
		//获取xml中设置的每页显示大小参数
		//int maxSize=Integer.parseInt(request.getServletContext().getInitParameter("maxPageSize"));
		int maxSize=10;

		PageBean pageBean=new PageBean(curPage,maxSize,ad.readCount());
		List<Admin> adminList=ad.userList(pageBean);

		request.setAttribute("adminList", ad.userList(pageBean));
		request.setAttribute("pageBean", pageBean);

		request.getRequestDispatcher(AdminManageServlet.ADMINLIST_PATH).forward(request, response);

	}

	//增加用户
	private void adminAdd(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		AdminDao ad=new AdminDaoImpl();
		Admin admin=new Admin(request.getParameter("username"),request.getParameter("password"),
				request.getParameter("name"));
		//Add之前判断username是否在库中存在
		if(new AdminDaoImpl().findUser(admin.getUsername())){
			request.setAttribute("adminMessage", "用户Add失败！username已存在");
			request.getRequestDispatcher(AdminManageServlet.ADMINADD_PATH).forward(request, response);
		}else{
			//执行dao层AddOperate
			if(ad.userAdd(admin)){
				request.setAttribute("adminMessage", "用户Add成功！");
				adminList(request, response);//通过servlet中listUser跳到用户列表
			}else{
				request.setAttribute("adminMessage", "用户Add失败！");
				request.getRequestDispatcher(AdminManageServlet.ADMINADD_PATH).forward(request, response);
			}
		}

	}
	//Update用户信息
	private void adminUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Admin admin=new Admin(Integer.parseInt(request.getParameter("id")),
				request.getParameter("password"),
				request.getParameter("name")
				);
		AdminDao ad=new AdminDaoImpl();
		if(ad.userUpdate(admin)) {
			request.setAttribute("adminMessage", "用户Update成功");
			adminList(request, response);//通过servlet中listUser跳到用户列表
		}else {
			//Update失败ToEdit页面
			request.setAttribute("adminMessage", "用户Update失败");
			request.setAttribute("adminInfo", ad.findUser(Integer.valueOf(admin.getId())));//这里回去是Admin对象
			request.getRequestDispatcher(AdminManageServlet.ADMINEDIT_PATH).forward(request, response);
		}

	}

	//Edit用户
	private void adminEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		AdminDao ad=new AdminDaoImpl();
		request.setAttribute("adminInfo",ad.findUser(Integer.valueOf(id)));//这里回去是Admin对象
		request.getRequestDispatcher(AdminManageServlet.ADMINEDIT_PATH).forward(request, response);
	}

	//Delete用户
	private void adminDel(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int id=Integer.parseInt(request.getParameter("id"));
		AdminDao ad=new AdminDaoImpl();
		if(ad.delUser(id)) {
			request.setAttribute("adminMessage", "用户已Delete");
		}else {
			request.setAttribute("adminMessage", "用户Delete失败");
		}
		//用户Delete成功失败都To用户列表页面
		adminList(request, response);//通过servlet中listUser跳到用户列表
	}

	//Batch Delete
	private void adminBatDel(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String ids=request.getParameter("ids");
		AdminDao ad=new AdminDaoImpl();

		if(ad.batDelUser(ids)) {
			request.setAttribute("adminMessage", "用户已Batch Delete");
		}else {
			request.setAttribute("adminMessage", "用户Batch Delete失败");
		}
		//用户Delete成功失败都To用户列表页面
		adminList(request, response);//通过servlet中listUser跳到用户列表

	}

	//ajax判断username(param为接收的username）
	private void adminFind(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username=request.getParameter("param");

		AdminDao ad=new AdminDaoImpl();
		JSONObject json=new JSONObject();
		if(ad.findUser(username)){
			json.put("info", "username已存在");
			json.put("status", "n");
		}else{
			json.put("info", "username可以使用");
			json.put("status", "y");
		}
		response.getWriter().write(json.toString());
	}

}

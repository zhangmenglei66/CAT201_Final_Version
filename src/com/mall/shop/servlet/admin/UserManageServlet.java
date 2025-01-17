package com.mall.shop.servlet.admin;

import com.mall.shop.bean.PageBean;
import com.mall.shop.bean.User;
import com.mall.shop.dao.impl.UserDaoImpl;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/jsp/admin/UserManageServlet")
public class UserManageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "list":
                userList(request, response);
                break;
            case "detail":
                userDetail(request, response);
                break;
            case "edit":
                userEdit(request, response);
                break;
            case "del":
                userDel(request, response);
                break;
            case "add":
                userAdd(request, response);
                break;
            case "update":
                userUpdate(request, response);
                break;
            case "find":
                userFind(request, response);
                break;
            case "batDel":
                userBatDel(request, response);
                break;

        }
    }

    //自定义Batch Delete用户的方法
    private void userBatDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取前端页面要Batch Delete的User ID
        String ids = request.getParameter("ids");
        UserDaoImpl ud = new UserDaoImpl();
        if (ud.batDelUser(ids)) {
            //提示Batch Delete用户成功
            request.setAttribute("userMessage", "Batch Delete用户成功!");
            userList(request, response);
        } else {
            //提示Batch Delete用户不成功
            request.setAttribute("userMessage", "Batch Delete用户不成功!");
            userList(request, response);
        }
    }

    //自定义查找用户是否重名的方法
    private void userFind(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取前端页面用户输入的username
        String userName = request.getParameter("param");
        UserDaoImpl ud = new UserDaoImpl();
        JSONObject json = new JSONObject();
        if (ud.findUser(userName)) {
            //查找到了重名的用户，username已存在
            json.put("info", "username可以使用");
            json.put("status", "n");
        } else {
            //没有查找到该username，可以使用该username
            json.put("info", "username可以使用");
            json.put("status", "y");
        }
        PrintWriter writer = response.getWriter();
        response.setContentType("text/html;charset=utf-8");
        writer.print(json.toString());
        writer.flush();
    }

    //自定义用户Update的方法
    private void userUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取前端页面input标签中的内容，构造新的User对象，Update数据库表中的用户信息
        User user = new User(
                Integer.parseInt(request.getParameter("id")),
                request.getParameter("username"),
                request.getParameter("password"),
                Double.parseDouble(request.getParameter("balance")),
                request.getParameter("sex"),
                request.getParameter("phone"),
                request.getParameter("email"),
                request.getParameter("name")
        );
        //往数据库中Update该用户信息
        UserDaoImpl ud = new UserDaoImpl();
        if (ud.userUpdate(user)) {
            //提示用户Update成功
            request.setAttribute("userMessage", "Successfully update user！");
            //再次返回userList页面
            userList(request, response);
        } else {
            //提示用户Update失败
            request.setAttribute("userMessage", "Update用户失败！");
            request.getRequestDispatcher("/jsp/admin/userManage/userEdit.jsp").forward(request, response);
        }
    }

    //自定义Add用户的方法
    private void userAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User(
                request.getParameter("username"),
                request.getParameter("password"),
                request.getParameter("sex"),
                request.getParameter("phone"),
                request.getParameter("email"),
                request.getParameter("name")
        );
        UserDaoImpl ud = new UserDaoImpl();
        if (ud.userAdd(user)) {
            //提示Add用户成功
            request.setAttribute("userMessage", "Add用户成功");
            userList(request, response);
        } else {
            //提示Add用户失败
            request.setAttribute("userMessage", "Add用户失败，请重新Add");
            request.getRequestDispatcher("/jsp/admin/userManage/userAdd.jsp");
        }
    }

    //自定义Delete用户的方法
    private void userDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Delete就是根据前端页面传入的User ID,调用UserDaoImpl类中的输出用户的方法
        //获取User ID
        int id = Integer.parseInt(request.getParameter("id"));
        UserDaoImpl ud = new UserDaoImpl();
        if (ud.delUser(id)) {
            //提示Delete成功
            request.setAttribute("userMessage", "Delete用户成功!");
            userList(request, response);
        } else {
            //提示Delete失败
            request.setAttribute("userMessage", "Delete用户失败！");
            userList(request, response);
        }
    }

    //自定义Edit用户的方法
    private void userEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从请求参数中获取User ID
        int userId = Integer.parseInt(request.getParameter("id"));
        UserDaoImpl ud = new UserDaoImpl();
        User user = ud.findUser(userId);
        request.setAttribute("userInfo", user);
        request.getRequestDispatcher("/jsp/admin/userManage/userEdit.jsp").forward(request, response);
    }

    //自定义显示详情的方法
    private void userDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("id"));
        UserDaoImpl ud = new UserDaoImpl();
        User user = ud.findUser(userId);
        request.setAttribute("userInfo", user);
        //ToProduct details page面
        request.getRequestDispatcher("/jsp/admin/userManage/userDetail.jsp").forward(request, response);

    }

    //自定义显示用户列表的方法
    private void userList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDaoImpl ud = new UserDaoImpl();
        long userCount = ud.userReadCount();
        String page = request.getParameter("page");
        int curPage = 1;
        if (page != null) {
            curPage = Integer.parseInt(page);
        }

        int maxSize = 10;
        PageBean pageBean = new PageBean(curPage, maxSize, userCount);
        List<User> userList = ud.userList(pageBean);

        request.setAttribute("userList", userList);
        request.setAttribute("pageBean", pageBean);
        request.getRequestDispatcher("/jsp/admin/userManage/userList.jsp").forward(request, response);
    }
}

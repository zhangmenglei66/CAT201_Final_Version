package com.mall.shop.servlet.shop;

import com.mall.shop.bean.PageBean;
import com.mall.shop.bean.User;
import com.mall.shop.dao.UserDao;
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
import java.util.Locale;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "login":
                login(request, response);
                break;
            case "Logout":
                Logout(request, response);
                break;
            case "regeiste":
                regeiste(request, response);
                break;
            case "usernamecheck":
                usernamecheck(request, response);
                break;
            case "userselfchange":
                userselfchange(request, response);
                break;


        }
    }


    private void userselfchange(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从请求参数中获取User ID
        int userId = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String nickname = request.getParameter("nickname");
        String user_Password = request.getParameter("user_Password");
        String user_Sex = request.getParameter("user_Sex");
        String user_Email = request.getParameter("user_Email");
        String user_Mobile = request.getParameter("user_Mobile");
        UserDaoImpl ud = new UserDaoImpl();
        User user = ud.findUser(userId);
        user.setUsername(username);
        user.setPassword(user_Password);
        user.setEmail(user_Email);
        user.setPhone(user_Mobile);
        user.setNickname(nickname);
        user.setSex(user_Sex);
        ud.userUpdate(user);
        response.sendRedirect("/jsp/IndexServlet?action=changedata");
    }

    //自定义显示详情的方法
    private void Logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("user");
        response.sendRedirect("/jsp/IndexServlet?action=base");
    }

    private void usernamecheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user_name = request.getParameter("user_name");
        UserDaoImpl ud = new UserDaoImpl();
        int count = ud.usernamecheck(user_name);
        if(count>0){
            response.getWriter().println(1);
        }else{
            response.getWriter().println(0);
        }
    }

    private void regeiste(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String user_Password = request.getParameter("user_Password");
        String user_rePassword = request.getParameter("user_rePassword");
        String nickname = request.getParameter("nickname");
        String user_Email = request.getParameter("user_Email");
        String user_Sex = request.getParameter("user_Sex");
        String user_Mobile = request.getParameter("user_Mobile");

        if("".equals(username)){
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().print("<script type='text/javascript'>alert('username can not be null');window.location.href='/jsp/regesite.jsp'</script>");
            return;
        }
        if("".equals(user_Password)){
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().print("<script type='text/javascript'>alert('password can not be null');window.location.href='/jsp/regesite.jsp'</script>");
            return;
        }
        if(!(user_rePassword.equals(user_Password))){
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().print("<script type='text/javascript'>alert('两次输入password不相同');window.location.href='/jsp/regesite.jsp'</script>");
            return;
        }

        User user = new User();
        user.setSex(user_Sex);
        user.setNickname(nickname);
        user.setUsername(username);
        user.setPhone(user_Mobile);
        user.setPassword(user_Password);
        user.setEmail(user_Email);
        UserDaoImpl ud = new UserDaoImpl();
        int count = ud.usernamecheck(username);
        if(count>0){
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().print("<script type='text/javascript'>alert('该username已被使用，不能重复Register');window.location.href='/jsp/regesite.jsp'</script>");
            return;
        }else{
            ud.userAdd(user);
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().print("<script type='text/javascript'>alert('Register成功');window.location.href='/jsp/login.jsp'</script>");
//            response.sendRedirect("/jsp/login.jsp");
        }
    }

    //自定义显示用户列表的方法
    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDaoImpl();
        Object verifyCode = request.getSession().getAttribute("verify_code");
        String username = request.getParameter("username");
        String user_Password = request.getParameter("user_Password");
        String verifyCodes = request.getParameter("verifyCodes");
        User user1 = new User();
        user1.setUsername(username);
        user1.setPassword(user_Password);
        if (verifyCode.toString().toUpperCase().equals(verifyCodes.toUpperCase()) || verifyCode.toString().toLowerCase(Locale.ROOT).equals(verifyCodes.toLowerCase(Locale.ROOT))) {
            int count = userDao.usernamecheck(username);
            if (count > 0) {
                User user = userDao.userLogin(user1);
                if (user == null) {
                    request.setAttribute("errMsg", "password error");
                    request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
                } else {
                    request.setAttribute("user", user);
                    request.getSession().setAttribute("user", user);
                    response.sendRedirect("/jsp/IndexServlet?action=base");

                }
            } else {
                request.setAttribute("errMsg", "username not exist");
                request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("errMsg", "Captcha error");
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        }
    }
}

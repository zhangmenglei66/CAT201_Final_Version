package com.mall.shop.filter;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
@WebFilter(filterName="LoginFilter",urlPatterns= {"/*"})
public class LoginFilter implements Filter{
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        HttpSession session = request.getSession();

        //判断访问资源路径是否和LoginRegister相关
        // 1,在数组中存储登陆和Register相关的资源路径
        String[] urls = {"images/","photo/","css/","js/","bs/","img/","plugins/",
        "jsp/admin/login.jsp","jsp/login.jsp","jsp/regesite.jsp","UserServlet?action=reg","UserServlet?action=login","jsp/admin/LoginServlet",
        "LoginOutServlet","usernamecheck","UserServlet?action=ajlogin","UserServlet?action=off","footer.jsp","header.jsp","jsp/IndexServlet"};
        //2,获取当前访问的资源路径
        String url = ((HttpServletRequest) req).getRequestURL().toString();
        String queryString = ((HttpServletRequest) req).getQueryString();
        String urlStr = url+"?"+queryString;
        //3,遍历数组，获取到每一个需要放行的资源路径
        boolean flag = false;
        for (String u : urls) {
            //4,判断当前访问的资源路径字符串是否包含要放行的的资源路径字符串
            if(urlStr.contains(u)){
                flag=true;
                break;
            }
        }
        if(url.equals("http://localhost:8080")){
            flag=true;
        }

        Object admin = session.getAttribute("adminUser");
        Object user = session.getAttribute("user");

        if(admin != null || user != null || flag){

            chain.doFilter(request,response);

        } else{
            PrintWriter out = response.getWriter();
            response.setContentType("text/html;charset=utf-8");


            out.print("<script language='JavaScript'>alert('请先Login！');window.location.href='/jsp/admin/login.jsp?type=login'</script>");
        }
    }
    public void init(FilterConfig config) throws ServletException {
    }
}

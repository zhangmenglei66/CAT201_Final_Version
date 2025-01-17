package com.mall.shop.servlet.admin;

import com.mall.shop.bean.PageBean;
import com.mall.shop.bean.Orders;

import com.mall.shop.bean.Product;
import com.mall.shop.bean.User;
import com.mall.shop.dao.OrdersDao;

import com.mall.shop.dao.ProductDao;
import com.mall.shop.dao.UserDao;
import com.mall.shop.dao.impl.OrdersDaoImpl;
import com.mall.shop.dao.impl.ProductDaoImpl;
import com.mall.shop.dao.impl.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * 订单servlet
 * Servlet implementation class ordersServlet
 */
@WebServlet("/jsp/admin/OrdersManageServlet")
public class OrdersManageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String CATALOGLIST_PATH = "ordersManage/ordersList.jsp";
    private static final String CATALOGADD_PATH = "ordersManage/ordersAdd.jsp";


    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "list":
                ordersList(request, response);
                break;
            case "add":
                try {
                    ordersAdd(request, response);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "del":
                ordersDel(request, response);
                break;
            case "batDel":
                ordersBatDel(request, response);
                break;
            case "edit":
                ordersEdit(request, response);
                break;
            case "update":
                try {
                    ordersUpdate(request, response);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }


    private void ordersBatDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ids = request.getParameter("ids");
        OrdersDao ordersDao = new OrdersDaoImpl();

        if (ordersDao.ordersBatDelById(ids)) {
            request.setAttribute("ordersMessage", "订单已Batch Delete");

        } else {
            request.setAttribute("ordersMessage", "订单Delete失败");
        }
        //订单Delete成功失败都ToOrder List页面
        ordersList(request, response);//通过servlet中listOrders跳到Order List

    }

    private void ordersDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int ordersId = Integer.parseInt(request.getParameter("id"));

        OrdersDao ordersDao = new OrdersDaoImpl();

        if (ordersDao.ordersDel(ordersId)) {
            request.setAttribute("ordersMessage", "该订单已Delete");
        } else {
            request.setAttribute("ordersMessage", "该订单Delete失败");
        }


        ordersList(request, response);
    }

    private void ordersAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Orders orders = new Orders(
                request.getParameter("orderNo"),
                Integer.parseInt(request.getParameter("productId")),
                Integer.parseInt(request.getParameter("userId")),
                Integer.parseInt(request.getParameter("num")),
                Double.parseDouble(request.getParameter("price")),
                new Date(sdf.parse(request.getParameter("createTime")).getTime()),
                Integer.parseInt(request.getParameter("status")),
                request.getParameter("address"),
                request.getParameter("phone"),
                request.getParameter("realname")
        );


        OrdersDao ordersDao = new OrdersDaoImpl();
        if (ordersDao.ordersAdd(orders)) {
            request.setAttribute("ordersMessage", "增加订单成功");
            ordersList(request, response);
        } else {
            request.setAttribute("ordersMessage", "增加订单失败");
            request.getRequestDispatcher(CATALOGADD_PATH).forward(request, response);
        }

    }

    //获取Order List
    private void ordersList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int curPage = 1;
        String page = request.getParameter("page");
        if (page != null) {
            curPage = Integer.parseInt(page);
        }
        int maxSize = 10;
        OrdersDao ordersDao = new OrdersDaoImpl();

        PageBean pb = new PageBean(curPage, maxSize, ordersDao.ordersReadCount());
        ProductDao productDao = new ProductDaoImpl();
        request.setAttribute("pageBean", pb);
        List<Orders> ordersList = ordersDao.ordersList(pb);
        for (int i = 0; i < ordersList.size(); i++) {
            int productid = ordersList.get(i).getProductId();
            Product product = productDao.findProductById(productid);
            ordersList.get(i).setProduct(product);

        }
        request.setAttribute("ordersList", ordersList);
        request.getRequestDispatcher(CATALOGLIST_PATH).forward(request, response);
    }


    private void ordersEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从请求参数中获取订单id
        int id = Integer.parseInt(request.getParameter("id"));
        OrdersDao ordersDao = new OrdersDaoImpl();
        Orders orders = ordersDao.findOrdersById(id);
        ProductDao productDao = new ProductDaoImpl();
        Product product = productDao.findProductById(orders.getProductId());
        orders.setProduct(product);
        UserDao userDao = new UserDaoImpl();
        User user = userDao.findUser(orders.getUserId());
        request.setAttribute("ordersInfo", orders);
        request.setAttribute("userInfo", user);
        request.getRequestDispatcher("/jsp/admin/ordersManage/ordersEdit.jsp").forward(request, response);
    }



    private void ordersUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        //获取前端页面input标签中的内容，构造新的Orders对象，Update数据库表中的Orders information
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Orders orders = new Orders(
                Integer.parseInt(request.getParameter("id")),
                request.getParameter("orderNo"),
                Integer.parseInt(request.getParameter("productId")),
                Integer.parseInt(request.getParameter("userId")),
                Integer.parseInt(request.getParameter("num")),
                Double.parseDouble(request.getParameter("price")),
                new Timestamp(sdf.parse(request.getParameter("createTime")).getTime()),
                Integer.parseInt(request.getParameter("status")),
                request.getParameter("address"),
                request.getParameter("phone"),
                request.getParameter("realname")
        );
        //往数据库中Update该Orders information
        OrdersDaoImpl ordersDao = new OrdersDaoImpl();
        if (ordersDao.ordersUpdate(orders)) {
            //提示订单Update成功
            request.setAttribute("ordersMessage", "Update订单成功！");
            //再次返回ordersList页面
            ordersList(request, response);
        } else {
            //提示订单Update失败
            request.setAttribute("ordersMessage", "Update订单失败！");
            request.getRequestDispatcher("/jsp/admin/ordersManage/ordersEdit.jsp").forward(request, response);
        }
    }
}



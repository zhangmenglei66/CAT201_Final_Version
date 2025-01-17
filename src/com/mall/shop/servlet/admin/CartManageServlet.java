package com.mall.shop.servlet.admin;

import com.mall.shop.bean.PageBean;
import com.mall.shop.bean.Cart;

import com.mall.shop.dao.CartDao;

import com.mall.shop.dao.impl.CartDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * servlet
 * Servlet implementation class cartServlet
 */
@WebServlet("/jsp/admin/CartManageServlet")
public class CartManageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String CATALOGLIST_PATH = "cartManage/cartList.jsp";
    private static final String CATALOGADD_PATH = "cartManage/cartAdd.jsp";


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
                cartList(request, response);
                break;
            case "add":
                cartAdd(request, response);
                break;
            case "del":
                cartDel(request, response);
                break;
            case "batDel":
                cartBatDel(request, response);
                break;

            case "edit":
                cartEdit(request, response);
                break;
            case "update":
                cartUpdate(request, response);
                break;
        }
    }


    private void cartBatDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ids = request.getParameter("ids");
        CartDao cartDao = new CartDaoImpl();

        if (cartDao.cartBatDelById(ids)) {
            request.setAttribute("cartMessage", "已Batch Delete");

        } else {
            request.setAttribute("cartMessage", "Delete失败");
        }
        //Delete成功失败都To列表页面
        cartList(request, response);//通过servlet中listCart跳到列表

    }

    private void cartDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cartId = Integer.parseInt(request.getParameter("id"));
        CartDao cartDao = new CartDaoImpl();

        if (cartDao.cartDel(cartId)) {
            request.setAttribute("cartMessage", "该已Delete");
        } else {
            request.setAttribute("cartMessage", "该Delete失败");

        }

        cartList(request, response);
    }

    private void cartAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = new Cart(Integer.parseInt(request.getParameter("userId")),
                Integer.parseInt(request.getParameter("productId")),
                Integer.parseInt(request.getParameter("cartNum")),
                request.getParameter("status"),
                Integer.parseInt(request.getParameter("orderId"))
        );

        CartDao cartDao = new CartDaoImpl();
        if (cartDao.cartAdd(cart)) {
            request.setAttribute("cartMessage", "增加成功");
            cartList(request, response);
        } else {
            request.setAttribute("cartMessage", "增加失败");
            request.getRequestDispatcher(CATALOGADD_PATH).forward(request, response);
        }

    }

    //获取列表
    private void cartList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int curPage = 1;
        String page = request.getParameter("page");
        if (page != null) {
            curPage = Integer.parseInt(page);
        }
        int maxSize = 10;
        CartDao cartDao = new CartDaoImpl();

        PageBean pb = new PageBean(curPage, maxSize, cartDao.cartReadCount());

        request.setAttribute("pageBean", pb);

        request.setAttribute("cartList", cartDao.cartList(pb));
        request.getRequestDispatcher(CATALOGLIST_PATH).forward(request, response);
    }


    private void cartEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从请求参数中获取id
        int id = Integer.parseInt(request.getParameter("id"));
        CartDao cartDao = new CartDaoImpl();
        Cart cart = cartDao.findCartById(id);
        request.setAttribute("cartInfo", cart);
        request.getRequestDispatcher("/jsp/admin/cartManage/cartEdit.jsp").forward(request, response);
    }



    private void cartUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取前端页面input标签中的内容，构造新的Cart对象，Update数据库表中的信息
        Cart cart = new Cart(
                Integer.parseInt(request.getParameter("id")),
                Integer.parseInt(request.getParameter("userId")),
                Integer.parseInt(request.getParameter("productId")),
                Integer.parseInt(request.getParameter("cartNum")),
                request.getParameter("status"),
                Integer.parseInt(request.getParameter("orderId"))
        );
        //往数据库中Update该信息
        CartDaoImpl cartDao = new CartDaoImpl();
        if (cartDao.cartUpdate(cart)) {
            //提示Update成功
            request.setAttribute("cartMessage", "Update成功！");
            //再次返回cartList页面
            cartList(request, response);
        } else {
            //提示Update失败
            request.setAttribute("cartMessage", "Update失败！");
            request.getRequestDispatcher("/jsp/admin/cartManage/cartEdit.jsp").forward(request, response);
        }
    }

}



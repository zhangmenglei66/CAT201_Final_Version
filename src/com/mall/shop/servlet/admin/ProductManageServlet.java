package com.mall.shop.servlet.admin;

import com.mall.shop.bean.Category;
import com.mall.shop.bean.PageBean;
import com.mall.shop.bean.Product;

import com.mall.shop.dao.CategoryDao;
import com.mall.shop.dao.ProductDao;

import com.mall.shop.dao.impl.CategoryDaoImpl;
import com.mall.shop.dao.impl.ProductDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;


/**
 * Product servlet
 * Servlet implementation class productServlet
 */
@WebServlet("/jsp/admin/ProductManageServlet")
public class ProductManageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String CATALOGLIST_PATH = "productManage/productList.jsp";
    private static final String CATALOGADD_PATH = "productManage/productAdd.jsp";


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
                productList(request, response);
                break;
            case "add":
                productAdd(request, response);
                break;
           case "toadd":
               toadd(request, response);
                break;
            case "del":
                productDel(request, response);
                break;
            case "batDel":
                productBatDel(request, response);
                break;

            case "edit":
                productEdit(request, response);
                break;
            case "update":
                productUpdate(request, response);
                break;
        }
    }


    private void productBatDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ids = request.getParameter("ids");
        ProductDao productDao = new ProductDaoImpl();

        if (productDao.productBatDelById(ids)) {
            request.setAttribute("productMessage", "Product 已Batch Delete");

        } else {
            request.setAttribute("productMessage", "Product Delete失败");
        }
        //Product Delete成功失败都ToProduct List页面
        productList(request, response);//通过servlet中listProduct跳到Product List

    }

    private void productDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("id"));

        ProductDao productDao = new ProductDaoImpl();

        if (productDao.productDel(productId)) {
            request.setAttribute("productMessage", "该Product 已Delete");
        } else {
            request.setAttribute("productMessage", "该Product Delete失败");
        }


        productList(request, response);
    }

    private void productAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Product product = new Product(
                request.getParameter("productName"),
                request.getParameter("productDescription"),
                Double.parseDouble(request.getParameter("productPrice")),
                Integer.parseInt(request.getParameter("productStock")),
                Integer.parseInt(request.getParameter("productFid")),
                Integer.parseInt(request.getParameter("productCid")),
                request.getParameter("productImage"),
                Integer.parseInt(request.getParameter("productStatus"))
        );


        ProductDao productDao = new ProductDaoImpl();
        if (productDao.productAdd(product)) {
            request.setAttribute("productMessage", "Add Product成功");
            productList(request, response);
        } else {
            request.setAttribute("productMessage", "Add Product失败");
            request.getRequestDispatcher(CATALOGADD_PATH).forward(request, response);
        }

    }

    //获取Product List
    private void productList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int curPage = 1;
        String page = request.getParameter("page");
        if (page != null) {
            curPage = Integer.parseInt(page);
        }
        int maxSize = 10;
        ProductDao productDao = new ProductDaoImpl();

        PageBean pb = new PageBean(curPage, maxSize, productDao.productReadCount());
        request.setAttribute("pageBean", pb);
        request.setAttribute("productList", productDao.productList(pb));
        request.getRequestDispatcher(CATALOGLIST_PATH).forward(request, response);
    }


    private void productEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从请求参数中获取Product id
        int id = Integer.parseInt(request.getParameter("id"));
        ProductDao productDao = new ProductDaoImpl();
        Product product = productDao.findProductById(id);
        CategoryDao categoryDao = new CategoryDaoImpl();
        List<Category> categoryfList = categoryDao.categoryFather();
        List<Category> categorycList = categoryDao.categoryChildren(product.getProductFid());
        request.setAttribute("categoryfList", categoryfList);
        request.setAttribute("categorycList", categorycList);
        request.setAttribute("productInfo", product);
        request.getRequestDispatcher("/jsp/admin/productManage/productEdit.jsp").forward(request, response);
    }
    private void toadd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryDao categoryDao = new CategoryDaoImpl();
        List<Category> categoryList = categoryDao.categoryFather();
        request.setAttribute("categoryList", categoryList);
        request.getRequestDispatcher("/jsp/admin/productManage/productAdd.jsp").forward(request, response);
    }



    private void productUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取前端页面input标签中的内容，构造新的Product对象，Update数据库表中的Product 信息
        Product product = new Product(
                Integer.parseInt(request.getParameter("id")),
                request.getParameter("productName"),
                request.getParameter("productDescription"),
                Double.parseDouble(request.getParameter("productPrice")),
                Integer.parseInt(request.getParameter("productStock")),
                Integer.parseInt(request.getParameter("productFid")),
                Integer.parseInt(request.getParameter("productCid")),
                request.getParameter("productImage"),
                Integer.parseInt(request.getParameter("productStatus"))
                );
        //往数据库中Update该Product 信息
        ProductDaoImpl productDao = new ProductDaoImpl();
        if (productDao.productUpdate(product)) {
            //提示Product Update成功
            request.setAttribute("productMessage", "UpdateProduct 成功！");
            //再次返回productList页面
            productList(request, response);
        } else {
            //提示Product Update失败
            request.setAttribute("productMessage", "UpdateProduct 失败！");
            request.getRequestDispatcher("/jsp/admin/productManage/productEdit.jsp").forward(request, response);
        }
    }

}



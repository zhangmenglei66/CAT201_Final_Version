package com.mall.shop.servlet.admin;

import com.mall.shop.bean.PageBean;
import com.mall.shop.bean.Category;

import com.mall.shop.dao.CategoryDao;

import com.mall.shop.dao.impl.CategoryDaoImpl;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * 分类servlet
 * Servlet implementation class categoryServlet
 */
@WebServlet("/jsp/admin/CategoryManageServlet")
public class CategoryManageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String CATALOGLIST_PATH = "categoryManage/categoryList.jsp";
    private static final String CATALOGADD_PATH = "categoryManage/categoryAdd.jsp";


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
                categoryList(request, response);
                break;
            case "categoryChildren":
                categoryChildren(request, response);
                break;
            case "add":
                categoryAdd(request, response);
                break;
            case "add2":
                categoryAdd2(request, response);
                break;
            case "del":
                categoryDel(request, response);
                break;
            case "batDel":
                categoryBatDel(request, response);
                break;

            case "edit":
                categoryEdit(request, response);
                break;
            case "categorytoadd":
                categorytoadd(request, response);
                break;
            case "update":
                categoryUpdate(request, response);
                break;
        }
    }


    private void categoryBatDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ids = request.getParameter("ids");
        CategoryDao categoryDao = new CategoryDaoImpl();

        if (categoryDao.categoryBatDelById(ids)) {
            request.setAttribute("categoryMessage", "分类已Batch Delete");

        } else {
            request.setAttribute("categoryMessage", "分类Delete失败");
        }
        //分类Delete成功失败都ToCategory List页面
        categoryList(request, response);//通过servlet中listCategory跳到Category List

    }

    private void categoryDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int categoryId = Integer.parseInt(request.getParameter("id"));
        CategoryDao categoryDao = new CategoryDaoImpl();

        if (categoryDao.categoryDel(categoryId)) {
            request.setAttribute("categoryMessage", "该分类已Delete");
        } else {
            request.setAttribute("categoryMessage", "该分类Delete失败");
        }
        categoryList(request, response);
    }

    private void categoryAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Category category = new Category(request.getParameter("cateName"), 0);
        CategoryDao categoryDao = new CategoryDaoImpl();
        if (categoryDao.categoryAdd(category)) {
            request.setAttribute("categoryMessage", "增加分类成功");
            categoryList(request, response);
        } else {
            request.setAttribute("categoryMessage", "增加分类失败");
            request.getRequestDispatcher(CATALOGADD_PATH).forward(request, response);
        }

    }

    private void categoryAdd2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Category category = new Category(
                request.getParameter("cateName"),
                Integer.parseInt(request.getParameter("cateParentId"))
        );
        CategoryDao categoryDao = new CategoryDaoImpl();
        if (categoryDao.categoryAdd(category)) {
            request.setAttribute("categoryMessage", "增加分类成功");
            categoryList(request, response);
        } else {
            request.setAttribute("categoryMessage", "增加分类失败");
            request.getRequestDispatcher(CATALOGADD_PATH).forward(request, response);
        }

    }

    //获取Category List
    private void categoryList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int curPage = 1;
        String page = request.getParameter("page");
        if (page != null) {
            curPage = Integer.parseInt(page);
        }
        int maxSize = 10;
        CategoryDao categoryDao = new CategoryDaoImpl();

        PageBean pb = new PageBean(curPage, maxSize, categoryDao.categoryReadCount());

        request.setAttribute("pageBean", pb);

        request.setAttribute("categoryList", categoryDao.categoryList(pb));
        request.getRequestDispatcher(CATALOGLIST_PATH).forward(request, response);
    }

    private void categoryChildren(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int fid =Integer.parseInt(request.getParameter("fid")) ;
        CategoryDao categoryDao = new CategoryDaoImpl();
        JSONObject json=new JSONObject();
        List<Category> categoryList = categoryDao.categoryChildren(fid);

        json.put("categoryList", categoryList);
        request.setAttribute("categoryList", categoryList);
        response.getWriter().write(json.toString());
    }


    private void categoryEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从请求参数中获取Secondary category
        int id = Integer.parseInt(request.getParameter("id"));
        CategoryDao categoryDao = new CategoryDaoImpl();
        Category category = categoryDao.findCategoryById(id);
        List<Category> categoryList = categoryDao.categoryFather();
        request.setAttribute("categoryList", categoryList);
        request.setAttribute("categoryInfo", category);
        request.getRequestDispatcher("/jsp/admin/categoryManage/categoryEdit.jsp").forward(request, response);
    }

    private void categorytoadd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从请求参数中获取Secondary category
        CategoryDao categoryDao = new CategoryDaoImpl();
        List<Category> categoryList = categoryDao.categoryFather();
        request.setAttribute("categoryList", categoryList);
        request.getRequestDispatcher("/jsp/admin/categoryManage/categoryAdd2.jsp").forward(request, response);
    }


    private void categoryUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取前端页面input标签中的内容，构造新的Category对象，Update数据库表中的分类信息
      String cateParentId=  request.getParameter("cateParentId");
      Integer fid = cateParentId==null?0: Integer.parseInt(request.getParameter("cateParentId"));
        Category category = new Category(
                Integer.parseInt(request.getParameter("id")),
                request.getParameter("cateName"),
                fid
        );
        //往数据库中Update该分类信息
        CategoryDaoImpl categoryDao = new CategoryDaoImpl();
        if (categoryDao.categoryUpdate(category)) {
            //提示分类Update成功
            request.setAttribute("categoryMessage", "Update分类成功！");
            //再次返回categoryList页面
            categoryList(request, response);
        } else {
            //提示分类Update失败
            request.setAttribute("categoryMessage", "Update分类失败！");
            request.getRequestDispatcher("/jsp/admin/categoryManage/categoryEdit.jsp").forward(request, response);
        }
    }

}



package com.mall.shop.servlet.shop;

import com.mall.shop.bean.*;
import com.mall.shop.dao.*;
import com.mall.shop.dao.impl.*;
import com.mall.shop.util.RandomUtils;
import com.mall.shop.util.VerificationCode;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/jsp/IndexServlet")
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "base":
                indexList(request, response);
                break;
            case "mydata":
                mydata(request, response);
                break;
            case "changedata":
                changedata(request, response);
                break;
            case "address":
                address(request, response);
                break;
            case "addresschange":
                addresschange(request, response);
                break;
            case "selectproduct":
                selectproduct(request, response);
                break;
            case "productdetails":
                productdetails(request, response);
                break;
            case "verifyCode":
                verifyCode(request, response);
                break;
            case "findcart":
                findcart(request, response);
                break;
            case "cart_numchange":
                cart_numchange(request, response);
                break;
            case "cart_del":
                cart_del(request, response);
                break;
            case "manydelete":
                manydelete(request, response);
                break;
            case "order":
                order(request, response);
                break;
            case "addorder":
                addorder(request, response);
                break;
            case "myorder":
                myorder(request, response);
                break;
            case "updatestatus":
                updatestatus(request, response);
                break;
            case "comfim":
                comfim(request, response);
                break;
            case "productoperation":
                productoperation(request, response);
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
    private void indexList(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ProductDao productDao = new ProductDaoImpl();
        CategoryDao categoryDao = new CategoryDaoImpl();
        List<Category> flist = categoryDao.findAllParentCate();
        List<Category> clist = categoryDao.findAllChildCate();
        List<Product> fz = productDao.findAllfproduct(3);
        List<Product> dz = productDao.findAllfproduct(1);
        List<Product> mz = productDao.findAllfproduct(6);

        request.setAttribute("flist", flist);
        request.setAttribute("clist", clist);
        request.setAttribute("fz", fz);
        request.setAttribute("dz", dz);
        request.setAttribute("mz", mz);
        request.getRequestDispatcher("index.jsp").forward(request, response);

    }

    //增加用户
    private void mydata(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CategoryDao categoryDao = new CategoryDaoImpl();
        List<Category> flist = categoryDao.findAllParentCate();
        List<Category> clist = categoryDao.findAllChildCate();
        request.setAttribute("flist", flist);
        request.setAttribute("clist", clist);
        request.getRequestDispatcher("Mydata.jsp").forward(request, response);

    }

    //Update用户信息
    private void changedata(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryDao categoryDao = new CategoryDaoImpl();
        List<Category> flist = categoryDao.findAllParentCate();
        List<Category> clist = categoryDao.findAllChildCate();
        request.setAttribute("flist", flist);
        request.setAttribute("clist", clist);
        request.getRequestDispatcher("changedata.jsp").forward(request, response);
    }

    private void address(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryDao categoryDao = new CategoryDaoImpl();
        List<Category> flist = categoryDao.findAllParentCate();
        List<Category> clist = categoryDao.findAllChildCate();
        request.setAttribute("flist", flist);
        request.setAttribute("clist", clist);
       User user = (User) request.getSession().getAttribute("user");
       AddressDao addressDao = new AddressDaoImpl();

        Address address = addressDao.findAddressByUserId(user.getId());
        if(address!=null){
            request.setAttribute("address", address);
        }else {
            Address address1 = new Address();
            request.setAttribute("address", address1);
        }

        request.getRequestDispatcher("address.jsp").forward(request, response);

    }

    private void addresschange(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从请求参数中获取User ID
        String addressId = request.getParameter("id");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        AddressDao ad = new AddressDaoImpl();

        User user = (User) request.getSession().getAttribute("user");
        Address address1 = ad.findAddressByUserId(user.getId());
        if(address1.getId()!=null){
            address1.setAddress(address);
            address1.setName(name);
            address1.setPhone(phone);
            ad.addressUpdate(address1);
        }else {
            Address address2 = new Address();
            address2.setAddress(address);
            address2.setName(name);
            address2.setPhone(phone);
            address2.setUserId(user.getId());
            ad.addressAdd(address2);
        }

        response.sendRedirect("/jsp/IndexServlet?action=address");

    }

    private void findcart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryDao categoryDao = new CategoryDaoImpl();
        List<Category> flist = categoryDao.findAllParentCate();
        List<Category> clist = categoryDao.findAllChildCate();
        request.setAttribute("flist", flist);
        request.setAttribute("clist", clist);

        CartDao cartDao = new CartDaoImpl();
        User user = (User) request.getSession().getAttribute("user");
        int userId = user.getId();
        List<Cart> cartList = cartDao.findCartByUserId(userId);
        ProductDao productDao = new ProductDaoImpl();
        for (int i = 0; i < cartList.size(); i++) {
            Cart cart = cartList.get(i);
            int productId = cart.getProductId();
            Product product = productDao.findProductById(productId);
            cart.setProduct(product);

        }
        request.setAttribute("cart", cartList);
        request.getRequestDispatcher("cart.jsp").forward(request, response);

    }

    //Edit用户
    private void selectproduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryDao categoryDao = new CategoryDaoImpl();
        ProductDao productDao = new ProductDaoImpl();
        List<Category> flist = categoryDao.findAllParentCate();
        List<Category> clist = categoryDao.findAllChildCate();
        request.setAttribute("flist", flist);
        request.setAttribute("clist", clist);
        String fid = request.getParameter("fid");
        String cid = request.getParameter("cid");
        if (cid == null) {
            Category cate = categoryDao.findCategoryById(Integer.parseInt(fid) );
            request.setAttribute("cate", cate);

            List<Product> product = productDao.findAllfproduct(Integer.parseInt(fid));
            request.setAttribute("product", product);
        }
        if (cid != null) {
            Category cate = categoryDao.findCategoryById(Integer.parseInt(cid));
            Category catef = categoryDao.findCategoryById(cate.getCateParentId());
            List<Product> product = productDao.findAllcproduct(Integer.parseInt(cid));
            request.setAttribute("product", product);
            request.setAttribute("cate", cate);
            request.setAttribute("catef", catef);
        }

        request.getRequestDispatcher("productlist.jsp").forward(request, response);
    }

    //Delete用户
    private void productdetails(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CategoryDao categoryDao = new CategoryDaoImpl();
        ProductDao productDao = new ProductDaoImpl();
        List<Category> flist = categoryDao.findAllParentCate();
        List<Category> clist = categoryDao.findAllChildCate();
        request.setAttribute("flist", flist);
        request.setAttribute("clist", clist);
        Integer product_id = Integer.parseInt(request.getParameter("product_id"));
        Product product = productDao.findProductById(product_id);
        request.setAttribute("product", product);
        request.getRequestDispatcher("prodetails.jsp").forward(request, response);
    }

    //Batch Delete
    private void verifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        VerificationCode code = new VerificationCode();
        BufferedImage image = code.getImage();
        String text = code.getText();
        HttpSession session = request.getSession(true);
        session.setAttribute("verify_code", text);
        VerificationCode.output(image, response.getOutputStream());

    }

    private void cart_numchange(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CartDao cartDao = new CartDaoImpl();
        Integer cart_id = Integer.parseInt(request.getParameter("cart_id"));
        Integer count = Integer.parseInt(request.getParameter("count"));
        cartDao.cart_numchange(cart_id, count);
    }

    private void cart_del(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CartDao cartDao = new CartDaoImpl();
        Integer cart_id = Integer.parseInt(request.getParameter("cart_id"));
        cartDao.cartDel(cart_id);
    }
    private void manydelete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CartDao cartDao = new CartDaoImpl();
        String str = request.getParameter("str");
        String[] id = str.split(",");
        for(int i=0;i<id.length;i++){
            cartDao.cartDel(Integer.parseInt(id[i]));
        }
    }
    private void myorder(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CategoryDao categoryDao = new CategoryDaoImpl();
        ProductDao productDao = new ProductDaoImpl();
        List<Category> flist = categoryDao.findAllParentCate();
        List<Category> clist = categoryDao.findAllChildCate();
        request.setAttribute("flist", flist);
        request.setAttribute("clist", clist);
        int curPage = 1;
        String page = request.getParameter("page");
        if (page != null) {
            curPage = Integer.parseInt(page);
        }
        int maxSize = 10;
        String size = request.getParameter("size");
        if (size != null) {
            maxSize = Integer.parseInt(size);
        }
        OrdersDao ordersDao = new OrdersDaoImpl();
        User user = (User) request.getSession().getAttribute("user");
        int userId = user.getId();
        PageBean pb = new PageBean(curPage, maxSize, ordersDao.myordersReadCount(userId));
        List<Orders> ordersList= ordersDao.myordersList(userId,pb);
        for (int i = 0; i < ordersList.size(); i++) {
            Integer productId = ordersList.get(i).getProductId();
            Product product = productDao.findProductById(productId);
            ordersList.get(i).setProduct(product);

        }
        request.setAttribute("pageInfo", pb);
        request.setAttribute("ordersList", ordersList);
        request.getRequestDispatcher("myorder.jsp").forward(request, response);

    }

    private void updatestatus(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        OrdersDao ordersDao = new OrdersDaoImpl();
        ProductDao productDao = new ProductDaoImpl();
        int order_id = Integer.parseInt(request.getParameter("order_id"));
        int product_id = Integer.parseInt(request.getParameter("product_id"));
        int order_num = Integer.parseInt(request.getParameter("order_num"));

        Product product = productDao.findProductById(product_id);
        int kuncun = product.getProductStock() + order_num;

        product.setProductStock(kuncun);
        productDao.productUpdate(product);
        Orders orders = ordersDao.findOrdersById(order_id);
        orders.setStatus(1);
        ordersDao.ordersUpdate(orders);
        response.sendRedirect("/jsp/IndexServlet?action=myorder");
    }

    private void comfim(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        OrdersDao ordersDao = new OrdersDaoImpl();
        int order_id = Integer.parseInt(request.getParameter("order_id"));
        Orders orders = ordersDao.findOrdersById(order_id);
        orders.setStatus(3);
        ordersDao.ordersUpdate(orders);
        response.sendRedirect("/jsp/IndexServlet?action=myorder");
    }

    private void addorder(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CartDao cartDao = new CartDaoImpl();
        OrdersDao ordersDao = new OrdersDaoImpl() ;
        ProductDao productDao = new ProductDaoImpl();
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String realname = request.getParameter("realname");
        String str = request.getParameter("str");
        String[] id = str.split(",");
        User user = (User) request.getSession().getAttribute("user");
        int user_id = user.getId();
        for(int i=0;i<id.length;i++){
            SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //获取Order creation time
            Date createTime = new Date();
            Cart cart = cartDao.findCartById(Integer.parseInt(id[i]));
            int productId = cart.getProductId();
            Product product = productDao.findProductById(productId);
            Orders order = new Orders();
            String order_no = "ORDE"+ RandomUtils.generateNumberString(12);
            order.setOrderNo(order_no);
            order.setCreateTime(createTime);
            order.setUserId(user_id);
            order.setProductId(productId);
            order.setPrice(product.getProductPrice() * cart.getCartNum());
            order.setNum(cart.getCartNum());
            int kucun=product.getProductStock()-cart.getCartNum();


            order.setAddress(address);
            order.setPhone(phone);
            order.setRealname(realname);
            order.setStatus(0);
            ordersDao.ordersAdd(order);
            product.setProductStock(kucun);
            productDao.productUpdate(product);
            cartDao.cartDel(Integer.parseInt(id[i]));
        }
        response.sendRedirect("/jsp/IndexServlet?action=myorder");

    }
    private void order(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CartDao cartDao = new CartDaoImpl();
        double totalprice = 0;
        int count = 0;
        CategoryDao categoryDao = new CategoryDaoImpl();
        ProductDao productDao = new ProductDaoImpl();
        List<Category> flist = categoryDao.findAllParentCate();
        List<Category> clist = categoryDao.findAllChildCate();
        request.setAttribute("flist", flist);
        request.setAttribute("clist", clist);
        User user = (User) request.getSession().getAttribute("user");
        AddressDao addressDao = new AddressDaoImpl();
        Address address = addressDao.findAddressByUserId(user.getId());

        List<Cart> carts = new ArrayList<>();
        String str = request.getParameter("str");
        String[] id = str.split(",");
        for(int i=0;i<id.length;i++){
            Cart cart = cartDao.findCartById(Integer.parseInt(id[i]));
            int productId = cart.getProductId();
            Product product = productDao.findProductById(productId);
            cart.setProduct(product);
            double dprice = product.getProductPrice() * cart.getCartNum();
            totalprice +=dprice;
            int num = cart.getCartNum();
            count += num;
            carts.add(cart);
        }
        request.setAttribute("count",count);
        request.setAttribute("totalprice",totalprice);
        request.setAttribute("order",carts);
        if(address!=null){
            request.setAttribute("address",address);
        }else {
            Address address1 = new Address();
            request.setAttribute("address",address1);
        }

        request.getRequestDispatcher("order.jsp").forward(request, response);
    }


    private void productoperation(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer product_id = Integer.parseInt(request.getParameter("product_id"));
        Integer cart_num = Integer.parseInt(request.getParameter("cart_num"));
        ProductDao productDao = new ProductDaoImpl();
        CartDao cartDao = new CartDaoImpl();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            Integer user_id = user.getId();
            Integer count = cartDao.checkcart(user_id, product_id);
            //若购物车不存在该Product ，则Successfully added to cart
            if (count == 0) {
                Product product = productDao.findProductById(product_id);
                Cart cart = new Cart();
                cart.setUserId(user_id);
                cart.setCartNum(cart_num);
                cart.setProductId(product_id);
                cart.setStatus("0");
                cartDao.cartAdd(cart);
                out.println("<script>");
                out.println("alert('Successfully added to cart');");
                out.println("history.back();");
                out.println("</script>");
                out.close();
            }
            //若购物车存在已存在该Product ,则UpdateProduct 的Quantity:
            else {
                int newcount = count + cart_num;
                cartDao.updatecart_num(user_id, product_id, newcount);
                out.println("<script>");
                out.println("alert('Successfully added to cart');");
                out.println("history.back();");
                out.println("</script>");
                out.close();
            }
        } else {
            out.println("<script>");
            out.println("alert('Please login first');");
            out.println("history.back();");
            out.println("</script>");
            out.close();
        }

    }

}

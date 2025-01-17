package com.mall.shop.servlet.admin;

import com.mall.shop.util.RandomUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.annotation.WebServlet;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@WebServlet(name = "MultipartServlet",urlPatterns = "/upload")
@WebServlet("/jsp/admin/upload")
public class MultipartServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException, UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        Map<String,String> parameters =new HashMap<>();

        // 1 获取到工厂
        DiskFileItemFactory factory=new DiskFileItemFactory();
        factory.setSizeThreshold(1024*1024*20);//10mb               //  设置缓冲区大小
        factory.setRepository(new File("D:/img"));    // 设置缓冲区路径

        // 2 获得文件上传对象
        ServletFileUpload upload=new ServletFileUpload(factory);
        upload.setSizeMax(1024*1024*10);                             // 设置单个文件最大上传大小
String newFileName="";
        // 3 获得表单所有Submit的数据
        try {
            List<FileItem> list =upload.parseRequest(request);
            for(FileItem fileItem:list){
                // 判断当前表单控件是普通控件，还是上传文件的控件
                if(fileItem.isFormField()){
                    // 获取名称
                    String fileItemName = fileItem.getFieldName();
                    System.out.println(fileItemName);
                    //获取普通控件得val值
                    String value = fileItem.getString("utf-8");
                    //将普通技术Add到map
                    parameters.put(fileItemName,value);

                }else{
                    // 获得上传图片得源路径名称
                    String name = fileItem.getName();
                    // 通过方法生成新得文件名称
                     newFileName = generatorName(name);
                    //获得上传文件Save的目录
                    String realPath = request.getSession().getServletContext().getRealPath("photo");
                    System.out.println(realPath);
                    //将缓冲区的文件移动到photo
                    fileItem.write(new File(realPath,newFileName));
                }
            }


        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(newFileName);
        writer.close();
    }

    //生成新的文件名
    private String generatorName(String name) {
        //截取图片扩展名称
        String extenName = name.substring(name.lastIndexOf("."));
       String filename= RandomUtils.generateLowerString(16)+extenName;
        // 使用UUID+扩展名形成新的文件名
        return filename;
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request,response);
    }
}

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Product 增加</title>
    <link rel="stylesheet" type="text/css" href="bs/css/bootstrap.css">
    <script type="text/javascript" src="bs/js/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="bs/validform/style.css">
    <script type="text/javascript" src="bs/validform/Validform_v5.3.2_min.js"></script>
	<style type="text/css">
		body{
			margin:0;
			padding:0;
			background:#eee;
		}
		.showpic {
			height: 150px;
			width: 200px;
			margin-top: 10px;
			margin-bottom: 10px;
		}
	</style>
</head>
<body>
<div class="container">
    <c:if test="${!empty productMessage}">
        <h3 class="text-center">${productMessage}</h3>
    </c:if>
    <h2 class="text-center">Product 增加</h2>
    <form id="productAddForm" class="form-horizontal" action="jsp/admin/ProductManageServlet?action=add" method="post">
        <div class="form-group">
            <label for="productName" class="col-sm-2 col-sm-offset-2 control-label">Product name</label>
            <div class="col-sm-4 ">
                <input type="text" name="productName" id="productName" class="form-control" required/>
            </div>
            <div class="col-sm-4 Validform_checktip"></div>
        </div>
        <div class="form-group">
            <label for="productDescription" class="col-sm-2 col-sm-offset-2 control-label">Product description</label>
            <div class="col-sm-4 ">
                <input type="text" name="productDescription" id="productDescription" class="form-control" required/>
            </div>
            <div class="col-sm-4 Validform_checktip"></div>
        </div>
        <div class="form-group">
            <label for="productPrice" class="col-sm-2 col-sm-offset-2 control-label">Price</label>
            <div class="col-sm-4 ">
                <input type="text" name="productPrice" id="productPrice" class="form-control" required/>
            </div>
            <div class="col-sm-4 Validform_checktip"></div>
        </div>
        <div class="form-group">
            <label for="productStock" class="col-sm-2 col-sm-offset-2 control-label"></label>
            <div class="col-sm-4 ">
                <input type="text" name="productStock" id="productStock" class="form-control" required/>
            </div>
            <div class="col-sm-4 Validform_checktip"></div>
        </div>

        <div class="form-group">
            <label for="productFid" class="col-sm-2 col-sm-offset-2 control-label">Primary category</label>
            <div class="col-sm-4 ">
                <select name="productFid" id="productFid" class="form-control" required>
                    <option value="">==Please select Primary category==</option>
                    <c:if test="${!empty categoryList}">
                        <c:forEach items="${categoryList}" var="i" >
                            <option value="${i.id}">${i.cateName}</option>
                        </c:forEach>
                    </c:if>
                </select>
            </div>
            <div class="col-sm-4 Validform_checktip"></div>
        </div>
        <div class="form-group">
            <label for="productCid" class="col-sm-2 col-sm-offset-2 control-label">Secondary category</label>
            <div class="col-sm-4 ">
                <select  class="form-control" name='productCid' id='productCid' required></select>
            </div>
            <div class="col-sm-4 Validform_checktip"></div>
        </div>

		<div class="form-group">
			<label  class="col-sm-2 col-sm-offset-2 control-label">Image Preview</label>
			<div class="col-sm-4">
				<input id="productImage" name="productImage" class="form-control" type="hidden">
				<img id="img"  class="showpic" src=""/>
			</div>
			<div class="col-sm-4 Validform_checktip"></div>
		</div>
		<div class="form-group">
			<label for="uploadimg" class="col-sm-2 col-sm-offset-2 control-label">Image Upload</label>
			<div class="col-sm-4">
				<input id="uploadimg"  type="file"/>
			</div>
			<div class="col-sm-4 Validform_checktip"></div>
		</div>
        <div class="form-group">
            <label for="productStatus" class="col-sm-2 col-sm-offset-2 control-label">Product Status</label>
            <div class="col-sm-4 ">
                <select name="productStatus" id="productStatus" class="form-control" required>
                    <option value="1">Put on shelf</option>
                    <option value="0">Remove from shelf</option>

                </select>
            </div>
            <div class="col-sm-4 Validform_checktip"></div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 col-sm-offset-2 control-label">
                <input class="btn btn-success btn-block" type="submit" value="Submit">
            </label>
            <label class="col-sm-2 control-label">
                <input class="btn btn-warning btn-block" type="reset" value="Reset">
            </label>

        </div>

    </form>

</div>


</body>
<script>
	$(function () {
		$('#uploadimg').on('change', function () {
			var formData = new FormData();
			formData.append("file", $("#uploadimg")[0].files[0]);
			$.ajax({
				url: 'jsp/admin/upload', /*接口域名Address*/
				type: 'post',
				data: formData,
				contentType: false,
				processData: false,
				success: function (res) {
					$("#productImage").val(res);
					var src =  "/photo/" + res;
					$("#img").attr('src', src);

				}
			})
		})

        $('#productFid').change(function(){
            var productFid = $('#productFid').val();
            $("#productCid").val("");
            $.ajax({
                url: 'jsp/admin/CategoryManageServlet?action=categoryChildren', /*接口域名Address*/
                type: 'post',
                data: {fid:productFid},

                success: function (res) {
                    const json=   JSON.parse(res)
                    console.log(json)
                    var data = json.categoryList;
                    addOptions(data);
                }
            })
        });
	})

    function addOptions(project) {

        var pro = $('#productCid');
        pro.empty();
        var options = '<option value ="0">Please select </option>';
        $(project).each(function () {
            options += '<option value="' + this.id + '" >' + this.cateName + '</option>';
        });
        pro.append(options);
    }
</script>
</html>

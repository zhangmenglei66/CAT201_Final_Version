$(function () {
    $(".edit").click(function () {
        $(".mask").show();
        $(".adddz").show()
    });
    $(".bc>input").click(function () {
        if ($(this).val() == "Save") {
            $(".mask").hide();
            $(".adddz").hide();
            $(".bj").hide();
            $(".xg").hide();
            $(".remima").hide();
            $(".pj").hide();
            $(".chak").hide()
        } else {
            $(".mask").hide();
            $(".adddz").hide();
            $(".bj").hide();
            $(".xg").hide();
            $(".remima").hide();
            $(".pj").hide();
            $(".chak").hide()
        }
    });
    $("#wa li").click(function () {
        $(this).addClass("on").siblings().removeClass("on");
        var a = $(this).find("a").text();
        $(".dkuang").find("p.one").each(function () {
            var b = $(this).text();
            if (a == b) {
                $(this).parent(".dkuang").show().siblings(".dkuang").hide()
            }
            $("#wa li").eq(0).click(function () {
                $(".dkuang").show()
            })
        })
    });
    $(".sx div:gt(0)").hide();
    $(".sx div").each(function (a) {
        if ($(this).html() == "") {
            var b = $("#pro li").eq(a).find("a").text();
            var c = "";
            c = '<div class="noz">当前没有' + b + "。</div>";
            $(this).html(c)
        }
    });
    $("#pro li").click(function () {
        $(this).addClass("on").siblings().removeClass("on");
        var a = $(this).index();
        $(".sx > div").eq(a).show().siblings().hide()
    });
    $("#edit1").click(function () {
        $(".mask").show();
        $(".bj").show()
    });
    $("#edit2").click(function () {
        $(".mask").show();
        $(".xg").show()
    });
    $("#avatar").click(function () {
        $(".mask").show();
        $(".avatar").show()
    });
    $(".gb").click(function () {
        $(".mask").hide();
        $(".bj").hide();
        $(".xg").hide();
        $(".remima").hide();
        $(".avatar").hide();
        $(".pj").hide();
        $(".chak").hide()
    });
    $("#addxad").click(function () {
        $(".mask").show();
        $(".adddz").show()
    });
    $("#dizhi").hover(function () {
        var a = "";
        a = '<p class="addp"><a href="#"  id="readd">Edit</a><a href="#" id="deladd">Delete</a></p>';
        $(this).append(a);
        $("#readd").click(function () {
            $(".mask").show();
            $(".readd").show()
        });
        $("#deladd").click(function () {
            $(this).parents("#dizhi").remove()
        })
    }, function () {
        $(".bc>input").click(function () {
            if ($(this).val() == "Save") {
                $(".mask").hide();
                $(".readd").hide()
            } else {
                $(".mask").hide();
                $(".readd").hide()
            }
        });
        $(".addp").remove()
    });
    $(".vewwl").hover(function () {
        $(this).children(".wuliu").fadeIn(100)
    }, function () {
        $(this).children(".wuliu").fadeOut(100)
    })
});

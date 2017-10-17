<%@page import="com.arex.mydream.service.impl.UserServiceImpl"%>
<%@page import="com.arex.mydream.service.UserService"%>
<%@page import="com.arex.mydream.model.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<body>
<!DOCTYPE html>
<html xmlns:wb="http://open.weibo.com/wb">
<head>

    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta name="description" content="">
    <meta name="author" content="Rose">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="mobile-web-app-capable" content="yes">
    <meta name="viewport" content="width=device-width,user-scalable=0,initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-title" content="fruitday">
    <meta content="telephone=no" name="format-detection" />
    <meta content="email=no" name="format-detection" />
    <meta name="keywords" content="奇异果,苹果,橙,火龙果,葡萄,瓜,石榴,李子" />
    <meta name="description" content="当然果园官方网站提供水果生鲜、果篮券卡订制、干果零食等优质食品网购服务,2014年车厘子销量全网第一,佳沛奇异果、新奇士橙、进口牛油果等亦深受果友喜爱。" />
    <title>当然果园-水果网购首选品牌，水果，我们只挑有来头的！</title> <link rel="shortcut icon"  href="img/default_userpic.ico">
<link rel="shortcut icon" href="http://www.f333ruitday.com/favicon.ico" >
   <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="css/common.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
   
    <script src="js/jquery-1.11.0.min.js"></script>
	<script src="js/jquery.lazyload.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/usercenter-basics.css">
    <link rel="stylesheet" type="text/css" href="css/usercenter-donation.css">
     <link rel="stylesheet" type="text/css" href="css/usercenter-order.css">
    <link rel="stylesheet" type="text/css" href="css/usercenter-order-evaluate.css">
    <link rel="stylesheet" type="text/css" href="css/usercenter-tryeat.css">
    <link rel="stylesheet" type="text/css" href="css/order-online.css">
    
    <!--[if lt IE 9]>
    <link rel="stylesheet" href="http://www.fruitday.com/css/style-ie.css">
    <link rel="stylesheet" href="http://www.fruitday.com/css/style-ie1.css">
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <![endif]-->

    <!-- GA -->
    <script>
        (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
            (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
                m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
        })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

        ga('create', 'UA-52936203-1', 'auto');
        ga('send', 'pageview');

    </script>
</head>
<body>
<!-- top -->
<div class="top-con">
    
    </div>

<!-- 头部开始 -->

<section class="p-common-header">
<jsp:include page="console_element/top1.jsp"></jsp:include>
<jsp:include page="console_element/top2.jsp"></jsp:include>
</section>
      <section class="p-component-usercenter clearfix">
        <div class="sidebar s_ani" >
            <span class="arrow"  id="arr"></span>
            <ul class="list-unstyled">
	<li><a href="./user.jsp"  >我的账户</a></li>
	<li>	<a href="orderAction_searchOrders2" >我的订单</a><a href="wodejifen.jsp" >我的积分</a>
		<a href="zhanghuyue.jsp" >账户余额</a>
		<a href="wodeyonghujuan.jsp" >我的优惠券</a>
		<a href="wodezengping.jsp" >我的赠品</a>
		  
	</li>
	<li>
		<a href="./zaixianchongzhi.jsp" class="cur">在线充值</a>
		<a href="zhongzhifapiao.jsp" >充值发票</a>
		 <% User user= (User)session.getAttribute("user"); 
                    
                                    if(user.getuFlag().equals("2")){
                                    %>
                                   <a href="storeAction_MyStore" >管理我的店面 </a>
                                     <%
                                    }else{
                                   %>  
                                    <a href="admin/kaidian.jsp" >申请开店</a>
                                    <%
                                    }
                                
                                %>
	</li>
	<li>
		<a href="userAction_searchjibenziliao" >基本资料</a>
		<a href="mimaxiugai.jsp" >密码修改</a>
		<a href="shouhuodizhi.jsp" >收货地址</a>
		<a href="tongzhishezhi.jsp" >通知设置</a>
	</li>
</ul>
        </div>
        <div class="main recharge">
            <h5>
                 您当前的余额为：<span class="VI-color2">￥0.00</span> (充值积分即时到账)
            </h5>

            <div class="tabmenu" id="uctabnav">
                <ul class="list-unstyled">
                    <li class="selected"><a href="/user/recharge" class="link"><span>在线充值</span><i class="interval">|</i></a></li>
                </ul>
                <div class="wrap-line">
                    <span class="line select0"></span>
                </div>
            </div>

            <div class="recharge-con1">
                <div class="sum">
                    <h5>请选择充值金额：</h5>
                    <ul class="list-unstyled clearfix pay-account" id="recharge-sum">
                        <li price="500" class="cur">500元</li>
                        <li price="1000">1000元</li>
                        <li price="2000" >2000元</li>
                        <li><input type="text" value="" placeholder="输入金额">元</li>
                    </ul>
                </div>
                <div class="gift">

                    <h5>充值有礼<span class="VI-color2">（赠品有效期2周）</span>：</h5>
                    <ul class="list-unstyled clearfix">
                                                <li>
                            <h5>充值金额满2000元可获得：</h5>
                            <dl class="clearfix list-unstyled">
                                <dt><img src="img/1-180x180-3451-S8C7CU9K.jpg"></dt>
                                <dd>
                                    <h6>新奇士美国脐橙20个装+美国华盛顿甜脆红地厘蛇果20个装（充值后2周内有效）</h6>
                                    <span>组合装/盒</span>
                                    <span>价值￥198.00 X 1 </span>
                                </dd>
                            </dl>
                        </li>
                                                <li>
                            <h5>充值金额满1000元可获得：</h5>
                            <dl class="clearfix list-unstyled">
                                <dt><img src="img/1-180x180-3451-S8C7CU9K.jpg"></dt>
                                <dd>
                                    <h6>新奇士美国脐橙20个（充值后2周内有效）</h6>
                                    <span>20个装/盒</span>
                                    <span>价值￥99.00 X 1 </span>
                                </dd>
                            </dl>
                        </li>
                                            </ul>
                </div>
                <a class="btn btn-success" role="button">立即充值</a>

            </div>

            <div class="recharge-rules">
                <h5>用户需知： </h5>
                <ul class="list-unstyled">
                    <li><span>1.</span>由于水果季节性及营销活动调整，充值有礼赠品请以领取时产品为准，若带来不便，请多多见谅 ！ </li>
                    <li><span>2.</span>账户余额不能购买券卡，使用充值卡充值不参与充值有礼赠品活动；领取充值赠品需单笔充值满足赠品条件。 </li>
                    <li><span>3.</span>充值后若发生用户申请充值账户余额退款的情况，获得赠品的用户需将赠品退回或自行承担赠品实际金额费用，扣除赠品实际价值金额后退回相应余额。</li>
                    <li><span>4.</span>充值赠品具有有效期，获得赠品后有效期为2周，在有效期内未领取赠品将消失。</li>
                    <li><span>5.</span>只可申请开具三个月内充值的发票。</li>
                </ul>
            </div>
        </div>
    </section>
   

<jsp:include page="console_element/buttom.jsp"></jsp:include>

<!-- 53 kefu  -->
<!--<iframe src="/kefu" style="width: 35px;height: 35px;outline: none;border: none;position: fixed;bottom: 36px;right: 0;">-->
<!--</iframe>-->

<!-- baidu map -->
<script>
    var _hmt = _hmt || [];
    (function() {
        var hm = document.createElement("script");
        hm.src = "//hm.baidu.com/hm.js?1c8a96ff82d41e32620cf4036a594559";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
    })();
</script>

<script>
    var have_bigImg = $('.bigImg').length;
    var have_smallImg = $('.smallImg').length;
    var type = "";
    if((have_bigImg == 1) && (have_smallImg == 0) ){
        type = 1;
    }
    if((have_bigImg == 0) && (have_smallImg == 1) ){
        type = 2;
    }
    if((have_bigImg > 0) && (have_smallImg > 0) ){
        type = 3;
    }
    //type状态 后台同志提供
    switch(type){
        case 1:
            // 1，只有一张大图 就显示一张大图
            break;
        case 2:
            // 2，只有一张小图 就是显示一张小图
            break;
        case 3:
            // 3，大小图同时出现，3秒钟之后 大图渐渐消失，小图出来并一直在顶部显示了
        {
            setTimeout(function(){
                $('#bI').fadeOut('800',function(){

                    $('#sI').fadeIn(300);
                })

            },3000);
        }
            break;
    }
</script>
</body>

</html>
</body>
<script>
    $(function(){
        $('.btn-success').on('click', function(){
            $('.pay-account').find('li').filter(function(i){
				if ( $(this).attr('class') == 'cur'){
					 if( $(this).attr('price') ){
						 var price = $(this).attr('price');
					 }else{
						 var price = $(this).find("input[type=text]").val();
					 }

                    $.ajax({
                        'type':'post',
                        'url': '/ajax/user/createTrade',
                        'data':{price:price},
                        'dataType':'json'

                    }).done(function(rs) {
                        if(rs.code == 200)
                        {
                            top.location.href="/order/succ/" + rs.msg;
                        }
                        else{
                            gdialog_info(rs.msg);
                        }
                    });

				}
			});
        });
    });
</script>
</html>
 
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	    <base href="<%=basePath%>">
		<title>梦之队 • 当然果园在线系统 | 修改订单</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

		<link rel="stylesheet" type="text/css" href="admin/css/style.css" />
		<script type="text/javascript" src="admin/js/jquery.min.js"></script>
	    <script type="text/javascript" src="admin/js/yanzheng.js"></script>
	    </head>
	<body>
		<form id="orderUpdate" class="orderUpdate" action="ordersAction_updateOrders" method="post" >
		  <input type="hidden" name="oId" value="${ougp.oId}"/>
			<div class="right_content" align="center">
				<h2>修改订单</h2>
				<table id="input-table" style="width: 40%;">
					<tbody>
						<tr>
							<td>
								<span class="">订单日期</span>
							</td>
							<td>	
						
								<input name="oStartdate" id="ostartdate" class="info-textfield" type="text"  value="${ougp.oStartdate}"/>
							</td>
							
						</tr>
						<tr>
							<td>
								<span class="">订单状态</span>
							</td>
							<td>
								<input name="oStatus" id="ostatus" class="info-textfield" type="text"  value="${ougp.oStatus}"/>
							</td>
							
						</tr>
							<tr>
							<td>
								<span class="">送达日期</span>
							</td>
							<td>
								<input name="oEnddate" id="oenddate" class="info-textfield" type="text" value="${ougp.oEnddate }"/>
							</td>
							
						</tr>	<tr>
							
								<input name="uName" id="uname" class="info-textfield" type="hidden" value="${ougp.uName }" />
						
							
						</tr>
						<tr>
						
							
								<input name="gName" id="gname" class="info-textfield" type="hidden" value="${ougp.gName }"/>
							
							
						</tr>
						<tr>
							<td>
								<span class="">数量</span>
							</td>
							<td>
								<input name="pNum" id="pnum" class="info-textfield" type="text"  value="${ougp.pNum }"  onblur="checkPnum(this.value)" />
					 <span id="Msg1" style="color:red;font-size:12px; float: left;">&nbsp;</span>
							</td>
							
							
							
						</tr>
						<tr>
							
							
								<input name="gPrice" id="gprice" class="info-textfield" type="hidden" value="${ougp.gPrice }"/>
							
							
						</tr>
						<tr>
							<td>
								<span class="">订单地址</span>
							</td>
							<td>
								<input name="oAddress" id="oaddress" class="info-textfield" type="text" value="${ougp.oAddress }" />
							</td>
							
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td></td>
							<td colspan="2" class="">
				
								<!-- 页码 + 按钮 -->
								<div class="btns-pagenav">
									<!-- 按钮 -->
									<div class="div-btns" style="float: left;">							
									<input class="bt_green" type="submit" id="bt_green" value="修改订单">									
										<a href="ordersAction_searchOrdersBySid" class="bt_green" onclick="reset()">返 回</a>
				 					</div>
									<!--end 按钮 -->
								</div>
								<!-- / 页码 + 按钮 -->
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</form>
		<div class="clear"></div>
	</body>
</html>
									
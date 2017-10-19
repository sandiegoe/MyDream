<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<html>
	<head>
        <base href="<%=basePath%>">
		<title>梦之队 •当然果园系统| 更改商品活动</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

		<link rel="stylesheet" type="text/css" href="admin/css/style.css" />
		<script type="text/javascript" src="admin/js/jquery.min.js"></script>
	</head>

	<body>
		<form id="activityUpdate" class="activityUpdate" action="activityAction_updateActivity" method="post">
			<div class="right_content" align="center">
				<h2>更改商品活动</h2>
				<table id="input-table" style="width: 40%;">
					<tbody>
						<input name="aId" id="" class="info-textfield" type="hidden" value="${acgoods.aId}"/>
						<tr>
							<td>
								<span class="">商品名称</span>
							</td>
							<td>
								<input name="gName" id="" class="info-textfield" type="text" value="${acgoods.gName}"/>
							</td>
							<!--<td><span class="">用户名不可更改。</span></td>-->
						</tr>
						
								<input name="aGid" id="" class="info-textfield" type="hidden" value="${acgoods.aGid}"/>
			
							<!--<td><span class="">用户名不可更改。</span></td>-->
						</tr>
						
						<tr>
							<td>
								<span class="">活动价格</span>
							</td>
							<td>
								<input name="aPrice" id="" class="info-textfield" type="text" value="${acgoods.aPrice}" />
							</td>
							
						</tr>
						<tr>
							<td>
								<span class="">活动日期</span>
							</td>
							<td>
								<input name="aDate" id="" class="info-textfield" type="text"  value="${acgoods.aDate}"/>
							</td>
						
						</tr>
						<tr>
							<td>
								<span class="">图片</span>
							</td>
							<td>
								<input name="aPic" id="" class="info-textfield" type="text" value="${acgoods.aPic}" />
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
									<input class="bt_green" type="submit" value="更改商品活动" >
										<a href="activityAction_searchActivity?aDate=2017.11.11" class="bt_green" onclick="reset()">返 回</a>
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


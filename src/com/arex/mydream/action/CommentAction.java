package com.arex.mydream.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.arex.mydream.action.vo.CommentDTO;
import com.arex.mydream.comm.Page;
import com.arex.mydream.model.Comment;
import com.arex.mydream.service.CommentBiz;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 
 * @author arex
 * @date 2017年10月18日
 *
 */
@Component
@Scope(value="prototype")
public class CommentAction extends ActionSupport implements ModelDriven,ServletResponseAware,ServletRequestAware{
	private CommentDTO commentDTO = new CommentDTO();
	private CommentBiz commentBiz;
	private HttpServletResponse response;
	private HttpServletRequest request;
	Integer pagesize = 5;
	private Page page = new Page();
	
	public String searchAllComment(){
		List<Comment> listcom = commentBiz.searchComment();
		request.setAttribute("listcom", listcom);
		this.searchnameComment();
		return "comment";
	}
	
	public String searchnameComment(){
		List<Comment> listcomname = commentBiz.searchComment();
		request.setAttribute("listcomname", listcomname);
		return null;
	}
	
	public String searchComment(){
		String pageNoStr = request.getParameter("pageNo");
		String cgname = request.getParameter("cgname");
		String cdate = request.getParameter("cdate");
		Comment comm = new Comment();
		if (cdate != null && !"".equals(cdate)) {
			comm.setcDate(cdate);
		}
		if (cgname != null && !"".equals(cgname)) {
			comm.setcGname(cgname);
		}
		if (pageNoStr != null && !pageNoStr.equals("")) {
			int pageNo = Integer.valueOf(pageNoStr);
			if (pageNo > 0) {
				page.setPageNo(pageNo);
			} else {
				page.setPageNo(1);
			}
		} else {
			page.setPageNo(1);
		}
		// 记录总数
		Integer count = commentBiz.searchCommentCount(comm);
		if (count % pagesize != 0) {
			count = count / pagesize + 1;
		} else {
			count = count / pagesize;
			if (count == 0) {
				count = 1;
			}
		}
		// 当前页面显示数量
		page.setPageSize(pagesize);
		page.setPageCount(count);
		List<Comment> listcom = commentBiz.searchPage_Comment(comm, page.getPageNo(),
				page.getPageSize());
		request.setAttribute("page", page);
		request.setAttribute("listcom", listcom);
		this.searchnameComment();
		return "comment";
	}
	
	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return commentDTO;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}

	public CommentDTO getCommentDTO() {
		return commentDTO;
	}

	public void setCommentDTO(CommentDTO commentDTO) {
		this.commentDTO = commentDTO;
	}

	public CommentBiz getCommentBiz() {
		return commentBiz;
	}

	@Resource
	public void setCommentBiz(CommentBiz commentBiz) {
		this.commentBiz = commentBiz;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

}

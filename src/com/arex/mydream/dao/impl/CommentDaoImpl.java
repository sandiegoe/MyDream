package com.arex.mydream.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.jsp.jstl.sql.Result;

import org.springframework.http.HttpRequest;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.arex.mydream.dao.CommentDao;
import com.arex.mydream.model.Comment;

@Component("commentDao")
public class CommentDaoImpl implements CommentDao{
	
	private HibernateTemplate hibernateTemplate;
	/**
	 * 添加评论
	 * 
	 * @param comment
	 */
	@Override
	public void add(Comment comment) {
		hibernateTemplate.save(comment);
	}

	/**
	 * 更新评论
	 * 
	 * @param comment
	 */
	@Override
	public void update(Comment comment) {
		hibernateTemplate.update(comment);
	}

	/**
	 * 删除评论
	 * 
	 * @param comment
	 */
	@Override
	public void delete(Comment comment) {
		hibernateTemplate.delete(comment);
	}

	/**
	 * 查询评论
	 * 
	 * @return
	 */
	@Override
	public List<Comment> search() {
		List<Comment> listcom = (List<Comment>) hibernateTemplate.find("from Comment");
		return listcom;
	}

	/**
	 * 根据评论ID查询评论
	 * 
	 * @param cId
	 * @return
	 */
	@Override
	public Comment search(Integer cId) {
		List<Comment> comments = (List<Comment>) hibernateTemplate.find("from Comment c where c.cId", cId);
		Comment comment = null;
		if(comments != null && comments.size() >0){
			comment = comments.get(0);
		}
		return comment;
	}

	/**
	 * 分页查询评论
	 * 
	 * @param comm
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@Override
	public List<Comment> searchPage_Comment(Comment comm, Integer pageNo,
			Integer pageSize) {
		
		StringBuffer sql = new StringBuffer(" select * from Comment where 1=1 ");
		if (comm.getcGname() != null && !"".equals(comm.getcGname())) {
			sql.append(" and cGname like ' "+comm.getcGname()+"'");
		}
		if (comm.getcDate() != null && !"".equals(comm.getcDate())) {
			sql.append(" and cdate= ' "+comm.getcDate()+"'");
		}
		if (pageNo <= 0) {
			pageNo = 1;
		}
		if (pageNo > 0 && pageSize >= 0) {
			sql.append(" limit ");
			sql.append((pageNo - 1) * pageSize);
			sql.append(" , ");
			sql.append(pageSize);
		}
		List<Comment> listcom = (List<Comment>) hibernateTemplate.find(sql.toString());
		return listcom;
	}

	/**
	 * 查询评论总数
	 * 
	 * @return
	 */
	@Override
	public Integer searchCommentCount() {
		Integer count = 0;
		List<Comment> comments = (List<Comment>) hibernateTemplate.find("from Comment");
		count = comments.size();
		return count;
	}

	/**
	 * 根据商品名称查询评论
	 * 
	 * @param cGname
	 * @return
	 */
	@Override
	public List<Comment> searchBygName(String cGname) {
		List<Comment> listcomment = (List<Comment>) hibernateTemplate.find("from Comment c where c.cGname", cGname);
		return listcomment;
	}

	/**
	 * 查询评论总数
	 * 
	 */
	@Override
	public Integer searchCommentCount(Comment comm) {
		Integer count = 0;
		StringBuffer sql = new StringBuffer("from Comment where 1=1  ");
		if (comm.getcGname() != null && !"".equals(comm.getcGname())) {
			sql.append(" and cgname like '"+comm.getcGname()+"'");
		}
		if (comm.getcDate() != null && !"".equals(comm.getcDate())) {
			sql.append(" and cdate= '"+comm.getcDate()+"'");
		}
		List<Comment> comments = (List<Comment>) hibernateTemplate.find(sql.toString());
		count = comments.size();
		return count;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}


}

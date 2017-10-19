package com.arex.mydream.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.arex.mydream.dao.CommentDao;
import com.arex.mydream.model.Comment;
import com.arex.mydream.service.CommentBiz;

/**
 * 评论业务成的实现类
 * 
 * 
 */
@Component("commentBiz")
public class CommentBizImpl implements CommentBiz {

	private CommentDao commentDao;
	/**
	 * 增加评论
	 */
	@Override
	public void addComment(Comment comment) {
		commentDao.add(comment);

	}

	/**
	 * 删除评论
	 */
	@Override
	public void deleteComment(Comment comment) {
		commentDao.delete(comment);
	}

	/**
	 * 更新评论
	 */
	@Override
	public void updateComment(Comment comment) {
		commentDao.update(comment);
	}

	/**
	 * 查询评论
	 */
	@Override
	public List<Comment> searchComment() {
		return commentDao.search();
	}

	/**
	 * 根据评论ID查询
	 */
	@Override
	public Comment findById(Integer cId) {
		return commentDao.search(cId);
	}

	/**
	 * 分页查询评论
	 */
	@Override
	public List<Comment> searchPage_Comment(Comment comm, Integer pageNo,
			Integer pageSize) {
		List<Comment> listcom = commentDao.searchPage_Comment(comm, pageNo, pageSize);
		return listcom;
	}

	/**
	 * 根据商品名字查询评论
	 */
	@Override
	public List<Comment> searchBygName(String cGname) {
		List<Comment> listcomment = commentDao.searchBygName(cGname);
		return listcomment;
	}

	/**
	 * 查询评论总数
	 */
	@Override
	public Integer searchCommentCount() {
		Integer count = commentDao.searchCommentCount();
		return count;
	}

	@Override
	public Integer searchCommentCount(Comment comm) {
		Integer count = commentDao.searchCommentCount(comm);
		return count;
	}

	public CommentDao getCommentDao() {
		return commentDao;
	}

	@Resource
	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

}
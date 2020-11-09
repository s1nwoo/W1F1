package com.wifi.comment.service;

import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wifi.comment.dao.CommentDao;
import com.wifi.comment.model.Comment;
import com.wifi.comment.model.CommentDTO;

/* 댓글 리스트 */
@Service
public class CommentListService {
	
	private CommentDao dao;
	
	@Autowired
	private SqlSessionTemplate template;

	public List<CommentDTO> getComment(int iidx) {
		
		dao = template.getMapper(CommentDao.class);
		System.out.println("댓글리스트 service");
		
		return dao.getComment(iidx);
	}
	
	

}

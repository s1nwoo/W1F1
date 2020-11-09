package com.wifi.comment.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wifi.comment.dao.CommentDao;

/* 댓글 삭제 */
@Service
public class CommentDelService {
	
	private CommentDao dao;
	
	@Autowired
	private SqlSessionTemplate template;
	
	public int delComment(int cidx) {
		
		dao = template.getMapper(CommentDao.class);
		System.out.println("댓글 삭제 service");
		
		return dao.delComment(cidx);
	}

}

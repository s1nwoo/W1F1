package com.wifi.comment.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wifi.comment.dao.CommentDao;
import com.wifi.comment.model.Comment;

/* 댓글 수정 */
@Service
public class CommentEditService {
	
	private CommentDao dao;
	
	@Autowired
	private SqlSessionTemplate template;


	public int editComment(int cidx, String content) {

		dao = template.getMapper(CommentDao.class);
		System.out.println("댓글수정 service");
		
		int result = 0;  // 수정결과 
		int iidx = 0;    // 수정한 댓글의 iidx
		
		result = dao.editComment(cidx, content);
		
		if(result ==1) {
			
			iidx = dao.getIidxComment(cidx);
		}
		
		return iidx;
	}

}

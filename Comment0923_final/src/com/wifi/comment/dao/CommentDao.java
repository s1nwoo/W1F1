package com.wifi.comment.dao;

import java.util.List;

import com.wifi.comment.model.Comment;
import com.wifi.comment.model.CommentDTO;

public interface CommentDao {

	/* 댓글 리스트 */
	List<CommentDTO> getComment(int iidx);
	
	/* 댓글 등록 */
	int regComment(Comment commentReq);
	
	/* 댓글 수정 - 원본가져오기*/
	int getViewComment(int cidx);
	
	/* 댓글 수정 - iidx 반환 */
	int getIidxComment(int cidx);
	
	/* 댓글 수정 */
	int editComment(int cidx, String content);
	
	/* 댓글 삭제 */
	int delComment(int cidx);

	
	
}

package com.wifi.comment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wifi.comment.model.Comment;
import com.wifi.comment.model.CommentDTO;
import com.wifi.comment.service.CommentDelService;
import com.wifi.comment.service.CommentEditService;
import com.wifi.comment.service.CommentListService;
import com.wifi.comment.service.CommentRegService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/comments")
public class CommentController {

	/* 댓글 리스트 */
	@Autowired
	private CommentListService listService;

	/* 댓글 등록 */
	@Autowired
	private CommentRegService regService;

	/* 댓글 수정 */
	@Autowired
	private CommentEditService editService;

	/* 댓글 삭제 */
	@Autowired
	private CommentDelService delService;

	/* 댓글 리스트 */
	@GetMapping("/{iidx}")
	public List<CommentDTO> getComment(@PathVariable("iidx") int iidx) {

		System.out.println("댓글리스트 controller");
		return listService.getComment(iidx);
	};

	/* 댓글 등록 */
	@PostMapping
	public int regComment(Comment commentReq) {

		System.out.println("댓글등록 controller");
		return regService.regComment(commentReq);

	};

	/* 댓글 수정 */
	@PutMapping
	public int editComment(@RequestParam("cidx") int cidx, @RequestParam("content") String content) {

		System.out.println("댓글수정 controller");
		return editService.editComment(cidx, content);
	};

	/* 댓글 삭제 */
	@PutMapping("/{cidx}")
	public int delComment(@PathVariable("cidx") int cidx) {

		System.out.println("댓글삭제 controller");
		return delService.delComment(cidx);

	};
};

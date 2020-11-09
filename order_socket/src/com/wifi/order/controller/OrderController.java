package com.wifi.order.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wifi.order.alarm.service.AlarmService;
import com.wifi.order.buyer.service.MyOrderService;
import com.wifi.order.buyer.service.MyProfileService;
import com.wifi.order.buyer.service.OrderCancleService;
import com.wifi.order.buyer.service.OrderDelService;
import com.wifi.order.buyer.service.OrderHideService;
import com.wifi.order.buyer.service.OrderRegService;
import com.wifi.order.buyer.service.SellerReviewService;
import com.wifi.order.item.service.OrderService;
import com.wifi.order.model.Item_rvs;
import com.wifi.order.model.MyItemDTO;
import com.wifi.order.model.Order;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private MyProfileService profileService;

	@Autowired
	private MyOrderService myOrderService;

	@Autowired
	private OrderRegService regService;

	@Autowired
	private OrderCancleService cancleService;

	@Autowired
	private SellerReviewService rvSellerService;

	@Autowired
	private OrderHideService hideService;

	@Autowired
	private OrderDelService delOrderService;

	@Autowired
	private AlarmService alarmService;

	@Autowired
	private OrderService orderService;

//	내 프로필 관련--------------------------------------

	@GetMapping("/profile/{loginMidx}")
	// public HashMap<String,String> getProfile(HttpSession session){
	public float[] getProfile(@PathVariable("loginMidx") int midx) {


		return profileService.getProfile(midx);
	}

//	내 공구 구매현황 관련----------------------------------

	// 내 구매글
	@GetMapping("/{loginMidx}")
	public List<MyItemDTO> getMyOrder(HttpSession session, @PathVariable("loginMidx") int midx) {

		System.out.println("내 구매글 controller");
		return myOrderService.getMyOrder(midx);

	};

	// 공구 참여신청
	@PostMapping("/{loginMidx}/{iidx}")
	public int regOrder(HttpSession session, @PathVariable("loginMidx") int midx, @PathVariable("iidx") int iidx) {

		System.out.println("공구 참여신청 controller");

		return regService.regOrder(midx, iidx);
	};

//	현황별 기능 관련 ----------------------------------

	// 나의 공구구매현황[참여중][다음기회에..] - 참여취소 (글삭제)
	@DeleteMapping("/{oidx}")
	public int cancleOrder(@PathVariable("oidx") int oidx) {

		System.out.println("참여신청 취소 controller");
		Order order = orderService.getOrderInfo(oidx);

		alarmService.deleteAlarm(order.getMidx(), order.getIidx(), "buyer");

		return cancleService.cancleOrder(oidx);
	};

	// 나의 공구구매현황[구매자] - 판매자 평점등록
	@PostMapping()
	public int reviewSeller(Item_rvs rvs) {

		System.out.println("판매자 평점등록 controller");
		return rvSellerService.reviewSeller(rvs);
	};

	// 나의 공구구매현황[구매완료] - 글숨김
	@PutMapping("/{oidx}")
	public int hideOrder(@PathVariable("oidx") int oidx) {

		System.out.println("내 구매글 숨김 controller");
		return hideService.hideOrder(oidx);
	};

	// 나의 공구구매현황[구매완료] - 글삭제
	@DeleteMapping("/{oidx}/{pidx}")
	public int delOrder(@PathVariable("oidx") int oidx, @PathVariable("pidx") int pidx) {

		System.out.println("내 구매글 삭제 controller");

		Order order = orderService.getOrderInfo(oidx);
		
		alarmService.deleteAlarm(order.getMidx(), order.getIidx(), "buyer");
		
		return delOrderService.delOrder(oidx, pidx);
	};

};

package com.wifi.order.item.service;

import java.io.File;
import java.io.IOException;


import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wifi.order.dao.AlarmDao;
import com.wifi.order.dao.ItemDao;
import com.wifi.order.model.Item;
import com.wifi.order.model.ItemRegRequest;

//공구글 등록 
@Service
public class ItemRegService {

	private ItemDao dao;
	private AlarmDao alarmDao;

	@Autowired
	private SqlSessionTemplate template;

	public int regItem(HttpServletRequest request, ItemRegRequest itemRequest) {

		// iidx, regdate, photo 가 없는 Item 객체를 먼저 생성
		Item item = itemRequest.toItem();
		System.out.println("공구글 등록 service ");
		System.out.println("item :" + item.toString());
		System.out.println("request :" + request);

		dao = template.getMapper(ItemDao.class);
		alarmDao = template.getMapper(AlarmDao.class);

		// 공구 등록한 결과데이터 변수
		int result = 0;

		// 공구 등록후 등록한 공구글의 iidx
		int iidx = 0;

		// 공구 등록한 midx
		int midx = itemRequest.getMidx();

		try {

			// 사진 파일 받아오기
			MultipartFile file = itemRequest.getPhoto();

			System.out.println("itemRequest :" + itemRequest);

			// 사진이 있으면,
			if (file != null && !file.isEmpty() && file.getSize() > 0) {

				// 서버 내부의 경로
				String uri = "/upload";

				// 시스템의 실제(절대)경로
				String realPath = request.getSession().getServletContext().getRealPath(uri);

				// 저장할 이미지 파일의 새로운 이름 생성
				String newFileName = System.nanoTime() + "_" + file.getOriginalFilename();

				// 서버의 저장소에 실제 저장
				File saveFile = new File(realPath, newFileName);
				file.transferTo(saveFile);
				System.out.println("파일저장위치 : " + realPath);
				System.out.println("파일저장완료 : " + newFileName);

				// 데이터베이스에 저장할 Item 객체의 데이터를 완성한다. : 사진경로
				item.setPhoto(newFileName);

				// 사진이 없으면, 기본이미지로 Item 객체의 데이터 완성.
			} else {
				item.setPhoto("defalut.png");
			}

		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("item(사진포함객체):" + item.toString());

		// DB에 공구등록
		result = dao.regItem(item);

		// 공구등록 성공이면, iidx 반환하기
		if (result == 1) {

//			Map<String, Object> map = new HashMap<String, Object>();
//			String title = itemRequest.getTitle();
//			int midx = itemRequest.getMidx();
//			
//			System.out.println("title : "+title + ", midx: "+midx);
//			map.put("title",title);
//			map.put("midx",midx);
//			
//			iidx = dao.regItemIidx(map);

			// 제목이 중복될 수 있으므로 midx의 최근등록글로 iidx 반환하도록 sql 수정 ㅡ> 따라서 매개변수는 midx만 필요
			iidx = dao.regItemIidx(midx);

			System.out.println("공구등록서비스 ㅡ> 등록하고 iidx반환 : " + iidx);

			alarmDao.insertSellerAlarm(midx, iidx);

		} else {
			iidx = 0;
		}

		return iidx;
	};

};

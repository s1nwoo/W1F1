package com.wifi.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wifi.order.alarm.service.AlarmService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/alarm")
public class AlarmController {

	@Autowired
	private AlarmService alarmService;

	@PostMapping
	public void cancleAlarm(int iidx, int midx, String type) {

		alarmService.cancleAlarm(midx, iidx, type);
	}
	
}

package com.wifi.order.model;

import java.util.List;

public class Messenger {

	private int senderMidx;
	private List<Integer> receiverList;
	private int iidx;
	private int receiverMidx;
	private String label;

	public Messenger() {
	}

	public Messenger(int senderMidx, List<Integer> receiverList, int receiverMidx, int iidx,  String label) {
		this.senderMidx = senderMidx;
		this.receiverList = receiverList;
		this.iidx = iidx;
		this.receiverMidx = receiverMidx;
		this.label = label;
	}
	
	
	public Messenger(int senderMidx,  int receiverMidx, int iidx, String label) {
		this.senderMidx = senderMidx;
		this.receiverList = null;
		this.iidx = iidx;
		this.receiverMidx = receiverMidx;
		this.label = label;
	}

	

	public int getSenderMidx() {
		return senderMidx;
	}

	public void setSenderMidx(int senderMidx) {
		this.senderMidx = senderMidx;
	}

	public List<Integer> getReceiverList() {
		return receiverList;
	}

	public void setReceiverList(List<Integer> receiverList) {
		this.receiverList = receiverList;
	}

	public int getIidx() {
		return iidx;
	}

	public void setIidx(int iidx) {
		this.iidx = iidx;
	}

	public int getReceiverMidx() {
		return receiverMidx;
	}

	public void setReceiverMidx(int receiverMidx) {
		this.receiverMidx = receiverMidx;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}

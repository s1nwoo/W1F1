package com.wifi.client.model;

import java.util.Date;

public class PaymentApprovalResponse {
		
	
	private String aid, tid, cid, sid, 
	partner_order_id, partner_user_id, payment_method_type;
	private AmountResponse amount;
	private String item_name;
	private int quantity, tax_free_amount;
	private Date created_at, approved_at;
	
	public PaymentApprovalResponse() {
	
	}
	
	public PaymentApprovalResponse(String aid, String tid, String cid, String sid, String partner_order_id,
	String partner_user_id, String payment_method_type, AmountResponse amount, String item_name, int quantity,
	int tax_free_amount, Date created_at, Date approved_at) {
	
	this.aid = aid;
	this.tid = tid;
	this.cid = cid;
	this.sid = sid;
	this.partner_order_id = partner_order_id;
	this.partner_user_id = partner_user_id;
	this.payment_method_type = payment_method_type;
	this.amount = amount;
	this.item_name = item_name;
	this.quantity = quantity;
	this.tax_free_amount = tax_free_amount;
	this.created_at = created_at;
	this.approved_at = approved_at;
	}
	public String getAid() {
	return aid;
	}
	public void setAid(String aid) {
	this.aid = aid;
	}
	public String getTid() {
	return tid;
	}
	public void setTid(String tid) {
	this.tid = tid;
	}
	public String getCid() {
	return cid;
	}
	public void setCid(String cid) {
	this.cid = cid;
	}
	public String getSid() {
	return sid;
	}
	public void setSid(String sid) {
	this.sid = sid;
	}
	public String getPartner_order_id() {
	return partner_order_id;
	}
	public void setPartner_order_id(String partner_order_id) {
	this.partner_order_id = partner_order_id;
	}
	public String getPartner_user_id() {
	return partner_user_id;
	}
	public void setPartner_user_id(String partner_user_id) {
	this.partner_user_id = partner_user_id;
	}
	public String getPayment_method_type() {
	return payment_method_type;
	}
	public void setPayment_method_type(String payment_method_type) {
	this.payment_method_type = payment_method_type;
	}
	public AmountResponse getAmount() {
	return amount;
	}
	public void setAmount(AmountResponse amount) {
	this.amount = amount;
	}
	public String getItem_name() {
	return item_name;
	}
	public void setItem_name(String item_name) {
	this.item_name = item_name;
	}
	public int getQuantity() {
	return quantity;
	}
	public void setQuantity(int quantity) {
	this.quantity = quantity;
	}
	public int getTax_free_amount() {
	return tax_free_amount;
	}
	public void setTax_free_amount(int tax_free_amount) {
	this.tax_free_amount = tax_free_amount;
	}
	public Date getCreated_at() {
	return created_at;
	}
	public void setCreated_at(Date created_at) {
	this.created_at = created_at;
	}
	public Date getApproved_at() {
	return approved_at;
	}
	public void setApproved_at(Date approved_at) {
	this.approved_at = approved_at;
	}
}

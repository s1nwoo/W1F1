package com.wifi.client.model;

public class AmountResponse {
		
	private int total, tax_free, vat, point, discount;
	
	public AmountResponse() {
		
	}
	
	
	public AmountResponse(int total, int tax_free, int vat, int point, int discount) {
		
		this.total = total;
		this.tax_free = tax_free;
		this.vat = vat;
		this.point = point;
		this.discount = discount;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getTax_free() {
		return tax_free;
	}

	public void setTax_free(int tax_free) {
		this.tax_free = tax_free;
	}

	public int getVat() {
		return vat;
	}

	public void setVat(int vat) {
		this.vat = vat;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
}

package edu.mum.domain;

import java.math.BigDecimal;

import java.io.Serializable;

public class OrderStats implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getItemsSummary() {
		return itemsSummary;
	}

	public void setItemsSummary(String itemsSummary) {
		this.itemsSummary = itemsSummary;
	}

	private BigDecimal totalAmount;
	private String customerEmail;
	private String itemsSummary;

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof OrderStats))
			return false;

		final OrderStats item = (OrderStats) o;

		if (customerEmail != null ? !customerEmail.equals(item.customerEmail) : item.customerEmail != null)
			return false;

		if (totalAmount != null ? !totalAmount.equals(item.totalAmount) : item.totalAmount != null)
			return false;

		if (itemsSummary != null ? !itemsSummary.equals(item.itemsSummary) : item.itemsSummary != null)
			return false;

		return true;
	}

	public int hashCode() {
		int result;
		result = (totalAmount != null ? totalAmount.hashCode() : 0);
		return result;
	}
}

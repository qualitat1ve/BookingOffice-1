package model;

import java.sql.Timestamp;

public class DataForReport {
	private Timestamp datePayment;
	private String arrival;
	private long ticketCount;
	private double ticketPrice;
	private double ticketSum;
	
	
	public DataForReport() {
		super();
	
	}

	public Timestamp getDatePayment() {
		return datePayment;
	}

	public void setDatePayment(Timestamp datePayment) {
		this.datePayment = datePayment;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public long getTicketCount() {
		return ticketCount;
	}

	public void setTicketCount(long ticketCount) {
		this.ticketCount = ticketCount;
	}

	public double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public double getTicketSum() {
		return ticketSum;
	}

	public void setTicketSum(double ticketSum) {
		this.ticketSum = ticketSum;
	}

	
	
}

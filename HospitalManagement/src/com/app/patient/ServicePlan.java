package com.app.patient;

public enum ServicePlan {
	HIGH(10000), MEDIUM(5000), LOW(2000);

	private double minRegAmt;

	private ServicePlan(double amt) {
		this.minRegAmt = minRegAmt;
	}

	public double getminRegAmt() {
		return minRegAmt;
	}

	public void minRegAmt(double minRegAmt) {
		this.minRegAmt = minRegAmt;
	}

	

}

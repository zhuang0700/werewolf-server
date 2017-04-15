package com.telan.weixincenter.domain.resp;

import java.io.Serializable;

public class UserSummaryReportDO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4910167631123017262L;

	private String refDate;
	private Integer userSource;
	private Integer newUser;
	private Integer cancelUser;

	public String getRefDate() {
		return refDate;
	}

	public void setRefDate(String refDate) {
		this.refDate = refDate;
	}

	public Integer getUserSource() {
		return userSource;
	}

	public void setUserSource(Integer userSource) {
		this.userSource = userSource;
	}

	public Integer getNewUser() {
		return newUser;
	}

	public void setNewUser(Integer newUser) {
		this.newUser = newUser;
	}

	public Integer getCancelUser() {
		return cancelUser;
	}

	public void setCancelUser(Integer cancelUser) {
		this.cancelUser = cancelUser;
	}

}

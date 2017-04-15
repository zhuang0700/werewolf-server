package com.telan.weixincenter.domain.resp;

import java.io.Serializable;

public class UserCumulateReportDO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8043247492864092117L;

	private String refDate;

	private Long cumulateUser;

	public String getRefDate() {
		return refDate;
	}

	public void setRefDate(String refDate) {
		this.refDate = refDate;
	}

	public Long getCumulateUser() {
		return cumulateUser;
	}

	public void setCumulateUser(Long cumulateUser) {
		this.cumulateUser = cumulateUser;
	}

}

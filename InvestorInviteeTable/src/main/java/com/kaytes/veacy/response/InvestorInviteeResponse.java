package com.kaytes.veacy.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvestorInviteeResponse {

	private Long id;
	private String emailId;
	private String invitedBy;
	private String invitedAt;
	private boolean isDeleted;
	private String roleId;
	
}

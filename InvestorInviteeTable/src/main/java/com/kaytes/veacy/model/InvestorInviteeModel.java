/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */

package com.kaytes.veacy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The InvestorInviteeModel class is a Model class that is used as request class for API request
 * related to InvestorInvitee management in the application.
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvestorInviteeModel {
	private Long id;
	private String emailId;
	private Long invitedBy;
	private String invitedAt;
	private boolean isDeleted;
	private Long roleId;
	
	
//	private RoleModel roleModel;
	
	
	

}

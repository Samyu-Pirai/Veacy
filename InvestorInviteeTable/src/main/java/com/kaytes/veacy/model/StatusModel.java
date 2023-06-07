/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */

package com.kaytes.veacy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The StatusModel class is a Model class that is used as request class for API request
 * related to Status management in the application.
 */

@Data
public class StatusModel {
	
	
	private Long investorInviteeId;
	private String status;
	private String invitedTime;
//	private InvestorInviteeModel investorInviteeModel;
}

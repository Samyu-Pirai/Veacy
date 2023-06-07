/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */
package com.kaytes.veacy.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvestorInviteeApiResponse extends ApiReturnResponse
{
	

	private static final long serialVersionUID = 1L;

	
	
	List<InvestorInviteeResponse> investorInviteeResponseList;
	
	
	

}

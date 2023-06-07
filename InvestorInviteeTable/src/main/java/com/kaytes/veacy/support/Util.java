/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */
package com.kaytes.veacy.support;

import org.springframework.stereotype.Service;

import com.kaytes.veacy.response.ApiReturnResponse;
import com.kaytes.veacy.response.InvestorInviteeApiResponse;
import com.kaytes.veacy.response.StatusApiResponse;
@Service
//@Slf4j
public class Util {
	
	ApiReturnResponse apiReturnResponse = new ApiReturnResponse();
	public ApiReturnResponse apiResponseMessage(String message, boolean status, String code) {
		apiReturnResponse.setMessage(message);
		apiReturnResponse.setStatus(status);
		apiReturnResponse.setStatusCode(code);
		return apiReturnResponse;
	}
	
	InvestorInviteeApiResponse investorInviteeApiResponse= new InvestorInviteeApiResponse();
	public InvestorInviteeApiResponse InvestorInviteeResponseMessage(String message, boolean status, String code) {
		investorInviteeApiResponse.setMessage(message);
		investorInviteeApiResponse.setStatus(status);
		investorInviteeApiResponse.setStatusCode(code);
		return investorInviteeApiResponse;
	}
	
	StatusApiResponse statusApiResponse= new StatusApiResponse();
	public StatusApiResponse StatusResponseMessage(String message, boolean status, String code) {
		statusApiResponse.setMessage(message);
		statusApiResponse.setStatus(status);
		statusApiResponse.setStatusCode(code);
		return statusApiResponse;
	}
	

}

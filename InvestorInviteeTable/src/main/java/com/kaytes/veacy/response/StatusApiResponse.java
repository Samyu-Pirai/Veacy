/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */
package com.kaytes.veacy.response;

import java.util.List;
import com.kaytes.veacy.model.StatusModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusApiResponse extends ApiReturnResponse{
	
	private static final long serialVersionUID = 1L;

	StatusResponse statusModel;
	
//	private String messsage;
	
	List<StatusResponse> statusResponse ;


}

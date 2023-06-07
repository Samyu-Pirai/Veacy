/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */

package com.kaytes.veacy.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.kaytes.veacy.entity.InvestorInvitee;
import com.kaytes.veacy.model.StatusModel;
import com.kaytes.veacy.response.InvestorInviteeApiResponse;
import com.kaytes.veacy.response.StatusApiResponse;

import ch.qos.logback.core.status.Status;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface StatusController {
	@ApiResponses(value = {@ApiResponse(responseCode="200" , description = "Successfully Completed the Task" ,
			content = {@Content(mediaType = "application/json" ,
			schema = @Schema(implementation = Status.class))})})
	public ResponseEntity<StatusApiResponse> createStatus(@RequestBody StatusModel statusModel);
	
	@ApiResponses(value = {@ApiResponse(responseCode="200" , description = "Successfully Completed the Task" ,
			content = {@Content(mediaType = "application/json" ,
			schema = @Schema(implementation = Status.class))})})
	public StatusApiResponse getStatusDetailsById(@PathVariable Long id);
	
	@ApiResponses(value = {@ApiResponse(responseCode="200" , description = "Successfully Completed the Task" ,
			content = {@Content(mediaType = "application/json" ,
			schema = @Schema(implementation = Status.class))})})
	public StatusApiResponse updateStatus(@PathVariable Long id, @RequestBody Map<String, Object> updates);
	
	@ApiResponses(value = {@ApiResponse(responseCode="200" , description = "Successfully Completed the Task" ,
			content = {@Content(mediaType = "application/json" ,
			schema = @Schema(implementation = Status.class))})})
	public StatusApiResponse getAllStatusDetails();
	
	

	
	
	
	
}


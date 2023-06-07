/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */
package com.kaytes.veacy.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.kaytes.veacy.entity.InvestorInvitee;
import com.kaytes.veacy.model.InvestorInviteeModel;
import com.kaytes.veacy.response.ApiReturnResponse;
import com.kaytes.veacy.response.InvestorInviteeApiResponse;

/**
 * The InvestorInviteeService interface provides methods for performing CRUD operations
 * and other actions on InvestorInvitee entities.
 */
public interface InvestorInviteeService {
	
	/**
     * Retrieve a InvestorInvitee by its unique id.
     *
     * @param id the unique id of the InvestorInvitee.
     * @return an Optional object containing the InvestorInviteeModel if found, or empty if not found.
     */
	public ResponseEntity<InvestorInviteeApiResponse> getInvestorInviteeDetailsById(Long id);
	
	/**
     * Save a new InvestorInvitee or update an existing one in the data source.
     *
     * @param InvestorInviteeModel the module object to be saved or updated.
     * @return the saved or updated InvestorInviteeModel object.
     */
	
	public ResponseEntity<InvestorInviteeApiResponse> create(InvestorInviteeModel investorInviteeModel);
	
	/**
     * Update a InvestorInvitee properties given its unique id and a map of the updates.
     *
     * @param id the unique id of the InvestorInvitee.
     * @param updates a map containing the properties to be updated and their new values.
     * @return the updated InvestorInviteeModel object.
     */
	public ResponseEntity<ApiReturnResponse> update(Long id, Map<String, Object> updates);
	
	/**
     * Retrieve a InvestorInvitee by its unique emailId.
     *
     * @param emailId the unique emailId of the InvestorInvitee.
     * @return an Optional object containing the InvestorInviteeModel if found, or empty if not found.
     */
	ResponseEntity<InvestorInviteeApiResponse> getInvestorInviteeDetailsByEmailId(String emailId);
	
	/**
     * Retrieve all InvestorInviteeDetails from the data source.
     *
     * @return a response of InvestorInviteeModel objects.
     */
	
	public ResponseEntity<InvestorInviteeApiResponse> getAllInvestorInvitee();
	
}



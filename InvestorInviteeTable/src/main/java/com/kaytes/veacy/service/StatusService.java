/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */
package com.kaytes.veacy.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.kaytes.veacy.entity.Status;
import com.kaytes.veacy.model.StatusModel;
import com.kaytes.veacy.response.InvestorInviteeApiResponse;
import com.kaytes.veacy.response.StatusApiResponse;

/**
 * The StatusService interface provides methods for performing CRUD operations
 * and other actions on Status entities.
 */
public interface StatusService {
	
	/**
     * Save a new Status or update an existing one in the data source.
     *
     * @param StatusModel the module object to be saved or updated.
     * @return the saved or updated StatusModel object.
     */
	public ResponseEntity<StatusApiResponse> createStatus(StatusModel statusModel);
	
	/**
     * Update a Status properties given its unique id and a map of the updates.
     *
     * @param id the unique id of the Status.
     * @param updates a map containing the properties to be updated and their new values.
     * @return the updated StatusModel object.
     */
	
	public ResponseEntity<StatusApiResponse> update(Long id, Map<String, Object> updates);

	/**
     * Retrieve a Status by its unique id.
     *
     * @param id the unique id of the Status.
     * @return an Optional object containing the StatusModel if found, or empty if not found.
     */
	
	public ResponseEntity<StatusApiResponse> getStatusDetailsById(Long id);

	/**
     * Retrieve all StatusDetails from the data source.
     *
     * @return a response of StatusModel objects.
     */
	
	public ResponseEntity<StatusApiResponse> getAllStatusDetails();
	
	




}

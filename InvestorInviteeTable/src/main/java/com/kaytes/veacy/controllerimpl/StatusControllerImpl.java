/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */

package com.kaytes.veacy.controllerimpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kaytes.veacy.model.StatusModel;
import com.kaytes.veacy.repository.StatusEntityRepo;
import com.kaytes.veacy.response.StatusApiResponse;
import com.kaytes.veacy.service.StatusService;



@RestController
public class StatusControllerImpl {
	@Autowired
	StatusService statusService;
	
	@Autowired
	StatusEntityRepo statusEntityRepo;
	
	/**
     * Create a new Status
     *
     * @param Status the Status object to be created.
     * @return a ResponseEntity containing the created Status object.
     */
	
	@PostMapping("/createStatus")
	public ResponseEntity<StatusApiResponse> createStatus(@RequestBody StatusModel statusModel) {
		return statusService.createStatus(statusModel);
	}
	
	/**
     * Get a Status by Id.
     *
     * @param id the unique identifier of the Status.
     * @return a ResponseEntity containing the Status object if found, or not found status if not found.
     */
	
	@GetMapping("/getStatusById/{id}")
	public ResponseEntity<StatusApiResponse> getStatusDetailsById(@PathVariable Long id) {
		return statusService.getStatusDetailsById(id);
	}
	
	/**
     * Update Status properties given its unique id.
     *
     * @param id the unique id of the module.
     * @param updates a map containing the properties to be updated and their new values.
     * @return the updated Status object.
     */
	
	@PutMapping("/updateStatus/{id}")
    public ResponseEntity<StatusApiResponse> updateStatus(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
            return statusService.update(id, updates);
     }
	
	/**
     * Get all StatusDetails.
     *
     * @return a ResponseEntity containing all Status objects.
     */
	
	@GetMapping("/getAllStatus")       
    public ResponseEntity<StatusApiResponse> getAllStatusDetails()
    {
   	 return statusService.getAllStatusDetails();
    }
		
	}
    



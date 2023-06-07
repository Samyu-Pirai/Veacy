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

import com.kaytes.veacy.model.InvestorInviteeModel;
import com.kaytes.veacy.repository.InvestorInviteeRepo;
import com.kaytes.veacy.response.ApiReturnResponse;
import com.kaytes.veacy.response.InvestorInviteeApiResponse;
import com.kaytes.veacy.service.InvestorInviteeService;

@RestController
public class InvestorInviteeControllerImpl {
	@Autowired
	InvestorInviteeService investorInviteeService;
	@Autowired
	InvestorInviteeRepo investorInviteeRepo;

	/**
     * Create a new InvestorInvitee
     *
     * @param InvestorInvitee the InvestorInvitee object to be created.
     * @return a ResponseEntity containing the created InvestorInvitee object.
     */
	
	@PostMapping("/CreateInvestorInvitee")
	public ResponseEntity<InvestorInviteeApiResponse> create(@RequestBody InvestorInviteeModel investorInviteeModel) {
		return investorInviteeService.create(investorInviteeModel);
	}
	
	/**
     * Get a InvestorInvitee by Id.
     *
     * @param id the unique identifier of the InvestorInvitee.
     * @return a ResponseEntity containing the InvestorInvitee object if found, or not found status if not found.
     */
	
	
	@GetMapping("/getInvestorInviteeDetails/{id}")
	public ResponseEntity<InvestorInviteeApiResponse> getInviteeDetails(@PathVariable Long id)
	{
		return investorInviteeService.getInvestorInviteeDetailsById(id);
	}
	/**
     * Get a InvestorInvitee by emailId.
     *
     * @param emailId the unique identifier of the InvestorInvitee.
     * @return a ResponseEntity containing the InvestorInvitee object if found, or not found status if not found.
     */
	
	@GetMapping("/getInviteeDetailsByEmail/{emailId}")
	ResponseEntity<InvestorInviteeApiResponse> getInvestorInviteeDetailsByEmailId(@PathVariable String emailId){
		return investorInviteeService.getInvestorInviteeDetailsByEmailId(emailId);
	}
	/**
     * Update InvestorInvitee's properties given its unique id.
     *
     * @param id the unique id of the module.
     * @param updates a map containing the properties to be updated and their new values.
     * @return the updated InvestorInvitee object.
     */
	
	
	@PutMapping("/updateInvitee/{id}")
    public ResponseEntity<ApiReturnResponse> updateInvestorInvitee(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
     
			return investorInviteeService.update(id, updates);
             
            
	}
            
   
	
	/**
     * Get all InvestorInviteeDetails.
     *
     * @return a ResponseEntity containing all InvestorInvitee objects.
     */
	
	 @GetMapping("/getAllDetails")
	 public ResponseEntity<InvestorInviteeApiResponse> getAllInvestorInvitee()
	{
		return investorInviteeService.getAllInvestorInvitee();
	}
		
	}

	
       

	



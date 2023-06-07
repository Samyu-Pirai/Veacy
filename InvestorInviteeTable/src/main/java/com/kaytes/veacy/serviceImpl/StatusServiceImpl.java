/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */
package com.kaytes.veacy.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kaytes.veacy.entity.InvestorInvitee;
import com.kaytes.veacy.entity.Status;
import com.kaytes.veacy.model.InvestorInviteeModel;
import com.kaytes.veacy.model.StatusModel;
import com.kaytes.veacy.properties.MessageProperties;
import com.kaytes.veacy.repository.InvestorInviteeRepo;
import com.kaytes.veacy.repository.StatusEntityRepo;
import com.kaytes.veacy.response.StatusApiResponse;
import com.kaytes.veacy.response.StatusResponse;
import com.kaytes.veacy.service.StatusService;
import com.kaytes.veacy.support.Util;

import lombok.extern.slf4j.Slf4j;

/**
 * The StatusServiceImpl class provides an implementation of the StatusService
 * interface, handling CRUD operations and other actions on Status entities.
 */

@Slf4j
@Service
public class StatusServiceImpl implements StatusService {
	@Autowired
	StatusEntityRepo statusEntityRepo;
	
	@Autowired
	InvestorInviteeRepo investorInviteeRepo;
	
	@Autowired
	Util util;
	
	@Autowired
	MessageProperties messageProperties;

	/**
	 * {@inheritDoc}
	 */

	@Override
	public ResponseEntity<StatusApiResponse>  createStatus(StatusModel statusModel) {
	
		log.info("Create Status Method Initiated");
		StatusApiResponse  statusApiResponse = new StatusApiResponse();
		try {
			Status statusEntity = new Status();
			BeanUtils.copyProperties(statusModel, statusEntity);
			Optional<InvestorInvitee> investorInviteeOptional = 
					investorInviteeRepo.findById(statusModel.getInvestorInviteeId());
			if(investorInviteeOptional.isPresent()) {
				statusEntity.setInvestorInviteeEntity(investorInviteeOptional.get());
				System.out.println("hi");
				statusEntityRepo.save(statusEntity);
				log.info("Status details has been saved");

			}
			else
			{
				log.error("InvestorInviteeId Not Valid");
				statusApiResponse=util.StatusResponseMessage(messageProperties.getInvalidInput(), Boolean.FALSE, messageProperties.getErrorCode400());
				return new ResponseEntity<>(statusApiResponse,HttpStatus.BAD_REQUEST);

			}
			
			
	}
		catch(Exception e)
		{
		log.warn("Status details haven't been saved");
		statusApiResponse= util.StatusResponseMessage(messageProperties.getInvalidInput(), Boolean.FALSE, messageProperties.getErrorCode400());
		return new ResponseEntity<>(statusApiResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		statusApiResponse= util.StatusResponseMessage(messageProperties.getSuccess(), Boolean.TRUE, messageProperties.getErrorCode200());
		return new ResponseEntity<>(statusApiResponse,HttpStatus.OK);
}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public ResponseEntity<StatusApiResponse> update(Long id, Map<String, Object> updates) {
		StatusApiResponse statusApiResponse = new StatusApiResponse();
		log.info("Update Method Initiated");
        try {
        Optional<Status> statusOptional = statusEntityRepo.findById((long) id);
        if (statusOptional.isPresent()) {
            Status status = statusOptional.get();
            updates.forEach((field, value) -> {
                switch (field) {
                    case "status":
                    	status.setStatus((String) value);
                    	break;
                    case "invitedTime":
                    	status.setInvitedTime((String)value);
                    	break;
//                    case "investorInviteeEntity":
//                    	InvestorInvitee investorInvitee= new InvestorInvitee();
//                    	investorInvitee.setId((Long)value);
//                    	status.setInvestorInviteeEntity(investorInvitee);
//                    	break;
//                    
                    default:
                        throw new IllegalArgumentException("Invalid field: " + field);
                }
            });
            statusEntityRepo.save(status);
            log.debug("Updated status {} in the database", status);
            statusApiResponse=util.StatusResponseMessage(messageProperties.getSuccess(), Boolean.TRUE, messageProperties.getErrorCode200());
   

        } 
        else {
        	log.error("Status with ID {} not found in the database", id);
            throw new IllegalArgumentException("Status with ID " + id + " not found.");
        }
       }
        catch(Exception e){
        	statusApiResponse=util.StatusResponseMessage(messageProperties.getNotFound(), Boolean.FALSE, messageProperties.getInternalServerError());
    		return new ResponseEntity<>(statusApiResponse,HttpStatus.INTERNAL_SERVER_ERROR);

        }
		return new ResponseEntity<>(statusApiResponse,HttpStatus.OK);

    }
	
	/**
	 * {@inheritDoc}
	 */

	@Override
	public ResponseEntity<StatusApiResponse> getStatusDetailsById(Long id) {
		log.info("Get Investor Invitee Details Method is Initiated");
		StatusApiResponse statusApiResponse = new StatusApiResponse();
		StatusResponse statusModel = new StatusResponse();

		try {
			log.debug("Get InvestorInviteeDetails by Id");
			Optional<Status> statusOptional = statusEntityRepo.findByIdAndInvestorInviteeEntityIsDeletedFalse(id);
			if(statusOptional.isPresent()) {
				BeanUtils.copyProperties(statusOptional.get(), statusModel);
				Optional<InvestorInvitee> investorInviteeOptional = 
						investorInviteeRepo.findByIdAndIsDeletedFalse(statusOptional.get().getInvestorInviteeEntity().getId());
				
				if(!(investorInviteeOptional.isEmpty())) {
//					InvestorInviteeModel investorInviteeModel = new InvestorInviteeModel();
				    statusModel.setInvestorInviteeId(investorInviteeOptional.get().getId());
//				    investorInviteeModel.setId(((InvestorInvitee) investorInviteeOptional).getId());
//				    investorInviteeModel.setInvitedBy(((InvestorInvitee) investorInviteeOptional).getId());
//				    investorInviteeModel.setEmailId(((InvestorInvitee) investorInviteeOptional).getEmailId());
//				    investorInviteeModel.setInvitedAt(((InvestorInvitee) investorInviteeOptional).getInvitedAt());
//				    investorInviteeModel.setDeleted(investorInviteeOptional.get().get);
//				    statusModel.setInvestorInviteeModel(investorInviteeModel);
					log.debug("StatusDetails Retrieved Successfully");
					statusApiResponse = util.StatusResponseMessage(messageProperties.getSuccess(), Boolean.TRUE, messageProperties.getErrorCode200());
				    statusApiResponse.setStatusModel(statusModel);
					

				}
			}
			else {
				statusApiResponse=util.StatusResponseMessage(messageProperties.getNotFound(), Boolean.FALSE, messageProperties.getErrorCode400());
				return new ResponseEntity<>(statusApiResponse,HttpStatus.NOT_FOUND);
			}
			
		}
		catch(Exception e) {
			log.warn("Status Details not retrieved" + e.getMessage());
			statusApiResponse = util.StatusResponseMessage(messageProperties.getInvalidInput(), Boolean.FALSE, messageProperties.getErrorCode400());
			return new ResponseEntity<>(statusApiResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(statusApiResponse,HttpStatus.OK);
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public ResponseEntity<StatusApiResponse> getAllStatusDetails() {
		// TODO Auto-generated method stub
		StatusApiResponse statusApiResponse = new StatusApiResponse();
		try {
		List<StatusResponse> statusList = new ArrayList<>();
		List<Status> statusDet = statusEntityRepo.findAll();
		
		for(Status status : statusDet) {
			StatusResponse statusModel= new StatusResponse();
			BeanUtils.copyProperties(status, statusModel);
			Optional<InvestorInvitee> investorInviteeOptional1= investorInviteeRepo.findById(status.getInvestorInviteeEntity().getId());
//			InvestorInviteeModel investorInviteeModel= new InvestorInviteeModel();
//			BeanUtils.copyProperties(investorInviteeOptional1.get(), investorInviteeModel);
			statusModel.setInvestorInviteeId(investorInviteeOptional1.get().getId());
			statusList.add(statusModel);
			}
		statusApiResponse=util.StatusResponseMessage(messageProperties.getSuccess(), Boolean.TRUE, messageProperties.getErrorCode200());
		statusApiResponse.setStatusResponse(statusList);

		}
		catch(Exception e){
			statusApiResponse = util.StatusResponseMessage(messageProperties.getInvalidInput(), Boolean.TRUE, messageProperties.getErrorCode400());
			return new ResponseEntity<>(statusApiResponse,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<>(statusApiResponse,HttpStatus.OK);
	}
	
}
	


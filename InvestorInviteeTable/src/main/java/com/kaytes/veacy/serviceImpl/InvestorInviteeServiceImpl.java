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

import com.kaytes.veacy.entity.Investor;
import com.kaytes.veacy.entity.InvestorInvitee;
import com.kaytes.veacy.entity.Role;
import com.kaytes.veacy.model.InvestorInviteeModel;
import com.kaytes.veacy.model.InvestorModel;
import com.kaytes.veacy.model.RoleModel;
import com.kaytes.veacy.properties.MessageProperties;
import com.kaytes.veacy.repository.InvestorInviteeRepo;
import com.kaytes.veacy.repository.InvestorRepo;
import com.kaytes.veacy.repository.RoleRepo;
import com.kaytes.veacy.response.ApiReturnResponse;
import com.kaytes.veacy.response.InvestorInviteeApiResponse;
import com.kaytes.veacy.response.InvestorInviteeResponse;
import com.kaytes.veacy.service.InvestorInviteeService;
import com.kaytes.veacy.support.Util;

import lombok.extern.slf4j.Slf4j;

/**
 * The InvestorInviteeServiceImpl class provides an implementation of the
 * InvestorInviteeService interface, handling CRUD operations and other actions
 * on InvestorInvitee entities.
 */

@Service
@Slf4j
public class InvestorInviteeServiceImpl implements InvestorInviteeService {

	@Autowired
	InvestorRepo investorRepo;
	@Autowired
	InvestorInviteeRepo investorInviteeRepo;
	@Autowired
	RoleRepo roleRepo;
	@Autowired
	MessageProperties messageProperties;
	@Autowired
	Util util;

	/**
	 * {@inheritDoc}
	 */

	@Override
	public ResponseEntity<InvestorInviteeApiResponse> create(InvestorInviteeModel investorInviteeModel) {
		InvestorInviteeApiResponse investorInviteeApiResponse = new InvestorInviteeApiResponse();

		log.info("Create InvestorInvitee Method Initiated");

		try {
			if (investorInviteeModel.getEmailId().isEmpty() || investorInviteeModel.getInvitedBy() == 0
					|| investorInviteeModel.getInvitedAt().isEmpty()) {
				investorInviteeApiResponse.setMessage(messageProperties.getNotFound());
				investorInviteeApiResponse.setStatusCode(messageProperties.getErrorCode400());
				investorInviteeApiResponse.setStatus(Boolean.FALSE);
				return new ResponseEntity<>(investorInviteeApiResponse,HttpStatus.OK);			
				} 
			else {
				if (!(investorInviteeRepo.findByEmailId(investorInviteeModel.getEmailId()).isEmpty())){
					investorInviteeApiResponse = util.InvestorInviteeResponseMessage(
							messageProperties.getEmailAlreadyExists(), Boolean.FALSE,
							messageProperties.getInternalServerError());
					return new ResponseEntity<>(investorInviteeApiResponse,HttpStatus.OK);
				} 
				else
				{
					log.debug("Investor Invitee Details Saved");
					InvestorInvitee investorInvitee = new InvestorInvitee();
					BeanUtils.copyProperties(investorInviteeModel, investorInvitee);
					Optional<Investor> investorOptional = investorRepo.findById(investorInviteeModel.getInvitedBy());
					Optional<Role> roleOptional = roleRepo.findById(investorInviteeModel.getRoleId());
					if (investorOptional.isPresent() && (roleOptional.isPresent())) {
						Investor investor = new Investor();
						investor.setId(investorInviteeModel.getInvitedBy());
						investorInvitee.setInvitedBy(investor);
						Role role = new Role();
						role.setId(investorInviteeModel.getRoleId());
						investorInvitee.setRole(role);
						investorInviteeRepo.save(investorInvitee);
					} else {
						investorInviteeApiResponse.setMessage(messageProperties.getNotFound());
						investorInviteeApiResponse.setStatusCode(messageProperties.getErrorCode400());
						investorInviteeApiResponse.setStatus(Boolean.FALSE);
						return new ResponseEntity<>(investorInviteeApiResponse,HttpStatus.OK);

					}
				}
			}
		} 
		catch (NullPointerException e)
		{
			investorInviteeApiResponse.setMessage(messageProperties.getNotFound());
			investorInviteeApiResponse.setStatusCode(messageProperties.getErrorCode400());
			investorInviteeApiResponse.setStatus(Boolean.FALSE);
			return new ResponseEntity<>(investorInviteeApiResponse,HttpStatus.BAD_REQUEST);

		} catch (Exception e) {

			log.warn("InvestorInviteeDetails has not been saved");
			investorInviteeApiResponse = util.InvestorInviteeResponseMessage(messageProperties.getInvalidInput(),
					Boolean.FALSE, messageProperties.getInternalServerError());
			return new ResponseEntity<>(investorInviteeApiResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		investorInviteeApiResponse = util.InvestorInviteeResponseMessage(messageProperties.getSuccess(), Boolean.TRUE,
				messageProperties.getErrorCode200());
		return new ResponseEntity<>(investorInviteeApiResponse,HttpStatus.OK);
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public ResponseEntity<InvestorInviteeApiResponse> getInvestorInviteeDetailsById(Long id) {
		log.info("Get Investor Invitee Details Method is Initiated");
		InvestorInviteeApiResponse investorInviteeApiResponse = new InvestorInviteeApiResponse();
		try {
			log.debug("Get IvestorInviteeDetails by Id");
			List<InvestorInvitee> investorInviteeOptional = investorInviteeRepo.findByIdIsDeletedFalse(id);

			if (!(investorInviteeOptional.isEmpty())) {
				List<InvestorInviteeResponse> investorInviteeApiResponseList = new ArrayList<>();
				for (InvestorInvitee investorInvitee : investorInviteeOptional) {
					InvestorInviteeResponse investorInviteeResponse = new InvestorInviteeResponse();
					investorInviteeResponse.setInvitedBy(investorInvitee.getInvitedBy().getName());
					investorInviteeResponse.setRoleId(investorInvitee.getRole().getName());
					BeanUtils.copyProperties(investorInvitee, investorInviteeResponse);
					investorInviteeApiResponseList.add(investorInviteeResponse);
				}
				investorInviteeApiResponse = util.InvestorInviteeResponseMessage(messageProperties.getSuccess(), Boolean.TRUE,
						messageProperties.getErrorCode200());
				investorInviteeApiResponse.setInvestorInviteeResponseList(investorInviteeApiResponseList);

			} else {
				investorInviteeApiResponse = util.InvestorInviteeResponseMessage(messageProperties.getNotFound(),
						Boolean.FALSE, messageProperties.getErrorCode400());
			}

		} catch (Exception e) {
			log.warn("InvestorInvitee Details not retrieved");
			investorInviteeApiResponse = util.InvestorInviteeResponseMessage(messageProperties.getInvalidInput(),
					Boolean.FALSE, messageProperties.getErrorCode400());
			return new ResponseEntity<>(investorInviteeApiResponse,HttpStatus.INTERNAL_SERVER_ERROR);

		}
		investorInviteeApiResponse.setMessage("True");
		log.debug("InvestorInviteeDetails Retrieved Successfully");
		
		return new ResponseEntity<>(investorInviteeApiResponse,HttpStatus.OK);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<ApiReturnResponse> update(Long id, Map<String, Object> updates) {
		ApiReturnResponse apiReturnResponse = new ApiReturnResponse();
		log.info("InvestorInvitee Update Method Initiated");
		try {
			Optional<InvestorInvitee> investorOptional = investorInviteeRepo.findById((long) id);

			if (investorOptional.isPresent()) {
				InvestorInvitee invitee = investorOptional.get();
				updates.forEach((field, value) -> {
					switch (field) {
					case "emailId":
						invitee.setEmailId((String) value);
						break;
					case "invitedAt":
						invitee.setInvitedAt((String) value);
						break;
					case "invitedBy":
						Investor investor = new Investor();
						investor.setId((Long) value);
						invitee.setInvitedBy(investor);
						break;
					case "roleId":
						Role roleDet = new Role();
						roleDet.setId((Long) value);
						Optional<Role> roleOptional = roleRepo.findById(roleDet.getId());
						invitee.setRole(roleDet);
						break;
					default:
						throw new IllegalArgumentException("Invalid field: " + field);
					}

				});

				investorInviteeRepo.save(invitee);
				log.debug("Updated inviteeDetails {} in the database", invitee);
				apiReturnResponse = util.InvestorInviteeResponseMessage(messageProperties.getSuccess(), Boolean.TRUE,
						messageProperties.getErrorCode200());
			} else {
				log.error("Invitee with ID {} not found in the database", id);
				throw new IllegalArgumentException("InvestorInvitee with ID " + id + " not found.");
			}
		} catch (Exception e) {
			apiReturnResponse = util.apiResponseMessage(messageProperties.getInternalServerError(), Boolean.FALSE,
					messageProperties.getErrorCode500());
		}
		return new ResponseEntity<>(apiReturnResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public ResponseEntity<InvestorInviteeApiResponse> getInvestorInviteeDetailsByEmailId(String emailId) {
		// TODO Auto-generated method stub
		log.info("Get Investor Invitee Details Method is Initiated");
		InvestorInviteeApiResponse investorInviteeApiResponse = new InvestorInviteeApiResponse();
		try {
			log.debug("Get IvestorInviteeDetails by Id");
			List<InvestorInvitee> investorInviteeOptional = investorInviteeRepo.findByEmailId(emailId);

			if (!(investorInviteeOptional.isEmpty())) {
				List<InvestorInviteeResponse> investorInviteeApiResponseList = new ArrayList<>();
				for (InvestorInvitee investorInvitee : investorInviteeOptional) {
					InvestorInviteeResponse investorInviteeResponse = new InvestorInviteeResponse();
					investorInviteeResponse.setInvitedBy(investorInvitee.getInvitedBy().getName());
					investorInviteeResponse.setRoleId(investorInvitee.getRole().getName());
					BeanUtils.copyProperties(investorInvitee, investorInviteeResponse);
					investorInviteeApiResponseList.add(investorInviteeResponse);
				}
				investorInviteeApiResponse.setInvestorInviteeResponseList(investorInviteeApiResponseList);

			} else {
				investorInviteeApiResponse = util.InvestorInviteeResponseMessage(messageProperties.getNotFound(),
						Boolean.FALSE, messageProperties.getErrorCode400());
			}

		} catch (Exception e) {
			log.warn("InvestorInvitee Details not retrieved");
			investorInviteeApiResponse = util.InvestorInviteeResponseMessage(messageProperties.getInvalidInput(),
					Boolean.FALSE, messageProperties.getErrorCode400());
			
		}
		investorInviteeApiResponse.setMessage("True");
		log.debug("InvestorInviteeDetails Retrieved Successfully");
		investorInviteeApiResponse = util.InvestorInviteeResponseMessage(messageProperties.getSuccess(), Boolean.TRUE,
				messageProperties.getErrorCode200());
		return new ResponseEntity<>(investorInviteeApiResponse,HttpStatus.OK);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<InvestorInviteeApiResponse> getAllInvestorInvitee() {
		InvestorInviteeApiResponse inviteeResponse = new InvestorInviteeApiResponse();
		try {
			List<InvestorInviteeResponse> investorInviteeResponseList = new ArrayList<>();
			List<InvestorInvitee> investorInvitees = investorInviteeRepo.findAllDetails();
			for (InvestorInvitee investorInvitee : investorInvitees) {
				InvestorInviteeResponse investorInviteeResponse = new InvestorInviteeResponse();
				BeanUtils.copyProperties(investorInvitee, investorInviteeResponse);
				List<Investor> investorOptional = investorRepo
						.findByIdAndIsDeletedFalse(investorInvitee.getInvitedBy().getId());
				if (!(investorOptional.isEmpty())) {
					InvestorModel investorModel = new InvestorModel();
//					BeanUtils.copyProperties(investorOptional.get(),investorModel);
					investorInviteeResponse.setInvitedBy(investorInvitee.getInvitedBy().getName());

				} else {
					inviteeResponse = util.InvestorInviteeResponseMessage(messageProperties.getNotFound(),
							Boolean.FALSE, messageProperties.getErrorCode200());
					return new ResponseEntity<>(inviteeResponse,HttpStatus.OK);
				}
				List<Role> roleOptional = roleRepo.findByIdAndIsDeletedFalse(investorInvitee.getRole().getId());
				if (!(roleOptional.isEmpty())) {
					RoleModel roleModel = new RoleModel();
//					BeanUtils.copyProperties(roleOptional.get(), roleModel);
					investorInviteeResponse.setRoleId(investorInvitee.getRole().getName());
				}
				investorInviteeResponseList.add(investorInviteeResponse);

			}
			inviteeResponse = util.InvestorInviteeResponseMessage(messageProperties.getSuccess(), Boolean.TRUE,
					messageProperties.getErrorCode200());
			inviteeResponse.setInvestorInviteeResponseList(investorInviteeResponseList);

		} catch (Exception e) {
			inviteeResponse = util.InvestorInviteeResponseMessage(messageProperties.getInvalidInput(), Boolean.FALSE,
					messageProperties.getErrorCode400());
			return new ResponseEntity<>(inviteeResponse,HttpStatus.OK);

					}
		return new ResponseEntity<>(inviteeResponse,HttpStatus.OK);
	}

}

/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */

package com.kaytes.veacy.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

/**
 * The Status class is an Entity class that replicates the database table
 * related to Status management in the application.
 */
@Entity
@Getter
@Setter
//@SQLDelete(sql = "UPDATE status SET is_deleted = true WHERE id=?")
//@Where(clause = "is_deleted=false")
public class Status {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String status;
	
	private String invitedTime;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "INVESTOR_INVITEE_ID", referencedColumnName = "id")
	@JsonIgnore
    private InvestorInvitee investorInviteeEntity;
	

}

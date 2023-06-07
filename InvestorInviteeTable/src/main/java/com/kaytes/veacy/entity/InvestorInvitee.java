/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */

package com.kaytes.veacy.entity;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

/**
 * The InvestorInvitee class is an Entity class that replicates the database table
 * related to InvestorInvitee management in the application.
 */

@Getter
@Setter
@Entity
//@SQLDelete(sql = "UPDATE investor_invitee SET is_deleted = true WHERE id=?")
//@Where(clause = "is_deleted=false")
public class InvestorInvitee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String emailId;
	
	private String invitedAt;
	
	private Boolean isDeleted=false;
	

	@ManyToOne
    @JoinColumn(name = "invitedBy", referencedColumnName = "id")
    private Investor invitedBy;
	
	@ManyToOne
	@JoinColumn(name="roleId",referencedColumnName="id")
    private Role role;

	
	
	
	

}

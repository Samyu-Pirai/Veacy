/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */

package com.kaytes.veacy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kaytes.veacy.entity.InvestorInvitee;

public interface InvestorInviteeRepo extends JpaRepository<InvestorInvitee, Long> {

	Optional<InvestorInvitee> findByIdAndIsDeletedFalse(long id);
	
	@Query(value = "SELECT * FROM investor_invitee WHERE id = ? and is_deleted =FALSE",nativeQuery= true)
	List<InvestorInvitee> findByIdIsDeletedFalse(long id);

	List<InvestorInvitee> findByEmailId(String emailId);

	@Query(value = "SELECT * FROM investor_invitee WHERE is_deleted =FALSE",nativeQuery= true)
	List<InvestorInvitee> getInvestorInviteeDetails();

	@Query(value = "SELECT * FROM investor_invitee WHERE is_deleted =FALSE",nativeQuery= true)
	List<InvestorInvitee> findAllDetails();

	
	


	

}

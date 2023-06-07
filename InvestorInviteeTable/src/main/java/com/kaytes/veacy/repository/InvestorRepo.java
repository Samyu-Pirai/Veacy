/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */

package com.kaytes.veacy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kaytes.veacy.entity.Investor;

public interface InvestorRepo extends JpaRepository<Investor, Long> {


	List<Investor> findByIdAndIsDeletedFalse(Long id);

	

}

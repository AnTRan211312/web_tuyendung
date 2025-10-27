package com.TranAn.BackEnd_Works.repository;

import com.TranAn.BackEnd_Works.model.CompanyLogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyLogoRepository extends JpaRepository<CompanyLogo, Long> {
}

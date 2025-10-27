package com.TranAn.BackEnd_Works.service;

import com.TranAn.BackEnd_Works.dto.request.company.DefaultCompanyRequestDto;
import com.TranAn.BackEnd_Works.dto.request.user.RecruiterRequestDto;
import com.TranAn.BackEnd_Works.dto.response.company.DefaultCompanyExtendedResponseDto;
import com.TranAn.BackEnd_Works.dto.response.company.DefaultCompanyResponseDto;
import com.TranAn.BackEnd_Works.dto.response.user.RecruiterResponseDto;
import com.TranAn.BackEnd_Works.model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CompanyService {
    DefaultCompanyResponseDto saveCompany(DefaultCompanyRequestDto dto, MultipartFile logoFile, boolean isRecruiter);

    DefaultCompanyResponseDto updateCompany(DefaultCompanyRequestDto dto, Long id, MultipartFile logoFile, boolean isRecruiter);

    Page<DefaultCompanyResponseDto> findAllCompanies(Specification<Company> spec, Pageable pageable);

    Page<DefaultCompanyExtendedResponseDto> findAllCompaniesWithJobsCount(Specification<Company> spec, Pageable pageable);

    DefaultCompanyResponseDto findCompanyById(Long id);

    DefaultCompanyResponseDto findSelfCompany();

    List<RecruiterResponseDto> findAllRecruitersBySelfCompany();

    void addMemberToCompany(RecruiterRequestDto recruiterRequestDto);

    void removeMemberFromCompany(RecruiterRequestDto recruiterRequestDto);

    DefaultCompanyResponseDto deleteCompanyById(Long id);
}

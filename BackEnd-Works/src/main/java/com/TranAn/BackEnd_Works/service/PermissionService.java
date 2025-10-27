package com.TranAn.BackEnd_Works.service;

import com.TranAn.BackEnd_Works.dto.request.permission.DefaultPermissionRequestDto;
import com.TranAn.BackEnd_Works.dto.response.permission.DefaultPermissionResponseDto;
import com.TranAn.BackEnd_Works.model.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface PermissionService {

    Page<DefaultPermissionResponseDto> findAllPermission(Specification<Permission> spec, Pageable pageable);
    DefaultPermissionResponseDto savePermission(DefaultPermissionRequestDto defaultPermissionRequestDto);

    DefaultPermissionResponseDto updatePermission(Long id,DefaultPermissionRequestDto defaultPermissionRequestDto);
    DefaultPermissionResponseDto deletePermission(Long id);
}

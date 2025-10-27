package com.TranAn.BackEnd_Works.controller;

import com.TranAn.BackEnd_Works.annotation.ApiMessage;
import com.TranAn.BackEnd_Works.dto.request.permission.DefaultPermissionRequestDto;
import com.TranAn.BackEnd_Works.dto.response.PageResponseDto;
import com.TranAn.BackEnd_Works.dto.response.permission.DefaultPermissionResponseDto;
import com.TranAn.BackEnd_Works.model.Permission;
import com.TranAn.BackEnd_Works.service.PermissionService;
import com.turkraft.springfilter.boot.Filter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@Tag(name = "Permission")
@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('GET /permissions/*')")
public class PermissionController {

    private final PermissionService permissionService;

    @PostMapping
    @ApiMessage(value = "Tạo quyền hạn")
    @Operation(
            summary = "Tạo quyền hạn",
            description = "Yêu cầu quyền: <b>'GET /permissions/*</b>"
    )
    public DefaultPermissionResponseDto savePermission(
            @Valid @RequestBody DefaultPermissionRequestDto defaultPermissionRequestDto
    ) {
        return permissionService.savePermission(defaultPermissionRequestDto);
    }


    @GetMapping
    @ApiMessage("Lấy danh sách quyền hạn")
    @Operation(
            summary = "Lấy danh sách quyền hạn",
            description = "Yêu cầu quyền: <b>'GET /permissions/*</b>"
    )
    public ResponseEntity<?> findAllPermissions(
            @Filter Specification<Permission> spec,
            @PageableDefault(size = 5) Pageable pageable
    ) {
        Page<DefaultPermissionResponseDto> page = permissionService.findAllPermission(spec, pageable);

        PageResponseDto<DefaultPermissionResponseDto> res = new PageResponseDto<>(
                page.getContent(),
                pageable.getPageNumber() + 1,
                pageable.getPageSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );

        return ResponseEntity.ok(res);
    }

    @GetMapping("/all")
    @ApiMessage("Lấy toàn bộ quyền hạn (không phân trang)")
    @Operation(
            summary = "Lấy toàn bộ quyền hạn (không phân trang)",
            description = "Yêu cầu quyền: <b>'GET /permissions/*</b>"
    )
    public ResponseEntity<?> findAllPermissionsNoPaging(
            @Filter Specification<Permission> spec
    ) {
        List<DefaultPermissionResponseDto> list = permissionService
                .findAllPermission(spec, Pageable.unpaged())
                .getContent();

        return ResponseEntity.ok(list);
    }

    @PutMapping("/{id}")
    @ApiMessage(value = "Cập nhật quyền hạn")
    @Operation(
            summary = "Cập nhật quyền hạn",
            description = "Yêu cầu quyền: <b>'GET /permissions/*</b>"
    )
    public DefaultPermissionResponseDto updatePermissionById(
            @Valid @RequestBody DefaultPermissionRequestDto defaultPermissionRequestDto,
            @PathVariable Long id
    ) {
        return permissionService.updatePermission(id, defaultPermissionRequestDto);
    }

    @DeleteMapping("/{id}")
    @ApiMessage(value = "Xóa quyền hạn")
    @Operation(
            summary = "Xóa quyền hạn",
            description = "Yêu cầu quyền: <b>'GET /permissions/*</b>"
    )
    public DefaultPermissionResponseDto deletePermissionById(
            @PathVariable Long id
    ) {
        return permissionService.deletePermission(id);
    }
}


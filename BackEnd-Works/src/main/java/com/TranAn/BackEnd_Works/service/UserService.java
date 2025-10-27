package com.TranAn.BackEnd_Works.service;

import com.TranAn.BackEnd_Works.dto.request.user.SelfUserUpdatePasswordRequestDto;
import com.TranAn.BackEnd_Works.dto.request.user.SelfUserUpdateProfileRequestDto;
import com.TranAn.BackEnd_Works.dto.request.user.UserCreateRequestDto;
import com.TranAn.BackEnd_Works.dto.request.user.UserUpdateRequestDto;
import com.TranAn.BackEnd_Works.dto.response.user.DefaultUserResponseDto;
import com.TranAn.BackEnd_Works.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    DefaultUserResponseDto saveUser(UserCreateRequestDto userCreateRequestDto);

    Page<DefaultUserResponseDto> findAllUser(Specification<User> spec, Pageable pageable);

    DefaultUserResponseDto findUserById(Long id);

    DefaultUserResponseDto updateUser(UserUpdateRequestDto userUpdateRequestDto);

    DefaultUserResponseDto deleteUserById(Long id);

    User findByEmail(String email);

    DefaultUserResponseDto updateSelfUserProfile(SelfUserUpdateProfileRequestDto selfUserUpdateProfileRequestDto);

    DefaultUserResponseDto updateSelfUserPassword(SelfUserUpdatePasswordRequestDto selfUserUpdatePasswordRequestDto);

    void updateSelfUserAvatar(MultipartFile avatarFile);
}

package com.TranAn.BackEnd_Works.dto.request.user;

import com.TranAn.BackEnd_Works.model.constant.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelfUserUpdateProfileRequestDto {

    @NotBlank(message = "Tên không được để trống")
    private String name;

    @NotNull(message = "Giới tính không được để trống")
    private Gender gender;

    @NotNull(message = "Ngày sinh không được để trống")
    private LocalDate dob;

    private String address;

}


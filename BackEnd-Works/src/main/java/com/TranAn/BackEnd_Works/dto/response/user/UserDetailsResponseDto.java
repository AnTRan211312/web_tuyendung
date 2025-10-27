package com.TranAn.BackEnd_Works.dto.response.user;

import com.TranAn.BackEnd_Works.model.constant.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDetailsResponseDto {
    private Long id;
    private String name;
    private String email;
    private LocalDate dob;
    private String address;
    private Gender gender;
    private String logoUrl;
    private Instant createdAt;
    private Instant updatedAt;
}

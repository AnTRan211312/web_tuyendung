package com.TranAn.BackEnd_Works.dto.response.skill;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class DefaultSkillResponseDto {

    private Long id;
    private String name;
    private String createdAt;
    private String updatedAt;

}


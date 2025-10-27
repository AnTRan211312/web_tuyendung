package com.TranAn.BackEnd_Works.service;

import com.TranAn.BackEnd_Works.dto.request.skill.CreateSkillRequestDto;
import com.TranAn.BackEnd_Works.dto.response.skill.DefaultSkillResponseDto;
import com.TranAn.BackEnd_Works.dto.response.skill.UpdateSkillResponseDto;
import com.TranAn.BackEnd_Works.model.Skill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface SkillService {
    DefaultSkillResponseDto saveSkill(CreateSkillRequestDto createSkillRequestDto);

    Page<DefaultSkillResponseDto> findAllSkills(Specification<Skill> spec, Pageable pageable);

    DefaultSkillResponseDto findSkillById(Long id);

    DefaultSkillResponseDto updateSkillById(UpdateSkillResponseDto updateSkillResponseDto);

    DefaultSkillResponseDto deleteSkillById(Long id);
}

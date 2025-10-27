package com.TranAn.BackEnd_Works.repository;


import com.TranAn.BackEnd_Works.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends
        JpaRepository<Skill, Long>,
        JpaSpecificationExecutor<Skill> {

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);

}

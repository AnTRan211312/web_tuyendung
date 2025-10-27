package com.TranAn.BackEnd_Works.model;

import com.TranAn.BackEnd_Works.model.common.BaseEntity;
import com.TranAn.BackEnd_Works.model.constant.ResumeStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "resumes")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Resume extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String email;

    private String fileKey;

    @Enumerated(EnumType.STRING)
    private ResumeStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;

    @ManyToOne
    @JoinColumn(name = "job_id")
    @ToString.Exclude
    private Job job;

    private Long version;

    public Resume(String email, ResumeStatus status, Long version) {
        this.email = email;
        this.status = status;
        this.version = version;
    }
}


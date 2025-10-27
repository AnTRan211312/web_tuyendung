package com.TranAn.BackEnd_Works.dto.response.resume;

import com.TranAn.BackEnd_Works.model.constant.Level;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumeForDisplayResponseDto {

    private Long id;
    private String status;
    private String pdfUrl;

    private User user;
    private Job job;
    private Company company;

    private String createdAt;
    private String updatedAt;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class User {
        private Long id;
        private String email;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Job {
        private Long id;
        private String name;
        private String location;
        private List<String> skills;
        private Level level;
        private String description;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Company {
        private Long id;
        private String name;
        private String logoUrl;
    }


}

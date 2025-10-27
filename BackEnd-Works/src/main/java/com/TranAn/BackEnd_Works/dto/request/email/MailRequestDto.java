package com.TranAn.BackEnd_Works.dto.request.email;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MailRequestDto {
    private String to;
    private String subject;
    private String body;
}

package com.TranAn.BackEnd_Works.dto.request.resume;

import com.TranAn.BackEnd_Works.model.constant.ResumeStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateResumeStatusRequestDto {

    @NotNull(message = "ID người dùng không được để trống")
    private Long id;
    @NotNull(message = "Trạng thái hồ sơ không được để trống")
    private ResumeStatus status;

}


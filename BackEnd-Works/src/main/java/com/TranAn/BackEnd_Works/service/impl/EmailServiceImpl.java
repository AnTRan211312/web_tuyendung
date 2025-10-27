package com.TranAn.BackEnd_Works.service.impl;

import com.TranAn.BackEnd_Works.dto.email.JobMailDto;
import com.TranAn.BackEnd_Works.model.Job;
import com.TranAn.BackEnd_Works.model.Subscriber;
import com.TranAn.BackEnd_Works.repository.JobRepository;
import com.TranAn.BackEnd_Works.repository.SubscriberRepository;
import com.TranAn.BackEnd_Works.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    private final JobRepository jobRepository;
    private final SubscriberRepository subscriberRepository;
    @Value("${mail.from}")
    private String sender;


    @Override
    @Async
    public void sendOtpEmail(String toEmail, String otp, String userName) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(toEmail);
            helper.setSubject("Mã OTP Khôi Phục Mật Khẩu");

            String htmlContent = buildOtpEmailTemplate(otp, userName);
            helper.setText(htmlContent, true);

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Không thể gửi email: " + e.getMessage());
        }
    }

    public String buildOtpEmailTemplate(String otp, String userName) {
        return """
            <!DOCTYPE html>
            <html>
            <head>
                <style>
                    body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 20px; }
                    .container { max-width: 600px; margin: 0 auto; background-color: #ffffff; padding: 30px; border-radius: 10px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }
                    .header { text-align: center; color: #333333; }
                    .otp-box { background-color: #f0f0f0; padding: 20px; text-align: center; border-radius: 5px; margin: 20px 0; }
                    .otp-code { font-size: 32px; font-weight: bold; color: #4CAF50; letter-spacing: 5px; }
                    .info { color: #666666; line-height: 1.6; }
                    .warning { color: #ff6b6b; font-weight: bold; margin-top: 20px; }
                    .footer { text-align: center; color: #999999; font-size: 12px; margin-top: 30px; }
                </style>
            </head>
            <body>
                <div class="container">
                    <h2 class="header">🔐 Khôi Phục Mật Khẩu</h2>
                    <p class="info">Xin chào <strong>%s</strong>,</p>
                    <p class="info">Bạn đã yêu cầu khôi phục mật khẩu. Dưới đây là mã OTP của bạn:</p>
                    
                    <div class="otp-box">
                        <div class="otp-code">%s</div>
                    </div>
                    
                    <p class="info">Mã OTP này có hiệu lực trong <strong>5 phút</strong>.</p>
                    <p class="info">Vui lòng nhập mã này vào trang khôi phục mật khẩu để tiếp tục.</p>
                    
                    <p class="warning">⚠️ Nếu bạn không yêu cầu khôi phục mật khẩu, vui lòng bỏ qua email này.</p>
                    
                    <div class="footer">
                        <p>Email này được gửi tự động, vui lòng không trả lời.</p>
                        <p>&copy; 2024 BackEnd Works. All rights reserved.</p>
                    </div>
                </div>
            </body>
            </html>
            """.formatted(userName, otp);
    }
    @Override
    public void sendJobNotificationForSubscriber(Subscriber subscriber) throws MessagingException {
        List<String> skillNames = subscriber.getSkills().stream()
                .map(skill -> skill.getName())
                .toList();

        List<Job> jobs = jobRepository.findDistinctTop3BySkills_NameInOrderByCreatedAtDesc(skillNames);

        List<JobMailDto> jobMailDtos = jobs.stream()
                .map(this::mapToEmailJobInform)
                .toList();

        Context context = new Context();
        context.setVariable("jobs", jobMailDtos);
        String html = templateEngine.process("job-notification-email.html", context);

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        helper.setFrom(sender);
        helper.setTo(subscriber.getEmail());
        helper.setSubject("🔥 Cơ hội việc làm mới dành cho bạn!");
        helper.setText(html, true);

        mailSender.send(mimeMessage);
    }

    @Override
    public void sendJobNotificationManually(String email) throws MessagingException {
        Subscriber subscriber = subscriberRepository
                .findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy người dùng"));

        sendJobNotificationForSubscriber(subscriber);
    }

    @Override
    public void sendResumeStatusNotification(String recipientEmail, String jobName, String companyName, String newStatus) throws MessagingException {
        try {
            // Map status sang tiếng Việt và màu sắc tương ứng
            StatusInfo statusInfo = mapStatusToInfo(newStatus);

            // Tạo context cho Thymeleaf
            Context context = new Context();
            context.setVariable("jobName", jobName);
            context.setVariable("companyName", companyName);
            context.setVariable("statusText", statusInfo.text);
            context.setVariable("statusColor", statusInfo.color);
            context.setVariable("statusIcon", statusInfo.icon);
            context.setVariable("message", statusInfo.message);

            // Process template
            String html = templateEngine.process("resume-status-notification.html", context);

            // Tạo và gửi email
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setFrom(sender);
            helper.setTo(recipientEmail);
            helper.setSubject("📋 Cập nhật trạng thái ứng tuyển - " + jobName);
            helper.setText(html, true);

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new MessagingException("Không thể gửi email thông báo: " + e.getMessage());
        }
    }

    private StatusInfo mapStatusToInfo(String status) {
        return switch (status.toUpperCase()) {
            case "PENDING" -> new StatusInfo(
                    "Đang xem xét",
                    "#FFA500",
                    "⏳",
                    "Hồ sơ của bạn đang được xem xét bởi nhà tuyển dụng."
            );
            case "REVIEWING" -> new StatusInfo(
                    "Đang đánh giá",
                    "#2196F3",
                    "👀",
                    "Nhà tuyển dụng đang đánh giá chi tiết hồ sơ của bạn."
            );
            case "APPROVED" -> new StatusInfo(
                    "Được chấp nhận",
                    "#4CAF50",
                    "✅",
                    "Chúc mừng! Hồ sơ của bạn đã được chấp nhận. Nhà tuyển dụng sẽ liên hệ với bạn sớm."
            );
            case "REJECTED" -> new StatusInfo(
                    "Không phù hợp",
                    "#F44336",
                    "❌",
                    "Rất tiếc, lần này hồ sơ của bạn chưa phù hợp với vị trí này. Đừng nản chí, hãy tiếp tục tìm kiếm cơ hội khác!"
            );
            default -> new StatusInfo(
                    status,
                    "#666666",
                    "📌",
                    "Trạng thái hồ sơ của bạn đã được cập nhật."
            );
        };
    }

    // Inner class để lưu thông tin status
    private record StatusInfo(String text, String color, String icon, String message) {}

    private JobMailDto mapToEmailJobInform(Job job) {
        String applyUrl = "http://localhost:3000/jobs/" + job.getId();

        JobMailDto jobMailDto = new JobMailDto(job.getId(), job.getName(), job.getSalary(), applyUrl);

        if (job.getCompany() != null) {
            JobMailDto.CompanyDto companyDto =
                    new JobMailDto.CompanyDto(
                            job.getCompany().getId(),
                            job.getCompany().getName(),
                            job.getCompany().getAddress()
                    );
            jobMailDto.setCompany(companyDto);
        }

        if (job.getSkills() != null) {
            List<JobMailDto.SkillDto> skillDto = job
                    .getSkills()
                    .stream()
                    .map(x -> new JobMailDto.SkillDto(x.getId(), x.getName()))
                    .toList();
            jobMailDto.setSkills(skillDto);
        }

        return jobMailDto;
    }
}

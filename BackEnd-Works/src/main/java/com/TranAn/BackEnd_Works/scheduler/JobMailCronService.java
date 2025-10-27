package com.TranAn.BackEnd_Works.scheduler;


import com.TranAn.BackEnd_Works.model.Subscriber;
import com.TranAn.BackEnd_Works.repository.SubscriberRepository;
import com.TranAn.BackEnd_Works.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class JobMailCronService {
    private final EmailService emailService;
    private final SubscriberRepository subscriberRepository;

    @Scheduled(cron = "0 0 8 * * ?")
    public void sendJobRecomendationToAllUser(){
        List<Subscriber> subscribers = subscriberRepository.findAll();
        int sent = 0;
        int failed = 0;
        for(Subscriber subscriber : subscribers){
            try{
                emailService.sendJobNotificationForSubscriber(subscriber);
                sent++;
            }catch(Exception e){
                failed++;

            }
        }
        log.info("Đã gửi job mail cho {} users,thất bại {}",sent,failed);
    }
}

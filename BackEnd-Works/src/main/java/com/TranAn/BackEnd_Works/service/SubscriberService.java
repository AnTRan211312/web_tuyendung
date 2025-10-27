package com.TranAn.BackEnd_Works.service;

import com.TranAn.BackEnd_Works.dto.request.subscriber.DefaultSubscriberRequestDto;
import com.TranAn.BackEnd_Works.dto.response.subcriber.DefaultSubscriberResponseDto;

public interface SubscriberService {
    DefaultSubscriberResponseDto saveSelfsubcriber(DefaultSubscriberRequestDto defaultSubscriberRequestDto);

    DefaultSubscriberResponseDto findSelfsubscriber();

    DefaultSubscriberResponseDto updateSelfSubscriber(
            DefaultSubscriberRequestDto defaultSubscriberRequestDto
    );

    void deleteSelfSubscriber();
}

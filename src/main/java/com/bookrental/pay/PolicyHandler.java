package com.bookrental.pay;

import com.bookrental.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{


    @Autowired PayRepository payRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void orderRefund(@Payload BookCanceled bookCanceled){

        if(bookCanceled.isMe()){
            System.out.println("##### listener 결재취소함 : " + bookCanceled.toJson());

            Pay pay = new Pay();

            pay.setPayStatus("취소");

            payRepository.save(pay);
        }
    }

}

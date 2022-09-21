package com.formacionback.Ej12.kafka;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageKafka {

    String message;
    String key;
    Date timeStamp;
    int partition;
    String topic;
    boolean received=false;

}

package com.xxbeam.service;

import com.rabbitmq.http.client.domain.QueueInfo;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitManagementTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private RabbitAdmin rabbitAdmin;
    @Autowired
    private RabbitManagementTemplate rabbitManagementTemplate;

    public void send(String queue,String msg) {
        this.checkQueue(queue);
        System.out.println("send to "+queue+"----msg : " + msg);
        this.rabbitTemplate.convertAndSend(queue, msg);
    }

    /**
     * 确认队列是否存在
     * @param name
     */
    public void checkQueue(String name){
        Queue quene = rabbitManagementTemplate.getQueue(name);
        if(quene==null){
            rabbitAdmin.declareQueue(new Queue(name,true,false,true));
        }
    }

    /**
     * 获取当前有消费者的队列
     * @return
     */
    public List<String> getQuenes(){
        List<QueueInfo> list = rabbitManagementTemplate.getClient().getQueues();
        List<String> returnList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            QueueInfo queueInfo = list.get(i);
            if(queueInfo.getConsumerCount()>0){
                returnList.add(queueInfo.getName());
            }
        }
        return returnList;
    }
}

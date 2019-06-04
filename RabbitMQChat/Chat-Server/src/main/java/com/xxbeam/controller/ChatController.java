package com.xxbeam.controller;

import com.xxbeam.DTO.MessageDTO;
import com.xxbeam.service.ChatService;
import com.xxbeam.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    @Autowired
    ChatService chatService;

    /**
     * 给指定用户发送消息
     * @param messageDTO
     * @return
     */
    @RequestMapping("/sendMsg")
    public ResultVO sendMsg(@RequestBody MessageDTO messageDTO){
        chatService.send(messageDTO.getChatId(),messageDTO.getMsg());
        return ResultVO.ok();
    }

    /**
     * 获取当前在线用户
     * @return
     */
    @RequestMapping("/getClients")
    public ResultVO getClients(){
        return ResultVO.ok(chatService.getQuenes());
    }

}

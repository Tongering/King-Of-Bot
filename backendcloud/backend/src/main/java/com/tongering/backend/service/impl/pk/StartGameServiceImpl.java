package com.tongering.backend.service.impl.pk;

import com.tongering.backend.consumer.WebSocketServer;
import com.tongering.backend.service.pk.StartGameService;
import org.springframework.stereotype.Service;

@Service
public class StartGameServiceImpl implements StartGameService {
    @Override
    public String startGame(Integer aId, Integer bId) {
        System.out.println("Start Game: " + aId + " " + bId);
        WebSocketServer.startGame(aId, bId);
        return "start game success";
    }
}

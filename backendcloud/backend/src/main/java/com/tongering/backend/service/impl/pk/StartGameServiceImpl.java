package com.tongering.backend.service.impl.pk;

import com.tongering.backend.consumer.WebSocketServer;
import com.tongering.backend.service.pk.StartGameService;
import org.springframework.stereotype.Service;

@Service
public class StartGameServiceImpl implements StartGameService {
    @Override
    public String startGame(Integer aId, Integer aBotId, Integer bId, Integer bBotId) {
        System.out.println("Start Game: " + aId + " " + aBotId + " " + bId + " " + bBotId);
        WebSocketServer.startGame(aId, aBotId, bId, bBotId);
        return "start game success";
    }
}

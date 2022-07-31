package com.tongering.backend.service.impl.user.bot;

import com.tongering.backend.mapper.BotMapper;
import com.tongering.backend.pojo.Bot;
import com.tongering.backend.pojo.User;
import com.tongering.backend.service.impl.util.UserDetailsImpl;
import com.tongering.backend.service.user.bot.RemoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RemoveServiceImpl implements RemoveService {
    @Autowired
    private BotMapper botMapper;

    @Override
    public Map<String, String> remove(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginuser = (UserDetailsImpl) authenticationToken.getPrincipal();

        User user = loginuser.getUser();
        int bot_id = Integer.parseInt(data.get("bot_id"));
        Bot bot = botMapper.selectById(bot_id);

        Map<String, String> map = new HashMap<>();

        if (bot == null) {
            map.put("error_message", "当前Bot不存在");
            return map;
        }

        if(!bot.getUserId().equals(user.getId())){
            map.put("error_message", "当前用户无权删除该Bot");
            return map;
        }

        botMapper.deleteById(bot_id);

        map.put("error_message", "success");
        return map;
    }
}

package com.tongering.backend.controller.user.bot;

import com.tongering.backend.pojo.Bot;
import com.tongering.backend.service.user.bot.GetListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetListController {
    @Autowired
    GetListService getListService;

    @PostMapping("/user/bot/getlist/")
    public List<Bot> getlist(){
        return getListService.getList();
    }
}

package com.ld.dhouse.web.controller;

import com.ld.dhouse.service.common.model.data.Channel;
import com.ld.dhouse.service.common.model.data.Template;
import com.ld.dhouse.service.common.service.ChannelService;
import com.ld.dhouse.service.common.service.TemplateService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/channel")
public class ChannelController {
    private static final Logger log = LoggerFactory.getLogger(ChannelController.class);
    @Resource
    ChannelService channelService;
    @Resource
    TemplateService templateService;

    @RequestMapping("/{channelId}")
    public String getChannelById(@PathVariable(value="channelId") Long channelId, Model model) {
        log.debug("getChannel-begin>>>param:"+"channelId = [" + channelId + "], model = [" + model + "]");
        Channel channel = channelService.getChannelById(channelId);
        if(channel != null){
            model.addAttribute("channel",channel);
            if(channel.getTemplateId() != null) {
                Template template = templateService.getTemplateById(channel.getTemplateId());
                if (template != null && StringUtils.isNotBlank(template.getPath())) {
                    //栏目配置模板按模板显示
                    return template.getPath();
                }
            }
        }
        log.debug("getChannel-end<<<return:");
        //栏目信息异常显示500页面
        return "forward:/error/404";
    }
}

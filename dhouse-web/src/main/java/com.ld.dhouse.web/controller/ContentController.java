package com.ld.dhouse.web.controller;

import com.ld.dhouse.service.common.model.data.Channel;
import com.ld.dhouse.service.common.model.data.Content;
import com.ld.dhouse.service.common.model.data.Template;
import com.ld.dhouse.service.common.model.vo.ContentVo;
import com.ld.dhouse.service.common.service.ChannelService;
import com.ld.dhouse.service.common.service.ContentService;
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
@RequestMapping("/content")
public class ContentController {
    private static final Logger log = LoggerFactory.getLogger(ContentController.class);
    @Resource
    ContentService contentService;
    @Resource
    TemplateService templateService;
    @Resource
    ChannelService channelService;

    @RequestMapping("/{contentId}")
    public String getContentById(@PathVariable(value="contentId") Long contentId, Model model) {
        log.debug("getContent-begin>>>param:"+"contentId = [" + contentId + "], model = [" + model + "]");
        ContentVo content = contentService.getContentWithContentTextById(contentId);
        if(content != null){
            model.addAttribute("content",content);
            if(content.getChannelId() != null){
                //查询栏目
                Channel channel = channelService.getChannelById(content.getChannelId());
                if(channel != null){
                    model.addAttribute("channel",channel);
                }
            }
            if(content.getTemplateId() != null) {
                //查询模板
                Template template = templateService.getTemplateById(content.getTemplateId());
                if (template != null && StringUtils.isNotBlank(template.getPath())) {
                    //栏目配置模板按模板显示
                    return template.getPath();
                }
            }
        }
        log.debug("getContent-end<<<return:");
        //内容信息异常显示500页面
        return "redirect:/error/500";
    }
}

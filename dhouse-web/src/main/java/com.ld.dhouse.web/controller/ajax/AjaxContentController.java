package com.ld.dhouse.web.controller.ajax;

import com.ld.dhouse.service.common.model.data.Content;
import com.ld.dhouse.service.common.service.ContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController()
@RequestMapping("/ajax/content")
public class AjaxContentController {
    private static final Logger log = LoggerFactory.getLogger(AjaxContentController.class);
    @Resource
    ContentService contentService;


    @RequestMapping("/queryContentList/{channelId}")
    public List<Content> queryContentList(@PathVariable(value="channelId") Long channelId) {
        log.debug("queryContentList-begin>>>param:"+"channelId = [" + channelId + "]");
        List<Content> list = contentService.queryContentListByChannelId(channelId);
        log.debug("queryContentList-end<<<return:");
        return list;
    }

}

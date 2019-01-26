package com.ld.dhouse.web.controller.ajax;

import com.ld.dhouse.service.common.model.data.Channel;
import com.ld.dhouse.service.common.model.vo.ChannelVo;
import com.ld.dhouse.service.common.service.ChannelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController()
@RequestMapping("/ajax/channel")
public class AjaxChannelController {
    private static final Logger log = LoggerFactory.getLogger(AjaxChannelController.class);
    @Resource
    ChannelService channelService;

    @RequestMapping("/queryNav")
    public List<Channel> queryNav() {
        log.debug("queryNav-begin>>>param:");
        List<Channel> list = channelService.queryNav();
        log.debug("queryNav-end<<<return:");
        return list;
    }

    @RequestMapping("/queryChannelChildren/{channelId}")
    public List<ChannelVo> queryChannelChildren(@PathVariable(value="channelId") Long channelId) {
        log.debug("queryChannelChildren-begin>>>param:"+"channelId = [" + channelId + "]");
        List<ChannelVo> list = channelService.queryChannelChildren(channelId);
        log.debug("queryChannelChildren-end<<<return:");
        return list;
    }

}

package com.ld.dhouse.service.server.service.impl;

import com.ld.dhouse.service.common.model.data.Channel;
import com.ld.dhouse.service.common.model.vo.ChannelVo;
import com.ld.dhouse.service.common.service.ChannelService;
import com.ld.dhouse.service.server.Application;
import com.ld.dhouse.service.server.dao.ChannelDao;
import com.ld.dhouse.util.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 栏目相关的业务接口
 * 梁聃 2017/12/22 13:47
 */
@Service("channelService")
public class ChannelServiceImlp implements ChannelService {
    private static final Logger log = LoggerFactory.getLogger(ChannelServiceImlp.class);
    @Resource
    private ChannelDao channelDao;
    /**
     * 查询导航栏栏目
     * @return
     * 梁聃 2018/1/2 19:32
     */
    @Cacheable(value="query_nav")
    public List<Channel> queryNav() {
        log.debug("queryNav-begin>>>param:"+"");
        List<Channel> list = channelDao.queryNav();
        log.debug("queryNav-end<<<return:");
        return list;
    }

    /**
     * 根据Id查询栏目内容
     *
     * @param channelId
     * @return 梁聃 2018/1/2 19:33
     */
    @Cacheable(value="getChannelById" ,key="'channelId'+#channelId ")
    @Transactional
    public Channel getChannelById(Long channelId) {
        log.debug("getChannelById-begin>>>param:"+"channelId = [" + channelId + "]");
        Channel channel = channelDao.getChannelById(channelId);
        log.debug("getChannelById-end<<<return:");
        return channel;
    }

    /**
     * 根据id查询子栏目信息
     *
     * @param channelId
     * @return 梁聃 2018/1/4 0:09
     */
    @Cacheable(value="channelChildren" ,key="'childrenChannelId'+#channelId ")
    public List<ChannelVo> queryChannelChildren(Long channelId) {
        log.debug("queryChannelChildren-begin>>>param:"+"channelId = [" + channelId + "]");
        List<Channel> origList = channelDao.queryProgeny(channelId);
        log.debug("queryChannelChildren-end<<<return:");
        Map<Long,ChannelVo> map = new HashMap<Long, ChannelVo>();
        List<ChannelVo> list = new ArrayList<ChannelVo>();
        for(Channel channel:origList){
            ChannelVo channelVo = new ChannelVo();
            BeanUtil.copyProperties(channelVo,channel);
            if(channelVo.getPid().equals(channelId)){
                //list中只存储末级元素
                list.add(channelVo);
            }
            map.put(channelVo.getId(),channelVo);
        }
        //建立树形结构
        for (Channel channel:origList){
            if(!channel.getPid().equals(channelId)){
                //末级元素不处理
                ChannelVo parentChannel = map.get(channel.getPid());
                parentChannel.getChildren().add(map.get(channel.getId()));
            }

        }
        return list;
    }
}

package com.ld.dhouse.service.common.service;

import com.ld.dhouse.service.common.model.data.Channel;
import com.ld.dhouse.service.common.model.vo.ChannelVo;

import java.util.List;

/**
 * 栏目相关的业务接口
 * 梁聃 2017/12/22 13:47
 */
public interface ChannelService {
    /**
     * 查询导航栏栏目
     * @return
     * 梁聃 2018/1/2 19:32
     */
    List<Channel> queryNav();

    /**
     * 根据Id查询栏目内容
     * @param channelId
     * @return
     * 梁聃 2018/1/2 19:33
     */
    Channel getChannelById(Long channelId);

    /**
     * 根据id查询子栏目信息
     * @param channelId
     * @return
     * 梁聃 2018/1/4 0:09
     */
    List<ChannelVo> queryChannelChildren(Long channelId);
}

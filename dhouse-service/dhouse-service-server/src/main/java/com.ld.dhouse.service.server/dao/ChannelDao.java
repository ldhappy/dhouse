package com.ld.dhouse.service.server.dao;

import com.ld.dhouse.service.common.model.data.Channel;
import java.util.List;

public interface ChannelDao {
    int deleteById(Long id);

    int saveChannel(Channel record);

    Channel getChannelById(Long id);

    List<Channel> queryAll();

    int updateById(Channel record);

    /**
     * 查询导航栏（父id为0的根目录栏目）
     * @return
     * 梁聃 2018/1/3 23:01
     */
    List<Channel> queryNav();

    /**
     * 根据id查询后代栏目信息
     *
     * @param channelId
     * @return 梁聃 2018/1/4 0:09
     */
    List<Channel> queryProgeny(Long channelId);

    /**
     * 根据id查询后代栏目id信息
     * @param channelId
     * @return
     * 梁聃 2018/1/17 18:30
     */
    List<Long> queryProgenyId(Long channelId);
}
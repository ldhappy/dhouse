package com.ld.dhouse.service.server.dao;

import com.ld.dhouse.service.common.model.data.Content;
import java.util.List;

public interface ContentDao {
    int deleteById(Long id);

    int saveContent(Content record);

    Content getContentById(Long id);

    List<Content> queryAll();

    int updateById(Content record);

    /**
     * 查询当前栏目内容列表
     * @param channelId
     * @return
     */
    List<Content> queryContentListByChannelId(Long channelId);
    /**
     * 批量查询当前栏目内容列表
     * @param channelIdList
     * @return
     */
    List<Content> queryContentListByChannelIdList(List<Long> channelIdList);
}
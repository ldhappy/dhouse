package com.ld.dhouse.service.common.service;

import com.ld.dhouse.service.common.model.data.Channel;
import com.ld.dhouse.service.common.model.data.Content;
import com.ld.dhouse.service.common.model.vo.ChannelVo;
import com.ld.dhouse.service.common.model.vo.ContentVo;

import java.util.List;

/**
 * 内容相关的业务接口
 * 梁聃 2018/1/10 21:03
 */
public interface ContentService {
    /**
     * 查询当前栏目及其子孙的内容列表
     * @param channelId
     * @return
     */
    List<Content> queryContentListByChannelId(Long channelId);

    /**
     * 根据内容id获取内容信息
     * @param contentId
     * @return
     */
    Content getContentById(Long contentId);

    /**
     * 根据内容id获取内容信息，包含内容详情字段
     * @param contentId
     * @return
     */
    ContentVo getContentWithContentTextById(Long contentId);
}

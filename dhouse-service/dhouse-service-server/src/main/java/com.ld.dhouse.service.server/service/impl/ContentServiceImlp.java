package com.ld.dhouse.service.server.service.impl;

import com.ld.dhouse.service.common.model.data.Content;
import com.ld.dhouse.service.common.model.data.ContentText;
import com.ld.dhouse.service.common.model.vo.ContentVo;
import com.ld.dhouse.service.common.service.ContentService;
import com.ld.dhouse.service.server.dao.ChannelDao;
import com.ld.dhouse.service.server.dao.ContentDao;
import com.ld.dhouse.service.server.dao.ContentTextDao;
import com.ld.dhouse.util.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 栏目相关的业务接口
 * 梁聃 2017/12/22 13:47
 */
@Service("contentService")
public class ContentServiceImlp implements ContentService {
    private static final Logger log = LoggerFactory.getLogger(ContentServiceImlp.class);
    @Resource
    private ContentDao contentDao;
    @Resource
    private ContentTextDao contentTextDao;
    @Resource
    private ChannelDao channelDao;
    /**
     * 查询当前栏目及其子孙的内容列表
     *
     * @param channelId
     * @return
     */
    @Cacheable(value="contentList" ,key="'contentListChannelId'+#channelId ")
    public List<Content> queryContentListByChannelId(Long channelId) {
        log.debug("queryContentList-begin>>>param:"+"channelId = [" + channelId + "]");
        List<Long> channelIdList = channelDao.queryProgenyId(channelId);
        //由于查询结果不包含当前栏目，故在列表中增加
        channelIdList.add(channelId);
        List<Content> list = contentDao.queryContentListByChannelIdList(channelIdList);
        log.debug("queryContentList-end<<<return:");
        return list;
    }

    /**
     * 根据内容id获取内容信息
     *
     * @param contentId
     * @return
     */
    @Cacheable(value="content" ,key="'contentId'+#contentId ")
    public Content getContentById(Long contentId) {
        log.debug("getContentById-begin>>>param:"+"contentId = [" + contentId + "]");
        Content content = contentDao.getContentById(contentId);
        log.debug("getContentById-end<<<return:");
        return content;
    }

    /**
     * 根据内容id获取内容信息，包含内容详情字段
     *
     * @param contentId
     * @return
     */
    @Cacheable(value="contentWithText" ,key="'contentWithTextId'+#contentId ")
    public ContentVo getContentWithContentTextById(Long contentId) {
        log.debug("getContentWithContentTextById-begin>>>param:"+"contentId = [" + contentId + "]");
        Content content = contentDao.getContentById(contentId);
        if(content != null){
            ContentVo contentVo = new ContentVo();
            BeanUtil.copyProperties(contentVo,content);
            ContentText contentText = contentTextDao.getContentTextByContentId(contentVo.getId());
            if(contentText != null){
                contentVo.setContent(contentText.getContent());
            }
            log.debug("getContentWithContentTextById-end<<<return:");
            return contentVo;
        }
        log.debug("getContentWithContentTextById-end<<<return:error:contentVo=null");
        return null;
    }
}

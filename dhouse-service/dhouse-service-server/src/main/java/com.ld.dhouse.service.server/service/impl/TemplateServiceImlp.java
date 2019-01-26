package com.ld.dhouse.service.server.service.impl;

import com.ld.dhouse.service.common.model.data.Template;
import com.ld.dhouse.service.common.service.TemplateService;
import com.ld.dhouse.service.server.dao.TemplateDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 模板相关的业务接口
 * 梁聃 2018/1/2 22:02
 */
@Service("templateService")
public class TemplateServiceImlp implements TemplateService {
    private static final Logger log = LoggerFactory.getLogger(TemplateServiceImlp.class);
    @Resource
    private TemplateDao templateDao;
    /**
     * 根据Id查询模板内容
     *
     * @param templateId
     * @return 梁聃 2018/1/2 19:33
     */
    @Cacheable(value="getTemplateById" ,key="'templateId'+#templateId ")
    public Template getTemplateById(Long templateId) {
        log.debug("getChannelById-begin>>>param:"+"channelId = [" + templateId + "]");
        Template template = templateDao.getTemplateById(templateId);
        log.debug("getChannelById-end<<<return:");
        return template;
    }
}

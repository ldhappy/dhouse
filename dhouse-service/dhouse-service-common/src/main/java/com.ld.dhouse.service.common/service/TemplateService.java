package com.ld.dhouse.service.common.service;

import com.ld.dhouse.service.common.model.data.Template;

import java.util.List;

/**
 * 模板相关的业务接口
 * 梁聃 2018/1/2 22:00
 */
public interface TemplateService {
    /**
     * 根据Id查询模板内容
     * @param templateId
     * @return
     * 梁聃 2018/1/2 19:33
     */
    Template getTemplateById(Long templateId);
}

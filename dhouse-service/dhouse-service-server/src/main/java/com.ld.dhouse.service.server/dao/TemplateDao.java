package com.ld.dhouse.service.server.dao;

import com.ld.dhouse.service.common.model.data.Template;
import java.util.List;

public interface TemplateDao {
    int deleteById(Long id);

    int saveTemplate(Template record);

    Template getTemplateById(Long id);

    List<Template> queryAll();

    int updateById(Template record);
}
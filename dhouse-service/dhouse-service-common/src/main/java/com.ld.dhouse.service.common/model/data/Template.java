package com.ld.dhouse.service.common.model.data;

import java.io.Serializable;
import java.util.Date;

public class Template implements Serializable {
    private static final long serialVersionUID = 5184363265154499796L;
    /**
     * 对应表列: dh_template.id * 主键
     *
     Tue Jan 02 15:48:38 CST 2018
     */
    private Long id;

    /**
     * 对应表列: dh_template.name * 模板名称
     *
     Tue Jan 02 15:48:38 CST 2018
     */
    private String name;

    /**
     * 对应表列: dh_template.path * 解析模板所在路径
     *
     Tue Jan 02 15:48:38 CST 2018
     */
    private String path;

    /**
     * 对应表列: dh_template.type * 模板类型（1：栏目，2：内容）
     *
     Tue Jan 02 15:48:38 CST 2018
     */
    private Integer type;

    /**
     * 对应表列: dh_template.create_time * 创建时间
     *
     Tue Jan 02 15:48:38 CST 2018
     */
    private Date createTime;

    /**
     * 对应表列: dh_template.update_time * 修改时间
     *
     Tue Jan 02 15:48:38 CST 2018
     */
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
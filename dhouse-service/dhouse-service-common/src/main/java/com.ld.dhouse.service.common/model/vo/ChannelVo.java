package com.ld.dhouse.service.common.model.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChannelVo implements Serializable {
    private static final long serialVersionUID = -4334608129453573085L;
    /**
     * 对应表列: dh_channel.id * 主键
     *
     Tue Jan 02 15:48:37 CST 2018
     */
    private Long id;

    /**
     * 对应表列: dh_channel.template_id * 模板id
     *
     Tue Jan 02 15:48:37 CST 2018
     */
    private Long templateId;

    /**
     * 对应表列: dh_channel.name * 栏目名称
     *
     Tue Jan 02 15:48:37 CST 2018
     */
    private String name;

    /**
     * 对应表列: dh_channel.pid * 父栏目id(0表示顶层栏目，无上层)
     *
     Tue Jan 02 15:48:37 CST 2018
     */
    private Long pid;

    /**
     * 对应表列: dh_channel.path * 栏目路径（|分割，如|1|，|1|28|）
     *
     Tue Jan 02 15:48:37 CST 2018
     */
    private String path;

    /**
     * 对应表列: dh_channel.sort * 排序
     *
     Tue Jan 02 15:48:37 CST 2018
     */
    private Integer sort;

    /**
     * 对应表列: dh_channel.create_time * 创建时间
     *
     Tue Jan 02 15:48:37 CST 2018
     */
    private Date createTime;

    /**
     * 对应表列: dh_channel.update_time * 修改时间
     *
     Tue Jan 02 15:48:37 CST 2018
     */
    private Date updateTime;

    /**
     * 对应表列: dh_channel.visible * 可见性（0：不可见，1：可见）
     *
     Thu Jan 11 15:23:51 CST 2018
     */
    private Boolean visible;

    private List<ChannelVo> children = new ArrayList<ChannelVo>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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

    public List<ChannelVo> getChildren() {
        return children;
    }

    public void setChildren(List<ChannelVo> children) {
        this.children = children;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
}
package com.ld.dhouse.service.common.model.data;

import java.io.Serializable;
import java.util.Date;

public class Content implements Serializable{
    private static final long serialVersionUID = -6285248073270175155L;
    /**
     * 对应表列: dh_content.id * 主键
     *
     Thu Jan 11 15:23:51 CST 2018
     */
    private Long id;

    /**
     * 对应表列: dh_content.template_id * 模板id
     *
     Thu Jan 11 15:23:51 CST 2018
     */
    private Long templateId;

    /**
     * 对应表列: dh_content.channel_id * 栏目id
     *
     Thu Jan 11 15:23:51 CST 2018
     */
    private Long channelId;

    /**
     * 对应表列: dh_content.name * 内容标题
     *
     Thu Jan 11 15:23:51 CST 2018
     */
    private String name;

    /**
     * 对应表列: dh_content.title_image * 标题图片
     *
     Thu Jan 11 15:23:51 CST 2018
     */
    private String titleImage;

    /**
     * 对应表列: dh_content.create_time * 创建时间
     *
     Thu Jan 11 15:23:51 CST 2018
     */
    private Date createTime;

    /**
     * 对应表列: dh_content.update_time * 修改时间
     *
     Thu Jan 11 15:23:51 CST 2018
     */
    private Date updateTime;

    /**
     * 对应表列: dh_content.introduction * 简介
     *
     Thu Jan 11 15:23:51 CST 2018
     */
    private String introduction;

    /**
     * 对应表列: dh_content.visible * 可见性（0：不可见，1：可见）
     *
     Thu Jan 11 15:23:51 CST 2018
     */
    private Boolean visible;

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

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitleImage() {
        return titleImage;
    }

    public void setTitleImage(String titleImage) {
        this.titleImage = titleImage;
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

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
}
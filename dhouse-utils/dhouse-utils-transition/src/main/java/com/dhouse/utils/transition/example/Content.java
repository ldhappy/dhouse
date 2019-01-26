package com.dhouse.utils.transition.example;

import com.dhouse.utils.transition.annotation.Conversion;
import com.dhouse.utils.transition.annotation.Name;
import com.dhouse.utils.transition.annotation.Regulation;

import java.io.Serializable;
import java.util.Date;

public class Content implements Serializable{
    private static final long serialVersionUID = -9060424456462424767L;
    /**
     * 对应表列: dh_content.id * 主键
     *
     Thu Jan 11 15:23:51 CST 2018
     */
    @Name(sourceName = "id",resultName = "id")
    @Regulation(rule = "^[0-9]+$",errorInfo = "必须输入数字",required = true)
    private Long id;

    /**
     * 对应表列: dh_content.template_id * 模板id
     *
     Thu Jan 11 15:23:51 CST 2018
     */
    @Name(sourceName = "template_id",resultName = "templateId")
    @Regulation(rule = "^[0-9]+$",errorInfo = "必须输入数字",required = true)
    private Long templateId;

    /**
     * 对应表列: dh_content.channel_id * 栏目id
     *
     Thu Jan 11 15:23:51 CST 2018
     */
    @Name(sourceName = "channel_id",resultName = "channelId")
    @Regulation(rule = "^[0-9]+$",errorInfo = "必须输入数字",required = true)
    private Long channelId;

    /**
     * 对应表列: dh_content.name * 内容标题
     *
     Thu Jan 11 15:23:51 CST 2018
     */
    @Name(sourceName = "name",resultName = "name")
    @Regulation(rule = "^.{1,200}$",errorInfo = "名称最多只能输入200字",required = true)
    private String name;

    /**
     * 对应表列: dh_content.title_image * 标题图片
     *
     Thu Jan 11 15:23:51 CST 2018
     */
    @Name(sourceName = "title_image",resultName = "titleImage")
    @Regulation(rule = "^.{1,500}$",errorInfo = "标题图地址最多只能输入500字",required = true)
    private String titleImage;

    /**
     * 对应表列: dh_content.create_time * 创建时间
     *
     Thu Jan 11 15:23:51 CST 2018
     */
    @Name(sourceName = "create_time",resultName = "createTime")
    @Regulation(rule = "^.*$",required = true)
    @Conversion(convertRuleClass = DateConvert.class)
    private Date createTime;

    /**
     * 对应表列: dh_content.update_time * 修改时间
     *
     Thu Jan 11 15:23:51 CST 2018
     */
    @Name(sourceName = "update_time",resultName = "updateTime")
    @Regulation(rule = "^.*$",required = true)
    @Conversion(convertRuleClass = DateConvert.class)
    private Date updateTime;

    /**
     * 对应表列: dh_content.introduction * 简介
     *
     Thu Jan 11 15:23:51 CST 2018
     */
    @Name(sourceName = "introduction",resultName = "introduction")
    @Regulation(rule = "^.{1,500}$",errorInfo = "简介最多只能输入500字",required = true)
    private String introduction;

    /**
     * 对应表列: dh_content.visible * 可见性（0：不可见，1：可见）
     *
     Thu Jan 11 15:23:51 CST 2018
     */
    @Name(sourceName = "visible",resultName = "visible")
    @Regulation(rule = "^(0)|(1)$",errorInfo = "可见性只能输入0或1",required = true)
    @Conversion(convertRuleClass = BooleanConvert.class)
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
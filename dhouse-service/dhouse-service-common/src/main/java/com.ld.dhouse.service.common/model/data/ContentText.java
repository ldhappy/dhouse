package com.ld.dhouse.service.common.model.data;

import java.io.Serializable;
import java.util.Date;

public class ContentText implements Serializable {
    private static final long serialVersionUID = -2811191385092393930L;
    /**
     * 对应表列: dh_content_text.id * 主键
     *
     Tue Jan 02 15:48:38 CST 2018
     */
    private Long id;

    /**
     * 对应表列: dh_content_text.content_id * 内容id
     *
     Tue Jan 02 15:48:38 CST 2018
     */
    private Long contentId;

    /**
     * 对应表列: dh_content_text.create_time * 创建时间
     *
     Tue Jan 02 15:48:38 CST 2018
     */
    private Date createTime;

    /**
     * 对应表列: dh_content_text.update_time * 修改时间
     *
     Tue Jan 02 15:48:38 CST 2018
     */
    private Date updateTime;

    /**
     * 对应表列: dh_content_text.content * 内容
     *
     Tue Jan 02 15:48:38 CST 2018
     */
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
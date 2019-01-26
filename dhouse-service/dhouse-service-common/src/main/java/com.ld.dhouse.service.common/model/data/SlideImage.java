package com.ld.dhouse.service.common.model.data;

import java.io.Serializable;

public class SlideImage  implements Serializable {
    private static final long serialVersionUID = -6937307762503328297L;
    /**
     * 对应表列: dh_slide_image.id * 主键
     *
     Thu Jan 04 20:20:30 CST 2018
     */
    private Long id;

    /**
     * 对应表列: dh_slide_image.content_id * 内容id
     *
     Thu Jan 04 20:20:30 CST 2018
     */
    private Long contentId;

    /**
     * 对应表列: dh_slide_image.sort * 排序
     *
     Thu Jan 04 20:20:30 CST 2018
     */
    private Integer sort;

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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
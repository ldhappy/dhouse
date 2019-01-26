package com.ld.dhouse.service.server.dao;

import com.ld.dhouse.service.common.model.data.SlideImage;
import java.util.List;

public interface SlideImageDao {
    int deleteById(Long id);

    int saveSlideImage(SlideImage record);

    SlideImage getSlideImageById(Long id);

    List<SlideImage> queryAll();

    int updateById(SlideImage record);
}
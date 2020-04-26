package com.axyy.service;

import java.util.List;

/**
 * @date 2020/4/15--14:07
 */
public interface ImgService {
    List getImgs(int type, String foreignId);
    int insert(int type,Long foreignId,String url);
}

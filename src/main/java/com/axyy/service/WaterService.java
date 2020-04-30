package com.axyy.service;

import com.axyy.entity.Water;
import com.axyy.entity.vo.WaterAddVo;

import java.util.List;

/**
 * @date 2020/4/15--14:08
 */
public interface WaterService {
    List<Water> list(int page, int size);

    /**
     * 获取数量
     * @return
     */
    int getCount();

    int deleteById(Long id);

    List<Water> search(String status,String orderNo);
    /**
     * 设置状态
     * @return
     */
    int setNext(Long id);

    int addWaterByVo(WaterAddVo waterAddVo);

    List<Water> wxlist(int page, int size, long userid);

}

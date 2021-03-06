package com.axyy.service;

import com.axyy.entity.Clean;
import com.axyy.entity.vo.CleanAddVo;

import java.util.List;

/**
 * @date 2020/4/15--13:59
 */
public interface CleanService {
    /**
     * 创建订单
     * @param clean
     * @return
     */
    Integer order(Clean clean);

    /**
     * 获取集合
     * @param page
     * @param size
     * @return
     */
    List<Clean> list(int page, int size);

    /**
     * 获取数量
     * @return
     */
    int getCount();
    /**
     * 根据id获取
     */
    Clean getById(Long id);

    int deleteById(Long id);

    List<Clean> search(String type, String orderNo);

    int setOk(Long id);

    int addCleanByVo(CleanAddVo clean);

    List<Clean> wxlist(int page, int size, long userid);

    int setworker(Long id, String workerName);
}

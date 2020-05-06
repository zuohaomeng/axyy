package com.axyy.service;

import com.axyy.entity.Repair;

import java.util.List;

/**
 * @date 2020/4/15--14:07
 */
public interface RepairService {
    /**
     * 获取列表
     * @param page
     * @param size
     * @return
     */
    List list(int page, int size);

    /**
     * 获取数量
     * @return
     */
    int getCount();
    /**
     * 根本id获取
     * @param id
     * @return
     */
    Repair getById(Long id);


    /**
     * 添加
     * @param repair
     * @return
     */
    Long add(Repair repair);

    int deleteList(List<Repair> repairs);

    int deleteById(int id);

    List<Repair> search(String status, String repairNo);

    int setNext(Long id);

    List wxlist(int page, int size, long userid);

    int setworker(Long id, String repairName);

}

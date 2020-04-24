package com.axyy.service;

import com.axyy.entity.Notice;

import java.util.List;

/**
 * @date 2020/4/15--14:07
 */
public interface NoticeService {
    /**
     * 获取通知
     * @param page
     * @param limit
     * @return
     */
    List<Notice> list(Integer page, Integer limit);
    /**
     * 添加
     * @param notice
     * @return
     */
    int add(Notice notice);

    /**
     * 通过id获取
     * @return
     */
    Notice getById(Long id);

    int getCount();

    /**
     * 根据id批量删除
     * @param idnums
     */
    int deleteList(List idnums);

    /**
     * 根据id删除
     * @param id
     */
    int deleteById(int id);

    List<Notice> search(String type, String keyword);
}

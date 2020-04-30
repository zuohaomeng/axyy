package com.axyy.service;

import com.axyy.entity.Suggest;

import java.util.List;

/**
 * @date 2020/4/15--14:07
 */
public interface SuggestService {
    List<Suggest> list(int page, int size);

    int getCount();

    int deleteById(Long id);

    int deleteByList(List list);

    int setNext(Long id);

    List<Suggest> search(String type);

    Long add(Suggest suggest);
}

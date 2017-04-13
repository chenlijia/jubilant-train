package com.overcloud.stat.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.overcloud.stat.entity.GameDlCount;

@Repository
public interface IGameDlCountDao {

    void add(String packageName);

    void edit(String packageName);

    List<GameDlCount> queryList();
}

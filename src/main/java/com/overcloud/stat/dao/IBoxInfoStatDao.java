package com.overcloud.stat.dao;

import java.util.List;

import com.overcloud.stat.entity.BoxSwitch;

public interface IBoxInfoStatDao {

    void saveUseInfo(List<BoxSwitch> list, String tableInfo);

}

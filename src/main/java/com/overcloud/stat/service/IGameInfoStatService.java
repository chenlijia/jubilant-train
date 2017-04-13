package com.overcloud.stat.service;

import java.util.Map;

public interface IGameInfoStatService {

    void download(Map<String, String> paramMap);

    void install(Map<String, String> paramMap);

    void intent(Map<String, String> paramMap);

    void pageAccess(Map<String, String> paramMap);

    void startup(Map<String, String> paramMap);

    void uninstall(Map<String, String> paramMap);

}

package com.overcloud.stat.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

import com.overcloud.stat.common.DBUtil;
import com.overcloud.stat.common.DateTimeUtil;
import com.overcloud.stat.common.SysConfig;
import com.overcloud.stat.common.consts.Constants;
import com.overcloud.stat.common.jedis.JedisConnectionPool;
import com.overcloud.stat.dao.IBoxInfoStatDao;
import com.overcloud.stat.entity.BoxSwitch;
import com.overcloud.stat.service.IBoxInfoStatService;

@Service
public class BoxInfoStatServiceImpl implements IBoxInfoStatService {

    private final Logger logger = LoggerFactory.getLogger(BoxInfoStatServiceImpl.class);

    @Resource
    IBoxInfoStatDao boxInfoStatDao;

    @Override
    public void useInfo(Map<String, String> paramMap) {
        Jedis jedis = JedisConnectionPool.getInstance().getJedis(Constants.JEDIS_DATASOURCE);
        jedis.select(Constants.JEDIS_NUM_BOX_SWITCH);// db6
        // 缓存起来
        this.logger.info("盒子开关机缓存：" + paramMap);
        jedis.hmset(DBUtil.keyGenerator(), paramMap);

        // 判断已缓存数量
        Set<String> keys = jedis.keys("*");
        if (keys.size() > SysConfig.getInt("switch.cached.number", 200)) {
            List<BoxSwitch> list = new ArrayList<BoxSwitch>();
            Iterator<String> it = keys.iterator();
            while (it.hasNext()) {
                String key = it.next();
                Map<String, String> valueMap = jedis.hgetAll(key);
                BoxSwitch boxSwitch = new BoxSwitch();
                boxSwitch.setIp(valueMap.get("IP"));
                boxSwitch.setChannel(valueMap.get("channel"));
                boxSwitch.setMac(valueMap.get("mac"));
                boxSwitch.setTimeType(Short.parseShort(valueMap.get("timeType")));
                boxSwitch.setCountry(valueMap.get("country"));
                boxSwitch.setProvince(valueMap.get("province"));
                boxSwitch.setCity(valueMap.get("city"));
                try {
                    boxSwitch.setTime(DateTimeUtil.parseDateOrTime(DateTimeUtil.TIME_STANDARDPATTERN,
                            valueMap.get("time")));
                }
                catch (ParseException e) {
                    this.logger.error(e.getMessage(), e);
                }

                list.add(boxSwitch);
                this.logger.info("清空数据" + key);
                jedis.del(key);
            }
            // 插入mysql数据库
            String suffix = DateTimeUtil.formatDateOrTime(DateTimeUtil.YEARMONTH_PATTERN);
            this.boxInfoStatDao.saveUseInfo(list, Constants.BOX_SWITCH_TABLE.concat("_").concat(suffix));
        }
        // 返回Jedis对象
        JedisConnectionPool.getInstance().returnJedis(Constants.JEDIS_DATASOURCE, jedis);
    }

}

package com.hamonize.portal.subscribe;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscribeService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
	private SubscribeRepostory sr;

    public Subscribe save(Subscribe vo){
        java.util.Date date = new Date();
        java.sql.Date s = new java.sql.Date(date.getTime());

        vo.setInsertdt(s);
        return sr.save(vo);
    }

    public int findSubscribeInfo (String userid){
        int ret = 0;
        List<Subscribe> sub = sr.findAllByUserid(userid);

        ret = sub.size();
        return ret;
    }

}

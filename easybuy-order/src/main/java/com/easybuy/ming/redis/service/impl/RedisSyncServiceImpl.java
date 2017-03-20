package com.easybuy.ming.redis.service.impl;


import com.easybuy.ming.redis.service.JedisClient;
import com.easybuy.ming.redis.service.RedisSyncService;
import com.easybuy.ming.utils.EasybuyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 同步缓存方法
 * <p>Title: RedisSyncServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.com</p> 
 * @author	Mr_Li
 * @date	2015年8月21日下午3:56:46
 * @version 1.0
 */
@Service
public class RedisSyncServiceImpl implements RedisSyncService {

	@Autowired
	private JedisClient jedisClient;
	


	public EasybuyResult syncContent(String key) {
		jedisClient.hdel("easybuy", key);

		return EasybuyResult.ok();
	}

}

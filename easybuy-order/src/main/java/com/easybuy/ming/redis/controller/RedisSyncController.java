package com.easybuy.ming.redis.controller;

import com.easybuy.ming.rest.redis.service.RedisSyncService;
import com.easybuy.ming.utils.EasybuyResult;
import com.easybuy.ming.utils.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 同步缓存服务
 * <p>Title: RedisSyncController</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.com</p> 
 * @author	入云龙
 * @date	2015年8月21日下午4:01:29
 * @version 1.0
 */
@Controller
@RequestMapping("/sync")
public class RedisSyncController {

	@Autowired
	private RedisSyncService redisSyncService;
	
	@RequestMapping("/content/{categoryId}")
	@ResponseBody
	public EasybuyResult syncContent(@PathVariable String categoryId) {
		
		try {
			redisSyncService.syncContent(categoryId);
		} catch (Exception e) {
			e.printStackTrace();
			return EasybuyResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return EasybuyResult.ok();
	}
	
}

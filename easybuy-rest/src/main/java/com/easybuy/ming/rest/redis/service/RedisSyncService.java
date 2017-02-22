package com.easybuy.ming.rest.redis.service;

import com.easybuy.ming.utils.EasybuyResult;

public interface RedisSyncService {

	EasybuyResult syncContent(String key);
}

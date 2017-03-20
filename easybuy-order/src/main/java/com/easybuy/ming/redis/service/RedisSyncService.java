package com.easybuy.ming.redis.service;

import com.easybuy.ming.utils.EasybuyResult;

public interface RedisSyncService {

	EasybuyResult syncContent(String key);
}

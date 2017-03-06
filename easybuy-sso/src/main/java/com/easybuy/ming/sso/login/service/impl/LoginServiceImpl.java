package com.easybuy.ming.sso.login.service.impl;

import com.easybuy.ming.mapper.TbUserMapper;
import com.easybuy.ming.pojo.TbUser;
import com.easybuy.ming.pojo.TbUserExample;
import com.easybuy.ming.sso.login.service.LoginService;
import com.easybuy.ming.sso.redis.JedisClient;
import com.easybuy.ming.utils.CookieUtils;
import com.easybuy.ming.utils.EasybuyResult;
import com.easybuy.ming.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2017-03-03.
 */
@Transactional
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private TbUserMapper userMapper;
    @Autowired
    private JedisClient jedisClient;
    @Value("${REDIS_SESSION_KEY}")
    private String REDIS_SESSION_KEY;
    @Value("${REDIS_SESSION_EXPIRE}")
    private Integer REDIS_SESSION_EXPIRE;

    private static String EASYBUY_TOKEN="EASYBUY_TOKEN";

    public EasybuyResult login(String username, String password, HttpServletRequest request, HttpServletResponse response) {

        //有效性验证
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return EasybuyResult.build(400, "用户名和密码不能为空");
        }
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<TbUser> list = userMapper.selectByExample(example);
        if (list == null || list.isEmpty()) {
            return EasybuyResult.build(400, "用户名或密码错误");
        }
        //判断密码是否正确
        TbUser user = list.get(0);
        if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
            return EasybuyResult.build(400, "用户名或密码错误");
        }
        //生成token
        UUID uuid = UUID.randomUUID();
        String token = uuid.toString();
        //把用户信息写入redis
        //把用户的密码清空，为了安全。
        user.setPassword(null);
        jedisClient.set(REDIS_SESSION_KEY + ":" + token, JsonUtils.objectsToJson(user));
        jedisClient.expire(REDIS_SESSION_KEY + ":" + token, REDIS_SESSION_EXPIRE);
        //把token写入cookie
        CookieUtils.setCookie(request, response, EASYBUY_TOKEN, token);
        //返回token
        return EasybuyResult.ok(token);
    }

    public EasybuyResult getLoginInfo(String token, HttpServletRequest request, HttpServletResponse response) {
        String value = jedisClient.get(REDIS_SESSION_KEY+":"+token);
        return EasybuyResult.ok(value);
    }
}

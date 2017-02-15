package com.easybuy.ming.utils;

import com.easybuy.ming.pojo.TbDeptUser;
import com.easybuy.ming.system.service.EmployeeService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ming on 2016/11/27.
 */
public class MyRealm extends AuthorizingRealm {

       @Resource
       private EmployeeService employeeService;

    /**
     * 获取权限信息
     * @param principalCollection
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录人姓名
        String loginName = (String) principalCollection.getPrimaryPrincipal();
        List<String> permissions = new ArrayList<String>();
        //获取
        List<Map<String,Object>> permissionList=employeeService.findAuthByLoginName(loginName);
        //将权限信息封闭为AuthorizationInfo
        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();

        //遍历权限列表
        if(permissionList.size()>0){
            for(int i=0;i<permissionList.size();i++){
                String permission = (String) permissionList.get(i).get("permission");
                permissions.add(permission);
            }
        }

        // 为当前用户设置权限
        simpleAuthorInfo.addStringPermissions(permissions);

        return simpleAuthorInfo;
    }

    /**
     * 获取登录认证信息
     * @param authcToken
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String username = token.getUsername();//获取用户名
        TbDeptUser deptUser=employeeService.findUserByName(username);
        if(deptUser!=null){
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(deptUser.getLoginName(),deptUser.getPassword(),getName());
            this.setSession("currentUser", deptUser);
            this.setSession("currentUserName",deptUser.getName());
            return authcInfo;
        }else{
            return null;
        }

    }

    /**
     * 将一些数据放到ShiroSession中,以便于其它地方使用
     *
     *  比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
     */
    private void setSession(Object key, Object value) {
        Subject currentUser = SecurityUtils.getSubject();
        if (null != currentUser) {
            Session session = currentUser.getSession();
            if (null != session) {
                session.setAttribute(key, value);
            }
        }
    }
    /**
     * 清除用户的权限缓存
     */
    public void clearAuthz() {
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }
}

package cn.didano.weichat.shiro;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import cn.didano.weichat.Service.FunctionService;
import cn.didano.weichat.Service.RoleService;
import cn.didano.weichat.Service.UserService;
import cn.didano.weichat.model.Tb_function;
import cn.didano.weichat.model.Tb_role;
import cn.didano.weichat.model.Tb_user;

public class AuthRealm extends AuthorizingRealm {
	
	@Autowired
    private UserService userService;
	@Autowired
	private FunctionService functionService;
	@Autowired
	private RoleService roleService;
	
	//授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		/*User user=(User) principal.fromRealm(this.getClass().getName()).iterator().next();//获取session中的用户
        List<String> permissions=new ArrayList<>();
        Set<Role> roles = user.getRoles();
        if(roles.size()>0) {
            for(Role role : roles) {
                Set<Module> modules = role.getModules();
                if(modules.size()>0) {
                    for(Module module : modules) {
                        permissions.add(module.getMname());
                    }
                }
            }
        }
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.addStringPermissions(permissions);//将权限放入shiro中.
        return info;*/
		
		//授权信息对象(接口AuthorizationInfo的实现类SimpleAuthorizationInfo);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//根据当前登录用户查询数据库,获得其其对应的权限;
		Tb_user user = (Tb_user) principal.getPrimaryPrincipal();
		Integer userid = user.getId();
		Tb_role role = roleService.getActiveRoleByUserid(userid);
		/*Subject currentUser = SecurityUtils.getSubject();
		currentUser.hasRole(role.getName());*/
		Set<Tb_role> roles = user.getRoles();
		Set<String> permissionSet = new HashSet<String>();
        for (Tb_role tb_role : roles) {
			if (tb_role.getName() != null) {
				info.addRole(tb_role.getName());
				Set<Tb_function> functions = tb_role.getFunctions();
				if (functions != null && !functions.isEmpty()) {
					for (Tb_function tb_function : functions) {
						if (tb_function.getUrl() != null) {
							permissionSet.add(tb_function.getUrl());
						}
					}
				}
			}
		}
		info.addStringPermissions(permissionSet);
        
		return info;
	}
	
	//认证.登录
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		/*UsernamePasswordToken utoken=(UsernamePasswordToken) token;//获取用户输入的token
        String username = utoken.getUsername();
        User user = userService.findUserByUserName(username);
        return new SimpleAuthenticationInfo(user, user.getPassword(),this.getClass().getName());//放入shiro.调用CredentialsMatcher检验密码*/	
		
		UsernamePasswordToken myToken = (UsernamePasswordToken) token;
		String openid = myToken.getUsername();
		char[] password = myToken.getPassword();
		//根据用户名查询数据库中的密码,将密码交给安全管理器,由安全管理器负责比较数据库中的密码和页面中传入的密码是否一致;
		System.out.println(1);
		System.out.println(openid);
		Tb_user user = userService.selectUserByOpenid(openid);
		System.out.println(user);
		System.out.println(user.getId());
		//如果查出的用户为空,返回null;
		if (user == null) {
			return null;
		}
		//创造AuthenticationInfo的子类实现类SimpleAuthenticationInfo,传入参数由安全管理器进行密码比较;
		/*
		 * 第一个参数principal:签名对象,可以为任意对象;此处因为userAction中要将user存入到session中,认证通过后，可以在程序的任意位置获取当前放入的对象,所入传入user;
		 * 第二个参数credentials:数据库中查询出的密码;
		 * 第三个参数realmName:当前realm的类名;
		 */
		SimpleAuthenticationInfo info = 
				new SimpleAuthenticationInfo(user, user.getMobile(), this.getClass().getName());
		//返回给安全管理器;
		return info;
	}
	
}
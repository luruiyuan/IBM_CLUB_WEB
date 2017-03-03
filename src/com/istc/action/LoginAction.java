package com.istc.action;

import com.istc.Entities.Entity.Member;
import com.istc.Service.EntityService.MemberService;
import com.istc.Utilities.CookieUtils;
import com.istc.Validation.InjectionCheck;
import com.istc.Utilities.TokenUtils;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.*;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by lurui on 2017/2/16 0016.
 */
/**
 * 注意,Struts有个坑，是所有的action必须放在action, actions, struts2, struts, xwork报名下(区分大小写), 否则不认识Action
 * 会显示"404 error" 找不到对应的action
 * ServletResponseAware 接口可以自动设置 response 从而获取 http 上下文的 cookie
 * SessionAware 接口可以自动设置 session 从而获取 Session 上下文的 session
 * Session 是服务器端的会话 也是 jsp 渲染的基础，即 jsp 是在服务器端渲染的
 * React 等框架则是在前端渲染，从后端通过 ajax 获取数据
 */
@Controller("loginAction")
@Scope(scopeName = "prototype")
//以下两个注解在struts2-convention-plugin.jar中
@Namespace("")
@ParentPackage("ajax")
@SuppressWarnings("serial")
@AllowedMethods({"loginC","logout"})
public class LoginAction extends ActionSupport implements ServletResponseAware,SessionAware {
    private static final long serialVersionUID = 6473585621724667329L;

    @Resource(name = "memberService")
    private MemberService memberService;

    //配置 Session
    private Map<String, Object> session;
    private String id;
    private String password;

    //配置 token
    private HttpServletResponse response;
    private String token;
    private String newToken;
    private boolean autoLogin;//是否记忆登陆，由前端表单提交

    //自定义的 token 工具，实现在 Utilities 中
    private TokenUtils tokenUtil;
    //自定义的 Cookie 工具，实现在 Utilities
    private CookieUtils cookieUtil;

    //定义全局常量
    private final String loginKey = "member";
    private final String tokenKey = "token";

    /**
     * 初始化自定义检查工具
     */
    public LoginAction(){
        tokenUtil = TokenUtils.getInstance();
        cookieUtil = CookieUtils.getInstance();
    }

    /**
     * 登陆
     * @return 返回操作结果的字符串(名称)
     * 与Action中的results的name属性相对应
     */
    @Action(value = "login", results = {
            // 1.如果location中不加'/', 则会使用Spring默认路径前缀: /WEB-INF/content
            //    例如location = "jsp/success.jsp" 实际路径指向:web(Eclipse 中为webRoot)/WEB-INF/content/jsp/success.jsp
            // 2.如果location中加'/', 则不会增加任何前缀, 实际路径与location中的一致
            //    例如location = "/jsp/success.jsp" 实际路径指向:web(Eclipse 中为webRoot)/jsp/success.jsp
            // 3.由于有validateLogin方法, 所有重写的validate方法内置为会返回INPUT

            //全部使用json返回则有两种写法，1.只return INPUT，并留下INPUT的这种配置
            //2.@Result中不写name属性，type=json，不写返回值
            //使用json方式后，跳转请求由前端的 js 或者 jquery 发出，而不再由 struts 执行
            @Result(name = INPUT, type = "json", params = {"ignoreHierarchy", "false"})
    })
    public String login() throws Exception{
        newToken = tokenUtil.tokenCheck(this, session, token);//validate方法会被执行两次导致token变化，因此只能放在login方法中
        System.out.println("新的token："+newToken);

        Member member = (Member) session.get(loginKey);
        if(member == null){
            member = new Member();
            member.setID(id);
            member.setPassword(password);
        }
        member = memberService.get(member);
        if(member == null){
            addActionError("学号或密码错误!");
            return INPUT;
        }
        //登陆成功, 放入 session 中, 如果前端传入的参数允许存储cookie, 则操作存储 cookie
        session.put(loginKey, member);
        session.remove(tokenKey);
        System.out.print(autoLogin);
        if(autoLogin) cookieUtil.addCookieToResponse(member, response);
        return INPUT;
    }

    @Action(value = "logout", results = {@Result(name="exit",location="jsp/logout.jsp")})
    public String logout()throws Exception {
        if (session != null) session.clear();
        return "exit";
    }

    /**
     * 验证器: 验证登陆有效性
     * 理论上自动被Struts根据方法名调用
     * 验证失败时进入 addFieldError 并自动返回 INPUT，成功时为 void
     */
    public void validateLogin(){
        if (id == null || id.equals("")) {
            addFieldError("id", "请输入学号！");
        }
        if (password == null || password.equals("")) {
            addFieldError("password", "请输入密码！");
        }
        InjectionCheck injectionChecker = InjectionCheck.getInstance();
        if (!injectionChecker.isValid(id)) {
            addFieldError("id", "请不要在输入的信息中包含特殊符号（* ' ; - + / % #）");
        }
        if (!injectionChecker.isValid(password)) {
            addFieldError("password","请不要在输入的信息中包含特殊符号（* ' ; - + / % #）");
        }
    }

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response = httpServletResponse;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setAutoLogin(boolean autoLogin) {
        this.autoLogin = autoLogin;
    }

    public String Id() {
        return id;
    }

    public String Password() {
        return password;
    }
//    @JSON(serialize = false)//如果配置了此项，则在返回的 json 中不会序列化 autoLogin 对象
    public boolean getAutoLogin() {
        return autoLogin;
    }

    public String getToken() {
        return token;
    }

    public String getNewToken() {
        return newToken;
    }

    public void setNewToken(String newToken) {
        this.newToken = newToken;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
}

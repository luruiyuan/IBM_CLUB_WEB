package com.istc.action;

import com.istc.Entities.Entity.Interviewee;
import com.istc.Entities.PropertyInterface.Authority;
import com.istc.Service.EntityService.IntervieweeService;
import com.istc.Service.EntityService.PersonService;
import com.istc.Utilities.CookieUtils;
import com.istc.Utilities.Encoder;
import com.istc.Utilities.TokenUtils;
import com.istc.Validation.RegisterCheck;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import com.istc.Validation.RegisterCheck.Type;

/**
 * Created by lurui on 2017/3/3 0003.
 */

@Controller("registerAction")
@Scope("prototype")
@ParentPackage("ajax")
@AllowedMethods({"register"})
public class RegisterAction extends ActionSupport implements SessionAware, Authority{

    private static final long serialVersionUID = 187387589387L;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static final String loginKey = "member";

    @Resource(name = "personService")
    private PersonService personService;
    @Resource(name = "intervieweeService")
    private IntervieweeService intervieweeService;

    //表单提交的属性
    private String id;
    private String birthday;
    private String name;
    private String password;
    private String repassword;
    private String QQ;
    private String phoneNumber;
    private boolean gender;
    protected String[] description;

    private String token;

    //会话
    private Map<String, Object> session;

    // 工具
    private TokenUtils tokenUtil;
    private CookieUtils cookieUtil;
    private RegisterCheck registerUtil;
    private Encoder encoder;

    public RegisterAction(){
        tokenUtil = TokenUtils.getInstance();
        cookieUtil = CookieUtils.getInstance();
        registerUtil = RegisterCheck.getInstance();
        encoder = Encoder.getInstance();
    }

    @Action(value = "register", results = {@Result(name = INPUT, type = "json", params = {"IgnoreHierarchy", "false"})})
    public String register(){
        System.out.println("进入 register");
        token = tokenUtil.tokenCheck(this, session, token);
        Interviewee interviewee = new Interviewee(id, false);
        interviewee.setPassword(encoder.encodeSHA512(password.getBytes()));
        interviewee.setName(name);
        interviewee.setQQ(QQ);
        interviewee.setPhoneNumber(phoneNumber);
        interviewee.setGender(gender);
        interviewee.setAuthority(intervieweeeAuthority);
        interviewee.setDescription(this.parseStringArrayToString(description));
        Calendar birth = Calendar.getInstance();
        try {
            birth.setTime(sdf.parse(birthday));
        } catch (ParseException e) {
        }
        interviewee.setBirthday(birth);//设置年龄
        System.out.println("即将存入数据库");

        //存入数据库
        intervieweeService.add(interviewee);
        //加入 session，自动成为登陆状态
        session.put(loginKey, interviewee);
        return INPUT;
    }

    public void validateRegister(){
        // id 检查
        if (id == null || id.equals(""))addFieldError("id", "请输入您的学号！");
        if(!registerUtil.isValid(Type.ID, id))addFieldError("id", "您的学号输入有误，请检查并重新输入!");
        if(personService.exist(id))addFieldError("id", "您的学号已经被注册过! 请登录或尝试找回密码!");
        // 密码检查
        if(password == null || password.equals(""))addFieldError("password", "请输入您的密码!");
        if(!registerUtil.isValid(Type.PASSWORD, password))addFieldError("password", "密码中只允许使用数字、字母和下划线。长度不小于6位，不大于30位!");
        if(password != null && !password.equals(repassword))addFieldError("repassword", "两次输入的密码不一致!");
        // 姓名检查
        if(name == null || name.equals(""))addFieldError("name", "请输入您的姓名!");
        if(!registerUtil.isValid(Type.NAME, name))addFieldError("name", "请输入正确的姓名信息!");
        // 号码检查
        if(phoneNumber == null || phoneNumber.equals(""))addFieldError("phoneNumber", "请告诉我们您的手机号以方便联系您!");
        if(!registerUtil.isValid(Type.PHONE_NUMBER, phoneNumber))addFieldError("phoneNumber", "请输入有效的手机号码!");
        // QQ 号检查
        if(QQ == null || QQ.equals(""))addFieldError("QQ", "请告诉我们您的QQ号以方便日后您与社团其他成员的互动!");
        if(!registerUtil.isValid(Type.QQ, QQ))addFieldError("QQ", "您的QQ号输入有误，请检查并重新输入!");
        // 生日检查
        if(birthday == null || birthday.equals(""))addFieldError("birthday", "请输入您的生日以便社团成员为您庆祝生日!");
        if(!registerUtil.isValid(Type.BIRTHDAY, birthday))addFieldError("birthday", "您输入的生日已经超越极限啦!您是来逗逼的吧!");
        if(description == null || "".equals(description))addFieldError("description", "请介绍一下自己来让大家认识你哦~");
    }

    private String parseStringArrayToString(String... str){
        if(str.length == 0)return null;
        if(str.length == 1)return new String(str[0]);
        StringBuilder strb = new StringBuilder();
        for(String s: str)strb.append(s);
        return strb.toString();
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean setMale() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        session = map;
    }

    public void setDescription(String[] description) {
        this.description = description;
    }
}

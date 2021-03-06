package com.istc.action;

import java.util.*;

import com.istc.Entities.Entity.Interviewee;
import com.istc.Service.EntityService.IntervieweeService;
import com.istc.Utilities.TokenUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 面试模块
 */
@Controller("interviewAction")
@Scope(scopeName = "prototype")
@AllowedMethods({"intervieweeGet", "intervieweeCheck"})
@ParentPackage("ajax")
//@Result(name="input",location="interview.jsp")
public class InterviewAction extends ActionSupport implements SessionAware{
	
	private static final long serialVersionUID = 12343251L;
	private static final String tokenKey = "token";

	@Resource(name = "intervieweeService")
    private IntervieweeService intervieweeService;

	private HttpServletRequest request;

	private String ids;
	private Map<String, Object> session;

	private Interviewee[] restInterviewees;
	private String token;

	private TokenUtils tokenUtil;

	/**
	 * 用于获得用户提交的面试结果信息，并对数据库进行相应的处理
	 */
	@Action(value="intervieweeCheck", results = {@Result(name = INPUT, type = "json", params = {"ignoreHierarchy","false"})})
	public String intervieweeCheck() throws Exception {

		request = ServletActionContext.getRequest();
		System.out.println(request.getParameter("ids"));

		tokenUtil = TokenUtils.getInstance();
		if (tokenUtil.isResubmit(session, token)) {
			token = tokenUtil.tokenCheck(this, session, token);
			return INPUT;
		}
		System.out.println("token: " + token);
		System.out.println("ids 是：" + ids);
//		String[] passIDArray = this.str2strArray(ids, "/");
//		for (String s : passIDArray) System.out.println("传入的id：" + s);
		//将数据库中面试通过的人删除并加入成员列表
		intervieweeService.setIntervieweesToMembers(str2strArray(ids, "/"));
		//从数据库中重新获取剩余对象的List
		restInterviewees = intervieweeService.getRestInterviewees();
		if (restInterviewees.length <= 0) addActionMessage("没有需要面试人员了哦~");
//			addFieldError("getIntervieweeError", "获取面试人员信息失败！");
		return INPUT;
	}

	private String[] str2strArray(String s, String split){
		if(s == null)return null;
		return s.split(split);
	}
	
	/**
	 * 从数据库获取面试人员列表
	 * 最后返回一个能用json解析的对象数组
	 */
	@Action(value="intervieweeGet",results = {@Result(name = INPUT, type = "json", params = {"IgnoreHierarchy","false"})})
	public String intervieweeGet() throws Exception{
		restInterviewees = intervieweeService.getRestInterviewees();
		if(restInterviewees.length <= 0) addActionMessage("没有需要面试人员了哦~");
		return INPUT;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		System.out.println("进入set");
		System.out.println("set的值为: "+ ids);
		this.ids = ids;
	}

	public Interviewee[] getRestInterviewees() {
		return restInterviewees;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session=arg0;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}

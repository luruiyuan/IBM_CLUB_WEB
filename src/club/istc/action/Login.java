package club.istc.action;

import java.util.Map;

import club.istc.validation.*;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


/**
 * 登录。
 * 通过输入的用户名和密码，获取该用户信息并存入session中。
 * 该session在浏览网站的过程中全程存在，代表用户处于登录状态。
 * 任何涉及数据库的操作均需要查验session。
 */

public class Login extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private String id;
	private String password;
	private Map<String, Object> session;
	// 用户登录 
	
	public Login() {
		// TODO Auto-generated constructor stub
		ActionContext context=ActionContext.getContext();
		session=context.getSession();
		//System.out.println(session.get("infofromjsp2Action"));
	}
	
	@Override
	public String execute() {
		//通过用户名和密码获取Member类对象信息
		//在这里嵌入数据库相关代码以检测是否成功连入数据库
//		try {
//			curPerson=new Func_for_control().getMember(""+id, password, ""+id);
//			if (curPerson==null) {
//				//登录失败，重定向至原页面，发送一个session表示登录失败
//				this.addActionError("学号和密码不匹配，请重新检查后输入！");
//				return INPUT;
//			}
//			else {
//				//登录成功，在前端通过memberInfo这个key即可获得session，并将其保存，进行请求时原样发送回来
//				session.put("personInfo", curPerson);
//			}
//		}
//		catch (IllegalAccessException e) {
//			// TODO: handle exception
//			this.addActionError("非法操作！");
//		}
//		catch (Exception e) {
//			// TODO: handle exception
//			this.addActionError("未知错误。请稍后再试。");
//		}
		//自定义一个session信息，用于测试从action到jsp的session传输
		//session.put("infofromAction2jsp", "这是一段测试从servlet到jsp能否正常发送session的文字，如果该段文字无乱码地正常显示则没有问题。");
		//在ajax下会失效
		if ("2141601033".equals(id)) {
			if (!"456".equals(password)) {
				this.addActionError("学号和密码不匹配，请重新检查后输入！");
			}
		}
		else {
			this.addActionError("学号和密码不匹配，请重新检查后输入！");
		}
		return INPUT;
	}
	
	@Override
	public void validate(){
		if (id==null || id=="") {
			addFieldError("id", "请输入学号！");
		}
		if (password==null || password=="") {
			addFieldError("password", "请输入密码！");
		}
		if (!new InjectionCheck(id).getResult()) {
			addFieldError("id", "请不要在输入的信息中包含特殊符号（* ' ; - + / % #）");
		}
		if (!new InjectionCheck(password).getResult()) {
			addFieldError("password","请不要在输入的信息中包含特殊符号（* ' ; - + / % #）");
		}
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
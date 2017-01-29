package club.istc.action;

import java.util.*;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import club.istc.bean.*;

public class InterviewAction extends ActionSupport{
	
	/**
	 * ����ģ��
	 */
	private static final long serialVersionUID = 1L;
	private List<Person> interviewees;
	private List<String> passed;
	private List<String> notpassed;
	Map<String, Object> session;
	
	public List<String> getPassed(){  
	    return passed;  
	}  
	
	public void setPassed(List<String> passed){  
	    this.passed=passed;  
	} 
	
	public List<String> getNotpassed() {
		return notpassed;
	}
	
	public void setNotpassed(List<String> notpassed) {
		this.notpassed = notpassed;
	}
	
	public InterviewAction() {
		// TODO Auto-generated constructor stub
		ActionContext context=ActionContext.getContext();
		session=context.getSession();
	}
	
	@Override
	public String execute(){
		deletePerson();
		session.put("interviewList", interviewees);
		return INPUT;
	}
	
	private void deletePerson(){
		//��ͨ�����Ե��û��������ݿⲢɾ�����������б��еļ�¼
		for (int i = 0; i < passed.size(); i++) {
			//��ѧ�Ŷ�Ӧ����Ա�������ݿ�
			//�����ݿ�ɾ����Ӧ��Ա
		}
		for (int i = 0; i < notpassed.size(); i++) {
			//�����ݿ�ɾ����Ӧ��Ա
		}
		//�����ݿ������»�ȡPerson�����List
	}

}

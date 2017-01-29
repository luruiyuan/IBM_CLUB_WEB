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
	private static List<Person> interviewees=new ArrayList<Person>();
	private String[] passed;
	Map<String, Object> session;
	
	public String[] getPassed(){  
	    return passed;  
	}  
	
	public void setPassed(String[] passed){  
	    this.passed=passed;  
	} 

	
	public InterviewAction() {
		// TODO Auto-generated constructor stub
		ActionContext context=ActionContext.getContext();
		session=context.getSession();
	}
	
	public String check() throws Exception{
		try {
			deletePerson();
			//�����ݿ������»�ȡPerson�����List
			session.put("interviewList", interviewees);
			if (interviewees.size()==0) {
				addFieldError("getIntervieweeError", "�����ѽ�����");
			}
			return INPUT;

		} catch (Exception e) {
			// TODO: handle exception
			addFieldError("getIntervieweeError", "��ȡ������Ա��Ϣʧ�ܣ�");
			return INPUT;
		}
	}
	
	public String get() throws Exception{
		addtemp();
		try {
			session.put("interviewList", interviewees);
			return INPUT;
		} catch (Exception e) {
			// TODO: handle exception
			addFieldError("getIntervieweeError", "��ȡ������Ա��Ϣʧ�ܣ�");
			return INPUT;
		}
	}
	
	private void deletePerson(){
		//��ͨ�����Ե��û��������ݿⲢɾ�����������б��еļ�¼
		try {
			for (int i = 0; i < passed.length; i++) {
				//��ѧ�Ŷ�Ӧ����Ա�������ݿ�
				//�����ݿ���ɾ����Ӧ��Ա
				for (int j = 0; j < interviewees.size(); j++) {
					
					if(passed[i].trim().equals(interviewees.get(j).getID().trim())){
						interviewees.remove(j);
						break;
					}	
				}
			}
		} catch (NullPointerException e) {
			// TODO: handle exception
			
		}

	}
	
/*	private ArrayList<Person> getIntervieweesFromDatabase() {
		//ԭ�����Ǵ����ݿ��л�ȡ���ݣ�����Ϊ�˲����ü�����
		try {
			ArrayList<Person> curIntervieweesList = new ArrayList<Person>();
			

			
			return curIntervieweesList;
			
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}*/
	
	public static void addtemp() {
		Person curPerson = new Person();
		curPerson.setID("2141601033");
		curPerson.setName("����");
		interviewees.add(curPerson);
		
		curPerson=new Person();
		curPerson.setID("2141601022");
		curPerson.setName("����");
		interviewees.add(curPerson);
		
		curPerson=new Person();
		curPerson.setID("2141601011");
		curPerson.setName("����");
		interviewees.add(curPerson);
	}

}

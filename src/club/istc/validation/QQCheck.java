package club.istc.validation;

/**
 * ���QQ���Ƿ�Ϸ�
 */

public class QQCheck {
	
	String QQ;
	boolean result;
	
	public QQCheck(String QQ) {
		// TODO Auto-generated constructor stub
		this.QQ=QQ.trim();
		result=checkQQ();
		//System.out.println("QQ�ż�����ǣ�"+false);
	}
	
	private boolean checkQQ() {
		//���qq���Ƿ�Ϸ�
		try {
			Long.parseLong(QQ);
			String regex = "[1-9][0-9]{4,14}";
			result = QQ.matches(regex);
		} catch (NumberFormatException e) {
			// TODO: handle exception
			result=false;
		}
		return result;
	}
	
	public boolean getResult(){
		return result;
	}

}

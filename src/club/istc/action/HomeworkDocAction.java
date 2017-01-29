package club.istc.action;

import java.io.*;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class HomeworkDocAction extends ActionSupport{
	
	/**
	 * ��Ա�����Լ���ҵ�ļ����ϴ�������
	 */
	
	private static final long serialVersionUID = 1L;
    
	 //ע�⣬file������ָǰ��jsp�ϴ��������ļ����������ļ��ϴ������������ʱ�ļ���������ļ�
	    private File file;
	    
	    //�ύ������file������
	    private String fileFileName;
	    
	    //�ύ������file��MIME����
	    private String fileContentType;

	    public File getFile(){
	        return file;
	    }

	    public void setFile(File file){
	        this.file = file;
	    }

	    public String getFileFileName(){
	        return fileFileName;
	    }

	    public void setFileFileName(String fileFileName){
	        this.fileFileName = fileFileName;
	    }

	    public String getFileContentType(){
	        return fileContentType;
	    }

	    public void setFileContentType(String fileContentType){
	        this.fileContentType = fileContentType;
	    }
	    
	    @Override
	    public String execute(){
	    	try {
	    //���Լ���һ����Ŀ¼������ʱ�Զ�����Ŀ¼�Ĵ���
		        String root = ServletActionContext.getServletContext().getRealPath("/upload"); 
		        InputStream is = new FileInputStream(file);
		        OutputStream os = new FileOutputStream(new File(root, fileFileName));
		        System.out.println("fileFileName: " + fileFileName);
		// ��Ϊfile�Ǵ������ʱ�ļ��е��ļ������ǿ��Խ����ļ������ļ�·����ӡ����������֮ǰ��fileFileName�Ƿ���ͬ
		        System.out.println("file: " + file.getName());
		        System.out.println("file: " + file.getPath());
		        byte[] buffer = new byte[500];
		        while(-1 != (is.read(buffer, 0, buffer.length))){
		            os.write(buffer);
		        }
		        os.close();
		        is.close();
		        return SUCCESS;
			} catch (Exception e) {
				// TODO: handle exception
				return INPUT;
			}

	    }
	}



import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class HandleTxt {
	String content;
	String year;
	String pdf_path="C:\\Users\\yzqnh\\Desktop\\pdf\\";
	String result_path = "C:\\Users\\yzqnh\\Desktop\\1\\";
	
	public HandleTxt()
	{
			
	}
	
	
    public void setContent(String file) {
    	content = PdfUtils.readPDF(file);	
    	content = content.replaceAll("\r|\n", "");
	}
	
	public void writetotxt(String result)
    {   
		if(result == "")
		{
			System.out.print("���ĵ���������Ϣ\n");
			return;
		}
        try   
        {   
        	String fileName = null;
        	String code = null;  
            String pattern_code = "(?<=��Ʊ����.{0,4})\\d{4,6}";
            Pattern r_code = Pattern.compile(pattern_code);           
            Matcher m_code = r_code.matcher(content);

            if (m_code.find()) {                 
               System.out.println("��Ʊ����: " + m_code.group(0) );               
               code = m_code.group(0); 
               fileName = this.result_path+code+"_"+this.year+".txt";
            } else {
            	System.out.println("δ�ҵ���Ʊ����");
            	return;
            }     	
        	
        	
            File f = new File(fileName);      
            if (!f.exists())   
            {       
                f.createNewFile();      
            }      
            OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f),"gbk");      
            BufferedWriter writer=new BufferedWriter(write);          
            writer.write(result);      
            writer.close(); 
            System.out.print("��д�뵽�ļ��� "+fileName);
        } catch (Exception e)   
        {      
            e.printStackTrace();     
        }
    }
	
	public String search()//\\d+[��|��|��][^��]
	{
		String pattern = "[^��]{0,20}(����Ȩ|ʵ������ר��|���ר��|����ר��)[^��]*";
		Pattern r = Pattern.compile(pattern);  
        Matcher m = r.matcher(content);  
        int count = 0;
        String result="";
        String str = "";
        while (m.find()) 
        { 
            count = count + 1;             
            System.out.println("***ƥ����"+count+"***: "+m.group()); 
            str = m.group();
            str = str.replaceAll("\r|\n", "");
            result = result + "***ƥ����" + count + "***: " + str;            
            result = result + "\r\n";
        }      
        System.out.println("���� " + count + "�� ");
		return result;      

        
	}
	
	 public static void main( String args[] ){
		 HandleTxt h = new HandleTxt();
		 
		    File f = null;  
	        f = new File(h.pdf_path);  
	        File[] files = f.listFiles(); 	          
	        for (File file : files) {  
	            if(file.isFile())
	            {
	            	System.out.println("��ȡ���ĵ���"+file.getName());
	            	h.setContent(h.pdf_path+file.getName());
	            	h.year = file.getName().substring(0, 4);	            	
	            	String result = h.search();
	        		h.writetotxt(result);
	            }
	        }  	 
		 
		 
		
		 
	 }

}
   



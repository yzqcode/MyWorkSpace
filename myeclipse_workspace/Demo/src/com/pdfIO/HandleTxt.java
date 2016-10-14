package com.pdfIO;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HandleTxt {
	String content;
	
	public HandleTxt()
	{
		setContent("C:\\Users\\yzqnh\\Desktop\\62440013.PDF");
		content = content.replaceAll("\r|\n", "");
	}
	
	
    public void setContent(String file) {
    	content = PdfUtils.readPDF(file);	
	}
	
	public void writetotxt(String result)
    {   
        try   
        {   
        	String fileName = null;
        	String code;
        	String year;
        	
            // ��ָ��ģʽ���ַ�������            
            String pattern_year = "\\d{4}\\s*�� ";
            String pattern_code = "��Ʊ����\\s*\\d{6}";

            // ���� Pattern ����
            Pattern r_year = Pattern.compile(pattern_year);
            Pattern r_code = Pattern.compile(pattern_code);

            // ���ڴ��� matcher ����
            Matcher m_year = r_year.matcher(content);
            Matcher m_code = r_code.matcher(content);
//            if(m_year.find( ))
//            	System.out.print("adsd");
            if (m_year.find( )&&m_code.find()) {
               System.out.println("Found value: " + m_year.group(0) );   
               System.out.println("Found value: " + m_code.group(0) );
               year = m_year.group(0).substring(0, 4);
               code = m_code.group(0).substring(5); 
               fileName = code+"_"+year+".txt";
            } else {
               System.out.println("NO MATCH");
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
	
	public String search()
	{
        // ��ָ��ģʽ���ַ�������
        String line = "���ר�����������Ĺ�ط���ר�����͵����͵���ר����˹�ٷ�˹�ٷҡ�";      
       
        
        String pattern = "(?=[^��]*\\d\\s*��[^��]*)((?=[^��]*(����Ȩ|ʵ������ר��|���ר��|����ר��)[^��]*)(([^��]*ר��[^��]*)��))";

        
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
		 //System.out.print(h.content);
		 h.writetotxt(h.search());
		 
	 }

}
   



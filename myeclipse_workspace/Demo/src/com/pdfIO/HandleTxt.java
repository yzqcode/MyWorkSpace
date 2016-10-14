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
        	
            // 按指定模式在字符串查找            
            String pattern_year = "\\d{4}\\s*年 ";
            String pattern_code = "股票代码\\s*\\d{6}";

            // 创建 Pattern 对象
            Pattern r_year = Pattern.compile(pattern_year);
            Pattern r_code = Pattern.compile(pattern_code);

            // 现在创建 matcher 对象
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
            System.out.print("已写入到文件： "+fileName);
        } catch (Exception e)   
        {      
            e.printStackTrace();     
        }
    }
	
	public String search()
	{
        // 按指定模式在字符串查找
        String line = "打打专利。打算打算的鬼地方个专利发送到发送到。专利发斯蒂芬斯蒂芬。";      
       
        
        String pattern = "(?=[^。]*\\d\\s*项[^。]*)((?=[^。]*(著作权|实用新型专利|外观专利|发明专利)[^。]*)(([^。]*专利[^。]*)。))";

        
        Pattern r = Pattern.compile(pattern);        

   
        Matcher m = r.matcher(content);  
        int count = 0;
        String result="";
        String str = "";
        while (m.find()) 
        { 
            count = count + 1;             
            System.out.println("***匹配结果"+count+"***: "+m.group()); 
            str = m.group();
            str = str.replaceAll("\r|\n", "");
            result = result + "***匹配结果" + count + "***: " + str;            
            result = result + "\r\n";
        }      
        System.out.println("共有 " + count + "个 ");
		return result;      

        
	}
	
	 public static void main( String args[] ){
		 HandleTxt h = new HandleTxt();
		 //System.out.print(h.content);
		 h.writetotxt(h.search());
		 
	 }

}
   



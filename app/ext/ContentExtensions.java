package ext;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import play.templates.JavaExtensions;

public class ContentExtensions  extends JavaExtensions{
	
	public static String firstImage(String content) throws Exception {
		String img="";     
	    Pattern p_image;     
	    Matcher m_image;
	    String firstImage="";
	    List<String> pics = new ArrayList<String>();  
	  
	    String regEx_img = "<img.*src=(.*?)[^>]*?>"; //图片链接地址     
	    p_image = Pattern.compile   
	             (regEx_img,Pattern.CASE_INSENSITIVE);     
	    m_image = p_image.matcher(content);   
	    if(m_image.find()){     
	         img = img + "," + m_image.group();     
	         Matcher m  = Pattern.compile("src=\"?(.*?)(\"|>|\\s+)").matcher(img); //匹配src  
	         
	         if(m.find()){  
	            pics.add(m.group(1));  
	            firstImage = m.group(1);
	         }  
	     }     
	    
		
		return firstImage;
	}
	

}

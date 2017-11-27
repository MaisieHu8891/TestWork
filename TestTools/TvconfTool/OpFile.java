package TestTools.TvconfTool;

import java.io.*;
import org.json.*;
import java.security.MessageDigest;
import java.util.HashMap;

public class OpFile{
	
	private String filepath;
	
	public OpFile(String filepath){
		String fp = this.filepath;
	}
	
	public String ReadFile(String fp) throws IOException {
        File file = new File(fp);
        if(!file.exists()||file.isDirectory()) {
            throw new FileNotFoundException();
        }
        StringBuffer sb = new StringBuffer();
        InputStreamReader read = new InputStreamReader(new FileInputStream(file),"UTF-8");        
        String lineTxt = null;        
        try (BufferedReader bufferedReader = new BufferedReader(read)){
            while ((lineTxt = bufferedReader.readLine()) != null) {
                sb.append(lineTxt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
	
	public void ReplaceValue(String fp,String koldw, String kneww) throws IOException{		
		String contents = this.ReadFile(fp);
		BufferedWriter bw = new BufferedWriter(new FileWriter(fp));
		bw.write(contents.replace(koldw, kneww));
		bw.flush();
        bw.close();			
	}
	
	public String getMD532Small(String message) {  
        MessageDigest messageDigest = null;  
        StringBuffer md5StrBuff = new StringBuffer();  
        try {  
            messageDigest = MessageDigest.getInstance("MD5");  
            messageDigest.reset();  
            messageDigest.update(message.getBytes("UTF-8"));  
              
            byte[] byteArray = messageDigest.digest();  
            for (int i = 0; i < byteArray.length; i++)   
            {  
                if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)  
                    md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));  
                else  
                    md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));  
            }  
        } catch (Exception e) {  
            throw new RuntimeException();  
        }  
        return md5StrBuff.toString();
    }  
	
	public HashMap<String, String> getConfAndSign(String fp) throws IOException{
		String contents = this.ReadFile(fp);
		JSONObject cjson = new JSONObject(contents);
		String conf = cjson.getJSONObject("data").getString("conf");
		String sign = cjson.getJSONObject("data").getString("sign");
		HashMap<String, String> confandsign = new HashMap<String, String>();
		confandsign.put(conf, sign);
		return confandsign;
	}
	

}

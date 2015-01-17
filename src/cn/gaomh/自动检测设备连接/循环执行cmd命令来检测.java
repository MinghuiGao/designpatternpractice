package cn.gaomh.自动检测设备连接;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class 循环执行cmd命令来检测 {

	/**
	 * 功能描述：
	 * 
	 * @param args
	 * @版本 1.0
	 * @创建者 gaominghui
	 * @创建时间 2015-1-14 下午2:22:30
	 * @版权所有 ©2015 CTFO
	 * @修改者 gaominghui
	 * @修改时间 2015-1-14 下午2:22:30 修改描述
	 */

	public static void main(String[] args) {
		
        try {
        	Runtime runtime = Runtime.getRuntime();
//            Process process = runtime.exec("cmd /c start D:/hm_workspace/heima_mobilesafe/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/web_fileupload/resource/");
//            Process process = runtime.exec("cmd /c start " +resourcePath+"/pack2.bat");
            
            boolean isLoop = true;
            while(isLoop){
            	try {
            		Thread.sleep(3000);
            	} catch (InterruptedException e) {
            		e.printStackTrace();
            	}
            	Process process = runtime.exec("cmd /c dir");
            	InputStream in = process.getInputStream();  
            	BufferedReader inr = new BufferedReader(  
            			new InputStreamReader(in, "GBK"));  
            	String line = null;  
                while ((line = inr.readLine()) != null) {
                    System.out.println("--"+line);
                    if(line.contains("	device")){
                    	isLoop = false;
                    }
                }
            }
            System.out.println("检测到设备，开始自动上传... .");
            
        	try {
        		Thread.sleep(3000);
        	} catch (InterruptedException e) {
        		e.printStackTrace();
        	}
            
//            // 不支持url编码
            Process process2 = runtime.exec("cmd /k start C:/Users/dell/Desktop/adbpull.bat");
            InputStream in2 = process2.getInputStream();  
            BufferedReader inr2 = new BufferedReader(  
            		new InputStreamReader(in2));  
            String line2 = null; 
            line2 = inr2.readLine();
            while (line2 != null) {
            	System.out.println("---"+line2);
            	try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
            }
        } catch (IOException e) {  
            e.printStackTrace();  
        }
	}
	
	/**
     * @param command  command
     * @return null
     */
    public static String runCommand(String command) {
        BufferedReader br2 = null;
        String line = null;
        InputStream is = null;
        InputStreamReader isReader = null;
        try {
            Process proc = Runtime.getRuntime().exec(command);
            is = proc.getInputStream();
            isReader = new InputStreamReader(is, "utf-8");
            br2 = new BufferedReader(isReader);
            while ((line = br2.readLine()) != null) {
                return line;
            }
        } catch (IOException e) {
            return line;
        } finally {
            if (isReader != null) {
                try {
                    isReader.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                }
            }

            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                }
            }

            if (br2 != null) {
                try {
                    br2.close();
                } catch (IOException e) {
                    // TODO
                }
            }
        }
        return line;
    }
	

}

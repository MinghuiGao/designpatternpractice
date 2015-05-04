package cn.gaomh.文件分块合并;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/** 
 * 功能描述  文件分块
 * @类型名称 FileSplitor
 * @版本 1.0
 * @创建者 gaominghui
 * @创建时间 2015年4月30日 下午2:16:23
 * @版权所有 ©2015 CTFO
 */
public class FileSplitor {
	// 快的大小为1m。
	private static final int blockSize = 1024*1024/2;

	public FileSplitor() {
	}

	public static void main(String[] args) {
//		int[] test = new int[BigInteger.intValue()];
//		System.out.println(BigInteger.ONE);
		uploadFileByParts();
		try {
//			File tempFolder = new  File("D:/tempFolder");
//			if(!tempFolder.exists()){
//				tempFolder.mkdir();
//			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/** 按分块上传文件  
	 * @throws IOException 
	 * @throws NoSuchAlgorithmException **/
	@SuppressWarnings("resource")
	public static void uploadFileByParts() {
		String filePath = "D:/bjvmmis.dmp";
		File file = new File(filePath);
		try{
			FileInputStream  is = new FileInputStream(file);
	//################################################################################################//
			long totalBytes = is.available();
			System.out.println("总字节数："+totalBytes);
			// 进行逻辑分块
			int blocksAmount = (int) (totalBytes/blockSize + (totalBytes%blockSize == 0?0:1));
			System.out.println("总的块数： "+ blocksAmount);
			// 对分块的文件进行指纹生成
			String[] digest = new String[blocksAmount+1];
			byte[] buff = new byte[blockSize];
			System.out.println("buff.len:"+buff.length);
			BufferedInputStream bis = new BufferedInputStream(is);
			long start = System.currentTimeMillis();
			for(int i = 0;i<blocksAmount;i++){
				int len = bis.read(buff);
				System.out.println("read len : "+ len);
				digest[i] = genMD5FinguerPrint(buff);
				System.out.println("fp  : "+ digest[i]);
				if( i == digest.length)
					digest[i+1] = String.valueOf(len);
			}
			System.out.println("time consumption:"+ (System.currentTimeMillis()-start));
	//################################################################################################//
			// 对分块后的文件进行并发读取并写入新文件
			File tempFolder = new  File("D:/tempFolder");
			if(!tempFolder.exists()){
				tempFolder.mkdir();
			}
			BufferedInputStream serverBis = new BufferedInputStream(is);
			for(int i = 0;i<digest.length;i++){
				// 创建文件，以前名为文件名
				File temp = new File("D:/tempFolder/"+i);
				if(!temp.exists()){
					temp.createNewFile();
				}
				// 读取指定的块，并写入新文件  
				// 这里的InputStream 应该是从请求中获取的字节流.
				byte[] serverBuffer = new byte[blockSize];
				FileOutputStream fos = new FileOutputStream(temp);
				int read = -1;
				if( i == digest.length){
					read = serverBis.read(serverBuffer, i*blockSize, Integer.valueOf(digest[i+1]));
				}else{
					read = serverBis.read(serverBuffer, i*blockSize, blockSize);
				}
				fos.write(serverBuffer);
				if(read != -1){// 读取到字节了
				}
				fos.flush();
				fos.close();
				serverBis.reset();
				
			}
			bis.close();
			is.close();
		} catch(IOException | NoSuchAlgorithmException e){
			e.printStackTrace();
		} finally{
			
		}
		
	}
	
	private static String genMD5FinguerPrint(byte[] buff) throws NoSuchAlgorithmException{
		MessageDigest md5Digest = MessageDigest.getInstance("MD5");
		byte[] digestRes = md5Digest.digest(buff);
		return new String(digestRes);
	}
	
	
	/**
     * 以字节为单位读取文件，常用于读二进制文件，如图片、声音、影像等文件。
     */
    public static void readFileByBytes(String fileName) {
        File file = new File(fileName);
        InputStream in = null;
        try {
            System.out.println("以字节为单位读取文件内容，一次读一个字节：");
            // 一次读一个字节
            in = new FileInputStream(file);
            int tempbyte;
            while ((tempbyte = in.read()) != -1) {
                System.out.write(tempbyte);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        try {
            System.out.println("以字节为单位读取文件内容，一次读多个字节：");
            // 一次读多个字节
            byte[] tempbytes = new byte[100];
            int byteread = 0;
            in = new FileInputStream(fileName);
            ReadFromFile.showAvailableBytes(in);
            // 读入多个字节到字节数组中，byteread为一次读入的字节数
            while ((byteread = in.read(tempbytes)) != -1) {
                System.out.write(tempbytes, 0, byteread);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                }
            }
        }
    }

}

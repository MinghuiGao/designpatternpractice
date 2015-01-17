package cn.gaomh.GZip压缩;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Gzip压缩测试 {

	private static String FOLDER_PATH = "D:\\ant_\\2014_检查企业test";

	private static String FILE_PATH = "D:\\ant_\\2014_检查企业test\\temp.jpg";

	private static String TARGET_ZIP_FILE_PATH = "D:\\gaomh.gz";

	static DecimalFormat df = new DecimalFormat("#.00");
	/**
	 * 功能描述：
	 * 
	 * @param srcFile
	 *            要压缩的目录或文件
	 * @throws IOException 
	 * @版本 1.0
	 * @创建者 gaominghui
	 * @创建时间 2015-1-16 下午1:37:06
	 * @版权所有 ©2015 CTFO
	 * @修改者 gaominghui
	 * @修改时间 2015-1-16 下午1:37:06 修改描述
	 */
	public static void gizpComprass(File srcFile) throws IOException {
		if (srcFile.isDirectory()) {
			File[] files = srcFile.listFiles();
			for (File file : files) {
				gizpComprass(file);
			}
		} else if (srcFile.isFile()) {
			File outputFile = new File(TARGET_ZIP_FILE_PATH);
			if(!outputFile.exists()){
				outputFile.createNewFile();
			}
			// gzip 输出文件流
			FileOutputStream fos = null;
			BufferedOutputStream bos = null;
			// 待压缩文件输入流
			BufferedInputStream bis = null;
			try {
				fos = new FileOutputStream(outputFile);
				bos = new BufferedOutputStream(new GZIPOutputStream(fos));
				bis = new BufferedInputStream(new FileInputStream(srcFile));
				
				long total = srcFile.length(); 
				byte[] buffer = new byte[1024];
				int len = 0;
				int progress = 0;
				
				while((len = bis.read(buffer)) != -1){
					progress += len;
//					System.out.println("progress : " + Math.floor((double)progress/total*100) + "%");
					bos.write(buffer);
					bos.flush();
				}
				
				System.out.println(srcFile.getName() + "  : 100% ,DONE !");
				bos.flush();
				bis.close();
				bos.close();
			} finally{
				try {
					if(bos != null)
					bos.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					if(bis != null)
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					if(bos != null)
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 功能描述：
	 * 
	 * @param args
	 * @版本 1.0
	 * @创建者 gaominghui
	 * @创建时间 2015-1-16 上午11:14:35
	 * @版权所有 ©2015 CTFO
	 * @修改者 gaominghui
	 * @修改时间 2015-1-16 上午11:14:35 修改描述
	 */
	public static void main(String[] args) {
		File srcFolder = new File(FOLDER_PATH);
		try {
			gizpComprass(srcFolder);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		int len = 98;
//		long total = 1892;
//		System.out.println((double)len/total*100 + "%");
		
//		try {
//			// BufferedReader in = new BufferedReader(new
//			// FileReader("D:\\gziptest.txt"));
//			BufferedReader in = new BufferedReader(new FileReader(FOLDER_PATH));
//			BufferedOutputStream out = new BufferedOutputStream(
//					new GZIPOutputStream(new FileOutputStream("D:\\test.gz")));
//			System.out.println("Writing file");
//			int c;
//			while ((c = in.read()) != -1) {
//				out.write(c);
//			}
//			out.close();
//			in.close();
//			System.out.println("Reading file");
//			BufferedReader in2 = new BufferedReader(new InputStreamReader(
//					new GZIPInputStream(new FileInputStream("D:\\test.gz"))));
//			String s;
//			while ((s = in2.readLine()) != null) {
//				System.out.println(s);
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	// 复制文件
	public void CopyFile(String frompath, String topath) {
		File file1 = new File(frompath);// 源文件路径
		File file2 = new File(topath);// 目标文件路径
		String filename = file1.getName();
		long filesize = (file1.length()) / 1024 / 1024;
		System.out.println("********************文件属性********************");
		System.out.println("源文件路径：" + frompath);
		System.out.println("目标文件路径：" + topath);
		System.out.println("文件名称：" + filename);
		System.out.println("文件大小：" + filesize + " MB");
		int ch = 0;
		try {
			FileInputStream fin = new FileInputStream(file1);
			FileOutputStream fout = new FileOutputStream(file2);
			ch = fin.read();
			System.out.println("开始复制！");
			long startTime = System.currentTimeMillis(); // 获取开始时间
			while (ch != -1) {
				fout.write(ch);
				ch = fin.read();
			}
			long endTime = System.currentTimeMillis(); // 获取结束时间
			System.out.println("程序运行时间： " + (endTime - startTime) + "ms");
			System.out.println("复制完毕！");
			// 关闭流
			fin.close();
			fout.close();
		} catch (Exception e) {
			System.err.println("Error:  " + e);
		}
	}

	// 验证文件是否存在
	public boolean CheckFile(String frompath, String topath) {
		File file1 = new File(frompath);// 源文件路径
		File file2 = new File(topath);// 目标文件路径
		if (!file1.exists()) { // 文件不存在
			System.out.println("源文件不存在，请检查路径是否正确！");
			return false;
		} else {
			if (file2.exists()) {
				System.out.println("目标文件已经存在，请选择  覆盖/取消  ？");
				System.out.println("1： 覆盖    2：取消");
				try {
					Scanner sc = new Scanner(System.in);
					int a = sc.nextInt();
					if (a == 1) {
						System.out.println("你输入的是1,操作将继续,目标文件将被覆盖。");
						return true;
					} else if (a == 2) {
						System.out.println("您输入了2，操作将取消。");
						return false;
					} else {

						System.out.println("输入无效。。;");
						CheckFile(frompath, topath);

						return false;
					}

				} catch (Exception ee) {
					System.out.println("输入无效。。;");
					CheckFile(frompath, topath);
					return false;
				}
			}
		}
		return false;
	}
}

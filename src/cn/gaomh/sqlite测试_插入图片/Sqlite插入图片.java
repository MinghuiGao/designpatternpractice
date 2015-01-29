package cn.gaomh.sqlite测试_插入图片;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;


public class Sqlite插入图片 {
	
	
	private String dbFilePath;
	private String dbFileName;
	
	private static Connection conn;
	/**
	 * @throws Exception **/
	public void createDBFile() throws Exception{
		dbFileName = "hygl_db";
		dbFilePath = this.getClass().getClassLoader().getResource("").getPath() + dbFileName;
		// /D:/github_projects/designpatternpractice/bin/hygl_db
		System.out.println("file path : "+dbFilePath);
		File dbFile = new File(dbFilePath);
		if(!dbFile.exists()){
			dbFile.createNewFile();
			System.out.println("create db file.");
			this.createTable();
		}
	}
	
	public Connection openConnection() throws Exception{
		if(conn == null || conn.isClosed()){
			Class.forName("org.sqlite.JDBC"); 
			conn = DriverManager.getConnection("jdbc:sqlite://"+dbFilePath); 
		}
        return conn;
	}
	
	public void closeConnection() throws Exception{
		if(conn != null){
			conn.setAutoCommit(true); //
			conn.close();
		}
	}
	
	public void createTable() throws Exception{
		Connection conn = this.openConnection();
		Statement stat = conn.createStatement(); 
        // 创建附件表
        stat.executeUpdate("drop table if exists attachment");
        String createAttachment = "create table attachment (file blob,file_id varchar(32),file_url varchar(32),filename varchar(256),org_filename varchar(256),file_size number(18),annex_type varchar(64),indx number(4),extention varchar(32),uploader varchar(64),update_time varchar(32),file_create_time varchar(32),file_create_addr varchar(256),file_create_cond varchar(256),remark varchar(1024));";
        stat.executeUpdate(createAttachment);
	}
	
	/**
	 * 保存数据
	 */
	public void saveData() throws Exception{
		long start = System.currentTimeMillis();
		Connection conn = this.openConnection();
		for(int i =0 ;i<1000;i++){ //time consumption:75204ms
			
			PreparedStatement prep = conn.prepareStatement("insert into attachment values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
			
			String filePath = "D:/pda.png";
	//		SerialBlob sb = addFile(filePath);
	//		prep.setBlob(1, sb);
			
			//TODO 手机上考虑oom，测试之
			// 插入图片 --
			FileInputStream fis1 = new FileInputStream(filePath);
	//		prep.setBinaryStream(1, fis1, fis1.available());
			byte[] bb = new byte[fis1.available()];
			fis1.read(bb);
			prep.setBytes(1, bb);
			
			prep.setString(2, "file_id0000");
			prep.setString(3,filePath);
			
	        prep.addBatch(); 
	        conn.setAutoCommit(false); 
	        prep.executeBatch(); 
		}
        closeConnection();
        System.out.println("time consumtion:"+ (System.currentTimeMillis()-start));
	}
	
	/**
	 * 功能描述：
	 * @param filePath 文件路径
	 * @return 该文件的blob对象
	 * @throws FileNotFoundException
	 * @throws SerialException
	 * @throws SQLException
	 * @throws IOException 
	 */
	private SerialBlob addFile(String filePath) throws FileNotFoundException,
			SerialException, SQLException, IOException {
		SerialBlob sb;
//		Blob blob = conn.createBlob(); use this to create a blob.
		File pic = new File(filePath);
		FileInputStream fis = new FileInputStream(pic);
		BufferedInputStream bis = new BufferedInputStream(fis);
		int len = 0;
		int pos = 1;
		byte[] buffer = new byte[fis.available()];
		sb = new SerialBlob(buffer); 
		while((len = bis.read(buffer)) != -1){
			sb.setBytes(pos, buffer);
			pos += len;
		}
		bis.close();
		fis.close();
		return sb;
	}
	
	
	public void queryData() throws Exception{
		long start = System.currentTimeMillis();
		Connection conn = this.openConnection();
		Statement stmt = conn.createStatement();
		ResultSet resultSet = stmt.executeQuery("select * from attachment");
		while(resultSet.next()){
			int findColumn = resultSet.findColumn("file");
//			Blob blob = resultSet.getBlob(findColumn); 不好用
//			InputStream is = blob.getBinaryStream(); 不好用
//			InputStream is = resultSet.getBinaryStream(findColumn); 不好用
			byte[] picBytes = resultSet.getBytes(findColumn);
			String filePath = resultSet.getString(resultSet.findColumn("file_url"));
			File picFile = new File(filePath + String.valueOf(System.currentTimeMillis()));
			if(!picFile.exists()){
				picFile.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(picFile);
			fos.write(picBytes);
			fos.flush();
			fos.close();
		}
        closeConnection();
        System.out.println("time consumtion:"+ (System.currentTimeMillis()-start));
	}
	
	public void deleteData(){
		
	}
	public static void main(String[] args) {
//		insertPics();
		queryPics();
		
	}

	/** 查询图片 **/
	private static void queryPics(){
		Sqlite插入图片 sqlite = new Sqlite插入图片();
		try {
			sqlite.createDBFile();
			sqlite.queryData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 插入图片 **/
	private static void insertPics() {
		Sqlite插入图片 sqlite = new Sqlite插入图片();
		try {
			sqlite.createDBFile();
			sqlite.saveData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

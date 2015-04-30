package cn.gaomh.swtThing;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/** 
 * 功能描述 swt demo.
 * @类型名称 HelloSwt
 * @版本 1.0
 * @创建者 gaominghui
 * @创建时间 2015年4月21日 下午2:06:38
 * @版权所有 ©2015 CTFO
 */
public class HelloSwt {

	public HelloSwt() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args){
		
		//java.library.path变量作用，这个属性是用来告诉Java一些库的存放目录。
		System.getProperties().list(System.out);
		System.out.println(System.getProperty("java.library.path"));
		
		// 创建一个display对象
		Display display = new Display();
		// shell是程序的主窗体
		Shell shell = new Shell(display);
		// 设置shell的布局方式
//		shell.setLayout(null);
		shell.setLayout(null);
		// 声明一个可以显示多行信息的文本框
		Text hello = new Text(shell,SWT.MULTI);
		// 设置主窗体的标题
		shell.setText("hello demo of SWT");
		// 设置主窗体的大小
		shell.setSize(640, 480);
		// 声明颜色对象
		Color color = new Color(Display.getCurrent(),0,255,255);
		// 设置窗体的背景颜色
		shell.setBackground(color);
		// 设置文本框信息
		hello.setText("hello,SWt world!\n\n你好，swt世界");
		// 设置大小，否则为0.
		hello.setSize(new Point(200, 200));
		hello.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				System.out.println("keycode ;" + arg0);
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				System.out.println("keycode ;" + arg0);
			}
		});
		
		// 打开主窗体
		shell.open();
		// 如果主窗体没有关闭则一直循环
		while(!shell.isDisposed()){
			// 如果display不忙
			if(!display.readAndDispatch()){
				display.sleep();
			}
		}
		display.dispose();// 销毁display
	}
	
}

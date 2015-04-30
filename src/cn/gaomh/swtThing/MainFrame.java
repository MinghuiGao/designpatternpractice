//package cn.gaomh.swtThing;
//
//import org.eclipse.swt.widgets.Display;
//import org.eclipse.swt.widgets.Shell;
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.widgets.Composite;
//import org.eclipse.swt.widgets.*;
//import com.swtdesigner.ResourceManager;
//import org.eclipse.swt.graphics.*;
//
///*
// * Created on 2004-8-31
// *
// * TODO To change the template for this generated file go to
// * Window - Preferences - Java - Code Style - Code Templates
// */
//
///**
// * @author sbr
// * 
// *         TODO To change the template for this generated type comment go to
// *         Window - Preferences - Java - Code Style - Code Templates
// */
//public class MainFrame {
//
//	public static void main(String[] args) {
//		final Display display = Display.getDefault();
//		final Shell shell = new Shell(SWT.MIN);
//		shell.setImage(ResourceManager.getImage(MainFrame.class,
//				"images/default0.ico"));
//		shell.setText("窗口设计演示");
//		// ////////////////////////////////////////////////////////////////
//		// 菜单部分程序如下
//		Menu bar = new Menu(shell, SWT.BAR);
//		shell.setMenuBar(bar);
//
//		MenuItem menuItem1 = new MenuItem(bar, SWT.CASCADE);
//		menuItem1.setText("设备管理(&M)");
//
//		Menu subMenu1 = new Menu(shell, SWT.DROP_DOWN);
//		menuItem1.setMenu(subMenu1);
//
//		MenuItem subMenuItem1 = new MenuItem(subMenu1, SWT.PUSH);
//		subMenuItem1.setText("添加设备\tCtrl+A");
//		subMenuItem1.setAccelerator(SWT.CTRL + 'A');
//
//		Image icon01 = new Image(display, "add.ico");
//		subMenuItem1.setImage(icon01);
//
//		MenuItem subMenuItem2 = new MenuItem(subMenu1, SWT.PUSH);
//		subMenuItem2.setText("删除设备\tCtrl+D");
//		subMenuItem2.setAccelerator(SWT.CTRL + 'D');
//
//		Image icon02 = new Image(display, "del.ico");
//		subMenuItem2.setImage(icon02);
//
//		MenuItem sunMenuSeparator = new MenuItem(subMenu1, SWT.SEPARATOR);
//
//		MenuItem subMenuItem3 = new MenuItem(subMenu1, SWT.PUSH);
//		subMenuItem3.setText("设备检查\tCtrl+C");
//		subMenuItem3.setAccelerator(SWT.CTRL + 'C');
//
//		Image icon03 = new Image(display, "chk.ico");
//		subMenuItem3.setImage(icon03);
//
//		MenuItem subMenuItemSeparator2 = new MenuItem(subMenu1, SWT.SEPARATOR);
//
//		MenuItem subMenuItem4 = new MenuItem(subMenu1, SWT.PUSH);
//		subMenuItem4.setText("退出系统\tCtrl+Q");
//		subMenuItem4.setAccelerator(SWT.CTRL + 'Q');
//
//		Image icon04 = new Image(display, "quit.ico");
//		subMenuItem4.setImage(icon04);
//
//		MenuItem menuItem2 = new MenuItem(bar, SWT.CASCADE);
//		menuItem2.setText("设备控制(&C)");
//
//		Menu subMenu2 = new Menu(shell, SWT.DROP_DOWN);
//		menuItem2.setMenu(subMenu2);
//
//		MenuItem subMenuItem11 = new MenuItem(subMenu2, SWT.PUSH);
//		subMenuItem11.setText("切断设备\tCtrl+S");
//		subMenuItem11.setAccelerator(SWT.CTRL + 'S');
//
//		Image icon11 = new Image(display, "cut.ico");
//		subMenuItem11.setImage(icon11);
//
//		MenuItem subMenuItem12 = new MenuItem(subMenu2, SWT.CASCADE);
//		subMenuItem12.setText("监控设备");
//
//		Menu jkMenu = new Menu(shell, SWT.DROP_DOWN);
//		subMenuItem12.setMenu(jkMenu);
//
//		MenuItem jkMenuItem1 = new MenuItem(jkMenu, SWT.PUSH);
//		jkMenuItem1.setText("光电转换机\tCtrl+E");
//		jkMenuItem1.setAccelerator(SWT.CTRL + 'E');
//
//		Image icon12 = new Image(display, "convert.ico");
//		jkMenuItem1.setImage(icon12);
//
//		MenuItem jkMenuItem2 = new MenuItem(jkMenu, SWT.PUSH);
//		jkMenuItem2.setText("收发信台\tCtrl+L");
//		jkMenuItem2.setAccelerator(SWT.CTRL + 'L');
//
//		Image icon13 = new Image(display, "sf.ico");
//		jkMenuItem2.setImage(icon13);
//
//		MenuItem subMenuItemSeparator1 = new MenuItem(subMenu2, SWT.SEPARATOR);
//
//		MenuItem subMenuItem13 = new MenuItem(subMenu2, SWT.PUSH);
//		subMenuItem13.setText("嘻嘻哈哈\tCtrl+X");
//		subMenuItem13.setAccelerator(SWT.CTRL + 'X');
//
//		Image icon14 = new Image(display, "face.ico");
//		subMenuItem13.setImage(icon14);
//		// 以上是菜单部分
//		// ///////////////////////////////////////////////////////////////
//
//		// ///////////////////////////////////////////////////////////////
//		// 以下是工具栏部分
//		final CoolBar coolBar = new CoolBar(shell, SWT.FLAT);
//		coolBar.setLocation(0, 0);
//		coolBar.setSize(1024, 30);
//		CoolItem coolItem1 = new CoolItem(coolBar, SWT.FLAT);
//		ToolBar toolBar = new ToolBar(coolBar, SWT.FLAT);
//
//		ToolItem toolItem1 = new ToolItem(toolBar, SWT.FLAT);
//		// toolItem1.setText("添加");
//		toolItem1.setImage(icon01);
//		toolItem1.setToolTipText("添加设备...");
//
//		ToolItem toolItem2 = new ToolItem(toolBar, SWT.FLAT);
//		toolItem2.setImage(icon02);
//		toolItem2.setToolTipText("删除设备...");
//
//		ToolItem seperator = new ToolItem(toolBar, SWT.SEPARATOR);
//
//		ToolItem toolItem3 = new ToolItem(toolBar, SWT.FLAT);
//		toolItem3.setImage(icon11);
//		toolItem3.setToolTipText("切断设备...");
//
//		ToolItem toolItem4 = new ToolItem(toolBar, SWT.FLAT);
//		toolItem4.setImage(icon12);
//		toolItem4.setToolTipText("监控光电转换机工作情况...");
//
//		toolBar.pack();
//		Point size = toolBar.getSize();
//		coolItem1.setControl(toolBar);
//		coolItem1.setSize(coolItem1.computeSize(size.x, size.y));
//		coolItem1.setMinimumSize(size);
//		// //////////////////////////////////////////////////////////
//		// 以上是工具栏的代码实现部分
//		// //////////////////////////////////////////////////////////
//		// 以下是树的实现部分代码
//		Image doticon = new Image(display, "dot.ico");
//		Image sdoticon = new Image(display, "sdot.ico");
//		final Tree tree = new Tree(shell, SWT.MULTI | SWT.BORDER);
//		tree.setSize(165, 420);
//		tree.setLocation(0, 30);
//		TreeItem treeItem1 = new TreeItem(tree, SWT.NONE);
//		treeItem1.setText("长春市传输设备");
//		treeItem1.setImage(doticon);
//		TreeItem treeItem11 = new TreeItem(treeItem1, SWT.NONE);
//		treeItem11.setText("二道区传输设备");
//		TreeItem treeItem12 = new TreeItem(treeItem1, SWT.NONE);
//		treeItem12.setText("双阳区传输设备");
//		TreeItem treeItem13 = new TreeItem(treeItem1, SWT.NONE);
//		treeItem13.setText("朝阳区传输设备");
//		TreeItem treeItem14 = new TreeItem(treeItem1, SWT.NONE);
//		treeItem14.setText("宽城区传输设备");
//
//		treeItem11.setImage(sdoticon);
//		treeItem12.setImage(sdoticon);
//		treeItem13.setImage(sdoticon);
//		treeItem14.setImage(sdoticon);
//
//		TreeItem treeItem2 = new TreeItem(tree, SWT.NONE);
//		treeItem2.setText("吉林市传输设备");
//		treeItem2.setImage(doticon);
//		TreeItem treeItem21 = new TreeItem(treeItem2, SWT.NONE);
//		treeItem21.setText("吉林一区传输设备");
//		TreeItem treeItem22 = new TreeItem(treeItem2, SWT.NONE);
//		treeItem22.setText("吉林二区传输设备");
//		TreeItem treeItem23 = new TreeItem(treeItem2, SWT.NONE);
//		treeItem23.setText("吉林三区传输设备");
//		TreeItem treeItem24 = new TreeItem(treeItem2, SWT.NONE);
//		treeItem24.setText("吉林四区传输设备");
//
//		treeItem21.setImage(sdoticon);
//		treeItem22.setImage(sdoticon);
//		treeItem23.setImage(sdoticon);
//		treeItem24.setImage(sdoticon);
//		// /////////////////////////////////////////////////////////
//		// 以上就是树的实现
//		// ////////////////////////////////////////////////////////
//		// 以下是右方操作区的实现
//
//		final Group group = new Group(shell, SWT.NONE);
//		group.setBounds(170, 25, 590, 425);
//		group.setText("吉林省通信设备监控中心");
//		// //////////////////////////////////////////////////////////
//		// /以上是操作去的实现
//		// ////////////////////////////////////////////////////////
//		// 以下是状态栏的实现,SWT中没有状态栏，怎么办呢？只好模仿了
//		ToolBar statusBar = new ToolBar(shell, SWT.BORDER | SWT.BOTTOM);
//		statusBar.setLocation(3, 455);
//		statusBar.setSize(756, 30);
//
//		// ////////////////////////////////////////////////////////////
//		// 以上是状态栏的实现
//		// ////////////////////////////////////////////////////////////
//		shell.open();
//		while (!shell.isDisposed()) {
//			if (!display.readAndDispatch())
//				display.sleep();
//		}
//	}
//}
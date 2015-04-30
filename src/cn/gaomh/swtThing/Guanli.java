package cn.gaomh.swtThing;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.*;

/** 
 * 功能描述 界面切换 
 * @类型名称 Guanli
 * @版本 1.0
 * @创建者 gaominghui
 * @创建时间 2015年4月21日 下午5:59:14
 * @版权所有 ©2015 CTFO
 * @修改者 gaominghui
 * @修改时间 2015年4月21日 下午5:59:14
 * @修改描述 
 */
public class Guanli {
 
  public static void main(String[] args){
  Display display=new Display();//创建一个display对象。
      final Shell shell=new Shell(display);//shell是程序的主窗体
      
      Shell shell1=new Shell();
     // shell1.getShell(Jiemian);
      //getShell
      shell.setText("人事管理系统");
      Menu mainMenu=new Menu(shell,SWT.BAR);
      shell.setMenuBar(mainMenu);
      {
          //"文件"项
          MenuItem fileItem=new MenuItem(mainMenu,SWT.CASCADE);
          fileItem.setText("文件");
          Menu fileMenu=new Menu(shell,SWT.DROP_DOWN);
          fileItem.setMenu(fileMenu);
          MenuItem newFileItem=new MenuItem(fileMenu,SWT.CASCADE);
          
          newFileItem.setText("登录选项");
          newFileItem.addSelectionListener(new SelectionAdapter(){
        	  public void widgetSelected(SelectionEvent e){
        	    
        	  }
        	  });
 
          new MenuItem(fileMenu, SWT.SEPARATOR);
          MenuItem newFileItem1=new MenuItem(fileMenu,SWT.CASCADE);
          newFileItem1.setText("新建1");
          newFileItem1.setEnabled(false);
          //newFileItem1.setVisible(false);

      }

      shell.pack();
      shell.open();
      while(!shell.isDisposed()){
      display.sleep();
      //如果主窗体没有关闭则一直循环
      if(!display.readAndDispatch()){ //如果display不忙
      //休眠
      }
      }
      display.dispose();
      }
      
      //销毁display
    
}
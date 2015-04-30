package cn.gaomh.swtThing;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * 功能描述 awt 浏览器，可以用来访问政务网的某个路径。<br>
 * 后来发现在红帽下面无论如何也不可以把swt的组件嵌入到Canvas里面，大家有遇到过这样的问题吗？有没有什么解决方法 解决办法：
 * -Dsun.awt.xembedserver=true
 * 
 * @类型名称 MyBrowser
 * @版本 1.0
 * @创建者 gaominghui
 * @创建时间 2015年4月21日 下午2:38:59
 * @版权所有 ©2015 CTFO
 */
public class MyBrowser {

	public static void main(String[] args) {
		final Display display = Display.getDefault();

		Frame frm = new Frame("MyBrowser");
		Canvas embedded = new Canvas();
		frm.add(embedded, BorderLayout.CENTER);

		frm.pack();

		final Shell composite = SWT_AWT.new_Shell(display, embedded);
		composite.setLayout(new FillLayout(SWT.VERTICAL));
		final Browser browser = installBrowser(composite,
				"http://www.baidu.com");
		frm.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				e.getWindow().dispose();
				// composite.dispose();
				// display.dispose();
			}
		});

		JTextField addr = new JTextField(80);
		addr.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				display.syncExec(new Runnable() {
					public void run() {
						browser.setUrl(((JTextComponent) e.getSource())
								.getText());
					}
				});
			}
		});
		frm.add(addr, BorderLayout.NORTH);

		frm.setSize(800, 600);
		frm.setVisible(true);
		while (frm.isDisplayable())
			if (!display.readAndDispatch())
				display.sleep();
		// display.dispose();
	}

	public static Browser installBrowser(Composite parent, String homeURL) {
		Browser browser = new Browser(parent, SWT.EMBEDDED);
		browser.setUrl(homeURL);
		return browser;
	}

}
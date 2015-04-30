package cn.gaomh.swtThing;
import org.eclipse.swt.SWT;   
import org.eclipse.swt.events.SelectionAdapter;   
import org.eclipse.swt.events.SelectionEvent;   
import org.eclipse.swt.widgets.Button;   
import org.eclipse.swt.widgets.Display;   
import org.eclipse.swt.widgets.List;   
import org.eclipse.swt.widgets.Shell;   
  
public class ListSample   
{   
  
    private static List listright;   
  
    private static List listleft;   
  
    /**  
     * Launch the application  
     * @param args  
     * @author wayswang  
     * @email:wayswang@gmail.com  
     */  
    public static void main(String[] args)   
        {   
            final Display display = Display.getDefault();   
            final Shell shell = new Shell();   
            shell.setSize(429, 304);   
            shell.setText("SWT Application");   
            //   
  
            shell.open();   
  
            SelectionAdapter listener = new SelectionAdapter()   
                {   
                    public void widgetSelected(SelectionEvent e)   
                        {   
                            Button b = (Button) e.widget;   
                            if (b.getText().equals(">")) {   
                                verifyValue(listleft.getSelection(), listleft,   
                                        listright);   
                            }   
                            if (b.getText().equals(">>")) {   
                                verifyValue(listleft.getItems(), listleft,   
                                        listright);   
                            }   
                            if (b.getText().equals("<")) {   
                                verifyValue(listright.getSelection(),   
                                        listright, listleft);   
                            }   
                            if (b.getText().equals("<<")) {   
                                verifyValue(listright.getItems(), listright,   
                                        listleft);   
                            }   
                            if (b.getText().equals("上移")) {   
                                int index = listright.getSelectionIndex();   
                                if (index <= 0) {   
                                    return;   
                                }   
                                String currentValue = listright.getItem(index);   
                                listright.setItem(index, listright   
                                        .getItem(index - 1));   
                                listright.setItem(index - 1, currentValue);   
                                listright.select(index - 1);   
                            }   
                            if (b.getText().equals("下移")) {   
                                int index = listright.getSelectionIndex();   
                                if (index >= listright.getItemCount()-1) {   
                                    return;   
                                }   
                                String currentValue = listright.getItem(index);   
                                listright.setItem(index, listright   
                                        .getItem(index + 1));   
                                listright.setItem(index + 1, currentValue);   
                                //listleft.select(index + 1);   
                                listright.setSelection(index+1);   
                            }   
                        }   
  
                    private void verifyValue(String[] selection, List from,   
                            List to)   
                        {   
                            // TODO 自动生成方法存根   
                            for (int i = 0; i < selection.length; i++) {   
                                from.remove(selection[i]);   
                                to.add(selection[i]);   
                            }   
                        }   
                };// 注意：这里为什么要加；，因为这是一个实例化   
  
            listleft = new List(shell, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);   
            listleft.setBounds(10, 10, 121, 244);   
  
            String[] items = new String[20];   
            for (int i = 0; i < 20; i++) {   
                listleft.add("ITEM" + i);   
            }   
  
            final Button bt1 = new Button(shell, SWT.NONE);   
            bt1.setText(">");   
            bt1.setBounds(161, 48, 44, 23);   
            bt1.addSelectionListener(listener);   
  
            final Button bt2 = new Button(shell, SWT.NONE);   
            bt2.setText(">>");   
            bt2.setBounds(161, 97, 44, 23);   
            bt2.addSelectionListener(listener);   
  
            final Button bt3 = new Button(shell, SWT.NONE);   
            bt3.setText("<");   
            bt3.setBounds(161, 148, 44, 23);   
            bt3.addSelectionListener(listener);   
  
            final Button bt4 = new Button(shell, SWT.NONE);   
            bt4.setText("<<");   
            bt4.setBounds(161, 200, 44, 23);   
            bt4.addSelectionListener(listener);   
  
            listright = new List(shell, SWT.BORDER);   
            listright.setBounds(230, 10, 113, 244);   
  
            final Button bt5 = new Button(shell, SWT.NONE);   
            bt5.setText("上移");   
            bt5.setBounds(372, 176, 44, 23);   
            bt5.addSelectionListener(listener);   
  
            final Button bt6 = new Button(shell, SWT.NONE);   
            bt6.setText("下移");   
            bt6.setBounds(372, 225, 44, 23);   
            bt6.addSelectionListener(listener);   
  
            shell.layout();   
            while (!shell.isDisposed()) {   
                if (!display.readAndDispatch())   
                    display.sleep();   
            }   
        }   
}   
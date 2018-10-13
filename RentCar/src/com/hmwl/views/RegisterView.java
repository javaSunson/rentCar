package com.hmwl.views;

import java.io.InputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.hmwl.service.UserService;
import com.hmwl.utils.ChangeSkin;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;

public class RegisterView {

	protected Shell shell;
	private Text t_username;
	private Text t_password;
	private Text t_address;
	private Text t_phone;
	Display display = null;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			RegisterView window = new RegisterView();
			//SWT. APPLICATION_MODAL|SWT.CLOSE
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell(SWT. APPLICATION_MODAL|SWT.CLOSE);   //模态窗口
		shell.setSize(558, 375);
		shell.setText("欢迎注册");
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		String bgpath = ChangeSkin.getCurrSkin();
		InputStream bg = this.getClass().getResourceAsStream(bgpath);
		InputStream reimg = this.getClass().getResourceAsStream("/Img/icon1.png");
		shell.setBackgroundImage(new Image(display,bg));
		shell.setImage(new Image(display, reimg));
		
		
		Label ui_username = new Label(shell, SWT.NONE);
		ui_username.setBounds(83, 100, 53, 20);
		ui_username.setText("\u7528\u6237\u540D");
		
		Label ui_password = new Label(shell, SWT.NONE);
		ui_password.setBounds(83, 135, 42, 20);
		ui_password.setText("\u5BC6\u7801");
		
		Label tips = new Label(shell, SWT.NONE);
		tips.setBounds(230, 248, 177, 20);
		
		Label ui_address = new Label(shell, SWT.NONE);
		ui_address.setBounds(83, 175, 42, 20);
		ui_address.setText("\u5730\u5740");
		
		Label ui_phone = new Label(shell, SWT.NONE);
		ui_phone.setBounds(83, 207, 53, 20);
		ui_phone.setText("\u624B\u673A\u53F7");
		
		t_username = new Text(shell, SWT.BORDER);
		t_username.setBounds(157, 97, 151, 26);
		
		t_password = new Text(shell, SWT.BORDER);
		t_password.setBounds(157, 132, 151, 26);
		
		t_address = new Text(shell, SWT.BORDER);
		t_address.setBounds(157, 172, 151, 26);
		
		t_phone = new Text(shell, SWT.BORDER);
		t_phone.setBounds(157, 204, 151, 26);
		
		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				UserService userService = new UserService();
				boolean regflag = userService.register(t_username.getText(), t_password.getText(), 
						t_address.getText(), t_phone.getText());
				if(regflag){
					tips.setText("注册成功!");
					try {
						Thread.sleep(2000);
						tips.setText("");
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					try {
						tips.setText("注册失败!用户名已经存在!");
						Thread.sleep(2000);
						tips.setText("");
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		button.setBounds(354, 128, 98, 30);
		button.setText("\u63D0\u4EA4");
		
		Button button_1 = new Button(shell, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				/**执行清空操作*/
				t_address.setText("");
				t_password.setText("");
				t_username.setText("");
				t_phone.setText("");
			}
		});
		button_1.setBounds(354, 165, 98, 30);
		button_1.setText("\u6E05\u7A7A");
		
		

	}

}

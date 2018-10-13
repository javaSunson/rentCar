package com.hmwl.views;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.awt.Color;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import com.hmwl.service.UserService;
import com.hmwl.utils.ChangeSkin;
import com.hmwl.utils.Md5Tools;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Combo;

public class MainView {

	protected Shell shell;
	private Text ui_username;
	private Text ui_password;
	private SimpleUser simple = new SimpleUser();
	private AdminUser admin = new AdminUser();
	private RegisterView register = new RegisterView();
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainView window = new MainView();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		/*背景处理代码**/
		//shell.setImage(new Image(display, "N:\\eclipse_workspace_P\\RentCar\\src\\Img\\icon1.png"));
		/***绝对路径写法
		shell.setImage(new Image(display, "N:\\eclipse_workspace_P\\RentCar\\src\\Img\\icon1.png"));
		shell.setBackgroundImage(new Image(display, "N:\\eclipse_workspace_P\\RentCar\\src\\bg.jpg") );
		****/
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		InputStream bg = this.getClass().getResourceAsStream("/Img/bg.jpg");
		InputStream reimg = this.getClass().getResourceAsStream("/Img/icon1.png");
		shell.setBackgroundImage(new Image(display,bg));
		shell.setImage(new Image(display, reimg));
		
		Button ui_changeskin = new Button(shell, SWT.NONE);
		ui_changeskin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String path = ChangeSkin.setSking();
				InputStream bg = this.getClass().getResourceAsStream(path);
				shell.setBackgroundImage(new Image(display,bg));
			}
		});
		ui_changeskin.setBounds(704, 489, 56, 30);
		ui_changeskin.setText("\u6362\u80A4");
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
		shell = new Shell();
		shell.setSize(800, 600);
		shell.setText("虹梦租车系统");
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);//设置全局的label背景透明
		Button ui_register = new Button(shell, SWT.NONE);
		ui_register.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				register.open();
			}
		});
		ui_register.setBounds(401, 357, 98, 30);
		ui_register.setText("\u6CE8\u518C");
		
		Label ui_loginres = new Label(shell, SWT.INHERIT_DEFAULT);
		
		
		
		ui_loginres.setBounds(347, 314, 76, 20);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(283, 179, 30, 20);
		lblNewLabel.setText("\u8D26\u6237");
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(283, 252, 30, 20);
		lblNewLabel_1.setText("\u5BC6\u7801");
		
		ui_username = new Text(shell, SWT.BORDER);
		ui_username.setBounds(346, 176, 153, 26);
		
		ui_password = new Text(shell, SWT.BORDER);
		ui_password.setText("");
		ui_password.setBounds(346, 249, 153, 26);
		
		Button ui_login = new Button(shell, SWT.NONE);
		ui_login.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				UserService userService = new UserService();
				
				try {
					MessageDigest md = MessageDigest.getInstance("MD5");
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String res = Md5Tools.stringToMD5(ui_password.getText());
				System.out.println(res);
				int loginstatus = userService.login(ui_username.getText(), res);
				switch (loginstatus) {
				case -1:
					//ui_loginres.setText("登陆失败!用户名或者密码错误!");
					ui_loginres.setText("登陆失败!");
					ui_loginres.setVisible(true);
					break;
				case 0:
					/**普通用户*/
					ui_loginres.setText("登陆成功!");
					ui_loginres.setVisible(true);
					shell.setVisible(false);
					simple.open();
					shell.setVisible(true);
					break;
				case 1:
					/**管理员账户*/
					ui_loginres.setText("登陆成功!");
					ui_loginres.setVisible(true);
					shell.setVisible(false);
					admin.open();
					shell.setVisible(true);
					break;
				default:
					break;
				}
			}
		});

		ui_login.setBounds(297, 357, 98, 30);
		ui_login.setText("\u767B\u9646");
		//comp.setBounds(0,0,800,600);
		/**增加个换肤的思路?*/
		/**写个方法,把每个界面需要的皮肤路径放在一起,去获取*/
		
	}
}

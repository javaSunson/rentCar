package com.hmwl.views;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.io.InputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Text;

import com.hmwl.entity.Car;
import com.hmwl.entity.RentInfo;
import com.hmwl.entity.User;
import com.hmwl.service.RentService;
import com.hmwl.utils.ChangeSkin;

import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class RebackCarView {

	protected Shell shell;
	private Text ui_getusername;
	private Table table;
	private String user_name = "UFO";
	Display display = null;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			RebackCarView window = new RebackCarView();
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
		shell = new Shell();
		shell.setSize(800, 375);
		shell.setText("归还车辆");
		String bgpath = ChangeSkin.getCurrSkin();
		
		InputStream bg = this.getClass().getResourceAsStream(bgpath);
		InputStream reimg = this.getClass().getResourceAsStream("/Img/icon1.png");
		
		shell.setBackgroundImage(new Image(display,bg));
		shell.setImage(new Image(display, reimg));
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		Button button_1 = new Button(shell, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				RentService rs = new RentService();
				rs.rebackCar(user_name);
			}
		});
		button_1.setBounds(30, 217, 128, 30);
		button_1.setText("\u4E00\u952E\u8FD8\u8F66");
		
		Label ui_result = new Label(shell, SWT.NONE);
		ui_result.setBounds(433, 298, 76, 20);

		
		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				user_name = ui_getusername.getText();
				RentService rents = new RentService();
				java.util.List<User> list = rents.queryUsersByName(ui_getusername.getText());
				java.util.List<RentInfo> rentlist = rents.queryRentList(list.get(0).getUserid());
				/*String [] sarr = new String [array.length+1];
				sarr[0]="编号:    品牌:     型号:    数量:     日租金:     价值:    租借状态:";
				for (int i = 0 ; i < array.length;i++) {
					 sarr[i+1]= array[i].toString();
				}
				 * **/
				/*需要判断**/
				if(rentlist.size() != 0) {
					TableItem item = null; 
					for (int row = 0; row < list.size() ;row++) { 
						item = new TableItem(table, SWT.NONE); 
							 item.setText(0, rentlist.get(row).getRent_id().toString());
							 item.setText(1, rentlist.get(row).getUser_id().toString());
							 item.setText(2, rentlist.get(row).getCar_id().toString());
							 item.setText(3, rentlist.get(row).getstart_date().toString());
							 item.setText(4, rentlist.get(row).getEnd_date().toString());
							 item.setText(5, rentlist.get(row).getMoney().toString());
					}
				}
			}
			}
		);
		button.setBounds(30, 156, 128, 30);
		button.setText("\u67E5\u770B\u79DF\u8D41\u8BB0\u5F55");
		
		ui_getusername = new Text(shell, SWT.BORDER);
		ui_getusername.setBounds(30, 108, 128, 26);
		
		Label label = new Label(shell, SWT.NONE);
		label.setBounds(30, 71, 149, 20);
		label.setText("\u8BF7\u5728\u4E0B\u65B9\u8F93\u5165\u7528\u6237\u540D");
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(185, 41, 574, 248);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(54);
		tableColumn.setText("\u5E8F\u53F7");
		
		TableColumn tblclmnid = new TableColumn(table, SWT.NONE);
		tblclmnid.setWidth(68);
		tblclmnid.setText("\u7528\u6237id");
		
		TableColumn tblclmnid_1 = new TableColumn(table, SWT.NONE);
		tblclmnid_1.setWidth(79);
		tblclmnid_1.setText("\u6C7D\u8F66id");
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(138);
		tblclmnNewColumn.setText("\u5F00\u59CB\u65F6\u95F4");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(145);
		tableColumn_1.setText("\u7ED3\u675F\u65F6\u95F4");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(191);
		tableColumn_2.setText("\u6D88\u8D39\u91D1\u989D");
		
	
	}
}

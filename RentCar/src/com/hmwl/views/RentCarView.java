package com.hmwl.views;

import java.io.InputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;

import com.hmwl.service.CarService;
import com.hmwl.service.RentService;
import com.hmwl.utils.ChangeSkin;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;

public class RentCarView {

	protected Shell shell;
	private Text ui_car_id;
	private Text ui_renter;
	Display display = null;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			RentCarView window = new RentCarView();
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
		shell = new Shell(SWT.APPLICATION_MODAL|SWT.CLOSE);
		shell.setSize(800, 600);
		shell.setText("租借/归还车辆");
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);

		/**换肤功能已经实现*/
		String bgpath = ChangeSkin.getCurrSkin();
		InputStream bg = this.getClass().getResourceAsStream(bgpath);
		
		InputStream reimg = this.getClass().getResourceAsStream("/Img/icon1.png");
		shell.setBackgroundImage(new Image(display,bg));
		shell.setImage(new Image(display, reimg));
		
		
		Label label = new Label(shell, SWT.NONE);
		label.setBounds(52, 48, 168, 20);
		label.setText("\u8BF7\u60A8\u8BA4\u771F\u586B\u5199\u79DF\u8D41\u4FE1\u606F:");
		
		Label lblid = new Label(shell, SWT.NONE);
		lblid.setBounds(158, 180, 61, 20);
		lblid.setText("\u8F66\u8F86ID");
		
		ui_car_id = new Text(shell, SWT.BORDER);
		ui_car_id.setBounds(225, 177, 129, 26);
		
		ui_renter = new Text(shell, SWT.BORDER);
		ui_renter.setBounds(225, 228, 129, 26);
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setBounds(159, 231, 61, 20);
		label_1.setText("\u79DF\u8D41\u4EBA");
		
		DateTime ui_start = new DateTime(shell, SWT.BORDER);
		ui_start.setBounds(225, 271, 129, 28);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setBounds(144, 271, 76, 20);
		label_2.setText("\u5F00\u59CB\u65F6\u95F4");
		
		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setBounds(144, 326, 76, 20);
		label_3.setText("\u7ED3\u675F\u65F6\u95F4");
		
		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setBounds(559, 97, 76, 20);
		label_4.setText("\u5F53\u524D\u8D39\u7528:");
		
		Label ui_count_price = new Label(shell, SWT.BORDER | SWT.CENTER);
		ui_count_price.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 16, SWT.NORMAL));
		ui_count_price.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		ui_count_price.setBounds(539, 205, 156, 77);
		ui_count_price.setText("0.00");
		
		DateTime ui_end = new DateTime(shell, SWT.BORDER);
		ui_end.setBounds(225, 318, 129, 28);
		
		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				RentService rentser = new RentService();
				double day_pri = rentser.rentPrice(ui_car_id.getText());
				/*当前仅能计算一个月以内的租用情况
				 * @Time 10/09/15.03
				 * 
				 * **/
				//不管当月几天,都按照30天计算
				int month = ui_end.getMonth()-ui_start.getMonth();
				int day = (ui_end.getDay() - ui_start.getDay()) +
						month * 30
						;
				if(day_pri > 0) {
					ui_count_price.setText(String.valueOf(day*day_pri));
				}else {
					ui_count_price.setText("数据非法");
				}
			}
		});
		button.setBounds(122, 393, 141, 30);
		button.setText("\u4F30\u7B97\u5F53\u524D\u79DF\u8D41\u4EF7\u683C");
		
		Button button_1 = new Button(shell, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				RentService rentService = new RentService();
				String sdate = ui_start.getDay()+"-"+ui_start.getMonth()+"月-"+ui_start.getYear();
				String edate = ui_end.getDay()+"-"+ui_end.getMonth()+"月-"+ui_end.getYear();
				/**这个地方可能有问题*/
				boolean rentCar = rentService.rentCar(ui_car_id.getText(),sdate , edate, ui_count_price.getText());
				if(rentCar) {
					ui_count_price.setText("租借成功!");
					/**租借成功,就该让信息表中的数据减1**/
					CarService cars = new CarService();
					cars.minusCar(ui_car_id.getText());
				}else {
					ui_count_price.setText("租借失败!");
				}
				
			}
		});
		button_1.setBounds(292, 393, 98, 30);
		button_1.setText("\u79DF\u501F");
		
	

	}
}

package com.hmwl.views;

import java.io.InputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.hmwl.service.CarService;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.SWTResourceManager;

public class AddCar {

	protected Shell shell;
	private Text ui_car_brand;
	private Text ui_car_model;
	private Text ui_car_num;
	private Text ui_car_isrent;
	private Text ui_car_price;
	private Text ui_day_money;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AddCar window = new AddCar();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	Display display = null;
	public void open() {
		display= Display.getDefault();
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
		
		shell = new Shell(SWT. APPLICATION_MODAL|SWT.CLOSE);   //当前是模态窗口
		shell.setSize(558, 375);
		shell.setText("增加车辆");
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		InputStream bg = this.getClass().getResourceAsStream("/Img/bg.jpg");
		InputStream reimg = this.getClass().getResourceAsStream("/Img/icon1.png");
		shell.setBackgroundImage(new Image(display,bg));
		shell.setImage(new Image(display, reimg));
		
		Label label = new Label(shell, SWT.NONE);
		label.setText("\u8BF7\u8F93\u5165\u54C1\u724C");
		label.setBounds(72, 71, 76, 20);
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setBounds(72, 109, 76, 20);
		label_1.setText("\u8BF7\u8F93\u5165\u578B\u53F7");
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setBounds(72, 144, 76, 23);
		label_2.setText("\u8BF7\u8F93\u5165\u6570\u91CF");
		
		ui_car_brand = new Text(shell, SWT.BORDER);
		ui_car_brand.setBounds(174, 71, 131, 26);
		
		ui_car_model = new Text(shell, SWT.BORDER);
		ui_car_model.setBounds(174, 106, 131, 26);
		
		ui_car_num = new Text(shell, SWT.BORDER);
		ui_car_num.setBounds(174, 141, 131, 26);
		
		ui_car_isrent = new Text(shell, SWT.BORDER);
		ui_car_isrent.setToolTipText("\u6B64\u9879\u53EF\u9009,true\u6216\u8005false");
		ui_car_isrent.setBounds(174, 269, 131, 26);
		
		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setBounds(72, 272, 76, 20);
		label_3.setText("\u662F\u5426\u53EF\u79DF");
		
		Label ui_tips = new Label(shell, SWT.NONE);
		ui_tips.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		ui_tips.setBounds(374, 247, 143, 20);
		
		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				CarService cars = new CarService();
				int result = cars.addCar(ui_car_brand.getText(), ui_car_model.getText(), 
						ui_car_num.getText(), ui_car_price.getText(), ui_day_money.getText(), ui_car_isrent.getText());
				//String car_brand,String car_model,String car_number,String car_price
				//,String money_day,String status
				if(result > 0) {
					ui_tips.setText("恭喜,增加成功!");
				}else {
					ui_tips.setText("很遗憾,添加失败!");
				}
			}
		});
		button.setBounds(392, 93, 98, 30);
		button.setText("\u63D0\u4EA4");
		
		Button button_1 = new Button(shell, SWT.NONE);
		button_1.setBounds(392, 154, 98, 30);
		button_1.setText("\u6E05\u7A7A");
		
		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setBounds(72, 227, 76, 20);
		label_4.setText("\u8BF7\u8F93\u5165\u4EF7\u503C");
		
		ui_car_price = new Text(shell, SWT.BORDER);
		ui_car_price.setBounds(174, 224, 131, 26);
		
		ui_day_money = new Text(shell, SWT.BORDER);
		ui_day_money.setBounds(175, 186, 130, 26);
		
		Label label_5 = new Label(shell, SWT.NONE);
		label_5.setText("\u8BF7\u8F93\u5165\u65E5\u79DF");
		label_5.setBounds(72, 189, 76, 20);
		
		
		

	}
}

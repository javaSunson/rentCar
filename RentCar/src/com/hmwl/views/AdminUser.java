package com.hmwl.views;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.io.InputStream;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import com.hmwl.entity.Car;
import com.hmwl.entity.RentInfo;
import com.hmwl.service.CarService;
import com.hmwl.service.RentService;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;

public class AdminUser {

	protected Shell shell;
	private Text ui_condition;
	private Table table;
	Display display = null;

	private AddCar addCar = new AddCar();
	RentService rentService = new RentService();
	//ChangeCarView ccv  = new ChangeCarVew();
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AdminUser window = new AdminUser();
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
	
	/**Test code:
	 * constructor tcl
	 * 
	 *by martin
	 * **/
	protected void createTableColumn() {
	}
	
	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(800, 600);
		shell.setText("管理员账户");
		
		InputStream bg = this.getClass().getResourceAsStream("/Img/adbg.jpg");
		InputStream reimg = this.getClass().getResourceAsStream("/Img/icon1.png");
		shell.setBackgroundImage(new Image(display,bg));
		shell.setImage(new Image(display, reimg));
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);

		Label ui_tips = new Label(shell, SWT.NONE);
		ui_tips.setForeground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
		ui_tips.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		ui_tips.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		ui_tips.setBounds(32, 27, 420, 30);
		ui_tips.setText("\u5F53\u524D\u662F\u7BA1\u7406\u5458\u8D26\u6237,\u6743\u5229\u8D8A\u5927,\u8D23\u4EFB\u8D8A\u5927");
		
		
		 /**该代码块是设计界面中的表格的*/
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI | SWT.FULL_SELECTION );   //如果需要复选框 | SWT.CHECK
			
		table.setBounds(32, 86, 550, 433);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
			
		TableColumn ui_tcl_0 = new TableColumn(table, SWT.NONE);	
		ui_tcl_0.setWidth(82);
		ui_tcl_0.setText("id");
			
			
			
		TableColumn ui_tcl_1 = new TableColumn(table, SWT.NONE);
		ui_tcl_1.setWidth(84);
		ui_tcl_1.setText("\u54C1\u724C");
			
		TableColumn ui_tcl_2 = new TableColumn(table, SWT.NONE);
		ui_tcl_2.setWidth(100);
		ui_tcl_2.setText("\u578B\u53F7");
		
		TableColumn ui_tcl_3 = new TableColumn(table, SWT.NONE);
		ui_tcl_3.setWidth(100);
		ui_tcl_3.setText("\u65E5\u79DF\u91D1");
			
		TableColumn ui_tcl_4 = new TableColumn(table, SWT.NONE);
		ui_tcl_4.setWidth(100);
		ui_tcl_4.setText("\u4EF7\u503C");
			
		TableColumn ui_tcl_5 = new TableColumn(table, SWT.NONE);
		ui_tcl_5.setWidth(100);
		ui_tcl_5.setText("\u53EF\u79DF\u6027");
			
		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ui_tcl_0.setText("序号");
				ui_tcl_1.setText("品牌");
				ui_tcl_2.setText("型号");
				ui_tcl_3.setText("数量");
				ui_tcl_4.setText("价值");
				ui_tcl_5.setText("可租性");
				
			/***
			 * 清空数据
			 * 释放item
			 * **/
				/**防止出现重复数据,清空一下table*/
				table.clearAll();//这个地方应该会有bug
				   TableItem tableItems[] = table.getItems();//得到所有的tableItem
		            for(int i = 0; i<tableItems.length; i++)
		            {
		                tableItems[i].dispose();//释放
		            }

				CarService cars = new CarService();
				List<Car> list = cars.queryCars();
				TableItem item = null; 
				/*这里有个bug隐患,当数据过多的时候,可能无法完全展示,或者出现越界情况**/
				for (int row = 0; row < list.size()  ;row++) { 
					item = new TableItem(table, SWT.NONE); 
						  item.setText(0, list.get(row).getCar_id().toString());
						  item.setText(1, list.get(row).getCar_brand());
						  item.setText(2, list.get(row).getCar_model());
						  item.setText(3, String.valueOf(list.get(row).getMoney_day()));
						  item.setText(4, String.valueOf(list.get(row).getCar_price()));
						  item.setText(5, list.get(row).getStatus());
				}
			
			}
		});
		button.setBounds(601, 123, 144, 30);
		button.setText("\u67E5\u770B\u6240\u6709\u6C7D\u8F66");
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				ui_tcl_0.setText("序号");
				ui_tcl_1.setText("品牌");
				ui_tcl_2.setText("型号");
				ui_tcl_3.setText("数量");
				ui_tcl_4.setText("价值");
				ui_tcl_5.setText("可租性");
				
				/**防止出现重复数据,清空一下table*/
				table.clearAll();//这个地方应该会有bug
				   TableItem tableItems[] = table.getItems();//得到所有的tableItem
		            for(int i = 0; i<tableItems.length; i++)
		            {
		                tableItems[i].dispose();//释放
		            }

				String condition = ui_condition.getText();
				CarService cars = new CarService();
				List<Car> list = cars.queryCarsByID(condition);
				if(list.size() != 0) {
					TableItem item = null; 
					for (int row = 0; row < list.size() ;row++) { 
						item = new TableItem(table, SWT.NONE); 
							  item.setText(0, list.get(row).getCar_id().toString());
							  item.setText(1, list.get(row).getCar_brand());
							  item.setText(2, list.get(row).getCar_model());
							  item.setText(3, String.valueOf(list.get(row).getMoney_day()));
							  item.setText(4, String.valueOf(list.get(row).getCar_price()));
							  item.setText(5, list.get(row).getStatus());
					}
				}else {
					System.out.println("为空");
					ui_tips.setText("请输入筛选条件!");
					try {
						Thread.sleep(1000);
						ui_tips.setText("当前是管理员权限,权利越大,责任越大!");
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
			}
		});
		btnNewButton.setBounds(601, 171, 144, 30);
		btnNewButton.setText("\u6839\u636EID\u7D22\u5F15\u67E5\u770B");
		
		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addCar.open();
			}
		});
		btnNewButton_1.setBounds(601, 219, 144, 30);
		btnNewButton_1.setText("\u6DFB\u52A0\u6C7D\u8F66");
		
		Button button_1 = new Button(shell, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
			}
		});
		button_1.setBounds(601, 273, 144, 30);
		button_1.setText("\u4FEE\u6539\u6C7D\u8F66");
		
		Button button_2 = new Button(shell, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ui_tcl_0.setText("序号");
				ui_tcl_1.setText("车辆");
				ui_tcl_2.setText("用户");
				ui_tcl_3.setText("开始日期");
				ui_tcl_4.setText("结束日期");
				ui_tcl_5.setText("金额");
				
				List<RentInfo> list = rentService.showAllRencentRecord();
				if(list.size() != 0) {
					TableItem item = null; 
					for (int row = 0; row < list.size() ;row++) { 
						item = new TableItem(table, SWT.NONE); 
							  item.setText(0, list.get(row).getRent_id().toString());
							  item.setText(1, list.get(row).getCar_id().toString());
							  item.setText(2, list.get(row).getUser_id().toString());
							  item.setText(3, String.valueOf(list.get(row).getstart_date().toString()));
							  item.setText(4, String.valueOf(list.get(row).getEnd_date().toString()));
							  item.setText(5, list.get(row).getMoney().toString());
					}
				}
			}
		});
		button_2.setBounds(601, 332, 144, 30);
		button_2.setText("\u67E5\u770B\u6240\u6709\u79DF\u8D41\u8BB0\u5F55");
		
		Button button_3 = new Button(shell, SWT.NONE);
		button_3.setToolTipText("\u8BF7\u5728\u9650\u5B9A\u6761\u4EF6\u5904\u8F93\u5165\u7528\u6237\u540D");
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				List<RentInfo> list = rentService.getRecordByUserName(ui_condition.getText().toString());
				// 方案1: 可以使用id查询rentService.getRecordByUserId(Integer.parseInt(ui_condition.getText()));
				ui_tcl_0.setText("序号");
				ui_tcl_1.setText("车辆");
				ui_tcl_2.setText("用户");
				ui_tcl_3.setText("开始日期");
				ui_tcl_4.setText("结束日期");
				ui_tcl_5.setText("金额");
				if(list.size() != 0) {
					TableItem item = null; 
					for (int row = 0; row < list.size() ;row++) { 
						item = new TableItem(table, SWT.NONE); 
							  item.setText(0, list.get(row).getRent_id().toString());
							  item.setText(1, list.get(row).getCar_id().toString());
							  item.setText(2, list.get(row).getUser_id().toString());
							  item.setText(3, String.valueOf(list.get(row).getstart_date().toString()));
							  item.setText(4, String.valueOf(list.get(row).getEnd_date().toString()));
							  item.setText(5, list.get(row).getMoney().toString());
					}
				}
			}
		});
		button_3.setBounds(601, 392, 144, 30);
		button_3.setText("\u67E5\u770B\u6307\u5B9A\u7528\u6237\u8BB0\u5F55");
		
		Button button_4 = new Button(shell, SWT.NONE);
		button_4.setToolTipText("\u8BF7\u5728\u9650\u5B9A\u6761\u4EF6\u5904\u8F93\u5165\u8F66\u8F86\u540D\u79F0");
		button_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				List<RentInfo> list = rentService.getRecordByCarId(Integer.parseInt(ui_condition.getText()));
				
				ui_tcl_0.setText("序号");
				ui_tcl_1.setText("车辆");
				ui_tcl_2.setText("用户");
				ui_tcl_3.setText("开始日期");
				ui_tcl_4.setText("结束日期");
				ui_tcl_5.setText("金额");
				if(list.size() != 0) {
					TableItem item = null; 
					for (int row = 0; row < list.size() ;row++) { 
						item = new TableItem(table, SWT.NONE); 
							  item.setText(0, list.get(row).getRent_id().toString());
							  item.setText(1, list.get(row).getCar_id().toString());
							  item.setText(2, list.get(row).getUser_id().toString());
							  item.setText(3, String.valueOf(list.get(row).getstart_date().toString()));
							  item.setText(4, String.valueOf(list.get(row).getEnd_date().toString()));
							  item.setText(5, list.get(row).getMoney().toString());
					}
				}
				
			}
		});
		button_4.setBounds(601, 448, 144, 30);
		button_4.setText("\u67E5\u770B\u6307\u5B9A\u6C7D\u8F66\u8BB0\u5F55");
		
		ui_condition = new Text(shell, SWT.BORDER);
		ui_condition.setBounds(646, 57, 99, 26);
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setBounds(564, 60, 76, 20);
		label_1.setText("\u9650\u5B9A\u6761\u4EF6");
		
		
		
		//添加行具值得出测试代码
//		for (int i = 0; i < 10; i++) {
//			new TableItem(table, SWT.NONE).setText(2,"Test");
//		}

	}
}

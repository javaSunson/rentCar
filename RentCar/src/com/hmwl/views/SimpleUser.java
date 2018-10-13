package com.hmwl.views;

import java.io.InputStream;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;

import com.hmwl.dao.CommonDao;
import com.hmwl.entity.Car;
import com.hmwl.entity.RentInfo;
import com.hmwl.service.CarService;
import com.hmwl.service.RentService;
import com.hmwl.service.UserService;
import com.hmwl.utils.ChangeSkin;

public class SimpleUser {

	protected Shell shell;
	protected RentCarView rentcar = new RentCarView();
	RebackCarView rbv = new RebackCarView();
	Display display = null;
	protected RentInfoView rentinfoview = new RentInfoView();
	private Table table;
	CommonDao cd = new CommonDao();
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SimpleUser window = new SimpleUser();
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
		shell.setText("普通账户");
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		/**换肤功能已经实现*/
		String bgpath = ChangeSkin.getCurrSkin();
		InputStream bg = this.getClass().getResourceAsStream(bgpath);
		
		InputStream reimg = this.getClass().getResourceAsStream("/Img/icon1.png");
		shell.setBackgroundImage(new Image(display,bg));
		shell.setImage(new Image(display, reimg));
		
		Label ui_showres = new Label(shell, SWT.NONE);
		ui_showres.setForeground(SWTResourceManager.getColor(SWT.COLOR_MAGENTA));
		ui_showres.setBounds(20, 21, 245, 20);
		

		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				CarService cs = new CarService();
				java.util.List<Car> data = cs.queryCars();
				if(data.size() != 0) {
						TableItem item = null; 
						for (int row = 0; row < data.size() ;row++) { 
							item = new TableItem(table, SWT.NONE); 
							 item.setText(0, data.get(row).getCar_id().toString());
							  item.setText(1, data.get(row).getCar_brand());
							  item.setText(2, data.get(row).getCar_model());
							  item.setText(3, data.get(row).getCar_number());
							  item.setText(4, String.valueOf(data.get(row).getMoney_day()));
							  item.setText(5, String.valueOf(data.get(row).getCar_price()));
							  item.setText(6, data.get(row).getStatus());
						}
				}
				
				ui_showres.setText("所有结果:当前共有"+(data.size())+"条记录");
				/**---------待优化---------*/
				
			}
		});
		button.setBounds(83, 421, 115, 30);
		button.setText("\u67E5\u8BE2\u6240\u6709\u6C7D\u8F66");
		
		Button button_1 = new Button(shell, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			CarService carser = new CarService();
			boolean flag = false;
			String way = "";
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(flag) {
					//carser.sortPrice("asc");
					way = "asc";//升序
				}else {
					//carser.sortPrice("desc");
					way = "desc";//降序
				}
				flag=!flag;
				/***
				 *清空视图表格
				 */
				table.clearAll();//这个地方应该会有bug
				   TableItem tableItems[] = table.getItems();//得到所有的tableItem
		            for(int i = 0; i<tableItems.length; i++)
		            {
		                tableItems[i].dispose();//释放
		            }
		            
				java.util.List<Car> data = carser.sortPrice(way);
				if(data.size() != 0) {
					TableItem item = null; 
					for (int row = 0; row < data.size() ;row++) { 
						item = new TableItem(table, SWT.NONE); 
						 item.setText(0, data.get(row).getCar_id().toString());
						  item.setText(1, data.get(row).getCar_brand());
						  item.setText(2, data.get(row).getCar_model());
						  item.setText(3, data.get(row).getCar_number());
						  item.setText(4, String.valueOf(data.get(row).getMoney_day()));
						  item.setText(5, String.valueOf(data.get(row).getCar_price()));
						  item.setText(6, data.get(row).getStatus());
					}
			}
			ui_showres.setText((flag==true?"价格升序:":"价格降序")+":当前共有"+(data.size())+"条记录");
			}
			
		});
		button_1.setBounds(202, 421, 115, 30);
		button_1.setText("\u6309\u4EF7\u683C\u6392\u5217");
		
		Button button_2 = new Button(shell, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			CarService carser = new CarService();
			boolean flag = false;
			String way = "";
			
			
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				
				if(flag) {
					//carser.sortPrice("asc");
					way = "asc";//升序
				}else {
					//carser.sortPrice("desc");
					way = "desc";//降序
				}
				flag=!flag;
				java.util.List<Car> data = carser.sortPrice(way);
				
				/***
				 *清空视图表格
				 */
				table.clearAll();//这个地方应该会有bug
				   TableItem tableItems[] = table.getItems();//得到所有的tableItem
		            for(int i = 0; i<tableItems.length; i++)
		            {
		                tableItems[i].dispose();//释放
		            }
				if(data.size() != 0) {
					TableItem item = null; 
					for (int row = 0; row < data.size() ;row++) { 
						item = new TableItem(table, SWT.NONE); 
							  item.setText(0, data.get(row).getCar_id().toString());
							  item.setText(1, data.get(row).getCar_brand());
							  item.setText(2, data.get(row).getCar_model());
							  item.setText(3, data.get(row).getCar_number());
							  item.setText(4, String.valueOf(data.get(row).getMoney_day()));
							  item.setText(5, String.valueOf(data.get(row).getCar_price()));
							  item.setText(6, data.get(row).getStatus());
					}
			}
			
				ui_showres.setText("所有结果:当前共有"+(data.size())+"条记录");
			}
			
		});
		button_2.setBounds(322, 421, 115, 30);
		button_2.setText("\u6309\u7167\u54C1\u724C\u8FC7\u6EE4");
		
		Button ui_b_rentcar = new Button(shell, SWT.NONE);
		ui_b_rentcar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				/**这里完成了初始版本的租车,按理说,租车成功后,要在车的信息表里,减去数量
				 * 当数量小于0的时候,就不能租车了
				 * **/
				rentcar.open();
			}
		});
		ui_b_rentcar.setBounds(423, 421, 115, 30);
		ui_b_rentcar.setText("  \u79DF\u8D41\u8F66\u8F86");
		
		Button button_4 = new Button(shell, SWT.NONE);
		button_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				rbv.open();
			}
		});
		button_4.setBounds(534, 421, 115, 30);
		button_4.setText("\u5F52\u8FD8\u8F66\u8F86");
		
		Button button_3 = new Button(shell, SWT.NONE);
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				rentinfoview.open();
			}
		});
		button_3.setBounds(82, 464, 116, 30);
		button_3.setText("\u67E5\u770B\u6211\u7684\u79DF\u8D41");
		
		Combo combo = new Combo(shell, SWT.NONE);
		combo.setBounds(82, 515, 116, 28);
//		String [] data = {"大众","奥迪","雪佛兰","宝马","路虎"};
//		combo.setItems(data);
		String sql = "select car_brand from car";
		List<Car> typelist = cd.commonQuery(new Car(), sql);
		String [] arrr=new String [typelist.size()];
		for (int i=0;i<typelist.size();i++) {
			arrr[i] = typelist.get(i).getCar_brand();
		}
		combo.setItems(arrr);
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				/*获取combo选中的值**/
				String string  = combo.getText();
//				RentService rents = new RentService();
//				List<RentInfo> list = rents.getRecordByCarBrand(string);
				

				/***
				 *清空视图表格
				 */
				table.clearAll();//这个地方应该会有bug
				   TableItem tableItems[] = table.getItems();//得到所有的tableItem
		            for(int i = 0; i<tableItems.length; i++)
		            {
		                tableItems[i].dispose();//释放
		            }
				CarService cService = new CarService();
				List<Car> data = cService.getRecordByCarBrand(string);
				if(data.size() != 0) {
					TableItem item = null; 
					for (int row = 0; row < data.size() ;row++) { 
						item = new TableItem(table, SWT.NONE); 
						 item.setText(0, data.get(row).getCar_id().toString());
						  item.setText(1, data.get(row).getCar_brand());
						  item.setText(2, data.get(row).getCar_model());
						  item.setText(3, data.get(row).getCar_number());
						  item.setText(4, String.valueOf(data.get(row).getMoney_day()));
						  item.setText(5, String.valueOf(data.get(row).getCar_price()));
						  item.setText(6, data.get(row).getStatus());
					}
				}
				
			}
		});
		btnNewButton.setBounds(215, 513, 98, 30);
		btnNewButton.setText("\u7B5B\u9009\u54C1\u724C");
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(20, 47, 739, 352);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("\u7F16\u53F7");
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("\u54C1\u724C");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("\u578B\u53F7");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(100);
		tblclmnNewColumn_2.setText("\u6570\u91CF");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_3.setWidth(100);
		tblclmnNewColumn_3.setText("\u65E5\u79DF\u91D1");
		
		TableColumn tblclmnNewColumn_4 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_4.setWidth(100);
		tblclmnNewColumn_4.setText("\u4EF7\u503C");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("\u53EF\u79DF\u6027");
		
	
	}
}

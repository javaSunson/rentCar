package com.hmwl.views;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.hmwl.entity.Car;
import com.hmwl.service.CarService;

import org.eclipse.swt.widgets.Button;

import java.io.InputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;

public class SimpleUserOld {

	protected Shell shell;
	protected RentCarView rentcar = new RentCarView();
	RebackCarView rbv = new RebackCarView();
	Display display = null;
	protected RentInfoView rentinfoview = new RentInfoView();
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SimpleUserOld window = new SimpleUserOld();
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
		
		InputStream bg = this.getClass().getResourceAsStream("/Img/bg.jpg");
		InputStream reimg = this.getClass().getResourceAsStream("/Img/icon1.png");
		shell.setBackgroundImage(new Image(display,bg));
		shell.setImage(new Image(display, reimg));
		
		
		List ui_showlist = new List(shell, SWT.BORDER);
		ui_showlist.setBounds(82, 47, 618, 368);
		
		Label ui_showres = new Label(shell, SWT.NONE);
		ui_showres.setForeground(SWTResourceManager.getColor(SWT.COLOR_MAGENTA));
		ui_showres.setBounds(82, 21, 183, 20);
		

		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				CarService cs = new CarService();
				java.util.List<Car> data = cs.queryCars();
				/**---------待优化---------*/
				Object[] array = data.toArray();
				String [] sarr = new String [array.length+1];
				sarr[0]="编号:    品牌:     型号:    数量:     日租金:     价值:    租借状态:";
				for (int i = 0 ; i < array.length;i++) {
					 sarr[i+1]= array[i].toString();
				}
				ui_showlist.setItems(sarr);
				ui_showlist.setVisible(true);
				ui_showres.setText("所有结果:当前共有"+(sarr.length-1)+"条记录");
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
				java.util.List<Car> data = carser.sortPrice(way);
				
				Object[] array = data.toArray();
				String [] sarr = new String [array.length+1];
				sarr[0]="编号:    品牌:     型号:    数量:     日租金:     价值:    租借状态:";
				for (int i = 0 ; i < array.length;i++) {
					 sarr[i+1]= array[i].toString();
				}
				ui_showlist.setItems(sarr);
				ui_showlist.setVisible(true);
				ui_showres.setText((flag==true?"价格升序:":"价格降序")+":当前共有"+(sarr.length-1)+"条记录");
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
				
				Object[] array = data.toArray();
				String [] sarr = new String [array.length+1];
				sarr[0]="编号:    品牌:     型号:    车牌号:     日租金:     价值:    租借状态:";
				for (int i = 0 ; i < array.length;i++) {
					 sarr[i+1]= array[i].toString();
				}
				ui_showlist.setItems(sarr);
				ui_showlist.setVisible(true);
				ui_showres.setText("所有结果:当前共有"+(sarr.length-1)+"条记录");
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
		button_3.setBounds(82, 464, 116, 30);
		button_3.setText("\u67E5\u770B\u6211\u7684\u79DF\u8D41");
		
		Combo combo = new Combo(shell, SWT.NONE);
		combo.setBounds(82, 515, 116, 28);
		String [] data = {"大众","奥迪","雪佛兰","宝马","路虎"};
		combo.setItems(data);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setBounds(215, 513, 98, 30);
		btnNewButton.setText("\u7B5B\u9009\u54C1\u724C");
		
	
	}
}

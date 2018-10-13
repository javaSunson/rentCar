--创建用户使用的id 队列
create sequence auto_id
start with 1
increment by 1
nocycle
noMaxvalue
cache 20;

--创建用户表 userid,username,password,phone,address,isadmin 租车需要身份证吗?
create table users(
userid number(3),
username varchar2(30),
password varchar2(30),
address varchar2(30),
phone varchar2(12),
isadmin number(2)
);
--初始化用户
desc users;
insert into users values(auto_id.nextval,'admin','888888','山东省济宁市','18888888888',1);
insert into users values(auto_id.nextval,'erha','123456','山东省济宁市','18888888888',1);
--创建汽车使用的id队列
create sequence car_next
start with 1
increment by 1
nocycle
noMaxvalue
cache 20
;
--创建汽车表
create table car(
car_id number(3) primary key,
car_brand varchar2(30),
car_model varchar2(30),
car_number varchar2(30),
money_day number(6,2),
car_price number(8,2),
status varchar2(10)
);
--初始化车表
desc car;
insert into car values(car_next.nextval,'路虎','揽胜','鲁H-88888',520.00,750000.00,'true');
insert into car values(car_next.nextval,'宝马','x7','鲁H-66888',480.00,720000.00,'true');
insert into car values(car_next.nextval,'路虎','星脉','鲁H-10102',470.00,650000.00,'true');
insert into car values(car_next.nextval,'奥迪','A6','鲁H-32128',370.00,570000.00,'true');
--insert into car values(car_next.nextval,'本田XR_V','鲁A-22888','true');
--insert into car values(car_next.nextval,'Jeep','鲁Q-23888','true');
create table rentinfo(
  rent_id number(5) primary key,
  user_id number(5),
  car_id number(5),
  start_date date,
  end_date date,
  money number(10)
);
--初始化

--测试
drop table users;
select * from car;
select * from rentinfo;
select * from users;
commit;
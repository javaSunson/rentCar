--�����û�ʹ�õ�id ����
create sequence auto_id
start with 1
increment by 1
nocycle
noMaxvalue
cache 20;

--�����û��� userid,username,password,phone,address,isadmin �⳵��Ҫ���֤��?
create table users(
userid number(3),
username varchar2(30),
password varchar2(30),
address varchar2(30),
phone varchar2(12),
isadmin number(2)
);
--��ʼ���û�
desc users;
insert into users values(auto_id.nextval,'admin','888888','ɽ��ʡ������','18888888888',1);
insert into users values(auto_id.nextval,'erha','123456','ɽ��ʡ������','18888888888',1);
--��������ʹ�õ�id����
create sequence car_next
start with 1
increment by 1
nocycle
noMaxvalue
cache 20
;
--����������
create table car(
car_id number(3) primary key,
car_brand varchar2(30),
car_model varchar2(30),
car_number varchar2(30),
money_day number(6,2),
car_price number(8,2),
status varchar2(10)
);
--��ʼ������
desc car;
insert into car values(car_next.nextval,'·��','��ʤ','³H-88888',520.00,750000.00,'true');
insert into car values(car_next.nextval,'����','x7','³H-66888',480.00,720000.00,'true');
insert into car values(car_next.nextval,'·��','����','³H-10102',470.00,650000.00,'true');
insert into car values(car_next.nextval,'�µ�','A6','³H-32128',370.00,570000.00,'true');
--insert into car values(car_next.nextval,'����XR_V','³A-22888','true');
--insert into car values(car_next.nextval,'Jeep','³Q-23888','true');
create table rentinfo(
  rent_id number(5) primary key,
  user_id number(5),
  car_id number(5),
  start_date date,
  end_date date,
  money number(10)
);
--��ʼ��

--����
drop table users;
select * from car;
select * from rentinfo;
select * from users;
commit;
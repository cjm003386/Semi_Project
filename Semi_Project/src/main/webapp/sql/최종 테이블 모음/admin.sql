create table admin(
admin_id		varchar2(20) primary key,
admin_password	varchar2(20) not null,
admin_date		date default sysdate,
admin_name		varchar2(15))
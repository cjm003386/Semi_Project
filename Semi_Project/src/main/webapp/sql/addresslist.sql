create table addresslist(
	addresslist_num number,
	address_id varchar2(20) references customer(id) on delete cascade,
	address_name varchar2(15),
	address_receiver varchar2(15),
	address_post varchar2(5),
	address_phone varchar2(14),
	address1 varchar2(150),
	address2 varchar2(50),
	primary key(addresslist_num)
);
create sequence addresslist_seq;
alter sequence order_info_seq nocache

select * from addresslist;

drop table addresslist purge;
drop sequence addresslist_seq
--삭제하려면 id랑 배송지 번호 pk가 있어야 함...
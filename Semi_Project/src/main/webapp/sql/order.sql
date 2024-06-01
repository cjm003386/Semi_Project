드
--order_code
-- - vam_order 테이블의 기본키입니다. 
--receiver 
--- 배송을 받는 사람 이름입니다.
--id
-- - 주문하는 사람의 아이디입니다.
--post 우편번호
--address1,2
-- - 주문한 사람이 배송받을 주소입니다.
--orderstate
-- - 주문 상태입니다.
-- - '배송 준비', '배송 취소'(, '배송 중', '배송 완료) 값이 들어갈 것입니다.
-- - 주문을 하게 되면 '배송준비' 상태가 되고, 주문 취소를 하면 '배송취소'상태가 됩니다. >> 주문서 작성페이지 input type hidden으로 배송준비 값을 받아와 넘겨주기
--delivercost 
-- - 배송비입니다.
--usePoint
-- - 주문 시 회원이 사용한 포인트입니다.
--orderDate 
-- - 주문한 날짜입니다.
create table order_info(
    order_code number primary key,
    receiver varchar2(50) not null,
    id varchar2(20)  references customer(id),
    post number not null,
    address1 varchar2(100) not null,
    address2 varchar2(100) not null,
    orderstate varchar2(30) not null,
    delivercost number not null,
    usepoint number not null,
    orderDate date default sysdate,
);

--orderitem_code
-- - order_item 테이블의 기본키입니다.
-- - DB에서 생성해주는 기본키 기능을 사용할 것입니다.

--order_code
-- - vma_order의 기본키 값이 들어가는 외래 키입니다.

--product_code 
-- - vam_book의 기본키 값이 들어가는 외래 키입니다.

--bookCount
-- - 주문한 상품의 수량입니다.

--bookPrice
-- - 주문한 상품 한 개의 가격입니다. (할인 적용 X)

--bookDiscount
-- - 주문한 상품의 할인율입니다.
create table order_info(--하나의 주문정보를 담는 테이블
    order_code number primary key,
    receiver varchar2(50) not null,
    id varchar2(20) references customer(id),
    post varchar2(5) not null,
    address1 varchar2(100) not null,
    address2 varchar2(100) not null,
    orderstate varchar2(30) default '배송전',--배송취소
    order_cost number not null,
    orderDate date default sysdate
);
create sequence order_info_seq

select * from order_info;

create table order_item(--한 주문에서 각 상품의 정보를 담는 테이블
    orderitem_code number primary key,
    order_code number,
    product_code number,
    productcount number not null,
    productprice number not null,
    FOREIGN KEY (order_code) REFERENCES order_info(order_code),
    FOREIGN KEY (product_code) REFERENCES product(product_code)
);
create sequence order_item_seq
select * from order_item;

insert into order_info values (order_info_seq.nextval, '홍길동', 'id', '11111', '서울아파트', '10동 10호','배송전',10000,sysdate)
insert into order_item values (order_item_seq.nextval, 1, 1, 2, 3000)
insert into order_item values (order_item_seq.nextval, 1, 2, 1, 4000)




--주문내역 보는 쿼리문
select * from order_item
inner join order_info
on order_item.order_code = order_info.order_code
inner join product
on product.product_code = order_item.product_code
where order_info.id='id';

select o.id, o.order_date, p.product_image, p.product_name, oit.product_count, oit.product_price, oit.orderstate, p.product_code
from product p, order_info o, order_item oit
where p.product_code=oit.product_code
and o.order_code=oit.order_code
and o.id='id';
ID         ORDERDAT PRODUCT_IM PRODUCT_NA PRODUCTCOUNT PRODUCTPRICE ORDERSTATE
---------- -------- ---------- ---------- ------------ ------------ ----------
test       22/08/08 사진       반팔                  2        10000 	배송전
test       22/08/08 사진1      긴팔                  1         6000 	배송전


update (select o.id, o.orderDate, p.product_image, p.product_name, oit.productcount, oit.productprice, o.orderstate
from product p, order_info o, order_item oit
where p.product_code=oit.product_code
and o.order_code=oit.order_code
and o.id=?) oit set o.orderstate='배송취소' where oit.orderitem_code=1 ;
 




   select o.id, o.orderDate, p.product_image, p.product_name, oit.productcount, oit.productprice, o.orderstate
   from product p, order_info o, order_item oit
   where p.product_code=oit.product_code
   and o.order_code=oit.order_code
   and o.id='id'
   and o.orderstate='배송전';
  
--상품 테이블 예시
  PRODUCT_CODE PRODUCT_NA PRODUCT_PRICE PRODUCT_IM
------------ ---------- ------------- ----------
           1 반팔                5000 사진
           2 긴팔                6000 사진1
--주문 정보 테이블 예시
ORDER_CODE RECEIVER   ID               POST ADDRESS1   ADDRESS2   ORDERSTATE ORDER_COST ORDERDAT
---------- ---------- ---------- ---------- ---------- ---------- ---------- ---------- --------
         1 홍길방     test            12345 서울시     행복도시   배송전          16000 22/08/08
--주문 상품 테이블 예시         
ORDERITEM_CODE ORDER_CODE PRODUCT_CODE PRODUCTCOUNT PRODUCTPRICE
-------------- ---------- ------------ ------------ ------------
             1          1            1            2        10000
             2          1            2            1         6000
             
             
             
ORDERITEM_CODE ORDER_CODE PRODUCT_CODE PRODUCTCOUNT PRODUCTPRICE ORDER_CODE RECEIVER   ID               POST ADDRESS1   ADDRESS2   ORDERSTATE ORDER_COST ORDERDAT PRODUCT_CODE PRODUCT_NA PRODUCT_PRICE PRODUCT_IM
-------------- ---------- ------------ ------------ ------------ ---------- ---------- ---------- ---------- ---------- ---------- ---------- ---------- -------- ------------ ---------- ------------- ----------
             1          1            1            2        10000          1 홍길방     test            12345 서울시     행복도시   배송전          16000 22/08/08            1 반팔                5000 사진
             2          1            2            1         6000          1 홍길방     test            12345 서울시     행복도시   배송전          16000 22/08/08            2 긴팔                6000 사진1
             
             
             
   
   
   drop table order_info purge;
   drop sequence order_info_seq;
   
   create sequence order_info_seq
   
   create table order_info(--하나의 주문정보를 담는 테이블
    order_code number primary key,
    id varchar2(20) references customer(id),
    payment_option varchar2(20),
    address1 varchar2(150) not null,
    address2 varchar2(150) not null,
    post varchar2(5) not null,
    receiver_name varchar2(15) not null,
    receiver_phone varchar2(13) not null,
    order_cost number not null,
    delivery_message varchar2(150),
    order_date date default sysdate
	);
	
insert into order_info values (order_info_seq.nextval,'id', '무통장 입금','서울아파트', '10동 10호', '11111', '홍길동','01012341234',50000,'문 앞에 놔주세요',sysdate)
	
	drop sequence order_item_seq;
	drop table order_item purge;
	
    create sequence order_item_seq
    
    create table order_item(--한 주문에서 각 상품의 정보를 담는 테이블
    orderitem_code number primary key,
    order_code number,
    product_code number,
    product_count number not null,
    product_price number not null,
    orderstate varchar2(30) default '배송 전',--배송취소
    FOREIGN KEY (order_code) REFERENCES order_info(order_code),
    FOREIGN KEY (product_code) REFERENCES product(product_code)
	);

insert into order_item values (order_item_seq.nextval, 1, 1, 2, 30000, '배송 전')
insert into order_item values (order_item_seq.nextval, 1, 2, 1, 20000, '배송 전')
insert into order_item values (order_item_seq.nextval, 2, 4, 2, 30000, '배송 전')
insert into order_item values (order_item_seq.nextval, 2, 3, 1, 20000, '배송 전')

insert into order_info values (order_info_seq.nextval,'id', '무통장 입금','서울아파트', '10동 10호', '11111', '홍길동','01012341234',10000,'문 앞에 놔주세요',sysdate)
insert into order_item values (order_item_seq.nextval, 3, 1, 2, 30000, '배송 전')




update order_item set orderstate = '배송 완료'
where orderitem_code=3;
select * from order_info 

select * from order_item;
   select o.id, o.order_date, p.product_image, p.product_name, oit.product_count, oit.product_price, oit.orderstate
   from product p, order_info o, order_item oit
   where p.product_code=oit.product_code
   and o.order_code=oit.order_code
   and o.id='id'
   and oit.orderstate='배송 전';

   --------------------------------------------------------
   select count(*)
   from product p, order_info o, order_item oit
   where p.product_code=oit.product_code
   and o.order_code=oit.order_code
   and o.id='id'
   and oit.orderstate='배송 취소';

   
   select count(*)
   from product p, order_info o, order_item oit
   where p.product_code=oit.product_code
   and o.order_code=oit.order_code
   and o.id='id'
   and (1=2 or oit.orderstate='배송 전' or oit.orderstate='배송 완료');
   
   
   select * 
	from
	(select rownum rnum, j.* 
	from (select o.id, o.order_date, p.product_image, p.product_name, p.product_code, 
	oit.product_count, oit.product_price, oit.orderitem_code, oit.orderstate 
		from product p, order_info o, order_item oit 
			where p.product_code=oit.product_code 
				and o.order_code=oit.order_code 
			and o.id=id
			order by o.order_date desc) j 
		where rownum<=1) 
		where rnum>=1 and rnum<=10
	
	select * from order_item
   
   --------------------------------------------------------
  select board.*, nvl(cnt,0) cnt
			  		from board left outer join(select comment_board_num,count(*) cnt
		 									from comm
		  									group by comment_board_num)
		 		on board_num=comment_board_num
			  		order by board_re_ref desc, 
			  		board_re_seq asc

	select o.id, o.order_date, p.product_image, p.product_name, 
	oit.product_count, oit.product_price, oit.orderitem_code, oit.orderstate 
	from product p, order_info o, order_item oit
	where p.product_code=oit.product_code
	and o.order_code=oit.order_code
	and o.id=id
	
	
	
	
	--주문내역 페이징 처리-----------------------------------------------
	
	select o.id, o.order_date, p.product_image, p.product_name, oit.product_count, oit.product_price, oit.orderstate, p.product_code
	from product p, order_info o, order_item oit
	where p.product_code=oit.product_code
	and o.order_code=oit.order_code
	and o.id='id';
	
	
	select *
	from(select rownum rnum, j.*
		 from (select o.id, o.order_date, p.product_image, 
	oit.product_count, oit.product_price, oit.orderitem_code, oit.orderstate 
	from product p, order_info o, order_item oit
	where p.product_code=oit.product_code
	and o.order_code=oit.order_code
	and o.id='id'
	order by o.order_date desc) j
	where rownum<=10)
	where rnum>=1 and rnum<=10;
	
	
	---------------------------------------------------------------
	---되는 코드
	select *
	from(select rownum rnum, j.*
		 from (select o.id, o.order_date, p.product_image, p.product_name, oit.product_count, 
		 oit.product_price, oit.orderstate, p.product_code
	from product p, order_info o, order_item oit
	where p.product_code=oit.product_code
	and o.order_code=oit.order_code
	and o.id='id'
	order by o.order_date desc) j
	where rownum<=10)
	where rnum>=1 and rnum<=10;
	--------------------------------------------------
	select *
	from(select rownum rnum, j.*
		 from (select o.id, o.order_date, p.product_image, p.product_name, p.product_code,
	oit.product_count, oit.product_price, oit.orderitem_code, oit.orderstate 
	from product p, order_info o, order_item oit
	where p.product_code=oit.product_code
	and o.order_code=oit.order_code
	and o.id='id'
	order by o.order_date desc) j
	where rownum<=10)
	where rnum>=1 and rnum<=10
	
	
	select *
	from(select rownum rnum, j.*
		 from (select * from order_item
	inner join order_info
		on order_item.order_code = order_info.order_code
	inner join product
	on product.product_code = order_item.product_code
	where order_info.id='id'
	order by o.order_date desc) j
	where rownum<=10)
	where rnum>=1 and rnum<=10;

	select *
	from(select rownum rnum, j.*
		 from (select o.id, o.order_date, p.product_image, p.product_name, oit.orderitem_code,
		 oit.product_count, oit.product_price, oit.orderstate, p.product_code
	from product p, order_info o, order_item oit
	where p.product_code=oit.product_code
	and o.order_code=oit.order_code
	and o.id='id'
	order by o.order_date desc) j
	where rownum<=10)
	where rnum>=1 and rnum<=10;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	select *
	from (select b.*, rownum rnum
	from (select * from member
	 where id != 'admin' 
	 and " + field + " like ? 
				order by id) b
					)
		where rnum between ? and ?
		
		"select *"
				+ "from (select rownum rnum, j.*"
				+ "	  from (select board.*, nvl(cnt,0) cnt"
				+ "	  		from board left outer join(select comment_board_num,count(*) cnt"
				+ "	  									from comm"
				+ "	  									group by comment_board_num)"
				+ "	  		on board_num=comment_board_num"
				+ "	  		order by board_re_ref desc, "
				+ "	  		board_re_seq asc) j "
				+ "	  	where rownum<=?"
				+ "	  	) "
				+ "where rnum>=? and rnum<=?";
			
	
	
	select * from order_item
	
	
	select *
	from(select rownum rnum, j.*
		 from (select o.id, o.order_date, p.product_image, p.product_name, 
	oit.product_count, oit.product_price, oit.orderitem_code, oit.orderstate 
	from product p, order_info o, order_item oit
	where p.product_code=oit.product_code
	and o.order_code=oit.order_code
	and o.id=id
	and oit.orderstate='배송 취소'
	order by o.order_date desc) j
	where rownum<=10)
	where rnum>=1 and rnum<=10;
	
	----------시퀀스 20씩 증가 안되게
	alter sequence 시퀀스명 nocache
	
	
	------------------정리본-----------------------
	drop sequence order_item_seq;
	drop table order_item purge;
	drop table order_info purge;
    drop sequence order_info_seq;
	최종 주문 정보 테이블

	--1) 주문 정보 테이블
   
   create sequence order_info_seq
   alter sequence order_info_seq nocache
   create table order_info(--하나의 주문정보를 담는 테이블
    order_code number primary key,
    id varchar2(20) references customer(id),
    payment_option varchar2(20),
    address1 varchar2(150) not null,
    address2 varchar2(150) not null,
    post varchar2(5) not null,
    receiver_name varchar2(15) not null,
    receiver_phone varchar2(13) not null,
    order_cost number not null,
    delivery_message varchar2(150),
    order_date date default sysdate
	);

	--2) 주문 상품 테이블 
	
    create sequence order_item_seq
    alter sequence order_item_seq nocache
    
    create table order_item(--한 주문에서 각 상품의 정보를 담는 테이블
    orderitem_code number primary key,
    order_code number,
    product_code number,
    product_count number not null,
    product_price number not null,
    orderstate varchar2(30) default '배송 전',--배송 취소/ 배송 완료
    FOREIGN KEY (order_code) REFERENCES order_info(order_code),
    FOREIGN KEY (product_code) REFERENCES product(product_code)
	);
	
	--주문하기를 눌렀을 때 order_info에 데이터 삽입이 이루어지고
	insert into order_info values (order_info_seq.nextval,'id', '무통장 입금','서울아파트', '10동 10호', '11111', '홍길동','01012341234',50000,'문 앞에 놔주세요',sysdate)
	--주문한 상품의 정보가 order_item 테이블에 삽입되어져야 한다.
	insert into order_item values (order_item_seq.nextval, 1, 1, 2, 10000, '배송 전')--orderitem_code PK, order_code(order_info 테이블의 order_info_seq.nextval로 들어간 번호), 주문한 상품의 상품코드(product테이블의 상품코드),
																					-- 상품의 가격(수량에 따라 달라져야 함), 주문 상태 (주문을 하면 배송 전이 들어가야 함) 
	insert into order_item values (order_item_seq.nextval, 1, 2, 1, 10000, '배송 전')
	insert into order_item values (order_item_seq.nextval, 1, 2, 1, 5000, '배송 전')
	--insert into order_item values (order_item_seq.nextval, 1, 2, 1, 15000, '배송 전')
	--insert into order_item values (order_item_seq.nextval, 1, 2, 1, 10000, '배송 전')
	--insert into order_item values (order_item_seq.nextval, 1, 3, 1, 10000, '배송 전')
	--insert into order_item values (order_item_seq.nextval, 1, 5, 1, 10000, '배송 전')
	--insert into order_item values (order_item_seq.nextval, 1, 4, 1, 10000, '배송 전')
	--insert into order_item values (order_item_seq.nextval, 1, 4, 1, 10000, '배송 전')
	--insert into order_item values (order_item_seq.nextval, 1, 4, 1, 10000, '배송 전')
	--insert into order_item values (order_item_seq.nextval, 1, 4, 1, 10000, '배송 전')
	
	insert into order_info values (order_info_seq.nextval,'id', '무통장 입금','서울아파트', '10동 10호', '11111', '홍길동','01012341234',50000,'문 앞에 놔주세요',sysdate)
	insert into order_item values (order_item_seq.nextval, 2, 1, 1, 10000, '배송 전')
	insert into order_item values (order_item_seq.nextval, 2, 3, 1, 10000, '배송 전')
	insert into order_item values (order_item_seq.nextval, 2, 5, 1, 10000, '배송 전')
	insert into order_item values (order_item_seq.nextval, 2, 4, 1, 10000, '배송 전')
	insert into order_item values (order_item_seq.nextval, 2, 2, 1, 15000, '배송 전')

	
	select * from product
	
	select count(*) from (select o.id, o.order_date, p.product_image, p.product_name, 
	oit.product_count, oit.product_price, oit.orderitem_code, oit.orderstate 
	from product p, order_info o, order_item oit
	where p.product_code=oit.product_code
	and o.order_code=oit.order_code
	and o.id=id
	and oit.orderstate='배송 전' or oit.orderstate='배송 완료')

	select count(*) from (select o.id, o.order_date, p.product_image, p.product_name, 
	oit.product_count, oit.product_price, oit.orderitem_code, oit.orderstate 
	from product p, order_info o, order_item oit
	where p.product_code=oit.product_code
	and o.order_code=oit.order_code
	and o.id=id
	and oit.orderstate='배송 취소')
	
	select * from order_info, order_item
	where order_info.order_code = order_item.order_code
	
	update (select * from order_info, order_item
	where order_info.order_code = order_item.order_code) j set j.order_date = sysdate
	where j.order_code =2
	
	select * from customer;
	
	select count(*)
	 from (select o.id, o.order_date, p.product_image, p.product_name, 
	oit.product_count, oit.product_price, oit.orderitem_code, oit.orderstate 
	from product p, order_info o, order_item oit 
	where p.product_code=oit.product_code 
	and o.order_code=oit.order_code 
	and o.id=id
	and oit.orderstate='배송 전' or oit.orderstate='배송 완료')
	
	select * from order_info
	select * from product
	
             
drop table product;
create table product(
	product_code number(5),
	product_name varchar2(200),
	product_price number(10),
	product_image varchar2(50),--이미지 파일 이름
	category_code number(5) references product_category(category_code),
	primary key(product_code)
)


drop sequence product_seq
create sequence product_seq start with 1 increment by 1;

insert into product (product_code, product_name, product_price, product_image, category_code) VALUES (product_seq.nextval,'나이키 티셔츠','50000','nike_tshirt',1);
insert into product (product_code, product_name, product_price, product_image, category_code) VALUES (product_seq.nextval,'디스이즈네버댓 긴팔티','28000','long tee',2);
insert into product (product_code, product_name, product_price, product_image, category_code) VALUES (product_seq.nextval,'트릴리온 시티보이 셔츠','46000','shirt',3);
insert into product (product_code, product_name, product_price, product_image, category_code) VALUES (product_seq.nextval,'솔티 코랄 니트','67000','knit',4);
insert into product (product_code, product_name, product_price, product_image, category_code) VALUES (product_seq.nextval,'커버낫 후디 집업','51000','hoodie',5);
insert into product (product_code, product_name, product_price, product_image, category_code) VALUES (product_seq.nextval,'챔피온 나일론 반바지','32000','shortpants',6);
insert into product (product_code, product_name, product_price, product_image, category_code) VALUES (product_seq.nextval,'디스퀘어드 청바지','90000','jean',7);
insert into product (product_code, product_name, product_price, product_image, category_code) VALUES (product_seq.nextval,'시티브리즈 썸머 슬랙스','79000','slacks',8);
insert into product (product_code, product_name, product_price, product_image, category_code) VALUES (product_seq.nextval,'배디 트레이닝 팬츠','52900','sweatpants',9);
insert into product (product_code, product_name, product_price, product_image, category_code) VALUES (product_seq.nextval,'RDVZ 데님 와이드 팬츠','108900','widepants',10);
insert into product (product_code, product_name, product_price, product_image, category_code) VALUES (product_seq.nextval,'무지 가디건','37000','cardigan',11);
insert into product (product_code, product_name, product_price, product_image, category_code) VALUES (product_seq.nextval,'로드존그레이 자켓','163700','jacket',12);
insert into product (product_code, product_name, product_price, product_image, category_code) VALUES (product_seq.nextval,'캐쥬얼 코트','150000','coat',13);
insert into product (product_code, product_name, product_price, product_image, category_code) VALUES (product_seq.nextval,'무스너클 발라스틱 패딩','1249900','padding',14);
insert into product (product_code, product_name, product_price, product_image, category_code) VALUES (product_seq.nextval,'톰브라운 클래식 블레이저','2444900','suit',15);
insert into product (product_code, product_name, product_price, product_image, category_code) VALUES (product_seq.nextval,'JESTINA 14k 다이아 반지','1349000','accessories',16);
insert into product (product_code, product_name, product_price, product_image, category_code) VALUES (product_seq.nextval,'페라가모 넥타이','233000','tie',17);
insert into product (product_code, product_name, product_price, product_image, category_code) VALUES (product_seq.nextval,'카리스 몬타포네 아델 토트백','11900000','bag',18);
insert into product (product_code, product_name, product_price, product_image, category_code) VALUES (product_seq.nextval,'프라다 버킷햇','796900','hat',19);
insert into product (product_code, product_name, product_price, product_image, category_code) VALUES (product_seq.nextval,'나이키 신발','98000','nike shoes',20);

--상품옵션, 카테고리
--create table product_option(
--	opt_code number,
--	product_code number references product(product_code),
--	opt_color varchar2,
--	opt_size varchar2
--)

drop table product_category
select * from product_category
create table product_category(
	category_code number(5),
	class1 varchar2(20),
	class2 varchar2(20),
	primary key(category_code)
)


create sequence product_category_seq;
drop sequence product_category_seq

--상품을 직접 insert (image : image경로에 들어갈 파일명)
 
insert into product_category(category_code, class1, class2) VALUES (product_category_seq.nextval,'top','short-tee');
insert into product_category(category_code, class1, class2) VALUES (product_category_seq.nextval,'top','long-tee');
insert into product_category(category_code, class1, class2) VALUES (product_category_seq.nextval,'top','shirts');
insert into product_category(category_code, class1, class2) VALUES (product_category_seq.nextval,'top','knit');
insert into product_category(category_code, class1, class2) VALUES (product_category_seq.nextval,'top','hoodie');
insert into product_category(category_code, class1, class2) VALUES (product_category_seq.nextval,'bottom','short-pants');
insert into product_category(category_code, class1, class2) VALUES (product_category_seq.nextval,'bottom','jeans');
insert into product_category(category_code, class1, class2) VALUES (product_category_seq.nextval,'bottom','slacks');
insert into product_category(category_code, class1, class2) VALUES (product_category_seq.nextval,'bottom','sweatpants');
insert into product_category(category_code, class1, class2) VALUES (product_category_seq.nextval,'bottom','wide-pants');
insert into product_category(category_code, class1, class2) VALUES (product_category_seq.nextval,'outer','cardigan');
insert into product_category(category_code, class1, class2) VALUES (product_category_seq.nextval,'outer','jacket');
insert into product_category(category_code, class1, class2) VALUES (product_category_seq.nextval,'outer','coat');
insert into product_category(category_code, class1, class2) VALUES (product_category_seq.nextval,'outer','padding');
insert into product_category(category_code, class1, class2) VALUES (product_category_seq.nextval,'outer','suit');
insert into product_category(category_code, class1, class2) VALUES (product_category_seq.nextval,'accessory','accessories');
insert into product_category(category_code, class1, class2) VALUES (product_category_seq.nextval,'accessory','tie');
insert into product_category(category_code, class1, class2) VALUES (product_category_seq.nextval,'accessory','bag');
insert into product_category(category_code, class1, class2) VALUES (product_category_seq.nextval,'accessory','hat');
insert into product_category(category_code, class1, class2) VALUES (product_category_seq.nextval,'accessory','shoes');


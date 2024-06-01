create table customer(
	id					varchar2(20) primary key,
	password			varchar2(20),
	name				varchar2(15),
	jumin				varchar2(14),
	gender				char(1)  check(gender in ('m','f')), 
	post				varchar2(5),
	address				varchar2(150),
	phone				varchar2(11),   --변수명 phone, varcher(11)로 수정함 (01012345678)
	address_detail		varchar2(100),	-- 8/12 상세주소 컬럼 추가
	email				varchar2(30),
	register_date		date default sysdate,
	grade				char(1) -- S,A,B,C
)

	--seconhand_board 테이블 생성후에 추가해줄것
	secondhand_code		number(5) references secondhand_board(secondhand_code) on delete cascade

select * from customer
	
drop table customer purge;

--예시

insert into customer
values('test', '1234', '가나다', '111111-1111111', 'm', '12345', '서울시 서울구', '01012341234', '서울 아파트 101동 101호 ', 'test@naver.com', sysdate, 'S' )

insert into customer
values('admin', '1234', '관리자', '111111-1111111', 'm', '12345', '서울시 서울구', '01012341234', '서울 아파트 101동 101호 ', 'admin@naver.com', sysdate, 'X' )

insert into customer
values('abcd', '1234', '홍길동', '111111-1111111', 'm', '12345', '서울시 중구', '01012341234', '서울 아파트 101동 101호 ', 'hong@naver.com', sysdate, 'A' )

insert into customer
values('test2', '1234', '김길동', '111111-1111111', 'm', '12345', '서울시 판교', '01012341234', '서울 아파트 101동 101호 ', 'kim@naver.com', sysdate, 'B' )

insert into customer
values('go123', '1234', '김영희', '111111-1111111', 'f', '12345', '서울시 중구', '01012341234', '서울 아파트 101동 101호 ', 'ab123@naver.com', sysdate, 'C' )

insert into customer
values('gogo123', '1234', '김철수', '111111-1111111', 'm', '12345', '서울시 중구', '01012341234', '서울 아파트 101동 101호 ', 'ab122@naver.com', sysdate, 'S' )

insert into customer
values('abcd123', '1234', '길동', '111111-1111111', 'm', '12345', '서울시 중구', '01012341234', '서울 아파트 101동 101호 ', 'abab1212@naver.com', sysdate, 'C' )

insert into customer
values('aaa111', '1234', '라마바', '111111-1111111', 'f', '12345', '서울시 중구', '01012341234', '서울 아파트 101동 101호 ', 'abab1212@naver.com', sysdate, 'C' )

--[미완성]문의사항 게시판만 출력
create table qna(
qna_num		number(5) primary key,
id 			varchar2(20)  references customer(id) on delete cascade,
qna_cate		varchar2(30) check(qna_cate in ('상품문의','배송문의','환불문의','기타')),
qna_title		varchar2(30),
qna_content	varchar2(4000),
qna_product_name	varchar2(200),
qna_date			date,
qna_state		varchar2(30) check(qna_state in ('답변대기','답변완료')),
qna_re_ref	number,
qna_re_lev	number,
qna_re_seq	number
)

select * from qna;

select * from customer;
insert into qna
values(1, 'test', '상품문의', '상품 문의합니다', '상품에 대한 문의입니다content', '나이키상의', sysdate, '답변대기',1,1,1);
insert into qna
values(2, 'test', '배송문의', '배송 문의합니다', '배송에 대한 문의입니다content', '나이키상의', sysdate, '답변대기',1,1,1);
insert into qna
values(3, 'test', '환불문의', '환불 문의합니다', '환불에 대한 문의입니다content', '나이키상의', sysdate, '답변대기',1,1,1);


drop table qna purge;

delete from qna
where qna_num='1';
select * from notice
order by notice_num desc


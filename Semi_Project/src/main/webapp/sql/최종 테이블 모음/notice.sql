create table notice(
notice_num	number(5) primary key,
notice_id	varchar2(20),	--admin id
notice_title	varchar2(300),
notice_content	varchar2(4000),
notice_file		varchar2(50),
notice_re_ref	number,
notice_re_lev	number,
notice_re_seq	number,
notice_date		date default sysdate,
notice_readcount number
);

select * from notice;

drop table notice purge;

select nvl(max(notice_num),0)+1 from notice;

delete from notice
where notice_num = 3;




	

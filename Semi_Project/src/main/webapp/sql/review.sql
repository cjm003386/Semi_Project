drop sequence review_seq
drop table review

create table review(
	review_num number,
	review_name varchar2(30) references customer(id),
	review_pass varchar2(30),
	review_subject varchar2(300),
	review_content varchar2(4000),
	review_file varchar2(50),
	review_re_ref number,
	review_re_lev number,
	review_re_seq number,
	review_readcount number,
	product_name varchar2(200),
	review_date date default sysdate,
	primary key(review_num)
);

drop table review_comm purge
create table review_comm(
	num number primary key,
	id varchar2(30) references customer(id),
	content varchar2(200),
	reg_date date,
	comment_review_num number references review(review_num) on delete cascade,
	--comm 테이블이 참조하는 보드 글 번호
	comment_re_lev number(1) check(comment_re_lev in (0,1,2)),--원문이면 0 답글이면 1
	comment_re_seq number,--원문이면 0, 1레벨이면 1레벨 시퀀스 +1
	comment_re_ref number--원문은 자신 글번호, 답글이면 원문 글번호
);
--게시판 글 삭제시 참조 댓글도 삭제된다.
drop sequence review_com_seq;
--시퀀스 생성
create sequence review_com_seq;
alter sequence review_com_seq nocache

create sequence review_seq;
alter sequence review_seq nocache

insert into review (review_num, review_subject, review_name, review_re_ref) VALUES (1,'처음','admin',1);
insert into review (review_num, review_subject, review_name, review_re_ref) VALUES (2,'둘째','admin',2);
insert into review (review_num, review_subject, review_name, review_re_ref) VALUES (3,'셋째','admin',3);

delete from review;
drop table review purge;
drop sequence review_seq;
select * from review;
select review_pass from review where review_num =1;


select * 
from 
(select rownum rnum, review_num, review_name, 
review_pass, review_subject,review_content, 
review_file, review_re_ref, review_re_lev,  review_re_seq, 
review_readcount, review_date from 
(SELECT * FROM review
where review_name='id'
ORDER BY review_re_ref DESC, 
 review_re_seq ASC)
where rownum<=10) 
 where rnum>=1 and rnum<=10

 select * from review
 

insert into review_comm(num, id, comment_review_num) values(1,'id',1);


select *
from (select rownum, j.*
	  from (
	  		select board_num, board_subject, nvl(cnt,0) as cnt
	  		from board left outer join(select comment_board_num,count(*) cnt
	  									from comm
	  									group by comment_board_num)
	  		on board_num=comment_board_num
	  		order by board_re_ref desc, 
	  		board_re_seq asc
	  		) j
	  	where rownum<=10
	  	)
where rnum>=1 and rnum<=10;


------------------------------------------------------
select * 
from (select rownum rnum, j.*
	from (
			SELECT review.*, nvl(cnt,0) as cnt
			FROM review left outer join(select comment_review_num, count(*) cnt
										from review_comm
										group by comment_review_num)
			on review_num = comment_review_num
			order by review_re_ref desc,
			review_re_seq asc
			) j
		where rownum<=10
		)
	where rnum>=1 and rnum<=10;
----------------------------------------------------------	
	select * from review_comm
	
	select count(*) 
	from review_comm 
 	where comment_review_num = 1
 	
 	
 	
 	
 	
 	select num, review_comm.id, content, reg_date, comment_re_lev,
	 comment_re_seq, 
     comment_re_ref 
 from   review_comm join customer 
	  on 	 review_comm.id= customer.id
 where comment_review_num = 1 
  order by comment_re_ref asc,
  comment_re_seq asc
	
select count(*) 
 from review_comm 
where comment_review_num = 2	

select *
 from review_comm 
where comment_review_num = 2	


	
	
	
where review_name='id'
ORDER BY review_re_ref DESC, 
 review_re_seq ASC)
where rownum<=10) 
 where rnum>=1 and rnum<=10
 
 drop table admin purge


create table faq(
faq_text_num		number(5) primary key,
faq_title			varchar2(300),
faq_content			varchar2(4000)
);

select * from faq;

drop table faq purge;


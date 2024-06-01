create table wishlist( -- 관심상품
    wishlist_code  number(5)    primary key,
    id             varchar2(20) references customer(id) on delete cascade,
    product_code   number(5)    references product(product_code) on delete cascade,
    opt_color      varchar2(10),
	opt_size       varchar2(10)
)
drop table wishlist
drop sequence wishlist_seq
create sequence wishlist_seq nocache

select * from wishlist;

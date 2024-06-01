drop table cart
drop sequence cart_seq
create table cart( -- 장바구니
    cart_code      number(5)    primary key,
    id             varchar2(20) references customer(id) on delete cascade,
    product_code   number(5)    references product(product_code) on delete cascade,
    cart_count     number(3),
    opt_color      varchar2(10),
	opt_size       varchar2(10)
)

create sequence cart_seq;
alter sequence cart_seq nocache


select * from cart, product 
where cart.product_code = product.product_code
and id ='id';


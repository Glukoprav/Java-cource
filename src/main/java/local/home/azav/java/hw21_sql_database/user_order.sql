create table user_orders
(
  id           number not null primary key,
  name         varchar2(25),
  age          number not null
);

create table order_all
(
  order_id     number not null primary key,
  date_order   date   not null,
  item_id      number not null,
  user_id      number not null,
  value        number not null
);

create table items_all
(
  item_id      number not null primary key,
  name         varchar2(25) not null,
  price        number(6,2)
);

alter table order_all
  add constraint ord_user_fk foreign key (user_id)
  references user_orders (id);

alter table order_all
  add constraint ord_item_fk foreign key (item_id)
  references items_all (item_id);

insert into USER_ORDERS (ID, NAME, AGE) values (1, 'Ivanov', 30);
insert into USER_ORDERS (ID, NAME, AGE) values (2, 'Petrov', 40);
insert into USER_ORDERS (ID, NAME, AGE) values (3, 'Sidorov', 20);
insert into USER_ORDERS (ID, NAME, AGE) values (4, 'Rabinovich', 35);

insert into ITEMS_ALL (ITEM_ID, NAME, PRICE) values (1, 'ascender', 30.00);
insert into ITEMS_ALL (ITEM_ID, NAME, PRICE) values (2, 'backpack 70 liter.', 50.00);
insert into ITEMS_ALL (ITEM_ID, NAME, PRICE) values (3, 'backpack 100 liter.', 60.00);
insert into ITEMS_ALL (ITEM_ID, NAME, PRICE) values (4, 'biner', 10.00);
insert into ITEMS_ALL (ITEM_ID, NAME, PRICE) values (5, 'bolt', 3.00);
insert into ITEMS_ALL (ITEM_ID, NAME, PRICE) values (6, 'climbing rope', 122.50);
insert into ITEMS_ALL (ITEM_ID, NAME, PRICE) values (7, 'carabiner', 8.00);
insert into ITEMS_ALL (ITEM_ID, NAME, PRICE) values (8, 'descender', 12.60);
insert into ITEMS_ALL (ITEM_ID, NAME, PRICE) values (9, 'fifi hook', 20.00);
insert into ITEMS_ALL (ITEM_ID, NAME, PRICE) values (10, 'harness', 45.00);
insert into ITEMS_ALL (ITEM_ID, NAME, PRICE) values (11, 'ice axe', 50.00);
insert into ITEMS_ALL (ITEM_ID, NAME, PRICE) values (12, 'nut set', 38.00);
insert into ITEMS_ALL (ITEM_ID, NAME, PRICE) values (13, 'quickdraw', 20.00);
insert into ITEMS_ALL (ITEM_ID, NAME, PRICE) values (14, 'sky hook', 23.60);

insert into ORDER_ALL (ORDER_ID, DATE_ORDER, ITEM_ID, USER_ID, VALUE) values (1, to_date('08-07-2018', 'dd-mm-yyyy'), 2, 1, 1);
insert into ORDER_ALL (ORDER_ID, DATE_ORDER, ITEM_ID, USER_ID, VALUE) values (2, to_date('08-07-2018', 'dd-mm-yyyy'), 6, 1, 1);
insert into ORDER_ALL (ORDER_ID, DATE_ORDER, ITEM_ID, USER_ID, VALUE) values (3, to_date('08-07-2018', 'dd-mm-yyyy'), 11, 1, 1);
insert into ORDER_ALL (ORDER_ID, DATE_ORDER, ITEM_ID, USER_ID, VALUE) values (4, to_date('08-07-2018', 'dd-mm-yyyy'), 7, 1, 5);
insert into ORDER_ALL (ORDER_ID, DATE_ORDER, ITEM_ID, USER_ID, VALUE) values (5, to_date('15-05-2018', 'dd-mm-yyyy'), 4, 1, 6);
insert into ORDER_ALL (ORDER_ID, DATE_ORDER, ITEM_ID, USER_ID, VALUE) values (6, to_date('15-05-2018', 'dd-mm-yyyy'), 13, 1, 3);
insert into ORDER_ALL (ORDER_ID, DATE_ORDER, ITEM_ID, USER_ID, VALUE) values (7, to_date('23-04-2018', 'dd-mm-yyyy'), 1, 3, 2);
insert into ORDER_ALL (ORDER_ID, DATE_ORDER, ITEM_ID, USER_ID, VALUE) values (8, to_date('23-04-2018', 'dd-mm-yyyy'), 10, 3, 1);
insert into ORDER_ALL (ORDER_ID, DATE_ORDER, ITEM_ID, USER_ID, VALUE) values (9, to_date('15-07-2018', 'dd-mm-yyyy'), 11, 3, 1);
insert into ORDER_ALL (ORDER_ID, DATE_ORDER, ITEM_ID, USER_ID, VALUE) values (10, to_date('12-07-2018', 'dd-mm-yyyy'), 3, 3, 1);
insert into ORDER_ALL (ORDER_ID, DATE_ORDER, ITEM_ID, USER_ID, VALUE) values (11, to_date('11-06-2018', 'dd-mm-yyyy'), 13, 2, 10);
insert into ORDER_ALL (ORDER_ID, DATE_ORDER, ITEM_ID, USER_ID, VALUE) values (12, to_date('11-06-2018', 'dd-mm-yyyy'), 5, 2, 15);
insert into ORDER_ALL (ORDER_ID, DATE_ORDER, ITEM_ID, USER_ID, VALUE) values (13, to_date('11-06-2018', 'dd-mm-yyyy'), 6, 2, 2);
insert into ORDER_ALL (ORDER_ID, DATE_ORDER, ITEM_ID, USER_ID, VALUE) values (14, to_date('11-06-2018', 'dd-mm-yyyy'), 12, 2, 1);
insert into ORDER_ALL (ORDER_ID, DATE_ORDER, ITEM_ID, USER_ID, VALUE) values (15, to_date('11-06-2018', 'dd-mm-yyyy'), 14, 2, 2);
insert into ORDER_ALL (ORDER_ID, DATE_ORDER, ITEM_ID, USER_ID, VALUE) values (16, to_date('02-07-2018', 'dd-mm-yyyy'), 9, 2, 1);
insert into ORDER_ALL (ORDER_ID, DATE_ORDER, ITEM_ID, USER_ID, VALUE) values (17, to_date('02-07-2018', 'dd-mm-yyyy'), 11, 2, 1);
insert into ORDER_ALL (ORDER_ID, DATE_ORDER, ITEM_ID, USER_ID, VALUE) values (18, to_date('01-04-2018', 'dd-mm-yyyy'), 2, 4, 1);
insert into ORDER_ALL (ORDER_ID, DATE_ORDER, ITEM_ID, USER_ID, VALUE) values (19, to_date('07-05-2018', 'dd-mm-yyyy'), 3, 4, 1);
insert into ORDER_ALL (ORDER_ID, DATE_ORDER, ITEM_ID, USER_ID, VALUE) values (20, to_date('15-05-2018', 'dd-mm-yyyy'), 3, 4, 1);

create or replace view user_order_item as
select u.id user_id,
       u.name user_name,
       o.order_id order_id,
       o.date_order date_order,
       i.name item_name,
       o.value value,
       i.price price,
       o.value * i.price order_cost
  from user_orders u
  join order_all o
    on u.id = o.user_id
  join items_all i
    on o.item_id = i.item_id
 order by o.date_order;

select * from user_order_item
 where user_id = 2 and date_order =to_date('11-06-2018', 'dd-mm-yyyy');

select * from user_order_item
 where user_name = 'Petrov' and date_order =to_date('11-06-2018', 'dd-mm-yyyy');

select sum(uo.order_cost) from user_order_item uo where uo.user_id = 2 and uo.date_order =to_date('11-06-2018', 'dd-mm-yyyy');

select user_id, user_name, order_id, date_order, item_name, value, price, order_cost from user_order_item
 where user_id = 2 and date_order = to_date('11-06-2018', 'dd-mm-yyyy')
union
select rt.user_id, rt.user_name, rt.order_id, rt.date_order, rt.item_name, rt.value, rt.price, sc.order_cost
  from (select null as user_id, null as user_name, null as order_id, null as date_order, 'Total cost' as item_name, null as value, null as price
          from user_order_item where rownum = 1) rt,
       (select sum(uo.order_cost) order_cost
          from user_order_item uo
         where uo.user_id = 2
           and uo.date_order = to_date('11-06-2018', 'dd-mm-yyyy')) sc
order by user_id desc;

select * from user_order_item uo where uo.date_order = to_date('15-05-2018', 'dd-mm-yyyy');

select user_id, user_name, order_id, date_order, item_name, value, price, order_cost
  from user_order_item uo where uo.date_order = to_date('15-05-2018', 'dd-mm-yyyy')
union
select rt.user_id, rt.user_name, rt.order_id, rt.date_order, rt.item_name, rt.value, rt.price, sc.order_cost
  from (select null as user_id, null as user_name, null as order_id, null as date_order, 'Total cost' as item_name, null as value, null as price
          from user_order_item where rownum = 1) rt,
       (select sum(uo.order_cost) order_cost
          from user_order_item uo
         where uo.date_order = to_date('15-05-2018', 'dd-mm-yyyy')) sc
order by user_id desc;
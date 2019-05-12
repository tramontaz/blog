create table post
(
  id           bigserial    not null
    constraint post_pk
      primary key,
  content      text,
  title        varchar(255) not null,
  date_created timestamp    not null,
  date_updated timestamp,
  preview varchar(10000)
);

alter table post
  owner to postgres;

create unique index posts_id_uindex
  on post (id);
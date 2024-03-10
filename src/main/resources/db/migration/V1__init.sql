create sequence user_seq start with 1 increment by 50;
create sequence client_seq start with 1 increment by 50;
create sequence delivery_seq start with 1 increment by 50;
create sequence time_slot_seq start with 1 increment by 50;

create table public.user (
                             is_temp_password boolean not null,
                             id bigint not null,
                             email varchar(255),
                             firstname varchar(255),
                             lastname varchar(255),
                             password varchar(255),
                             role varchar(255) check (role in ('USER','ADMIN')),
                             username varchar(255) not null,
                             primary key (id)
);

create table public.client (
                               age integer not null,
                               id bigint not null,
                               firstname varchar(255),
                               lastname varchar(255),
                               primary key (id)
);

create table public.delivery (
                                 delivery_date date,
                                 client_id bigint,
                                 id bigint not null,
                                 time_slot_id bigint,
                                 description varchar(255),
                                 mode varchar(255) check (mode in ('DRIVE','DELIVERY','DELIVERY_TODAY','DELIVERY_ASAP')),
                                 primary key (id)
);

create table public.time_slot (
                                  end_time time(6),
                                  start_time time(6),
                                  id bigint not null,
                                  primary key (id)
);


alter table if exists public.delivery
    add constraint FKag1yyks6o5roxjoy2o8lmyjvb
    foreign key (client_id)
    references public.client;

alter table if exists public.delivery
    add constraint FK2v8tcar80hc481dst5e8qfulm
    foreign key (time_slot_id)
    references public.time_slot;
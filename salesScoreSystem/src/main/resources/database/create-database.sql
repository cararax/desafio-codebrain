create sequence hibernate_sequence;

alter sequence hibernate_sequence owner to postgres;

create table if not exists product
(
    id    bigint not null
        primary key,
    name  varchar(255),
    price double precision
);

alter table product
    owner to postgres;

create table if not exists seller
(
    id   bigint not null
        primary key,
    name varchar(255)
);

alter table seller
    owner to postgres;

create table if not exists sale
(
    id           bigint not null
        primary key,
    local_date   date,
    total_amount double precision,
    seller_id    bigint not null
        constraint fkqo5yb2opubvktdxvya06qctjs
            references seller
);

alter table sale
    owner to postgres;

create table if not exists sale_products
(
    sale_id    bigint not null
        constraint fk4kyjb6xkhu1h3ooi1eyjh9jcp
            references sale,
    product_id bigint not null
        constraint fk4l8no5x888rdwhw49evionm4b
            references product
);

alter table sale_products
    owner to postgres;


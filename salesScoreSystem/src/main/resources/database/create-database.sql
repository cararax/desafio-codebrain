create sequence hibernate_sequence;

alter sequence hibernate_sequence owner to postgres;

create table product
(
    id    bigint not null
        primary key,
    name  varchar(255),
    price double precision
);

alter table product
    owner to postgres;

create table seller
(
    id   bigint not null
        primary key,
    name varchar(255)
);

alter table seller
    owner to postgres;

create table sale
(
    id           bigint not null
        primary key,
    total_amount double precision,
    seller_id    bigint not null
        constraint fkqo5yb2opubvktdxvya06qctjs
            references seller
);

alter table sale
    owner to postgres;

create table sale_products
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


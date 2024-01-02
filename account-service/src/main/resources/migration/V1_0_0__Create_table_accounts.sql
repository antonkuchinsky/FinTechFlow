create table if not exists accounts
(
    id           uuid primary key,
    first_name    varchar (30) not null,
    last_name    varchar (30) not null,
    balance      numeric(15,2) not null,
    account_type varchar(50) not null,
    currency       varchar(3) not null,
    status   varchar (20) not null,
    opening_date date not null,
    closing_date date not null
    );
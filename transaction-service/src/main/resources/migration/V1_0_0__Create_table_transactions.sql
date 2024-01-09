create table if not exists transactions
(
    id           uuid primary key,
    sender_id    uuid not null,
    recepient_id    uuid not null,
    sum      numeric(15,2) not null,
    currency       varchar(3) not null,
    date_transaction date not null
    );
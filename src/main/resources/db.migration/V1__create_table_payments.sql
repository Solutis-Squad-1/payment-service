CREATE TABLE payments
(
    id           BIGINT generated always as identity,
    created_at   timestamp(6)   not null,
    deleted      boolean        not null,
    deleted_at   timestamp(6),
    form_payment varchar(255)   not null check (form_payment in ('PIX', 'CREDIT_CARD', 'BANK_BILL')),
    order_id     bigint         not null,
    total        numeric(38, 2) not null,
    updated_at   timestamp(6),
    user_id      bigint         not null,
    primary key (id)
);
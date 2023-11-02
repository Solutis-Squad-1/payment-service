CREATE TABLE payments (
    id BIGINT generated always as identity,
    order_id BIGINT not null,
    total BIGDECIMAL not null,
    form_payment BIGINT not null,

    primary key (id)
);
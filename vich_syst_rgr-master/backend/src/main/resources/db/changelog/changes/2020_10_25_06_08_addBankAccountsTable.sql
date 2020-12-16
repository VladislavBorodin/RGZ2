create TABLE bank_accounts
(
    id              serial      not null,
    owner_id        integer     not null,
    amount          numeric     not null,
    created_date    timestamp   not null,
    updated_date    timestamp   null,
    removed_date    timestamp   null,
    primary key     (id)
)
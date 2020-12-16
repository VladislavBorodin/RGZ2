create TABLE bank_users
(
    id              serial      not null,
    user_id         integer     not null,
    created_date    timestamp   not null,
    updated_date    timestamp   null,
    removed_date    timestamp   null,
    primary key     (id)
)
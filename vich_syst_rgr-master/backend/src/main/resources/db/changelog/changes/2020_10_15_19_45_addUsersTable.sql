create TABLE users
(
    id              serial      not null,
    username        varchar     not null,
    password_hash   varchar     not null,
    created_date    timestamp   not null,
    updated_date    timestamp   null,
    removed_date    timestamp   null,
    primary key     (id)
)
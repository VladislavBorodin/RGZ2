create table atms (
    id              serial      not null,
    cash            numeric     not null,
    created_date    timestamp   not null,
    updated_date    timestamp   null,
    removed_date    timestamp   null,
    primary key     (id)
)
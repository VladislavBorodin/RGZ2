create table history_lines (
    command_type    varchar     not null ,
    date            timestamp   not null ,
    money           numeric     not null ,
    account_id      integer     not null
)
create table diary (
    created_at timestamp(6),
    deleted_at timestamp(6),
    id bigserial not null,
    updated_at timestamp(6),
    user_id bigint,
    alarm_url varchar(255),
    category varchar(255),
    content varchar(255),
    fcm_token varchar(255),
    uid varchar(255),
    primary key (id)
)

create table users (
    created_at timestamp(6),
    deleted_at timestamp(6),
    id bigserial not null,
    last_login_at timestamp(6),
    last_logout_at timestamp(6),
    updated_at timestamp(6),
    email varchar(255),
    image varchar(255),
    nickname varchar(255),
    password varchar(255),
    provider_id varchar(255),
    provider_name varchar(255) check (provider_name in ('UNKNOWN','DEFAULT','GOOGLE','APPLE','KAKAO','TWITTER','TIKTOK')),
    role varchar(255),
    primary key (id)
)

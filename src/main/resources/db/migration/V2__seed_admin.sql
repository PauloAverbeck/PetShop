insert into app_user (name, email, password_hash)
values ('Admin', 'admin@local', '$2a$10$REPLACE_WITH_BCRYPT_HASH')
on conflict (email) do nothing;
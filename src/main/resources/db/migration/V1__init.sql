create table app_user (
  id bigserial primary key,
  name varchar(120) not null,
  email varchar(160) not null unique,
  password_hash varchar(255) not null,
  created_at timestamp not null default now()
);

create table pet (
  id bigserial primary key,
  owner_id bigint not null references app_user(id),
  name varchar(120) not null,
  species varchar(40) not null,
  breed varchar(80),
  birth_date date,
  created_at timestamp not null default now()
);

create table appointment (
  id bigserial primary key,
  pet_id bigint not null references pet(id),
  starts_at timestamp not null,
  status varchar(30) not null,
  observation text,
  created_at timestamp not null default now()
);

create index idx_pet_owner on pet(owner_id);
create index idx_appointment_pet on appointment(pet_id);
create index idx_appointment_starts_at on appointment(starts_at);
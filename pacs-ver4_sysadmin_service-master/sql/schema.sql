CREATE TABLE gb_body_part
(
    id                  varchar(32) primary key,
    name                text not null,
    description         text
);

CREATE TABLE gb_modality_type_name
(
    id                  varchar(8)  primary key,
    name                text not null,
    description         text
);

CREATE TABLE gb_role
(
    id                varchar(32) primary key,
    name              text not null,
    description       text
);

CREATE TABLE gb_extension_type
(
    id                varchar(32) primary key,
    name              text not null,
    description       text
);

CREATE TABLE gb_consumable_type
(
    id                  varchar(64) primary key,
    name                text not null,
    description         text
);

CREATE TABLE gb_config_attribute
(
    id                varchar(64) primary key,
    name              text not null,
    description       text,
    datatype          varchar(16),
    datatype_config   text,
    min_occurs        integer not null default 0,
    max_occurs        integer
);

CREATE TABLE gb_statistics_type
(
    id                varchar(64) primary key,
    name              text not null,
    description       text,
    schema_params     text,
    filter_params     text,
    config            text
);

CREATE TABLE gb_hospital
(
    id                varchar(16) primary key,
    name              text not null,
    name_search       text not null,
    description       text,
    phone             varchar(12),
    email             text,
    address           text,
    logo              bytea,
    logo_full         bytea,
    enabled           boolean not null default true
);

CREATE TABLE gb_domain
(
    id                varchar(128) primary key,
    public_address    boolean not null default false,
    preferred         boolean not null default true,
    hospital_id       varchar(16) NOT NULL references gb_hospital (id)
);

-- Thêm bảng gb_client
-- Mục đích: Lưu tài khoản sysadmin
-- Lưu các tài khoản connector kết nối với RIS
create table gb_client
(
    id                varchar(36) primary key,
    secret            text not null,
    type              varchar(16) not null default 'connector',
    authorities       text not null,
    ip_allowed        text,
    hospital_id       varchar(16) references gb_hospital (id)
);
INSERT INTO gb_config_attribute (id, name, description, datatype, datatype_config, min_occurs, max_occurs)
VALUES ('003', 'ngo', 'hang', 'INTEGER', '', 0, 2);
INSERT INTO gb_hospital (id, name, name_search, description, phone, email, address, enabled)
VALUES ('001', 'bvtinh', 'tinhqn', 'BOOLEAN', '097', 'h@gmai.com', 'hf', true);
create table gb_hospital_config(

                                   id                serial primary key,

                                   attribute_id      varchar(64) NOT NULL references gb_config_attribute (id),

                                   attribute_value   text not null,

                                   preferred         boolean not null default true,

                                   hospital_id       varchar(16) NOT NULL references gb_hospital (id));

drop table if exists aptos_account;
drop table if exists hibernate_hello;
drop table if exists hibernate_sequences;
drop table if exists post;
drop table if exists post_event;
drop table if exists townesquare_state;
drop table if exists townesquare_state_event;
drop table if exists user;
drop table if exists user_event;
create table aptos_account (name varchar(255) not null, address varchar(255), created_at datetime, created_by varchar(255), updated_at datetime, updated_by varchar(255), version bigint, primary key (name)) engine=InnoDB;
create table hibernate_hello (id bigint not null, message varchar(255), primary key (id)) engine=InnoDB;
create table hibernate_sequences (sequence_name varchar(255) not null, next_val bigint, primary key (sequence_name)) engine=InnoDB;
insert into hibernate_sequences(sequence_name, next_val) values ('default',9999);
create table post (post_id DECIMAL(20,0) not null, off_chain_version bigint not null, poster VARCHAR(66), user_id varchar(66), content varchar(1000), digest varchar(66), version DECIMAL(20,0), created_by varchar(255), updated_by varchar(255), active bit, deleted bit, created_at datetime, updated_at datetime, primary key (post_id)) engine=InnoDB;
create table post_event (post_id DECIMAL(20,0) not null, version DECIMAL(20,0) not null, event_class varchar(255) not null, created_by varchar(255), created_at datetime, command_id varchar(255), aptos_event_version DECIMAL(20,0), aptos_event_sequence_number DECIMAL(20,0), aptos_event_type varchar(500), aptos_event_guid_creation_number DECIMAL(20,0), aptos_event_guid_account_address VARCHAR(66), status CHAR(1), command_type varchar(50), dynamic_properties_lob VARCHAR(2000), primary key (post_id, version)) engine=InnoDB;
create table townesquare_state (account_address VARCHAR(66) not null, off_chain_version bigint not null, is_emergency bit, user_admin VARCHAR(66), post_admin VARCHAR(66), created_by varchar(255), updated_by varchar(255), active bit, deleted bit, version DECIMAL(20,0), created_at datetime, updated_at datetime, primary key (account_address)) engine=InnoDB;
create table townesquare_state_event (account_address VARCHAR(66) not null, version DECIMAL(20,0) not null, event_class varchar(255) not null, created_by varchar(255), created_at datetime, command_id varchar(255), aptos_event_version DECIMAL(20,0), aptos_event_sequence_number DECIMAL(20,0), aptos_event_type varchar(500), aptos_event_guid_creation_number DECIMAL(20,0), aptos_event_guid_account_address VARCHAR(66), status CHAR(1), command_type varchar(50), dynamic_properties_lob VARCHAR(2000), primary key (account_address, version)) engine=InnoDB;
create table user (user_wallet VARCHAR(66) not null, off_chain_version bigint not null, username varchar(66), profile_image varchar(255), bio varchar(255), version DECIMAL(20,0), created_by varchar(255), updated_by varchar(255), active bit, deleted bit, created_at datetime, updated_at datetime, primary key (user_wallet)) engine=InnoDB;
create table user_event (user_wallet VARCHAR(66) not null, version DECIMAL(20,0) not null, event_class varchar(255) not null, created_by varchar(255), created_at datetime, command_id varchar(255), aptos_event_version DECIMAL(20,0), aptos_event_sequence_number DECIMAL(20,0), aptos_event_type varchar(500), aptos_event_guid_creation_number DECIMAL(20,0), aptos_event_guid_account_address VARCHAR(66), status CHAR(1), command_type varchar(50), dynamic_properties_lob VARCHAR(2000), primary key (user_wallet, version)) engine=InnoDB;

delete from gb_hospital_config;
delete from gb_hospital;
delete from gb_config_attribute;
insert into gb_config_attribute(id, name, description, datatype, datatype_config, max_occurs) VALUES
   ('001', 'ngo', 'hang', 'BOOLEAN', 'chiu', 2),('003', 'ngo', 'hang', 'INTEGER', '',2);
INSERT INTO gb_hospital (id, name, name_search, description, phone, email, address, enabled)
VALUES ('001', 'bvtinh', 'tinhqn', 'BOOLEAN', '097', 'h@gmai.com', 'hf', true);

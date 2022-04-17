
INSERT INTO app_role (id, user_role) VALUES ('role1', 'ROLE_PATIENT_USER');
INSERT INTO app_role (id, user_role) VALUES ('role2', 'ROLE_PREMISES_ADMIN');
INSERT INTO app_role (id, user_role) VALUES ('role3', 'ROLE_SUPER_ADMIN');

INSERT INTO app_user (id, password, username) VALUES ('user1', 'test123','patient1');
INSERT INTO app_user (id, password, username) VALUES ('user4', 'test123','patient2');
INSERT INTO app_user (id, password, username) VALUES ('user2', 'test123', 'manager1');
INSERT INTO app_user (id, password, username) VALUES ('user3', 'test123', 'admin1');

INSERT INTO role_app_user (fk_app_role_id, fk_app_user_id) VALUES ('role1','user1');
INSERT INTO role_app_user (fk_app_role_id, fk_app_user_id) VALUES ('role1','user4');
INSERT INTO role_app_user (fk_app_role_id, fk_app_user_id) VALUES ('role2','user2');
INSERT INTO role_app_user (fk_app_role_id, fk_app_user_id) VALUES ('role3','user3');

INSERT INTO contact_info (id, email, phone) VALUES ('contact1', 'patient1@gmail.com', null);
INSERT INTO contact_info (id, email, phone) VALUES ('contact2', 'patient2@gmail.com', null);
INSERT INTO patient (id, birth_date, first_name, last_name, pnr, fk_contact_info_id, fk_app_user_id) VALUES ('patient1', '1990-01-01', 'Nils', 'Nilsson', '199001012424', 'contact1', 'user1');
INSERT INTO patient (id, birth_date, first_name, last_name, pnr, fk_contact_info_id, fk_app_user_id) VALUES ('patient2', '1991-01-01', 'Anna', 'Andersson', '199101012425', 'contact2', 'user4');

INSERT INTO address (id, city, street, zip_code) VALUES ('address1' ,'Växjö', 'Storgatan 1', '35233');
INSERT INTO address (id, city, street, zip_code) VALUES ('address2' ,'Jönköping', 'Västergatan 23', '55302');
INSERT INTO premises (id, name, fk_address_id) VALUES ('premises1', 'Vårdcentral Centrum', 'address1');
INSERT INTO premises (id, name, fk_address_id) VALUES ('premises2', 'Vårdcentral Väster', 'address2');

INSERT INTO booking (id, administrator_id, date_time, price, vacant, vaccine_type, fk_patient_id, fk_premises_id) VALUES ('booking1', 'test', '2022-01-01T15:10', 0, 0, 'Covid 19', 'patient1','premises1');
INSERT INTO booking (id, administrator_id, date_time, price, vacant, vaccine_type, fk_patient_id, fk_premises_id) VALUES ('booking2', null, '2022-01-01T16:00', 0, 1, 'Säsongsinfluensa', null,'premises1');
INSERT INTO booking (id, administrator_id, date_time, price, vacant, vaccine_type, fk_patient_id, fk_premises_id) VALUES ('booking3', null, '2022-01-02T07:00', 0, 1, 'Covid 19', null,'premises1');
INSERT INTO booking (id, administrator_id, date_time, price, vacant, vaccine_type, fk_patient_id, fk_premises_id) VALUES ('booking4', null, '2022-01-01T15:15', 0, 0, 'Covid 19', 'patient2','premises2');
INSERT INTO booking (id, administrator_id, date_time, price, vacant, vaccine_type, fk_patient_id, fk_premises_id) VALUES ('booking5', null, '2022-01-02T15:10', 0, 1, 'Covid 19', null,'premises2');

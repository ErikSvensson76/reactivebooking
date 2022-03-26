
INSERT INTO app_role (id, user_role) VALUES ('role1', 'ROLE_PATIENT_USER');
INSERT INTO app_role (id, user_role) VALUES ('role2', 'ROLE_PREMISES_ADMIN');
INSERT INTO app_role (id, user_role) VALUES ('role3', 'ROLE_SUPER_ADMIN');

INSERT INTO app_user (id, password, username) VALUES ('user1', 'test123','patient1');
INSERT INTO app_user (id, password, username) VALUES ('user2', 'test123', 'manager1');
INSERT INTO app_user (id, password, username) VALUES ('user3', 'test123', 'admin1');

INSERT INTO role_app_user (fk_app_role_id, fk_app_user_id) VALUES ('role1','user1');
INSERT INTO role_app_user (fk_app_role_id, fk_app_user_id) VALUES ('role2','user2');
INSERT INTO role_app_user (fk_app_role_id, fk_app_user_id) VALUES ('role3','user3');

INSERT INTO contact_info (id, email, phone) VALUES ('contact1', 'patient1@gmail.com', null);
INSERT INTO patient (id, birth_date, first_name, last_name, pnr, fk_contact_info_id, fk_app_user_id) VALUES ('patient1', '1990-01-01', 'Nils', 'Nilsson', '199001012424', 'contact1', 'user1');
-- Insert family data
INSERT INTO family (name, address) VALUES ('Stepanov', '123 Main St, Moscow');
INSERT INTO family (name, address) VALUES ('Glupov', '456 Oak Ave, Saint Petersburg');
INSERT INTO family (name, address) VALUES ('Mashkin', '789 Pine Rd, Novosibirsk');
INSERT INTO family (name, address) VALUES ('Pupkin', '321 Cedar Ln, Ekaterinburg');
INSERT INTO family (name, address) VALUES ('Ivanov', '654 Maple Dr, Kazan');

-- Insert person data with family relationships
INSERT INTO person (firstname, lastname, family_id, is_married) VALUES ('maria', 'stepanova', 1, false);
INSERT INTO person (firstname, lastname, family_id, is_married) VALUES ('daria', 'glupova', 2, false);
INSERT INTO person (firstname, lastname, family_id, is_married) VALUES ('ivan', 'mashkin', 3, false);
INSERT INTO person (firstname, lastname, family_id, is_married) VALUES ('vasiliy', 'pupkin', 4, false);
INSERT INTO person (firstname, lastname, family_id, is_married) VALUES ('petr', 'ivanov', 5, false);

-- Additional family members
INSERT INTO person (firstname, lastname, family_id, is_married) VALUES ('nikolai', 'stepanov', 1, true);
INSERT INTO person (firstname, lastname, family_id, is_married) VALUES ('anna', 'stepanova', 1, true);
INSERT INTO person (firstname, lastname, family_id, is_married) VALUES ('sergei', 'glupov', 2, false);
INSERT INTO person (firstname, lastname, family_id, is_married) VALUES ('mikhail', 'mashkin', 3, true);
INSERT INTO person (firstname, lastname, family_id, is_married) VALUES ('elena', 'mashkina', 3, true);
INSERT INTO person (firstname, lastname, family_id, is_married) VALUES ('olga', 'pupkina', 4, false);
INSERT INTO person (firstname, lastname, family_id, is_married) VALUES ('alexei', 'ivanov', 5, true);
INSERT INTO person (firstname, lastname, family_id, is_married) VALUES ('tatiana', 'ivanova', 5, true);
-- Insert family data
INSERT INTO family (name, address) VALUES ('Stepanov', '123 Main St, Moscow');
INSERT INTO family (name, address) VALUES ('Glupov', '456 Oak Ave, Saint Petersburg');
INSERT INTO family (name, address) VALUES ('Mashkin', '789 Pine Rd, Novosibirsk');
INSERT INTO family (name, address) VALUES ('Pupkin', '321 Cedar Ln, Ekaterinburg');
INSERT INTO family (name, address) VALUES ('Ivanov', '654 Maple Dr, Kazan');

-- Insert person data with family relationships
INSERT INTO person (firstname, lastname, family_id) VALUES ('maria', 'stepanova', 1);
INSERT INTO person (firstname, lastname, family_id) VALUES ('daria', 'glupova', 2);
INSERT INTO person (firstname, lastname, family_id) VALUES ('ivan', 'mashkin', 3);
INSERT INTO person (firstname, lastname, family_id) VALUES ('vasiliy', 'pupkin', 4);
INSERT INTO person (firstname, lastname, family_id) VALUES ('petr', 'ivanov', 5);

-- Additional family members
INSERT INTO person (firstname, lastname, family_id) VALUES ('nikolai', 'stepanov', 1);
INSERT INTO person (firstname, lastname, family_id) VALUES ('anna', 'stepanova', 1);
INSERT INTO person (firstname, lastname, family_id) VALUES ('sergei', 'glupov', 2);
INSERT INTO person (firstname, lastname, family_id) VALUES ('mikhail', 'mashkin', 3);
INSERT INTO person (firstname, lastname, family_id) VALUES ('elena', 'mashkina', 3);
INSERT INTO person (firstname, lastname, family_id) VALUES ('olga', 'pupkina', 4);
INSERT INTO person (firstname, lastname, family_id) VALUES ('alexei', 'ivanov', 5);
INSERT INTO person (firstname, lastname, family_id) VALUES ('tatiana', 'ivanova', 5);
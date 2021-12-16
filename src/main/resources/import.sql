INSERT INTO ExternalUser(id,lastName,firstName) VALUES (1, 'Lamotte', 'Edouard');
INSERT INTO ExternalUser(id,lastName,firstName) VALUES (2, 'Cucchietti', 'Thomas');
INSERT INTO ExternalUser(id,lastName,firstName) VALUES (3, 'Pilato', 'David');

INSERT INTO Brewery(id, name, type) VALUES (1, 'Kronenbourg', 'INDUSTRIAL');
INSERT INTO Brewery(id, name, type) VALUES (2, 'La Bière de la Rade', 'CRAFT');

INSERT INTO Beer(id, name, brewery_id, alcoholLevel, creatorId) VALUES (1, '1664', 1, 5.5, 1);
INSERT INTO Beer(id, name, brewery_id, alcoholLevel, creatorId) VALUES (2, 'Censurée', 2, 5.8, 2);

INSERT INTO Beer_Flavors(Beer_id, flavors) VALUES (1, 'Houblon floral');
INSERT INTO Beer_Flavors(Beer_id, flavors) VALUES (1, 'Malt');
INSERT INTO Beer_Flavors(Beer_id, flavors) VALUES (2, 'Houblon');
INSERT INTO Beer_Flavors(Beer_id, flavors) VALUES (2, 'Mangue');
INSERT INTO Beer_Flavors(Beer_id, flavors) VALUES (2, 'Fruits exotiques');

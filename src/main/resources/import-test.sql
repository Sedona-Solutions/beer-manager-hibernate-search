REPLACE INTO ExternalUser(id,lastName,firstName) VALUES (1, 'Lamotte', 'Edouard');
REPLACE INTO ExternalUser(id,lastName,firstName) VALUES (2, 'Cucchietti', 'Thomas');
REPLACE INTO ExternalUser(id,lastName,firstName) VALUES (3, 'Pilato', 'David');

REPLACE INTO Brewery(id, name, type) VALUES (1, 'Kronenbourg', 'INDUSTRIAL');
REPLACE INTO Brewery(id, name, type) VALUES (2, 'La Bière de la Rade', 'CRAFT');

REPLACE INTO Beer(id, name, brewery_id, alcoholLevel, creatorId) VALUES (1, '1664', 1, 5.5, 1);
REPLACE INTO Beer(id, name, brewery_id, alcoholLevel, creatorId) VALUES (2, 'Censurée', 2, 5.8, 2);

REPLACE INTO Beer_Flavors(Beer_id, flavors) VALUES (1, 'Houblon floral');
REPLACE INTO Beer_Flavors(Beer_id, flavors) VALUES (1, 'Malt');
REPLACE INTO Beer_Flavors(Beer_id, flavors) VALUES (2, 'Houblon');
REPLACE INTO Beer_Flavors(Beer_id, flavors) VALUES (2, 'Mangue');
REPLACE INTO Beer_Flavors(Beer_id, flavors) VALUES (2, 'Fruits exotiques');

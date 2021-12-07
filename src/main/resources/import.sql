INSERT INTO Brewery(id, name, type) VALUES (1, 'Kronenbourg', 'INDUSTRIAL');
INSERT INTO Brewery(id, name, type) VALUES (2, 'La Bière de la Rade', 'CRAFT');
INSERT INTO Beer(id, name, brewery_id, alcoholLevel) VALUES (1, '1664', 1, 5.5);
INSERT INTO Beer(id, name, brewery_id, alcoholLevel) VALUES (2, 'Censurée', 2, 5.8);
INSERT INTO Beer_Flavors(Beer_id, flavors) VALUES (1, 'Houblon floral');
INSERT INTO Beer_Flavors(Beer_id, flavors) VALUES (1, 'Malt');
INSERT INTO Beer_Flavors(Beer_id, flavors) VALUES (2, 'Houblon');
INSERT INTO Beer_Flavors(Beer_id, flavors) VALUES (2, 'Mangue');
INSERT INTO Beer_Flavors(Beer_id, flavors) VALUES (2, 'Fruits exotiques');

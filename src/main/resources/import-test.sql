REPLACE INTO ExternalUser(id,lastName,firstName) VALUES (1, 'Lamotte', 'Edouard');
REPLACE INTO ExternalUser(id,lastName,firstName) VALUES (2, 'Cucchietti', 'Thomas');
REPLACE INTO ExternalUser(id,lastName,firstName) VALUES (3, 'Pilato', 'David');

REPLACE INTO Brewery(id, name, type, country) VALUES (1, 'Kronenbourg', 'INDUSTRIAL', 'France');
REPLACE INTO Brewery(id, name, type, country) VALUES (2, 'La Bière de la Rade', 'CRAFT', 'France');
REPLACE INTO Brewery(id, name, type, country) VALUES (3, 'Brasserie Castelain', 'INDUSTRIAL', 'France');
REPLACE INTO Brewery(id, name, type, country) VALUES (4, 'Carlsberg', 'INDUSTRIAL', 'Danemark');
REPLACE INTO Brewery(id, name, type, country) VALUES (5, 'Sainte-Cru', 'CRAFT', 'France');

REPLACE INTO Beer(id, name, brewery_id, alcoholLevel, creatorId) VALUES (1, '1664', 1, 5.5, 1);
REPLACE INTO Beer(id, name, brewery_id, alcoholLevel, creatorId) VALUES (2, 'Censurée', 2, 5.8, 2);
REPLACE INTO Beer(id, name, brewery_id, alcoholLevel, creatorId) VALUES (3, 'Ch''ti Blonde', 3, 6.4, 3);
REPLACE INTO Beer(id, name, brewery_id, alcoholLevel, creatorId) VALUES (4, 'Skoll', 1, 6.0, 1);
REPLACE INTO Beer(id, name, brewery_id, alcoholLevel, creatorId) VALUES (5, 'Tigre Bock', 1, 5.5, 1);
REPLACE INTO Beer(id, name, brewery_id, alcoholLevel, creatorId) VALUES (6, 'Carlsberg', 4, 5.7, 1);
REPLACE INTO Beer(id, name, brewery_id, alcoholLevel, creatorId) VALUES (7, 'Elephant 1959', 4, 7.2, 2);
REPLACE INTO Beer(id, name, brewery_id, alcoholLevel, creatorId) VALUES (8, 'Red is Dead', 5, 7.2, 2);
REPLACE INTO Beer(id, name, brewery_id, alcoholLevel, creatorId) VALUES (9, 'Apocalypse Now', 5, 8.0, 3);
REPLACE INTO Beer(id, name, brewery_id, alcoholLevel, creatorId) VALUES (10, 'Delicatessen', 5, 6.6, 3);

REPLACE INTO Beer_Ingredients(Beer_id, ingredients) VALUES (1, 'Eau');
REPLACE INTO Beer_Ingredients(Beer_id, ingredients) VALUES (1, 'Malt d''orge');
REPLACE INTO Beer_Ingredients(Beer_id, ingredients) VALUES (1, 'Sirop de glucose');
REPLACE INTO Beer_Ingredients(Beer_id, ingredients) VALUES (1, 'Blé');
REPLACE INTO Beer_Ingredients(Beer_id, ingredients) VALUES (1, 'Malt de blé');
REPLACE INTO Beer_Ingredients(Beer_id, ingredients) VALUES (1, 'Extrait de houblon');
REPLACE INTO Beer_Ingredients(Beer_id, ingredients) VALUES (1, 'Sulfates');
REPLACE INTO Beer_Ingredients(Beer_id, ingredients) VALUES (3, 'Eau');
REPLACE INTO Beer_Ingredients(Beer_id, ingredients) VALUES (3, 'Malts d''orge');
REPLACE INTO Beer_Ingredients(Beer_id, ingredients) VALUES (3, 'Sucre');
REPLACE INTO Beer_Ingredients(Beer_id, ingredients) VALUES (3, 'Malts de blé');
REPLACE INTO Beer_Ingredients(Beer_id, ingredients) VALUES (3, 'Houblon');
REPLACE INTO Beer_Ingredients(Beer_id, ingredients) VALUES (4, 'Eau');
REPLACE INTO Beer_Ingredients(Beer_id, ingredients) VALUES (4, 'Sucre');
REPLACE INTO Beer_Ingredients(Beer_id, ingredients) VALUES (4, 'Arôme');
REPLACE INTO Beer_Ingredients(Beer_id, ingredients) VALUES (4, 'Acide citrique (E330)');
REPLACE INTO Beer_Ingredients(Beer_id, ingredients) VALUES (4, 'Ecorce d''orange douce');
REPLACE INTO Beer_Ingredients(Beer_id, ingredients) VALUES (4, 'Coriandre');
REPLACE INTO Beer_Ingredients(Beer_id, ingredients) VALUES (4, 'Malt d''orge');
REPLACE INTO Beer_Ingredients(Beer_id, ingredients) VALUES (4, 'Sirop de glucose');
REPLACE INTO Beer_Ingredients(Beer_id, ingredients) VALUES (4, 'Extrait de houblon');
REPLACE INTO Beer_Ingredients(Beer_id, ingredients) VALUES (5, 'Malt d''orge');
REPLACE INTO Beer_Ingredients(Beer_id, ingredients) VALUES (5, 'Sulfites');
REPLACE INTO Beer_Ingredients(Beer_id, ingredients) VALUES (6, 'Eau');
REPLACE INTO Beer_Ingredients(Beer_id, ingredients) VALUES (6, 'Malt d''orge');
REPLACE INTO Beer_Ingredients(Beer_id, ingredients) VALUES (6, 'Extrait de houblon');
REPLACE INTO Beer_Ingredients(Beer_id, ingredients) VALUES (7, 'Eau');
REPLACE INTO Beer_Ingredients(Beer_id, ingredients) VALUES (7, 'Malt d''orge');
REPLACE INTO Beer_Ingredients(Beer_id, ingredients) VALUES (7, 'Extrait de houblon');
REPLACE INTO Beer_Ingredients(Beer_id, ingredients) VALUES (7, 'Sulfites');
REPLACE INTO Beer_Ingredients(Beer_id, ingredients) VALUES (8, 'Eau');
REPLACE INTO Beer_Ingredients(Beer_id, ingredients) VALUES (8, 'Malt d''orge');
REPLACE INTO Beer_Ingredients(Beer_id, ingredients) VALUES (8, 'Malt de blé');
REPLACE INTO Beer_Ingredients(Beer_id, ingredients) VALUES (8, 'Houblons');
REPLACE INTO Beer_Ingredients(Beer_id, ingredients) VALUES (9, 'Eau');
REPLACE INTO Beer_Ingredients(Beer_id, ingredients) VALUES (9, 'Malt d''orge');
REPLACE INTO Beer_Ingredients(Beer_id, ingredients) VALUES (9, 'Malt de blé');
REPLACE INTO Beer_Ingredients(Beer_id, ingredients) VALUES (9, 'Houblons');
REPLACE INTO Beer_Ingredients(Beer_id, ingredients) VALUES (10, 'Eau');
REPLACE INTO Beer_Ingredients(Beer_id, ingredients) VALUES (10, 'Malt d''orge');
REPLACE INTO Beer_Ingredients(Beer_id, ingredients) VALUES (10, 'Malt de blé');
REPLACE INTO Beer_Ingredients(Beer_id, ingredients) VALUES (10, 'Houblons');

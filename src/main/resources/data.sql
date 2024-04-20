INSERT INTO ENGINETYPE(name) VALUES('FUEL');
INSERT INTO ENGINETYPE(name) VALUES('ELECTRIC');
INSERT INTO ENGINETYPE(name) VALUES('HYBRID');

INSERT INTO COLOR(name) VALUES('RED');
INSERT INTO COLOR(name) VALUES('BLUE');
INSERT INTO COLOR(name) VALUES('YELLOW');
INSERT INTO COLOR(name) VALUES('BLACK');
INSERT INTO COLOR(name) VALUES('GREEN');
INSERT INTO COLOR(name) VALUES('PINK');
INSERT INTO COLOR(name) VALUES('WHITE');
INSERT INTO COLOR(name) VALUES('GRAY');
INSERT INTO COLOR(name) VALUES('ORANGE');

INSERT INTO CAR(manufacturer, type, engineTypeId, colorId,
                 productionYear, milage, price)
VALUES('Mercedes', '355 CLK', 2, 1, 2023, 15000, 55000);

INSERT INTO CAR(manufacturer, type, engineTypeId, colorId,
                 productionYear, milage, price)
VALUES('BMW', 'X 100', 3, 6, 2024, 1500, 67700);

INSERT INTO CAR(manufacturer, type, engineTypeId, colorId,
                 productionYear, milage, price)
VALUES('Citroen', 'C9', 1, 7, 2020, 150000, 13000);

INSERT INTO CAR(manufacturer, type, engineTypeId, colorId,
                 productionYear, milage, price)
VALUES('Open', 'Corsa', 1, 5, 1999, 500000, 1000);
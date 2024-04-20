CREATE TABLE ENGINETYPE(
    id      INT         GENERATED ALWAYS AS IDENTITY,
    name    VARCHAR(25)  NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE COLOR(
    id      INT         GENERATED ALWAYS AS IDENTITY,
    name    VARCHAR(25)  NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE car
(
    id              INT         GENERATED ALWAYS AS IDENTITY,
    manufacturer    VARCHAR(25)  NOT NULL,
    type            VARCHAR(25)  NOT NULL,
    engineTypeId    INT  NOT NULL,
    colorId         INT  NOT NULL,
    productionYear  INT          NOT NULL,
    milage  INT          NOT NULL,
    price  DECIMAL(8,2) NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(engineTypeId) REFERENCES ENGINETYPE(id),
    FOREIGN KEY(colorId) REFERENCES COLOR(id)
);
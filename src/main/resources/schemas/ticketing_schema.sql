CREATE TABLE TICKETING
(
    TICKETING_ID BINARY(16) NOT NULL,
    START_DATE DATE NOT NULL,
    END_DATE DATE NOT NULL,
    START_TIME TIME NOT NULL,
    TYPE VARCHAR(20) NOT NULL
)
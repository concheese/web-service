CREATE TABLE schedule
(
    id   BINARY(16) PRIMARY KEY,
    timestamp timestamp NOT NULL,
    postal int(5) NOT NULL,
    concert_id BINARY(16),
    FOREIGN KEY (concert_id) REFERENCES CONCERT(ID)
)
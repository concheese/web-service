CREATE TABLE TICKETING
(
    ID BINARY(16) NOT NULL primary key,
    START timestamp NOT NULL,
    END  timestamp NOT NULL,
    status varchar(63) NOT NULL,
    concert_id BINARY(16) NOT NULL,
    foreign key (concert_id) references CONCERT(ID)
)
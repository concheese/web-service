create table concert_performer(
    id binary(16) primary key,
    concert_id binary(16),
    performer_id binary(16),
    foreign key (concert_id) references CONCERT(ID),
    foreign key (performer_id) references performer(ID)
)
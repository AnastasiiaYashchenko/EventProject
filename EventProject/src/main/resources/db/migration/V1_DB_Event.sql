create table users
(
    id          bigint,
    name        varchar(255),
    lastName    varchar(255),
    phoneNumber char(10),
    email       varchar(255),
    dateOfBirth date
);
create table event
(
    id               bigint,
    nameEvent        varchar(255),
    dayEvent         date,
    timeEvent        time,
    price            int,
    lasting          int,
    address          varchar(255),
    descriptionEvent varchar(255),
    ageLimit         int
);
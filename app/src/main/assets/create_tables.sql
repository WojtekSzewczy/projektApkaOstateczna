create table User
(
    UserID   integer not null
        constraint User_PK
            primary key autoincrement,
    Name     TEXT    not null,
    Surname  TEXT    not null,
    Position TEXT    not null,
    Email TEXT    not null,
    Login    TEXT    not null,
    Password TEXT    not null
);

create table Device
(
    DeviceID           integer           not null
        constraint Device_PK
            primary key autoincrement,
    OwnerID            integer           not null
        constraint OwnerID
            references User
            on update cascade on delete cascade,
    Password           TEXT              not null,
    Type               TEXT              not null,
    Description        TEXT,
    "Wi-Fi"            INTEGER default 0 not null,
    Connection         TEXT              not null,
    "Google Assistant" integer default 0 not null,
    Location           TEXT              not null,
    check ("Google Assistant" IN (0, 1)),
    check ("Wi-Fi" IN (0, 1))
);

create table Reservation
(
    ReservationID    integer not null
        constraint Reservation_PK
            primary key autoincrement,
    BookerID         integer not null
        constraint Booker__FK
            references User
            on update cascade on delete cascade,
    ReservationStart TEXT    not null,
    ReservationEnd   TEXT    not null,
    check (ReservationEnd REGEXP '^[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}$' AND
           strftime('%Y-%m-%dT%H:%M:%S', ReservationEnd) = ReservationEnd),
    check (ReservationStart REGEXP '^[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}$' AND
           strftime('%Y-%m-%dT%H:%M:%S', ReservationStart) = ReservationStart)
);

create table Reservation_Device
(
    ReservationID integer not null
        constraint Reservation_FK
            references Reservation
            on update cascade on delete cascade,
    DeviceID      integer not null
        constraint Device_FK
            references Device
            on update cascade on delete cascade
);

create table Room
(
    RoomID           integer not null
        constraint Room_pk
            primary key autoincrement,
    OwnerID          integer not null
        constraint Owner_FK
            references User
            on update cascade on delete cascade,
    Name             text    not null,
    Password         text    not null,
    MaxParticipants  integer not null,
    CreationDateTime text    not null,
    CloseDateTime    text    not null,
    check (CloseDateTime REGEXP '^[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}$' AND
           strftime('%Y-%m-%dT%H:%M:%S', CloseDateTime) = CloseDateTime),
    check (CreationDateTime REGEXP '^[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}$' AND
           strftime('%Y-%m-%dT%H:%M:%S', CreationDateTime) = CreationDateTime)
);

create table RoomHistory
(
    RoomID   integer not null
        constraint Room_FK
            references Room
            on update cascade on delete cascade,
    DeviceID integer not null
        constraint Device_FK
            references Device
            on update cascade on delete cascade
);

create table User_Device
(
    DeviceID integer           not null
        constraint Device_FK
            references Device
            on update cascade on delete cascade,
    UserID   integer           not null
        constraint User_FK
            references User
            on update cascade on delete cascade,
    Owner    integer default 0 not null,
    check (Owner IN (0, 1))
);


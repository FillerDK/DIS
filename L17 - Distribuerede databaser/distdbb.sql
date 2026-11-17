create database ddbb COLLATE Danish_Norwegian_CI_AS

go

use ddbb

-- Create table personloen
create table personloen
(
    cpr char(10) primary key,
    loen int,
    skatteprocent int
)

-- Create table kontob
create table kontob
(
    kontonr int primary key identity(1002,2),
    kontohavercpr char(10),
    rente int -- hvis positiv renteindt�gt, negativ renteudgift 
)

-- Insert into personloen
insert into personloen values ('1212921456',307000,39)
insert into personloen values ('1111971112',500000,41)
insert into personloen values ('0909950056',200000,38)

-- Insert into kontob
insert into kontob values('1212921456',654)
insert into kontob values('1212921456',-63)
insert into kontob values('1111971112',87)
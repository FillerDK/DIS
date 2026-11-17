use tempdb

create table brugere
(
brugerid char(10),
passw char(8)
)
insert into brugere values('kaj','haj')
insert into brugere values('karsten','skak')

create table produkt
(
produktid int identity,
produktnavn varchar(25),
lagerantal int,
pris decimal(8,2)
)
insert into produkt values('�bler',15,3.50)
insert into produkt values('p�rer',10,2.50)
insert into produkt values('bananer',30,2.75)
insert into produkt values('blommer',50,1.75)

create table loenopl
(
cpr char(10),
navn varchar(50),
loen int
)
insert into loenopl values('1212121212','Ib Mangepenge',1000000)
insert into loenopl values('1313131313','Per Fattigr�v',150000)
insert into loenopl values('1414141414','Ole Middell�n',300000)

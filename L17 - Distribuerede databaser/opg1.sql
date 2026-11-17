use ddba

go

-- Rekonstruktion på personer
select pa.navn, pa.cpr, pa.bynavn, pl.loen, pl.skatteprocent
from personadr pa
join ddbb.dbo.personloen pl
    on pa.cpr = pl.cpr

go

-- Rekonstruktion på kontoer
(select ka.kontonr, ka.kontohavercpr, ka.rente from kontoa ka)
UNION
(select kb.kontonr, kb.kontohavercpr, kb.rente from ddbb.dbo.kontob kb)

go

-- Globale skema med views person og konto
create or alter view person
AS
select pa.navn, pa.cpr, pa.bynavn, pl.loen, pl.skatteprocent
from personadr pa
join ddbb.dbo.personloen pl
    on pa.cpr = pl.cpr

go

create or alter view konto
AS
(select ka.kontonr, ka.kontohavercpr, ka.rente from kontoa ka)
UNION
(select kb.kontonr, kb.kontohavercpr, kb.rente from ddbb.dbo.kontob kb)

go

-- Test views
select * from person

select * from konto

-- Lav query med persons navn og hvor meget den person skal betale i skat af den pågældende indestående renteindtægter.
SELECT 
    p.navn AS [Navn],
    SUM(k.rente) AS [Samlet Renteindtægt],
    SUM(k.rente) * (p.skatteprocent / 100.0) AS [Skat af Renteindtægt]
FROM 
    person p
JOIN 
    konto k ON p.cpr = k.kontohavercpr
GROUP BY 
    p.navn, p.skatteprocent

go

-- Instead of trigger på person view
create or alter trigger inserttrigger
on person
instead of insert
as
insert into personadr select cpr, navn, bynavn from inserted
insert into ddbb.dbo.personloen select cpr, loen, skatteprocent from inserted

go

-- Test trigger
insert into person (cpr, navn, bynavn, loen, skatteprocent) values ('7562857315', 'Danni Nielsen', 'Nakskov', 400000, 40)
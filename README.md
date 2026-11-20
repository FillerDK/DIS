# DIS – Distribution, Integration and Security

Java and SQL exercises from the course *Distribution, integration og sikkerhed* (Distribution, Integration and Security) at Erhvervsakademi Aarhus (Datamatiker, spring 2025).

| Lecture | Topic | What is in the folder |
|---|---|---|
| L02 | Distributed systems | Exercise 6 with two threads and a shared `Common` class |
| L03 | Application layer | Exercise 7 reads a web page with `URL`. Exercise 8 has merge sort and a threaded variant (`ThreadFlet`) |
| L04 | Socket programming | Workshop exercises with TCP (including `SocketEks2023` and a chat GUI) and a UDP client and server (`workshopOpg4`) |
| L06 | Transport layer | A threaded web server (one thread per connection) that serves the test pages in `myWEB` (`opg11`), a TCP chat client and server where a separate thread receives messages (`opg12`), and an extra exercise with a name server |
| L14 | Transactions | SQL script that creates a small bank database with customers and accounts |
| L17 | Distributed databases | Two databases (`ddba`, `ddbb`) and `opg1.sql`, which combines them in global views (`person`, `konto`) with an `INSTEAD OF` insert trigger |
| L23 | Web security | Setup script for exercise 30 with tables for users, products and salaries |

## Notes

- The chat server handles one connection at a time.

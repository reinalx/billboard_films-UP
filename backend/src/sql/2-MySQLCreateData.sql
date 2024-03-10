-- ----------------------------------------------------------------------------
-- Put here INSERT statements for inserting data required by the application
-- in the "paproject" database.
-------------------------------------------------------------------------------
INSERT INTO User (userName,password,firstName,lastName,email,role) VALUES ('viewer','$2a$10$8o34vbwlRURkBGETvQzr8OCuPrk52E.j2ilm4KGKPrwNR89eNV/YG','view','er','viewer@udc.es',0);
INSERT INTO User (userName,password,firstName,lastName,email,role) VALUES ('ticketseller','$2a$10$8o34vbwlRURkBGETvQzr8OCuPrk52E.j2ilm4KGKPrwNR89eNV/YG','ticketsell','er','ticketseller@udc.es',1);

INSERT INTO Screen (name,capacity) VALUES ('screen1',100);
INSERT INTO Screen (name,capacity) VALUES ('screen2',9);

INSERT INTO Movie (title,summary,runtime) VALUES ('PIRATAS DEL CARIBE V','El capitán Barbossa le roba el barco al pirata Jack Sparrow y secuestra a Elizabeth, amiga de Will Turner. Barbossa y su tripulación son víctimas de un conjuro que los condena a vivir eternamente y a transformarse cada noche en esqueletos vivientes.',90);
INSERT INTO Movie (title,summary,runtime) VALUES ('AVATAR','Entramos en el mundo Avatar de la mano de Jake Sully, un ex-Marine en silla de ruedas, que ha sido reclutado para viajar a Pandora, donde existe un mineral raro y muy preciado que puede solucionar la crisis energética existente en la Tierra',360);

--TODAY
INSERT INTO Session (sessionDate,price,movieId,screenId,remainingSeats) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '0 00:05' DAY_MINUTE),10,1,1,80);
INSERT INTO Session (sessionDate,price,movieId,screenId,remainingSeats) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '0 23:55' DAY_MINUTE),11,2,2,9);
-- DAY2
INSERT INTO Session (sessionDate,price,movieId,screenId,remainingSeats) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '1 18:30' DAY_MINUTE),10,1,1,100);
INSERT INTO Session (sessionDate,price,movieId,screenId,remainingSeats) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '1 19:30' DAY_MINUTE),11,2,2,9);
-- DAY3
INSERT INTO Session (sessionDate,price,movieId,screenId,remainingSeats) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '2 16:30' DAY_MINUTE),10,1,1,100);
INSERT INTO Session (sessionDate,price,movieId,screenId,remainingSeats) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '2 20:30' DAY_MINUTE),11,2,2,9);
--DAY4
INSERT INTO Session (sessionDate,price,movieId,screenId,remainingSeats) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '3 12:30' DAY_MINUTE),10,1,1,100);
INSERT INTO Session (sessionDate,price,movieId,screenId,remainingSeats) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '3 17:30' DAY_MINUTE),11,2,2,9);
--DAY5
INSERT INTO Session (sessionDate,price,movieId,screenId,remainingSeats) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '4 21:30' DAY_MINUTE),10,1,1,100);
INSERT INTO Session (sessionDate,price,movieId,screenId,remainingSeats) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '4 12:30' DAY_MINUTE),11,2,2,9);
--DAY6
INSERT INTO Session (sessionDate,price,movieId,screenId,remainingSeats) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '5 11:30' DAY_MINUTE),10,1,1,100);
INSERT INTO Session (sessionDate,price,movieId,screenId,remainingSeats) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '5 01:30' DAY_MINUTE),11,2,2,9);
--DAY7
INSERT INTO Session (sessionDate,price,movieId,screenId,remainingSeats) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '6 19:30' DAY_MINUTE),10,1,1,100);
INSERT INTO Session (sessionDate,price,movieId,screenId,remainingSeats) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '6 19:45' DAY_MINUTE),11,2,2,9);

INSERT INTO Sale (saleDate,bankCard,numberTickets,delivered,sessionId,userId) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '0 00:04' DAY_MINUTE),'123123',10,0,1,1);
INSERT INTO Sale (saleDate,bankCard,numberTickets,delivered,sessionId,userId) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '0 00:04' DAY_MINUTE),'123124',10,0,1,1);




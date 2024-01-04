CREATE DATABASE greengame;

USE greengame;

CREATE TABLE game (
        id bigint(20) PRIMARY KEY AUTO_INCREMENT,
        name varchar(255) DEFAULT NULL,
        description varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


INSERT INTO game (id, name, description) VALUES
(1, 'Recykling', ''),
(2, 'Oszczędzanie wody', ''),
(3, 'Oszczędzanie energii', ''),
(4, 'Ponowne wykorzystanie', ''),
(5, 'Korzystanie z transportu publicznego', ''),
(6, 'Prawidłowe pozbywanie się odpadów', ''),
(7, 'Spożywanie produktów ekologicznych', '');


DROP DATABASE IF EXISTS carinventoryservice;

DROP USER IF EXISTS `car_inventory_service`@`%`;

CREATE DATABASE IF NOT EXISTS carinventoryservice CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE USER IF NOT EXISTS `car_inventory_service`@`%` IDENTIFIED WITH mysql_native_password BY '12345678';

GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW,

CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON `carinventoryservice`.* TO `car_inventory_service`@`%`;

FLUSH PRIVILEGES;
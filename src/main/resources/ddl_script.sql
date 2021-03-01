create database crypto_bdd;
create user 'crypto'@'localhost`' identified by 'cryptopwd';
grant all privileges on crypto_bdd.* to 'crypto'@'localhost';
use crypto_bdd;


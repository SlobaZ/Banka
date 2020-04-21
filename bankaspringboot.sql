CREATE USER IF NOT EXISTS root IDENTIFIED BY 'root';

DROP DATABASE IF EXISTS bankaspringboot;
CREATE DATABASE bankaspringboot DEFAULT CHARACTER SET utf8;

USE bankaspringboot;

GRANT ALL ON bankaspringboot.* TO 'root'@'%';

FLUSH PRIVILEGES;

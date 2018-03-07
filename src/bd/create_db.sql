/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  thibault
 * Created: 6 mars 2018
 */
-- User
DROP USER IF EXISTS 'chi_chat'@'localhost';
CREATE USER 'chi_chat'@'localhost'
  IDENTIFIED BY 'Z-=37^3Jp';

-- Database
DROP DATABASE IF EXISTS chi_chat_db;
CREATE DATABASE chi_chat_db;
GRANT ALL PRIVILEGES ON chi_chat_db.* TO 'chi_chat'@'localhost';
-- Connect
USE chi_chat_db;

-- Tables
DROP TABLE IF EXISTS `chi_chat_user`;
CREATE TABLE `chi_chat_user` (
  `username`  VARCHAR(64)       NOT NULL,
  `name`      VARCHAR(100)      NOT NULL,
  `firstname` VARCHAR(100)      NOT NULL,
  `password`  VARCHAR(256)      NOT NULL, 

  PRIMARY KEY (`username`)
);

DROP TABLE IF EXISTS `chi_chat_contact`;
CREATE TABLE `chi_chat_contact` (
  `id`                  INT             AUTO_INCREMENT,
  `user_1_username`     VARCHAR(64)     NOT NULL,
  `user_2_username`     VARCHAR(64)     NOT NULL,

    PRIMARY KEY (`id`),
    CONSTRAINT FK_user1Contact FOREIGN KEY (`user_1_username`) REFERENCES `chi_chat_user`(`username`)  ON DELETE CASCADE,
    CONSTRAINT FK_user2Contact FOREIGN KEY (`user_2_username`) REFERENCES `chi_chat_user`(`username`)  ON DELETE CASCADE
);

-- INSERTS
-- First User (root, root, root, root)
INSERT INTO `chi_chat_user` (username, name, firstname, password) VALUES ('root', 'root', 'root', '63a9f0ea7bb98050796b649e85481845');


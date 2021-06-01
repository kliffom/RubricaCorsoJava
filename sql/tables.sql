CREATE TABLE `contacts` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(25) DEFAULT NULL,
  `surname` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE rubrica.email (
	email varchar(100) NOT NULL,
	ID_contatto BIGINT UNSIGNED NOT NULL,
	CONSTRAINT email_PK PRIMARY KEY (email),
	CONSTRAINT email_FK FOREIGN KEY (ID_contatto) REFERENCES rubrica.contacts(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE rubrica.num_telefono (
	telefono varchar(15) NOT NULL,
	ID_contatto BIGINT UNSIGNED NOT NULL,
	CONSTRAINT num_telefono_PK PRIMARY KEY (telefono),
	CONSTRAINT num_telefono_FK FOREIGN KEY (ID_contatto) REFERENCES rubrica.contacts(id) ON DELETE CASCADE ON UPDATE CASCADE
);
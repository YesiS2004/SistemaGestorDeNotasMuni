CREATE TABLE `Area`  (
  `ID_Area` integer NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(70) NOT NULL,
  `Usuario_Area` integer NOT NULL,
  PRIMARY KEY (`ID_Area`)
);

CREATE TABLE `Derivacion`  (
  `ID` integer NOT NULL AUTO_INCREMENT,
  `ID_Nota` integer NOT NULL,
  `ID_Area` integer NOT NULL,
  `Fecha_Derivacion` timestamp NOT NULL,
  `ID_Secretaria` integer NOT NULL,
  PRIMARY KEY (`ID`)
);

CREATE TABLE `Derivación`  ();

CREATE TABLE `Estado`  (
  `ID_Estado` integer NOT NULL,
  `Estado` varchar(50) NOT NULL,
  PRIMARY KEY (`ID_Estado`)
);

CREATE TABLE `Nota`  (
  `ID_Nota` integer NOT NULL AUTO_INCREMENT,
  `ID_Persona` integer NOT NULL,
  `Fecha_Entrega` date NOT NULL,
  `Detalles` text NULL,
  `Estado_Actual` integer NOT NULL,
  `Nota` longblob NOT NULL,
  PRIMARY KEY (`ID_Nota`)
);

CREATE TABLE `Persona`  (
  `ID_Persona` integer NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(50) NOT NULL,
  `Apellido` varchar(50) NOT NULL,
  `Telefono` varchar(50) NOT NULL,
  `Cedula` varchar(25) NULL,
  PRIMARY KEY (`ID_Persona`)
);

CREATE TABLE `Recepcion`  (
  `ID_Recepcion` integer NOT NULL AUTO_INCREMENT,
  `Nota_ID` integer NOT NULL,
  `Usuario_ID` integer NOT NULL,
  `Fecha_hora` timestamp NOT NULL,
  `Secretaria_ID` integer NOT NULL,
  PRIMARY KEY (`ID_Recepcion`)
);

CREATE TABLE `Rol`  (
  `ID_Rol` integer NOT NULL AUTO_INCREMENT,
  `Rol` varchar(25) NOT NULL,
  PRIMARY KEY (`ID_Rol`)
);

CREATE TABLE `Secretaria`  (
  `ID_Secretaria` integer NOT NULL AUTO_INCREMENT,
  `ID_Usuario` integer NOT NULL,
  PRIMARY KEY (`ID_Secretaria`)
);

CREATE TABLE `Usuario`  (
  `ID_Usuario` integer NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(20) NOT NULL,
  `Contrasenia` varchar(15) NOT NULL,
  `Rol` integer NOT NULL,
  PRIMARY KEY (`ID_Usuario`)
);

ALTER TABLE `Area` ADD CONSTRAINT `Usuario_Area` FOREIGN KEY (`Usuario_Area`) REFERENCES `Usuario` (`ID_Usuario`);
ALTER TABLE `Derivacion` ADD CONSTRAINT `Area` FOREIGN KEY (`ID_Area`) REFERENCES `Area` (`ID_Area`);
ALTER TABLE `Derivacion` ADD CONSTRAINT `Secretaria` FOREIGN KEY (`ID_Secretaria`) REFERENCES `Secretaria` (`ID_Secretaria`);
ALTER TABLE `Derivacion` ADD CONSTRAINT `Nota` FOREIGN KEY (`ID_Nota`) REFERENCES `Nota` (`ID_Nota`);
ALTER TABLE `Nota` ADD CONSTRAINT `ID_Persona` FOREIGN KEY (`ID_Persona`) REFERENCES `Persona` (`ID_Persona`);
ALTER TABLE `Nota` ADD CONSTRAINT `ID_Estado` FOREIGN KEY (`Estado_Actual`) REFERENCES `Estado` (`ID_Estado`);
ALTER TABLE `Recepcion` ADD CONSTRAINT `Nota_ID` FOREIGN KEY (`Nota_ID`) REFERENCES `Nota` (`ID_Nota`);
ALTER TABLE `Recepcion` ADD CONSTRAINT `Usuario_ID` FOREIGN KEY (`Usuario_ID`) REFERENCES `Usuario` (`ID_Usuario`);
ALTER TABLE `Recepcion` ADD CONSTRAINT `Secretaria_ID` FOREIGN KEY (`Secretaria_ID`) REFERENCES `Secretaria` (`ID_Secretaria`);
ALTER TABLE `Secretaria` ADD CONSTRAINT `ID_Usuario` FOREIGN KEY (`ID_Usuario`) REFERENCES `Usuario` (`ID_Usuario`);
ALTER TABLE `Usuario` ADD CONSTRAINT `Rol` FOREIGN KEY (`Rol`) REFERENCES `Rol` (`ID_Rol`);


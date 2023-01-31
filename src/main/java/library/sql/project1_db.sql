CREATE SCHEMA `project_1` ;

CREATE TABLE `project_1`.`person` (
   `id` INT NOT NULL AUTO_INCREMENT,
   `fullName` VARCHAR(100) NOT NULL,
   `ageOfBirth` INT NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `fullName_UNIQUE` (`fullName` ASC) VISIBLE);

CREATE TABLE `project_1`.`book` (
`id` INT NOT NULL AUTO_INCREMENT,
`name` VARCHAR(100) NOT NULL,
`author` VARCHAR(100) NOT NULL,
`dateOfPresent` INT NOT NULL,
`personId` INT,
PRIMARY KEY (`id`),
FOREIGN KEY (`personId`)
REFERENCES `project_1`.`person` (`id`)
ON DELETE SET NULL
ON UPDATE NO ACTION);

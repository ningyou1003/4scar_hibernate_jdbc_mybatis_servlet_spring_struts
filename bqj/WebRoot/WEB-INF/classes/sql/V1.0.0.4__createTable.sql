CREATE TABLE `l_law` (
	`KeyID` varchar(50) NOT NULL DEFAULT '',
	`Title` varchar(255) DEFAULT NULL,
	`category` varchar(255) DEFAULT NULL,
	`author` varchar(255) DEFAULT NULL,
	`syscreatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (`KeyID`) 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `l_ppt` (
	`KeyID` varchar(50) NOT NULL DEFAULT '',
	`Title` varchar(255) DEFAULT NULL,
	`category` varchar(255) DEFAULT NULL,
	`author` varchar(255) DEFAULT NULL,
	`syscreatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (`KeyID`) 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE `a_alarm`
DROP COLUMN `Agender`,
DROP COLUMN `AreginCode`,
DROP COLUMN `Address`,
DROP COLUMN `Astatus`,
DROP COLUMN `Aisvailable`,
ADD COLUMN `Aurl`  varchar(255) NULL AFTER `Afounder`;

ALTER TABLE `a_appeal`
DROP COLUMN `Gender`,
DROP COLUMN `ReginCode`,
DROP COLUMN `Status`,
DROP COLUMN `Isvailable`,
ADD COLUMN `Url`  varchar(255) NULL AFTER `Founder`;
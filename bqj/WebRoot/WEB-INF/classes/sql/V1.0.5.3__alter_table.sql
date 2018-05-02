ALTER TABLE `n_leader`
DROP COLUMN `Month`,
ADD COLUMN `fyear`  varchar(50) NULL AFTER `regionId`,
ADD COLUMN `lyear`  varchar(50) NULL AFTER `fyear`;
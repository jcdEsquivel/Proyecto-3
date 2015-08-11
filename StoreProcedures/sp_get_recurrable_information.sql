SELECT * FROM treeseeddb.recurrable_donation;DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `GET_RECURRABLE_INFORMATION_SP`(IN pdonorId INT)
BEGIN
	SELECT rd.amount, rd.non_profit_id, n.name, c.english, c.spanish 
	FROM recurrable_donation rd
	INNER JOIN nonprofit n
	ON n.id = rd.non_profit_id
	INNER JOIN catalog c
	ON n.cause = c.id 
	WHERE rd.donor_id = pdonorId;
END$$
DELIMITER ;

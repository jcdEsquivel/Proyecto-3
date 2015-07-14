CREATE DEFINER=`root`@`localhost` PROCEDURE `general_search_sp`(IN pFilter varchar(100), IN pCountry varchar(100))
BEGIN

DECLARE vCount INT;

CREATE TEMPORARY TABLE  IF NOT EXISTS RESULTS (
ID INT NOT NULL,
NAME VARCHAR(100) NOT NULL,
TYPE VARCHAR(20) NOT NULL,
IMG VARCHAR(100)  NULL,
COUNTVALUE INT NOT NULL
) ;



INSERT INTO RESULTS
(ID, NAME, TYPE, IMG, COUNTVALUE)
SELECT  N.ID,  N.NAME, 'nonProfit', N.PROFILE_PICTURE, COUNT(*) AS COUNT
FROM 	NONPROFIT N
		INNER JOIN 	RECURRABLE_DONATION RD ON RD.NON_PROFIT_ID = N.ID
        INNER JOIN CATALOG CAT ON N.COUNTRY = CAT.ID
WHERE	N.IS_ACTIVE = 1 AND RD.IS_ACTIVE = 1 AND
		(pCountry is null or CAT.NAME LIKE CONCAT('%',pCountry,'%'))  and
		(pFilter is null or N.NAME LIKE CONCAT('%', pFilter, '%'))
GROUP BY  N.ID, N.NAME, N.PROFILE_PICTURE
ORDER BY COUNT DESC
LIMIT 3; 



INSERT INTO RESULTS
(ID, NAME, TYPE, IMG, COUNTVALUE)
SELECT  C.ID, C.NAME, 'CAMPAIGN', C.PICTURE, COUNT(*) AS COUNT
FROM 	CAMPAIGN C
		INNER JOIN DONATION	D ON D.CAMPAING_ID = C.ID
        INNER JOIN NONPROFIT N ON N.ID = C.ID_NON_PROFIT
        INNER JOIN CATALOG CAT ON CAT.ID = N.COUNTRY
WHERE	C.IS_ACTIVE = 1 AND C.DUE_DATE >= CURDATE() AND
		(pCountry is null or CAT.NAME  LIKE  CONCAT('%', pCountry, '%'))  AND
		 (pFilter is null or C.NAME LIKE  CONCAT('%', pFilter, '%') )
GROUP BY C.ID, N.NAME, C.PICTURE
ORDER BY COUNT DESC
LIMIT 3; 


INSERT INTO RESULTS
(ID, NAME, TYPE, IMG, COUNTVALUE)
SELECT  D.ID, CONCAT(D.NAME,' ', D.LAST_NAME), 'donor',  D.PROFILE_PICTURE, COUNT(*) AS COUNT
FROM 	DONOR D
		INNER JOIN RECURRABLE_DONATION	RD ON D.ID = RD.DONOR_ID
        INNER JOIN CATALOG CAT ON CAT.ID = D.COUNTRY
WHERE	RD.IS_ACTIVE = 1 AND RD.IS_ACTIVE = 1 AND
		(pCountry is null or  CAT.NAME LIKE  CONCAT('%', pCountry, '%') ) and
        (pFilter is null or CONCAT(D.NAME,' ', D.LAST_NAME) LIKE CONCAT('%', pFilter, '%') )
GROUP BY D.ID, D.NAME, D.LAST_NAME ,  D.PROFILE_PICTURE
ORDER BY COUNT DESC
LIMIT 3; 


select count(*) into vCount
from RESULTS;



IF vCount < 30 THEN
--
	  
	INSERT INTO RESULTS
	(ID, NAME, TYPE, IMG, COUNTVALUE)
	SELECT  N.ID, N.NAME, 'nonProfit', N.PROFILE_PICTURE, COUNT(*) AS COUNT
	FROM 	NONPROFIT N
			INNER JOIN 	RECURRABLE_DONATION RD ON RD.NON_PROFIT_ID = N.ID
	WHERE	N.IS_ACTIVE = 1 AND RD.IS_ACTIVE = 1 AND
			( pFilter is null or N.NAME LIKE CONCAT('%', pFilter, '%') )
	GROUP BY N.ID, N.NAME, N.PROFILE_PICTURE
	ORDER BY COUNT DESC
	LIMIT 3; 


	INSERT INTO RESULTS
	(ID, NAME, TYPE, IMG, COUNTVALUE)
	SELECT  C.ID, C.NAME, 'CAMPAIGN', C.PICTURE,  COUNT(*) AS COUNT
	FROM 	CAMPAIGN C
			INNER JOIN DONATION	D ON D.CAMPAING_ID = C.ID
			INNER JOIN NONPROFIT N ON N.ID = C.ID_NON_PROFIT
	WHERE	C.IS_ACTIVE = 1 AND C.DUE_DATE >= CURDATE() AND
			( pFilter is null or C.NAME LIKE  CONCAT('%', pFilter, '%') )
	GROUP BY C.ID, N.NAME, C.PICTURE
	ORDER BY COUNT DESC
	LIMIT 3; 


	INSERT INTO RESULTS
	(ID, NAME, TYPE, IMG, COUNTVALUE)
	SELECT  D.ID, CONCAT(D.NAME,' ', D.LAST_NAME), 'donor', D.PROFILE_PICTURE, COUNT(*) AS COUNT
	FROM 	DONOR D
			INNER JOIN RECURRABLE_DONATION	RD ON D.ID = RD.DONOR_ID
	WHERE	RD.IS_ACTIVE = 1 AND RD.IS_ACTIVE = 1 AND
			( pFilter is null or CONCAT(D.NAME,' ', D.LAST_NAME) LIKE CONCAT('%', pFilter, '%') )
	GROUP BY D.ID, D.NAME, D.LAST_NAME , D.PROFILE_PICTURE
	ORDER BY COUNT DESC
	LIMIT 3; 

END IF;

select count(*) into vCount
from RESULTS;




IF vCount < 30 THEN

INSERT INTO RESULTS
	(ID, NAME, TYPE, IMG, COUNTVALUE)
	SELECT  N.ID, N.NAME, 'nonProfit', N.PROFILE_PICTURE, COUNT(*) AS COUNT
	FROM 	NONPROFIT N
	WHERE	N.IS_ACTIVE = 1  AND
			( pFilter is null or N.NAME LIKE CONCAT('%', pFilter, '%') )
	GROUP BY N.ID, N.NAME, N.PROFILE_PICTURE
	ORDER BY COUNT DESC
	LIMIT 3; 


	INSERT INTO RESULTS
	(ID, NAME, TYPE, IMG, COUNTVALUE)
	SELECT  C.ID, C.NAME, 'CAMPAIGN', C.PICTURE,  COUNT(*) AS COUNT
	FROM 	CAMPAIGN C
			INNER JOIN NONPROFIT N ON N.ID = C.ID_NON_PROFIT
	WHERE	C.IS_ACTIVE = 1 AND C.DUE_DATE >= CURDATE() AND
			( pFilter is null or C.NAME LIKE  CONCAT('%', pFilter, '%') )
	GROUP BY C.ID, N.NAME, C.PICTURE
	ORDER BY COUNT DESC
	LIMIT 3; 


	INSERT INTO RESULTS
	(ID, NAME, TYPE, IMG, COUNTVALUE)
	SELECT  D.ID, CONCAT(D.NAME,' ', D.LAST_NAME), 'donor', D.PROFILE_PICTURE, COUNT(*) AS COUNT
	FROM 	DONOR D
	WHERE	D.IS_ACTIVE = 1  AND
			( pFilter is null or CONCAT(D.NAME,' ', D.LAST_NAME) LIKE CONCAT('%', pFilter, '%') )
	GROUP BY D.ID, D.NAME, D.LAST_NAME , D.PROFILE_PICTURE
	ORDER BY COUNT DESC
	LIMIT 3; 

END IF;




SELECT DISTINCT * 
FROM	RESULTS
ORDER BY COUNTVALUE DESC
LIMIT 20; 

DROP table RESULTS;

END
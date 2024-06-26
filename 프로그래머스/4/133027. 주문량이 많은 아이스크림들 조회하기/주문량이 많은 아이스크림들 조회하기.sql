-- 코드를 입력하세요
SELECT D.FLAVOR
FROM (SELECT A.FLAVOR, (SUM(A.TOTAL_ORDER) + B.TOTAL_ORDER) AS C
        FROM JULY AS A
        LEFT JOIN FIRST_HALF AS B ON A.FLAVOR = B.FLAVOR
        GROUP BY A.FLAVOR
        ORDER BY C DESC
        LIMIT 3
    ) AS D;
        


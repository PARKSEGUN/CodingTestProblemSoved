(
    SELECT SUBSTRING(SALES_DATE,1,10) AS SALES_DATE, PRODUCT_ID, USER_ID, SALES_AMOUNT
    FROM ONLINE_SALE 
    WHERE SUBSTRING(SALES_DATE,1,7) = '2022-03'
)
UNION
(
    SELECT SUBSTRING(SALES_DATE,1,10) AS SALES_DATE, PRODUCT_ID, NULL  USER_ID, SALES_AMOUNT
    FROM OFFLINE_SALE
    WHERE SUBSTRING(SALES_DATE,1,7) = '2022-03'
)
ORDER BY SALES_DATE, PRODUCT_ID, USER_ID;

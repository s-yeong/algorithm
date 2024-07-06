-- 동일한 회원이 동일한 상품을 재구매한 데이터를
SELECT user_id, product_id
FROM ONLINE_SALE
GROUP BY user_id, product_id
HAVING count(*) >= 2
ORDER BY user_id, product_id desc;
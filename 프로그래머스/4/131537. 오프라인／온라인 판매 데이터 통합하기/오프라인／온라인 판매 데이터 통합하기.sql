-- 코드를 입력하세요
# 2022년 3월의 오프라인/온라인 상품 판매 데이터의 판매 날짜, 상품ID, 유저ID, 판매량을 출력


SELECT A.sales_date, A.product_id, A.user_id, sum(sales_amount) as sales_amount
FROM (
SELECT date_format(ons.sales_date, '%Y-%m-%d') sales_date, product_id, user_id, sales_amount
FROM ONLINE_SALE ons
WHERE date_format(ons.sales_date, '%Y-%m') = '2022-03'
UNION ALL
SELECT date_format(ofs.sales_date, '%Y-%m-%d') sales_date, product_id, null, sales_amount
FROM OFFLINE_SALE ofs
WHERE date_format(ofs.sales_date, '%Y-%m') = '2022-03'
) A
GROUP BY sales_date, product_id, user_id
ORDER BY sales_date, product_id, user_id;


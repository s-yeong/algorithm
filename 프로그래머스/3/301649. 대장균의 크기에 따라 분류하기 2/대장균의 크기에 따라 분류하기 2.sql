

select A.ID,
(CASE 
WHEN A.R <= (select count(*)*0.25 from ECOLI_DATA) THEN 'CRITICAL'
WHEN A.R <= (select count(*)*0.5 from ECOLI_DATA) THEN 'HIGH'
WHEN A.R <= (select count(*)*0.75 from ECOLI_DATA) THEN 'MEDIUM'
ELSE 'LOW'
END) COLONY_NAME
from (
    select id, RANK() OVER (ORDER BY SIZE_OF_COLONY DESC) R
    from ECOLI_DATA
) A
ORDER BY ID;
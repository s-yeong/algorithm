select FI.ID, FNI.FISH_NAME, FI.LENGTH
from fish_info fi
    JOIN (select fish_type, max(length) length
    from fish_info 
    group by fish_type) A ON FI.LENGTH = A.LENGTH AND FI.FISH_TYPE = A.FISH_TYPE
    JOIN FISH_NAME_INFO FNI
    ON FNI.FISH_TYPE = FI.FISH_TYPE
ORDER BY FI.ID

select count(*) FISH_COUNT
from fish_info
WHERE fish_type in (select fish_type from FISH_NAME_INFO  where FISH_NAME in ('BASS', 'SNAPPER'))
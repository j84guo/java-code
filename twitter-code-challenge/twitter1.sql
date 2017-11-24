select sum(tiv_2012) from 
(
select a.tiv_2012 from 
insurance a 
where 
exists (select 1 from insurance b where b.pid != a.pid and a.tiv_2011 = b.tiv_2011)
and
not exists (select 1 from insurance c where c.pid != a.pid and c.lat = a.lat and c.lon = a.lon)
) sub;
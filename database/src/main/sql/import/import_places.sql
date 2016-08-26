insert into places (name,typ,code,area_id,region_id,guid,okato,updated,status)
  SELECT s.formalname,s.shortname,s.placecode,s.area_id,s.region_id,s.aoguid,s.okato,now(),'A'
  FROM (
    WITH RECURSIVE parents AS (
      SELECT
        aoguid rootguid,
        aoguid,
        parentguid,
        aolevel
      FROM addrobj
      WHERE 1 = 1
            AND aolevel IN (4, 6, 90)
            AND currstatus = 0
            and actstatus = 1
      UNION ALL
      SELECT
        p.rootguid,
        a.aoguid,
        a.parentguid,
        a.aolevel
      FROM addrobj a
      JOIN parents p ON a.aoguid = p.parentguid
     WHERE 1=1
        and a.currstatus = 0
        and a.actstatus = 1
    )
    SELECT s.*, a.id area_id, r.id region_id
    FROM addrobj s
      JOIN parents pr on pr.rootguid = s.aoguid and pr.aolevel = 1
      join regions r on r.guid = pr.aoguid
      left JOIN parents pa on pa.rootguid = s.aoguid and pa.aolevel = 3
      left join areas a on a.guid = pa.aoguid
    where 1=1
      and s.aolevel in (4,6,90)
      and s.currstatus = 0
      and s.actstatus = 1
      and not exists(
        select 1
        from places p
        where p.guid = s.aoguid
      )
   ) as s;

update places p
   set name = s.formalname,
       typ = s.shortname,
       code = s.placecode,
       area_id = s.area_id,
       region_id = s.region_id,
       city_id = s.city_id,
       updated = now(),
       status = 'A'
 FROM (
   WITH RECURSIVE parents AS (
     SELECT
            aoguid rootguid,
            aoguid,
            parentguid,
            aolevel
       FROM addrobj
      WHERE 1 = 1
        AND aolevel IN (4, 6, 90)
        AND currstatus = 0
        and actstatus = 1
     UNION ALL
     SELECT
            p.rootguid,
            a.aoguid,
            a.parentguid,
            a.aolevel
       FROM addrobj a
       JOIN parents p ON a.aoguid = p.parentguid
      WHERE 1=1
        and a.currstatus = 0
        and a.actstatus = 1
  )
  SELECT s.*, a.id area_id, r.id region_id, c.id city_id
    FROM addrobj s
    JOIN parents pr on pr.rootguid = s.aoguid and pr.aolevel = 1
    join regions r on r.guid = pr.aoguid
    left JOIN parents pa on pa.rootguid = s.aoguid and pa.aolevel = 3
    left join areas a on a.guid = pa.aoguid
    left join parents pc on pc.rootguid = s.aoguid and pc.aolevel = 4
    left join places c on c.guid = pc.aoguid
  where 1=1
    and s.aolevel in (4,6,90)
    and s.currstatus = 0
    and s.actstatus = 1
  ) as s
where 1=1
  and p.guid = s.aoguid
  and (
      p.name <> s.formalname
      or p.typ <> s.shortname
      or p.code <> s.placecode
      or coalesce(p.area_id,-1) <> coalesce(s.area_id,-1)
      or coalesce(p.region_id,-1) <> coalesce(s.region_id,-1)
      or coalesce(p.city_id,-1) <> coalesce(s.city_id,-1)
      or p.status <> 'A'
  );


update places p
   set status = 'D'
where 1=1
  and NOT exists(
    select 1
    FROM addrobj s
    where 1=1
          and s.aolevel in (4,6,90)
          and s.currstatus = 0
          and s.actstatus = 1
          and s.aoguid = p.guid
);


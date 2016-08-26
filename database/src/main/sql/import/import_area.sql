insert into areas (name,typ,code,region_id,guid,okato)
  SELECT s.formalname,s.shortname,s.areacode,r.id,s.aoguid,s.okato
  FROM addrobj s
       join regions r on r.guid = s.parentguid
      where 1=1
        and s.aolevel = 3
        and s.currstatus = 0
        and s.actstatus = 1
        and not EXISTS (
          select 1
            from areas a
           where a.guid = s.aoguid
        );

update areas t
  set name = s.formalname, code = s.areacode, typ = s.shortname, region_id = r.id, updated = now(), status = 'A'
 from addrobj s
  join regions r on r.guid = s.parentguid
WHERE 1=1
  and t.guid = s.aoguid
  and s.currstatus = 0
  and s.aolevel = 3
  and s.actstatus = 1
  and (s.formalname <> t.name
    or s.areacode <> t.code
    or s.shortname <> t.typ
    or r.id <> t.region_id
    or t.status <> 'A');

UPDATE areas as t
set status = 'D', updated = now()
WHERE not exists (SELECT 1
                  FROM addrobj s
                 WHERE 1=1
                   and s.aoguid = t.guid
                   and s.aolevel = 3
                   and s.actstatus = 1
                   and s.currstatus = 0);


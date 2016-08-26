insert into regions (name,typ,code,guid,okato)
  SELECT s.formalname,s.shortname,s.regioncode,s.aoguid,s.okato
  FROM addrobj s
  where 1=1
    and s.aolevel = 1
    and s.actstatus = 1
    and s.currstatus = 0
    and s.aoguid not in (select guid from regions);

UPDATE regions as t
  set name = s.formalname, code = s.regioncode, typ = s.shortname, updated = now(), status = 'A'
  from addrobj as s
  WHERE 1=1
    and t.guid = s.aoguid
    and s.currstatus = 0
    and s.actstatus = 1
    and s.aolevel = 1
    and (s.formalname <> t.name
      or s.regioncode <> t.code
      OR s.shortname <> t.typ
      or t.status <> 'A');

UPDATE regions as t
set status = 'D', updated = now()
WHERE not exists (SELECT 1
                    FROM addrobj s
                   WHERE 1=1
                     and s.aoguid = t.guid
                     and s.aolevel = 1
                     and s.currstatus = 0
                     and s.actstatus = 1);


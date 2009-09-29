insert into ht_role(roleid,rolename,roledesc) values(0,'admin','管理员');
insert into ht_role(roleid,rolename,roledesc) values(-1,'operator','员工');
insert into ht_operator(pid,loginname,desc,password,createdate,role_roleid) values(-1,'李艳民','管理员','123456','2009-09-09 00:00:00',-1);
--------------------------------------------------------------------------------------------------------------------------------

INSERT INTO fs_auto.profile (id,name,code,access_permission)
VALUES (
    nextval('fs_auto.profile_seq') --id
   ,'NOPERMISSION' --name
   ,'DF001' --code
   ,'NO_ACCESS' --access_permission
);
---------------------------------------------------------------
INSERT INTO fs_auto.profile (id,name,code,access_permission)
VALUES (
    nextval('fs_auto.profile_seq') --id
   ,'DEFAULT' --name
   ,'DF002' --code
   ,'READING' --access_permission
);
---------------------------------------------------------------
INSERT INTO fs_auto.profile (id,name,code,access_permission)
VALUES (
    nextval('fs_auto.profile_seq') --id
   ,'ADMIN' --name
   ,'DF003' --code
   ,'WRITING' --access_permission
);

--------------------------------------------------------------------------------------------------------------------------------

INSERT INTO fs_auto.person (id,name,cpf,cell_phone,city,zip_code,address,registration_date)
VALUES (
    nextval('fs_auto.person_seq') --id
   ,'ADMIN' --name
   ,null --cpf
   ,null --cell_phone
   ,null --city
   ,null --zip_code
   ,null --address
   ,now() --registration_date
);

--------------------------------------------------------------------------------------------------------------------------------

INSERT INTO fs_auto.app_user (id,name,key,person_id,profile_id,start_date,end_date,registration_date)
VALUES (
    nextval('fs_auto.app_user_seq') --id
   ,'admin' --name
   ,'admin123' --key
   ,(SELECT id FROM fs_auto.person WHERE name = 'ADMIN') --person_id
   ,(SELECT id FROM fs_auto.profile WHERE code = 'DF003') --profile_id
   ,now() --start_date
   ,(now() + interval '30 day') --end_date
   ,now() --registration_date
);
---------------------------------------------------------------
INSERT INTO fs_auto.app_user (id,name,key,person_id,profile_id,start_date,end_date,registration_date)
VALUES (
    nextval('fs_auto.app_user_seq') --id
   ,'outher' --name
   ,'outher123' --key
   ,null --person_id
   ,(SELECT id FROM fs_auto.profile WHERE code = 'DF002') --profile_id
   ,now() --start_date
   ,(now() + interval '20 day') --end_date
   ,now() --registration_date
);

--------------------------------------------------------------------------------------------------------------------------------

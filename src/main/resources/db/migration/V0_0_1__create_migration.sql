INSERT INTO fs_auto.profile (id,name,code,access_permission)
VALUES (
    nextval('fs_auto.profile_seq') --id
   ,'DEFAULT' --name
   ,'DF001' --code
   ,'NO_ACCESS' --access_permission
);

INSERT INTO fs_auto.app_user (id,name,key,person_id,profile_id,start_date,end_date,registration_date)
VALUES (
    nextval('fs_auto.app_user_seq') --id
   ,'DEFAULT' --name
   ,'' --key
   ,null --person_id
   ,(SELECT id FROM fs_auto.profile WHERE code = 'DF001') --profile_id
   ,now() --start_date
   ,(now() + interval '20 day') --end_date
   ,now() --registration_date
);
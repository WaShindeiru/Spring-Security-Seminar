select * from application_user u
                  inner join user_authorities ua on ua.user_id = u.id
                  inner join authority a on a.id = ua.authority_id;
select * from application_user;
select * from user_authorities;
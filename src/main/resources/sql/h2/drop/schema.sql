alter table T_ACTIVITI_USER_ROLE drop constraint FK_T_ACTIVITI_USER_ROLE_USER_ID;
alter table T_ACTIVITI_USER_ROLE drop constraint FK_T_ACTIVITI_USER_ROLE_ROLE_ID;

drop table T_ACTIVITI_ROLE if exists;

drop table T_ACTIVITI_USER if exists;

drop table T_ACTIVITI_USER_ROLE if exists;
drop table T_ACTIVITI_LEAVE if exists;
drop table T_ACTIVITI_DEPARTMENT if exists;

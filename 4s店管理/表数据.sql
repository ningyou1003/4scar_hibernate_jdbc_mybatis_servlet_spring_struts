prompt PL/SQL Developer import file
prompt Created on 2017年3月17日 by zhql
set feedback off
set define off
prompt Creating T_CLASS...
create table T_CLASS
(
  id          NUMBER(10) not null,
  name        VARCHAR2(32) not null,
  charger     VARCHAR2(32) not null,
  create_date DATE
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table T_CLASS
  is '班级信息表';
comment on column T_CLASS.id
  is '记录ID';
comment on column T_CLASS.name
  is '班级名称';
comment on column T_CLASS.charger
  is '班主任';
comment on column T_CLASS.create_date
  is '创建日期';
alter table T_CLASS
  add constraint T_CLASS_PK primary key (ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating T_COURSE...
create table T_COURSE
(
  id          NUMBER(10) not null,
  name        VARCHAR2(32) not null,
  credit      NUMBER(8,2),
  create_date DATE
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table T_COURSE
  is '课程信息表';
comment on column T_COURSE.id
  is '记录ID(PK)';
comment on column T_COURSE.name
  is '课程名称';
comment on column T_COURSE.credit
  is '学分';
comment on column T_COURSE.create_date
  is '创建日期';

prompt Creating T_COURSE_ON...
create table T_COURSE_ON
(
  id          NUMBER(10) not null,
  teacher_id  NUMBER(10) not null,
  course_id   NUMBER(10),
  year        NUMBER(6),
  school_term NUMBER(2),
  state       NUMBER(2),
  create_date DATE
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table T_COURSE_ON
  is '开课信息表';
comment on column T_COURSE_ON.id
  is '记录ID(PK)';
comment on column T_COURSE_ON.teacher_id
  is '授课老师ID(FK)';
comment on column T_COURSE_ON.course_id
  is '课程ID(FK)';
comment on column T_COURSE_ON.year
  is '年度(如:2014,2015,2016)';
comment on column T_COURSE_ON.school_term
  is '学期(1-春季学期,2-秋季学期)';
comment on column T_COURSE_ON.state
  is '状态(1-正常,2-无效)';
comment on column T_COURSE_ON.create_date
  is '创建日期';

prompt Creating T_DICT...
create table T_DICT
(
  id          NUMBER(10),
  dict_name   VARCHAR2(32),
  ckey        VARCHAR2(32),
  cvalue      VARCHAR2(32),
  use_flag    NUMBER(2),
  order_no    NUMBER(2),
  create_date DATE
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on column T_DICT.id
  is '记录ID';
comment on column T_DICT.dict_name
  is '字典名称';
comment on column T_DICT.ckey
  is '字典KEY';
comment on column T_DICT.cvalue
  is '字典VALUE';
comment on column T_DICT.use_flag
  is '使用状态(1-可用,2-不可用)';
comment on column T_DICT.order_no
  is '顺序号';
comment on column T_DICT.create_date
  is '创建日期';

prompt Creating T_HBM_CLASS...
create table T_HBM_CLASS
(
  id          NUMBER(10) not null,
  name        VARCHAR2(32) not null,
  charger     VARCHAR2(32) not null,
  create_date DATE
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
alter table T_HBM_CLASS
  add constraint PK_T_HBM_CLASS primary key (ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating T_HBM_COURSE...
create table T_HBM_COURSE
(
  id          NUMBER(10) not null,
  name        VARCHAR2(32) not null,
  credit      NUMBER(8,2),
  create_date DATE
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );

prompt Creating T_HBM_COURSE_ON...
create table T_HBM_COURSE_ON
(
  id          NUMBER(10) not null,
  teacher_id  NUMBER(10) not null,
  course_id   NUMBER(10),
  year        NUMBER(6),
  school_term NUMBER(2),
  state       NUMBER(2),
  create_date DATE
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating T_HBM_DICT...
create table T_HBM_DICT
(
  id          NUMBER(10) not null,
  dict_name   VARCHAR2(32),
  ckey        VARCHAR2(32),
  cvalue      VARCHAR2(32),
  use_flag    NUMBER(2),
  order_no    NUMBER(2),
  create_date DATE
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
alter table T_HBM_DICT
  add constraint PK_T_HBM_DICT primary key (ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating T_HBM_MENU...
create table T_HBM_MENU
(
  id          NUMBER(10) not null,
  name        VARCHAR2(32) not null,
  url         VARCHAR2(32) not null,
  parent_id   NUMBER(10) not null,
  menu_level  NUMBER(2),
  create_date DATE
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating T_HBM_ROLE...
create table T_HBM_ROLE
(
  id          NUMBER(10),
  name        VARCHAR2(32),
  create_date DATE
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating T_HBM_ROLE2MENU...
create table T_HBM_ROLE2MENU
(
  role_id NUMBER(19) not null,
  menu_id NUMBER(19) not null
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table T_HBM_ROLE2MENU
  add primary key (ROLE_ID, MENU_ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating T_HBM_SCORE...
create table T_HBM_SCORE
(
  id         NUMBER(10) not null,
  student_id NUMBER(10) not null,
  course_id  NUMBER(10),
  score      NUMBER(8,2)
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating T_HBM_STUDENT...
create table T_HBM_STUDENT
(
  id          NUMBER(10) not null,
  name        VARCHAR2(32),
  sex         NUMBER(1),
  birthday    DATE,
  snative     VARCHAR2(32),
  class_id    NUMBER(10),
  create_date DATE
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
alter table T_HBM_STUDENT
  add constraint PK_T_HBM_STUDENT primary key (ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table T_HBM_STUDENT
  add constraint FK_SUH6QT6JOUUKI3Q6LMTKHNQ0W foreign key (CLASS_ID)
  references T_HBM_CLASS (ID);

prompt Creating T_HBM_STU_COURSE...
create table T_HBM_STU_COURSE
(
  id          NUMBER(10) not null,
  student_id  NUMBER(10) not null,
  course_id   NUMBER(10),
  create_date DATE,
  stu_id      NUMBER(19)
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating T_HBM_TEACHER...
create table T_HBM_TEACHER
(
  id          NUMBER(10) not null,
  name        VARCHAR2(32) not null,
  sex         NUMBER(1),
  birthday    DATE,
  work_date   DATE,
  specialty   VARCHAR2(32) not null,
  tlevel      NUMBER(2) not null,
  create_date DATE
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating T_HBM_USER...
create table T_HBM_USER
(
  id          NUMBER(10) not null,
  name        VARCHAR2(32) not null,
  sex         NUMBER(1) not null,
  birthday    DATE not null,
  username    VARCHAR2(32) not null,
  password    VARCHAR2(32) not null,
  login_flag  NUMBER(1) not null,
  role_id     NUMBER(10),
  file_path   VARCHAR2(32),
  create_date DATE
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating T_LOG...
create table T_LOG
(
  log         VARCHAR2(64),
  state       NUMBER(2),
  create_date DATE
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table T_LOG
  is '日志表';
comment on column T_LOG.log
  is '日志描述';
comment on column T_LOG.state
  is '状态(0-失败,1-成功)';
comment on column T_LOG.create_date
  is '创建日期';

prompt Creating T_MENU...
create table T_MENU
(
  id          NUMBER(10) not null,
  name        VARCHAR2(32) not null,
  url         VARCHAR2(32) not null,
  parent_id   NUMBER(10) not null,
  menu_level  NUMBER(2),
  create_date DATE
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table T_MENU
  is '菜单信息表';
comment on column T_MENU.id
  is '菜单ID(PK)';
comment on column T_MENU.name
  is '菜单名称';
comment on column T_MENU.url
  is '链接URL';
comment on column T_MENU.parent_id
  is '父菜单ID(FK)';
comment on column T_MENU.menu_level
  is '菜单级别(1-一级,2-二级,3-权限)';
comment on column T_MENU.create_date
  is '创建日期';
alter table T_MENU
  add constraint T_MENU_PK primary key (ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating T_ROLE...
create table T_ROLE
(
  id          NUMBER(10),
  name        VARCHAR2(32),
  create_date DATE
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on column T_ROLE.id
  is '记录ID(PK)';
comment on column T_ROLE.name
  is '角色名称';
comment on column T_ROLE.create_date
  is '创建日期';

prompt Creating T_ROLE2MENU...
create table T_ROLE2MENU
(
  role_id NUMBER(10) not null,
  menu_id NUMBER(10) not null
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on column T_ROLE2MENU.role_id
  is '角色ID(FK)';
comment on column T_ROLE2MENU.menu_id
  is '菜单ID(FK)';

prompt Creating T_SCORE...
create table T_SCORE
(
  id          NUMBER(10) not null,
  student_id  NUMBER(10) not null,
  study_year  VARCHAR2(32),
  maths       NUMBER(5,2),
  english     NUMBER(5,2),
  create_date DATE
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table T_SCORE
  is '学生成绩信息表';
comment on column T_SCORE.id
  is 'ID(PK-主键)';
comment on column T_SCORE.student_id
  is '学生ID(FK-外键)';
comment on column T_SCORE.study_year
  is '学年(yyyy-春季,yyyy-秋季)';
comment on column T_SCORE.maths
  is '数学';
comment on column T_SCORE.english
  is '英语';
comment on column T_SCORE.create_date
  is '创建日期';

prompt Creating T_STUDENT...
create table T_STUDENT
(
  id          NUMBER(10) not null,
  name        VARCHAR2(32) not null,
  sex         NUMBER(1) not null,
  birthday    DATE not null,
  snative     VARCHAR2(32) not null,
  class_id    NUMBER(10),
  create_date DATE
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table T_STUDENT
  is '学生信息表';
comment on column T_STUDENT.id
  is '记录ID';
comment on column T_STUDENT.name
  is '姓名';
comment on column T_STUDENT.sex
  is '性别(1-男,2-女)';
comment on column T_STUDENT.birthday
  is '出生日期';
comment on column T_STUDENT.snative
  is '籍貫';
comment on column T_STUDENT.class_id
  is '班級ID(FK)';
comment on column T_STUDENT.create_date
  is '創建日期';
create index IDX_STUDENT1 on T_STUDENT (BIRTHDAY)
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index IDX_STUDENT2 on T_STUDENT (TRUNC(BIRTHDAY))
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table T_STUDENT
  add constraint PK_T_STUDENT primary key (ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating T_STU_COURSE...
create table T_STU_COURSE
(
  id          NUMBER(10) not null,
  student_id  NUMBER(10) not null,
  course_id   NUMBER(10) not null,
  create_date DATE,
  score       NUMBER(10,2),
  remark      VARCHAR2(64)
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table T_STU_COURSE
  is '学生选课信息表';
comment on column T_STU_COURSE.id
  is '记录ID(PK)';
comment on column T_STU_COURSE.student_id
  is '学生ID(FK)';
comment on column T_STU_COURSE.course_id
  is '课程ID(FK)';
comment on column T_STU_COURSE.create_date
  is '创建日期';
comment on column T_STU_COURSE.score
  is '成绩';
comment on column T_STU_COURSE.remark
  is '备注';

prompt Creating T_TEACHER...
create table T_TEACHER
(
  id          NUMBER(10) not null,
  name        VARCHAR2(32) not null,
  sex         NUMBER(1),
  birthday    DATE,
  work_date   DATE,
  specialty   VARCHAR2(32) not null,
  tlevel      NUMBER(2) not null,
  create_date DATE
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on column T_TEACHER.id
  is '记录ID';
comment on column T_TEACHER.name
  is '姓名';
comment on column T_TEACHER.sex
  is '性别(1-男,2-女)';
comment on column T_TEACHER.birthday
  is '出生日期';
comment on column T_TEACHER.work_date
  is '开始工作日期';
comment on column T_TEACHER.specialty
  is '专业方向';
comment on column T_TEACHER.tlevel
  is '职称等级(1-初级,2-中级,3-高级)';
comment on column T_TEACHER.create_date
  is '创建日期';

prompt Creating T_USER...
create table T_USER
(
  id          NUMBER(10) not null,
  name        VARCHAR2(32) not null,
  sex         NUMBER(1) not null,
  birthday    DATE not null,
  username    VARCHAR2(32) not null,
  password    VARCHAR2(32) not null,
  login_flag  NUMBER(1) not null,
  role_id     NUMBER(10),
  file_path   VARCHAR2(32),
  create_date DATE
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table T_USER
  is '用户信息表';
comment on column T_USER.id
  is '记录ID';
comment on column T_USER.name
  is '姓名';
comment on column T_USER.sex
  is '性别(1-男,2-女)';
comment on column T_USER.birthday
  is '出生日期';
comment on column T_USER.username
  is '用户名';
comment on column T_USER.password
  is '密码';
comment on column T_USER.login_flag
  is '登录状态(1-正常,2-无效)';
comment on column T_USER.role_id
  is '角色ID(FK)';
comment on column T_USER.file_path
  is '文件路径';
comment on column T_USER.create_date
  is '创建日期';
create index IDX_USER1 on T_USER (USERNAME, SEX, LOGIN_FLAG)
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table T_USER
  add constraint T_USER_PK primary key (ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Disabling triggers for T_CLASS...
alter table T_CLASS disable all triggers;
prompt Disabling triggers for T_COURSE...
alter table T_COURSE disable all triggers;
prompt Disabling triggers for T_COURSE_ON...
alter table T_COURSE_ON disable all triggers;
prompt Disabling triggers for T_DICT...
alter table T_DICT disable all triggers;
prompt Disabling triggers for T_HBM_CLASS...
alter table T_HBM_CLASS disable all triggers;
prompt Disabling triggers for T_HBM_COURSE...
alter table T_HBM_COURSE disable all triggers;
prompt Disabling triggers for T_HBM_COURSE_ON...
alter table T_HBM_COURSE_ON disable all triggers;
prompt Disabling triggers for T_HBM_DICT...
alter table T_HBM_DICT disable all triggers;
prompt Disabling triggers for T_HBM_MENU...
alter table T_HBM_MENU disable all triggers;
prompt Disabling triggers for T_HBM_ROLE...
alter table T_HBM_ROLE disable all triggers;
prompt Disabling triggers for T_HBM_ROLE2MENU...
alter table T_HBM_ROLE2MENU disable all triggers;
prompt Disabling triggers for T_HBM_SCORE...
alter table T_HBM_SCORE disable all triggers;
prompt Disabling triggers for T_HBM_STUDENT...
alter table T_HBM_STUDENT disable all triggers;
prompt Disabling triggers for T_HBM_STU_COURSE...
alter table T_HBM_STU_COURSE disable all triggers;
prompt Disabling triggers for T_HBM_TEACHER...
alter table T_HBM_TEACHER disable all triggers;
prompt Disabling triggers for T_HBM_USER...
alter table T_HBM_USER disable all triggers;
prompt Disabling triggers for T_LOG...
alter table T_LOG disable all triggers;
prompt Disabling triggers for T_MENU...
alter table T_MENU disable all triggers;
prompt Disabling triggers for T_ROLE...
alter table T_ROLE disable all triggers;
prompt Disabling triggers for T_ROLE2MENU...
alter table T_ROLE2MENU disable all triggers;
prompt Disabling triggers for T_SCORE...
alter table T_SCORE disable all triggers;
prompt Disabling triggers for T_STUDENT...
alter table T_STUDENT disable all triggers;
prompt Disabling triggers for T_STU_COURSE...
alter table T_STU_COURSE disable all triggers;
prompt Disabling triggers for T_TEACHER...
alter table T_TEACHER disable all triggers;
prompt Disabling triggers for T_USER...
alter table T_USER disable all triggers;
prompt Disabling foreign key constraints for T_HBM_STUDENT...
alter table T_HBM_STUDENT disable constraint FK_SUH6QT6JOUUKI3Q6LMTKHNQ0W;
prompt Deleting T_USER...
delete from T_USER;
commit;
prompt Deleting T_TEACHER...
delete from T_TEACHER;
commit;
prompt Deleting T_STU_COURSE...
delete from T_STU_COURSE;
commit;
prompt Deleting T_STUDENT...
delete from T_STUDENT;
commit;
prompt Deleting T_SCORE...
delete from T_SCORE;
commit;
prompt Deleting T_ROLE2MENU...
delete from T_ROLE2MENU;
commit;
prompt Deleting T_ROLE...
delete from T_ROLE;
commit;
prompt Deleting T_MENU...
delete from T_MENU;
commit;
prompt Deleting T_LOG...
delete from T_LOG;
commit;
prompt Deleting T_HBM_USER...
delete from T_HBM_USER;
commit;
prompt Deleting T_HBM_TEACHER...
delete from T_HBM_TEACHER;
commit;
prompt Deleting T_HBM_STU_COURSE...
delete from T_HBM_STU_COURSE;
commit;
prompt Deleting T_HBM_STUDENT...
delete from T_HBM_STUDENT;
commit;
prompt Deleting T_HBM_SCORE...
delete from T_HBM_SCORE;
commit;
prompt Deleting T_HBM_ROLE2MENU...
delete from T_HBM_ROLE2MENU;
commit;
prompt Deleting T_HBM_ROLE...
delete from T_HBM_ROLE;
commit;
prompt Deleting T_HBM_MENU...
delete from T_HBM_MENU;
commit;
prompt Deleting T_HBM_DICT...
delete from T_HBM_DICT;
commit;
prompt Deleting T_HBM_COURSE_ON...
delete from T_HBM_COURSE_ON;
commit;
prompt Deleting T_HBM_COURSE...
delete from T_HBM_COURSE;
commit;
prompt Deleting T_HBM_CLASS...
delete from T_HBM_CLASS;
commit;
prompt Deleting T_DICT...
delete from T_DICT;
commit;
prompt Deleting T_COURSE_ON...
delete from T_COURSE_ON;
commit;
prompt Deleting T_COURSE...
delete from T_COURSE;
commit;
prompt Deleting T_CLASS...
delete from T_CLASS;
commit;
prompt Loading T_CLASS...
insert into T_CLASS (id, name, charger, create_date)
values (1, '软件工程一班', '李小珍', to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_CLASS (id, name, charger, create_date)
values (2, '软件工程二班', '彭娟', to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_CLASS (id, name, charger, create_date)
values (3, '软件工程三班', '王小利', to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_CLASS (id, name, charger, create_date)
values (4, '软件工程四班', '李小彤', to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_CLASS (id, name, charger, create_date)
values (5, '软件工程五班', '潘霞', to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_CLASS (id, name, charger, create_date)
values (6, '软件工程六班', '杨柳', to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_CLASS (id, name, charger, create_date)
values (7, '软件工程七班', '杨柳', to_date('05-01-2017', 'dd-mm-yyyy'));
commit;
prompt 7 records loaded
prompt Loading T_COURSE...
insert into T_COURSE (id, name, credit, create_date)
values (1, 'JAVA基础', 3, to_date('01-03-2017', 'dd-mm-yyyy'));
insert into T_COURSE (id, name, credit, create_date)
values (2, 'C++', 2, to_date('01-03-2017', 'dd-mm-yyyy'));
insert into T_COURSE (id, name, credit, create_date)
values (3, '数据结构', 2.5, to_date('01-03-2017', 'dd-mm-yyyy'));
insert into T_COURSE (id, name, credit, create_date)
values (4, '软件工程', 1.5, to_date('01-03-2017', 'dd-mm-yyyy'));
insert into T_COURSE (id, name, credit, create_date)
values (5, '软件架构', 2.5, to_date('01-03-2017', 'dd-mm-yyyy'));
insert into T_COURSE (id, name, credit, create_date)
values (112, '网络技术', 2.5, to_date('17-03-2017 15:10:14', 'dd-mm-yyyy hh24:mi:ss'));
insert into T_COURSE (id, name, credit, create_date)
values (110, 'UI设计', 2, to_date('17-03-2017 15:09:27', 'dd-mm-yyyy hh24:mi:ss'));
insert into T_COURSE (id, name, credit, create_date)
values (111, '软件测试', 1.5, to_date('17-03-2017 15:09:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into T_COURSE (id, name, credit, create_date)
values (113, '微机原理', 2, to_date('17-03-2017 15:10:28', 'dd-mm-yyyy hh24:mi:ss'));
commit;
prompt 9 records loaded
prompt Loading T_COURSE_ON...
insert into T_COURSE_ON (id, teacher_id, course_id, year, school_term, state, create_date)
values (1, 1, 1, 2016, 1, 1, to_date('01-03-2017', 'dd-mm-yyyy'));
insert into T_COURSE_ON (id, teacher_id, course_id, year, school_term, state, create_date)
values (2, 2, 2, 2016, 1, 1, to_date('01-03-2017', 'dd-mm-yyyy'));
insert into T_COURSE_ON (id, teacher_id, course_id, year, school_term, state, create_date)
values (103, 102, 111, 2015, 2, 2, to_date('17-03-2017 15:11:11', 'dd-mm-yyyy hh24:mi:ss'));
insert into T_COURSE_ON (id, teacher_id, course_id, year, school_term, state, create_date)
values (101, 1, 5, 2016, 1, 1, to_date('17-03-2017 15:06:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into T_COURSE_ON (id, teacher_id, course_id, year, school_term, state, create_date)
values (102, 102, 4, 2017, 1, 1, to_date('17-03-2017 15:09:01', 'dd-mm-yyyy hh24:mi:ss'));
insert into T_COURSE_ON (id, teacher_id, course_id, year, school_term, state, create_date)
values (104, 102, 113, 2016, 2, 1, to_date('17-03-2017 15:11:36', 'dd-mm-yyyy hh24:mi:ss'));
commit;
prompt 6 records loaded
prompt Loading T_DICT...
insert into T_DICT (id, dict_name, ckey, cvalue, use_flag, order_no, create_date)
values (1, 'SYS_SEX', '1', '男', 1, 2, to_date('29-12-2016', 'dd-mm-yyyy'));
insert into T_DICT (id, dict_name, ckey, cvalue, use_flag, order_no, create_date)
values (2, 'SYS_SEX', '2', '女', 1, 4, to_date('29-12-2016', 'dd-mm-yyyy'));
insert into T_DICT (id, dict_name, ckey, cvalue, use_flag, order_no, create_date)
values (3, 'SYS_STATE', '1', '正常', 1, 2, to_date('29-12-2016', 'dd-mm-yyyy'));
insert into T_DICT (id, dict_name, ckey, cvalue, use_flag, order_no, create_date)
values (4, 'SYS_STATE', '2', '无效', 1, 4, to_date('29-12-2016', 'dd-mm-yyyy'));
insert into T_DICT (id, dict_name, ckey, cvalue, use_flag, order_no, create_date)
values (7, 'MENU_LEVEL', '1', '一级菜单', 1, 2, to_date('06-01-2017', 'dd-mm-yyyy'));
insert into T_DICT (id, dict_name, ckey, cvalue, use_flag, order_no, create_date)
values (8, 'MENU_LEVEL', '2', '二级菜单', 1, 5, to_date('06-01-2017', 'dd-mm-yyyy'));
insert into T_DICT (id, dict_name, ckey, cvalue, use_flag, order_no, create_date)
values (130, 'PRO_LEVLE', '1', '初级', 1, 1, to_date('15-03-2017 16:53:06', 'dd-mm-yyyy hh24:mi:ss'));
insert into T_DICT (id, dict_name, ckey, cvalue, use_flag, order_no, create_date)
values (131, 'PRO_LEVLE', '2', '中级', 1, 3, to_date('15-03-2017 16:53:19', 'dd-mm-yyyy hh24:mi:ss'));
insert into T_DICT (id, dict_name, ckey, cvalue, use_flag, order_no, create_date)
values (132, 'PRO_LEVLE', '3', '高级', 1, 5, to_date('15-03-2017 16:53:37', 'dd-mm-yyyy hh24:mi:ss'));
insert into T_DICT (id, dict_name, ckey, cvalue, use_flag, order_no, create_date)
values (9, 'SCHOOL_TERM', '1', '春季学期', 1, 1, to_date('15-03-2017', 'dd-mm-yyyy'));
insert into T_DICT (id, dict_name, ckey, cvalue, use_flag, order_no, create_date)
values (10, 'SCHOOL_TERM', '2', '秋季学期', 1, 3, to_date('15-03-2017', 'dd-mm-yyyy'));
commit;
prompt 11 records loaded
prompt Loading T_HBM_CLASS...
insert into T_HBM_CLASS (id, name, charger, create_date)
values (1, '软件工程一班', '李小珍', to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_CLASS (id, name, charger, create_date)
values (2, '软件工程二班', '彭娟', to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_CLASS (id, name, charger, create_date)
values (3, '软件工程三班', '王小利', to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_CLASS (id, name, charger, create_date)
values (4, '软件工程四班', '李小彤', to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_CLASS (id, name, charger, create_date)
values (5, '软件工程五班', '潘霞', to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_CLASS (id, name, charger, create_date)
values (6, '软件工程六班', '杨柳', to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_CLASS (id, name, charger, create_date)
values (7, '软件工程七班', '杨柳', to_date('05-01-2017', 'dd-mm-yyyy'));
insert into T_HBM_CLASS (id, name, charger, create_date)
values (8, '测试班级二', '李小萍', to_date('09-03-2017 10:29:36', 'dd-mm-yyyy hh24:mi:ss'));
commit;
prompt 8 records loaded
prompt Loading T_HBM_COURSE...
insert into T_HBM_COURSE (id, name, credit, create_date)
values (1, 'JAVA基础', 3, to_date('01-03-2017', 'dd-mm-yyyy'));
insert into T_HBM_COURSE (id, name, credit, create_date)
values (2, 'C++', 2, to_date('01-03-2017', 'dd-mm-yyyy'));
insert into T_HBM_COURSE (id, name, credit, create_date)
values (3, '数据结构', 2.5, to_date('01-03-2017', 'dd-mm-yyyy'));
insert into T_HBM_COURSE (id, name, credit, create_date)
values (4, '软件工程', 1.5, to_date('01-03-2017', 'dd-mm-yyyy'));
insert into T_HBM_COURSE (id, name, credit, create_date)
values (5, '软件架构', 2.5, to_date('01-03-2017', 'dd-mm-yyyy'));
commit;
prompt 5 records loaded
prompt Loading T_HBM_COURSE_ON...
prompt Table is empty
prompt Loading T_HBM_DICT...
insert into T_HBM_DICT (id, dict_name, ckey, cvalue, use_flag, order_no, create_date)
values (1, 'SYS_SEX', '1', '男', 1, 2, to_date('29-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_DICT (id, dict_name, ckey, cvalue, use_flag, order_no, create_date)
values (2, 'SYS_SEX', '2', '女', 1, 4, to_date('29-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_DICT (id, dict_name, ckey, cvalue, use_flag, order_no, create_date)
values (3, 'SYS_STATE', '1', '正常', 1, 2, to_date('29-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_DICT (id, dict_name, ckey, cvalue, use_flag, order_no, create_date)
values (4, 'SYS_STATE', '2', '无效', 1, 4, to_date('29-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_DICT (id, dict_name, ckey, cvalue, use_flag, order_no, create_date)
values (7, 'MENU_LEVEL', '1', '一级菜单', 1, 2, to_date('06-01-2017', 'dd-mm-yyyy'));
insert into T_HBM_DICT (id, dict_name, ckey, cvalue, use_flag, order_no, create_date)
values (8, 'MENU_LEVEL', '2', '二级菜单', 1, 5, to_date('06-01-2017', 'dd-mm-yyyy'));
insert into T_HBM_DICT (id, dict_name, ckey, cvalue, use_flag, order_no, create_date)
values (9, 'PRO_LEVLE', '1', '初级', 1, 1, to_date('06-01-2017', 'dd-mm-yyyy'));
insert into T_HBM_DICT (id, dict_name, ckey, cvalue, use_flag, order_no, create_date)
values (10, 'PRO_LEVLE', '2', '中级', 1, 3, to_date('06-01-2017', 'dd-mm-yyyy'));
insert into T_HBM_DICT (id, dict_name, ckey, cvalue, use_flag, order_no, create_date)
values (11, 'PRO_LEVLE', '3', '高级', 1, 5, to_date('06-01-2017', 'dd-mm-yyyy'));
commit;
prompt 9 records loaded
prompt Loading T_HBM_MENU...
insert into T_HBM_MENU (id, name, url, parent_id, menu_level, create_date)
values (2, '基础数据', 'url', 0, 1, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_MENU (id, name, url, parent_id, menu_level, create_date)
values (3, '字典管理', 'dict_list.do', 1, 2, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_MENU (id, name, url, parent_id, menu_level, create_date)
values (4, '菜单管理', 'menu_list.do', 1, 2, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_MENU (id, name, url, parent_id, menu_level, create_date)
values (5, '角色管理', 'role_list.do', 1, 2, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_MENU (id, name, url, parent_id, menu_level, create_date)
values (6, '用户管理', 'user_list.do', 1, 2, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_MENU (id, name, url, parent_id, menu_level, create_date)
values (7, '教学管理', 'url', 0, 1, to_date('06-01-2017', 'dd-mm-yyyy'));
insert into T_HBM_MENU (id, name, url, parent_id, menu_level, create_date)
values (8, '班级管理', 'tclass_list.do', 2, 2, to_date('06-01-2017', 'dd-mm-yyyy'));
insert into T_HBM_MENU (id, name, url, parent_id, menu_level, create_date)
values (9, '学生管理', 'student_list.do', 2, 2, to_date('06-01-2017', 'dd-mm-yyyy'));
insert into T_HBM_MENU (id, name, url, parent_id, menu_level, create_date)
values (10, '课程管理', 'course_list.do', 2, 2, to_date('06-01-2017', 'dd-mm-yyyy'));
insert into T_HBM_MENU (id, name, url, parent_id, menu_level, create_date)
values (11, '开课管理', 'courseon_list.do', 7, 2, to_date('06-01-2017', 'dd-mm-yyyy'));
insert into T_HBM_MENU (id, name, url, parent_id, menu_level, create_date)
values (12, '选课管理', 'coursesel_list.do', 7, 2, to_date('06-01-2017', 'dd-mm-yyyy'));
insert into T_HBM_MENU (id, name, url, parent_id, menu_level, create_date)
values (13, '成绩管理', 'score_list.do', 7, 2, to_date('06-01-2017', 'dd-mm-yyyy'));
insert into T_HBM_MENU (id, name, url, parent_id, menu_level, create_date)
values (1, '系统管理', 'url', 0, 1, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_MENU (id, name, url, parent_id, menu_level, create_date)
values (14, '教师管理', 'teacher_list.do', 2, 2, to_date('06-01-2017', 'dd-mm-yyyy'));
commit;
prompt 14 records loaded
prompt Loading T_HBM_ROLE...
insert into T_HBM_ROLE (id, name, create_date)
values (1, '管理员', to_date('08-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_ROLE (id, name, create_date)
values (2, '教师角色', to_date('08-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_ROLE (id, name, create_date)
values (3, '学生角色', to_date('07-01-2017', 'dd-mm-yyyy'));
commit;
prompt 3 records loaded
prompt Loading T_HBM_ROLE2MENU...
insert into T_HBM_ROLE2MENU (role_id, menu_id)
values (1, 1);
insert into T_HBM_ROLE2MENU (role_id, menu_id)
values (1, 2);
insert into T_HBM_ROLE2MENU (role_id, menu_id)
values (1, 3);
insert into T_HBM_ROLE2MENU (role_id, menu_id)
values (1, 4);
insert into T_HBM_ROLE2MENU (role_id, menu_id)
values (1, 5);
insert into T_HBM_ROLE2MENU (role_id, menu_id)
values (1, 6);
insert into T_HBM_ROLE2MENU (role_id, menu_id)
values (1, 7);
insert into T_HBM_ROLE2MENU (role_id, menu_id)
values (1, 8);
insert into T_HBM_ROLE2MENU (role_id, menu_id)
values (1, 9);
insert into T_HBM_ROLE2MENU (role_id, menu_id)
values (1, 10);
insert into T_HBM_ROLE2MENU (role_id, menu_id)
values (1, 11);
insert into T_HBM_ROLE2MENU (role_id, menu_id)
values (1, 12);
insert into T_HBM_ROLE2MENU (role_id, menu_id)
values (1, 13);
insert into T_HBM_ROLE2MENU (role_id, menu_id)
values (1, 14);
commit;
prompt 14 records loaded
prompt Loading T_HBM_SCORE...
prompt Table is empty
prompt Loading T_HBM_STUDENT...
insert into T_HBM_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (1, '谢美玲', 2, to_date('12-12-2016', 'dd-mm-yyyy'), '广西百色', 3, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (2, '谢芳', 2, to_date('12-05-1993', 'dd-mm-yyyy'), '广西百色', 3, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (3, '何小玲', 2, to_date('12-12-1994', 'dd-mm-yyyy'), '广西百色', 1, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (4, '何金健', 1, to_date('14-02-1984', 'dd-mm-yyyy'), '广西百色', 1, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (5, '何康', 1, to_date('08-12-1995', 'dd-mm-yyyy'), '广西桂林', 2, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (6, '卢欢', 2, to_date('12-03-1992', 'dd-mm-yyyy'), '广西百色', 1, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (7, '韦小芳', 2, to_date('12-05-1993', 'dd-mm-yyyy'), '广西百色', 3, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (8, '王小利', 2, to_date('10-02-1995', 'dd-mm-yyyy'), '广西钦州', 4, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (9, '谢婷', 2, to_date('02-12-1995', 'dd-mm-yyyy'), '广西南宁', 5, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (10, '何玲', 2, to_date('12-12-1994', 'dd-mm-yyyy'), '广西百色', 1, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (11, '王鑫', 1, to_date('14-02-1994', 'dd-mm-yyyy'), '广西百色', 5, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (12, '何霖', 1, to_date('08-12-1990', 'dd-mm-yyyy'), '广西桂林', 2, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (13, '卢铮', 1, to_date('14-03-1992', 'dd-mm-yyyy'), '广西百色', 1, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (14, '王芳', 2, to_date('12-05-1993', 'dd-mm-yyyy'), '广西百色', 3, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (15, '周比利', 1, to_date('12-02-1995', 'dd-mm-yyyy'), '广西钦州', 4, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (16, '谢胜', 1, to_date('04-12-1995', 'dd-mm-yyyy'), '广西南宁', 5, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (17, '周小玲', 2, to_date('12-12-1994', 'dd-mm-yyyy'), '广西百色', 1, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (18, '周金健', 1, to_date('14-02-1994', 'dd-mm-yyyy'), '广西百色', 1, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (19, '黄康', 1, to_date('08-12-1993', 'dd-mm-yyyy'), '广西桂林', 2, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (20, '李欢', 2, to_date('12-03-1992', 'dd-mm-yyyy'), '广西百色', 1, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (21, '杨小芳', 2, to_date('12-05-1993', 'dd-mm-yyyy'), '广西百色', 3, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (22, '杨小利', 2, to_date('10-02-1995', 'dd-mm-yyyy'), '广西钦州', 4, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (23, '李婷', 2, to_date('02-12-1995', 'dd-mm-yyyy'), '广西南宁', 5, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (24, '王玲', 2, to_date('12-12-1994', 'dd-mm-yyyy'), '广西百色', 1, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (25, '学生姓名', 1, to_date('14-02-1994', 'dd-mm-yyyy'), '广西百色', 8, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (26, '卢霖', 1, to_date('08-12-1990', 'dd-mm-yyyy'), '广西桂林', 8, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (27, '王铮', 1, to_date('14-03-1992', 'dd-mm-yyyy'), '广西百色', 1, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (32, 'a1', 2, to_date('15-03-2017', 'dd-mm-yyyy'), 'a1', 7, to_date('10-03-2017 16:59:21', 'dd-mm-yyyy hh24:mi:ss'));
insert into T_HBM_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (31, '学生姓名', 2, to_date('09-03-2017 10:59:52', 'dd-mm-yyyy hh24:mi:ss'), '广西北海', 8, to_date('09-03-2017 10:59:52', 'dd-mm-yyyy hh24:mi:ss'));
insert into T_HBM_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (30, '学生姓名', 2, to_date('09-03-2017 10:29:36', 'dd-mm-yyyy hh24:mi:ss'), '广西北海', 8, to_date('09-03-2017 10:29:36', 'dd-mm-yyyy hh24:mi:ss'));
commit;
prompt 30 records loaded
prompt Loading T_HBM_STU_COURSE...
prompt Table is empty
prompt Loading T_HBM_TEACHER...
insert into T_HBM_TEACHER (id, name, sex, birthday, work_date, specialty, tlevel, create_date)
values (1, '李强', 1, to_date('15-03-1987', 'dd-mm-yyyy'), to_date('01-03-2007', 'dd-mm-yyyy'), '软件工程', 2, to_date('01-03-2017', 'dd-mm-yyyy'));
insert into T_HBM_TEACHER (id, name, sex, birthday, work_date, specialty, tlevel, create_date)
values (2, '张国力', 1, to_date('15-03-1977', 'dd-mm-yyyy'), to_date('01-03-2000', 'dd-mm-yyyy'), '计算机与科学', 3, to_date('01-03-2017', 'dd-mm-yyyy'));
commit;
prompt 2 records loaded
prompt Loading T_HBM_USER...
insert into T_HBM_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (111, '测试用户', 1, to_date('01-01-1995', 'dd-mm-yyyy'), 'admin', '123', 1, 0, null, to_date('28-02-2017 09:40:45', 'dd-mm-yyyy hh24:mi:ss'));
insert into T_HBM_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (112, '2', 1, to_date('13-03-2017', 'dd-mm-yyyy'), '2', 'null', 1, 3, '1488359144095.jpg', to_date('01-03-2017 17:05:44', 'dd-mm-yyyy hh24:mi:ss'));
insert into T_HBM_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (113, '3', 1, to_date('06-03-2017', 'dd-mm-yyyy'), '3', 'null', 1, 3, '1488421721528.jpg', to_date('02-03-2017 10:28:41', 'dd-mm-yyyy hh24:mi:ss'));
insert into T_HBM_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (2, '卢辉', 1, to_date('12-09-1990', 'dd-mm-yyyy'), 'lh', '1', 2, 1, null, to_date('21-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (3, '杨阳', 1, to_date('05-12-1995', 'dd-mm-yyyy'), 'yy', '123', 2, 1, null, to_date('21-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (4, '王明阳', 2, to_date('23-11-1990', 'dd-mm-yyyy'), 'wmy', '123', 2, 1, null, to_date('21-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (5, '李小凤', 2, to_date('02-01-1996', 'dd-mm-yyyy'), 'lxf', '123', 1, 1, null, to_date('21-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (6, '彭娟', 2, to_date('09-01-1995', 'dd-mm-yyyy'), 'pj', '1', 2, 1, null, to_date('22-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (7, '黄奇进', 2, to_date('10-12-2001', 'dd-mm-yyyy'), 'hqj', '1', 1, 1, null, to_date('06-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (8, '许晓华', 2, to_date('01-12-2002', 'dd-mm-yyyy'), 'xxh', '2', 1, 1, null, to_date('02-11-2016', 'dd-mm-yyyy'));
insert into T_HBM_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (9, '肖建郡', 1, to_date('02-12-2016', 'dd-mm-yyyy'), 'xjj', '12', 1, 1, null, to_date('02-11-2016', 'dd-mm-yyyy'));
insert into T_HBM_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (10, '李力', 1, to_date('01-12-2016', 'dd-mm-yyyy'), 'll', '123', 1, 1, null, to_date('06-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (12, '李向阳', 1, to_date('07-02-1994', 'dd-mm-yyyy'), 'abc', '123', 2, 1, null, to_date('26-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (15, '测试用户1', 1, to_date('30-12-2016', 'dd-mm-yyyy'), 'a1', '1', 1, 1, null, to_date('26-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (16, '测试用户1', 1, to_date('03-02-1994', 'dd-mm-yyyy'), 'abc', '123', 2, 1, null, to_date('26-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (18, '测试用户2', 1, to_date('03-02-1994', 'dd-mm-yyyy'), 'abc', '123', 2, 1, null, to_date('26-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (19, '测试用户1', 1, to_date('28-12-2016', 'dd-mm-yyyy'), 'a', 'a', 1, 1, null, to_date('26-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (20, '测试用户2', 1, to_date('28-12-2016', 'dd-mm-yyyy'), 'a', '1', 1, 1, null, to_date('26-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (21, '测试用户3', 1, to_date('28-12-2016', 'dd-mm-yyyy'), 'a', '1', 1, 1, null, to_date('26-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (22, '测试用户4', 1, to_date('28-12-2016', 'dd-mm-yyyy'), 'a', '1', 1, 1, null, to_date('26-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (23, '测试用户5', 1, to_date('28-12-2016', 'dd-mm-yyyy'), 'a', '1', 1, 1, null, to_date('26-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (24, '测试用户6', 1, to_date('28-12-2016', 'dd-mm-yyyy'), 'a', '1', 1, 1, null, to_date('26-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (25, '测试用户7', 1, to_date('28-12-2016', 'dd-mm-yyyy'), 'a', '1', 1, 1, null, to_date('26-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (26, '测试用户8', 1, to_date('28-12-2016', 'dd-mm-yyyy'), 'a', '1', 1, 1, null, to_date('26-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (27, '测试用户9', 1, to_date('28-12-2016', 'dd-mm-yyyy'), 'a', '1', 1, 1, null, to_date('26-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (28, '测试用户10', 1, to_date('28-12-2016', 'dd-mm-yyyy'), 'a', '1', 1, 1, null, to_date('26-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (29, '测试用户11', 1, to_date('28-12-2016', 'dd-mm-yyyy'), 'a', '1', 1, 1, null, to_date('26-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (30, '测试用户12', 1, to_date('28-12-2016', 'dd-mm-yyyy'), 'a', '1', 1, 1, null, to_date('26-12-2016', 'dd-mm-yyyy'));
insert into T_HBM_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (1021, '上传用户', 1, to_date('05-01-1996', 'dd-mm-yyyy'), 'upload', '123', 1, 3, '1483926147584.jpg', to_date('09-01-2017', 'dd-mm-yyyy'));
insert into T_HBM_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (114, '1', 1, to_date('12-03-2017', 'dd-mm-yyyy'), '1', 'null', 1, 3, '1488421944827.jpg', to_date('02-03-2017 10:32:24', 'dd-mm-yyyy hh24:mi:ss'));
insert into T_HBM_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (115, '1', 1, to_date('27-02-2017', 'dd-mm-yyyy'), '1', 'null', 1, 3, '1488422296128.jpg', to_date('02-03-2017 10:38:16', 'dd-mm-yyyy hh24:mi:ss'));
insert into T_HBM_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (116, '1', 1, to_date('06-03-2017', 'dd-mm-yyyy'), '1', 'null', 1, 3, '1488427080658.jpg', to_date('02-03-2017 10:44:28', 'dd-mm-yyyy hh24:mi:ss'));
commit;
prompt 32 records loaded
prompt Loading T_LOG...
insert into T_LOG (log, state, create_date)
values ('proc_test04执行', 1, to_date('23-02-2017 16:56:19', 'dd-mm-yyyy hh24:mi:ss'));
insert into T_LOG (log, state, create_date)
values ('proc_test01执行了', 1, to_date('24-02-2017 09:43:40', 'dd-mm-yyyy hh24:mi:ss'));
insert into T_LOG (log, state, create_date)
values ('2-proc_test02执行了-4', 1, to_date('24-02-2017 09:52:33', 'dd-mm-yyyy hh24:mi:ss'));
insert into T_LOG (log, state, create_date)
values ('2-proc_test03执行了-', 1, to_date('24-02-2017 09:58:59', 'dd-mm-yyyy hh24:mi:ss'));
commit;
prompt 4 records loaded
prompt Loading T_MENU...
insert into T_MENU (id, name, url, parent_id, menu_level, create_date)
values (2, '基础数据', 'url', 0, 1, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_MENU (id, name, url, parent_id, menu_level, create_date)
values (3, '字典管理', 'dict_list.do', 1, 2, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_MENU (id, name, url, parent_id, menu_level, create_date)
values (4, '菜单管理', 'menu_list.do', 1, 2, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_MENU (id, name, url, parent_id, menu_level, create_date)
values (5, '角色管理', 'role_list.do', 1, 2, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_MENU (id, name, url, parent_id, menu_level, create_date)
values (6, '用户管理', 'user_list.do', 1, 2, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_MENU (id, name, url, parent_id, menu_level, create_date)
values (7, '教学管理', 'url', 0, 1, to_date('06-01-2017', 'dd-mm-yyyy'));
insert into T_MENU (id, name, url, parent_id, menu_level, create_date)
values (8, '班级管理', 'tclass_list.do', 2, 2, to_date('06-01-2017', 'dd-mm-yyyy'));
insert into T_MENU (id, name, url, parent_id, menu_level, create_date)
values (9, '学生管理', 'student_list.do', 2, 2, to_date('06-01-2017', 'dd-mm-yyyy'));
insert into T_MENU (id, name, url, parent_id, menu_level, create_date)
values (10, '课程管理', 'course_list.do', 2, 2, to_date('06-01-2017', 'dd-mm-yyyy'));
insert into T_MENU (id, name, url, parent_id, menu_level, create_date)
values (11, '开课管理', 'courseOn_list.do', 7, 2, to_date('06-01-2017', 'dd-mm-yyyy'));
insert into T_MENU (id, name, url, parent_id, menu_level, create_date)
values (12, '选课管理', 'stuCourse_list.do', 7, 2, to_date('06-01-2017', 'dd-mm-yyyy'));
insert into T_MENU (id, name, url, parent_id, menu_level, create_date)
values (13, '成绩管理', 'score_list.do', 7, 2, to_date('06-01-2017', 'dd-mm-yyyy'));
insert into T_MENU (id, name, url, parent_id, menu_level, create_date)
values (1, '系统管理', 'url', 0, 1, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_MENU (id, name, url, parent_id, menu_level, create_date)
values (14, '老师管理', 'teach_list.do', 2, 2, to_date('06-01-2017', 'dd-mm-yyyy'));
commit;
prompt 14 records loaded
prompt Loading T_ROLE...
insert into T_ROLE (id, name, create_date)
values (1, '管理员', to_date('08-12-2016', 'dd-mm-yyyy'));
insert into T_ROLE (id, name, create_date)
values (2, '教师角色', to_date('08-12-2016', 'dd-mm-yyyy'));
insert into T_ROLE (id, name, create_date)
values (3, '学生角色', to_date('07-01-2017', 'dd-mm-yyyy'));
insert into T_ROLE (id, name, create_date)
values (100, '测试角色1', to_date('16-03-2017 10:41:25', 'dd-mm-yyyy hh24:mi:ss'));
commit;
prompt 4 records loaded
prompt Loading T_ROLE2MENU...
insert into T_ROLE2MENU (role_id, menu_id)
values (1, 1);
insert into T_ROLE2MENU (role_id, menu_id)
values (1, 3);
insert into T_ROLE2MENU (role_id, menu_id)
values (1, 4);
insert into T_ROLE2MENU (role_id, menu_id)
values (1, 5);
insert into T_ROLE2MENU (role_id, menu_id)
values (1, 6);
insert into T_ROLE2MENU (role_id, menu_id)
values (2, 1);
insert into T_ROLE2MENU (role_id, menu_id)
values (2, 8);
insert into T_ROLE2MENU (role_id, menu_id)
values (2, 4);
insert into T_ROLE2MENU (role_id, menu_id)
values (1, 7);
insert into T_ROLE2MENU (role_id, menu_id)
values (1, 11);
insert into T_ROLE2MENU (role_id, menu_id)
values (1, 12);
insert into T_ROLE2MENU (role_id, menu_id)
values (1, 13);
insert into T_ROLE2MENU (role_id, menu_id)
values (1, 2);
insert into T_ROLE2MENU (role_id, menu_id)
values (1, 8);
insert into T_ROLE2MENU (role_id, menu_id)
values (1, 9);
insert into T_ROLE2MENU (role_id, menu_id)
values (1, 10);
insert into T_ROLE2MENU (role_id, menu_id)
values (2, 7);
insert into T_ROLE2MENU (role_id, menu_id)
values (2, 11);
insert into T_ROLE2MENU (role_id, menu_id)
values (2, 12);
insert into T_ROLE2MENU (role_id, menu_id)
values (2, 13);
insert into T_ROLE2MENU (role_id, menu_id)
values (2, 2);
insert into T_ROLE2MENU (role_id, menu_id)
values (2, 9);
insert into T_ROLE2MENU (role_id, menu_id)
values (2, 10);
insert into T_ROLE2MENU (role_id, menu_id)
values (100, 2);
insert into T_ROLE2MENU (role_id, menu_id)
values (100, 8);
insert into T_ROLE2MENU (role_id, menu_id)
values (100, 10);
insert into T_ROLE2MENU (role_id, menu_id)
values (100, 1);
insert into T_ROLE2MENU (role_id, menu_id)
values (100, 3);
insert into T_ROLE2MENU (role_id, menu_id)
values (100, 4);
insert into T_ROLE2MENU (role_id, menu_id)
values (100, 5);
insert into T_ROLE2MENU (role_id, menu_id)
values (100, 6);
insert into T_ROLE2MENU (role_id, menu_id)
values (1, 14);
commit;
prompt 32 records loaded
prompt Loading T_SCORE...
insert into T_SCORE (id, student_id, study_year, maths, english, create_date)
values (100, 5, '2014-A', 23, 34, to_date('14-04-2016 17:54:12', 'dd-mm-yyyy hh24:mi:ss'));
insert into T_SCORE (id, student_id, study_year, maths, english, create_date)
values (30, 7, '2014-B', 105, 43, to_date('12-04-2015 22:34:03', 'dd-mm-yyyy hh24:mi:ss'));
insert into T_SCORE (id, student_id, study_year, maths, english, create_date)
values (281, 13, '2016-A', 87, 89, to_date('30-10-2016 21:13:26', 'dd-mm-yyyy hh24:mi:ss'));
insert into T_SCORE (id, student_id, study_year, maths, english, create_date)
values (5, 3, '2016-A', 96, 86, to_date('02-02-2015', 'dd-mm-yyyy'));
insert into T_SCORE (id, student_id, study_year, maths, english, create_date)
values (6, 3, '2014-A', 118, 88, to_date('02-02-2015', 'dd-mm-yyyy'));
insert into T_SCORE (id, student_id, study_year, maths, english, create_date)
values (7, 4, '2014-A', 120, 96, to_date('02-02-2015', 'dd-mm-yyyy'));
insert into T_SCORE (id, student_id, study_year, maths, english, create_date)
values (8, 4, '2014-B', 126, 66, to_date('02-02-2015', 'dd-mm-yyyy'));
insert into T_SCORE (id, student_id, study_year, maths, english, create_date)
values (9, 5, '2014-A', 119, 61, to_date('02-02-2015', 'dd-mm-yyyy'));
insert into T_SCORE (id, student_id, study_year, maths, english, create_date)
values (10, 5, '2014-B', 122, 86, to_date('02-02-2015', 'dd-mm-yyyy'));
insert into T_SCORE (id, student_id, study_year, maths, english, create_date)
values (11, 6, '2014-B', 120, 76, to_date('02-02-2015', 'dd-mm-yyyy'));
insert into T_SCORE (id, student_id, study_year, maths, english, create_date)
values (12, 6, '2014-A', 122, 96, to_date('02-02-2015', 'dd-mm-yyyy'));
insert into T_SCORE (id, student_id, study_year, maths, english, create_date)
values (20, 7, '2015-A', 137, 98, to_date('31-03-2015 18:03:13', 'dd-mm-yyyy hh24:mi:ss'));
insert into T_SCORE (id, student_id, study_year, maths, english, create_date)
values (15, 8, '2014-B', 122, 86, to_date('02-02-2015', 'dd-mm-yyyy'));
insert into T_SCORE (id, student_id, study_year, maths, english, create_date)
values (16, 8, '2014-A', 122, 46, to_date('02-02-2015', 'dd-mm-yyyy'));
insert into T_SCORE (id, student_id, study_year, maths, english, create_date)
values (101, 3, '2014-B', 137, 84, to_date('08-10-2015 12:01:35', 'dd-mm-yyyy hh24:mi:ss'));
insert into T_SCORE (id, student_id, study_year, maths, english, create_date)
values (130, 3, '2015-A', 115, 10, to_date('14-10-2015 15:22:23', 'dd-mm-yyyy hh24:mi:ss'));
insert into T_SCORE (id, student_id, study_year, maths, english, create_date)
values (131, 3, '2015-B', 104, 10, to_date('14-10-2015 15:23:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into T_SCORE (id, student_id, study_year, maths, english, create_date)
values (190, 5, '2015-A', 105.01, 53.01, to_date('30-10-2015 00:19:28', 'dd-mm-yyyy hh24:mi:ss'));
insert into T_SCORE (id, student_id, study_year, maths, english, create_date)
values (191, 6, '2016-A', 122, 97, to_date('30-10-2015 00:19:51', 'dd-mm-yyyy hh24:mi:ss'));
insert into T_SCORE (id, student_id, study_year, maths, english, create_date)
values (193, 6, '2015-B', 119, 56, to_date('30-10-2015 00:21:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into T_SCORE (id, student_id, study_year, maths, english, create_date)
values (136, 16, '2015-A', 124, 91, to_date('14-10-2015 17:02:31', 'dd-mm-yyyy hh24:mi:ss'));
insert into T_SCORE (id, student_id, study_year, maths, english, create_date)
values (220, 16, '2015-A', 105.01, 66, to_date('30-10-2015 09:40:34', 'dd-mm-yyyy hh24:mi:ss'));
insert into T_SCORE (id, student_id, study_year, maths, english, create_date)
values (221, 2, '2016-B', 78.9, 88.9, to_date('12-10-2016 09:53:45', 'dd-mm-yyyy hh24:mi:ss'));
insert into T_SCORE (id, student_id, study_year, maths, english, create_date)
values (251, 7, '2014-A', 45.92, 56.02, to_date('25-10-2016 10:43:42', 'dd-mm-yyyy hh24:mi:ss'));
commit;
prompt 24 records loaded
prompt Loading T_STUDENT...
insert into T_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (1, '谢美玲', 2, to_date('12-12-2016', 'dd-mm-yyyy'), '广西百色', 3, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (2, '谢芳', 2, to_date('12-05-1993', 'dd-mm-yyyy'), '广西百色', 3, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (3, '何小玲', 2, to_date('12-12-1994', 'dd-mm-yyyy'), '广西百色', 1, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (4, '何金健', 1, to_date('14-02-1984', 'dd-mm-yyyy'), '广西百色', 1, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (5, '何康', 1, to_date('08-12-1990', 'dd-mm-yyyy'), '广西桂林', 2, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (6, '卢欢', 2, to_date('12-03-1992', 'dd-mm-yyyy'), '广西百色', 1, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (7, '韦小芳', 2, to_date('12-05-1993', 'dd-mm-yyyy'), '广西百色', 3, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (8, '王小利', 2, to_date('10-02-1995', 'dd-mm-yyyy'), '广西钦州', 4, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (9, '谢婷', 2, to_date('02-12-1995', 'dd-mm-yyyy'), '广西南宁', 5, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (10, '何玲', 2, to_date('12-12-1994', 'dd-mm-yyyy'), '广西百色', 1, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (11, '王鑫', 1, to_date('14-02-1994', 'dd-mm-yyyy'), '广西百色', 5, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (12, '何霖', 1, to_date('08-12-1990', 'dd-mm-yyyy'), '广西桂林', 2, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (13, '卢铮', 1, to_date('14-03-1992', 'dd-mm-yyyy'), '广西百色', 1, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (14, '王芳', 2, to_date('12-05-1993', 'dd-mm-yyyy'), '广西百色', 3, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (15, '周比利', 1, to_date('12-02-1995', 'dd-mm-yyyy'), '广西钦州', 4, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (16, '谢胜', 1, to_date('04-12-1995', 'dd-mm-yyyy'), '广西南宁', 5, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (17, '周小玲', 2, to_date('12-12-1994', 'dd-mm-yyyy'), '广西百色', 1, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (18, '周金健', 1, to_date('14-02-1994', 'dd-mm-yyyy'), '广西百色', 1, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (19, '黄康', 1, to_date('08-12-1990', 'dd-mm-yyyy'), '广西桂林', 2, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (20, '李欢', 2, to_date('12-03-1992', 'dd-mm-yyyy'), '广西百色', 1, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (21, '杨小芳', 2, to_date('12-05-1993', 'dd-mm-yyyy'), '广西百色', 3, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (22, '杨小利', 2, to_date('10-02-1995', 'dd-mm-yyyy'), '广西钦州', 4, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (23, '李婷', 2, to_date('02-12-1995', 'dd-mm-yyyy'), '广西南宁', 5, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (24, '王玲', 2, to_date('12-12-1994', 'dd-mm-yyyy'), '广西百色', 1, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (25, '周鑫', 1, to_date('14-02-1994', 'dd-mm-yyyy'), '广西百色', 5, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (26, '卢霖', 1, to_date('08-12-1990', 'dd-mm-yyyy'), '广西桂林', 2, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (27, '王铮', 1, to_date('14-03-1992', 'dd-mm-yyyy'), '广西百色', 1, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (28, '谢芳', 2, to_date('12-05-1993', 'dd-mm-yyyy'), '广西百色', 3, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (29, '王大利', 1, to_date('12-02-1995', 'dd-mm-yyyy'), '广西钦州', 4, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (30, '谢庐', 1, to_date('04-12-1995', 'dd-mm-yyyy'), '广西南宁', 5, to_date('23-12-2016', 'dd-mm-yyyy'));
insert into T_STUDENT (id, name, sex, birthday, snative, class_id, create_date)
values (31, '测试用户1', 2, to_date('23-01-1994', 'dd-mm-yyyy'), '广西百色', 5, to_date('03-01-2017', 'dd-mm-yyyy'));
commit;
prompt 31 records loaded
prompt Loading T_STU_COURSE...
insert into T_STU_COURSE (id, student_id, course_id, create_date, score, remark)
values (1, 2, 2, to_date('17-03-2017', 'dd-mm-yyyy'), 89, null);
insert into T_STU_COURSE (id, student_id, course_id, create_date, score, remark)
values (2, 3, 2, to_date('08-03-2017', 'dd-mm-yyyy'), 78, null);
insert into T_STU_COURSE (id, student_id, course_id, create_date, score, remark)
values (3, 4, 3, to_date('01-03-2017', 'dd-mm-yyyy'), 67, null);
commit;
prompt 3 records loaded
prompt Loading T_TEACHER...
insert into T_TEACHER (id, name, sex, birthday, work_date, specialty, tlevel, create_date)
values (1, '李强', 1, to_date('15-03-1987', 'dd-mm-yyyy'), to_date('01-03-2007', 'dd-mm-yyyy'), '软件工程', 2, to_date('01-03-2017', 'dd-mm-yyyy'));
insert into T_TEACHER (id, name, sex, birthday, work_date, specialty, tlevel, create_date)
values (2, '张国力', 1, to_date('15-03-1977', 'dd-mm-yyyy'), to_date('01-03-2000', 'dd-mm-yyyy'), '计算机与科学', 3, to_date('01-03-2017', 'dd-mm-yyyy'));
insert into T_TEACHER (id, name, sex, birthday, work_date, specialty, tlevel, create_date)
values (100, '李世强', 1, to_date('16-03-1975', 'dd-mm-yyyy'), to_date('16-03-2003', 'dd-mm-yyyy'), '通信工程', 1, to_date('16-03-2017 14:36:21', 'dd-mm-yyyy hh24:mi:ss'));
insert into T_TEACHER (id, name, sex, birthday, work_date, specialty, tlevel, create_date)
values (101, '张琳', 1, to_date('16-03-1983', 'dd-mm-yyyy'), to_date('16-03-2010', 'dd-mm-yyyy'), '计算机信息工程', 2, to_date('16-03-2017 14:37:15', 'dd-mm-yyyy hh24:mi:ss'));
insert into T_TEACHER (id, name, sex, birthday, work_date, specialty, tlevel, create_date)
values (102, '李小凤', 2, to_date('16-03-1989', 'dd-mm-yyyy'), to_date('16-03-2010', 'dd-mm-yyyy'), '幼儿教育', 3, to_date('16-03-2017 14:38:03', 'dd-mm-yyyy hh24:mi:ss'));
commit;
prompt 5 records loaded
prompt Loading T_USER...
insert into T_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (111, '测试用户', 1, to_date('01-01-1995', 'dd-mm-yyyy'), 'admin', '123', 1, 0, null, to_date('28-02-2017 09:40:45', 'dd-mm-yyyy hh24:mi:ss'));
insert into T_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (112, '2', 1, to_date('13-03-2017', 'dd-mm-yyyy'), '2', 'null', 1, 3, '1488359144095.jpg', to_date('01-03-2017 17:05:44', 'dd-mm-yyyy hh24:mi:ss'));
insert into T_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (113, '3', 1, to_date('06-03-2017', 'dd-mm-yyyy'), '3', 'null', 1, 3, '1488421721528.jpg', to_date('02-03-2017 10:28:41', 'dd-mm-yyyy hh24:mi:ss'));
insert into T_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (2, '卢辉', 1, to_date('12-09-1990', 'dd-mm-yyyy'), 'lh', '1', 2, 1, null, to_date('21-12-2016', 'dd-mm-yyyy'));
insert into T_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (3, '杨阳', 1, to_date('05-12-1995', 'dd-mm-yyyy'), 'yy', '123', 2, 1, null, to_date('21-12-2016', 'dd-mm-yyyy'));
insert into T_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (4, '王明阳', 2, to_date('23-11-1990', 'dd-mm-yyyy'), 'wmy', '123', 2, 1, null, to_date('21-12-2016', 'dd-mm-yyyy'));
insert into T_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (5, '李小凤', 2, to_date('02-01-1996', 'dd-mm-yyyy'), 'lxf', '123', 1, 1, null, to_date('21-12-2016', 'dd-mm-yyyy'));
insert into T_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (6, '彭娟', 2, to_date('09-01-1995', 'dd-mm-yyyy'), 'pj', '1', 2, 1, null, to_date('22-12-2016', 'dd-mm-yyyy'));
insert into T_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (7, '黄奇进', 2, to_date('10-12-2001', 'dd-mm-yyyy'), 'hqj', '1', 1, 1, null, to_date('06-12-2016', 'dd-mm-yyyy'));
insert into T_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (8, '许晓华', 2, to_date('01-12-2002', 'dd-mm-yyyy'), 'xxh', '2', 1, 1, null, to_date('02-11-2016', 'dd-mm-yyyy'));
insert into T_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (9, '肖建郡', 1, to_date('02-12-2016', 'dd-mm-yyyy'), 'xjj', '12', 1, 1, null, to_date('02-11-2016', 'dd-mm-yyyy'));
insert into T_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (10, '李力', 1, to_date('01-12-2016', 'dd-mm-yyyy'), 'll', '123', 1, 1, null, to_date('06-12-2016', 'dd-mm-yyyy'));
insert into T_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (12, '李向阳', 1, to_date('07-02-1994', 'dd-mm-yyyy'), 'abc', '123', 2, 1, null, to_date('26-12-2016', 'dd-mm-yyyy'));
insert into T_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (15, '测试用户1', 1, to_date('30-12-2016', 'dd-mm-yyyy'), 'a1', '1', 1, 1, null, to_date('26-12-2016', 'dd-mm-yyyy'));
insert into T_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (16, '测试用户1', 1, to_date('03-02-1994', 'dd-mm-yyyy'), 'abc', '123', 2, 1, null, to_date('26-12-2016', 'dd-mm-yyyy'));
insert into T_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (18, '测试用户2', 1, to_date('03-02-1994', 'dd-mm-yyyy'), 'abc', '123', 2, 1, null, to_date('26-12-2016', 'dd-mm-yyyy'));
insert into T_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (19, '测试用户1', 1, to_date('28-12-2016', 'dd-mm-yyyy'), 'a', 'a', 1, 1, null, to_date('26-12-2016', 'dd-mm-yyyy'));
insert into T_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (20, '测试用户2', 1, to_date('28-12-2016', 'dd-mm-yyyy'), 'a', '1', 1, 1, null, to_date('26-12-2016', 'dd-mm-yyyy'));
insert into T_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (21, '测试用户3', 1, to_date('28-12-2016', 'dd-mm-yyyy'), 'a', '1', 1, 1, null, to_date('26-12-2016', 'dd-mm-yyyy'));
insert into T_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (22, '测试用户4', 1, to_date('28-12-2016', 'dd-mm-yyyy'), 'a', '1', 1, 1, null, to_date('26-12-2016', 'dd-mm-yyyy'));
insert into T_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (23, '测试用户5', 1, to_date('28-12-2016', 'dd-mm-yyyy'), 'a', '1', 1, 1, null, to_date('26-12-2016', 'dd-mm-yyyy'));
insert into T_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (24, '测试用户6', 1, to_date('28-12-2016', 'dd-mm-yyyy'), 'a', '1', 1, 1, null, to_date('26-12-2016', 'dd-mm-yyyy'));
insert into T_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (25, '测试用户7', 1, to_date('28-12-2016', 'dd-mm-yyyy'), 'a', '1', 1, 1, null, to_date('26-12-2016', 'dd-mm-yyyy'));
insert into T_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (26, '测试用户8', 1, to_date('28-12-2016', 'dd-mm-yyyy'), 'a', '1', 1, 1, null, to_date('26-12-2016', 'dd-mm-yyyy'));
insert into T_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (27, '测试用户9', 1, to_date('28-12-2016', 'dd-mm-yyyy'), 'a', '1', 1, 1, null, to_date('26-12-2016', 'dd-mm-yyyy'));
insert into T_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (28, '测试用户10', 1, to_date('28-12-2016', 'dd-mm-yyyy'), 'a', '1', 1, 1, null, to_date('26-12-2016', 'dd-mm-yyyy'));
insert into T_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (29, '测试用户11', 1, to_date('28-12-2016', 'dd-mm-yyyy'), 'a', '1', 1, 1, null, to_date('26-12-2016', 'dd-mm-yyyy'));
insert into T_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (30, '测试用户12', 1, to_date('28-12-2016', 'dd-mm-yyyy'), 'a', '1', 1, 1, null, to_date('26-12-2016', 'dd-mm-yyyy'));
insert into T_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (1019, 'a', 1, to_date('14-12-2016', 'dd-mm-yyyy'), 'a', '123', 1, 1, null, to_date('28-12-2016', 'dd-mm-yyyy'));
insert into T_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (1020, 'b', 1, to_date('18-01-2017', 'dd-mm-yyyy'), 'b', '123', 1, 0, null, to_date('07-01-2017', 'dd-mm-yyyy'));
insert into T_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (1021, '上传用户', 1, to_date('05-01-1996', 'dd-mm-yyyy'), 'upload', '123', 1, 3, '1483926147584.jpg', to_date('09-01-2017', 'dd-mm-yyyy'));
insert into T_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (114, '1', 1, to_date('12-03-2017', 'dd-mm-yyyy'), '1', 'null', 1, 3, '1488421944827.jpg', to_date('02-03-2017 10:32:24', 'dd-mm-yyyy hh24:mi:ss'));
insert into T_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (115, '1', 1, to_date('27-02-2017', 'dd-mm-yyyy'), '1', 'null', 1, 3, '1488422296128.jpg', to_date('02-03-2017 10:38:16', 'dd-mm-yyyy hh24:mi:ss'));
insert into T_USER (id, name, sex, birthday, username, password, login_flag, role_id, file_path, create_date)
values (116, '1', 1, to_date('06-03-2017', 'dd-mm-yyyy'), '1', 'null', 1, 3, '1488427080658.jpg', to_date('02-03-2017 10:44:28', 'dd-mm-yyyy hh24:mi:ss'));
commit;
prompt 34 records loaded
prompt Enabling foreign key constraints for T_HBM_STUDENT...
alter table T_HBM_STUDENT enable constraint FK_SUH6QT6JOUUKI3Q6LMTKHNQ0W;
prompt Enabling triggers for T_CLASS...
alter table T_CLASS enable all triggers;
prompt Enabling triggers for T_COURSE...
alter table T_COURSE enable all triggers;
prompt Enabling triggers for T_COURSE_ON...
alter table T_COURSE_ON enable all triggers;
prompt Enabling triggers for T_DICT...
alter table T_DICT enable all triggers;
prompt Enabling triggers for T_HBM_CLASS...
alter table T_HBM_CLASS enable all triggers;
prompt Enabling triggers for T_HBM_COURSE...
alter table T_HBM_COURSE enable all triggers;
prompt Enabling triggers for T_HBM_COURSE_ON...
alter table T_HBM_COURSE_ON enable all triggers;
prompt Enabling triggers for T_HBM_DICT...
alter table T_HBM_DICT enable all triggers;
prompt Enabling triggers for T_HBM_MENU...
alter table T_HBM_MENU enable all triggers;
prompt Enabling triggers for T_HBM_ROLE...
alter table T_HBM_ROLE enable all triggers;
prompt Enabling triggers for T_HBM_ROLE2MENU...
alter table T_HBM_ROLE2MENU enable all triggers;
prompt Enabling triggers for T_HBM_SCORE...
alter table T_HBM_SCORE enable all triggers;
prompt Enabling triggers for T_HBM_STUDENT...
alter table T_HBM_STUDENT enable all triggers;
prompt Enabling triggers for T_HBM_STU_COURSE...
alter table T_HBM_STU_COURSE enable all triggers;
prompt Enabling triggers for T_HBM_TEACHER...
alter table T_HBM_TEACHER enable all triggers;
prompt Enabling triggers for T_HBM_USER...
alter table T_HBM_USER enable all triggers;
prompt Enabling triggers for T_LOG...
alter table T_LOG enable all triggers;
prompt Enabling triggers for T_MENU...
alter table T_MENU enable all triggers;
prompt Enabling triggers for T_ROLE...
alter table T_ROLE enable all triggers;
prompt Enabling triggers for T_ROLE2MENU...
alter table T_ROLE2MENU enable all triggers;
prompt Enabling triggers for T_SCORE...
alter table T_SCORE enable all triggers;
prompt Enabling triggers for T_STUDENT...
alter table T_STUDENT enable all triggers;
prompt Enabling triggers for T_STU_COURSE...
alter table T_STU_COURSE enable all triggers;
prompt Enabling triggers for T_TEACHER...
alter table T_TEACHER enable all triggers;
prompt Enabling triggers for T_USER...
alter table T_USER enable all triggers;
set feedback on
set define on
prompt Done.

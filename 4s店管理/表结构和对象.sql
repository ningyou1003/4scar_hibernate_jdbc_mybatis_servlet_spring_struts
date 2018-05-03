---------------------------------------------
-- Export file for user U20                --
-- Created by zhql on 2017-03-17, 17:32:31 --
---------------------------------------------

set define off
spool 1111111111111111111111.log

prompt
prompt Creating table T_CLASS
prompt ======================
prompt
create table U20.T_CLASS
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
comment on table U20.T_CLASS
  is '班级信息表';
comment on column U20.T_CLASS.id
  is '记录ID';
comment on column U20.T_CLASS.name
  is '班级名称';
comment on column U20.T_CLASS.charger
  is '班主任';
comment on column U20.T_CLASS.create_date
  is '创建日期';
alter table U20.T_CLASS
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

prompt
prompt Creating table T_COURSE
prompt =======================
prompt
create table U20.T_COURSE
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
comment on table U20.T_COURSE
  is '课程信息表';
comment on column U20.T_COURSE.id
  is '记录ID(PK)';
comment on column U20.T_COURSE.name
  is '课程名称';
comment on column U20.T_COURSE.credit
  is '学分';
comment on column U20.T_COURSE.create_date
  is '创建日期';

prompt
prompt Creating table T_COURSE_ON
prompt ==========================
prompt
create table U20.T_COURSE_ON
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
comment on table U20.T_COURSE_ON
  is '开课信息表';
comment on column U20.T_COURSE_ON.id
  is '记录ID(PK)';
comment on column U20.T_COURSE_ON.teacher_id
  is '授课老师ID(FK)';
comment on column U20.T_COURSE_ON.course_id
  is '课程ID(FK)';
comment on column U20.T_COURSE_ON.year
  is '年度(如:2014,2015,2016)';
comment on column U20.T_COURSE_ON.school_term
  is '学期(1-春季学期,2-秋季学期)';
comment on column U20.T_COURSE_ON.state
  is '状态(1-正常,2-无效)';
comment on column U20.T_COURSE_ON.create_date
  is '创建日期';

prompt
prompt Creating table T_DICT
prompt =====================
prompt
create table U20.T_DICT
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
comment on column U20.T_DICT.id
  is '记录ID';
comment on column U20.T_DICT.dict_name
  is '字典名称';
comment on column U20.T_DICT.ckey
  is '字典KEY';
comment on column U20.T_DICT.cvalue
  is '字典VALUE';
comment on column U20.T_DICT.use_flag
  is '使用状态(1-可用,2-不可用)';
comment on column U20.T_DICT.order_no
  is '顺序号';
comment on column U20.T_DICT.create_date
  is '创建日期';

prompt
prompt Creating table T_HBM_CLASS
prompt ==========================
prompt
create table U20.T_HBM_CLASS
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
alter table U20.T_HBM_CLASS
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

prompt
prompt Creating table T_HBM_COURSE
prompt ===========================
prompt
create table U20.T_HBM_COURSE
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

prompt
prompt Creating table T_HBM_COURSE_ON
prompt ==============================
prompt
create table U20.T_HBM_COURSE_ON
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

prompt
prompt Creating table T_HBM_DICT
prompt =========================
prompt
create table U20.T_HBM_DICT
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
alter table U20.T_HBM_DICT
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

prompt
prompt Creating table T_HBM_MENU
prompt =========================
prompt
create table U20.T_HBM_MENU
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

prompt
prompt Creating table T_HBM_ROLE
prompt =========================
prompt
create table U20.T_HBM_ROLE
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

prompt
prompt Creating table T_HBM_ROLE2MENU
prompt ==============================
prompt
create table U20.T_HBM_ROLE2MENU
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
alter table U20.T_HBM_ROLE2MENU
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

prompt
prompt Creating table T_HBM_SCORE
prompt ==========================
prompt
create table U20.T_HBM_SCORE
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

prompt
prompt Creating table T_HBM_STUDENT
prompt ============================
prompt
create table U20.T_HBM_STUDENT
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
alter table U20.T_HBM_STUDENT
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
alter table U20.T_HBM_STUDENT
  add constraint FK_SUH6QT6JOUUKI3Q6LMTKHNQ0W foreign key (CLASS_ID)
  references U20.T_HBM_CLASS (ID);

prompt
prompt Creating table T_HBM_STU_COURSE
prompt ===============================
prompt
create table U20.T_HBM_STU_COURSE
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

prompt
prompt Creating table T_HBM_TEACHER
prompt ============================
prompt
create table U20.T_HBM_TEACHER
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

prompt
prompt Creating table T_HBM_USER
prompt =========================
prompt
create table U20.T_HBM_USER
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

prompt
prompt Creating table T_LOG
prompt ====================
prompt
create table U20.T_LOG
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
comment on table U20.T_LOG
  is '日志表';
comment on column U20.T_LOG.log
  is '日志描述';
comment on column U20.T_LOG.state
  is '状态(0-失败,1-成功)';
comment on column U20.T_LOG.create_date
  is '创建日期';

prompt
prompt Creating table T_MENU
prompt =====================
prompt
create table U20.T_MENU
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
comment on table U20.T_MENU
  is '菜单信息表';
comment on column U20.T_MENU.id
  is '菜单ID(PK)';
comment on column U20.T_MENU.name
  is '菜单名称';
comment on column U20.T_MENU.url
  is '链接URL';
comment on column U20.T_MENU.parent_id
  is '父菜单ID(FK)';
comment on column U20.T_MENU.menu_level
  is '菜单级别(1-一级,2-二级,3-权限)';
comment on column U20.T_MENU.create_date
  is '创建日期';
alter table U20.T_MENU
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

prompt
prompt Creating table T_ROLE
prompt =====================
prompt
create table U20.T_ROLE
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
comment on column U20.T_ROLE.id
  is '记录ID(PK)';
comment on column U20.T_ROLE.name
  is '角色名称';
comment on column U20.T_ROLE.create_date
  is '创建日期';

prompt
prompt Creating table T_ROLE2MENU
prompt ==========================
prompt
create table U20.T_ROLE2MENU
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
comment on column U20.T_ROLE2MENU.role_id
  is '角色ID(FK)';
comment on column U20.T_ROLE2MENU.menu_id
  is '菜单ID(FK)';

prompt
prompt Creating table T_SCORE
prompt ======================
prompt
create table U20.T_SCORE
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
comment on table U20.T_SCORE
  is '学生成绩信息表';
comment on column U20.T_SCORE.id
  is 'ID(PK-主键)';
comment on column U20.T_SCORE.student_id
  is '学生ID(FK-外键)';
comment on column U20.T_SCORE.study_year
  is '学年(yyyy-春季,yyyy-秋季)';
comment on column U20.T_SCORE.maths
  is '数学';
comment on column U20.T_SCORE.english
  is '英语';
comment on column U20.T_SCORE.create_date
  is '创建日期';

prompt
prompt Creating table T_STUDENT
prompt ========================
prompt
create table U20.T_STUDENT
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
comment on table U20.T_STUDENT
  is '学生信息表';
comment on column U20.T_STUDENT.id
  is '记录ID';
comment on column U20.T_STUDENT.name
  is '姓名';
comment on column U20.T_STUDENT.sex
  is '性别(1-男,2-女)';
comment on column U20.T_STUDENT.birthday
  is '出生日期';
comment on column U20.T_STUDENT.snative
  is '籍貫';
comment on column U20.T_STUDENT.class_id
  is '班級ID(FK)';
comment on column U20.T_STUDENT.create_date
  is '創建日期';
create index U20.IDX_STUDENT1 on U20.T_STUDENT (BIRTHDAY)
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
create index U20.IDX_STUDENT2 on U20.T_STUDENT (TRUNC(BIRTHDAY))
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
alter table U20.T_STUDENT
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

prompt
prompt Creating table T_STU_COURSE
prompt ===========================
prompt
create table U20.T_STU_COURSE
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
comment on table U20.T_STU_COURSE
  is '学生选课信息表';
comment on column U20.T_STU_COURSE.id
  is '记录ID(PK)';
comment on column U20.T_STU_COURSE.student_id
  is '学生ID(FK)';
comment on column U20.T_STU_COURSE.course_id
  is '课程ID(FK)';
comment on column U20.T_STU_COURSE.create_date
  is '创建日期';
comment on column U20.T_STU_COURSE.score
  is '成绩';
comment on column U20.T_STU_COURSE.remark
  is '备注';

prompt
prompt Creating table T_TEACHER
prompt ========================
prompt
create table U20.T_TEACHER
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
comment on column U20.T_TEACHER.id
  is '记录ID';
comment on column U20.T_TEACHER.name
  is '姓名';
comment on column U20.T_TEACHER.sex
  is '性别(1-男,2-女)';
comment on column U20.T_TEACHER.birthday
  is '出生日期';
comment on column U20.T_TEACHER.work_date
  is '开始工作日期';
comment on column U20.T_TEACHER.specialty
  is '专业方向';
comment on column U20.T_TEACHER.tlevel
  is '职称等级(1-初级,2-中级,3-高级)';
comment on column U20.T_TEACHER.create_date
  is '创建日期';

prompt
prompt Creating table T_USER
prompt =====================
prompt
create table U20.T_USER
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
comment on table U20.T_USER
  is '用户信息表';
comment on column U20.T_USER.id
  is '记录ID';
comment on column U20.T_USER.name
  is '姓名';
comment on column U20.T_USER.sex
  is '性别(1-男,2-女)';
comment on column U20.T_USER.birthday
  is '出生日期';
comment on column U20.T_USER.username
  is '用户名';
comment on column U20.T_USER.password
  is '密码';
comment on column U20.T_USER.login_flag
  is '登录状态(1-正常,2-无效)';
comment on column U20.T_USER.role_id
  is '角色ID(FK)';
comment on column U20.T_USER.file_path
  is '文件路径';
comment on column U20.T_USER.create_date
  is '创建日期';
create index U20.IDX_USER1 on U20.T_USER (USERNAME, SEX, LOGIN_FLAG)
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
alter table U20.T_USER
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

prompt
prompt Creating sequence HIBERNATE_SEQUENCE
prompt ====================================
prompt
create sequence U20.HIBERNATE_SEQUENCE
minvalue 1
maxvalue 9999999999999999999999999999
start with 21
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_T_CLASS
prompt =============================
prompt
create sequence U20.SEQ_T_CLASS
minvalue 100
maxvalue 10000000
start with 110
increment by 1
cache 10
cycle
order;

prompt
prompt Creating sequence SEQ_T_COURSE
prompt ==============================
prompt
create sequence U20.SEQ_T_COURSE
minvalue 100
maxvalue 10000000
start with 120
increment by 1
cache 10
cycle
order;

prompt
prompt Creating sequence SEQ_T_COURSE_ON
prompt =================================
prompt
create sequence U20.SEQ_T_COURSE_ON
minvalue 100
maxvalue 10000000
start with 110
increment by 1
cache 10
cycle
order;

prompt
prompt Creating sequence SEQ_T_DICT
prompt ============================
prompt
create sequence U20.SEQ_T_DICT
minvalue 100
maxvalue 10000000
start with 140
increment by 1
cache 10
cycle
order;

prompt
prompt Creating sequence SEQ_T_MENU
prompt ============================
prompt
create sequence U20.SEQ_T_MENU
minvalue 100
maxvalue 10000000
start with 110
increment by 1
cache 10
cycle
order;

prompt
prompt Creating sequence SEQ_T_ROLE
prompt ============================
prompt
create sequence U20.SEQ_T_ROLE
minvalue 100
maxvalue 10000000
start with 110
increment by 1
cache 10
cycle
order;

prompt
prompt Creating sequence SEQ_T_STUDENT
prompt ===============================
prompt
create sequence U20.SEQ_T_STUDENT
minvalue 100
maxvalue 10000000
start with 100
increment by 1
cache 10
cycle
order;

prompt
prompt Creating sequence SEQ_T_TEACHER
prompt ===============================
prompt
create sequence U20.SEQ_T_TEACHER
minvalue 100
maxvalue 10000000
start with 110
increment by 1
cache 10
cycle
order;

prompt
prompt Creating sequence SEQ_T_USER
prompt ============================
prompt
create sequence U20.SEQ_T_USER
minvalue 100
maxvalue 10000000
start with 120
increment by 1
cache 10
cycle
order;

prompt
prompt Creating synonym STU
prompt ====================
prompt
create or replace synonym U20.STU
  for U20.T_STUDENT;

prompt
prompt Creating view V_CLASS_SCORE
prompt ===========================
prompt
create or replace force view u20.v_class_score as
select a.name,c.study_year,avg(c.maths) maths，avg(c.english) english
 from t_class a,t_student b,t_score c where a.id = b.class_id and b.id = c.student_id
 group by a.name,c.study_year;

prompt
prompt Creating view V_CLASS_STU
prompt =========================
prompt
create or replace force view u20.v_class_stu as
select a.name,decode(b.sex,1,'男',2,'女') sex ,count(*) total
from t_class a, t_student b where a.id = b.class_id group by a.name,b.sex;

prompt
prompt Creating view V_STU
prompt ===================
prompt
create or replace force view u20.v_stu as
select t.name,decode(t.sex,1,'男',2,'女') sex ,trunc((sysdate-t.birthday)/365) age from t_student t;

prompt
prompt Creating package PKG_TEST01
prompt ===========================
prompt
create or replace package u20.pkg_test01 is

  -- Author  : ZHQL
  -- Created : 2017-02-23 17:31:48
  -- Purpose : 我的第一个程序包  
  -- 公共类型声明
  type ref_cursor_type is ref cursor;  
  -- Public constant declarations
  --<ConstantName> constant <Datatype> := <Value>;
  -- Public variable declarations
  --<VariableName> <Datatype>;
  -- 函数声明
  function func_sex(p_sex number) return varchar2;
  -- 根据班级ID打印学生信息
  procedure proc_test1(p_class_id number);
  -- 根据班级ID返回学生信息列表
  procedure proc_test2(p_class_id in number,p_cursor out ref_cursor_type);
end pkg_test01;
/

prompt
prompt Creating function FUNC_TEST01
prompt =============================
prompt
create or replace function u20.func_test01(p_date date) return number is
 /* 根据出生日期计算年龄*/
 v_age number(5);
begin
  v_age := trunc((sysdate - p_date)/365);
  -- 返回值只能是一个
  return v_age;
end func_test01;
/

prompt
prompt Creating procedure PROC_TEST01
prompt ==============================
prompt
create or replace procedure u20.proc_test01 is
  -- 声明ref 游标类型,不指定返回类型
  type ref_cursor_v is ref cursor;
  -- 声明游标变量
  cur_v ref_cursor_v;
  v_row t_student%rowtype;
begin
  --打开游标并指向结果
  open cur_v for 'select * from t_student where sex = :1' using 1;
  loop
    fetch cur_v into v_row;
    exit when cur_v%notfound;
    dbms_output.put_line('姓名:' || v_row.name 
                      || ' 出生日期:' || to_char(v_row.birthday, 'yyyy-mm-dd'));
  end loop;
  close cur_v;
  -- 记录日志
  insert into t_log
    (log, state, create_date)
  values
    ('proc_test01执行了', 1, sysdate);
  commit;
exception
  when others then
    -- 捕获所有异常
    dbms_output.put_line('程序出异常:' || sqlerrm);
end proc_test01;
/

prompt
prompt Creating procedure PROC_TEST02
prompt ==============================
prompt
create or replace procedure u20.proc_test02(p_id in integer) is
  /*根据班级ID查询学生人数*/
  v_total integer :=0;
begin
  for v in (select * from t_student where class_id= p_id) loop
    dbms_output.put_line('姓名:' || v.name);
    v_total := v_total + 1;
  end loop;
  -- 记录日志
  insert into t_log
    (log, state, create_date)
  values
    (p_id || '-proc_test02执行了-'||v_total, 1, sysdate);
  commit;
exception
  when others then
    -- 捕获所有异常
    dbms_output.put_line('程序出异常:' || sqlerrm);
end proc_test02;
/

prompt
prompt Creating procedure PROC_TEST03
prompt ==============================
prompt
create or replace procedure u20.proc_test03(p_id in number,p_count out number) is
  /*根据班级ID查询返回学生人数*/
begin
  select count(*) into p_count from t_student where class_id= p_id;
  dbms_output.put_line('执行proc_test03');
  -- 记录日志
  insert into t_log
    (log, state, create_date)
  values
    (p_id || '-proc_test03执行了-', 1, sysdate);
  commit;
exception
  when others then
    -- 捕获所有异常
    dbms_output.put_line('程序出异常:' || sqlerrm);
end proc_test03;
/

prompt
prompt Creating procedure PROC_TEST04
prompt ==============================
prompt
create or replace procedure u20.proc_test04(p_num in out number) is
  /*根据班级ID查询返回学生人数*/
begin
  select count(*) into p_num from t_student where class_id = p_num;
  dbms_output.put_line('执行proc_test04');
  insert into t_log
    (log, state, create_date)
  values
    ('proc_test04执行', 1, sysdate);
  commit;
exception
  when others then
    -- 捕获所有异常
    dbms_output.put_line('程序出异常:' || sqlerrm);
    insert into t_log
      (log, state, create_date)
    values
      ('proc_test04执行异常' , 0, sysdate);
    commit;
end proc_test04;
/

prompt
prompt Creating package body PKG_TEST01
prompt ================================
prompt
create or replace package body u20.pkg_test01 is

    -- 函数声明
  function func_sex(p_sex number) return varchar2 is
    v_sex varchar2(12);
  begin
    select decode(p_sex,1,'男',2,'女') into v_sex from dual;
    return v_sex;
  end func_sex;
  -- 根据班级ID打印学生信息
  procedure proc_test1(p_class_id number) is
  begin
    for v in (select * from t_student t where t.class_id =p_class_id ) loop
      dbms_output.put_line('姓名:' || v.name);
    end loop;
  end proc_test1;
  -- 根据班级ID返回学生信息列表
  procedure proc_test2(p_class_id in number,p_cursor out ref_cursor_type)is
  begin
    -- 打开游标变量指向结果
    open p_cursor for select * from t_student t where t.class_id =p_class_id;
  end proc_test2;
end pkg_test01;
/


spool off

prompt PL/SQL Developer import file
prompt Created on 2017年3月20日 by Administrator
set feedback off
set define off
prompt Creating T_4S_CAR...
create table T_4S_CAR
(
  id          NUMBER(10) not null,
  brand       VARCHAR2(32) not null,
  series      VARCHAR2(32) not null,
  type        NUMBER(2) not null,
  volume      VARCHAR2(12) not null,
  color       VARCHAR2(12) not null,
  pro_place   VARCHAR2(12) not null,
  price       NUMBER(10,2) not null,
  create_date DATE,
  del_flag    NUMBER(2) not null,
  remark      VARCHAR2(64),
  pic_path    VARCHAR2(64) not null
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
comment on table T_4S_CAR
  is '汽车息表';
comment on column T_4S_CAR.id
  is 'ID(PK)                         ';
comment on column T_4S_CAR.brand
  is '品牌                           ';
comment on column T_4S_CAR.series
  is '车系                           ';
comment on column T_4S_CAR.type
  is '类型(1-SUV,2-紧凑车型,3-中级车)';
comment on column T_4S_CAR.volume
  is '排量(1.6L,1.8L,2.0L,2.4L)      ';
comment on column T_4S_CAR.color
  is '颜色                           ';
comment on column T_4S_CAR.pro_place
  is '产地                           ';
comment on column T_4S_CAR.price
  is '指导价(万)                     ';
comment on column T_4S_CAR.create_date
  is '上市日期                       ';
comment on column T_4S_CAR.del_flag
  is '删除标志(0-未删,1-已删)        ';
comment on column T_4S_CAR.remark
  is '备注                           ';
comment on column T_4S_CAR.pic_path
  is '图片路径                       ';
alter table T_4S_CAR
  add constraint PK_T_4S_CAR primary key (ID)
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

prompt Creating T_4S_CAR_INORDER...
create table T_4S_CAR_INORDER
(
  id          NUMBER(10) not null,
  car_id      NUMBER(10) not null,
  supplier_id NUMBER(10) not null,
  in_price    NUMBER(10,2) not null,
  count       NUMBER(5) not null,
  total       NUMBER(10,2),
  create_date DATE,
  in_date     DATE,
  in_state    NUMBER(2) not null,
  remark      VARCHAR2(64) not null,
  del_flag    NUMBER(2) not null
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
comment on table T_4S_CAR_INORDER
  is '整车进货单信息表';
comment on column T_4S_CAR_INORDER.id
  is 'ID(PK)                 ';
comment on column T_4S_CAR_INORDER.car_id
  is '整车ID(FK)             ';
comment on column T_4S_CAR_INORDER.supplier_id
  is '供应商ID(FK)           ';
comment on column T_4S_CAR_INORDER.in_price
  is '进货价                 ';
comment on column T_4S_CAR_INORDER.count
  is '数量                   ';
comment on column T_4S_CAR_INORDER.total
  is '总价(单价*数量)        ';
comment on column T_4S_CAR_INORDER.create_date
  is '填单日期(年月日时分)   ';
comment on column T_4S_CAR_INORDER.in_date
  is '入库日期(年月日时分)   ';
comment on column T_4S_CAR_INORDER.in_state
  is '入库状态(字典)         ';
comment on column T_4S_CAR_INORDER.remark
  is '备注                   ';
comment on column T_4S_CAR_INORDER.del_flag
  is '删除标志(1-未删,0-已删)';
alter table T_4S_CAR_INORDER
  add constraint PK_T_4S_CAR_INORDER primary key (ID)
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

prompt Creating T_4S_CAR_SELLORDER...
create table T_4S_CAR_SELLORDER
(
  id          NUMBER(10) not null,
  car_id      NUMBER(10) not null,
  customer_id NUMBER(10) not null,
  salesman    VARCHAR2(32) not null,
  sell_price  NUMBER(10,2) not null,
  count       NUMBER(5) not null,
  total       NUMBER(10,2),
  sell_date   DATE,
  out_date    DATE,
  out_state   NUMBER(2) not null,
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
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table T_4S_CAR_SELLORDER
  is '整车销售单信息表';
alter table T_4S_CAR_SELLORDER
  add constraint PK_T_4S_CAR_SELLORDER primary key (ID)
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

prompt Creating T_4S_CAR_STOCK...
create table T_4S_CAR_STOCK
(
  id     NUMBER(10) not null,
  car_id NUMBER(10) not null,
  count  NUMBER(5) not null,
  remark VARCHAR2(64)
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
comment on table T_4S_CAR_STOCK
  is '整车库存信息表 ';
comment on column T_4S_CAR_STOCK.id
  is 'ID(PK)    ';
comment on column T_4S_CAR_STOCK.car_id
  is '整车ID(FK)  ';
comment on column T_4S_CAR_STOCK.count
  is '数量    ';
comment on column T_4S_CAR_STOCK.remark
  is '备注    ';
alter table T_4S_CAR_STOCK
  add constraint PK_T_4S_CAR_STOCK primary key (ID)
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

prompt Creating T_4S_CUSTOMER...
create table T_4S_CUSTOMER
(
  id          NUMBER(10) not null,
  name        VARCHAR2(32) not null,
  sex         NUMBER(2) not null,
  vocation    VARCHAR2(20),
  workunit    VARCHAR2(32),
  id_no       VARCHAR2(20),
  contact_tel VARCHAR2(20),
  address     VARCHAR2(64),
  create_date DATE,
  del_flag    NUMBER(2) not null,
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
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table T_4S_CUSTOMER
  is '客户信息表';
comment on column T_4S_CUSTOMER.id
  is 'ID(PK)                 ';
comment on column T_4S_CUSTOMER.name
  is '姓名                   ';
comment on column T_4S_CUSTOMER.sex
  is '性别(字典)             ';
comment on column T_4S_CUSTOMER.vocation
  is '职业                   ';
comment on column T_4S_CUSTOMER.workunit
  is '工作单位               ';
comment on column T_4S_CUSTOMER.id_no
  is '身份证号码             ';
comment on column T_4S_CUSTOMER.contact_tel
  is '联系电话               ';
comment on column T_4S_CUSTOMER.address
  is '联系地址               ';
comment on column T_4S_CUSTOMER.create_date
  is '创建日期               ';
comment on column T_4S_CUSTOMER.del_flag
  is '删除标志(1-未删,0-已删)';
comment on column T_4S_CUSTOMER.remark
  is '备注                   ';
alter table T_4S_CUSTOMER
  add constraint PK_T_4S_CUSTOMER primary key (ID)
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

prompt Creating T_4S_DEPT...
create table T_4S_DEPT
(
  id          NUMBER(10) not null,
  name        VARCHAR2(32) not null,
  charger     VARCHAR2(32) not null,
  contact_tel VARCHAR2(32),
  create_date DATE,
  del_flag    NUMBER(2) not null
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
comment on table T_4S_DEPT
  is '部门信息表';
comment on column T_4S_DEPT.id
  is '部门ID(PK)';
comment on column T_4S_DEPT.name
  is '姓名';
comment on column T_4S_DEPT.charger
  is '负责人';
comment on column T_4S_DEPT.contact_tel
  is '联系电话';
comment on column T_4S_DEPT.create_date
  is '创建日期';
comment on column T_4S_DEPT.del_flag
  is '删除标准(1-未删,0-已删)';
alter table T_4S_DEPT
  add constraint PK_T_4S_DEPT primary key (ID)
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

prompt Creating T_4S_DICT...
create table T_4S_DICT
(
  id          NUMBER(10) not null,
  dict_name   VARCHAR2(32) not null,
  key         VARCHAR2(32) not null,
  value       VARCHAR2(32) not null,
  use_flag    NUMBER(2) not null,
  create_date DATE,
  order_no    NUMBER(2) not null,
  del_flag    NUMBER(2) not null
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
comment on table T_4S_DICT
  is '系统字典表';
comment on column T_4S_DICT.id
  is '记录ID';
comment on column T_4S_DICT.dict_name
  is '字典名称(英文大写，多个单词用下画线分隔)';
comment on column T_4S_DICT.key
  is '字典KEY';
comment on column T_4S_DICT.value
  is '字典VALUE';
comment on column T_4S_DICT.use_flag
  is '状态(1-可用,2-不可用)';
comment on column T_4S_DICT.create_date
  is '创建日期';
comment on column T_4S_DICT.order_no
  is '顺序号';
comment on column T_4S_DICT.del_flag
  is '删除标志(1-未删,0-已删)';
alter table T_4S_DICT
  add constraint PK_T_4S_DICT primary key (ID)
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

prompt Creating T_4S_FEEDBACK...
create table T_4S_FEEDBACK
(
  id          NUMBER(10) not null,
  title       VARCHAR2(32) not null,
  info        VARCHAR2(100) not null,
  customer_id NUMBER(10) not null,
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
comment on table T_4S_FEEDBACK
  is '客户反馈信息表';
comment on column T_4S_FEEDBACK.id
  is 'ID(PK)';
comment on column T_4S_FEEDBACK.title
  is '标题';
comment on column T_4S_FEEDBACK.info
  is '内容';
comment on column T_4S_FEEDBACK.customer_id
  is '客户ID';
comment on column T_4S_FEEDBACK.create_date
  is '创建日期';
alter table T_4S_FEEDBACK
  add constraint PK_T_4S_FEEDBACK primary key (ID)
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

prompt Creating T_4S_FITTINGS...
create table T_4S_FITTINGS
(
  id          NUMBER(10) not null,
  name        VARCHAR2(32) not null,
  brand       VARCHAR2(32) not null,
  unit        NUMBER(2) not null,
  price       NUMBER(10,2) not null,
  type        VARCHAR2(20) not null,
  create_date DATE,
  del_flag    NUMBER(2) not null,
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
comment on table T_4S_FITTINGS
  is '配件息表';
comment on column T_4S_FITTINGS.id
  is 'ID(PK)                 ';
comment on column T_4S_FITTINGS.name
  is '配件名称               ';
comment on column T_4S_FITTINGS.brand
  is '品牌                   ';
comment on column T_4S_FITTINGS.unit
  is '单位(字典)             ';
comment on column T_4S_FITTINGS.price
  is '单价(元)               ';
comment on column T_4S_FITTINGS.type
  is '型号                   ';
comment on column T_4S_FITTINGS.create_date
  is '创建日期               ';
comment on column T_4S_FITTINGS.del_flag
  is '删除标志(1-未删,0-已删)';
comment on column T_4S_FITTINGS.remark
  is '备注                   ';
alter table T_4S_FITTINGS
  add constraint PK_T_4S_FITTINGS primary key (ID)
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

prompt Creating T_4S_FITTINGS_INORDER...
create table T_4S_FITTINGS_INORDER
(
  id          NUMBER(10) not null,
  fittings_id NUMBER(10) not null,
  supplier_id NUMBER(10) not null,
  in_price    NUMBER(10,2) not null,
  count       NUMBER(5) not null,
  total       NUMBER(10,2),
  create_date DATE,
  in_date     DATE,
  in_state    NUMBER(2) not null,
  remark      VARCHAR2(64),
  del_flag    NUMBER(2)
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
comment on table T_4S_FITTINGS_INORDER
  is '配件进货单信息表';
comment on column T_4S_FITTINGS_INORDER.id
  is 'ID(PK)                 ';
comment on column T_4S_FITTINGS_INORDER.fittings_id
  is '配件ID(FK)             ';
comment on column T_4S_FITTINGS_INORDER.supplier_id
  is '供应商ID(FK)           ';
comment on column T_4S_FITTINGS_INORDER.in_price
  is '进货价                 ';
comment on column T_4S_FITTINGS_INORDER.count
  is '数量                   ';
comment on column T_4S_FITTINGS_INORDER.total
  is '总价                   ';
comment on column T_4S_FITTINGS_INORDER.create_date
  is '填单日期(年月日时分)   ';
comment on column T_4S_FITTINGS_INORDER.in_date
  is '入库日期(年月日时分)   ';
comment on column T_4S_FITTINGS_INORDER.in_state
  is '入库状态(字典)         ';
comment on column T_4S_FITTINGS_INORDER.remark
  is '备注                   ';
comment on column T_4S_FITTINGS_INORDER.del_flag
  is '删除标志(1-未删,0-已删)';
alter table T_4S_FITTINGS_INORDER
  add constraint PK_T_4S_FITTINGS_INORDER primary key (ID)
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

prompt Creating T_4S_FITTINGS_SELLORDER...
create table T_4S_FITTINGS_SELLORDER
(
  id          NUMBER(10) not null,
  fittings_id NUMBER(10) not null,
  customer_id NUMBER(10) not null,
  salesman    VARCHAR2(32) not null,
  sell_price  NUMBER(10,2) not null,
  count       NUMBER(5) not null,
  total       NUMBER(10,2),
  sell_date   DATE,
  out_date    DATE,
  out_state   NUMBER(2) not null,
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
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table T_4S_FITTINGS_SELLORDER
  is '配件销售单信息表';
comment on column T_4S_FITTINGS_SELLORDER.id
  is 'ID(PK)      ';
comment on column T_4S_FITTINGS_SELLORDER.fittings_id
  is '配件ID(FK)    ';
comment on column T_4S_FITTINGS_SELLORDER.customer_id
  is '客户ID(FK)    ';
comment on column T_4S_FITTINGS_SELLORDER.salesman
  is '销售人姓名    ';
comment on column T_4S_FITTINGS_SELLORDER.sell_price
  is '实际单价    ';
comment on column T_4S_FITTINGS_SELLORDER.count
  is '数量      ';
comment on column T_4S_FITTINGS_SELLORDER.total
  is '总价      ';
comment on column T_4S_FITTINGS_SELLORDER.sell_date
  is '销售日期(年月日时分)  ';
comment on column T_4S_FITTINGS_SELLORDER.out_date
  is '提货日期(年月日)  ';
comment on column T_4S_FITTINGS_SELLORDER.out_state
  is '提货状态(字典)    ';
comment on column T_4S_FITTINGS_SELLORDER.remark
  is '备注      ';
alter table T_4S_FITTINGS_SELLORDER
  add constraint PK_T_4S_FITTINGS_SELLORDER primary key (ID)
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

prompt Creating T_4S_FITTINGS_STOCK...
create table T_4S_FITTINGS_STOCK
(
  id          NUMBER(10) not null,
  fittings_id NUMBER(10) not null,
  count       NUMBER(5) not null,
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
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table T_4S_FITTINGS_STOCK
  is '配件库存信息表';
comment on column T_4S_FITTINGS_STOCK.id
  is 'ID(PK)    ';
comment on column T_4S_FITTINGS_STOCK.fittings_id
  is '配件ID(FK)  ';
comment on column T_4S_FITTINGS_STOCK.count
  is '数量    ';
comment on column T_4S_FITTINGS_STOCK.remark
  is '备注    ';
alter table T_4S_FITTINGS_STOCK
  add constraint PK_T_4S_FITTINGS_STOCK primary key (ID)
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

prompt Creating T_4S_MENU...
create table T_4S_MENU
(
  id          NUMBER(10) not null,
  name        VARCHAR2(32) not null,
  url         VARCHAR2(64) not null,
  parent_id   NUMBER(10),
  menu_level  NUMBER(2) not null,
  create_date DATE,
  use_flag    NUMBER(2) not null,
  del_flag    NUMBER(2) not null
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
comment on table T_4S_MENU
  is '菜单信息表';
comment on column T_4S_MENU.id
  is '菜单ID(PK)';
comment on column T_4S_MENU.name
  is '菜单名称';
comment on column T_4S_MENU.url
  is '链接路径';
comment on column T_4S_MENU.parent_id
  is '父菜单';
comment on column T_4S_MENU.menu_level
  is '级别(1-一级,2-二级,3-权限)';
comment on column T_4S_MENU.create_date
  is '创建日期';
comment on column T_4S_MENU.use_flag
  is '可用标准(1-可用,2-不可用)';
comment on column T_4S_MENU.del_flag
  is '删除标准(1-未删,0-已删)';
alter table T_4S_MENU
  add constraint PK_T_4S_MENU primary key (ID)
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

prompt Creating T_4S_ROLE...
create table T_4S_ROLE
(
  id          NUMBER(10) not null,
  name        VARCHAR2(32) not null,
  create_date DATE,
  del_flag    NUMBER(2) not null
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
comment on table T_4S_ROLE
  is '角色信息表';
comment on column T_4S_ROLE.id
  is 'ID(PK)';
comment on column T_4S_ROLE.name
  is '角色名称';
comment on column T_4S_ROLE.create_date
  is '创建日期';
comment on column T_4S_ROLE.del_flag
  is '删除标准(1-未删,0-已删)';
alter table T_4S_ROLE
  add constraint PK_T_4S_ROLE primary key (ID)
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

prompt Creating T_4S_ROLE2MENU...
create table T_4S_ROLE2MENU
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
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table T_4S_ROLE2MENU
  is '菜单与角色关系表';

prompt Creating T_4S_SUPPLIER...
create table T_4S_SUPPLIER
(
  id           NUMBER(10) not null,
  name         VARCHAR2(64) not null,
  contacts     VARCHAR2(32),
  contact_tel  VARCHAR2(32),
  bank_name    VARCHAR2(32),
  bank_account VARCHAR2(32),
  create_date  DATE,
  del_flag     NUMBER(2) not null,
  remark       VARCHAR2(64)
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
comment on table T_4S_SUPPLIER
  is '供应商息表';
comment on column T_4S_SUPPLIER.id
  is 'ID(PK)                 ';
comment on column T_4S_SUPPLIER.name
  is '供应商名称             ';
comment on column T_4S_SUPPLIER.contacts
  is '联系人姓名             ';
comment on column T_4S_SUPPLIER.contact_tel
  is '联系电话               ';
comment on column T_4S_SUPPLIER.bank_name
  is '开户银行               ';
comment on column T_4S_SUPPLIER.bank_account
  is '银行账号               ';
comment on column T_4S_SUPPLIER.create_date
  is '创建日期               ';
comment on column T_4S_SUPPLIER.del_flag
  is '删除标志(1-未删,0-已删)';
comment on column T_4S_SUPPLIER.remark
  is '备注                   ';
alter table T_4S_SUPPLIER
  add constraint PK_T_4S_SUPPLIER primary key (ID)
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

prompt Creating T_4S_USER...
create table T_4S_USER
(
  id          NUMBER(10) not null,
  dept_id     NUMBER(10) not null,
  name        VARCHAR2(50) not null,
  sex         NUMBER(2),
  birthday    DATE,
  entry_date  DATE,
  username    VARCHAR2(32) not null,
  password    VARCHAR2(32) not null,
  login_flag  NUMBER(2) not null,
  create_date DATE,
  apply_state NUMBER(2) not null,
  role_id     NUMBER(10) not null
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
comment on table T_4S_USER
  is '员工信息表';
comment on column T_4S_USER.id
  is '员工ID(PK)               ';
comment on column T_4S_USER.dept_id
  is '所属部门(FK)             ';
comment on column T_4S_USER.name
  is '员工姓名                 ';
comment on column T_4S_USER.sex
  is '性别(1-男,2-女)          ';
comment on column T_4S_USER.birthday
  is '出生日期                 ';
comment on column T_4S_USER.entry_date
  is '入职时间                 ';
comment on column T_4S_USER.username
  is '登录用户名               ';
comment on column T_4S_USER.password
  is '登录密码                 ';
comment on column T_4S_USER.login_flag
  is '登录状态(1-冻结,2-正常)  ';
comment on column T_4S_USER.create_date
  is '创建日期                 ';
comment on column T_4S_USER.apply_state
  is '申请状态(1-申请中,2-通过)';
comment on column T_4S_USER.role_id
  is '角色ID(FK)               ';
alter table T_4S_USER
  add constraint PK_T_4S_USER primary key (ID)
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

prompt Disabling triggers for T_4S_CAR...
alter table T_4S_CAR disable all triggers;
prompt Disabling triggers for T_4S_CAR_INORDER...
alter table T_4S_CAR_INORDER disable all triggers;
prompt Disabling triggers for T_4S_CAR_SELLORDER...
alter table T_4S_CAR_SELLORDER disable all triggers;
prompt Disabling triggers for T_4S_CAR_STOCK...
alter table T_4S_CAR_STOCK disable all triggers;
prompt Disabling triggers for T_4S_CUSTOMER...
alter table T_4S_CUSTOMER disable all triggers;
prompt Disabling triggers for T_4S_DEPT...
alter table T_4S_DEPT disable all triggers;
prompt Disabling triggers for T_4S_DICT...
alter table T_4S_DICT disable all triggers;
prompt Disabling triggers for T_4S_FEEDBACK...
alter table T_4S_FEEDBACK disable all triggers;
prompt Disabling triggers for T_4S_FITTINGS...
alter table T_4S_FITTINGS disable all triggers;
prompt Disabling triggers for T_4S_FITTINGS_INORDER...
alter table T_4S_FITTINGS_INORDER disable all triggers;
prompt Disabling triggers for T_4S_FITTINGS_SELLORDER...
alter table T_4S_FITTINGS_SELLORDER disable all triggers;
prompt Disabling triggers for T_4S_FITTINGS_STOCK...
alter table T_4S_FITTINGS_STOCK disable all triggers;
prompt Disabling triggers for T_4S_MENU...
alter table T_4S_MENU disable all triggers;
prompt Disabling triggers for T_4S_ROLE...
alter table T_4S_ROLE disable all triggers;
prompt Disabling triggers for T_4S_ROLE2MENU...
alter table T_4S_ROLE2MENU disable all triggers;
prompt Disabling triggers for T_4S_SUPPLIER...
alter table T_4S_SUPPLIER disable all triggers;
prompt Disabling triggers for T_4S_USER...
alter table T_4S_USER disable all triggers;
prompt Deleting T_4S_USER...
delete from T_4S_USER;
commit;
prompt Deleting T_4S_SUPPLIER...
delete from T_4S_SUPPLIER;
commit;
prompt Deleting T_4S_ROLE2MENU...
delete from T_4S_ROLE2MENU;
commit;
prompt Deleting T_4S_ROLE...
delete from T_4S_ROLE;
commit;
prompt Deleting T_4S_MENU...
delete from T_4S_MENU;
commit;
prompt Deleting T_4S_FITTINGS_STOCK...
delete from T_4S_FITTINGS_STOCK;
commit;
prompt Deleting T_4S_FITTINGS_SELLORDER...
delete from T_4S_FITTINGS_SELLORDER;
commit;
prompt Deleting T_4S_FITTINGS_INORDER...
delete from T_4S_FITTINGS_INORDER;
commit;
prompt Deleting T_4S_FITTINGS...
delete from T_4S_FITTINGS;
commit;
prompt Deleting T_4S_FEEDBACK...
delete from T_4S_FEEDBACK;
commit;
prompt Deleting T_4S_DICT...
delete from T_4S_DICT;
commit;
prompt Deleting T_4S_DEPT...
delete from T_4S_DEPT;
commit;
prompt Deleting T_4S_CUSTOMER...
delete from T_4S_CUSTOMER;
commit;
prompt Deleting T_4S_CAR_STOCK...
delete from T_4S_CAR_STOCK;
commit;
prompt Deleting T_4S_CAR_SELLORDER...
delete from T_4S_CAR_SELLORDER;
commit;
prompt Deleting T_4S_CAR_INORDER...
delete from T_4S_CAR_INORDER;
commit;
prompt Deleting T_4S_CAR...
delete from T_4S_CAR;
commit;
prompt Loading T_4S_CAR...
prompt Table is empty
prompt Loading T_4S_CAR_INORDER...
prompt Table is empty
prompt Loading T_4S_CAR_SELLORDER...
prompt Table is empty
prompt Loading T_4S_CAR_STOCK...
prompt Table is empty
prompt Loading T_4S_CUSTOMER...
prompt Table is empty
prompt Loading T_4S_DEPT...
prompt Table is empty
prompt Loading T_4S_DICT...
prompt Table is empty
prompt Loading T_4S_FEEDBACK...
prompt Table is empty
prompt Loading T_4S_FITTINGS...
prompt Table is empty
prompt Loading T_4S_FITTINGS_INORDER...
prompt Table is empty
prompt Loading T_4S_FITTINGS_SELLORDER...
prompt Table is empty
prompt Loading T_4S_FITTINGS_STOCK...
prompt Table is empty
prompt Loading T_4S_MENU...
prompt Table is empty
prompt Loading T_4S_ROLE...
prompt Table is empty
prompt Loading T_4S_ROLE2MENU...
prompt Table is empty
prompt Loading T_4S_SUPPLIER...
prompt Table is empty
prompt Loading T_4S_USER...
prompt Table is empty
prompt Enabling triggers for T_4S_CAR...
alter table T_4S_CAR enable all triggers;
prompt Enabling triggers for T_4S_CAR_INORDER...
alter table T_4S_CAR_INORDER enable all triggers;
prompt Enabling triggers for T_4S_CAR_SELLORDER...
alter table T_4S_CAR_SELLORDER enable all triggers;
prompt Enabling triggers for T_4S_CAR_STOCK...
alter table T_4S_CAR_STOCK enable all triggers;
prompt Enabling triggers for T_4S_CUSTOMER...
alter table T_4S_CUSTOMER enable all triggers;
prompt Enabling triggers for T_4S_DEPT...
alter table T_4S_DEPT enable all triggers;
prompt Enabling triggers for T_4S_DICT...
alter table T_4S_DICT enable all triggers;
prompt Enabling triggers for T_4S_FEEDBACK...
alter table T_4S_FEEDBACK enable all triggers;
prompt Enabling triggers for T_4S_FITTINGS...
alter table T_4S_FITTINGS enable all triggers;
prompt Enabling triggers for T_4S_FITTINGS_INORDER...
alter table T_4S_FITTINGS_INORDER enable all triggers;
prompt Enabling triggers for T_4S_FITTINGS_SELLORDER...
alter table T_4S_FITTINGS_SELLORDER enable all triggers;
prompt Enabling triggers for T_4S_FITTINGS_STOCK...
alter table T_4S_FITTINGS_STOCK enable all triggers;
prompt Enabling triggers for T_4S_MENU...
alter table T_4S_MENU enable all triggers;
prompt Enabling triggers for T_4S_ROLE...
alter table T_4S_ROLE enable all triggers;
prompt Enabling triggers for T_4S_ROLE2MENU...
alter table T_4S_ROLE2MENU enable all triggers;
prompt Enabling triggers for T_4S_SUPPLIER...
alter table T_4S_SUPPLIER enable all triggers;
prompt Enabling triggers for T_4S_USER...
alter table T_4S_USER enable all triggers;
set feedback on
set define on
prompt Done.

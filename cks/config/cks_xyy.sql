drop database if exists cks;

create database cks default character set utf8;

use cks;

DROP TABLE IF EXISTS user;

CREATE TABLE user (
  user_id varchar(100),
  user_name varchar(80),
  login_name varchar(80),
  password varchar(100),
  email varchar(80),
  mobile varchar(80),
	type   varchar(6),
  employee_base_id varchar(100),
  status int,
  created_by varchar(100),
  creation_date varchar(80) ,
  last_update_by varchar(100),
  last_update_date varchar(80) ,
  PRIMARY KEY (user_id)
);

/* 添加用户数据*/
INSERT INTO user VALUES ('1', 'admin', 'admin', 'admin', 'email', 'mobile', '1','1', 1, 1, '', 1, '');
INSERT INTO user VALUES ('2', 'admin2', 'admin2', 'admin2', 'email', 'mobile','1', '2', 1, 1, '', 1, '');
INSERT INTO user VALUES ('10000', 'xyy', 'xyy', 'xyy', 'email', 'mobile', '1','1', 1, 1, '', 1, '');
INSERT INTO user VALUES ('10001', 'zdy', 'zdy', 'zdy', 'email', 'mobile','1', '2', 1, 1, '', 1, '');
INSERT INTO user VALUES ('10002', 'gj',  'gj',  'gj', 'email', 'mobile','1', '3', 1, 1, '', 1, '');
INSERT INTO user VALUES ('10003', 'wl',  'wl',  'wl', 'email', 'mobile', '1','4', 1, 1, '', 1, '');
/* 部门表*/
DROP TABLE IF EXISTS department;
CREATE TABLE department (
  dept_id varchar(100),
  dept_name varchar(80),
	dept_type	varchar(12),
  leader_id varchar(100),
  description varchar(100),
  PRIMARY KEY (dept_id)
);
/* 员工表*/
DROP TABLE IF EXISTS employeeBase;
CREATE TABLE employeeBase (
  employee_base_id varchar(100),
  employee_dept_id varchar(100),
	employee_name	varchar(80),
  email	varchar(80), 
  mobile	varchar(80), 
  PRIMARY KEY (employee_base_id)
);

/* 业主表*/
DROP TABLE IF EXISTS owner;
CREATE TABLE owner (
  owner_id varchar(100),
  owner_name varchar(80),
  own_loc varchar(40),
  own_location varchar(40),
  email	varchar(80),	 	
  mobile	varchar(80),	 
  PRIMARY KEY (owner_id)
);

/* 菜单表 */
DROP TABLE IF EXISTS menu;
CREATE TABLE menu (
  menu_id varchar(100) ,
  user_id varchar(100), 
  name varchar(40) DEFAULT NULL,
  parent_id varchar(100) DEFAULT NULL,
  url varchar(100) DEFAULT NULL,
  root_menu_id varchar(100) DEFAULT NULL,
  sort_no int(11) DEFAULT NULL,
  created_by varchar(100) DEFAULT NULL,
  creation_date varchar(80) DEFAULT NULL,
  last_update_by varchar(100),
  last_update_date varchar(80) ,
  PRIMARY KEY (menu_id,user_id)
);

/* 菜单表记录*/
INSERT INTO menu VALUES ('1', '10003', '主菜单', '1', null, '1', '1', null, null, null, null);
INSERT INTO menu VALUES ('2', '10003', '首页', '1', '/cks/menu/main.action', '1', '2', null, null, null, null);
INSERT INTO menu VALUES ('3', '10003', '设备台账', '1', null, '1', '4', null, null, null, null);
INSERT INTO menu VALUES ('4', '10003', '设备基础信息', '3', '/cks/menu/list.action', '1', '21', null, null, null, null);
INSERT INTO menu VALUES ('5', '10003', '设备负责人基础信息', '3', null, '1', '22', null, null, null, null);
INSERT INTO menu VALUES ('6', '10003', '设备状态', '1', '', '1', '7', null, '', null, '');
INSERT INTO menu VALUES ('7', '10003', '设备状态明细', '6', null, '1', '23', null, null, null, null);
INSERT INTO menu VALUES ('8', '10003', '结果统计', '1', '', '1', '3', null, '', null, '');
INSERT INTO menu VALUES ('9', '10003', '巡检结果总览', '8', '', '1', '9', null, '', null, '');
INSERT INTO menu VALUES ('10', '10003', '设备巡检', '1', '', '1', '5', null, '', null, '');
INSERT INTO menu VALUES ('11', '10003', '设备报修', '1', '', '1', '6', null, '', null, '');
INSERT INTO menu VALUES ('12', '10003', '设备报修单', '11', '', '1', '24', null, '', null, '');
INSERT INTO menu VALUES ('13', '10003', '电气设备巡检表', '10', '', '1', '25', null, '', null, '');
INSERT INTO menu VALUES ('14', '10003', '设备状态表', '6', null, '1', '22', null, null, null, null);

/* 状态表 */
DROP TABLE IF EXISTS status;
CREATE TABLE status (
  status_id varchar(100) ,
  ep_id	varchar(100), 
  status_val	varchar(20),
  re_type	varchar(8),
	re_typeId	varchar(100),
	remarks	varchar(100),
	created_by	varchar(100),
	creation_date	varchar(80),
	last_updated_by	varchar(100) ,
	last_updated_date	varchar(80),
  PRIMARY KEY (status_id)
);

--字典表
DROP TABLE IF EXISTS  sys_dict ;
CREATE TABLE  sys_dict  (
   id  varchar(64)   COMMENT '编号',
   value  varchar(100)   COMMENT '数据值',
   label  varchar(100)   COMMENT '标签名',
   type  varchar(100)   COMMENT '类型',
   description  varchar(100)   COMMENT '描述',
   sort  decimal(10,0)  COMMENT '排序（升序）',
   parent_id  varchar(64)  COMMENT '父级编号',
   create_by  varchar(64)   COMMENT '创建者',
   create_date  datetime  COMMENT '创建时间',
   update_by  varchar(64)   COMMENT '更新者',
   update_date  datetime   COMMENT '更新时间',
   remarks  varchar(255)   COMMENT '备注信息',
   del_flag  char(1)   DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY ( id ),
  KEY  sys_dict_value  ( value ),
  KEY  sys_dict_label  ( label ),
  KEY  sys_dict_del_flag  ( del_flag )
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典表';
INSERT INTO  sys_dict  VALUES ('1', '1', '正常使用', 'status_type', '设备状态', '1', '0', '1', '2019-01-02 10:34:17', '1', '2019-01-02 08:00:00', null, '0');
INSERT INTO  sys_dict  VALUES ('2', '2', '设备异常', 'status_type', '设备状态', '2', '0', '1', '2019-10-01 08:00:00', '1', '2019-01-02 08:00:00', '', '0');

DROP TABLE
IF EXISTS electricalCheck;

CREATE TABLE electricalCheck (
	checks_id VARCHAR (100),
	ep_id VARCHAR (100),
	status_old_val VARCHAR (20),
	user_id VARCHAR (100),
	checks_loc VARCHAR (100),
	checks_photo VARCHAR (100),
	checks_time VARCHAR (80),
	main_part VARCHAR (20),
	oil_tank VARCHAR (20),
	voice VARCHAR (20),
	sleeve VARCHAR (20),
	drainage_wire VARCHAR (20),
	fan VARCHAR (20),
	silica_gel VARCHAR (20),
	is_normal VARCHAR (20),
	status_new_val VARCHAR (20),
	created_by	varchar(100),
	creation_date	varchar(80),
	last_updated_by	varchar(100) ,
	last_updated_date	varchar(80),
	PRIMARY KEY (checks_id)
);
/* 历史状态表 */
DROP TABLE IF EXISTS hstatus;
CREATE TABLE hstatus (
	hstatus_id varchar(100) ,
  status_id varchar(100) ,
  ep_id	varchar(100), 
  status_val	varchar(20),
  re_type	varchar(8),
	re_typeId	varchar(100),
	remarks	varchar(100),
	created_by	varchar(100),
	creation_date	varchar(80),
	last_updated_by	varchar(100) ,
	last_updated_date	varchar(80),
  PRIMARY KEY (hstatus_id)
);

/* 设备报修单 */
DROP TABLE IF EXISTS equipmentRepair;
CREATE TABLE equipmentRepair (
equipmentRepair_id	varchar(100),
ep_id	varchar(100),
ep_type	varchar(20),
	
epr_ck_id	varchar(100),
epr_ck_time	varchar(80),
epr_ck_video	varchar(100),
epr_ck_photo	varchar(100),
epr_ck_describe	varchar(100),
epr_ck_level	varchar(20),
	
ep_home_equtype	varchar(20),
ep_home_equname	varchar(100),
	
ep_re_no	varchar(80),
ep_re_id	varchar(100),
ep_re_kind	varchar(20),
ep_re_group	varchar(20),
ep_re_describe	varchar(80),
ep_re_time	varchar(80),
ep_re_status	varchar(20),
	
created_by	varchar(100),
creation_date	varchar(80),
last_updated_by	varchar(100),
last_updated_date	varchar(80),
	
ep_ac_status	varchar(20),
ep_ac_nowid	varchar(20),
ep_ac_nowuser	varchar(100),
ep_ac_nextid	varchar(20),
ep_ac_nextuser	varchar(100),

);

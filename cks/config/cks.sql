drop database if exists cks;

create database cks default character set utf8;

use cks;
/* 菜单表 */
DROP TABLE IF EXISTS menu;
CREATE TABLE menu (
  menu_id int(11) NOT NULL DEFAULT '0',
  user_id int(11) NOT NULL DEFAULT '0',
  name varchar(40) DEFAULT NULL,
  parent_id int(11) DEFAULT NULL,
  url varchar(100) DEFAULT NULL,
  root_menu_id int(11) DEFAULT NULL,
  sort_no int(11) DEFAULT NULL,
  created_by int(11) DEFAULT NULL,
  creation_date varchar(80) DEFAULT NULL,
  lastUpdatedBy int(11) DEFAULT NULL,
  lastUpdatedDate varchar(80) DEFAULT NULL,
  PRIMARY KEY (menu_id,user_id)
);

/* 菜单表记录*/
INSERT INTO menu VALUES ('1', '1', '主菜单', '1', null, '1', '1', null, null, null, null);
INSERT INTO menu VALUES ('2', '1', '首页', '1', '/cks/menu/main.action', '1', '2', null, null, null, null);
INSERT INTO menu VALUES ('3', '1', '设备台账', '1', null, '1', '4', null, null, null, null);
INSERT INTO menu VALUES ('4', '1', '设备基础信息', '3', '/cks/menu/list.action', '1', '21', null, null, null, null);
INSERT INTO menu VALUES ('5', '1', '设备负责人基础信息', '3', null, '1', '22', null, null, null, null);
INSERT INTO menu VALUES ('6', '1', '设备状态', '1', '', '1', '7', null, '', null, '');
INSERT INTO menu VALUES ('7', '1', '设备状态明细', '6', null, '1', '23', null, null, null, null);
INSERT INTO menu VALUES ('8', '1', '结果统计', '1', '', '1', '3', null, '', null, '');
INSERT INTO menu VALUES ('9', '1', '巡检结果总览', '8', '', '1', '9', null, '', null, '');
INSERT INTO menu VALUES ('10', '1', '设备巡检', '1', '', '1', '5', null, '', null, '');
INSERT INTO menu VALUES ('11', '1', '设备报修', '1', '', '1', '6', null, '', null, '');
INSERT INTO menu VALUES ('12', '1', '设备报修单', '11', '', '1', '24', null, '', null, '');
INSERT INTO menu VALUES ('13', '1', '电气设备巡检表', '10', '', '1', '25', null, '', null, '');



 
DROP TABLE IF EXISTS sys_user;

DROP TABLE IF EXISTS user;

CREATE TABLE user (
  user_id int,
  user_name varchar(80),
  login_name varchar(80),
  password varchar(80),
  email varchar(80),
	mobile varchar(80),
  employee_base_id int,
  status int,
  created_by int ,
  creation_date varchar(80) ,
  lastUpdatedBy int ,
  lastUpdatedDate varchar(80) ,
  PRIMARY KEY (user_id)
);

/* 添加用户数据*/
INSERT INTO user VALUES ('1', 'admin', 'admin', 'admin', 'email', 'mobile', '1', 1, 1, '', 1, '');
INSERT INTO user VALUES ('2', 'admin2', 'admin2', 'admin2', 'email', 'mobile', '2', 1, 1, '', 1, '');
INSERT INTO user VALUES ('3', 'xyy', 'xyy', 'xyy', 'email', 'mobile', '', 1, 1, '', 1, '');




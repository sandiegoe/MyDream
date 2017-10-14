
create database MyDream;
use MyDream;

CREATE TABLE `user` (
  `uid` int(100) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `uname` varchar(100) DEFAULT NULL COMMENT '用户姓名',
  `upwd` varchar(100) DEFAULT NULL COMMENT '用户密码',
  `sex` varchar(100) DEFAULT NULL COMMENT '用户性别',
  `ubir` varchar(100) DEFAULT NULL COMMENT '用户生日',
  `uphone` varchar(100) DEFAULT NULL COMMENT '用户电话',
  `umail` varchar(100) DEFAULT NULL COMMENT '用户邮箱',
  `uaddress` varchar(100) DEFAULT NULL COMMENT '用户常用地址',
  `uflag` varchar(100) DEFAULT NULL COMMENT '用户权限',
  `ulevel` varchar(100) DEFAULT NULL COMMENT '会员等级',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
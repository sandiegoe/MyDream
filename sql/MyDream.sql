
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

DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `cid` int(100) NOT NULL AUTO_INCREMENT COMMENT '评论ID',
  `cgname` varchar(255) NOT NULL COMMENT '评论的果品名',
  `ccontent` varchar(255) DEFAULT NULL COMMENT '评论的内容',
  `cdate` varchar(255) DEFAULT NULL COMMENT '评论的日期',
  `cip` varchar(255) DEFAULT NULL COMMENT '评论人IP',
  `cauthor` varchar(255) DEFAULT NULL COMMENT '评论人',
  PRIMARY KEY (`cid`),
  KEY `cgname` (`cgname`),
)  ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '菲律宾凤梨', 'mm', '212', '121', '葛大傻');
INSERT INTO `comment` VALUES ('2', 'xueshen', 'sb', '2016-3-4', '192.169.31.243', 'sb');

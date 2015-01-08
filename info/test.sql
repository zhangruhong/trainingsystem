/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2015-01-08 18:58:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `starttime` datetime DEFAULT NULL,
  `endtime` datetime DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `goal` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `practise` longtext,
  `trainer_id` int(11) DEFAULT NULL,
  `dictionary_id` int(11) DEFAULT NULL,
  `term_id` int(11) DEFAULT NULL,
  `practise_publish_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK78A7CC3B2EAC087D` (`trainer_id`) USING BTREE,
  KEY `FK78A7CC3BEBCC5407` (`dictionary_id`) USING BTREE,
  KEY `FK78A7CC3B51D68A07` (`term_id`) USING BTREE,
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`trainer_id`) REFERENCES `user` (`id`),
  CONSTRAINT `course_ibfk_2` FOREIGN KEY (`term_id`) REFERENCES `term` (`id`),
  CONSTRAINT `course_ibfk_3` FOREIGN KEY (`dictionary_id`) REFERENCES `dictionary` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', '2014-12-23 14:45:50', '2014-12-24 14:45:53', 'spring', 'spring ...', '学好数理化走遍天下都不怕', '大会议室', '<p>spring的作业是学会使用spring的事务管理</p>', '2', '4', '1', '1');
INSERT INTO `course` VALUES ('3', '2014-12-26 09:43:27', '2014-12-31 10:30:29', 'java基础', '2345', '4567', '大视频', '', '2', '7', '1', '0');
INSERT INTO `course` VALUES ('7', '2015-01-02 14:40:42', '2015-01-02 15:20:43', 'java3', '对方好感', '发生的更好', 'sdg', null, '2', '3', '1', '0');
INSERT INTO `course` VALUES ('8', '2015-01-06 15:40:13', '2015-01-27 15:50:15', '大学语文', '大学语文内容', '学会写诗', '大视频', null, '5', '8', '1', '0');
INSERT INTO `course` VALUES ('9', '2015-01-07 09:50:22', '2015-01-07 10:00:25', '英语', '山东分公司的', '是大发光火', 'asdfg', null, '2', '8', '1', '0');

-- ----------------------------
-- Table structure for dictionary
-- ----------------------------
DROP TABLE IF EXISTS `dictionary`;
CREATE TABLE `dictionary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dictionary
-- ----------------------------
INSERT INTO `dictionary` VALUES ('3', 'j2EE', null);
INSERT INTO `dictionary` VALUES ('4', '测试分类1', null);
INSERT INTO `dictionary` VALUES ('5', '测试分类2', null);
INSERT INTO `dictionary` VALUES ('6', '测试分类3', null);
INSERT INTO `dictionary` VALUES ('7', '测试分类4', null);
INSERT INTO `dictionary` VALUES ('8', '大学课程', null);

-- ----------------------------
-- Table structure for practice
-- ----------------------------
DROP TABLE IF EXISTS `practice`;
CREATE TABLE `practice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `score` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `attachment` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  `score_description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKB013E55B9DEC59A7` (`user_id`) USING BTREE,
  KEY `FKB013E55BF87B7867` (`course_id`) USING BTREE,
  CONSTRAINT `practice_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `practice_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of practice
-- ----------------------------
INSERT INTO `practice` VALUES ('10', '0', '1', '<p>我的作业已经完成<br/></p>', null, '3', '1', null);

-- ----------------------------
-- Table structure for term
-- ----------------------------
DROP TABLE IF EXISTS `term`;
CREATE TABLE `term` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `description` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of term
-- ----------------------------
INSERT INTO `term` VALUES ('1', '学期1', '学期1');
INSERT INTO `term` VALUES ('2', '计划2', '计划简介');
INSERT INTO `term` VALUES ('3', '保护U健康', '个');
INSERT INTO `term` VALUES ('9', '第3期synnex英语培训', '第二期2很叼id额2');
INSERT INTO `term` VALUES ('10', '123', '123');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `loginname` varchar(20) NOT NULL,
  `password` varchar(33) NOT NULL,
  `phoneno` varchar(255) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'hiramh@synnex.com', 'admin', '21232f297a57a5a743894a0e4a801fc3', '234523', '0');
INSERT INTO `user` VALUES ('2', 'hiramh@synnex.com', 'trainer', '2c065aae9fcb37b49043a5a2012b3dbf', '345', '1');
INSERT INTO `user` VALUES ('3', 'hiramh@synnex.com', 'trainee', '3961a903097f9cb79f07dae17bac2f11', '3451', '2');
INSERT INTO `user` VALUES ('4', 'sdfg@s.cn', 'Davis', '670b14728ad9902aecba32e22fa4f6bd', '324135465', '2');
INSERT INTO `user` VALUES ('5', 'asf@sdf.com', 'ketyz', '670b14728ad9902aecba32e22fa4f6bd', 'adrg', '1');
INSERT INTO `user` VALUES ('6', '123@123.cn', '1123a', '8b353d5cc07e13577608711f4602fcb7', '123456789', '1');
INSERT INTO `user` VALUES ('7', 'bitch0@123.cn', 'bitch0loginname', 'c628108c046723bc44dfe74263d0ec9c', 'bitch0phoneno', '0');
INSERT INTO `user` VALUES ('8', 'bitch1@123.cn', 'bitch1loginname', '1ee9547a3f708f8fd986216bffd1eb7', 'bitch1phoneno', '1');
INSERT INTO `user` VALUES ('9', 'bitch2@123.cn', 'bitch2loginname', '73ecc90fb95df0de757faa6aa042b601', 'bitch2phoneno', '2');
INSERT INTO `user` VALUES ('10', 'bitch3@123.cn', 'bitch3loginname', '67603590914465c502420e5aa3a0d5a1', 'bitch3phoneno', '0');
INSERT INTO `user` VALUES ('11', 'bitch4@123.cn', 'bitch4loginname', '6b92266a310e8d001661e666deb84392', 'bitch4phoneno', '1');
INSERT INTO `user` VALUES ('12', 'bitch5@123.cn', 'bitch5loginname', '7134d5d690c65e02379af5b565f73ff1', 'bitch5phoneno', '2');
INSERT INTO `user` VALUES ('13', 'bitch6@123.cn', 'bitch6loginname', '6ad738a59b27906a7a29f2a52baecb42', 'bitch6phoneno', '0');
INSERT INTO `user` VALUES ('14', 'bitch7@123.cn', 'bitch7loginname', 'f2e8aa3b925c8c691f2ce1d21903adc8', 'bitch7phoneno', '1');
INSERT INTO `user` VALUES ('15', 'bitch8@123.cn', 'bitch8loginname', 'c47114105fa52a98ba23df756b37a3c3', 'bitch8phoneno', '2');
INSERT INTO `user` VALUES ('16', 'bitch9@123.cn', 'bitch9loginname', 'f49be7e59d493f77c03cc1ffd70d6dd1', 'bitch9phoneno', '0');
INSERT INTO `user` VALUES ('17', 'bitch10@123.cn', 'bitch10loginname', 'd27e8af821c6010f7a603f44edabdfb8', 'bitch10phoneno', '1');
INSERT INTO `user` VALUES ('18', 'bitch11@123.cn', 'bitch11loginname', '690f7ce2ffb40ca67d812721dd9db2de', 'bitch11phoneno', '2');
INSERT INTO `user` VALUES ('19', 'bitch12@123.cn', 'bitch12loginname', '34afc98da14b2fcd3dc29c979701f2c5', 'bitch12phoneno', '0');
INSERT INTO `user` VALUES ('20', 'bitch13@123.cn', 'bitch13loginname', 'fa3457457a8cd9eee2957ab838612345', 'bitch13phoneno', '1');
INSERT INTO `user` VALUES ('21', 'bitch14@123.cn', 'bitch14loginname', '69b92c7e10c0f1f65ff90f0b49b234ce', 'bitch14phoneno', '2');
INSERT INTO `user` VALUES ('22', 'bitch15@123.cn', 'bitch15loginname', 'e388414154b0985078bd7a82b35dd8f9', 'bitch15phoneno', '0');
INSERT INTO `user` VALUES ('23', 'bitch16@123.cn', 'bitch16loginname', '6b2238d9440248bf6c71e1c211538c23', 'bitch16phoneno', '1');
INSERT INTO `user` VALUES ('24', 'bitch17@123.cn', 'bitch17loginname', '80ebbce22385cff596542b5b2bad6ff6', 'bitch17phoneno', '2');
INSERT INTO `user` VALUES ('25', 'bitch18@123.cn', 'bitch18loginname', 'c258ae5d690c55cb4f696f398f848bcc', 'bitch18phoneno', '0');
INSERT INTO `user` VALUES ('26', 'bitch19@123.cn', 'bitch19loginname', 'b3fc3d71142e7242998e0bd747ebd26e', 'bitch19phoneno', '1');
INSERT INTO `user` VALUES ('27', 'bitch20@123.cn', 'bitch20loginname', 'd4ed3b28dce4a8772680717332355276', 'bitch20phoneno', '2');
INSERT INTO `user` VALUES ('28', 'bitch21@123.cn', 'bitch21loginname', 'db21e8bbf48b2411b0d57e0c52d0da0f', 'bitch21phoneno', '0');
INSERT INTO `user` VALUES ('29', 'bitch22@123.cn', 'bitch22loginname', 'e255f1961b2e9945a79e6e11f934e462', 'bitch22phoneno', '1');
INSERT INTO `user` VALUES ('30', 'bitch23@123.cn', 'bitch23loginname', '18c92cdb56d9905ee2f1f9912c8dedaf', 'bitch23phoneno', '2');
INSERT INTO `user` VALUES ('31', 'bitch24@123.cn', 'bitch24loginname', '3088eb3e91956289deafdacab5d26821', 'bitch24phoneno', '0');
INSERT INTO `user` VALUES ('32', 'bitch25@123.cn', 'bitch25loginname', 'c8df29e014b5d3fe02a2bc1bb6a7da76', 'bitch25phoneno', '1');
INSERT INTO `user` VALUES ('33', 'bitch26@123.cn', 'bitch26loginname', 'e5230fc5e6c2790e75ecf8d6656bd117', 'bitch26phoneno', '2');
INSERT INTO `user` VALUES ('34', 'bitch27@123.cn', 'bitch27loginname', 'a0dc42ff0a081a58638d8dcc16cdf16e', 'bitch27phoneno', '0');
INSERT INTO `user` VALUES ('35', 'bitch28@123.cn', 'bitch28loginname', '477c7b64fc047e2f1dd23da89d166f9d', 'bitch28phoneno', '1');
INSERT INTO `user` VALUES ('36', 'bitch29@123.cn', 'bitch29loginname', 'b11c2967983b19dbeedbe037737afb9b', 'bitch29phoneno', '2');
INSERT INTO `user` VALUES ('37', 'bitch30@123.cn', 'bitch30loginname', '2b47647b45eba9c2144fe7c5d0403bcd', 'bitch30phoneno', '0');
INSERT INTO `user` VALUES ('38', 'bitch31@123.cn', 'bitch31loginname', 'fc08e37d0399107a3de85ab8ca8ce8ad', 'bitch31phoneno', '1');
INSERT INTO `user` VALUES ('39', 'bitch32@123.cn', 'bitch32loginname', '595305ac3e671dc7a1bfab5857288238', 'bitch32phoneno', '2');
INSERT INTO `user` VALUES ('40', 'bitch33@123.cn', 'bitch33loginname', 'fd4fac2c905b22becf4e02c7da85d25f', 'bitch33phoneno', '0');
INSERT INTO `user` VALUES ('41', 'bitch34@123.cn', 'bitch34loginname', 'c758c3b2e4c64b300ded28cb52e32e1a', 'bitch34phoneno', '1');
INSERT INTO `user` VALUES ('42', 'bitch35@123.cn', 'bitch35loginname', '299d93e04328158aefe260cc453e91a6', 'bitch35phoneno', '2');
INSERT INTO `user` VALUES ('43', 'bitch36@123.cn', 'bitch36loginname', '5746d2fecb9046b004340f2af8fa5147', 'bitch36phoneno', '0');
INSERT INTO `user` VALUES ('44', 'bitch37@123.cn', 'bitch37loginname', 'a7163722c8c275a3dc41620a9014be91', 'bitch37phoneno', '1');
INSERT INTO `user` VALUES ('45', 'bitch38@123.cn', 'bitch38loginname', '337719171bf040a6608c89bbef4c893d', 'bitch38phoneno', '2');
INSERT INTO `user` VALUES ('46', 'bitch39@123.cn', 'bitch39loginname', '71f57b6ccb149243e3cc1f15af66f7d7', 'bitch39phoneno', '0');
INSERT INTO `user` VALUES ('47', 'bitch40@123.cn', 'bitch40loginname', '5f93d98a4600562f0fb3b38ac19aff91', 'bitch40phoneno', '1');
INSERT INTO `user` VALUES ('48', 'bitch41@123.cn', 'bitch41loginname', '64f64ae3e16b880914db51dda3170dac', 'bitch41phoneno', '2');
INSERT INTO `user` VALUES ('49', 'bitch42@123.cn', 'bitch42loginname', 'c42a91d50f51a97b163987fd823f0a05', 'bitch42phoneno', '0');
INSERT INTO `user` VALUES ('50', 'bitch43@123.cn', 'bitch43loginname', 'ac9a43ebdeba5051ff83fe13755b4edc', 'bitch43phoneno', '1');
INSERT INTO `user` VALUES ('51', 'bitch44@123.cn', 'bitch44loginname', 'a51a08d65f9679bdbef109f044643040', 'bitch44phoneno', '2');
INSERT INTO `user` VALUES ('52', 'bitch45@123.cn', 'bitch45loginname', 'bd7c17401c492166e331612c805f05f2', 'bitch45phoneno', '0');
INSERT INTO `user` VALUES ('53', 'bitch46@123.cn', 'bitch46loginname', 'a58fa5c303e775d829843826024c37f4', 'bitch46phoneno', '1');
INSERT INTO `user` VALUES ('54', 'bitch47@123.cn', 'bitch47loginname', '8753e1ea1ce960cfeef4ac1ca473a9bf', 'bitch47phoneno', '2');
INSERT INTO `user` VALUES ('55', 'bitch48@123.cn', 'bitch48loginname', '7536e97f2e4d50059a765dd0ec62fc24', 'bitch48phoneno', '0');
INSERT INTO `user` VALUES ('56', 'bitch49@123.cn', 'bitch49loginname', 'e6e5fbadf9d8359701dbb1d96aa30bda', 'bitch49phoneno', '1');
INSERT INTO `user` VALUES ('57', 'bitch50@123.cn', 'bitch50loginname', '856edf89fb055f9d5bc1339cb72a6e23', 'bitch50phoneno', '2');
INSERT INTO `user` VALUES ('58', 'bitch51@123.cn', 'bitch51loginname', '8cae62ea32c0f843f5bd40cd97f2b2dc', 'bitch51phoneno', '0');
INSERT INTO `user` VALUES ('59', 'bitch52@123.cn', 'bitch52loginname', '3fe11387bde23a17c6145896839d7530', 'bitch52phoneno', '1');
INSERT INTO `user` VALUES ('60', 'bitch53@123.cn', 'bitch53loginname', '60b4e59dc67cc9706190ca41f2f6c628', 'bitch53phoneno', '2');
INSERT INTO `user` VALUES ('61', 'bitch54@123.cn', 'bitch54loginname', '1197fad774a896a32558d3bf8a469b4d', 'bitch54phoneno', '0');
INSERT INTO `user` VALUES ('62', 'bitch55@123.cn', 'bitch55loginname', 'd7b4c17dfad0662c2b582b3275d2504d', 'bitch55phoneno', '1');
INSERT INTO `user` VALUES ('63', 'bitch56@123.cn', 'bitch56loginname', 'ba8062a774f3da18eab80c519a82dced', 'bitch56phoneno', '2');
INSERT INTO `user` VALUES ('64', 'bitch57@123.cn', 'bitch57loginname', '14eb4d7dd0ac61b837bf7e588fdde152', 'bitch57phoneno', '0');
INSERT INTO `user` VALUES ('65', 'bitch58@123.cn', 'bitch58loginname', '65dede9563bef93ede89ce4c632a8dab', 'bitch58phoneno', '1');
INSERT INTO `user` VALUES ('66', 'bitch59@123.cn', 'bitch59loginname', '91e5b9df5fa4cf27f0702842bf6f9d5d', 'bitch59phoneno', '2');
INSERT INTO `user` VALUES ('67', 'bitch60@123.cn', 'bitch60loginname', 'a2204302e7ee91ecbc906f195cd71e4f', 'bitch60phoneno', '0');
INSERT INTO `user` VALUES ('68', 'bitch61@123.cn', 'bitch61loginname', 'a56f3e9ae2f58bdd36608f9229ba01b1', 'bitch61phoneno', '1');
INSERT INTO `user` VALUES ('69', 'bitch62@123.cn', 'bitch62loginname', '45c419e6718b0d7f66f59c0f1d1c331', 'bitch62phoneno', '2');
INSERT INTO `user` VALUES ('70', 'bitch63@123.cn', 'bitch63loginname', '7475a8ece264590dd19539d0081844ed', 'bitch63phoneno', '0');
INSERT INTO `user` VALUES ('71', 'bitch64@123.cn', 'bitch64loginname', 'd8b065ab4dbaf099a626f042ea369fa6', 'bitch64phoneno', '1');
INSERT INTO `user` VALUES ('72', 'bitch65@123.cn', 'bitch65loginname', 'e1b5958a452f1409eab0b76d69a0ea71', 'bitch65phoneno', '2');
INSERT INTO `user` VALUES ('73', 'bitch66@123.cn', 'bitch66loginname', 'f637a2e4fc8bf6de6215da68b221f89', 'bitch66phoneno', '0');
INSERT INTO `user` VALUES ('74', 'bitch67@123.cn', 'bitch67loginname', 'dbbaac62e8b3cf17383bf9b86b923d53', 'bitch67phoneno', '1');
INSERT INTO `user` VALUES ('75', 'bitch68@123.cn', 'bitch68loginname', '61f6bd9a82a19f6835828e90b01d06f4', 'bitch68phoneno', '2');
INSERT INTO `user` VALUES ('76', 'bitch69@123.cn', 'bitch69loginname', '468aa0fa66e0fee6a52d244ca357ecd4', 'bitch69phoneno', '0');
INSERT INTO `user` VALUES ('77', 'bitch70@123.cn', 'bitch70loginname', '473504067f5b1ad9b536233691d2e2be', 'bitch70phoneno', '1');
INSERT INTO `user` VALUES ('78', 'bitch71@123.cn', 'bitch71loginname', 'ca6d2794ece2347330b54b9049c4aeb5', 'bitch71phoneno', '2');
INSERT INTO `user` VALUES ('79', 'bitch72@123.cn', 'bitch72loginname', '1eeb4c8aa321df71dea642cda8e287ac', 'bitch72phoneno', '0');
INSERT INTO `user` VALUES ('80', 'bitch73@123.cn', 'bitch73loginname', '3785964bbf3e58486b2a5bdeadbd595c', 'bitch73phoneno', '1');
INSERT INTO `user` VALUES ('81', 'bitch74@123.cn', 'bitch74loginname', 'f58b82e866af7b16bf5f3b413974d612', 'bitch74phoneno', '2');
INSERT INTO `user` VALUES ('82', 'bitch75@123.cn', 'bitch75loginname', '778d7f608a9e5ec2c53d4439ee04affd', 'bitch75phoneno', '0');
INSERT INTO `user` VALUES ('83', 'bitch76@123.cn', 'bitch76loginname', 'f9ee48c84c0945beaf844e19625c3ba3', 'bitch76phoneno', '1');
INSERT INTO `user` VALUES ('84', 'bitch77@123.cn', 'bitch77loginname', 'e06de8172543f06ba61bc445bfbefc09', 'bitch77phoneno', '2');
INSERT INTO `user` VALUES ('85', 'bitch78@123.cn', 'bitch78loginname', 'b5dde08c9fd654a7d6e6bcb6f258f7b2', 'bitch78phoneno', '0');
INSERT INTO `user` VALUES ('86', 'bitch79@123.cn', 'bitch79loginname', 'f4a705837681fc5a5faf3a39a64de991', 'bitch79phoneno', '1');
INSERT INTO `user` VALUES ('87', 'bitch80@123.cn', 'bitch80loginname', '3ea5182c283966a27858787dcdb920c6', 'bitch80phoneno', '2');
INSERT INTO `user` VALUES ('88', 'bitch81@123.cn', 'bitch81loginname', 'a898a170f0c4bcbf2ff0629696ed7a30', 'bitch81phoneno', '0');
INSERT INTO `user` VALUES ('89', 'bitch82@123.cn', 'bitch82loginname', '18a8d90eb2ade712ff70cc6795f96f94', 'bitch82phoneno', '1');
INSERT INTO `user` VALUES ('90', 'bitch83@123.cn', 'bitch83loginname', 'e37442fbd6bfda9b4d33f1a60031a1f9', 'bitch83phoneno', '2');
INSERT INTO `user` VALUES ('91', 'bitch84@123.cn', 'bitch84loginname', 'f26c816b2504c24465ffcc734706b47', 'bitch84phoneno', '0');
INSERT INTO `user` VALUES ('92', 'bitch85@123.cn', 'bitch85loginname', 'eb3570d4ba792740ea65c8b6f3d7dc82', 'bitch85phoneno', '1');
INSERT INTO `user` VALUES ('93', 'bitch86@123.cn', 'bitch86loginname', 'de1a4e711befd79a0e9972fa44731cea', 'bitch86phoneno', '2');
INSERT INTO `user` VALUES ('94', 'bitch87@123.cn', 'bitch87loginname', '4d6ac023189682087ed1046271d12ca5', 'bitch87phoneno', '0');
INSERT INTO `user` VALUES ('95', 'bitch88@123.cn', 'bitch88loginname', 'a22cedbf702f6b496d7dfb4fa51ef67d', 'bitch88phoneno', '1');
INSERT INTO `user` VALUES ('96', 'bitch89@123.cn', 'bitch89loginname', '11e96346ae39b4bd69692f17c4f80607', 'bitch89phoneno', '2');
INSERT INTO `user` VALUES ('97', 'bitch90@123.cn', 'bitch90loginname', '6a8657d7a76446148f10500c35751286', 'bitch90phoneno', '0');
INSERT INTO `user` VALUES ('98', 'bitch91@123.cn', 'bitch91loginname', '780a630eb998d15a19fbae3cc81e7b2b', 'bitch91phoneno', '1');
INSERT INTO `user` VALUES ('99', 'bitch92@123.cn', 'bitch92loginname', '4f5472a28b82f9c85a19c717ac0e5902', 'bitch92phoneno', '2');
INSERT INTO `user` VALUES ('100', 'bitch93@123.cn', 'bitch93loginname', 'bea52a076c42f050335b811ac9433094', 'bitch93phoneno', '0');
INSERT INTO `user` VALUES ('101', 'bitch94@123.cn', 'bitch94loginname', 'ee3874cfabaf6659510f853c6d5420f9', 'bitch94phoneno', '1');
INSERT INTO `user` VALUES ('102', 'bitch95@123.cn', 'bitch95loginname', '23583260efccd4708a4265ec16dac723', 'bitch95phoneno', '2');
INSERT INTO `user` VALUES ('103', 'bitch96@123.cn', 'bitch96loginname', '41cec958137e500284d661eb5bb765ad', 'bitch96phoneno', '0');
INSERT INTO `user` VALUES ('104', 'bitch97@123.cn', 'bitch97loginname', '8221eaadbc54b2607c7632f58ed5a6bc', 'bitch97phoneno', '1');
INSERT INTO `user` VALUES ('105', 'bitch98@123.cn', 'bitch98loginname', 'bb9b04b65f6d6b3bd0f4d1d69ac774ba', 'bitch98phoneno', '2');
INSERT INTO `user` VALUES ('106', 'bitch99@123.cn', 'bitch99loginname', 'e3ba13361d17235789a70bd899274c30', 'bitch99phoneno', '0');

-- ----------------------------
-- Table structure for usergroup
-- ----------------------------
DROP TABLE IF EXISTS `usergroup`;
CREATE TABLE `usergroup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(15) DEFAULT NULL,
  `description` varchar(40) DEFAULT NULL,
  `term_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8C1ED17451D68A07` (`term_id`) USING BTREE,
  CONSTRAINT `usergroup_ibfk_1` FOREIGN KEY (`term_id`) REFERENCES `term` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of usergroup
-- ----------------------------
INSERT INTO `usergroup` VALUES ('1', '分组1', '', '1');
INSERT INTO `usergroup` VALUES ('2', '分组2', '', '1');
INSERT INTO `usergroup` VALUES ('3', '分组1', null, '2');

-- ----------------------------
-- Table structure for user_course
-- ----------------------------
DROP TABLE IF EXISTS `user_course`;
CREATE TABLE `user_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `attend_course_status` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKDB7C270F9DEC59A7` (`user_id`) USING BTREE,
  KEY `FKDB7C270FF87B7867` (`course_id`) USING BTREE,
  CONSTRAINT `user_course_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `user_course_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_course
-- ----------------------------
INSERT INTO `user_course` VALUES ('11', '1', '啊时代发生地方', '3', '1');
INSERT INTO `user_course` VALUES ('12', '2', '请假敢不敢', '4', '1');

-- ----------------------------
-- Table structure for user_usergroup
-- ----------------------------
DROP TABLE IF EXISTS `user_usergroup`;
CREATE TABLE `user_usergroup` (
  `user_id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  PRIMARY KEY (`group_id`,`user_id`),
  KEY `FK721496209DE980C2` (`group_id`) USING BTREE,
  KEY `FK721496209DEC59A7` (`user_id`) USING BTREE,
  CONSTRAINT `user_usergroup_ibfk_1` FOREIGN KEY (`group_id`) REFERENCES `usergroup` (`id`),
  CONSTRAINT `user_usergroup_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_usergroup
-- ----------------------------
INSERT INTO `user_usergroup` VALUES ('3', '1');
INSERT INTO `user_usergroup` VALUES ('4', '1');
INSERT INTO `user_usergroup` VALUES ('3', '2');

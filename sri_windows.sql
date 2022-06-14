/*
 Navicat MySQL Data Transfer

 Source Server         : lh
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : localhost:3306
 Source Schema         : sri

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 11/05/2022 13:12:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

create database if not exists SRI;
use SRI;
alter database SRI default character set utf8; 

-- ----------------------------
-- Table structure for achievement
-- ----------------------------
DROP TABLE IF EXISTS `achievement`;
CREATE TABLE `achievement`  (
  `aid` int NOT NULL AUTO_INCREMENT,
  `aname` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `aunit` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `atime` date NOT NULL,
  `alevel` varchar(3) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `atype` varchar(4) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `aaddress` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `uid` varchar(8) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`aid`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE,
  CONSTRAINT `achievement_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of achievement
-- ----------------------------
INSERT INTO `achievement` VALUES (1, '全国高校智能机器人创意大赛', '中国高等教育学会', '2021-06-28', '国家级', '竞赛', '20000001\\成果获奖\\国家级\\竞赛\\附件1-2022 年大赛内容分类、参赛要求、承办院校与决赛时间_aeb0c71e-2baa-4826-b3a4-d88c28efe0f1.docx', '20000001');
INSERT INTO `achievement` VALUES (2, '中国智能机器人格斗及竞技大赛', '中国人工智能学会', '2018-12-17', '国家级', '科研', '20000002\\成果获奖\\国家级\\科研\\中国智能机器人格斗及竞技大赛1_28509c3d-b6f9-4a17-bb29-1a7313183d70.txt', '20000002');
INSERT INTO `achievement` VALUES (3, '第七届全国大学生工程训练综合能力竞赛', '全国大学生工程训练综合能力竞赛组委会', '2021-04-19', '市级', '教学成果', '20000003\\成果获奖\\市级\\教学成果\\第七届全国大学生工程训练综合能力竞赛1_3e8fb8da-7dbe-455b-9ec8-a573fbbbc15d.txt', '20000003');
INSERT INTO `achievement` VALUES (4, '中国机器人大赛暨RoboCup中国公开赛', '中国自动化学会', '2018-07-20', '国家级', '竞赛', '20000004\\成果获奖\\国家级\\竞赛\\中国机器人大赛暨RoboCup中国公开赛1_3e72fb00-9d4c-43d9-9504-876b848e3256.txt', '20000004');
INSERT INTO `achievement` VALUES (5, '中国大学生计算机设计大赛', '教育部高等学校计算机类专业教学指导委员会', '2022-05-10', '校级', '科研', '20000005\\成果获奖\\校级\\科研\\中国大学生计算机设计大赛1_16ccbbac-7c51-4b9e-bc1c-1c7f3376509a.txt', '20000005');
INSERT INTO `achievement` VALUES (6, '互联网+创新创业大赛', '教育部与政府', '2018-05-20', '校级', '教学成果', '20000001\\成果获奖\\校级\\教学成果\\互联网+创新创业大赛1_593f4eff-861c-45f9-8af0-51a0538d7253.txt', '20000001');
INSERT INTO `achievement` VALUES (7, '全国高校智能机器人创意大赛', '中国高等教育学会', '2020-05-21', '国家级', '竞赛', '20000002\\成果获奖\\国家级\\竞赛\\全国高校智能机器人创意大赛2_33a63ab1-5d78-4e0e-9096-b234ea5d58cb.txt', '20000002');
INSERT INTO `achievement` VALUES (8, '中国智能机器人格斗及竞技大赛', '中国人工智能学会', '2020-05-22', '国家级', '科研', '20000003\\成果获奖\\国家级\\科研\\中国智能机器人格斗及竞技大赛2_cb7e7812-fba5-4ba6-a3ec-53347669f704.txt', '20000003');
INSERT INTO `achievement` VALUES (9, '第七届全国大学生工程训练综合能力竞赛', '全国大学生工程训练综合能力竞赛组委会', '2022-05-23', '市级', '教学成果', '20000004\\成果获奖\\市级\\教学成果\\第七届全国大学生工程训练综合能力竞赛2_3c1a26b3-6bc4-464a-993f-65143c8591db.txt', '20000004');
INSERT INTO `achievement` VALUES (10, '中国机器人大赛暨RoboCup中国公开赛', '中国自动化学会', '2018-05-24', '国家级', '竞赛', '20000005\\成果获奖\\国家级\\竞赛\\中国机器人大赛暨RoboCup中国公开赛2_d703dd02-2fba-4ad9-bd7d-f4b0eadddb53.txt', '20000005');
INSERT INTO `achievement` VALUES (11, '中国大学生计算机设计大赛', '教育部高等学校计算机类专业教学指导委员会', '2020-05-25', '市级', '科研', '20000001\\成果获奖\\市级\\科研\\中国大学生计算机设计大赛2_53888f47-7221-41bf-b197-c685c201cde5.txt', '20000001');
INSERT INTO `achievement` VALUES (12, '互联网+创新创业大赛', '教育部与政府', '2019-05-26', '校级', '教学成果', '20000002\\成果获奖\\校级\\教学成果\\互联网+创新创业大赛2_b2cc72cb-e049-40e2-b98b-69dc2de45a6f.txt', '20000002');
INSERT INTO `achievement` VALUES (13, '全国高校智能机器人创意大赛', '中国高等教育学会', '2020-05-27', '国家级', '竞赛', '20000003\\成果获奖\\国家级\\竞赛\\全国高校智能机器人创意大赛3_1a474a55-f38b-4e69-81d7-a7ed3c1f2f47.txt', '20000003');
INSERT INTO `achievement` VALUES (14, '中国智能机器人格斗及竞技大赛', '中国人工智能学会', '2022-05-28', '国家级', '科研', '20000004\\成果获奖\\国家级\\科研\\中国智能机器人格斗及竞技大赛3_3f587ed6-9be2-408f-8c4f-ca2ceb1327ac.txt', '20000004');
INSERT INTO `achievement` VALUES (15, '第七届全国大学生工程训练综合能力竞赛', '全国大学生工程训练综合能力竞赛组委会', '2019-05-29', '市级', '教学成果', '20000005\\成果获奖\\市级\\教学成果\\第七届全国大学生工程训练综合能力竞赛3_7b0a9aae-450c-4abe-a5e6-f87fb8db9228.txt', '20000005');
INSERT INTO `achievement` VALUES (16, '中国机器人大赛暨RoboCup中国公开赛', '中国自动化学会', '2020-05-30', '国家级', '竞赛', '20000001\\成果获奖\\国家级\\竞赛\\中国机器人大赛暨RoboCup中国公开赛3_8fd5dfa7-d888-4987-8029-87d95d26c401.txt', '20000001');
INSERT INTO `achievement` VALUES (17, '中国大学生计算机设计大赛', '教育部高等学校计算机类专业教学指导委员会', '2022-05-31', '校级', '科研', '20000002\\成果获奖\\校级\\科研\\互联网+创新创业大赛3_2a2fae8a-e266-45f5-a56f-95370ba758b0.txt', '20000002');
INSERT INTO `achievement` VALUES (18, '互联网+创新创业大赛', '教育部与政府', '2022-06-01', '市级', '教学成果', '20000003\\成果获奖\\市级\\教学成果\\互联网+创新创业大赛3_6f560015-0cd4-4efc-978f-01042a463777.txt', '20000003');
INSERT INTO `achievement` VALUES (19, '全国高校智能机器人创意大赛', '中国高等教育学会', '2019-06-02', '国家级', '竞赛', '20000004\\成果获奖\\国家级\\竞赛\\全国高校智能机器人创意大赛4_204a2fd7-20d8-417d-ada4-8ea4bed7979e.txt', '20000004');

-- ----------------------------
-- Table structure for awork
-- ----------------------------
DROP TABLE IF EXISTS `awork`;
CREATE TABLE `awork`  (
  `awid` int NOT NULL AUTO_INCREMENT,
  `awname` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `awperson` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `awpress` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `awtime` date NOT NULL,
  `awaddress` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `uid` varchar(8) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`awid`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE,
  CONSTRAINT `awork_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of awork
-- ----------------------------
INSERT INTO `awork` VALUES (1, '基于yolov5算法的目标识别系统', '张三', 'xx出版社', '2021-07-15', '20000003\\著作\\学术著作\\“互联网+”无人机人员名单_2e3dbaa2-d1f7-4739-b23d-48d330f3c919.xlsx', '20000003');
INSERT INTO `awork` VALUES (2, 'BBB', '小强', 'xx出版社', '2020-07-16', '20000002\\著作\\学术著作\\666666_a6cf5c46-2bdf-4df6-b4e8-e542d8e1c610.txt', '20000002');
INSERT INTO `awork` VALUES (3, 'CCC', '小李', 'xx出版社', '2021-07-17', '20000003\\著作\\学术著作\\基于yolov5算法的目标识别系统.txt', '20000004');
INSERT INTO `awork` VALUES (4, 'DDD', '张三', 'xx出版社', '2022-07-18', '20000003\\著作\\学术著作\\基于yolov5算法的目标识别系统.txt', '20000003');
INSERT INTO `awork` VALUES (5, 'EEE', '李四', 'xx出版社', '2019-07-19', '20000003\\著作\\学术著作\\基于yolov5算法的目标识别系统.txt', '20000005');
INSERT INTO `awork` VALUES (6, 'FFF', '小李', 'xx出版社', '2021-07-20', '20000003\\著作\\学术著作\\基于yolov5算法的目标识别系统.txt', '20000004');
INSERT INTO `awork` VALUES (7, 'GGG', '张三', 'xx出版社', '2018-07-21', '20000003\\著作\\学术著作\\基于yolov5算法的目标识别系统.txt', '20000003');
INSERT INTO `awork` VALUES (8, 'HHH', '小明', 'xx出版社', '2020-07-22', '20000003\\著作\\学术著作\\基于yolov5算法的目标识别系统.txt', '20000001');
INSERT INTO `awork` VALUES (9, 'III', '小强', 'xx出版社', '2019-07-23', '20000003\\著作\\学术著作\\基于yolov5算法的目标识别系统.txt', '20000002');

-- ----------------------------
-- Table structure for hx
-- ----------------------------
DROP TABLE IF EXISTS `hx`;
CREATE TABLE `hx`  (
  `hid` int NOT NULL AUTO_INCREMENT,
  `hname` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `hobject` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `hbtime` date NOT NULL,
  `hetime` date NOT NULL,
  `hmoney` int NOT NULL,
  `haddress` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `uid` varchar(8) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`hid`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE,
  CONSTRAINT `hx_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of hx
-- ----------------------------
INSERT INTO `hx` VALUES (1, 'aa', 'aa公司', '2020-08-12', '2021-08-15', 50000, '20000001\\横项\\aa_329e1a8a-330f-4ff2-82e6-d9292334b360.txt', '20000001');
INSERT INTO `hx` VALUES (2, 'bb', 'bb公司', '2019-08-16', '2020-08-16', 20000, '20000002\\横项\\bb_a7d11b88-aef4-4186-800f-a90061d01a09.txt', '20000002');
INSERT INTO `hx` VALUES (3, 'cc', 'cc公司', '2022-03-10', '2023-03-10', 40000, '20000003\\横项\\cc_ce566de5-5e65-4f08-864a-1ee374220cc2.txt', '20000003');
INSERT INTO `hx` VALUES (4, 'dd', 'dd公司', '2021-08-18', '2022-08-18', 9000, '20000004\\横项\\dd_ff769633-8e2d-4759-98d2-4518a5a04ecb.txt', '20000004');
INSERT INTO `hx` VALUES (5, 'ee', 'ee公司', '2022-08-19', '2023-08-19', 60000, '20000005\\横项\\ee_9263e0b9-8168-4034-9fa3-75bc81cc3081.txt', '20000005');
INSERT INTO `hx` VALUES (6, 'ff', 'ff公司', '2018-08-20', '2021-08-20', 50000, '20000001\\横项\\ff_e6176f57-5b6b-46bf-a3fd-7afbb6b8384b.txt', '20000001');
INSERT INTO `hx` VALUES (7, 'gg', 'gg公司', '2020-08-21', '2021-08-21', 20000, '20000002\\横项\\gg_c715643f-eb29-47d3-9761-d71d77920de9.txt', '20000002');
INSERT INTO `hx` VALUES (8, 'hh', 'hh公司', '2018-07-22', '2019-07-22', 40000, '20000003\\横项\\hh_54594124-9c13-4c46-89b6-c342535dea43.txt', '20000003');
INSERT INTO `hx` VALUES (9, 'ii', 'ii公司', '2021-08-23', '2022-08-23', 9000, '20000004\\横项\\ii_45565455-421b-4be9-91ec-39e742f52259.txt', '20000004');
INSERT INTO `hx` VALUES (10, 'jj', 'jj公司', '2019-08-24', '2020-08-24', 60000, '20000005\\横项\\jj_a61f4dd6-4db2-4ca3-aab5-d8f592bab108.txt', '20000005');
INSERT INTO `hx` VALUES (11, 'kk', 'kk公司', '2018-08-25', '2021-08-25', 50000, '20000001\\横项\\kk_4bb34d78-d964-48f6-8b3d-f798923f06b4.txt', '20000001');
INSERT INTO `hx` VALUES (12, 'll', 'll公司', '2018-08-26', '2021-08-26', 20000, '20000002\\横项\\ll_8ce9e8c6-7441-40dc-91a9-36a629c42cfd.txt', '20000002');
INSERT INTO `hx` VALUES (13, 'mm', 'mm公司', '2022-02-27', '2023-02-27', 40000, '20000003\\横项\\mm_c30fdd7b-27af-4d9e-89fc-d1d9190e1e55.txt', '20000003');
INSERT INTO `hx` VALUES (14, 'nn', 'nn公司', '2019-08-28', '2020-08-28', 9000, '20000004\\横项\\nn_e4cee084-f5cf-4598-9d16-0b82e056388a.txt', '20000004');
INSERT INTO `hx` VALUES (15, 'oo', 'oo公司', '2020-08-29', '2021-08-29', 60000, '20000005\\横项\\oo_07231f2a-b346-4743-a999-bc3fc65e207a.txt', '20000005');
INSERT INTO `hx` VALUES (16, 'pp', 'pp公司', '2021-08-30', '2022-08-30', 50000, '20000001\\横项\\pp_03b80741-a349-47d6-af99-83a9c54aa88c.txt', '20000001');
INSERT INTO `hx` VALUES (17, 'qq', 'qq公司', '2022-08-31', '2023-08-31', 20000, '20000002\\横项\\qq_e76486a7-b6ce-439b-b663-59472b7f3aaf.txt', '20000002');
INSERT INTO `hx` VALUES (18, 'rr', 'rr公司', '2022-02-01', '2023-02-01', 40000, '20000003\\横项\\rr_3451ebc4-ca75-4121-a347-eef069552c39.txt', '20000003');
INSERT INTO `hx` VALUES (19, 'ss', 'ss公司', '2020-09-02', '2021-09-02', 9000, '20000004\\横项\\ss_994abef0-b533-495d-8d51-c0160c7803d4.txt', '20000004');
INSERT INTO `hx` VALUES (20, 'tt', 'tt公司', '2022-01-03', '2023-01-03', 60000, '20000005\\横项\\tt_cb5a8dfd-fc0c-4369-8cc5-d77a4724836a.txt', '20000005');
INSERT INTO `hx` VALUES (22, 'xxx合同', 'xxx公司', '2022-04-30', '2023-05-04', 111111, '20000006\\横项\\前端框架_0a6f7ad0-56c7-42fe-9495-4b37cecd754c.txt', '20000006');

-- ----------------------------
-- Table structure for lp
-- ----------------------------
DROP TABLE IF EXISTS `lp`;
CREATE TABLE `lp`  (
  `lpid` varchar(12) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `lpname` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `lid` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`lpid`, `lid`) USING BTREE,
  INDEX `fk_lp_lx`(`lid`) USING BTREE,
  CONSTRAINT `fk_lp_lx` FOREIGN KEY (`lid`) REFERENCES `lx` (`lid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lp
-- ----------------------------
INSERT INTO `lp` VALUES ('10', 'j', '1010');
INSERT INTO `lp` VALUES ('11', 'k', '1011');
INSERT INTO `lp` VALUES ('1111', 'aaa', '1007');
INSERT INTO `lp` VALUES ('12', 'l', '1012');
INSERT INTO `lp` VALUES ('13', 'm', '1013');
INSERT INTO `lp` VALUES ('14', 'n', '1014');
INSERT INTO `lp` VALUES ('15', 'o', '1015');
INSERT INTO `lp` VALUES ('16', 'p', '1016');
INSERT INTO `lp` VALUES ('17', 'q', '1017');
INSERT INTO `lp` VALUES ('18', 'r', '1018');
INSERT INTO `lp` VALUES ('19', 's', '1019');
INSERT INTO `lp` VALUES ('2', 'b', '1002');
INSERT INTO `lp` VALUES ('20', 't', '1020');
INSERT INTO `lp` VALUES ('20000003', '张三', '1001');
INSERT INTO `lp` VALUES ('20010001', '小方', '1001');
INSERT INTO `lp` VALUES ('20020002', '小王', '1001');
INSERT INTO `lp` VALUES ('20030001', '李二四', '1008');
INSERT INTO `lp` VALUES ('201900000001', '王五', '1008');
INSERT INTO `lp` VALUES ('222', 'bb', '1007');
INSERT INTO `lp` VALUES ('3', 'c', '1003');
INSERT INTO `lp` VALUES ('4', 'd', '1004');
INSERT INTO `lp` VALUES ('5', 'e', '1005');
INSERT INTO `lp` VALUES ('6', 'ffff', '1006');
INSERT INTO `lp` VALUES ('9', 'i', '1009');

-- ----------------------------
-- Table structure for lx
-- ----------------------------
DROP TABLE IF EXISTS `lx`;
CREATE TABLE `lx`  (
  `lid` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `lname` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `ltype` varchar(3) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `lbtime` date NOT NULL,
  `letime` date NOT NULL,
  `lmoney` int NOT NULL,
  `laddress` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `uid` varchar(8) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `lperson` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`lid`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE,
  CONSTRAINT `lx_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of lx
-- ----------------------------
INSERT INTO `lx` VALUES ('1001', 'aaa', '国家级', '2019-08-12', '2020-08-15', 20000, '20000003\\立项\\国家级\\aaa_265ba3e2-d4b1-4681-b6a1-88af72242ea1.txt', '20000003', '张三');
INSERT INTO `lx` VALUES ('1002', 'bbb', '省级', '2020-08-16', '2019-08-16', 10000, '20000002\\立项\\省级\\bbb_e07bf737-ed95-4862-bd71-6d57ef51feef.txt', '20000002', '小强');
INSERT INTO `lx` VALUES ('1003', 'ccc', '校级', '2022-03-10', '2023-03-10', 5000, '20000004\\立项\\校级\\ccc_910663bd-11f0-4f37-9a64-684b0631e13b.txt', '20000004', '小李');
INSERT INTO `lx` VALUES ('1004', 'ddd', '省级', '2021-08-18', '2022-08-18', 10000, '20000003\\立项\\省级\\ddd_0622dc05-ed39-4358-917a-8d8792079541.txt', '20000003', '张三');
INSERT INTO `lx` VALUES ('1005', 'eee', '国家级', '2019-08-19', '2020-08-19', 20000, '20000005\\立项\\国家级\\eee_0c50992b-833f-4c3d-a0ac-4822adc83f38.txt', '20000005', '李四');
INSERT INTO `lx` VALUES ('1006', 'fff', '省级', '2018-08-20', '2019-08-20', 10000, '20000004\\立项\\省级\\fff_49a1e721-f333-4b6b-a1be-73aaf19af242.txt', '20000004', '小李');
INSERT INTO `lx` VALUES ('1007', 'ggg', '校级', '2019-08-21', '2020-08-21', 5000, '20000003\\立项\\校级\\ggg_b34d74d1-d015-40fc-8082-5c5126afaf49.txt', '20000003', '张三');
INSERT INTO `lx` VALUES ('1008', 'hhh', '省级', '2020-07-22', '2021-07-22', 10000, '20000001\\立项\\省级\\hhh_94a42bcd-63b3-479a-afb3-26ff45cb1c55.txt', '20000001', '小明');
INSERT INTO `lx` VALUES ('1009', 'iii', '省级', '2021-08-23', '2022-08-23', 5000, '20000002\\立项\\省级\\iii_fc9bd89f-7175-4820-9c11-e882837ad863.txt', '20000002', '小强');
INSERT INTO `lx` VALUES ('1010', 'jjj', '省级', '2019-08-24', '2020-08-24', 10000, '20000001\\立项\\省级\\jjj_0e006c9d-285f-4e7d-9dc4-6ac765337463.txt', '20000001', '小明');
INSERT INTO `lx` VALUES ('1011', 'kkk', '国家级', '2018-08-25', '2019-08-25', 20000, '20000003\\立项\\国家级\\kkk_97a91b0e-ee2e-4e79-bd8c-bc3f6d21545a.txt', '20000003', '张三');
INSERT INTO `lx` VALUES ('1012', 'lll', '省级', '2018-08-26', '2019-08-26', 5000, '20000001\\立项\\省级\\lll_8e4c6a93-b04c-48b2-9007-34f74958ecad.txt', '20000001', '小明');
INSERT INTO `lx` VALUES ('1013', 'mmm', '校级', '2022-02-27', '2023-02-27', 5000, '20000005\\立项\\校级\\mmm_2a413c56-9507-4c76-bdde-9c8453d18358.txt', '20000005', '李四');
INSERT INTO `lx` VALUES ('1014', 'nnn', '国家级', '2020-08-28', '2021-08-28', 20000, '20000002\\立项\\国家级\\nnn_340a662f-c810-4e3f-b2a3-99a72b845b28.txt', '20000002', '小强');
INSERT INTO `lx` VALUES ('1015', 'ooo', '省级', '2019-08-29', '2020-08-29', 10000, '20000001\\立项\\省级\\ooo_91b4b075-5fab-448b-b471-e5cea2c1551d.txt', '20000001', '小明');
INSERT INTO `lx` VALUES ('1016', 'ppp', '校级', '2021-08-30', '2022-08-30', 10000, '20000003\\立项\\校级\\ppp_eb862e68-7928-4d3b-b168-c7f477af4bf7.txt', '20000003', '张三');
INSERT INTO `lx` VALUES ('1017', 'qqq', '国家级', '2019-08-31', '2020-08-31', 20000, '20000005\\立项\\国家级\\qqq_a156e99c-a93d-4cb9-a9af-e94c9080c5b4.txt', '20000005', '李四');
INSERT INTO `lx` VALUES ('1018', 'rrr', '省级', '2022-02-01', '2023-02-01', 10000, '20000002\\立项\\省级\\rrr_c07923ef-dabf-4e67-a55d-b73e15346be8.txt', '20000002', '小强');
INSERT INTO `lx` VALUES ('1019', 'sss', '校级', '2019-09-02', '2020-09-02', 5000, '20000001\\立项\\校级\\sss_6048b5fd-5ba7-476a-b6fd-4df610589b04.txt', '20000001', '小明');
INSERT INTO `lx` VALUES ('1020', 'ttt', '省级', '2022-01-03', '2023-01-03', 10000, '20000003\\立项\\省级\\ttt_1a3f1748-43d5-478f-bb88-e43781f23dc4.txt', '20000003', '张三');

-- ----------------------------
-- Table structure for patent
-- ----------------------------
DROP TABLE IF EXISTS `patent`;
CREATE TABLE `patent`  (
  `pid` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `pname` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `pstate` varchar(2) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `ptime` date NOT NULL,
  `ppatentee` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `paddress` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `uid` varchar(8) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`pid`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE,
  CONSTRAINT `patent_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of patent
-- ----------------------------
INSERT INTO `patent` VALUES ('1001', 'a', '申请', '2022-06-09', '张三', '20000003\\专利\\申请\\a_43845c2b-47b4-4f92-80df-c1536edb324a.txt', '20000003');
INSERT INTO `patent` VALUES ('1002', 'b', '授权', '2021-06-10', '小强', '20000002\\专利\\授权\\b_b33163e9-6644-47aa-8394-c5252e53a3f5.txt', '20000002');
INSERT INTO `patent` VALUES ('1003', 'c', '授权', '2020-06-11', '小李', '20000004\\专利\\授权\\c_7efa15bb-7998-4bd8-a918-be33216b00ca.txt', '20000004');
INSERT INTO `patent` VALUES ('1004', 'd', '授权', '2019-06-12', '张三', '20000003\\专利\\授权\\d_fc0dd636-8687-46f9-ae03-9762cd6333f5.txt', '20000003');
INSERT INTO `patent` VALUES ('1005', 'e', '申请', '2018-06-13', '李四', '20000005\\专利\\申请\\e_b9f74513-16d5-46c7-bc42-095df3a45dc1.txt', '20000005');
INSERT INTO `patent` VALUES ('1006', 'f', '授权', '2020-06-14', '小李', '20000004\\专利\\授权\\f_b120c28f-a1b1-47b4-aa5d-f01ec1357d78.txt', '20000004');
INSERT INTO `patent` VALUES ('1007', 'g', '申请', '2020-06-15', '张三', '20000003\\专利\\申请\\g_a34e7811-db3f-4a86-81bb-a89c3edf0470.txt', '20000003');
INSERT INTO `patent` VALUES ('1008', 'h', '授权', '2022-06-16', '小明', '20000001\\专利\\授权\\h_54f906e3-d1e2-440b-b80e-0185af0e74b3.txt', '20000001');
INSERT INTO `patent` VALUES ('1009', 'i', '申请', '2021-06-17', '小强', '20000002\\专利\\申请\\i_60cee58a-02bb-4962-b0da-ffa8890fe5c3.txt', '20000002');
INSERT INTO `patent` VALUES ('1010', 'j', '授权', '2018-06-18', '小明', '20000001\\专利\\授权\\j_944d6c52-3185-42c9-a3d7-4b020e23ff67.txt', '20000001');
INSERT INTO `patent` VALUES ('1011', 'k', '授权', '2020-06-19', '张三', '20000003\\专利\\授权\\k_9b363890-4515-4033-9584-e92eb2baaa83.txt', '20000003');
INSERT INTO `patent` VALUES ('1012', 'l', '授权', '2020-06-20', '小明', '20000001\\专利\\授权\\l_5566bc18-ae7b-4a7e-a604-18da83b95ab1.txt', '20000001');
INSERT INTO `patent` VALUES ('1013', 'm', '申请', '2020-06-21', '李四', '20000005\\专利\\申请\\m_bf177542-a536-4158-a8fe-677e4f225e38.txt', '20000005');
INSERT INTO `patent` VALUES ('1014', 'n', '授权', '2021-06-22', '小强', '20000002\\专利\\授权\\n_52a7dd27-90a3-43a7-91e9-2b51b7ee5c94.txt', '20000002');
INSERT INTO `patent` VALUES ('1015', 'o', '申请', '2020-06-23', '小明', '20000001\\专利\\申请\\o_a62406cf-7b61-4e60-8bb0-899ae1143c35.txt', '20000001');
INSERT INTO `patent` VALUES ('1016', 'p', '申请', '2022-06-24', '张三', '20000003\\专利\\申请\\p_80be8135-164c-420b-8a4e-e38818278400.txt', '20000003');
INSERT INTO `patent` VALUES ('1017', 'q', '申请', '2019-06-25', '李四', '20000005\\专利\\申请\\q_7746ce13-f543-4712-b539-3a8c74aa36cf.txt', '20000005');
INSERT INTO `patent` VALUES ('1018', 'r', '申请', '2018-06-26', '小强', '20000002\\专利\\申请\\r_028a1416-21f4-42a5-8787-7a861457b4b9.txt', '20000002');
INSERT INTO `patent` VALUES ('1019', 's', '授权', '2020-06-27', '小明', '20000001\\专利\\授权\\s_8f6f6d72-3959-4e06-a9a0-fb7c8a5be849.txt', '20000001');
INSERT INTO `patent` VALUES ('1020', 't', '申请', '2022-06-28', '张三', '20000003\\专利\\申请\\t_f2f74c6f-c80b-4cd4-b25d-a109a3ce78c4.txt', '20000003');
INSERT INTO `patent` VALUES ('1021', 'u', '授权', '2022-04-01', 'c', '20000003\\专利\\授权\\u_3c0494fb-7117-42d5-9bbd-6f18b83a1743.txt', '20000003');

-- ----------------------------
-- Table structure for pp
-- ----------------------------
DROP TABLE IF EXISTS `pp`;
CREATE TABLE `pp`  (
  `ppid` varchar(12) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `ppname` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `pid` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ppid`, `pid`) USING BTREE,
  INDEX `fk_pp_patent`(`pid`) USING BTREE,
  CONSTRAINT `fk_pp_patent` FOREIGN KEY (`pid`) REFERENCES `patent` (`pid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pp
-- ----------------------------
INSERT INTO `pp` VALUES ('10', 'j', '1010');
INSERT INTO `pp` VALUES ('11', 'k', '1011');
INSERT INTO `pp` VALUES ('12', 'l', '1012');
INSERT INTO `pp` VALUES ('13', 'm', '1013');
INSERT INTO `pp` VALUES ('14', 'n', '1014');
INSERT INTO `pp` VALUES ('15', 'o', '1015');
INSERT INTO `pp` VALUES ('16', 'p', '1016');
INSERT INTO `pp` VALUES ('17', 'q', '1017');
INSERT INTO `pp` VALUES ('18', 'r', '1018');
INSERT INTO `pp` VALUES ('19', 's', '1019');
INSERT INTO `pp` VALUES ('2', 'b', '1002');
INSERT INTO `pp` VALUES ('20', 't', '1020');
INSERT INTO `pp` VALUES ('20000003', '张三', '1001');
INSERT INTO `pp` VALUES ('20010001', '小方', '1001');
INSERT INTO `pp` VALUES ('20020002', '小王', '1001');
INSERT INTO `pp` VALUES ('21', 'u', '1021');
INSERT INTO `pp` VALUES ('3', 'c', '1002');
INSERT INTO `pp` VALUES ('3', 'c', '1003');
INSERT INTO `pp` VALUES ('3', 'c', '1021');
INSERT INTO `pp` VALUES ('4', 'd', '1004');
INSERT INTO `pp` VALUES ('5', 'e', '1005');
INSERT INTO `pp` VALUES ('6', 'f', '1006');
INSERT INTO `pp` VALUES ('7', 'g', '1007');
INSERT INTO `pp` VALUES ('8', 'h', '1008');
INSERT INTO `pp` VALUES ('9', 'i', '1009');

-- ----------------------------
-- Table structure for swork
-- ----------------------------
DROP TABLE IF EXISTS `swork`;
CREATE TABLE `swork`  (
  `swid` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `swname` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `swperson` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `swaddress` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `swtime` date NOT NULL,
  `uid` varchar(8) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`swid`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE,
  CONSTRAINT `swork_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of swork
-- ----------------------------
INSERT INTO `swork` VALUES ('wrj0000001', '无人机目标识别系统', '张三', '20000003\\著作\\软件著作\\808080_304ede54-c807-4188-90cd-61050fb7e5e0.txt', '2019-04-20', '20000003');
INSERT INTO `swork` VALUES ('wrj0000002', '111', '小明', '20000001\\著作\\软件著作\\111_451291c8-a3ec-4327-bfd8-bad1c436ead9.txt', '2018-04-20', '20000001');
INSERT INTO `swork` VALUES ('wrj0000003', '222', '李四', '20000005\\著作\\软件著作\\676767_212c1858-bcba-4675-a6b0-6cf8cb5efa62.txt', '2018-04-20', '20000005');
INSERT INTO `swork` VALUES ('wrj0000004', '333', '小强', '20000002\\著作\\软件著作\\686868_62e7c115-c8ba-4585-b747-e2d457495f6a.txt', '2022-04-20', '20000002');
INSERT INTO `swork` VALUES ('wrj0000005', '444', '小明', '20000001\\著作\\软件著作\\696969_f484624a-b550-468b-88e5-b767015e6bd2.txt', '2021-04-20', '20000001');
INSERT INTO `swork` VALUES ('wrj0000006', '555', '张三', '20000003\\著作\\软件著作\\707070_01dd9481-85ae-4fc5-a03f-9a99a31a36c2.txt', '2020-04-20', '20000003');
INSERT INTO `swork` VALUES ('wrj0000007', '666', '李四', '20000005\\著作\\软件著作\\666_e8e1e279-66c5-4daa-995f-9ec46027f5c4.txt', '2021-04-20', '20000005');
INSERT INTO `swork` VALUES ('wrj0000008', '777', '小强', '20000002\\著作\\软件著作\\717171_9c3aae64-2a5e-4754-8612-b88f900d1d1b.txt', '2022-04-20', '20000002');
INSERT INTO `swork` VALUES ('wrj0000009', '888', '小明', '20000001\\著作\\软件著作\\727272_2a9424ae-fa15-49dc-8642-e422c5662234.txt', '2018-04-20', '20000001');
INSERT INTO `swork` VALUES ('wrj0000010', '999', '张三', '20000003\\著作\\软件著作\\757575_3fa4f9b7-866e-4f1e-bbcb-a3b708454f10.txt', '2022-04-20', '20000003');

-- ----------------------------
-- Table structure for thesis
-- ----------------------------
DROP TABLE IF EXISTS `thesis`;
CREATE TABLE `thesis`  (
  `tid` int NOT NULL AUTO_INCREMENT,
  `tname` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `tperiodical` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `ttime` date NOT NULL,
  `ttype` varchar(6) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `taddress` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `uid` varchar(8) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`tid`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE,
  CONSTRAINT `thesis_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of thesis
-- ----------------------------
INSERT INTO `thesis` VALUES (1, 'aaaa', 'a期刊', '2022-12-05', '一区', '20000003\\论文\\一区\\aaaa_5cb51bb9-25d0-4f8f-b7ff-a4c20cc357c3.txt', '20000003');
INSERT INTO `thesis` VALUES (2, 'bbbb', 'b期刊', '2018-12-06', '二区', '20000002\\论文\\二区\\bbbb_b7f50939-5a42-48d7-9b03-5711c4f6fba6.txt', '20000002');
INSERT INTO `thesis` VALUES (3, 'cccc', 'c杂志', '2022-12-07', '三区,四区', '20000004\\论文\\三区,四区\\cccc_35076bcf-d583-487a-bf27-27cd81f2f046.txt', '20000004');
INSERT INTO `thesis` VALUES (4, 'dddd', 'd期刊', '2019-12-08', '公开区', '20000003\\论文\\公开区\\dddd_19a096df-943f-428a-910d-7fea2a51c271.txt', '20000003');
INSERT INTO `thesis` VALUES (5, 'eeee', 'e杂志', '2022-12-09', '核心区', '20000005\\论文\\核心区\\eeee_14dc7d4c-2fa6-4356-87e5-6fb8a67a8fa6.txt', '20000005');
INSERT INTO `thesis` VALUES (6, 'ffff', 'f期刊', '2020-12-10', 'IE会议论文', '20000004\\论文\\IE会议论文\\ffff_c4d3a175-99a1-4389-8d92-1be9d107bcfb.txt', '20000004');
INSERT INTO `thesis` VALUES (7, 'gggg', 'g杂志', '2021-12-11', '一区', '20000003\\论文\\一区\\gggg_775baa58-272c-498e-958e-042f064eaa96.txt', '20000003');
INSERT INTO `thesis` VALUES (8, 'hhhh', 'h期刊', '2018-12-12', '二区', '20000001\\论文\\二区\\hhhh_3f4a258a-c22e-44df-940e-046d69548a28.txt', '20000001');
INSERT INTO `thesis` VALUES (9, 'iiii', 'i杂志', '2020-12-13', '三区,四区', '20000002\\论文\\三区,四区\\iiii_291d78f0-9fb3-4960-870b-061728c1d5ef.txt', '20000002');
INSERT INTO `thesis` VALUES (10, 'jjjj', 'j杂志', '2019-12-14', '公开区', '20000001\\论文\\公开区\\jjjj_1f57d927-5393-440b-bcf4-122c570d0a56.txt', '20000001');
INSERT INTO `thesis` VALUES (11, 'kkkk', 'k期刊', '2021-12-15', '核心区', '20000003\\论文\\核心区\\kkkk_86bfe3d0-da53-4143-82e9-450b03502023.txt', '20000003');
INSERT INTO `thesis` VALUES (12, 'llll', 'l杂志', '2020-12-16', 'IE会议论文', '20000001\\论文\\IE会议论文\\llll_55f09e80-35d5-478c-9d08-ad1d6f3e51aa.txt', '20000001');
INSERT INTO `thesis` VALUES (13, 'mmmm', 'm期刊', '2021-12-17', '一区', '20000005\\论文\\一区\\mmmm_9b3eeb3a-ae70-46dc-a861-ec6fa55a9514.txt', '20000005');
INSERT INTO `thesis` VALUES (14, 'nnnn', 'n杂志', '2018-12-18', '二区', '20000002\\论文\\二区\\nnnn_23142f79-4dab-4746-a524-18b0e6458802.txt', '20000002');
INSERT INTO `thesis` VALUES (15, 'oooo', 'o杂志', '2019-12-19', '三区,四区', '20000001\\论文\\三区,四区\\oooo_ad366698-3bbe-420f-ae06-0fd8c5fb125e.txt', '20000001');
INSERT INTO `thesis` VALUES (16, 'pppp', 'p期刊', '2022-12-20', '公开区', '20000003\\论文\\公开区\\pppp_1cd6e122-261a-430a-8130-75baefbee70c.txt', '20000003');
INSERT INTO `thesis` VALUES (17, 'qqqq', 'q期刊', '2019-12-21', '核心区', '20000005\\论文\\核心区\\qqqq_fb2580fa-35fe-4ec0-bc1e-bfaa30880c3b.txt', '20000005');
INSERT INTO `thesis` VALUES (18, 'rrrr', 'r期刊', '2018-12-22', 'IE会议论文', '20000002\\论文\\IE会议论文\\rrrr_98ede4ef-ff19-409b-b628-9af74a062da3.txt', '20000002');
INSERT INTO `thesis` VALUES (19, 'ssss', 's杂志', '2020-12-23', '二区', '20000001\\论文\\二区\\ssss_9dc81b3d-5dc8-4557-b821-af6cb6e74541.txt', '20000001');
INSERT INTO `thesis` VALUES (20, 'tttt', 't杂志', '2018-12-24', '核心区', '20000003\\论文\\核心区\\tttt_efbba0ee-cfe0-47d7-a86e-ba0bfed8d2a0.txt', '20000003');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `uid` varchar(8) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `upwd` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT 'cqwusri',
  `uname` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `utype` varchar(5) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `utel` varchar(11) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`uid`, `utype`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('20000001', 'cqwusri', '小明', 'user', '13500000001');
INSERT INTO `users` VALUES ('20000002', 'cqwusri', '小强', 'user', '13500000002');
INSERT INTO `users` VALUES ('20000003', '1234', '张三', 'admin', '13500000003');
INSERT INTO `users` VALUES ('20000003', '1234', '张三', 'user', '13500000003');
INSERT INTO `users` VALUES ('20000004', 'cqwusri', '小李', 'user', '13500000004');
INSERT INTO `users` VALUES ('20000005', 'cqwusri', '李四', 'admin', '13500000005');
INSERT INTO `users` VALUES ('20000006', 'cqwusri', '老六', 'user', '13500000006');

SET FOREIGN_KEY_CHECKS = 1;

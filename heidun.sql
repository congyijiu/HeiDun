/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : heidun

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 19/06/2023 14:14:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for exams
-- ----------------------------
DROP TABLE IF EXISTS `exams`;
CREATE TABLE `exams`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '考试id',
  `subject` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '考试名称',
  `start_time` datetime NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '结束时间',
  `num_questions` int NOT NULL COMMENT '题目数量',
  `status` int NULL DEFAULT NULL COMMENT '考试状态：0.未开始，1.已结束',
  `result` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '考试结果',
  `score` int NULL DEFAULT NULL COMMENT '考试分数',
  `user_id` int NOT NULL COMMENT '用户id',
  `type` int NOT NULL COMMENT '0.考试，1.模拟1，2.模拟2，3.模拟3',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of exams
-- ----------------------------
INSERT INTO `exams` VALUES (1, '123', '2023-06-02 00:00:00', NULL, 15, 0, NULL, NULL, 1, 0);
INSERT INTO `exams` VALUES (2, NULL, NULL, NULL, 15, NULL, NULL, NULL, 1, 0);
INSERT INTO `exams` VALUES (3, NULL, NULL, NULL, 15, NULL, NULL, NULL, 1, 0);
INSERT INTO `exams` VALUES (4, NULL, '2023-06-02 00:00:00', NULL, 15, NULL, NULL, NULL, 1, 0);

-- ----------------------------
-- Table structure for questions
-- ----------------------------
DROP TABLE IF EXISTS `questions`;
CREATE TABLE `questions`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '题目id',
  `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '题目详情',
  `options` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '题目选项',
  `answer` int NOT NULL COMMENT '答案:0:A,1:B,2:C,3:D',
  `explanation` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '答案解析',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of questions
-- ----------------------------
INSERT INTO `questions` VALUES (1, '1 + 1 等于几？', 'A. 1, B. 2, C. 3, D. 4', 1, '1 加 1 等于 2。');
INSERT INTO `questions` VALUES (2, '以下哪个是地球上最大的洲？', 'A. 亚洲, B. 非洲, C. 欧洲, D. 北美洲', 0, '亚洲是地球上最大的洲，面积约为 44,579,000 平方公里。');
INSERT INTO `questions` VALUES (3, '谁是《哈利·波特》系列小说的作者？', 'A. J.K.罗琳, B. J.R.R.托尔金, C. C.S.刘易斯, D. J.D.塞林格', 0, 'J.K.罗琳是《哈利·波特》系列小说的作者。');
INSERT INTO `questions` VALUES (4, '水的化学式是什么？', 'A. H2O, B. CO2, C. O2, D. NaCl', 0, '水的化学式是 H2O。');
INSERT INTO `questions` VALUES (5, '哪个是全球最高的山峰？', 'A. 珠穆朗玛峰, B. 喜马拉雅山, C. 南迦帕尔巴特峰, D. 乞力马扎罗山', 1, '喜马拉雅山是全球最高的山峰，包括珠穆朗玛峰。');
INSERT INTO `questions` VALUES (6, '世界上最长的河流是哪条？', 'A. 尼罗河, B. 亚马逊河, C. 长江, D.密西西比河', 0, '尼罗河是世界上最长的河流，全长约6,650公里。');
INSERT INTO `questions` VALUES (7, '什么是最小的鸟类？', 'A. 蜂鸟, B. 麻雀, C. 企鹅, D. 信天翁', 0, '蜂鸟是最小的鸟类，体长约为5-9厘米。');
INSERT INTO `questions` VALUES (8, '哪个是世界上最大的大陆？', 'A. 亚洲, B. 非洲, C. 欧洲, D. 南美洲', 0, '亚洲是世界上最大的大陆，占地面积约为44,579,000平方公里。');
INSERT INTO `questions` VALUES (9, '以下哪个是蛇类？', 'A. 鳄鱼, B. 鲨鱼, C. 蜥蜴, D. 蟒蛇', 3, '蟒蛇是蛇类的一种，属于巨蟒科。');
INSERT INTO `questions` VALUES (10, '哪个是法国的首都？', 'A. 伦敦, B. 巴黎, C. 柏林, D. 雅典', 1, '法国的首都是巴黎。');
INSERT INTO `questions` VALUES (11, '以下哪个是最大的行星？', 'A. 地球, B. 木星, C. 火星, D. 水星', 1, '木星是太阳系中最大的行星，体积约为其他行星总和的1.3倍。');
INSERT INTO `questions` VALUES (12, '哪个是世界上最大的瀑布？', 'A. 北美尼亚加拉瀑布, B. 南美伊瓜苏瀑布, C. 非洲维多利亚瀑布, D. 亚洲黄果树瀑布', 2, '非洲的维多利亚瀑布是世界上最大的瀑布，宽度约为1,708米，高度约为108米。');
INSERT INTO `questions` VALUES (13, '下面哪个国家拥有最多的人口？', 'A. 中国, B. 印度, C. 美国, D. 印度尼西亚', 1, '印度是世界上人口最多的国家，截至2021年，人口超过13亿。');
INSERT INTO `questions` VALUES (14, '哪个是最硬的天然矿物？', 'A. 金刚石, B. 石英, C. 石膏, D. 钾长石', 0, '金刚石是最硬的天然矿物，它由纯碳构成。');
INSERT INTO `questions` VALUES (15, '以下哪个是太阳系中最小的行星？', 'A. 水星, B. 金星, C. 地球, D. 火星', 0, '水星是太阳系中最小的行星，直径约为4,879公里。');
INSERT INTO `questions` VALUES (16, '以下哪个不属于五大洲之一？', 'A. 南极洲, B. 欧洲, C. 大洋洲, D. 加勒比海地区', 3, '加勒比海地区不是五大洲之一，它是一个海洋地区。');
INSERT INTO `questions` VALUES (17, '世界上最长的河流是哪条？', 'A. 尼罗河, B. 亚马逊河, C. 长江, D.密西西比河', 0, '尼罗河是世界上最长的河流，全长约6,650公里。');
INSERT INTO `questions` VALUES (18, '以下哪个是国际象棋中的棋子？', 'A. 王, B. 车, C. 马, D. 鹰', 1, '车是国际象棋中的棋子，它具有水平和垂直移动的能力。');
INSERT INTO `questions` VALUES (19, '太阳系中有几颗行星？', 'A. 8, B. 9, C. 10, D. 7', 0, '太阳系中有8颗行星，包括水星、金星、地球、火星、木星、土星、天王星和海王星。');
INSERT INTO `questions` VALUES (20, '以下哪个是美国的首都？', 'A. 华盛顿特区, B. 纽约, C. 洛杉矶, D. 芝加哥', 0, '华盛顿特区是美国的首都。');

-- ----------------------------
-- Table structure for user_exams
-- ----------------------------
DROP TABLE IF EXISTS `user_exams`;
CREATE TABLE `user_exams`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `question_id` int NOT NULL COMMENT '题目id',
  `exam_id` int NOT NULL COMMENT '考试id',
  `correct` int NULL DEFAULT NULL COMMENT '正确情况',
  `selected` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 75 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_exams
-- ----------------------------
INSERT INTO `user_exams` VALUES (61, 5, 1, NULL, NULL);
INSERT INTO `user_exams` VALUES (62, 6, 1, NULL, NULL);
INSERT INTO `user_exams` VALUES (63, 13, 1, NULL, NULL);
INSERT INTO `user_exams` VALUES (64, 11, 1, NULL, NULL);
INSERT INTO `user_exams` VALUES (65, 10, 1, NULL, NULL);
INSERT INTO `user_exams` VALUES (66, 3, 1, NULL, NULL);
INSERT INTO `user_exams` VALUES (67, 12, 1, NULL, NULL);
INSERT INTO `user_exams` VALUES (68, 9, 1, NULL, NULL);
INSERT INTO `user_exams` VALUES (69, 1, 1, NULL, NULL);
INSERT INTO `user_exams` VALUES (70, 7, 1, NULL, NULL);
INSERT INTO `user_exams` VALUES (71, 17, 1, NULL, NULL);
INSERT INTO `user_exams` VALUES (72, 4, 1, NULL, NULL);
INSERT INTO `user_exams` VALUES (73, 8, 1, NULL, NULL);
INSERT INTO `user_exams` VALUES (74, 15, 1, NULL, NULL);
INSERT INTO `user_exams` VALUES (75, 19, 1, NULL, NULL);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话号码',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `idcard` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '身份证号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, '从一九', 'e10adc3949ba59abbe56e057f20f883e', '123', '321', '123');

SET FOREIGN_KEY_CHECKS = 1;

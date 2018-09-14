/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : concentrate_tv

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2018-04-11 22:06:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `twitter_id` int(11) DEFAULT NULL COMMENT '动态id',
  `content` varchar(50) DEFAULT NULL COMMENT '评论内容',
  `comment_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('00000000001', '1', '9', null, '2018-03-18 14:19:56');
INSERT INTO `comment` VALUES ('00000000002', '1', '9', '', '2018-03-18 14:20:37');
INSERT INTO `comment` VALUES ('00000000003', '1', '9', '', '2018-03-18 14:29:06');
INSERT INTO `comment` VALUES ('00000000004', '1', '9', '', '2018-03-18 14:32:33');
INSERT INTO `comment` VALUES ('00000000005', '1', '9', '', '2018-04-01 11:31:32');
INSERT INTO `comment` VALUES ('00000000006', '1', '9', 'wqeq', '2018-04-01 11:34:21');
INSERT INTO `comment` VALUES ('00000000007', '1', '7', '456', '2018-04-01 11:34:35');
INSERT INTO `comment` VALUES ('00000000008', '1', '9', '46', '2018-04-01 12:23:11');
INSERT INTO `comment` VALUES ('00000000009', '1', '9', '46', '2018-04-01 12:23:13');
INSERT INTO `comment` VALUES ('00000000010', '1', '8', '好的', '2018-04-01 12:37:13');
INSERT INTO `comment` VALUES ('00000000011', '1', '3', '我也爱你', '2018-04-01 14:16:14');

-- ----------------------------
-- Table structure for `hit_record`
-- ----------------------------
DROP TABLE IF EXISTS `hit_record`;
CREATE TABLE `hit_record` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `twitter_id` int(11) DEFAULT NULL COMMENT '说说id',
  `hit_date` datetime DEFAULT NULL COMMENT '点赞（点踩）时间',
  `is_like` int(11) DEFAULT NULL COMMENT '点赞或踩（0：为操作，1：点踩，2：点赞）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `hit_onlyone` (`user_id`,`twitter_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hit_record
-- ----------------------------
INSERT INTO `hit_record` VALUES ('00000000001', '1', '1', '2018-03-06 22:48:32', '1');
INSERT INTO `hit_record` VALUES ('00000000002', '1', '2', '2018-03-06 22:48:46', '1');
INSERT INTO `hit_record` VALUES ('00000000003', '2', '5', '2018-03-07 22:34:40', '2');
INSERT INTO `hit_record` VALUES ('00000000004', '2', '4', '2018-03-07 22:34:41', '2');
INSERT INTO `hit_record` VALUES ('00000000005', '1', '6', '2018-03-07 23:10:44', '1');
INSERT INTO `hit_record` VALUES ('00000000006', '1', '9', '2018-03-18 14:32:52', '2');
INSERT INTO `hit_record` VALUES ('00000000010', '1', '8', '2018-04-01 12:03:25', '1');
INSERT INTO `hit_record` VALUES ('00000000020', '1', '7', '2018-04-01 12:10:17', '2');
INSERT INTO `hit_record` VALUES ('00000000026', '1', '3', '2018-04-01 12:14:03', '2');

-- ----------------------------
-- Table structure for `live_impression`
-- ----------------------------
DROP TABLE IF EXISTS `live_impression`;
CREATE TABLE `live_impression` (
  `id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `room_id` int(11) DEFAULT NULL COMMENT '主播房间id',
  `label` varchar(20) DEFAULT NULL COMMENT '标签',
  `heat` int(11) unsigned zerofill DEFAULT NULL COMMENT '标签热度（只显示前三）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of live_impression
-- ----------------------------

-- ----------------------------
-- Table structure for `live_show`
-- ----------------------------
DROP TABLE IF EXISTS `live_show`;
CREATE TABLE `live_show` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `person_name` varchar(16) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '主播昵称',
  `pic_url` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '直播间隙图片',
  `type` varchar(60) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '直播类型',
  `live_title` varchar(60) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '直播标题',
  `show_num` int(60) DEFAULT '0' COMMENT '直播人数',
  `msg_channel` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '获取渠道',
  `live_url` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '直播地址（房间号）',
  `is_show` int(11) DEFAULT '0' COMMENT '是否在播（0：未直播、1：在播）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_channel` (`person_name`,`msg_channel`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for `live_type`
-- ----------------------------
DROP TABLE IF EXISTS `live_type`;
CREATE TABLE `live_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_pic` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '直播分类图片',
  `type_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '分类名称',
  `msg_channel` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '获取渠道',
  `heat` int(11) DEFAULT '0' COMMENT '类型热度',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_channel` (`type_name`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of live_type
-- ----------------------------
INSERT INTO `live_type` VALUES ('4', 'https://img2.zhanqi.tv/uploads/2016/09/gamespic-2016092818163772754.jpeg', '百变娱乐', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('5', 'https://img1.zhanqi.tv/uploads/2017/12/gamespic-2017121515565533267.jpeg', '绝地求生', '战旗直播', '30');
INSERT INTO `live_type` VALUES ('6', 'https://img2.zhanqi.tv/uploads/2016/09/gamespic-2016092818171928495.jpeg', '英雄联盟', '战旗直播', '29');
INSERT INTO `live_type` VALUES ('7', 'https://img1.zhanqi.tv/uploads/2016/09/gamespic-2016092818192238943.png', '王者荣耀', '战旗直播', '20');
INSERT INTO `live_type` VALUES ('8', 'https://img2.zhanqi.tv/uploads/2017/07/gamespic-2017071317352873128.jpeg', '狼人杀', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('9', 'https://img1.zhanqi.tv/uploads/2016/09/gamespic-2016092818184948774.jpeg', '守望先锋', '战旗直播', '10');
INSERT INTO `live_type` VALUES ('10', 'https://img2.zhanqi.tv/uploads/2016/09/gamespic-2016092818430605332.png', '梦三国2', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('11', 'https://img1.zhanqi.tv/uploads/2016/09/gamespic-2016092818220671806.png', '三国杀', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('12', 'https://img2.zhanqi.tv/uploads/2017/06/gamespic-2017061315341283154.jpeg', '主机游戏', '战旗直播', '10');
INSERT INTO `live_type` VALUES ('13', 'https://img2.zhanqi.tv/uploads/2016/09/gamespic-2016092818173848103.jpeg', '炉石传说', '战旗直播', '25');
INSERT INTO `live_type` VALUES ('14', 'https://img2.zhanqi.tv/uploads/2016/09/gamespic-2016092818233556640.jpeg', 'DNF', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('15', 'https://img2.zhanqi.tv/uploads/2017/11/gamespic-2017112216361276194.jpeg', '决战！平安京', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('16', 'https://img2.zhanqi.tv/uploads/2017/11/gamespic-2017110714523504961.jpeg', '吃鸡手游', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('17', 'https://img2.zhanqi.tv/uploads/2016/12/gamespic-2016120909561605837.png', '魔兽世界', '战旗直播', '10');
INSERT INTO `live_type` VALUES ('18', 'https://img1.zhanqi.tv/uploads/2016/09/gamespic-2016092818175757095.png', 'DOTA2', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('19', 'https://img2.zhanqi.tv/uploads/2016/09/gamespic-2016092818235539179.png', '射击游戏', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('20', 'https://img1.zhanqi.tv/uploads/2017/06/gamespic-2017060213092183574.jpeg', '暴雪经典', '战旗直播', '10');
INSERT INTO `live_type` VALUES ('21', 'https://img1.zhanqi.tv/uploads/2016/09/gamespic-2016092818245291369.png', '传奇', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('22', 'https://img1.zhanqi.tv/uploads/2016/09/gamespic-2016092818251932977.jpeg', '网游综合', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('23', 'https://img1.zhanqi.tv/uploads/2017/11/gamespic-2017110816090479027.jpeg', '光荣使命', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('24', 'https://img1.zhanqi.tv/uploads/2017/12/gamespic-2017121111431387512.jpeg', '非人学园', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('25', 'https://img2.zhanqi.tv/uploads/2017/03/gamespic-2017030817401439594.jpeg', '战争网游', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('26', 'https://img1.zhanqi.tv/uploads/2016/09/gamespic-2016092818390959075.png', '300英雄', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('27', 'https://img2.zhanqi.tv/uploads/2017/10/gamespic-2017102414113671465.jpeg', '终结者2: 审判日', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('28', 'https://img2.zhanqi.tv/uploads/2017/11/gamespic-2017110814373668264.png', '最终幻想14', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('29', 'https://img2.zhanqi.tv/uploads/2017/07/gamespic-2017072410510179059.jpeg', '忍者村大战2', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('30', 'https://img1.zhanqi.tv/uploads/2017/10/gamespic-2017101617253806544.jpeg', '荒野行动', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('31', 'https://img1.zhanqi.tv/uploads/2017/11/gamespic-2017112817005840231.jpeg', '第五人格', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('32', 'https://img2.zhanqi.tv/uploads/2017/11/gamespic-2017112916295793810.jpeg', '大话西游手游', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('33', 'https://img2.zhanqi.tv/uploads/2016/09/gamespic-2016092818230684411.png', '魔兽争霸3', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('34', 'https://img2.zhanqi.tv/uploads/2017/07/gamespic-2017071317560582579.jpeg', '饭局狼人杀', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('35', 'https://img1.zhanqi.tv/uploads/2017/04/gamespic-2017041417354628047.png', '幻想全明星', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('36', 'https://img1.zhanqi.tv/uploads/2017/11/gamespic-2017111319595587068.jpeg', '永恒之塔', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('37', 'https://img2.zhanqi.tv/uploads/2017/11/gamespic-2017110817394726366.jpeg', '群雄逐鹿2', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('38', 'https://img1.zhanqi.tv/uploads/2017/02/gamespic-2017022219075548738.jpeg', '本月手游玩什么', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('39', 'https://img1.zhanqi.tv/uploads/2017/06/gamespic-2017062715192264978.jpeg', '择天记', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('40', 'https://img1.zhanqi.tv/uploads/2016/09/gamespic-2016092818393759935.jpeg', '怀旧回忆', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('41', 'https://img2.zhanqi.tv/uploads/2017/10/gamespic-2017101115335907055.jpeg', '欢乐斗地主', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('42', 'https://img2.zhanqi.tv/uploads/2016/09/gamespic-2016092818401850151.jpeg', '格斗游戏', '战旗直播', '5');
INSERT INTO `live_type` VALUES ('43', 'https://img1.zhanqi.tv/uploads/2017/05/gamespic-2017051608222302524.jpeg', '天龙八部手游', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('44', 'https://img1.zhanqi.tv/uploads/2016/09/gamespic-2016092818403821107.jpeg', '风暴英雄', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('45', 'https://img2.zhanqi.tv/uploads/2017/06/gamespic-2017061618304398826.png', '传奇战记', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('46', 'https://img1.zhanqi.tv/uploads/2016/09/gamespic-2016092818423683712.png', '三国杀移动版', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('47', 'https://img2.zhanqi.tv/uploads/2016/09/gamespic-2016092818432641543.jpeg', '棋牌竞技', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('48', 'https://img1.zhanqi.tv/uploads/2016/09/gamespic-2016092818443532206.jpeg', '暗黑破坏神Ⅲ', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('49', 'https://img1.zhanqi.tv/uploads/2016/09/gamespic-2016092818441517144.jpeg', '剑网3', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('50', 'https://img2.zhanqi.tv/uploads/2016/09/gamespic-2016092818482257323.png', '坦克世界', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('51', 'https://img1.zhanqi.tv/uploads/2016/09/gamespic-2016092818484239854.jpeg', '星际争霸2', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('52', 'https://img2.zhanqi.tv/uploads/2017/03/gamespic-2017031518273485423.jpeg', '枪火游侠', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('53', 'https://img1.zhanqi.tv/uploads/2016/09/gamespic-2016092818592868840.jpeg', '冒险岛2', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('54', 'https://img1.zhanqi.tv/uploads/2016/09/gamespic-2016092818400328752.jpeg', '手机游戏', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('55', 'https://img2.zhanqi.tv/uploads/2016/09/gamespic-2016092818515108055.png', '球球大作战', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('56', 'https://img1.zhanqi.tv/uploads/2017/08/gamespic-2017080710102647574.jpeg', '影武者', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('57', 'https://img2.zhanqi.tv/uploads/2017/06/gamespic-2017060121065024117.jpeg', '欢乐球吃球', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('58', 'https://img1.zhanqi.tv/uploads/2017/08/gamespic-2017081416294152672.jpeg', '野蛮人大作战', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('59', 'https://img2.zhanqi.tv/uploads/2017/08/gamespic-2017083015432919980.jpeg', 'QQ飞车手游', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('60', 'https://img2.zhanqi.tv/uploads/2017/08/gamespic-2017080709064306540.jpeg', '最强NBA', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('61', 'https://img1.zhanqi.tv/uploads/2017/06/gamespic-2017060812075756315.jpeg', '侠客风云传', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('62', 'https://img1.zhanqi.tv/uploads/2017/08/gamespic-2017080417542229086.jpeg', '全民飞机大战', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('63', 'https://img2.zhanqi.tv/uploads/2017/06/gamespic-2017062008501126936.png', '炮炮大作战', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('64', 'https://img2.zhanqi.tv/uploads/2017/12/gamespic-2017122117564356757.jpeg', '绝地求生 全军出击', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('65', 'https://img1.zhanqi.tv/uploads/2016/09/gamespic-2016092819004846329.jpeg', 'CS:GO', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('66', 'https://img2.zhanqi.tv/uploads/2016/09/gamespic-2016092818574442279.jpeg', '战舰世界', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('67', 'https://img2.zhanqi.tv/uploads/2017/07/gamespic-2017071716304072239.jpeg', '我的世界', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('68', 'https://img1.zhanqi.tv/uploads/2017/12/gamespic-2017121415023480217.jpeg', '神舞幻想', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('69', 'https://img1.zhanqi.tv/uploads/2016/09/gamespic-2016092818525525060.jpeg', 'Lyingman', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('70', 'https://img2.zhanqi.tv/uploads/2016/09/gamespic-2016092818491470789.png', '皇室战争', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('71', 'https://img2.zhanqi.tv/uploads/2017/03/gamespic-2017030609411345042.jpeg', '装甲战争', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('72', 'https://img1.zhanqi.tv/uploads/2017/03/gamespic-2017030912112165363.png', '任天堂', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('73', 'https://img2.zhanqi.tv/uploads/2016/12/gamespic-2016122309374219087.jpeg', '创世战车', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('74', 'https://img2.zhanqi.tv/uploads/2016/09/gamespic-2016092818535052063.jpeg', '穿越火线', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('75', 'https://img1.zhanqi.tv/uploads/2016/09/gamespic-2016092818541377274.jpeg', '逆战', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('76', 'https://img1.zhanqi.tv/uploads/2017/04/gamespic-2017042515561176972.jpeg', '爆破组', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('77', 'https://img2.zhanqi.tv/uploads/2016/10/gamespic-2016101211221853950.png', '阴阳师', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('78', 'https://img1.zhanqi.tv/uploads/2016/10/gamespic-2016101410552597796.jpeg', '流放之路', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('79', 'https://img1.zhanqi.tv/uploads/2016/11/gamespic-2016110815293778823.png', '街篮', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('80', 'https://img2.zhanqi.tv/uploads/2017/08/gamespic-2017082212470974945.jpeg', '命运2', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('81', 'https://img2.zhanqi.tv/uploads/2017/09/gamespic-2017092211462416151.jpeg', '户外直播', '战旗直播', '15');
INSERT INTO `live_type` VALUES ('82', 'https://img2.zhanqi.tv/uploads/2016/09/gamespic-2016092818564401710.jpeg', '游戏放映室', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('83', 'https://img2.zhanqi.tv/uploads/2016/09/gamespic-2016092818570284099.jpeg', '三国杀英雄传', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('84', 'https://img2.zhanqi.tv/uploads/2017/12/gamespic-2017120420095745931.jpeg', '穿越火线手游', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('85', 'https://img2.zhanqi.tv/uploads/2016/09/gamespic-2016092818572462436.jpeg', '天涯明月刀', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('86', 'https://img2.zhanqi.tv/uploads/2016/09/gamespic-2016092818580027609.jpeg', '自由篮球', '战旗直播', '0');
INSERT INTO `live_type` VALUES ('87', 'https://img2.zhanqi.tv/uploads/2016/09/gamespic-2016092818162051250.jpeg', '旗妙梦工厂', '战旗直播', '0');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `nick_name` varchar(25) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户昵称',
  `password` varchar(25) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户密码',
  `phone` varchar(60) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户手机号',
  `email` varchar(60) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户邮箱',
  `registration_time` datetime DEFAULT NULL COMMENT '注册时间',
  `last_login` datetime DEFAULT NULL COMMENT '最后登入时间',
  `head_pic` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户头像',
  `status` int(11) DEFAULT '1' COMMENT '用户状态（0：封号、1：未封号）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone_channel` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'lethean', '123456', '15563866317', 'dzkwork@126.com', '2018-01-01 21:15:47', '2018-03-01 21:15:54', '1520431861910.jpg', '1');
INSERT INTO `user` VALUES ('2', '傻子', '123456', '15555555555', '1434745143@qq.com', null, null, '1520433119921.png', '1');
INSERT INTO `user` VALUES ('3', '一条狗', '123456', '16666666666', 'dzkwork@126.com', null, null, 'testHead.jpg', '1');

-- ----------------------------
-- Table structure for `user_follow`
-- ----------------------------
DROP TABLE IF EXISTS `user_follow`;
CREATE TABLE `user_follow` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `room_id` int(11) NOT NULL COMMENT '房间id',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '是否关注（1：关注，0：取消关注）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_channel` (`user_id`,`room_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `user_twitter`
-- ----------------------------
DROP TABLE IF EXISTS `user_twitter`;
CREATE TABLE `user_twitter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `feeling` varchar(255) DEFAULT NULL COMMENT '心情动态（说说）',
  `creat_date` datetime DEFAULT NULL COMMENT '发表时间',
  `delete_date` datetime DEFAULT NULL COMMENT '删除时间',
  `like_hit` int(10) unsigned zerofill DEFAULT '0000000000' COMMENT '被赞次数',
  `dislike_hit` int(10) unsigned zerofill DEFAULT '0000000000' COMMENT '被踩次数',
  `reply_hit` int(10) unsigned zerofill DEFAULT '0000000000' COMMENT '回复次数',
  `status` int(10) DEFAULT '0' COMMENT '0：未删除，1：已删除，2：已被封',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user_twitter
-- ----------------------------
INSERT INTO `user_twitter` VALUES ('1', '1', '末将于禁，原为曹家世代赴汤蹈火。', '2018-03-06 22:18:09', '2018-04-11 21:34:19', '0000000005', '0000000000', '0000000002', '1');
INSERT INTO `user_twitter` VALUES ('2', '1', '不戴紧箍，如何救你；带上紧箍，如何爱你', '2018-03-06 22:19:38', null, '0000000050', '0000000000', '0000000005', '0');
INSERT INTO `user_twitter` VALUES ('3', '1', '<p>這是一個簡單的測試，<span style=\"color: rgb(249, 150, 59); font-weight: bold;\">我愛你</span></p>', '2018-03-07 21:53:53', null, '0000000000', '0000000000', '0000000000', '0');
INSERT INTO `user_twitter` VALUES ('4', '2', '<p><span style=\"color: rgb(70, 172, 200);\">人各有命，上天注定，有人天生为王，有人落草为寇。脚下的路，如果不是你自己的选择，那么旅程的终点在哪，也没人知道。你会走到哪，会遇到谁，都不一定。&nbsp;&nbsp;</span>?<br></p>', '2018-03-07 22:28:08', null, '0000000000', '0000000000', '0000000000', '0');
INSERT INTO `user_twitter` VALUES ('5', '2', '<p>test</p>', '2018-03-07 22:32:10', null, '0000000000', '0000000000', '0000000000', '0');
INSERT INTO `user_twitter` VALUES ('6', '1', '<p>发表图片测试</p><p><img src=\"/upLoad/pic/1520435203207.jpg\" style=\"max-width:100%;\"></p>', '2018-03-07 23:06:45', null, '0000000000', '0000000000', '0000000000', '0');
INSERT INTO `user_twitter` VALUES ('7', '1', '<p><img src=\"/upLoad/pic/1520518200523.png\" style=\"max-width:100%;\">哪位同学解释一下这个图片的含义</p>', '2018-03-08 22:10:33', null, '0000000000', '0000000000', '0000000000', '0');
INSERT INTO `user_twitter` VALUES ('8', '1', '<p><span style=\"font-weight: bold; color: rgb(139, 170, 74);\">1221345646</span><img src=\"/upLoad/pic/1520951022303.png\" style=\"max-width: 100%;\"></p>', '2018-03-13 22:23:46', '2018-04-11 21:46:12', '0000000000', '0000000000', '0000000000', '1');
INSERT INTO `user_twitter` VALUES ('9', '1', '<p><img src=\"/upLoad/pic/1520951035103.jpg\" style=\"max-width:100%;\"><br></p>', '2018-03-13 22:23:56', null, '0000000000', '0000000000', '0000000000', '0');

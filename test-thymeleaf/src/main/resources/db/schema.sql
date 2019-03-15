DROP TABLE IF EXISTS blog_post;

CREATE TABLE `blog_post` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `title` varchar(100) NOT NULL COMMENT '标题',
  `body` varchar(255) NOT NULL COMMENT '内容',
  `tags` varchar(32) NOT NULL COMMENT '创建人',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
  );
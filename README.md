## 社区

## 资料
[spring 文档](spring.io/guides)
elasticsearch.cn/explore

## 工具
git
bootstrap

##sql语句
```sql
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account_id` varchar(100) DEFAULT NULL COMMENT '账户id',
  `name` varchar(50) DEFAULT NULL COMMENT '账户名',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户表';

```

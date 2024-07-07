-- 创建表
CREATE TABLE IF NOT EXISTS kaleido_scope (
    id bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    --
    title varchar(32) NOT NULL COMMENT '模块标题',
    title_icon varchar(32) DEFAULT NULL COMMENT '模块图标',
    --
    name varchar(32) NOT NULL COMMENT '对象名称',
    name_icon varchar(32) DEFAULT NULL COMMENT '对象图标',
    --
    tag varchar(32) NOT NULL COMMENT '环境名称',
    url varchar(200) NOT NULL COMMENT '链接',
    account varchar(64) DEFAULT NULL COMMENT '账号',
    password varchar(64) DEFAULT NULL COMMENT '密码',
    --
    priority int(10) DEFAULT NULL COMMENT '优先级',
    --
    created datetime default NULL COMMENT '创建时间',
    updated datetime default NULL COMMENT '更新时间',
    PRIMARY KEY (id)
) COMMENT='万花筒导航表';

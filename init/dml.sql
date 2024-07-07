-- 初始化数据
INSERT INTO kaleido_scope (title, title_icon, name, name_icon, tag, url, account, password, priority, created, updated)
values
('企业', 'icon-qiye', '邮箱', 'icon-youxiang', '正式', 'https://mail.qq.com', NULL, NULL, 1000, '2024-06-29 16:03:46.000', '2024-06-29 16:03:46.000'),
('企业', 'icon-qiye', 'Confluence', 'icon-confluence', '正式', 'https://www.atlassian.com/software/confluence', NULL, NULL, 1000, '2024-06-29 16:03:46.000', '2024-06-29 16:03:46.000'),
('企业', 'icon-qiye', '禅道', 'icon-chandao', '正式', 'https://www.zentao.net', NULL, NULL, 1000, '2024-06-29 16:03:46.000', '2024-06-29 16:03:46.000'),
--
('开发', 'icon-shujukaifajiaobenkaifa', 'GitLab', 'icon-gitlab', '正式', 'https://gitlab.com', NULL, NULL, 1000, '2024-06-29 16:03:46.000', '2024-06-29 16:03:46.000'),
('开发', 'icon-shujukaifajiaobenkaifa', 'Jenkins', 'icon-jenkins', '正式', 'https://www.jenkins.io', 'admin', 'admin123', 1000, '2024-06-29 16:03:46.000', '2024-06-29 16:03:46.000'),
('开发', 'icon-shujukaifajiaobenkaifa', 'YApi', 'icon-hexo', '正式', 'https://yapi.pro', 'dev', 'dev123', 1000, '2024-06-29 16:03:46.000', '2024-06-29 16:03:46.000'),
('开发', 'icon-shujukaifajiaobenkaifa', 'Yearning', 'icon-component', '正式', 'https://next.yearning.io', 'yourName', '123456', 1000, '2024-06-29 16:03:46.000', '2024-06-29 16:03:46.000'),
('开发', 'icon-shujukaifajiaobenkaifa', 'Jumpserver', 'icon-jumpserver', '正式', 'https://www.jumpserver.org', 'yourName', '123456', 1000, '2024-06-29 16:03:46.000', '2024-06-29 16:03:46.000'),
('开发', 'icon-shujukaifajiaobenkaifa', 'Nexus', 'icon-NexusRepo_Icon', '正式', 'https://your.nexus.com', NULL, NULL, 1000, '2024-06-29 16:03:46.000', '2024-06-29 16:03:46.000'),
('开发', 'icon-shujukaifajiaobenkaifa', 'Nacos', 'icon-a-nacos1', 'dev/test', 'https://nacos.io', 'nacos', 'nacos', 1000, '2024-06-29 16:03:46.000', '2024-06-29 16:03:46.000'),
('开发', 'icon-shujukaifajiaobenkaifa', 'Nacos', 'icon-a-nacos1', 'pre/prod', 'https://nacos.io/cloud/', NULL, NULL, 1000, '2024-06-29 16:03:46.000', '2024-06-29 16:03:46.000'),
('开发', 'icon-shujukaifajiaobenkaifa', 'RabbitMQ', 'icon-rabbitmq', 'dev/test', 'https://www.rabbitmq.com', 'mq-test', 'mq-test123', 1000, '2024-06-29 16:03:46.000', '2024-06-29 16:03:46.000'),
('开发', 'icon-shujukaifajiaobenkaifa', 'RabbitMQ', 'icon-rabbitmq', 'pre', 'https://www.rabbitmq.com/tutorials', 'mq-pre', 'mq-pre123', 1000, '2024-06-29 16:03:46.000', '2024-06-29 16:03:46.000'),
('开发', 'icon-shujukaifajiaobenkaifa', 'RabbitMQ', 'icon-rabbitmq', 'prod', 'https://www.rabbitmq.com/docs', NULL, NULL, 1000, '2024-06-29 16:03:46.000', '2024-06-29 16:03:46.000'),
('开发', 'icon-shujukaifajiaobenkaifa', 'XXL-JOB', 'icon-xxl-job', 'dev/test', 'https://www.xuxueli.com/xxl-job/', 'dev', 'dev123', 1000, '2024-06-29 16:03:46.000', '2024-06-29 16:03:46.000'),
('开发', 'icon-shujukaifajiaobenkaifa', 'XXL-JOB', 'icon-xxl-job', 'pre/prod', 'https://www.xuxueli.com/xxl-job/', NULL, NULL, 1000, '2024-06-29 16:03:46.000', '2024-06-29 16:03:46.000'),
('开发', 'icon-shujukaifajiaobenkaifa', 'Linux', 'icon-linux', 'dev200', '127.0.0.1', 'root', '123456', 2000, '2024-06-29 16:03:46.000', '2024-06-29 16:03:46.000'),
('开发', 'icon-shujukaifajiaobenkaifa', 'Linux', 'icon-linux', 'test201', '127.0.0.1', 'dev', '123456', 2000, '2024-06-29 16:03:46.000', '2024-06-29 16:03:46.000'),
('开发', 'icon-shujukaifajiaobenkaifa', 'Linux', 'icon-linux', 'test202', '127.0.0.1', 'dev', '123456', 2000, '2024-06-29 16:03:46.000', '2024-06-29 16:03:46.000'),
--
('数仓', 'icon-shucangweihu', 'Doris', 'icon-Doris', '正式', 'https://doris.apache.org', 'dev', '123456', 1000, '2024-06-29 16:03:46.000', '2024-06-29 16:03:46.000'),
('数仓', 'icon-shucangweihu', 'Metabase', 'icon-metabase', '正式', 'https://www.metabase.com', 'dev', '123456', 1000, '2024-06-29 16:03:46.000', '2024-06-29 16:03:46.000'),
--
('产品', 'icon-chanpin', '后台管理', 'icon-dingdanhoutai', 'dev', 'https://www.baidu.com', 'dev', '123456', 1000, '2024-06-29 16:03:46.000', '2024-06-29 16:03:46.000'),
('产品', 'icon-chanpin', '后台管理', 'icon-dingdanhoutai', 'uat', 'https://www.baidu.com', 'uat', '123456', 1000, '2024-06-29 16:03:46.000', '2024-06-29 16:03:46.000'),
('产品', 'icon-chanpin', '后台管理', 'icon-dingdanhoutai', 'prod', 'https://www.baidu.com', 'prod', '123456', 1000, '2024-06-29 16:03:46.000', '2024-06-29 16:03:46.000'),
('产品', 'icon-chanpin', '客服系统', 'icon-kefupingtai', 'test', 'https://www.baidu.com', 'test', '123456', 1000, '2024-06-29 16:03:46.000', '2024-06-29 16:03:46.000'),
('产品', 'icon-chanpin', '客服系统', 'icon-kefupingtai', 'pre', 'https://www.baidu.com', 'pre', '123456', 1000, '2024-06-29 16:03:46.000', '2024-06-29 16:03:46.000'),
('产品', 'icon-chanpin', '客服系统', 'icon-kefupingtai', 'prod', 'https://www.baidu.com', 'prod', '123456', 1000, '2024-06-29 16:03:46.000', '2024-06-29 16:03:46.000'),
('产品', 'icon-chanpin', '官网', 'icon-weiguanwang', 'test', 'https://www.baidu.com', NULL, NULL, 1000, '2024-06-29 16:03:46.000', '2024-06-29 16:03:46.000'),
('产品', 'icon-chanpin', '官网', 'icon-weiguanwang', 'pre', 'https://www.baidu.com', NULL, NULL, 1000, '2024-06-29 16:03:46.000', '2024-06-29 16:03:46.000'),
('产品', 'icon-chanpin', '官网', 'icon-weiguanwang', 'prod', 'https://www.baidu.com', NULL, NULL, 1000, '2024-06-29 16:03:46.000', '2024-06-29 16:03:46.000'),
('产品', 'icon-chanpin', 'xx管理', 'icon-ladiye', 'prod', 'https://www.baidu.com', 'admin', '123456', 1000, '2024-06-29 16:03:46.000', '2024-06-29 16:03:46.000'),
('产品', 'icon-chanpin', 'yy管理', 'icon-web', 'prod', 'https://www.baidu.com', 'admin', '123456', 1000, '2024-06-29 16:03:46.000', '2024-06-29 16:03:46.000'),
('产品', 'icon-chanpin', 'WordPress', 'icon-wordpress', 'test', 'https://www.baidu.com', 'admin', '123456', 1000, '2024-06-29 16:03:46.000', '2024-06-29 16:03:46.000'),
('产品', 'icon-chanpin', 'WordPress', 'icon-wordpress', 'prod', 'https://www.baidu.com', 'admin', '123456', 1000, '2024-06-29 16:03:46.000', '2024-06-29 16:03:46.000');



REPLACE INTO 'tableName' (`rule_code`, `business_type`, `grade`, `desc`, `seq`, `required`, `enable`, `rule_class_name`)
VALUES ('test_1', 'test', '违规', '字段number>3时，string不能为test', 3, true, true, 'com.ylzinfo.ruler.rule.StringRule')
     , ('test_2', 'test', '可疑', '字段number必须>0', 4, true, true, 'com.ylzinfo.ruler.rule.NumberRule')

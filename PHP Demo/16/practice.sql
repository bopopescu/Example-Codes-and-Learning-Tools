-- phpMyAdmin SQL Dump
-- version 2.7.0-pl1
-- http://www.phpmyadmin.net
-- 
-- �D��: localhost
-- �إߤ��: Jan 01, 2006, 11:08 PM
-- ���A������: 4.0.24
-- PHP ����: 4.3.11
-- 
-- ��Ʈw: `myweb`
-- 

-- --------------------------------------------------------

-- 
-- ��ƪ�榡�G `practice`
-- 

CREATE TABLE `practice` (
  `num` tinyint(3) unsigned NOT NULL auto_increment,
  `name` varchar(255) NOT NULL default '',
  `sex` enum('�k','�k') NOT NULL default '�k',
  `birthday` date NOT NULL default '0000-00-00',
  `salary` smallint(5) unsigned NOT NULL default '0',
  PRIMARY KEY  (`num`)
) TYPE=MyISAM PACK_KEYS=0 AUTO_INCREMENT=7 ;

-- 
-- �C�X�H�U��Ʈw���ƾڡG `practice`
-- 

INSERT INTO `practice` VALUES (1, 'tad', '�k', '1973-06-16', 35000);
INSERT INTO `practice` VALUES (2, 'apple', '�k', '1973-06-10', 25000);
INSERT INTO `practice` VALUES (3, 'tim', '�k', '1972-01-10', 50000);
INSERT INTO `practice` VALUES (4, 'huihui', '�k', '1980-03-07', 40000);
INSERT INTO `practice` VALUES (5, 'alice', '�k', '1976-12-01', 35000);
INSERT INTO `practice` VALUES (6, 'phebe', '�k', '1973-03-10', 60000);

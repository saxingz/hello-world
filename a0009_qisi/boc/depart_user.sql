/* ---------------------------------------------------- */
/*  Generated by Enterprise Architect Version 12.0 		*/
/*  Created On : 06-5��-2018 15:37:10 				*/
/*  DBMS       : MySql 						*/
/* ---------------------------------------------------- */

SET FOREIGN_KEY_CHECKS=0

/* Create Tables */

CREATE TABLE `depart_user`
(
	`id` BIGINT NOT NULL COMMENT '����',
	`depart_id` VARCHAR(50) NOT NULL COMMENT '����id',
	`user_id` VARCHAR(50) NOT NULL COMMENT '�û�id',
	CONSTRAINT `PK_depart_user` PRIMARY KEY (`id`)
) COMMENT='���� �û���ϵ��'
;

SET FOREIGN_KEY_CHECKS=1
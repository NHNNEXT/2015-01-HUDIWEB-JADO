-- Test Set

-- -----------------------------------------------------
-- Table `jado_dev`.`USER`
-- -----------------------------------------------------

-- 권한 종류
--		ROLE_EMAIL_NOT_VERIFIED_USER : 이메일 인증이 되지 않은 회원
--		ROLE_CUSTOMER : 이메일 인증이 되었고 구매자로 가입한 회원
--		ROLE_SELLER : 이메일 인증이 되었고 판매자로 가입한 회원
--		ROLE_DEVELOPER : 개발할 떄 사용할
		
-- 비밀번호 = 1
insert into USER VALUES('user', '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b','name','phone','address','2015-03-26 12:34:56','2015-03-26 12:34:56','T', 1);
insert into USER VALUES('customer', '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b','name','phone','address','2015-03-26 12:34:56','2015-03-26 12:34:56','T', 1);
insert into USER VALUES('seller', '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b','name','phone','address','2015-03-26 12:34:56','2015-03-26 12:34:56','T', 1);
insert into USER VALUES('developer', '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b','name','phone','address','2015-03-26 12:34:56','2015-03-26 12:34:56','T', 1);
insert into USER VALUES('erin314@naver.com', '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b','name','phone','address','2015-03-26 12:34:56','2015-03-26 12:34:56','T', 1);

insert into USER_ROLE VALUES('user', 'ROLE_EMAIL_NOT_VERIFIED_USER');
insert into USER_ROLE VALUES('customer', 'ROLE_CUSTOMER');
insert into USER_ROLE VALUES('seller', 'ROLE_SELLER');
insert into USER_ROLE VALUES('developer', 'ROLE_DEVELOPER');
insert into USER_ROLE VALUES('erin314@naver.com', 'ROLE_CUSTOMER');

insert into SHOP VALUES('testurl', 'HEY, JADO', '031-737-7979', default, default, 'footer', default, default, default, default);
--insert into SHOP VALUES('erin', 'HEY, JADO', '031-737-7979', default, default, 'footer', 2, default, default, default);
insert into SELLER VALUES('testurl', 'seller','우리','bankaccount');
--insert into SELLER VALUES('erin', 'erin314@naver.com','우리','bankaccount');

insert into BOARD VALUES(null, 'testurl', 'INFO');
--insert into BOARD VALUES(null, 'erin', 'INFO');

insert into ARTICLE VALUES( null, 1, '오픈 기념 이벤트', 'content', null);
insert into ARTICLE VALUES( null, 1, '봄 신상 할인 이벤트', 'content', null);
insert into ARTICLE VALUES( null, 1, '직원 모집', 'content', null);
insert into ARTICLE_COMMENT VALUES( 1, 'customer', null, 'contents');

--insert into ARTICLE VALUES( null, 2, '오픈 기념 이벤트', 'content', null);
--insert into ARTICLE VALUES( null, 2, '봄 신상 할인 이벤트', 'content', null);
--insert into ARTICLE VALUES( null, 2, '직원 모집', 'content', null);
--insert into ARTICLE_COMMENT VALUES( 4, 'customer', null, 'contents');

insert into CATEGORY VALUES(null, 'OUTER', 'testurl');
insert into CATEGORY VALUES(null, 'TOP', 'testurl');
insert into CATEGORY VALUES(null, 'DRESS', 'testurl');
insert into CATEGORY VALUES(null, 'PANTS', 'testurl');
insert into CATEGORY VALUES(null, 'SKIRT', 'testurl');
insert into CATEGORY VALUES(null, 'ACC', 'testurl');

--insert into CATEGORY VALUES(null, 'OUTER', 'erin');
--insert into CATEGORY VALUES(null, 'TOP', 'erin');
--insert into CATEGORY VALUES(null, 'DRESS', 'erin');
--insert into CATEGORY VALUES(null, 'PANTS', 'erin');
--insert into CATEGORY VALUES(null, 'SKIRT', 'erin');
--insert into CATEGORY VALUES(null, 'ACC', 'erin');

insert into PRODUCT VALUES(null, 2, 'Pleats-droped<br>Shift Dress', 14000, 100,'/userImg/product/img/12.gif','OPS2099/Color Block<br>Pleats-droped<br>Shift Dress', null);
insert into PRODUCT VALUES(null, 1, 'Pleats-droped<br>Shift Dress', 14000, 100,'/userImg/product/img/11.gif','OPS2099/Color Block<br>Pleats-droped<br>Shift Dress', null);
insert into PRODUCT VALUES(null, 3, 'Pleats-droped<br>Shift Dress', 14000, 100,'/userImg/product/img/10.gif','OPS2099/Color Block<br>Pleats-droped<br>Shift Dress', null);
insert into PRODUCT VALUES(null, 6, 'Pleats-droped<br>Shift Dress', 14000, 100,'/userImg/product/img/9.jpg','OPS2099/Color Block<br>Pleats-droped<br>Shift Dress', null);
insert into PRODUCT VALUES(null, 5, 'Pleats-droped<br>Shift Dress', 12000, 100,'/userImg/product/img/8.gif','OPS2099/Color Block<br>Pleats-droped<br>Shift Dress', null);
insert into PRODUCT VALUES(null, 2, 'Pleats-droped<br>Shift Dress', 15000, 100,'/userImg/product/img/7.gif','OPS2099/Color Block<br>Pleats-droped<br>Shift Dress', null);
insert into PRODUCT VALUES(null, 2, 'Pleats-droped<br>Shift Dress', 12000, 100,'/userImg/product/img/6.gif','OPS2099/Color Block<br>Pleats-droped<br>Shift Dress', null);
insert into PRODUCT VALUES(null, 2, 'Pleats-droped<br>Shift Dress', 14000, 100,'/userImg/product/img/5.gif','OPS2099/Color Block<br>Pleats-droped<br>Shift Dress', null);
insert into PRODUCT VALUES(null, 2, 'Pleats-droped<br>Shift Dress', 18000, 100,'/userImg/product/img/4.gif','OPS2099/Color Block<br>Pleats-droped<br>Shift Dress', null);
insert into PRODUCT VALUES(null, 4, 'Shift Dress', 10000, 100,'/userImg/product/img/3.gif','OPS2099/Color Block<br>Pleats-droped<br>Shift Dress', null);
insert into PRODUCT VALUES(null, 2, 'Shift Dress', 13000, 100,'/userImg/product/img/2.gif','OPS2099/Color Block<br>Pleats-droped<br>Shift Dress', null);
insert into PRODUCT VALUES(null, 3, 'Shift Dress', 14000, 100,'/userImg/product/img/1.gif', '대박 좋아 좋아좋아', null);
--insert into CART_has_PRODUCT values('testShopUrl', 'user', 1, '1');
--insert into PRODUCT VALUES(null, 7, 'Pleats-droped<br>Shift Dress', 14000, 100,'/userImg/product/img/9.jpg','OPS2099/Color Block<br>Pleats-droped<br>Shift Dress', null);
--insert into PRODUCT VALUES(null, 8, 'Pleats-droped<br>Shift Dress', 12000, 100,'/userImg/product/img/8.gif','OPS2099/Color Block<br>Pleats-droped<br>Shift Dress', null);
--insert into PRODUCT VALUES(null, 9, 'Pleats-droped<br>Shift Dress', 15000, 100,'/userImg/product/img/7.gif','OPS2099/Color Block<br>Pleats-droped<br>Shift Dress', null);
--insert into PRODUCT VALUES(null, 10, 'Pleats-droped<br>Shift Dress', 12000, 100,'/userImg/product/img/6.gif','OPS2099/Color Block<br>Pleats-droped<br>Shift Dress', null);
--insert into PRODUCT VALUES(null, 11, 'Pleats-droped<br>Shift Dress', 14000, 100,'/userImg/product/img/5.gif','OPS2099/Color Block<br>Pleats-droped<br>Shift Dress', null);
--insert into PRODUCT VALUES(null, 12, 'Pleats-droped<br>Shift Dress', 18000, 100,'/userImg/product/img/4.gif','OPS2099/Color Block<br>Pleats-droped<br>Shift Dress', null);
--insert into PRODUCT VALUES(null, 7, 'Shift Dress', 10000, 100,'/userImg/product/img/3.gif','OPS2099/Color Block<br>Pleats-droped<br>Shift Dress', null);
--insert into PRODUCT VALUES(null, 8, 'Shift Dress', 13000, 100,'/userImg/product/img/2.gif','OPS2099/Color Block<br>Pleats-droped<br>Shift Dress', null);
--insert into PRODUCT VALUES(null, 9, 'Shift Dress', 14000, 100,'/userImg/product/img/1.gif', '대박 좋아 좋아좋아', null);

insert into PAYMENT VALUES('testUrl', 'erin314@naver.com', 1, '국민', 32600, default);
insert into PAYMENT VALUES('testUrl', 'erin314@naver.com', 2, '국민', 67500, default);
insert into PAYMENT VALUES('testUrl', 'erin314@naver.com', 3, '국민', 50000, default);
insert into PAYMENT VALUES('testUrl', 'erin314@naver.com', 4, '국민', 34000, default);
insert into PAYMENT VALUES('testUrl', 'erin314@naver.com', 5, '국민', 12000, default);
insert into PAYMENT VALUES('testUrl', 'erin314@naver.com', 6, '국민', 29000, default);
insert into PAYMENT VALUES('testUrl', 'erin314@naver.com', 7, '국민', 22600, default);
insert into PAYMENT VALUES('testUrl', 'erin314@naver.com', 8, '국민', 77700, default);
insert into PAYMENT VALUES('testUrl', 'erin314@naver.com', 9, '국민', 25600, default);

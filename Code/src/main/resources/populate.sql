INSERT INTO `authority` VALUES (1,'Admin'),(2,'Customer');
INSERT INTO `category` VALUES (1,'2019-03-20 15:40:35','Foods'),(2,'2019-03-20 15:40:35','Grocery'),(3,'2019-03-20 15:40:35','Gifts');

INSERT INTO `item` VALUES (1,'2019-03-20 15:40:35','Very High Quality','Meet',28.00,0,1),(2,'2019-03-20 15:40:35','Original Salted crackers','Crackers',5.00,0,1);
INSERT INTO `item_category` VALUES (1,1),(2,1),(1,2),(2,2);

INSERT INTO `users` VALUES (1,'admin@mum.com',1,'Admin','user','$2a$10$w2rmCytG5dmN/3Lg2vKS4uboepeIkYyRjmhujeX65.XcTkre5M/qa','admin',1),(2,'Asaid@abc.com',1,'Ahmed','Said','$2a$10$hmJvmBhq4wrxOiCt97tFh.fMO.a1af2RnJHha8AJ7rIqOZbmxZazi','Asaid',2);

INSERT INTO `springarrival`.`orders`  VALUES ('1', '30', '1');

INSERT INTO `springarrival`.`orders_items`  VALUES ('1', '2', '1', '1');
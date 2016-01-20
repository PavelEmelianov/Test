INSERT INTO Role VALUES (default,'admin');
INSERT INTO Role VALUES (default,'user');

INSERT INTO User(id,login,password,email,firstName,lastName,birthDate,roleId) VALUES (default,'admin','admin','admin@mail.ru','Ivan','Ivanov','1990-10-10',1);
INSERT INTO User(id,login,password,email,firstName,lastName,birthDate,roleId) VALUES (default,'user','user','user@mail.ru','Petr','Petrov','1989-06-06',2);



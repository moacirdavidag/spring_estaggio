INSERT INTO tb_usuario (first_name, last_name, email, password) VALUES ('Moacir', 'David', 'moacir@gmail.com', '$2a$12$7.X0j7eLkcJ4H50tRVrIj.1ZMap7DSVsNC9xVkAGsqnTM57aWlCJW');
INSERT INTO tb_usuario (first_name, last_name, email, password) VALUES ('admin', 'admin', 'admin@gmail.com', 'admin');


INSERT INTO tb_role (authority) VALUES ('ROLE_OPERATOR');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_usuario_role (usuario_id, role_id) VALUES (1, 1);
INSERT INTO tb_usuario_role (usuario_id, role_id) VALUES (1, 2);
INSERT INTO tb_usuario_role (usuario_id, role_id) VALUES (2, 1);
INSERT INTO tb_usuario_role (usuario_id, role_id) VALUES (2, 2);

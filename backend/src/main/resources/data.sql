INSERT INTO tb_user(first_name, last_name, email, password, user_role) VALUES ('Renato', 'Marques','email@gmail.com', '173', 'ROLE_ADMIN');
INSERT INTO tb_user(first_name, last_name, email, password, user_role) VALUES ('Gabriel', 'Marques','email@gmail.com', '173', 'ROLE_TRIADOR');
INSERT INTO tb_user(first_name, last_name, email, password, user_role) VALUES ('Ryan', 'Marques','email@gmail.com', '173', 'ROLE_FINALIZADOR');
INSERT INTO tb_user(first_name, last_name, email, password, user_role) VALUES ('Gih', 'Marques','email@gmail.com', '173', 'ROLE_ADMIN');


INSERT INTO tb_process (name, created_at, description, process_status)  VALUES ('processo 01', TIMESTAMP WITH TIME ZONE '2020-01-21T21:59:19Z', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 'PENDENT');
INSERT INTO tb_process (name, created_at, description, process_status)  VALUES ('processo 02', TIMESTAMP WITH TIME ZONE '2020-02-21T20:59:19Z', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 'PENDENT');
INSERT INTO tb_process (name, created_at, description, process_status)  VALUES ('processo 03', TIMESTAMP WITH TIME ZONE '2020-12-17T20:59:19Z', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 'PENDENT');
INSERT INTO tb_process (name, created_at, description, process_status)  VALUES ('processo 04', TIMESTAMP WITH TIME ZONE '2020-07-14T20:59:19Z', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 'PENDENT');
INSERT INTO tb_process (name, created_at, description, process_status)  VALUES ('processo 05', TIMESTAMP WITH TIME ZONE '2020-08-05T20:59:19Z', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.','PENDENT');


INSERT INTO tb_feedback ( created_at, text_feedback, process_id)  VALUES (TIMESTAMP WITH TIME ZONE '2020-08-05T20:59:19Z', 'Lorem ipsum dolor sit amet, sed do eiusmod tempor incididunt ut labore et ', 2);
INSERT INTO tb_feedback ( created_at, text_feedback, process_id)  VALUES (TIMESTAMP WITH TIME ZONE '2020-08-05T20:59:19Z', 'Lorem ipsum dolor sit amet, sed do eiusmod tempor incididunt ut labore et ', 1);
INSERT INTO tb_feedback ( created_at, text_feedback, process_id)  VALUES (TIMESTAMP WITH TIME ZONE '2020-08-05T20:59:19Z', 'Lorem ipsum dolor sit amet, sed do eiusmod tempor incididunt ut labore et ', 3);

INSERT INTO tb_process_users ( Process_id, users_id)  VALUES (1,2);
INSERT INTO tb_process_users ( Process_id, users_id)  VALUES (2,3);
INSERT INTO tb_process_users ( Process_id, users_id)  VALUES (3,1);
INSERT INTO tb_process_users ( Process_id, users_id)  VALUES (4,4);
INSERT INTO tb_process_users ( Process_id, users_id)  VALUES (5,4);

INSERT INTO tb_feedback_users ( feedback_id, users_id)  VALUES (1,1);
INSERT INTO tb_feedback_users ( feedback_id, users_id)  VALUES (2,2);
INSERT INTO tb_feedback_users ( feedback_id, users_id)  VALUES (3,3);
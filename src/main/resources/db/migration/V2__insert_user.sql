INSERT INTO public.user(id, username, firstname, lastname, email, password, is_temp_password, role)
VALUES (nextVal('user_seq'), 'admin', 'admin', 'admin', 'admin@mail.com', '$2a$10$jKrfZ3jhp3UI1SsQgdWQjOwNfiTek9VaqPY37zxpPBrWrxM2vt5xm', false, 'ADMIN');


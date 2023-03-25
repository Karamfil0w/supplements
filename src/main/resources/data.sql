INSERT INTO roles (id, role)
values
    (1, 'ADMIN'),
    (2, 'USER');

INSERT INTO users (id, email, username, password)
VALUES
    (1, 'admin@example.com', 'Admin', '$10$zQejItSuntbwIb638Akxsu/4GHDVqusV9Ep8HD6Kw5i5Ksjfqeeya');


INSERT INTO users_roles (user_id, role_id)
VALUES
    (1, 1),
    (1, 2);
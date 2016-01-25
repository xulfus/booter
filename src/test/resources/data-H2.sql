insert into account (id, password, username) values (1, '$2a$04$dvwjGLRb3ZggHBx01EE4nOJfIfRHYBLFHU8i0um5CjCFYUxEV3WAi', 'janne');
insert into bookmark (id, description, uri, account_id) values (2, 'first url', 'http://facebook.com', 1);
insert into bookmark (id, description, uri, account_id) values (3, 'second url', 'http://twitter.com', 1);
insert into account (id, password, username) values (2, 'non-usable-password', 'manne');

insert into bookmark (id, description, uri, account_id) values (4, 'first url', 'http://facebook.com', 2);
insert into bookmark (id, description, uri, account_id) values (5, 'second url', 'http://twitter.com', 2);


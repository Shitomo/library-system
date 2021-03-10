DROP TABLE IF EXISTS author;
DROP TABLE IF EXISTS book_detail;
DROP TABLE IF EXISTS book;

DROP SEQUENCE IF EXISTS book_id_seq;
DROP SEQUENCE IF EXISTS book_detail_id_seq;
DROP SEQUENCE IF EXISTS author_id_seq;

CREATE SEQUENCE book_id_seq;
CREATE SEQUENCE book_detail_id_seq;
CREATE SEQUENCE author_id_seq;

CREATE TABLE book (
    id INTEGER DEFAULT nextval('book_id_seq') NOT NULL,
    name   TEXT NOT NULL,
    page_num INTEGER,
    CONSTRAINT book_id_pkey PRIMARY KEY(id)
);

CREATE TABLE book_detail (
    id     INTEGER DEFAULT nextval('book_detail_id_seq') NOT NULL,
    detail TEXT,
    book_id INTEGER,
    CONSTRAINT book_detail_id_pkey PRIMARY KEY(id),
    CONSTRAINT book_user_id_fkey FOREIGN KEY(book_id)
        REFERENCES book(id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE TABLE author (
     id     INTEGER DEFAULT nextval('author_id_seq') NOT NULL,
     first_name TEXT,
     last_name TEXT,
     book_id INTEGER,
     CONSTRAINT author_id_pkey PRIMARY KEY(id),
     CONSTRAINT book_user_id_fkey FOREIGN KEY(book_id)
         REFERENCES book(id) MATCH SIMPLE
         ON UPDATE NO ACTION ON DELETE CASCADE
);

INSERT INTO book(name,page_num) VALUES ('Cheese',3);

INSERT INTO book_detail(detail, book_id) VALUES
('testing', (SELECT id from book WHERE name='Cheese')),
('another row', (SELECT id from book WHERE name='Cheese'));

INSERT INTO author(first_name, last_name, book_id) VALUES
('shikina','tomoaki', (SELECT id from book WHERE name='Cheese')),
('takenaka','tomoaki', (SELECT id from book WHERE name='Cheese'));
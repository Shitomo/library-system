DROP TABLE IF EXISTS book_detail;
DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS author;
DROP SEQUENCE IF EXISTS book_id_seq;
DROP SEQUENCE IF EXISTS book_detail_id_seq;

CREATE SEQUENCE book_id_seq;
CREATE SEQUENCE book_detail_id_seq;

CREATE TABLE book (
    id INTEGER DEFAULT nextval('book_id_seq') NOT NULL,
    name   TEXT NOT NULL,
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

INSERT INTO book(name) VALUES ('Cheese');
INSERT INTO book_detail(detail, book_id) VALUES
( 'testing', (SELECT id from book WHERE name='Cheese')),
( 'another row', (SELECT id from book WHERE name='Cheese'));
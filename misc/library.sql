DROP TABLE IF EXISTS book_stock;
DROP TABLE IF EXISTS author_book_correspondence;
DROP TABLE IF EXISTS author;
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

CREATE TABLE author (
     id INTEGER DEFAULT nextval('author_id_seq') NOT NULL,
     first_name TEXT,
     last_name TEXT,
     CONSTRAINT author_id_pkey PRIMARY KEY(id)
);

CREATE TABLE author_book_correspondence (
    book_id INTEGER,
    author_id INTEGER,
    CONSTRAINT book_id_fkey FOREIGN KEY(book_id)
        REFERENCES book(id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE CASCADE,
    CONSTRAINT author_id_fkey FOREIGN KEY(author_id)
        REFERENCES author(id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE CASCADE,
    CONSTRAINT author_book_correspondence_pkey PRIMARY KEY(book_id, author_id)
);

CREATE TABLE book_stock (
    stock_id INTEGER,
    book_id INTEGER,
    text VARCHAR(300),
    CONSTRAINT book_id_fkey FOREIGN KEY(book_id)
        REFERENCES book(id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE CASCADE,
    CONSTRAINT stock_id_pkey PRIMARY KEY(stock_id)
);

INSERT INTO book(name,page_num) VALUES ('Cheese',3);

INSERT INTO author(first_name, last_name) VALUES
('shikina','tomoaki'),
('takenaka','tomoaki');
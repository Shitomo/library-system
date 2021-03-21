DROP TABLE IF EXISTS library;
DROP TABLE IF EXISTS book;

DROP SEQUENCE IF EXISTS library_id_seq;
DROP SEQUENCE IF EXISTS book_id_seq;

CREATE SEQUENCE library_id_seq;
CREATE SEQUENCE book_id_seq;

CREATE TABLE book (
    id INTEGER DEFAULT nextval('book_id_seq') NOT NULL,
    title   TEXT NOT NULL,
    authors TEXT NOT NULL,
    CONSTRAINT book_id_pkey PRIMARY KEY(id),
    CONSTRAINT UNIQUE(title, authors)
);

CREATE TABLE library (
    id INTEGER DEFAULT nextval('library_id_seq') NOT NULL,
    book_id INTEGER,
    is_borrowed BOOLEAN DEFAULT FALSE,
    CONSTRAINT book_id_fkey FOREIGN KEY(book_id)
        REFERENCES book(id),
    CONSTRAINT book_collection_id_pkey PRIMARY KEY(id)
);

INSERT INTO book(title,authors) VALUES ('novel1','author1#author2#author3#');
INSERT INTO library(book_id) VALUES (1);

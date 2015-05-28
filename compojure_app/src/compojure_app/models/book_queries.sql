-- name: all-books
-- Selects all books
SELECT id, title, authors
FROM books;

-- name: insert-book<!
-- Queries a single book
INSERT INTO books (title, authors) VALUES (:title, :authors);

-- name: create-books-table-if-not-exists!
-- create the books table if it does not exist
CREATE TABLE IF NOT EXISTS books (
  id serial PRIMARY KEY,
  title VARCHAR (20) NOT NULL,
  authors VARCHAR (20) NOT NULL
);

-- name: delete-books!
-- drop the books table
DELETE FROM books;

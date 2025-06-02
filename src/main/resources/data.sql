INSERT INTO `APP_USER` (username, password, role, created_at) 
VALUES 
('admin', '$2a$10$m.D8XqAPp9MNJ.4339L7WehwrKI5JVAmo.WPsjQfqoTfaDoqhXwb6', 'ROLE_ADMIN', CURRENT_TIMESTAMP),
('tomcat', '$2a$10$FJ5HECMtgkpC02IDNbvZcO7npnoxAi4DAmg./WiSkskdSoyj5f80S', 'ROLE_USER', CURRENT_TIMESTAMP);

INSERT INTO BOOK (title, author, is_available) VALUES
  ('Pride and Prejudice',      'Jane Austen',         true),
  ('1984',                      'George Orwell',       true),
  ('To Kill a Mockingbird',     'Harper Lee',          true),
  ('The Great Gatsby',          'F. Scott Fitzgerald', true),
  ('Moby Dick',                 'Herman Melville',     true),
  ('War and Peace',             'Leo Tolstoy',         true),
  ('The Catcher in the Rye',    'J. D. Salinger',      true),
  ('Brave New World',           'Aldous Huxley',       true),
  ('Crime and Punishment',      'Fyodor Dostoevsky',   true),
  ('The Hobbit',                'J. R. R. Tolkien',    true),
  ('Jane Eyre',                 'Charlotte Brontë',    true),
  ('The Odyssey',               'Homer',               true),
  ('Catch-22',                  'Joseph Heller',       true),
  ('The Lord of the Rings',     'J. R. R. Tolkien',    true),
  ('One Hundred Years of Solitude', 'Gabriel García Márquez', true);

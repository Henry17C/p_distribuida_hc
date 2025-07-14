INSERT INTO authors (id, name, version) VALUES
    (1, 'Gabriel García Márquez', 1),
    (2, 'Isabel Allende', 1),
    (3, 'Mario Vargas Llosa', 1),
    (4, 'Jorge Luis Borges', 1),
    (5, 'Julio Cortázar', 1);


-- 1. LIBROS
INSERT INTO books (isbn, title, price, version) VALUES
                                                    ('ISBN001', 'Cien Años de Soledad', 25.99, 1),
                                                    ('ISBN002', 'La Casa de los Espíritus', 22.50, 1),
                                                    ('ISBN003', 'La Ciudad y los Perros', 19.75, 1),
                                                    ('ISBN004', 'Ficciones', 18.40, 1),
                                                    ('ISBN005', 'Rayuela', 21.30, 1);

-- 2. RELACIÓN LIBROS - AUTORES
INSERT INTO books_authors (books_isbn, authors_id) VALUES
                                                       ('ISBN001', 1), -- Gabriel García Márquez
                                                       ('ISBN002', 2), -- Isabel Allende
                                                       ('ISBN003', 3), -- Mario Vargas Llosa
                                                       ('ISBN004', 4), -- Jorge Luis Borges
                                                       ('ISBN005', 5); -- Julio Cortázar

-- 3. INVENTARIO
INSERT INTO inventory (isbn, sold, supplied) VALUES
                                                 ('ISBN001', 5, 20),
                                                 ('ISBN002', 2, 15),
                                                 ('ISBN003', 3, 18),
                                                 ('ISBN004', 1, 10),
                                                 ('ISBN005', 0, 12);

-- 4. CLIENTES
INSERT INTO customers (id, name, email, version) VALUES
                                                     (1, 'Ana Pérez', 'ana@example.com', 1),
                                                     (2, 'Carlos Ruiz', 'carlos@example.com', 1);

-- 5. ÓRDENES
INSERT INTO purchase_orders (id, customer_id, total, status, placed_on, delivered_on) VALUES
                                                                                          (1, 1, 48, 1, NOW(), NOW()),
                                                                                          (2, 2, 21, 0, NOW(), NULL);

-- 6. LÍNEAS DE ÍTEMS
INSERT INTO line_items (order_id, quantity, isbn) VALUES
                                                      (1, 1, 'ISBN001'),
                                                      (1, 1, 'ISBN002'),
                                                      (2, 1, 'ISBN005');

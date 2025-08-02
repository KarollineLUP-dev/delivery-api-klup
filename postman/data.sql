-- Dados para a tabela 'restaurantes'
INSERT INTO restaurantes (active, fee_delivery, feedback, address, category, name, phone) VALUES
(TRUE, 5.00, 4.5, 'Rua A, 123', 'Pizzaria', 'Pizza Express', '11987654321'),
(TRUE, 3.50, 4.8, 'Av. B, 456', 'Japonesa', 'Sushi Delícia', '11998765432'),
(FALSE, 0.00, 3.9, 'Rua C, 789', 'Lanchonete', 'Burguer Mania', '11976543210');

-- Dados para a tabela 'clientes'
INSERT INTO clientes (active, dt_register, address, email, name, phone) VALUES
(TRUE, '2024-01-15 10:30:00', 'Rua X, 10', 'joao.silva@email.com', 'João Silva', '11912345678'),
(TRUE, '2024-02-20 14:00:00', 'Av. Y, 20', 'maria.souza@email.com', 'Maria Souza', '11923456789'),
(FALSE, '2024-03-01 09:15:00', 'Rua Z, 30', 'pedro.santos@email.com', 'Pedro Santos', '11934567890');

-- Dados para a tabela 'produtos'
INSERT INTO produtos (available, price, restaurant_id, category, description, name) VALUES
(TRUE, 45.00, 1, 'Pizza', 'Pizza de Calabresa com queijo', 'Pizza Calabresa Média'),
(TRUE, 60.00, 1, 'Pizza', 'Pizza Portuguesa com azeitonas', 'Pizza Portuguesa Grande'),
(TRUE, 35.00, 2, 'Sushi', '20 peças de sushi variados', 'Combinado Sushi 20pçs'),
(TRUE, 18.00, 3, 'Lanche', 'Hambúrguer com queijo e bacon', 'X-Bacon');

-- Dados para a tabela 'pedidos'
INSERT INTO pedidos (number_order, price_total, client_id, dt_order, restaurant_id, items, observations, status) VALUES
('PED001', 50.00, 1, '2025-08-01 10:00:00', 1, '[{"produtoId": 1, "quantidade": 1}]', 'Sem cebola', 'CONCLUIDO'),
('PED002', 40.00, 2, '2025-08-01 11:30:00', 2, '[{"produtoId": 3, "quantidade": 1}]', 'Com shoyu extra', 'PENDENTE'),
('PED003', 25.00, 1, '2025-08-01 12:45:00', 3, '[{"produtoId": 4, "quantidade": 1}]', 'Coca-cola zero', 'EM_PREPARACAO');
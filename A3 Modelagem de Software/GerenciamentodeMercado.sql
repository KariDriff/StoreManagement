CREATE database Supermercado;
USE Supermercado;

-- ===============
-- FORNECEDORES
-- ===============
CREATE TABLE Fornecedor (
    id SERIAL PRIMARY KEY auto_increment,
    nome VARCHAR(100),
    telefone VARCHAR(20),
    cnpj VARCHAR(20) UNIQUE,
    email VARCHAR(100),
    isAtivo BOOLEAN DEFAULT TRUE
);

-- ===============
-- PRODUTOS
-- ===============
CREATE TABLE Produto (
    id SERIAL PRIMARY KEY auto_increment,
    nome VARCHAR(100),
    marca VARCHAR(50),
    preco DECIMAL(10,2),
    quantidade SMALLINT,
    lote VARCHAR(50),
    codigoBarras VARCHAR(50) UNIQUE,
    dataValidade DATE,
    volumeLitros SMALLINT,
    pesoGramas DOUBLE,
    id_fornecedor INT REFERENCES Fornecedor(id),
    id_categoria INT REFERENCES Categoria(id)

);

-- =======================
-- CATEGORIA
-- =======================
CREATE TABLE Categoria(
	id SERIAL PRIMARY KEY auto_increment,
    nome VARCHAR(50),
    descricao VARCHAR(60)
);

-- ========================
-- FUNCIONÁRIOS
-- ========================
CREATE TABLE Funcionario (
    id SERIAL PRIMARY KEY auto_increment,
    nome VARCHAR(100),
    cpf VARCHAR(15) UNIQUE,
    dataContratado DATE,
    cargo VARCHAR(50),
    login VARCHAR(50) UNIQUE,
    senha VARCHAR(100),
    isAdmin BOOLEAN DEFAULT FALSE,
    isAtivo BOOLEAN DEFAULT TRUE
);

-- ========================
-- FINANCEIRO
-- ========================
CREATE TABLE Financeiro (
    id SERIAL PRIMARY KEY auto_increment,
    tipo VARCHAR(10) CHECK (tipo IN ('entrada', 'saida')),
    valor DECIMAL(10,2),
    descricao TEXT,
    dataOperacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    id_funcionario INT REFERENCES Funcionario(id),
    id_despesa INT REFERENCES despesa(id)
  
);

-- ========================
-- MOVIMENTAÇÃO DE ESTOQUE
-- ========================
CREATE TABLE MovimentoEstoque (
    id SERIAL PRIMARY KEY auto_increment,
    id_produto INT REFERENCES Produto(id),
    tipo_movimento VARCHAR(10) CHECK (tipo_movimento IN ('entrada', 'saida')),
    quantidade INT,
    data_movimento TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    origem_destino VARCHAR(100),
    observacao TEXT,
    id_referencia INT,
    id_funcionario INT REFERENCES Funcionario(id)
);

-- ========================
-- DESPESAS
-- ========================
CREATE TABLE Despesa (
    id SERIAL PRIMARY KEY auto_increment,
    descricao TEXT,
    valor DECIMAL(10,2),
    dataDespesa DATE
);

-- ========================
-- VENDAS
-- ========================
CREATE TABLE Venda (
    id SERIAL PRIMARY KEY auto_increment,
    data TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    id_funcionario INT REFERENCES Funcionario(id),
    valor_total DECIMAL(10,2),
    forma_pagamento SMALLINT CHECK (forma_pagamento BETWEEN 0 AND 3)
);

CREATE TABLE ItemVenda (
    id SERIAL PRIMARY KEY auto_increment,
    id_venda INT REFERENCES Venda(id),
    id_produto INT REFERENCES Produto(id),
    quantidade INT,
    preco_unitario DECIMAL(10,2),
    desconto DECIMAL(10,2)
);

-- ===========
-- EVENTOS
-- ===========
CREATE TABLE Evento (
    id SERIAL PRIMARY KEY auto_increment,
    nome VARCHAR(100),
    descricao TEXT,
    dataInicio DATE,
    dataFim DATE,
    desconto DECIMAL(5,2)
);

CREATE TABLE EventoProduto (
    id_evento INT REFERENCES Evento(id),
    id_produto INT REFERENCES Produto(id),
    PRIMARY KEY (id_evento, id_produto)
);

-- ========================
-- LOGS
-- ========================
CREATE TABLE LogSistema (
    id SERIAL PRIMARY KEY auto_increment,
    data TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    acao TEXT,
    id_funcionario INT REFERENCES Funcionario(id)
);

-- inserir fornecedor
INSERT INTO Fornecedor(nome, telefone, cnpj, email, isAtivo)
VALUES ('fornece express', '(71)98565-4585', '13.500.450/0001-56', 'forneceexpress@gmail.com', true),
('fornece brasil', '(71)99156-8534', '04.500.754/0001-60', 'fornecebrasil@outlook.com', true),
('Distribuidora da Bahia', '(71)98654-5687', '30.450.652/0001-65', 'distribuidoradabahia@gmail.com', true),
('Alimentos Distribuição', '(71)99652-6545', '08.642.351/0001-50', 'alimentos2distribuicao@gmail.com', false)
;
-- Inserir Produto
INSERT INTO Produto(nome, marca, preco, quantidade, lote, codigoBarras, dataValidade, volumeLitros, pesoGramas)
VALUES('Refrigerante', 'Coca Cola', 4.50, 30, 'A5542BF65', '5468214568546', '2026-07-10', 1, null),
('feijao', 'kisabor', 6.80, 100, 'SJ656AS5F6S6', '54685226956565', '2026-05-15', null, 1000),
('milho verde', null, 0.60, 200, 'F546SFD55', '65481165668750', '2026-01-10', null, 0.500),
('açúcar', 'açucarin', 4.50, 300, 'DKJ65SAF6', '55554578516656', '2026-02-05', null, 1000),
('desinfetante', 'pinho', 5.0, 150, 'AKSJ65SA4FDS', '26598523323214', '2025-12-30', 1, null)
;
-- inserir funcionarios
INSERT INTO Funcionario (nome, cpf, dataContratado, cargo, login, senha)
VALUES ('Kaio Brasileiro', '12545298545', '2023-05-10', 'Contador', 'Kaio10', 'kaio123'),
('Everton Catarino', '12141516147', '2020-01-15', 'Operador de Caixa', 'Evertinho', '123Evertinho'),
('Joao Junior', '25485468579', '2022-05-05', 'Estoquista', 'Joaojao', 'Jooao123')
;

-- inserir financeiro
INSERT INTO Financeiro (tipo, valor, descricao, dataOperacao, id_funcionario, id_despesa)
VALUES ('entrada', 350.00, 'venda de produtos', '2025-06-06', 3, null),
('saida', 250.00, 'boleto de agua', '2025-06-10', 1, 2)
;

-- inserir movimento estoque
INSERT INTO MovimentoEstoque (id_produto, tipo_movimento, quantidade, data_movimento, origem_destino, observacao, id_referencia, id_funcionario)
VALUES (1, 'entrada', 50, '2025-05-30', 'reposição de estoque', null, null, 3 )
;
-- inserir despesas
INSERT INTO Despesa (descricao, valor, dataDespesa)
VALUES ('Conta de Agua', 500.0, '2025-06-10')
;
-- inserir evento
INSERT INTO Evento (nome, descricao, dataInicio, dataFim, desconto)
VALUES ('Promoção Arraiá Junino', 'A cada 10 produtos juninos', '2025-06-01', '2025-06-30', 0.10)
;
-- inserir evento produto
INSERT INTO EventoProduto(id_evento, id_produto)
VALUES(1, 3)
;
-- inserir venda
INSERT INTO Venda (id_funcionario, valor_total, forma_pagamento)
VALUES (1, 5 * 5.49, 1);  -- total R$ 27.45

-- 2. Inserir o item da venda na tabela ItemVenda
-- (supondo que a venda criada tenha id = 3; ajuste conforme necessário)
INSERT INTO ItemVenda (id_venda, id_produto, quantidade, preco_unitario, desconto)
VALUES (3, 1, 5, 5.49, 0.00);

-- 3. Registrar a saída no estoque
INSERT INTO MovimentoEstoque (id_produto, tipo_movimento, quantidade, origem_destino, observacao, id_referencia)
VALUES (1, 'saida', 5, 'Venda', 'Venda nº 3 - Leite Integral', 3);

-- Atualização de dados
UPDATE Produto
SET preco = 6.90
WHERE id = 1;

-- Consulta em uma única tabela
SELECT 
    id,
    nome,
    marca,
    preco
FROM Produto;

-- Consulta em múltiplas tabelas
SELECT 
    p.nome AS produto,
    p.marca,
    f.nome AS fornecedor,
    f.telefone
FROM Produto p
JOIN Fornecedor f ON p.id_fornecedor = f.id;

-- Consulta utilizando múltiplas tabelas utilizando agrupamento 
SELECT
    p.id,
    p.nome,
    COALESCE(SUM(CASE WHEN m.tipo_movimento = 'entrada' THEN m.quantidade ELSE 0 END), 0) -
    COALESCE(SUM(CASE WHEN m.tipo_movimento = 'saida' THEN m.quantidade ELSE 0 END), 0) AS estoque_atual
FROM Produto p
LEFT JOIN MovimentoEstoque m ON m.id_produto = p.id
WHERE p.id = 1
GROUP BY p.id, p.nome;

-- quantidade total vendida do produto
SELECT 
    p.nome AS produto,
    SUM(iv.quantidade) AS total_vendido
FROM ItemVenda iv
JOIN Produto p ON iv.id_produto = p.id
GROUP BY p.nome
ORDER BY total_vendido DESC;


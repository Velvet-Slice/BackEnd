-- 1. Categorias
INSERT INTO categorias (nome_cat) VALUES ('Clássicos');
INSERT INTO categorias (nome_cat) VALUES ('Especiais');

-- 2. Produtos
INSERT INTO produtos (nome_prod, preco_prod, descricao_prod, imagem_url, id_cat) VALUES
('Bolo Red Velvet', 85.00, 'Massa vermelha aveludada clássica com recheio cremoso de cream cheese.', 'https://cdn.shoppub.io/cdn-cgi/image/w=1000,h=1000,q=80,f=auto/beirario/media/uploads/produtos/foto/xrwupuqt/file.jpg', 1);

INSERT INTO produtos (nome_prod, preco_prod, descricao_prod, imagem_url, id_cat) VALUES
('Bolo de Cenoura', 45.50, 'Receita caseira fofinha com cobertura generosa de brigadeiro.', 'https://i.pinimg.com/736x/92/c4/13/92c413997277ef13bec3735c954433d0.jpg', 1);

INSERT INTO produtos (nome_prod, preco_prod, descricao_prod, imagem_url, id_cat) VALUES
('Torta de Limão', 55.00, 'Massa crocante, recheio azedinho e merengue maçaricado.', 'https://cdn.shoppub.io/cdn-cgi/image/w=1000,h=1000,q=80,f=auto/beirario/media/uploads/produtos/foto/xrwupuqt/file.jpg', 2);

INSERT INTO produtos (nome_prod, preco_prod, descricao_prod, imagem_url, id_cat) VALUES
('Bolo de Churros', 60.00, 'Massa com canela e muito recheio de doce de leite.', 'https://cdn.shoppub.io/cdn-cgi/image/w=1000,h=1000,q=80,f=auto/beirario/media/uploads/produtos/foto/xrwupuqt/file.jpg', 2);

INSERT INTO produtos (nome_prod, preco_prod, descricao_prod, imagem_url, id_cat) VALUES
('Cheesecake Frutas', 95.00, 'Base de biscoito, creme suave e geleia de frutas vermelhas.', 'https://cdn.shoppub.io/cdn-cgi/image/w=1000,h=1000,q=80,f=auto/beirario/media/uploads/produtos/foto/xrwupuqt/file.jpg', 2);

-- 3. Usuário de Teste (Senha: 123456)
-- Nota: A senha aqui não está criptografada, ideal apenas para testes locais se não usar BCrypt
INSERT INTO usuarios (nome, email, cpf, senha) VALUES
('Cliente Teste', 'teste@teste.com', '12345678900', '123456');

-- 4. Cliente vinculado ao Usuário (O ID do usuário acima será 1)
INSERT INTO clientes (nome_cli, cpf_cli, user_id) VALUES
('Cliente Teste', '12345678900', 1);
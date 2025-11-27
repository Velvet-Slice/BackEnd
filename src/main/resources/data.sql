-- 1. Categorias
INSERT INTO categorias (nome_cat) VALUES ('Clássicos');
INSERT INTO categorias (nome_cat) VALUES ('Especiais');
INSERT INTO categorias (nome_cat) VALUES ('Infantis');

-- 2. Produtos
-- Floresta Negra
INSERT INTO produtos (nome_prod, preco_prod, descricao_prod, imagem_url, id_cat) VALUES
    ('Floresta Negra', 85.00, 'Um clássico irresistível: massa de chocolate molhadinha, recheada com chantilly fresco e cerejas ao maraschino, coberto com raspas de chocolate.', 'https://confeitariaxv.com.br/wp-content/uploads/2020/07/WhatsApp-Image-2020-07-21-at-07.58.17.jpeg', 1);

-- Purple Cake
INSERT INTO produtos (nome_prod, preco_prod, descricao_prod, imagem_url, id_cat) VALUES
    ('Purple Cake', 92.00, 'Um bolo vibrante e sofisticado! Massa suave de baunilha com um delicioso recheio de creme de amora e mirtilo, finalizado com uma decoração em tons de roxo.', 'https://www.sabornamesa.com.br/media/k2/items/cache/8212207f146778f83156ffc204ca380c_XL.jpg', 2);

-- Xadreizinho
INSERT INTO produtos (nome_prod, preco_prod, descricao_prod, imagem_url, id_cat) VALUES
    ('Xadreizinho', 78.00, 'Visual divertido e sabor duplo! Massa intercalada de baunilha e chocolate criando um efeito xadrez, ideal para quem não quer escolher só um sabor.', 'https://boutiquedofolhado.com.br/wp-content/uploads/2023/03/Boutique-do-Folhado-Prestigio-Aniversario-Curitiba-Docinhos-Salgadinhos-Bolo-Infantil-Brigadeiro-Coxinha-Chocolate-Empadao-Evento-Confeitaria.jpg', 1);

-- Bolo de Churros
INSERT INTO produtos (nome_prod, preco_prod, descricao_prod, imagem_url, id_cat) VALUES
    ('Bolo de Churros', 65.00, 'O sabor da infância em forma de bolo. Massa fofinha com um toque de canela e especiarias, recheada e coberta com doce de leite caseiro cremoso.', 'https://i.pinimg.com/474x/bf/a0/28/bfa0280744b1910e0b5bab7dc7d21569.jpg', 1);

-- Bolo de Animal
INSERT INTO produtos (nome_prod, preco_prod, descricao_prod, imagem_url, id_cat) VALUES
    ('Bolo de Animal', 110.00, 'Perfeito para festas infantis! Decoração temática e criativa com modelagens divertidas, encantando as crianças e os adultos.', 'https://www.confeiteiradesucesso.com/wp-content/uploads/2019/07/bolopeudo-furrycake2.jpg', 3);

-- Naked Cake
INSERT INTO produtos (nome_prod, preco_prod, descricao_prod, imagem_url, id_cat) VALUES
    ('Naked Cake', 95.00, 'Elegância rústica. Bolo sem cobertura lateral, exibindo camadas generosas de recheio de creme leve e decorado com frutas vermelhas frescas.', 'https://conteudo.imguol.com.br/c/entretenimento/af/2021/02/19/bolo-de-confeiteiro-pode-ser-feito-em-casa-com-alguns-itens-1613786541971_v2_4x3.jpg', 2);

-- Bolo de Cenoura
INSERT INTO produtos (nome_prod, preco_prod, descricao_prod, imagem_url, id_cat) VALUES
    ('Bolo de Cenoura', 55.00, 'Aquele gostinho de casa de vó. Massa de cenoura super macia e alaranjada, coberta com uma ganache de chocolate meio amargo brilhante.', 'https://www.mococa.com.br/wp-content/uploads/2025/07/tipos-de-bolos-que-encantam-clientes-na-confeitaria-1024x683.png', 1);

-- Ninho com Morango
INSERT INTO produtos (nome_prod, preco_prod, descricao_prod, imagem_url, id_cat) VALUES
    ('Ninho com Morango', 88.00, 'A combinação campeã de vendas. Creme aveludado de Leite Ninho intercalado com pedaços de morangos frescos e suculentos.', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTYfVUrn1GfZ6pgTJ86vOc05gd-A-rGDgmgRA&s', 2);

-- 3. Usuário de Teste (Senha: 123456)
-- Nota: Facilita seus testes sem precisar cadastrar toda vez
INSERT INTO usuarios (nome, email, cpf, senha) VALUES
    ('Cliente Teste', 'teste@teste.com', '12345678900', '123456');

-- 4. Cliente vinculado ao Usuário (O ID do usuário acima será 1)
INSERT INTO clientes (nome_cli, cpf_cli, user_id) VALUES
    ('Cliente Teste', '12345678900', 1);
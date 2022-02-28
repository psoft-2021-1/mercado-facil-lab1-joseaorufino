insert into produto (ID, NOME, CODIGO_BARRA, FABRICANTE, IS_DISPONIVEL, CATEGORIA, PRECO, TIPO_PRODUTO)
values(10001,'Leite Integral', '87654321-B', 'Parmalat', FALSE, 'Mercearia', 4.5, 'COMUM');

insert into produto (ID, NOME, CODIGO_BARRA, FABRICANTE, IS_DISPONIVEL, CATEGORIA, PRECO, TIPO_PRODUTO)
values(10002,'Arroz Integral', '87654322-B', 'Tio Joao', FALSE, 'Perecíveis', 5.5, 'COMUM');

insert into produto (ID, NOME, CODIGO_BARRA, FABRICANTE, IS_DISPONIVEL, CATEGORIA, PRECO, TIPO_PRODUTO)
values(10003,'Sabao em Po', '87654323-B', 'OMO', FALSE, 'Limpeza', 12, 'COMUM');

insert into produto (ID, NOME, CODIGO_BARRA, FABRICANTE, IS_DISPONIVEL, CATEGORIA, PRECO, TIPO_PRODUTO)
values(10004,'Agua Sanitaria', '87654324-C', 'Dragao', FALSE, 'limpeza', 3, 'COMUM');

insert into produto (ID, NOME, CODIGO_BARRA, FABRICANTE, IS_DISPONIVEL, CATEGORIA, PRECO, TIPO_PRODUTO)
values(10005,'Creme Dental', '87654325-C', 'Colgate', FALSE, 'HIGIENE', 2.5, 'COMUM');

insert into produto (ID, NOME, CODIGO_BARRA, FABRICANTE, IS_DISPONIVEL, CATEGORIA, PRECO, TIPO_PRODUTO)
values(10006,'Carne de Sol', '10000000-R', 'Friggor', FALSE, 'Carnes', 10.5, 'REFRIGERACAO');

insert into produto (ID, NOME, CODIGO_BARRA, FABRICANTE, IS_DISPONIVEL, CATEGORIA, PRECO, TIPO_PRODUTO)
values(10007,'Prato de Vidro', '10000000-F', 'Pratto', FALSE, 'Cozinha', 5.0, 'FRAGIL');

insert into lote (ID, PRODUTO_ID, NUMERO_DE_ITENS)
values(1, 10005, 5);

insert into lote (ID, PRODUTO_ID, NUMERO_DE_ITENS)
values(2, 10006, 2);

insert into lote (ID, PRODUTO_ID, NUMERO_DE_ITENS)
values(3, 10007, 3);

update produto set IS_DISPONIVEL = TRUE where ID = 10005;
update produto set IS_DISPONIVEL = TRUE where ID = 10006;
update produto set IS_DISPONIVEL = TRUE where ID = 10007;

insert into cliente (ID, CPF, NOME, IDADE, ENDERECO, TIPO_DE_CLIENTE)
values(1001, 10020030006, 'Fulano', 23, 'Rua tal', 'NORMAL');

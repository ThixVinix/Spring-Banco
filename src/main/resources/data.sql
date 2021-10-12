INSERT INTO BANCOS (id, agencia, cnpj, nome) VALUES(1, 0572, '88.105.140/0001-75',  'Itaú');
INSERT INTO BANCOS (id, agencia, cnpj, nome) VALUES(2, 7092, '60.009.694/0001-50', 'Bradesco');

UPDATE BANCOS SET nome='Nubank' WHERE id=1; 

INSERT INTO ENDERECOS (id, bairro, cep, cidade, logradouro, uf) VALUES(1 ,'Engenho Velho de Brotas', '40243-300', 'Salvador', 'Vila Amanda', 'BA');
INSERT INTO ENDERECOS (id, bairro, cep, cidade, logradouro, uf) VALUES(2 ,'Centro', '87145-970', 'Água Boa', 'Avenida Ipiranga, s/n','PR');
INSERT INTO ENDERECOS (id, bairro, cep, cidade, logradouro, uf) VALUES(3 ,'Alto da Serra', '25640-554', 'Petrópolis', 'Servidão Ilca Vitral de Souza', 'RJ');
INSERT INTO ENDERECOS (id, bairro, cep, cidade, logradouro, uf) VALUES(4 ,'Itaboa', '18430-971', 'Ribeirão Branco', 'Rua Sol Nascente 16', 'SP');

INSERT INTO CONTAS (id, limite, numero_conta, saldo, senha, tipo, data_abertura) VALUES(1, 1000.50, '1234-5', 6325.51, 5124, 'NORMAL', DATE '2015-12-17');
INSERT INTO CONTAS (id, limite, numero_conta, saldo, senha, tipo, data_abertura) VALUES(2, 2000.50, '6645-5', 1365.27, 3424, 'ESPECIAL', DATE '2016-03-25');
INSERT INTO CONTAS (id, limite, numero_conta, saldo, senha, tipo, data_abertura) VALUES(3, 3000.50, '3544-5', 4353.23, 5164, 'NORMAL', DATE '2020-09-02');
INSERT INTO CONTAS (id, limite, numero_conta, saldo, senha, tipo, data_abertura) VALUES(4, 4000.50, '8234-5', 8943.88, 7245, 'ESPECIAL', DATE '2021-10-12');

INSERT INTO CLIENTES (id, cpf, nome, rg, conta_id, endereco_id) VALUES(1, '021.183.710-55', 'João', '31.113.159-1', 2, 2);
INSERT INTO CLIENTES (id, cpf, nome, rg, conta_id, endereco_id) VALUES(2, '841.845.320-63', 'Maria', '33.470.558-7', 1, 1);
INSERT INTO CLIENTES (id, cpf, nome, rg, conta_id, endereco_id) VALUES(3, '024.891.850-80', 'Ricardo', '27.931.876-5', 4, 4);
INSERT INTO CLIENTES (id, cpf, nome, rg, conta_id, endereco_id) VALUES(4, '154.822.960-16', 'Yara', '19.231.963-2', 3, 3);


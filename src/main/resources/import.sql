insert into cozinha(nome) values('Tailandesa');
insert into cozinha(nome) values('Indiana');

insert into estado(nome) values('Minas Gerais');
insert into estado(nome) values('São Paulo');
insert into estado(nome) values('Ceará');

insert into  cidade(id, nome, estado_id) values(1, 'Uberlândia', 1)
insert into  cidade(id, nome, estado_id) values(2, 'Belo Horizonte', 1)
insert into  cidade(id, nome, estado_id) values(3, 'São Paulo', 2)
insert into  cidade(id, nome, estado_id) values(4, 'Campinas', 2)
insert into  cidade(id, nome, estado_id) values(5, 'Fortaleza', 3)

insert into restaurante(id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id,  endereco_cep,  endereco_logradouro,  endereco_numero,  endereco_bairro) values(1, 'Food da rua', 20, 1, utc_timestamp, utc_timestamp, 1, '300-444', 'Rua qualquer', '1000', 'Centro');
insert into restaurante(id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id,  endereco_cep,  endereco_logradouro,  endereco_numero,  endereco_bairro) values(2, 'Sua comida', 10, 2, utc_timestamp, utc_timestamp, 2, '777-444', 'Rua da esquina', '56', 'Centro');
insert into restaurante(id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id,  endereco_cep,  endereco_logradouro,  endereco_numero,  endereco_bairro) values(3, 'Mexicano food', 10, 1, utc_timestamp, utc_timestamp, 2, '888-444', 'Rua da ponta', '677', 'Centro');

insert into forma_pagamento(id, descricao) values(1, 'Cartão de crédito');
insert into forma_pagamento(id, descricao) values(2, 'Cartão de débito');
insert into forma_pagamento(id, descricao) values(3, 'Dinheiro');

insert into restaurante_forma_pagamento(restaurante_id, forma_pagamento_id) values(1,1),(1,2),(1,3),(2,3),(3,2),(3,3);

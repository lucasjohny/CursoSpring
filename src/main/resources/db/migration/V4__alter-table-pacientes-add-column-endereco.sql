alter table pacientes add logradouro varchar(100) not null;
alter table pacientes add bairro varchar(100) not null;
alter table pacientes add cep varchar(9) not null;
alter table pacientes add complemento varchar(100);
alter table pacientes add numero varchar(20);
alter table pacientes add uf char(2) not null;
alter table pacientes add cidade varchar(100) not null;
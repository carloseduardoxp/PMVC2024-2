create db_artistas;

use db_artistas;

create table tb_artista (
   cod_artista integer not null primary key auto_increment,
   nom_artista varchar(255) not null
);

create table tb_album (
   cod_album integer not null primary key auto_increment,
   nom_album varchar(255) not null,
   ano_lancamento integer not null,
   cod_artista integer not null,
   foreign key(cod_artista) references tb_artista(cod_artista)
);
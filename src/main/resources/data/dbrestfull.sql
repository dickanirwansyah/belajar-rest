insert into product(description, nama, price, vendor) values('Kelezatan sekali gigit', 'Bembeng', 
'1000', 'Atom');
insert into product(description, nama, price, vendor) values('Kelazatan duakali jilat', 'Geary', 
'1000', 'Angular');
insert into product(description, nama, price, vendor) values('Kelezatan tigakali gigit', 'Kerupuk', 
'1000', 'React');
insert into product(description, nama, price, vendor) values('Kelezatan sekali gigit', 'Kacang', 
'1000', 'Vue');
insert into product(description, nama, price, vendor) values('Kelezatan sekali gigit', 'TOP', 
'1000', 'EmberJs');
insert into product(description, nama, price, vendor) values('Kelezatan sekali gigit', 'Bubur', 
'1000', 'Go');
insert into product(description, nama, price, vendor) values('Kelezatan sekali gigit', 'Astor', 
'1000', 'Scala');


insert into kategori(idkategori, nama, kodekategori) values('k001', 'kategori1', 'kk001');
insert into kategori(idkategori, nama, kodekategori) values('k002', 'kategori2', 'kk002');
insert into kategori(idkategori, nama, kodekategori) values('k003', 'kategori3', 'kk003');
insert into kategori(idkategori, nama, kodekategori) values('k004', 'kategori4', 'kk004');
insert into kategori(idkategori, nama, kodekategori) values('k005', 'kategori5', 'kk005');
insert into kategori(idkategori, nama, kodekategori) values('k006', 'kategori6', 'kk006');
insert into kategori(idkategori, nama, kodekategori) values('k007', 'kategori7', 'kk007');
insert into kategori(idkategori, nama, kodekategori) values('k008', 'kategori8', 'kk008');
insert into kategori(idkategori, nama, kodekategori) values('k009', 'kategori9', 'kk009');


create table gudang(

    idgudang int not null auto_increment,
    nama varchar(255) not null,
    deskripsi varchar(255) not null,

    constraint fk_gudang_idgudang primary key(idgudang)
);

insert into gudang(idgudang, nama, deskripsi) values(1, 'gudang a', 'gudang makanan');
insert into gudang(idgudang, nama, deskripsi) values(2, 'gudang b', 'gudang minuman');
insert into gudang(idgudang, nama, deskripsi) values(3, 'gudang c', 'gudang minyak');
insert into gudang(idgudang, nama, deskripsi) values(4, 'gudang d', 'gudang snack');
insert into gudang(idgudang, nama, deskripsi) values(5, 'gudang e', 'gudang susu');

create table produk(

    idproduk int not null auto_increment,
    nama varchar(255) not null,
    deskripsi varchar(255) not null,
    harga int not null,
    tanggal_kadaluarsa date not null,
    idgudang int not null,

    constraint pk_produk_idproduk primary key(idproduk),
    constraint fk_produk_idgudang foreign key(idgudang) references gudang(idgudang)
);

insert into produk(nama, deskripsi, harga, tanggal_kadaluarsa, idgudang)
values('susu milo', 'susu', 3500, '2020-12-01', 5);
insert into produk(nama, deskripsi, harga, tanggal_kadaluarsa, idgudang)
values('minyak bimoli', 'minyak', 80000, '2019-12-01', 3);
insert into produk(nama, deskripsi, harga, tanggal_kadaluarsa, idgudang)
values('indomie goreng', 'indomie', 1500, '2020-12-01', 1);
insert into produk(nama, deskripsi, harga, tanggal_kadaluarsa, idgudang)
values('aqua', 'air mineral', 1000, '2090-12-01', 2);
insert into produk(nama, deskripsi, harga, tanggal_kadaluarsa, idgudang)
values('susu hilo', 'susu', 3500, '2020-12-01', 5);
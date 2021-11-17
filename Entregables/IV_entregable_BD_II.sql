-- Entregable 3, proyecto, grupo 04
-- Integrantes: 
	-- Marlon Freer Acevedo 117190458 
	-- Diana Fallas Méndez 117770654
	-- Johnny Chacón Gairaud 115540497
	-- Giancarlo Alvarado Sánchez 117230466
conn system/system;

PROMPT Creacion de tablas
create table system.area(
id_area NUMBER GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1),
descripcion varchar2(30) not null,
constraint area_pk primary key(id_area)
);

create table system.seco(
id_seco NUMBER GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1),
ean number(13,0) not null,
descripcion varchar2(30) not null,
precio float not null,
cantidad int not null,
area_id int not null,
constraint seco_pk primary key(id_seco),
constraint seco_uk_ean unique(ean),
constraint area_fk_producto foreign key(area_id) references system.area(id_area)
);

create table system.fresco(
id_fresco NUMBER GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1),
plu int not null,
peso float not null,
ean number(13,0) not null,
descripcion varchar2(30) not null,
precio float not null,
constraint fresco_pk primary key(id_fresco),
constraint fresco_uk_ean unique(ean)
);

create table system.caja(
id_caja NUMBER GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1),
usuario varchar2(50) null,
constraint caja_pk primary key(id_caja)
);

create table system.factura(
id_factura NUMBER GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1),
numero int not null,
fecha date not null,
total float not null,
usuario varchar2(60) not null,
num_caja int not null,
constraint factura_pk primary key(id_factura),
constraint caja_fk_factura foreign key(num_caja) references system.caja(id_caja)
);

create table system.detalleSeco(
id_detalleSeco NUMBER GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1),
cantidad int not null,
subtotal float not null,
seco_id int not null,
factura_id int not null,
constraint detalleseco_pk primary key(id_detalleSeco),
constraint seco_fk_detalle foreign key(seco_id) references system.seco(id_seco),
constraint factura_fk_detalle foreign key(factura_id)
references system.factura(id_factura)
);

create table system.detalleFresco(
id_detalleFresco NUMBER GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1),
peso float not null,
subtotal float not null,
fresco_id int not null,
factura_id int not null,
constraint detalleFresco_pk primary key(id_detalleFresco),
constraint fresco_fk_detalle foreign key(fresco_id) references system.fresco(id_fresco),
constraint facturaFresco_fk_detalle foreign key(factura_id)
references system.factura(id_factura)
);

conn system/system as sysdba
alter session set "_oracle_script"=true;

PROMPT Crear cajeros
create user maria_fallas_mendez identified by 1234
default tablespace users
temporary tablespace temp
quota unlimited on users;
create user johnny_chacon_gairaud identified by 1234
default tablespace users
temporary tablespace temp
quota unlimited on users;
create user jose_perez_zamora identified by 1234
default tablespace users
temporary tablespace temp
quota unlimited on users;

PROMPT Crear gerentes de area
create user giancarlo_alvarado_sanchez identified by 1234
default tablespace users
temporary tablespace temp
quota unlimited on users;
create user juan_hernandez_ramirez identified by 1234
default tablespace users
temporary tablespace temp
quota unlimited on users;
create user samantha_arroyo_arrieta identified by 1234
default tablespace users
temporary tablespace temp
quota unlimited on users;
create user marlon_freer_acevedo identified by 1234
default tablespace users
temporary tablespace temp
quota unlimited on users;

PROMPT Crear gerentes generales
create user alex_soto_moreira identified by 1234
default tablespace users
temporary tablespace temp
quota unlimited on users;
create user david_camacho_melendez identified by 1234
default tablespace users
temporary tablespace temp
quota unlimited on users;

PROMPT Crear dos personas en el area de sistemas
create user diana_quiros_ugalde identified by 1234
default tablespace users
temporary tablespace temp
quota unlimited on users;
create user tatiana_torres_fernandez identified by 1234
default tablespace users
temporary tablespace temp
quota unlimited on users;

PROMPT Crear roles
create role cajero;
create role gerente_abarrotes;
create role gerente_cuidado_personal;
create role gerente_mercancias;
create role gerente_frescos;
create role gerente_general;
create role sistemas;

grant select on dba_role_privs to cajero;
grant select on dba_role_privs to gerente_abarrotes;
grant select on dba_role_privs to gerente_cuidado_personal;
grant select on dba_role_privs to gerente_mercancias;
grant select on dba_role_privs to gerente_frescos;
grant select on dba_role_privs to gerente_general;
grant select on dba_role_privs to sistemas;

grant execute on UTL_MATCH to cajero;
grant execute on UTL_MATCH to gerente_abarrotes;
grant execute on UTL_MATCH to gerente_cuidado_personal;
grant execute on UTL_MATCH to gerente_mercancias;
grant execute on UTL_MATCH to gerente_frescos;
grant execute on UTL_MATCH to gerente_general;
grant execute on UTL_MATCH to sistemas;
 
PROMPT Asignacion de permisos generales para cada rol
grant connect, resource to cajero;
grant create session to cajero;
grant select on system.factura to cajero;
grant insert on system.factura to cajero;
grant select on system.fresco to cajero;
grant select on system.seco to cajero;
grant create any sequence to cajero;

grant connect, resource to gerente_abarrotes;
grant create session to gerente_abarrotes;

grant connect, resource to gerente_cuidado_personal;
grant create session to gerente_cuidado_personal;

grant connect, resource to gerente_mercancias;
grant create session to gerente_mercancias;

grant connect, resource to gerente_frescos;
grant create session to gerente_frescos;

grant connect, resource to gerente_general;
grant create session to gerente_general;

grant connect, resource to sistemas;
grant create session to sistemas;

PROMPT Asignacion de roles
grant cajero to maria_fallas_mendez;
grant cajero to johnny_chacon_gairaud;
grant cajero to jose_perez_zamora;

conn maria_fallas_mendez/1234;
CREATE SEQUENCE numero_factura_seq START WITH 100 INCREMENT BY 1;

conn johnny_chacon_gairaud/1234;
CREATE SEQUENCE numero_factura_seq START WITH 100 INCREMENT BY 1;

conn jose_perez_zamora/1234;
CREATE SEQUENCE numero_factura_seq START WITH 100 INCREMENT BY 1;

conn system/system;

grant gerente_abarrotes to giancarlo_alvarado_sanchez;
grant gerente_cuidado_personal to juan_hernandez_ramirez;
grant gerente_mercancias to samantha_arroyo_arrieta;
grant gerente_frescos to marlon_freer_acevedo;

grant gerente_general to alex_soto_moreira;
grant gerente_general to david_camacho_melendez;

grant sistemas to diana_quiros_ugalde;
grant sistemas to tatiana_torres_fernandez;

PROMPT Asignacion de permisos para cajeros
grant select on system.seco to cajero;
grant select on system.fresco to cajero;
grant select on system.caja to cajero;

PROMPT Asignacion de permisos para gerentes generales
grant select, update, delete, insert on system.area to gerente_general;
grant select, update, delete, insert on system.seco to gerente_general;
grant select, update, delete, insert on system.fresco to gerente_general;

PROMPT Asignacion de permisos para gerentes de area

CREATE VIEW vista_producto_abarrotes AS SELECT id_seco, EAN, descripcion, precio, cantidad, area_id FROM system.seco WHERE area_id = 1;
CREATE VIEW vista_producto_cuidado_personal AS SELECT id_seco, EAN, descripcion, precio, cantidad, area_id FROM  system.seco WHERE area_id = 2;
CREATE VIEW vista_producto_mercancias AS SELECT id_seco, EAN, descripcion, precio, cantidad, area_id FROM  system.seco WHERE area_id = 3;
CREATE VIEW vista_producto_frescos AS SELECT id_fresco, PLU, EAN, descripcion, peso, precio FROM  system.fresco;

grant select on system.area to gerente_abarrotes;
grant select on system.area to gerente_cuidado_personal;
grant select on system.area to gerente_frescos;
grant select on system.area to gerente_mercancias;

grant select on system.seco to gerente_abarrotes;
grant select on system.seco to gerente_cuidado_personal;
grant select on system.seco to gerente_frescos;
grant select on system.seco to gerente_mercancias;

grant select on system.fresco to gerente_abarrotes;
grant select on system.fresco to gerente_cuidado_personal;
grant select on system.fresco to gerente_frescos;
grant select on system.fresco to gerente_mercancias;

grant update on system.area to gerente_abarrotes;
grant update on system.area to gerente_cuidado_personal;
grant update on system.area to gerente_frescos;
grant update on system.area to gerente_mercancias;

grant update on system.seco to gerente_abarrotes;
grant update on system.seco to gerente_cuidado_personal;
grant update on system.seco to gerente_frescos;
grant update on system.seco to gerente_mercancias;

grant update on system.fresco to gerente_abarrotes;
grant update on system.fresco to gerente_cuidado_personal;
grant update on system.fresco to gerente_frescos;
grant update on system.fresco to gerente_mercancias;

GRANT select, update on vista_producto_abarrotes to gerente_abarrotes;
GRANT select, update on vista_producto_cuidado_personal to gerente_cuidado_personal;
GRANT select, update on vista_producto_frescos to gerente_frescos;
GRANT select, update on vista_producto_mercancias to gerente_mercancias;

PROMPT Asignacion de permisos para sistemas

grant select any table to sistemas;
grant update any table to sistemas;
grant insert any table to sistemas;
grant delete any table to sistemas;

PROMPT Creacion tabla de auditoria

create table system.auditoria(
id_auditoria NUMBER GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1),
usuario varchar2(60) not null,
tabla varchar2(50) not null,
transaccion varchar2(10) not null,
fecha date not null,
constraint auditoria_pk primary key(id_auditoria)
);

PROMPT asignacion de permisos de seleccion al rol de sistemas
PROMPT son los unicos con permiso de consulta a estas tablas

grant select on system.auditoria to sistemas;
--grant select on system.venta to sistemas;

PROMPT Creacion de triggers para insertar en las bitacoras despues de cada movimiento

create or replace trigger auditoria_area_trig 
after insert or update or delete on system.area
for each row
declare
	accion varchar(10);
begin
	IF UPDATING THEN 
     accion:='Update';            
	ELSIF DELETING THEN 
        accion:='Delete';
	ELSE
      accion:='Insert';
	END IF;
	
	insert into system.auditoria (usuario, tabla, transaccion,fecha)
		values(user,'area',accion,sysdate);
end auditoria_area_trig;
/

create or replace trigger auditoria_produc_trig 
after insert or update or delete on system.seco
for each row
declare
	accion varchar(10);
begin
	IF UPDATING THEN 
     accion:='Update';            
	ELSIF DELETING THEN 
        accion:='Delete';
	ELSE
      accion:='Insert';
	END IF;
	
	insert into system.auditoria (usuario, tabla, transaccion,fecha)
		values(user,'seco',accion,sysdate);
end auditoria_produc_trig;
/

create or replace trigger auditoria_produc_fresco_trig 
after insert or update or delete on system.fresco
for each row
declare
	accion varchar(10);
begin
	IF UPDATING THEN 
     accion:='Update';            
	ELSIF DELETING THEN 
        accion:='Delete';
	ELSE
      accion:='Insert';
	END IF;
	
	insert into system.auditoria (usuario, tabla, transaccion,fecha)
		values(user,'fresco',accion,sysdate);
end auditoria_produc_fresco_trig;
/

create or replace trigger auditoria_fac_trig 
after insert or update or delete on system.factura
for each row
declare
	accion varchar(10);
begin
	IF UPDATING THEN 
     accion:='Update';            
	ELSIF DELETING THEN 
        accion:='Delete';
	ELSE
      accion:='Insert';
	END IF;
	
	insert into system.auditoria (usuario, tabla, transaccion,fecha)
		values(user,'factura',accion,sysdate);
end auditoria_fac_trig;
/

create or replace trigger auditoria_det_seco_trig 
after insert or update or delete on system.detalleSeco
for each row
declare
	accion varchar(10);
begin
	IF UPDATING THEN 
     accion:='Update';            
	ELSIF DELETING THEN 
        accion:='Delete';
	ELSE
      accion:='Insert';
	END IF;
	
	insert into system.auditoria (usuario, tabla, transaccion,fecha)
		values(user,'detalle_seco',accion,sysdate);
end auditoria_det_seco_trig;
/

create or replace trigger auditoria_det_fresco_trig 
after insert or update or delete on system.detalleFresco
for each row
declare
	accion varchar(10);
begin
	IF UPDATING THEN 
     accion:='Update';            
	ELSIF DELETING THEN 
        accion:='Delete';
	ELSE
      accion:='Insert';
	END IF;
	
	insert into system.auditoria (usuario, tabla, transaccion,fecha)
		values(user,'detalle_fresco',accion,sysdate);
end auditoria_det_fresco_trig;
/

PROMPT LA AUDITORIA DE LOS SELECT SE HARA POR MEDIO DEL COMANDO AUDIT, ESTO PORQUE NO SE PUEDE CREAR UN TRIGGER DEL SELECT.
Select name, value  from v$parameter where name like 'audit_trail';
AUDIT SELECT TABLE BY maria_fallas_mendez BY ACCESS;
AUDIT SELECT TABLE BY marlon_freer_acevedo BY ACCESS;
AUDIT SELECT TABLE BY johnny_chacon_gairaud BY ACCESS;
AUDIT SELECT TABLE BY jose_perez_zamora BY ACCESS;
AUDIT SELECT TABLE BY juan_hernandez_ramirez BY ACCESS;
AUDIT SELECT TABLE BY giancarlo_alvarado_sanchez BY ACCESS;
AUDIT SELECT TABLE BY alex_soto_moreira BY ACCESS;
AUDIT SELECT TABLE BY david_camacho_melendez BY ACCESS;
AUDIT SELECT TABLE BY diana_quiros_ugalde BY ACCESS;
AUDIT SELECT TABLE BY tatiana_torres_fernandez BY ACCESS;

PROMPT Inserciones a las tablas
----------------------------------------------------------------------------------------------------------------------------------------
PROMPT Area
------------------------------------------------------------------------------------------------------------------------------------
insert into system.area(descripcion) values ('Abarrotes');
insert into system.area(descripcion) values ('Cuidado personal');
insert into system.area(descripcion) values ('Mercancias');

-----------------------------------------------------------------------------------------------------------------------------------------
PROMPT SECOS
--------------------------------------------------------------------------------------------------------------------------------------
PROMPT Abarrotes
insert into system.seco(ean, descripcion, precio, cantidad, area_id) values (1234567895246, 'Arroz', 1800, 10, 1);
insert into system.seco(ean, descripcion, precio, cantidad, area_id) values (1234567745246, 'Frijol', 950, 10, 1);
PROMPT Cuidado personal
insert into system.seco(ean, descripcion, precio, cantidad, area_id) values (1238527745246, 'Shampoo', 4850, 80, 2);
insert into system.seco(ean, descripcion, precio, cantidad, area_id) values (1232587745246, 'Protex Avena', 1340, 35, 2);
PROMPT Mercancias
insert into system.seco(ean, descripcion, precio, cantidad, area_id) values (1238527789646, 'Silla metal', 12500, 5, 3);
insert into system.seco(ean, descripcion, precio, cantidad, area_id) values (1232587745123, 'Basurero', 3540, 10, 3);
-------------------------------------------------------------------------------------------------------------------------------------------
PROMPT FRESCOS
insert into system.fresco(plu, peso, ean, descripcion, precio) values (52634, 2.6, 1232784525123, 'Papaya', 950);
insert into system.fresco(plu, peso, ean, descripcion, precio) values (52414, 3.5, 1232723625123, 'Melon', 1250);
--------------------------------------------------------------------------------------------------------------------------------------------

---------------------------------------------------------------------------------------------------------------------------------------------
PROMPT Caja
---------------------------------------------------------------------------------------------------------------------------------------------
insert into system.caja(usuario) values ('maria_fallas_mendez');
insert into system.caja(usuario) values ('johnny_chacon_gairaud');
insert into system.caja(usuario) values ('jose_perez_zamora');
insert into system.caja(usuario) values ('maria_fallas_mendez');
-----------------------------------------------------------------------------------------------------------------------------------------------
PROMPT Facturas
----------------------------------------------------------------------------------------------------------------------------------------------
insert into system.factura(numero, fecha, total, usuario, num_caja) values (101, TO_DATE('2021-09-11', 'YYYY-MM-DD'), 5485, 'maria_fallas_mendez', 1);
insert into system.factura(numero, fecha, total, usuario, num_caja) values (102, TO_DATE('2021-09-01', 'YYYY-MM-DD'), 12365, 'johnny_chacon_gairaud', 2);
insert into system.factura(numero, fecha, total, usuario, num_caja) values (103, TO_DATE('2021-09-12', 'YYYY-MM-DD'), 78412, 'jose_perez_zamora', 3);
insert into system.factura(numero, fecha, total, usuario, num_caja) values (104, TO_DATE('2021-09-12', 'YYYY-MM-DD'), 950, 'jose_perez_zamora', 3);
insert into system.factura(numero, fecha, total, usuario, num_caja) values (105, TO_DATE('2021-09-12', 'YYYY-MM-DD'), 1250, 'jose_perez_zamora', 3);
-------------------------------------------------------------------------------------------------------------------------------------------------------

PROMPT DetalleSeco
-----------------------------------------------------------------------------------------------------------------------------------------------------
insert into system.detalleSeco(cantidad,subtotal, seco_id, factura_id) values (1, 5485, 3, 1);
insert into system.detalleSeco(cantidad,subtotal, seco_id, factura_id) values (7, 12365, 1, 2);
insert into system.detalleSeco(cantidad,subtotal, seco_id, factura_id) values (22, 78412, 6, 3);
----------------------------------------------------------------------------------------------------------------------------------------------------------
PROMPT Detalle fresco
---------------------------------------------------------------------------------------------------------------------------------------------------------
insert into system.detalleFresco(peso,subtotal, fresco_id, factura_id) values (2.5, 950, 1, 3);
insert into system.detalleFresco(peso,subtotal, fresco_id, factura_id) values (3.2, 1250, 2, 3);
-------------------------------------------------------------------------------------------------------------------------------------------------
insert into system.auditoria(usuario, tabla, transaccion,fecha) values('malillon','tabla3','insert','02-02-2021');

-- Entregable 3, proyecto, grupo 04
-- Integrantes: 
	-- Marlon Freer Acevedo 117190458 
	-- Diana Fallas Méndez 117770654
	-- Johnny Chacón Gairaud 115540497
	-- Giancarlo Alvarado Sánchez 117230466
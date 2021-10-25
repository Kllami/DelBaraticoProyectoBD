-- Entregable 2, proyecto, grupo 04
-- Integrantes: 
	-- Marlon Freer Acevedo 117190458 
	-- Diana Fallas Méndez 117770654
	-- Johnny Chacón Gairaud 115540497
	-- Giancarlo Alvarado Sánchez 117230466
	
conn system/system as sysdba
alter session set "_oracle_script"=true;

PROMPT Crear cajeros
create user maria_fallas_mendez identified by 1234;
create user johnny_chacon_gairaud identified by 1234;
create user jose_perez_zamora identified by 1234;

PROMPT Crear gerentes de area
create user giancarlo_alvarado_sanchez identified by 1234;
create user juan_hernandez_ramirez identified by 1234;
create user samantha_arroyo_arrieta identified by 1234;
create user marlon_freer_acevedo identified by 1234;

PROMPT Crear gerentes generales
create user alex_soto_moreira identified by 1234;
create user david_camacho_melendez identified by 1234;

PROMPT Crear dos personas en el area de sistemas
create user diana_quiros_ugalde identified by 1234;
create user tatiana_torres_fernandez identified by 1234;

PROMPT Crear roles
create role cajero;
create role gerente_abarrotes;
create role gerente_cuidado_personal;
create role gerente_mercancias;
create role gerente_frescos;
create role gerente_general;
create role sistemas;

PROMPT Asignacion de permisos generales para cada rol
grant connect, resource to cajero;
grant create session to cajero;

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

grant gerente_abarrotes to giancarlo_alvarado_sanchez;
grant gerente_cuidado_personal to juan_hernandez_ramirez;
grant gerente_mercancias to samantha_arroyo_arrieta;
grant gerente_frescos to marlon_freer_acevedo;

grant gerente_general to alex_soto_moreira;
grant gerente_general to david_camacho_melendez;

grant sistemas to diana_quiros_ugalde;
grant sistemas to tatiana_torres_fernandez; 

PROMPT Creacion de tablas
create table system.area(
id_area int not null,
descripcion varchar2(30) not null,
constraint area_pk primary key(id_area)
);

create table system.producto(
id_producto int not null,
plu int null,
ean long null,
descripcion varchar2(30) not null,
peso float null,
precio float not null,
cantidad int null,
area_id int not null,
constraint producto_pk primary key(id_producto),
constraint area_fk_producto foreign key(area_id) 
references system.area(id_area)
);

create table system.factura(
id_factura int not null,
numero int not null,
fecha date not null,
total float not null,
constraint factura_pk primary key(id_factura)
);

create table system.detalle(
id_detalle int not null,
cantidad int not null,
subtotal float not null,
producto_id int not null,
factura_id int not null,
constraint detalle_pk primary key(id_detalle),
constraint producto_fk_detalle foreign key(producto_id) references system.producto(id_producto),
constraint factura_fk_detalle foreign key(factura_id)
references system.factura(id_factura)
);

PROMPT Asignacion de permisos para cajeros
grant select on system.producto to cajero;

PROMPT Asignacion de permisos para gerentes generales
grant select, update, delete, insert on system.producto to gerente_general;

PROMPT Asignacion de permisos para gerentes de area

CREATE VIEW vista_producto_abarrotes AS SELECT id_producto, PLU, EAN, descripcion, peso, precio, cantidad, area_id FROM system.producto WHERE area_id = 1;
CREATE VIEW vista_producto_cuidado_personal AS SELECT id_producto, PLU, EAN, descripcion, peso, precio, cantidad, area_id FROM  system.producto WHERE area_id = 2;
CREATE VIEW vista_producto_frescos AS SELECT id_producto, PLU, EAN, descripcion, peso, precio, cantidad, area_id FROM  system.producto WHERE area_id = 3;
CREATE VIEW vista_producto_mercancias AS SELECT id_producto, PLU, EAN, descripcion, peso, precio, cantidad, area_id FROM  system.producto WHERE area_id = 4;

GRANT select, update on vista_producto_abarrotes to gerente_abarrotes;
GRANT select, update on vista_producto_cuidado_personal to gerente_cuidado_personal;
GRANT select, update on vista_producto_frescos to gerente_frescos;
GRANT select, update on vista_producto_mercancias to gerente_mercancias;

PROMPT Asignacion de permisos para sistemas

grant select any table to sistemas;
grant update any table to sistemas;
grant insert any table to sistemas;
grant delete any table to sistemas;

PROMPT Creacion tablas auditoria

create table system.auditoria(
id_auditoria NUMBER GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1),
usuario varchar2(60) not null,
tabla varchar2(50) not null,
transaccion varchar2(10) not null,
fecha date not null,
constraint auditoria_pk primary key(id_auditoria)
);

create table system.venta(
id_venta NUMBER GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1),
usuario varchar2(60) not null,
num_caja int not null,
factura_id int not null,
fecha date not null,
constraint venta_pk primary key(id_venta)
);

PROMPT asignacion de permisos de seleccion al rol de sistemas
PROMPT son los unicos con permiso de consulta a estas tablas

grant select on system.auditoria to sistemas;
grant select on system.venta to sistemas;

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
show error

create or replace trigger auditoria_produc_trig 
after insert or update or delete on system.producto
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
		values(user,'producto',accion,sysdate);
end auditoria_produc_trig;
/
show error

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
show error

create or replace trigger auditoria_det_trig 
after insert or update or delete on system.detalle
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
		values(user,'detalle',accion,sysdate);
end auditoria_det_trig;
/
show error


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


PROMPT Trigger para agregar las ventas.
create or replace trigger auditoria_vent_trig 
after insert on system.factura
for each row
declare
	num_caj int;
begin
	IF us = 'maria_fallas_mendez' THEN 
		num_caj:=1;            
	ELSIF us = 'johnny_chacon_gairaud' THEN 
        num_caj:=2;
	ELSE
		num_caj:=3;
	END IF;
	
	insert into system.venta (usuario, num_caja, factura_id,fecha)
		values(user,num_caj,:new.id_factura,sysdate);
end auditoria_vent_trig;
/
show error

-- Entregable 2, proyecto, grupo 04
-- Integrantes: 
	-- Marlon Freer Acevedo 117190458 
	-- Diana Fallas Méndez 117770654
	-- Johnny Chacón Gairaud 115540497
	-- Giancarlo Alvarado Sánchez 117230466
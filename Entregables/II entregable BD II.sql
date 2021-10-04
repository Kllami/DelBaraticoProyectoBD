-- Entregable 2, proyecto, grupo 04
-- Integrantes: 
	-- Marlon Freer Acevedo 117190458 
	-- Diana Fallas Méndez 117770654
	-- Johnny Chacón Gairaud 115540497
	-- Giancarlo Alvarado Sánchez 117230466
	

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


-- Entregable 2, proyecto, grupo 04
-- Integrantes: 
	-- Marlon Freer Acevedo 117190458 
	-- Diana Fallas Méndez 117770654
	-- Johnny Chacón Gairaud 115540497
	-- Giancarlo Alvarado Sánchez 117230466
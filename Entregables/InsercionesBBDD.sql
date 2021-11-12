-- Inserciones a las tablas
----------------------------------------------------------------------------------------------------------------------------------------
--Area
------------------------------------------------------------------------------------------------------------------------------------
insert into system.area(id_area, descripcion) values (1, 'Abarrotes');
insert into system.area(id_area, descripcion) values (2, 'Cuidado personal');
insert into system.area(id_area, descripcion) values (3, 'Mercancias');

-----------------------------------------------------------------------------------------------------------------------------------------
--SECOS
--------------------------------------------------------------------------------------------------------------------------------------
-- Abarrotes
insert into system.seco(id_seco, ean, descripcion, precio, cantidad, area_id) values (1, 1234567895246, 'Arroz', 1800, 10, 1);
insert into system.seco(id_seco, ean, descripcion, precio, cantidad, area_id) values (2, 1234567745246, 'Frijol', 950, 10, 1);
-- Cuidado personal
insert into system.seco(id_seco, ean, descripcion, precio, cantidad, area_id) values (3, 1238527745246, 'Shampoo', 4850, 80, 2);
insert into system.seco(id_seco, ean, descripcion, precio, cantidad, area_id) values (4, 1232587745246, 'Protex Avena', 1340, 35, 2);
-- Mercancias
insert into system.seco(id_seco, ean, descripcion, precio, cantidad, area_id) values (5, 1238527789646, 'Silla metal', 12500, 5, 3);
insert into system.seco(id_seco, ean, descripcion, precio, cantidad, area_id) values (6, 1232587745123, 'Basurero', 3540, 10, 3);
-------------------------------------------------------------------------------------------------------------------------------------------
--FRESCOS
insert into system.fresco(plu, peso, ean, descripcion, precio, cantidad) values (52634, 2.6, 1232784525123, 'Papaya', 950, 10);
insert into system.fresco(plu, peso, ean, descripcion, precio, cantidad) values (52414, 3.5, 1232723625123, 'Melon', 1250, 10);
--------------------------------------------------------------------------------------------------------------------------------------------

---------------------------------------------------------------------------------------------------------------------------------------------
-- Caja
---------------------------------------------------------------------------------------------------------------------------------------------
insert into system.caja(id_caja, usuario) values (1, 'maria_fallas_mendez');
insert into system.caja(id_caja, usuario) values (2, 'johnny_chacon_gairaud');
insert into system.caja(id_caja, usuario) values (3, 'jose_perez_zamora');
insert into system.caja(id_caja, usuario) values (4, 'maria_fallas_mendez');
-----------------------------------------------------------------------------------------------------------------------------------------------
--Facturas
----------------------------------------------------------------------------------------------------------------------------------------------
insert into system.factura(numero, fecha, total, usuario, num_caja) values (numero_factura_seq.NEXTVAL, TO_DATE('2021-09-11', 'YYYY-MM-DD'), 5485, 'maria_fallas_mendez', 1);
insert into system.factura(numero, fecha, total, usuario, num_caja) values (numero_factura_seq.NEXTVAL, TO_DATE('2021-09-01', 'YYYY-MM-DD'), 12365, 'johnny_chacon_gairaud', 2);
insert into system.factura(numero, fecha, total, usuario, num_caja) values (numero_factura_seq.NEXTVAL, TO_DATE('2021-09-12', 'YYYY-MM-DD'), 78412, 'jose_perez_zamora', 3);
insert into system.factura(numero, fecha, total, usuario, num_caja) values (numero_factura_seq.NEXTVAL, TO_DATE('2021-09-12', 'YYYY-MM-DD'), 950, 'jose_perez_zamora', 3);
insert into system.factura(numero, fecha, total, usuario, num_caja) values (numero_factura_seq.NEXTVAL, TO_DATE('2021-09-12', 'YYYY-MM-DD'), 1250, 'jose_perez_zamora', 3);
-------------------------------------------------------------------------------------------------------------------------------------------------------

--DetalleSeco
-----------------------------------------------------------------------------------------------------------------------------------------------------
insert into system.detalleSeco(id_detalleSeco, cantidad,subtotal, seco_id, factura_id) values (1, 1, 5485, 3, 1);
insert into system.detalleSeco(id_detalleSeco, cantidad,subtotal, seco_id, factura_id) values (2, 7, 12365, 1, 2);
insert into system.detalleSeco(id_detalleSeco, cantidad,subtotal, seco_id, factura_id) values (3, 22, 78412, 6, 3);
----------------------------------------------------------------------------------------------------------------------------------------------------------
-- Detalle fresco
---------------------------------------------------------------------------------------------------------------------------------------------------------
insert into system.detalleFresco(id_detalleFresco, peso,subtotal, fresco_id, factura_id) values (1, 2.5, 950, 1, 3);
insert into system.detalleFresco(id_detalleFresco, peso,subtotal, fresco_id, factura_id) values (2, 3.2, 1250, 2, 3);
-------------------------------------------------------------------------------------------------------------------------------------------------
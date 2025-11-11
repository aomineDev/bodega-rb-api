---------------------------- Proveedores --------------------------------------

-- Personas
INSERT INTO personas (direccion, telefono, email) VALUES 
('AV. MICHAEL FARADAY NRO 111 Z.I. SANTA ROSA ', '934567788', 'braedt@gmail.com'),
('CAR. PANAMERICANA NORTE KM. 84 ', '957867322', 'ottokunz@gmail.com'),
('AV. NICOLAS DE PIEROLA NRO 601 FND. LA ESTRELLA PARCELA M,N,F ', '937442232', 'laive@gmail.com'),
('AV. LAS JOJOBAS MZA. B LOTE 12 Z.I. ASOC. AGRIC. LA CONCORDIA ', '923521345', 'casaEuropa@gmail.com'),
('AV. REPUBLICA DE PANAMA NRO 4575 ', '935643222', 'sanFernando@gmail.com'),
('CAL. CALLE 7 MZA. J LOTE 03 URB. CAMPOY ', '963468644', 'cerdeña@gmail.com'),
('AV. REPUBLICA DE PANAMA NRO 2461 URB. SANTA CATALINA ', '971283782', 'gloria@gmail.com'),
('AV. VENEZUELA NRO 2850 URB. ELIO ', '963645455', 'molitalia@gmail.com'),
('AV. VIA EXPRESA LUIS FERNAN B NRO 2455 ', '975646633', 'ajimoto@gmail.com')

-- Personas juridicas
INSERT INTO personas_juridicas (persona_id, ruc, razon_social, tipo_contribuyente, actividad_economica) VALUES 
(2, '20100067910', 'BRAEDT S.A. Ó BRSA', 'SOCIEDAD ANONIMA', 'ELABORACION Y CONSERVACION DE CARNE'),
(3, '20136974697', 'SOCIEDAD SUIZO PERUANA DE EMBUTIDOS S.A. ó SUPEMSA', 'SOCIEDAD ANONIMA', 'ELABORACION DE OTROS PRODUCTOS ALIMENTICIOS NO CLASIFICADOS PREVIAMENTE'),
(4, '20100095450', 'LAIVE S A', 'SOCIEDAD ANONIMA', 'ELABORACION DE PRODUCTOS LACTEOS'),
(5, '20546793517', 'WESTPHALIA ALIMENTOS SOCIEDAD ANONIMA CERRADA - WESTPHALIA S.A.C.', 'SOCIEDAD ANONIMA CERRADA', 'ELABORACION DE OTROS PRODUCTOS ALIMENTICIOS NO CLASIFICADOS PREVIAMENTE'),
(6, '20100154308', 'SAN FERNANDO S.A.', 'SOCIEDAD ANONIMA', 'CRIA DE AVES DE CORRAL'),
(7, '20100171229', 'PROCESADORA DE ALIMENTOS TI-CAY S.R.L.', 'SOCIEDAD COMERCIAL DE RESPONSABILIDAD LIMITADA', 'ELABORACION Y CONSERVACION DE CARNE'),
(8, '20100190797', 'LECHE GLORIA SOCIEDAD ANONIMA - GLORIA S.A.', 'SOCIEDAD ANONIMA', 'ELABORACION DE PRODUCTOS LACTEOS'),
(9, '20100035121', 'MOLITALIA S.A', 'SOCIEDAD ANONIMA', 'ELABORACION DE PRODUCTOS DE MOLINERIA'),
(10, '20100085063', 'AJINOMOTO DEL PERU S A', 'SOCIEDAD ANONIMA', 'ELABORACION DE OTROS PRODUCTOS ALIMENTICIOS NO CLASIFICADOS PREVIAMENTE')

-- Proveedores
INSERT INTO proveedores (persona_id, fecha_registro) VALUES 
(2, '2025-11-10'),
(3, '2025-11-10'),
(4, '2025-11-10'),
(5, '2025-11-10'),
(6, '2025-11-10'),
(7, '2025-11-10'),
(8, '2025-11-10'),
(9, '2025-11-10'),
(10, '2025-11-10')

---------------------------- Categorias --------------------------------------

INSERT INTO categorias (nombre, descripcion) VALUES
-- Embutidos principales
('Chorizos', 'Variedades de chorizos frescos, parrilleros y criollos.'),
('Jamonadas', 'Embutidos cocidos, ideales para sánguches y loncheras.'),
('Jamones', 'Cortes curados o cocidos de cerdo listos para servir.'),
('Salchichas', 'Embutidos cocidos tipo hot dog, parrilleras y especiales.'),
('Morcillas', 'Embutidos tradicionales elaborados con sangre y condimentos.'),
('Salames', 'Embutidos curados y madurados de sabor fuerte.'),
('Tocinos', 'Cortes de cerdo curados o ahumados, usados en desayunos y cocina.'),
('Pates', 'Pastas untable de carne o hígado, elaboradas con condimentos finos.'),
('Fiambres', 'Productos cárnicos listos para consumir, como jamón y mortadela.'),
('Fiambres Ahumados', 'Variedades de fiambres con sabor y aroma a humo.'),
('Prensados', 'Productos elaborados con carne prensada y cocida.'),

-- Lácteos y derivados
('Quesos', 'Quesos frescos, maduros y mantecosos.'),
('Mantequillas', 'Mantequillas y margarinas para untar o cocinar.'),
('Yogures', 'Bebidas y yogures naturales o con sabor.'),
('Leches', 'Leche entera, evaporada y deslactosada.'),

-- Aderezos y condimentos
('Mayonesas', 'Salsas base para sánguches, ensaladas y comidas rápidas.'),
('Ketchups', 'Salsas de tomate dulces o picantes.'),
('Mostazas', 'Salsas amarillas o dijon para carnes y sánguches.'),
('Ajíes y Salsas Picantes', 'Ají amarillo, rocoto y otros condimentos tradicionales.'),
('Sazonadores', 'Condimentos y sobres de sabor como Sibarita o Ajinomoto.')

---------------------------- Productos --------------------------------------

INSERT INTO productos (
  categoria_id,
  proveedor_id,
  codigo_barra,
  nombre,
  descripcion,
  precio_unitario,
  precio_promocion,
  inicio_promocion,
  fin_promocion,
  stock,
  unidad_medida,
  imagen
) VALUES

-- 1. Chorizo Cocido Parrillero San Fernando 480g
(1, 6, '941168', 'Chorizo Cocido Parrillero San Fernando 480g',
 'Chorizos cocidos tipo parrillero elaborados con carne de cerdo seleccionada. Paquete de 480 gramos, ideales para parrillas y reuniones.',
 13.50, 13.50, '2025-01-01', '2025-12-31', 110, 'Unidad', null),

-- 2. Chorizo Parrillero Otto Kunz 400g (4% de descuento)
(1, 3, '508118', 'Chorizo Parrillero Otto Kunz 400g',
 'Chorizos tipo parrillero con sabor ahumado característico de Otto Kunz. Presentación de 400 gramos.',
 16.50, 15.90, '2025-11-01', '2025-11-30', 95, 'Unidad', null),

-- 3. Pack Parrillero Casa Europa 450g
(1, 5, '745276', 'Pack Parrillero Casa Europa 450g',
 'Pack surtido de chorizos y embutidos especiales Casa Europa. Presentación de 450 gramos, ideal para parrilladas familiares.',
 24.50, 24.50, '2025-01-01', '2025-12-31', 70, 'Unidad', null),

-- 4. Chorizo Parrillero Fresco Braedt 500g (9% de descuento)
(1, 2, '564238', 'Chorizo Parrillero Fresco Braedt 500g',
 'Chorizo fresco de cerdo, sazonado al estilo Braedt, listo para asar o freír. Presentación de 500 gramos.',
 18.50, 16.90, '2025-11-01', '2025-11-30', 85, 'Unidad', null),

-- 1. Jamonada de Pavita San Fernando 85g
(2, 6, '399281', 'Jamonada de Pavita San Fernando 85g',
 'Jamonada elaborada con carne de pavita, ligera y práctica para desayunos y loncheras. Presentación de 85 gramos.',
 2.30, 2.30, '2025-01-01', '2025-12-31', 150, 'Unidad', NULL),

-- 2. Jamonada Polaca Otto Kunz 180g
(2, 3, '186444', 'Jamonada Polaca Otto Kunz 180g',
 'Jamonada tipo polaca con un sabor suave y textura firme, elaborada con carne de cerdo seleccionada. Presentación de 180 gramos.',
 8.90, 8.90, '2025-01-01', '2025-12-31', 120, 'Unidad', NULL),

-- 3. Twopack Jamonada Especial Suiza 200g
(2, 4, '989709', 'Twopack Jamonada Especial Suiza 200g',
 'Pack doble de jamonada especial Suiza, de sabor clásico y consistencia ideal para sánguches. Contiene dos porciones de 100g cada una.',
 12.90, 12.90, '2025-01-01', '2025-12-31', 100, 'Unidad', NULL),

-- 4. Jamonada Polaca Casa Europa 200g
(2, 5, '749200', 'Jamonada Polaca Casa Europa 200g',
 'Jamonada polaca Casa Europa, elaborada con carne de cerdo curada y especias finas. Presentación sellada de 200 gramos.',
 10.50, 10.50, '2025-01-01', '2025-12-31', 90, 'Unidad', NULL),

-- 1. Jamón Inglés Otto Kunz 200g
(3, 3, '42891', 'Jamón Inglés Otto Kunz 200g',
 'Jamón inglés tipo deli elaborado con carne de cerdo seleccionada. Presentación de 200 gramos, ideal para sánguches.',
 14.90, 14.90, '2025-01-01', '2025-12-31', 120, 'Unidad', null),

-- 2. Jamón de Pavita Braedt Balance 200g
(3, 2, '714136', 'Jamón de Pavita Braedt Balance 200g',
 'Jamón de pavita bajo en grasa, producto saludable de la línea Balance Braedt. Presentación sellada de 200g.',
 11.99, 11.99, '2025-01-01', '2025-12-31', 100, 'Unidad', null),

-- 3. Jamón Pizzero Braedt 480g (10% de descuento)
(3, 2, '526949', 'Jamón Pizzero Braedt 480g',
 'Jamón especial para pizzas y sánguches, con textura ideal para fundirse. Presentación de 480 gramos.',
 14.99, 13.50, '2025-11-01', '2025-11-30', 80, 'Unidad', null),

-- 4. Jamón Americano Suiza 500g (16% de descuento)
(3, 4, '574352', 'Jamón Americano Suiza 500g',
 'Jamón tipo americano con sabor suave y textura uniforme. Paquete sellado de 500 gramos.',
 14.90, 12.50, '2025-11-01', '2025-11-30', 90, 'Unidad', null),

-- 5. Jamón Inglés Casa Europa 200g
(3, 5, '767880', 'Jamón Inglés Casa Europa 200g',
 'Jamón inglés premium elaborado con carne de cerdo curada y cocida. Paquete de 200 gramos.',
 16.50, 16.50, '2025-01-01', '2025-12-31', 75, 'Unidad', null),

-- 1. Salchicha de Huacho Casa Europa 150g
(4, 5, '703787', 'Salchicha de Huacho Casa Europa 150g',
 'Salchicha artesanal tipo Huacho elaborada con carne de cerdo y condimentos naturales. Paquete de 150 gramos.',
 7.70, 7.70, '2025-01-01', '2025-12-31', 130, 'Unidad', NULL),

-- 2. Pack Salchicha Vienesa Especial Otto Kunz 750g (27% de descuento)
(4, 3, '545538', 'Pack Salchicha Vienesa Especial Otto Kunz 750g',
 'Pack familiar de salchichas tipo vienesa elaboradas con carne seleccionada. Presentación de 750 gramos.',
 18.50, 13.50, '2025-11-01', '2025-11-30', 100, 'Unidad', NULL),

-- 3. Hot Dog con Pollo Suiza 200g (7% de descuento)
(4, 4, '355376', 'Hot Dog con Pollo Suiza 200g',
 'Salchichas de pollo de sabor suave y textura ligera, ideales para hot dogs o comidas rápidas. Paquete de 200 gramos.',
 5.90, 5.50, '2025-11-01', '2025-11-30', 140, 'Unidad', NULL),

-- 4. Hot Dog de Pollo Ahumado San Fernando 220g
(4, 6, '444191', 'Hot Dog de Pollo Ahumado San Fernando 220g',
 'Salchichas de pollo ahumado con el sabor característico de San Fernando. Presentación de 220 gramos.',
 5.90, 5.90, '2025-01-01', '2025-12-31', 150, 'Unidad', NULL),

-- 1. Morcilla Cóctel Otto Kunz 220g
(5, 3, '733752', 'Morcilla Cóctel Otto Kunz 220g',
 'Morcillas tipo cóctel elaboradas con carne y sangre de cerdo condimentadas al estilo tradicional. Presentación de 220 gramos.',
 14.90, 14.90, '2025-01-01', '2025-12-31', 85, 'Unidad', NULL),

-- 2. Morcilla Cocktail Casa Europa 250g
(5, 5, '749197', 'Morcilla Cocktail Casa Europa 250g',
 'Morcillas pequeñas tipo cóctel con sabor intenso y especias finas. Presentación de 250 gramos, ideal para picadas y parrillas.',
 14.50, 14.50, '2025-01-01', '2025-12-31', 80, 'Unidad', NULL),

-- 1. Cabanossi Braedt x unidad (17% de descuento)
(6, 2, '251835', 'Cabanossi Braedt x Unidad',
 'Salame tipo cabanossi elaborado con carne de cerdo curada y especias selectas. Ideal como snack o acompañamiento.',
 3.50, 2.90, '2025-11-01', '2025-11-30', 200, 'Unidad', NULL),

-- 2. Salame Húngaro Braedt 150g
(6, 2, '327477', 'Salame Húngaro Braedt 150g',
 'Salame tipo húngaro con curado natural y sabor intenso, elaborado con carne de cerdo premium. Paquete de 150 gramos.',
 24.90, 24.90, '2025-01-01', '2025-12-31', 90, 'Unidad', NULL),

-- 3. Salame Húngaro Casa Europa 150g
(6, 5, '505211', 'Salame Húngaro Casa Europa 150g',
 'Salame curado tipo húngaro, de textura firme y sabor tradicional europeo. Presentación de 150 gramos.',
 24.50, 24.50, '2025-01-01', '2025-12-31', 85, 'Unidad', NULL),

-- 1. Tocino Natural Ahumado Cuisine & Co 200g
(7, 5, '1025881', 'Tocino Natural Ahumado Cuisine & Co 200g',
 'Tocino natural ahumado con leña, ideal para desayunos o preparaciones gourmet. Presentación de 200 gramos.',
 13.50, 13.50, '2025-01-01', '2025-12-31', 100, 'Unidad', NULL),

-- 2. Tocino Culinario Otto Kunz 500g
(7, 3, '946225', 'Tocino Culinario Otto Kunz 500g',
 'Tocino culinario premium, ahumado y listo para cocinar. Perfecto para pastas, hamburguesas o guisos. Presentación de 500 gramos.',
 27.50, 27.50, '2025-01-01', '2025-12-31', 80, 'Unidad', NULL),

-- 1. Paté de Hígado de Pollo San Fernando 100 g
(8, 6, '11791', 'Paté de Hígado de Pollo San Fernando 100 g',
 'Paté de hígado de pollo suave y cremoso, ideal para untar en panes o galletas. Presentación de 100 gramos.',
 3.90, 2.50, '2025-11-01', '2025-11-30', 120, 'Unidad', NULL),

-- 2. Paté A Las Finas Hierbas Casa Europa 150 g
(8, 5, '559205', 'Paté A Las Finas Hierbas Casa Europa 150 g',
 'Delicioso paté artesanal con un toque de finas hierbas. Perfecto para aperitivos y entradas. Presentación de 150 gramos.',
 10.20, 10.20, '2025-01-01', '2025-12-31', 90, 'Unidad', NULL),

-- 1. Queso Edam Laive en Tajadas 440g
(12, 4, '878610', 'Queso Edam Laive en Tajadas 440g',
 'Queso Edam en tajadas, ideal para sandwiches, hamburguesas y snacks. Presentación de 440 gramos.',
 24.50, 21.90, '2025-11-01', '2025-11-30', 85, 'Unidad', NULL),

-- 2. Queso Fresco Gloria Molde 400 g
(12, 8, '495730', 'Queso Fresco Gloria Molde 400 g',
 'Queso fresco tradicional, con textura suave y sabor ligero. Ideal para desayunos y acompañamientos. Presentación de 400 gramos.',
 38.90, 38.50, '2025-11-01', '2025-11-30', 100, 'Kilogramo', NULL);
--
-- Base de datos: club_tenis
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla pistas
--

select * from usuarios;

INSERT INTO usuarios (id, nombre, email, password, telefono, es_socio, Perfil) VALUES
    (0, 'admin', 'admin@club.com', '$2y$10$/ARhFCPMaPGKnS0RMRhuQOQ/aV5TRZ4s6ccepzt2OCMSzHxQ3hmrS', '123456789', 0, 'Administrador');







-- ANTIGUAS DE MYSQL
CREATE TABLE pistas (
  id int NOT NULL,
  nombre varchar(50) NOT NULL,
  tipo varchar(50) NOT NULL,
  descripcion text,
  precio_hora decimal(10,2) NOT NULL,
  imagen varchar(255) DEFAULT NULL
);

--
-- Volcado de datos para la tabla pistas
--

INSERT INTO pistas (id, nombre, tipo, descripcion, precio_hora, imagen) VALUES
(1, 'Pista Central', 'Tierra Batida', 'Pista principal del club con capacidad para espectadores', 1.00, '2wCEAAkGBxITEhUREhMWFhUVGBUVFhgVFxUXGBUVGBUXFxUXFRcYHSggGBolHRUVITEhJSkrLi4uFx8zODMsNygtLisBCgoKDg0OGxAQGy8lICUtLi0tLS0uLy0tLy0tLS0tLS0tLS0tLS0tLS0tKy0tLS0tLS0tLS0tLS0tLS0tLS0tLf'),
(2, 'Pista 2', 'Tierra Batida', 'Pista de arcilla', 1.00, NULL),
(3, 'Pista 3', 'Tierra Batida', 'Pista de superficie dura para todo tipo de juego', 1.00, NULL),
(4, 'Pista 4', 'Tierra Batida', '', 1.00, NULL),
(5, 'Pista 5', 'Dura', NULL, 1.00, NULL),
(6, 'Pista 6', 'Dura', NULL, 1.00, NULL),
(7, 'Pista Padel', 'Cesped Artificial', 'Es una gran oportunidad para disfrutar con tus amigas y pasar una rato divertido', 1.00, NULL),
(8, 'Fronton 1', 'Dura', NULL, 1.00, NULL),
(9, 'Fronton 2', 'Dura', NULL, 1.00, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla reservas
--

CREATE TABLE reservas (
  id int NOT NULL,
  usuario_id int NOT NULL,
  pista_id int NOT NULL,
  fecha date NOT NULL,
  hora_inicio time NOT NULL,
  hora_fin time NOT NULL,
  estado enum('pendiente','confirmada','cancelada') DEFAULT 'confirmada',
  fecha_reserva timestamp NULL DEFAULT CURRENT_TIMESTAMP
);

--
-- Volcado de datos para la tabla reservas
--

INSERT INTO reservas (id, usuario_id, pista_id, fecha, hora_inicio, hora_fin, estado, fecha_reserva) VALUES
(105, 3, 1, '2025-05-10', '19:00:00', '20:00:00', 'confirmada', '2025-05-10 16:30:24'),
(120, 3, 1, '2025-05-11', '20:00:00', '21:00:00', 'confirmada', '2025-05-11 17:17:22'),
(121, 3, 1, '2025-05-13', '18:00:00', '19:00:00', 'confirmada', '2025-05-13 15:51:55'),
(122, select id from usuario where nombre='Antonio', 1, '2025-05-13', '19:00:00', '20:00:00', 'confirmada', '2025-05-13 15:55:16'),
(124, 3, 1, '2025-05-14', '17:00:00', '18:00:00', 'confirmada', '2025-05-14 13:09:26'),
(126, 11, 4, '2025-05-14', '16:00:00', '17:00:00', 'confirmada', '2025-05-14 13:11:19'),
(127, 3, 1, '2025-05-15', '19:00:00', '20:00:00', 'confirmada', '2025-05-15 16:33:54'),
(129, 3, 1, '2025-05-15', '20:00:00', '21:00:00', 'confirmada', '2025-05-15 16:39:30'),
(130, 11, 1, '2025-05-15', '21:00:00', '22:00:00', 'confirmada', '2025-05-15 16:52:53'),
(131, 3, 3, '2025-05-17', '18:00:00', '19:00:00', 'confirmada', '2025-05-17 15:56:28'),
(132, 3, 1, '2025-05-17', '18:00:00', '19:00:00', 'confirmada', '2025-05-17 15:56:36'),
(133, 3, 1, '2025-05-17', '19:00:00', '20:00:00', 'confirmada', '2025-05-17 16:00:43'),
(135, 3, 7, '2025-05-18', '19:00:00', '20:00:00', 'confirmada', '2025-05-18 16:47:29'),
(136, 3, 7, '2025-05-20', '18:00:00', '19:00:00', 'confirmada', '2025-05-20 15:21:11'),
(138, 3, 1, '2025-05-21', '13:00:00', '14:00:00', 'confirmada', '2025-05-21 10:39:31'),
(139, 11, 1, '2025-05-21', '14:00:00', '15:00:00', 'confirmada', '2025-05-21 10:41:22'),
(140, 3, 1, '2025-05-22', '14:00:00', '15:00:00', 'confirmada', '2025-05-22 11:02:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla usuarios
--

CREATE TABLE usuarios (
  id int NOT NULL,
  nombre varchar(100) NOT NULL,
  email varchar(100) NOT NULL,
  password varchar(255) NOT NULL,
  telefono varchar(20) DEFAULT NULL,
  es_socio tinyint(1) DEFAULT '0',
  fecha_registro timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  Perfil enum('Administrador','Socio') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla usuarios
--

INSERT INTO usuarios (id, nombre, email, password, telefono, es_socio, fecha_registro, Perfil) VALUES
(2, 'Josevi', 'josevi@club.com', '123456', '65467675', 1, '2025-04-08 14:34:54', 'Administrador'),
(3, 'adrian', 'adrian@club.com', 'adri', '6666666666', 1, '2025-04-12 16:13:11', 'Administrador'),
(4, 'Marta Peirats', 'marta@club.com', '$2y$10$nTxyOD7bq.HFsTOm0LjKo.ZDHX2xnh99XbR18u79Fj3LoR8bruXnG', '123456789', 0, '2025-05-10 16:47:50', 'Administrador'),
(6, 'Alfonso', 'alfonso@club.com', '$2y$10$XtZVTvp/H0CdLbyZp/nKROL3uoeHp.UnLD1QP5tSGovQH/HwB9MZe', '123456789', 0, '2025-05-10 17:38:40', 'Administrador'),
(10, 'alvaro', 'alvaro@club.com', '$2y$10$Bc0zYL/aMqpFk6GJ7lFAIeseoUH2lndDdE5LW6obJ1zHeD/KA1Cza', '123456789', 0, '2025-05-11 10:42:35', 'Administrador'),
(11, 'antonio', 'a@club.com', '$2y$10$P1Q6YS0NVyfYG99.rBSrc.psAMmFKlxQDtXjA6ifmtW3dZLalzmaG', '123456789', 0, '2025-05-11 10:43:48', 'Socio'),
(12, 'Ana Maria', 'anam@club.com', '$2y$10$FTmN8D2uF5txz86dA9MVRemanrUdXjp2GuYLcKyZp8b1P4YW6XqPO', '123456789', 0, '2025-05-11 17:04:45', 'Socio'),
(15, 'Jose Antonio Gutierrez', 'joseantonio@gmail.com', '$2y$10$f1ixg1zy/d5mqfaxDusv7.HhpYvVU8PpyRdwqIGZB70M9vASagQla', '123456789', 0, '2025-05-18 16:13:33', 'Socio'),
(16, 'admin', 'admin@club.com', '$2y$10$/ARhFCPMaPGKnS0RMRhuQOQ/aV5TRZ4s6ccepzt2OCMSzHxQ3hmrS', '123456789', 0, '2025-05-21 10:28:12', 'Administrador');

-- --------------------------------------------------------

--
-- Índices para tablas volcadas
--

ALTER TABLE pistas
  ADD PRIMARY KEY (id);

ALTER TABLE reservas
  ADD PRIMARY KEY (id),
  ADD KEY usuario_id (usuario_id),
  ADD KEY pista_id (pista_id);

ALTER TABLE usuarios
  ADD PRIMARY KEY (id),
  ADD UNIQUE KEY email (email);

-- AUTO_INCREMENT

ALTER TABLE pistas
  MODIFY id int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

ALTER TABLE reservas
  MODIFY id int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=141;

ALTER TABLE usuarios
  MODIFY id int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

-- FOREIGN KEYS

ALTER TABLE reservas
  ADD CONSTRAINT reservas_ibfk_1 FOREIGN KEY (usuario_id) REFERENCES usuarios (id),
  ADD CONSTRAINT reservas_ibfk_2 FOREIGN KEY (pista_id) REFERENCES pistas (id);

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
 /*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
 /*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

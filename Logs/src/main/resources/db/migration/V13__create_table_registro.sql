--
-- PostgreSQL database dump
--

-- Dumped from database version 12.20
-- Dumped by pg_dump version 16.4

-- Started on 2024-09-11 23:53:17

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 204 (class 1259 OID 24929)
-- Name: registros; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.registros (
    registroid bigint NOT NULL,
    actividad character varying(1000) NOT NULL,
    fecha timestamp(6) without time zone NOT NULL,
    idusuario bigint,
    direccion_ip character varying(15) NOT NULL,
    estadoid bigint NOT NULL,
    mensaje character varying(100000)
);


ALTER TABLE public.registros OWNER TO postgres;

--
-- TOC entry 2844 (class 0 OID 24929)
-- Dependencies: 204
-- Data for Name: registros; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.registros VALUES (1, 'Insertar usuario: Rayner', '2024-09-02 11:10:51.030573', NULL, '127.0.0.1', 1, NULL);
INSERT INTO public.registros VALUES (2, 'Insertar o reactivar usuario: Dianelis', '2024-09-02 11:11:29.654235', NULL, '127.0.0.1', 2, NULL);
INSERT INTO public.registros VALUES (3, 'Insertar usuario: Dianelis', '2024-09-02 11:11:58.340427', NULL, '127.0.0.1', 1, NULL);
INSERT INTO public.registros VALUES (52, 'Autenticación de usuario: Rayner', '2024-09-02 13:14:08.319051', NULL, '127.0.0.1', 1, NULL);
INSERT INTO public.registros VALUES (102, 'Autenticación de usuario: Rayner', '2024-09-03 03:28:59.803184', NULL, '127.0.0.1', 1, NULL);
INSERT INTO public.registros VALUES (103, 'Obtener listado de todos las provincias del sistema', '2024-09-03 03:29:22.520922', 1, '127.0.0.1', 1, NULL);
INSERT INTO public.registros VALUES (104, 'Listar todos todos los municipios del sistema', '2024-09-03 03:30:44.61333', 1, '127.0.0.1', 1, NULL);
INSERT INTO public.registros VALUES (152, 'Listar todos todos los municipios del sistema', '2024-09-03 03:35:04.705633', 1, '127.0.0.1', 1, NULL);
INSERT INTO public.registros VALUES (153, 'Listar todos todos los municipios del sistema', '2024-09-03 03:35:20.535822', 1, '127.0.0.1', 1, NULL);
INSERT INTO public.registros VALUES (154, 'Obtener el listado de todas las zonas de transportes del sistema', '2024-09-03 03:55:10.082563', 1, '127.0.0.1', 2, NULL);
INSERT INTO public.registros VALUES (155, 'Obtener el listado de todas las zonas de transportes del sistema', '2024-09-03 03:59:30.797736', 1, '127.0.0.1', 1, NULL);
INSERT INTO public.registros VALUES (156, 'Obtener el listado de todas las zonas de transportes del sistema', '2024-09-03 04:00:18.927093', 1, '127.0.0.1', 1, NULL);
INSERT INTO public.registros VALUES (202, 'Autenticación de usuario: Rayner', '2024-09-03 11:35:15.498347', NULL, '127.0.0.1', 1, NULL);
INSERT INTO public.registros VALUES (252, 'Autenticación de usuario: Rayner', '2024-09-07 15:11:31.488566', NULL, '127.0.0.1', 2, NULL);
INSERT INTO public.registros VALUES (302, 'Autenticación de usuario: Rayner', '2024-09-07 15:29:47.221721', NULL, '127.0.0.1', 2, NULL);
INSERT INTO public.registros VALUES (352, 'Autenticación de usuario: Rayner', '2024-09-07 15:39:28.692014', NULL, '127.0.0.1', 2, 'Cannot invoke "cu.edu.cujae.logs.core.mapper.Usuario.getUuid()" because "usuario" is null');
INSERT INTO public.registros VALUES (402, 'Autenticación de usuario: Rayner', '2024-09-08 12:50:12.604328', NULL, '127.0.0.1', 1, NULL);
INSERT INTO public.registros VALUES (452, 'Autenticación de usuario: Rayner', '2024-09-08 13:01:59.244895', NULL, '127.0.0.1', 1, NULL);
INSERT INTO public.registros VALUES (453, 'Listar todas las entidades del sistema', '2024-09-08 13:06:11.948155', 1, '127.0.0.1', 1, NULL);
INSERT INTO public.registros VALUES (454, 'Listado de empleados del sistema junto con sus centros laborales', '2024-09-08 14:43:47.900762', 1, '0:0:0:0:0:0:0:1', 1, NULL);


--
-- TOC entry 2715 (class 2606 OID 24955)
-- Name: registros registros_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.registros
    ADD CONSTRAINT registros_pkey PRIMARY KEY (registroid);


--
-- TOC entry 2717 (class 2620 OID 24980)
-- Name: registros Operaciones no permitidas; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER "Operaciones no permitidas" BEFORE DELETE OR UPDATE ON public.registros FOR EACH ROW EXECUTE FUNCTION public."Funcion no soportada"();


--
-- TOC entry 2716 (class 2606 OID 24990)
-- Name: registros fkdi9b8fxeix26dgcjvulepci5u; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.registros
    ADD CONSTRAINT fkdi9b8fxeix26dgcjvulepci5u FOREIGN KEY (estadoid) REFERENCES public.estados(estadoid);


-- Completed on 2024-09-11 23:53:17

--
-- PostgreSQL database dump complete
--


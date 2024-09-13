--
-- PostgreSQL database dump
--

-- Dumped from database version 12.20
-- Dumped by pg_dump version 16.4

-- Started on 2024-09-12 21:27:52

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
-- TOC entry 218 (class 1259 OID 21701)
-- Name: entidades; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.entidades (
    identidad bigint NOT NULL,
    area_salud bigint,
    datos_adicionales character varying(255),
    direccion character varying(255) NOT NULL,
    distcentroasalud double precision,
    distcentromunic double precision,
    distcentroprov double precision,
    distcentrozonat double precision,
    entidadmadre character varying(100),
    geolocalizacion public.geometry,
    horario_entrada time(6) without time zone,
    horario_propuesto_entrada time(6) without time zone,
    horario_propuesto_salida time(6) without time zone,
    horario_salida time(6) without time zone,
    localidad character varying(255),
    nombre character varying(1000) NOT NULL,
    numero character varying(255),
    zona_transporte bigint,
    idmunicipio bigint NOT NULL,
    idprovincia integer NOT NULL
);


ALTER TABLE public.entidades OWNER TO postgres;

--
-- TOC entry 3770 (class 0 OID 21701)
-- Dependencies: 218
-- Data for Name: entidades; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.entidades VALUES (52, NULL, '', 'Patrocinio , #620 e/Goss y Parque,  Vibora,   10 de Octubre,  La Habana', NULL, NULL, NULL, NULL, '', NULL, '08:00:00', '09:00:00', '18:30:00', '17:30:00', '', 'Dirección Municipal de la Vivienda', '', NULL, 31, 3);
INSERT INTO public.entidades VALUES (102, NULL, '', 'Playitas, #S/N e/23 y 24,  Lawton,   10 de Octubre,  La Habana', NULL, NULL, NULL, NULL, 'Dirección Municipal de la Vivienda', NULL, '08:00:00', '09:00:00', '18:30:00', '17:30:00', '', 'C/T IV Congreso ', '', NULL, 31, 3);
INSERT INTO public.entidades VALUES (103, NULL, '', 'Santa Irene, #168 e/10 de Octubre , Tamarindo,   10 de Octubre,  La Habana', NULL, NULL, NULL, NULL, 'Dirección Municipal de la Vivienda', NULL, '08:00:00', '09:00:00', '18:30:00', '17:30:00', '', 'C/T Snata Irene', '', NULL, 31, 3);
INSERT INTO public.entidades VALUES (104, NULL, '', 'Calle 23,  e/Giral y Playitas,  Lawton,   10 de Octubre,  La Habana', NULL, NULL, NULL, NULL, 'Dirección Municipal de la Vivienda', NULL, '08:00:00', '09:00:00', '18:30:00', '17:30:00', '', 'C/T Sevilla Sebastian Cataluna', '', NULL, 31, 3);
INSERT INTO public.entidades VALUES (105, NULL, '', 'San Carlos, #21 y 16 e/Morell y e Iznaga,  Stos Suarez,   10 de Octubre,  La Habana', NULL, NULL, NULL, NULL, 'Dirección Municipal de la Vivienda', NULL, '08:00:00', '09:00:00', '18:30:00', '17:30:00', '', 'C/T San Carlos 21 y 16', '', NULL, 31, 3);
INSERT INTO public.entidades VALUES (106, NULL, '', 'San Lazaro, #758 e/Vista Alegre y San Mariano,  Acosta,   10 de Octubre,  La Habana', NULL, NULL, NULL, NULL, 'Dirección Municipal de la Vivienda', NULL, '08:00:00', '09:00:00', '18:30:00', '17:30:00', '', 'C/T San Lazaro ', '', NULL, 31, 3);
INSERT INTO public.entidades VALUES (107, NULL, '', 'Calle 24,  e/Comezanas y Dos Rios,   10 de Octubre,  La Habana', NULL, NULL, NULL, NULL, 'Dirección Municipal de la Vivienda', NULL, '08:00:00', '09:00:00', '18:30:00', '17:30:00', '', 'C/T Sevilla', '', NULL, 31, 3);
INSERT INTO public.entidades VALUES (108, NULL, '', 'Luyano, #468 e/Rosa Enrique y Nuestra Senora de Regla,  Luyano,   10 de Octubre,  La Habana', NULL, NULL, NULL, NULL, 'Dirección Municipal de la Vivienda', NULL, '08:00:00', '09:00:00', '18:30:00', '17:30:00', '', 'C/T Luyano Trijol ', '', NULL, 31, 3);
INSERT INTO public.entidades VALUES (109, NULL, '', 'Avenida Acosta,  e/3era y Carmen,  Vibora,   10 de Octubre,  La Habana', NULL, NULL, NULL, NULL, 'Dirección Municipal de la Vivienda', NULL, '08:00:00', '09:00:00', '18:30:00', '17:30:00', '', 'C/T Dos Plamas Felicidad ', '', NULL, 31, 3);
INSERT INTO public.entidades VALUES (110, NULL, '', 'Luis Estevez, #509 e/Juan Delgado  y Goicuría,  Santos Suarez,   10 de Octubre,  La Habana', NULL, NULL, NULL, NULL, 'Dirección Municipal de la Vivienda', NULL, '08:00:00', '09:00:00', '18:30:00', '17:30:00', '', 'C/T Luis Estevez ', '', NULL, 31, 3);
INSERT INTO public.entidades VALUES (111, NULL, '', 'Porvenir,  e/Linea del Ferrocarril , Lawton,   10 de Octubre,  La Habana', NULL, NULL, NULL, NULL, 'Dirección Municipal de la Vivienda', NULL, '08:00:00', '09:00:00', '18:30:00', '17:30:00', '', 'C/T Felicidad', '', NULL, 31, 3);
INSERT INTO public.entidades VALUES (112, NULL, '', '114, #11901 e/Ciclovía y Rotonda,   Marianao,  La Habana', NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, '', 'CUJAE', '', NULL, 33, 3);
INSERT INTO public.entidades VALUES (113, NULL, '', '114, #11901 e/Ciclovía y Rotonda,   Marianao,  La Habana', NULL, NULL, NULL, NULL, 'CUJAE', NULL, NULL, NULL, NULL, NULL, '', 'Informática', '', NULL, 33, 3);
INSERT INTO public.entidades VALUES (114, NULL, '', '114, #11901 e/Ciclovía y Rotonda,   Marianao,  La Habana', NULL, NULL, NULL, NULL, 'CUJAE', NULL, NULL, NULL, NULL, NULL, '', 'Tele', '', NULL, 33, 3);
INSERT INTO public.entidades VALUES (115, NULL, '', 'Carretera de la CUJAE,  e/Avenida RanchoBoyeros ,  Boyeros,  La Habana', NULL, NULL, NULL, NULL, 'CUJAE', NULL, NULL, NULL, NULL, NULL, '', 'Eléctrica', '', NULL, 35, 3);
INSERT INTO public.entidades VALUES (116, NULL, '', '114, #11901 e/Ciclovía y Rotonda,   Marianao,  La Habana', NULL, NULL, NULL, NULL, 'CUJAE', NULL, NULL, NULL, NULL, NULL, '', 'Arquitectura', '', NULL, 33, 3);
INSERT INTO public.entidades VALUES (117, NULL, '', '114, #11901 e/Ciclovía y Rotonda,   Marianao,  La Habana', NULL, NULL, NULL, NULL, 'CUJAE', NULL, NULL, NULL, NULL, NULL, '', 'Automática', '', NULL, 33, 3);
INSERT INTO public.entidades VALUES (118, NULL, '', '114, #11901 e/Ciclovía y Rotonda,   Marianao,  La Habana', NULL, NULL, NULL, NULL, 'CUJAE', NULL, NULL, NULL, NULL, NULL, '', 'Biomédica', '', NULL, 33, 3);
INSERT INTO public.entidades VALUES (119, NULL, '', '114, #11901 e/Ciclovía y Rotonda,   Marianao,  La Habana', NULL, NULL, NULL, NULL, 'CUJAE', NULL, NULL, NULL, NULL, NULL, '', 'Civil', '', NULL, 33, 3);
INSERT INTO public.entidades VALUES (120, NULL, '', '114, #11901 e/Ciclovía y Rotonda,   Marianao,  La Habana', NULL, NULL, NULL, NULL, 'CUJAE', NULL, NULL, NULL, NULL, NULL, '', 'Geofísica', '', NULL, 33, 3);
INSERT INTO public.entidades VALUES (121, NULL, '', '114, #11901 e/Ciclovía y Rotonda,   Marianao,  La Habana', NULL, NULL, NULL, NULL, 'CUJAE', NULL, NULL, NULL, NULL, NULL, '', 'Hidráulica', '', NULL, 33, 3);
INSERT INTO public.entidades VALUES (122, NULL, '', '114, #11901 e/Ciclovía y Rotonda,   Marianao,  La Habana', NULL, NULL, NULL, NULL, 'CUJAE', NULL, NULL, NULL, NULL, NULL, '', 'Industrial', '', NULL, 33, 3);
INSERT INTO public.entidades VALUES (123, NULL, '', '114, #11901 e/Ciclovía y Rotonda,   Marianao,  La Habana', NULL, NULL, NULL, NULL, 'CUJAE', NULL, NULL, NULL, NULL, NULL, '', 'Mecánica', '', NULL, 33, 3);
INSERT INTO public.entidades VALUES (124, NULL, '', '114, #11901 e/Ciclovía y Rotonda,   Marianao,  La Habana', NULL, NULL, NULL, NULL, 'CUJAE', NULL, NULL, NULL, NULL, NULL, '', 'Metalurgia', '', NULL, 33, 3);
INSERT INTO public.entidades VALUES (125, NULL, '', '114, #11901 e/Ciclovía y Rotonda,   Marianao,  La Habana', NULL, NULL, NULL, NULL, 'CUJAE', NULL, NULL, NULL, NULL, NULL, '', 'Química', '', NULL, 33, 3);
INSERT INTO public.entidades VALUES (126, NULL, '', 'José Antonio Saco,  e/Vista Alegre y San Mariano,   Diez de Octubre,  La Habana', NULL, NULL, NULL, NULL, 'CUJAE', NULL, NULL, NULL, NULL, NULL, '', 'Filial de 10 de octubre', '', NULL, 31, 3);


--
-- TOC entry 3634 (class 2606 OID 21708)
-- Name: entidades entidades_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.entidades
    ADD CONSTRAINT entidades_pkey PRIMARY KEY (identidad);


--
-- TOC entry 3636 (class 2606 OID 21947)
-- Name: entidades uk_6wvuug3lnsjv838wi1mc6cdk0; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.entidades
    ADD CONSTRAINT uk_6wvuug3lnsjv838wi1mc6cdk0 UNIQUE (nombre);


--
-- TOC entry 3637 (class 2606 OID 21957)
-- Name: entidades fkktt2a3qocdxcy00qq5mmok3cu; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.entidades
    ADD CONSTRAINT fkktt2a3qocdxcy00qq5mmok3cu FOREIGN KEY (idprovincia) REFERENCES public.provincias(idprovincia);


--
-- TOC entry 3638 (class 2606 OID 21952)
-- Name: entidades fkss9u07vm4l3deosy3g2d8ytnf; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.entidades
    ADD CONSTRAINT fkss9u07vm4l3deosy3g2d8ytnf FOREIGN KEY (idmunicipio) REFERENCES public.municipios(idmunicipio);


-- Completed on 2024-09-12 21:27:52

--
-- PostgreSQL database dump complete
--


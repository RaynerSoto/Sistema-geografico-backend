--
-- PostgreSQL database dump
--

-- Dumped from database version 12.20
-- Dumped by pg_dump version 16.4

-- Started on 2024-09-12 21:29:39

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
-- TOC entry 220 (class 1259 OID 21879)
-- Name: personal; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.personal (
    idpersonal bigint NOT NULL,
    area_salud bigint,
    ci character varying(11) NOT NULL,
    datos_adicionales character varying(255),
    direccion character varying(255) NOT NULL,
    distcentroasalud double precision,
    distcentromunic double precision,
    distcentroprov double precision,
    distcentrozonat double precision,
    geolocalizacion public.geometry,
    localidad character varying(255),
    nombre character varying(100) NOT NULL,
    numero character varying(255),
    zona_transporte bigint,
    idmunicipio bigint NOT NULL,
    idprovincia integer NOT NULL
);


ALTER TABLE public.personal OWNER TO postgres;

--
-- TOC entry 3770 (class 0 OID 21879)
-- Dependencies: 220
-- Data for Name: personal; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.personal VALUES (1, NULL, '00031067310', '', 'Calle Santos Suárez #195 / Calle Flores y Calle San Benigno, reparto Santo Suárez ', NULL, NULL, NULL, NULL, NULL, NULL, 'Keila  Izquierdo Marin', NULL, NULL, 31, 3);
INSERT INTO public.personal VALUES (2, NULL, '01111466510', '', 'Calle: 279 e/114 y 116 #11413 Calabazar', NULL, NULL, NULL, NULL, NULL, NULL, 'Thais Acén Ravelo', NULL, NULL, 35, 3);


--
-- TOC entry 3634 (class 2606 OID 21886)
-- Name: personal personal_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.personal
    ADD CONSTRAINT personal_pkey PRIMARY KEY (idpersonal);


--
-- TOC entry 3636 (class 2606 OID 21951)
-- Name: personal uk_esxo3djtx1y5s16tkwiyjnt00; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.personal
    ADD CONSTRAINT uk_esxo3djtx1y5s16tkwiyjnt00 UNIQUE (ci);


--
-- TOC entry 3637 (class 2606 OID 21977)
-- Name: personal fk1qgwnnio6egaxu40f13r6uvwb; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.personal
    ADD CONSTRAINT fk1qgwnnio6egaxu40f13r6uvwb FOREIGN KEY (idprovincia) REFERENCES public.provincias(idprovincia);


--
-- TOC entry 3638 (class 2606 OID 21972)
-- Name: personal fka28acwdq2pe0r1gnvekbh4co7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.personal
    ADD CONSTRAINT fka28acwdq2pe0r1gnvekbh4co7 FOREIGN KEY (idmunicipio) REFERENCES public.municipios(idmunicipio);


-- Completed on 2024-09-12 21:29:39

--
-- PostgreSQL database dump complete
--


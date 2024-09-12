--
-- PostgreSQL database dump
--

-- Dumped from database version 12.20
-- Dumped by pg_dump version 16.4

-- Started on 2024-09-11 23:23:26

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
-- TOC entry 202 (class 1259 OID 24924)
-- Name: estados; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.estados (
    estadoid bigint NOT NULL,
    descripcion character varying(100) NOT NULL,
    nombre character varying(100) NOT NULL
);


ALTER TABLE public.estados OWNER TO postgres;

--
-- TOC entry 2847 (class 0 OID 24924)
-- Dependencies: 202
-- Data for Name: estados; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.estados VALUES (1, 'La petición a sido aceptada', 'Aceptado');
INSERT INTO public.estados VALUES (2, 'La petición a sido denegada', 'Rechazado');


--
-- TOC entry 2715 (class 2606 OID 24953)
-- Name: estados estados_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estados
    ADD CONSTRAINT estados_pkey PRIMARY KEY (estadoid);


--
-- TOC entry 2717 (class 2606 OID 24963)
-- Name: estados uk_93g5ha066boi1ef1x390la9rc; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estados
    ADD CONSTRAINT uk_93g5ha066boi1ef1x390la9rc UNIQUE (nombre);


--
-- TOC entry 2719 (class 2606 OID 24973)
-- Name: estados uk_tc0jqqgxhsvxjns1qcsnet2pq; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estados
    ADD CONSTRAINT uk_tc0jqqgxhsvxjns1qcsnet2pq UNIQUE (descripcion);


--
-- TOC entry 2720 (class 2620 OID 24978)
-- Name: estados Estados no modificable; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER "Estados no modificable" BEFORE INSERT OR DELETE OR UPDATE ON public.estados FOR EACH ROW EXECUTE FUNCTION public."tabla no modificable"();


-- Completed on 2024-09-11 23:23:26

--
-- PostgreSQL database dump complete
--


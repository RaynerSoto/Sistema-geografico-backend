--
-- PostgreSQL database dump
--

-- Dumped from database version 12.20
-- Dumped by pg_dump version 16.4

-- Started on 2024-09-11 23:24:30

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
-- TOC entry 208 (class 1259 OID 24939)
-- Name: sexos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sexos (
    sexoid bigint NOT NULL,
    nombre character varying(100) NOT NULL,
    sigla character varying(5) NOT NULL
);


ALTER TABLE public.sexos OWNER TO postgres;

--
-- TOC entry 2849 (class 0 OID 24939)
-- Dependencies: 208
-- Data for Name: sexos; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.sexos VALUES (1, 'Masculino', 'M');
INSERT INTO public.sexos VALUES (2, 'Femenino', 'F');


--
-- TOC entry 2715 (class 2606 OID 24959)
-- Name: sexos sexos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sexos
    ADD CONSTRAINT sexos_pkey PRIMARY KEY (sexoid);


--
-- TOC entry 2717 (class 2606 OID 24961)
-- Name: sexos uk_63qfmldbuh3ceq5jh0wmdsh7q; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sexos
    ADD CONSTRAINT uk_63qfmldbuh3ceq5jh0wmdsh7q UNIQUE (sigla);


--
-- TOC entry 2719 (class 2606 OID 24975)
-- Name: sexos uk_tnbxpr91ldmh7m80x873384gc; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sexos
    ADD CONSTRAINT uk_tnbxpr91ldmh7m80x873384gc UNIQUE (nombre);


--
-- TOC entry 2720 (class 2620 OID 24979)
-- Name: sexos No modificar; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER "No modificar" BEFORE DELETE OR UPDATE ON public.sexos FOR EACH ROW EXECUTE FUNCTION public."Funcion no soportada"();


--
-- TOC entry 2721 (class 2620 OID 24983)
-- Name: sexos Sexos maximos; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER "Sexos maximos" BEFORE INSERT ON public.sexos FOR EACH ROW EXECUTE FUNCTION public."sexos maximos"();


--
-- TOC entry 2722 (class 2620 OID 25169)
-- Name: sexos Sexos no modificables; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER "Sexos no modificables" BEFORE INSERT OR DELETE OR UPDATE ON public.sexos FOR EACH ROW EXECUTE FUNCTION public."Funcion no soportada"();


-- Completed on 2024-09-11 23:24:30

--
-- PostgreSQL database dump complete
--


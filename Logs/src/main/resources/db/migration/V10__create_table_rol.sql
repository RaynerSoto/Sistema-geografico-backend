--
-- PostgreSQL database dump
--

-- Dumped from database version 12.20
-- Dumped by pg_dump version 16.4

-- Started on 2024-09-11 23:23:58

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
-- TOC entry 206 (class 1259 OID 24934)
-- Name: roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.roles (
    rolid bigint NOT NULL,
    rol_nombre character varying(50) NOT NULL
);


ALTER TABLE public.roles OWNER TO postgres;

--
-- TOC entry 2847 (class 0 OID 24934)
-- Dependencies: 206
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.roles VALUES (1, 'Super Administrador');
INSERT INTO public.roles VALUES (2, 'Administrador');
INSERT INTO public.roles VALUES (3, 'Gestor');


--
-- TOC entry 2715 (class 2606 OID 24957)
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (rolid);


--
-- TOC entry 2717 (class 2606 OID 24971)
-- Name: roles uk_rn4x84cv8llunnoky04okfkci; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT uk_rn4x84cv8llunnoky04okfkci UNIQUE (rol_nombre);


--
-- TOC entry 2718 (class 2620 OID 25168)
-- Name: roles Rol no modificable; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER "Rol no modificable" BEFORE INSERT OR DELETE OR UPDATE ON public.roles FOR EACH ROW EXECUTE FUNCTION public."Funcion no soportada"();


--
-- TOC entry 2719 (class 2620 OID 24981)
-- Name: roles Roles con usuario; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER "Roles con usuario" BEFORE DELETE ON public.roles FOR EACH ROW EXECUTE FUNCTION public."usuarios con rol"();


--
-- TOC entry 2720 (class 2620 OID 24982)
-- Name: roles Roles no modificables; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER "Roles no modificables" BEFORE INSERT OR DELETE OR UPDATE ON public.roles FOR EACH ROW EXECUTE FUNCTION public."tabla no modificable"();


-- Completed on 2024-09-11 23:23:58

--
-- PostgreSQL database dump complete
--


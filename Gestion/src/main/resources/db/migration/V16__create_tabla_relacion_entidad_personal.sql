--
-- PostgreSQL database dump
--

-- Dumped from database version 12.20
-- Dumped by pg_dump version 16.4

-- Started on 2024-09-12 21:31:16

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
-- TOC entry 219 (class 1259 OID 21709)
-- Name: entidadpersonal; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.entidadpersonal (
    identidad bigint NOT NULL,
    idpersonal bigint NOT NULL
);


ALTER TABLE public.entidadpersonal OWNER TO postgres;

--
-- TOC entry 3768 (class 0 OID 21709)
-- Dependencies: 219
-- Data for Name: entidadpersonal; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.entidadpersonal VALUES (114, 1);
INSERT INTO public.entidadpersonal VALUES (114, 2);


--
-- TOC entry 3634 (class 2606 OID 21949)
-- Name: entidadpersonal ukgnv576a6jw8egptmch4dph4xx; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.entidadpersonal
    ADD CONSTRAINT ukgnv576a6jw8egptmch4dph4xx UNIQUE (identidad, idpersonal);


--
-- TOC entry 3635 (class 2606 OID 21962)
-- Name: entidadpersonal fk9c9wbay0i3eopkoflkniujvno; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.entidadpersonal
    ADD CONSTRAINT fk9c9wbay0i3eopkoflkniujvno FOREIGN KEY (idpersonal) REFERENCES public.personal(idpersonal);


--
-- TOC entry 3636 (class 2606 OID 21967)
-- Name: entidadpersonal fkdjtl1oslr75qfgp7w12q4367o; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.entidadpersonal
    ADD CONSTRAINT fkdjtl1oslr75qfgp7w12q4367o FOREIGN KEY (identidad) REFERENCES public.entidades(identidad);


-- Completed on 2024-09-12 21:31:17

--
-- PostgreSQL database dump complete
--


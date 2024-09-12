--
-- PostgreSQL database dump
--

-- Dumped from database version 12.20
-- Dumped by pg_dump version 16.4

-- Started on 2024-09-11 23:52:29

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
-- TOC entry 210 (class 1259 OID 24944)
-- Name: usuarios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuarios (
    usuarioid bigint NOT NULL,
    activo boolean NOT NULL,
    email character varying(100) NOT NULL,
    creacion timestamp(6) without time zone NOT NULL,
    eliminacion timestamp(6) without time zone,
    nombre character varying(100) NOT NULL,
    password character varying(1000) NOT NULL,
    username character varying(50) NOT NULL,
    rolid bigint NOT NULL,
    sexoid bigint NOT NULL
);


ALTER TABLE public.usuarios OWNER TO postgres;

--
-- TOC entry 2851 (class 0 OID 24944)
-- Dependencies: 210
-- Data for Name: usuarios; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.usuarios VALUES (1, true, 'raynersoto01@gmail.com', '2024-09-02 11:10:50.967448', NULL, 'Rayner Alejandro Soto Martínez', '$2a$10$IL3H7xfMROZZuYrvSS.aaeB0BvhHJk5gBs24VfQFyV80EhUG9Ka1i', 'Rayner', 1, 1);
INSERT INTO public.usuarios VALUES (2, true, 'dianimerci2001@gmail.com', '2024-09-02 11:11:58.328096', NULL, 'Dianelis de las Mercedes Estenoz Vazquez', '$2a$10$QLdtdlhmFTrdWe6nvSR2xuiMX39EUeQti/t6Mj52S6LB8N6dR.Mg6', 'Dianelis', 1, 2);

--
-- TOC entry 2717 (class 2606 OID 24967)
-- Name: usuarios uk_kfsp0s1tflm1cwlj8idhqsad0; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT uk_kfsp0s1tflm1cwlj8idhqsad0 UNIQUE (email);


--
-- TOC entry 2719 (class 2606 OID 24969)
-- Name: usuarios uk_m2dvbwfge291euvmk6vkkocao; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT uk_m2dvbwfge291euvmk6vkkocao UNIQUE (username);


--
-- TOC entry 2721 (class 2606 OID 24977)
-- Name: usuarios usuarios_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT usuarios_pkey PRIMARY KEY (usuarioid);


--
-- TOC entry 2724 (class 2620 OID 24984)
-- Name: usuarios usuarios no eliminable; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER "usuarios no eliminable" BEFORE DELETE ON public.usuarios FOR EACH ROW EXECUTE FUNCTION public."Funcion no soportada"();


--
-- TOC entry 2722 (class 2606 OID 24985)
-- Name: usuarios fk78kw0t13jqp78rnew5ck07iwn; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT fk78kw0t13jqp78rnew5ck07iwn FOREIGN KEY (sexoid) REFERENCES public.sexos(sexoid);


--
-- TOC entry 2723 (class 2606 OID 24995)
-- Name: usuarios fkh0rjch4pwipdc6mj42s4kclns; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT fkh0rjch4pwipdc6mj42s4kclns FOREIGN KEY (rolid) REFERENCES public.roles(rolid);


-- Completed on 2024-09-11 23:52:29

--
-- PostgreSQL database dump complete
--


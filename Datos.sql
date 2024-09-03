toc.dat                                                                                             0000600 0004000 0002000 00000027556 14665405424 0014470 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        PGDMP        ;                |           Sistema Geo Login    12.20    16.4 *    ?           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false         @           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false         A           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false         B           1262    24576    Sistema Geo Login    DATABASE     �   CREATE DATABASE "Sistema Geo Login" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Spanish_Mexico.1252';
 #   DROP DATABASE "Sistema Geo Login";
                postgres    false                     2615    2200    public    SCHEMA     2   -- *not* creating schema, since initdb creates it
 2   -- *not* dropping schema, since initdb creates it
                postgres    false         C           0    0    SCHEMA public    ACL     Q   REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;
                   postgres    false    6         �            1259    24577    estados    TABLE     �   CREATE TABLE public.estados (
    estadoid bigint NOT NULL,
    descripcion character varying(100) NOT NULL,
    nombre character varying(100) NOT NULL
);
    DROP TABLE public.estados;
       public         heap    postgres    false    6         �            1259    24621    estados_seq    SEQUENCE     u   CREATE SEQUENCE public.estados_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.estados_seq;
       public          postgres    false    6         �            1259    24582 	   registros    TABLE     	  CREATE TABLE public.registros (
    registroid bigint NOT NULL,
    actividad character varying(100) NOT NULL,
    fecha timestamp(6) without time zone NOT NULL,
    idusuario bigint,
    direccion_ip character varying(15) NOT NULL,
    estadoid bigint NOT NULL
);
    DROP TABLE public.registros;
       public         heap    postgres    false    6         �            1259    24623    registros_seq    SEQUENCE     w   CREATE SEQUENCE public.registros_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.registros_seq;
       public          postgres    false    6         �            1259    24587    roles    TABLE     h   CREATE TABLE public.roles (
    rolid bigint NOT NULL,
    rol_nombre character varying(50) NOT NULL
);
    DROP TABLE public.roles;
       public         heap    postgres    false    6         �            1259    24625 	   roles_seq    SEQUENCE     s   CREATE SEQUENCE public.roles_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
     DROP SEQUENCE public.roles_seq;
       public          postgres    false    6         �            1259    24592    sexos    TABLE     �   CREATE TABLE public.sexos (
    sexoid bigint NOT NULL,
    nombre character varying(100) NOT NULL,
    sigla character varying(5) NOT NULL
);
    DROP TABLE public.sexos;
       public         heap    postgres    false    6         �            1259    24627 	   sexos_seq    SEQUENCE     s   CREATE SEQUENCE public.sexos_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
     DROP SEQUENCE public.sexos_seq;
       public          postgres    false    6         �            1259    24597    usuarios    TABLE     �  CREATE TABLE public.usuarios (
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
    DROP TABLE public.usuarios;
       public         heap    postgres    false    6         �            1259    24629    usuarios_seq    SEQUENCE     v   CREATE SEQUENCE public.usuarios_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.usuarios_seq;
       public          postgres    false    6         3          0    24577    estados 
   TABLE DATA           @   COPY public.estados (estadoid, descripcion, nombre) FROM stdin;
    public          postgres    false    202       2867.dat 4          0    24582 	   registros 
   TABLE DATA           d   COPY public.registros (registroid, actividad, fecha, idusuario, direccion_ip, estadoid) FROM stdin;
    public          postgres    false    203       2868.dat 5          0    24587    roles 
   TABLE DATA           2   COPY public.roles (rolid, rol_nombre) FROM stdin;
    public          postgres    false    204       2869.dat 6          0    24592    sexos 
   TABLE DATA           6   COPY public.sexos (sexoid, nombre, sigla) FROM stdin;
    public          postgres    false    205       2870.dat 7          0    24597    usuarios 
   TABLE DATA           ~   COPY public.usuarios (usuarioid, activo, email, creacion, eliminacion, nombre, password, username, rolid, sexoid) FROM stdin;
    public          postgres    false    206       2871.dat D           0    0    estados_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.estados_seq', 801, true);
          public          postgres    false    207         E           0    0    registros_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.registros_seq', 101, true);
          public          postgres    false    208         F           0    0 	   roles_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('public.roles_seq', 801, true);
          public          postgres    false    209         G           0    0 	   sexos_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('public.sexos_seq', 801, true);
          public          postgres    false    210         H           0    0    usuarios_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.usuarios_seq', 51, true);
          public          postgres    false    211         �
           2606    24581    estados estados_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.estados
    ADD CONSTRAINT estados_pkey PRIMARY KEY (estadoid);
 >   ALTER TABLE ONLY public.estados DROP CONSTRAINT estados_pkey;
       public            postgres    false    202         �
           2606    24586    registros registros_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.registros
    ADD CONSTRAINT registros_pkey PRIMARY KEY (registroid);
 B   ALTER TABLE ONLY public.registros DROP CONSTRAINT registros_pkey;
       public            postgres    false    203         �
           2606    24591    roles roles_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (rolid);
 :   ALTER TABLE ONLY public.roles DROP CONSTRAINT roles_pkey;
       public            postgres    false    204         �
           2606    24596    sexos sexos_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.sexos
    ADD CONSTRAINT sexos_pkey PRIMARY KEY (sexoid);
 :   ALTER TABLE ONLY public.sexos DROP CONSTRAINT sexos_pkey;
       public            postgres    false    205         �
           2606    24614 "   sexos uk_63qfmldbuh3ceq5jh0wmdsh7q 
   CONSTRAINT     ^   ALTER TABLE ONLY public.sexos
    ADD CONSTRAINT uk_63qfmldbuh3ceq5jh0wmdsh7q UNIQUE (sigla);
 L   ALTER TABLE ONLY public.sexos DROP CONSTRAINT uk_63qfmldbuh3ceq5jh0wmdsh7q;
       public            postgres    false    205         �
           2606    24608 $   estados uk_93g5ha066boi1ef1x390la9rc 
   CONSTRAINT     a   ALTER TABLE ONLY public.estados
    ADD CONSTRAINT uk_93g5ha066boi1ef1x390la9rc UNIQUE (nombre);
 N   ALTER TABLE ONLY public.estados DROP CONSTRAINT uk_93g5ha066boi1ef1x390la9rc;
       public            postgres    false    202         �
           2606    24618 %   usuarios uk_io49vjba68pmbgpy9vtw8vm81 
   CONSTRAINT     b   ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT uk_io49vjba68pmbgpy9vtw8vm81 UNIQUE (nombre);
 O   ALTER TABLE ONLY public.usuarios DROP CONSTRAINT uk_io49vjba68pmbgpy9vtw8vm81;
       public            postgres    false    206         �
           2606    24616 %   usuarios uk_kfsp0s1tflm1cwlj8idhqsad0 
   CONSTRAINT     a   ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT uk_kfsp0s1tflm1cwlj8idhqsad0 UNIQUE (email);
 O   ALTER TABLE ONLY public.usuarios DROP CONSTRAINT uk_kfsp0s1tflm1cwlj8idhqsad0;
       public            postgres    false    206         �
           2606    24620 %   usuarios uk_m2dvbwfge291euvmk6vkkocao 
   CONSTRAINT     d   ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT uk_m2dvbwfge291euvmk6vkkocao UNIQUE (username);
 O   ALTER TABLE ONLY public.usuarios DROP CONSTRAINT uk_m2dvbwfge291euvmk6vkkocao;
       public            postgres    false    206         �
           2606    24610 "   roles uk_rn4x84cv8llunnoky04okfkci 
   CONSTRAINT     c   ALTER TABLE ONLY public.roles
    ADD CONSTRAINT uk_rn4x84cv8llunnoky04okfkci UNIQUE (rol_nombre);
 L   ALTER TABLE ONLY public.roles DROP CONSTRAINT uk_rn4x84cv8llunnoky04okfkci;
       public            postgres    false    204         �
           2606    24606 $   estados uk_tc0jqqgxhsvxjns1qcsnet2pq 
   CONSTRAINT     f   ALTER TABLE ONLY public.estados
    ADD CONSTRAINT uk_tc0jqqgxhsvxjns1qcsnet2pq UNIQUE (descripcion);
 N   ALTER TABLE ONLY public.estados DROP CONSTRAINT uk_tc0jqqgxhsvxjns1qcsnet2pq;
       public            postgres    false    202         �
           2606    24612 "   sexos uk_tnbxpr91ldmh7m80x873384gc 
   CONSTRAINT     _   ALTER TABLE ONLY public.sexos
    ADD CONSTRAINT uk_tnbxpr91ldmh7m80x873384gc UNIQUE (nombre);
 L   ALTER TABLE ONLY public.sexos DROP CONSTRAINT uk_tnbxpr91ldmh7m80x873384gc;
       public            postgres    false    205         �
           2606    24604    usuarios usuarios_pkey 
   CONSTRAINT     [   ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT usuarios_pkey PRIMARY KEY (usuarioid);
 @   ALTER TABLE ONLY public.usuarios DROP CONSTRAINT usuarios_pkey;
       public            postgres    false    206         �
           2606    24641 $   usuarios fk78kw0t13jqp78rnew5ck07iwn    FK CONSTRAINT     �   ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT fk78kw0t13jqp78rnew5ck07iwn FOREIGN KEY (sexoid) REFERENCES public.sexos(sexoid);
 N   ALTER TABLE ONLY public.usuarios DROP CONSTRAINT fk78kw0t13jqp78rnew5ck07iwn;
       public          postgres    false    205    2725    206         �
           2606    24631 %   registros fkdi9b8fxeix26dgcjvulepci5u    FK CONSTRAINT     �   ALTER TABLE ONLY public.registros
    ADD CONSTRAINT fkdi9b8fxeix26dgcjvulepci5u FOREIGN KEY (estadoid) REFERENCES public.estados(estadoid);
 O   ALTER TABLE ONLY public.registros DROP CONSTRAINT fkdi9b8fxeix26dgcjvulepci5u;
       public          postgres    false    202    2713    203         �
           2606    24636 $   usuarios fkh0rjch4pwipdc6mj42s4kclns    FK CONSTRAINT     �   ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT fkh0rjch4pwipdc6mj42s4kclns FOREIGN KEY (rolid) REFERENCES public.roles(rolid);
 N   ALTER TABLE ONLY public.usuarios DROP CONSTRAINT fkh0rjch4pwipdc6mj42s4kclns;
       public          postgres    false    2721    204    206                                                                                                                                                          2867.dat                                                                                            0000600 0004000 0002000 00000000126 14665405424 0014271 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        1	La petición a sido aceptada	Aceptado
2	La petición a sido denegada	Rechazado
\.


                                                                                                                                                                                                                                                                                                                                                                                                                                          2868.dat                                                                                            0000600 0004000 0002000 00000000463 14665405424 0014276 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        1	Insertar usuario: Rayner	2024-09-02 11:10:51.030573	\N	127.0.0.1	1
2	Insertar o reactivar usuario: Dianelis	2024-09-02 11:11:29.654235	\N	127.0.0.1	2
3	Insertar usuario: Dianelis	2024-09-02 11:11:58.340427	\N	127.0.0.1	1
52	Autenticación de usuario: Rayner	2024-09-02 13:14:08.319051	\N	127.0.0.1	1
\.


                                                                                                                                                                                                             2869.dat                                                                                            0000600 0004000 0002000 00000000064 14665405424 0014274 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        1	Super Administrador
2	Administrador
3	Gestor
\.


                                                                                                                                                                                                                                                                                                                                                                                                                                                                            2870.dat                                                                                            0000600 0004000 0002000 00000000040 14665405424 0014256 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        1	Masculino	M
2	Femenino	F
\.


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                2871.dat                                                                                            0000600 0004000 0002000 00000000524 14665405424 0014266 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        1	t	raynersoto01@gmail.com	2024-09-02 11:10:50.967448	\N	Rayner Alejandro Soto Martínez	$2a$10$IL3H7xfMROZZuYrvSS.aaeB0BvhHJk5gBs24VfQFyV80EhUG9Ka1i	Rayner	1	1
2	t	dianimerci2001@gmail.com	2024-09-02 11:11:58.328096	\N	Dianelis de las Mercedes Estenoz Vazquez	$2a$10$QLdtdlhmFTrdWe6nvSR2xuiMX39EUeQti/t6Mj52S6LB8N6dR.Mg6	Dianelis	1	2
\.


                                                                                                                                                                            restore.sql                                                                                         0000600 0004000 0002000 00000023275 14665405424 0015407 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        --
-- NOTE:
--
-- File paths need to be edited. Search for $$PATH$$ and
-- replace it with the path to the directory containing
-- the extracted data files.
--
--
-- PostgreSQL database dump
--

-- Dumped from database version 12.20
-- Dumped by pg_dump version 16.4

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

DROP DATABASE "Sistema Geo Login";
--
-- Name: Sistema Geo Login; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "Sistema Geo Login" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Spanish_Mexico.1252';


ALTER DATABASE "Sistema Geo Login" OWNER TO postgres;

\connect -reuse-previous=on "dbname='Sistema Geo Login'"

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

--
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

-- *not* creating schema, since initdb creates it


ALTER SCHEMA public OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: estados; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.estados (
    estadoid bigint NOT NULL,
    descripcion character varying(100) NOT NULL,
    nombre character varying(100) NOT NULL
);


ALTER TABLE public.estados OWNER TO postgres;

--
-- Name: estados_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.estados_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.estados_seq OWNER TO postgres;

--
-- Name: registros; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.registros (
    registroid bigint NOT NULL,
    actividad character varying(100) NOT NULL,
    fecha timestamp(6) without time zone NOT NULL,
    idusuario bigint,
    direccion_ip character varying(15) NOT NULL,
    estadoid bigint NOT NULL
);


ALTER TABLE public.registros OWNER TO postgres;

--
-- Name: registros_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.registros_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.registros_seq OWNER TO postgres;

--
-- Name: roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.roles (
    rolid bigint NOT NULL,
    rol_nombre character varying(50) NOT NULL
);


ALTER TABLE public.roles OWNER TO postgres;

--
-- Name: roles_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.roles_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.roles_seq OWNER TO postgres;

--
-- Name: sexos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sexos (
    sexoid bigint NOT NULL,
    nombre character varying(100) NOT NULL,
    sigla character varying(5) NOT NULL
);


ALTER TABLE public.sexos OWNER TO postgres;

--
-- Name: sexos_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.sexos_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.sexos_seq OWNER TO postgres;

--
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
-- Name: usuarios_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.usuarios_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.usuarios_seq OWNER TO postgres;

--
-- Data for Name: estados; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.estados (estadoid, descripcion, nombre) FROM stdin;
\.
COPY public.estados (estadoid, descripcion, nombre) FROM '$$PATH$$/2867.dat';

--
-- Data for Name: registros; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.registros (registroid, actividad, fecha, idusuario, direccion_ip, estadoid) FROM stdin;
\.
COPY public.registros (registroid, actividad, fecha, idusuario, direccion_ip, estadoid) FROM '$$PATH$$/2868.dat';

--
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.roles (rolid, rol_nombre) FROM stdin;
\.
COPY public.roles (rolid, rol_nombre) FROM '$$PATH$$/2869.dat';

--
-- Data for Name: sexos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.sexos (sexoid, nombre, sigla) FROM stdin;
\.
COPY public.sexos (sexoid, nombre, sigla) FROM '$$PATH$$/2870.dat';

--
-- Data for Name: usuarios; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuarios (usuarioid, activo, email, creacion, eliminacion, nombre, password, username, rolid, sexoid) FROM stdin;
\.
COPY public.usuarios (usuarioid, activo, email, creacion, eliminacion, nombre, password, username, rolid, sexoid) FROM '$$PATH$$/2871.dat';

--
-- Name: estados_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.estados_seq', 801, true);


--
-- Name: registros_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.registros_seq', 101, true);


--
-- Name: roles_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.roles_seq', 801, true);


--
-- Name: sexos_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.sexos_seq', 801, true);


--
-- Name: usuarios_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usuarios_seq', 51, true);


--
-- Name: estados estados_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estados
    ADD CONSTRAINT estados_pkey PRIMARY KEY (estadoid);


--
-- Name: registros registros_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.registros
    ADD CONSTRAINT registros_pkey PRIMARY KEY (registroid);


--
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (rolid);


--
-- Name: sexos sexos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sexos
    ADD CONSTRAINT sexos_pkey PRIMARY KEY (sexoid);


--
-- Name: sexos uk_63qfmldbuh3ceq5jh0wmdsh7q; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sexos
    ADD CONSTRAINT uk_63qfmldbuh3ceq5jh0wmdsh7q UNIQUE (sigla);


--
-- Name: estados uk_93g5ha066boi1ef1x390la9rc; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estados
    ADD CONSTRAINT uk_93g5ha066boi1ef1x390la9rc UNIQUE (nombre);


--
-- Name: usuarios uk_io49vjba68pmbgpy9vtw8vm81; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT uk_io49vjba68pmbgpy9vtw8vm81 UNIQUE (nombre);


--
-- Name: usuarios uk_kfsp0s1tflm1cwlj8idhqsad0; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT uk_kfsp0s1tflm1cwlj8idhqsad0 UNIQUE (email);


--
-- Name: usuarios uk_m2dvbwfge291euvmk6vkkocao; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT uk_m2dvbwfge291euvmk6vkkocao UNIQUE (username);


--
-- Name: roles uk_rn4x84cv8llunnoky04okfkci; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT uk_rn4x84cv8llunnoky04okfkci UNIQUE (rol_nombre);


--
-- Name: estados uk_tc0jqqgxhsvxjns1qcsnet2pq; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estados
    ADD CONSTRAINT uk_tc0jqqgxhsvxjns1qcsnet2pq UNIQUE (descripcion);


--
-- Name: sexos uk_tnbxpr91ldmh7m80x873384gc; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sexos
    ADD CONSTRAINT uk_tnbxpr91ldmh7m80x873384gc UNIQUE (nombre);


--
-- Name: usuarios usuarios_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT usuarios_pkey PRIMARY KEY (usuarioid);


--
-- Name: usuarios fk78kw0t13jqp78rnew5ck07iwn; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT fk78kw0t13jqp78rnew5ck07iwn FOREIGN KEY (sexoid) REFERENCES public.sexos(sexoid);


--
-- Name: registros fkdi9b8fxeix26dgcjvulepci5u; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.registros
    ADD CONSTRAINT fkdi9b8fxeix26dgcjvulepci5u FOREIGN KEY (estadoid) REFERENCES public.estados(estadoid);


--
-- Name: usuarios fkh0rjch4pwipdc6mj42s4kclns; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT fkh0rjch4pwipdc6mj42s4kclns FOREIGN KEY (rolid) REFERENCES public.roles(rolid);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
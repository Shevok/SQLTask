--
-- PostgreSQL database dump
--

-- Dumped from database version 11.2
-- Dumped by pg_dump version 11.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: address; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.address (
    id_address integer NOT NULL,
    house_number integer NOT NULL,
    city text NOT NULL,
    street text NOT NULL
);


ALTER TABLE public.address OWNER TO postgres;

--
-- Name: profession; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.profession (
    id_profession integer NOT NULL,
    description text NOT NULL
);


ALTER TABLE public.profession OWNER TO postgres;

--
-- Name: school; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.school (
    id_school integer NOT NULL,
    school_number integer NOT NULL,
    school_type text NOT NULL
);


ALTER TABLE public.school OWNER TO postgres;

--
-- Name: schoolboy; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.schoolboy (
    id_schoolboy integer NOT NULL,
    age integer NOT NULL,
    address_id integer NOT NULL,
    school_id integer NOT NULL,
    firstname text NOT NULL,
    lastname text NOT NULL
);


ALTER TABLE public.schoolboy OWNER TO postgres;

--
-- Name: schoolboy_profession_relation; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.schoolboy_profession_relation (
    schoolboy_id integer NOT NULL,
    profession_id integer NOT NULL
);


ALTER TABLE public.schoolboy_profession_relation OWNER TO postgres;

--
-- Data for Name: address; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.address (id_address, house_number, city, street) FROM stdin;
1	1	Минск	пр.Независимости
3	3	Брест	Центральная
6	10	Минск	Гикало
7	10	Минск	Гикало
4	10	Минск	Гикало
2	7	Витебск	Красивая
5	5	Minsk	asd
8	5	Minsk	asd
9	5	Minsk	asd
10	4	Минск	Беды
12	4	Брест	Беды
\.


--
-- Data for Name: profession; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.profession (id_profession, description) FROM stdin;
2	Программист
3	Учитель
1	Стоматолог
4	Комбайнер
\.


--
-- Data for Name: school; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.school (id_school, school_number, school_type) FROM stdin;
2	2	Гимназия
3	3	Средняя школа
1	1	Гимназия
6	7	Gym
8	7	Gym
9	7	Gym
10	15	Средняя школа
12	12	Средняя школа
\.


--
-- Data for Name: schoolboy; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.schoolboy (id_schoolboy, age, address_id, school_id, firstname, lastname) FROM stdin;
1	14	1	1	Иван	Иванов
2	13	2	2	Петр	Петров
3	15	3	3	Роман	Романов
5	16	5	6	Egor	Shevko
8	16	8	8	Egor	Shevko
9	16	9	9	Egor	Shevko
10	17	10	10	Григорий	Алферов
12	12	12	12	Ян	Алферов
\.


--
-- Data for Name: schoolboy_profession_relation; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.schoolboy_profession_relation (schoolboy_id, profession_id) FROM stdin;
1	1
1	2
2	3
3	2
9	1
9	2
10	1
10	2
12	3
12	4
\.


--
-- Name: address address_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.address
    ADD CONSTRAINT address_pkey PRIMARY KEY (id_address);


--
-- Name: profession profession_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profession
    ADD CONSTRAINT profession_pkey PRIMARY KEY (id_profession);


--
-- Name: school school_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.school
    ADD CONSTRAINT school_pkey PRIMARY KEY (id_school);


--
-- Name: schoolboy_profession_relation student_id_profession_id_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.schoolboy_profession_relation
    ADD CONSTRAINT student_id_profession_id_pk PRIMARY KEY (schoolboy_id, profession_id);


--
-- Name: schoolboy student_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.schoolboy
    ADD CONSTRAINT student_pkey PRIMARY KEY (id_schoolboy);


--
-- Name: schoolboy student_address_ID_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.schoolboy
    ADD CONSTRAINT "student_address_ID_fkey" FOREIGN KEY (address_id) REFERENCES public.address(id_address) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: schoolboy_profession_relation student_profession_relation_profession_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.schoolboy_profession_relation
    ADD CONSTRAINT student_profession_relation_profession_id_fkey FOREIGN KEY (profession_id) REFERENCES public.profession(id_profession);


--
-- Name: schoolboy_profession_relation student_profession_relation_student_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.schoolboy_profession_relation
    ADD CONSTRAINT student_profession_relation_student_id_fkey FOREIGN KEY (schoolboy_id) REFERENCES public.schoolboy(id_schoolboy);


--
-- Name: schoolboy student_school_ID_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.schoolboy
    ADD CONSTRAINT "student_school_ID_fkey" FOREIGN KEY (school_id) REFERENCES public.school(id_school);


--
-- PostgreSQL database dump complete
--


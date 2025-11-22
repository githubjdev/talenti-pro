--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.2
-- Dumped by pg_dump version 9.5.2

-- Started on 2025-10-07 05:32:33

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12355)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2144 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 184 (class 1259 OID 356274)
-- Name: medicamento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE medicamento (
    id bigint NOT NULL,
    nome character varying(255)
);


ALTER TABLE medicamento OWNER TO postgres;

--
-- TOC entry 183 (class 1259 OID 356272)
-- Name: medicamento_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE medicamento_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE medicamento_id_seq OWNER TO postgres;

--
-- TOC entry 2145 (class 0 OID 0)
-- Dependencies: 183
-- Name: medicamento_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE medicamento_id_seq OWNED BY medicamento.id;


--
-- TOC entry 186 (class 1259 OID 356314)
-- Name: medicamento_receita; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE medicamento_receita (
    id bigint NOT NULL,
    obs character varying(255),
    quantidade double precision NOT NULL,
    id_medicamento bigint NOT NULL,
    id_receituario bigint NOT NULL
);


ALTER TABLE medicamento_receita OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 356312)
-- Name: medicamento_receita_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE medicamento_receita_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE medicamento_receita_id_seq OWNER TO postgres;

--
-- TOC entry 2146 (class 0 OID 0)
-- Dependencies: 185
-- Name: medicamento_receita_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE medicamento_receita_id_seq OWNED BY medicamento_receita.id;


--
-- TOC entry 182 (class 1259 OID 356263)
-- Name: paciente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE paciente (
    id bigint NOT NULL,
    nome character varying(255),
    cpf character varying(255)
);


ALTER TABLE paciente OWNER TO postgres;

--
-- TOC entry 181 (class 1259 OID 356261)
-- Name: paciente_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE paciente_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE paciente_id_seq OWNER TO postgres;

--
-- TOC entry 2147 (class 0 OID 0)
-- Dependencies: 181
-- Name: paciente_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE paciente_id_seq OWNED BY paciente.id;


--
-- TOC entry 188 (class 1259 OID 356323)
-- Name: receituario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE receituario (
    id bigint NOT NULL,
    id_paciente bigint NOT NULL
);


ALTER TABLE receituario OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 356321)
-- Name: receituario_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE receituario_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE receituario_id_seq OWNER TO postgres;

--
-- TOC entry 2148 (class 0 OID 0)
-- Dependencies: 187
-- Name: receituario_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE receituario_id_seq OWNED BY receituario.id;


--
-- TOC entry 2001 (class 2604 OID 356277)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY medicamento ALTER COLUMN id SET DEFAULT nextval('medicamento_id_seq'::regclass);


--
-- TOC entry 2002 (class 2604 OID 356317)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY medicamento_receita ALTER COLUMN id SET DEFAULT nextval('medicamento_receita_id_seq'::regclass);


--
-- TOC entry 2000 (class 2604 OID 356266)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY paciente ALTER COLUMN id SET DEFAULT nextval('paciente_id_seq'::regclass);


--
-- TOC entry 2003 (class 2604 OID 356326)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY receituario ALTER COLUMN id SET DEFAULT nextval('receituario_id_seq'::regclass);


--
-- TOC entry 2132 (class 0 OID 356274)
-- Dependencies: 184
-- Data for Name: medicamento; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO medicamento (id, nome) VALUES (1, 'zolpidem');
INSERT INTO medicamento (id, nome) VALUES (2, 'dipirona');
INSERT INTO medicamento (id, nome) VALUES (3, 'doril');
INSERT INTO medicamento (id, nome) VALUES (5, 'Omeprazol');
INSERT INTO medicamento (id, nome) VALUES (6, 'Alegra D 1');
INSERT INTO medicamento (id, nome) VALUES (4, 'vitamina D d');


--
-- TOC entry 2149 (class 0 OID 0)
-- Dependencies: 183
-- Name: medicamento_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('medicamento_id_seq', 8, true);


--
-- TOC entry 2134 (class 0 OID 356314)
-- Dependencies: 186
-- Data for Name: medicamento_receita; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO medicamento_receita (id, obs, quantidade, id_medicamento, id_receituario) VALUES (1, NULL, 0.5, 6, 3);
INSERT INTO medicamento_receita (id, obs, quantidade, id_medicamento, id_receituario) VALUES (2, NULL, 0.5, 5, 4);
INSERT INTO medicamento_receita (id, obs, quantidade, id_medicamento, id_receituario) VALUES (3, 'Nenhuma', 2, 6, 4);
INSERT INTO medicamento_receita (id, obs, quantidade, id_medicamento, id_receituario) VALUES (4, '3 vez ao dia', 1, 2, 4);
INSERT INTO medicamento_receita (id, obs, quantidade, id_medicamento, id_receituario) VALUES (5, NULL, 1, 5, 5);
INSERT INTO medicamento_receita (id, obs, quantidade, id_medicamento, id_receituario) VALUES (6, 'Nenhuma', 3, 6, 5);
INSERT INTO medicamento_receita (id, obs, quantidade, id_medicamento, id_receituario) VALUES (7, NULL, 0.5, 4, 6);
INSERT INTO medicamento_receita (id, obs, quantidade, id_medicamento, id_receituario) VALUES (8, NULL, 5, 4, 7);
INSERT INTO medicamento_receita (id, obs, quantidade, id_medicamento, id_receituario) VALUES (9, 'Nenhuma', 1, 5, 7);
INSERT INTO medicamento_receita (id, obs, quantidade, id_medicamento, id_receituario) VALUES (10, 'Nenhuma', 1, 6, 8);
INSERT INTO medicamento_receita (id, obs, quantidade, id_medicamento, id_receituario) VALUES (11, '10', 0.5, 3, 9);
INSERT INTO medicamento_receita (id, obs, quantidade, id_medicamento, id_receituario) VALUES (12, NULL, 1, 1, 10);
INSERT INTO medicamento_receita (id, obs, quantidade, id_medicamento, id_receituario) VALUES (13, 'De manhã', 1, 5, 11);


--
-- TOC entry 2150 (class 0 OID 0)
-- Dependencies: 185
-- Name: medicamento_receita_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('medicamento_receita_id_seq', 13, true);


--
-- TOC entry 2130 (class 0 OID 356263)
-- Dependencies: 182
-- Data for Name: paciente; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO paciente (id, nome, cpf) VALUES (117, 'alex fernando', '456.564.664-44');
INSERT INTO paciente (id, nome, cpf) VALUES (119, 'joão', '564.646.565-46');
INSERT INTO paciente (id, nome, cpf) VALUES (118, 'pedro', '344.444.444-44');
INSERT INTO paciente (id, nome, cpf) VALUES (112, 'egidio', '564.646.565-46');
INSERT INTO paciente (id, nome, cpf) VALUES (111, 'Maria', '564.646.565-46');
INSERT INTO paciente (id, nome, cpf) VALUES (110, 'Paulo', '564.646.565-46');
INSERT INTO paciente (id, nome, cpf) VALUES (109, 'José', '465.466.464-65');
INSERT INTO paciente (id, nome, cpf) VALUES (108, 'Adriano', '444.444.444-44');
INSERT INTO paciente (id, nome, cpf) VALUES (106, 'Mauro', '444.444.444-44');
INSERT INTO paciente (id, nome, cpf) VALUES (102, 'Izael', '444.444.444-44');
INSERT INTO paciente (id, nome, cpf) VALUES (101, 'Zeus', '444.444.444-44');


--
-- TOC entry 2151 (class 0 OID 0)
-- Dependencies: 181
-- Name: paciente_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('paciente_id_seq', 119, true);


--
-- TOC entry 2136 (class 0 OID 356323)
-- Dependencies: 188
-- Data for Name: receituario; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO receituario (id, id_paciente) VALUES (3, 119);
INSERT INTO receituario (id, id_paciente) VALUES (4, 119);
INSERT INTO receituario (id, id_paciente) VALUES (5, 112);
INSERT INTO receituario (id, id_paciente) VALUES (6, 117);
INSERT INTO receituario (id, id_paciente) VALUES (7, 111);
INSERT INTO receituario (id, id_paciente) VALUES (8, 117);
INSERT INTO receituario (id, id_paciente) VALUES (9, 111);
INSERT INTO receituario (id, id_paciente) VALUES (10, 118);
INSERT INTO receituario (id, id_paciente) VALUES (11, 118);


--
-- TOC entry 2152 (class 0 OID 0)
-- Dependencies: 187
-- Name: receituario_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('receituario_id_seq', 11, true);


--
-- TOC entry 2007 (class 2606 OID 356279)
-- Name: medicamento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY medicamento
    ADD CONSTRAINT medicamento_pkey PRIMARY KEY (id);


--
-- TOC entry 2009 (class 2606 OID 356320)
-- Name: medicamento_receita_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY medicamento_receita
    ADD CONSTRAINT medicamento_receita_pkey PRIMARY KEY (id);


--
-- TOC entry 2005 (class 2606 OID 356268)
-- Name: paciente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY paciente
    ADD CONSTRAINT paciente_pkey PRIMARY KEY (id);


--
-- TOC entry 2011 (class 2606 OID 356328)
-- Name: receituario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY receituario
    ADD CONSTRAINT receituario_pkey PRIMARY KEY (id);


--
-- TOC entry 2012 (class 2606 OID 356329)
-- Name: fk_medicamento; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY medicamento_receita
    ADD CONSTRAINT fk_medicamento FOREIGN KEY (id_medicamento) REFERENCES medicamento(id);


--
-- TOC entry 2014 (class 2606 OID 356339)
-- Name: fk_paciente; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY receituario
    ADD CONSTRAINT fk_paciente FOREIGN KEY (id_paciente) REFERENCES paciente(id);


--
-- TOC entry 2013 (class 2606 OID 356334)
-- Name: fk_receituario; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY medicamento_receita
    ADD CONSTRAINT fk_receituario FOREIGN KEY (id_receituario) REFERENCES receituario(id);


--
-- TOC entry 2143 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2025-10-07 05:32:33

--
-- PostgreSQL database dump complete
--


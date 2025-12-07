

CREATE OR REPLACE VIEW vw_usuario_roles AS
SELECT
    u.login       AS username,
    a.acesso      AS role_name,
	u.senha       AS senha
FROM aut_usuario u
JOIN aut_usuario_acesso ua ON ua.usuario_id = u.id
JOIN aut_acesso a          ON a.id = ua.acesso_id;


INSERT INTO public.aut_acesso (id, acesso)
SELECT nextval('seq_acesso'), 'ROLE_USER'
WHERE NOT EXISTS (
    SELECT 1 FROM public.aut_acesso WHERE acesso = 'ROLE_USER'
);

INSERT INTO public.aut_acesso (id, acesso)
SELECT nextval('seq_acesso'), 'ROLE_ADMIN'
WHERE NOT EXISTS (
    SELECT 1 FROM public.aut_acesso WHERE acesso = 'ROLE_ADMIN'
);

INSERT INTO public.aut_acesso (id, acesso)
SELECT nextval('seq_acesso'), 'ROLE_MASTER'
WHERE NOT EXISTS (
    SELECT 1 FROM public.aut_acesso WHERE acesso = 'ROLE_MASTER'
);

INSERT INTO public.aut_acesso (id, acesso)
SELECT nextval('seq_acesso'), 'ROLE_MEDIADOR'
WHERE NOT EXISTS (
    SELECT 1 FROM public.aut_acesso WHERE acesso = 'ROLE_MEDIADOR'
);

INSERT INTO public.aut_acesso (id, acesso)
SELECT nextval('seq_acesso'), 'ROLE_COLABORADOR'
WHERE NOT EXISTS (
    SELECT 1 FROM public.aut_acesso WHERE acesso = 'ROLE_COLABORADOR'
);


INSERT INTO public.aut_acesso (id, acesso)
SELECT nextval('seq_acesso'), 'ROLE_GESTOR'
WHERE NOT EXISTS (
    SELECT 1 FROM public.aut_acesso WHERE acesso = 'ROLE_GESTOR'
);


INSERT INTO public.aut_usuario (id, login, senha)
SELECT nextval('seq_usuario'), 'admin', 'admin'
WHERE NOT EXISTS (
    SELECT 1 FROM public.aut_usuario WHERE login = 'admin'
);

INSERT INTO public.aut_usuario (id, login, senha)
SELECT nextval('seq_usuario'), 'master', 'master'
WHERE NOT EXISTS (
    SELECT 1 FROM public.aut_usuario WHERE login = 'master'
);

INSERT INTO public.aut_usuario (id, login, senha)
SELECT nextval('seq_usuario'), 'colaborador', 'colaborador'
WHERE NOT EXISTS (
    SELECT 1 FROM public.aut_usuario WHERE login = 'colaborador'
);

INSERT INTO public.aut_usuario (id, login, senha)
SELECT nextval('seq_usuario'), 'mediador', 'mediador'
WHERE NOT EXISTS (
    SELECT 1 FROM public.aut_usuario WHERE login = 'mediador'
);

INSERT INTO public.aut_usuario (id, login, senha)
SELECT nextval('seq_usuario'), 'gestor', 'gestor'
WHERE NOT EXISTS (
    SELECT 1 FROM public.aut_usuario WHERE login = 'gestor'
);



INSERT INTO public.aut_usuario_acesso (id, acesso_id, usuario_id)
SELECT nextval('seq_usuario_acesso'), 2, 1
WHERE NOT EXISTS (
    SELECT 1 FROM public.aut_usuario_acesso 
    WHERE acesso_id = 2 AND usuario_id = 1
);

INSERT INTO public.aut_usuario_acesso (id, acesso_id, usuario_id)
SELECT nextval('seq_usuario_acesso'), 2, 2
WHERE NOT EXISTS (
    SELECT 1 FROM public.aut_usuario_acesso 
    WHERE acesso_id = 2 AND usuario_id = 2
);

INSERT INTO public.aut_usuario_acesso (id, acesso_id, usuario_id)
SELECT nextval('seq_usuario_acesso'), 2, 3
WHERE NOT EXISTS (
    SELECT 1 FROM public.aut_usuario_acesso 
    WHERE acesso_id = 2 AND usuario_id = 3
);

INSERT INTO public.aut_usuario_acesso (id, acesso_id, usuario_id)
SELECT nextval('seq_usuario_acesso'), 2, 4
WHERE NOT EXISTS (
    SELECT 1 FROM public.aut_usuario_acesso 
    WHERE acesso_id = 2 AND usuario_id = 4
);

INSERT INTO public.aut_usuario_acesso (id, acesso_id, usuario_id)
SELECT nextval('seq_usuario_acesso'), 2, 5
WHERE NOT EXISTS (
    SELECT 1 FROM public.aut_usuario_acesso 
    WHERE acesso_id = 2 AND usuario_id = 5
);

INSERT INTO public.aut_usuario_acesso (id, acesso_id, usuario_id)
SELECT nextval('seq_usuario_acesso'), 1, 1
WHERE NOT EXISTS (
    SELECT 1 FROM public.aut_usuario_acesso 
    WHERE acesso_id = 1 AND usuario_id = 1
);

INSERT INTO public.aut_usuario_acesso (id, acesso_id, usuario_id)
SELECT nextval('seq_usuario_acesso'), 3, 2
WHERE NOT EXISTS (
    SELECT 1 FROM public.aut_usuario_acesso 
    WHERE acesso_id = 3 AND usuario_id = 2
);

INSERT INTO public.aut_usuario_acesso (id, acesso_id, usuario_id)
SELECT nextval('seq_usuario_acesso'), 5, 3
WHERE NOT EXISTS (
    SELECT 1 FROM public.aut_usuario_acesso 
    WHERE acesso_id = 5 AND usuario_id = 3
);

INSERT INTO public.aut_usuario_acesso (id, acesso_id, usuario_id)
SELECT nextval('seq_usuario_acesso'), 4, 4
WHERE NOT EXISTS (
    SELECT 1 FROM public.aut_usuario_acesso 
    WHERE acesso_id = 4 AND usuario_id = 4
);

INSERT INTO public.aut_usuario_acesso (id, acesso_id, usuario_id)
SELECT nextval('seq_usuario_acesso'), 6, 5
WHERE NOT EXISTS (
    SELECT 1 FROM public.aut_usuario_acesso 
    WHERE acesso_id = 6 AND usuario_id = 5
);


DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1 
        FROM pg_constraint 
        WHERE conname = 'uk_aut_usuario_login'
    ) THEN
        ALTER TABLE public.aut_usuario
        ADD CONSTRAINT uk_aut_usuario_login UNIQUE (login);
    END IF;
END $$;


DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1 
        FROM pg_constraint 
        WHERE conname = 'uk_aut_usuario_acesso_acesso_usuario'
    ) THEN
        ALTER TABLE public.aut_usuario_acesso
        ADD CONSTRAINT uk_aut_usuario_acesso_acesso_usuario
        UNIQUE (acesso_id, usuario_id);
    END IF;
END $$;


DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM pg_constraint
        WHERE conname = 'uk_aut_acesso_acesso'
    ) THEN
        ALTER TABLE public.aut_acesso
        ADD CONSTRAINT uk_aut_acesso_acesso UNIQUE (acesso);
    END IF;
END;
$$;


DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM pg_constraint
        WHERE conname = 'uk_ver_versionador_arquivosql'
    ) THEN
        ALTER TABLE public.ver_versionador_banco
        ADD CONSTRAINT uk_ver_versionador_arquivosql UNIQUE (arquivosql);
    END IF;
END;
$$;

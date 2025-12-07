
CREATE OR REPLACE VIEW vw_usuario_roles AS
SELECT
    u.login       AS username,
    a.acesso      AS role_name,
	u.senha       AS senha
FROM aut_usuario u
JOIN aut_usuario_acesso ua ON ua.usuario_id = u.id
JOIN aut_acesso a          ON a.id = ua.acesso_id;
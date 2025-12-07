
CREATE OR REPLACE VIEW vw_usuario_roles AS
SELECT
    u.login       AS username,
    a.acesso      AS role_name
FROM usuario u
JOIN usuario_acesso ua ON ua.usuario_id = u.id
JOIN acesso a          ON a.id = ua.acesso_id;

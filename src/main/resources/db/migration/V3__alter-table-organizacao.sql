ALTER TABLE organizacao
ADD COLUMN usuario_administrador_id bigint;

ALTER TABLE organizacao
ADD CONSTRAINT fk_usuario_administrador
FOREIGN KEY (usuario_administrador_id) REFERENCES usuario(id) ON DELETE CASCADE;


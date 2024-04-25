ALTER TABLE medicos DROP COLUMN ativo;
ALTER TABLE medicos ADD COLUMN ativo BOOLEAN;

UPDATE medicos SET ativo = TRUE;
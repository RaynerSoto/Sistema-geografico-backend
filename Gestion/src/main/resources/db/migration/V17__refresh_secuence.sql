-- 1. Verificar el valor máximo actual en la tabla usuarios
-- Esto se hace para asegurarse de que la secuencia no genere valores duplicados
SELECT setval('entidad_sequence', (SELECT COALESCE(MAX(identidad), 0) FROM entidades) + 1);
SELECT setval('empleado_sequence', (SELECT COALESCE(MAX(idpersonal), 0) FROM personal) + 1);


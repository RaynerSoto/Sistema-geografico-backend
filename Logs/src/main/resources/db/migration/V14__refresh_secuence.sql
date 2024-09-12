-- 1. Verificar el valor máximo actual en la tabla usuarios
-- Esto se hace para asegurarse de que la secuencia no genere valores duplicados
SELECT setval('usuario_sequence', (SELECT COALESCE(MAX(usuarioid), 0) FROM usuarios) + 1);
SELECT setval('registro_sequence', (SELECT COALESCE(MAX(registroid), 0) FROM registros) + 1);


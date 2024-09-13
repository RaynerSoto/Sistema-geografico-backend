-- FUNCTION: public.usuarios con rol()

-- DROP FUNCTION IF EXISTS public."usuarios con rol"();

CREATE OR REPLACE FUNCTION public."usuarios con rol"()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE NOT LEAKPROOF
AS $BODY$
Declare
	variable bigint := 0;
Begin
	SELECT COUNT(*)
    INTO variable
    FROM roles
    INNER JOIN usuarios 
	ON roles.rolid = usuarios.rolid and roles.rolid = OLD.rolid;
    IF variable != 0 THEN
        RETURN OLD;
    END IF;
    RAISE EXCEPTION 'Hay usuarios afiliados a dicho rol';
End;
$BODY$;

ALTER FUNCTION public."usuarios con rol"()
    OWNER TO postgres;

-- FUNCTION: public.prohibido eliminar()

-- DROP FUNCTION IF EXISTS public."prohibido eliminar"();

CREATE OR REPLACE FUNCTION public."prohibido eliminar"()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE NOT LEAKPROOF
AS $BODY$
Begin
	if TG_OP = 'Remove' then
		Raise Exception 'Operación no soportada';
		End if;
		End;
$BODY$;

ALTER FUNCTION public."prohibido eliminar"()
    OWNER TO postgres;

-- FUNCTION: public.prohibido modificar()

-- DROP FUNCTION IF EXISTS public."prohibido modificar"();

CREATE OR REPLACE FUNCTION public."prohibido modificar"()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE NOT LEAKPROOF
AS $BODY$
Begin
	if TG_OP = 'Update' then
		Raise Exception 'Operación no soportada';
		End if;
		End;
$BODY$;

ALTER FUNCTION public."prohibido modificar"()
    OWNER TO postgres;

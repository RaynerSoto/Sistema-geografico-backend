-- FUNCTION: public.prohibido insertar()

-- DROP FUNCTION IF EXISTS public."prohibido insertar"();

CREATE OR REPLACE FUNCTION public."prohibido insertar"()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE NOT LEAKPROOF
AS $BODY$
Begin
	if TG_OP = 'Insert' then
		Raise Exception 'Operación no soportada';
		End if;
		End;
$BODY$;

ALTER FUNCTION public."prohibido insertar"()
    OWNER TO postgres;

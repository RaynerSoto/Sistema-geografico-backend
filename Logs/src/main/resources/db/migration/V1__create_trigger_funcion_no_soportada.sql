-- FUNCTION: public.Funcion no soportada()

-- DROP FUNCTION IF EXISTS public."Funcion no soportada"();

CREATE OR REPLACE FUNCTION public."Funcion no soportada"()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE NOT LEAKPROOF
AS $BODY$
Begin 
	Raise Exception 'Operación no soportada';
End;
$BODY$;

ALTER FUNCTION public."Funcion no soportada"()
    OWNER TO postgres;

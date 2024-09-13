-- FUNCTION: public.tabla no modificable()

-- DROP FUNCTION IF EXISTS public."tabla no modificable"();

CREATE OR REPLACE FUNCTION public."tabla no modificable"()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE NOT LEAKPROOF
AS $BODY$
Begin 
	Raise Exception 'Tabla no modificable';
	End;
$BODY$;

ALTER FUNCTION public."tabla no modificable"()
    OWNER TO postgres;

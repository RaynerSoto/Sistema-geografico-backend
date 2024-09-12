-- FUNCTION: public.sexos maximos()

-- DROP FUNCTION IF EXISTS public."sexos maximos"();

CREATE OR REPLACE FUNCTION public."sexos maximos"()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE NOT LEAKPROOF
AS $BODY$
Declare
	cantidad bigint;
Begin
	Select Count(*)
	into cantidad
	From sexos;
	if cantidad = 2 then
		Raise Exception 'Máximo número de sexo admitidos';
	else 
		return NEW;
	End if;
End;
$BODY$;

ALTER FUNCTION public."sexos maximos"()
    OWNER TO postgres;

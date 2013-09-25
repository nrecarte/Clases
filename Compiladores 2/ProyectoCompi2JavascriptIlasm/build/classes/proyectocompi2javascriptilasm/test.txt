program potencia();
 (*Obtener la raíz cuadrada de un número real x cualquiera.*)

 var 
   i,j,k: int;

function potencia(nbase:int; exp:int):int;
begin
    if(exp < 2) then
    begin
        return nbase;
    end
    else
    begin
        return potencia(nbase, exp-1) * nbase;
    end
end;

   begin
  
   writeln("respuesta: ");
   writeln(i);

   for i := 1 to 10 do
   begin
      for j := 1 to 10 do
      begin 
        writeln(potencia(i, j));
      end
   end
  
   readln(i);

 end.
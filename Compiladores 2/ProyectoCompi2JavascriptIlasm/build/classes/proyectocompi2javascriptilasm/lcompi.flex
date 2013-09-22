   
/* --------------------------Usercode Section------------------------ */
   
import java_cup.runtime.*;
      
%%
   
%class Lexer

%line
%column
    
%cup
   
%{   
    
    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }
    
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }
%}
   

LineTerminator = \r|\n|\r\n
   

WhiteSpace     = {LineTerminator} | [ \t\f]
   

dec_int_lit = 0 | [1-9][0-9]*
   

dec_int_id = [A-Za-z_][A-Za-z_0-9]*
   
%%
/* ------------------------Lexical Rules Section---------------------- */
   
   
<YYINITIAL> {
   
   
    ";"                { return symbol(sym.SEMI); }
   
   
    "+"                {  return symbol(sym.PLUS); }
    "-"                {  return symbol(sym.MINUS); }
    "*"                {  return symbol(sym.TIMES); }
    "/"                {  return symbol(sym.DIVIDE); }
    "("                {  return symbol(sym.LPAREN); }
    ")"                {  return symbol(sym.RPAREN); }
"IF"                {  return symbol(sym.IF); }
"ELSE"                {  return symbol(sym.ELSE); }
"PRINT"                {  return symbol(sym.PRINT); }
"READ"                {  return symbol(sym.READ); }
"="                {  return symbol(sym.EQUALS); }
"FOR"                {  return symbol(sym.FOR); }
"TO"                {  return symbol(sym.TO); }
"BEGIN"                {  return symbol(sym.BEGIN); }
"END"                {  return symbol(sym.END); }
   
   
    {dec_int_lit}      { 
                         return symbol(sym.NUMBER, new Integer(yytext())); }
   
   
    {dec_int_id}       { 
                         return symbol(sym.ID, yytext());}
   
    {WhiteSpace}       { /* just skip what was found, do nothing */ }   
}



[^]                    { throw new Error("Illegal character <"+yytext()+">"); }

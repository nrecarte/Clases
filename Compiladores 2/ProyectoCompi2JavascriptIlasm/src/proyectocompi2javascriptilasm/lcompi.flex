package proyectocompi2javascriptilasm;
import java_cup.runtime.*;
      
%%
   

   

%class Lexer


%line
%column
    

%cup
   

%{   
    StringBuffer string = new StringBuffer();

    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }
   
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }
%}
   

/*-*
 * PATTERN DEFINITIONS:
 */

letter          = [A-Za-z]
digit           = [0-9]
alphanumeric    = {letter}|{digit}
other_id_char   = [_]
identifier      = {letter}({alphanumeric}|{other_id_char})*
integer         = {digit}*
float           = {integer}\.{integer}
bool            = [true|false]
char            = '{alphanumeric}'
leftbrace       = \{
rightbrace      = \}

LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace     = {LineTerminator} | [ \t\f]  


/* comments */
Comment = {TraditionalComment} | {EndOfLineComment} | 
          {DocumentationComment}

TraditionalComment = "(*" [^*] ~"*)" | "(*" "*"+ ")"
EndOfLineComment = "//" {InputCharacter}* {LineTerminator}?
DocumentationComment = "(*" "*"+ [^(*] ~"*)"

/* Char Literal */
StringCharacter = [^\r\n\"\\]

%state STRING
   
%%

 
   
<YYINITIAL> {
   

    /*  Symbols  */  
    ";"                { return symbol(sym.SEMICOLON); }
    ":"                { return symbol(sym.COLON); }
    ","                { return symbol(sym.SEMI); }
    "+"                { return symbol(sym.PLUS); }
    "-"                { return symbol(sym.MINUS); }
    "*"                { return symbol(sym.TIMES); }
    "/"                { return symbol(sym.DIVIDE); }
    "%"                { return symbol(sym.MOD); }
    "("                { return symbol(sym.LPAR); }
    ")"                { return symbol(sym.RPAR); }
    "["                { return symbol(sym.LBRACKET); }
    "]"                { return symbol(sym.RBRACKET); }
    ">="               { return symbol(sym.GREATER_THAN_OR_EQUAL); }
    ">"                { return symbol(sym.GREATER_THAN); }
    "<="               { return symbol(sym.LESS_THAN_OR_EQUAL); }
    "<"                { return symbol(sym.LESS_THAN); }
    "!="               { return symbol(sym.NOT_EQUAL); }
    "="                { return symbol(sym.EQUAL); }
    "."                { return symbol(sym.DOT); }
    ":="               { return symbol(sym.ASSIGN); }
    "!"                { return symbol(sym.NOT); }

    /* keywords */
    "program"          { return symbol(sym.PROGRAM); }
    "function"         { return symbol(sym.FUNCTION); }
    "var"              { return symbol(sym.VAR); }
    "case"             { return symbol(sym.CASE); }
    "if"               { return symbol(sym.IF); }
    "else"             { return symbol(sym.ELSE); }
    "while"            { return symbol(sym.WHILE); }
    "for"              { return symbol(sym.FOR); }
    "repeat"           { return symbol(sym.REPEAT); }
    "then"             { return symbol(sym.THEN); }
    "to"               { return symbol(sym.TO); }
    "do"               { return symbol(sym.DO); }
    "of"               { return symbol(sym.OF); }
    "until"            { return symbol(sym.UNTIL); }
    "begin"            { return symbol(sym.BEGIN); }
    "end"              { return symbol(sym.END); }
    "and"              { return symbol(sym.AND); }
    "or"               { return symbol(sym.OR); }
    "ref"              { return symbol(sym.REF); }
    "writeln"          { return symbol(sym.WRITELN); }
    "readln"           { return symbol(sym.READLN); }
    "type"             { return symbol(sym.TYPE); }
    "const"            { return symbol(sym.CONST); }
    "record"           { return symbol(sym.RECORD); }

    /* types */
    "array"            { return symbol(sym.ARRAY); }
    "string"           { return symbol(sym.STRING); }
    "int"              { return symbol(sym.INT); }
    "float"            { return symbol(sym.FLOAT); }
    "bool"             { return symbol(sym.BOOL); }
    "char"             { return symbol(sym.CHAR); }
    "void"             { return symbol(sym.VOID); }
    "return"           { return symbol(sym.RETURN); }
    
   
    {identifier}       { return symbol(sym.ID, yytext()); }
    {integer}          { return symbol(sym.INT_LITERAL, new Integer(yytext())); }
    {float}            { return symbol(sym.FLOAT_LITERAL, new Float(yytext())); }
    {char}             { return symbol(sym.CHAR_LITERAL, new Character(yytext().charAt(1))); }
    {bool}             { return symbol(sym.BOOLEAN_LITERAL, new Boolean(yytext())); }

    /* comments */
    {Comment}          { /* ignore */ }
   
    {WhiteSpace}       { /* just skip what was found, do nothing */ }  

    /* string literal */
    \"                 { yybegin(STRING); string.setLength(0); }
 
}

<STRING> {
  \"                             { yybegin(YYINITIAL); return symbol(sym.STRING_LITERAL, string.toString()); }
  
  {StringCharacter}+             { string.append( yytext() ); }
  
  /* escape sequences */
  "\\b"                          { string.append( '\b' ); }
  "\\t"                          { string.append( '\t' ); }
  "\\n"                          { string.append( '\n' ); }
  "\\f"                          { string.append( '\f' ); }
  "\\r"                          { string.append( '\r' ); }
  "\\'"                          { string.append( '\'' ); }
  "\\\\"                         { string.append( '\\' ); }
  \\[0-3]?                       { char val = (char) Integer.parseInt(yytext().substring(1),8);
                        	   string.append( val ); 
                                 }
  
  /* error cases */
  \\.                            { throw new RuntimeException("Illegal escape sequence \""+yytext()+"\""); }
  {LineTerminator}               { throw new RuntimeException("Unterminated string at end of line"); }
}
 /* error fallback */
[^]                              { throw new Error("Illegal character <"+yytext()+">"); }
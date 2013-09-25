PROGRAM -> id parameter ';' blocks '.'
         | epsilon

blocks -> block block_list
         | epsilon
 
block -> var id_list ':' type ';' 
       | id ':' type  ';'             
       | function id parameter ':' type ';' block ';'
       | compound_statement

id_list -> identifier , id_list
         | identifier
 
parameter -> '(' params ')'
           | epsilon

params ->  param ',' param_list
         | param
         | epsilon
         
param -> ref? id ':' type
       | epsilon

Statement -> id ':=' expression ';'
           | if expression then statement_list else statement_list
           | case expression of (constant_list ':' statement_list )* end 
           | while expression do statement_list
           | for id ':=' expression to expression do statement_list
           | Repeat statement_list Until expression ';'
           | compound_statement
           | writeln '(' constant | id ')' ';'
           | readln '(' id ')' ';'

Compound_statement -> begin statement_list end

Statement_list -> Statement statement_list
                | Statement

expression_list -> expression , expression_list
                 | expression

expression -> expression < factor
            | expression > factor
            | expression >= factor
            | expression <= factor
            | expression = factor
            | expression + factor
            | expression - factor
            | expression OR factor
                   
factor -> factor * term
        | factor / term
        | factor '%' term
        | factor AND term
      
term -> '(' expression ')'
       | variable
       | constant

variable -> id '[' expression_list ']'
          | ID '(' expression_list ')'
          | id 

simple_type -> constant .. constant , simple_type
             | constant .. constant

type -> simple_type
      | array '[' simple_type ']' of type
      
constant -> number
          | id
          
id -> letter (letter|digit)*
number-> [0..9]*
comments -> '(*' any_letter '*)'*
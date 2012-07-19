
	grammar Expr;
	
	expr:	multExpr ( ('+'|'-') multExpr )*
		;
	
	multExpr
		:	atom ( '*' atom )*
		;
	
	atom:	INT
		|	'(' expr ')'
		;
		
	INT: ('0'..'9')+;
	WS:	(' ' | '\t' | '\n' | '\r')+ { skip(); };
	
	Hello <name; separator=", ">
	
	String[] names = {"Lula", "FHC", "Itamar"};
	template.setAttribute("name", names);

	Hello Lula, FHC, Itamar
	
	
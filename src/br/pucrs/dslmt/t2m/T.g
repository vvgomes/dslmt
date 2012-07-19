grammar T;

@header {
package br.pucrs.dslmt.t2m;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.common.util.EList;
}

@lexer::header {
package br.pucrs.dslmt.t2m;
}

@members {
EPackage pkg;

public TParser(TokenStream input, EPackage pkg) {
	this(input);
	this.pkg= pkg;
}

private char removeQuotes(String s){
	return s.toCharArray()[1];
}

private String removeDoubleQuotes(String s) {
	return s.substring(1, s.length()-1);
}
}
	
STRING: '"'.*'"';
CHAR: '\''.'\'';
BOOL: 'true'|'false';
INT: ('0'..'9')+;
REAL: ('0'..'9')+'.'('0'..'9')+;
ID:	('a'..'z'|'A'..'Z')+ ;
WS:	(' '|'\t'|'\n'|'\r')+ {skip();};
fsm returns [EObject node]
	@init {
	EClass eClass= (EClass)pkg.getEClassifier("fsm");
	$node= pkg.getEFactoryInstance().create(eClass);
	}
	:
	'inputAlphabet'
	inputAlphabet=STRING {$node.eSet(eClass.getEStructuralFeature("inputAlphabet"), removeDoubleQuotes($inputAlphabet.text));}
	'outputAlphabet'
	outputAlphabet=STRING {$node.eSet(eClass.getEStructuralFeature("outputAlphabet"), removeDoubleQuotes($outputAlphabet.text));}
	(r=state {((EList)$node.eGet(eClass.getEStructuralFeature("state"))).add($r.node);})*
	;
state returns [EObject node]
	@init {
	EClass eClass= (EClass)pkg.getEClassifier("state");
	$node= pkg.getEFactoryInstance().create(eClass);
	}
	:
	('start' {$node.eSet(eClass.getEStructuralFeature("start"), true);})?
	'state'
	id=ID {$node.eSet(eClass.getEStructuralFeature("id"), $id.text);}
	(r=transition {((EList)$node.eGet(eClass.getEStructuralFeature("transition"))).add($r.node);})*
	;
transition returns [EObject node]
	@init {
	EClass eClass= (EClass)pkg.getEClassifier("transition");
	$node= pkg.getEFactoryInstance().create(eClass);
	}
	:
	'transition'
	inputt=CHAR {$node.eSet(eClass.getEStructuralFeature("inputt"), removeQuotes($inputt.text));}
	'/'
	output=CHAR {$node.eSet(eClass.getEStructuralFeature("output"), removeQuotes($output.text));}
	'->'
	target=ID {$node.eSet(eClass.getEStructuralFeature("target"), $target.text);}
	;
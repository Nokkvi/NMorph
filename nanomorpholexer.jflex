/**
	Höfundur: Snorri Agnarsson, febrúar 2017

	Þennan þáttara má þýða og keyra með skipununum
		java -jar JFlex-1.6.1.jar nanomorpholexer.jflex
		javac NanoMorphoLexer.java NanoMorphoParser.java
		java NanoMorphoParser inntaksskrá
	Einnig má nota forritið 'make', ef viðeigandi 'makefile'
	er til staðar:
		make test
 */

import java.io.*;

%%

%public
%class NanoMorphoLexer
%unicode
%byaccj
%line
%column

%{


private NanoMorphoParser yyparser;

public NanoMorphoLexer(Reader r, NanoMorphoParser a)
{
	this(r);
	yyparser = a;
}


public int getLine()
{
	return yyline+1;
}

public int getColumn()
{
	return yycolumn+1;
}



%}

  /* Reglulegar skilgreiningar */

  /* Regular definitions */

_DIGIT=[0-9]
_FLOAT={_DIGIT}+\.{_DIGIT}+([eE][+-]?{_DIGIT}+)?
_INT={_DIGIT}+
_STRING=\"([^\"\\]|\\b|\\t|\\n|\\f|\\r|\\\"|\\\'|\\\\|(\\[0-3][0-7][0-7])|\\[0-7][0-7]|\\[0-7])*\"
_CHAR=\'([^\'\\]|\\b|\\t|\\n|\\f|\\r|\\\"|\\\'|\\\\|(\\[0-3][0-7][0-7])|(\\[0-7][0-7])|(\\[0-7]))\'
_DELIM=[(){},;=]
_NAME=([:letter:]|{_DIGIT})+
_OPNAME=[\+\-*/!%&=><\:\^\~&|?]+

%%

  /* Lesgreiningarreglur */

{_DELIM} {
	yyparser.yylval = new NanoMorphoParserVal(yytext());
	return yycharat(0);
}

{_STRING} | {_FLOAT} | {_CHAR} | {_INT} | null | true | false {
	yyparser.yylval = new NanoMorphoParserVal(yytext());
	return NanoMorphoParser.LITERAL;
}

"if" {
	yyparser.yylval = new NanoMorphoParserVal(yytext());
	return NanoMorphoParser.IF;
}

"else" {
	yyparser.yylval = new NanoMorphoParserVal(yytext());
	return NanoMorphoParser.ELSE;
}

"elsif" {
	yyparser.yylval = new NanoMorphoParserVal(yytext());
	return NanoMorphoParser.ELSIF;
}

"while" {
	yyparser.yylval = new NanoMorphoParserVal(yytext());
	return NanoMorphoParser.WHILE;
}

"var" {
	yyparser.yylval = new NanoMorphoParserVal(yytext());
	return NanoMorphoParser.VAR;
}

"return" {
	yyparser.yylval = new NanoMorphoParserVal(yytext());
	return NanoMorphoParser.RETURN;
}

{_NAME} {
	yyparser.yylval = new NanoMorphoParserVal(yytext());
	return NanoMorphoParser.NAME;
}

{_OPNAME} {
	yyparser.yylval = new NanoMorphoParserVal(yytext());
	return NanoMorphoParser.OPNAME;
}

";;;".*$ {
}

[ \t\r\n\f] {
}

. {
	yyparser.yylval = new NanoMorphoParserVal(yytext());
	return NanoMorphoParser.ERROR;
}

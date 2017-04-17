//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 2 "NMorph.byacc"
        import java.io.*;
        import java.util.*;
//#line 20 "NMorphParser.java"




public class NMorphParser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class NMorphParserVal is defined in NMorphParserVal.java


String   yytext;//user variable to return contextual strings
NMorphParserVal yyval; //used to return semantic vals from action routines
NMorphParserVal yylval;//the 'lval' (result) I got from yylex()
NMorphParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new NMorphParserVal[YYSTACKSIZE];
  yyval=new NMorphParserVal();
  yylval=new NMorphParserVal();
  valptr=-1;
}
void val_push(NMorphParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
NMorphParserVal val_pop()
{
  if (valptr<0)
    return new NMorphParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
NMorphParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new NMorphParserVal();
  return valstk[ptr];
}
final NMorphParserVal dup_yyval(NMorphParserVal val)
{
  NMorphParserVal dup = new NMorphParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short LITERAL=257;
public final static short NAME=258;
public final static short OPNAME=259;
public final static short ERROR=260;
public final static short IF=261;
public final static short ELSE=262;
public final static short ELSIF=263;
public final static short VAR=264;
public final static short WHILE=265;
public final static short RETURN=266;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,   15,    2,   13,   13,   14,   14,   11,
   11,   12,   12,    3,    3,    4,    4,    4,    5,    5,
    6,    6,    6,    6,    6,    6,    6,    9,    9,    9,
    7,    7,   10,   10,    8,
};
final static short yylen[] = {                            2,
    1,    2,    1,    0,    9,    1,    3,    0,    1,    0,
    3,    3,    2,    2,    3,    2,    3,    1,    1,    3,
    1,    4,    2,    1,    3,    4,    3,    0,    2,    4,
    0,    1,    3,    1,    3,
};
final static short yydefred[] = {                         0,
    4,    0,    0,    3,    0,    2,    0,    6,    0,    0,
    0,    0,    7,   10,    0,   24,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   19,    0,    0,    0,
    0,   23,    0,   13,    0,   16,    0,    5,    0,   14,
    0,    0,   11,   34,    0,    0,   17,    0,    0,   27,
   25,   15,   20,   12,   22,    0,    0,    0,    0,   26,
   33,   35,   29,    0,    0,   30,
};
final static short yydgoto[] = {                          2,
    3,    4,   24,   25,   26,   27,   45,   49,   60,   46,
   15,   28,    9,   10,    5,
};
final static short yysindex[] = {                      -243,
    0,    0, -243,    0,  -14,    0, -231,    0,  -15,   -6,
 -227,  -87,    0,    0,  -40,    0,  -28,    1,  -10, -221,
  -10,  -10,  -10,  -30,  -19, -220,    0,  -31,  -10,  -10,
    2,    0,  -80,    0,  -80,    0,    3,    0,  -13,    0,
    1, -213,    0,    0,    6,    4,    0,  -10, -261,    0,
    0,    0,    0,    0,    0,  -10,  -20,  -80,  -10,    0,
    0,    0,    0,  -80, -261,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,   49,    0,    0,    0,    9,    0,   12,    0,
    0,    0,    0,    0,    0,    0,  -37,    0,    0,    0,
    0,    0,    0,    0,    0,  -38,    0,    0,   14,    0,
  -37,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   15,    0,    0,  -36,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  -36,    0,
};
final static short yygindex[] = {                         0,
    0,   54,   10,   -5,    0,   -7,    0,  -26,   -4,    0,
    0,    0,    0,    0,    0,
};
final static int YYTABLESIZE=266;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         23,
   58,   59,   18,   21,   28,   18,   21,   28,   50,   23,
   32,   29,   42,   33,    1,   35,   36,   37,   39,   23,
   18,   21,   28,   44,   47,    7,    8,   43,   11,   23,
   13,   63,   30,   53,   12,   14,   34,   65,   41,   40,
   23,   29,   48,   51,   54,   52,   55,   56,    1,    8,
   61,   39,    9,   64,   31,   32,    6,   57,    0,    0,
   66,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   18,   21,   28,    0,    0,    0,
    0,    0,    0,    0,   38,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   62,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   16,   17,   18,    0,
   19,   21,   28,   20,   21,   22,   16,   17,   18,    0,
   19,    0,    0,    0,   21,   22,   16,   17,   18,    0,
   19,    0,    0,    0,   21,   22,   16,   17,   18,    0,
   19,    0,    0,    0,   21,   22,    0,   16,   31,   18,
    0,   19,    0,    0,    0,   21,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
  262,  263,   41,   41,   41,   44,   44,   44,   35,   40,
   18,   40,   44,   19,  258,   21,   22,   23,   24,   40,
   59,   59,   59,   29,   30,   40,  258,   59,   44,   40,
  258,   58,   61,   41,   41,  123,  258,   64,  259,   59,
   40,   40,  123,   41,  258,   59,   41,   44,    0,   41,
   56,   57,   41,   59,   41,   41,    3,   48,   -1,   -1,
   65,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  123,  123,  123,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  125,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  125,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,   -1,
  261,  259,  259,  264,  265,  266,  257,  258,  259,   -1,
  261,   -1,   -1,   -1,  265,  266,  257,  258,  259,   -1,
  261,   -1,   -1,   -1,  265,  266,  257,  258,  259,   -1,
  261,   -1,   -1,   -1,  265,  266,   -1,  257,  258,  259,
   -1,  261,   -1,   -1,   -1,  265,
};
}
final static short YYFINAL=2;
final static short YYMAXTOKEN=266;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,"'('","')'",null,null,"','",
null,null,null,null,null,null,null,null,null,null,null,null,null,null,"';'",
null,"'='",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,"LITERAL","NAME","OPNAME","ERROR","IF",
"ELSE","ELSIF","VAR","WHILE","RETURN",
};
final static String yyrule[] = {
"$accept : start",
"start : program",
"program : program function",
"program : function",
"$$1 :",
"function : NAME $$1 '(' optparlist ')' '{' decls exprs '}'",
"parlist : NAME",
"parlist : parlist ',' NAME",
"optparlist :",
"optparlist : parlist",
"decls :",
"decls : decls decl ';'",
"decl : decl ',' NAME",
"decl : VAR NAME",
"exprs : expr ';'",
"exprs : exprs expr ';'",
"expr : RETURN expr",
"expr : NAME '=' expr",
"expr : binopexpr",
"binopexpr : smallexpr",
"binopexpr : binopexpr OPNAME smallexpr",
"smallexpr : NAME",
"smallexpr : NAME '(' optexpr ')'",
"smallexpr : OPNAME smallexpr",
"smallexpr : LITERAL",
"smallexpr : '(' expr ')'",
"smallexpr : IF expr body ifrest",
"smallexpr : WHILE expr body",
"ifrest :",
"ifrest : ELSE body",
"ifrest : ELSIF expr body ifrest",
"optexpr :",
"optexpr : nonoptexpr",
"nonoptexpr : nonoptexpr ',' expr",
"nonoptexpr : expr",
"body : '{' exprs '}'",
};

//#line 98 "NMorph.byacc"

private static int varCount;
private static HashMap<String,Integer> varTable;
private NanoMorphoLexer lexer;
private static String name;

private void addVar( String name )
{
  if( varTable.get(name) != null )
  	throw new Error("Variable "+name+" already exists, near line "+lexer.getLine());
  varTable.put(name,varCount++);
}

private int findVar( String name )
{
	Integer res = varTable.get(name);
	if( res == null )
		throw new Error("Variable "+name+" does not exist, near line "+lexer.getLine());
	return res;
}

public NanoMorphoParser(Reader r) {
	lexer = new NanoMorphoLexer(r,this);
}

private int yylex()
{
	int yyl_return = -1;
	try
	{
		yylval = null;
		yyl_return = lexer.yylex();
		if( yylval==null )
			yylval = new NanoMorphoParserVal(NanoMorphoParser.yyname[yyl_return]);
	}
	catch (IOException e)
	{
		System.err.println("IO error: "+e);
	}
	return yyl_return;
}

public void yyerror( String error )
{
	System.err.println("Error: "+error);
	System.err.println("Line: "+lexer.getLine());
	System.err.println("Column: "+lexer.getColumn());
	System.exit(1);
}

public static void main( String[] args) throws IOException, FileNotFoundException
{
	NanoMorphoParser par = new NanoMorphoParser(new FileReader(args[0]));
  name = args[0].substring(0,args[0].lastIndexOf('.'));
	par.yyparse();
}

static void generateProgram( String filename, Object[] funs )
{
  String programname = filename.substring(0,filename.indexOf('.'));
  System.out.println("\""+programname+".mexe\" = main in");
  System.out.println("!");
  System.out.println("{{");
  for( Object f: funs )
  {
      generateFunction((Object[])f);
  }
  System.out.println("}}");
  System.out.println("*");
  System.out.println("BASIS;");
}

static void generateFunction( Object[] fun )
{
  //fun = {fname, argcount, varcount, res[]};
  String fname = (String)fun[0];

  int argCount = (int)fun[1];
  int varCount = (int)fun[2];
  System.out.println("#\""+fname+"[f"+argCount+"]\" =");

  System.out.println("[");
  for(int k = 0; k<varCount;k++){
    System.out.println("(MakeVal null)");
    System.out.println("(Push)");
  }

  for(Object e:(Object[])fun[3]){
    generateExpr((Object[])e);
  }
  System.out.println("(Return)");
  System.out.println("];");
}

static int nextLab = 0;



static void generateExpr( Object[] e )
{
  switch((String)e[0]){
    case "NAME":
      System.out.println("(Fetch "+e[1]+")");
      return;
    case "LITERAL":
      System.out.println("(MakeVal "+e[1]+")");
      return;
    case "RETURN":
      generateExpr((Object[])e[1]);
      System.out.println("(Return)");
      return;
    case "OPNAME":
      generateExpr((Object[])e[2]);
      System.out.println("(Call \""+e[1]+"[f1]\" "+1+")");
      return;
    case "BODY":
      // e={"BODY",exprvector}
      for( Object expr: (Vector<Object>)e[1] ) generateExpr((Object[])expr);
      return;
    case "IF":
			// e={"IF",condexpr,thenexpr,elseexpr}
			int elslab = nextLab++;
			int endlab = nextLab++;
			generateExpr((Object[])e[1]);
			System.out.println("(GoFalse _"+elslab+")");
			generateExpr((Object[])e[2]);
			System.out.println("(Go _"+endlab+")");
			System.out.println("_"+elslab+":");
			generateExpr((Object[])e[3]);
			System.out.println("_"+endlab+":");
      return;
    case "WHILE":
      int labStart = nextLab++;
      int labQuit = nextLab++;
      System.out.println("_"+labStart+":");
      generateExpr((Object[])e[1]);
      System.out.println("(GoFalse _"+labQuit+")");
      generateExpr((Object[])e[2]);
      System.out.println("(Go _"+labStart+")");
      System.out.println("_"+labQuit+":");
      return;
    case "CALL":
      //e = {"CALL", name, [expr,...,expr]}
      if(e[2]==null){
          System.out.println("(Call #\""+e[1]+"[f"+0+"]\" "+0+")");
          return;
      }
      Object[] args = (Object[])e[2];
      if( args.length!=0){
        generateExpr((Object[])args[0]);
      }
      for (int i = 1; i<args.length; i++){
        System.out.println("(Push)");
        generateExpr((Object[])args[i]);
      }
      System.out.println("(Call #\""+e[1]+"[f"+args.length+"]\" "+args.length+")");
      return;
    case "STORE":
      generateExpr((Object[])e[2]);
      System.out.println("(Store "+e[1]+")");
      return;
  }
}

static void generateBody( Object[] bod )
{
	for(int i=0; i<bod.length; i++) {
		generateExpr((Object[])bod[i]);
  }
}
//#line 446 "NMorphParser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 12 "NMorph.byacc"
{ generateProgram(name,((Vector<Object>)(val_peek(0).obj)).toArray()); }
break;
case 2:
//#line 16 "NMorph.byacc"
{ ((Vector<Object>)(val_peek(1).obj)).add(val_peek(0).obj); yyval.obj=val_peek(1).obj; }
break;
case 3:
//#line 17 "NMorph.byacc"
{ yyval.obj = new Vector<Object>(); ((Vector<Object>)(yyval.obj)).add(val_peek(0).obj); }
break;
case 4:
//#line 21 "NMorph.byacc"
{
              varCount = 0;
              varTable = new HashMap<String,Integer>();
          }
break;
case 5:
//#line 26 "NMorph.byacc"
{
            yyval.obj = new Object[]{val_peek(8).sval, val_peek(5).ival, val_peek(2).ival, ((Vector<Object>)(val_peek(1).obj)).toArray()};
          }
break;
case 6:
//#line 32 "NMorph.byacc"
{ addVar(val_peek(0).sval);yyval.ival = yyval.ival+1;}
break;
case 7:
//#line 33 "NMorph.byacc"
{ addVar(val_peek(0).sval);yyval.ival = 1+val_peek(2).ival;}
break;
case 8:
//#line 37 "NMorph.byacc"
{yyval.ival = 0;}
break;
case 9:
//#line 38 "NMorph.byacc"
{ yyval.ival = val_peek(0).ival;}
break;
case 10:
//#line 42 "NMorph.byacc"
{yyval.ival = 0;}
break;
case 11:
//#line 43 "NMorph.byacc"
{yyval.ival = val_peek(2).ival+val_peek(1).ival;}
break;
case 12:
//#line 47 "NMorph.byacc"
{ addVar(val_peek(0).sval); yyval.ival=val_peek(2).ival+1; }
break;
case 13:
//#line 48 "NMorph.byacc"
{addVar(val_peek(0).sval); yyval.ival=1;}
break;
case 14:
//#line 52 "NMorph.byacc"
{yyval.obj = new Vector<Object>(); ((Vector<Object>)(yyval.obj)).add(val_peek(1).obj);}
break;
case 15:
//#line 53 "NMorph.byacc"
{yyval.obj=val_peek(2).obj; ((Vector<Object>)(val_peek(2).obj)).add(val_peek(1).obj);}
break;
case 16:
//#line 57 "NMorph.byacc"
{yyval.obj = new Object[]{"RETURN", val_peek(0).obj};}
break;
case 17:
//#line 58 "NMorph.byacc"
{yyval.obj = new Object[]{"STORE", findVar(val_peek(2).sval), val_peek(0).obj};}
break;
case 18:
//#line 59 "NMorph.byacc"
{yyval.obj = val_peek(0).obj;}
break;
case 19:
//#line 63 "NMorph.byacc"
{yyval.obj=val_peek(0).obj;}
break;
case 20:
//#line 64 "NMorph.byacc"
{yyval.obj = new Object[]{"CALL", val_peek(1).sval, new Object[]{val_peek(2).obj, val_peek(0).obj}};}
break;
case 21:
//#line 68 "NMorph.byacc"
{yyval.obj = new Object[]{"NAME", findVar(val_peek(0).sval)};}
break;
case 22:
//#line 69 "NMorph.byacc"
{yyval.obj = new Object[]{"CALL", val_peek(3).sval, ((Vector<Object>)(val_peek(1).obj)).toArray()};}
break;
case 23:
//#line 70 "NMorph.byacc"
{yyval.obj = new Object[]{"CALL", val_peek(1).sval, new Object[]{val_peek(0).obj}};}
break;
case 24:
//#line 71 "NMorph.byacc"
{ yyval.obj = new Object[]{"LITERAL", val_peek(0).sval};}
break;
case 25:
//#line 72 "NMorph.byacc"
{yyval.obj = val_peek(1).obj;}
break;
case 26:
//#line 73 "NMorph.byacc"
{ yyval.obj = new Object[]{"IF",val_peek(2).obj,val_peek(1).obj,val_peek(0).obj}; }
break;
case 27:
//#line 74 "NMorph.byacc"
{yyval.obj = new Object[]{"WHILE", val_peek(1).obj, val_peek(0).obj};}
break;
case 28:
//#line 78 "NMorph.byacc"
{ yyval.obj=new Object[]{"LITERAL","null"}; }
break;
case 29:
//#line 79 "NMorph.byacc"
{ yyval.obj=val_peek(0).obj; }
break;
case 30:
//#line 80 "NMorph.byacc"
{ yyval.obj=new Object[]{"IF",val_peek(2).obj,val_peek(1).obj,val_peek(0).obj}; }
break;
case 31:
//#line 84 "NMorph.byacc"
{yyval.obj = new Vector<Object>();}
break;
case 32:
//#line 85 "NMorph.byacc"
{yyval.obj = val_peek(0).obj;}
break;
case 33:
//#line 89 "NMorph.byacc"
{((Vector<Object>)(val_peek(2).obj)).add(val_peek(0).obj); yyval.obj=val_peek(2).obj; }
break;
case 34:
//#line 90 "NMorph.byacc"
{yyval.obj=new Vector<Object>(); ((Vector<Object>)(yyval.obj)).add(val_peek(0).obj);}
break;
case 35:
//#line 94 "NMorph.byacc"
{ yyval.obj=new Object[]{"BODY",val_peek(1).obj}; }
break;
//#line 740 "NMorphParser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public NMorphParser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public NMorphParser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################

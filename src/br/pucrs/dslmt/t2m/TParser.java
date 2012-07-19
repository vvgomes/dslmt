// $ANTLR 3.1.3 Mar 17, 2009 19:23:44 src/br/pucrs/dslmt/t2m/T.g 2009-12-15 14:58:04

package br.pucrs.dslmt.t2m;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.common.util.EList;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("unchecked")public class TParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "STRING", "CHAR", "BOOL", "INT", "REAL", "ID", "WS", "'inputAlphabet'", "'outputAlphabet'", "'start'", "'state'", "'transition'", "'/'", "'->'"
    };
    public static final int REAL=8;
    public static final int WS=10;
    public static final int T__16=16;
    public static final int T__15=15;
    public static final int T__17=17;
    public static final int T__12=12;
    public static final int T__11=11;
    public static final int T__14=14;
    public static final int T__13=13;
    public static final int BOOL=6;
    public static final int CHAR=5;
    public static final int INT=7;
    public static final int ID=9;
    public static final int EOF=-1;
    public static final int STRING=4;

    // delegates
    // delegators


        public TParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public TParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return TParser.tokenNames; }
    public String getGrammarFileName() { return "src/br/pucrs/dslmt/t2m/T.g"; }


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



    // $ANTLR start "fsm"
    // src/br/pucrs/dslmt/t2m/T.g:37:1: fsm returns [EObject node] : 'inputAlphabet' inputAlphabet= STRING 'outputAlphabet' outputAlphabet= STRING (r= state )* ;
    public final EObject fsm() throws RecognitionException {
        EObject node = null;

        Token inputAlphabet=null;
        Token outputAlphabet=null;
        EObject r = null;



        	EClass eClass= (EClass)pkg.getEClassifier("fsm");
        	node = pkg.getEFactoryInstance().create(eClass);
        	
        try {
            // src/br/pucrs/dslmt/t2m/T.g:42:2: ( 'inputAlphabet' inputAlphabet= STRING 'outputAlphabet' outputAlphabet= STRING (r= state )* )
            // src/br/pucrs/dslmt/t2m/T.g:43:2: 'inputAlphabet' inputAlphabet= STRING 'outputAlphabet' outputAlphabet= STRING (r= state )*
            {
            match(input,11,FOLLOW_11_in_fsm131); 
            inputAlphabet=(Token)match(input,STRING,FOLLOW_STRING_in_fsm136); 
            node.eSet(eClass.getEStructuralFeature("inputAlphabet"), removeDoubleQuotes((inputAlphabet!=null?inputAlphabet.getText():null)));
            match(input,12,FOLLOW_12_in_fsm141); 
            outputAlphabet=(Token)match(input,STRING,FOLLOW_STRING_in_fsm146); 
            node.eSet(eClass.getEStructuralFeature("outputAlphabet"), removeDoubleQuotes((outputAlphabet!=null?outputAlphabet.getText():null)));
            // src/br/pucrs/dslmt/t2m/T.g:47:2: (r= state )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>=13 && LA1_0<=14)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // src/br/pucrs/dslmt/t2m/T.g:47:3: r= state
            	    {
            	    pushFollow(FOLLOW_state_in_fsm154);
            	    r=state();

            	    state._fsp--;

            	    ((EList)node.eGet(eClass.getEStructuralFeature("state"))).add(r);

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return node;
    }
    // $ANTLR end "fsm"


    // $ANTLR start "state"
    // src/br/pucrs/dslmt/t2m/T.g:49:1: state returns [EObject node] : ( 'start' )? 'state' id= ID (r= transition )* ;
    public final EObject state() throws RecognitionException {
        EObject node = null;

        Token id=null;
        EObject r = null;



        	EClass eClass= (EClass)pkg.getEClassifier("state");
        	node = pkg.getEFactoryInstance().create(eClass);
        	
        try {
            // src/br/pucrs/dslmt/t2m/T.g:54:2: ( ( 'start' )? 'state' id= ID (r= transition )* )
            // src/br/pucrs/dslmt/t2m/T.g:55:2: ( 'start' )? 'state' id= ID (r= transition )*
            {
            // src/br/pucrs/dslmt/t2m/T.g:55:2: ( 'start' )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==13) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // src/br/pucrs/dslmt/t2m/T.g:55:3: 'start'
                    {
                    match(input,13,FOLLOW_13_in_state180); 
                    node.eSet(eClass.getEStructuralFeature("start"), true);

                    }
                    break;

            }

            match(input,14,FOLLOW_14_in_state187); 
            id=(Token)match(input,ID,FOLLOW_ID_in_state192); 
            node.eSet(eClass.getEStructuralFeature("id"), (id!=null?id.getText():null));
            // src/br/pucrs/dslmt/t2m/T.g:58:2: (r= transition )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==15) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // src/br/pucrs/dslmt/t2m/T.g:58:3: r= transition
            	    {
            	    pushFollow(FOLLOW_transition_in_state200);
            	    r=transition();

            	    state._fsp--;

            	    ((EList)node.eGet(eClass.getEStructuralFeature("transition"))).add(r);

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return node;
    }
    // $ANTLR end "state"


    // $ANTLR start "transition"
    // src/br/pucrs/dslmt/t2m/T.g:60:1: transition returns [EObject node] : 'transition' inputt= CHAR '/' output= CHAR '->' target= ID ;
    public final EObject transition() throws RecognitionException {
        EObject node = null;

        Token inputt=null;
        Token output=null;
        Token target=null;


        	EClass eClass= (EClass)pkg.getEClassifier("transition");
        	node = pkg.getEFactoryInstance().create(eClass);
        	
        try {
            // src/br/pucrs/dslmt/t2m/T.g:65:2: ( 'transition' inputt= CHAR '/' output= CHAR '->' target= ID )
            // src/br/pucrs/dslmt/t2m/T.g:66:2: 'transition' inputt= CHAR '/' output= CHAR '->' target= ID
            {
            match(input,15,FOLLOW_15_in_transition225); 
            inputt=(Token)match(input,CHAR,FOLLOW_CHAR_in_transition230); 
            node.eSet(eClass.getEStructuralFeature("inputt"), removeQuotes((inputt!=null?inputt.getText():null)));
            match(input,16,FOLLOW_16_in_transition235); 
            output=(Token)match(input,CHAR,FOLLOW_CHAR_in_transition240); 
            node.eSet(eClass.getEStructuralFeature("output"), removeQuotes((output!=null?output.getText():null)));
            match(input,17,FOLLOW_17_in_transition245); 
            target=(Token)match(input,ID,FOLLOW_ID_in_transition250); 
            node.eSet(eClass.getEStructuralFeature("target"), (target!=null?target.getText():null));

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return node;
    }
    // $ANTLR end "transition"

    // Delegated rules


 

    public static final BitSet FOLLOW_11_in_fsm131 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_STRING_in_fsm136 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_fsm141 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_STRING_in_fsm146 = new BitSet(new long[]{0x0000000000006002L});
    public static final BitSet FOLLOW_state_in_fsm154 = new BitSet(new long[]{0x0000000000006002L});
    public static final BitSet FOLLOW_13_in_state180 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_state187 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_ID_in_state192 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_transition_in_state200 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_15_in_transition225 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_CHAR_in_transition230 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_transition235 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_CHAR_in_transition240 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_transition245 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_ID_in_transition250 = new BitSet(new long[]{0x0000000000000002L});

}
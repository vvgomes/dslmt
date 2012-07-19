// $ANTLR 3.1.3 Mar 17, 2009 19:23:44 src/br/pucrs/dslmt/t2m/T.g 2009-12-15 14:58:04

package br.pucrs.dslmt.t2m;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class TLexer extends Lexer {
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

    public TLexer() {;} 
    public TLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public TLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "src/br/pucrs/dslmt/t2m/T.g"; }

    // $ANTLR start "T__11"
    public final void mT__11() throws RecognitionException {
        try {
            int _type = T__11;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/br/pucrs/dslmt/t2m/T.g:7:7: ( 'inputAlphabet' )
            // src/br/pucrs/dslmt/t2m/T.g:7:9: 'inputAlphabet'
            {
            match("inputAlphabet"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__11"

    // $ANTLR start "T__12"
    public final void mT__12() throws RecognitionException {
        try {
            int _type = T__12;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/br/pucrs/dslmt/t2m/T.g:8:7: ( 'outputAlphabet' )
            // src/br/pucrs/dslmt/t2m/T.g:8:9: 'outputAlphabet'
            {
            match("outputAlphabet"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__12"

    // $ANTLR start "T__13"
    public final void mT__13() throws RecognitionException {
        try {
            int _type = T__13;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/br/pucrs/dslmt/t2m/T.g:9:7: ( 'start' )
            // src/br/pucrs/dslmt/t2m/T.g:9:9: 'start'
            {
            match("start"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__13"

    // $ANTLR start "T__14"
    public final void mT__14() throws RecognitionException {
        try {
            int _type = T__14;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/br/pucrs/dslmt/t2m/T.g:10:7: ( 'state' )
            // src/br/pucrs/dslmt/t2m/T.g:10:9: 'state'
            {
            match("state"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__14"

    // $ANTLR start "T__15"
    public final void mT__15() throws RecognitionException {
        try {
            int _type = T__15;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/br/pucrs/dslmt/t2m/T.g:11:7: ( 'transition' )
            // src/br/pucrs/dslmt/t2m/T.g:11:9: 'transition'
            {
            match("transition"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__15"

    // $ANTLR start "T__16"
    public final void mT__16() throws RecognitionException {
        try {
            int _type = T__16;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/br/pucrs/dslmt/t2m/T.g:12:7: ( '/' )
            // src/br/pucrs/dslmt/t2m/T.g:12:9: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__16"

    // $ANTLR start "T__17"
    public final void mT__17() throws RecognitionException {
        try {
            int _type = T__17;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/br/pucrs/dslmt/t2m/T.g:13:7: ( '->' )
            // src/br/pucrs/dslmt/t2m/T.g:13:9: '->'
            {
            match("->"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__17"

    // $ANTLR start "STRING"
    public final void mSTRING() throws RecognitionException {
        try {
            int _type = STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/br/pucrs/dslmt/t2m/T.g:30:7: ( '\"' ( . )* '\"' )
            // src/br/pucrs/dslmt/t2m/T.g:30:9: '\"' ( . )* '\"'
            {
            match('\"'); 
            // src/br/pucrs/dslmt/t2m/T.g:30:12: ( . )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0=='\"') ) {
                    alt1=2;
                }
                else if ( ((LA1_0>='\u0000' && LA1_0<='!')||(LA1_0>='#' && LA1_0<='\uFFFF')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // src/br/pucrs/dslmt/t2m/T.g:30:12: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STRING"

    // $ANTLR start "CHAR"
    public final void mCHAR() throws RecognitionException {
        try {
            int _type = CHAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/br/pucrs/dslmt/t2m/T.g:31:5: ( '\\'' . '\\'' )
            // src/br/pucrs/dslmt/t2m/T.g:31:7: '\\'' . '\\''
            {
            match('\''); 
            matchAny(); 
            match('\''); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CHAR"

    // $ANTLR start "BOOL"
    public final void mBOOL() throws RecognitionException {
        try {
            int _type = BOOL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/br/pucrs/dslmt/t2m/T.g:32:5: ( 'true' | 'false' )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0=='t') ) {
                alt2=1;
            }
            else if ( (LA2_0=='f') ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // src/br/pucrs/dslmt/t2m/T.g:32:7: 'true'
                    {
                    match("true"); 


                    }
                    break;
                case 2 :
                    // src/br/pucrs/dslmt/t2m/T.g:32:14: 'false'
                    {
                    match("false"); 


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BOOL"

    // $ANTLR start "INT"
    public final void mINT() throws RecognitionException {
        try {
            int _type = INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/br/pucrs/dslmt/t2m/T.g:33:4: ( ( '0' .. '9' )+ )
            // src/br/pucrs/dslmt/t2m/T.g:33:6: ( '0' .. '9' )+
            {
            // src/br/pucrs/dslmt/t2m/T.g:33:6: ( '0' .. '9' )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='0' && LA3_0<='9')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // src/br/pucrs/dslmt/t2m/T.g:33:7: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INT"

    // $ANTLR start "REAL"
    public final void mREAL() throws RecognitionException {
        try {
            int _type = REAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/br/pucrs/dslmt/t2m/T.g:34:5: ( ( '0' .. '9' )+ '.' ( '0' .. '9' )+ )
            // src/br/pucrs/dslmt/t2m/T.g:34:7: ( '0' .. '9' )+ '.' ( '0' .. '9' )+
            {
            // src/br/pucrs/dslmt/t2m/T.g:34:7: ( '0' .. '9' )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>='0' && LA4_0<='9')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // src/br/pucrs/dslmt/t2m/T.g:34:8: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);

            match('.'); 
            // src/br/pucrs/dslmt/t2m/T.g:34:21: ( '0' .. '9' )+
            int cnt5=0;
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>='0' && LA5_0<='9')) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // src/br/pucrs/dslmt/t2m/T.g:34:22: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt5 >= 1 ) break loop5;
                        EarlyExitException eee =
                            new EarlyExitException(5, input);
                        throw eee;
                }
                cnt5++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "REAL"

    // $ANTLR start "ID"
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/br/pucrs/dslmt/t2m/T.g:35:3: ( ( 'a' .. 'z' | 'A' .. 'Z' )+ )
            // src/br/pucrs/dslmt/t2m/T.g:35:5: ( 'a' .. 'z' | 'A' .. 'Z' )+
            {
            // src/br/pucrs/dslmt/t2m/T.g:35:5: ( 'a' .. 'z' | 'A' .. 'Z' )+
            int cnt6=0;
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( ((LA6_0>='A' && LA6_0<='Z')||(LA6_0>='a' && LA6_0<='z')) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // src/br/pucrs/dslmt/t2m/T.g:
            	    {
            	    if ( (input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt6 >= 1 ) break loop6;
                        EarlyExitException eee =
                            new EarlyExitException(6, input);
                        throw eee;
                }
                cnt6++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ID"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/br/pucrs/dslmt/t2m/T.g:36:3: ( ( ' ' | '\\t' | '\\n' | '\\r' )+ )
            // src/br/pucrs/dslmt/t2m/T.g:36:5: ( ' ' | '\\t' | '\\n' | '\\r' )+
            {
            // src/br/pucrs/dslmt/t2m/T.g:36:5: ( ' ' | '\\t' | '\\n' | '\\r' )+
            int cnt7=0;
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( ((LA7_0>='\t' && LA7_0<='\n')||LA7_0=='\r'||LA7_0==' ') ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // src/br/pucrs/dslmt/t2m/T.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt7 >= 1 ) break loop7;
                        EarlyExitException eee =
                            new EarlyExitException(7, input);
                        throw eee;
                }
                cnt7++;
            } while (true);

            skip();

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WS"

    public void mTokens() throws RecognitionException {
        // src/br/pucrs/dslmt/t2m/T.g:1:8: ( T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | STRING | CHAR | BOOL | INT | REAL | ID | WS )
        int alt8=14;
        alt8 = dfa8.predict(input);
        switch (alt8) {
            case 1 :
                // src/br/pucrs/dslmt/t2m/T.g:1:10: T__11
                {
                mT__11(); 

                }
                break;
            case 2 :
                // src/br/pucrs/dslmt/t2m/T.g:1:16: T__12
                {
                mT__12(); 

                }
                break;
            case 3 :
                // src/br/pucrs/dslmt/t2m/T.g:1:22: T__13
                {
                mT__13(); 

                }
                break;
            case 4 :
                // src/br/pucrs/dslmt/t2m/T.g:1:28: T__14
                {
                mT__14(); 

                }
                break;
            case 5 :
                // src/br/pucrs/dslmt/t2m/T.g:1:34: T__15
                {
                mT__15(); 

                }
                break;
            case 6 :
                // src/br/pucrs/dslmt/t2m/T.g:1:40: T__16
                {
                mT__16(); 

                }
                break;
            case 7 :
                // src/br/pucrs/dslmt/t2m/T.g:1:46: T__17
                {
                mT__17(); 

                }
                break;
            case 8 :
                // src/br/pucrs/dslmt/t2m/T.g:1:52: STRING
                {
                mSTRING(); 

                }
                break;
            case 9 :
                // src/br/pucrs/dslmt/t2m/T.g:1:59: CHAR
                {
                mCHAR(); 

                }
                break;
            case 10 :
                // src/br/pucrs/dslmt/t2m/T.g:1:64: BOOL
                {
                mBOOL(); 

                }
                break;
            case 11 :
                // src/br/pucrs/dslmt/t2m/T.g:1:69: INT
                {
                mINT(); 

                }
                break;
            case 12 :
                // src/br/pucrs/dslmt/t2m/T.g:1:73: REAL
                {
                mREAL(); 

                }
                break;
            case 13 :
                // src/br/pucrs/dslmt/t2m/T.g:1:78: ID
                {
                mID(); 

                }
                break;
            case 14 :
                // src/br/pucrs/dslmt/t2m/T.g:1:81: WS
                {
                mWS(); 

                }
                break;

        }

    }


    protected DFA8 dfa8 = new DFA8(this);
    static final String DFA8_eotS =
        "\1\uffff\4\13\4\uffff\1\13\1\23\2\uffff\5\13\2\uffff\13\13\1\46"+
        "\3\13\1\52\1\53\1\13\1\uffff\1\46\2\13\2\uffff\14\13\1\73\2\13\1"+
        "\uffff\2\13\1\100\1\13\1\uffff\1\102\1\uffff";
    static final String DFA8_eofS =
        "\103\uffff";
    static final String DFA8_minS =
        "\1\11\1\156\1\165\1\164\1\162\4\uffff\1\141\1\56\2\uffff\1\160\1"+
        "\164\2\141\1\154\2\uffff\1\165\1\160\1\162\1\156\1\145\1\163\1\164"+
        "\1\165\1\164\1\145\1\163\1\101\1\145\1\101\1\164\2\101\1\151\1\uffff"+
        "\1\101\1\154\1\101\2\uffff\1\164\1\160\1\154\1\151\1\150\1\160\1"+
        "\157\1\141\1\150\1\156\1\142\1\141\1\101\1\145\1\142\1\uffff\1\164"+
        "\1\145\1\101\1\164\1\uffff\1\101\1\uffff";
    static final String DFA8_maxS =
        "\1\172\1\156\1\165\1\164\1\162\4\uffff\1\141\1\71\2\uffff\1\160"+
        "\1\164\1\141\1\165\1\154\2\uffff\1\165\1\160\1\164\1\156\1\145\1"+
        "\163\1\164\1\165\1\164\1\145\1\163\1\172\1\145\1\101\1\164\2\172"+
        "\1\151\1\uffff\1\172\1\154\1\101\2\uffff\1\164\1\160\1\154\1\151"+
        "\1\150\1\160\1\157\1\141\1\150\1\156\1\142\1\141\1\172\1\145\1\142"+
        "\1\uffff\1\164\1\145\1\172\1\164\1\uffff\1\172\1\uffff";
    static final String DFA8_acceptS =
        "\5\uffff\1\6\1\7\1\10\1\11\2\uffff\1\15\1\16\5\uffff\1\14\1\13\22"+
        "\uffff\1\12\3\uffff\1\3\1\4\17\uffff\1\5\4\uffff\1\1\1\uffff\1\2";
    static final String DFA8_specialS =
        "\103\uffff}>";
    static final String[] DFA8_transitionS = {
            "\2\14\2\uffff\1\14\22\uffff\1\14\1\uffff\1\7\4\uffff\1\10\5"+
            "\uffff\1\6\1\uffff\1\5\12\12\7\uffff\32\13\6\uffff\5\13\1\11"+
            "\2\13\1\1\5\13\1\2\3\13\1\3\1\4\6\13",
            "\1\15",
            "\1\16",
            "\1\17",
            "\1\20",
            "",
            "",
            "",
            "",
            "\1\21",
            "\1\22\1\uffff\12\12",
            "",
            "",
            "\1\24",
            "\1\25",
            "\1\26",
            "\1\27\23\uffff\1\30",
            "\1\31",
            "",
            "",
            "\1\32",
            "\1\33",
            "\1\34\1\uffff\1\35",
            "\1\36",
            "\1\37",
            "\1\40",
            "\1\41",
            "\1\42",
            "\1\43",
            "\1\44",
            "\1\45",
            "\32\13\6\uffff\32\13",
            "\1\47",
            "\1\50",
            "\1\51",
            "\32\13\6\uffff\32\13",
            "\32\13\6\uffff\32\13",
            "\1\54",
            "",
            "\32\13\6\uffff\32\13",
            "\1\55",
            "\1\56",
            "",
            "",
            "\1\57",
            "\1\60",
            "\1\61",
            "\1\62",
            "\1\63",
            "\1\64",
            "\1\65",
            "\1\66",
            "\1\67",
            "\1\70",
            "\1\71",
            "\1\72",
            "\32\13\6\uffff\32\13",
            "\1\74",
            "\1\75",
            "",
            "\1\76",
            "\1\77",
            "\32\13\6\uffff\32\13",
            "\1\101",
            "",
            "\32\13\6\uffff\32\13",
            ""
    };

    static final short[] DFA8_eot = DFA.unpackEncodedString(DFA8_eotS);
    static final short[] DFA8_eof = DFA.unpackEncodedString(DFA8_eofS);
    static final char[] DFA8_min = DFA.unpackEncodedStringToUnsignedChars(DFA8_minS);
    static final char[] DFA8_max = DFA.unpackEncodedStringToUnsignedChars(DFA8_maxS);
    static final short[] DFA8_accept = DFA.unpackEncodedString(DFA8_acceptS);
    static final short[] DFA8_special = DFA.unpackEncodedString(DFA8_specialS);
    static final short[][] DFA8_transition;

    static {
        int numStates = DFA8_transitionS.length;
        DFA8_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA8_transition[i] = DFA.unpackEncodedString(DFA8_transitionS[i]);
        }
    }

    class DFA8 extends DFA {

        public DFA8(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 8;
            this.eot = DFA8_eot;
            this.eof = DFA8_eof;
            this.min = DFA8_min;
            this.max = DFA8_max;
            this.accept = DFA8_accept;
            this.special = DFA8_special;
            this.transition = DFA8_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | STRING | CHAR | BOOL | INT | REAL | ID | WS );";
        }
    }
 

}
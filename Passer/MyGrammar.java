import computation.contextfreegrammar.*;
import java.util.ArrayList;
import java.util.List;

public class MyGrammar {
	public static ContextFreeGrammar makeGrammar() {
		// You can write your code here to make the context-free grammar from the assignment
		Variable S = new Variable ('S');
    Variable E = new Variable ('E');
    Variable T = new Variable ('T');
    Variable F = new Variable('F');
    Variable A = new Variable('A');
    Variable B = new Variable('B');
    Variable C = new Variable('C');
    Variable D = new Variable('D');
    Variable G = new Variable('G');
    Variable H = new Variable('H');
    Variable I = new Variable('I');
    Variable V = new Variable('V');
    

    Terminal p = new Terminal('p');
    Terminal r = new Terminal('r');
    Terminal minus = new Terminal('-');
    Terminal plus = new Terminal('+');
    Terminal closeB = new Terminal(')');
    Terminal openB = new Terminal('(');
    Terminal and = new Terminal('&');

    Rule r1 = new Rule(S, new Word(E,H));
    Rule r2 = new Rule(S, new Word(T,A));
    Rule r3 = new Rule(S, new Word(C,D));
    Rule r4 = new Rule(S, new Word(G,F));
    Rule r5 = new Rule(S, new Word(p));
    Rule r6 = new Rule(S, new Word(r));

    Rule r7 = new Rule(E, new Word(E,H));
    Rule r8 = new Rule(E, new Word(T,A));
    Rule r9 = new Rule(E, new Word(C,D));
    Rule r10 = new Rule(E, new Word(G,F));
    Rule r11 = new Rule(E, new Word(p));
    Rule r12 = new Rule(E, new Word(r));

    Rule r13 = new Rule(T, new Word(T,A));
    Rule r14 = new Rule(T, new Word(C,D));
    Rule r15 = new Rule(T, new Word(G,F));
    Rule r16 = new Rule(T, new Word(p));
    Rule r17 = new Rule(T, new Word(r));

    Rule r18 = new Rule(F, new Word(C,D));
    Rule r19 = new Rule(F, new Word(G,F));
    Rule r20 = new Rule(F, new Word(p));
    Rule r21 = new Rule(F, new Word(r));

    Rule r22 = new Rule(A, new Word(B,F));
    Rule r23 = new Rule(B, new Word(and));
    Rule r24 = new Rule(C, new Word(openB));
    Rule r25 = new Rule(D, new Word(E,V));
    Rule r26 = new Rule(V, new Word(closeB));
    Rule r27 = new Rule(G, new Word(minus));
    Rule r28 = new Rule(H, new Word(I,T));
    Rule r29 = new Rule(I, new Word(plus));

    List<Rule> rules = new ArrayList<Rule>();
    rules.add(r1);
    rules.add(r2);
    rules.add(r3);
    rules.add(r4);
    rules.add(r5);
    rules.add(r6);
    rules.add(r7);
    rules.add(r8);
    rules.add(r9);
    rules.add(r10);
    rules.add(r11);
    rules.add(r12);
    rules.add(r13);
    rules.add(r14);
    rules.add(r15);
    rules.add(r16);
    rules.add(r17);
    rules.add(r18);
    rules.add(r19);
    rules.add(r20);
    rules.add(r21);
    rules.add(r22);
    rules.add(r23);
    rules.add(r24);
    rules.add(r25);
    rules.add(r26);
    rules.add(r27);
    rules.add(r28);
    rules.add(r29);

    ContextFreeGrammar cfg = new ContextFreeGrammar(rules);
    return cfg;


		//return null;
	}
 
}

import computation.contextfreegrammar.*;
import computation.parser.*;
import computation.parsetree.*;
import computation.derivation.*;
import java.io.*;
import java.util.*;

public class Parser implements IParser {

  public boolean isInLanguage(ContextFreeGrammar cfg, Word w) {
   
    ArrayList<Word> active_strings = new ArrayList<Word>();
    active_strings.add(new Word(cfg.getStartVariable()));

    int length = w.length();

    int steps = 0;
    int n = length;
    while (steps < (2 * n) - 1) {
      ArrayList<Word> new_active_string = new ArrayList<Word>();
      for (Word each : active_strings) {
        for (Rule rule : cfg.getRules()) {
          //e.g. first rule is S->...
          //below counts how many first rule variable'S' is in the word
          int k = each.count(rule.getVariable());
          //below going to exspand S number of times it comes up
          for (int i=0; i<k; i++){
            //below, index will be where the nth occurance of "S" comes up
            //e.g SAS would be index[0] and then 2 when i = 2
            int index = each.indexOfNth(rule.getVariable(), i);
            //replaces the given index of the word with replacer
            Word replacer = each.replace(index, rule.getExpansion());
            new_active_string.add(replacer);
          }
        }
      }
        steps++;
        active_strings = new_active_string;
        }
        if(active_strings.contains(w)){
          return true;
      }
    return false ;
  }


  public Derivation getDerivation(ContextFreeGrammar cfg, Word w) {
    // public Derivation getDerivation(ContextFreeGrammar cfg, Word w)
    // {............}
    // public boolean isInLanguage(ContextFreeGrammar cfg, Word w) {............}
    int derivationLength;

    if(w.length() == 0) { 
      derivationLength = 1;}
    else {
      derivationLength = 2*w.length() - 1;}

    List<Derivation> derivations = new ArrayList<>(); //* create a dynamic list of objects of the Derivation class
    Derivation start = new Derivation(new Word(cfg.getStartVariable())); //* new Derivation object
    derivations.add(start);
 

    int steps = 0;
    int n = derivationLength;
    while (steps < derivationLength) {
      List<Derivation> newderivations = new ArrayList<>();
      for (Derivation each2 : derivations) {
        Word latest = each2.getLatestWord();
        for (Rule rule : cfg.getRules()){

          Variable current = rule.getVariable();
          int L = latest.count(current);
          for(int i = 0; i < L; i++){
    
              int index = latest.indexOfNth(rule.getVariable(), i);
              Word replacer2 = latest.replace(index, rule.getExpansion());

              Derivation one = new Derivation(each2);
              one.addStep(replacer2, rule, index);
              newderivations.add(one);
          }
        } 
        derivations = newderivations; 
      }
      steps++;
    }
          for(Derivation derivate:derivations){
        if(derivate.getLatestWord().equals(w)){
          System.out.println("Word is in language");
          return derivate;

        }
      }
      System.out.println("Word not in language");
    return null;
  }

  ////////////////////////////////////////////
    
  //function to create the parse tree 
  public ParseTreeNode generateParseTree(ContextFreeGrammar cfg, Word w) {


    Derivation words = getDerivation(cfg, w);
    
  
    if(words == null){
      return null;}
    
    ParseTreeNode node =  createParseTree(words);
    node.print();
    return node;
    
  }


  private ParseTreeNode createParseTree(Derivation derivation){
    Word finalWord = derivation.getLatestWord(); 
    

    LinkedList<ParseTreeNode> backwardsDerive = new LinkedList<>();

    for(int i = 0; i < finalWord.length(); i++) {
      backwardsDerive.add(new ParseTreeNode(finalWord.get(i)));// for word 0011 adds 0 then 0 then 1 then 1
    }
    //backwards derive = [0, 0, 1, 1]
    //done

    for(Step s : derivation) // derivation passed index
    //for each step (word rule index) in derivation
    {    
      if(s.isStartSymbol()) { 
          break; }
      //If the expansion is equal to 1 it must be a terminal symbol 
          else if (s.getRule().getExpansion().length() == 1){
            int index = s.getIndex();
            //use this index to make the new node
            ParseTreeNode pairup = new ParseTreeNode(s.getRule().getVariable(), backwardsDerive.get(index));
            // make this parse tree node for everything 
            //pairup.print();

            backwardsDerive.set(index, pairup);  
          }
          //if length 2 must be two non terminals  as per chomsky normal form 
          else if(s.getRule().getExpansion().length() == 2){
            int index = s.getIndex();
            int index2 = index+1;

            ParseTreeNode pairup = new ParseTreeNode(s.getRule().getVariable(), backwardsDerive.get(index), backwardsDerive.get(index2));
            
            backwardsDerive.set(index, pairup);
            backwardsDerive.remove(index2);
              
          }    
    }

            
    assert(backwardsDerive.size() == 1); //* Termination and return
    return backwardsDerive.get(0);//getting the 0th element from the list 
  }




  //Hard coded to print out greater than four character 

    public void Tree() {
		Terminal p = new Terminal('p');
		Terminal and = new Terminal('&');
    Terminal b1 = new Terminal('(');
    Terminal b2 = new Terminal(')');
    Terminal r = new Terminal('r');
    Terminal plus = new Terminal('+');
    Terminal minus = new Terminal('-');
  
		Variable E = new Variable("E");
		Variable T = new Variable('T');
		Variable A = new Variable('A');
		Variable B = new Variable('B');
		Variable C = new Variable('C');
    Variable D = new Variable('D');
		Variable V = new Variable('V');
    Variable H = new Variable('H');
    Variable I = new Variable('I');
    Variable F = new Variable('F');
    Variable G = new Variable('G');
		
	
		ParseTreeNode node1 = new ParseTreeNode(p);
		ParseTreeNode node2 = new ParseTreeNode(and);
		ParseTreeNode node3 = new ParseTreeNode(b1);
		ParseTreeNode node4 = new ParseTreeNode(r);
    ParseTreeNode node5 = new ParseTreeNode(plus);
    ParseTreeNode node6 = new ParseTreeNode(p);
    ParseTreeNode node7 = new ParseTreeNode(b2);
    ParseTreeNode node78 = new ParseTreeNode(minus);
    
	
    ParseTreeNode newnode8 = new ParseTreeNode(G, node78);
		ParseTreeNode newnode9 = new ParseTreeNode(B, node2);
		ParseTreeNode newnode10 = new ParseTreeNode(F, node4);
		ParseTreeNode newnode11 = new ParseTreeNode(E, node4);
    ParseTreeNode newnode12 = new ParseTreeNode(I, node5);
    ParseTreeNode newnode13 = new ParseTreeNode(T, node1);
    ParseTreeNode newnode14 = new ParseTreeNode(V, node7);
    ParseTreeNode newnode44 = new ParseTreeNode(C, node3);

    ParseTreeNode newnode15 = new ParseTreeNode(A, newnode9, newnode10);
    ParseTreeNode newnode16 = new ParseTreeNode(E, newnode13, newnode15);
    ParseTreeNode newnode17 = new ParseTreeNode(D, newnode16, newnode14);
    ParseTreeNode newnode18 = new ParseTreeNode(F, newnode44, newnode17);
    ParseTreeNode newnode19 = new ParseTreeNode(E, newnode8, newnode18);

  
		ParseTreeNode node8 = new ParseTreeNode(T, node1);
		ParseTreeNode node9 = new ParseTreeNode(B, node2);
		ParseTreeNode node10 = new ParseTreeNode(C, node3);
		ParseTreeNode node11 = new ParseTreeNode(E, node4);
    ParseTreeNode node12 = new ParseTreeNode(I, node5);
    ParseTreeNode node13 = new ParseTreeNode(T, node1);
    ParseTreeNode node14 = new ParseTreeNode(V, node7);

    ParseTreeNode node15 = new ParseTreeNode(H, node12, node8);
    ParseTreeNode node16 = new ParseTreeNode(E, node11,node15);
    ParseTreeNode node17 = new ParseTreeNode(D, node16,node14);
    ParseTreeNode node18 = new ParseTreeNode(F, node10,node17);
    ParseTreeNode node19 = new ParseTreeNode(A, node9,node18);
    ParseTreeNode node20 = new ParseTreeNode(E, node8,node19);
    
    
		// Below print statement for the hard coded trees
		newnode19.print();
    node20.print();

    }

}



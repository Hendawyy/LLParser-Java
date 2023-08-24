import java.util.Scanner;
import java.util.Stack;
public class LLParser {

 public String input="";
 private int count=-1;
 Stack <String> strack=new Stack<String>();
    
String [][] table=
{
 {null,null,null,null,"TQ",null,"TQ",null}
            ,
 {"+TQ","-TQ",null,null,null,"",null,""}
            ,
 {null,null,null,null,"FR",null,"FR",null}
            ,
 {"","","*FR","/FR",null,"",null,""}
            ,
 {null,null,null,null,"(E)",null,"a",null}
};
    
String [] nonTers={"E","Q","T","R","F"};
String [] terminals={"+","-","*","/","(",")","a","$"};


public LLParser(String in)
{
this.input=in;
}

private  void pushRule(String rule)
{
for(int i=rule.length()-1;i>=0;i--)
{
char ch=rule.charAt(i);
String str=String.valueOf(ch);
push(str);
}
}

public void LLPARSING()
{
    push(this.input.charAt(0)+"");
    push("E");

    String token=scan();
    String top=null;

do
  {
     top=this.pop();
     if(isNonTerminal(top))
     {
        String rule=this.getRule(top, token);
        this.pushRule(rule);
     }
       
     else if(isTerminal(top))
     {
        if(!top.equals(token)) 
        {
          System.out.println("Token is not correct. Token : ("+token+")");
        }
        else
        {
          token =scan();
         }
      }
      else
      {
    	  System.out.println("String can't be accepted");
      }
if(token.equals("$"))
{break;}}while(true);

    if(token.equals("$"))
    {
     System.out.println("The entered string is: "+input);
     System.out.println("String is accepted by LL(1) parser");
    }
    else
    {
    	System.out.println("The entered string is: "+input);
    	System.out.println("String is rejected by LL(1) parser");
    }
}

    private boolean isTerminal(String s) {
               for(int i=0;i<this.terminals.length;i++)
        {
        if(s.equals(this.terminals[i]))
        {
        return true;
        }

        }
        return false;
    }

    private boolean isNonTerminal(String s) {
        for(int i=0;i<this.nonTers.length;i++)
        {
        if(s.equals(this.nonTers[i]))
        {
        return true;
        }
        }
       return false;
    }

  private String scan() {
   count++;
   char ch=this.input.charAt(count);
   String str=String.valueOf(ch);
   return str;
    }

  private void push(String s) { this.strack.push(s); }
  private String pop() { return this.strack.pop(); }


    public String getRule(String non,String term)
    {
    int row=getnonTermIndex(non);
    int column=getTermIndex(term);
    String rule=this.table[row][column];
    if(rule==null)
    {
     System.out.println("The entered string is: "+input);
     System.out.println("String is rejected");
    }
    return rule;
    }

    private int getnonTermIndex(String non) {
       for(int i=0;i<this.nonTers.length;i++)
       {
       if(non.equals(this.nonTers[i]))
       {
       return i;
       }
       }
       System.out.println(non +" is not Non-Terminal");
       return -1;
       }

    private int getTermIndex(String term) {
              for(int i=0;i<this.terminals.length;i++)
       {
       if(term.equals(this.terminals[i]))
       {
       return i;
       }
       }
       System.out.println(term +" is not Terminal");
       return -1;
       }

public static void main(String[] args) {
// TODO code application logic here
System.out.println("Enter a  string to be parsed:");
Scanner sc = new Scanner(System.in);
String parse = sc.nextLine();
LLParser ll = new LLParser(parse+"$");
ll.LLPARSING();
    }
}
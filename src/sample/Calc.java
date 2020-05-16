package sample;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;
import java.util.StringTokenizer;
//this class uses reverse polish notation
public class Calc {
    private final StringBuilder exitText=new StringBuilder();
    //for example: change sin(2+3)*5 to sin ( 2 3 + ) 5 *
    public void changeEnterToExit(String enterText){
        Stack<String> stack=new Stack<>();
        StringTokenizer stringTokenizer=new StringTokenizer(enterText,"+-*/(),",true);
        while (stringTokenizer.hasMoreTokens()){
            String element=stringTokenizer.nextToken();
            switch (element) {
                case "+":
                case "*":
                case "-":
                case "/":
                    while (!stack.empty() && getPriority(stack.peek()) >= getPriority(element))
                        exitText.append(stack.pop()).append(" ");

                    stack.push(element);
                    break;
                case "sin": {
                    StringBuilder sub = new StringBuilder();
                    int k = 0;//variable k counts number of brackets for example:for sin((2+3)*7) sub is ((2+3)*7)
                    while ((!element.equals(")")) || (k != 0)) {
                        element = stringTokenizer.nextToken();
                        if (element.equals("(")) k++;
                        if (element.equals(")")) k--;
                        sub.append(element);
                    }

                    exitText.append("sin ( ");
                    changeEnterToExit(sub.toString());
                    exitText.append(") ");
                    break;
                }
                case "cos": {
                    StringBuilder sub = new StringBuilder();
                    int k = 0;
                    while ((!element.equals(")")) || (k != 0)) {
                        element = stringTokenizer.nextToken();
                        if (element.equals("(")) k++;
                        if (element.equals(")")) k--;
                        sub.append(element);
                    }

                    exitText.append("cos ( ");
                    changeEnterToExit(sub.toString());
                    exitText.append(") ");
                    break;
                }
                case "log": {
                    StringBuilder sub = new StringBuilder();
                    int k = 0;
                    while ((!element.equals(")")) || (k != 0)) {
                        element = stringTokenizer.nextToken();
                        if (element.equals("(")) k++;
                        if (element.equals(")")) k--;
                        sub.append(element);
                    }

                    exitText.append("log ( ");
                    changeEnterToExit(sub.toString());
                    exitText.append(") ");
                    break;
                }
                case "ln": {
                    StringBuilder sub = new StringBuilder();
                    int k = 0;
                    while ((!element.equals(")")) || (k != 0)) {
                        element = stringTokenizer.nextToken();
                        if (element.equals("(")) k++;
                        if (element.equals(")")) k--;
                        sub.append(element);
                    }

                    exitText.append("ln ( ");
                    changeEnterToExit(sub.toString());
                    exitText.append(") ");
                    break;
                }
                case "abs": {
                    StringBuilder sub = new StringBuilder();
                    int k = 0;
                    while ((!element.equals(")")) || (k != 0)) {
                        element = stringTokenizer.nextToken();
                        if (element.equals("(")) k++;
                        if (element.equals(")")) k--;
                        sub.append(element);
                    }

                    exitText.append("abs ( ");
                    changeEnterToExit(sub.toString());
                    exitText.append(") ");
                    break;
                }
                case "sqrt": {
                    StringBuilder sub = new StringBuilder();
                    int k = 0;
                    while ((!element.equals(")")) || (k != 0)) {
                        element = stringTokenizer.nextToken();
                        if (element.equals("(")) k++;
                        if (element.equals(")")) k--;
                        sub.append(element);
                    }

                    exitText.append("sqrt ( ");
                    changeEnterToExit(sub.toString());
                    exitText.append(") ");
                    break;
                }
                case "pow": {
                    StringBuilder sub = new StringBuilder();
                    int k = 0;
                    stringTokenizer.nextToken();
                    element = stringTokenizer.nextToken();
                    while ((!element.equals(",")) || (k != 0)) {
                        if (element.equals("(")) k++;
                        if (element.equals(")")) k--;
                        sub.append(element);
                        element = stringTokenizer.nextToken();
                    }

                    exitText.append("pow ( ");
                    changeEnterToExit(sub.toString());
                    exitText.append(", ");
                    sub = new StringBuilder();
                    k = 0;
                    element = stringTokenizer.nextToken();
                    while ((!element.equals(")")) || (k != 0)) {
                        if (element.equals("(")) k++;
                        if (element.equals(")")) k--;
                        sub.append(element);
                        element = stringTokenizer.nextToken();
                    }
                    changeEnterToExit(sub.toString());
                    exitText.append(") ");
                    break;
                }
                case "(":
                    stack.push(element);
                    break;
                case ")":

                    while (!stack.peek().equals("(")) exitText.append(stack.pop()).append(" ");
                    stack.pop();
                    break;
                default:
                    exitText.append(element).append(" ");
                    break;
            }
        }
        while(!stack.empty()) exitText.append(stack.pop()) .append(" ");

    }
    // calculate from reverse expression
    public double calculate(double x,String exitText)throws EmptyStackException {
        Stack<Double>stack=new Stack<>();
        StringTokenizer stringTokenizer=new StringTokenizer(exitText," ");
        while(stringTokenizer.hasMoreTokens()) {
            String element = stringTokenizer.nextToken();
            if (!element.equals("+") && !element.equals("*") && !element.equals("-") && !element.equals("/")
                    && !element.equals("x")&& !element.equals("sin") && !element.equals("cos") && !element.equals("log")
                    && !element.equals("ln") && !element.equals("pow")&& !element.equals("abs") && !element.equals("sqrt")
                    && !element.equals("(") && !element.equals(")")) {
                double value = Double.parseDouble(element);
                stack.push(value);
            }
            else if(element.equals("x")){
                stack.push(x);
            }
            else if(element.equals("sin")){
                StringBuilder sub= new StringBuilder();
                int  k=0;
                for(int i=0;i<=1;i++){
                    element=stringTokenizer.nextToken();
                }

                while((!element.equals(")"))||(k!=0)){
                    if(element.equals("("))k++;
                    if(element.equals(")"))k--;
                    sub.append(element).append(" ");
                    element=stringTokenizer.nextToken();
                }
                double arg=calculate(x, sub.toString());
                stack.push(Math.sin(arg));
            }
            else if(element.equals("cos")){
                StringBuilder sub= new StringBuilder();
                int  k=0;
                for(int i=0;i<=1;i++){
                    element=stringTokenizer.nextToken();
                }

                while((!element.equals(")"))||(k!=0)){
                    if(element.equals("("))k++;
                    if(element.equals(")"))k--;
                    sub.append(element).append(" ");
                    element=stringTokenizer.nextToken();
                }
                double arg=calculate(x, sub.toString());
                stack.push(Math.cos(arg));
            }
            else if(element.equals("log")){
                StringBuilder sub= new StringBuilder();
                int  k=0;
                for(int i=0;i<=1;i++){
                    element=stringTokenizer.nextToken();
                }

                while((!element.equals(")"))||(k!=0)){
                    if(element.equals("("))k++;
                    if(element.equals(")"))k--;
                    sub.append(element).append(" ");
                    element=stringTokenizer.nextToken();
                }
                double arg=calculate(x, sub.toString());
                stack.push(Math.log10(arg));
            }
            else if(element.equals("ln")){
                StringBuilder sub= new StringBuilder();
                int  k=0;
                for(int i=0;i<=1;i++){
                    element=stringTokenizer.nextToken();
                }

                while((!element.equals(")"))||(k!=0)){
                    if(element.equals("("))k++;
                    if(element.equals(")"))k--;
                    sub.append(element).append(" ");
                    element=stringTokenizer.nextToken();
                }
                double arg=calculate(x, sub.toString());
                stack.push(Math.log(arg));
            }
            else if(element.equals("abs")){
                StringBuilder sub= new StringBuilder();
                int  k=0;
                for(int i=0;i<=1;i++){
                    element=stringTokenizer.nextToken();
                }

                while((!element.equals(")"))||(k!=0)){
                    if(element.equals("("))k++;
                    if(element.equals(")"))k--;
                    sub.append(element).append(" ");
                    element=stringTokenizer.nextToken();
                }
                double arg=calculate(x, sub.toString());
                stack.push(Math.abs(arg));
            }
            else if(element.equals("sqrt")){
                StringBuilder sub= new StringBuilder();
                int  k=0;
                for(int i=0;i<=1;i++){
                    element=stringTokenizer.nextToken();
                }

                while((!element.equals(")"))||(k!=0)){
                    if(element.equals("("))k++;
                    if(element.equals(")"))k--;
                    sub.append(element).append(" ");
                    element=stringTokenizer.nextToken();
                }
                double arg=calculate(x, sub.toString());
                stack.push(Math.sqrt(arg));
            }
            else if(element.equals("pow")){
                StringBuilder sub= new StringBuilder();
                int  k=0;
                for(int i=0;i<=1;i++){
                    element=stringTokenizer.nextToken();
                }

                while((!element.equals(","))||(k!=0)){
                    if(element.equals("("))k++;
                    if(element.equals(")"))k--;
                    sub.append(element).append(" ");
                    element=stringTokenizer.nextToken();
                }
                double arg1=calculate(x, sub.toString());
                sub = new StringBuilder();
                k=0;
                element=stringTokenizer.nextToken();
                while((!element.equals(")"))||(k!=0)){
                    if(element.equals("("))k++;
                    if(element.equals(")"))k--;
                    sub.append(element).append(" ");
                    element=stringTokenizer.nextToken();
                }
                double arg2=calculate(x, sub.toString());
                stack.push(Math.pow(arg1,arg2));
            }
            else {
                double valueOne = stack.pop();
                double valueTwo = stack.pop();
                switch(element.charAt(0)) {
                    case '*': {
                        stack.push(valueTwo * valueOne);
                        break;
                    }
                    case '+': {
                        stack.push(valueTwo + valueOne);
                        break;
                    }
                    case '-': {
                        stack.push(valueTwo - valueOne);
                        break;
                    }
                    case '/': {
                        stack.push(valueTwo / valueOne);
                        break;
                    }
                }
            }
        }
        return stack.pop();
    }
    //priority * / is greater than + -
    private int getPriority(String operator) {

        if(operator.equals("+") || operator.equals("-")) return 1;
        else if(operator.equals("*") || operator.equals("/")) return 2;
        else return 0;
    }
    //check if numbers of brackets "("==numbers of brackets")" and their order
    public boolean checkBrackets(String enterText){
        Stack<String> stackForBrackets = new Stack<>();
        StringTokenizer st = new StringTokenizer(enterText, "()",true);
        while(st.hasMoreTokens()) {
            String elementInBracket = st.nextToken();
            if(elementInBracket.equals("(")) stackForBrackets.push(elementInBracket);

            if(elementInBracket.equals(")")) {
                if (stackForBrackets.isEmpty()) return false;
                if (!stackForBrackets.pop().equals("(")) return false;
            }
        }
        return stackForBrackets.isEmpty();
    }
    //for example: change (-4)*(-sin(x)) to (0-4)*(0-sin(x))
    public String changeTextWithNegativeNumbers(String text){
        StringBuilder stringBuilder=new StringBuilder(text);
        ArrayList<Integer> list=new ArrayList<>();
        StringTokenizer stringTokenizer=new StringTokenizer(text,"-(",true);
        int fromIndex=0;
        while(stringTokenizer.hasMoreTokens()){
            String element=stringTokenizer.nextToken();
            if(element.equals("(")){
                if((stringTokenizer.nextToken()).equals("-")){
                    int index=text.indexOf('(',fromIndex);
                    fromIndex=index+1;
                    list.add(index);
                }
                else{
                    fromIndex=text.indexOf("(",fromIndex)+1;
                }
            }
        }
        int counter=1;
        for(Integer element:list){
            stringBuilder.insert(element+counter,'0');
            counter++;

        }
        return stringBuilder.toString();
    }
    public String getExitText(){
        return exitText.toString();
    }
}


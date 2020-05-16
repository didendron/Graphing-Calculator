package sample;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.EmptyStackException;


public class Board {
    private TextField result;
    private GridPane gridPane;
    private StackPane stackPane;
    private LineChart<Number,Number> chart;
    private double oldX;
    private double oldY;
    private double translX =0;
    private double translY =0;



    public void drawBoard(){
        gridPane =new GridPane();
        EventHandler<ActionEvent> insert = new InsertAction();
        EventHandler<ActionEvent> clear = new ClearAction();
        EventHandler<ActionEvent> draw = new DrawAction();

        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.setAlignment(Pos.TOP_CENTER);

        result=new TextField("");
        result.setEditable(true);
        result.setFocusTraversable(true);
        result.setAlignment(Pos.CENTER_RIGHT);
        gridPane.add(result,0,0,7,1);

        Button seven = new Button("7");
        addButton(insert, seven);
        gridPane.add(seven,0,1,1,1);

        Button eight = new Button("8");
        addButton(insert, eight);
        gridPane.add(eight,1,1,1,1);

        Button nine = new Button("9");
        addButton(insert, nine);
        gridPane.add(nine,2,1,1,1);

        Button divide = new Button("/");
        addButton(insert, divide);
        gridPane.add(divide,3,1,1,1);

        Button xButton = new Button("x");
        addButton(insert, xButton);
        gridPane.add(xButton,4,1,1,1);

        Button pow = new Button("pow(,");
        addButton(insert, pow);
        gridPane.add(pow,5,1,1,1);

        Button clearButton = new Button("C");
        addButton(clear, clearButton);
        gridPane.add(clearButton,6,1,1,1);

        Button four = new Button("4");
        addButton(insert, four);
        gridPane.add(four,0,2,1,1);

        Button five = new Button("5");
        addButton(insert, five);
        gridPane.add(five,1,2,1,1);

        Button six = new Button("6");
        addButton(insert, six);
        gridPane.add(six,2,2,1,1);

        Button multiply = new Button("*");
        addButton(insert, multiply);
        gridPane.add(multiply,3,2,1,1);

        Button firstBracket = new Button("(");
        addButton(insert, firstBracket);
        gridPane.add(firstBracket,4,2,1,1);

        Button sin = new Button("sin(");
        addButton(insert, sin);
        gridPane.add(sin,5,2,1,1);

        Button cos = new Button("cos(");
        addButton(insert, cos);
        gridPane.add(cos,6,2,1,1);

        Button one = new Button("1");
        addButton(insert, one);
        gridPane.add(one,0,3,1,1);

        Button two = new Button("2");
        addButton(insert, two);
        gridPane.add(two,1,3,1,1);

        Button three = new Button("3");
        addButton(insert, three);
        gridPane.add(three,2,3,1,1);

        Button minus = new Button("-");
        addButton(insert, minus);
        gridPane.add(minus,3,3,1,1);

        Button secondBracket = new Button(")");
        addButton(insert, secondBracket);
        gridPane.add(secondBracket,4,3,1,1);

        Button log = new Button("log(");
        addButton(insert, log);
        gridPane.add(log,5,3,1,1);

        Button ln = new Button("ln(");
        addButton(insert, ln);
        gridPane.add(ln,6,3,1,1);

        Button zero = new Button("0");
        addButton(insert, zero);
        gridPane.add(zero,0,4,1,1);

        Button dot = new Button(".");
        addButton(insert, dot);
        gridPane.add(dot,1,4,1,1);

        Button plus = new Button("+");
        addButton(insert, plus);
        gridPane.add(plus,2,4,1,1);

        Button drawButton = new Button("DRAW");
        drawButton.setPrefSize(95,30);
        gridPane.add(drawButton,3,4,2,1);
        drawButton.setOnAction(draw);

        Button sqrt = new Button("sqrt(");
        addButton(insert, sqrt);
        gridPane.add(sqrt,5,4,1,1);

        Button abs = new Button("abs(");
        addButton(insert, abs);
        gridPane.add(abs,6,4,1,1);

        drawXYAxis();


    }

    private void addButton(EventHandler<ActionEvent> insert,Button button) {
        button.setPrefSize(45,30);
        button.setOnAction(insert);
        button.setFocusTraversable(false);
    }


    private class InsertAction implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent actionEvent) {
            int positionCursor= result.getCaretPosition();
            String display=((Button)actionEvent.getSource()).getText();
            result.insertText(positionCursor,display);
        }
    }
    private class ClearAction implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent actionEvent) {
            result.setText("");
            chart.getData().clear();
        }
    }

    private  class DrawAction implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent actionEvent) {
            Calc calc = new Calc();
            if(!calc.checkBrackets(result.getText())){
                result.setText("Uncorrect brackets!");
            }
            else {
                try {
                    ArrayList<XYChart.Series> listOfSeries=new ArrayList<>();
                    XYChart.Series series = new XYChart.Series();
                    String changeText = calc.changeTextWithNegativeNumbers(result.getText());
                    calc.changeEnterToExit(changeText);
                    double x;
                    for (x = -25; x <= 25; x += 0.04) {
                        double y= calc.calculate(x, calc.getExitText());
                        if(Double.isNaN(y)&&!Double.isNaN(calc.calculate(x -0.04, calc.getExitText()))){
                            listOfSeries.add(series);
                            series=new XYChart.Series();
                        }
                        series.getData().add(new XYChart.Data(x, y));
                    }
                    if(!series.getData().isEmpty())listOfSeries.add(series);
                    for(XYChart.Series s:listOfSeries)chart.getData().add(s);

                } catch (EmptyStackException e) {
                    result.setText("Invalid expression!");

                }
            }
        }
    }
    private void drawXYAxis(){
        NumberAxis xAxis=new NumberAxis();
        xAxis.setAutoRanging(false);
        xAxis.setLowerBound(-25);
        xAxis.setUpperBound(25);
        xAxis.setTranslateY(-263);
        NumberAxis yAxis=new NumberAxis();
        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(-25);
        yAxis.setUpperBound(25);
        yAxis.setTranslateX(262.5);




        chart=new LineChart<>(xAxis,yAxis);
        chart.setCreateSymbols(false);
        chart.setLegendVisible(false);
        chart.setTranslateX(95);
        chart.setOnScroll(scrollEvent -> {
            scrollEvent.consume();
            if(scrollEvent.getDeltaY()==0){
                return;
            }
            double scaleFactor=(scrollEvent.getDeltaY()>0)?1.1:1/1.1;
            if(chart.getScaleX()*scaleFactor>=2){
                chart.setScaleX(2);
            }
            else chart.setScaleX(Math.max(chart.getScaleX() * scaleFactor, 0.5));

            if(chart.getScaleY()*scaleFactor>=2){
                chart.setScaleY(2);
            }
            else chart.setScaleY(Math.max(chart.getScaleY() * scaleFactor, 0.5));
        });
        chart.setOnMousePressed(mouseEvent -> {
            oldX=mouseEvent.getX();
            oldY=mouseEvent.getY();
            if (mouseEvent.getClickCount()==2){
                chart.setScaleX(1.0);
                chart.setScaleY(1.0);
                chart.setTranslateX(95);
                chart.setTranslateY(0);
            }
        });

        chart.setOnMouseDragged(mouseEvent -> {
            chart.setCursor(Cursor.CLOSED_HAND);
            double newX=mouseEvent.getX();
            double newY=mouseEvent.getY();
            translX +=(newX-oldX)*(chart.getScaleX());
            translY +=(newY-oldY)*(chart.getScaleY());
            chart.setTranslateX(95+ translX);
            chart.setTranslateY(0+translY);
            oldX=newX;
            oldY=newY;
        });
        stackPane=new StackPane(chart);
        stackPane.setMinSize(580,580);
        stackPane.setMaxSize(580,580);


    }

    public GridPane getGridPane() {
        return gridPane;
    }
    public StackPane getStackPane(){return stackPane;}

}

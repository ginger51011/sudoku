package projektet;

import java.util.Scanner;

import javax.swing.JOptionPane;

import com.sun.glass.ui.Window;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GraphicInterface extends Application{
	
	private TextField numberBoxes[][];
	private int sendBoxes[][] = new int[9][9];
	private GameBoard gb;
	private Alert alert = new Alert(AlertType.WARNING);
	
	public static void main(String[] args) {
		launch(args);
		
		
	}
	
	

	@Override
	public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Sudoku");

        numberBoxes = new TextField[9][9];
        
        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        
        Button solve = new Button("Solve");
        Button clear = new Button("Clear");
        
        TilePane tilePane = new TilePane();
        tilePane.getChildren().add(solve);
        tilePane.getChildren().add(clear);
        tilePane.setHgap(10);

   
        BorderPane border = new BorderPane();
        border.setTop(gridPane);
        border.setBottom(tilePane);
        BorderPane.setMargin(tilePane, new Insets(10,10,10,10));

        
        GridPane regions[][]=new GridPane[3][3];
        gridPane.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        
        
        

        /*Lägger till de olika regionerna i gridPane*/
        for(int i=0; i<3; i++) {
        	for(int n=0; n<3; n++) {
        		regions[i][n]=new GridPane();
        		regions[i][n].setVgap(1);
        		regions[i][n].setHgap(1);
        		gridPane.add(regions[i][n], i, n, 1,1);
        	}
        }
        
        /*Lägger till textFields (numberBoxes) i alla regions*/
        for(int i=0; i<9; i++) {
        	for(int n=0; n<9; n++) {
        		numberBoxes[i][n]=new TextField();
        		int regionRow=i/3;
        		int regionCol=n/3;
        		int regionRowMod=i%3;
        		int regionColMod=n%3;
        		regions[regionRow][regionCol].add(numberBoxes[i][n], regionRowMod, regionColMod, 1, 1);
        		if((regionRow==0&&regionCol==0)||(regionRow==0&&regionCol==2)||(regionRow==1&&regionCol==1)||(regionRow==2&&regionCol==0)||(regionRow==2&&regionCol==2)) {
        			numberBoxes[i][n].setBackground(new Background(new BackgroundFill(Color.CORAL, CornerRadii.EMPTY, Insets.EMPTY)));
        		}
        	}
        }
        
        
        Scene scene = new Scene(border, 250, 310);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
        
        
        
        solve.setOnAction(event -> {
        	
        	//Checks if users input can be converted to a int 2D array
            if(scanGrid()) {
            	
                gb = new GameBoard(sendBoxes);
                int result[][]=null;
                GameBoard gbSolve=Solver.solve(gb);
                
                try {
                	//Tries to get the solved board
                	result = gbSolve.getBoard();
                }
                catch(NullPointerException e) {
                	//If NullPointerException solution could not be found
                    alert.setTitle("Warning Dialog");
                    alert.setHeaderText("No solution could be found");
                    alert.showAndWait();
                    return;
                }

               
                    for(int i=0; i<9; i++) {
                        for(int n=0; n<9; n++) {
                            numberBoxes[i][n].setText(result[i][n]+"");
                        }
                    }
                
            }
        });
		
		clear.setOnAction(event -> {
			clearGrid();
		});
		
	}
	
	/*
	 * Private help method
	 * Clears grid feom users input
	 */
	private void clearGrid() {
	//Clears grid from users input
		for(int i=0; i<9; i++) {
        	for(int n=0; n<9; n++) {
        		numberBoxes[i][n].setText("");
        	}
        }
	}
	
	/*
	 * Private help method
	 * Checks if user input can be converted to a 2D-array of type int
	 * @return true if this is the case.
	 */
	private boolean scanGrid() {
		for(int i=0; i<9; i++) {
        	for(int n=0; n<9; n++) {
        		
        		
        		if(numberBoxes[i][n].getText()==null||numberBoxes[i][n].getText().equals("")) {
        			sendBoxes[i][n]=-1;
        		}
        		else {
            		try {

            			sendBoxes[i][n]=Integer.parseInt(numberBoxes[i][n].getText());
            			if(sendBoxes[i][n]>9||sendBoxes[i][n]<1) {
            				throw new NumberFormatException();
            			}
            		}
            		catch(NumberFormatException e) {
            			System.out.println("Exception");
            			alert.setTitle("Warning Dialog");
            			alert.setHeaderText("You can only have integers between 1 and 9 as input");

            			alert.showAndWait();
            			return false;
            		}
        		}


        	}
        }
		return true;
	}
	

}

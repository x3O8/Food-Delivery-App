package application;

import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SampleController{
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void switchtoScene1(ActionEvent e) throws IOException {
	    try {
	        Parent root = (Parent) FXMLLoader.load(getClass().getResource("Login.fxml"));
	        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
	        Scene scene = new Scene(root);
	        stage.setScene(scene);
	        stage.show();
	    } catch (IOException ex) {
	        System.out.print("Could not link the Scene1 due to IOException");
	        throw ex;
	    }
	}

	
	public void switchtoScene2(ActionEvent e) throws IOException {
		try {
			root = (Parent)FXMLLoader.load(getClass().getResource("Signup.fxml"));
			stage=(Stage)((Node)e.getSource()).getScene().getWindow();
			scene=new Scene(root);
			stage.setScene(scene);
			stage.show();
			}
		catch (IOException ex) {
	        System.out.print("Could not link the Scene2 due to IOException");
	        throw ex;
	    	}
		}
	
	public void switchtoScene3(ActionEvent e) throws IOException {
		try {
			root = (Parent)FXMLLoader.load(getClass().getResource("MainPage.fxml"));
			stage=(Stage)((Node)e.getSource()).getScene().getWindow();
			stage.setX(200);
	        stage.setY(5);
			scene=new Scene(root);
			stage.setScene(scene);
			stage.show();
			}
		catch (IOException ex) {
	        System.out.print("Could not link the Scene3 due to IOException");
	        throw ex;
	    	}
		}
	
	}

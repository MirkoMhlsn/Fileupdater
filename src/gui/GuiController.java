package gui;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
/***/	import updater.Controller;
/***/	import updater.GeneralWorker;

public class GuiController implements Initializable {
	
	@FXML
	private AnchorPane pane;
	
	@FXML
	private Button button;
	
	@FXML
	private TextField pathCOM;
	
	@FXML
	private TextField pathSSD;
	
	@FXML
	private Button triggerFolderCOM;
	
	@FXML
	private Button triggerFolderSSD;
	
	@FXML
	private Label label;
	
//	@FXML
//	private ScrollPane scrollPane;
	
	private File tryFileCOM;
	private File tryFileSSD;
	
	private boolean validCOM = false;
	private boolean validSSD = false;
	
	BooleanProperty validCOMproperty = new SimpleBooleanProperty(validCOM);
	BooleanProperty validSSDproperty = new SimpleBooleanProperty(validSSD);
	
	String showFolders = "";
	
	GeneralWorker generalWorker = new GeneralWorker();
	
	public void triggerCOM() {
		
		Stage stage = (Stage)pane.getScene().getWindow();
		
		DirectoryChooser chooseCOM = new DirectoryChooser();
		File folderCOM = chooseCOM.showDialog(stage);
		pathCOM.setText(folderCOM.getAbsolutePath());
		
	}
	
	public void triggerSSD() {

		Stage stage = (Stage)pane.getScene().getWindow();
		
		DirectoryChooser chooseSSD = new DirectoryChooser();
		File folderSSD = chooseSSD.showDialog(stage);
		pathSSD.setText(folderSSD.getAbsolutePath());
		
	}
	
	
	public void klick() {
		
		String a = pathCOM.getText();
		String b = pathSSD.getText();
		
		Controller.master(a, b);
		
		System.out.println(generalWorker.getMissingFolders());
		
		for(int z = 0; z < generalWorker.getMissingFolders().size(); z++) {
			
			showFolders = showFolders + generalWorker.getMissingFolders().get(z) + "\n";
			label.setText(showFolders);
			
		}
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		pathCOM.textProperty().addListener(e -> {
			
			tryFileCOM = new File(pathCOM.getText());
			
			if(tryFileCOM.isDirectory()) {
				
				validCOM = true;
				System.err.println(validCOM);
				validCOMproperty.set(validCOM);
				
				System.out.println("com: " + validCOMproperty.get());
				
			}else {
				
				validCOM = false;
				System.err.println(validCOM);
				validCOMproperty.set(validCOM);
				
				System.out.println("com: " + validCOMproperty.get());
				
			}
		});
		
		pathSSD.textProperty().addListener(e -> {
			
			tryFileSSD = new File(pathSSD.getText());
			
			if(tryFileSSD.isDirectory()) {
				
				validSSD = true;
				System.err.println(validSSD);
				validSSDproperty.set(validSSD);
				
				System.out.println("ssd: " + validSSDproperty.get());
				
			}else {
				
				validSSD = false;
				System.err.println(validSSD);
				validSSDproperty.set(validSSD);
				
				System.out.println("ssd: " + validSSDproperty.get());
				
			}
		});
		
		button.disableProperty().bind(Bindings.or(validCOMproperty.not(), validSSDproperty.not()));
		
		pane.heightProperty().addListener(e -> {
			
			label.setPrefHeight(pane.getHeight() - 205);
			
		});
		
		//label.setTextFill(Color.RED);
		
		
	}

}

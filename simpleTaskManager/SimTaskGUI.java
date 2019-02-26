
package simpleTaskManager;
import simpleTaskManager.*;

//standard javafx imports
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;

//components import for this application
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ComboBox;

//Imports for layout
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

//imports for file
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Uche Mbaka
 *
 */
public class SimTaskGUI extends Application implements Serializable{

	//instantiate components at class level
	Label lblCurrentTask, lblShowCurrentTask, lblProgress, lblShowDgSelectState, lblShowDgShowState, lblShowDgShowName, lblNewDgID, lblNewDgName, lblNewDgOptional, lblNewDgNote,
			lblEditDgID, lblEditDgName, lblEditDgOPtional, lblEditDgState, lblEditDgNote;
	
	ProgressBar progBar;
	
	Button btnAdd, btnShowAll, btnExit, btnShowDgEndStart, btnShowDgEdit, btnShowDgDelete, btnSHowDgDetails, btnShowDgExit, btnNewDgOk, btnNewDgCancel, btnEditDgInfo, btnEditDgSave,
	btnEditDgCancel;
	
	ListView<String> lvTasks, lvDates;

	
	TextField txtfNewDgID, txtfNewDgName, txtfEditDgID, txtfEditDgName;
	
	TextArea txtNewDgNote, txtEditDgNote;
	
	RadioButton rdoNewDgOptYes, rdoNewDgOptNo, rdoNew, rdoUnfinished, rdoFinished, rdoEditNo, rdoEditYes, rdoEditNew, rdoEditFinished, rdoEditUnfinished;
	
	ToggleGroup tgOptionalNewDg, tgState, tgEditDgOpt, tgEditDgState;
	
	ComboBox<String> cbDatesBox; 
	
	TaskList myTasks;
	
	DateList myDates;
	
	ObjectInputStream datFile, dateFile;
	
	ObjectOutputStream fileOut, dateFileOut;
	
	boolean fot;
	int ifot;
	int ilot;
	
	public SimTaskGUI() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		//initialize components and objects
		
		myTasks = TaskList.getTaskList();
		myDates = DateList.getDateList();
		
		fot = true;
		
		ifot = -1;
		ilot = -1;
		
		lvTasks = new ListView<String>();
		lvDates = new ListView<String>();
		lvDates.setMaxWidth(150);
		

		
		lblCurrentTask = new Label("Tasks for: ");
		lblShowCurrentTask = new Label();
		lblProgress = new Label("Progress: ");
		
		progBar = new ProgressBar(0);
		progBar.setMinWidth(340);
		
		
		btnAdd = new Button("Add Task");
		btnAdd.setMinWidth(100);
		btnAdd.setStyle("-fx-color: green");
		btnShowAll = new Button("Show Tasks");
		btnShowAll.setMinWidth(100);
		btnExit = new Button("Exit");
		btnExit.setMinWidth(100);
		btnExit.setStyle("-fx-color: red");
		
		
		//ShowAll task components
		btnShowDgEndStart = new Button("Start");
		btnShowDgEndStart.setMinWidth(70);
		btnShowDgEdit = new Button("Edit");
		btnShowDgEdit.setMinWidth(70);
		btnShowDgDelete = new Button("Delete");
		btnShowDgDelete.setMinWidth(70);
		btnShowDgDelete.setStyle("-fx-color: red");
		btnSHowDgDetails = new Button("Details");
		btnSHowDgDetails.setMinWidth(70);
		btnShowDgExit = new Button("Exit");
		btnShowDgExit.setMinWidth(70);
		
		lblShowDgShowState = new Label();
		lblShowDgShowState.setAlignment(Pos.BOTTOM_CENTER);
		lblShowDgShowName = new Label();
		
		//Add new taks components
		lblNewDgID = new Label("ID: ");
		lblNewDgName = new Label("Name: ");
		lblNewDgOptional = new Label("Optional: ");
		lblNewDgNote = new Label("Note: ");
		lblShowDgSelectState = new Label("Select State");
		
		txtfNewDgID = new TextField();
		txtfNewDgID.setDisable(true);
		
		txtfNewDgName = new TextField();
		
		tgOptionalNewDg = new ToggleGroup();
		
		rdoNewDgOptYes = new RadioButton("Yes");
		rdoNewDgOptYes.setToggleGroup(tgOptionalNewDg);
		rdoNewDgOptNo = new RadioButton("No");
		rdoNewDgOptNo.setToggleGroup(tgOptionalNewDg);
		rdoNewDgOptNo.setSelected(true);
		
		tgState = new ToggleGroup();
		
		rdoNew = new RadioButton("New");
		rdoNew.setToggleGroup(tgState);
		rdoNew.setSelected(true);
		rdoUnfinished = new RadioButton("Unfinished");
		rdoUnfinished.setToggleGroup(tgState);
		rdoFinished = new RadioButton("Finished");
		rdoFinished.setToggleGroup(tgState);
		
		txtNewDgNote = new TextArea();
		
		btnNewDgOk = new Button("Ok");
		btnNewDgOk.setMinWidth(70);
		btnNewDgOk.setStyle("-fx-color: green");
		
		btnNewDgCancel = new Button("Cancel");
		btnNewDgCancel.setMinWidth(70);
		btnNewDgCancel.setStyle("-fx-color: red");
		
		//edit task components
		lblEditDgID = new Label("ID: ");
		lblEditDgName = new Label("Name: ");
		lblEditDgOPtional = new Label("Optional: ");
		lblEditDgState = new Label("State: ");
		lblEditDgNote = new Label("Note: ");
		
		btnEditDgInfo = new Button("i");
		btnEditDgSave = new Button("Save");
		btnEditDgCancel = new Button("Cancel");
		
		txtfEditDgID = new TextField();
		txtfEditDgID.setDisable(true);
		txtfEditDgName = new TextField();
		
		tgEditDgOpt  = new ToggleGroup();
		tgEditDgState = new ToggleGroup();
		
		rdoEditNo = new RadioButton("No");
		rdoEditNo.setToggleGroup(tgEditDgOpt);
		rdoEditYes = new RadioButton("Yes");
		rdoEditYes.setToggleGroup(tgEditDgOpt);
		
		rdoEditNew = new RadioButton("New");
		rdoEditNew.setToggleGroup(tgEditDgState);
		rdoEditFinished = new RadioButton("Completed");
		rdoEditFinished.setToggleGroup(tgEditDgState);
		rdoEditUnfinished = new RadioButton("Unfinished");
		rdoEditUnfinished.setToggleGroup(tgEditDgState);
		
		txtEditDgNote = new TextArea();
		
		cbDatesBox = new ComboBox<String>();
		cbDatesBox.setMinWidth(300);
		
		//set event on action
		btnExit.setOnAction(ae -> {
			saveDates();
			saveTasks();
			Platform.exit();
		});
		
		btnAdd.setOnAction(ae -> showAddDialog());
		
		btnShowAll.setOnAction(ae -> {
			showAllDialog();
		});
		
		
	}//init()
	
	private void createTasksDate() {
		
		if (fot == true) {
			if (myTasks.getSize() == 0) {
				fot = false;
				ifot = 0;
				ilot = 0;
				myDates.addItem(new TasksDate(myDates.getSize(), LocalDate.now(), ifot, ilot));
				saveDates();
			}else {
				if (!(myTasks.getItem(myTasks.getSize()-1).getDateCreated().isEqual(LocalDate.now()))) {
					if (!(myTasks.getItem(myTasks.getSize()-1).isDeleted())) {
						fot = false;
						ifot = myTasks.getSize();
						myDates.getItem(myDates.getSize()-1).setEndIndex(myTasks.getSize()-1);
						ilot = myTasks.getSize();
//						System.out.println(myDates.getSize());
						int id = myDates.getSize();
						System.out.println(id);
						myDates.addItem(new TasksDate(id, LocalDate.now(), ifot, ilot));
						saveDates();
					}
					
				}
			}
		}else {
			//do nothing
		}
		

	}
	
	private void showAddDialog() {
		Stage addDgStage = new Stage();
		
		//set title
		addDgStage.setTitle("Add New Task");
		
		//set width and height
		addDgStage.setWidth(400);
		addDgStage.setHeight(400);
		
		
		//create layout
		GridPane addGp = new GridPane();
		addGp.setPadding(new Insets(10));
		//addGp.setHgap(10);
		addGp.setVgap(20);
		
	
		HBox optionalHBox = new HBox();
		optionalHBox.getChildren().addAll(rdoNewDgOptYes, rdoNewDgOptNo);
		optionalHBox.setSpacing(15);

		
		
		addGp.add(lblNewDgID, 0, 0);
		addGp.add(txtfNewDgID, 1, 0);
		
		addGp.add(lblNewDgName, 0, 1);
		addGp.add(txtfNewDgName, 1, 1);
		
		addGp.add(lblNewDgOptional, 0, 2);
		addGp.add(optionalHBox, 1, 2);
		
		addGp.add(lblShowDgSelectState, 0, 3);
		HBox stateHBox = new HBox();
		stateHBox.getChildren().addAll(rdoNew, rdoUnfinished);
		stateHBox.setSpacing(10);
		addGp.add(stateHBox, 1, 3);
		
		addGp.add(lblNewDgNote, 0, 4);
		addGp.add(txtNewDgNote, 0, 5, 2, 1);
		
		
		HBox btnHBox = new HBox();
		btnHBox.getChildren().addAll(btnNewDgOk, btnNewDgCancel);
		btnHBox.setSpacing(15);
		addGp.add(btnHBox, 0, 6);
		
		//create scene
		Scene addScene = new Scene(addGp);
		
		//set scene
		addDgStage.setScene(addScene);
		
		//show stage
		addDgStage.show();
		
		txtfNewDgID.setText(SimTask.genID(myTasks.getSize()));
		
		//events on action
		btnNewDgCancel.setOnAction(ae -> addDgStage.close());
		
		btnNewDgOk.setOnAction(ae -> {
			
			if (txtfNewDgName.getText().isEmpty()) {
				new Alert(AlertType.WARNING, "Name field cannot be empty").showAndWait();
			}else if (tgOptionalNewDg.getSelectedToggle() == rdoNewDgOptNo) {
				if (tgState.getSelectedToggle() == rdoNew) {
					createTasksDate();
					saveDates();
					myTasks.addItem(new SimTask(txtfNewDgID.getText(), txtfNewDgName.getText(), "New", null, false, txtNewDgNote.getText()));
					saveTasks();
					new Alert(AlertType.INFORMATION, "New Task Added").showAndWait();
					addDgStage.close();
				}else {
					createTasksDate();
					saveDates();
					myTasks.addItem(new SimTask(txtfNewDgID.getText(), txtfNewDgName.getText(), "Unfinished",LocalTime.now(), false, txtNewDgNote.getText()));
					saveTasks();
					new Alert(AlertType.INFORMATION, "New Task Added").showAndWait();
					addDgStage.close();
				}
				
			}else {
				if (tgState.getSelectedToggle() == rdoNew) {
					createTasksDate();
					saveDates();
					myTasks.addItem(new SimTask(txtfNewDgID.getText(), txtfNewDgName.getText(), "New", null, true, txtNewDgNote.getText()));
					saveTasks();
					new Alert(AlertType.INFORMATION, "New Task Added").showAndWait();
					
					addDgStage.close();
				}else {
					createTasksDate();
					saveDates();
					myTasks.addItem(new SimTask(txtfNewDgID.getText(), txtfNewDgName.getText(), "Unfinished", LocalTime.now(), true, txtNewDgNote.getText()));
					saveTasks();
					new Alert(AlertType.INFORMATION, "New Task Added").showAndWait();
					
					addDgStage.close();
				}
				
			}
			
			
		});
		
	}
	
	private void saveTasks() {
		try {
			fileOut = new ObjectOutputStream(new FileOutputStream("save.dat"));
			for (int i = 0; i < myTasks.getSize(); i++) {
				fileOut.writeObject(myTasks.getItem(i));
			}
			fileOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//save()
	
	private void saveDates() {
		try {
			dateFileOut = new ObjectOutputStream(new FileOutputStream("dates.dat"));
			for (int i = 0; i < myDates.getSize(); i++) {
				dateFileOut.writeObject(myDates.getItem(i));
			}
			dateFileOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void showAllDialog() {
		Stage showAllDg = new Stage();
		
		//set stage title
		showAllDg.setTitle("All Tasks");
		
		//set width and height
		showAllDg.setWidth(670);
		showAllDg.setHeight(260);
		
		//create layout
		GridPane showAllGp = new GridPane();
		showAllGp.setPadding(new Insets(10));
		showAllGp.setVgap(10);
		showAllGp.setHgap(10);

		
		//events on action
		btnShowDgExit.setOnAction(ae -> {
			updateProgress();
			showAllDg.close();
		});
		
		lvTasks.setOnMousePressed(ae -> {
			basicInfo();
		});
		
		lvTasks.setOnKeyReleased(ae -> {
			basicInfo();
		});
		
		lvDates.setOnMousePressed(ae -> {
			lvTasks.getItems().clear();
			popList();
		});
		
		lvDates.setOnKeyReleased(ae -> {
			lvTasks.getItems().clear();
			popList();
		});
		
		showAllGp.add(lvTasks, 0, 0, 1, 4);
		
		btnShowDgEndStart.setOnAction(ae ->{
			if (myTasks.getSize() > 0) {
				int index = taskIndex();
				if (myTasks.getItem(index).getState().equals("New")) {
					myTasks.getItem(index).setStartTime(LocalTime.now());
					btnShowDgEndStart.setText("End");
					btnShowDgEndStart.setDisable(false);
					myTasks.getItem(index).setState("Unfinished");
				}else if (myTasks.getItem(index).getState().equals("Unfinished")) {
					myTasks.getItem(index).setState("Completed");
					myTasks.getItem(index).setEndTime(LocalTime.now());
					btnShowDgEndStart.setText("End");
					btnShowDgEndStart.setDisable(true);
					updateProgress();
				}
			}
			
		});
		
		btnSHowDgDetails.setOnAction(ae ->{
			if (myTasks.getSize() > 0) {
				int index = taskIndex();
				new Alert(AlertType.INFORMATION,"Task ID: "+myTasks.getItem(index).getID()
						+"\nName: "+myTasks.getItem(index).getName()
						+"\nState: "+myTasks.getItem(index).getState()
						+"\nOptional: "+myTasks.getItem(index).getOptional()
						+"\nStart Time: "+myTasks.getItem(index).getStartTime()
						+"\nComplete Time: "+myTasks.getItem(index).getEndTime()
						+"\nNote:\n"+myTasks.getItem(index).getNote()).showAndWait();
			}

		});
		
		btnShowDgDelete.setOnAction(ae -> {
			if (myTasks.getSize() > 0) {
				deleteItem();
				new Alert(AlertType.INFORMATION, "Task Deleted").showAndWait();
				updateProgress();
				showAllDg.close();
				saveTasks();
			}
			
		});
		
		btnShowDgEdit.setOnAction(ae -> {
			editTask();
			updateProgress();
			showAllDg.close();
		});

		showAllGp.add(lblShowDgShowName, 1, 0);
		
		HBox stateHBox = new HBox();
		stateHBox.getChildren().addAll(lblShowDgShowState, btnShowDgEndStart);
		stateHBox.setSpacing(10);
		showAllGp.add(stateHBox, 1, 1, 2, 1);
		
		VBox btnVBox = new VBox();
		btnVBox.getChildren().addAll(btnShowDgEdit, btnShowDgDelete, btnSHowDgDetails, btnShowDgExit);
		btnVBox.setSpacing(10);
		btnVBox.setAlignment(Pos.CENTER);
		showAllGp.add(btnVBox, 1, 2, 2, 1);
		//showAllGp.add(, 1, 3);
		
		showAllGp.add(lvDates, 3, 0, 1, 4);
		
		 lvDates.getItems().clear();
		 popDateList();
		 
		//populate listview
		lvTasks.getItems().clear();
		 popList();
		 
		 //show basic info preselected item
		 basicInfo();
		 
		
		
		//create scene
		Scene showDgScene = new Scene(showAllGp);
		
		//set scene
		showAllDg.setScene(showDgScene);
		
		//show stage
		showAllDg.show();
	}//showAlldialog()
	
	private void basicInfo() {//show basic info of selected task
		if (myTasks.getSize() != 0) {
			int index = taskIndex();
			lblShowDgShowName.setText("Name: "+myTasks.getItem(index).getName());
			lblShowDgShowState.setText("Current State: "+myTasks.getItem(index).getState());
			//if block to set button text and default on showAll clicked
			if (myTasks.getItem(index).getState().equals("New")) {
				btnShowDgEndStart.setText("New");
				btnShowDgEndStart.setDisable(false);
			}else if (myTasks.getItem(index).getState().equals("Unfinished")) {
				btnShowDgEndStart.setText("End");
				btnShowDgEndStart.setDisable(false);
			}else {
				btnShowDgEndStart.setText("End");
				btnShowDgEndStart.setDisable(true);
			}
		}

	}
	
	private void deleteItem() {
		int index = taskIndex();
		myTasks.deleteItem(index);
		saveTasks();
		
	}//dleteTask()
	
	private void editTask() {
		
		if (myTasks.getSize() > 0) {
			int index = taskIndex();
			
			
			Stage editDgStage = new Stage();
			
			//set title
			editDgStage.setTitle("Edit Selected Task");
			
			//set width and height
			editDgStage.setWidth(400);
			editDgStage.setHeight(400);
			
			
			//create layout
			GridPane editGp = new GridPane();
			editGp.setPadding(new Insets(10));
			//addGp.setHgap(10);
			editGp.setVgap(20);
			
		
			HBox optionalHBox = new HBox();
			optionalHBox.getChildren().addAll(rdoEditYes, rdoEditNo, btnEditDgInfo);
			optionalHBox.setSpacing(15);

			
			
			editGp.add(lblEditDgID, 0, 0);
			editGp.add(txtfEditDgID, 1, 0);
			txtfEditDgID.setText(myTasks.getItem(index).getID());
			
			editGp.add(lblEditDgName, 0, 1);
			editGp.add(txtfEditDgName, 1, 1);
			txtfEditDgName.setText(myTasks.getItem(index).getName());
			
			editGp.add(lblEditDgOPtional, 0, 2);
			editGp.add(optionalHBox, 1, 2);
			if (myTasks.getItem(index).getOptional() == true) {
				rdoEditYes.setSelected(true);
			}else {
				rdoEditNo.setSelected(true);
			}
			
			editGp.add(lblEditDgState, 0, 3);
			HBox stateHBox = new HBox();
			stateHBox.getChildren().addAll(rdoEditNew, rdoEditUnfinished, rdoEditFinished);
			stateHBox.setSpacing(10);
			editGp.add(stateHBox, 1, 3);
			
			if (myTasks.getItem(index).getState().matches("New")) {
				rdoEditNew.setSelected(true);
			}else if (myTasks.getItem(index).getState().matches("Unfinished")) {
				rdoEditUnfinished.setSelected(true);
			}else {
				rdoEditFinished.setSelected(true);
			}
			
			editGp.add(lblEditDgNote, 0, 4);
			editGp.add(txtEditDgNote, 0, 5, 2, 1);
			txtEditDgNote.setText(myTasks.getItem(index).getNote());
			
			
			HBox btnHBox = new HBox();
			btnHBox.getChildren().addAll(btnEditDgSave, btnEditDgCancel);
			btnHBox.setSpacing(15);
			editGp.add(btnHBox, 0, 6);
			
			//events on action
			btnEditDgCancel.setOnAction(ae -> editDgStage.close());
			
			btnEditDgInfo.setOnAction(ae ->{
				new Alert(AlertType.INFORMATION, "Start and/or Finish Time may be set to \"null\" depending on state selected").showAndWait();
			});
			btnEditDgSave.setOnAction(ae->{
				myTasks.getItem(index).setName(txtfEditDgName.getText());
				
				//Optional 
				if (tgEditDgOpt.getSelectedToggle() == rdoEditYes) {
					myTasks.getItem(index).setOptional(true);
				}else {
					myTasks.getItem(index).setOptional(false);
				}
				
				//note
				myTasks.getItem(index).setNote(txtEditDgNote.getText());
				
				//state
				if (tgEditDgState.getSelectedToggle() == rdoEditNew) {
					myTasks.getItem(index).setState("New");
					btnShowDgEndStart.setDisable(false);
					myTasks.getItem(index).setStartTime(null);
					myTasks.getItem(index).setEndTime(null);
				}else if (tgEditDgState.getSelectedToggle() == rdoEditUnfinished) {
					myTasks.getItem(index).setState("Unfinished");
					btnShowDgEndStart.setDisable(false);
					myTasks.getItem(index).setEndTime(null);
				}else {
					new Alert(AlertType.ERROR, "Setting task state to complete from edit not allowed").showAndWait();
					editDgStage.close();

				}				
				//save edit
				saveTasks();
				
				editDgStage.close();
				
			});
			
			//create scene
			Scene editScene = new Scene(editGp);
			
			//set scene
			editDgStage.setScene(editScene);
			
			//show stage
			editDgStage.show();
		}
	}//editTask()
	
	@Override
	public void start(Stage pStage) throws Exception {
		//set stage title
		pStage.setTitle("Simple Task Manager V0.0.1");
		
		//set width and height
		pStage.setWidth(450);
		pStage.setHeight(180);
		
		//create layout
		GridPane gp = new GridPane();
		gp.setPadding(new Insets(10));
		gp.setVgap(20);
		gp.setHgap(20);
		
		//add components to layout
		HBox lblCurrentHBox = new HBox();
		lblCurrentHBox.getChildren().addAll(lblCurrentTask, cbDatesBox);
		lblCurrentHBox.setSpacing(15);
		gp.add(lblCurrentHBox, 0, 0, 2, 1);
		
		HBox progressHBox = new HBox();
		progressHBox.getChildren().addAll(lblProgress, progBar);
		progressHBox.setSpacing(15);
		gp.add(progressHBox, 0, 1, 2, 1);
		
		
		HBox btnHBox = new HBox();
		btnHBox.getChildren().addAll(btnAdd, btnShowAll);
		btnHBox.setSpacing(10);
		btnHBox.setAlignment(Pos.CENTER_RIGHT);
		gp.add(btnHBox, 1, 2);
		gp.add(btnExit, 0, 2);
		
		//read in .dat file
		 datFileIn();
		 dateFileIn();
		 updateProgress();
	
		 popDateList();

		
		 
		 
		//create scene
		Scene s = new Scene(gp);
		
		//set scene
		pStage.setScene(s);
		
		//show stage
		pStage.show();
	}
	private void updateProgress() {
		if (myTasks.getSize() > 0) {
			int tasksNos = 0;
			int completed = 0;
			int start = myDates.getItem(myDates.getSize()-1).getStartIndex();
			for (int i = start; i < myTasks.getSize(); i++) {
				if (!(myTasks.getItem(i)).isDeleted()) {
					tasksNos++;
					if (myTasks.getItem(i).getState().equals("Completed")) {
						completed++;
					}
				}
				
			}
			
			double incBy = 1/(double)tasksNos;//increase by

			double curProg = incBy*completed; //current progress
		
			progBar.setProgress(curProg);
			
			if (curProg <= 0.40) {
				progBar.setStyle("-fx-accent: red;");
			}else if (curProg > 0.40 && curProg <= 0.70) {
				progBar.setStyle("-fx-accent: yellow;");
			}else {
				progBar.setStyle("-fx-accent: green;");
			}
		}

		
	}//UpdateProgress();
	
	@Override
	public void stop() {
		
	}//stop()
	
	private int taskIndex() {//return the index of selected task
		if (lvTasks.getSelectionModel().getSelectedIndex() >= 0) {
			String[] firstSplit = lvTasks.getSelectionModel().getSelectedItem().split(" : ");
			String[] splitID = firstSplit[0].split("-");
			int index = Integer.valueOf(splitID[1])-1;
			return index;
		}else {
			return -1;
		}
		
	}
	
	private int dateIndex() {
		if (lvDates.getSelectionModel().getSelectedIndex() >= 0) {
			String[] token = lvDates.getSelectionModel().getSelectedItem().split(" . ");
			int index = Integer.valueOf(token[0]);
			return index;
		}else {
			return -1;
		}
	}
	
	
	//datFileIn load SimTask object to tasklist
	private void datFileIn() {
		
		try {
			datFile = new ObjectInputStream(new FileInputStream("save.dat"));
			SimTask t = (SimTask) datFile.readObject();
			myTasks.addItem(t);
			
			while(t != null) {
				t = (SimTask) datFile.readObject();
				myTasks.addItem(t);
			}//while
			
			datFile.close();
			
			
			
		}catch(IOException e) {
			if (e instanceof EOFException) {
				return;
			}
			e.printStackTrace();
		}catch(ClassNotFoundException c) {
			c.printStackTrace();
		}//catch
	}//datFileIn()
	
	private void dateFileIn() {
		try {
			dateFile = new ObjectInputStream(new FileInputStream("dates.dat"));
			TasksDate d = (TasksDate) dateFile.readObject();
			myDates.addItem(d);
			
			while(d != null) {
				d = (TasksDate) dateFile.readObject();
				myDates.addItem(d);
			}//while
			
			dateFile.close();
		}catch(IOException e) {
			if (e instanceof EOFException) {
				return;
			}
			e.printStackTrace();
		}catch(ClassNotFoundException c) {
			c.printStackTrace();
		}//catch
	}
	
	private void popList() {
		if (myDates.getSize() > 0) {
			int index = dateIndex();
			if(((myDates.getItem(myDates.getSize()-1).getDate() != LocalDate.now()) 
					&& index == myDates.getSize()-1) 
					|| (LocalDate.now().equals(myDates.getItem(index).getDate()) )){
				int start = myDates.getItem(index).getStartIndex();
				//int end = myDates.getItem(index).getEndIndex();
				for (int i = start; i < myTasks.getSize(); i++) {
					if (!(myTasks.getItem(i).isDeleted())) {
						lvTasks.getItems().add(myTasks.getItem(i).getID()+" : "+myTasks.getItem(i).getName());
					}
					
				}
				lvTasks.getSelectionModel().selectFirst();
			
			}

			else {
				int start = myDates.getItem(index).getStartIndex();
				int end = myDates.getItem(index).getEndIndex();
				for (int i = start; i <= end; i++) {
					if (!(myTasks.getItem(i).isDeleted())) {
						lvTasks.getItems().add(myTasks.getItem(i).getID()+" : "+myTasks.getItem(i).getName());
					}
					
				}
				lvTasks.getSelectionModel().selectFirst();
			}
		}


	}
	
	private void popDateList() {
	
		for (int i = myDates.getSize()-1; i>=0 ; i--) {
			
			if (myDates.getItem(i).getDate().equals(LocalDate.now())) {
				lvDates.getItems().add(myDates.getItem(i).getIndex()+" . Today");
				cbDatesBox.getItems().add(myDates.getItem(i).getIndex()+" . Today");
			}else if (ChronoUnit.DAYS.between(myDates.getItem(i).getDate(), LocalDate.now()) == 1) {
				lvDates.getItems().add(myDates.getItem(i).getIndex()+" . Yesterday");
				cbDatesBox.getItems().add(myDates.getItem(i).getIndex()+" . Yesterday");
			}else {
				lvDates.getItems().add(myDates.getItem(i).getIndex()+" . "+myDates.getItem(i).getDate());
				cbDatesBox.getItems().add(myDates.getItem(i).getIndex()+" . "+myDates.getItem(i).getDate());
			}
		}
		lvDates.getSelectionModel().selectFirst();
		cbDatesBox.getSelectionModel().selectFirst();

	}

	
	public static void main(String[] args) {
		
		// launch application
		launch();

	}//main()

}//class

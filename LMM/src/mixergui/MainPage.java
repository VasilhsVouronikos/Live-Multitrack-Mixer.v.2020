package mixergui;

import java.awt.Desktop;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import gui_helper.MainPageHelper;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.Mnemonic;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainPage extends Application{
	private BorderPane pane;
	private ArrayList<Channel>data;
	private String style="-fx-control-inner-background: #000000; -fx-font-family: Consolas; "
			+ "-fx-highlight-fill: #00FF33; -fx-highlight-text-fill:#000000;"
			+ "-fx-text-fill: #00FF33;";
	private Button Createchannel;
	private Channel cha;
	private Label defaultch;
	private Label mix_name;
	private boolean maxset=false;
	private TextArea inputch;
	private int trackcount;
	private MenuItem save;
	private int Channelnum=0;
	private MenuItem openmix;
	private String channelTitle;
	private MenuItem export;
	private TreeView<VBox> tvright;
	private Text txth;
	private MenuItem exit;
	private MenuItem numch8;
	private MenuItem numch16;
	private MenuItem numch32;
	private TabPane tabPaneRight;
	private int count;
	private int maxch=0;
	private int defaultmax=0;
	private int createcount=0;
	private int defaultcount=0;
	private HBox mixer;
	private Tab tab2;
	private MainPageHelper manager;
	private TreeItem<VBox> tright;
	private ArrayList<String>labels=new ArrayList<String>();
	private TextArea info;
	private String tracks="";
	private String onplay="0";
	private String label="";
	private Tab tab1;
	private TreeView<VBox> tvleft;
	private TabPane tabPaneleft;
	private TreeItem<VBox> tleft;
	private MenuItem op16;
	private MenuItem op32;
	private boolean maxopen=false;
	private int track;
	private MenuItem op22;
	private MenuItem openyoutube;
	private KeyCombination yout;
	private KeyCombination ex;
	private Mnemonic myout;
	private Mnemonic mex;
	private KeyCodeCombination open16;
	private KeyCodeCombination open22;
	private KeyCodeCombination open32;
	private MenuItem about;
	private MenuItem help;
	public static void main(String[] args) {
		launch(args);
	}

	//**********************************
	 //**********************************
	 //Initialize scene(window)
	 //**********************************
	 //**********************************
	
	public void start(final Stage primaryStage) throws Exception {
		primaryStage.setTitle("Live_Multitrack_Mixer");

		
		primaryStage.getIcons().add(new Image(MainPage.class.getResourceAsStream("/Sound-Mixer-icon.png")));
		Scene scene=new Scene(createLayout(),1400,700);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		manager=new MainPageHelper();
		primaryStage.show();
		data=new ArrayList<Channel>();
		//manager.setWorkspace();
			
		
		
		//**********************************
		 //**********************************
		 //Set button actions
		 //**********************************
		 //**********************************
		 
		
		primaryStage.setOnCloseRequest(e->{
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Live_Multitrack_Mixer");
			alert.setHeaderText("Warning");
			alert.setContentText("Are you sure you want to exit?");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
			   primaryStage.close();
			}
			else {
				 e.consume();
			}
			
		});
		
		scene.addEventFilter(KeyEvent.KEY_PRESSED, e->{
			if(yout.match(e)) {
				openyoutube.fire();
			}
			else if(ex.match(e)) {
				exit.fire();
			}
			else{
					e.consume();
			}
			
		});
		
		
		
		exit.setOnAction(e->{
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Live_Multitrack_Mixer");
			alert.setHeaderText("Warning");
			alert.setContentText("Are you sure you want to exit?");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
			   primaryStage.close();
			}
		});
		
		openyoutube.setOnAction(e->{
			try {
				  Desktop desktop = Desktop.getDesktop();
				  URI oURL = new URI("https://www.youtube.com");
				  desktop.browse(oURL);
				} catch (Exception e1) {
				  e1.printStackTrace();
				}
		});
		
		 /*save.setOnAction(e->{
			 String title=manager.InitName();
			 DirectoryChooser filech=new DirectoryChooser();
				filech.setTitle("save");
				File file=filech.showDialog(primaryStage);
				if(file!=null){
					String path=file.getAbsolutePath();
					if(data.size()==0 ){
						Alert a=new Alert(AlertType.ERROR);
						a.setTitle("Master_Audio_Mixer");
						a.setContentText("Cannot save mix data\nProblem found: there is no active channels to save.\n"
								+ "Solution: create a new channel and load a new sound file.");
						a.showAndWait();
					}
					else if(cha.getload()==0){
						Alert a=new Alert(AlertType.ERROR);
						a.setTitle("Master_Audio_Mixer");
						a.setContentText("no loaded tracks");
						a.showAndWait();
					}
					else{
						manager.saveFile(data, path, title);
					}
				}
			
		});
		*/
		 op32.setOnAction(e->{
			 op32.setDisable(true);
			 op22.setDisable(true);
			 op16.setDisable(true);
			 maxopen=true;
			 numch16.setDisable(true);
			 numch32.setDisable(true);
			 numch8.setDisable(true);
			 for(int i=0; i<32; i++){
				 Channelnum++;
				 cha=new Channel();
				 String num=String.valueOf(i+1);
				 String title="Channel:";
				 Stage s=new Stage();
				 try {
					cha.start(s,1,title);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				 data.add(cha);
				 tright.getChildren().add(setToolBar(cha,num,
						 cha.getDisplay(num,Channelnum),cha.getButtonLoad(s,num,null,0),
						 cha.getButtonPlay(),cha.getButtonStop(),cha.getButtonMute()));
				 
				 //tleft.getChildren().add(setControlBar(cha.getPanning()));
				 mixer.getChildren().addAll(setMixer(cha.getSlider(),cha.getPanning(),title,cha.getMixerDis(title+Channelnum)));
			 }
			
			 //op16.setDisable(true);
			 defaultch.setText(" \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+"Channel max number:"+" "+String.valueOf(32));
			 trackcount=32;
			 tracks=String.valueOf(trackcount);
			 info.setText("\tMaster_Audio_Mixer console\n\n"+
						"Tracks: "+tracks+"\n\n"+"Preset: "+"On");
			 
			 
			 
		 });
		 op16.setOnAction(e->{
			 op32.setDisable(true);
			 op16.setDisable(true);
			 op22.setDisable(true);
			 defaultcount=5;
			 maxch=-1;
			 maxopen=true;
			 numch16.setDisable(true);
			 numch32.setDisable(true);
			 numch8.setDisable(true);
			 createcount=0;
			 for(int i=0; i<16; i++){
				 Channelnum++;
				 cha=new Channel();
				 String num=String.valueOf(i+1);
				 String title="Channel:";
				 Stage s=new Stage();
				 try {
					cha.start(s,1,title);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				 data.add(cha);
				 tright.getChildren().add(setToolBar(cha,num,
						 cha.getDisplay(num,Channelnum),cha.getButtonLoad(s,num,null,0),
						 cha.getButtonPlay(),cha.getButtonStop(),cha.getButtonMute()));
				 
				 //tleft.getChildren().add(setControlBar(cha.getPanning()));
				 mixer.getChildren().addAll(setMixer(cha.getSlider(),cha.getPanning(),title,cha.getMixerDis(title+Channelnum)));
			 }
			
			 //op16.setDisable(true);
			 defaultch.setText(" \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+"Channel max number:"+" "+String.valueOf(32));
			 trackcount=16;
			 tracks=String.valueOf(trackcount);
			 info.setText("\tMaster_Audio_Mixer console\n\n"+
						"Tracks: "+tracks+"\n\n"+"Preset: "+"On");
			 
			 
			 
		 });
		 op22.setOnAction(e->{
			 op32.setDisable(true);
			 op16.setDisable(true);
			 op22.setDisable(true);
			 defaultcount=5;
			 maxch=-1;
			 maxopen=true;
			 numch16.setDisable(true);
			 numch32.setDisable(true);
			 numch8.setDisable(true);
			 createcount=0;
			 for(int i=0; i<22; i++){
				 Channelnum++;
				 cha=new Channel();
				 String num=String.valueOf(i+1);
				 String title="Channel:";
				 Stage s=new Stage();
				 try {
					cha.start(s,1,title);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				 data.add(cha);
				 tright.getChildren().add(setToolBar(cha,num,
						 cha.getDisplay(num,Channelnum),cha.getButtonLoad(s,num,null,0),
						 cha.getButtonPlay(),cha.getButtonStop(),cha.getButtonMute()));
				 
				 //tleft.getChildren().add(setControlBar(cha.getPanning()));
				 mixer.getChildren().addAll(setMixer(cha.getSlider(),cha.getPanning(),title,cha.getMixerDis(title+Channelnum)));
			 }
			
			 //op16.setDisable(true);
			 defaultch.setText(" \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+"Channel max number:"+" "+String.valueOf(32));
			 trackcount=22;
			 tracks=String.valueOf(trackcount);
			 info.setText("\tMaster_Audio_Mixer console\n\n"+
						"Tracks: "+tracks+"\n\n"+"Preset: "+"On");
			 
			 
			 
		 });
		 
		 
		 
		/* numch8.setOnAction(e->{
			 maxch=9;
			 maxset=true;
			 createcount=0;
			 numch16.setDisable(true);
			 numch32.setDisable(true);
			 op32.setDisable(true);
			 //op16.setDisable(true);
			 numch8.setDisable(true);
			 defaultch.setText("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+"Channel max number:"+" "+String.valueOf(maxch-1+track));
		});
		 numch16.setOnAction(e->{
			 maxch=17;
			 maxset=true;
			 createcount=0;
			 numch8.setDisable(true);
			 numch32.setDisable(true);
			 op32.setDisable(true);
			 numch16.setDisable(true);
			 
			 //op16.setDisable(true);
			 
			
			 defaultch.setText("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+"Channel max number:"+" "+String.valueOf(maxch-1+track));
		});
		 numch32.setOnAction(e->{
			 maxch=33;
			 maxset=true;
			 createcount=0;
			 numch8.setDisable(true);
			 numch16.setDisable(true);
			 numch32.setDisable(true);
			 //op16.setDisable(true);
			 op32.setDisable(true);
			 defaultch.setText("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+
					 "Channel max number:"+" "+String.valueOf(maxch-1+track));
		});
		*/
		
		/* Createchannel.setOnAction(e0->{
			 Stage stage=new Stage();
			 try {
				 if(maxset==true){
					 createcount++;
					 tracks=String.valueOf(trackcount);
					 info.setText("\tMaster_Audio_Mixer console\n\n"+
								"Tracks: "+tracks+"\n"+"\n\n"+"Preset:"+" Off");
				 }
				 
				 channelTitle=inputch.getText();
				 defaultcount++;
				 trackcount++;
				 
				
				 
				 if(createcount<maxch || defaultcount<=defaultmax){
					 if(channelTitle.equals("")){
						 Alert a=new Alert(AlertType.ERROR);
						 a.setTitle("Master_Audio_Mixer");
						 a.setContentText("Empty channel name");
						 a.showAndWait();
						 defaultcount--;
						 trackcount--;
						 createcount--;
						 //inputch.setText(null);
					}
					 else{
						 labels.add(channelTitle);
						 if(exist(channelTitle)){
								Alert a=new Alert(AlertType.ERROR);
								a.setTitle("Master_Audio_Mixer");
								a.setContentText("Channel name already exists");
								a.showAndWait();
								defaultcount--;
								trackcount--;
								createcount--;
								inputch.setText("");
							}
						 else{
							 count++;
							 Channelnum++;
							 if(count<=1){
								Alert a=new Alert(AlertType.INFORMATION);
								a.setTitle("Tip");
								a.setContentText("If you want to load a track while another is playing\n"
											+ "stop the first one,so as not cover eachother ");
								a.showAndWait();
								
							 }
							 cha=new Channel();
							 cha.start(stage,1,channelTitle);
							 data.add(cha);
							 tright.getChildren().add(setToolBar(cha,null,channelTitle,
									 cha.getDisplay(channelTitle,Channelnum),cha.getButtonLoad(stage,channelTitle,null,null),
									 cha.getButtonPlay(),cha.getButtonStop(),cha.getButtonMute()));
							 
							 //tleft.getChildren().add(setControlBar(cha.getPanning()));
							 mixer.getChildren().addAll(setMixer(cha.getSlider(),cha.getPanning(),"",cha.getMixerDis("channel:"+Channelnum)));
							 inputch.setText("");
							 
							 
							 tracks=String.valueOf(trackcount);
							 label=label+" "+channelTitle+"\n";
							 
							 info.setText("\tMaster_Audio_Mixer console\n\n"+
										"Tracks: "+tracks+"\n\n"+"Preset:"+" Off");
						}
					}
				 }
				 else{
					 	Alert a=new Alert(AlertType.ERROR);
					 	a.setTitle("Master_Audio_Mixer");
						a.setContentText("Max channel number is riched");
						a.showAndWait();
						inputch.setText("");
						trackcount--;
				 }
			}
			catch (Exception e00) {
				// TODO Auto-generated catch block
				e00.printStackTrace();
			 }
			 
		 });
		 */
		 /*openmix.setOnAction(e->{
			ArrayList<String[]> mixes= manager.openMix(primaryStage);
			mix_name.setText("\t\t\t\t\t\t\t\t\t"+"Mix opened: "+manager.getMixName());
			for(int i=0; i<mixes.size(); i++){
				String title="Channel:";
				Channelnum++;
				String num=String.valueOf(i+1);
				String[] mixLine=mixes.get(i);
				cha=new Channel();
				Stage stg=new Stage();
				try {
					cha.start(stg, 0, title);
					data.add(cha);
					tright.getChildren().add(setToolBar(cha,num,
							cha.getDisplay(num,Channelnum),cha.getButtonLoad(stg,num,mixLine[0],mixLine.length),
							cha.getButtonPlay(),cha.getButtonStop(),cha.getButtonMute()));
					 mixer.getChildren().addAll(setMixer(cha.getSlider(),cha.getPanning(),"",cha.getMixerDis("channel:"+Channelnum)));
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
			info.setText("\tMaster_Audio_Mixer console\n\n"+
					"Tracks: "+tracks+"\n\n"+"Preset: "+"Off");
		});
		*/
		
	}
	public BorderPane createLayout(){
		//**********************************
		 //**********************************
		 //Mixer window layout configuration
		 //**********************************
		 //**********************************
		//Createchannel=new Button("Create\nchannel");
		pane=new BorderPane();
		mixer=new HBox();
		ScrollPane p=new ScrollPane(mixer);
		mixer.prefHeight(200);
		info=new TextArea();
		
		
		
		info.setEditable(false);
		info.setPrefSize(300, 100);
		
		String styledis="-fx-control-inner-background: #000000; -fx-font-family: Consolas; "
				+ "-fx-highlight-fill: #33FF00; -fx-highlight-text-fill:#000000;"
				+ "-fx-text-fill: #33FF00; -fx-border-color: black;";
		
		
		info.setStyle(styledis);
		txth=new Text();
		txth.setText("\tLive_Multitrack_Mixer console\n\n"+
		"Tracks:"+tracks+"\n\n"+"Preset:"+" Off");
		txth.setStyle("-fx-font-size: 18px");
		info.setText(txth.getText());
		
		
		VBox b=new VBox();
		VBox b1=new VBox();
		mix_name=new Label();
		Label l=new Label("Channel name:");
		defaultch=new Label();
		defaultch.setText("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"
				+ "Channel max number:"+" "+String.valueOf(defaultmax)+" "+"(default)");
		//mix_name.setText("\t\t\t"+"Mix opened: (None)");
		
		
		
		HBox rs=new HBox(defaultch);
		//HBox mix=new HBox(mix_name);
		//HBox rcreateb=new HBox(Createchannel);
		
		
		rs.setAlignment(Pos.BOTTOM_RIGHT);
		//rcreateb.setAlignment(Pos.BOTTOM_RIGHT);
		//mix.setAlignment(Pos.BOTTOM_RIGHT);
		
		
		l.setStyle(style);
		defaultch.setStyle(style);
		//mix_name.setStyle(style);
		
		
		inputch=new TextArea();
		inputch.setPrefSize(120, 8);
		inputch.setStyle(style);
		
		
		//b.getChildren().addAll(l,inputch);
		b1.getChildren().addAll(rs);
		b1.setAlignment(Pos.BOTTOM_RIGHT);
		
		
		BackgroundSize bsize=new  BackgroundSize(BackgroundSize.AUTO,BackgroundSize.AUTO,true,true,true,true);
		Image img=new Image("/metallictoolbar2.png",1920,1080,false,true,true);
		BackgroundImage br=new BackgroundImage(img, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, bsize);
		
		BackgroundSize bsizet=new  BackgroundSize(BackgroundSize.AUTO,BackgroundSize.AUTO,true,true,true,true);
		Image imgt=new Image("/metallictoolbar2.png",1920,1080,false,true,true);
		BackgroundImage brt=new BackgroundImage(imgt, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, bsizet);
		
		
		
		pane.setBackground(new Background(br));
		openmix=new MenuItem("Open mix");
		save=new MenuItem("Save");
		//export=new MenuItem("Export");
		exit=new MenuItem("Exit                        Ctrl+E");
		openyoutube=new MenuItem("Search youtube    Cntrl+Y");
		numch8=new MenuItem("8");
		numch16=new MenuItem("16");
		numch32=new MenuItem("32");
		//op16=new MenuItem("open 16 channels");
		op32=new MenuItem("open 32 channels");
		op16=new MenuItem("open 16 channels");
		op22=new MenuItem("open 22 channels");
		help=new MenuItem("open 22 channels");
		about=new MenuItem("open 22 channels");
		
		
		yout = new KeyCodeCombination(KeyCode.Y, KeyCombination.CONTROL_DOWN);
		ex = new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN);
		open16 = new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN);
		open22 = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
		open32 = new KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_DOWN);
		
		
		
		
		
		MenuBar menuBar = new MenuBar();
	    //Menu fileMenu = new Menu("File");
	   // Menu chMenu=new Menu("Max channel number");
	    Menu presets=new Menu("Presets");
	    
	    //fileMenu.getItems().addAll(
	       //exit
	       //);
	    
	    ToolBar channels=new ToolBar(b,b1);
	    channels.setBackground(new Background(brt));
	    channels.setStyle("-fx-border-color:black;");
	    //channels.setCenterShape(true);
	    
	    
	   
	    Menu helpMenu = new Menu("Help");
	    Menu channelMenu = new Menu("Mixer options");
	    
	    
	    channelMenu.getItems().addAll(presets,openyoutube,exit);
	    //chMenu.getItems().addAll(numch8,numch16,numch32);
	    presets.getItems().addAll(op16,op22,op32);
	    helpMenu.getItems().addAll(help,
	     about);
	    menuBar.getMenus().addAll(channelMenu);
	    
	    
	    VBox vbox = new VBox();
	    vbox.getChildren().addAll(menuBar);
	    
	    
	     tright=new TreeItem<>();
	     tvright = new TreeView<VBox>(tright);
	     tleft=new TreeItem<>();
	     tvleft = new TreeView<VBox>(tleft);
	     
	     tabPaneRight = new TabPane();
	     tabPaneRight.setPrefWidth(400);
	     
	     tabPaneleft = new TabPane();
	     tabPaneleft.setPrefWidth(150);
	     
	     
	     tab2 = new Tab("Channels");
	     tab2.setClosable(false);
	     tab2.setContent(tvright);
	     tab1 = new Tab("Controls");
	     tab1.setClosable(false);
	     tab1.setContent(tvleft);
	     
	     tabPaneRight.getTabs().addAll(tab2);
	     tabPaneleft.getTabs().addAll(tab1);
	     
	     tvright.setStyle("-fx-border-color: black;");
	     tvleft.setStyle("-fx-border-color: black;");
	    
	     VBox dis=new VBox(info);
	     VBox btm=new VBox();
	     VBox botm=new VBox(p,channels);
	     btm.getChildren().addAll(botm);
	     btm.setStyle("-fx-background-color:white;");
	     pane.setTop(vbox);
	     pane.setLeft(dis);
	     pane.setBottom(btm);
	     pane.setRight(tabPaneRight);
	    
	    
	    
	    return pane;
	}
	
	 //Right tab method for seting up channel activeness
	 //**********************************
	 //**********************************
	public boolean exist(String title){
		if((Collections.frequency(labels, title))>=2){
			return true;
		}
		return false;
	}
	public ScrollPane setMixer(Slider sl,Slider pan,String title,TextArea t){
		ToolBar m=new ToolBar();
		//VBox m=new HBox();
		m.setPrefHeight(100);
		//VBox v=new VBox(t);
		Label lpan=new Label("            pan");
		VBox s=new VBox(t,lpan,pan,sl);
		m.getItems().addAll(s);
		ScrollPane sp=new ScrollPane(s);
		sp.setFitToWidth(true);
		//m.getChildren().addAll(s);
		return sp;
	}
	public TreeItem<VBox> setToolBar(Channel act,String text,TextArea ta,Button btnl,Button btnpl,Button btns,Button btnm){
		VBox b = new VBox();
		BackgroundSize bsize=new  BackgroundSize(BackgroundSize.AUTO,BackgroundSize.AUTO,true,true,true,true);
		Image img=new Image("/mettalic3.png",1920,1080,false,true,true);
		BackgroundImage br=new BackgroundImage(img, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, bsize);
		
		
		
		ToolBar t=new ToolBar();
		t.getItems().addAll(ta,btnl,btnpl,btns,btnm);
		t.setBackground(new Background(br));
		t.setStyle( "-fx-border-color: #000000;");
		t.setPrefWidth(250);
		t.setPrefHeight(110);
		b.getChildren().addAll(t);
		
		
		return new TreeItem<VBox>(b);
	}
	public TreeItem<VBox> setControlBar(Slider sl,Slider pan){
		
		String styledis="-fx-control-inner-background: #000000; -fx-font-family: Consolas; "
				+ "-fx-highlight-fill: #33FF00; -fx-highlight-text-fill:#000000;"
				+ "-fx-text-fill: #33FF00; ";
		
		VBox tb=new VBox();
		VBox panc=new VBox();
		
		Label pc=new Label();
		
		
		pc.setStyle(styledis);
		
		
		
		panc.getChildren().addAll(pc);
		
		HBox cntrl=new HBox(panc);
		//info.setAlignment(Pos.CENTER_RIGHT);
		
		
		BackgroundSize bsize=new  BackgroundSize(BackgroundSize.AUTO,BackgroundSize.AUTO,true,true,true,true);
		Image img=new Image("/mettalic3.png",1920,1080,false,true,true);
		BackgroundImage br=new BackgroundImage(img, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, bsize);
		
		ToolBar t=new ToolBar();
		VBox slider=new VBox(sl);
		VBox box=new VBox(info,slider,cntrl);
		
		//slider.setAlignment(Pos.BOTTOM_CENTER);
		t.getItems().addAll(box);
		t.setBackground(new Background(br));
		t.setStyle( "-fx-border-color: #000000;  -fx-background-color:black;");
		t.setPrefWidth(400);
		t.setPrefHeight(110);
		tb.getChildren().addAll(t);
		
		return new TreeItem<VBox>(tb);
	}
	
}

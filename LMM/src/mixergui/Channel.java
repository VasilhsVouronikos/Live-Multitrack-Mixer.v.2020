package mixergui;

import java.io.File;
import java.util.ArrayList;
import gui_helper.MainPageHelper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import mixer.MixControl;
import mixer.MixControlFactory;

public class Channel {
	private BorderPane channel;
	private MixControlFactory controller;
	private Button play;
	private int id;
	private String status="";
	private Button delete;
	private Button load;
	private static int loaded=0;
	private MainPageHelper manager;
	private boolean isloaded=false;
	private TextArea t;
	private int playc=0;
	private String vol="100";
	private String pancontrol="50";
	private Button stop;
	private String song_Name="";
	private String clippath;
	private String curtime;
	private Button pause;
	private Button mute;
	private String title;
	private ArrayList<String[]> mixdata=new
			ArrayList<String[]>();
	private MixControl con;
	private String dur="00:00:00";
	private boolean isOnMute=false;
	private boolean sname;
	private boolean playOn=false;
	
	//**************************************
	//overload for channel id verification
	//**************************************
	public void start(Stage chlstage,int id,String title) throws Exception {
		if(title.equals("")){
			this.title="untitled";
			
		}
		else{
			this.title=title;
		}
		controller=new MixControlFactory();
		con=controller.create("AudioClipControl");
		manager=new MainPageHelper();
		setChannelLayout();
		
		
	//****************************************************
	//set stage layout
	//****************************************************
		
	}
	public BorderPane setChannelLayout(){
		
		
		play=new Button();
		pause=new Button();
		stop=new Button();
		load=new Button();
		mute=new Button();
		delete=new Button();
		
		
		
		return channel;
	}
	public void setButtonSize(){
		play.setPrefSize(32, 32);
		load.setPrefSize(32, 32);
		pause.setPrefSize(32, 32);
		stop.setPrefSize(32, 32);
		mute.setPrefSize(32, 32);
		delete.setPrefSize(32, 32);
	}
	
	
	public void setButtonImageView(Button b,String imgpath){
		Image img1=new Image(imgpath,16,16,false,true,true);
		b.setGraphic(new ImageView(img1));
		//b.setStyle("-fx-background-color: black");
		b.setStyle("-fx-background-color: black; -fx-border-color:white;");
	}
	public ArrayList<String[]> getMixData(){
		return mixdata;
		
	}
	/////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////
	public Button getButtonLoad(Stage stage,String title,String path,int size){
		Button loadcp=new Button();
		playOn=false;
		/*if(path!=null && size!=0){
			loadcp.setPrefSize(32, 32);
			setButtonImageView(loadcp,"/load.png");
			
			clippath=path;
			song_Name=songName(clippath);
			con.load(clippath);
			status="loaded";
			loaded++;
			t.setText("Song:"+song_Name+"\n"+"Status:"+status+"\n"
					+"Vol:"+vol+"\n"+"Dur:"+dur+"\n"+"ID:"+id+"\n");
			
			
			mixdata=con.saveInitMix(clippath,song_Name,id);
			
			loadcp.setOnAction(e->{
				FileChooser filech=new FileChooser();
				filech.setTitle("load audio file");
				filech.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("WAV", "*.wav"),
				new FileChooser.ExtensionFilter("MP3", "*.mp3"));
				File file=filech.showOpenDialog(stage);
				if(file!=null){
					clippath=file.toURI().toString();
					System.out.println(clippath);
					con.load(clippath);
					song_Name=songName(clippath);
					isloaded=true;
					mixdata=con.saveInitMix(clippath,song_Name,id);
					t.setText("Song:"+song_Name+"\n"+"Status:"+status+"\n"
							+"Vol:"+vol+"\n"+"Dur:"+dur+"\n"+"ID:"+id+"\n");
					loaded++;
					}
				});
			}
			
			//else{
			 * 
			 */
				loadcp.setPrefSize(32, 32);
				setButtonImageView(loadcp,"/load.png");
				loadcp.setOnAction(e->{
					if(playOn==false) {
						FileChooser filech=new FileChooser();
						filech.setTitle("load audio file");
						filech.getExtensionFilters().addAll(
								new FileChooser.ExtensionFilter("WAV", "*.wav"),
								new FileChooser.ExtensionFilter("MP3", "*.mp3"));
						File file=filech.showOpenDialog(stage);
						if(file!=null){
							clippath=file.toURI().toString();
							con.load(clippath);
							song_Name=songName(clippath);
							sname=true;
							status="loaded";
							t.setText("Song:"+song_Name+"\n"+"Status:"+status+"\n"
									+"Vol:"+vol+"\n"+"Dur:"+dur+"\n"+"ID:"+id+"\n");
							mixdata=con.saveInitMix(clippath,song_Name,id);
							loaded++;
							}
						}
					else {
						Alert a=new Alert(AlertType.ERROR);
						a.setTitle("Live_Multitrack_Mixer");
						a.setContentText("Another track is played,stop the current track first!!");
						a.showAndWait();
						e.consume();
						}
					});
			//}
		return loadcp;
	}
	///////////////////////////////////////////
	///////////////////////////////////////////
	///////////////////////////////////////////
	public Button getButtonStop(){
		Button stopcp=new Button();
		stopcp.setPrefSize(32, 32);
		setButtonImageView(stopcp,"/stop.png");
		stopcp.setOnAction(e->{
			if(clippath==null){
				Alert a=new Alert(AlertType.ERROR);
				a.setTitle("Live_Multitrack_Mixer");
				a.setContentText("No loaded tracks");
				a.showAndWait();
			}
			else{
					con.stop();
					status="stop";
					playOn=false;
					t.setText("Song:"+song_Name+"\n"+"Status:"+status+"\n"
							+"Vol:"+vol+"\n"+"Dur:"+dur+"\n"+"ID:"+id+"\n");
				}
				
			
		});
		return stopcp;
	}
	//////////////////////////////////////////////////
	//////////////////////////////////////////////////
	//////////////////////////////////////////////////
	public TextArea getDisplay(String title,int id){
		this.title=title;
		this.id=id;
		title=song_Name;
		t=new TextArea();
		t.setEditable(false);
		t.setPrefSize(180, 10);
		String style="-fx-control-inner-background: #006600; -fx-font-family: Consolas; "
				+ "-fx-highlight-fill: #99FF00; -fx-highlight-text-fill:#006600;"
				+ "-fx-text-fill: #99FF00; -fx-border-color: black;";
		
		
		t.setStyle(style);
		if(isloaded){
			status="loaded";
			t.setText("Song:"+title+"\n"+"Status:"+status+"\n"
					+"Vol:"+vol+"\n"+"Dur:"+dur+"\n"+"ID:"+id+"\n");
		}
		else{
			status="waiting...";
			t.setText("Song:"+title+"\n"+"Status:"+status+"\n"
				+"Vol:"+vol+"\n"+"Dur:"+dur+"\n"+"ID:"+id+"\n");
		}
		
		return t;
	}
	public TextArea getMixerDis(String title){
		TextArea m=new TextArea();
		m.setEditable(false);
		m.setPrefSize(30, 5);
		String style="-fx-control-inner-background: #006600; -fx-font-family: Consolas; "
				+ "-fx-highlight-fill: #99FF00; -fx-highlight-text-fill:#006600;"
				+ "-fx-text-fill: #99FF00; -fx-border-color: black;";
		if(sname) {
			title=song_Name;
			m.setText(title);
		}
		else {
			m.setText(title);
		}
		
		
		m.setStyle(style);
		return m;
	}
	
	
	///////////////////////////////////////////////
	///////////////////////////////////////////////
	///////////////////////////////////////////////
	public Button getButtonPlay(){
		Button playcp=new Button();
		playcp.setPrefSize(32, 32);
		setButtonImageView(playcp,"/playf.png");
		
		playcp.setOnAction(e->{
			if(clippath==null){
				Alert a=new Alert(AlertType.ERROR);
				a.setTitle("Live_Multitrack_Mixer");
				a.setContentText("No loaded tracks");
				a.showAndWait();
			}
			else{
				con.play();
				playOn=true;
				curtime=String.valueOf(con.getDuration());
				ArrayList<String> time=manager.update(curtime);
				dur="00"+":"+
						time.get(2)+time.get(3)+":"+time.get(4)+time.get(5);
					
				if(!con.isplayed()){
						status="play";
						t.setText("Song:"+song_Name+"\n"+"Status:"+status+"\n"
								+"Vol:"+vol+"\n"+"Dur:"+dur+"\n"+"ID:"+id+"\n");
						setButtonImageView(playcp,"/playf.png");
				}
				else{
					status="pause";
					playOn=false;
					t.setText("Song:"+song_Name+"\n"+"Status:"+status+"\n"
							+"Vol:"+vol+"\n"+"Dur:"+dur+"\n"+"ID:"+id+"\n");
					setButtonImageView(playcp,"/pause.png");
						
					}
				}
		});
		return playcp;
	}
///////////////////////////////////////////////////////
//////////////////////////////////////////////////////
//////////////////////////////////////////////////////
	public Button getButtonMute(){
		Button mute=new Button();
		mute.setPrefSize(32, 32);
		setButtonImageView(mute,"/muteof.png");
		mute.setOnAction(e->{
			if(clippath==null){
				Alert a=new Alert(AlertType.ERROR);
				a.setTitle("Live_Multitrack_Mixer");
				a.setContentText("No loaded tracks");
				a.showAndWait();
			}
			else{
				if(isOnMute==false){
					setButtonImageView(mute,"/muteof.png");
					con.mute();
					isOnMute=true;
				}
				else{
					setButtonImageView(mute,"/muteon.png");
					con.mute();
					isOnMute=false;
				}
			}
		});
		return mute;
	}
	///////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Slider getSlider(){
		Slider sl =new Slider();
		sl.setOrientation(Orientation.VERTICAL);
		sl.setMax(100.0);
		sl.setMin(0.0);
		sl.setValue(100.0);
		sl.setMajorTickUnit(100);
		sl.setShowTickLabels(true);
		sl.setShowTickMarks(true);
		sl.setPrefSize(100, 180);
		sl.valueProperty().addListener(new ChangeListener(){

			public void changed(ObservableValue arg0, Object arg1, Object arg2) {
				float gain=con.gain((float)sl.getValue()/100);
				vol=String.valueOf((int)(gain*100));
				if(sname) {
					t.setText("Song:"+song_Name+"\n"+"Status:"+status+"\n"
							+"Vol:"+vol+"\n"+"Dur:"+dur+"\n"+"ID:"+id+"\n");
				}
			}
			
		});
		return sl;
		
	}
	//////////////////////////////////////////
	//////////////////////////////////////////
	//////////////////////////////////////////
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Slider getPanning(){
		Slider pan=new Slider();
		pan.setOrientation(Orientation.HORIZONTAL);
		pan.setMax(1.0);
		pan.setMin(-1.0);
		pan.setValue(0.0);
		pan.setMajorTickUnit(100);
		pan.setShowTickLabels(true);
		pan.setShowTickMarks(true);
		pan.setPrefSize(80, 50);
		pan.valueProperty().addListener(new ChangeListener(){

			public void changed(ObservableValue arg0, Object arg1, Object arg2) {
				float balance=con.balance((float)pan.getValue());
				pancontrol=String.valueOf((int)(balance));
				
			}
			
		});
		
		return pan;
	}
	//////////////////////////////////////////
	//////////////////////////////////////////
	//////////////////////////////////////////
	public String getTitle(){
		return this.title;
	}
	public String getClippath(){
		return clippath;
	}
	public boolean isLoaded(){
		return isloaded;
		
	}
	public int getload() {
		return loaded;
	}
	public void stopWDelete(){
		con.stop();
	}
	public int getOnPlay(){
		return playc;
	}
	public String songName(String path) {
		String name;
		File f=new File(path);
		name=f.getName();
		return name;
	}
	
}

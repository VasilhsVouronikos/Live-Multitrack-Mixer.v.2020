package gui_helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import mixergui.Channel;

public class MainPageHelper {
	private File[] audioFiles;
	private ArrayList<String>labels=new ArrayList<String>();
	private ArrayList<String[]> mix=new ArrayList<String[]>();
	private String mixname;
	private String Title;
	
	public File[] openFile(Stage stage){
		
		DirectoryChooser filech=new DirectoryChooser();
		filech.setTitle("Open");
		File file=filech.showDialog(stage);
		if(file!=null){
			String path=file.getAbsolutePath();
			File filepath=new File(path);
			audioFiles=filepath.listFiles();
		}
		return audioFiles;
	}
	
	

	public void setTitle(String string) {
		this.Title=string;
		
	}
	public String getTitle(){
		return Title;
	}
	public String InitName(){
		TextInputDialog in=new TextInputDialog("Enter...");
		in.setTitle("Create");
		in.setHeaderText("Channel name");
		in.showAndWait();
		
		return in.getEditor().getText();
		
		
	}
	
	public void saveFile(ArrayList<Channel>items,String path,String name){
		try {
			File f=new File(path+"//"+name+".mix");
			if(f.exists()){
				Alert a=new Alert(AlertType.WARNING);
				a.setTitle("Master_Audio_Mixer");
				a.setContentText("File name already exists");
				a.showAndWait();
			}
			else{
				FileWriter wr=new FileWriter(f);
				BufferedWriter bf=new BufferedWriter(wr);
				for(int i=0; i<items.size(); i++){
					ArrayList<String[]>data=items.get(i).getMixData();
					for(int j=0; j<data.size(); j++){
						String track[]=data.get(j);
						bf.write(track[0]+"===>"+track[1]);
						
					}
					bf.newLine();
				}
				f.setReadOnly();
				bf.close();
			}
			File dataFolder=new File("C://Mix_data//"+name);
			if(!dataFolder.exists()){
				dataFolder.mkdir();
				File dataFile=new File("C://Mix_data//"+name+"//"+name+".mix");
				FileWriter wr=new FileWriter(dataFile);
				BufferedWriter bf=new BufferedWriter(wr);
				for(int i=0; i<items.size(); i++){
					ArrayList<String[]>data=items.get(i).getMixData();
					for(int j=0; j<data.size(); j++){
						String track[]=data.get(j);
						bf.write(track[0]+"===>"+track[1]);
					}
					bf.newLine();
				}
				dataFile.setReadOnly();
				bf.close();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public ArrayList<String[]> openMix(Stage stage){
		FileChooser filech=new FileChooser();
		filech.setTitle("Open");
		File file=filech.showOpenDialog(stage);
		if(file!=null){
			String path=file.getAbsolutePath();
			String[] suffix=path.split("\\.");
			String[] name=suffix[0].split(Pattern.quote(File.separator));
			mixname=name[name.length-1];
			if(!suffix[suffix.length-1].equals("mix")){
				Alert a=new Alert(AlertType.ERROR);
				a.setTitle("Master_Audio_Mixer");
				a.setContentText("This file is not a '.mix' file");
				a.showAndWait();
			}
			else{
				try {
					BufferedReader bf=new BufferedReader(new FileReader(path));
					String line;
					while((line=bf.readLine())!=null){
						String[] mixLine=line.split("===>");
						mix.add(mixLine);
					}
					bf.close();
				} catch ( IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		return mix;
	}
	public void setWorkspace(){
		
		File wrksp=new File("C:\\Mix_data");
		if(wrksp.exists()){
			Alert a=new Alert(AlertType.INFORMATION);
			a.setTitle("Live_Multitrack_Mixer");
			a.setContentText("Welcome again!!");
			a.showAndWait();
		}
		else{
			Alert a=new Alert(AlertType.INFORMATION);
			a.setTitle("Master_Audio_Mixer");
			a.setContentText("Welcome to Master_Audio_Mixer");
			a.showAndWait();
			wrksp.mkdir();
		}
		
	}
	
	public boolean exist(String title){
		if((Collections.frequency(labels, title))>=2){
			return true;
		}
		return false;
	}
	public String getMixName(){
		return mixname;
	}
	public ArrayList<String> update(String curtime){
		
		ArrayList<String> times=new ArrayList<String>();
		
		String[]septime=curtime.split("\\.");
		System.out.println(curtime);
		
		String hours0;
		String hours1;
		String min0;
		String min1;
		String sec0;
		String sec1;
		
		if(septime.length==2){
			hours0="0";
			hours1="0";
			
			double sec=Integer.valueOf(septime[1])/10;
			String fsec=String.valueOf(sec);
			String rsec[]=fsec.split("\\.");
			
			if(Integer.valueOf(septime[0])>10){
				
				
				double min=Integer.valueOf(septime[0])/10;
				String fmin=String.valueOf(min);
				String rmin[]=fmin.split("\\.");
				
				
				min0=rmin[0];
				min1=rmin[1];
				sec0=rsec[0];
				sec1=rsec[1];
				times.add(hours0);
				times.add(hours1);
				times.add(min0);
				times.add(min1);
				times.add(sec0);
				times.add(sec1);
			}
			else{
				min0="0";
				min1=septime[0];
				sec0=rsec[0];
				sec1=rsec[1];
				times.add(hours0);
				times.add(hours1);
				times.add(min0);
				times.add(min1);
				times.add(sec0);
				times.add(sec1);
			}
			
			
			
		}
		
		
		return times;
		
		
	}
	
}

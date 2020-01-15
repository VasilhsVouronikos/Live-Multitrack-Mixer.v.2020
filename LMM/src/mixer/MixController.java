package mixer;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MixController implements MixControl {
	private MediaPlayer mp;
	private Media me;
	private ArrayList<String[]>paths;
	private boolean mute=false;
	private int loadcount=0;
	private int playcount=0;
	private int onplay=0;
	private double duration;
	private boolean stoped=true;
	public boolean isFinished=false;
	public boolean isplayed;
	private String path;
	public MixController(){
		paths=new ArrayList<>();
	}
	@Override
	public void play() {
		new Thread(){
			public void run(){
				if(!stoped){
					stoped=true;
					isplayed=false;
					onplay--;
					mp.pause();
				}
				else{
					if(loadcount==0){
						try {
							Thread.sleep(5000);
						} catch (Exception e1) {
							
							e1.printStackTrace();
						}
					}
					loadcount=1;
					stoped=false;
					isplayed=true;
					onplay++;
					mp.play();
					}
					
				}
			}.start();
			if(playcount==0){
				duration=(double) mp.getTotalDuration().toMinutes();
			}
			playcount=1;
			
	}

	@Override
	public void stop() {
		new Thread(){
			public void run(){
				mp.stop();
				stoped=true;
				isplayed=false;
				onplay--;
			}
		}.start();
	}
	

	@Override
	public void mute() {
		mp.setMute(mute);
		mute=setMute();
	}

	@Override
	public void loop() {
		
		
	}

	@Override
	public float gain(float gain) {
		new Thread(){
			public void run(){
				mp.setVolume(gain);
			}
		}.start();
		return gain;
	}

	public boolean setMute(){
		if(mute==false){
			mute=true;
		}
		else{
			mute=false;
			
		}
		return mute;
	}
	@Override
	public void cleanUp(){
		mp.dispose();
	}
	public ArrayList<String[]> saveInitMix(String path,String label,int id){
		String track[]=new String[3];
		track[0]=path;
		track[1]=label;
		track[2]=String.valueOf(id);
		//add path and label but avoid dublicated items
		if(paths.size()!=0){
			for(int i=0; i<paths.size(); i++){
				String cur[]=paths.get(i);
				if(cur[2].equals(track[2])){
					paths.remove(i);
					paths.add(cur);
				}		
			}
		}
		else{
			paths.add(track);
		}
		
	
		return paths;
	}
	public ArrayList<String[]> getPaths(){
		return paths;
	}
	public boolean getStoped(){
		return stoped;
	}
	@Override
	public void load(String file) {
		this.path=file;
		new Thread(){
			public void run(){
				me= new Media(file);
				mp=new MediaPlayer(me);
			}
			
		}.start();
		
	}
	@Override
	public boolean isplayed() {
		
		return isplayed;
	}
	

	@Override
	public double getDuration() {
		BigDecimal bd=BigDecimal.valueOf(duration);
		bd=bd.setScale(2,RoundingMode.HALF_DOWN);
		return bd.doubleValue();
	}
	@Override
	public float balance(float pan) {
		new Thread(){
			public void run(){
				mp.setBalance(pan);
			}
		}.start();
		return pan;
	}


	

	
	

}

package mixer;

import java.util.ArrayList;





public interface MixControl {
	public void play();
	public void stop();
	public void mute();
	public void loop();
	public float gain(float gain);
	public ArrayList<String[]> saveInitMix(String clippath, String title,int id);
	public void cleanUp();
	public boolean getStoped();
	public void load(String file);
	public boolean isplayed();
	public double getDuration();
	public float balance(float pan);
	
	

	
	

}

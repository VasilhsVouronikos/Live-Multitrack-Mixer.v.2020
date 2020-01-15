package mixer;



public class MixControlFactory {
	public MixControlFactory(){
		
	}
	public MixControl create(String classname){
		switch(classname) {
		case "AudioClipControl": 
			return new MixController();
		default: 
			return null;
	}

	}
}

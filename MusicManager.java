package Core;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import TypeListings.ObjectType;

public class MusicManager {
	ArrayList<MusicClips> musList;
	
	public MusicManager() {
		this.musList= new ArrayList<MusicClips>();
	}
	
	public void addMusic(ObjectType type) {
    	int i = 0; 
        switch (type) {
        case PLAYERSHIP:
        	for (i = 0; i < this.musList.size() -1; i++ ) {
        		if (this.musList.get(i).getName().equalsIgnoreCase("SHIP")) {
        			// Sets frame position for clip to 0, since after the object is instantiated it doesn't seem to play.
        			this.musList.get(i).getClip().setFramePosition(0);
        			this.musList.get(i).getClip().start();
        			//this.musList.get(i).getClip().setFramePosition(0);
        			System.out.println("Replay success");
        			System.out.println(i);
        			break;
        		}        			
        	}    		
        	if (i == this.musList.size()){
    			MusicClips musship = new MusicClips("SHIP", 1.0);
        		this.musList.add(musship);
        		this.musList.get(i).getClip().start();
        		System.out.println("Creation success");
                break;
    		}
            break;
        case PROJECTILE:       	
        	for (i = 0; i <= this.musList.size() - 1; i++ ) {
        		if (this.musList.get(i).getName().contentEquals("PROJECTILE")) {
        			this.musList.get(i).getClip().setFramePosition(0);
        			this.musList.get(i).getClip().start();
        			//this.musList.get(i).getClip().setFramePosition(0);
        			System.out.println("Replay success");
        			System.out.println(i);
        			break;
        		}
        }
        	if (i == this.musList.size()){
    			System.out.println(i);
    			MusicClips muspro = new MusicClips("PROJECTILE", 1.0);
        		this.musList.add(muspro);
        		this.musList.get(i).getClip().start();
        		System.out.println("Creation success");
                break;
    		}  	
		default:
			break;
	}
	
}
}

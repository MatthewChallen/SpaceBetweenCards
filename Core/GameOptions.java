package Core;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class GameOptions
{
	// This variable can be used to modify the volume value for
	// a clip instance.
	private double masterVolume;
	
	public GameOptions()
	{
		masterVolume = 1.0;
		
		try
		{
			File file = new File("Data/game_options.txt");
			Scanner scanner = new Scanner(file);
			String optionsData = scanner.nextLine();
			scanner.close();
			
			String[] optionsSplit = optionsData.split(",");
			setVolume(Double.parseDouble(optionsSplit[1]));
			
		} catch(Exception ex)
		{
		   System.out.println("Failed to load game options - check folder.");
		}
	}
	
	public Double getVolume()
	{
		return masterVolume;
	}
	
	public void setVolume(double volume)
	{
		if(volume >= 0.0 && volume <= 2.0)
		{
			masterVolume = volume;
		} else
		{
			System.out.println("Invalid volume setting requested.");
		}
	}
	
	public void saveOptions()
	{
		String saveData = "Volume," + masterVolume;
		
		try
		{
			File file = new File("Data/game_options.txt");
			PrintWriter printWriter = new PrintWriter(file);
			printWriter.println(saveData);
			printWriter.close();
			
		} catch(Exception ex)
		{
			System.out.println("Save error - could not save options.");
		}
	}
}

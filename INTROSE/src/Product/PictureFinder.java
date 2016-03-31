package Product;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.JFileChooser;


public class PictureFinder {
	private static JFileChooser fileChooser;
	
	public PictureFinder()
	{
	}
	
	public String getPicturePath()
	{
		fileChooser = new JFileChooser();

		File path;
		
		String type = "None";
		int approve = fileChooser.showOpenDialog(null);
		
//		if(approve == JFileChooser.APPROVE_OPTION){
//			  System.out.println("getCurrentDirectory(): " + fileChooser.getCurrentDirectory());
//			  System.out.println("getSelectedFile() : " + fileChooser.getSelectedFile());
//		}
			
		path = fileChooser.getSelectedFile();
		try {
			type = path.toString();

		} catch (Exception e) {
			System.out.println("None selected");
		}
		System.out.printf("Path %s", type);
		return type;
	}
}
	


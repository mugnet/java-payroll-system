package payroll_system;


import java.io.File;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Properties;

import javax.swing.JFrame;

public class Config  extends JFrame{
	
	public String baseDir =  System.getProperty("user.dir");
	
	private final String CONFIG_FILE =  baseDir + "/app.env";
	Properties properties;
	String propName;
	
	public Config() {
		 this.properties = new Properties();
		 
		
	}
	
	 
	
	public String config(String name) {
		 
		
		 
		
		 try (FileInputStream fis = new FileInputStream(CONFIG_FILE)) {
	            
			 	this.properties.load(fis);
			 	this.propName = this.properties.getProperty(name); 
	            
	             
	             
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		 
		 
		 return this.propName;
	}
	
	
	 public String getName() {
		 return this.propName;
	 }
	 
	 
	 
	
	 
	
	
}

package payroll_system;

public class Base extends Config{

	public String appDir = baseDir;
	
	public Base() {
		
	}
	
	
	public String basePath() {
		
		return this.appDir + "/";
		
	}
	
	public String basePath(String file_path) { //file path must be separated by a dot (.)
		
		return this.basePath() +  file_path.replace('.', '/');
		
	}
	
	public String helperPath(String file_path) {
		
		return this.strDelimeter(file_path);
		
	}
	
	public String assets(String file_path) {
		
		return this.strDelimeter(file_path);
		
	}
	
	public String strDelimeter(String value) {
		return  this.basePath()+ value.replace('.', '/');
	}
	
	
	
}

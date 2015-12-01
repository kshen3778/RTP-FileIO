// The "FullFileIO" clasasfs.
import java.awt.*;
import hsa.Console;
import java.io.*;
import javax.swing.JOptionPane;
/** @author Vincent Macri
    @author Kevin Shen

    This program demonstrates full file IO. The user can store a number.
    
    Explanation of instance variables:

*/
public class FullFileIO
{
    private Console c;
    private char choice;
    private int number;
    private String header = "camelCaseIsKISS";
    
    /** @author Vincent Macri
     * 	@author Kevin Shen
     * 
     *   The main method, it controls program flow.
     *   
     *   Explanation of local variables:
     *   
     *       Name    Type        Purpose
     *       f       FullFileIO  Reference variable for an instance of the FullFileIO class.
     */
    public static void main (String[] args)
    {
        FullFileIO f = new FullFileIO ();
        mainLoop:
        while (true){
        	f.mainmenu();
        	switch (choice){
        		
        		case '1':
        			f.newValue();
        			break;
        			
        		case '2':
        			f.openFile();
        			break;
        		
        		case '3':
        			f.display();
        			break;
        		
        		case '4':
        			f.modify();
        			break;
        		
        		case '5':
        			f.save();
        			break;
        		
        		case '6':
        			f.saveAs(); 
	        		break;
        		
        		default:
    	    		break mainLoop;
        	}
        }
        
    }

    public void askData(){
		title();
		while(true){
			try{
				c.print("Enter your number: ");
				number = Integer.parseInt (c.readLine ());
				break;
			}catch(NumberFormatException e){
			    JOptionPane.showMessageDialog (null, "Please enter an integer.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	
	public void pauseProgram(){
		c.println ();
		c.println ("Press any key to continue: ");
		c.getChar ();	
	}
	
	public void goodBye(){
		title ();
		c.println ("Thanks for using this program!");
		c.println ("By: Kevin Shen and Vincent Macri");
		pauseProgram ();
		c.close ();
	}

    /** @author Vincent Macri
            Creates a new instance of the FullFileIO class.
            Creates a new Console with a window name.
    */
    public FullFileIO ()
    {
        c = new Console ("Full File IO - Vincent M. & Kevin S.");
    }

	/** @author Vincent Macri
	 * 	
	 * The main menu. It asks the user what they want to do.
	 * 
	 * Explanation of structures:
	 * 
	 * 	Do while loop - Ask the user what they want to do until the input is valid. Ask at least once.
	 * 
	 */
    public void mainMenu ()
    {
        title ();
        c.println ("1. New");
        c.println ("2. Open");
        c.println ("3. Display");
        c.println ("4. Modify");
        c.println ("5. Save");
        c.println ("6. Save As");
        c.println ("7. Quit");
        do
        {
            choice = c.getChar ();
        }
        while (!(choice >= '1' && choice <= '7'));
    }

	/** @author Vincent Macri
	 * 
	 * 	Clear the screen and write the program title to the screen.
	 * 
	 */
    private void title ()
    {
        c.clear ();
        c.print ("", 34);
        c.println ("Full File IO");
        c.println ();
    }
    
    private void newValue(){
    	askData();
    }
    
	private String addExtension(String s){
		String extension = s.substring(s.length()-5, s.length());
		if(!extension.equals(".ksvm")){
			return s + ".ksvm";
		}
		return s;
	}
	
	
	
	/** @author Vincent Macri
	 * 	
	 * 	Open the file the user asks for. Store its value in number.
	 * 
	 */
	public void openFile(){
		String fileName;
        BufferedReader input;
 		
        c.println ("Enter the name of the file you want to open:");
        fileName = addExtension(c.readLine ());
        
        
        
        try
        {
            input = new BufferedReader (new FileReader (fileName));
            line = input.readLine ();
        }catch(FileNotFoundException e){
        	 JOptionPane.showMessageDialog (null, "That file does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        catch (IOException f)
        {
        	JOptionPane.showMessageDialog (null, "Error reading file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
	}
	
	
	
} /* FullFileIO class */
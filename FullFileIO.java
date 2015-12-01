// The "FullFileIO" clasasfs.
import java.awt.*;
import hsa.Console;

/** @author Vincent Macri
	@author Kevin Shen

	This program demonstrates full file IO. The user can store a number.

*/
public class FullFileIO
{
    private Console c;           // The output console

	private int number;
	
    public static void main (String[] args)
    {
	FullFileIO f = new FullFileIO ();
    }
	
	public void askData(){
		title();
		
		
	}


    /** @author Vincent Macri
	    Creates a new instance of the FullFileIO class.
	    Creates a new Console with a window name.
    */
    public FullFileIO ()
    {
	c = new Console ("Full File IO");
    }
} /* FullFileIO class */

// The "FullFileIO" clasasfs.
import java.awt.*;
import hsa.Console;
import java.io.*;
import javax.swing.JOptionPane;

/** @author Vincent Macri
        @author Kevin Shen

        This program demonstrates full file IO. The user can store a number.

        Explanation of instance variables:

                Name            Type            Purpose
                c                  Console       The output Console.
                choice    char          What option the user picked on the main menu.
                number    int            The value stored.
                header    String          The header for the .ksvm files.
                isFileOpen  boolean      If a file is open.
                valueStored boolean      If a number is stored.
                fileName        String    The name of the open file.

*/

public class FullFileIO
{
    private Console c;
    private char choice;
    private int number;
    private String header = "camelCaseIsKISS snake_case_makes_me_feel_dirty PascalCaseLooksReallyStupidReallyReallyStupid";
    private boolean isFileOpen = false, valueStored = false;
    private String fileName;

    /** @author Vincent Macri
     *  @author Kevin Shen
     *
     *   The main method, it controls program flow.
     *
     *   Explanation of local variables:
     *
     *         Name Type            Purpose
     *         f       FullFileIO  Reference variable for an instance of the FullFileIO class.
     */
    public static void main (String[] args)
    {
        FullFileIO f = new FullFileIO ();
        mainLoop:
        while (true)
        {
            f.mainMenu ();
            switch (f.choice)
            {

                case '1':
                    f.askData ();
                    break;

                case '2':
                    f.openFile ();
                    break;

                case '3':
                    f.display ();
                    break;

                case '4':
                    f.modify ();
                    break;

                case '5':
                    f.save ();
                    break;

                case '6':
                    f.saveAs ();
                    break;

                default:
                    break mainLoop;
            }
        }
        f.goodBye ();
    }


    /** Display a JOptionPane popup error.
     * @param error The error message.
     * @param icon The error icon.
     */
    private void error (String error, int icon)
    {
        JOptionPane.showMessageDialog (null, error, "Error", icon);
    }


    public void save ()
    {
        PrintWriter output;
        BufferedReader file;

        if (!isFileOpen)
        {
            saveAs ();
        }

        //If there is nothing to save
        if (!valueStored)
        {
            error ("There is nothing to save.", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        //if the user chooses Save
        try
        {
            file = new BufferedReader (new FileReader (fileName));

            //if yes - continues and saves
            //if no - call saveas which creates a new file
            //if cancel return
            int userChoice = yesNoCancelBox ("Would you like to overwrite the existing file?");

            if (userChoice != 0)
            {
                if (userChoice == 1)
                {
                    saveAs ();
                }
                else
                {
                    return;
                }
            }

        }
        catch (IOException e)
        {
            e.printStackTrace ();
        }

        try
        {
            output = new PrintWriter (new FileWriter (fileName));
            output.println (header);
            output.println (number);
            output.close ();
        }
        catch (IOException e)
        {
            e.printStackTrace ();
        }


    }


    public void saveAs ()
    {
        if (valueStored)
        {
            getValidFileName ("save");
            save ();
        }
        else
        {
            error ("There is nothing for you to save.", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    /**
     * @author Vincent Macri
     * @author Kevin Shen
     * Clear the screen, display the number, then call pauseProgram.
     */
    public void display ()
    {
        if (valueStored)
        {
            title ();
            c.println ("The number stored in your file is: " + number);
            pauseProgram ();
        }
    }


    public void askData ()
    {
        title ();

        while (true)
        {
            clearLine (4);
            try
            {
                c.print ("Enter your number: ");
                number = Integer.parseInt (c.readLine ());
                valueStored = true;
                break;
            }
            catch (NumberFormatException e)
            {
                error ("Please enter an integer.", JOptionPane.ERROR_MESSAGE);
            }

            if (!yesNoBox ("Would you like to enter a number instead of trying to crash me?"))
            {
                return;
            }
        }
    }


    /**
     * @author Vincent Macri
     * @author Kevin Shen
     * If a file is open, call askData(). If no file is open, error.
     *
     */
    public void modify ()
    {
        if (isFileOpen)
        {
            askData ();
        }
        else
        {
            error ("You cannot modify a file when no file is open.", JOptionPane.WARNING_MESSAGE);
        }
    }



    /**
     *  @author Vincent Macri
     * @author Kevin Shen
     *  Pause the program until a key is pressed.
     */
    private void pauseProgram ()
    {
        c.setCursor (24, 1);
        c.println ("Press any key to continue...");
        c.getChar ();
    }


    public void goodBye ()
    {
        title ();
        c.println ("Thanks for using this program!");
        c.println ("By: Vincent Macri and Kevin Shen");
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
     *        Do while loop - Ask the user what they want to do until the input is valid. Ask at least once.
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


    private void clearLine (int line)
    {
        c.setCursor (line, 1);
        c.println ();
        c.setCursor (line, 1);
    }


    /** Clear the screen and write the program title to the screen. */
    private void title ()
    {
        c.clear ();
        c.print ("", 34);
        c.println ("Full File IO");
        c.println ();
    }


    //TODO: Trap for file name smaller than 4 characters.
    private String addExtension (String s)
    {
        if (s.length () > 5 && s.substring (s.length () - 5, s.length ()).equals (".ksvm"))
        {
            return s;
        }
        return s + ".ksvm";
    }


    /** Return true if they click yes, false if they click no. */
    private boolean yesNoBox (String text)
    {
        return (JOptionPane.showConfirmDialog (null, text, "Pick one.", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0);
    }


    private int yesNoCancelBox (String text)
    {
        return JOptionPane.showConfirmDialog ((Component) null, text, "What would you like to do?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
    }


    /**
     * @author Vincent Macri
     *
     * Return true if the file name is valid, false if its not.
     *
     */
    private boolean isFileNameValid (String file)
    {
        return !((file.indexOf ("\\") + file.indexOf ("/") + file.indexOf ("?") + file.indexOf (":") + file.indexOf ("*")
                    + file.indexOf ("\"") + file.indexOf ("<") + file.indexOf (">") + file.indexOf ("|") != -9) || (file.length () <= 0));
    }


    /**
     * @param operation What the user wants to do with the file.
     *
     * @return True if they picked a file, false if they didn't.'
     *
     */
    private boolean getValidFileName (String operation)
    {
        title ();
        c.println ("Enter the name of the file to want to " + operation + ":");
        while (true)
        {
            clearLine (4);
            fileName = c.readLine ();

            if (isFileNameValid (fileName))
            {
                break;
            }

            error ("Invalid file name.", JOptionPane.WARNING_MESSAGE);

            if (!yesNoBox ("Do you want to enter another file name?"))
            {
                return false;
            }
        }

        isFileOpen = true;
        fileName = addExtension (fileName);

        return true;
    }


    /** @author Vincent Macri
     *  @author Kevin Shen
     *
     *        Open the file the user asks for. Store its value in number.
     *
     */
    public void openFile ()
    {
        title ();
        String inputStr;
        BufferedReader input;


        while (true)
        {
            if (!getValidFileName ("open"))
            {
                return;
            }
            try
            {
                input = new BufferedReader (new FileReader (fileName));
                inputStr = input.readLine (); //Read header.
                if (inputStr.equals (header)) //Check header.
                {
                    number = Integer.parseInt (input.readLine ());
                    isFileOpen = true;
                    valueStored = true;
                    break;
                }

                error ("This file could not be opened. It may be corrupt.", JOptionPane.WARNING_MESSAGE);
            }
            catch (FileNotFoundException e)
            {
                error ("That file does not exist, or is not the right file type.", JOptionPane.ERROR_MESSAGE);
            }
            catch (NumberFormatException f)
            {
                error ("This file does not contain a valid integer. It may be corrupt.", JOptionPane.ERROR_MESSAGE);
            }
            catch (IOException g)
            {
                error ("Error reading file.", JOptionPane.ERROR_MESSAGE);
                g.printStackTrace ();
            }
            if (!yesNoBox ("Would you like to open a different file?"))
            {
                return;
            }
        }
        f.display ();
    }
} /* FullFileIO class */



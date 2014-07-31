package in.msitprogram.iiit.paypal;

import java.io.IOException;

import in.msitprogram.iiit.paypal.console.MainMenu;

/**
 * @author pg
 *
 */
public class PPSystem 
{

	public static void main(String[] args) throws IOException, ClassNotFoundException 
	{
	  try
	  {
		MainMenu.show();
	  }
	  catch(Exception e)
	  {
		  
	  }
	  PPSystem.main(null);
	}

}

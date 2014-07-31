/**
 * 
 */
package in.msitprogram.iiit.paypal.utils;
import java.util.*;
import java.util.Random;
/**
 * @author pg
 *
 */
public class PPToolkit {

	public static String generateActivationCode() 
	{
		/*Random randomGenerator = new Random();
		Character ch;
		String str="",s;
		int k;
		for(int i=0;i<6;i++)
		{
		do
			{
				k=randomGenerator.nextInt(100);
			}
		while(!((k>96&&k<123)||((k>47&&k<58)&&i!=0)));
		
		ch = new Character((char)k);
		s=ch.toString();
		str=str+s;
		}
		return str;*/
		
		
	    return "a23j5h";
	}

	public static void sendActivationCode(String phone) 
	{
		
		
	}

}

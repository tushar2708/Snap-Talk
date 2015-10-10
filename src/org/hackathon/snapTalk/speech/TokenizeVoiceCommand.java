package org.hackathon.snapTalk.speech;

import android.content.Context;
import java.util.Arrays;
import org.hackathon.snapTalk.R;

public class TokenizeVoiceCommand {
	
	private static TokenizeVoiceCommand instance = null;
	
	private static String[] action_array;
	private static String[] property_array;
	private static String[] value_array;
	
	private static Context mContext;
	
	private TokenizeVoiceCommand(Context context) {
	      // Exists only to defeat instantiation.
	   }
	public static TokenizeVoiceCommand getInstance(Context context) {
		if(instance == null) {
			instance = new TokenizeVoiceCommand(context);
		}
		mContext = context;
		
		action_array = mContext.getResources().getStringArray(R.array.action);
		property_array = mContext.getResources().getStringArray(R.array.property);
		value_array = mContext.getResources().getStringArray(R.array.value);
		
		return instance;
	}
	
	public CommandTokens resolveCommand(String voice_command){
		CommandTokens tokens = new CommandTokens();
		String[] parts = voice_command.split(" ");
		
		if(isAction(parts[0]))
			tokens.action = parts[0];
		
		if(parts.length <= 1){
			tokens.property = parts[0];
			return tokens;
		}
		
		if(isProperty(parts[1]))
			tokens.property = parts[1];
		
		if(parts.length <= 2)
			return tokens;
		
			if((parts[2].compareToIgnoreCase("to") == 0) || (parts[2].compareToIgnoreCase("by") == 0) || (parts[2].compareToIgnoreCase("via") == 0) )
			{
				if(isValue(parts[3]))
					tokens.value = parts[3];	
			} else {
				if(isValue(parts[2]))
					tokens.value = parts[2];
			}
		
		if(parts.length > 4)
			tokens.others = Arrays.copyOfRange(parts, 3, (parts.length));
		
		return tokens;
	}
	
	public static boolean isAction(String targetValue) {
		for(String s: action_array){
			if(s.compareToIgnoreCase(targetValue) == 0)
				return true;
		}
		return false;
	}
	
	public static boolean isProperty(String targetValue) {
		for(String s: property_array){
			if(s.compareToIgnoreCase(targetValue) == 0)
				return true;
		}
		return false;
	}
	
	public static boolean isValue(String targetValue) {
		for(String s: value_array){
			if((s.compareToIgnoreCase(targetValue) == 0) || isNumeric(targetValue) ) 
				return true;
		}
		return false;
	}
	
	public static boolean isNumeric(String str)
	{
	  return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
	}

}

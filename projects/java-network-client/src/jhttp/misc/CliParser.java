/*
*
* Command line option parser for Jhttp network client
*
* short form options -d
* long form options --data
*
* some options have values, others just need to be present, like -v
*
* java Cli --data "n1=v1&n2=v2" -h "h1=v1&h2=v2" -m "POST" -v
*
*/

import java.util.HashMap;

public class CliParser {

	public static void main(String[] args){
		if(args.length == 0){
			System.out.println("usage: jhttp <options> <url>");
			return;
		}

		HashMap<JhttpOption, String> argMap = new HashMap<JhttpOption, String>();

		for(int i=0; i<args.length-1; i++){
			if(isKey(args[i])){
				putEnumKeyAndValue(i, args, argMap);
			}
		}
		argMap.put(JhttpOption.URL, args[args.length-1]);

		for(JhttpOption option : argMap.keySet()){
			System.out.println(option + ": " + argMap.get(option));
		}
	}

	public static void putEnumKeyAndValue(int index, String[] args, HashMap<JhttpOption, String> argMap){
 			String key = args[index];
			String value = getValueIfValid(index+1, args);

			JhttpOption option = null;

			switch(key){
				case "-m" : option = JhttpOption.METHOD;
				break;
				case "-h" : option = JhttpOption.HEADER;
				break;
				case "-d" : option = JhttpOption.DATA;
				break;
				case "-v" : option = JhttpOption.VERBOSE;
				break;
				default : option = JhttpOption.UNKNOWN;
				break;
			}

			if(option == JhttpOption.UNKNOWN){
				System.out.println("unknown option was ignored: " + key + " " + value);
			}else{
				if(argMap.containsKey(option)){
					  if(option == JhttpOption.HEADER){
							value = argMap.get(option) + "&" + value;
						}else{
							value = argMap.get(option) + value;
						}
				}

				argMap.put(option, value);
			}
	}

	public static boolean isKey(String str){
		return str.charAt(0) == '-';
	}

	public static String getValueIfValid(int index, String[] args){
		String value = null;

		if(index > 0 && index < args.length-1){
			if(!isKey(args[index])){
				value = args[index];
			}
		}

		return value;
	}
}

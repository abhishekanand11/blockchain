

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternExtractor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// String stringToSearch = "CMAX:GEM 2456 04-23-2018 01:07:35 My Name is Khan File: US_en_NGN_H_Generic.html DO NOT MODIFY”";
		String test= "<!--Created by CMAX:GEM 05-22-2018 03:55:15 File: US_en_NGN_H_Generic.html DO NOT MODIFY-->";
		String regex = "(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])-([0-9]{4}) (2[0-3]|[01][0-9]):([0-5][0-9]):([0-5][0-9])";
		Pattern pattern = Pattern.compile(regex);
		Matcher m = pattern.matcher(test);
		
		if(m.find()) {
			System.out.println();
			System.out.println(m.group());
		}
	}

}

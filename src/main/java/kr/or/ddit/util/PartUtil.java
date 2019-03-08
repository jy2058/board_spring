package kr.or.ddit.util;

public class PartUtil {

	public static String getFileNameFromPart(String contentDisposition) {
		String[] splits = contentDisposition.split("; ");
		String filename = "";
		
		for (String str : splits) {
			if (str.startsWith("filename=")) {
//				filename = str.split("\"")[1];
				filename = str.substring(str.indexOf("\"") + 1, str.lastIndexOf("\""));
			}
		}
		
		return filename;
	}

}

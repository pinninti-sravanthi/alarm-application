package com.alarmspring;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

public class Util {
	public static String getPostTimerJDO(HttpServletRequest req) {
		StringBuilder sb = new StringBuilder();
		try {
		BufferedReader reader = req.getReader();
		reader.mark(10000);

		String line;
		do {
		line = reader.readLine();
		sb.append(line).append("\n");
		} while (line != null);
		reader.reset();
		// do NOT close the reader here, or you won't be able to get the post data twice
		} catch(IOException e) {
		//logger.warn("getPostData couldn't.. get the post data", e); // This has happened if the request's reader is closed 
		e.printStackTrace();
		}

		return sb.toString();
		}

}

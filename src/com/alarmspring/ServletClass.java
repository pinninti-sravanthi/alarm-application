/*package com.alarmspring;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletClass extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Logger.getLogger("").info("inside doPost ");
		Long ms;
		
		<String> addTime=req.getParameter("giveTime");
		
		//System.out.println(addTime);
		
		SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss");
		
	    Date d;
		try {
			d = f.parse(addTime);
		
	     ms = d.getTime();
	    System.out.println(ms);
		JSONObject jObj = new JSONObject(req.getParameter("jsonobject"));
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> data = mapper.readValue(req.getParameter("jsonobject"), Map.class);
		 //System.out.println(data);
		TimerJDO object = new TimerJDO();
		object.setaddTime(ms);
		PersistenceManager pmf = PMF.get().getPersistenceManager();
		pmf.makePersistent(object);
		//resp.getWriter().write("data stored"+addTime);
	} catch (ParseException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	}
}
*/
package com.alarmspring;

import java.io.IOException;
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
		
		String addTime=req.getParameter("giveTime");
		System.out.println(addTime);
		/*JSONObject jObj = new JSONObject(req.getParameter("jsonobject"));*/
		/*ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> data = mapper.readValue(req.getParameter("jsonobject"), Map.class);*/
		 //System.out.println(data);
		TimerJDO object = new TimerJDO();
		object.setaddTime(addTime);
		PersistenceManager pmf = PMF.get().getPersistenceManager();
		pmf.makePersistent(object);
		//resp.getWriter().write("data stored"+addTime);
	}

}

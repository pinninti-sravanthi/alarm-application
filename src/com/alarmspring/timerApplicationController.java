package com.alarmspring;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;

@SuppressWarnings("serial")
@Controller
public class timerApplicationController {
	@RequestMapping("/")
	public String home() {
		return "index";
	}

	@ResponseBody
	@RequestMapping(value = "/AuthenticationServlet", method = RequestMethod.POST)
	public String register(@RequestBody String userdata, HttpServletRequest request, HttpServletResponse response)
			throws JsonParseException, JsonMappingException, IOException {
		System.out.println("into register");
		ObjectMapper objectmapper = new ObjectMapper();

		Map<String, String> map = new HashMap<>();
		Map<String, Object> map1 = objectmapper.readValue(userdata, new TypeReference<Map<String, Object>>() {
		});
		System.out.println(userdata);
		String firstname = (String) map1.get("firstname");
		String lastname = (String) map1.get("lastname");
		String email = (String) map1.get("Email");
		String password = (String) map1.get("password");
		System.out.println(firstname);
		System.out.println(email);
		HttpSession session = request.getSession();
		UserJDO userJDOObject = new UserJDO();
		userJDOObject.setfirstName(firstname);
		userJDOObject.setlastName(lastname);
		userJDOObject.setemail(email);
		userJDOObject.setpassword(password);
		PersistenceManager pmf = PMF.get().getPersistenceManager();
		// pmf.makePersistent(pojo);
		session.setAttribute("name", firstname);
		session.setAttribute("email", email);
		javax.jdo.Query q = pmf.newQuery(UserJDO.class, ("email==email1"));
		q.declareParameters("String email1");
		List<UserJDO> obj = (List<UserJDO>) q.execute(email);
		if (obj.isEmpty()) {
			pmf.makePersistent(userJDOObject);
			map.put("key", "success");
			session.setAttribute("id", userJDOObject);
			// session.setAttribute("name", obj.get(0).getfirstName());

		} else {
			map.put("key1", "fail");

			// out.println("you have already registerd ");
		}
		return objectmapper.writeValueAsString(map);

	}

	@ResponseBody
	@RequestMapping(value = "/LoginServlet", method = RequestMethod.POST)
	public String login(@RequestBody String userinfo, HttpServletRequest request, HttpServletResponse response)
			throws JsonParseException, JsonMappingException, IOException {
		System.out.println("into login");
		ObjectMapper objectmapper = new ObjectMapper();
		Map<String, String> map = new HashMap<>();
		Map<String, Object> maper = objectmapper.readValue(userinfo, new TypeReference<Map<String, Object>>() {
		});
		System.out.println(userinfo);
		String email = (String) maper.get("email");
		String password = (String) maper.get("password");
		HttpSession session = request.getSession();
		session.setAttribute("email", email);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		UserJDO loginObject = new UserJDO();
		loginObject.setemail(email);
		loginObject.setpassword(password);
		List<UserJDO> results = null;
		try {

			Query q = pm.newQuery(UserJDO.class, ("email == email1 && password==password1"));
			q.declareParameters("String email1,,String password1");

			try {
				results = (List<UserJDO>) q.execute(email, password);
				if (results.isEmpty() || results.equals(null)) {
					map.put("key", "success");

				} else {

					session.setAttribute("id", results.get(0));
					session.setAttribute("name", results.get(0).getfirstName());
					// System.out.println(name);
					map.put("key1", "fail");
					// map.put("name",name);
				}
			}

			catch (NullPointerException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			pm.close();
		}

		return objectmapper.writeValueAsString(map);
	}

	@ResponseBody
	@RequestMapping(value = "/ListOfTimersOfUser", method = RequestMethod.POST)
	public String displayListOfTimersOfUser(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		Long milli;
		System.out.println("into lists");
		HttpSession session = request.getSession();
		HashMap<String, Object> responseMap = new HashMap<>();

		UserJDO registerobj = (UserJDO) session.getAttribute("id");
		Long Id = registerobj.getId();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List results1 = null;
		List<String> listStrings = new ArrayList<String>();
		try {

			Query query = pm.newQuery(TimerJDO.class, ("Idvalues == id1 && isDeleted == false"));
			query.declareParameters("String id1");
			try {

				results1 = (List<TimerJDO>) query.execute(Id);

				System.out.println("dfdf");
				if (results1.isEmpty() || results1.equals(null)) {
				} else {

					System.out.println("inside the if ");
					for (Object timer : results1) {

						TimerJDO timers = (TimerJDO) timer;
						System.out.println(timers);

						DateFormat formatter = new SimpleDateFormat("hh:mm:ss");
						// String time=timers.getaddTime();
						milli = timers.getaddTime();
						// System.out.println(time + " time");
						// milli=Long.valueOf(time);
						System.out.println(milli);
						String dateFormatted = formatter.format(milli);
						// listStrings.add(timers.getaddTime());
						listStrings.add(dateFormatted);

					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		} finally {

			pm.close();
		}
		responseMap.put("listOfStrings", listStrings);
		ObjectMapper obj = new ObjectMapper();
		String response1 = obj.writeValueAsString(responseMap);

		return response1;

	}

	@ResponseBody
	@RequestMapping(value = "/Timer", method = RequestMethod.POST)
	public String addTime(@RequestBody String timedata, HttpServletRequest request, HttpServletResponse response)
			throws JsonParseException, JsonMappingException, IOException {
		Long milliseconds;

		System.out.println("timer method");
		System.out.println(timedata);
		ObjectMapper objectmapper = new ObjectMapper();

		Map<String, String> map = new HashMap<>();
		Map<String, Object> map1 = objectmapper.readValue(timedata, new TypeReference<Map<String, Object>>() {
		});

		String addtime = (String) map1.get("giveTime");
		System.out.println("entered time in spring" + addtime);

		SimpleDateFormat f = new SimpleDateFormat("hh:mm:ss");

		Date d;
		try {
			d = f.parse(addtime);

			milliseconds = d.getTime();
			// String dateString=milliseconds.toString();
			System.out.println(milliseconds);

			HttpSession session = request.getSession();
			UserJDO registerobj = (UserJDO) session.getAttribute("id");
			Long ids = registerobj.getId();
			System.out.println(ids);
			String email = registerobj.getemail();
			TimerJDO date = new TimerJDO();
			date.setaddTime(milliseconds);
			date.getIdvalues().add(ids);
			date.setEmail(email);

			PersistenceManager pmf = PMF.get().getPersistenceManager();
			// pmf.makePersistent(date);
			List<TimerJDO> results1 = null;
			List<String> listStrings = new ArrayList<String>();
			try {

				Query query = pmf.newQuery(TimerJDO.class, ("addTime == Time && Idvalues == id1"));
				query.declareParameters("Long Time,String id1");

				try {

					results1 = (List<TimerJDO>) query.execute(milliseconds, ids);
					if (results1.isEmpty() || results1.equals(null)) {
						pmf.makePersistent(date);
						map.put("key", "success");
					} else if (results1.get(0).getIsDeleted().equals(true)) {
						results1.get(0).setIsDeleted(false);
						pmf.makePersistent(results1.get(0));
						map.put("key", "success");
					} else {
						map.put("key1", "fail");
						System.out.println("time already exists");
					}
				} catch (Exception e) {
					System.out.println(e);
				}
			} finally {
				pmf.close();
			}

		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return objectmapper.writeValueAsString(map);
	}

	@RequestMapping("/afterlogin")
	public String afterLogin() {
		return "success";
	}

	@RequestMapping("/register")
	public ModelAndView afterregister() {
		return new ModelAndView("success");
	}

	@ResponseBody
	@RequestMapping(value = "/Signout", method = RequestMethod.POST)
	public String signOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("into signout");
		/*
		 * response.setHeader("Cache-Control",
		 * "no-cache, no-store, must-revalidate"); response.setHeader("Pragma",
		 * "no-cache"); response.setHeader("Expires", "0");
		 */
		ObjectMapper objectmapeer = new ObjectMapper();
		Map<String, String> map = new HashMap();
		map.put("key", "success");

		return objectmapeer.writeValueAsString(map);

	}

	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String Delete(@RequestBody String delete, HttpServletRequest request, HttpServletResponse response)
			throws JsonParseException, JsonMappingException, IOException {
		// System.out.println("into delete");
		Long millisec;
		ObjectMapper objectmapper = new ObjectMapper();
		Map<String, String> map = new HashMap<>();
		Map<String, Object> maper = objectmapper.readValue(delete, new TypeReference<Map<String, Object>>() {
		});
		// System.out.println(delete);
		String deleteTime = (String) maper.get("deltime");

		SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");

		Date d;
		try {
			d = format.parse(deleteTime);

			millisec = d.getTime();
			// String dateString=milliseconds.toString();
			System.out.println(millisec);

			HttpSession session = request.getSession();
			PersistenceManager pm = PMF.get().getPersistenceManager();
			TimerJDO dataobj = new TimerJDO();
			List<TimerJDO> results11 = null;
			try {

				Query query = pm.newQuery(TimerJDO.class, ("addTime == time"));
				query.declareParameters("Long time");

				try {

					results11 = (List<TimerJDO>) query.execute(millisec);

					if (results11.isEmpty() || results11.equals(null)) {
						map.put("key1", "fail");
					} else {
						for (TimerJDO deleteObject : results11) {
							// System.out.println("dfgh");
							deleteObject.getIsDeleted();
							deleteObject.setIsDeleted(true);
							map.put("key", "success");
						}
					}
				} catch (Exception e) {
					System.out.println(e);
				}

			} finally {
					pm.close();
			}
		} catch (ParseException e1) {
			
			e1.printStackTrace();
		}
		return objectmapper.writeValueAsString(map);
	}

	@RequestMapping("/aftersigout")
	public ModelAndView after(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		UserJDO userJdoObject = (UserJDO) session.getAttribute("id");
		String email = userJdoObject.getemail();
		session.invalidate();
		return new ModelAndView("index");
	}

}

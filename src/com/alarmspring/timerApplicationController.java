package com.alarmspring;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tools.ant.taskdefs.condition.Http;
//import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.scheduling.quartz.LocalDataSourceJobStore;
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
		List<accessibleUsersInfo> adduserdata = null;
		accessibleUsersInfo addUserObject = new accessibleUsersInfo();
		addUserObject.setemailId(email);
		addUserObject.setpassword(password);
		Query q = pm.newQuery(UserJDO.class, ("email == email1 && password==password1"));
		q.declareParameters("String email1,String password1");

		try {
			results = (List<UserJDO>) q.execute(email, password);
			System.out.println("result : " + results);
			if (results.isEmpty() || results.equals(null)) {
				map.put("key1", "fail1");
				Query query = pm.newQuery(accessibleUsersInfo.class, ("emailId == email1 && password==password1"));
				query.declareParameters("String email1,String password1");
				adduserdata = (List<accessibleUsersInfo>) query.execute(email, password);
				if (adduserdata.isEmpty() || adduserdata.equals(null)) {
					map.put("key3", "fail2");
					System.out.println("new user data" + adduserdata);
					/* map.put("key1", "fail1"); */
					return objectmapper.writeValueAsString(map);

				} else {

					session.setAttribute("ids", adduserdata.get(0));
					session.setAttribute("name", adduserdata.get(0).getfirstName());
					map.put("key4", "success2");
					return objectmapper.writeValueAsString(map);
				}

			} else {

				session.setAttribute("id", results.get(0));
				session.setAttribute("name", results.get(0).getfirstName());
				map.put("key2", "success1");
				return objectmapper.writeValueAsString(map);

			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		/*
		 * finally { pm.close(); }
		 */

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
		PersistenceManager pm = PMF.get().getPersistenceManager();
		UserJDO registerobj = (UserJDO) session.getAttribute("id");
		List<String> listStrings = new ArrayList<String>();
		if (registerobj == null) {
			System.out.println(registerobj);
			accessibleUsersInfo addUserObject = (accessibleUsersInfo) session.getAttribute("ids");
			Query query1 = pm.newQuery(TimerJDO.class, ("Idvalues == id1"));
			query1.declareParameters("String id1");
			List<TimerJDO> adduserdata = null;
			Set<Long> ids = addUserObject.getId();
			adduserdata = (List<TimerJDO>) query1.execute(ids);
			if (adduserdata.isEmpty() || adduserdata.equals(null)) {

			} else {
				for (Object timer : adduserdata) {

					TimerJDO timers = (TimerJDO) timer;
					System.out.println(timers);

					DateFormat formatter = new SimpleDateFormat("hh:mm:ss");
					milli = timers.getaddTime();
					System.out.println(milli);
					String dateFormatted = formatter.format(milli);
					listStrings.add(dateFormatted);

				}
			}
		} else {
			Long Id = registerobj.getId();
			List results1 = null;

			try {

				Query query = pm.newQuery(TimerJDO.class, ("Idvalues == id1 && isDeleted == false"));
				query.declareParameters("String id1");
				try {

					results1 = (List<TimerJDO>) query.execute(Id);
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
		PersistenceManager pmf = PMF.get().getPersistenceManager();
		Date d;
		try {
			d = f.parse(addtime);

			milliseconds = d.getTime();
			// String dateString=milliseconds.toString();
			System.out.println(milliseconds);

			HttpSession session = request.getSession();
			UserJDO registerobj = (UserJDO) session.getAttribute("id");
			System.out.println(registerobj + "register object of user");
			if (registerobj == null) {
				System.out.println("register object not null");
				accessibleUsersInfo addUserObject = (accessibleUsersInfo) session.getAttribute("ids");
				TimerJDO date = new TimerJDO();
				String email = addUserObject.getemailId();
				Set<Long> id = addUserObject.getId();
				System.out.println(id + "id of new added user");
				date.setaddTime(milliseconds);
				date.getIdvalues().addAll(id);
				date.setEmail(email);

				List<TimerJDO> adduserdata = null;
				Query query = pmf.newQuery(TimerJDO.class, ("addTime == Time && Idvalues == id1"));
				query.declareParameters("Long Time,Long id1");
				adduserdata = (List<TimerJDO>) query.execute(milliseconds, id);

				if (adduserdata.isEmpty() || adduserdata.equals(null)) {
					pmf.makePersistent(date);
					map.put("key", "success");
				} else if (adduserdata.get(0).getIsDeleted().equals(true)) {
					adduserdata.get(0).setIsDeleted(false);
					pmf.makePersistent(adduserdata.get(0));
					map.put("key", "success");
				} else {
					map.put("key1", "fail");
					System.out.println("time already exists");
				}

			} else {
				Long ids = registerobj.getId();

				System.out.println(ids + "admin user ids");
				String email = registerobj.getemail();
				TimerJDO date = new TimerJDO();
				date.setaddTime(milliseconds);
				date.getIdvalues().add(ids);
				date.setEmail(email);

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

	/*
	 * @RequestMapping("/sendEmail") public String sendEmail(HttpServletRequest
	 * request, HttpServletResponse response) {
	 * 
	 * return "index"; }
	 */

	@RequestMapping("/register")
	public ModelAndView afterregister() {

		return new ModelAndView("success");
	}

	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping(value = "/register", method = RequestMethod.POST) public
	 * String register(HttpServletRequest request, HttpServletResponse response)
	 * throws IOException { System.out.println("into register"); ObjectMapper
	 * objectmaper = new ObjectMapper(); Map<String, String> map = new
	 * HashMap(); PersistenceManager pmf=PMF.get().getPersistenceManager();
	 * UserJDO userObject=new UserJDO();
	 * if(userObject.isPermitted().equals(false)){ System.out.println(
	 * "for new users"); map.put("key3", "newRegister"); return
	 * objectmaper.writeValueAsString(map); } else { System.out.println(
	 * "for permitted users"); userObject.getId(); map.put("key4",
	 * "permittedUsers"); return objectmaper.writeValueAsString(map); } }
	 */

	@ResponseBody
	@RequestMapping(value = "/addUsers", method = RequestMethod.POST)
	public String addUsers(@RequestBody String userdata, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		System.out.println("into userdata");
		ObjectMapper objectmaper = new ObjectMapper();
		Map<String, String> map = new HashMap<>();
		Map<String, Object> map1 = objectmaper.readValue(userdata, new TypeReference<Map<String, Object>>() {
		});
		HttpSession session = request.getSession();
		String firstName = (String) map1.get("firstName");
		String emailId1 = (String) map1.get("useremailId");
		String pass = (String) map1.get("pass");
		String lastName = (String) map1.get("lastName");
		System.out.println(emailId1 + "email id of user");
		UserJDO registerobj = (UserJDO) session.getAttribute("id");
		Long ids = registerobj.getId();
		accessibleUsersInfo addUserobject = new accessibleUsersInfo();
		addUserobject.getId().add(ids);
		addUserobject.setfirstName(firstName);
		addUserobject.setemailId(emailId1);
		addUserobject.setpassword(pass);
		addUserobject.setlastName(lastName);
		PersistenceManager pmf = PMF.get().getPersistenceManager();
		// pmf.makePersistent(date);
		List<accessibleUsersInfo> addUserObject = null;
		List<String> listStrings = new ArrayList<String>();
		try {

			Query query = pmf.newQuery(accessibleUsersInfo.class, ("emailId==email1"));
			query.declareParameters("String email1");

			try {

				addUserObject = (List<accessibleUsersInfo>) query.execute(emailId1);
				if (addUserObject.isEmpty() || addUserObject.equals(null)) {
					pmf.makePersistent(addUserobject);
					final String toEmail = emailId1;
					Properties props = new Properties();
					Session session1 = Session.getDefaultInstance(props, null);

					try {
						MimeMessage message = new MimeMessage(session1);
						System.out.println("Mail Check 1");
						message.setFrom(new InternetAddress("sravanthi.reddy@full.co", "sravanthi"));
						System.out.println("Mail Check 2");
						message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
						System.out.println("Mail Check 3");
						message.setSubject("Details of registers");
						System.out.println("Mail Check 4");
						message.setText("Your email id is:" + emailId1 + "password is:" + pass
								+ "http://10-dot-alarmwithspring.appspot.com/");
						System.out.println("Mail Check 5");
						Transport.send(message);
						System.out.println("Mail Check 5");
						map.put("key", "success");
					} catch (AddressException e) {
						// ...
						System.out.println(e);
					} catch (MessagingException e) {
						// ...
						System.out.println(e);
					} catch (UnsupportedEncodingException e) {
						// ...
						System.out.println(e);
					}

					// session.setAttribute("idvalues", addUserObject.get(0));
					// session.setAttribute("email",
					// addUserObject.get(0).getemailId());
					// session.setAttribute("password",
					// addUserObject.get(0).getpassword());

				} else {
					map.put("key1", "fail");
					System.out.println("unable to add user,already exists");
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		} finally {
			pmf.close();
		}

		return objectmaper.writeValueAsString(map);
	}

	@ResponseBody
	@RequestMapping(value = "/Signout", method = RequestMethod.POST)
	public String signOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("into signout");
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

package com.alarmspring;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
//import com.google.appengine.repackaged.com.google.gson.JsonObject;


@SuppressWarnings("serial")
@Controller
public class timerApplicationController {
	@RequestMapping("/")
	public String home() {
		return "index";
	}

	/*@RequestMapping(value = "/AuthenticationServlet", method = RequestMethod.POST)
	@ResponseBody
	public void Register(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
		//response.setContentType("text/plain");
	    //PrintWriter out = response.getWriter();
		String str=Util.getPostTimerJDO(request);
	       String str1=str.replace("null", "");
	       System.out.println(str1);
	       JSONObject jsonObject = new JSONObject(str1);
		String firstName = jsonObject.getString("firstname");
		String lastName = jsonObject.getString("lastname");
		String email = jsonObject.getString("email");
		String password = jsonObject.getString("password");
		HttpSession session = request.getSession();
		// session.setAttribute("useremail",email);
		//String password = request.getParameter("password");
		PersistenceManager pm = PMF.get().getPersistenceManager();
		UserJDO userJDOObject = new UserJDO();
		userJDOObject.setfirstName(firstName);
		userJDOObject.setlastName(lastName);
		userJDOObject.setemail(email);
		userJDOObject.setpassword(password);
		pm.makePersistent(userJDOObject);
		session.setAttribute("id", userJDOObject);

		try {

			Query dataquery = pm.newQuery(UserJDO.class);
			// dataquery.declareParameters("String email1");

			try {
				List<UserJDO> results = (List<UserJDO>) dataquery.execute();
				boolean valid = true;
				for (UserJDO obj : results) {
					if (obj.getemail().equals(email)) {
						
						System.out.println("email already exists");
						valid = false;
						break;
						
						// return new ModelAndView("index");

					}

				}
				if (valid) {
				    
					pm.makePersistent(userJDOObject);
					session.setAttribute("id", userJDOObject);
					//return new ModelAndView("success");
				}
			}
			
			catch (NullPointerException nullexception) {

			}

		} catch (Exception exception) {
			exception.printStackTrace();
			System.out.println("Exception occurs");

		} finally {

		}

		// ModelAndView modelObject=new ModelAndView("success");
		// return modelObject;
		
	}
	*/
	@ResponseBody
	@RequestMapping(value="/AuthenticationServlet",method=RequestMethod.POST)
	public String register(@RequestBody String userdata,HttpServletRequest request, HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException {
	System.out.println("into spring");
	ObjectMapper objectmapper = new ObjectMapper();
	
	Map<String, String> map = new HashMap<>();
	Map<String, Object> map1 = objectmapper.readValue(userdata, new TypeReference<Map<String, Object>>() {
	});
	System.out.println(userdata);
	String firstname = (String)map1.get("firstname");
	String lastname = (String) map1.get("lastname");
	String email = (String)map1.get("Email");
	String password = (String)map1.get("password");
	System.out.println(firstname);
	System.out.println(email);
	HttpSession session = request.getSession();
	UserJDO  userJDOObject= new UserJDO();
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
	//session.setAttribute("name", obj.get(0).getfirstName());
	
	} else {
	map.put("key1","fail");
	
	//out.println("you have already registerd ");
	}
	return objectmapper.writeValueAsString(map);
	
	}
	
	@ResponseBody
	@RequestMapping(value="/LoginServlet",method=RequestMethod.POST)
	public String login(@RequestBody String userinfo,HttpServletRequest request, HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException {
	System.out.println("into spring");
	ObjectMapper objectmapper = new ObjectMapper();
	Map<String, String> map = new HashMap<>();
	Map<String, Object> maper = objectmapper.readValue(userinfo, new TypeReference<Map<String, Object>>() {
	});
	System.out.println(userinfo);
	String email = (String)maper.get("email");
	String password = (String)maper.get("password");
	HttpSession session = request.getSession();
	session.setAttribute("email", email);
	PersistenceManager pm = PMF.get().getPersistenceManager();
	UserJDO  loginObject= new UserJDO();
	loginObject.setemail(email);
	loginObject.setpassword(password);
	List<UserJDO> results = null;
	try {

		Query q = pm.newQuery(UserJDO.class, ("email == email1 && password==password1"));
		q.declareParameters("String email1,,String password1");

		
			try {
				results = (List<UserJDO>) q.execute(email, password);
				if (results.isEmpty() || results.equals(null)) {
					map.put("key","success");

				} else {
					
					session.setAttribute("id", results.get(0));
					session.setAttribute("name", results.get(0).getfirstName());
					//System.out.println(name);
					map.put("key1", "fail");
					//map.put("name",name);
				}
			}
	
				catch(NullPointerException e)
				{
					e.printStackTrace();
				}
	}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		
	
				finally {
					pm.close();
				}
	
	return objectmapper.writeValueAsString(map);
	}
	
	
	/*@RequestMapping(value = "/LoginServlet",method = RequestMethod.POST)
	@ResponseBody
	public void Login(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
		//response.setContentType("text/plain");
	    System.out.println("into spring");
		//PrintWriter out = response.getWriter();
        String str=Util.getPostTimerJDO(request);
       String str1=str.replace("null", "");
       System.out.println(str1);
       JSONObject jsonObject = new JSONObject(str1);
       //String username=jsonObject.getString("username");
       String email = jsonObject.getString("email");
       String password = jsonObject.getString("password");

		//String email = request.getParameter("email");
		HttpSession session = request.getSession();
		//String password = request.getParameter("password");
		PersistenceManager pm = PMF.get().getPersistenceManager();
		UserJDO userJDOObject = new UserJDO();
		userJDOObject.setemail(email);
		userJDOObject.setpassword(password);
		List<UserJDO> results = null;
		try {

			Query q = pm.newQuery(UserJDO.class, ("email == email1 && password==password1"));
			q.declareParameters("String email1,,String password1");

			try {
				try {
					results = (List<UserJDO>) q.execute(email, password);
					if (results.isEmpty() || results.equals(null)) {

						// out.println("please enter registered mail... and
						// password....");
						//return new ModelAndView("index");

					} else {
						
						session.setAttribute("id", results.get(0));
						results.get(0).getfirstName();
					
						//return new ModelAndView("success");
						
						 * HttpSession session = request.getSession();
						 * 
						 * session.setAttribute("email",
						 * results.get(0).getemail());
						 * session.setAttribute("name",
						 * results.get(0).getfirstName());
						 * System.out.print(results.get(0).getfirstName());
						 * System.out.print(results.get(0).getemail());
						 
					}
				} catch (NullPointerException e) {
					e.printStackTrace();
				}
			} 
		
		 catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception occurs");

		}
		}
			finally {

			}
		}

		
	
	*/
	
	@ResponseBody @RequestMapping(value = "/ListOfTimersOfUser", method = RequestMethod.POST)
	public String displayListOfTimersOfUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//PrintWriter out = response.getWriter();
	//	String email = request.getParameter("email");
		HttpSession session = request.getSession();
		HashMap<String, Object> responseMap = new HashMap<>();
		
		UserJDO registerobj = (UserJDO) session.getAttribute("id");
		Long Id = registerobj.getId();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<TimerJDO> results1 = null;
		List<String> listStrings = new ArrayList<String>();
		try {

			Query query = pm.newQuery(TimerJDO.class, ("Idvalues == id1 && isDeleted == false"));
			query.declareParameters("String id1");
			

			try {

				results1 = (List<TimerJDO>) query.execute(Id);
				
				//System.out.println("dfdf");
				if (results1.isEmpty() || results1.equals(null)) {
				} else {
					for (TimerJDO timers : results1) {
					//out.println(d.getaddTime());
					//response.getWriter().write("dfgh");
					listStrings.add(timers.getaddTime());

					}
					//System.out.println(((TimerJDO) results1).getaddTime());

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
	public String addTime(@RequestBody String timedata, HttpServletRequest request, HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException {

		//PrintWriter out = response.getWriter();
		// String giveTime = request.getParameter("giveTime");
        System.out.println("timer method");
        System.out.println(timedata);
        ObjectMapper objectmapper = new ObjectMapper();
      
      Map<String, String> map = new HashMap<>();
      Map<String, Object> map1 = objectmapper.readValue(timedata, new TypeReference<Map<String, Object>>() {
      });
	
		String addtime = (String) map1.get("giveTime");
		System.out.println("entered time in spring"+addtime);
		HttpSession session = request.getSession();
		UserJDO registerobj = (UserJDO) session.getAttribute("id");
		Long ids = registerobj.getId();
		System.out.println(ids);
		String email = registerobj.getemail();
		TimerJDO date = new TimerJDO();
		date.setaddTime(addtime);
		date.getIdvalues().add(ids);
		date.setEmail(email);
		
		PersistenceManager pmf= PMF.get().getPersistenceManager();
		//pmf.makePersistent(date);
				List<TimerJDO> results1 = null;
				List<String> listStrings = new ArrayList<String>();
				try {

				Query query = pmf.newQuery(TimerJDO.class, ("addTime == Time && Idvalues == id1"));
				query.declareParameters("String Time,String id1");

				try {

				results1 = (List<TimerJDO>) query.execute(addtime,ids);
				if (results1.isEmpty() || results1.equals(null)) {
					
					
				pmf.makePersistent(date);
				map.put("key", "success");
					}
				else if(results1.get(0).getIsDeleted().equals(true))
					{
						results1.get(0).setIsDeleted(false);
						pmf.makePersistent(results1.get(0));
						map.put("key", "success");
					}
				else
				{			
				map.put("key1", "fail");
				System.out.println("time already exists");
				}
						
		}
				catch(Exception e)
				{
				System.out.println(e);
				}
				}
				finally
				{
					pmf.close();
				}
				
				return objectmapper.writeValueAsString(map);
		      }
			
			
      
     /*@RequestMapping(value = "/Timer", method = RequestMethod.POST)
public void addTime(HttpServletRequest request, HttpServletResponse response)
throws Exception {

PrintWriter out=response.getWriter();
//String giveTime = request.getParameter("giveTime");

String str=Util.getPostTimerJDO(request);
String str1=str.replace("null", "");
JSONObject data = new JSONObject(str1);
System.out.println(data);
String time = data.getString("giveTime");
System.out.println(time);
HttpSession session=request.getSession();
UserJDO registerobj=(UserJDO) session.getAttribute("id");
Long ids=registerobj.getId();
System.out.println(ids);
String email=registerobj.getemail();
TimerJDO date = new TimerJDO();
date.setaddTime(time);
date.getIdvalues().add(ids);
date.setEmail(email);
PersistenceManager pmf= PMF.get().getPersistenceManager();
//pmf.makePersistent(date);
		List<TimerJDO> results1 = null;
		List<String> listStrings = new ArrayList<String>();
		try {

		Query query = pmf.newQuery(TimerJDO.class, ("addTime == Time && Idvalues == id1 "));
		query.declareParameters("String Time,String id1");

		try {

		results1 = (List<TimerJDO>) query.execute(time,ids);
		if (results1.isEmpty() || results1.equals(null)) {
		pmf.makePersistent(date);
		} else {
		for (TimerJDO d : results1)
		{
		System.out.println(d);
		//pmf.makePersistent(date);	
		}
		System.out.println("time already exists");
		//return "null";

		}
}
		catch(Exception e)
		{
		System.out.println(e);
		}
		}
		finally
		{
			pmf.close();
		}
		
      }*/
	
	
	

	@RequestMapping("/afterlogin")
	public String afterLogin()
	{
		return "success";
	}
	@RequestMapping("/register")
	public ModelAndView afterregister(){
		return new ModelAndView("success");
	}
	/*@RequestMapping("/Signout")
	public String signOut(HttpServletRequest req) throws JSONException{
	   String sss = Util.getPostTimerJDO(req);
	   JSONObject json = new JSONObject(sss);
	   //System.out.println(str);
	   String email = json.getString("email");  
	   System.out.println(email);
	   
	   return "index";
	}*/
	
	
	@ResponseBody
	@RequestMapping(value="/Signout",method=RequestMethod.POST)
	public String signOut(HttpServletRequest request, HttpServletResponse response) throws  IOException {
	System.out.println("into signout");
	/*response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");*/
	ObjectMapper objectmapeer = new ObjectMapper();
	Map<String,String> map = new HashMap();	
	map.put("key","success");
	
	return  objectmapeer.writeValueAsString(map);

}
	@RequestMapping("/aftersigout")
	public ModelAndView after(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		UserJDO userJdoObject=(UserJDO) session.getAttribute("id");
		String email=userJdoObject.getemail();
		session.invalidate();
		return new ModelAndView("index"); 
	}

	/*@RequestMapping("/aftersignout")
	public String after()
	{
		return "index";
	}*/
	
	
	@ResponseBody
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public String Delete(@RequestBody String delete,HttpServletRequest request, HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException {
	System.out.println("into spring");
	ObjectMapper objectmapper = new ObjectMapper();
	Map<String, String> map = new HashMap<>();
	Map<String, Object> maper = objectmapper.readValue(delete, new TypeReference<Map<String, Object>>() {
	});
	System.out.println(delete);
	String deleteTime = (String)maper.get("deltime");
	HttpSession session = request.getSession();
	PersistenceManager pm = PMF.get().getPersistenceManager();
	TimerJDO dataobj=new TimerJDO();
	List<TimerJDO> results11 = null;
	try {

		Query query = pm.newQuery(TimerJDO.class, ("addTime == time"));
		query.declareParameters("String time");
		

		try {

			results11 = (List<TimerJDO>) query.execute(deleteTime);
		
			if (results11.isEmpty() || results11.equals(null)) {
				map.put("key1", "fail");
			} else {
				for(TimerJDO deleteObject: results11){
				deleteObject.getIsDeleted();
				deleteObject.setIsDeleted(true);
				map.put("key","success");
					}
			}
		}catch(Exception e){
				System.out.println(e);
			}
				
			
	}finally{
		
	}
	return  objectmapper.writeValueAsString(map);
	}
	
}
	

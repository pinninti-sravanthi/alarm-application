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
public class AuthenticationServlet1 {
	@RequestMapping("/")
	public String home() {
		return "index";
	}

	@RequestMapping(value = "/AuthenticationServlet", method = RequestMethod.POST)
	@ResponseBody
	public void Register(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
		//response.setContentType("text/plain");
	    //PrintWriter out = response.getWriter();
		String str=Util.getPostData(request);
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
		AuthenticationJDO authenticationJdoObject = new AuthenticationJDO();
		authenticationJdoObject.setfirstName(firstName);
		authenticationJdoObject.setlastName(lastName);
		authenticationJdoObject.setemail(email);
		authenticationJdoObject.setpassword(password);
		pm.makePersistent(authenticationJdoObject);
		session.setAttribute("id", authenticationJdoObject);

		try {

			Query dataquery = pm.newQuery(AuthenticationJDO.class);
			// dataquery.declareParameters("String email1");

			try {
				List<AuthenticationJDO> results = (List<AuthenticationJDO>) dataquery.execute();
				boolean valid = true;
				for (AuthenticationJDO obj : results) {
					if (obj.getemail().equals(email)) {
						//out.println("already exists");
						System.out.println("email already exists");
						valid = false;
						break;
						// return new ModelAndView("index");

					}

				}
				if (valid) {
				    
					pm.makePersistent(authenticationJdoObject);
					session.setAttribute("id", authenticationJdoObject);
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
	
	/*@ResponseBody
	@RequestMapping(value="/AuthenticationServlet",method=RequestMethod.POST)
	public String User(@RequestBody String userdata,HttpServletRequest request, HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException {
	System.out.println("into spring");
	ObjectMapper objectmapper = new ObjectMapper();
	
	Map<String, String> map = new HashMap<>();
	Map<String, Object> map1 = objectmapper.readValue(userdata, new TypeReference<Map<String, Object>>() {
	});
	System.out.println(userdata);
	String firstname = (String)map1.get("firstname");
	String lastname = (String) map1.get("lastname");
	String email = (String)map1.get("email");
	String password = (String)map1.get("password");
	System.out.println(firstname);
	HttpSession session = request.getSession();
	AuthenticationJDO authenticationJdoObject = new AuthenticationJDO();
	authenticationJdoObject.setfirstName(firstname);
	authenticationJdoObject.setlastName(lastname);
	authenticationJdoObject.setemail(email);
	authenticationJdoObject.setpassword(password);
	PersistenceManager pmf = PMF.get().getPersistenceManager();
	// pmf.makePersistent(pojo);
	if(username.equals("akhil")&&email.equals("akhil@gmail.com")&&password.equals("akhil"))
	{
	map.put("key", "success");
	//System.out.println(objectmapper.writeValueAsString(map));


	}
	else
	{
	map.put("fail","fail");
	//return objectmapper.writeValueAsString(map);
	}
	javax.jdo.Query q = pmf.newQuery(AuthenticationJDO.class, ("email==email1"));
	q.declareParameters("String email1");
	List<AuthenticationJDO> obj = (List<AuthenticationJDO>) q.execute(email);
	if (obj.isEmpty()) {
	pmf.makePersistent(authenticationJdoObject);
	map.put("key", "success");
	session.setAttribute("id", authenticationJdoObject);
	} else {
	map.put("key1","fail");
	//out.println("you have already registerd ");
	}
	return objectmapper.writeValueAsString(map);
	
	}
	
	*/
	@RequestMapping(value = "/LoginServlet",method = RequestMethod.POST)
	@ResponseBody
	public void Login(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
		//response.setContentType("text/plain");
	    System.out.println("into spring");
		//PrintWriter out = response.getWriter();
        String str=Util.getPostData(request);
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
		AuthenticationJDO authenticationJdoObject = new AuthenticationJDO();
		authenticationJdoObject.setemail(email);
		authenticationJdoObject.setpassword(password);
		List<AuthenticationJDO> results = null;
		try {

			Query q = pm.newQuery(AuthenticationJDO.class, ("email == email1 && password==password1"));
			q.declareParameters("String email1,,String password1");

			try {
				try {
					results = (List<AuthenticationJDO>) q.execute(email, password);
					if (results.isEmpty() || results.equals(null)) {

						// out.println("please enter registered mail... and
						// password....");
						//return new ModelAndView("index");

					} else {
						// out.println("welcome " + email);
						// resp.sendRedirect("success.jsp");

						/*List<Data> results1 = null;
						try {

							Query query = pm.newQuery(Data.class, ("email == email1"));
							q.declareParameters("String email1");

							try {

								results1 = (List<Data>) q.execute(email);
								System.out.println("dfdf");
								if (results.isEmpty() || results.equals(null)) {
								} else {
									for (Data d : results1) {
										System.out.println(d.getaddTime());

									}

								}
							} catch (Exception e) {
								e.printStackTrace();
							}

						} finally {

						}*/

						session.setAttribute("id", results.get(0));
						//return new ModelAndView("success");
						/*
						 * HttpSession session = request.getSession();
						 * 
						 * session.setAttribute("email",
						 * results.get(0).getemail());
						 * session.setAttribute("name",
						 * results.get(0).getfirstName());
						 * System.out.print(results.get(0).getfirstName());
						 * System.out.print(results.get(0).getemail());
						 */
					}
				} catch (NullPointerException e) {

				}
			} finally {

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception occurs");

		}

		
	}
	
	
	@ResponseBody @RequestMapping(value = "/ListOfTimersOfUser", method = RequestMethod.POST)
	public String displayListOfTimersOfUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//PrintWriter out = response.getWriter();
	//	String email = request.getParameter("email");
		HttpSession session = request.getSession();
		HashMap<String, Object> responseMap = new HashMap<>();
		
		AuthenticationJDO registerobj = (AuthenticationJDO) session.getAttribute("id");
		Long Id = registerobj.getId();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<Data> results1 = null;
		List<String> listStrings = new ArrayList<String>();
		try {

			Query query = pm.newQuery(Data.class, ("Idvalues == id1"));
			query.declareParameters("String id1");
			

			try {

				results1 = (List<Data>) query.execute(Id);
				
				//System.out.println("dfdf");
				if (results1.isEmpty() || results1.equals(null)) {
				} else {
					for (Data d : results1) {
					//out.println(d.getaddTime());
					//response.getWriter().write("dfgh");
					listStrings.add(d.getaddTime());

					}
					//System.out.println(((Data) results1).getaddTime());

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
    /*@ResponseBody
	@RequestMapping(value = "/Timer", method = RequestMethod.POST)
	public String addTime(@RequestBody String timedata, HttpServletRequest request, HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException {

		//PrintWriter out = response.getWriter();
		// String giveTime = request.getParameter("giveTime");
        System.out.println("timer method");
        System.out.println(timedata);
        ObjectMapper objectmapper = new ObjectMapper();
      //objectmapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
      Map<String, String> map = new HashMap<>();
      Map<String, Object> map1 = objectmapper.readValue(timedata, new TypeReference<Map<String, Object>>() {
      });
	
		String addtime = (String) map1.get("givenTime");
		
		
		//JSONObject data = new JSONObject();
		//System.out.println(data);
		//String time = data.getString("giveTime");
		System.out.println("entered time in spring"+addtime);
		HttpSession session = request.getSession();
		AuthenticationJDO registerobj = (AuthenticationJDO) session.getAttribute("id");
		Long ids = registerobj.getId();
		System.out.println(ids);
		String email = registerobj.getemail();
		Data date = new Data();
		date.setaddTime(addtime);
		date.getIdvalues().add(ids);
		date.setEmail(email);
		PersistenceManager pmf = PMF.get().getPersistenceManager();

		pmf.makePersistent(date);
		
       session.setAttribute("timerId", date);
      return timedata;
    */
      
      @RequestMapping(value = "/Timer", method = RequestMethod.POST)
public void addTime(HttpServletRequest request, HttpServletResponse response)
throws Exception {

PrintWriter out=response.getWriter();
//String giveTime = request.getParameter("giveTime");

String str=Util.getPostData(request);
String str1=str.replace("null", "");
JSONObject data = new JSONObject(str1);
System.out.println(data);
String time = data.getString("giveTime");
System.out.println(time);
HttpSession session=request.getSession();
AuthenticationJDO registerobj=(AuthenticationJDO) session.getAttribute("id");
Long ids=registerobj.getId();
System.out.println(ids);
String email=registerobj.getemail();
Data date = new Data();
date.setaddTime(time);
date.getIdvalues().add(ids);
date.setEmail(email);
PersistenceManager pmf= PMF.get().getPersistenceManager();
pmf.makePersistent(date);





}
      
		
		
		
		/*List<Data> results1 = null;
		List<String> listStrings = new ArrayList<String>();
		try {

		Query query = pmf.newQuery(Data.class, ("addTime == Time && Idvalues == id1 "));
		query.declareParameters("String Time,String id1");

		try {

		results1 = (List<Data>) query.execute(time,ids);
		if (results1.isEmpty() || results1.equals(null)) {
		pmf.makePersistent(date);
		} else {
		for (Data d : results1)
		{
		System.out.println(d);
		pmf.makePersistent(date);	
		}
		System.out.println("time already exists");
		return;

		}



		}
		catch(Exception e)
		{
		System.out.println(e);
		}
		}
		finally
		{

		}
		
		*/

	@RequestMapping("/afterlogin")
	public String afterLogin()
	{
		return "success";
	}
	@RequestMapping("/Signout")
	public String signOut(HttpServletRequest req) throws JSONException{
	   String sss = Util.getPostData(req);
	   JSONObject json = new JSONObject(sss);
	   //System.out.println(str);
	   String email = json.getString("email");
	   System.out.println(email);
	   return "index";
	}
	@RequestMapping("/aftersignout")
	public String after()
	{
		return "index";
	}
	/*@RequestMapping(value="/delete",method=RequestMethod.POST)
	public void Delete(HttpServletRequest req, HttpServletResponse res) throws JSONException{
    System.out.println("in delete function");
	String str = Util.getPostData(req);
	String str1 = str.replace("null", "");
	//JSONObject jsonobject = new JSONObject(str1);
	JSONObject jsonObject = new JSONObject(str1);
	String deltime = jsonObject.getString("deltime");
	System.out.println(deltime+"pkj");
	HttpSession session = req.getSession();
	PersistenceManager pm = PMF.get().getPersistenceManager();
	
	//AuthenticationJDO registerobj1 = (AuthenticationJDO) session.getAttribute("timerId");
	Data dataobj=new Data();
	List<Data> results11 = null;
	try {

		Query query = pm.newQuery(Data.class, ("addTime == time"));
		query.declareParameters("String time");
		

		try {

			results11 = (List<Data>) query.execute(deltime);
		
			if (results11.isEmpty() || results11.equals(null)) {
			} else {
				for(Data dd: results11){
				//signOut(req)
					System.out.println(dd.getaddTime());
				dd.getaddTime();
				dd.setaddTime("");

					}
			}
		}catch(Exception e){
				System.out.println(e);
			}
				
			
	}finally{
		
	}
		}*/
}
	

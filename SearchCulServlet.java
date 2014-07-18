package cn.itcast.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.thread.MongoDb;
import cn.itcast.util.TCulturalHeritage;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;



public class SearchCulServlet extends HttpServlet {

		
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try{			
			request.setCharacterEncoding("UTF-8");
						
			String collectionName = request.getParameter("collection");
			String category = request.getParameter("category");
			String name = request.getParameter("name");				
			
			MongoDb mo = new MongoDb("CHeritages");			
					 
			DBCollection coll = mo.getColl(collectionName);
			
			System.out.println(collectionName);
					
			DBObject ref = new BasicDBObject();	
			//ref.put("category", "tulou");
			
			ref.put("category", category);
			if(name.length()!=0){				
				
				//ref.put("name", "zhenchenglou19");
				ref.put("name", name);
			}
			
			
			System.out.println(ref);
			
			DBObject keys = new BasicDBObject();
			keys.put("_id", false);
			keys.put("name", true);
			keys.put("category", true);
			keys.put("info", true);
			keys.put("picture", true);
			
			System.out.println(keys);
			
			long time1 = System.currentTimeMillis();
						
			DBCursor cur = coll.find(ref, keys);
			
			long time2 = System.currentTimeMillis();
			
			System.out.println("time="+(time2-time1));
						
			List<TCulturalHeritage> list = new ArrayList<TCulturalHeritage>();
			
			while (cur.hasNext()) {
				
			    DBObject object = cur.next();
			    System.out.println(object.get("name"));
			    
			    TCulturalHeritage tch = new TCulturalHeritage();
			    
			    tch.setName((String) object.get("name"));
			    tch.setCategory((String) object.get("category"));
			    tch.setInfo((String) object.get("info"));
			    tch.setScenicSpot((String) object.get("scenicSpot"));				
			    			    
				list.add(tch);
			}
			
			request.setAttribute("list", list);
			
			request.getRequestDispatcher("/result.jsp").forward(request, response);
			
			mo.closeOne();
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "≤È—Ø ß∞‹£°£°£°");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			
		}

	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
	
	

}

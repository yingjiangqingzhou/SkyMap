package cn.itcast.catalog;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.thread.MongoDb;
import cn.itcast.thread.TCHeritage;

public class Catalog1 extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		
		out.write("物质文化遗产： <br/>");
		
		//1、输出所有物质文化遗产目录（拿到数据库，拿到collections列表）
		MongoDb mo = new MongoDb("TCHeritages");
		
		Map<String, String> map = mo.getCollectionNames();
		
		for(Map.Entry<String, String> entry : map.entrySet()){
			
			//out.write("<a href=‘/WebWeb/servlet/Catalog2?id=’"+entry.getValue()+"' target='_blank' >"+entry.getValue()+"</a><br/>");
			out.write("<a href=/WebWeb/servlet/Catalog2?id="+entry.getKey()+">"+entry.getValue()+"</a><br/>");
		}		
		
		//2、显示用户曾经浏览的内容		
//		out.print("<br/>浏览过的内容<br/>");
//		Cookie cookies[] = request.getCookies();
//		for(int i= 0;cookies!=null&&i<=cookies.length;i++ )
//		{
//			out.print("<br/>先空着<br/>");
//			if(cookies[i].getName().equals("TCHeritagesCatalog")){
//				String ids[] = cookies[i].getValue().split("\\,");
//				for(String id:ids){
//					String coll = map.get(id);
//					out.print(coll+"<br/>");
//				}
//			}
//		}
		
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}

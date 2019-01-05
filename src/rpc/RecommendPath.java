package rpc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import entity.Day;
import entity.POI;
import entity.User;

import recommendation.ViewRecommendation;

/**
 * Servlet implementation class RecommendPath
 */
@WebServlet("/path")
public class RecommendPath extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RecommendPath() {
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//take input 
		try {
			JSONObject input = RpcHelper.readJSONObject(request);
			JSONObject userInfo = input.getJSONObject("userInfo");
			JSONArray array = input.getJSONArray("pointsOfInterests");
			ViewRecommendation vr = new ViewRecommendation();
			User user = new User(userInfo);
			List<POI> allPOIs = vr.getFromAndroid(array) ;
			
		//generate a list with our algorightm
			List<Day> recommondation = vr.generatePath(user, allPOIs);
			
		//return it to front-end
			JSONObject allRoute = new JSONObject(); 
			for(int i = 0; i < recommondation.size(); i++) {
				allRoute.put(String.valueOf(i), recommondation.get(i).toJSONArray());
			}
			RpcHelper.writeJsonObject(response, allRoute);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

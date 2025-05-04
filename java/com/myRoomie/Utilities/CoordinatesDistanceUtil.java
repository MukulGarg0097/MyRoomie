package com.myRoomie.Utilities;

public class CoordinatesDistanceUtil {

    /*obj.distance(28.459497, 77.026634, 28.704060, 77.102493);*/
	public static double distance(double lat1, double lon1, double lat2, double lon2) {
		
		try {
			double theta = lon1 - lon2;
			double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) +
		        		        Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
			dist = Math.acos(dist);
			dist = rad2deg(dist);
			dist = dist * 60 * 1.1515;
			dist = dist * 1.609344;
			return (dist);
		} catch (Exception e) {
		}
		return 0.0;
	}
	public static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}
	public static double rad2deg(double rad) {
		return (rad * 180.0 / Math.PI);
	}
}

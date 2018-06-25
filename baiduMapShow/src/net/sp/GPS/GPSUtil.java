package net.sp.GPS;


/*
 * ��������ϵ����ת���������볤�ȡ�
 */
public class GPSUtil {  
    public static double pi = 3.1415926535897932384626;  
    public static double x_pi = 3.14159265358979324 * 3000.0 / 180.0;  
    public static double a = 6378245.0;  
    public static double ee = 0.00669342162296594323;  
    private static final Double PI = Math.PI;  
	  
    private static final Double PK = 180 / PI; 
    
    public static double transformLat(double x, double y) {  
        double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y  
                + 0.2 * Math.sqrt(Math.abs(x));  
        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;  
        ret += (20.0 * Math.sin(y * pi) + 40.0 * Math.sin(y / 3.0 * pi)) * 2.0 / 3.0;  
        ret += (160.0 * Math.sin(y / 12.0 * pi) + 320 * Math.sin(y * pi / 30.0)) * 2.0 / 3.0;  
        return ret;  
    }  
  
    public static double transformLon(double x, double y) {  
        double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1  
                * Math.sqrt(Math.abs(x));  
        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;  
        ret += (20.0 * Math.sin(x * pi) + 40.0 * Math.sin(x / 3.0 * pi)) * 2.0 / 3.0;  
        ret += (150.0 * Math.sin(x / 12.0 * pi) + 300.0 * Math.sin(x / 30.0  
                * pi)) * 2.0 / 3.0;  
        return ret;  
    }  
    public static double[] transform(double lat, double lon) {  
        if (outOfChina(lat, lon)) {  
            return new double[]{lat,lon};  
        }  
        double dLat = transformLat(lon - 105.0, lat - 35.0);  
        double dLon = transformLon(lon - 105.0, lat - 35.0);  
        double radLat = lat / 180.0 * pi;  
        double magic = Math.sin(radLat);  
        magic = 1 - ee * magic * magic;  
        double sqrtMagic = Math.sqrt(magic);  
        dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);  
        dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);  
        double mgLat = lat + dLat;  
        double mgLon = lon + dLon;  
        return new double[]{mgLat,mgLon};  
    }  
    public static boolean outOfChina(double lat, double lon) {  
        if (lon < 72.004 || lon > 137.8347)  
            return true;  
        if (lat < 0.8293 || lat > 55.8271)  
            return true;  
        return false;  
    }  
    /** 
     * 84 to ��������ϵ (GCJ-02) World Geodetic System ==> Mars Geodetic System 
     * 
     * @param lat 
     * @param lon 
     * @return 
     */  
    public static double[] gps84_To_Gcj02(double lat, double lon) {  
        if (outOfChina(lat, lon)) {  
            return new double[]{lat,lon};  
        }  
        double dLat = transformLat(lon - 105.0, lat - 35.0);  
        double dLon = transformLon(lon - 105.0, lat - 35.0);  
        double radLat = lat / 180.0 * pi;  
        double magic = Math.sin(radLat);  
        magic = 1 - ee * magic * magic;  
        double sqrtMagic = Math.sqrt(magic);  
        dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);  
        dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);  
        double mgLat = lat + dLat;  
        double mgLon = lon + dLon;  
        return new double[]{mgLat, mgLon};  
    }  
  
    /** 
     * * ��������ϵ (GCJ-02) to 84 * * @param lon * @param lat * @return 
     * */  
    public static double[] gcj02_To_Gps84(double lat, double lon) {  
        double[] gps = transform(lat, lon);  
        double lontitude = lon * 2 - gps[1];  
        double latitude = lat * 2 - gps[0];  
        return new double[]{latitude, lontitude};  
    }  
    /** 
     * ��������ϵ (GCJ-02) ��ٶ�����ϵ (BD-09) ��ת���㷨 �� GCJ-02 ����ת���� BD-09 ���� 
     * 
     * @param lat 
     * @param lon 
     */  
    public static double[] gcj02_To_Bd09(double lat, double lon) {  
        double x = lon, y = lat;  
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);  
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi);  
        double tempLon = z * Math.cos(theta) + 0.0065;  
        double tempLat = z * Math.sin(theta) + 0.006;  
        double[] gps = {tempLat,tempLon};  
        return gps;  
    }  
  
    /** 
     * * ��������ϵ (GCJ-02) ��ٶ�����ϵ (BD-09) ��ת���㷨 * * �� BD-09 ����ת����GCJ-02 ���� * * @param 
     * bd_lat * @param bd_lon * @return 
     */  
    public static double[] bd09_To_Gcj02(double lat, double lon) {  
        double x = lon - 0.0065, y = lat - 0.006;  
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);  
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);  
        double tempLon = z * Math.cos(theta);  
        double tempLat = z * Math.sin(theta);  
        double[] gps = {tempLat,tempLon};  
        return gps;  
    }  
  
    /**��gps84תΪbd09 
     * @param lat 
     * @param lon 
     * @return 
     */  
    public static double[] gps84_To_bd09(double lat,double lon){  
        double[] gcj02 = gps84_To_Gcj02(lat,lon);  
        double[] bd09 = gcj02_To_Bd09(gcj02[0],gcj02[1]);  
        return bd09;  
    }  
    public static double[] bd09_To_gps84(double lat,double lon){  
        double[] gcj02 = bd09_To_Gcj02(lat, lon);  
        double[] gps84 = gcj02_To_Gps84(gcj02[0], gcj02[1]);  
        //����С�������λ  
        gps84[0] = retain6(gps84[0]);  
        gps84[1] = retain6(gps84[1]);  
        return gps84;  
    }  
  
    /**����С�������λ 
     * @param num 
     * @return 
     */  
    private static double retain6(double num){  
        String result = String .format("%.6f", num);  
        return Double.valueOf(result);  
    }
    
    public static double getDistanceFromTwoPoints(double lat_a, double lng_a, double lat_b, double lng_b) {  
        double t1 = Math.cos(lat_a / PK) * Math.cos(lng_a / PK) * Math.cos(lat_b / PK) * Math.cos(lng_b / PK);  
        double t2 = Math.cos(lat_a / PK) * Math.sin(lng_a / PK) * Math.cos(lat_b / PK) * Math.sin(lng_b / PK);  
        double t3 = Math.sin(lat_a / PK) * Math.sin(lat_b / PK);  
  
        double tt = Math.acos(t1 + t2 + t3);  
  
        System.out.println("�����ľ��룺" + 6366000 * tt + " ��");  
        return 6371000 * tt;  
    }
  
}  
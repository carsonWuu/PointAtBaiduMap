package net.sp.programme.data;

public class mapPosition {
	/*
	 * 1.zoom层级
	 * 2.中心点经度
	 * 3.中心点纬度
	 * 
	 * 4.5左下角经纬度
	 * 
	 * 6.7右上角经纬度
	 */
	
//	public static int num=200;//默认显示数量
	
	public int zoom;
	public double index_x;
	public double index_y;
	
	public double bounds_leftDown_x;
	public double bounds_leftDown_y;
	
	public double bounds_rightTop_x;
	public double bounds_rightTop_y;
	
	@Override
	public String toString(){
		return zoom+"\n"+index_x+"\t"+index_y+"\n"+bounds_leftDown_x+"\t"+bounds_leftDown_y+"\n"+bounds_rightTop_x+"\t"+bounds_rightTop_y;
	}
}

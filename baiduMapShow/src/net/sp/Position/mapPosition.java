package net.sp.Position;

/**客户端请求转换为对象
 * 
 * 1.zoom
 * 2.3当前经纬度
 * 
 * 4.5左下角经纬度
 * 
 * 6.7右上角经纬度
 * 8.展示数据个数
 */
public class mapPosition {
	
	
	public int zoom;
	public double index_x;
	public double index_y;
	
	public double bounds_leftDown_x;
	public double bounds_leftDown_y;
	
	public double bounds_rightTop_x;
	public double bounds_rightTop_y;
	
	public int number;
	@Override
	public String toString(){
		return zoom+"\n"+index_x+"\t"+index_y+"\n"+bounds_leftDown_x+"\t"+bounds_leftDown_y+"\n"+bounds_rightTop_x+"\t"+bounds_rightTop_y;
	}
}

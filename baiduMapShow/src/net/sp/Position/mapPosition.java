package net.sp.Position;

public class mapPosition {
	/*�û�������Ϣ��
	 * 
	 * 1.zoom�㼶
	 * 2.���ĵ㾭��
	 * 3.���ĵ�γ��
	 * 
	 * 4.5���½Ǿ�γ��
	 * 
	 * 6.7���ϽǾ�γ��
	 * 8.�û�����չʾ����
	 */
	
//	public static int num=200;//Ĭ����ʾ����
	
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
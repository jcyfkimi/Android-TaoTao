package tk.jcyfkimi.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import tk.jcyfkimi.DAO.GoodsDAO;
import tk.jcyfkimi.beans.Goods;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
/**
 * ���մ����Ĳ�����������Ӧ������Ȼ�󷵻�json��ʽ�����ݻ�ͻ��� 
 * @author Administrator
 *
 */
public class GoodsAction extends ActionSupport implements ServletRequestAware{

	private HttpServletRequest request;
	private List<Goods> glist = null;
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		GoodsDAO goodsDAO = new GoodsDAO();
		glist = goodsDAO.getAllGoods();
		for (Goods goods : glist) {
			System.out.println(goods);
		}
		return Action.SUCCESS;
	}
	public List<Goods> getGlist() {
		return glist;
	}
	public void setGlist(List<Goods> glist) {
		this.glist = glist;
	}
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
		
	}
	
	
}

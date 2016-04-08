package hun.hbase.admin.controller;

import java.util.ArrayList;
import java.util.List;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import hun.hbase.admin.dao.HbaseDao;
import hun.hbase.admin.model.Address;
import hun.hbase.admin.model.Cell;
import hun.hbase.admin.model.Page;
import hun.hbase.admin.util.HbaseUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginHbase {
	
	@Autowired
	private HbaseDao hbaseDao;

	@RequestMapping("/main")
	public ModelAndView login(Address address) {
		HbaseUtil.setIP(address.getIP());
		ModelAndView mav = new ModelAndView();
		Map<String,String> map = hbaseDao.get_alltable();
		mav.addObject("tableMap", map);
		return mav;

	}

	@RequestMapping(value = "/addTable", params = "tablename")
	@ResponseBody
	public String add_table(@RequestParam("tablename") String ip) {
		System.out.println(ip);
		String[] s = null;
		s = ip.split(",");
		//System.out.println(s.length);
		if (s.length == 1) {
			s = ip.split("，");
			//System.out.println(s.length);
		}
		if (s.length != 2) {
			return "!!!!!No,No,No You are Wrong.create Table is fail .Please again";
		}
		boolean b = hbaseDao.create_table(s[0], s[1]);
		if (!b) {
			return "!!!!!No,No,No You are Wrong again.The Table is Exits .Please again";
		}
		//return s[0];
		return ip;
	}

	/**
	 * 
	 * @param string
	 * @return
	 */
	@RequestMapping(value = "/getDateForTable", params = "table")
	@ResponseBody
	public Page get_table_data(@RequestParam("table") String string) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		Page p = (Page) request.getAttribute("page");
		if (p == null) {
			p = new Page();
		}
		List<ArrayList<String>> data = hbaseDao.get_data_fortable(string, p);
		p.setArrayList(data);
		p.setCurrentpage(p.getCurrentpage() + 1);
		request.setAttribute("page", p);

		return p;
	}

	/**
	 * 这里的刷新表是：删除某张表后，重新加载所有表名。功能凑合用着，等前端页面加强再改
	 * 
	 * @return
	 */
	@RequestMapping("/refluse_tables")
	@ResponseBody
	public Boolean refluse_tables(String tablenames) {
		return hbaseDao.drop_table(tablenames);
	}

	/**
	 * 在这里，前端增加一个cell。假如前端只显示十条数据。前端已经放满十条数据就不用显示。让翻页的去做，但是如果不满，应该直接追加....
	 */
	@RequestMapping("/add_cell")
	@ResponseBody
	public boolean add_cell(Cell cell){
		System.out.println(cell.toString());
		List<Cell> cells = new ArrayList<Cell>();
		cells.add(cell);
		return hbaseDao.addCell(cells);
		
	}
}

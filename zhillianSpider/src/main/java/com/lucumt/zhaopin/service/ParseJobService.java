package com.lucumt.zhaopin.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.lucumt.zhaopin.util.SpiderConfig;

public class ParseJobService {

	public void praseContent(String job,String position) {
		Document document = null;
		String pageNo = null;
		Elements nextPageNo = null;
		try {

			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("jl", position);
			paramMap.put("kw", job);
			paramMap.put("sm", "0");
			paramMap.put("p", "1");
			
			do {
				document = Jsoup.connect(SpiderConfig.QUERY_URL).data(paramMap).timeout(SpiderConfig.TIME_OUT_INTERVAL).get();
				
				pageNo = document.select("div.pagesDown>ul>li>a.current").text();
				System.out.println("-------------------------查询参数:\t" + paramMap+"\t------------------------------------------");
				
				parseJobInfo(document);
				
				nextPageNo = document.select("div.pagesDown>ul>li.pagesDown-pos>a.next-page");
				paramMap.put("p", String.valueOf(Integer.parseInt(pageNo) + 1));
			}while(!nextPageNo.isEmpty());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void parseJobInfo(Document document){
		Elements jobElements=document.select("table.newlist:gt(0)>tbody>tr:eq(0)");
	    for(Element jobElement:jobElements){
	    	System.out.println(jobElement.select("td.zwmc>div>a.ehref_yun").text()+"\t"+jobElement.select("td.gsmc>a").text()
	    			+"\t"+jobElement.select("td.zwyx").text()+"\t"+jobElement.select("td.gzdd").text()+"\t"+jobElement.select("td.gxsj").text());
	    }
		
	}
}

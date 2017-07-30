package com.project.way;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class GetLotteryClass {
	public static final String lotteryId = "{" + 
			"  \"result\" : [ {" + 
			"    \"code\" : \"CQSSC\"," + 
			"    \"id\" : 1," + 
			"    \"cn\" : \"重庆时时彩\"" + 
			"  }, {" + 
			"    \"code\" : \"GD11Y\"," + 
			"    \"id\" : 2," + 
			"    \"cn\" : \"广东11选5\"" + 
			"  }, {" + 
			"    \"code\" : \"MKG5FC\"," + 
			"    \"id\" : 4," + 
			"    \"cn\" : \"GOD5分彩\"" + 
			"  }, {" + 
			"    \"code\" : \"XJSSC\"," + 
			"    \"id\" : 8," + 
			"    \"cn\" : \"新疆时时彩\"" + 
			"  }, {" + 
			"    \"code\" : \"MKGFFC\"," + 
			"    \"id\" : 11," + 
			"    \"cn\" : \"新腾讯分分彩\"" + 
			"  }, {" + 
			"    \"code\" : \"MKGMMC\"," + 
			"    \"id\" : 12," + 
			"    \"cn\" : \"GOD秒秒彩\"" + 
			"  }, {" + 
			"    \"code\" : \"CQSSCN\"," + 
			"    \"id\" : 13," + 
			"    \"cn\" : \"新重庆时时彩\"" + 
			"  }, {" + 
			"    \"code\" : \"XGSSC\"," + 
			"    \"id\" : 21," + 
			"    \"cn\" : \"香港时时彩\"" + 
			"  }, {" + 
			"    \"code\" : \"RBSSC\"," + 
			"    \"id\" : 23," + 
			"    \"cn\" : \"日本时时彩\"" + 
			"  }, {" + 
			"    \"code\" : \"HGSSC\"," + 
			"    \"id\" : 31," + 
			"    \"cn\" : \"韩国1.5分彩\"" + 
			"  }, {" + 
			"    \"code\" : \"BJPK10\"," + 
			"    \"id\" : 32," + 
			"    \"cn\" : \"北京PK10\"" + 
			"  }, {" + 
			"    \"code\" : \"XJPSSC\"," + 
			"    \"id\" : 34," + 
			"    \"cn\" : \"新加坡2分彩\"" + 
			"  }, {" + 
			"    \"code\" : \"JDSSC\"," + 
			"    \"id\" : 41," + 
			"    \"cn\" : \"京都1.5分彩\"" + 
			"  }, {" + 
			"    \"code\" : \"HG1FSSC\"," + 
			"    \"id\" : 45," + 
			"    \"cn\" : \"韩国1分彩\"" + 
			"  }, {" + 
			"    \"code\" : \"TG30SSC\"," + 
			"    \"id\" : 46," + 
			"    \"cn\" : \"泰国30秒\"" + 
			"  }, {" + 
			"    \"code\" : \"CNDF1\"," + 
			"    \"id\" : 47," + 
			"    \"cn\" : \"极速赛车\"" + 
			"  }, {" + 
			"    \"code\" : \"BL11Y\"," + 
			"    \"id\" : 61," + 
			"    \"cn\" : \"巴黎11选5\"" + 
			"  }, {" + 
			"    \"code\" : \"MG3SSC\"," + 
			"    \"id\" : 63," + 
			"    \"cn\" : \"美国3.5分彩\"" + 
			"  }, {" + 
			"    \"code\" : \"LSWJSSSC\"," + 
			"    \"id\" : 64," + 
			"    \"cn\" : \"拉斯维加斯1.5分彩\"" + 
			"  }, {" + 
			"    \"code\" : \"BLSSC\"," + 
			"    \"id\" : 65," + 
			"    \"cn\" : \"巴黎1分彩\"" + 
			"  }, {" + 
			"    \"code\" : \"MGSSC\"," + 
			"    \"id\" : 66," + 
			"    \"cn\" : \"美国1.5分彩\"" + 
			"  } ]," + 
			"  \"code\" : 1," + 
			"  \"msg\" : \"ok\"" + 
			"}";
	public static final String inserWay = "INSERT INTO `t_ssc_lottery` (`id`,`code`,`cn`) VALUES (%s);";
	public static void main(String[] args) {
		JSONObject configJson = JSON.parseObject(lotteryId);
		List<JSONObject> wayArr = (List<JSONObject>) configJson.get("result");
		for (JSONObject way : wayArr) {
			 String value = way.get("id") + ", \"" + way.get("code") + "\", \"" + way.get("cn") + "\"";
			System.err.println(String.format(inserWay, value));
		}
	}

	private static List<JSONObject> parseJsonArray(String wayStr) {
		List<JSONObject> wayArr = JSONArray.parseArray(wayStr, JSONObject.class);
		return wayArr;
	}

}

package autoInsurance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Connection.KeyVal;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;

public class BeiJPingAnImpl implements IAutoInsurance {
	Map<String, String> templateData = new HashMap<String, String>();
	String charset = "utf-8";
	HttpClientUtil httpClientUtil = new HttpClientUtil();
	
	public String login(String in) {
		String out = "";
		
		JSONObject jsonObject = JSONObject.fromObject(in);
		String ukey = jsonObject.getString("ukey");
		String loginName = jsonObject.getString("loginName");
		String password = jsonObject.getString("password");
		
		String url = "https://icore-pts.pingan.com.cn/ebusiness/auto/newness/toibcswriter.do?transmitId=apply";
		String retStr = httpClientUtil.doPost(url, new HashMap(), charset);
		Document doc = Jsoup.parse(retStr);
		Element script_umlogin = doc.getElementById("script_umlogin");
		if(null != script_umlogin){
			url = "https://icore-pts.pingan.com.cn/ebusiness/j_security_check";
			Map<String,String> paramMap = new HashMap<String,String>();
			paramMap.put("j_username", loginName);
			paramMap.put("j_password", password);
			paramMap.put("SMAUTHREASON", "0");
			paramMap.put("randCode", "");
			retStr = httpClientUtil.doPost(url, paramMap, charset);
			doc = Jsoup.parse(retStr);
			String title = doc.title();
			if(title.startsWith("中国平安产险合作伙伴交易服务平台")){
				url = "https://icore-pts.pingan.com.cn/ebusiness/auto/newness/toibcswriter.do?transmitId=apply";
				retStr = httpClientUtil.doPost(url, new HashMap(), charset);
				doc = Jsoup.parse(retStr);
				FormElement form = (FormElement) doc.getElementById("workbenchIBCSAppForm");
				List<KeyVal> datas = form.formData();
				paramMap = new HashMap<String,String>();
				for(KeyVal kv:datas){
					paramMap.put(kv.key(), kv.value());
				}
				paramMap.put("dealerCodes","");
				url = "https://icorepnbs.pingan.com.cn/icore_pnbs/do/usermanage/systemTransfer";
				retStr = httpClientUtil.doPost(url, paramMap, charset);
				doc = Jsoup.parse(retStr);
				title = doc.title();
				if ("产险业务新核心系统".equals(title))
					out = "{\"success\": true, \"msg\": \"" + loginName + "," + paramMap.get("userName") + ",登录成功\"}";
			}
		}else{
			FormElement form = (FormElement) doc.getElementById("workbenchIBCSAppForm");
			if(null != form){
				return "{\"success\": false, \"msg\": \"已登录，不能重复登录!\"}";
			}
		}
		
		return out;
	}

	public String queryBaseData(String in, Map<String, String> map) {
		JSONObject jsonObject = JSONObject.fromObject(in);
		String chepNu = jsonObject.getString("chepNu");
		String chejh = jsonObject.getString("chejh");
		String fadjh = jsonObject.getString("fadjh");
		
		String url = "https://icorepnbs.pingan.com.cn/icore_pnbs/do/app/quotation/autoModelCodeQuery";
		String postData = "{\"vehicleFrameNo\":\"LVGDU25R6BG002030\",\"departmentCode\":\"20119\",\"vehicleLicenceCode\":\"京N-0CY82\",\"insuranceType\":\"1\"}";
		String respStr = httpClientUtil.doPost(url, postData, charset);
		System.out.println(respStr);
		
		// https://icorepnbs.pingan.com.cn/icore_pnbs/do/app/quotation/circVehicleTypeInfoQuery
		
		return respStr;
	}

	public String suanF(String in, Map<String, String> map) {
		String url = "https://icorepnbs.pingan.com.cn/icore_pnbs/do/app/quotation/applyQueryAndQuote";
		
		String postData = "{\"mainQuotationNo\":\"Q011900390000306762702\",\"saleInfo\":{\"departmentCode\":\"20119\",\"dealerCode\":\"201191510069\",\"businessSourceCode\":\"2\",\"businessSourceDetailCode\":\"2\",\"channelSourceCode\":\"9\",\"channelSourceDetailCode\":\"V\",\"agentInfoList\":[{\"agencyCode\":\"\",\"agentCode\":\"01190173\",\"agentAgreementNo\":\"0119017315001\",\"supplementAgreementNo\":\"1\",\"agencySaleName\":\"\",\"agencySaleProfCertifNo\":\"\"}],\"brokerInfoList\":[{\"brokerCode\":\"\"}],\"employeeInfoList\":[{\"employeeCode\":\"2010001607\",\"employeeProfCertifNo\":\"\"}],\"primaryIntroducerInfo\":null,\"partnerInfoList\":[{\"partnerType\":\"02\",\"partnerCode\":\"201191510069\"}]},\"quotationBaseInfo\":{\"totalStandardPremium\":0,\"documentGroupId\":\"\"},\"sendInfo\":{\"sendWay\":\"03\",\"country\":\"01\",\"province\":\"\",\"receiveTimeZone\":\"0\"},\"aplylicantInfoList\":[{\"sexCode\":\"M\",\"nationality\":\"156\",\"personnelType\":\"1\",\"certificateType\":\"01\",\"homeTelephone\":\"\",\"address\":\"\",\"isConfirm\":5,\"invoicePrintType\":\"03\",\"taxpayerCertificateType\":\"\",\"taxpayerCertificateNo\":\"\",\"billingAddress\":\"\",\"billingPhone\":\"\",\"billingDepositBank\":\"\",\"billingDepositBankAccount\":\"\"}],\"insurantInfoList\":[],\"quotationList\":[{\"voucher\":{\"accommodationInfoDTO\":{\"isAbnormalCarCheck\":false,\"approveChain\":\"\",\"accommodationReason\":\"\",\"accommodationExemptFlag\":\"\",\"isAbnormalCar\":\"0\",\"ApproveChain\":\"\",\"ApproveChainType\":\"\",\"ApproveChainUmNum\":\"\",\"accmmdtnBrkrChrgPrprtn\":\"\",\"accmmdtnCmmssnChrgPrprtn\":\"\",\"accommodatePremium\":\"\",\"accommodationDiscount\":\"\",\"brokerageFeeCheck\":\"\",\"commissionFeeCheck\":\"\",\"exemptCarCheck\":\"\",\"reason\":\"\",\"totalDiscountCheck\":\"\"},\"ownerDriver\":{\"certificateTypeCode\":\"01\",\"linkmodeType\":\"03\",\"sexCode\":\"M\",\"personnelFlag\":\"1\",\"groupCertificateTypeCodeList\":[{\"departmentCode\":\"201\",\"displayNo\":1,\"encodeKey\":\"01\",\"encodeType\":\"PNBSZJLX\",\"encodeValue\":\"身份证\"},{\"departmentCode\":\"201\",\"displayNo\":2,\"encodeKey\":\"02\",\"encodeType\":\"PNBSZJLX\",\"encodeValue\":\"护照\"},{\"departmentCode\":\"201\",\"displayNo\":3,\"encodeKey\":\"03\",\"encodeType\":\"PNBSZJLX\",\"encodeValue\":\"军人证\"},{\"departmentCode\":\"201\",\"displayNo\":6,\"encodeKey\":\"06\",\"encodeType\":\"PNBSZJLX\",\"encodeValue\":\"港澳回乡证或台胞证\"}],\"personnelName\":\"迟红月\",\"certificateTypeNo\":\"13292819740204331X\",\"birthday\":\"1974-02-04\",\"clientNo\":\"001921290592\",\"encryptedAddress\":\"#undefined\",\"encryptedEmail\":\"#undefined\",\"isShowCustomerHistory\":true},\"applicantInfo\":{\"personnelFlag\":\"1\",\"groupCertificateTypeCodeList\":[{\"departmentCode\":\"201\",\"displayNo\":1,\"encodeKey\":\"01\",\"encodeType\":\"PNBSZJLX\",\"encodeValue\":\"身份证\"},{\"departmentCode\":\"201\",\"displayNo\":2,\"encodeKey\":\"02\",\"encodeType\":\"PNBSZJLX\",\"encodeValue\":\"护照\"},{\"departmentCode\":\"201\",\"displayNo\":3,\"encodeKey\":\"03\",\"encodeType\":\"PNBSZJLX\",\"encodeValue\":\"军人证\"},{\"departmentCode\":\"201\",\"displayNo\":6,\"encodeKey\":\"06\",\"encodeType\":\"PNBSZJLX\",\"encodeValue\":\"港澳回乡证或台胞证\"}],\"country\":\"00\",\"province\":\"\",\"city\":\"\",\"county\":\"\",\"communicationAddress\":\"\",\"certificateTypeCode\":\"01\",\"sexCode\":\"M\",\"linkmodeType\":\"03\",\"nationality\":\"156\",\"isConfirm\":5,\"invoicePrintType\":\"03\"},\"insurantInfo\":{\"certificateTypeCode\":\"01\",\"linkmodeType\":\"03\",\"sexCode\":\"M\",\"personnelFlag\":\"1\",\"groupCertificateTypeCodeList\":[{\"departmentCode\":\"201\",\"displayNo\":1,\"encodeKey\":\"01\",\"encodeType\":\"PNBSZJLX\",\"encodeValue\":\"身份证\"},{\"departmentCode\":\"201\",\"displayNo\":2,\"encodeKey\":\"02\",\"encodeType\":\"PNBSZJLX\",\"encodeValue\":\"护照\"},{\"departmentCode\":\"201\",\"displayNo\":3,\"encodeKey\":\"03\",\"encodeType\":\"PNBSZJLX\",\"encodeValue\":\"军人证\"},{\"departmentCode\":\"201\",\"displayNo\":6,\"encodeKey\":\"06\",\"encodeType\":\"PNBSZJLX\",\"encodeValue\":\"港澳回乡证或台胞证\"}],\"country\":\"00\",\"province\":\"\",\"city\":\"\",\"county\":\"\",\"communicationAddress\":\"\",\"nationality\":\"156\",\"insureSameText\":\"--请选择--\",\"isShowCustomerHistory\":false,\"encryptInfoDTO\":{}},\"mainDriver\":null,\"assistantDriver\":null,\"baseInfo\":{\"disputedSettleModeCode\":1,\"queryType\":\"0\",\"departmentCode\":\"20119\",\"rateClassFlag\":\"14\"},\"c01BaseInfo\":{\"calculateResult\":{\"beginTime\":\"2016-10-03 00:00:00\",\"endTime\":\"2017-10-02 23:59:59\",\"shortTermRatio\":1},\"insuranceBeginTime\":\"2016-10-03 00:00:00\",\"insuranceEndTime\":\"2017-10-02 23:59:59\",\"shortTimeCoefficient\":1,\"isReportElectronRelation\":\"\",\"isCalculateWithoutCirc\":\"N\",\"departmentCode\":\"20119\",\"brokerCode\":\"\",\"agentCode\":\"01190173\",\"agentName\":\"北京龙岗兴业商贸有限公司\",\"agentAgreementNo\":\"0119017315001\",\"supplementAgreementNo\":\"1\",\"remark\":\"\",\"productCode\":\"\",\"productName\":\"\",\"queryType\":\"0\",\"disputedSettleModeCode\":1,\"renewalTypeCode\":\"0\",\"isRound\":\"N\",\"insuranceType\":\"1\",\"rateClassFlag\":\"14\",\"rateChannelAdjustFlag\":\"\",\"channelAdjustPoudndageRate\":\"\",\"channelAdjustDeploitationFeeRate\":\"\",\"channeladjustPromptingFee\":\"\",\"planCode\":\"C01\",\"quoteTimes\":0,\"isaccommodation\":\"N\",\"totalAgreePremium\":\"\",\"totalDiscountCommercial\":\"\",\"totalActualPremium\":\"\",\"totalStandardPremium\":\"\",\"lastPolicyNo\":\"\"},\"c01ExtendInfo\":{\"commercialClaimRecord\":\"09\",\"applyYears\":\"\",\"offerLastPolicyFlag\":\"N\",\"brandDetail\":\"1\",\"ownerVehicleTypeCode\":\"K33\",\"useMobileLocation\":\"N\",\"dealerCode\":\"201191510069\",\"expectationUnderwriteLimit\":\"2\",\"analogyVehicleFlag\":\"0\",\"documentGroupId\":\"39000360428789\"},\"c51ExtendInfo\":{\"brandDetail\":\"1\",\"dealerCode\":\"201191510069\",\"ownerVehicleTypeCode\":\"K33\",\"expectationUnderwriteLimit\":\"2\",\"useMobileLocation\":\"N\",\"documentGroupId\":\"39000360428789\"},\"c51BaseInfo\":{\"calculateResult\":{\"beginTime\":\"2016-10-03 00:00:00\",\"endTime\":\"2017-10-02 23:59:59\",\"shortTermRatio\":1},\"insuranceBeginTime\":\"2016-10-03\",\"insuranceEndTime\":\"2017-10-02\",\"shortTimeCoefficient\":1,\"isReportElectronRelation\":\"\",\"isCalculateWithoutCirc\":\"N\",\"departmentCode\":\"20119\",\"brokerCode\":\"\",\"agentCode\":\"01190173\",\"agentName\":\"北京龙岗兴业商贸有限公司\",\"agentAgreementNo\":\"0119017315001\",\"supplementAgreementNo\":\"1\",\"queryType\":\"0\",\"disputedSettleModeCode\":1,\"renewalTypeCode\":\"0\",\"isRound\":\"N\",\"insuranceType\":\"1\",\"rateClassFlag\":\"14\",\"quoteTimes\":0,\"totalAgreePremium\":\"\",\"totalDiscountCommercial\":\"\",\"totalActualPremium\":\"\",\"totalStandardPremium\":\"\",\"lastPolicyNo\":\"\"},\"saleInfo\":{\"opportunityCode\":null,\"opportunityName\":null,\"developFlg\":\"N\",\"departmentCode\":\"20119\",\"businessSourceCode\":\"2\",\"businessSourceDetailCode\":\"2\",\"channelSourceCode\":\"9\",\"channelSourceDetailCode\":\"V\",\"saleAgentCode\":\"2010001607\"},\"saleAgentList\":[],\"propertyList\":{},\"c01DisplayRateFactorList\":[{\"factorCode\":\"F76\",\"ratingTableNo\":\"I101003001\",\"factorRatioCOM\":null,\"factorValue\":null,\"factorValueName\":\"\"},{\"factorCode\":\"F15\",\"ratingTableNo\":\"I101003001\",\"factorValue\":0},{\"factorCode\":\"F74\",\"ratingTableNo\":\"I101003001\",\"factorRatioCOM\":null,\"factorValue\":null,\"factorValueName\":\"\"},{\"factorCode\":\"F30\",\"ratingTableNo\":\"I101003001\",\"factorRatioCOM\":null,\"factorValue\":null,\"factorValueName\":\"\"},{\"factorCode\":\"F34\",\"ratingTableNo\":\"I101003001\",\"factorRatioCOM\":null,\"factorValue\":null,\"factorValueName\":\"\"}],\"c01DutyList\":[{\"dutyCode\":\"02\",\"insuredAmount\":5,\"premiumRate\":0,\"basePremium\":0,\"totalStandardPremium\":0,\"totalAgreePremium\":0,\"totalActualPremium\":0,\"pureRiskPremium\":\"\",\"riskPremium\":\"\"},{\"dutyCode\":\"01\",\"insuredAmount\":17461.6,\"premiumRate\":0,\"basePremium\":0,\"totalStandardPremium\":0,\"totalAgreePremium\":0,\"totalActualPremium\":0,\"pureRiskPremium\":\"\",\"riskPremium\":\"\"},{\"dutyCode\":\"28\"},{\"dutyCode\":\"27\"}],\"receiverInfo\":{\"sendWay\":\"03\",\"country\":\"01\",\"province\":\"\",\"receiveTimeZone\":\"0\"},\"vehicleTarget\":{\"cache\":{\"brand\":\"\"},\"energyType\":\"A\",\"specialCarLicenseChoice\":\"\",\"specialCarFlag\":\" \",\"addr\":{\"country\":\"01\"},\"ownerVehicleTypeDesc\":\"小型轿车\",\"changeOwnerFlag\":\"0\",\"transferDate\":\"\",\"vehicleLossInsuredValue\":17461.6,\"purchasePriceDefaultValue\":69800,\"firstRegisterDate\":\"2006-11-15\",\"loanVehicleFlag\":\"0\",\"vehicleLicenceCode\":\"京Q-V3303\",\"vehicleFrameNo\":\"LBEMCACA66X033634\",\"engineNo\":\"6B749516\",\"autoModelCode\":\"YSD1003BJX\",\"autoModelName\":\"北京现代BH7140MW轿车\",\"modifyAutoModelName\":\"北京现代BH7140MW轿车\",\"circVehicleModel\":\"北京现代BH7140MW轿车\",\"circVehicleChineseBrand\":\"北京现代\",\"vehicleSeats\":5,\"vehicleTonnages\":0,\"exhaustCapability\":1.399,\"price\":62800,\"analogyPrice\":0,\"vehicleTypeCode\":\"A012\",\"brandParaOutYear\":\"2006\",\"ownerVehicleTypeCode\":\"K33\",\"vehicleTypeName\":\"六座以下客车\",\"vehicleClassCode\":\"1\",\"vehicleTypeDetailCode\":\"\",\"licenceTypeCode\":\"02\",\"licenceTypeName\":\"小型汽车\",\"autoTypeQueryCode\":\"39PAIC02160000000000964115850G\",\"circAutoModelCode\":\"XDABBD0003\",\"wholeWeight\":\"\",\"usageAttributeCode\":\"02\",\"ownershipAttributeCode\":\"03\",\"brandName\":\"北京现代\",\"fleetMark\":\"0\",\"fleetNo\":\"\",\"isMiniVehicle\":\"N\",\"isAbnormalCar\":\"0\"},\"c51DisplayRateFactorList\":[{\"factorCode\":\"F54\",\"factorValue\":\"A4\",\"factorRatioCOM\":\"1\",\"ratingTableNo\":\"\"},{\"factorCode\":\"F55\",\"factorValue\":\"V4\",\"factorRatioCOM\":null,\"ratingTableNo\":\"\"},{\"factorCode\":\"F999\",\"factorValue\":\"\",\"factorRatioCOM\":0,\"ratingTableNo\":\"\"}],\"c51DutyList\":[{\"dutyCode\":47,\"insuredAmount\":\"2000\"},{\"dutyCode\":46,\"insuredAmount\":\"10000\"},{\"dutyCode\":45,\"insuredAmount\":\"110000\"}],\"vehicleTaxInfo\":{\"taxType\":0,\"delinquentTaxDue\":true,\"taxPayerId\":\"13292819740204331X\",\"isNeedAddTax\":\"02\",\"deduction\":\"\",\"deductionDueProportion\":\"\",\"totalTaxMoney\":\"\",\"deductionDueCode\":\"\",\"deductionDueType\":\"\",\"fuelType\":\"A\"},\"c51FleetInfoDTO\":{},\"thirdCarBusinessInfoDTO\":{\"agentLicenseNo\":\"\",\"agentName\":\"\",\"cardNo\":0,\"companyCode\":\"\",\"saleName\":\"\",\"saleAddr\":\"\",\"dateValidBegin\":\"\",\"dateValidEnd\":\"\"},\"paymentInfo\":{},\"c01SpecialPromiseList\":[],\"c51SpecialPromiseList\":[],\"c51UdwrAttachList\":[],\"c01UdwrAttachList\":[],\"attachDelayEOAInfo\":{}},\"c01RateFactorPremCalcResult\":{},\"c01IsApply\":false,\"c51IsApply\":false,\"quoteCompleteTime\":null,\"confirmTime\":1475340682000,\"combineQuotationNo\":\"\",\"c01CircInfoDTO\":{},\"c51CircInfoDTO\":{},\"displayNo\":\"01\",\"applyPlans\":\"C01\"}]}";
		
		String respStr = httpClientUtil.doPost(url, postData, charset);
		System.out.println(respStr);
		
		return null;
	}

	public String saveAndHeB(String in, Map<String, String> map) {
		// TODO Auto-generated method stub
		return null;
	}

	public String queryBaodhByToubdh(String in) {
		// TODO Auto-generated method stub
		JSONObject jsonObj = JSONObject.fromObject(in);
		String toubdh = jsonObj.getString("toubdh");
		
		String url = "https://icorepnbs.pingan.com.cn/icore_pnbs/do/app/workbench/qtWaitTaskInfo";
		String postData = "{\"voucherType\":\"1\",\"voucherNo\":\""+toubdh+"\",\"inputById\":\"LGXY-00001\",\"departmentCode\":\"20119\",\"lowDepartment\":true,\"applicantType\":\"\",\"coinsuranceMark\":\"\"}";
		String retStr = httpClientUtil.doPost(url, postData, charset);
		System.out.println(retStr);
		
		url = "https://icorepnbs.pingan.com.cn/icore_pnbs/bussiness/workbench/templates/view/popup/quotationDetail.tpl?voucherNo=" + toubdh + "&isFromCNBS=false&moduleType=detail&_u=" + HttpClientUtil.digestMap.get(toubdh);
		retStr = httpClientUtil.doGet(url, new HashMap(), charset);
		System.out.println(retStr);
		
		Document doc = Jsoup.parse(retStr);
		Element elm = doc.getElementsByAttributeValue("language", "javascript").get(0);
		String js = elm.data();
		System.out.println(js);
		String quotationDTO = StringUtils.substringBetween(js, "var quotationDTO = ", " ;");
		System.out.println(quotationDTO);
		
		Map<String, String> outMap = new HashMap<String, String>();
//		outMap.put("proposalNo", jObj2.getString("proposalNo"));
//		outMap.put("policyNo", jObj2.getString("policyNo"));
//		outMap.put("insuredName", jObj2.getString("insuredName"));
//		//outMap.put("startDate", jObj2.getString("startDate"));
//		outMap.put("startDate", timeStamp2Date("" + (Long) ((Map) jObj2.get("startDate")).get("time"), "yyyy-M-d"));
		
		return JSONObject.fromObject(outMap).toString();
	}

	public String queryRelationData(String in) {
		JSONObject jsonObj = JSONObject.fromObject(in);
		String shenfzNu = jsonObj.getString("shenfzNu");
		
		String url = "https://icorepnbs.pingan.com.cn/icore_pnbs/do/app/quotation/searchIndiCustomerInfo";
		String postData = "{\"certificateType\":\"01\",\"certificateNo\":\"" + shenfzNu + "\",\"sexCode\":\"\",\"birthday\":\"\",\"agentCode\":\"01190173\"}";
		String respStr = httpClientUtil.doPost(url, postData, charset);
		System.out.println(respStr);
		
		JSONObject jObj = JSONObject.fromObject(respStr);
		JSONArray jdatas = jObj.getJSONArray("data");
		if(jdatas.size() < 1)
			return "{\"success\":false, \"msg\":\"查询关系人失败\"}";
		
		JSONObject jObj2 = JSONObject.fromObject(jdatas.get(0));
		
		Map<String, String> outMap = new HashMap<String, String>();
		outMap.put("identifyNumber", jObj2.getString("certificateNo"));
		outMap.put("customerCode", jObj2.getString("clientNo"));
		outMap.put("customerCName", jObj2.getString("clientName"));
		outMap.put("addressCName", jObj2.getString("address"));
		outMap.put("customMobile", jObj2.getString("mobileNumber"));
		
		return JSONObject.fromObject(outMap).toString();
	}

	public String queryHebJg(String in) {
		// TODO Auto-generated method stub
		return null;
	}

	public String detail(String voucherNo){
		String url = "https://icorepnbs.pingan.com.cn/icore_pnbs/do/app/workbench/qtWaitTaskInfo";
		String postData = "{\"voucherType\":\"1\",\"voucherNo\":\""+voucherNo+"\",\"inputById\":\"LGXY-00001\",\"departmentCode\":\"20119\",\"lowDepartment\":true,\"applicantType\":\"\",\"coinsuranceMark\":\"\"}";
		String retStr = httpClientUtil.doPost(url, postData, charset);
		System.out.println(retStr);
		
		url = "https://icorepnbs.pingan.com.cn/icore_pnbs/bussiness/workbench/templates/view/popup/quotationDetail.tpl?voucherNo=" + voucherNo + "&isFromCNBS=false&moduleType=detail&_u=" + HttpClientUtil.digestMap.get(voucherNo);
		retStr = httpClientUtil.doGet(url, new HashMap(), charset);
		System.out.println(retStr);
		return "";
	}
}

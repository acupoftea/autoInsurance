package autoInsurance;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Connection.KeyVal;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.select.Elements;

public class BeiJPiccImpl implements IAutoInsurance {
	Map<String, String> templateData = new HashMap<String, String>();
	String charset = "utf-8";
	HttpClientUtil httpClientUtil = new HttpClientUtil();
	
	Map<String, String> carTypeMap = new HashMap<String, String>();
	
	String readFile2Strng(String filePath) throws Exception {
		File file = new File(filePath);
		Long filelength = file.length();
		byte[] filecontent = new byte[filelength.intValue()];
		FileInputStream in = new FileInputStream(file);
		in.read(filecontent);
		in.close();

		return new String(filecontent, "utf-8");
	}
	
	 void init(Document doc) throws Exception{
			String str = "<select class=\"w_p80\" name=\"carKindCodeBak\" title=\"   \" id=\"carKindCodeBak\"><option value=\"A01\">客车</option><option value=\"B01\">货车</option><option value=\"B02\">半挂牵引车</option><option value=\"B11\">三轮汽车</option><option value=\"B12\">低速货车</option><option value=\"B13\">客货两用车</option><option value=\"B21\">自卸货车</option><option value=\"B91\">货车挂车</option><option value=\"C01\">油罐车</option><option value=\"C02\">气罐车</option><option value=\"C03\">液罐车</option><option value=\"C04\">冷藏车</option><option value=\"C11\">罐车挂车</option><option value=\"C20\">推土车</option><option value=\"C22\">清障车</option><option value=\"C23\">清扫车</option><option value=\"C24\">清洁车</option><option value=\"C25\">起重车</option><option value=\"C26\">装卸车</option><option value=\"C27\">升降车</option><option value=\"C28\">混凝土搅拌车</option><option value=\"C29\">挖掘车</option><option value=\"C30\">专业拖车</option><option value=\"C31\">特种车二挂车</option><option value=\"C39\">特种车二类其它</option><option value=\"C41\">电视转播车</option><option value=\"C42\">消防车</option><option value=\"C43\">医疗车</option><option value=\"C44\">油汽田操作用车</option><option value=\"C45\">压路车</option><option value=\"C46\">矿山车</option><option value=\"C47\">运钞车</option><option value=\"C48\">救护车</option><option value=\"C49\">监测车</option><option value=\"C50\">雷达车</option><option value=\"C51\">X光检查车</option><option value=\"C52\">电信抢修车/电信工程车</option><option value=\"C53\">电力抢修车/电力工程车</option><option value=\"C54\">专业净水车</option><option value=\"C55\">保温车</option><option value=\"C56\">邮电车</option><option value=\"C57\">警用特种车</option><option value=\"C58\">混凝土泵车</option><option value=\"C61\">特种车三类挂车</option><option value=\"C69\">特种车三类其它</option><option value=\"C90\">集装箱拖头</option><option value=\"D01\">摩托车</option><option value=\"D02\">正三轮摩托车</option><option value=\"D03\">侧三轮摩托车</option><option value=\"E01\">拖拉机</option><option value=\"E11\">联合收割机</option><option value=\"E12\">变形拖拉机/其它</option><option value=\"Z99\">其它车辆</option></select>";
			Document tmpDoc = Jsoup.parse(str);
			Elements els = tmpDoc.select("#carKindCodeBak> option");
		    for (Element el: els) {
		    	carTypeMap.put(el.attr("value"),el.text());
		    }
			
			templateData = new HashMap<String,String>();
			List<FormElement>  forms = doc.getAllElements().forms();
			for(FormElement form:forms){
				List<KeyVal> datas = form.formData();
				for(KeyVal item:datas){
					templateData.put(item.key(), item.value());
					//System.out.print(item.key()+"="+item.value() + "&");
				}
				System.out.println("------");
			}
			
			templateData.put("prpCmainCI.sumAmount","122000"); 
			templateData.put("prpCitemKindCI.familyNo","1");//null
			templateData.put("prpCitemKindCI.amount","122000");//0
			templateData.put("prpCitemKindCI.adjustRate","0.9");//1
		}
	
	public BeiJPiccImpl() {
		super();
		// TODO Auto-generated constructor stub
		System.out.println("ver 1.00");
		
		String str = "<select class=\"w_p80\" name=\"carKindCodeBak\" title=\"   \" id=\"carKindCodeBak\"><option value=\"A01\">客车</option><option value=\"B01\">货车</option><option value=\"B02\">半挂牵引车</option><option value=\"B11\">三轮汽车</option><option value=\"B12\">低速货车</option><option value=\"B13\">客货两用车</option><option value=\"B21\">自卸货车</option><option value=\"B91\">货车挂车</option><option value=\"C01\">油罐车</option><option value=\"C02\">气罐车</option><option value=\"C03\">液罐车</option><option value=\"C04\">冷藏车</option><option value=\"C11\">罐车挂车</option><option value=\"C20\">推土车</option><option value=\"C22\">清障车</option><option value=\"C23\">清扫车</option><option value=\"C24\">清洁车</option><option value=\"C25\">起重车</option><option value=\"C26\">装卸车</option><option value=\"C27\">升降车</option><option value=\"C28\">混凝土搅拌车</option><option value=\"C29\">挖掘车</option><option value=\"C30\">专业拖车</option><option value=\"C31\">特种车二挂车</option><option value=\"C39\">特种车二类其它</option><option value=\"C41\">电视转播车</option><option value=\"C42\">消防车</option><option value=\"C43\">医疗车</option><option value=\"C44\">油汽田操作用车</option><option value=\"C45\">压路车</option><option value=\"C46\">矿山车</option><option value=\"C47\">运钞车</option><option value=\"C48\">救护车</option><option value=\"C49\">监测车</option><option value=\"C50\">雷达车</option><option value=\"C51\">X光检查车</option><option value=\"C52\">电信抢修车/电信工程车</option><option value=\"C53\">电力抢修车/电力工程车</option><option value=\"C54\">专业净水车</option><option value=\"C55\">保温车</option><option value=\"C56\">邮电车</option><option value=\"C57\">警用特种车</option><option value=\"C58\">混凝土泵车</option><option value=\"C61\">特种车三类挂车</option><option value=\"C69\">特种车三类其它</option><option value=\"C90\">集装箱拖头</option><option value=\"D01\">摩托车</option><option value=\"D02\">正三轮摩托车</option><option value=\"D03\">侧三轮摩托车</option><option value=\"E01\">拖拉机</option><option value=\"E11\">联合收割机</option><option value=\"E12\">变形拖拉机/其它</option><option value=\"Z99\">其它车辆</option></select>";
		Document tmpDoc = Jsoup.parse(str);
		Elements els = tmpDoc.select("#carKindCodeBak> option");
		for (Element el : els) {
			carTypeMap.put(el.attr("value"), el.text());
		}
		
		templateData = new HashMap<String, String>();
		//String mapStr = "carShipTaxPlatFormFlag=1&randomProposalNo=9345617301473906111955 &editType=NEW&bizType=PROPOSAL&ABflag=&isBICI=01&strCarShipFlag=1&prpCmain.renewalFlag=&GuangdongSysFlag=0&GDREALTIMECARFlag=&GDREALTIMEMOTORFlag=&GDCANCIINFOFlag=0&prpCmain.reinsFlag=0&prpCmain.checkFlag=&prpCmain.othFlag=&prpCmain.dmFlag=&prpCmainCI.dmFlag=&prpCmain.underWriteCode=&prpCmain.underWriteName=&prpCmain.underWriteEndDate=&prpCmain.underWriteFlag=0&prpCmainCI.checkFlag=&prpCmainCI.underWriteFlag=&bizNo=&applyNo=&oldPolicyNo=&bizNoBZ=&bizNoCI=&prpPhead.endorDate=&prpPhead.validDate=&prpPhead.validHour=0&prpPhead.comCode=&sumAmountBI=0&isTaxDemand=1&cIInsureFlag=1&bIInsureFlag=1&ciInsureSwitchKindCode=E01,E11,E12,D01,D02,D03&ciInsureSwitchValues=1111111&cIInsureMotorFlag=1&mtPlatformTime=&noPermissionsCarKindCode=&isTaxFlag=&rePolicyNo=&oldPolicyType=&ZGRS_PURCHASEPRICE=&ZGRS_LOWESTPREMIUM=&clauseFlag=&prpCinsuredOwn_Flag=&prpCinsuredDiv_Flag=&prpCinsuredBon_Flag=&relationType=&ciLimitDays=90&udFlag=&kbFlag=&sbFlag=&xzFlag=&userType=02&noNcheckFlag=&planFlag=0&R_SWITCH=&biStartDate=2016-09-16&ciStartDate=2016-09-16&AGENTSWITCH=1&JFCDSWITCH=19&carShipTaxFlag=11&commissionFlag=&ICCardCHeck=&riskWarningFlag=&comCodePrefix=11&DAGMobilePhoneNum=&scanSwitch=1000000000&haveScanFlag=0&diffDay=90&cylinderFlag=0&ciPlateVersion=&biPlateVersion=&criterionFlag=0&isQuotatonFlag=1&quotationRisk=PUB&getReplenishfactor=&useYear=9&FREEINSURANCEFLAG=011111&isMotoDrunkDriv=0&immediateFlag=1&immediateFlagCI=0&claimAmountReason=&isQueryCarModelFlag=1&isDirectFee=&userCode=1102680006&comCode=11026871&operatorCode=1102680006&chgProfitFlag=00&ciPlatTask=&biPlatTask=&upperCostRateBI=&upperCostRateCI=&useCarshiptaxFlag=1&taxFreeLicenseNo=&isTaxFree=0&premiumChangeFlag=1&operationTimeStamp=2016-09-15 10:21:51&VEHICLEPLAT=1&MOTORFASTTRACK=&motorFastTrack_flag=&MOTORFASTTRACK_INSUREDCODE=&currentDate=2016-09-15&carPremium=0.0&projectBak=&projectCodeBT=&checkTimeFlag=&commissionView=0&checkUndwrt=0&carDamagedNum=&insurePayTimes=&claimAdjustValue=&unitedSaleRelatioStr=&purchasePriceU=&countryNatureU=&purchasePriceUFlag=&startDateU=&endDateU=&biCiFlagU=&biCiFlagIsChange=&biCiDateIsChange=&operatorProjectCode=1-6599,2-6599,4-6599,5-6599&insurancefee_reform=1&vat_switch=1&pm_vehicle_switch=&operateDateForFG=&prpCmainCommon.clauseIssue=2&amountFloat=30&BiLastPolicyFlag=&CiLastPolicyFlag=&CiLastEffectiveDate=&CiLastExpireDate=&benchMarkPremium=&BiLastEffectiveDate=&BiLastExpireDate=&lastTotalPremium=&insuredChangeFlag=0&refreshEadFlag=1&specialflag=&switchFlag=0&relatedFlag=0&riskCode=DAA&prpCmain.riskCode=&riskName=&prpCproposalVo.checkFlag=&prpCproposalVo.underWriteFlag=&prpCproposalVo.strStartDate=&prpCproposalVo.othFlag=&prpCproposalVo.checkUpCode=&prpCproposalVo.operatorCode1=&prpCproposalVo.businessNature=&agentCodeValidType=U&agentCodeValidValue=115192BJ&agentCodeValidIPPer=&qualificationNo=110104784805551006&qualificationName=北京翰文兴业汽车俱乐部有限公司&OLD_STARTDATE_CI=&OLD_ENDDATE_CI=&prpPhead.applyNo=&prpPheadCI.applyNo=&checkCplanFlag=0&is4SFlag=Y&commissionCalculationFlag=0&prpCmainCommon.greyList=&reinComPany=&reinPolicyNo=&reinStartDate=&reinEndDate=&prpCmain.proposalNo=&prpCmainCI.proposalNo=&prpCmain.comCode=11026871&comCodeDes=劲松营业部车险中介业务部&prpCmain.handler1Code=13154727  &handler1CodeDes=栾巍&homePhone=&officePhone=&moblie=&checkHandler1Code=1&handler1CodeDesFlag=&handler1Info=&prpCmainCommon.handler1code_uni=&prpCmain.handlerCode=13154727  &handlerCodeDes=栾巍&homePhonebak=&officePhonebak=&mobliebak=&handler1CodeDesFlagbak=&prpCmainCommon.handlercode_uni=&handlerInfo=&prpCmain.businessNature=3&businessNatureTranslation=兼业代理业务&prpCmain.agentCode=11003O100375&prpCmainagentName=北京翰文兴业汽车俱乐部有限公司&agentType=3O1000&agentCode=11003O100375&prpCmain.operateDate=2016-09-15&Today=2016-09-15&OperateDate=&prpCmain.makeCom=11026871&makeComDes=劲松营业部车险中介业务部&sumPremiumChgFlag=0&prpCmain.sumPremium1=&sumPayTax1=&sunnumber=&prpCmain.startDate=2016-09-16&prpCmain.startHour=0&prpCmain.startMinutes=0&prpCmain.endDate=2017-09-15&prpCmain.endHour=24&prpCmain.startMinutes=0&prpCmainCI.startDate=2016-12-14&prpCmainCI.startHour=0&prpCmainCI.endDate=2017-12-13&prpCmainCI.endHour=24&prpBatchVehicle.id.contractNo=&prpBatchVehicle.id.serialNo=&prpBatchVehicle.motorCadeNo=&prpBatchVehicle.licenseNo=&prpBatchVehicle.licenseType=&prpBatchVehicle.carKindCode=&prpBatchVehicle.proposalNo=&prpBatchVehicle.policyNo=&prpBatchVehicle.sumAmount=&prpBatchVehicle.sumPremium=&prpBatchVehicle.prpProjectCode=&prpBatchVehicle.coinsProjectCode=&prpBatchVehicle.profitProjectCode=&prpBatchVehicle.facProjectCode=&prpBatchVehicle.flag=&prpBatchVehicle.carId=&prpBatchVehicle.versionNo=&prpBatchMain.discountmode=&minusFlag=&paramIndex=&batchCIFlag=&batchBIFlag=&prpCmain.policyNo=&prpCmainCI.policyNo=&prpCmain.contractNo=&prpCmainCI.contractNo=&prpCmain.language=C&prpCmain.checkUpCode=&prpCmainCI.checkUpCode=&pageEndorRecorder.endorFlags=&endorDateEdit=&validDateEdit=&endDateEdit=&endorType=&prpPhead.endorType=&generatePtextFlag=0&generatePtextAgainFlag=0&quotationNo=&quotationFlag=&customerCode=&customerFlag=&prpCfixationTemp.discount=&prpCfixationTemp.id.riskCode=&prpCfixationTemp.profits=&prpCfixationTemp.cost=&prpCfixationTemp.taxorAppend=&prpCfixationTemp.payMentR=&prpCfixationTemp.basePayMentR=&prpCfixationTemp.poundAge=&prpCfixationTemp.basePremium=&prpCfixationTemp.riskPremium=&prpCfixationTemp.riskSumPremium=&prpCfixationTemp.signPremium=&prpCfixationTemp.isQuotation=&prpCfixationTemp.riskClass=&prpCfixationTemp.operationInfo=&prpCfixationTemp.realDisCount=&prpCfixationTemp.realProfits=&prpCfixationTemp.realPayMentR=&prpCfixationTemp.profitClass=&prpCfixationTemp.costRate=&prpCfixationCITemp.discount=&prpCfixationCITemp.id.riskCode=&prpCfixationCITemp.profits=&prpCfixationCITemp.cost=&prpCfixationCITemp.taxorAppend=&prpCfixationCITemp.payMentR=&prpCfixationCITemp.basePayMentR=&prpCfixationCITemp.poundAge=&prpCfixationCITemp.basePremium=&prpCfixationCITemp.riskPremium=&prpCfixationCITemp.riskSumPremium=&prpCfixationCITemp.signPremium=&prpCfixationCITemp.isQuotation=&prpCfixationCITemp.riskClass=&prpCfixationCITemp.operationInfo=&prpCfixationCITemp.realDisCount=&prpCfixationCITemp.realProfits=&prpCfixationCITemp.realPayMentR=&prpCfixationCITemp.remark=&prpCfixationCITemp.responseCode=&prpCfixationCITemp.errorMessage=&prpCfixationCITemp.profitClass=&prpCfixationCITemp.costRate=&prpCsalesFixes_[0].id.proposalNo=&prpCsalesFixes_[0].id.serialNo=&prpCsalesFixes_[0].comCode=&prpCsalesFixes_[0].businessNature=&prpCsalesFixes_[0].riskCode=&prpCsalesFixes_[0].version=&prpCsalesFixes_[0].isForMal=&userCode=1102680006&iProposalNo=&CProposalNo=&timeFlag=&prpCremarks_[0].id.proposalNo=&prpCremarks_[0].id.serialNo=&prpCremarks_[0].operatorCode=1102680006&prpCremarks_[0].remark=&prpCremarks_[0].flag=&prpCremarks_[0].insertTimeForHis=&hidden_index_remark=0&prpCitemCar.monopolyFlag=1&licenseNoCar=&prpCitemCar.carLoanFlag=&carModelPlatFlag=1&updateQuotation=&monopolyConfigsCount=1&monopolyConfigFlag=0&pmCarOwner=&prpCitemCar.monopolyCode=1100729000451&prpCitemCar.monopolyName=北京翰文兴业汽车俱乐部有限公司&queryCarModelInfo=车型信息平台交互&prpCitemCar.id.itemNo=1&oldClauseType=&prpCitemCar.carId=&prpCitemCar.versionNo=&prpCmainCar.newDeviceFlag=&prpCitemCar.otherNature=&prpCitemCar.flag=&newCarFlagValue=2&prpCitemCar.discountType=&prpCitemCar.carLotEquQuality=985&prpCitemCar.colorCode=&prpCitemCar.safeDevice=&prpCitemCar.coefficient1=&prpCitemCar.coefficient2=&prpCitemCar.coefficient3=&prpCitemCar.startSiteName=&prpCitemCar.endSiteName=&prpCitemCar.licenseNo1=&prpCmainCommon.netsales=0&prpCitemCar.licenseFlag=1&prpCitemCar.licenseNo=京PND798&codeLicenseType=LicenseType01,04,LicenseType02,01,LicenseType03,02,LicenseType04,02,LicenseType05,02,LicenseType06,02,LicenseType07,04,LicenseType08,04,LicenseType09,01,LicenseType10,01,LicenseType11,01,LicenseType12,01,LicenseType13,04,LicenseType14,04,LicenseType15,04,	LicenseType16,04,LicenseType17,04,LicenseType18,01,LicenseType19,01,LicenseType20,01,LicenseType21,01,LicenseType22,01,LicenseType23,03,LicenseType24,01,LicenseType25,01,LicenseType31,03,LicenseType32,03,LicenseType90,02&prpCitemCar.licenseType=02&LicenseTypeDes=小型汽车号牌&prpCitemCar.modelCodeAlias=&prpCitemCar.engineNo=9600082-J&prpCitemCar.vinNo=&prpCitemCar.frameNo=LFB0C134296F20165&prpCitemCar.carKindCode=A01&CarKindCodeDes=客车&carKindCodeBak=A01&prpCitemCar.useNatureCode=211&useNatureCodeBak=211&prpCitemCar.clauseType=F42&clauseTypeBak=F42&prpCitemCar.enrollDate=2009-11-4&enrollDateTrue=&prpCitemCar.runMiles=0&prpCitemCar.runAreaCode=11&taxAbateForPlat=&taxAbateForPlatCarModel=&prpCitemCar.modelCode=JFAIAD0045&prpCitemCar.brandName=解放CA6371A4客车&prpCitemCar.modelDemandNo=39PICC02160000000000956205595D&owner=吴云利&prpCitemCar.remark=&PurchasePriceScal=10&prpCitemCar.purchasePrice=29000&CarActualValueTrue=29000&SZpurchasePriceUp=&SZpurchasePriceDown=&purchasePriceF48=200000&purchasePriceUp=100&purchasePriceDown=0&purchasePriceOld=29000&prpCitemCar.countryNature=01&prpCitemCar.seatCount=5&prpCitemCar.tonCount=0&prpCitemCar.exhaustScale=0.970&prpCmainCommon.queryArea=110000&queryArea=北京市&prpCitemCar.carProofType=01&prpCitemCar.carProofNo=&prpCitemCar.carProofDate=&prpCitemCar.fuelType=A&prpCitemCar.newCarFlag=0&prpCitemCar.transferVehicleFlag=0&prpCitemCar.transferDate=&prpCitemCar.licenseColorCode=01&LicenseColorCodeDes=蓝&prpCitemCar.useYears=6&prpCitemCar.carInsuredRelation=1&vehiclePricer=&prpCitemCar.actualValue=14732.00&prpCitemCar.loanVehicleFlag=0&prpCmain.projectCode=&projectCode=&prpCmainCommon.ext3=&importantProjectCode=&prpCitemCar.noNlocalFlag=0&isQuotation=1&prpCitemCar.cylinderCount=&SYFlag=0&MTFlag=0&BMFlag=0&STFlag=0&prpCitemCar.energyType=0&prpCitemCar.isDropinVisitInsure=0&prpCmainChannel.assetAgentName=&prpCmainChannel.assetAgentCode=&prpCmainChannel.assetAgentPhone=&hidden_index_citemcar=0&prpCcarDevices_[0].deviceName=&prpCcarDevices_[0].id.itemNo=1&prpCcarDevices_[0].id.proposalNo=&prpCcarDevices_[0].id.serialNo=&prpCcarDevices_[0].flag=&prpCcarDevices_[0].quantity=&prpCcarDevices_[0].purchasePrice=&prpCcarDevices_[0].buyDate=&prpCcarDevices_[0].actualValue=&configedRepeatTimesLocal=5&prpCmainCommon.ext2=&editFlag=1&prpCinsureds_[0].insuredFlagDes=&prpCinsureds_[0].insuredFlag=&prpCinsureds_[0].id.serialNo=&insuredFlagTemp_[0]=0&insuredFlagTemp_[0]=0&prpCinsureds_[0].insuredType1=1&prpCinsureds_[0].insuredType=1&prpCinsureds_[0].insuredNature=&prpCinsureds_[0].insuredCode=&prpCinsureds_[0].insuredName=&customerURL=http://10.134.136.48:8300/cif&prpCinsureds_[0].unitType=100&prpCinsureds_[0].identifyType1=01&prpCinsureds_[0].identifyType=01&prpCinsureds_[0].identifyNumber=&prpCinsureds_[0].unifiedSocialCreditCode=&prpCinsureds_[0].insuredAddress=&prpCinsureds_[0].email=&prpCinsuredsview_[0].phoneNumber=&prpCinsureds_[0].phoneNumber=&prpCinsureds_[0].postCode=&prpCinsureds_[0].versionNo=&prpCinsureds_[0].auditStatus=&prpCinsureds_[0].auditStatusDes=&prpCinsureds_[0].countryCode=&resident_[0]=&prpCinsureds_[0].flag=&prpCinsureds_[0].appendPrintName=&reLoadFlag[0]=&groupCode_[0]=&isCheckRepeat_[0]=&configedRepeatTimes_[0]=&repeatTimes_[0]=&idCardCheckInfo_[0].insuredcode=&idCardCheckInfo_[0].insuredFlag=&idCardCheckInfo_[0].mobile=&idCardCheckInfo_[0].idcardCode=&idCardCheckInfo_[0].name=&idCardCheckInfo_[0].nation=&idCardCheckInfo_[0].birthday=&idCardCheckInfo_[0].sex=&idCardCheckInfo_[0].address=&idCardCheckInfo_[0].issure=&idCardCheckInfo_[0].validStartDate=&idCardCheckInfo_[0].validEndDate=&idCardCheckInfo_[0].samCode=&idCardCheckInfo_[0].samType=&idCardCheckInfo_[0].flag=0&prpCinsuredsview_[0].mobile=&prpCinsureds_[0].mobile=&prpCinsureds_[0].drivingLicenseNo=&prpCinsureds_[0].drivingCarType=&CarKindLicense=&prpCinsureds_[0].causetroubleTimes=&prpCinsureds_[0].acceptLicenseDate=&prpCinsureds_[0].drivingYears=&prpCinsureds_[0].age=&prpCinsureds_[0].sex=0&prpCinsureds_[0].soldierRelations=&prpCinsureds_[0].soldierRelations1=0&prpCinsureds_[0].soldierIdentifyType=&prpCinsureds_[0].soldierIdentifyType1=000&prpCinsureds_[0].soldierIdentifyNumber=&_insuredFlag=&_insuredFlag_hide=投保人&_insuredFlag=&_insuredFlag_hide=被保险人&_insuredFlag=&_insuredFlag_hide=车主&_insuredFlag_hide=指定驾驶人&_insuredFlag_hide=受益人&_insuredFlag_hide=港澳车车主&_insuredFlag_hide=联系人&_insuredFlag=0&_insuredFlag_hide=委托人&_insuredFlag=0&_insuredFlag_hide=军属军人&updateIndex=-1&prpCinsureds[0].insuredFlagDes=投保人/被保险人/车主&prpCinsureds[0].insuredFlag=111000000000000000000000000000&prpCinsureds[0].id.serialNo=&insuredFlagTemp[0]=0&insuredFlagTemp[0]=0&prpCinsureds[0].insuredType1=1&prpCinsureds[0].insuredType=1&prpCinsureds[0].insuredNature=&prpCinsureds[0].insuredCode=&prpCinsureds[0].insuredName=吴云利&customerURL=http://10.134.136.48:8300/cif&prpCinsureds[0].unitType=100&prpCinsureds[0].identifyType1=01&prpCinsureds[0].identifyType=01&prpCinsureds[0].identifyNumber=&prpCinsureds[0].unifiedSocialCreditCode=&prpCinsureds[0].insuredAddress=&prpCinsureds[0].email=&prpCinsuredsview[0].phoneNumber=&prpCinsureds[0].phoneNumber=&prpCinsureds[0].postCode=&prpCinsureds[0].versionNo=&prpCinsureds[0].auditStatus=&prpCinsureds[0].auditStatusDes=&prpCinsureds[0].countryCode=CHN&resident[0]=0&prpCinsureds[0].flag=&prpCinsureds[0].appendPrintName=&reLoadFlag[0]=&groupCode[0]=&isCheckRepeat[0]=&configedRepeatTimes[0]=&repeatTimes[0]=&idCardCheckInfo[0].insuredcode=&idCardCheckInfo[0].insuredFlag=&idCardCheckInfo[0].mobile=&idCardCheckInfo[0].idcardCode=&idCardCheckInfo[0].name=&idCardCheckInfo[0].nation=&idCardCheckInfo[0].birthday=&idCardCheckInfo[0].sex=&idCardCheckInfo[0].address=&idCardCheckInfo[0].issure=&idCardCheckInfo[0].validStartDate=&idCardCheckInfo[0].validEndDate=&idCardCheckInfo[0].samCode=&idCardCheckInfo[0].samType=&idCardCheckInfo[0].flag=0&prpCinsuredsview[0].mobile=&prpCinsureds[0].mobile=&prpCinsureds[0].drivingLicenseNo=&prpCinsureds[0].drivingCarType=&CarKindLicense=&prpCinsureds[0].causetroubleTimes=&prpCinsureds[0].acceptLicenseDate=&prpCinsureds[0].drivingYears=&prpCinsureds[0].age=&prpCinsureds[0].sex=0&prpCinsureds[0].soldierRelations=&prpCinsureds[0].soldierRelations1=0&prpCinsureds[0].soldierIdentifyType=&prpCinsureds[0].soldierIdentifyType1=000&prpCinsureds[0].soldierIdentifyNumber=&hidden_index_insured=1&prpCmainCar.agreeDriverFlag=&prpBatchProposal.profitType=&insurancefee_reform=1&prpCprofitDetailsTemp_[0].chooseFlag=on&prpCprofitDetailsTemp_[0].profitName=&prpCprofitDetailsTemp_[0].condition=&profitRateTemp_[0]=&prpCprofitDetailsTemp_[0].profitRate=&prpCprofitDetailsTemp_[0].profitRateMin=&prpCprofitDetailsTemp_[0].profitRateMax=&prpCprofitDetailsTemp_[0].id.proposalNo=&prpCprofitDetailsTemp_[0].id.itemKindNo=&prpCprofitDetailsTemp_[0].id.profitCode=&prpCprofitDetailsTemp_[0].id.serialNo=1&prpCprofitDetailsTemp_[0].id.profitType=&prpCprofitDetailsTemp_[0].kindCode=&prpCprofitDetailsTemp_[0].conditionCode=&prpCprofitDetailsTemp_[0].flag=&motorFastTrack_Amount=&prpCprofitFactorsTemp_[0].chooseFlag=on&serialNo_[0]=&prpCprofitFactorsTemp_[0].profitName=&prpCprofitFactorsTemp_[0].condition=&rateTemp_[0]=&prpCprofitFactorsTemp_[0].rate=&prpCprofitFactorsTemp_[0].lowerRate=&prpCprofitFactorsTemp_[0].upperRate=&prpCprofitFactorsTemp_[0].id.profitCode=&prpCprofitFactorsTemp_[0].id.conditionCode=&prpCprofitFactorsTemp_[0].flag=&prpCitemKind.shortRateFlag=2&prpCitemKind.shortRate=100&prpCitemKind.currency=CNY&prpCmain.sumPremiumTemp=&prpCitemKindCI.shortRate=100&prpCitemKindCI.familyNo=1&cIBPFlag=1&prpCitemKindCI.unitAmount=0&prpCitemKindCI.id.itemKindNo=&prpCitemKindCI.kindCode=050100&prpCitemKindCI.clauseCode=050001&prpCitemKindCI.riskPremium=&prpCitemKindCI.kindName=机动车交通事故强制责任保险&prpCitemKindCI.calculateFlag=Y&prpCitemKindCI.quantity=1&prpCitemKindCI.amount=122000&prpCitemKindCI.deductible=&prpCitemKindCI.adjustRate=0.9&prpCitemKindCI.rate=0&prpCitemKindCI.benchMarkPremium=0&prpCitemKindCI.disCount=1&prpCmainCI.sumPremium=&prpCitemKindCI.premium=&prpCitemKindCI.flag=&prpCitemKindCI.netPremium=&prpCitemKindCI.taxPremium=&prpCitemKindCI.taxRate=&prpCitemKindCI.dutyFlag=&passengersSwitchFlag=&sumBenchPremium=&prpCmain.discount=&prpCmain.sumPremium=&premiumF48=5000&prpCmain.sumNetPremium=&prpCmain.sumTaxPremium=&premiumF48=5000&prpCmainCommon.groupFlag=0&prpCmain.preDiscount=&prpCitemKindsTemp[0].itemKindNo=&prpCitemKindsTemp[0].basePremium=&prpCitemKindsTemp[0].riskPremium=&prpCitemKindsTemp[0].clauseCode=050051&prpCitemKindsTemp[0].kindCode=050202&prpCitemKindsTemp[0].kindName=机动车损失保险&prpCitemKindsTemp[0].deductible=0.00&prpCitemKindsTemp[0].deductibleRate=&prpCitemKindsTemp[0].pureRiskPremium=&prpCitemKindsTemp[0].amount=&prpCitemKindsTemp[0].calculateFlag=Y11Y000&prpCitemKindsTemp[0].startDate=&prpCitemKindsTemp[0].startHour=&prpCitemKindsTemp[0].endDate=&prpCitemKindsTemp[0].endHour=&relateSpecial[0]=050930&prpCitemKindsTemp[0].flag= 100000&prpCitemKindsTemp[0].rate=&prpCitemKindsTemp[0].benchMarkPremium=&prpCitemKindsTemp[0].disCount=&prpCitemKindsTemp[0].premium=&prpCitemKindsTemp[0].netPremium=&prpCitemKindsTemp[0].taxPremium=&prpCitemKindsTemp[0].taxRate=&prpCitemKindsTemp[0].dutyFlag=&prpCitemKindsTemp[1].itemKindNo=&prpCitemKindsTemp[1].basePremium=&prpCitemKindsTemp[1].riskPremium=&prpCitemKindsTemp[1].clauseCode=050054&prpCitemKindsTemp[1].kindCode=050501&prpCitemKindsTemp[1].kindName=盗抢险&prpCitemKindsTemp[1].unitAmount=&prpCitemKindsTemp[1].quantity=&prpCitemKindsTemp[1].amount=14732.00&prpCitemKindsTemp[1].calculateFlag=N11Y000&prpCitemKindsTemp[1].startDate=&prpCitemKindsTemp[1].startHour=&prpCitemKindsTemp[1].endDate=&prpCitemKindsTemp[1].endHour=&relateSpecial[1]=050932&prpCitemKindsTemp[1].flag= 100000&prpCitemKindsTemp[1].rate=&prpCitemKindsTemp[1].benchMarkPremium=&prpCitemKindsTemp[1].disCount=&prpCitemKindsTemp[1].premium=&prpCitemKindsTemp[1].netPremium=&prpCitemKindsTemp[1].taxPremium=&prpCitemKindsTemp[1].taxRate=&prpCitemKindsTemp[1].dutyFlag=&prpCitemKindsTemp[2].itemKindNo=&prpCitemKindsTemp[2].basePremium=&prpCitemKindsTemp[2].riskPremium=&prpCitemKindsTemp[2].clauseCode=050052&prpCitemKindsTemp[2].kindCode=050602&prpCitemKindsTemp[2].kindName=第三者责任保险&prpCitemKindsTemp[2].unitAmount=&prpCitemKindsTemp[2].quantity=&prpCitemKindsTemp[2].amount=&prpCitemKindsTemp[2].calculateFlag=Y21Y000&prpCitemKindsTemp[2].startDate=&prpCitemKindsTemp[2].startHour=&prpCitemKindsTemp[2].endDate=&prpCitemKindsTemp[2].endHour=&relateSpecial[2]=050931&prpCitemKindsTemp[2].flag= 100000&prpCitemKindsTemp[2].rate=&prpCitemKindsTemp[2].benchMarkPremium=&prpCitemKindsTemp[2].disCount=&prpCitemKindsTemp[2].premium=&prpCitemKindsTemp[2].netPremium=&prpCitemKindsTemp[2].taxPremium=&prpCitemKindsTemp[2].taxRate=&prpCitemKindsTemp[2].dutyFlag=&prpCitemKindsTemp[3].itemKindNo=&prpCitemKindsTemp[3].basePremium=&prpCitemKindsTemp[3].riskPremium=&prpCitemKindsTemp[3].clauseCode=050053&prpCitemKindsTemp[3].kindCode=050711&prpCitemKindsTemp[3].kindName=车上人员责任险（司机）&prpCitemKindsTemp[3].unitAmount=&prpCitemKindsTemp[3].quantity=&prpCitemKindsTemp[3].amount=&prpCitemKindsTemp[3].calculateFlag=Y21Y00&prpCitemKindsTemp[3].startDate=&prpCitemKindsTemp[3].startHour=&prpCitemKindsTemp[3].endDate=&prpCitemKindsTemp[3].endHour=&relateSpecial[3]=050933&prpCitemKindsTemp[3].flag= 100000&prpCitemKindsTemp[3].rate=&prpCitemKindsTemp[3].benchMarkPremium=&prpCitemKindsTemp[3].disCount=&prpCitemKindsTemp[3].premium=&prpCitemKindsTemp[3].netPremium=&prpCitemKindsTemp[3].taxPremium=&prpCitemKindsTemp[3].taxRate=&prpCitemKindsTemp[3].dutyFlag=&prpCitemKindsTemp[4].itemKindNo=&prpCitemKindsTemp[4].basePremium=&prpCitemKindsTemp[4].riskPremium=&prpCitemKindsTemp[4].clauseCode=050053&prpCitemKindsTemp[4].kindCode=050712&prpCitemKindsTemp[4].kindName=车上人员责任险（乘客）&prpCitemKindsTemp[4].unitAmount=&prpCitemKindsTemp[4].quantity=&prpCitemKindsTemp[4].amount=&prpCitemKindsTemp[4].calculateFlag=Y21Y00&prpCitemKindsTemp[4].startDate=&prpCitemKindsTemp[4].startHour=&prpCitemKindsTemp[4].endDate=&prpCitemKindsTemp[4].endHour=&relateSpecial[4]=050934&prpCitemKindsTemp[4].flag= 100000&prpCitemKindsTemp[4].rate=&prpCitemKindsTemp[4].benchMarkPremium=&prpCitemKindsTemp[4].disCount=&prpCitemKindsTemp[4].premium=&prpCitemKindsTemp[4].netPremium=&prpCitemKindsTemp[4].taxPremium=&prpCitemKindsTemp[4].taxRate=&prpCitemKindsTemp[4].dutyFlag=&prpCitemKindsTemp[5].itemKindNo=&kindcodesub=&prpCitemKindsTemp[5].clauseCode=050059&prpCitemKindsTemp[5].kindCode=050211&relateSpecial[5]=050937&prpCitemKindsTemp[5].basePremium=&prpCitemKindsTemp[5].riskPremium=&prpCitemKindsTemp[5].kindName=车身划痕损失险&prpCitemKindsTemp[5].amount=2000.00&prpCitemKindsTemp[5].calculateFlag=N12Y000&prpCitemKindsTemp[5].startDate=&prpCitemKindsTemp[5].startHour=&prpCitemKindsTemp[5].endDate=&prpCitemKindsTemp[5].endHour=&prpCitemKindsTemp[5].flag= 200000&prpCitemKindsTemp[5].rate=&prpCitemKindsTemp[5].benchMarkPremium=&prpCitemKindsTemp[5].disCount=&prpCitemKindsTemp[5].premium=&prpCitemKindsTemp[5].netPremium=&prpCitemKindsTemp[5].taxPremium=&prpCitemKindsTemp[5].taxRate=&prpCitemKindsTemp[5].dutyFlag=&prpCitemKindsTemp[6].itemKindNo=&kindcodesub=&prpCitemKindsTemp[6].clauseCode=050056&prpCitemKindsTemp[6].kindCode=050232&relateSpecial[6]=      &prpCitemKindsTemp[6].basePremium=&prpCitemKindsTemp[6].riskPremium=&prpCitemKindsTemp[6].kindName=玻璃单独破碎险&prpCitemKindsTemp[6].modeCode=10&prpCitemKindsTemp[6].amount=&prpCitemKindsTemp[6].calculateFlag=N32N000&prpCitemKindsTemp[6].startDate=&prpCitemKindsTemp[6].startHour=&prpCitemKindsTemp[6].endDate=&prpCitemKindsTemp[6].endHour=&prpCitemKindsTemp[6].flag= 200000&prpCitemKindsTemp[6].rate=&prpCitemKindsTemp[6].benchMarkPremium=&prpCitemKindsTemp[6].disCount=&prpCitemKindsTemp[6].premium=&prpCitemKindsTemp[6].netPremium=&prpCitemKindsTemp[6].taxPremium=&prpCitemKindsTemp[6].taxRate=&prpCitemKindsTemp[6].dutyFlag=&prpCitemKindsTemp[7].itemKindNo=&kindcodesub=&prpCitemKindsTemp[7].clauseCode=050065&prpCitemKindsTemp[7].kindCode=050253&relateSpecial[7]=      &prpCitemKindsTemp[7].basePremium=&prpCitemKindsTemp[7].riskPremium=&prpCitemKindsTemp[7].kindName=指定修理厂险&prpCitemKindsTemp[7].amount=&prpCitemKindsTemp[7].calculateFlag=N32N000&prpCitemKindsTemp[7].startDate=&prpCitemKindsTemp[7].startHour=&prpCitemKindsTemp[7].endDate=&prpCitemKindsTemp[7].endHour=&prpCitemKindsTemp[7].flag= 200000&prpCitemKindsTemp[7].rate=&prpCitemKindsTemp[7].benchMarkPremium=&prpCitemKindsTemp[7].disCount=&prpCitemKindsTemp[7].premium=&prpCitemKindsTemp[7].netPremium=&prpCitemKindsTemp[7].taxPremium=&prpCitemKindsTemp[7].taxRate=&prpCitemKindsTemp[7].dutyFlag=&prpCitemKindsTemp[8].itemKindNo=&kindcodesub=&prpCitemKindsTemp[8].clauseCode=050057&prpCitemKindsTemp[8].kindCode=050311&relateSpecial[8]=050935&prpCitemKindsTemp[8].basePremium=&prpCitemKindsTemp[8].riskPremium=&prpCitemKindsTemp[8].kindName=自燃损失险&prpCitemKindsTemp[8].amount=14732.00&prpCitemKindsTemp[8].calculateFlag=N12Y000&prpCitemKindsTemp[8].startDate=&prpCitemKindsTemp[8].startHour=&prpCitemKindsTemp[8].endDate=&prpCitemKindsTemp[8].endHour=&prpCitemKindsTemp[8].flag= 200000&prpCitemKindsTemp[8].rate=&prpCitemKindsTemp[8].benchMarkPremium=&prpCitemKindsTemp[8].disCount=&prpCitemKindsTemp[8].premium=&prpCitemKindsTemp[8].netPremium=&prpCitemKindsTemp[8].taxPremium=&prpCitemKindsTemp[8].taxRate=&prpCitemKindsTemp[8].dutyFlag=&prpCitemKindsTemp[9].itemKindNo=&kindcodesub=&prpCitemKindsTemp[9].clauseCode=050060&prpCitemKindsTemp[9].kindCode=050461&relateSpecial[9]=050938&prpCitemKindsTemp[9].basePremium=&prpCitemKindsTemp[9].riskPremium=&prpCitemKindsTemp[9].kindName=发动机涉水损失险&prpCitemKindsTemp[9].amount=&prpCitemKindsTemp[9].calculateFlag=N32Y000&prpCitemKindsTemp[9].startDate=&prpCitemKindsTemp[9].startHour=&prpCitemKindsTemp[9].endDate=&prpCitemKindsTemp[9].endHour=&prpCitemKindsTemp[9].flag= 200000&prpCitemKindsTemp[9].rate=&prpCitemKindsTemp[9].benchMarkPremium=&prpCitemKindsTemp[9].disCount=&prpCitemKindsTemp[9].premium=&prpCitemKindsTemp[9].netPremium=&prpCitemKindsTemp[9].taxPremium=&prpCitemKindsTemp[9].taxRate=&prpCitemKindsTemp[9].dutyFlag=&sumItemKindSpecial=&prpCitemKindsTemp[10].clauseCode=050066&prpCitemKindsTemp[10].kindCode=050917&prpCitemKindsTemp[10].itemKindNo=&kindcodesub=&prpCitemKindsTemp[10].basePremium=&prpCitemKindsTemp[10].riskPremium=&prpCitemKindsTemp[10].kindName=不计免赔险（精神损害抚慰金责任险）&prpCitemKindsTemp[10].amount=&prpCitemKindsTemp[10].calculateFlag=N33N000&prpCitemKindsTemp[10].startDate=&prpCitemKindsTemp[10].startHour=&prpCitemKindsTemp[10].endDate=&prpCitemKindsTemp[10].endHour=&prpCitemKindsTemp[10].flag= 200000&prpCitemKindsTemp[10].rate=&prpCitemKindsTemp[10].benchMarkPremium=&prpCitemKindsTemp[10].disCount=&prpCitemKindsTemp[10].premium=&prpCitemKindsTemp[10].netPremium=&prpCitemKindsTemp[10].taxPremium=&prpCitemKindsTemp[10].taxRate=&prpCitemKindsTemp[10].dutyFlag=&prpCitemKindsTemp[11].clauseCode=050066&prpCitemKindsTemp[11].kindCode=050930&prpCitemKindsTemp[11].itemKindNo=&kindcodesub=&prpCitemKindsTemp[11].basePremium=&prpCitemKindsTemp[11].riskPremium=&prpCitemKindsTemp[11].kindName=不计免赔险（车损险）&prpCitemKindsTemp[11].amount=&prpCitemKindsTemp[11].calculateFlag=N33N000&prpCitemKindsTemp[11].startDate=&prpCitemKindsTemp[11].startHour=&prpCitemKindsTemp[11].endDate=&prpCitemKindsTemp[11].endHour=&prpCitemKindsTemp[11].flag= 200000&prpCitemKindsTemp[11].rate=&prpCitemKindsTemp[11].benchMarkPremium=&prpCitemKindsTemp[11].disCount=&prpCitemKindsTemp[11].premium=&prpCitemKindsTemp[11].netPremium=&prpCitemKindsTemp[11].taxPremium=&prpCitemKindsTemp[11].taxRate=&prpCitemKindsTemp[11].dutyFlag=&prpCitemKindsTemp[12].clauseCode=050066&prpCitemKindsTemp[12].kindCode=050931&prpCitemKindsTemp[12].itemKindNo=&kindcodesub=&prpCitemKindsTemp[12].basePremium=&prpCitemKindsTemp[12].riskPremium=&prpCitemKindsTemp[12].kindName=不计免赔险（三者险）&prpCitemKindsTemp[12].amount=&prpCitemKindsTemp[12].calculateFlag=N33N000&prpCitemKindsTemp[12].startDate=&prpCitemKindsTemp[12].startHour=&prpCitemKindsTemp[12].endDate=&prpCitemKindsTemp[12].endHour=&prpCitemKindsTemp[12].flag= 200000&prpCitemKindsTemp[12].rate=&prpCitemKindsTemp[12].benchMarkPremium=&prpCitemKindsTemp[12].disCount=&prpCitemKindsTemp[12].premium=&prpCitemKindsTemp[12].netPremium=&prpCitemKindsTemp[12].taxPremium=&prpCitemKindsTemp[12].taxRate=&prpCitemKindsTemp[12].dutyFlag=&prpCitemKindsTemp[13].clauseCode=050066&prpCitemKindsTemp[13].kindCode=050932&prpCitemKindsTemp[13].itemKindNo=&kindcodesub=&prpCitemKindsTemp[13].basePremium=&prpCitemKindsTemp[13].riskPremium=&prpCitemKindsTemp[13].kindName=不计免赔险（盗抢险）&prpCitemKindsTemp[13].amount=&prpCitemKindsTemp[13].calculateFlag=N33N000&prpCitemKindsTemp[13].startDate=&prpCitemKindsTemp[13].startHour=&prpCitemKindsTemp[13].endDate=&prpCitemKindsTemp[13].endHour=&prpCitemKindsTemp[13].flag= 200000&prpCitemKindsTemp[13].rate=&prpCitemKindsTemp[13].benchMarkPremium=&prpCitemKindsTemp[13].disCount=&prpCitemKindsTemp[13].premium=&prpCitemKindsTemp[13].netPremium=&prpCitemKindsTemp[13].taxPremium=&prpCitemKindsTemp[13].taxRate=&prpCitemKindsTemp[13].dutyFlag=&prpCitemKindsTemp[14].clauseCode=050066&prpCitemKindsTemp[14].kindCode=050933&prpCitemKindsTemp[14].itemKindNo=&kindcodesub=&prpCitemKindsTemp[14].basePremium=&prpCitemKindsTemp[14].riskPremium=&prpCitemKindsTemp[14].kindName=不计免赔险（车上人员（司机））&prpCitemKindsTemp[14].amount=&prpCitemKindsTemp[14].calculateFlag=N33N000&prpCitemKindsTemp[14].startDate=&prpCitemKindsTemp[14].startHour=&prpCitemKindsTemp[14].endDate=&prpCitemKindsTemp[14].endHour=&prpCitemKindsTemp[14].flag= 200000&prpCitemKindsTemp[14].rate=&prpCitemKindsTemp[14].benchMarkPremium=&prpCitemKindsTemp[14].disCount=&prpCitemKindsTemp[14].premium=&prpCitemKindsTemp[14].netPremium=&prpCitemKindsTemp[14].taxPremium=&prpCitemKindsTemp[14].taxRate=&prpCitemKindsTemp[14].dutyFlag=&prpCitemKindsTemp[15].clauseCode=050066&prpCitemKindsTemp[15].kindCode=050934&prpCitemKindsTemp[15].itemKindNo=&kindcodesub=&prpCitemKindsTemp[15].basePremium=&prpCitemKindsTemp[15].riskPremium=&prpCitemKindsTemp[15].kindName=不计免赔险（车上人员（乘客））&prpCitemKindsTemp[15].amount=&prpCitemKindsTemp[15].calculateFlag=N33N000&prpCitemKindsTemp[15].startDate=&prpCitemKindsTemp[15].startHour=&prpCitemKindsTemp[15].endDate=&prpCitemKindsTemp[15].endHour=&prpCitemKindsTemp[15].flag= 200000&prpCitemKindsTemp[15].rate=&prpCitemKindsTemp[15].benchMarkPremium=&prpCitemKindsTemp[15].disCount=&prpCitemKindsTemp[15].premium=&prpCitemKindsTemp[15].netPremium=&prpCitemKindsTemp[15].taxPremium=&prpCitemKindsTemp[15].taxRate=&prpCitemKindsTemp[15].dutyFlag=&prpCitemKindsTemp[16].clauseCode=050066&prpCitemKindsTemp[16].kindCode=050935&prpCitemKindsTemp[16].itemKindNo=&kindcodesub=&prpCitemKindsTemp[16].basePremium=&prpCitemKindsTemp[16].riskPremium=&prpCitemKindsTemp[16].kindName=不计免赔险（自燃损失险）&prpCitemKindsTemp[16].amount=&prpCitemKindsTemp[16].calculateFlag=N33N000&prpCitemKindsTemp[16].startDate=&prpCitemKindsTemp[16].startHour=&prpCitemKindsTemp[16].endDate=&prpCitemKindsTemp[16].endHour=&prpCitemKindsTemp[16].flag= 200000&prpCitemKindsTemp[16].rate=&prpCitemKindsTemp[16].benchMarkPremium=&prpCitemKindsTemp[16].disCount=&prpCitemKindsTemp[16].premium=&prpCitemKindsTemp[16].netPremium=&prpCitemKindsTemp[16].taxPremium=&prpCitemKindsTemp[16].taxRate=&prpCitemKindsTemp[16].dutyFlag=&prpCitemKindsTemp[17].clauseCode=050066&prpCitemKindsTemp[17].kindCode=050936&prpCitemKindsTemp[17].itemKindNo=&kindcodesub=&prpCitemKindsTemp[17].basePremium=&prpCitemKindsTemp[17].riskPremium=&prpCitemKindsTemp[17].kindName=不计免赔险（新增设备损失险）&prpCitemKindsTemp[17].amount=&prpCitemKindsTemp[17].calculateFlag=N33N000&prpCitemKindsTemp[17].startDate=&prpCitemKindsTemp[17].startHour=&prpCitemKindsTemp[17].endDate=&prpCitemKindsTemp[17].endHour=&prpCitemKindsTemp[17].flag= 200000&prpCitemKindsTemp[17].rate=&prpCitemKindsTemp[17].benchMarkPremium=&prpCitemKindsTemp[17].disCount=&prpCitemKindsTemp[17].premium=&prpCitemKindsTemp[17].netPremium=&prpCitemKindsTemp[17].taxPremium=&prpCitemKindsTemp[17].taxRate=&prpCitemKindsTemp[17].dutyFlag=&prpCitemKindsTemp[18].clauseCode=050066&prpCitemKindsTemp[18].kindCode=050937&prpCitemKindsTemp[18].itemKindNo=&kindcodesub=&prpCitemKindsTemp[18].basePremium=&prpCitemKindsTemp[18].riskPremium=&prpCitemKindsTemp[18].kindName=不计免赔险（车身划痕损失险）&prpCitemKindsTemp[18].amount=&prpCitemKindsTemp[18].calculateFlag=N33N000&prpCitemKindsTemp[18].startDate=&prpCitemKindsTemp[18].startHour=&prpCitemKindsTemp[18].endDate=&prpCitemKindsTemp[18].endHour=&prpCitemKindsTemp[18].flag= 200000&prpCitemKindsTemp[18].rate=&prpCitemKindsTemp[18].benchMarkPremium=&prpCitemKindsTemp[18].disCount=&prpCitemKindsTemp[18].premium=&prpCitemKindsTemp[18].netPremium=&prpCitemKindsTemp[18].taxPremium=&prpCitemKindsTemp[18].taxRate=&prpCitemKindsTemp[18].dutyFlag=&prpCitemKindsTemp[19].clauseCode=050066&prpCitemKindsTemp[19].kindCode=050938&prpCitemKindsTemp[19].itemKindNo=&kindcodesub=&prpCitemKindsTemp[19].basePremium=&prpCitemKindsTemp[19].riskPremium=&prpCitemKindsTemp[19].kindName=不计免赔险（发动机涉水损失险）&prpCitemKindsTemp[19].amount=&prpCitemKindsTemp[19].calculateFlag=N33N000&prpCitemKindsTemp[19].startDate=&prpCitemKindsTemp[19].startHour=&prpCitemKindsTemp[19].endDate=&prpCitemKindsTemp[19].endHour=&prpCitemKindsTemp[19].flag= 200000&prpCitemKindsTemp[19].rate=&prpCitemKindsTemp[19].benchMarkPremium=&prpCitemKindsTemp[19].disCount=&prpCitemKindsTemp[19].premium=&prpCitemKindsTemp[19].netPremium=&prpCitemKindsTemp[19].taxPremium=&prpCitemKindsTemp[19].taxRate=&prpCitemKindsTemp[19].dutyFlag=&prpCitemKindsTemp[20].clauseCode=050066&prpCitemKindsTemp[20].kindCode=050939&prpCitemKindsTemp[20].itemKindNo=&kindcodesub=&prpCitemKindsTemp[20].basePremium=&prpCitemKindsTemp[20].riskPremium=&prpCitemKindsTemp[20].kindName=不计免赔险（车上货物责任险）&prpCitemKindsTemp[20].amount=&prpCitemKindsTemp[20].calculateFlag=N33N000&prpCitemKindsTemp[20].startDate=&prpCitemKindsTemp[20].startHour=&prpCitemKindsTemp[20].endDate=&prpCitemKindsTemp[20].endHour=&prpCitemKindsTemp[20].flag= 200000&prpCitemKindsTemp[20].rate=&prpCitemKindsTemp[20].benchMarkPremium=&prpCitemKindsTemp[20].disCount=&prpCitemKindsTemp[20].premium=&prpCitemKindsTemp[20].netPremium=&prpCitemKindsTemp[20].taxPremium=&prpCitemKindsTemp[20].taxRate=&prpCitemKindsTemp[20].dutyFlag=&hidden_index_itemKind=21&totalIndex=21&prpCitemKindsTemp_[0].chooseFlag=on&prpCitemKindsTemp_[0].basePremium=&prpCitemKindsTemp_[0].riskPremium=&prpCitemKindsTemp_[0].itemKindNo=&prpCitemKindsTemp_[0].startDate=&prpCitemKindsTemp_[0].kindCode=&prpCitemKindsTemp_[0].kindName=&prpCitemKindsTemp_[0].startHour=&prpCitemKindsTemp_[0].endDate=&prpCitemKindsTemp_[0].calculateFlag=&relateSpecial_[0]=&prpCitemKindsTemp_[0].clauseCode=&prpCitemKindsTemp_[0].flag=&prpCitemKindsTemp_[0].amount=&prpCitemKindsTemp_[0].rate=&prpCitemKindsTemp_[0].benchMarkPremium=&prpCitemKindsTemp_[0].disCount=&prpCitemKindsTemp_[0].premium=&prpCitemKindsTemp_[0].netPremium=&prpCitemKindsTemp_[0].taxPremium=&prpCitemKindsTemp_[0].taxRate=&prpCitemKindsTemp_[0].dutyFlag=&prpCitemKindsTemp_[0].unitAmount=&prpCitemKindsTemp_[0].quantity=&prpCitemKindsTemp_[0].value=&prpCitemKindsTemp_[0].value=50&prpCitemKindsTemp_[0].unitAmount=&prpCitemKindsTemp_[0].quantity=&prpCitemKindsTemp_[0].modeCode=10&prpCitemKindsTemp_[0].modeCode=1&prpCitemKindsTemp_[0].modeCode=1&prpCitemKindsTemp_[0].value=1000&prpCitemKindsTemp_[0].amount=2000&prpCitemKindsTemp_[0].amount=2000&prpCitemKindsTemp_[0].amount=10000&prpCitemKindsTemp_[0].unitAmount=&prpCitemKindsTemp_[0].quantity=60&prpCitemKindsTemp_[0].unitAmount=&prpCitemKindsTemp_[0].quantity=90&prpCitemKindsTemp_[0].amount=2000.00&prpCitemKindsTemp_[0].amount=&prpCitemKindsTemp_[0].amount=50000.00&prpCitemKindsTemp_[0].amount=10000.00&prpCitemKindsTemp_[0].amount=5000.00&itemKindLoadFlag=&MealFlag=0&MealFirstFlag=0&_taxUnit=&prpCcarShipTax.sumPayTax=&iniPrpCcarShipTax_Flag=&prpCcarShipTax.taxType=1&prpCcarShipTax.carLotEquQuality=985&prpCcarShipTax.calculateMode=C1&prpCcarShipTax.leviedDate=&prpCcarShipTax.taxPayerIdentNo=&prpCcarShipTax.carKindCode=A01&prpCcarShipTax.model=B11&prpCcarShipTax.taxPayerNumber=&prpCcarShipTax.taxPayerCode=&prpCcarShipTax.id.itemNo=1&prpCcarShipTax.taxPayerNature=3&prpCcarShipTax.taxPayerName=&prpCcarShipTax.taxComCode=&prpCcarShipTax.taxComName=&prpCcarShipTax.taxExplanation=&prpCcarShipTax.taxUnit=&prpCcarShipTax.taxUnitAmount=&prpCcarShipTax.taxAbateReason=&prpCcarShipTax.dutyPaidProofNo_1=&prpCcarShipTax.dutyPaidProofNo_2=&prpCcarShipTax.dutyPaidProofNo=&prpCcarShipTax.taxAbateRate=&prpCcarShipTax.taxAbateAmount=&prpCcarShipTax.taxAbateType=1&prpCcarShipTax.prePayTaxYear=2015&prpCcarShipTax.prePolicyEndDate=&prpCcarShipTax.payStartDate=&prpCcarShipTax.payEndDate=&prpCcarShipTax.thisPayTax=&prpCcarShipTax.prePayTax=&prpCcarShipTax.delayPayTax=&prpCcarShipTax.taxItemCode=&prpCcarShipTax.taxItemName=&prpCcarShipTax.baseTaxation=&prpCcarShipTax.taxRelifFlag=&CarShipInit_Flag=&prpCcarShipTax.flag=&quotationtaxPayerCode=&BIdemandNo=&BIdemandTime=&bIRiskWarningType=&noDamageYearsBIPlat=0&prpCitemCarExt.lastDamagedBI=0&lastDamagedBITemp=0&DAZlastDamagedBI=&prpCitemCarExt.thisDamagedBI=0&prpCitemCarExt.noDamYearsBI=0&noDamYearsBINumber=0&prpCitemCarExt.lastDamagedCI=0&BIDemandClaim_Flag=&BiInsureDemandPay_[0].id.serialNo=&BiInsureDemandPay_[0].payCompany=&BiInsureDemandPay_[0].claimregistrationno=&BiInsureDemandPay_[0].compensateNo=&BiInsureDemandPay_[0].lossTime=&BiInsureDemandPay_[0].endcCaseTime=&PrpCmain_[0].startDate=&PrpCmain_[0].endDate=&BiInsureDemandPay_[0].lossFee=&BiInsureDemandPay_[0].payType=&BiInsureDemandPay_[0].personpayType=&BiInsureDemandLoss_[0].id.serialNo=&BiInsureDemandLoss_[0].peccancyid=&BiInsureDemandLoss_[0].lossAction=&BiInsureDemandLoss_[0].lossType=&BiInsureDemandLoss_[0].lossTime=&BiInsureDemandLoss_[0].lossDddress=&BiInsureDemandLoss_[0].decisionid=&BiInsureDemandLoss_[0].lossAcceptDate=&bIRiskWarningClaimItems_[0].id.serialNo=&bIRiskWarningClaimItems_[0].riskWarningType=&bIRiskWarningClaimItems_[0].claimSequenceNo=&bIRiskWarningClaimItems_[0].insurerCode=&bIRiskWarningClaimItems_[0].lossTime=&bIRiskWarningClaimItems_[0].lossArea=&prpCtrafficDetails_[0].trafficType=1&prpCtrafficDetails_[0].accidentType=1&prpCtrafficDetails_[0].indemnityDuty=有责&prpCtrafficDetails_[0].sumPaid=&prpCtrafficDetails_[0].accidentDate=&prpCtrafficDetails_[0].payComCode=&prpCtrafficDetails_[0].flag=&prpCtrafficDetails_[0].id.serialNo=&prpCtrafficDetails_[0].trafficType=1&prpCtrafficDetails_[0].accidentType=1&prpCtrafficDetails_[0].indemnityDuty=有责&prpCtrafficDetails_[0].sumPaid=&prpCtrafficDetails_[0].accidentDate=&prpCtrafficDetails_[0].payComCode=&prpCtrafficDetails_[0].flag=&prpCtrafficDetails_[0].id.serialNo=&prpCitemCarExt_CI.rateRloatFlag=01&prpCitemCarExt_CI.noDamYearsCI=1&prpCitemCarExt_CI.lastDamagedCI=0&prpCitemCarExt_CI.damFloatRatioCI=0&prpCitemCarExt_CI.offFloatRatioCI=0&prpCitemCarExt_CI.thisDamagedCI=0&prpCitemCarExt_CI.flag=&hidden_index_ctraffic_NOPlat_Drink=0&hidden_index_ctraffic_NOPlat=0&ciInsureDemand.demandNo=&ciInsureDemand.demandTime=&ciInsureDemand.restricFlag=&ciInsureDemand.preferentialDay=&ciInsureDemand.preferentialPremium=&ciInsureDemand.preferentialFormula =&ciInsureDemand.lastyearenddate=&prpCitemCar.noDamageYears=0&ciInsureDemand.rateRloatFlag=00&ciInsureDemand.claimAdjustReason=A1&ciInsureDemand.peccancyAdjustReason=V1&cIRiskWarningType=&CIDemandFecc_Flag=&ciInsureDemandLoss_[0].id.serialNo=&ciInsureDemandLoss_[0].lossTime=&ciInsureDemandLoss_[0].lossDddress=&ciInsureDemandLoss_[0].lossAction=&ciInsureDemandLoss_[0].coeff=&ciInsureDemandLoss_[0].lossType=&ciInsureDemandLoss_[0].identifyType=&ciInsureDemandLoss_[0].identifyNumber=&ciInsureDemandLoss_[0].lossAcceptDate=&ciInsureDemandLoss_[0].lossActionDesc=&CIDemandClaim_Flag=&ciInsureDemandPay_[0].id.serialNo=&ciInsureDemandPay_[0].payCompany=&ciInsureDemandPay_[0].claimregistrationno=&ciInsureDemandPay_[0].compensateNo=&ciInsureDemandPay_[0].lossTime=&ciInsureDemandPay_[0].endcCaseTime=&ciInsureDemandPay_[0].lossFee=&ciInsureDemandPay_[0].payType=&ciInsureDemandPay_[0].personpayType=&ciRiskWarningClaimItems_[0].id.serialNo=&ciRiskWarningClaimItems_[0].riskWarningType=&ciRiskWarningClaimItems_[0].claimSequenceNo=&ciRiskWarningClaimItems_[0].insurerCode=&ciRiskWarningClaimItems_[0].lossTime=&ciRiskWarningClaimItems_[0].lossArea=&ciInsureDemand.licenseNo=&ciInsureDemand.licenseType=&ciInsureDemand.useNatureCode=&ciInsureDemand.frameNo=&ciInsureDemand.engineNo=&ciInsureDemand.licenseColorCode=&ciInsureDemand.carOwner=&ciInsureDemand.enrollDate=&ciInsureDemand.makeDate=&ciInsureDemand.seatCount=&ciInsureDemand.tonCount=&ciInsureDemand.validCheckDate=&ciInsureDemand.manufacturerName=&ciInsureDemand.modelCode=&ciInsureDemand.brandCName=&ciInsureDemand.brandName=&ciInsureDemand.carKindCode=&ciInsureDemand.checkDate=&ciInsureDemand.endValidDate=&ciInsureDemand.carStatus=&ciInsureDemand.haulage=&AccidentFlag=&rateFloatFlag=ND1&prpCtrafficRecordTemps_[0].id.serialNo=&prpCtrafficRecordTemps_[0].accidentDate=&prpCtrafficRecordTemps_[0].claimDate=&hidden_index_ctraffic=0&noBringOutEngage=&prpCengageTemps_[0].id.serialNo=&prpCengageTemps_[0].clauseCode=&prpCengageTemps_[0].clauseName=&clauses_[0]=&prpCengageTemps_[0].flag=&prpCengageTemps_[0].engageFlag=&prpCengageTemps_[0].maxCount=&prpCengageTemps_[0].clauses=&iniPrpCengage_Flag=&hidden_index_engage=0&levelMaxRate=&maxRateScm=&levelMaxRateCi=&maxRateScmCi=&certificateNo=&isModifyBI=&isModifyCI=&isNetFlag=&isNetFlagEad=&netCommission_Switch=&netCommission_SwitchEad=&agentsRateBI=&agentsRateCI=&subsidyRate=&prpCmain.policyRelName=&prpCmain.sumAmount=0&prpCmainCI.sumAmount=&prpCmain.sumDiscount=&prpCmainCI.sumDiscount=&prpCstampTaxBI.biTaxRate=&prpCstampTaxBI.biPayTax=&prpCstampTaxCI.ciTaxRate=&prpCstampTaxCI.ciPayTax=&prpCmainCar.rescueFundRate=&prpCmainCar.resureFundFee=0&prpCmain.language=CNY&prpCmain.policySort=1&prpCmain.operatorCode=1102680006&operatorName=韩建玲&operateDateShow=2016-09-15&prpCmainCar.carCheckStatus=0&prpCmainCar.carChecker=&carCheckerTranslate=&prpCmainCar.carCheckTime=&prpCmain.argueSolution=1&prpCmainCommon.DBCFlag=0&prpCmain.arbitBoardName=&arbitBoardNameDes=&prpCmain.coinsFlag=00&prpCcommissionsTemp_[0].costType=&prpCcommissionsTemp_[0].riskCode=&prpCcommissionsTemp_[0].currency=AED&prpCcommissionsTemp_[0].adjustFlag=0&prpCcommissionsTemp_[0].upperFlag=0&prpCcommissionsTemp_[0].auditRate=&prpCcommissionsTemp_[0].auditFlag=1&prpCcommissionsTemp_[0].sumPremium=&prpCcommissionsTemp_[0].costRate=&prpCcommissionsTemp_[0].costRateUpper=&prpCcommissionsTemp_[0].coinsRate=100&prpCcommissionsTemp_[0].coinsDeduct=1&prpCcommissionsTemp_[0].costFee=&prpCcommissionsTemp_[0].agreementNo=&prpCcommissionsTemp_[0].configCode=&hidden_index_commission=0&scmIsOpen=1111100000&prpCagents_[0].roleType=&roleTypeName_[0]=&prpCagents_[0].id.roleCode=&prpCagents_[0].roleCode_uni=&prpCagents_[0].roleName=&prpCagents_[0].costRate=&prpCagents_[0].costFee=&prpCagents_[0].flag=&prpCagents_[0].businessNature=&prpCagents_[0].isMain=&prpCagentCIs_[0].roleType=&roleTypeNameCI_[0]=&prpCagentCIs_[0].id.roleCode=&prpCagentCIs_[0].roleCode_uni=&prpCagentCIs_[0].roleName=&prpCagentCIs_[0].costRate=&prpCagentCIs_[0].costFee=&prpCagentCIs_[0].flag=&prpCagentCIs_[0].businessNature=&prpCagentCIs_[0].isMain=&commissionCount=&prpCsaless_[0].salesDetailName=&prpCsaless_[0].riskCode=&prpCsaless_[0].splitRate=&prpCsaless_[0].oriSplitNumber=&prpCsaless_[0].splitFee=&prpCsaless_[0].agreementNo=&prpCsaless_[0].id.salesCode=&prpCsaless_[0].salesName=&prpCsaless_[0].id.proposalNo=&prpCsaless_[0].id.salesDetailCode=&prpCsaless_[0].totalRate=&prpCsaless_[0].splitWay=&prpCsaless_[0].totalRateMax=&prpCsaless_[0].flag=&prpCsaless_[0].remark=&commissionPower=&hidden_index_prpCsales=0&prpCsalesDatils_[0].id.salesCode=&prpCsalesDatils_[0].id.proposalNo=&prpCsalesDatils_[0].id.  =&prpCsalesDatils_[0].id.roleType=&prpCsalesDatils_[0].id.roleCode=&prpCsalesDatils_[0].currency=&prpCsalesDatils_[0].splitDatilRate=&prpCsalesDatils_[0].splitDatilFee=&prpCsalesDatils_[0].roleName=&prpCsalesDatils_[0].splitWay=&prpCsalesDatils_[0].flag=&prpCsalesDatils_[0].remark=&hidden_index_prpCsalesDatil=0&prpDdismantleDetails_[0].id.agreementNo=&prpDdismantleDetails_[0].flag=&prpDdismantleDetails_[0].id.configCode=&prpDdismantleDetails_[0].id.assignType=&prpDdismantleDetails_[0].id.roleCode=&prpDdismantleDetails_[0].roleName=&prpDdismantleDetails_[0].costRate=&prpDdismantleDetails_[0].roleFlag=&prpDdismantleDetails_[0].businessNature=&prpDdismantleDetails_[0].roleCode_uni=&hidden_index_prpDdismantleDetails=0&prpCmain.remark=&upperCrossFlag=&prpCmain.crossFlag=&crossFlagDes=&ciInsureDemandCheckVo.demandNo=&ciInsureDemandCheckVo.checkQuestion=&ciInsureDemandCheckVo.checkAnswer=&ciInsureDemandCheckCIVo.demandNo=&ciInsureDemandCheckCIVo.checkQuestion=&ciInsureDemandCheckCIVo.checkAnswer=&ciInsureDemandCheckVo.flag=DEMAND&ciInsureDemandCheckVo.riskCode=&ciInsureDemandCheckCIVo.flag=DEMAND&ciInsureDemandCheckCIVo.riskCode=&flagCheck=00&payTimes=1&prpCplanTemps_[0].payNo=&prpCplanTemps_[0].serialNo=&prpCplanTemps_[0].endorseNo=&cplan_[0].payReasonC=&prpCplanTemps_[0].payReason=&prpCplanTemps_[0].planDate=&prpCplanTemps_[0].currency=&description_[0].currency=&prpCplanTemps_[0].planFee=&cplans_[0].planFee=&cplans_[0].backPlanFee=&prpCplanTemps_[0].netPremium=&prpCplanTemps_[0].taxPremium=&prpCplanTemps_[0].delinquentFee=&prpCplanTemps_[0].flag=&prpCplanTemps_[0].subsidyRate=&prpCplanTemps_[0].isBICI=&iniPrpCplan_Flag=&loadFlag9=&planfee_index=0&planStr=&planPayTimes=";
		String mapStr = "buttonProfitDetail[3]=....&prpCitemKindsTemp[19].taxPremium=&prpCitemKindsTemp[6].endDate=&prpCitemKindsTemp[8].benchMarkPremium=&prpCfixationTemp.riskPremium=&BiInsureDemandLoss_[0].id.serialNo=&prpCitemKindCI.flag=&idCardCheckInfo_[0].nation=&prpCitemKindsTemp[20].kindCode=050939&vehiclePricer=&prpBatchVehicle.sumAmount=&prpCcarShipTax.taxComCode=&prpCagents_[0].isMain=&prpCcommissionsTemp_[0].riskCode=&LicenseTypeDes=&oldPolicyNo=&prpCsalesDatils_[0].id.proposalNo=&prpCcarDevices_[0].id.proposalNo=&prpCinsuredsview[0].mobile=&prpBatchVehicle.carId=&buttonProfitDetail[2]=....&prpCitemKindsTemp_[0].benchMarkPremium=&prpCfixationTemp.discount=&prpCitemKindCI.taxRate=&noDamageYearsBIPlat=0&prpCplanTemps_[0].taxPremium=&biPlateVersion=&prpCitemKindsTemp[13].basePremium=&prpCitemKindsTemp[0].pureRiskPremium=&prpCsalesDatils_[0].splitWay=&qualificationName=&ciInsureDemandCheckVo.riskCode=&cIRiskWarningType=&prpCitemKindsTemp[9].kindCode=050461&prpCmainCommon.handler1code_uni=&prpCitemCar.id.itemNo=1&prpCitemKindsTemp[18].basePremium=&buttonRenewal=续 保&prpCitemKindsTemp[2].netPremium=&buttonProfitDetail[1]=....&isTaxFlag=&CiLastPolicyFlag=&prpCitemKindsTemp[18].endHour=&buttonProfitDetail[5]=....&batchBIFlag=&cIInsureMotorFlag=1&MOTORFASTTRACK_INSUREDCODE=&prpCitemKindsTemp[13].benchMarkPremium=&prpCitemKindsTemp[12].dutyFlag=&prpCitemKindsTemp[0].itemKindNo=&prpCmain.riskCode=&prpCcarShipTax.calculateMode=C1&prpCfixationCITemp.riskPremium=&prpCsaless_[0].agreementNo=&prpCitemKindsTemp[4].calculateFlag=Y21Y00&prpCmain.policyRelName=&prpCinsureds[0].versionNo=&prpCplanTemps_[0].delinquentFee=&FREEINSURANCEFLAG=011111&carCheckerTranslate=&prpCitemKindsTemp[12].taxPremium=&qualificationNo=&prpCmain.sumPremium=0&prpCitemKindsTemp[9].startDate=&BiInsureDemandPay_[0].lossFee=&prpCitemKindsTemp[4].endDate=&prpCitemKindsTemp_[0].quantity=90&noNcheckFlag=&BiInsureDemandPay_[0].endcCaseTime=&buttonProfitDetail[4]=....&prpCitemKindsTemp[3].kindCode=050711&carKindCodeBak=A01&idCardCheckInfo_[0].sex=&prpCitemKindsTemp[7].premium=&prpCitemKindsTemp[6].kindName=玻璃单独破碎险&prpCsalesDatils_[0].id.roleType=&maxRateScm=&ciInsureDemand.carKindCode=&rePolicyNo=&prpCitemKindsTemp[16].itemKindNo=&prpCengageTemps_[0].clauseCode=&ciInsureDemand.lastyearenddate=&prpCitemKindsTemp[7].dutyFlag=&ciInsureDemand.validCheckDate=&prpCitemKindsTemp[4].disCount=&cIInsureFlag=1&repeatTimes_[0]=&prpCitemCar.energyType=0&BiInsureDemandLoss_[0].peccancyid=&prpCcarDevices_[0].flag=&BiInsureDemandLoss_[0].decisionid=&ciInsureDemandCheckCIVo.demandNo=&prpCmainChannel.assetAgentCode=&prpCsalesDatils_[0].remark=&prpBatchVehicle.proposalNo=&prpCinsureds[0].insuredNature=&prpCitemCar.newCarFlag=0&MealFlag=0&prpCprofitDetailsTemp_[0].flag=&upperCrossFlag=&prpCsaless_[0].flag=&prpCitemKindsTemp_[0].endDate=&prpCitemKindsTemp[17].endHour=&prpCitemKindsTemp[11].startHour=&prpCitemKindsTemp[11].benchMarkPremium=&hidden_index_commission=0&prpCitemKindsTemp[20].basePremium=&BiInsureDemandLoss_[0].lossAction=&prpCitemKindsTemp[12].disCount=&prpCitemCar.flag=&prpCitemKindsTemp[1].clauseCode=050054&endDateU=&prpCitemKindsTemp[7].startDate=&prpCinsureds_[0].mobile=&prpCcommissionsTemp_[0].sumPremium=&btn_delete_remark=删除&prpCfixationTemp.basePayMentR=&prpCitemKindsTemp[3].premium=&prpCitemKindsTemp[7].riskPremium=&carShipTaxPlatFormFlag=1&projectCodeBT=&prpCitemKindsTemp[0].flag= 100000&prpCitemKindsTemp[3].riskPremium=&prpPheadCI.applyNo=&prpCitemKindsTemp[13].calculateFlag=N33N000&prpCcarShipTax.payStartDate=&prpCitemCar.licenseFlag=1&prpCitemKindsTemp[14].amount=&prpCitemCar.transferDate=&prpCitemKindsTemp[16].amount=&prpCcommissionsTemp_[0].costRateUpper=&openCplan4S_1=缴费计划&isMotoDrunkDriv=0&prpCitemCar.carLoanFlag=&prpCfixationTemp.signPremium=&prpCinsureds_[0].versionNo=&prpCitemKindsTemp[16].dutyFlag=&prpCitemCar.countryNature=01&prpCinsureds[0].soldierIdentifyType=&bizType=PROPOSAL&prpCitemKindsTemp[1].rate=&prpCitemKindsTemp[8].dutyFlag=&prpCitemKindsTemp[3].taxRate=&prpCitemKindsTemp[15].netPremium=&prpCitemKindsTemp[11].amount=&prpPhead.validDate=&checkHandler1Code=1&prpCitemKindsTemp[5].startDate=&prpCitemKindsTemp[4].premium=&prpCinsureds_[0].unitType=100&prpCitemKindsTemp_[0].premium=&biCiFlagU=&isCheckRepeat_[0]=&backRemark=返回&buttonProfitDetail[0]=....&queryArea=11&DAZlastDamagedBI=&prpCfixationTemp.operationInfo=&hidden_index_prpCsales=0&prpCcarShipTax.taxAbateReason=&ciInsureDemandCheckVo.checkQuestion=&prpCitemKindsTemp[11].riskPremium=&prpCinsureds[0].insuredAddress=&prpCfixationTemp.poundAge=&cIBPFlag=1&prpCitemKindsTemp[0].premium=&prpDdismantleDetails_[0].id.assignType=&prpCprofitDetailsTemp_[0].profitRateMin=&prpCitemKindsTemp_[0].itemKindNo=&prpCitemKindsTemp[15].itemKindNo=&prpCitemKindsTemp[0].taxRate=&ciRiskWarningClaimItems_[0].insurerCode=&prpCitemKindsTemp[5].endDate=&prpCmain.reinsFlag=0&idCardCheckInfo[0].name=&prpCitemKindsTemp[2].kindName=第三者责任保险&prpCitemCar.carKindCode=   &prpCitemKindsTemp[16].endDate=&prpCitemCar.coefficient2=&rateFloatFlag=ND4&prpCitemKindsTemp[17].startHour=&prpCitemCar.coefficient1=&prpCmainCommon.DBCFlag=0&prpCitemKindsTemp[10].basePremium=&prpCitemKindsTemp[0].calculateFlag=Y11Y000&prpCfixationCITemp.riskSumPremium=&prpCcommissionsTemp_[0].auditRate=&prpCitemKindsTemp[11].startDate=&prpCfixationTemp.cost=&prpCagentCIs_[0].businessNature=&prpCitemKindsTemp[12].calculateFlag=N33N000&prpCitemKindsTemp[3].itemKindNo=&prpCitemKindsTemp[6].dutyFlag=&buttonRemark=备 注&purchasePriceOld=0&prpBatchProposal.profitType=&prpCcarShipTax.taxComName=&prpCitemKindsTemp[17].amount=&prpCmain.sumPremiumTemp=0&prpCitemKindsTemp[6].itemKindNo=&prpCitemKindsTemp[4].amount=&prpCitemCar.enrollDate=&ciInsureDemand.manufacturerName=&prpCsaless_[0].salesDetailName=&prpCplanTemps_[0].endorseNo=&prpCitemCar.monopolyFlag=1&biPlatTask=&prpCprofitDetailsTemp_[0].profitName=&prpCitemKindsTemp[9].endHour=&prpCitemKindCI.netPremium=&prpCitemKindsTemp[0].benchMarkPremium=&prpCitemCar.noNlocalFlag=0&prpCitemKindsTemp[14].disCount=&prpCitemKindsTemp[3].dutyFlag=&prpCitemKindsTemp[16].taxRate=&prpCitemKindsTemp[9].taxRate=&idCardCheckInfo_[0].insuredFlag=&prpCitemKindsTemp[1].startHour=&prpCitemKindsTemp[2].taxPremium=&prpCcarDevices_[0].id.serialNo=&prpCitemKindsTemp[11].kindCode=050930&agentType=&prpCcarShipTax.model=B11&prpCitemKindsTemp[11].dutyFlag=&prpCitemKindsTemp[15].dutyFlag=&prpCitemKindsTemp[20].dutyFlag=&prpCitemKindsTemp[12].netPremium=&carDamagedNum=&prpCitemKindsTemp[1].quantity=&prpCmainChannel.assetAgentPhone=&prpCitemKindsTemp[1].taxRate=&prpCitemKindsTemp[8].kindName=自燃损失险&prpCinsureds[0].unitType=100&prpCprofitDetailsTemp_[0].chooseFlag=on&prpCitemKindsTemp[7].clauseCode=050065&prpCmainagentName=北京翰文兴业汽车俱乐部有限公司&prpCsaless_[0].splitWay=&prpCprofitFactorsTemp_[0].flag=&prpCitemKindsTemp[4].netPremium=&prpCitemCar.carId=&prpCitemKindsTemp[19].riskPremium=&isDirectFee=&prpCitemKindsTemp[2].endHour=&ciInsureDemand.licenseType=&prpCitemCar.tonCount=0&prpCitemKindsTemp[18].calculateFlag=N33N000&prpCfixationCITemp.signPremium=&ciInsureDemandPay_[0].payType=&prpCtrafficDetails_[0].accidentType=1&prpCitemKindsTemp[4].kindCode=050712&prpCinsureds_[0].soldierRelations1=0&prpCitemKindsTemp[8].endDate=&_insuredFlag=0&sbFlag=&operatorProjectCode=1-6599,2-6599,4-6599,5-6599&officePhonebak=&validDateEdit=&operatorName=韩建玲&idCardCheckInfo_[0].validEndDate=&passengersSwitchFlag=&specialflag=&prpCitemKindsTemp[3].flag= 100000&VEHICLEPLAT=1&hidden_index_itemKind=21&prpCsalesFixes_[0].businessNature=&prpCprofitDetailsTemp_[0].id.proposalNo=&proposalDemandCheckBtn=确_定&ciInsureSwitchKindCode=E01,E11,E12,D01,D02,D03&prpCitemKindsTemp[4].basePremium=&prpCfixationCITemp.riskClass=&prpCmain.policyNo=&prpPhead.applyNo=&prpCitemKindsTemp[4].riskPremium=&certificateNo=&carModelPlatFlag=1&ZGRS_PURCHASEPRICE=&prpCsaless_[0].totalRateMax=&prpCinsureds[0].phoneNumber=&prpCitemCar.carProofNo=&homePhone=&prpCitemKindsTemp[20].startHour=&prpCmain.crossFlag=&prpCitemKindsTemp[13].disCount=&prpCitemKindsTemp[16].calculateFlag=N33N000&prpBatchVehicle.policyNo=&buttonProfitDetail[7]=....&prpCmain.agentCode=11003O100375&prpCitemCar.transferVehicleFlag=0&prpCitemKindsTemp[1].riskPremium=&prpCitemKindsTemp[10].taxRate=&prpCitemCar.seatCount=&prpCitemKindsTemp[5].clauseCode=050059&prpCitemKindsTemp[3].quantity=&GuangdongSysFlag=0&_taxUnit=&prpCitemKindsTemp[17].taxPremium=&prpCmainCI.contractNo=&ciInsureDemand.preferentialFormula =&scmIsOpen=1111100000&prpCitemKindsTemp[8].itemKindNo=&prpCagentCIs_[0].flag=&prpCplanTemps_[0].serialNo=&prpCinsureds[0].auditStatusDes=&premiumF48=5000&prpCitemKindsTemp[13].startDate=&prpCitemKindsTemp[14].basePremium=&ciPlateVersion=&prpCitemKindsTemp[6].riskPremium=&owner=&prpCitemKindsTemp[20].amount=&prpCfixationCITemp.realDisCount=&hidden_index_ctraffic_NOPlat_Drink=0&buttonProfitDetail[6]=....&prpCitemKindsTemp[8].amount=&prpCitemKindsTemp_[0].flag=&prpCitemKindsTemp[13].taxPremium=&prpCinsureds_[0].insuredFlagDes=&projectCode=&buttonGetData=获取数据&useCarshiptaxFlag=1&prpCitemKindsTemp[12].taxRate=&prpCstampTaxBI.biTaxRate=&prpCcarShipTax.taxExplanation=&GDCANCIINFOFlag=0&bIRiskWarningClaimItems_[0].lossTime=&idCardCheckInfo_[0].name=&prpCitemKindsTemp[19].endDate=&prpCmain.startMinutes=0&prpCinsuredOwn_Flag=&prpCitemKindsTemp[17].netPremium=&motorFastTrack_Amount=&prpCcommissionsTemp_[0].auditFlag=1&prpCitemKindsTemp[4].clauseCode=050053&agentCodeValidValue=115192BJ&arbitBoardNameDes=&prpCitemKindsTemp[15].clauseCode=050066&prpCmain.contractNo=&prpCitemKindsTemp[20].rate=&buttonProfitDetail[9]=....&prpCitemKindsTemp[6].startHour=&prpCitemKindsTemp[12].startHour=&prpCinsureds[0].identifyType=01&operatorCode=1102680006&idCardCheckInfo[0].idcardCode=&prpCcommissionsTemp_[0].upperFlag=0&prpCitemKindsTemp[7].benchMarkPremium=&DAGMobilePhoneNum=&pm_vehicle_switch=&reinEndDate=&groupCode_[0]=&prpCitemKindsTemp[2].calculateFlag=Y21Y000&prpCitemKindsTemp[1].endDate=&prpCinsureds_[0].soldierIdentifyNumber=&profitRateTemp_[0]=&accRisk=意外险&prpBatchVehicle.id.serialNo=&prpCitemKindsTemp[3].rate=&prpCitemCar.coefficient3=&prpCitemKindCI.premium=&prpCitemKindsTemp[5].netPremium=&prpCplanTemps_[0].payNo=&prpCmainCommon.groupFlag=0&buttonProfitDetail[8]=....&countryNatureU=&prpCitemKindsTemp[6].benchMarkPremium=&prpCfixationTemp.realDisCount=&hidden_index_engage=0&button_InsuredFlagDetail_SubPage_Save[0]=确定&prpCitemKindsTemp[2].premium=&BIdemandNo=&prpCitemKindsTemp[15].kindName=不计免赔险（车上人员（乘客））&prpCitemCar.carLotEquQuality=&btn_deleteEngage=删除&prpCmain.startDate=2016-09-24&ciInsureDemand.rateRloatFlag=00&bIInsureFlag=1&reinPolicyNo=&prpCitemKindsTemp[15].startHour=&prpCitemKindsTemp[5].taxPremium=&prpCitemKindsTemp[10].startDate=&prpCitemKindsTemp[6].flag= 200000&prpCitemKindsTemp[14].startDate=&prpCitemKindsTemp[13].amount=&prpCitemKindCI.unitAmount=0&endorDateEdit=&prpCsaless_[0].remark=&prpCitemCar.isDropinVisitInsure=0&customerURL=http://10.134.136.48:8300/cif&prpCitemKindsTemp[10].rate=&prpCinsuredsview_[0].mobile=&prpCplanTemps_[0].isBICI=&ciInsureDemandCheckVo.flag=DEMAND&prpCmain.othFlag=&prpCmain.preDiscount=&taxAbateForPlat=&prpCitemKindsTemp[5].amount=2000.00&prpCsaless_[0].riskCode=&prpCitemKindsTemp[20].flag= 200000&ciRiskWarningClaimItems_[0].claimSequenceNo=&prpCitemKindsTemp[12].clauseCode=050066&prpCinsureds_[0].insuredNature=&prpDdismantleDetails_[0].id.agreementNo=&prpCitemKindsTemp_[0].calculateFlag=&prpCinsureds_[0].unifiedSocialCreditCode=&prpCinsureds[0].soldierIdentifyNumber=&minusFlag=&prpCsalesFixes_[0].version=&prpCprofitDetailsTemp_[0].profitRate=&ciInsureDemandPay_[0].id.serialNo=&insuredFlagTemp[0]=0&prpPhead.endorType=&prpCinsureds[0].identifyNumber=&prpCitemKindsTemp[20].taxPremium=&prpCitemKindsTemp[5].startHour=&prpCitemKindsTemp[6].amount=&prpCitemKindsTemp[5].itemKindNo=&STFlag=0&prpCsaless_[0].id.proposalNo=&prpCprofitFactorsTemp_[0].id.profitCode=&prpBatchVehicle.licenseNo=&prpCprofitFactorsTemp_[0].condition=&prpCitemKindsTemp[18].amount=&ciRiskWarningClaimItems_[0].id.serialNo=&prpCmainCI.underWriteFlag=&ciInsureDemandCheckVo.demandNo=&prpCmain.underWriteName=&prpCitemKindsTemp[0].startDate=&haveScanFlag=0&prpCcommissionsTemp_[0].agreementNo=&prpCcarShipTax.dutyPaidProofNo=&handlerCodeDes=栾巍&prpCinsureds_[0].acceptLicenseDate=&prpCitemKindsTemp[8].disCount=&prpCitemCar.fuelType=A&prpCcarDevices_[0].deviceName=&prpCitemKindsTemp[20].benchMarkPremium=&prpCplanTemps_[0].planDate=&prpCmain.underWriteEndDate=&prpCitemKindsTemp[19].dutyFlag=&ciInsureDemand.enrollDate=&prpCitemKindsTemp[18].premium=&prpCitemKindsTemp[11].kindName=不计免赔险（车损险）&idCardCheckInfo_[0].insuredcode=&prpCmain.operatorCode=1102680006&prpCitemCar.startSiteName=&prpCmainCI.sumPremium=&ciInsureDemand.peccancyAdjustReason=V1&prpCcarShipTax.delayPayTax=&prpCinsureds_[0].identifyType1=01&prpCinsureds_[0].flag=&prpCitemCar.runAreaCode=11&mealConfigQuery=套餐方案&kbFlag=&prpCengageTemps_[0].engageFlag=&prpCitemKindsTemp[6].disCount=&prpCitemKindsTemp[7].flag= 200000&prpCinsureds[0].flag=&bIRiskWarningClaimItems_[0].riskWarningType=&prpCinsureds[0].causetroubleTimes=&prpCitemKindsTemp[3].clauseCode=050053&hidden_index_prpCsalesDatil=0&prpCagents_[0].roleType=&btn_deleteTraffic=删除&prpCcommissionsTemp_[0].coinsRate=100&prpCitemKindsTemp[8].taxPremium=&ciStartDate=2016-09-24&clauses_[0]=&prpCitemCar.modelCode=&prpCitemKindsTemp[2].itemKindNo=&prpCagentCIs_[0].roleCode_uni=&prpCcommissionsTemp_[0].costFee=&prpCitemKindsTemp[18].startHour=&kindcodesub=&prpCitemKindsTemp[18].endDate=&btn_deleteInsured=删&prpCitemKindCI.riskPremium=&prpCitemKindsTemp[19].amount=&crossFlagDes=&quotationNo=&prpCinsureds_[0].insuredType1=1&prpCitemKindsTemp_[0].basePremium=&prpCmainCI.checkUpCode=&clauseFlag=&prpDdismantleDetails_[0].id.configCode=&ciInsureDemand.seatCount=&prpCitemCar.useYears=&ciInsureDemand.engineNo=&buttonSubmitUnw=提交核保&prpCagents_[0].flag=&prpCmain.endDate=2017-09-23&prpCfixationTemp.profitClass=&prpCitemKindsTemp[17].endDate=&iProposalNo=&quotationtaxPayerCode=&MealFirstFlag=0&prpCitemKindsTemp[9].amount=&checkTimeFlag=&prpCitemKindsTemp[19].startDate=&prpCitemKindsTemp[17].calculateFlag=N33N000&biCiDateIsChange=&prpCtrafficDetails_[0].accidentDate=&prpCitemKindsTemp[3].calculateFlag=Y21Y00&prpCitemCar.otherNature=&prpCinsureds[0].insuredType=1&prpCitemKindCI.calculateFlag=Y&immediateFlagCI=0&ciInsureDemandPay_[0].lossFee=&prpBatchVehicle.versionNo=&hidden_index_ctraffic_NOPlat=0&prpCinsureds_[0].auditStatus=&commissionView=0&CProposalNo=&prpCitemKindsTemp[17].flag= 200000&checkCplanFlag=0&prpCcarShipTax.prePayTaxYear=&prpCitemKindsTemp[4].dutyFlag=&BiInsureDemandLoss_[0].lossAcceptDate=&prpCitemCar.colorCode=&ciInsureDemandCheckCIVo.checkQuestion=&prpCitemKindsTemp[18].clauseCode=050066&BiLastPolicyFlag=&prpCinsureds_[0].id.serialNo=&purchasePriceU=&prpCfixationCITemp.operationInfo=&CarKindCodeDes=&prpCitemCarExt_CI.damFloatRatioCI=0&R_SWITCH=&sumPremiumChgFlag=0&prpCitemKindsTemp[8].kindCode=050311&prpBatchVehicle.licenseType=&prpCitemKindsTemp[6].endHour=&prpCmain.sumTaxPremium=&prpCitemCar.carInsuredRelation=1&prpCitemKindsTemp[2].dutyFlag=&insuredFlagTemp_[0]=0&insurancefee_reform=1&updateQuotation=&prpBatchVehicle.carKindCode=&prpCagents_[0].roleCode_uni=&agentsRateCI=&ciInsureDemandLoss_[0].identifyType=&BiInsureDemandPay_[0].payCompany=&prpCitemKindsTemp[10].disCount=&prpCitemKindsTemp[4].rate=&prpCprofitFactorsTemp_[0].rate=&unitedSaleRelatioStr=&prpCprofitFactorsTemp_[0].lowerRate=&loadFlag9=&prpCitemKindsTemp[8].flag= 200000&ciInsureDemandPay_[0].lossTime=&prpCitemKindsTemp[17].premium=&prpCproposalVo.checkFlag=&queryCarModelInfo=车型信息平台交互&prpCitemKindsTemp[10].amount=&prpCitemKindsTemp[3].netPremium=&officePhone=&prpCitemKindsTemp[8].calculateFlag=N12Y000&ciPlatTask=&prpCcarShipTax.carLotEquQuality=&prpCmain.sumPremium1=0&prpCitemKindsTemp[2].amount=&prpCitemKindsTemp[1].amount=&prpCmain.projectCode=&prpCproposalVo.underWriteFlag=&prpCitemKindsTemp[11].taxPremium=&prpCinsureds[0].insuredCode=&agentsRateBI=&prpCitemKindsTemp[7].kindCode=050253&prpCitemKindsTemp[18].flag= 200000&buttonProfitDetail_[0]=....&operationTimeStamp=2016-09-23 00:33:00&prpCitemKindsTemp[5].riskPremium=&prpCmain.sumDiscount=&ciInsureDemandPay_[0].endcCaseTime=&prpCfixationTemp.realProfits=&prpCprofitDetailsTemp_[0].condition=&prpCcarShipTax.taxUnit=&prpCitemKindsTemp_[0].clauseCode=&prpCitemKindsTemp[9].premium=&PrpCmain_[0].endDate=&prpCitemKindsTemp[19].benchMarkPremium=&ciInsureDemandLoss_[0].lossAction=&xzFlag=&prpCmain.dmFlag=&prpCitemKindsTemp[13].endHour=&buttonProfitDetail[20]=....&prpBatchVehicle.motorCadeNo=&planFlag=0&prpCitemKindsTemp[1].kindName=盗抢险&pmCarOwner=&prpCitemKindsTemp[13].rate=&prpCinsureds[0].email=&prpCmainCI.policyNo=&idCardCheckInfo_[0].flag=0&prpCfixationTemp.basePremium=&buttonDelete_carDevice=删除&prpCitemKindsTemp[15].benchMarkPremium=&BiInsureDemandPay_[0].claimregistrationno=&prpCitemKindsTemp[10].premium=&prpCmain.handlerCode=13154727  &bizNoCI=&prpDdismantleDetails_[0].costRate=&prpBatchVehicle.profitProjectCode=&GDREALTIMEMOTORFlag=&bizNoBZ=&prpCplanTemps_[0].flag=&prpBatchVehicle.flag=&prpCitemKindsTemp[14].clauseCode=050066&currentDate=2016-09-23&taxFreeLicenseNo=&commissionFlag=&idCardCheckInfo_[0].address=&prpCitemKindCI.shortRate=100&prpCitemKindsTemp[14].riskPremium=&prpCitemKindsTemp[18].itemKindNo=&prpCitemKindsTemp[9].clauseCode=050060&agentCodeValidIPPer=&prpCinsureds_[0].insuredName=&AccidentFlag=&buttonSave=保存&prpCsaless_[0].splitFee=&prpCitemKindsTemp[17].taxRate=&prpCinsureds_[0].drivingLicenseNo=&prpCitemKindsTemp[18].benchMarkPremium=&ciInsureDemand.licenseColorCode=&description_[0].currency=&generatePtextAgainFlag=0&prpCitemKindsTemp[5].basePremium=&prpCmainCI.endHour=24&prpCitemKindsTemp[15].taxPremium=&prpCitemKindsTemp[0].basePremium=&planPayTimes=&ciInsureDemandCheckVo.checkAnswer=&ciInsureDemand.preferentialPremium=&prpCitemCarExt_CI.flag=&prpCitemCar.versionNo=&getReplenishfactor=&prpCitemKindsTemp[12].endDate=&prpCitemCar.licenseNo=京&MTFlag=0&saveRemark=保存备注&prpCfixationCITemp.poundAge=&prpCitemKindsTemp[8].endHour=&prpCcarShipTax.taxRelifFlag=&prpCitemKindsTemp[7].basePremium=&prpCsalesDatils_[0].flag=&prpCitemKindsTemp[6].netPremium=&prpCitemKindsTemp[2].rate=&prpCitemKindsTemp[11].disCount=&prpBatchVehicle.sumPremium=&applyNo=&prpCitemKindsTemp[1].dutyFlag=&prpCcommissionsTemp_[0].costType=&prpCitemKindsTemp[18].taxRate=&prpCitemKindsTemp[15].amount=&ciRiskWarningClaimItems_[0].lossArea=&prpCtrafficDetails_[0].indemnityDuty=有责&isTaxDemand=1&relatedFlag=0&prpCitemKindsTemp_[0].kindName=&prpCitemKindsTemp[17].kindCode=050936&prpCitemKindsTemp[10].taxPremium=&prpCsaless_[0].totalRate=&editCustom=改&prpCitemKindsTemp[6].startDate=&repeatTimes[0]=&prpCitemKindsTemp[19].netPremium=&benchMarkPremium=&prpCitemKindsTemp[14].taxRate=&resident_[0]=&configedRepeatTimes[0]=&prpCmain.policySort=1&prpCitemKindsTemp[14].itemKindNo=&editFlag=1&isModifyBI=&prpCitemKindsTemp_[0].riskPremium=&purchasePriceUFlag=&prpCmain.operateDate=2016-09-23&prpCinsureds_[0].drivingYears=&ciInsureDemand.restricFlag=&prpCitemKindsTemp[19].calculateFlag=N33N000&reinComPany=&prpCitemKindsTemp[5].taxRate=&prpCitemKindsTemp[14].flag= 200000&prpCitemKindsTemp[7].disCount=&prpCitemKindsTemp[6].taxRate=&prpCtrafficDetails_[0].trafficType=1&prpCprofitDetailsTemp_[0].conditionCode=&ciInsureDemand.endValidDate=&riskCode=DAA&prpCitemKindsTemp[16].basePremium=&idCardCheckInfo_[0].samType=&idCardCheckInfo[0].validStartDate=&prpCitemCarExt_CI.thisDamagedCI=0&prpCitemKindsTemp[8].clauseCode=050057&prpCitemKindsTemp[7].calculateFlag=N32N000&prpCitemCar.endSiteName=&prpCprofitFactorsTemp_[0].chooseFlag=on&prpCitemKindsTemp[13].startHour=&prpCitemKindsTemp[8].taxRate=&isModifyCI=&prpCitemCarExt.noDamYearsBI=0&Today=2016-09-23&prpCinsureds_[0].insuredAddress=&noPermissionsCarKindCode=&prpCitemKindsTemp[2].kindCode=050602&prpCitemKindsTemp[17].rate=&prpCcarShipTax.sumPayTax=&handler1CodeDesFlag=&prpCitemKindsTemp[20].clauseCode=050066&prpCitemKindsTemp_[0].modeCode=1&prpCtrafficDetails_[0].id.serialNo=&prpCitemKindsTemp[3].unitAmount=&ciInsureDemandLoss_[0].lossDddress=&prpCitemCar.engineNo=&prpCitemKindsTemp[4].taxPremium=&prpCitemKindsTemp[8].riskPremium=&prpCitemCar.clauseType=F42&prpCitemCar.carProofDate=&sumPayTax1=&prpCitemKindsTemp[6].rate=&prpCitemKindsTemp[12].kindCode=050931&prpCitemKindsTemp[1].taxPremium=&prpCagentCIs_[0].isMain=&prpCinsureds[0].drivingCarType=&prpCmainCI.sumAmount=&prpCitemKindsTemp[20].netPremium=&prpCprofitDetailsTemp_[0].id.profitType=&prpCitemKindsTemp[14].benchMarkPremium=&prpCitemKindsTemp[9].disCount=&insurePayTimes=&idCardCheckInfo_[0].samCode=&prpCagents_[0].businessNature=&prpCitemKindsTemp[0].deductible=0.00&prpCtrafficRecordTemps_[0].id.serialNo=&prpCinsureds_[0].postCode=&taxAbateForPlatCarModel=&purchasePriceDown=0&CiLastExpireDate=&prpCitemKindsTemp[11].itemKindNo=&prpCitemKindsTemp[5].calculateFlag=N12Y000&prpCmainCommon.handlercode_uni=&prpCitemKindsTemp[0].startHour=&prpCmainCar.resureFundFee=0&ZGRS_LOWESTPREMIUM=&prpCitemKindsTemp[13].clauseCode=050066&prpCitemKindsTemp[6].taxPremium=&prpCmain.discount=1&prpCitemKindsTemp[4].startHour=&prpCitemKindsTemp[18].kindName=不计免赔险（车身划痕损失险）&prpCitemKindsTemp[3].benchMarkPremium=&prpCcarShipTax.taxAbateRate=&prpCitemKindsTemp[1].netPremium=&idCardCheckInfo[0].insuredFlag=&idCardCheckInfo[0].nation=&prpCitemKindsTemp[4].flag= 100000&ciInsureDemand.claimAdjustReason=A1&prpCitemCarExt_CI.lastDamagedCI=0&prpCproposalVo.operatorCode1=&prpCitemKindsTemp[19].clauseCode=050066&prpCfixationTemp.costRate=&prpCitemKindsTemp[19].premium=&ciInsureDemandLoss_[0].identifyNumber=&prpCcommissionsTemp_[0].adjustFlag=0&ciInsureDemand.checkDate=&prpCcarShipTax.flag=&BiInsureDemandLoss_[0].lossTime=&ciLimitDays=90&prpCitemKindsTemp[6].kindCode=050232&totalIndex=21&BiLastEffectiveDate=&purchasePriceF48=200000&prpCitemKindsTemp[3].kindName=车上人员责任险（司机）&biStartDate=2016-09-24&prpCitemKindCI.id.itemKindNo=&prpCitemKindsTemp[11].clauseCode=050066&prpCprofitFactorsTemp_[0].upperRate=&prpCmain.language=CNY&upperCostRateCI=&prpCitemKindsTemp[2].startHour=&prpCcarShipTax.taxPayerName=&prpCitemKindsTemp[12].amount=&prpCitemCar.discountType=&prpCitemKindsTemp[1].kindCode=050501&prpCitemKindsTemp[2].taxRate=&prpCinsureds_[0].identifyType=01&prpCmainCar.rescueFundRate=&prpCcarShipTax.prePayTax=&prpCinsureds[0].id.serialNo=&prpCcarDevices_[0].id.itemNo=1&prpCinsuredsview_[0].phoneNumber=&prpCitemKindsTemp[12].premium=&prpCitemKindsTemp[0].kindCode=050202&licenseNoCar=&prpCitemCar.runMiles=0&buttonPremium=计算税额&prpCitemKindsTemp[4].unitAmount=&prpCinsureds_[0].sex=0&button_InsuredFlagDetail_SubPage_Close[0]=取消&ciInsureDemand.makeDate=&prpCmain.underWriteCode=&CarShipInit_Flag=&sumBenchPremium=&prpCmainCar.carCheckStatus=0&prpCitemKindsTemp[1].unitAmount=&upperCostRateBI=&prpCinsureds[0].insuredFlagDes=投保人/被保险人/车主&prpCfixationTemp.isQuotation=&idCardCheckInfo[0].validEndDate=&ciInsureDemand.brandName=&prpCitemKindsTemp_[0].disCount=&prpCitemKindsTemp[7].itemKindNo=&prpCitemKindsTemp[5].premium=&prpCitemKindsTemp[8].netPremium=&prpCitemCar.purchasePrice=&prpCinsureds[0].insuredName=&prpCitemKindsTemp[1].itemKindNo=&is4SFlag=Y&prpCcarShipTax.taxType=1&prpCinsureds_[0].appendPrintName=&prpCitemKindsTemp[12].kindName=不计免赔险（三者险）&prpCitemKindsTemp[9].startHour=&prpCinsureds[0].drivingLicenseNo=&comCode=11026871&prpCsalesFixes_[0].isForMal=&prpCitemKindsTemp[2].unitAmount=&prpCitemKindsTemp[8].startDate=&mobliebak=&resident[0]=&prpCitemCar.noDamageYears=0&prpCitemKindsTemp[9].flag= 200000&handler1CodeDes=栾巍&prpCsalesDatils_[0].splitDatilRate=&prpCitemKindsTemp[6].modeCode=10&prpCitemKindsTemp[11].endDate=&prpCcarShipTax.taxPayerNature=3&prpCitemCarExt_CI.rateRloatFlag=01&prpBatchVehicle.id.contractNo=&ciInsureDemandLoss_[0].lossType=&prpCitemKindsTemp[16].clauseCode=050066&sunnumber=&prpCitemKindsTemp[5].dutyFlag=&businessNatureTranslation=兼业代理业务&paramIndex=&monopolyConfigsCount=1&prpBatchVehicle.coinsProjectCode=&prpCitemKindsTemp[5].benchMarkPremium=&ciInsureDemand.carOwner=&prpCitemKindsTemp[18].kindCode=050937&prpPhead.endorDate=&prpCcarShipTax.thisPayTax=&prpCitemKindsTemp[14].calculateFlag=N33N000&prpCmainCommon.greyList=&prpCplanTemps_[0].subsidyRate=&prpCitemKindsTemp[6].clauseCode=050056&iniPrpCengage_Flag=&prpCitemKindsTemp[19].itemKindNo=&_insuredFlag_hide=军属军人&prpCinsureds[0].sex=0&levelMaxRateCi=&prpCitemKindsTemp[17].startDate=&prpCitemKindsTemp[20].itemKindNo=&prpCitemKindsTemp[18].disCount=&lastTotalPremium=&prpCmainCI.startHour=0&prpCengageTemps_[0].clauses=&prpCitemCarExt_CI.offFloatRatioCI=0&userType=02&prpCplanTemps_[0].planFee=&hidden_index_prpDdismantleDetails=0&ciInsureDemand.frameNo=&prpCitemKindsTemp[15].startDate=&prpCmain.renewalFlag=&prpCitemCar.vinNo=&prpCitemKindsTemp[11].calculateFlag=N33N000&prpCitemKindsTemp[20].endHour=&prpCproposalVo.checkUpCode=&prpCitemKindsTemp[10].endDate=&prpCcommissionsTemp_[0].coinsDeduct=1&prpCitemKindsTemp[10].flag= 200000&prpCfixationCITemp.payMentR=&prpCitemKindsTemp[17].basePremium=&prpCcarDevices_[0].purchasePrice=&prpCitemKind.currency=CNY&prpCitemCar.monopolyName=北京翰文兴业汽车俱乐部有限公司&prpCfixationTemp.taxorAppend=&prpCitemKindsTemp[17].disCount=&prpCitemKindsTemp[1].disCount=&JFCDSWITCH=19&prpBatchMain.discountmode=&prpCinsureds[0].identifyType1=01&carPremium=0.0&prpCitemKindsTemp[5].kindCode=050211&prpCitemKindsTemp[19].basePremium=&sumItemKindSpecial=&prpCitemKindsTemp_[0].startDate=&netCommission_SwitchEad=&prpCfixationCITemp.discount=&addRemark=增加&prpCitemKindsTemp[0].disCount=&prpBatchVehicle.facProjectCode=&prpCstampTaxCI.ciTaxRate=&serialNo_[0]=&ciRiskWarningClaimItems_[0].lossTime=&prpCinsureds_[0].age=&prpCitemKindsTemp[0].rate=&customerFlag=&save2_insured_4S=查询&BIDemandClaim_Flag=&prpCprofitFactorsTemp_[0].profitName=&CarKindLicense=&prpCsalesDatils_[0].splitDatilFee=&netCommission_Switch=&prpCitemKindsTemp[10].kindCode=050917&prpCfixationTemp.riskClass=&prpCprofitDetailsTemp_[0].id.serialNo=1&prpCitemKindsTemp[7].taxPremium=&prpCitemKindsTemp[16].riskPremium=&prpCinsureds_[0].email=&prpCplanTemps_[0].currency=&prpCitemKindsTemp[12].endHour=&prpCinsureds[0].age=&startDateU=&prpCitemKindsTemp[9].taxPremium=&prpCitemKindsTemp[16].kindCode=050935&ciInsureDemand.licenseNo=&newCarFlagValue=2&prpCmainCI.checkFlag=&prpCitemKindsTemp[18].startDate=&prpCitemCarExt_CI.noDamYearsCI=1&ciInsureDemandPay_[0].personpayType=&prpCfixationCITemp.costRate=&itemKindLoadFlag=&prpCitemKindsTemp[4].itemKindNo=&prpCitemKindsTemp[12].flag= 200000&prpCsalesFixes_[0].id.serialNo=&configedRepeatTimes_[0]=&prpCitemCar.safeDevice=&prpCitemCar.isCriterion=1&clauseTypeBak=F42&idCardCheckInfo[0].flag=0&prpCsaless_[0].oriSplitNumber=&prpCitemKindsTemp[10].riskPremium=&randomProposalNo=5298484681474561980222 &prpCitemKindsTemp[3].endHour=&prpCtrafficDetails_[0].payComCode=&prpCitemKindsTemp[13].taxRate=&prpCitemKindsTemp[13].kindName=不计免赔险（盗抢险）&homePhonebak=&prpCitemKindsTemp[8].rate=&prpCitemCar.carProofType=01&BiInsureDemandPay_[0].payType=&buttonPremium_1=保费计算&prpCmainCI.proposalNo=&prpCitemKindsTemp[0].dutyFlag=&CarActualValueTrue=&prpCinsureds_[0].phoneNumber=&riskName=&OLD_ENDDATE_CI=&prpCfixationCITemp.realProfits=&prpCitemKindsTemp[19].kindCode=050938&button=费用拆分&BiInsureDemandLoss_[0].lossDddress=&prpCinsureds_[0].soldierIdentifyType=&prpCcarDevices_[0].quantity=&prpCitemKindsTemp[15].kindCode=050934&prpCsalesDatils_[0].id.salesCode=&prpCitemKindsTemp[1].benchMarkPremium=&prpCitemKindsTemp[3].startDate=&prpCitemCar.brandName=&BiInsureDemandPay_[0].personpayType=&prpCitemCar.loanVehicleFlag=0&prpCcarShipTax.taxAbateType=1&prpCitemKindsTemp[18].netPremium=&prpCitemKindsTemp[2].disCount=&prpCitemCar.licenseNo1=&prpCitemKindsTemp[14].dutyFlag=&reinStartDate=&configedRepeatTimesLocal=5&prpCitemKindsTemp[4].endHour=&pageEndorRecorder.endorFlags=&commissionCount=&prpCitemKindsTemp_[0].kindCode=&bt_[0]=置顶&cylinderFlag=0&prpCcarShipTax.baseTaxation=&oldClauseType=&prpCremarks_[0].remark=&prpCitemKindCI.dutyFlag=&prpCsaless_[0].id.salesDetailCode=&prpCitemKindsTemp[14].taxPremium=&prpCcarShipTax.payEndDate=&prpCitemKindsTemp[5].rate=&prpCitemKindsTemp[2].riskPremium=&prpCitemKindsTemp[3].amount=&prpCinsureds[0].appendPrintName=&idCardCheckInfo_[0].birthday=&prpCitemKindsTemp[16].startDate=&comCodePrefix=11&insertInsuredBtn=新增&prpCitemKindsTemp[17].kindName=不计免赔险（新增设备损失险）&idCardCheckInfo_[0].idcardCode=&prpCremarks_[0].flag=&flagCheck=00&endDateEdit=&prpCitemKindsTemp[16].benchMarkPremium=&prpCitemKindsTemp[13].kindCode=050932&generatePtextFlag=0&prpCinsureds[0].drivingYears=&prpCitemKindsTemp[17].clauseCode=050066&prpCitemKindsTemp[13].netPremium=&prpCitemKindCI.kindCode=050100&idCardCheckInfo_[0].validStartDate=&BiLastExpireDate=&prpCitemKindsTemp[12].benchMarkPremium=&prpCinsureds_[0].insuredCode=&prpCitemKindsTemp[9].dutyFlag=&ciInsureDemandCheckCIVo.riskCode=&prpCitemKindsTemp[9].kindName=发动机涉水损失险&prpCitemCar.modelDemandNo=&updateIndex=-1&prpCinsureds[0].mobile=&isCheckRepeat[0]=&idCardCheckInfo[0].samCode=&prpCitemKindsTemp[4].startDate=&prpCitemKindsTemp[19].taxRate=&prpCitemKindsTemp[2].quantity=&prpCitemKindsTemp[15].premium=&prpCitemKindsTemp[10].itemKindNo=&prpCagents_[0].id.roleCode=&comCodeDes=劲松营业部车险中介业务部&prpCinsureds_[0].drivingCarType=&AGENTSWITCH=1&unitedSale=联合销售&prpCitemKindsTemp[5].kindName=车身划痕损失险&endorType=&isBICI=&importantProjectCode=&prpCitemKindsTemp[15].rate=&agentCodeValidType=U&prpCinsureds[0].unifiedSocialCreditCode=&prpCagentCIs_[0].roleType=&prpCitemKindsTemp[1].flag= 100000&ABflag=&prpCitemKindsTemp[3].basePremium=&prpCitemKindsTemp[11].flag= 200000&btn_add_kindSub=新增&prpCmainCI.endDate=2017-09-23&prpCproposalVo.othFlag=&prpCstampTaxBI.biPayTax=&ciInsureDemand.modelCode=&customerCode=&prpCagents_[0].costRate=&prpCitemKindsTemp[17].dutyFlag=&prpCremarks_[0].id.proposalNo=&cplans_[0].planFee=&CiLastEffectiveDate=&OperateDate=&prpCitemKindsTemp[20].endDate=&checkUndwrt=0&prpCitemKindsTemp[7].taxRate=&prpCitemKindsTemp[14].netPremium=&prpCitemKindsTemp[19].flag= 200000&prpCitemKindsTemp[16].netPremium=&insured_btn_Reset=重置&prpCtrafficRecordTemps_[0].accidentDate=&prpCfixationTemp.profits=&sumAmountBI=0&GDREALTIMECARFlag=&BMFlag=0&prpDdismantleDetails_[0].roleFlag=&prpCitemKindsTemp[17].benchMarkPremium=&prpCitemKindsTemp_[0].netPremium=&prpCfixationCITemp.responseCode=&prpCproposalVo.strStartDate=&cplans_[0].backPlanFee=&prpCitemKindsTemp[0].clauseCode=050051&prpCitemKindsTemp[20].riskPremium=&prpCinsureds_[0].insuredType=1&subsidyRate=&prpCitemKindsTemp_[0].chooseFlag=on&prpCitemKindsTemp[15].taxRate=&prpCitemKindsTemp[12].riskPremium=&prpCitemKindsTemp[10].clauseCode=050066&prpCcarDevices_[0].buyDate=&batchCIFlag=&ciInsureDemandCheckCIVo.flag=DEMAND&prpCitemKindsTemp[9].netPremium=&prpCitemCar.actualValue=&hidden_index_insured=1&prpCitemCar.licenseType=   &prpCfixationCITemp.taxorAppend=&prpCcarShipTax.taxPayerCode=&button_InsuredFlagDetail_SubPage_Close_[0]=取消&prpCitemKindsTemp[19].disCount=&prpCtrafficDetails_[0].sumPaid=&idCardCheckInfo[0].samType=&prpCitemKindsTemp[0].endHour=&PrpCmain_[0].startDate=&BIdemandTime=&readCard=读卡&prpCprofitDetailsTemp_[0].id.profitCode=&prpCitemKindsTemp[20].startDate=&noDamYearsBINumber=0&idCardCheckInfo[0].address=&prpCitemKindsTemp[7].amount=&prpCfixationCITemp.basePayMentR=&editType=NEW&prpCitemKindsTemp[7].endDate=&premiumChangeFlag=0&prpCitemKindsTemp[16].disCount=&prpCitemKindsTemp[7].kindName=指定修理厂险&mtPlatformTime=&prpCmain.businessNature=3&ciInsureDemand.demandTime=&prpCitemCarExt.lastDamagedCI=0&prpCitemKindsTemp[19].rate=&BiInsureDemandPay_[0].compensateNo=&prpCitemKindsTemp[18].riskPremium=&prpCmain.sumNetPremium=&prpCinsureds_[0].soldierRelations=&prpCitemKindsTemp[2].flag= 100000&prpCitemCar.monopolyCode=1100729000451&prpCfixationTemp.riskSumPremium=&prpCremarks_[0].insertTimeForHis=&riskWarningFlag=&prpCagents_[0].roleName=&button_ProfitDetail_SubPage_Close=确定&prpCitemKindsTemp[12].rate=&prpCitemKindsTemp[0].kindName=机动车损失保险&buttonProfitDetail[18]=....&prpCmainCommon.netsales=0&prpCitemKindsTemp[11].endHour=&prpCinsureds[0].insuredFlag=111000000000000000000000000000&prpCitemKindsTemp[15].endHour=&prpCagentCIs_[0].costRate=&prpCprofitDetailsTemp_[0].profitRateMax=&newCarRecordButton=新车备案&prpCitemKindsTemp[10].endHour=&agentCode=11003O100375&ciInsureDemand.useNatureCode=&prpCitemKindsTemp[8].basePremium=&commissionPower=&prpCagentCIs_[0].id.roleCode=&prpCsaless_[0].splitRate=&operateDateShow=2016-09-23&buttonProfitDetail[17]=....&buttonUploadBI=影 像&prpCinsuredBon_Flag=&prpCcarShipTax.taxUnitAmount=&prpCitemKindsTemp_[0].value=1000&idCardCheckInfo_[0].mobile=&claimAmountReason=&prpCitemKindsTemp[8].startHour=&OLD_STARTDATE_CI=&prpCitemKindsTemp[15].calculateFlag=N33N000&amountFloat=30&cplan_[0].payReasonC=&payTimes=1&prpCcarShipTax.id.itemNo=1&prpCitemKindsTemp[14].startHour=&prpCitemKindCI.quantity=1&hidden_index_citemcar=0&prpCitemKindsTemp[20].calculateFlag=N33N000&prpCtrafficDetails_[0].flag=&buttonProfitDetail[16]=....&prpCitemKindsTemp[14].kindCode=050933&btn_deleteKInd=删&prpCitemKindsTemp[2].basePremium=&prpCinsureds_[0].countryCode=&prpCitemKindsTemp[3].disCount=&prpCinsureds_[0].identifyNumber=&ciRiskWarningClaimItems_[0].riskWarningType=&quotationRisk=PUB&prpCitemKindsTemp[20].taxRate=&prpCmain.remark=&prpCitemKindsTemp[20].kindName=不计免赔险（车上货物责任险）&prpCitemKindsTemp_[0].taxPremium=&makeComDes=劲松营业部车险中介业务部&prpCsaless_[0].salesName=&prpCitemKindsTemp[9].riskPremium=&prpCitemKindsTemp[14].endHour=&prpCsalesDatils_[0].id.roleCode=&prpCitemKindsTemp[1].startDate=&prpCfixationCITemp.profits=&claimAdjustValue=&prpCmainCommon.clauseIssue=2&prpCinsureds[0].insuredType1=1&buttonPremiumCost=计算跟单费用&buttonProfitDetail[15]=....&prpCcarShipTax.taxPayerNumber=&prpCitemKindsTemp[0].netPremium=&prpCitemKindsTemp[11].basePremium=&quotationFlag=&idCardCheckInfo[0].sex=&prpCitemKindCI.benchMarkPremium=0&prpCitemKindsTemp[18].rate=&prpCfixationCITemp.errorMessage=&handlerInfo=&prpCitemKindsTemp[0].amount=&button_InsuredFlagDetail_SubPage_Query[0]=查询&strCarShipFlag=1&prpCitemKindsTemp[0].endDate=&prpCengageTemps_[0].id.serialNo=&prpCitemCar.cylinderCount=&idCardCheckInfo[0].birthday=&ciInsureDemand.demandNo=&prpCfixationCITemp.cost=&prpCitemKindsTemp[7].startHour=&relateSpecial[1]=050932&ciInsureDemand.haulage=&ciInsureDemandLoss_[0].lossTime=&prpCplanTemps_[0].netPremium=&useNatureCodeBak=211&prpCprofitFactorsTemp_[0].id.conditionCode=&prpCitemKindsTemp[10].kindName=不计免赔险（精神损害抚慰金责任险）&prpCitemKindsTemp[17].itemKindNo=&reLoadFlag[0]=&prpCcommissionsTemp_[0].configCode=&codeLicenseType=LicenseType01,04,LicenseType02,01,LicenseType03,02,LicenseType04,02,LicenseType05,02,LicenseType06,02,LicenseType07,04,LicenseType08,04,LicenseType09,01,LicenseType10,01,LicenseType11,01,LicenseType12,01,LicenseType13,04,LicenseType14,04,LicenseType15,04,	LicenseType16,04,LicenseType17,04,LicenseType18,01,LicenseType19,01,LicenseType20,01,LicenseType21,01,LicenseType22,01,LicenseType23,03,LicenseType24,01,LicenseType25,01,LicenseType31,03,LicenseType32,03,LicenseType90,02&prpCcarShipTax.taxAbateAmount=&levelMaxRate=&prpCitemKindsTemp_[0].startHour=&prpCitemCarExt.thisDamagedBI=0&prpPhead.comCode=&prpCitemKindsTemp[3].startHour=&operateDateForFG=&prpCprofitDetailsTemp_[0].kindCode=&prpCitemCar.exhaustScale=0&button_InsuredFlagDetail_SubPage_query_[0]=查询&prpCitemKindsTemp[16].taxPremium=&ciInsureSwitchValues=1111111&prpCitemKindsTemp[9].itemKindNo=&prpCitemKindsTemp[14].kindName=不计免赔险（车上人员（司机））&prpCitemKindsTemp[2].endDate=&relateSpecial[2]=050931&prpDdismantleDetails_[0].flag=&prpCitemKindsTemp[11].rate=&idCardCheckInfo[0].insuredcode=&prpCsalesFixes_[0].id.proposalNo=&prpCitemKindsTemp_[0].dutyFlag=&LicenseColorCodeDes=&ciInsureDemandLoss_[0].coeff=&prpCitemKindsTemp[16].kindName=不计免赔险（自燃损失险）&prpCitemKindsTemp[13].endDate=&prpCsalesDatils_[0].id.  =&scanSwitch=1000000000&prpCsaless_[0].id.salesCode=&prpCitemKindsTemp[10].benchMarkPremium=&prpCmainCI.dmFlag=&prpCitemCar.remark=&BiInsureDemandLoss_[0].lossType=&prpCitemKindsTemp[16].endHour=&prpCitemKindsTemp[7].netPremium=&oldPolicyType=&groupCode[0]=&idCardCheckInfo_[0].issure=&prpCsalesDatils_[0].currency=&CIDemandClaim_Flag=&prpCagents_[0].costFee=&prpCfixationCITemp.remark=&prpCmain.proposalNo=&prpCitemKindsTemp_[0].rate=&prpCitemKindCI.amount=0&prpCfixationCITemp.profitClass=&prpCitemCarExt.lastDamagedBI=0&prpCmain.underWriteFlag=0&handler1CodeDesFlagbak=&prpCitemKindsTemp[9].basePremium=&hidden_index_remark=0&diffDay=90&motorFastTrack_flag=&prpCmainCI.startDate=2016-09-24&prpCfixationCITemp.isQuotation=&roleTypeNameCI_[0]=&buttonProfitDetail[19]=....&BiInsureDemandPay_[0].lossTime=&prpCitemKindsTemp[17].riskPremium=&ciInsureDemandPay_[0].claimregistrationno=&relateSpecial[0]=050930&prpCitemKindsTemp[10].netPremium=&buttonProfitDetail[10]=....&prpCitemKindsTemp[8].premium=&maxRateScmCi=&relateSpecial_[0]=&prpCcarShipTax.taxPayerIdentNo=&proposalListPrint=投保清单打印&prpCinsureds_[0].auditStatusDes=&prpCitemKindsTemp[6].calculateFlag=N32N000&prpCitemKindsTemp[1].premium=&prpCitemKindsTemp[12].itemKindNo=&prpCitemKindsTemp[20].disCount=&relateSpecial[5]=050937&prpCplanTemps_[0].payReason=&prpCproposalVo.businessNature=&isNetFlag=&prpBatchVehicle.prpProjectCode=&carShipTaxFlag=11&prpCitemKindsTemp[2].clauseCode=050052&prpCitemKindsTemp_[0].unitAmount=&prpCprofitDetailsTemp_[0].id.itemKindNo=&prpCitemKindCI.clauseCode=050001&prpCitemKindsTemp[4].quantity=&prpCmain.argueSolution=1&prpCitemKindsTemp_[0].amount=5000.00&prpCmainCommon.ext3=&SZpurchasePriceDown=&prpCmainCommon.ext2=&prpCmain.arbitBoardName=&prpCsalesFixes_[0].comCode=&prpCmainCI.sumDiscount=&prpCitemKindsTemp[10].calculateFlag=N33N000&prpCfixationTemp.realPayMentR=&prpCinsureds_[0].soldierIdentifyType1=000&prpCinsureds[0].acceptLicenseDate=&SYFlag=0&prpCitemCar.useNatureCode=211&idCardCheckInfo[0].mobile=&switchFlag=0&relateSpecial[6]=      &immediateFlag=1&prpCinsuredDiv_Flag=&commissionCalculationFlag=0&prpCitemKindsTemp[0].riskPremium=&prpCitemKindsTemp[4].kindName=车上人员责任险（乘客）&prpCitemKindsTemp[20].premium=&purchasePriceUp=100&prpCinsuredsview[0].phoneNumber=&prpCitemKindsTemp[15].disCount=&moblie=&prpCitemKindCI.deductible=&prpCitemKindsTemp[1].endHour=&prpCitemKindsTemp[15].riskPremium=&prpCitemKindsTemp[10].startHour=&prpCinsureds[0].countryCode=&prpCitemKindsTemp[15].flag= 200000&prpCitemKindsTemp[3].endDate=&button_NewCarDevice=设备信息&prpPhead.validHour=0&prpCagentCIs_[0].roleName=&prpCitemKindsTemp[18].taxPremium=&button_CalActualValue=计算实际价值&ciInsureDemand.carStatus=&prpCinsureds[0].postCode=&bIRiskWarningClaimItems_[0].lossArea=&bIRiskWarningClaimItems_[0].claimSequenceNo=&prpCitemKindCI.disCount=1&prpCitemKindsTemp[16].premium=&relationType=&prpCitemKindsTemp[13].premium=&prpCinsureds[0].soldierRelations=&prpCcarShipTax.prePolicyEndDate=&prpCremarks_[0].operatorCode=1102680006&relateSpecial[3]=050933&prpCcommissionsTemp_[0].costRate=&prpCsalesFixes_[0].riskCode=&prpCitemKindsTemp[16].startHour=&prpCitemKindsTemp[2].startDate=&prpCitemKindsTemp[12].basePremium=&ciInsureDemand.preferentialDay=&prpCitemKind.shortRate=100&isNetFlagEad=&prpCitemKindsTemp[9].benchMarkPremium=&iniPrpCplan_Flag=&prpCitemKindsTemp[16].rate=&prpCmainCommon.queryArea=11&prpCmain.checkFlag=&prpCcommissionsTemp_[0].currency=AED&isQueryCarModelFlag=&ciInsureDemandPay_[0].payCompany=&prpCmain.endHour=24&prpCitemKindsTemp[4].taxRate=&prpCtrafficRecordTemps_[0].claimDate=&prpCitemKindsTemp[0].taxPremium=&prpCitemKindsTemp[6].premium=&ICCardCHeck=&prpCcarShipTax.dutyPaidProofNo_1=&prpCitemKindsTemp[18].dutyFlag=&prpCitemKindCI.rate=0&CIDemandFecc_Flag=&prpCitemKind.shortRateFlag=2&prpCcarShipTax.dutyPaidProofNo_2=&relateSpecial[4]=050934&criterionFlag=0&monopolyConfigFlag=0&prpCitemKindsTemp[19].endHour=&ciInsureDemandLoss_[0].lossActionDesc=&prpCitemKindsTemp[5].disCount=&prpCmain.sumAmount=0&prpCitemKindsTemp[7].endHour=&prpCmain.comCode=11026871&prpCitemKindsTemp[13].flag= 200000&ciInsureDemandLoss_[0].id.serialNo=&prpCmain.startHour=0&prpCitemKindsTemp_[0].taxRate=&prpDdismantleDetails_[0].id.roleCode=&prpCengageTemps_[0].clauseName=&prpCcarShipTax.carKindCode=A01&prpCfixationCITemp.id.riskCode=&prpCitemKindsTemp[12].startDate=&isTaxFree=0&SZpurchasePriceUp=&ciInsureDemand.brandCName=&buttonProfitDetail[14]=....&prpCitemKindsTemp[11].taxRate=&prpCitemKindsTemp[4].benchMarkPremium=&prpCmainCar.carCheckTime=&timeFlag=&prpCmain.makeCom=11026871&projectBak=&udFlag=&prpDdismantleDetails_[0].roleCode_uni=&ciInsureDemand.tonCount=&prpCitemKindsTemp[1].basePremium=&idCardCheckInfo[0].issure=&ciInsureDemandPay_[0].compensateNo=&prpCremarks_[0].id.serialNo=&prpCinsureds[0].soldierIdentifyType1=000&prpCitemKindsTemp[14].endDate=&prpCcarShipTax.taxItemName=&prpCitemKindsTemp[0].deductibleRate=&noBringOutEngage=&prpDdismantleDetails_[0].businessNature=&prpCitemKindsTemp[14].rate=&prpCmainCar.carChecker=&prpCengageTemps_[0].flag=&prpCitemKindsTemp[15].endDate=&prpCinsureds_[0].causetroubleTimes=&planfee_index=0&prpCinsureds_[0].insuredFlag=&prpCengageTemps_[0].maxCount=&prpCitemKindsTemp[13].itemKindNo=&bIRiskWarningClaimItems_[0].insurerCode=&userCode=1102680006&buttonProfitDetail[13]=....&prpCcarShipTax.leviedDate=&prpCitemKindsTemp[5].endHour=&MOTORFASTTRACK=&hidden_index_ctraffic=0&prpCmain.handler1Code=13154727  &roleTypeName_[0]=&prpCitemKindsTemp[3].taxPremium=&prpCagentCIs_[0].costFee=&planStr=&prpCitemKindsTemp[9].endDate=&vat_switch=1&insuredChangeFlag=0&ciInsureDemandLoss_[0].lossAcceptDate=&isQuotatonFlag=1&prpCstampTaxCI.ciPayTax=&prpCitemKindsTemp[13].riskPremium=&button_InsuredFlagDetail_SubPage_Save_[0]=确定&prpCitemKindCI.adjustRate=1&prpCitemKindsTemp[9].rate=&ciInsureDemandCheckCIVo.checkAnswer=&prpCitemKindsTemp[10].dutyFlag=&enrollDateTrue=&prpCitemKindsTemp[16].flag= 200000&prpCitemKindsTemp[11].netPremium=&queryCar()=查&bIRiskWarningType=&prpCitemKindCI.taxPremium=&prpCfixationCITemp.realPayMentR=&biCiFlagIsChange=&relateSpecial[7]=      &prpCfixationCITemp.basePremium=&BiInsureDemandPay_[0].id.serialNo=&refreshEadFlag=1&prpCitemKindsTemp[13].dutyFlag=&prpCmainChannel.assetAgentName=&chgProfitFlag=00&prpCitemKindsTemp[7].rate=&prpCitemCar.modelCodeAlias=&prpCmainCar.agreeDriverFlag=&buttonProfitDetail[12]=....&prpCmainCar.newDeviceFlag=&bIRiskWarningClaimItems_[0].id.serialNo=&prpCitemKindsTemp[9].calculateFlag=N32Y000&prpDdismantleDetails_[0].roleName=&prpCitemKindsTemp[5].flag= 200000&bizNo=&queryCarInfChangeBtn=车型变动查询&prpCfixationTemp.payMentR=&lastDamagedBITemp=0&prpCitemKindsTemp[14].premium=&useYear=9&prpCitemCar.licenseColorCode=  &relateSpecial[8]=050935&prpCcarDevices_[0].actualValue=&handler1Info=&prpCitemKindCI.kindName=机动车交通事故强制责任保险&prpCitemKindsTemp[15].basePremium=&buttonProfitDetail[11]=....&prpCmain.checkUpCode=&prpCitemKindsTemp[19].kindName=不计免赔险（发动机涉水损失险）&prpCitemKindsTemp[1].calculateFlag=N11Y000&prpCitemCar.frameNo=&prpCitemKindsTemp[11].premium=&prpCitemKindsTemp[19].startHour=&rateTemp_[0]=&iniPrpCcarShipTax_Flag=&prpCinsureds[0].auditStatus=&relateSpecial[9]=050938&prpCitemKindsTemp[6].basePremium=&PurchasePriceScal=10&prpCitemKindsTemp[2].benchMarkPremium=&prpCmain.coinsFlag=00&prpCfixationTemp.id.riskCode=&prpCsalesDatils_[0].roleName=&prpCinsureds[0].soldierRelations1=0&prpCcarShipTax.taxItemCode=";
		String[] kvs = StringUtils.split(mapStr, "&");
		for (String kv : kvs) {
			String[] k_v = StringUtils.split(kv, "=");
			if (k_v.length > 1) {
				templateData.put(k_v[0], k_v[1]);
			} else
				templateData.put(k_v[0], "");
		}
		
		
	}
	
	void write2Html(String str) {
		PrintWriter out;
		try {
			out = new PrintWriter("d:\\1.html");
			out.write(str);
			out.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String login(String in) {
		// TODO Auto-generated method stub
		String out = "";
		
		JSONObject jsonObject = JSONObject.fromObject(in);
		String ukey = jsonObject.getString("ukey");
		String loginName = jsonObject.getString("loginName");
		String password = jsonObject.getString("password");
		
		String url = "http://10.134.136.48:8000/prpall/index.jsp";
		String httpOrgCreateTestRtn = httpClientUtil.doPost(url, new HashMap<String, String>(), charset);
		if (httpOrgCreateTestRtn == null) {
			return "{\"success\": false, \"msg\": \"连接人保服务器失败\"}";
		}
		
//		write2Html(httpOrgCreateTestRtn);

		Document doc = Jsoup.parse(httpOrgCreateTestRtn);
		System.out.println(doc.title());
		if(doc.title().contains("PICC承保系统"))
			return "{\"success\": false, \"msg\": \"已登录，不能重复登录!\"}";
		
		String action = "";
		if(doc.getElementById("fm") != null)
			action = doc.getElementById("fm").attr("action");
		
		url = "https://10.134.136.48:8888" + action;
		String lt = doc.getElementsByAttributeValue("name", "lt").get(0).attr("value");
		String postData = "PTAVersion=&toSign=&Signature=&rememberFlag=0&userMac=&key=yes&errorKey=null&loginMethod=nameAndPwd&username=" + loginName
				+ "&password=" + password + "&lt=" + lt + "&_eventId=submit&pcguid=&button.x=20&button.y=17";
		
		Map<String, String> map = null;
		try {
			map = parse2Map(postData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String respStr = httpClientUtil.doPost(url, map, charset);
		write2Html(respStr);
		doc = Jsoup.parse(respStr);
		System.out.println(doc.title());
		
//		httpOrgCreateTestRtn = httpClientUtil.doPost("http://10.134.136.48:8000/prpall/business/quickProposal.do?bizType=PROPOSAL&editType=NEW&is4S=Y",null,charset);
//		doc = Jsoup.parse(httpOrgCreateTestRtn);
//		try {
//			init(doc);
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}

		String comCode = templateData.get("prpCmain.comCode"); // 归属部门
		String handler1Code = templateData.get("prpCmain.handler1Code");//
		String agentCode = templateData.get("prpCmain.agentCode");//
		String businessNature = templateData.get("prpCmain.businessNature");//

		String param = "actionType=query&fieldIndex=206&fieldValue="
				+ agentCode
				+ "&codeMethod=change&codeType=select&codeRelation=0%2C1%2C2&isClear=Y&otherCondition=operateDate%3D"
				+ new SimpleDateFormat("yyyy-MM-dd").format(new Date())
				+ "%2CriskCode%3DDAA%2CcomCode%3D"
				+ comCode
				+ "%2CbusinessNature%3D"
				+ businessNature
				+ "&typeParam=&callBackMethod=MainTotal.setAgentCode()%3BMainTotal.clearForAgentType()%3BItemCar.checkSelectKYFlag()%3B&getDataMethod=getAgents";
		respStr = httpClientUtil.doPost("http://10.134.136.48:8000/prpall/common/changeCodeInput.do?" + param, new HashMap<String, String>(), charset);
		//System.out.println(respStr);// 11003O100375_FIELD_SEPARATOR_北京翰文兴业汽车俱乐部有限公司_FIELD_SEPARATOR_3O1000
		String[] _field_separator = respStr.split("_FIELD_SEPARATOR_");
		if(_field_separator.length < 3)
			return "{\"success\": false, \"msg\": \"登录北京人保失败\"}";
		
		String agentName = _field_separator[1];
		String agentType = _field_separator[2];
		templateData.put("agentType", agentType);
		try {
			param = "comCode=" + URLEncoder.encode(comCode, charset) + "&handler1Code=" + URLEncoder.encode(handler1Code, charset) + "&agentCode="
					+ URLEncoder.encode(agentCode, charset) + "&businessNature=" + URLEncoder.encode(businessNature, charset);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		respStr = httpClientUtil.doPost("http://10.134.136.48:8000/prpall/business/getCheckUserMsg.do?" + param, new HashMap<String, String>(), charset);
		Map retMap = JackJson.fromJsonToObject(respStr, Map.class);
		//System.out.println(retMap);
		String b = templateData.get("isCqp");
		String qualificationName = "";
		if (templateData.get("qualificationName") != null) {
			if (StringUtils.equals("1", b)) {
				qualificationName = templateData.get("prpQmainVoagentName");
			} else {
				qualificationName = templateData.get("prpCmainagentName");
			}
		}
		templateData.put("qualificationName", qualificationName);
		templateData.put("qualificationNo", (String) ((Map) ((List) retMap.get("data")).get(0)).get("permitNo"));

		templateData.put("prpCmainCommon.queryArea", "110000");
		templateData.put("queryArea", "北京市");
		templateData.put("prpCinsureds[0].countryCode", "CHN");
		templateData.put("resident[0]", "0");
		templateData.put("LicenseColorCodeDes", "蓝");
		templateData.put("prpCitemCar.licenseColorCode", "01");
		// 115192BJ
		templateData.put("agentCodeValidValue", ukey);
		templateData.put("agentCodeValidType", "U");
		
		out = "{\"success\": true, \"msg\": \"" + loginName + "," + agentName + ",登录成功\"}";
		return out;
	}
	
	void map2map(Map<String, String> map1, Map<String, String> map2) {
		for (String key : map1.keySet()) {
			map2.put(key,  map1.get(key));
		}
	}
	
	void map2mapEx(Map<String, String> map1, Map<String, Object> map2) {
		for (String key : map1.keySet()) {
			map2.put(key,  map1.get(key));
		}
	}
	
	String timeStamp2Date(String seconds, String format) {
		if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
			return "";
		}
		if (format == null || format.isEmpty())
			format = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String str = sdf.format(new Date(Long.valueOf(seconds)));
		System.out.println(str);
		return str;
	}
	
	public String queryBaseData(String in, Map<String, String> map) {
		// TODO Auto-generated method stub
		JSONObject jsonObject = JSONObject.fromObject(in);
		String chepNu = jsonObject.getString("chepNu");
		String chejh = jsonObject.getString("chejh");
		String fadjh = jsonObject.getString("fadjh");
		
		String enrollDate1, enrollDate2;
		
		Map<String, Object> outMap = new HashMap<String, Object>();
		outMap.put("uuid", map.get("uuid"));
		if(chepNu != null && !chepNu.equals(""))
			chepNu = chepNu.toUpperCase();
		else
			return queryBaseData2(chejh, fadjh, map);
		
		map2map(templateData, map);
		
		map.put("prpCitemCar.licenseNo", chepNu);
		outMap.put("licenseNo", chepNu);
		
		String url = "http://10.134.136.48:8000/prpall/carInf/getDataFromCiCarInfo.do";
		String respStr = httpClientUtil.doPost(url, map, "gbk");
		System.out.println(respStr);
		Map carMap = JackJson.fromJsonToObject(respStr, Map.class);
		if (((List) carMap.get("data")).size() > 0) {
			Map data = (Map) ((List) carMap.get("data")).get(0);
			map.put("prpCitemCar.frameNo", (String) data.get("rackNo"));
			outMap.put("frameNo", data.get("rackNo"));
			map.put("prpCitemCar.vinNo", (String) data.get("rackNo"));
			outMap.put("vinNo", data.get("rackNo"));
			map.put("prpCitemCar.engineNo", (String) data.get("engineNo"));
			outMap.put("engineNo", data.get("engineNo"));
			map.put("prpCitemCar.enrollDate", timeStamp2Date("" + (Long) ((Map) data.get("enrollDate")).get("time"), "yyyy-M-d"));
			outMap.put("enrollDate", map.get("prpCitemCar.enrollDate"));
			
			// 通过车牌号查到的初登日期
			enrollDate1 = map.get("prpCitemCar.enrollDate");
			
			int eny = 0;
			try {
				eny = new SimpleDateFormat("yyyy-M-d").parse(map.get("prpCitemCar.enrollDate")).getYear();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			map.put("prpCitemCar.useYears", "" + (new Date().getYear() - eny));
			outMap.put("useYears", map.get("prpCitemCar.useYears"));
			
			map.put("prpCcarShipTax.prePayTaxYear", "" + (Calendar.getInstance().get(Calendar.YEAR) - 1));
			map.put("prpCitemCar.carKindCode", "A01");
			//map.put("prpCitemCar.carKindCode", (String)data.get("carKindCode"));
			map.put("CarKindCodeDes", carTypeMap.get((String) data.get("carKindCode")));
			if (StringUtils.startsWith(((String) data.get("carKindCode")), "K")) {
				map.put("prpCitemCar.licenseType", "80");
			} else if (StringUtils.startsWith(((String) data.get("carKindCode")), "M")) {
				map.put("prpCitemCar.licenseType", "81");
			} else {
				map.put("prpCitemCar.licenseType", (String) ((Map) data.get("id")).get("licenseType"));
			}
			outMap.put("licenseType", map.get("prpCitemCar.licenseType"));
			
			String carOwner = (String) data.get("carOwner");
			if (null != carOwner) {
				map.put("insuredCarOwner", carOwner);
				outMap.put("insuredCarOwner", map.get("insuredCarOwner"));
				map.put("prpCinsureds[0].insuredName", carOwner);
				outMap.put("insuredName", map.get("prpCinsureds[0].insuredName"));
				map.put("owner", carOwner);
				outMap.put("owner", map.get("owner"));
				map.put("prpCcarShipTax.taxPayerName", carOwner);
			}

			String tonCount = data.get("tonCount") == null ? "0" : data.get("tonCount") + "";
			map.put("prpCitemCar.tonCount", tonCount);
//			outMap.put("tonCount", map.get("prpCitemCar.tonCount"));

			String seatCount = "" + (Integer) data.get("seatCount");
			if (StringUtils.isNotBlank(seatCount)) {
				map.put("prpCitemCar.seatCount", seatCount);
				outMap.put("seatCount", map.get("prpCitemCar.seatCount"));
			}

		} else
			return "{\"success\": flase, \"msg\": \"未找到" + chepNu + "的车辆信息\"}";;

		url = "http://10.134.136.48:8000/prpall/carInf/getCarModelInfo.do";
		respStr = httpClientUtil.doPost(url, map, "gbk");
		System.out.println(respStr);

		Map car2Map = JackJson.fromJsonToObject(respStr, Map.class);

		List<Map> dataList = (List<Map>) car2Map.get("data");
		if (dataList.size() > 0) {
			Map itemMap = dataList.get(0);
			if(itemMap.get("refCode2") != null && !itemMap.get("refCode2").equals(""))
				return "{\"success\": flase, \"msg\": \"" + itemMap.get("refCode2") + "\"}";
				
			map.put("prpCitemCar.brandName", (String) itemMap.get("modelName"));
			outMap.put("brandName", map.get("prpCitemCar.brandName"));
			map.put("prpCitemCar.countryNature", (String) itemMap.get("vehicleType"));
			map.put("prpCitemCar.modelCode", (String) itemMap.get("modelCode"));
			outMap.put("modelCode", map.get("prpCitemCar.modelCode"));
			map.put("CarActualValueTrue", "" + itemMap.get("replaceMentValue"));
			map.put("prpCitemCar.purchasePrice", "" + itemMap.get("replaceMentValue"));
			map.put("purchasePriceOld", "" + itemMap.get("replaceMentValue"));
			
			if (itemMap.get("disPlaceMent") != null) {
				map.put("prpCitemCar.exhaustScale", "" + Integer.parseInt(itemMap.get("disPlaceMent") + "") / 1000.00);
			} else {
				map.put("prpCitemCar.exhaustScale", "");
			}
			outMap.put("exhaustScale", map.get("prpCitemCar.exhaustScale"));

			if (!map.get("comCode").startsWith("11")) {
				System.out.println("comCode 不是11开头");
				return null;
			} else {
				String seatCount = map.get("prpCitemCar.seatCount");
				String l = "" + itemMap.get("rateDPassengercapacity");
				String w = map.get("riskCode");
				if (seatCount.equals("0") || seatCount.equals("") && l != null) {
					map.put("prpCitemCar.seatCount", l);
				}
				if ("DAV".equals(w) && Integer.parseInt(seatCount) >= 9) {
					map.put("prpCitemCar.brandName", "");
					map.put("prpCitemCar.modelCode", "");
				}
				String F = itemMap.get("tonnage") == null ? "0" : itemMap.get("tonnage") + "";
				if (F != null && (map.get("prpCitemCar.tonCount").equals("0") || map.get("prpCitemCar.tonCount").equals(""))) {
					map.put("prpCitemCar.tonCount", F);
				}
				map.put("prpCitemCar.modelDemandNo", (String) itemMap.get("modelCode"));
				map.put("prpCitemCar.modelDemandNo", (String) ((Map) itemMap.get("id")).get("pmQueryNo"));
				map.put("isQueryCarModelFlag", "1");
			}
			map.put("_insuredName", (String) itemMap.get("owner"));

			url = "http://10.134.136.48:8000/prpall/business/calActualValue.do";
			respStr = httpClientUtil.doPost(url, map, "gbk");
			System.out.println(respStr);

			map.put("prpCitemCar.actualValue", respStr);
			outMap.put("actualValue", respStr);
			map.put("premiumChangeFlag", "1");

		} else {
			System.out.println("getCarModelInfo 返回信息不止一条");
			return null;
		}
		
		// 查询续保记录
		url = "http://10.134.136.48:8000/prpall/business/selectRenewal.do";
		Map<String, String> map4xub = null;
		try {
			map4xub = parse2Map("prpCrenewalVo.licenseNo=" + chepNu + "&prpCrenewalVo.licenseType=02");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		respStr = httpClientUtil.doPost(url, map4xub, "GBK");

		String lastPolicyNo = "";
		JSONObject jObj = JSONObject.fromObject(respStr);
		JSONArray jdatas = jObj.getJSONArray("data");
		Iterator<Object> it = jdatas.iterator();
		while (it.hasNext()) {
			JSONObject obj = (JSONObject) it.next();
			lastPolicyNo = obj.getString("policyNo");
		}
		
		//outMap.put("lastPolicyNo", lastPolicyNo);
		System.out.println("lastPolicyNo: " + lastPolicyNo);
		
		
		Map<String, Object> xubCopyMap = new HashMap<String, Object>();
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		if(!lastPolicyNo.equals("")) {
			url = "http://10.134.136.48:8000/prpall/business/quickProposalEditRenewalCopy.do?bizNo=" + lastPolicyNo;
			System.out.println("续保复制: " + url);
			respStr = httpClientUtil.doPost(url, new HashMap<String, String>(), "GBK");
			
//			PrintWriter out;
//			try {
//				out = new PrintWriter("d:\\1.html");
//				out.write(respStr);
//				respStr2 = readFile2Strng("d:\\1.html");
//				
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
//			write2Html(respStr);
			
			Document doc = Jsoup.parse(respStr);
			
			//if(doc.getElementById("prpCitemCar.licenseNo") != null) {
				//return "{\"success\": flase, \"msg\": \"没查到续保记录\"}";
			
			if(doc.getElementById("prpCmainHeadInput") != null) {
				String lastYearBaoQi = "";
				Elements elements = doc.select("#prpCmainHeadInput strong");
				for (Element element : elements) {
					if(element.toString().contains("上年")) {
						lastYearBaoQi = element.text();
						break;
					}
				}
				System.out.println("上年投保日期: " + lastYearBaoQi);
				xubCopyMap.put("lastYearBaoQi", lastYearBaoQi);
			}
			
			
			if(doc.getElementById("prpCitemCar.licenseNo") != null) {
				String licenseNo = doc.getElementById("prpCitemCar.licenseNo").attr("value");
				System.out.println("车牌号: " + licenseNo);
				xubCopyMap.put("licenseNo", licenseNo);
			}
			
			if(doc.getElementById("prpCitemCar.modelCodeAlias") != null) {
				String modelCodeAlias = doc.getElementById("prpCitemCar.modelCodeAlias").attr("value");
				System.out.println("车型别名: " + modelCodeAlias);
				xubCopyMap.put("modelCodeAlias", modelCodeAlias);
			}
			
			String new_engineNo = "";
			if(doc.getElementById("prpCitemCar.engineNo") != null) {
				String engineNo = doc.getElementById("prpCitemCar.engineNo").attr("value");
				System.out.println("发动机号: " + engineNo);
				xubCopyMap.put("engineNo", engineNo);
				new_engineNo = engineNo;
			}
			
			String new_frameNo = "";
			if(doc.getElementById("prpCitemCar.frameNo") != null) {
				String frameNo = doc.getElementById("prpCitemCar.frameNo").attr("value");
				System.out.println("车架号: " + frameNo);
				xubCopyMap.put("frameNo", frameNo);
				new_frameNo = frameNo;
			}
			
			if(doc.getElementById("prpCitemCar.useNatureCode") != null) {
				String useNatureCode = doc.getElementById("prpCitemCar.useNatureCode").attr("title");
				System.out.println("使用性质: " + useNatureCode);
				xubCopyMap.put("useNatureCode", useNatureCode);
			}
			
			if(doc.getElementById("prpCitemCar.enrollDate") != null) {
				String enrollDate = doc.getElementById("prpCitemCar.enrollDate").attr("value");
				System.out.println("初登日期: " + enrollDate);
				xubCopyMap.put("enrollDate", enrollDate);
				
				// 通过上一年的续保数据查到的初登日期
				enrollDate2 = enrollDate;
				
				try {
					Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(enrollDate1);
					Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(enrollDate2);
					
					if(!date1.equals(date2)) {
						System.out.println("用户换车了");
						System.out.println("车架号: " + new_frameNo + "\t发动机号: " + new_engineNo);
						
						return queryBaseData2(new_frameNo, new_engineNo, map);
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(doc.getElementById("prpCitemCar.modelCode") != null) {
				String modelCode = doc.getElementById("prpCitemCar.modelCode").attr("value");
				System.out.println("车型编码: " + modelCode);
				xubCopyMap.put("modelCode", modelCode);
			}
			
			if(doc.getElementById("prpCitemCar.purchasePrice") != null) {
				String purchasePrice = doc.getElementById("prpCitemCar.purchasePrice").attr("value");
				System.out.println("新车购置价格: " + purchasePrice);
				xubCopyMap.put("purchasePrice", purchasePrice);
			}
			
			if(doc.getElementById("prpCitemCar.seatCount") != null) {
				String seatCount = doc.getElementById("prpCitemCar.seatCount").attr("value");
				System.out.println("核定载客量(人): " + seatCount);
				xubCopyMap.put("seatCount", seatCount);
			}
			
			if(doc.getElementById("prpCitemCar.exhaustScale") != null) {
				String exhaustScale = doc.getElementById("prpCitemCar.exhaustScale").attr("value");
				System.out.println("排量/功率(升): " + exhaustScale);
				xubCopyMap.put("exhaustScale", exhaustScale);
			}
			
			if(doc.getElementById("prpCinsureds[0].insuredName") != null) {
				String insuredName = doc.getElementById("prpCinsureds[0].insuredName").attr("value");
				System.out.println("被保险人: " + insuredName);
				xubCopyMap.put("insuredName", insuredName);
			}
			
			if(doc.getElementById("prpCinsureds[0].identifyNumber") != null) {
				String identifyNumber = doc.getElementById("prpCinsureds[0].identifyNumber").attr("value");
				System.out.println("身份证: " + identifyNumber);
				xubCopyMap.put("identifyNumber", identifyNumber);
			}
			
			if(doc.getElementById("prpCinsureds[0].insuredAddress") != null) {
				String insuredAddress = doc.getElementById("prpCinsureds[0].insuredAddress").attr("value");
				System.out.println("地址: " + insuredAddress);
				xubCopyMap.put("insuredAddress", insuredAddress);
			}
			
			if(doc.getElementById("prpCinsureds[0].mobile") != null) {
				String mobile = doc.getElementById("prpCinsureds[0].mobile").attr("value");
				System.out.println("电话: " + mobile);
				xubCopyMap.put("mobile", mobile);
			}
			
			System.out.println();
			
			Element element = null;
			for(int i = 0; i < 11; i++) {
				Map<String, String> xianzMap = new HashMap<String, String>();
				
				element = doc.getElementById("prpCitemKindsTemp[" + i + "].chooseFlag");
//				System.out.println(element.toString());
				String xianz = "";
				if(element != null)
					xianz = element.attr("checked");
				
//				if(xianz.equals(""))
//					continue;
				
				if(i == 0)
					xianzMap.put("A", xianz);
				if(i == 1)
					xianzMap.put("G", xianz);
				if(i == 2)
					xianzMap.put("B", xianz);
				if(i == 3)
					xianzMap.put("D11", xianz);
				if(i == 4)
					xianzMap.put("D12", xianz);
				if(i == 5)
					xianzMap.put("L", xianz);
				if(i == 6)
					xianzMap.put("F", xianz);
				if(i == 8)
					xianzMap.put("Z", xianz);
				if(i == 9)
					xianzMap.put("X1", xianz);
				
				element = doc.getElementById("prpCitemKindsTemp[" + i + "].specialFlag");
				String bujmp = "";
				if(element != null)
					bujmp = element.attr("checked");
				xianzMap.put("bujmp", bujmp);
				
				element = doc.getElementById("prpCitemKindsTemp[" + i + "].amount");
				String amount = "";
				if(element != null)
					amount = element.attr("value");
				xianzMap.put("amount", amount);
				
				element = doc.getElementById("prpCitemKindsTemp[" + i + "].modeCode");
				String modeCode = "";
				if(element != null) {
					Elements tmp = element.select("option");
					for(Element et : tmp) {
						System.out.println(et.toString());
						if(et.hasAttr("selected")) {
							modeCode = tmp.get(0).attr("value");
							break;
						}
					}
				}
				xianzMap.put("modeCode", modeCode);
				
				System.out.print(i + ": " + xianz);
				System.out.print("\t\tbujmp: " + bujmp);
				System.out.println("\t\tamount: " + amount);
				System.out.println("\t\tmodeCode: " + modeCode);
				
				list.add(xianzMap);
			}
		}
		
		xubCopyMap.put("xianZDetail", list);
		
		outMap.put("xubCopy", xubCopyMap);
//		map2mapEx(map, outMap);
		
		return JSONObject.fromObject(outMap) + "";
	}
	
	String queryBaseData2(String new_frameNo, String new_engineNo, Map<String, String> map) {
		Map<String, Object> outMap = new HashMap<String, Object>();
		outMap.put("uuid", map.get("uuid"));
		
		map2map(templateData, map);
		
		String chepNu = "";
		//String url = "http://10.134.136.48:8000/prpall/carInf/getDataFromCiCarInfo.do";
		String url2 = "http://10.134.136.48:8000/prpall/carInf/getCarInfoList.do"
				+ "?pageSize=10&pageNo=1&comCode=11026871&prpCitemCar.licenseNo=%BE%A9&prpCitemCar.engineNo=" + new_engineNo + "&prpCitemCar.frameNo=" + new_frameNo + "&queryType=1";
		
		String respStr = httpClientUtil.doPost(url2, new HashMap<String, String>(), "gbk");
		System.out.println(respStr);
		Map carMap = JackJson.fromJsonToObject(respStr, Map.class);
		
		if (((List) carMap.get("data")).size() > 0) {
			Map data = (Map) ((List) carMap.get("data")).get(0);
			outMap.put("licenseNo", data.get("id.licenseNo"));
			chepNu = data.get("id.licenseNo") + "";
			map.put("prpCitemCar.licenseNo", chepNu);
			map.put("prpCitemCar.frameNo", (String) data.get("rackNo"));
			outMap.put("frameNo", data.get("rackNo"));
			map.put("prpCitemCar.vinNo", (String) data.get("rackNo"));
			outMap.put("vinNo", data.get("rackNo"));
			map.put("prpCitemCar.engineNo", (String) data.get("engineNo"));
			outMap.put("engineNo", data.get("engineNo"));
			map.put("prpCitemCar.enrollDate", timeStamp2Date("" + (Long) ((Map) data.get("enrollDate")).get("time"), "yyyy-M-d"));
			outMap.put("enrollDate", map.get("prpCitemCar.enrollDate"));
			
			int eny = 0;
			try {
				eny = new SimpleDateFormat("yyyy-M-d").parse(map.get("prpCitemCar.enrollDate")).getYear();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			map.put("prpCitemCar.useYears", "" + (new Date().getYear() - eny));
			outMap.put("useYears", map.get("prpCitemCar.useYears"));
			
			map.put("prpCcarShipTax.prePayTaxYear", "" + (Calendar.getInstance().get(Calendar.YEAR) - 1));
			map.put("prpCitemCar.carKindCode", "A01");
			//map.put("prpCitemCar.carKindCode", (String)data.get("carKindCode"));
			map.put("CarKindCodeDes", carTypeMap.get((String) data.get("carKindCode")));
			if (StringUtils.startsWith(((String) data.get("carKindCode")), "K")) {
				map.put("prpCitemCar.licenseType", "80");
			} else if (StringUtils.startsWith(((String) data.get("carKindCode")), "M")) {
				map.put("prpCitemCar.licenseType", "81");
			} else {
				map.put("prpCitemCar.licenseType", data.get("id.licenseType") + "");
			}
			outMap.put("licenseType", map.get("prpCitemCar.licenseType"));
			
			String carOwner = (String) data.get("carOwner");
			if (null != carOwner) {
				map.put("insuredCarOwner", carOwner);
				outMap.put("insuredCarOwner", map.get("insuredCarOwner"));
				map.put("prpCinsureds[0].insuredName", carOwner);
				outMap.put("insuredName", map.get("prpCinsureds[0].insuredName"));
				map.put("owner", carOwner);
				outMap.put("owner", map.get("owner"));
				map.put("prpCcarShipTax.taxPayerName", carOwner);
			}

			String tonCount = data.get("tonCount") == null ? "0" : data.get("tonCount") + "";
			map.put("prpCitemCar.tonCount", tonCount);
//			outMap.put("tonCount", map.get("prpCitemCar.tonCount"));

			String seatCount = "" + (Integer) data.get("seatCount");
			if (StringUtils.isNotBlank(seatCount)) {
				map.put("prpCitemCar.seatCount", seatCount);
				outMap.put("seatCount", map.get("prpCitemCar.seatCount"));
			}

		} else
			return "{\"success\": flase, \"msg\": \"未找到" + new_frameNo + ", " + new_engineNo + "的车辆信息\"}";;

		String url = "http://10.134.136.48:8000/prpall/carInf/getCarModelInfo.do";
		respStr = httpClientUtil.doPost(url, map, "gbk");
		System.out.println(respStr);

		Map car2Map = JackJson.fromJsonToObject(respStr, Map.class);

		List<Map> dataList = (List<Map>) car2Map.get("data");
		if (dataList.size() > 0) {
			Map itemMap = dataList.get(0);
			if(itemMap.get("refCode2") != null && !itemMap.get("refCode2").equals(""))
				return "{\"success\": flase, \"msg\": \"" + itemMap.get("refCode2") + "\"}";
				
			map.put("prpCitemCar.brandName", (String) itemMap.get("modelName"));
			outMap.put("brandName", map.get("prpCitemCar.brandName"));
			map.put("prpCitemCar.countryNature", (String) itemMap.get("vehicleType"));
			map.put("prpCitemCar.modelCode", (String) itemMap.get("modelCode"));
			outMap.put("modelCode", map.get("prpCitemCar.modelCode"));
			map.put("CarActualValueTrue", "" + itemMap.get("replaceMentValue"));
			map.put("prpCitemCar.purchasePrice", "" + itemMap.get("replaceMentValue"));
			map.put("purchasePriceOld", "" + itemMap.get("replaceMentValue"));
			
			if (itemMap.get("disPlaceMent") != null) {
				map.put("prpCitemCar.exhaustScale", "" + Integer.parseInt(itemMap.get("disPlaceMent") + "") / 1000.00);
			} else {
				map.put("prpCitemCar.exhaustScale", "");
			}
			outMap.put("exhaustScale", map.get("prpCitemCar.exhaustScale"));

			if (!map.get("comCode").startsWith("11")) {
				System.out.println("comCode 不是11开头");
				return null;
			} else {
				String seatCount = map.get("prpCitemCar.seatCount");
				String l = "" + itemMap.get("rateDPassengercapacity");
				String w = map.get("riskCode");
				if (seatCount.equals("0") || seatCount.equals("") && l != null) {
					map.put("prpCitemCar.seatCount", l);
				}
				if ("DAV".equals(w) && Integer.parseInt(seatCount) >= 9) {
					map.put("prpCitemCar.brandName", "");
					map.put("prpCitemCar.modelCode", "");
				}
				String F = itemMap.get("tonnage") == null ? "0" : itemMap.get("tonnage") + "";
				if (F != null && (map.get("prpCitemCar.tonCount").equals("0") || map.get("prpCitemCar.tonCount").equals(""))) {
					map.put("prpCitemCar.tonCount", F);
				}
				map.put("prpCitemCar.modelDemandNo", (String) itemMap.get("modelCode"));
				map.put("prpCitemCar.modelDemandNo", (String) ((Map) itemMap.get("id")).get("pmQueryNo"));
				map.put("isQueryCarModelFlag", "1");
			}
			map.put("_insuredName", (String) itemMap.get("owner"));

			url = "http://10.134.136.48:8000/prpall/business/calActualValue.do";
			respStr = httpClientUtil.doPost(url, map, "gbk");
			System.out.println(respStr);

			map.put("prpCitemCar.actualValue", respStr);
			outMap.put("actualValue", respStr);
			map.put("premiumChangeFlag", "1");

		} else {
			System.out.println("getCarModelInfo 返回信息不止一条");
			return null;
		}
		
		// 查询续保记录
		url = "http://10.134.136.48:8000/prpall/business/selectRenewal.do";
		Map<String, String> map4xub = null;
		try {
			map4xub = parse2Map("prpCrenewalVo.licenseNo=" + chepNu + "&prpCrenewalVo.licenseType=02");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		respStr = httpClientUtil.doPost(url, map4xub, "GBK");

		String lastPolicyNo = "";
		JSONObject jObj = JSONObject.fromObject(respStr);
		JSONArray jdatas = jObj.getJSONArray("data");
		Iterator<Object> it = jdatas.iterator();
		while (it.hasNext()) {
			JSONObject obj = (JSONObject) it.next();
			lastPolicyNo = obj.getString("policyNo");
		}
		
		//outMap.put("lastPolicyNo", lastPolicyNo);
		System.out.println("lastPolicyNo: " + lastPolicyNo);
		
		
		Map<String, Object> xubCopyMap = new HashMap<String, Object>();
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		if(!lastPolicyNo.equals("")) {
			url = "http://10.134.136.48:8000/prpall/business/quickProposalEditRenewalCopy.do?bizNo=" + lastPolicyNo;
			System.out.println("续保复制: " + url);
			respStr = httpClientUtil.doPost(url, new HashMap<String, String>(), "GBK");
			
//			PrintWriter out;
//			try {
//				out = new PrintWriter("d:\\1.html");
//				out.write(respStr);
//				respStr2 = readFile2Strng("d:\\1.html");
//				
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
//			write2Html(respStr);
			
			Document doc = Jsoup.parse(respStr);
			
			//if(doc.getElementById("prpCitemCar.licenseNo") != null) {
				//return "{\"success\": flase, \"msg\": \"没查到续保记录\"}";
			
			if(doc.getElementById("prpCmainHeadInput") != null) {
				String lastYearBaoQi = "";
				Elements elements = doc.select("#prpCmainHeadInput strong");
				for (Element element : elements) {
					if(element.toString().contains("上年")) {
						lastYearBaoQi = element.text();
						break;
					}
				}
				System.out.println("上年投保日期: " + lastYearBaoQi);
				xubCopyMap.put("lastYearBaoQi", lastYearBaoQi);
			}
			
			
			if(doc.getElementById("prpCitemCar.licenseNo") != null) {
				String licenseNo = doc.getElementById("prpCitemCar.licenseNo").attr("value");
				System.out.println("车牌号: " + licenseNo);
				xubCopyMap.put("licenseNo", licenseNo);
			}
			
			if(doc.getElementById("prpCitemCar.modelCodeAlias") != null) {
				String modelCodeAlias = doc.getElementById("prpCitemCar.modelCodeAlias").attr("value");
				System.out.println("车型别名: " + modelCodeAlias);
				xubCopyMap.put("modelCodeAlias", modelCodeAlias);
			}
			
			if(doc.getElementById("prpCitemCar.engineNo") != null) {
				String engineNo = doc.getElementById("prpCitemCar.engineNo").attr("value");
				System.out.println("发动机号: " + engineNo);
				xubCopyMap.put("engineNo", engineNo);
				new_engineNo = engineNo;
			}
			
			if(doc.getElementById("prpCitemCar.frameNo") != null) {
				String frameNo = doc.getElementById("prpCitemCar.frameNo").attr("value");
				System.out.println("车架号: " + frameNo);
				xubCopyMap.put("frameNo", frameNo);
				new_frameNo = frameNo;
			}
			
			if(doc.getElementById("prpCitemCar.useNatureCode") != null) {
				String useNatureCode = doc.getElementById("prpCitemCar.useNatureCode").attr("title");
				System.out.println("使用性质: " + useNatureCode);
				xubCopyMap.put("useNatureCode", useNatureCode);
			}
			
			if(doc.getElementById("prpCitemCar.enrollDate") != null) {
				String enrollDate = doc.getElementById("prpCitemCar.enrollDate").attr("value");
				System.out.println("初登日期: " + enrollDate);
				xubCopyMap.put("enrollDate", enrollDate);
			}
			
			if(doc.getElementById("prpCitemCar.modelCode") != null) {
				String modelCode = doc.getElementById("prpCitemCar.modelCode").attr("value");
				System.out.println("车型编码: " + modelCode);
				xubCopyMap.put("modelCode", modelCode);
			}
			
			if(doc.getElementById("prpCitemCar.purchasePrice") != null) {
				String purchasePrice = doc.getElementById("prpCitemCar.purchasePrice").attr("value");
				System.out.println("新车购置价格: " + purchasePrice);
				xubCopyMap.put("purchasePrice", purchasePrice);
			}
			
			if(doc.getElementById("prpCitemCar.seatCount") != null) {
				String seatCount = doc.getElementById("prpCitemCar.seatCount").attr("value");
				System.out.println("核定载客量(人): " + seatCount);
				xubCopyMap.put("seatCount", seatCount);
			}
			
			if(doc.getElementById("prpCitemCar.exhaustScale") != null) {
				String exhaustScale = doc.getElementById("prpCitemCar.exhaustScale").attr("value");
				System.out.println("排量/功率(升): " + exhaustScale);
				xubCopyMap.put("exhaustScale", exhaustScale);
			}
			
			if(doc.getElementById("prpCinsureds[0].insuredName") != null) {
				String insuredName = doc.getElementById("prpCinsureds[0].insuredName").attr("value");
				System.out.println("被保险人: " + insuredName);
				xubCopyMap.put("insuredName", insuredName);
			}
			
			if(doc.getElementById("prpCinsureds[0].identifyNumber") != null) {
				String identifyNumber = doc.getElementById("prpCinsureds[0].identifyNumber").attr("value");
				System.out.println("身份证: " + identifyNumber);
				xubCopyMap.put("identifyNumber", identifyNumber);
			}
			
			if(doc.getElementById("prpCinsureds[0].insuredAddress") != null) {
				String insuredAddress = doc.getElementById("prpCinsureds[0].insuredAddress").attr("value");
				System.out.println("地址: " + insuredAddress);
				xubCopyMap.put("insuredAddress", insuredAddress);
			}
			
			if(doc.getElementById("prpCinsureds[0].mobile") != null) {
				String mobile = doc.getElementById("prpCinsureds[0].mobile").attr("value");
				System.out.println("电话: " + mobile);
				xubCopyMap.put("mobile", mobile);
			}
			
			System.out.println();
			
			Element element = null;
			for(int i = 0; i < 11; i++) {
				Map<String, String> xianzMap = new HashMap<String, String>();
				
				element = doc.getElementById("prpCitemKindsTemp[" + i + "].chooseFlag");
//				System.out.println(element.toString());
				String xianz = "";
				if(element != null)
					xianz = element.attr("checked");
				
//				if(xianz.equals(""))
//					continue;
				
				if(i == 0)
					xianzMap.put("A", xianz);
				if(i == 1)
					xianzMap.put("G", xianz);
				if(i == 2)
					xianzMap.put("B", xianz);
				if(i == 3)
					xianzMap.put("D11", xianz);
				if(i == 4)
					xianzMap.put("D12", xianz);
				if(i == 5)
					xianzMap.put("L", xianz);
				if(i == 6)
					xianzMap.put("F", xianz);
				if(i == 8)
					xianzMap.put("Z", xianz);
				if(i == 9)
					xianzMap.put("X1", xianz);
				
				element = doc.getElementById("prpCitemKindsTemp[" + i + "].specialFlag");
				String bujmp = "";
				if(element != null)
					bujmp = element.attr("checked");
				xianzMap.put("bujmp", bujmp);
				
				element = doc.getElementById("prpCitemKindsTemp[" + i + "].amount");
				String amount = "";
				if(element != null)
					amount = element.attr("value");
				xianzMap.put("amount", amount);
				
				element = doc.getElementById("prpCitemKindsTemp[" + i + "].modeCode");
				String modeCode = "";
				if(element != null) {
					Elements tmp = element.select("option");
					for(Element et : tmp) {
						System.out.println(et.toString());
						if(et.hasAttr("selected")) {
							modeCode = tmp.get(0).attr("value");
							break;
						}
					}
				}
				xianzMap.put("modeCode", modeCode);
				
				System.out.print(i + ": " + xianz);
				System.out.print("\t\tbujmp: " + bujmp);
				System.out.println("\t\tamount: " + amount);
				System.out.println("\t\tmodeCode: " + modeCode);
				
				list.add(xianzMap);
			}
		}
		
		xubCopyMap.put("xianZDetail", list);
		
		outMap.put("xubCopy", xubCopyMap);
		
		return JSONObject.fromObject(outMap) + "";
	}

	String jisJQX(Map<String, String> baseDataMap) {
		String rtnData = "";
		
		baseDataMap.put("isBICI", "01");
		baseDataMap.put("prpCmainCI.sumAmount","122000"); 
		baseDataMap.put("prpCitemKindCI.familyNo","1");//null
		baseDataMap.put("prpCitemKindCI.amount","122000");//0
		baseDataMap.put("prpCitemKindCI.adjustRate","0.9");//1

		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 1);
		String startDate = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		String prpCmainCI_startDate = baseDataMap.get("prpCmainCI.startDate");
		if (prpCmainCI_startDate == null || prpCmainCI_startDate.equals("")) {
			baseDataMap.put("prpCmainCI.startDate", startDate);
			prpCmainCI_startDate = startDate;
		}

		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(prpCmainCI_startDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.setTime(date);
		c.add(Calendar.YEAR, 1);
		c.add(Calendar.DATE, -1);
		String endDate = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		baseDataMap.put("prpCmainCI.endDate", endDate);
		
		System.out.println("计算交强");
		System.out.println("prpCmainCI.startDate: " + baseDataMap.get("prpCmainCI.startDate"));
		System.out.println("prpCmainCI.endDate: " + baseDataMap.get("prpCmainCI.endDate"));

		rtnData = httpClientUtil.doPost("http://10.134.136.48:8000/prpall/business/caculatePremiunForFG.do", baseDataMap, "gbk");
		System.out.println("交强返回: " + rtnData);
		if(rtnData.contains("NullPointerException"))
			return "{\"success\": flase, \"msg\": \"计算交强险失败\"}";
		
		if(rtnData.contains("单点登录中心"))
			return "{\"success\": flase, \"msg\": \"session超时，重新登录\"}";
		
		JSONObject jObj = JSONObject.fromObject(rtnData);
		if(jObj.get("success") != null && jObj.get("success").equals(false)) {
			return "{\"success\":false, \"msg\":\"" + jObj.get("msg") + "\"}";
		}
		
		if(jObj.get("msg") != null) {
			String errMessage = jObj.get("msg").toString();
			if(errMessage.length() > 0) {
				return "{\"success\":false, \"msg\":\"" + errMessage + "\"}";
			}
		}
		
		Map<String, Object> outMap = new HashMap<String, Object>();
		outMap.put("saveHebId", baseDataMap.get("uuid"));
		outMap.put("success", false);
		
		String uuid = UUID.randomUUID().toString();
		outMap.put("saveHebId2", uuid);
		suanfResultMap.put(uuid, rtnData);
		
		JSONArray jdatas = jObj.getJSONArray("data");
		JSONObject jObj2 = JSONObject.fromObject(jdatas.get(0));
		if(jObj2.get("errMessage") != null && !jObj2.get("errMessage").equals("null")) {
			String errMessage = jObj2.get("errMessage").toString();
			if(errMessage.length() > 0) {
				return "{success:false,\"msg\":\"" + errMessage + "\"}";
			}
		}
		
		JSONArray jObj3 = jObj2.getJSONArray("ciInsureVOList");
		JSONObject jObj4 = JSONObject.fromObject(jObj3.get(0));
		JSONObject jObj5 = jObj4.getJSONObject("ciInsureDemand");
		
		String jq_basePremium = "";
		String jq_premium = "";
		String carShipTax= "";
//		String seatCount = "";
//		String startdate1 = null, enddate1 = null, startdate2 = null, enddate2 = null;
//		String fadjNu = null, chejNu = null;
		String carOwner = "", brandCName = "";
		
		//List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> mapJiaoQ = new HashMap<String, Object>();
		
		if(jObj5.get("carOwner") != null) {
			carOwner = jObj5.get("carOwner").toString();
			System.out.println("车主: " + carOwner);
			mapJiaoQ.put("carOwner", carOwner);
		}
//		if(jObj5.get("brandCName") != null) {
//			brandCName = jObj5.get("brandCName").toString();
//			System.out.println("厂牌: " + brandCName);
//			mapJiaoQ.put("brandCName", brandCName);
//		}
		
		if(jObj5.get("basePremium") != null) {
			jq_basePremium = jObj5.get("basePremium").toString();
			System.out.println("基础保费: " + jq_basePremium);
			mapJiaoQ.put("jq_basePremium", jq_basePremium);
		}
		
		if(jObj5.get("premium") != null) {
			jq_premium = jObj5.get("premium").toString();
			System.out.println("应交保费: " + jq_premium);
			mapJiaoQ.put("jq_premium", jq_premium);
		}
		
		if(jObj5.get("taxTotal") != null) {
			carShipTax = jObj5.get("taxTotal").toString();
			System.out.println("车船税: " + carShipTax);
			mapJiaoQ.put("carShipTax", carShipTax);
		}
		
//		if(jObj5.get("seatCount") != null) {
//			seatCount = jObj5.get("seatCount").toString();
//			System.out.println("座位: " + seatCount);
//			mapJiaoQ.put("seatCount", seatCount);
//		}
		
//		if(jObj5.get("engineNo") != null) {
//			fadjNu = jObj5.get("engineNo").toString();
//			System.out.println("发动机号: " + fadjNu);
//			mapJiaoQ.put("fadjNu", fadjNu);
//		}
//		
//		if(jObj5.get("frameNo") != null) {
//			chejNu = jObj5.get("frameNo").toString();
//			System.out.println("车架号: " + chejNu);
//			mapJiaoQ.put("chejNu", chejNu);
//		}
		
//		if(jObj5.get("lastyearstartdate") != null) {
//			JSONObject jObj8 = JSONObject.fromObject(jObj5.get("lastyearstartdate"));
//			Object obj = jObj8.get("time");
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			
//			startdate1 = sdf.format(Long.parseLong(obj.toString()));
//			startdate2 = startdate1;
//		}
//		
//		if(jObj5.get("lastyearenddate") != null) {
//			JSONObject jObj8 = JSONObject.fromObject(jObj5.get("lastyearenddate"));
//			Object obj = jObj8.get("time");
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			
//			enddate1 = sdf.format(Long.parseLong(obj.toString()));
//			enddate2 = enddate1;
//		}
		
//		list.add(mapJiaoQ);
		outMap.put("success", true);
		outMap.put("JQX", mapJiaoQ);

		JSONObject jsonObject = JSONObject.fromObject(outMap);

		return jsonObject.toString();
	}
	
	boolean setXianZData(String xianZDetail, Map<String, String> map) {
		boolean isEmpty = false;

		Iterator<Object> it = JSONArray.fromObject(xianZDetail).iterator();
		while (it.hasNext()) {
			JSONObject obj = (JSONObject) it.next();

			if (obj.getString("xianZ") != null && obj.getString("xianZ").equals("A") && obj.get("checked").equals(true)) {
				map.put("prpCitemKindsTemp[0].amount", map.get("prpCitemCar.actualValue"));
				map.put("prpCitemKindsTemp[0].chooseFlag", "on");

				if (obj.get("bujmP") != null && obj.get("bujmP").equals(true)) {
					map.put("prpCitemKindsTemp[0].specialFlag", "on");
					map.put("prpCitemKindsTemp[11].chooseFlag", "on");
				}

				isEmpty = true;
				continue;
			}

			if (obj.getString("xianZ") != null && obj.getString("xianZ").equals("G") && obj.get("checked").equals(true)) {
				map.put("prpCitemKindsTemp[1].amount", map.get("prpCitemCar.actualValue"));
				map.put("prpCitemKindsTemp[1].chooseFlag", "on");

				if (obj.get("bujmP") != null && obj.get("bujmP").equals(true)) {
					map.put("prpCitemKindsTemp[1].specialFlag", "on");
					map.put("prpCitemKindsTemp[13].chooseFlag", "on");
				}

				isEmpty = true;
				continue;
			}

			if (obj.getString("xianZ") != null && obj.getString("xianZ").equals("B") && obj.get("checked").equals(true)) {
				map.put("prpCitemKindsTemp[2].amount", obj.getString("baoE") == null ? "" : obj.getString("baoE"));
				map.put("prpCitemKindsTemp[2].chooseFlag", "on");

				if (obj.get("bujmP") != null && obj.get("bujmP").equals(true)) {
					map.put("prpCitemKindsTemp[2].specialFlag", "on");
					map.put("prpCitemKindsTemp[12].chooseFlag", "on");
				}

				isEmpty = true;
				continue;
			}

			if (obj.getString("xianZ") != null && obj.getString("xianZ").equals("D11") && obj.get("checked").equals(true)) {
				map.put("prpCitemKindsTemp[3].amount", obj.getString("baoE") == null ? "" : obj.getString("baoE"));
				map.put("prpCitemKindsTemp[3].chooseFlag", "on");

				if (obj.get("bujmP") != null && obj.get("bujmP").equals(true)) {
					map.put("prpCitemKindsTemp[3].specialFlag", "on");
					map.put("prpCitemKindsTemp[14].chooseFlag", "on");
				}

				isEmpty = true;
				continue;
			}

			if (obj.getString("xianZ") != null && obj.getString("xianZ").equals("D12") && obj.get("checked").equals(true)) {
				map.put("prpCitemKindsTemp[4].amount", obj.getString("baoE") == null ? "" : obj.getString("baoE"));
				map.put("prpCitemKindsTemp[4].unitAmount", "10000");
				int seatCount = Integer.parseInt(map.get("prpCitemCar.seatCount") == null ? "5" : map.get("prpCitemCar.seatCount") + "");
				map.put("prpCitemKindsTemp[4].quantity", --seatCount + "");
				map.put("prpCitemKindsTemp[4].chooseFlag", "on");

				if (obj.get("bujmP") != null && obj.get("bujmP").equals(true)) {
					map.put("prpCitemKindsTemp[4].specialFlag", "on");
					map.put("prpCitemKindsTemp[15].chooseFlag", "on");
				}

				isEmpty = true;
				continue;
			}

			if (obj.getString("xianZ") != null && obj.getString("xianZ").equals("L") && obj.get("checked").equals(true)) {
				map.put("prpCitemKindsTemp[5].amount", obj.getString("baoE") == null ? "" : obj.getString("baoE"));
				map.put("prpCitemKindsTemp[5].chooseFlag", "on");

				if (obj.get("bujmP") != null && obj.get("bujmP").equals(true)) {
					map.put("prpCitemKindsTemp[5].specialFlag", "on");
					map.put("prpCitemKindsTemp[18].chooseFlag", "on");
				}

				isEmpty = true;
				continue;
			}

			if (obj.getString("xianZ") != null && obj.getString("xianZ").equals("F") && obj.get("checked").equals(true)) {
				map.put("prpCitemKindsTemp[6].chooseFlag", "on");
				map.put("prpCitemKindsTemp[6].amount", "0");
				map.put("prpCitemKindsTemp[6].modeCode", obj.getString("baoE") == null ? "" : obj.getString("baoE")); // 10 国产; 20 进口

				isEmpty = true;
				continue;
			}

			// 指定修理厂险
			if (obj.getString("xianZ") != null && obj.getString("xianZ").equals("?")) {
				map.put("prpCitemKindsTemp[7].amount", obj.getString("baoE") == null ? "" : obj.getString("baoE"));
				map.put("prpCitemKindsTemp[7].chooseFlag", "on");

				isEmpty = true;
				continue;
			}

			if (obj.getString("xianZ") != null && obj.getString("xianZ").equals("Z") && obj.get("checked").equals(true)) {
				map.put("prpCitemKindsTemp[8].amount", map.get("prpCitemCar.actualValue"));
				map.put("prpCitemKindsTemp[8].chooseFlag", "on");

				if (obj.get("bujmP") != null && obj.get("bujmP").equals(true)) {
					map.put("prpCitemKindsTemp[8].specialFlag", "on");
					map.put("prpCitemKindsTemp[16].chooseFlag", "on");
				}

				isEmpty = true;
				continue;
			}

			if (obj.getString("xianZ") != null && obj.getString("xianZ").equals("X1") && obj.get("checked").equals(true)) {
				map.put("prpCitemKindsTemp[9].chooseFlag", "on");

				if (obj.get("bujmP") != null && obj.get("bujmP").equals(true)) {
					map.put("prpCitemKindsTemp[9].specialFlag", "on");
					map.put("prpCitemKindsTemp[19].chooseFlag", "on");
				}

				isEmpty = true;
				continue;
			}
		}

		return isEmpty;
	}
	
	String round(String num) {
        BigDecimal big  = new BigDecimal(num);  
        BigDecimal result = big.divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP);  
        return result.toString();  
    }
	
	// 用uuid标识保存交强或商业的算费结果或or交强和商业一起的结果
	Map<String, Object> suanfResultMap = new HashMap<String, Object>();
	
	String jisSYX(String xianZDetail, Map<String, String> baseDataMap) {
		String rtnData = "";

		baseDataMap.put("isBICI", "10");
		
		if (setXianZData(xianZDetail, baseDataMap))
			rtnData = httpClientUtil.doPost("http://10.134.136.48:8000/prpall/business/caculatePremiunForFG.do", baseDataMap, "gbk");
		System.out.println("商业险返回: " + rtnData);
		
		if(rtnData.contains("NullPointerException"))
			return "{\"success\": flase, \"msg\": \"计算商业险失败\"}";
		
		JSONObject jsonObj = JSONObject.fromObject(rtnData);
//		System.out.println("jsonObj1: " + jsonObj);
		
		if(jsonObj.get("msg") != null && !jsonObj.get("msg").equals("")) {
			return "{\"success\":false, \"msg\":\"" + jsonObj.get("msg") + "\"}";
		}
		
		JSONArray jsonObj2 = jsonObj.getJSONArray("data");
//		System.out.println("jsonObj2: " + jsonObj2);
		
		JSONObject jsonObj3 = jsonObj2.getJSONObject(0);
//		System.out.println("jsonObj3: " + jsonObj3);
		
		JSONArray jsonObj4 = jsonObj3.getJSONArray("biInsuredemandVoList");
//		System.out.println("jsonObj4: " + jsonObj4);
		
		JSONObject jsonObj5 = jsonObj4.getJSONObject(0);
//		System.out.println("jsonObj5: " + jsonObj5);
		
		JSONObject ciInsureDemandDAA = jsonObj5.getJSONObject("ciInsureDemandDAA");
		String remark = ciInsureDemandDAA.getString("remark");
		if(remark != null && !remark.equals(""))
			return "{\"success\":false, \"msg\":\"" + remark + "\"}";
		
		Map<String, Object> outMap = new HashMap<String, Object>();
		outMap.put("saveHebId", baseDataMap.get("uuid"));
		outMap.put("success", false);
		
		String uuid = UUID.randomUUID().toString();
		outMap.put("saveHebId2", uuid);
		suanfResultMap.put(uuid, rtnData);
		 
		JSONArray array = jsonObj5.getJSONArray("prpCitemKinds");
		
		String kindCode = "";
		String benchMarkPremium = "";
		String amount = "";
		String disCount = "";
		String premium = "";
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		
		// 从json中挑出各个险种的保费
		for (Object obj : array) {
			jsonObj = JSONObject.fromObject(obj);
			if(jsonObj != null) {
				if(jsonObj.get("kindCode") != null) {
					kindCode = jsonObj.get("kindCode").toString();
					System.out.print(kindCode + "\t");
				}
				
				if(jsonObj.get("kindName") != null)
					System.out.print(jsonObj.get("kindName") + "\t");
				
				if(jsonObj.get("benchMarkPremium") != null) {
					benchMarkPremium = jsonObj.get("benchMarkPremium").toString();
					System.out.print(benchMarkPremium + "\t");
				}
				
				if(jsonObj.get("amount") != null) {
					amount = jsonObj.get("amount").toString();
					System.out.print(amount + "\t");
				}
				
				if(jsonObj.get("disCount") != null) {
					disCount = jsonObj.get("disCount").toString();
					System.out.print(disCount + "\t");
				}
				
				if(jsonObj.get("premium") != null) {
					premium = jsonObj.get("premium").toString();
					System.out.print(premium + "\t");
				}
				
				System.out.println();
				Map<String, Object> mapShangY = new HashMap<String, Object>();
				
				if(kindCode.equals("050202")) {
					mapShangY.put("A", "checked");
					mapShangY.put("benchMarkPremium", round(benchMarkPremium));
					mapShangY.put("disCount", disCount);
					mapShangY.put("premium", round(premium));
					list.add(mapShangY);
					continue;
				}
				if(kindCode.equals("050930")) {
					mapShangY.put("A_bujmP", "checked");
					mapShangY.put("benchMarkPremium", round(benchMarkPremium));
					mapShangY.put("disCount", disCount);
					mapShangY.put("premium", round(premium));
					list.add(mapShangY);
					continue;
				}
				
				
				if(kindCode.equals("050501")) {
					mapShangY.put("G", "checked");
					mapShangY.put("benchMarkPremium", round(benchMarkPremium));
					mapShangY.put("disCount", disCount);
					mapShangY.put("premium", round(premium));
					list.add(mapShangY);
					continue;
				}
				if(kindCode.equals("050932")) {
					mapShangY.put("G_bujmP", "checked");
					mapShangY.put("benchMarkPremium", round(benchMarkPremium));
					mapShangY.put("disCount", disCount);
					mapShangY.put("premium", round(premium));
					list.add(mapShangY);
				}
				
				if(kindCode.equals("050602")) {
					mapShangY.put("B", "checked");
					mapShangY.put("amount", amount);
					mapShangY.put("benchMarkPremium", round(benchMarkPremium));
					mapShangY.put("disCount", disCount);
					mapShangY.put("premium", round(premium));
					list.add(mapShangY);
					continue;
				}
				if(kindCode.equals("050931")) {
					mapShangY.put("B_bujmP", "checked");
					mapShangY.put("benchMarkPremium", round(benchMarkPremium));
					mapShangY.put("disCount", disCount);
					mapShangY.put("premium", round(premium));
					list.add(mapShangY);
				}
				
				if(kindCode.equals("050711")) {
					mapShangY.put("D11", "checked");
					mapShangY.put("amount", amount);
					mapShangY.put("benchMarkPremium",  round(benchMarkPremium));
					mapShangY.put("disCount",  disCount);
					mapShangY.put("premium",  round(premium));
					list.add(mapShangY);
					continue;
				}
				if(kindCode.equals("050933")) {
					mapShangY.put("D11_bujmP", "checked");
					mapShangY.put("benchMarkPremium", round(benchMarkPremium));
					mapShangY.put("disCount", disCount);
					mapShangY.put("premium", round(premium));
					list.add(mapShangY);
				}
				
				if(kindCode.equals("050712")) {
					mapShangY.put("D12", "checked");
					mapShangY.put("amount", amount);
					mapShangY.put("benchMarkPremium",  round(benchMarkPremium));
					mapShangY.put("disCount",  disCount);
					mapShangY.put("premium",  round(premium));
					list.add(mapShangY);
					continue;
				}
				if(kindCode.equals("050934")) {
					mapShangY.put("D12_bujmP", "checked");
					mapShangY.put("benchMarkPremium", round(benchMarkPremium));
					mapShangY.put("disCount", disCount);
					mapShangY.put("premium", round(premium));
					list.add(mapShangY);
				}
				
				if(kindCode.equals("050211")) {
					mapShangY.put("L", "checked");
					mapShangY.put("amount", amount);
					mapShangY.put("benchMarkPremium",  round(benchMarkPremium));
					mapShangY.put("disCount",  disCount);
					mapShangY.put("premium",  round(premium));
					list.add(mapShangY);
					continue;
				}
				if(kindCode.equals("050937")) {
					mapShangY.put("L_bujmP", "checked");
					mapShangY.put("benchMarkPremium", round(benchMarkPremium));
					mapShangY.put("disCount", disCount);
					mapShangY.put("premium", round(premium));
					list.add(mapShangY);
				}
				
				if(kindCode.equals("050232")) {
					mapShangY.put("F", "checked");
					mapShangY.put("amount", amount);
					mapShangY.put("benchMarkPremium",  round(benchMarkPremium));
					mapShangY.put("disCount",  disCount);
					mapShangY.put("premium",  round(premium));
					list.add(mapShangY);
					continue;
				}
				
				if(kindCode.equals("050311")) {
					mapShangY.put("Z", "checked");
					mapShangY.put("amount", "");
					mapShangY.put("benchMarkPremium",  round(benchMarkPremium));
					mapShangY.put("disCount",  disCount);
					mapShangY.put("premium",  round(premium));
					list.add(mapShangY);
					continue;
				}
				
				if(kindCode.equals("050461")) {
					mapShangY.put("X1", "checked");
					mapShangY.put("amount", "");
					mapShangY.put("benchMarkPremium",  round(benchMarkPremium));
					mapShangY.put("disCount",  disCount);
					mapShangY.put("premium",  round(premium));
					list.add(mapShangY);
					continue;
				}
				if(kindCode.equals("050938")) {
					mapShangY.put("X1_bujmP", "checked");
					mapShangY.put("benchMarkPremium", round(benchMarkPremium));
					mapShangY.put("disCount", disCount);
					mapShangY.put("premium", round(premium));
					list.add(mapShangY);
				}
			}
		}
		
		outMap.put("success", true);
		outMap.put("SYX", list);
		
		JSONObject jsonObject = JSONObject.fromObject(outMap);

		return jsonObject.toString();
	}
	
	String jisJQXAndSYX(String xianZDetail, Map<String, String> baseDataMap) {
		String rtnData = "";

		baseDataMap.put("isBICI", "11");
		
		baseDataMap.put("prpCmainCI.sumAmount","122000"); 
		baseDataMap.put("prpCitemKindCI.familyNo","1");//null
		baseDataMap.put("prpCitemKindCI.amount","122000");//0
		baseDataMap.put("prpCitemKindCI.adjustRate","0.9");//1
		
		// A
		baseDataMap.put("prpCitemKindsTemp[0].chooseFlag", "");
		baseDataMap.put("prpCitemKindsTemp[0].specialFlag", "");
		baseDataMap.put("prpCitemKindsTemp[11].chooseFlag", "");
		// G
		baseDataMap.put("prpCitemKindsTemp[1].chooseFlag", "");
		baseDataMap.put("prpCitemKindsTemp[1].specialFlag", "");
		baseDataMap.put("prpCitemKindsTemp[13].chooseFlag", "");
		// B
		baseDataMap.put("prpCitemKindsTemp[2].chooseFlag", "");
		baseDataMap.put("prpCitemKindsTemp[2].specialFlag", "");
		baseDataMap.put("prpCitemKindsTemp[12].chooseFlag", "");
		// D11
		baseDataMap.put("prpCitemKindsTemp[3].chooseFlag", "");
		baseDataMap.put("prpCitemKindsTemp[3].specialFlag", "");
		baseDataMap.put("prpCitemKindsTemp[14].chooseFlag", "");
		// D12
		baseDataMap.put("prpCitemKindsTemp[4].chooseFlag", "");
		baseDataMap.put("prpCitemKindsTemp[4].specialFlag", "");
		baseDataMap.put("prpCitemKindsTemp[15].chooseFlag", "");
		// L
		baseDataMap.put("prpCitemKindsTemp[5].chooseFlag", "");
		baseDataMap.put("prpCitemKindsTemp[5].specialFlag", "");
		baseDataMap.put("prpCitemKindsTemp[18].chooseFlag", "");
		// F
		baseDataMap.put("prpCitemKindsTemp[6].chooseFlag", "");
		baseDataMap.put("prpCitemKindsTemp[6].specialFlag", "");
		// Z
		baseDataMap.put("prpCitemKindsTemp[8].chooseFlag", "");
		baseDataMap.put("prpCitemKindsTemp[8].specialFlag", "");
		baseDataMap.put("prpCitemKindsTemp[16].chooseFlag", "");
		// X1
		baseDataMap.put("prpCitemKindsTemp[9].chooseFlag", "");
		baseDataMap.put("prpCitemKindsTemp[9].specialFlag", "");
		baseDataMap.put("prpCitemKindsTemp[19].chooseFlag", "");
		
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 1);
		String startDate = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		String prpCmainCI_startDate = baseDataMap.get("prpCmainCI.startDate");
		if (prpCmainCI_startDate == null || prpCmainCI_startDate.equals("")) {
			baseDataMap.put("prpCmainCI.startDate", startDate);
			prpCmainCI_startDate = startDate;
		}

		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(prpCmainCI_startDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.setTime(date);
		c.add(Calendar.YEAR, 1);
		c.add(Calendar.DATE, -1);
		String endDate = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		baseDataMap.put("prpCmainCI.endDate", endDate);
		
		System.out.println("计算交强");
		System.out.println("prpCmainCI.startDate: " + baseDataMap.get("prpCmainCI.startDate"));
		System.out.println("prpCmainCI.endDate: " + baseDataMap.get("prpCmainCI.endDate"));
		
		if (setXianZData(xianZDetail, baseDataMap))
			rtnData = httpClientUtil.doPost("http://10.134.136.48:8000/prpall/business/caculatePremiunForFG.do", baseDataMap, "gbk");
		
		System.out.println("交强和商业险: " + rtnData);
		
		if(rtnData.contains("NullPointerException"))
			return "{\"success\": flase, \"msg\": \"计算商业险失败\"}";
		
		Map<String, Object> outMap = new HashMap<String, Object>();
		outMap.put("saveHebId", baseDataMap.get("uuid"));
		outMap.put("success", false);
		
		String uuid = UUID.randomUUID().toString();
		outMap.put("saveHebId2", uuid);
		suanfResultMap.put(uuid, rtnData);
		
		JSONObject jsonObj = JSONObject.fromObject(rtnData);
//		System.out.println("jsonObj1: " + jsonObj);
		
		if(jsonObj.get("msg") != null && !jsonObj.get("msg").equals("")) {
			return "{\"success\":false, \"msg\":\"" + jsonObj.get("msg") + "\"}";
		}
		
		JSONArray jsonObj2 = jsonObj.getJSONArray("data");
//		System.out.println("jsonObj2: " + jsonObj2);
		
		JSONObject jsonObj3 = jsonObj2.getJSONObject(0);
//		System.out.println("jsonObj3: " + jsonObj3);
		
		// 解析交强费用
		JSONArray jObj3 = jsonObj3.getJSONArray("ciInsureVOList");
		JSONObject jObj4 = JSONObject.fromObject(jObj3.get(0));
		JSONObject jObj5 = jObj4.getJSONObject("ciInsureDemand");
		
		String jq_basePremium = "";
		String jq_premium = "";
		String carShipTax= "";
		String carOwner = "", brandCName = "";
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> mapJiaoQ = new HashMap<String, Object>();
		
		if(jObj5.get("carOwner") != null) {
			carOwner = jObj5.get("carOwner").toString();
			System.out.println("车主: " + carOwner);
			mapJiaoQ.put("carOwner", carOwner);
		}
		if(jObj5.get("brandCName") != null) {
			brandCName = jObj5.get("brandCName").toString();
			System.out.println("厂牌: " + brandCName);
			mapJiaoQ.put("brandCName", brandCName);
		}
		
		if(jObj5.get("basePremium") != null) {
			jq_basePremium = jObj5.get("basePremium").toString();
			System.out.println("基础保费: " + jq_basePremium);
			mapJiaoQ.put("jq_basePremium", jq_basePremium);
		}
		
		if(jObj5.get("premium") != null) {
			jq_premium = jObj5.get("premium").toString();
			System.out.println("应交保费: " + jq_premium);
			mapJiaoQ.put("jq_premium", jq_premium);
		}
		
		if(jObj5.get("taxTotal") != null) {
			carShipTax = jObj5.get("taxTotal").toString();
			System.out.println("车船税: " + carShipTax);
			mapJiaoQ.put("carShipTax", carShipTax);
		}
		
		outMap.put("JQX", mapJiaoQ);
		
		// 解析商业险
		JSONArray jsonObj4 = jsonObj3.getJSONArray("biInsuredemandVoList");
//		System.out.println("jsonObj4: " + jsonObj4);
		
		JSONObject jsonObj5 = jsonObj4.getJSONObject(0);
//		System.out.println("jsonObj5: " + jsonObj5);
		
		JSONObject ciInsureDemandDAA = jsonObj5.getJSONObject("ciInsureDemandDAA");
		String remark = ciInsureDemandDAA.getString("remark");
		if(remark != null && !remark.equals(""))
			return "{\"success\":false, \"msg\":\"" + remark + "\"}";
		
		 
		JSONArray array = jsonObj5.getJSONArray("prpCitemKinds");
		
		String kindCode = "";
		String benchMarkPremium = "";
		String amount = "";
		String disCount = "";
		String premium = "";
		
//		Map<String, Object> outMap = new HashMap<String, Object>();
		outMap.put("success", false);
//		
//		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		
		// 从json中挑出各个险种的保费
		for (Object obj : array) {
			jsonObj = JSONObject.fromObject(obj);
			if(jsonObj != null) {
				if(jsonObj.get("kindCode") != null) {
					kindCode = jsonObj.get("kindCode").toString();
					System.out.print(kindCode + "\t");
				}
				
				if(jsonObj.get("kindName") != null)
					System.out.print(jsonObj.get("kindName") + "\t");
				
				if(jsonObj.get("benchMarkPremium") != null) {
					benchMarkPremium = jsonObj.get("benchMarkPremium").toString();
					System.out.print(benchMarkPremium + "\t");
				}
				
				if(jsonObj.get("amount") != null) {
					amount = jsonObj.get("amount").toString();
					System.out.print(amount + "\t");
				}
				
				if(jsonObj.get("disCount") != null) {
					disCount = jsonObj.get("disCount").toString();
					System.out.print(disCount + "\t");
				}
				
				if(jsonObj.get("premium") != null) {
					premium = jsonObj.get("premium").toString();
					System.out.print(premium + "\t");
				}
				
				System.out.println();
				Map<String, Object> mapShangY = new HashMap<String, Object>();
				
				if(kindCode.equals("050202")) {
					mapShangY.put("A", "checked");
					mapShangY.put("benchMarkPremium", round(benchMarkPremium));
					mapShangY.put("disCount", disCount);
					mapShangY.put("premium", round(premium));
					list.add(mapShangY);
					continue;
				}
				if(kindCode.equals("050930")) {
					mapShangY.put("A_bujmP", "checked");
					mapShangY.put("benchMarkPremium", round(benchMarkPremium));
					mapShangY.put("disCount", disCount);
					mapShangY.put("premium", round(premium));
					list.add(mapShangY);
					continue;
				}
				
				
				if(kindCode.equals("050501")) {
					mapShangY.put("G", "checked");
					mapShangY.put("benchMarkPremium", round(benchMarkPremium));
					mapShangY.put("disCount", disCount);
					mapShangY.put("premium", round(premium));
					list.add(mapShangY);
					continue;
				}
				if(kindCode.equals("050932")) {
					mapShangY.put("G_bujmP", "checked");
					mapShangY.put("benchMarkPremium", round(benchMarkPremium));
					mapShangY.put("disCount", disCount);
					mapShangY.put("premium", round(premium));
					list.add(mapShangY);
				}
				
				if(kindCode.equals("050602")) {
					mapShangY.put("B", "checked");
					mapShangY.put("amount", amount);
					mapShangY.put("benchMarkPremium", round(benchMarkPremium));
					mapShangY.put("disCount", disCount);
					mapShangY.put("premium", round(premium));
					list.add(mapShangY);
					continue;
				}
				if(kindCode.equals("050931")) {
					mapShangY.put("B_bujmP", "checked");
					mapShangY.put("benchMarkPremium", round(benchMarkPremium));
					mapShangY.put("disCount", disCount);
					mapShangY.put("premium", round(premium));
					list.add(mapShangY);
				}
				
				if(kindCode.equals("050711")) {
					mapShangY.put("D11", "checked");
					mapShangY.put("amount", amount);
					mapShangY.put("benchMarkPremium",  round(benchMarkPremium));
					mapShangY.put("disCount",  disCount);
					mapShangY.put("premium",  round(premium));
					list.add(mapShangY);
					continue;
				}
				if(kindCode.equals("050933")) {
					mapShangY.put("D11_bujmP", "checked");
					mapShangY.put("benchMarkPremium", round(benchMarkPremium));
					mapShangY.put("disCount", disCount);
					mapShangY.put("premium", round(premium));
					list.add(mapShangY);
				}
				
				if(kindCode.equals("050712")) {
					mapShangY.put("D12", "checked");
					mapShangY.put("amount", amount);
					mapShangY.put("benchMarkPremium",  round(benchMarkPremium));
					mapShangY.put("disCount",  disCount);
					mapShangY.put("premium",  round(premium));
					list.add(mapShangY);
					continue;
				}
				if(kindCode.equals("050934")) {
					mapShangY.put("D12_bujmP", "checked");
					mapShangY.put("benchMarkPremium", round(benchMarkPremium));
					mapShangY.put("disCount", disCount);
					mapShangY.put("premium", round(premium));
					list.add(mapShangY);
				}
				
				if(kindCode.equals("050211")) {
					mapShangY.put("L", "checked");
					mapShangY.put("amount", amount);
					mapShangY.put("benchMarkPremium",  round(benchMarkPremium));
					mapShangY.put("disCount",  disCount);
					mapShangY.put("premium",  round(premium));
					list.add(mapShangY);
					continue;
				}
				if(kindCode.equals("050937")) {
					mapShangY.put("L_bujmP", "checked");
					mapShangY.put("benchMarkPremium", round(benchMarkPremium));
					mapShangY.put("disCount", disCount);
					mapShangY.put("premium", round(premium));
					list.add(mapShangY);
				}
				
				if(kindCode.equals("050232")) {
					mapShangY.put("F", "checked");
					mapShangY.put("amount", amount);
					mapShangY.put("benchMarkPremium",  round(benchMarkPremium));
					mapShangY.put("disCount",  disCount);
					mapShangY.put("premium",  round(premium));
					list.add(mapShangY);
					continue;
				}
				
				if(kindCode.equals("050311")) {
					mapShangY.put("Z", "checked");
					mapShangY.put("amount", "");
					mapShangY.put("benchMarkPremium",  round(benchMarkPremium));
					mapShangY.put("disCount",  disCount);
					mapShangY.put("premium",  round(premium));
					list.add(mapShangY);
					continue;
				}
				
				if(kindCode.equals("050461")) {
					mapShangY.put("X1", "checked");
					mapShangY.put("amount", "");
					mapShangY.put("benchMarkPremium",  round(benchMarkPremium));
					mapShangY.put("disCount",  disCount);
					mapShangY.put("premium",  round(premium));
					list.add(mapShangY);
					continue;
				}
				if(kindCode.equals("050938")) {
					mapShangY.put("X1_bujmP", "checked");
					mapShangY.put("benchMarkPremium", round(benchMarkPremium));
					mapShangY.put("disCount", disCount);
					mapShangY.put("premium", round(premium));
					list.add(mapShangY);
				}
			}
		}
		
		outMap.put("success", true);
		outMap.put("SYX", list);
		
		JSONObject jsonObject = JSONObject.fromObject(outMap);

		return jsonObject.toString();
	}

	public String suanF(String in, Map<String, String> map) {
		// TODO Auto-generated method stub
		String out = "";
		JSONObject jsonObject = JSONObject.fromObject(in);
//		JSONArray jsonArray = JSONArray.fromObject(jsonObject.getString("carBaseInfo"));
		
		//map2map(templateData, map);
//		if(!setCarBaseInfo2map(jsonArray, map))
//			return "{\"success\": false, \"msg\": \"算费设置车辆基本信息失败\"}";
		
		int jiaoqOrShangy = jsonObject.getInt("jiaoqOrShangy");
		String jiaoqStartDate = jsonObject.getString("jiaoqStartDate");
		if(jiaoqStartDate != null && !jiaoqStartDate.equals(""))
			map.put("prpCmainCI.startDate", jiaoqStartDate);
		
		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 1);
		String todayAdd1 = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		
		map.put("biStartDate", todayAdd1);
		map.put("ciStartDate", todayAdd1);
		map.put("prpCmain.startDate", todayAdd1);
		
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(todayAdd1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.setTime(date);
		c.add(Calendar.YEAR, 1);
		c.add(Calendar.DATE, -1);
		String endDate = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		
		map.put("prpCmain.endDate", endDate);
		
		map.put("currentDate", today);
		map.put("prpCmain.operateDate", today);
		map.put("Today", today);
		map.put("operateDateShow", today);
		
		map.put("isBICI", "");
		map.put("prpCmainCI.sumAmount",""); 
		map.put("prpCitemKindCI.familyNo","");//null
		map.put("prpCitemKindCI.amount","");//0
		map.put("prpCitemKindCI.adjustRate","1");//1
		
		// A
		map.put("prpCitemKindsTemp[0].chooseFlag", "");
		map.put("prpCitemKindsTemp[0].specialFlag", "");
		map.put("prpCitemKindsTemp[11].chooseFlag", "");
		// G
		map.put("prpCitemKindsTemp[1].chooseFlag", "");
		map.put("prpCitemKindsTemp[1].specialFlag", "");
		map.put("prpCitemKindsTemp[13].chooseFlag", "");
		// B
		map.put("prpCitemKindsTemp[2].chooseFlag", "");
		map.put("prpCitemKindsTemp[2].specialFlag", "");
		map.put("prpCitemKindsTemp[12].chooseFlag", "");
		// D11
		map.put("prpCitemKindsTemp[3].chooseFlag", "");
		map.put("prpCitemKindsTemp[3].specialFlag", "");
		map.put("prpCitemKindsTemp[14].chooseFlag", "");
		// D12
		map.put("prpCitemKindsTemp[4].chooseFlag", "");
		map.put("prpCitemKindsTemp[4].specialFlag", "");
		map.put("prpCitemKindsTemp[15].chooseFlag", "");
		// L
		map.put("prpCitemKindsTemp[5].chooseFlag", "");
		map.put("prpCitemKindsTemp[5].specialFlag", "");
		map.put("prpCitemKindsTemp[18].chooseFlag", "");
		// F
		map.put("prpCitemKindsTemp[6].chooseFlag", "");
		map.put("prpCitemKindsTemp[6].specialFlag", "");
		// Z
		map.put("prpCitemKindsTemp[8].chooseFlag", "");
		map.put("prpCitemKindsTemp[8].specialFlag", "");
		map.put("prpCitemKindsTemp[16].chooseFlag", "");
		// X1
		map.put("prpCitemKindsTemp[9].chooseFlag", "");
		map.put("prpCitemKindsTemp[9].specialFlag", "");
		map.put("prpCitemKindsTemp[19].chooseFlag", "");
		
		map.put("jiaoqOrShangy", jiaoqOrShangy + "");
		
		// 交强
		if (jiaoqOrShangy == 1)
			out = jisJQX(map);
		
		// 商业
		if (jiaoqOrShangy == 0)
			out = jisSYX(jsonObject.getString("xianZDetail"), map);
		
		// 交强和商业
		if (jiaoqOrShangy == 2)
			out = jisJQXAndSYX(jsonObject.getString("xianZDetail"), map);
		
		JSONObject jsonObject4HebId = JSONObject.fromObject(out);
		String saveHebId = "";
		if(jsonObject4HebId.get("saveHebId2") != null)
			saveHebId = jsonObject4HebId.getString("saveHebId2");
		else
			return out;
		
		// 0商业, 1交强, 2交强商业
		String bici = jiaoqOrShangy + "";
		String suanfResult = suanfResultMap.get(saveHebId) + "";
//		if(suanfResult.equals(""))
//			return "\"success\": false, \"msg\":\"查询核保id失败\"";
		
		
        Map sfMap = JackJson.fromJsonToObject(suanfResult, Map.class);
		double permium = 0l;
		
		if(bici.equals("1") || bici.equals("2")) {
			if(sfMap == null)
	        	return "\"success\": false, \"msg\":\"查询交强核保数据失败\"";
			
			if(((List)((Map)((List)sfMap.get("data")).get(0)).get("ciInsureVOList")).size() < 1)
	        	return "\"success\": false, \"msg\":\"查询交强核保数据失败\"";
			
            //交强保费
            Map ciVo = (Map)((List)((Map)((List)sfMap.get("data")).get(0)).get("ciInsureVOList")).get(0); 
            
            Map ciInsureDemand = (Map)ciVo.get("ciInsureDemand");
            
            Map ciKind = (Map)((List)ciVo.get("prpCitemKinds")).get(0);
            map.put("prpCitemKindCI.adjustRate",ciKind.get("adjustRate").toString());
            map.put("prpCitemKindCI.benchMarkPremium",ciInsureDemand.get("basePremium").toString());
    		map.put("sumPayTax1",ciInsureDemand.get("taxTotal").toString());//
    		
            map.put("prpCitemKindCI.premium",ciInsureDemand.get("premium").toString());
            map.put("prpCitemKindCI.netPremium",ciKind.get("netPremium").toString());
            map.put("prpCitemKindCI.taxPremium",ciKind.get("taxPremium").toString());
            map.put("prpCitemKindCI.taxRate",ciKind.get("taxRate").toString());
            map.put("prpCitemKindCI.shortRate",ciKind.get("shortRate").toString());
            map.put("prpCmainCI.sumPremium", map.get("prpCitemKindCI.premium"));
            map.put("prpCmainCI.sumDiscount","" + (Double.parseDouble(map.get("prpCitemKindCI.benchMarkPremium"))-Double.parseDouble(map.get("prpCitemKindCI.premium"))));//
            
            Map prpCi = (Map)((List)ciVo.get("prpCfixations")).get(0);
            
            map.put("prpCfixationCITemp.discount",prpCi.get("discount").toString());
            map.put("prpCfixationCITemp.id.riskCode",((Map)prpCi.get("id")).get("riskCode").toString());                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
            map.put("prpCfixationCITemp.profits",prpCi.get("profits").toString());
            map.put("prpCfixationCITemp.cost",prpCi.get("cost").toString());
    		map.put("prpCfixationCITemp.taxorAppend",prpCi.get("taxorAppend").toString());
    		map.put("prpCfixationCITemp.payMentR",prpCi.get("payMentR").toString());
    		map.put("prpCfixationCITemp.basePayMentR",prpCi.get("basePayMentR").toString());
    		map.put("prpCfixationCITemp.poundAge",prpCi.get("poundAge").toString());
    		map.put("prpCfixationCITemp.basePremium",prpCi.get("basePremium").toString());
    		map.put("prpCfixationCITemp.riskPremium",prpCi.get("riskPremium").toString());
    		map.put("prpCfixationCITemp.riskSumPremium","0");
    		map.put("prpCfixationCITemp.signPremium",prpCi.get("signPremium").toString());
    		map.put("prpCfixationCITemp.isQuotation",prpCi.get("isQuotation").toString());
    		map.put("prpCfixationCITemp.riskClass",prpCi.get("riskClass").toString());
    		map.put("prpCfixationCITemp.operationInfo",prpCi.get("operationInfo").toString());
    		map.put("prpCfixationCITemp.realDisCount",prpCi.get("realDisCount").toString());
    		map.put("prpCfixationCITemp.realProfits",prpCi.get("realProfits").toString());
    		map.put("prpCfixationCITemp.realPayMentR",prpCi.get("realPayMentR").toString());
    		map.put("prpCfixationCITemp.profitClass",prpCi.get("profitClass").toString());
    		map.put("prpCfixationCITemp.costRate",prpCi.get("costRate").toString());
            
            Map taxMap = (Map)ciVo.get("ciCarShipTax");
    		map.put("ciInsureDemand.demandNo", taxMap.get("demandNo").toString());
    		map.put("prpCcarShipTax.sumPayTax",taxMap.get("thisPayTax").toString());
			map.put("prpCcarShipTax.taxType",taxMap.get("taxFlag").toString());
			map.put("prpCcarShipTax.carLotEquQuality",taxMap.get("poWeight").toString());
			map.put("prpCcarShipTax.calculateMode","C1");
			map.put("prpCcarShipTax.carKindCode","A01");
			map.put("prpCcarShipTax.model","B11");
			map.put("prpCcarShipTax.id.itemNo","1");
			map.put("prpCcarShipTax.taxPayerNature","3");
			map.put("prpCcarShipTax.taxPayerName","");
			map.put("prpCcarShipTax.taxAbateType",taxMap.get("taxFlag").toString());
			map.put("prpCcarShipTax.payStartDate",timeStamp2Date(((Map)taxMap.get("payStartDate")).get("time").toString(),"yyyy-M-d"));
			map.put("prpCcarShipTax.payEndDate",timeStamp2Date(((Map)taxMap.get("payEndDate")).get("time").toString(),"yyyy-M-d"));
			map.put("prpCcarShipTax.thisPayTax",taxMap.get("thisPayTax").toString());
			map.put("prpCcarShipTax.prePayTax",taxMap.get("prePayTax").toString());
        }
		
        if(bici.equals("0") || bici.equals("2")){
    		//商业保费
        	if(sfMap.get("data") != null)
        		if(((List)sfMap.get("data")).get(0) != null)
        			if((List)((Map)((List)sfMap.get("data")).get(0)).get("biInsuredemandVoList") != null)
        				if(((List)((Map)((List)sfMap.get("data")).get(0)).get("biInsuredemandVoList")).size() < 1)
        					return "\"success\": false, \"msg\":\"查询商业险核保数据失败\"";
        	
            Map biVo = (Map)((List)((Map)((List)sfMap.get("data")).get(0)).get("biInsuredemandVoList")).get(0);
            map.put("BIdemandNo", (String)((Map)biVo.get("ciInsureDemandDAA")).get("demandNo"));
            
            List cprofitList = (List)biVo.get("prpCprofitFactors");
            for(int i=0;i<cprofitList.size();i++){
            	Map fit = (Map)cprofitList.get(i);
            	map.put("prpCprofitFactorsTemp["+i+"].condition", fit.get("condition").toString());
            	map.put("prpCprofitFactorsTemp["+i+"].flag", fit.get("flag").toString());
            	map.put("prpCprofitFactorsTemp["+i+"].chooseFlag", "on");//fit.get("condition").toString());
            	map.put("prpCprofitFactorsTemp["+i+"].upperRate", fit.get("upperRate").toString());
            	map.put("prpCprofitFactorsTemp["+i+"].rate", fit.get("rate").toString());
            	map.put("prpCprofitFactorsTemp["+i+"].id.profitCode", ((Map)fit.get("id")).get("profitCode").toString());
            	map.put("prpCprofitFactorsTemp["+i+"].profitName", fit.get("profitName").toString());
            	map.put("prpCprofitFactorsTemp["+i+"].lowerRate", fit.get("lowerRate").toString());
            	map.put("prpCprofitFactorsTemp["+i+"].id.conditionCode", ((Map)fit.get("id")).get("conditionCode").toString());
            
            }
            
            List kindList = (List)biVo.get("prpCitemKinds");
            int index = 0;
            
            double pureRiskPremium = 0l;
            double netPremium = 0l;
            double taxPremium = 0l;
            double benchMarkPremium = 0l;
            double sumSp = 0l;
            double discount = 0l;
            
            for(int i=0;i<kindList.size();i++){
            	Map kind = (Map)kindList.get(i);
            	String kindCode = (String)kind.get("kindCode");
            	String idx = "0";
            	if("050202".equals(kindCode)){
            		idx = "0";
            	}else if("050930".equals(kindCode)){
            		idx = "11";
            	}else if("050501".equals(kindCode)){
            		idx = "1";
            	}else if("050932".equals(kindCode)){
            		idx = "13";
            	}else if("050602".equals(kindCode)){
            		idx = "2";
            	}else if("050931".equals(kindCode)){
            		idx = "12";
            	}else if("050711".equals(kindCode)){
            		idx = "3";
            	}else if("050933".equals(kindCode)){
            		idx = "14";
            	}else if("050712".equals(kindCode)){
            		idx = "4";
            	}else if("050934".equals(kindCode)){
            		idx = "15";
            	}else if("050211".equals(kindCode)){
            		idx = "5";
            	}else if("050937".equals(kindCode)){
            		idx = "18";
            	}else if("050232".equals(kindCode)) { //玻璃
            		idx = "6";
            	}else if("050311".equals(kindCode)){
            		idx = "8";
            	}else if("050935".equals(kindCode)){
            		idx = "16";
            	}else if("050461".equals(kindCode)){
            		idx = "9";
            	}else if("050938".equals(kindCode)){
            		idx = "19";
            	}else if("050253".equals(kindCode)){
            		idx="7";
            	}
            	
            	map.put("prpCitemKindsTemp["+idx+"].premium",kind.get("premium").toString());  //应交保费 
            	map.put("prpCitemKindsTemp["+idx+"].pureRiskPremium", kind.get("pureRiskPremium").toString());
            	map.put("prpCitemKindsTemp["+idx+"].netPremium", kind.get("netPremium").toString());
            	map.put("prpCitemKindsTemp["+idx+"].taxPremium", kind.get("taxPremium").toString());
            	map.put("prpCitemKindsTemp["+idx+"].benchMarkPremium",kind.get("benchMarkPremium").toString());
            	map.put("prpCitemKindsTemp["+idx+"].dutyFlag", kind.get("dutyFlag").toString());
            	map.put("prpCitemKindsTemp["+idx+"].disCount", kind.get("disCount").toString());
            	map.put("prpCitemKindsTemp["+idx+"].taxRate", kind.get("taxRate").toString());
            	map.put("prpCitemKindsTemp["+idx+"].rate", kind.get("rate").toString());
            	map.put("prpCitemKindsTemp["+idx+"].riskPremium", kind.get("riskPremium").toString());
            	map.put("prpCitemKindsTemp["+idx+"].basePremium", kind.get("basePremium").toString());
	            
	            permium += Double.parseDouble(map.get("prpCitemKindsTemp["+idx+"].premium"));
	            pureRiskPremium  += Double.parseDouble(map.get("prpCitemKindsTemp["+idx+"].pureRiskPremium"));
	            netPremium  += Double.parseDouble(map.get("prpCitemKindsTemp["+idx+"].netPremium"));
	            taxPremium  += Double.parseDouble(map.get("prpCitemKindsTemp["+idx+"].taxPremium"));
	            benchMarkPremium  += Double.parseDouble(map.get("prpCitemKindsTemp["+idx+"].benchMarkPremium"));
	            if(i == 0){
	            	discount =  Double.parseDouble(kind.get("disCount").toString());
	            }
	            if(kind.get("calculateFlag").toString().equals("N")){
	            	sumSp += Double.parseDouble(map.get("prpCitemKindsTemp["+idx+"].premium"));
	            }
	            List prpCprofits = (List) kind.get("prpCprofits");
	            for(int j=0;j<prpCprofits.size();j++){
	            	Map tmp = (Map) prpCprofits.get(j);
	            	List prpCprofitDetails = (List) tmp.get("prpCprofitDetails");
	            	for(int m=0;m<prpCprofitDetails.size();m++){
	            		Map detail = (Map) prpCprofitDetails.get(m);
	            		map.put("prpCprofitDetailsTemp["+index+"].profitRateMin",detail.get("profitRateMin").toString());
        	            map.put("prpCprofitDetailsTemp["+index+"].profitName",detail.get("profitName").toString());
        	            map.put("prpCprofitDetailsTemp["+index+"].id.profitType",((Map)detail.get("id")).get("profitType").toString());
        	            map.put("prpCprofitDetailsTemp["+index+"].profitRateMax",detail.get("profitRateMax").toString());
        	            map.put("prpCprofitDetailsTemp["+index+"].kindCode",detail.get("kindCode").toString());
        	            map.put("prpCprofitDetailsTemp["+index+"].condition", detail.get("condition").toString());
        	            map.put("prpCprofitDetailsTemp["+index+"].flag", detail.get("flag").toString());
        	            map.put("prpCprofitDetailsTemp["+index+"].conditionCode", detail.get("conditionCode").toString());
        	            map.put("prpCprofitDetailsTemp["+index+"].profitRate", detail.get("profitRate").toString());
        	            map.put("prpCprofitDetailsTemp["+index+"].id.serialNo", ((Map)detail.get("id")).get("serialNo").toString());
        	            map.put("prpCprofitDetailsTemp["+index+"].id.proposalNo", ((Map)detail.get("id")).get("proposalNo").toString());
        	            map.put("prpCprofitDetailsTemp["+index+"].id.itemKindNo", ((Map)detail.get("id")).get("itemKindNo").toString());
        	            map.put("prpCprofitDetailsTemp["+index+"].id.profitCode", ((Map)detail.get("id")).get("profitCode").toString());
        	            map.put("prpCprofitDetailsTemp["+index+"].chooseFlag","on");// detail.get("chooseFlag").toString());
        	            
        	            map.put("profitRateTemp["+index+"]", "" + (0.01*Double.parseDouble(detail.get("profitRate").toString())));
        	            map.put("profitRateTemp_["+index+"]", "" + (0.01*Double.parseDouble(detail.get("profitRate").toString())));
        	            index++;
	            	}
	            }
            }
            String sumSpStr = String.format("%.2f",sumSp);
            String permiumStr = String.format("%.2f", permium);
            String benchMarkPremiumStr = String.format("%.2f",benchMarkPremium);
            String netPremiumStr = String.format("%.2f", netPremium);
            String taxPremiumStr = String.format("%.2f",taxPremium);
            String discountStr = String.format("%.2f", discount);
        	map.put("sumItemKindSpecial",sumSpStr);//
//		    		map.put("prpCmain.sumPremiumTemp",(Double.parseDouble(formMap.get("prpCitemKindCI.premium")) + permium) + "");//0
    		map.put("prpCmain.sumDiscount", String.format("%.2f",(benchMarkPremium - permium)));//(Double.parseDouble(bzbf) - Double.parseDouble(zbf)));//
    		map.put("prpCmain.discount", discountStr);//1
    		map.put("sumBenchPremium","" + benchMarkPremiumStr);//
    		map.put("prpCmain.sumNetPremium","" + netPremiumStr);//
    		map.put("prpCmain.riskCode","DAA");//
    		map.put("prpCmain.sumTaxPremium","" + taxPremiumStr);//
            
            map.put("prpCmain.sumPremium", "" + permiumStr);
            map.put("prpCmain.sumPremium1", "" + permium);
            System.out.println(index);
            Map cfix = (Map)((List)biVo.get("prpCfixations")).get(0);
            map.put("prpCfixationTemp.signPremium", cfix.get("signPremium").toString());
            map.put("prpCfixationTemp.operationInfo", cfix.get("operationInfo").toString());
            map.put("prpCfixationTemp.riskPremium", cfix.get("riskPremium").toString());
            map.put("prpCfixationTemp.discount", cfix.get("discount").toString());
            map.put("prpCfixationTemp.poundAge", cfix.get("poundAge").toString());
            map.put("prpCfixationTemp.cost", cfix.get("cost").toString());
            map.put("prpCfixationTemp.costRate", cfix.get("costRate").toString());
            map.put("prpCfixationTemp.payMentR", cfix.get("payMentR").toString());
            map.put("prpCfixationTemp.basePayMentR", cfix.get("basePayMentR").toString());
            map.put("prpCfixationTemp.riskClass", cfix.get("riskClass").toString());
            map.put("prpCfixationTemp.profits", cfix.get("profits").toString());
            map.put("prpCfixationTemp.id.riskCode", ((Map)cfix.get("id")).get("riskCode").toString());
            
            List prpCsalesFixes = ((List)((Map)((List)sfMap.get("data")).get(0)).get("prpCsalesFixes"));
            for(int i=0;i<prpCsalesFixes.size();i++){
            	Map csale = (Map)prpCsalesFixes.get(i);
            	map.put("prpCsalesFixes["+i+"].businessNature", csale.get("businessNature").toString());
            	map.put("prpCsalesFixes["+i+"].version", csale.get("version").toString());
            	map.put("prpCsalesFixes["+i+"].comCode", csale.get("comCode").toString());
            	map.put("prpCsalesFixes["+i+"].riskCode", csale.get("riskCode").toString());
            	map.put("prpCsalesFixes["+i+"].isForMal", csale.get("isForMal").toString());
            	map.put("prpCsalesFixes["+i+"].id.serialNo", ((Map)csale.get("id")).get("serialNo").toString());
            	map.put("prpCsalesFixes["+i+"].id.proposalNo", ((Map)csale.get("id")).get("proposalNo").toString());
            }
        }
		
		
		
		return out;
	}

	private boolean setCarBaseInfo2map(JSONArray jsonArray, Map<String, String> map) {
		// TODO Auto-generated method stub
		if(jsonArray.size() < 1)
			return false;
		
		JSONObject jsonObject = JSONObject.fromObject(jsonArray.get(0));
		
		String prpCitemCar_licenseNo = jsonObject.getString("licenseNo");
		map.put("prpCitemCar.licenseNo", prpCitemCar_licenseNo);
		
		String prpCitemCar_licenseType = jsonObject.getString("licenseType");
		map.put("prpCitemCar.licenseType", prpCitemCar_licenseType);
		
		String prpCitemCar_frameNo = jsonObject.getString("frameNo");
		map.put("prpCitemCar.frameNo", prpCitemCar_frameNo);
		
		String prpCitemCar_vinNo = jsonObject.getString("vinNo");
		map.put("prpCitemCar.vinNo", prpCitemCar_vinNo);
		
		String prpCitemCar_engineNo = jsonObject.getString("engineNo");
		map.put("prpCitemCar.engineNo", prpCitemCar_engineNo);
		
		String prpCitemCar_enrollDate = jsonObject.getString("enrollDate");
		map.put("prpCitemCar.enrollDate", prpCitemCar_enrollDate);
		
		String prpCitemCar_modelCode = jsonObject.getString("modelCode");
		map.put("prpCitemCar.modelCode", prpCitemCar_modelCode);
		
//		String prpCitemCar_modelCode = jsonObject.getString("modelCode");
//		map.put("prpCitemCar.modelCodeAlias", "马自达牌CA7201AT4");
//		map.put("prpCitemCar.brandName", "马自达牌CA7201AT4");
		
		String prpCitemCar_actualValue = jsonObject.getString("actualValue");
		map.put("prpCitemCar.actualValue", prpCitemCar_actualValue);
		
		String prpCitemCar_seatCount = jsonObject.getString("seatCount");
		map.put("prpCitemCar.seatCount", prpCitemCar_seatCount);
		
		String prpCitemCar_exhaustScale = jsonObject.getString("exhaustScale");
		map.put("prpCitemCar.exhaustScale", prpCitemCar_exhaustScale);
		
		return true;
		
	}

	public String saveAndHeB(String in, Map<String, String> formMap) {
		// TODO Auto-generated method stub
		JSONObject jsonObject = JSONObject.fromObject(in);
		String hebXianZ = jsonObject.getString("hebXianZ");
//		String saveHebId = jsonObject.getString("saveHebId");
		JSONArray relation = jsonObject.getJSONArray("relation");
		
		String carOwner_customMobile = "";
		String carOwner_addressCName = "";
		String carOwner_customerCode = "";
		String carOwner_identifyNumber = "";
		String carOwner_customerCName = "";
		for (Object object : relation) {
			JSONObject jObj = (JSONObject)object;
//			if(jObj.getString("type").equals("cheZ")) {
				carOwner_customMobile = jObj.getString("customMobile");
				carOwner_addressCName = jObj.getString("addressCName");
				carOwner_customerCode = jObj.getString("customerCode");
				carOwner_identifyNumber = jObj.getString("identifyNumber");
				carOwner_customerCName = jObj.getString("customerCName");
				
				continue;
//			}
		}
		
		
		formMap.put("insuredCarOwner",carOwner_customerCName);
		
		formMap.put("prpCinsureds[0].sex","1");//0
    	formMap.put("prpCinsureds[0].age","49");//
    	formMap.put("prpCinsureds[0].insuredFlag","11100000000000000000000000000A");
    	formMap.put("prpCinsureds[0].auditStatus","2");//
    	formMap.put("prpCinsureds[0].versionNo","3");//
    	formMap.put("prpCinsureds[0].identifyNumber",carOwner_identifyNumber);//
    	formMap.put("prpCinsureds[0].insuredAddress",carOwner_addressCName);//
    	formMap.put("prpCinsureds[0].insuredCode",carOwner_customerCode);//
    	formMap.put("prpCinsureds[0].drivingLicenseNo",carOwner_identifyNumber);//
    	formMap.put("prpCinsureds[0].mobile",carOwner_customMobile);//
		formMap.put("prpCcarShipTax.taxPayerCode",carOwner_customerCode);//
		formMap.put("prpCcarShipTax.taxPayerNumber",carOwner_identifyNumber);//
		formMap.put("prpCcarShipTax.taxPayerIdentNo",carOwner_identifyNumber);//
		
		
		
        
        String url = "http://10.134.136.48:8000/prpall/business/refreshPlanByTimes.do";
        String respStr = httpClientUtil.doPost(url, formMap, "gbk");
        System.out.println("缴费计划:");
        System.out.println(respStr);
        
        Map planMap = JackJson.fromJsonToObject(respStr, Map.class);
        List planData = (List)planMap.get("data");
        for(int i=0;i<planData.size();i++){
        	Map plan = (Map)planData.get(i);
        	formMap.put("cplans["+i+"].backPlanFee",plan.get("planFee").toString());
        	formMap.put("cplans["+i+"].planFee",plan.get("planFee").toString());//null
        	formMap.put("cplan["+i+"].payReasonC",plan.get("payReasonName").toString());//null
        	formMap.put("prpCplanTemps["+i+"].netPremium",plan.get("netPremium") == null?"":plan.get("netPremium").toString());//7275.58
        	formMap.put("prpCplanTemps["+i+"].payReason",plan.get("payReason").toString());//null
        	formMap.put("prpCplanTemps["+i+"].taxPremium",plan.get("taxPremium")== null?"":plan.get("taxPremium").toString());//436.54
        	formMap.put("prpCplanTemps["+i+"].planDate",timeStamp2Date(((Map)plan.get("planDate")).get("time").toString(),"yyyy-M-d"));//null
        	formMap.put("prpCplanTemps["+i+"].subsidyRate",plan.get("subsidyRate").toString());//null
            formMap.put("prpCplanTemps["+i+"].payNo",plan.get("payNo").toString());//null
            formMap.put("prpCplanTemps["+i+"].isBICI",plan.get("isBICI").toString());//null
            formMap.put("prpCplanTemps["+i+"].planFee",plan.get("planFee").toString());//null
            formMap.put("prpCplanTemps["+i+"].delinquentFee",plan.get("delinquentFee").toString());//null  
            
        	formMap.put("cplan_["+i+"].payReasonC",plan.get("payReasonName").toString());//null
        }
        
        url = "http://10.134.136.48:8000/prpall/business/queryPayFor.do?agreementNo=&riskCode=DAA&comCode=11026871&chgCostRate=0";
        respStr = httpClientUtil.doPost(url, formMap, "gbk");
        System.out.println("querypayfor:");
        System.out.println(respStr);
        
        Map payMap = JackJson.fromJsonToObject(respStr, Map.class);
        
        if(((List)payMap.get("data")).size() < 1)
        	return "\"success\": false, \"msg\":\"查询 queryPayFor 数据失败\"";
        
        List prpDpayForPolicies = (List)((Map)((List)payMap.get("data")).get(0)).get("prpDpayForPolicies");
        for(int i=0;i<prpDpayForPolicies.size();i++){
        	Map dpay = (Map)prpDpayForPolicies.get(i);
        	formMap.put("prpCcommissionsTemp["+i+"].agreementNo",((Map)dpay.get("id")).get("agreementNo").toString());//null
        	formMap.put("prpCcommissionsTemp["+i+"].riskCode",dpay.get("riskCode").toString());//null
        	formMap.put("prpCcommissionsTemp["+i+"].auditRate","");//null
        	formMap.put("prpCcommissionsTemp["+i+"].coinsRate","100");//null
        	formMap.put("prpCcommissionsTemp["+i+"].adjustFlag",dpay.get("adjustFlag").toString());//null
        	formMap.put("prpCcommissionsTemp["+i+"].costRateUpper",dpay.get("costRateUpper").toString());//null
        	formMap.put("prpCcommissionsTemp["+i+"].configCode",((Map)dpay.get("id")).get("configCode").toString());//null
        	formMap.put("prpCcommissionsTemp["+i+"].costType",dpay.get("costType").toString());//null
        	formMap.put("prpCcommissionsTemp["+i+"].costFee","0");//null
        	if(dpay.get("riskCode").toString().equals("DAA")){
        		formMap.put("prpCcommissionsTemp["+i+"].sumPremium",formMap.get("prpCmain.sumPremium"));//null
        	}else{
        		formMap.put("prpCcommissionsTemp["+i+"].sumPremium",formMap.get("prpCitemKindCI.premium"));//null
        	}
        	formMap.put("prpCcommissionsTemp["+i+"].costRate",dpay.get("costRate").toString());//null
        	
        }
        
        List prpDdismantleDetails = (List)((Map)((List)payMap.get("data")).get(0)).get("prpDdismantleDetails");
        for(int i=0;i<prpDdismantleDetails.size();i++){
        	Map dismant = (Map)prpDdismantleDetails.get(i);
        	formMap.put("prpDdismantleDetails["+i+"].roleName",dismant.get("roleName").toString());
        	formMap.put("prpDdismantleDetails["+i+"].id.agreementNo",((Map)dismant.get("id")).get("agreementNo").toString());
        	formMap.put("prpDdismantleDetails["+i+"].roleFlag",dismant.get("roleFlag").toString());
        	formMap.put("prpDdismantleDetails["+i+"].id.configCode",((Map)dismant.get("id")).get("configCode").toString());
        	formMap.put("prpDdismantleDetails["+i+"].roleCode_uni",dismant.get("roleCode_uni").toString());
        	formMap.put("prpDdismantleDetails["+i+"].id.roleCode",((Map)dismant.get("id")).get("roleCode").toString());
        	formMap.put("prpDdismantleDetails["+i+"].costRate",dismant.get("costRate").toString());
        	formMap.put("prpDdismantleDetails["+i+"].businessNature",dismant.get("businessNature").toString());
        	formMap.put("prpDdismantleDetails["+i+"].id.assignType",((Map)dismant.get("id")).get("assignType").toString());
        	formMap.put("prpDdismantleDetails["+i+"].flag",dismant.get("flag").toString());
        }
        
        List prpCsaless = (List)((Map)((List)payMap.get("data")).get(0)).get("prpCsaless");
        prpCsaless =  prpCsaless == null?new ArrayList():prpCsaless;
    	for(int j=0;j<prpCsaless.size();j++){
    		Map csale = (Map)prpCsaless.get(j);
    		formMap.put("prpCsaless["+j+"].totalRate",csale.get("totalRate").toString());
    		formMap.put("prpCsaless["+j+"].riskCode",csale.get("riskCode").toString());
    		formMap.put("prpCsaless["+j+"].id.salesCode",((Map)csale.get("id")).get("salesCode").toString());
    		formMap.put("prpCsaless["+j+"].oriSplitNumber",csale.get("oriSplitNumber").toString());
    		formMap.put("prpCsaless["+j+"].id.salesDetailCode",((Map)csale.get("id")).get("salesDetailCode").toString());
    		formMap.put("prpCsaless["+j+"].totalRateMax",csale.get("totalRateMax").toString());
    		formMap.put("prpCsaless["+j+"].splitWay",csale.get("splitWay").toString());
    		formMap.put("prpCsaless["+j+"].splitFee",csale.get("splitFee").toString());
    		formMap.put("prpCsaless["+j+"].id.proposalNo",((Map)csale.get("id")).get("proposalNo").toString());
    		formMap.put("prpCsaless["+j+"].salesName",csale.get("salesName").toString());
    		formMap.put("prpCsaless["+j+"].salesDetailName",csale.get("salesDetailName").toString());
    		formMap.put("prpCsaless["+j+"].splitRate",csale.get("splitRate").toString());
    		formMap.put("prpCsaless["+j+"].flag",csale.get("flag").toString());
    		formMap.put("prpCsaless["+j+"].agreementNo",csale.get("agreementNo").toString());
    	}
		
    	Map<String, Object> outMap = new HashMap<String, Object>();
    	outMap.put("success", "false");
    	outMap.put("msg", "保存核保失败");
    	
		respStr = httpClientUtil.doPost("http://10.134.136.48:8000/prpall/business/insert4S.do", formMap, "GBK");
		System.out.println("保存投保单: " + respStr);
		String toubdH = respStr.split(",")[0];
		String toubdH2 = "";
		if(respStr.split(",").length > 1)
			toubdH2 = respStr.split(",")[1];
		
		if(!respStr.contains("errorMessage")) {
			String respStr2 = httpClientUtil.doPost("http://10.134.136.48:8000/prpall/business/editSubmitUndwrt.do?bizNo=" + toubdH, new HashMap<String, String>(), "GBK");
			respStr2 = httpClientUtil.doPost("http://10.134.136.48:8000/prpall/business/editSubmitUndwrt.do?bizNo=" + toubdH2, new HashMap<String, String>(), "GBK");
			
			outMap.put("syxToubdh", toubdH);
			outMap.put("jqxToubdh", toubdH2);
			
			outMap.put("success", "true");
	    	outMap.put("msg", "保存核保成功");
			
			Document doc = null;
			
			try {
				String strURL="http://10.134.136.48:8000/prpall/business/showUndwrtMsg.do?bizNo="+toubdH+"&bizType=PROPOSAL";
				if(!toubdH.equals("")) {
					respStr = httpClientUtil.doPost(strURL, new HashMap<String, String>(), "GBK");
					System.out.println(respStr);
					doc = Jsoup.parse(respStr);;
					System.out.println("商业险, " + doc.title());
					Element element = doc.getElementById("bpmUwNotionX[0].handleText");
					
					if(element != null) {
						String syxHbYj = element.childNodes().get(0).toString();
						outMap.put("syxHbYj", syxHbYj);
					}
				}
				
				strURL="http://10.134.136.48:8000/prpall/business/showUndwrtMsg.do?bizNo="+toubdH2+"&bizType=PROPOSAL";
				if(!toubdH2.equals("")) {
					respStr = httpClientUtil.doPost(strURL, new HashMap<String, String>(), "GBK");
					System.out.println(respStr);
					doc = Jsoup.parse(respStr);
					System.out.println("交强险, " + doc.title());
					Element element = doc.getElementById("bpmUwNotionX[0].handleText");
					
					if(element != null) {
						String jqxHbYj = element.childNodes().get(0).toString();
						outMap.put("jqxHbYj", jqxHbYj);
					}
				}
				
				outMap.put("success", "true");
		    	outMap.put("msg", "保存核保成功");
				
			} catch(Exception e) {
				outMap.put("success", "false");
		    	outMap.put("msg", e.getMessage());
			}
			
		}
		else
			outMap.put("msg", respStr);
		
//		outMap.put("success", "true");
//    	outMap.put("msg", "保存核保成功");
//		if(hebXianZ.equals("0")) {
//			outMap.put("syxToubdh", "TDDA201611020000717134");
//			outMap.put("syxHbYj", "满足自动核保规则，自动核保通过，通过原因为： 自动校验核保通过");
//		}
//		
//		if(hebXianZ.equals("1")) {
//			outMap.put("jqxToubdh", "TDZA201611020000717134");
//			outMap.put("jqxHbYj", "满足自动核保规则，自动核保通过，通过原因为： 自动校验核保通过");
//		}
//		
//		if(hebXianZ.equals("2")) {
//			outMap.put("syxToubdh", "TDDA201611020000717134");
//			outMap.put("syxHbYj", "满足自动核保规则，自动核保通过，通过原因为： 自动校验核保通过");
//			outMap.put("jqxToubdh", "TDZA201611020000717134");
//			outMap.put("jqxHbYj", "满足自动核保规则，自动核保通过，通过原因为： 自动校验核保通过");
//		}
			
		
		return JSONObject.fromObject(outMap).toString();
	}
	
	public String queryBaodhByToubdh(String in) {
		// TODO Auto-generated method stub
		JSONObject jsonObj = JSONObject.fromObject(in);
		String toubdh = jsonObj.getString("toubdh");
		
		Map<String, String> map = null;
		
		try {
			map = parse2Map("prpCpolicyVo.policyNo=&prpCpolicyVo.policyNo2=&prpCpolicyVo.proposalNo=" + toubdh + "&riskCode=DAA&maxPrintNo=&NEWVISASWITCH=0&isDirectFee=&searchConditionSwitch=0&queryinterval=04&prpCpolicyVo.licenseNo=&prpCpolicyVo.vinNo=&prpCpolicyVo.insuredCode=&prpCpolicyVo.insuredName=&prpCpolicyVo.contractNo=&prpCpolicyVo.operateDate=&prpCpolicyVo.operateDate2=&prpCpolicyVo.startDate=&prpCpolicyVo.startDate2=&prpCpolicyVo.dmFlag=all&prpCpolicyVo.visaNo=&prpCpolicyVo.printFlag=&prpCpolicyVo.brandName=&prpCpolicyVo.engineNo=&prpCpolicyVo.frameNo=&prpCpolicyVo.riskCode=DAA,DZA&prpCpolicyVo.appliCode=&prpCpolicyVo.apliName=&prpCpolicyVo.makeCom=&makeComDes=&prpCpolicyVo.operatorCode=&operatorCodeDes=&prpCpolicyVo.comCode=&comCodeDes=&prpCpolicyVo.handlerCode=&handlerCodeDes=&prpCpolicyVo.handler1Code=&handler1CodeDes=&prpCpolicyVo.endDate=&prpCpolicyVo.endDate2=");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String respStr = httpClientUtil.doPost("http://10.134.136.48:8000/prpall/business/selectPolicy.do?pageSize=10&pageNo=1", map, "GBK");
		System.out.println(respStr);
		JSONObject jObj = JSONObject.fromObject(respStr);
		JSONArray jdatas = jObj.getJSONArray("data");
		if(jdatas.size() < 1)
			return "{\"success\":false, \"msg\":\"查询保单号失败\"}";
		
		JSONObject jObj2 = JSONObject.fromObject(jdatas.get(0));
		jObj2.getString("policyNo");
		
		Map<String, String> outMap = new HashMap<String, String>();
		outMap.put("proposalNo", jObj2.getString("proposalNo"));
		outMap.put("policyNo", jObj2.getString("policyNo"));
		outMap.put("insuredName", jObj2.getString("insuredName"));
		//outMap.put("startDate", jObj2.getString("startDate"));
		outMap.put("startDate", timeStamp2Date("" + (Long) ((Map) jObj2.get("startDate")).get("time"), "yyyy-M-d"));
		
		
		return JSONObject.fromObject(outMap).toString();
	}
	
	
	Map<String, String> parse2Map(String param) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		String[] kvs = StringUtils.split(param, "&");
		for (String kv : kvs) {
			String[] k_v = StringUtils.split(kv, "=");
			if ("prpCitemKindsTemp[5].chooseFlag".equals(URLDecoder.decode(k_v[0], "gbk"))) {
				System.out.println(kv);
			}
			if (k_v.length > 1) {
				map.put(URLDecoder.decode(k_v[0], "gbk"), URLDecoder.decode(k_v[1], "gbk"));
			} else
				map.put(URLDecoder.decode(k_v[0], "gbk"), "");
		}
		return map;
	}

	public String queryRelationData(String in) {
		JSONObject jsonObj = JSONObject.fromObject(in);
		String shenfzNu = jsonObj.getString("shenfzNu");
		
		Map<String, String> map = null;
		
		String url = "http://10.134.136.48:8300/cif/customperson/findCustomPersonIntf.do?pageSize=10&pageNo=1&" + 
				"returnxs=&comCode=11026871&syscode=prpall&clientURL=&prpDcustomerPerson.customerCName=&prpDcustomerPerson.customerFullEName=&prpDcustomerPerson.identifyType=01&"
				+ "prpDcustomerPerson.identifyNumber=" + shenfzNu + "&prpDcustomerPerson.customerCode=&jumpToPage=1&jumpToPage=1";
		
		String respStr = httpClientUtil.doPost(url, new HashMap<String, String>(), charset);
		System.out.println(respStr);
		
		JSONObject jObj = JSONObject.fromObject(respStr);
		JSONArray jdatas = jObj.getJSONArray("data");
		if(jdatas.size() < 1)
			return "{\"success\":false, \"msg\":\"查询关系人失败\"}";
		
		JSONObject jObj2 = JSONObject.fromObject(jdatas.get(0));
		
		Map<String, String> outMap = new HashMap<String, String>();
		outMap.put("identifyNumber", jObj2.getString("identifyNumber"));
		outMap.put("customerCode", jObj2.getString("customerCode"));
		outMap.put("customerCName", jObj2.getString("customerCName"));
		outMap.put("addressCName", jObj2.getString("addressCName"));
		outMap.put("customMobile", jObj2.getString("customMobile"));
		
		return JSONObject.fromObject(outMap).toString();
	}

	public String queryHebJg(String in) {
		// TODO Auto-generated method stub
		Map<String, Object> outMap = new HashMap<String, Object>();
		outMap.put("success", false);
		outMap.put("hebYj", "核保还未完成");
		
		JSONObject jsonObj = JSONObject.fromObject(in);
		String toubDh = jsonObj.getString("toubDh");
		
		if(!toubDh.equals("")) {
			String postData = "comCode=11026871&riskCode=DAA&prpCproposalVo.checkFlag=&prpCproposalVo.underWriteFlag=&prpCproposalVo.strStartDate=&prpCproposalVo.othFlag=&prpCproposalVo.checkUpCode=&prpCproposalVo.operatorCode1=&prpCproposalVo.businessNature=&noNcheckFlag=0&jfcdURL=http://10.134.136.48:8100/cbc&prpallURL=http://10.134.136.48:8000/prpall&bizNoZ=&pageNo_=1&pageSize_=10&scmIsOpen=1111100000&searchConditionSwitch=0&queryinterval=04&prpCproposalVo.proposalNo=" + toubDh + "&prpCproposalVo.policyNo=&prpCproposalVo.licenseNo=&prpCproposalVo.vinNo=&prpCproposalVo.insuredCode=&prpCproposalVo.insuredName=&prpCproposalVo.contractNo=&prpCproposalVo.operateDate=&prpCproposalVo.operateDate2=&prpCproposalVo.startDate=&prpCproposalVo.startDate2=&prpCproposalVo.dmFlag=all&prpCproposalVo.underWriteFlagC=&prpCproposalVo.brandName=&prpCproposalVo.engineNo=&prpCproposalVo.frameNo=&prpCproposalVo.riskCode=DAA,DZA&prpCproposalVo.appliCode=&prpCproposalVo.apliName=&prpCproposalVo.makeCom=&makeComDes=&prpCproposalVo.operatorCode=&operatorCodeDes=&prpCproposalVo.comCode=&comCodeDes=&prpCproposalVo.handlerCode=&handlerCodeDes=&prpCproposalVo.handler1Code=&handler1CodeDes=&prpCproposalVo.endDate=&prpCproposalVo.endDate2=&prpCproposalVo.underWriteEndDate=&prpCproposalVo.underWriteEndDate2=";
			Map<String, String> map = null;
			try {
				map = parse2Map(postData);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String strURL="http://10.134.136.48:8000/prpall/business/selectProposal.do?pageSize=10&pageNo=1";
			String respStr = httpClientUtil.doPost(strURL, map, "GBK");
			System.out.println("查询投保单: " + respStr);
			
			strURL="http://10.134.136.48:8000/prpall/business/showUndwrtMsg.do?bizNo=" + toubDh + "&bizType=PROPOSAL";
			respStr = httpClientUtil.doPost(strURL, new HashMap<String, String>(), "GBK");
//			System.out.println(respStr);
			Document doc = Jsoup.parse(respStr);
//			System.out.println(doc.title());
			Element element = doc.getElementById("bpmUwNotionX[0].handleText");
			
			if(element != null) {
				String hebYj = element.childNodes().get(0).toString();
				outMap.put("hebYj", hebYj);
				outMap.put("success", true);
			}
		}
		
		return JSONObject.fromObject(outMap).toString();
	}

}

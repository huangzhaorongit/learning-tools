package com.distribution.wamoli.modules.ts.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.distribution.wamoli.common.bean.BasePojo;
import com.distribution.wamoli.modules.device.domain.Device;
import com.distribution.wamoli.modules.utils.TimeSeriesUtils;
import org.apache.commons.beanutils.BeanUtils;


/**
 * @author 方八零
 * @version 2016-06-5
 */
public class TimeSeries extends BasePojo<TimeSeries> {

	private static final long serialVersionUID = 348364807814450011L;
	@Transient
	private List<String> deviceids;
	@Transient
	private Integer colNum;
	@Transient
	private String colName; //update by column Name;
	@Transient
	private String measureType;
	@Transient
	private String tableName;

	@Transient
	private Device device;

 	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "device_id")
    private String deviceId;

    @Column(name = "YMD")
    private String ymd;

    private Integer cat;

    @Column(name = "Val0000")
    private Double val0000;

    @Column(name = "Val0005")
    private Double val0005;

    @Column(name = "Val0010")
    private Double val0010;

    @Column(name = "Val0015")
    private Double val0015;

    @Column(name = "Val0020")
    private Double val0020;

    @Column(name = "Val0025")
    private Double val0025;

    @Column(name = "Val0030")
    private Double val0030;

    @Column(name = "Val0035")
    private Double val0035;

    @Column(name = "Val0040")
    private Double val0040;

    @Column(name = "Val0045")
    private Double val0045;

    @Column(name = "Val0050")
    private Double val0050;

    @Column(name = "Val0055")
    private Double val0055;

    @Column(name = "Val0100")
    private Double val0100;

    @Column(name = "Val0105")
    private Double val0105;

    @Column(name = "Val0110")
    private Double val0110;

    @Column(name = "Val0115")
    private Double val0115;

    @Column(name = "Val0120")
    private Double val0120;

    @Column(name = "Val0125")
    private Double val0125;

    @Column(name = "Val0130")
    private Double val0130;

    @Column(name = "Val0135")
    private Double val0135;

    @Column(name = "Val0140")
    private Double val0140;

    @Column(name = "Val0145")
    private Double val0145;

    @Column(name = "Val0150")
    private Double val0150;

    @Column(name = "Val0155")
    private Double val0155;

    @Column(name = "Val0200")
    private Double val0200;

    @Column(name = "Val0205")
    private Double val0205;

    @Column(name = "Val0210")
    private Double val0210;

    @Column(name = "Val0215")
    private Double val0215;

    @Column(name = "Val0220")
    private Double val0220;

    @Column(name = "Val0225")
    private Double val0225;

    @Column(name = "Val0230")
    private Double val0230;

    @Column(name = "Val0235")
    private Double val0235;

    @Column(name = "Val0240")
    private Double val0240;

    @Column(name = "Val0245")
    private Double val0245;

    @Column(name = "Val0250")
    private Double val0250;

    @Column(name = "Val0255")
    private Double val0255;

    @Column(name = "Val0300")
    private Double val0300;

    @Column(name = "Val0305")
    private Double val0305;

    @Column(name = "Val0310")
    private Double val0310;

    @Column(name = "Val0315")
    private Double val0315;

    @Column(name = "Val0320")
    private Double val0320;

    @Column(name = "Val0325")
    private Double val0325;

    @Column(name = "Val0330")
    private Double val0330;

    @Column(name = "Val0335")
    private Double val0335;

    @Column(name = "Val0340")
    private Double val0340;

    @Column(name = "Val0345")
    private Double val0345;

    @Column(name = "Val0350")
    private Double val0350;

    @Column(name = "Val0355")
    private Double val0355;

    @Column(name = "Val0400")
    private Double val0400;

    @Column(name = "Val0405")
    private Double val0405;

    @Column(name = "Val0410")
    private Double val0410;

    @Column(name = "Val0415")
    private Double val0415;

    @Column(name = "Val0420")
    private Double val0420;

    @Column(name = "Val0425")
    private Double val0425;

    @Column(name = "Val0430")
    private Double val0430;

    @Column(name = "Val0435")
    private Double val0435;

    @Column(name = "Val0440")
    private Double val0440;

    @Column(name = "Val0445")
    private Double val0445;

    @Column(name = "Val0450")
    private Double val0450;

    @Column(name = "Val0455")
    private Double val0455;

    @Column(name = "Val0500")
    private Double val0500;

    @Column(name = "Val0505")
    private Double val0505;

    @Column(name = "Val0510")
    private Double val0510;

    @Column(name = "Val0515")
    private Double val0515;

    @Column(name = "Val0520")
    private Double val0520;

    @Column(name = "Val0525")
    private Double val0525;

    @Column(name = "Val0530")
    private Double val0530;

    @Column(name = "Val0535")
    private Double val0535;

    @Column(name = "Val0540")
    private Double val0540;

    @Column(name = "Val0545")
    private Double val0545;

    @Column(name = "Val0550")
    private Double val0550;

    @Column(name = "Val0555")
    private Double val0555;

    @Column(name = "Val0600")
    private Double val0600;

    @Column(name = "Val0605")
    private Double val0605;

    @Column(name = "Val0610")
    private Double val0610;

    @Column(name = "Val0615")
    private Double val0615;

    @Column(name = "Val0620")
    private Double val0620;

    @Column(name = "Val0625")
    private Double val0625;

    @Column(name = "Val0630")
    private Double val0630;

    @Column(name = "Val0635")
    private Double val0635;

    @Column(name = "Val0640")
    private Double val0640;

    @Column(name = "Val0645")
    private Double val0645;

    @Column(name = "Val0650")
    private Double val0650;

    @Column(name = "Val0655")
    private Double val0655;

    @Column(name = "Val0700")
    private Double val0700;

    @Column(name = "Val0705")
    private Double val0705;

    @Column(name = "Val0710")
    private Double val0710;

    @Column(name = "Val0715")
    private Double val0715;

    @Column(name = "Val0720")
    private Double val0720;

    @Column(name = "Val0725")
    private Double val0725;

    @Column(name = "Val0730")
    private Double val0730;

    @Column(name = "Val0735")
    private Double val0735;

    @Column(name = "Val0740")
    private Double val0740;

    @Column(name = "Val0745")
    private Double val0745;

    @Column(name = "Val0750")
    private Double val0750;

    @Column(name = "Val0755")
    private Double val0755;

    @Column(name = "Val0800")
    private Double val0800;

    @Column(name = "Val0805")
    private Double val0805;

    @Column(name = "Val0810")
    private Double val0810;

    @Column(name = "Val0815")
    private Double val0815;

    @Column(name = "Val0820")
    private Double val0820;

    @Column(name = "Val0825")
    private Double val0825;

    @Column(name = "Val0830")
    private Double val0830;

    @Column(name = "Val0835")
    private Double val0835;

    @Column(name = "Val0840")
    private Double val0840;

    @Column(name = "Val0845")
    private Double val0845;

    @Column(name = "Val0850")
    private Double val0850;

    @Column(name = "Val0855")
    private Double val0855;

    @Column(name = "Val0900")
    private Double val0900;

    @Column(name = "Val0905")
    private Double val0905;

    @Column(name = "Val0910")
    private Double val0910;

    @Column(name = "Val0915")
    private Double val0915;

    @Column(name = "Val0920")
    private Double val0920;

    @Column(name = "Val0925")
    private Double val0925;

    @Column(name = "Val0930")
    private Double val0930;

    @Column(name = "Val0935")
    private Double val0935;

    @Column(name = "Val0940")
    private Double val0940;

    @Column(name = "Val0945")
    private Double val0945;

    @Column(name = "Val0950")
    private Double val0950;

    @Column(name = "Val0955")
    private Double val0955;

    @Column(name = "Val1000")
    private Double val1000;

    @Column(name = "Val1005")
    private Double val1005;

    @Column(name = "Val1010")
    private Double val1010;

    @Column(name = "Val1015")
    private Double val1015;

    @Column(name = "Val1020")
    private Double val1020;

    @Column(name = "Val1025")
    private Double val1025;

    @Column(name = "Val1030")
    private Double val1030;

    @Column(name = "Val1035")
    private Double val1035;

    @Column(name = "Val1040")
    private Double val1040;

    @Column(name = "Val1045")
    private Double val1045;

    @Column(name = "Val1050")
    private Double val1050;

    @Column(name = "Val1055")
    private Double val1055;

    @Column(name = "Val1100")
    private Double val1100;

    @Column(name = "Val1105")
    private Double val1105;

    @Column(name = "Val1110")
    private Double val1110;

    @Column(name = "Val1115")
    private Double val1115;

    @Column(name = "Val1120")
    private Double val1120;

    @Column(name = "Val1125")
    private Double val1125;

    @Column(name = "Val1130")
    private Double val1130;

    @Column(name = "Val1135")
    private Double val1135;

    @Column(name = "Val1140")
    private Double val1140;

    @Column(name = "Val1145")
    private Double val1145;

    @Column(name = "Val1150")
    private Double val1150;

    @Column(name = "Val1155")
    private Double val1155;

    @Column(name = "Val1200")
    private Double val1200;

    @Column(name = "Val1205")
    private Double val1205;

    @Column(name = "Val1210")
    private Double val1210;

    @Column(name = "Val1215")
    private Double val1215;

    @Column(name = "Val1220")
    private Double val1220;

    @Column(name = "Val1225")
    private Double val1225;

    @Column(name = "Val1230")
    private Double val1230;

    @Column(name = "Val1235")
    private Double val1235;

    @Column(name = "Val1240")
    private Double val1240;

    @Column(name = "Val1245")
    private Double val1245;

    @Column(name = "Val1250")
    private Double val1250;

    @Column(name = "Val1255")
    private Double val1255;

    @Column(name = "Val1300")
    private Double val1300;

    @Column(name = "Val1305")
    private Double val1305;

    @Column(name = "Val1310")
    private Double val1310;

    @Column(name = "Val1315")
    private Double val1315;

    @Column(name = "Val1320")
    private Double val1320;

    @Column(name = "Val1325")
    private Double val1325;

    @Column(name = "Val1330")
    private Double val1330;

    @Column(name = "Val1335")
    private Double val1335;

    @Column(name = "Val1340")
    private Double val1340;

    @Column(name = "Val1345")
    private Double val1345;

    @Column(name = "Val1350")
    private Double val1350;

    @Column(name = "Val1355")
    private Double val1355;

    @Column(name = "Val1400")
    private Double val1400;

    @Column(name = "Val1405")
    private Double val1405;

    @Column(name = "Val1410")
    private Double val1410;

    @Column(name = "Val1415")
    private Double val1415;

    @Column(name = "Val1420")
    private Double val1420;

    @Column(name = "Val1425")
    private Double val1425;

    @Column(name = "Val1430")
    private Double val1430;

    @Column(name = "Val1435")
    private Double val1435;

    @Column(name = "Val1440")
    private Double val1440;

    @Column(name = "Val1445")
    private Double val1445;

    @Column(name = "Val1450")
    private Double val1450;

    @Column(name = "Val1455")
    private Double val1455;

    @Column(name = "Val1500")
    private Double val1500;

    @Column(name = "Val1505")
    private Double val1505;

    @Column(name = "Val1510")
    private Double val1510;

    @Column(name = "Val1515")
    private Double val1515;

    @Column(name = "Val1520")
    private Double val1520;

    @Column(name = "Val1525")
    private Double val1525;

    @Column(name = "Val1530")
    private Double val1530;

    @Column(name = "Val1535")
    private Double val1535;

    @Column(name = "Val1540")
    private Double val1540;

    @Column(name = "Val1545")
    private Double val1545;

    @Column(name = "Val1550")
    private Double val1550;

    @Column(name = "Val1555")
    private Double val1555;

    @Column(name = "Val1600")
    private Double val1600;

    @Column(name = "Val1605")
    private Double val1605;

    @Column(name = "Val1610")
    private Double val1610;

    @Column(name = "Val1615")
    private Double val1615;

    @Column(name = "Val1620")
    private Double val1620;

    @Column(name = "Val1625")
    private Double val1625;

    @Column(name = "Val1630")
    private Double val1630;

    @Column(name = "Val1635")
    private Double val1635;

    @Column(name = "Val1640")
    private Double val1640;

    @Column(name = "Val1645")
    private Double val1645;

    @Column(name = "Val1650")
    private Double val1650;

    @Column(name = "Val1655")
    private Double val1655;

    @Column(name = "Val1700")
    private Double val1700;

    @Column(name = "Val1705")
    private Double val1705;

    @Column(name = "Val1710")
    private Double val1710;

    @Column(name = "Val1715")
    private Double val1715;

    @Column(name = "Val1720")
    private Double val1720;

    @Column(name = "Val1725")
    private Double val1725;

    @Column(name = "Val1730")
    private Double val1730;

    @Column(name = "Val1735")
    private Double val1735;

    @Column(name = "Val1740")
    private Double val1740;

    @Column(name = "Val1745")
    private Double val1745;

    @Column(name = "Val1750")
    private Double val1750;

    @Column(name = "Val1755")
    private Double val1755;

    @Column(name = "Val1800")
    private Double val1800;

    @Column(name = "Val1805")
    private Double val1805;

    @Column(name = "Val1810")
    private Double val1810;

    @Column(name = "Val1815")
    private Double val1815;

    @Column(name = "Val1820")
    private Double val1820;

    @Column(name = "Val1825")
    private Double val1825;

    @Column(name = "Val1830")
    private Double val1830;

    @Column(name = "Val1835")
    private Double val1835;

    @Column(name = "Val1840")
    private Double val1840;

    @Column(name = "Val1845")
    private Double val1845;

    @Column(name = "Val1850")
    private Double val1850;

    @Column(name = "Val1855")
    private Double val1855;

    @Column(name = "Val1900")
    private Double val1900;

    @Column(name = "Val1905")
    private Double val1905;

    @Column(name = "Val1910")
    private Double val1910;

    @Column(name = "Val1915")
    private Double val1915;

    @Column(name = "Val1920")
    private Double val1920;

    @Column(name = "Val1925")
    private Double val1925;

    @Column(name = "Val1930")
    private Double val1930;

    @Column(name = "Val1935")
    private Double val1935;

    @Column(name = "Val1940")
    private Double val1940;

    @Column(name = "Val1945")
    private Double val1945;

    @Column(name = "Val1950")
    private Double val1950;

    @Column(name = "Val1955")
    private Double val1955;

    @Column(name = "Val2000")
    private Double val2000;

    @Column(name = "Val2005")
    private Double val2005;

    @Column(name = "Val2010")
    private Double val2010;

    @Column(name = "Val2015")
    private Double val2015;

    @Column(name = "Val2020")
    private Double val2020;

    @Column(name = "Val2025")
    private Double val2025;

    @Column(name = "Val2030")
    private Double val2030;

    @Column(name = "Val2035")
    private Double val2035;

    @Column(name = "Val2040")
    private Double val2040;

    @Column(name = "Val2045")
    private Double val2045;

    @Column(name = "Val2050")
    private Double val2050;

    @Column(name = "Val2055")
    private Double val2055;

    @Column(name = "Val2100")
    private Double val2100;

    @Column(name = "Val2105")
    private Double val2105;

    @Column(name = "Val2110")
    private Double val2110;

    @Column(name = "Val2115")
    private Double val2115;

    @Column(name = "Val2120")
    private Double val2120;

    @Column(name = "Val2125")
    private Double val2125;

    @Column(name = "Val2130")
    private Double val2130;

    @Column(name = "Val2135")
    private Double val2135;

    @Column(name = "Val2140")
    private Double val2140;

    @Column(name = "Val2145")
    private Double val2145;

    @Column(name = "Val2150")
    private Double val2150;

    @Column(name = "Val2155")
    private Double val2155;

    @Column(name = "Val2200")
    private Double val2200;

    @Column(name = "Val2205")
    private Double val2205;

    @Column(name = "Val2210")
    private Double val2210;

    @Column(name = "Val2215")
    private Double val2215;

    @Column(name = "Val2220")
    private Double val2220;

    @Column(name = "Val2225")
    private Double val2225;

    @Column(name = "Val2230")
    private Double val2230;

    @Column(name = "Val2235")
    private Double val2235;

    @Column(name = "Val2240")
    private Double val2240;

    @Column(name = "Val2245")
    private Double val2245;

    @Column(name = "Val2250")
    private Double val2250;

    @Column(name = "Val2255")
    private Double val2255;

    @Column(name = "Val2300")
    private Double val2300;

    @Column(name = "Val2305")
    private Double val2305;

    @Column(name = "Val2310")
    private Double val2310;

    @Column(name = "Val2315")
    private Double val2315;

    @Column(name = "Val2320")
    private Double val2320;

    @Column(name = "Val2325")
    private Double val2325;

    @Column(name = "Val2330")
    private Double val2330;

    @Column(name = "Val2335")
    private Double val2335;

    @Column(name = "Val2340")
    private Double val2340;

    @Column(name = "Val2345")
    private Double val2345;

    @Column(name = "Val2350")
    private Double val2350;

    @Column(name = "Val2355")
    private Double val2355;

    @Column(name = "MaxVal")
    private Double maxval;

    @Column(name = "MinVal")
    private Double minval;

    @Column(name = "AvgVal")
    private Double avgval;

    @Column(name = "IntVal")
    private Double intval;

    @Column(name = "SumVal")
    private Double sumval;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return device_id
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * @param deviceId
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * @return YMD
     */
    public String getYmd() {
        return ymd;
    }

    /**
     * @param ymd
     */
    public void setYmd(String ymd) {
        this.ymd = ymd;
    }

    /**
     * @return cat
     */
    public Integer getCat() {
        return cat;
    }

    /**
     * @param cat
     */
    public void setCat(Integer cat) {
        this.cat = cat;
    }

    /**
     * @return Val0000
     */
    public Double getVal0000() {
        return val0000;
    }

    /**
     * @param val0000
     */
    public void setVal0000(Double val0000) {
        this.val0000 = val0000;
    }

    /**
     * @return Val0005
     */
    public Double getVal0005() {
        return val0005;
    }

    /**
     * @param val0005
     */
    public void setVal0005(Double val0005) {
        this.val0005 = val0005;
    }

    /**
     * @return Val0010
     */
    public Double getVal0010() {
        return val0010;
    }

    /**
     * @param val0010
     */
    public void setVal0010(Double val0010) {
        this.val0010 = val0010;
    }

    /**
     * @return Val0015
     */
    public Double getVal0015() {
        return val0015;
    }

    /**
     * @param val0015
     */
    public void setVal0015(Double val0015) {
        this.val0015 = val0015;
    }

    /**
     * @return Val0020
     */
    public Double getVal0020() {
        return val0020;
    }

    /**
     * @param val0020
     */
    public void setVal0020(Double val0020) {
        this.val0020 = val0020;
    }

    /**
     * @return Val0025
     */
    public Double getVal0025() {
        return val0025;
    }

    /**
     * @param val0025
     */
    public void setVal0025(Double val0025) {
        this.val0025 = val0025;
    }

    /**
     * @return Val0030
     */
    public Double getVal0030() {
        return val0030;
    }

    /**
     * @param val0030
     */
    public void setVal0030(Double val0030) {
        this.val0030 = val0030;
    }

    /**
     * @return Val0035
     */
    public Double getVal0035() {
        return val0035;
    }

    /**
     * @param val0035
     */
    public void setVal0035(Double val0035) {
        this.val0035 = val0035;
    }

    /**
     * @return Val0040
     */
    public Double getVal0040() {
        return val0040;
    }

    /**
     * @param val0040
     */
    public void setVal0040(Double val0040) {
        this.val0040 = val0040;
    }

    /**
     * @return Val0045
     */
    public Double getVal0045() {
        return val0045;
    }

    /**
     * @param val0045
     */
    public void setVal0045(Double val0045) {
        this.val0045 = val0045;
    }

    /**
     * @return Val0050
     */
    public Double getVal0050() {
        return val0050;
    }

    /**
     * @param val0050
     */
    public void setVal0050(Double val0050) {
        this.val0050 = val0050;
    }

    /**
     * @return Val0055
     */
    public Double getVal0055() {
        return val0055;
    }

    /**
     * @param val0055
     */
    public void setVal0055(Double val0055) {
        this.val0055 = val0055;
    }

    /**
     * @return Val0100
     */
    public Double getVal0100() {
        return val0100;
    }

    /**
     * @param val0100
     */
    public void setVal0100(Double val0100) {
        this.val0100 = val0100;
    }

    /**
     * @return Val0105
     */
    public Double getVal0105() {
        return val0105;
    }

    /**
     * @param val0105
     */
    public void setVal0105(Double val0105) {
        this.val0105 = val0105;
    }

    /**
     * @return Val0110
     */
    public Double getVal0110() {
        return val0110;
    }

    /**
     * @param val0110
     */
    public void setVal0110(Double val0110) {
        this.val0110 = val0110;
    }

    /**
     * @return Val0115
     */
    public Double getVal0115() {
        return val0115;
    }

    /**
     * @param val0115
     */
    public void setVal0115(Double val0115) {
        this.val0115 = val0115;
    }

    /**
     * @return Val0120
     */
    public Double getVal0120() {
        return val0120;
    }

    /**
     * @param val0120
     */
    public void setVal0120(Double val0120) {
        this.val0120 = val0120;
    }

    /**
     * @return Val0125
     */
    public Double getVal0125() {
        return val0125;
    }

    /**
     * @param val0125
     */
    public void setVal0125(Double val0125) {
        this.val0125 = val0125;
    }

    /**
     * @return Val0130
     */
    public Double getVal0130() {
        return val0130;
    }

    /**
     * @param val0130
     */
    public void setVal0130(Double val0130) {
        this.val0130 = val0130;
    }

    /**
     * @return Val0135
     */
    public Double getVal0135() {
        return val0135;
    }

    /**
     * @param val0135
     */
    public void setVal0135(Double val0135) {
        this.val0135 = val0135;
    }

    /**
     * @return Val0140
     */
    public Double getVal0140() {
        return val0140;
    }

    /**
     * @param val0140
     */
    public void setVal0140(Double val0140) {
        this.val0140 = val0140;
    }

    /**
     * @return Val0145
     */
    public Double getVal0145() {
        return val0145;
    }

    /**
     * @param val0145
     */
    public void setVal0145(Double val0145) {
        this.val0145 = val0145;
    }

    /**
     * @return Val0150
     */
    public Double getVal0150() {
        return val0150;
    }

    /**
     * @param val0150
     */
    public void setVal0150(Double val0150) {
        this.val0150 = val0150;
    }

    /**
     * @return Val0155
     */
    public Double getVal0155() {
        return val0155;
    }

    /**
     * @param val0155
     */
    public void setVal0155(Double val0155) {
        this.val0155 = val0155;
    }

    /**
     * @return Val0200
     */
    public Double getVal0200() {
        return val0200;
    }

    /**
     * @param val0200
     */
    public void setVal0200(Double val0200) {
        this.val0200 = val0200;
    }

    /**
     * @return Val0205
     */
    public Double getVal0205() {
        return val0205;
    }

    /**
     * @param val0205
     */
    public void setVal0205(Double val0205) {
        this.val0205 = val0205;
    }

    /**
     * @return Val0210
     */
    public Double getVal0210() {
        return val0210;
    }

    /**
     * @param val0210
     */
    public void setVal0210(Double val0210) {
        this.val0210 = val0210;
    }

    /**
     * @return Val0215
     */
    public Double getVal0215() {
        return val0215;
    }

    /**
     * @param val0215
     */
    public void setVal0215(Double val0215) {
        this.val0215 = val0215;
    }

    /**
     * @return Val0220
     */
    public Double getVal0220() {
        return val0220;
    }

    /**
     * @param val0220
     */
    public void setVal0220(Double val0220) {
        this.val0220 = val0220;
    }

    /**
     * @return Val0225
     */
    public Double getVal0225() {
        return val0225;
    }

    /**
     * @param val0225
     */
    public void setVal0225(Double val0225) {
        this.val0225 = val0225;
    }

    /**
     * @return Val0230
     */
    public Double getVal0230() {
        return val0230;
    }

    /**
     * @param val0230
     */
    public void setVal0230(Double val0230) {
        this.val0230 = val0230;
    }

    /**
     * @return Val0235
     */
    public Double getVal0235() {
        return val0235;
    }

    /**
     * @param val0235
     */
    public void setVal0235(Double val0235) {
        this.val0235 = val0235;
    }

    /**
     * @return Val0240
     */
    public Double getVal0240() {
        return val0240;
    }

    /**
     * @param val0240
     */
    public void setVal0240(Double val0240) {
        this.val0240 = val0240;
    }

    /**
     * @return Val0245
     */
    public Double getVal0245() {
        return val0245;
    }

    /**
     * @param val0245
     */
    public void setVal0245(Double val0245) {
        this.val0245 = val0245;
    }

    /**
     * @return Val0250
     */
    public Double getVal0250() {
        return val0250;
    }

    /**
     * @param val0250
     */
    public void setVal0250(Double val0250) {
        this.val0250 = val0250;
    }

    /**
     * @return Val0255
     */
    public Double getVal0255() {
        return val0255;
    }

    /**
     * @param val0255
     */
    public void setVal0255(Double val0255) {
        this.val0255 = val0255;
    }

    /**
     * @return Val0300
     */
    public Double getVal0300() {
        return val0300;
    }

    /**
     * @param val0300
     */
    public void setVal0300(Double val0300) {
        this.val0300 = val0300;
    }

    /**
     * @return Val0305
     */
    public Double getVal0305() {
        return val0305;
    }

    /**
     * @param val0305
     */
    public void setVal0305(Double val0305) {
        this.val0305 = val0305;
    }

    /**
     * @return Val0310
     */
    public Double getVal0310() {
        return val0310;
    }

    /**
     * @param val0310
     */
    public void setVal0310(Double val0310) {
        this.val0310 = val0310;
    }

    /**
     * @return Val0315
     */
    public Double getVal0315() {
        return val0315;
    }

    /**
     * @param val0315
     */
    public void setVal0315(Double val0315) {
        this.val0315 = val0315;
    }

    /**
     * @return Val0320
     */
    public Double getVal0320() {
        return val0320;
    }

    /**
     * @param val0320
     */
    public void setVal0320(Double val0320) {
        this.val0320 = val0320;
    }

    /**
     * @return Val0325
     */
    public Double getVal0325() {
        return val0325;
    }

    /**
     * @param val0325
     */
    public void setVal0325(Double val0325) {
        this.val0325 = val0325;
    }

    /**
     * @return Val0330
     */
    public Double getVal0330() {
        return val0330;
    }

    /**
     * @param val0330
     */
    public void setVal0330(Double val0330) {
        this.val0330 = val0330;
    }

    /**
     * @return Val0335
     */
    public Double getVal0335() {
        return val0335;
    }

    /**
     * @param val0335
     */
    public void setVal0335(Double val0335) {
        this.val0335 = val0335;
    }

    /**
     * @return Val0340
     */
    public Double getVal0340() {
        return val0340;
    }

    /**
     * @param val0340
     */
    public void setVal0340(Double val0340) {
        this.val0340 = val0340;
    }

    /**
     * @return Val0345
     */
    public Double getVal0345() {
        return val0345;
    }

    /**
     * @param val0345
     */
    public void setVal0345(Double val0345) {
        this.val0345 = val0345;
    }

    /**
     * @return Val0350
     */
    public Double getVal0350() {
        return val0350;
    }

    /**
     * @param val0350
     */
    public void setVal0350(Double val0350) {
        this.val0350 = val0350;
    }

    /**
     * @return Val0355
     */
    public Double getVal0355() {
        return val0355;
    }

    /**
     * @param val0355
     */
    public void setVal0355(Double val0355) {
        this.val0355 = val0355;
    }

    /**
     * @return Val0400
     */
    public Double getVal0400() {
        return val0400;
    }

    /**
     * @param val0400
     */
    public void setVal0400(Double val0400) {
        this.val0400 = val0400;
    }

    /**
     * @return Val0405
     */
    public Double getVal0405() {
        return val0405;
    }

    /**
     * @param val0405
     */
    public void setVal0405(Double val0405) {
        this.val0405 = val0405;
    }

    /**
     * @return Val0410
     */
    public Double getVal0410() {
        return val0410;
    }

    /**
     * @param val0410
     */
    public void setVal0410(Double val0410) {
        this.val0410 = val0410;
    }

    /**
     * @return Val0415
     */
    public Double getVal0415() {
        return val0415;
    }

    /**
     * @param val0415
     */
    public void setVal0415(Double val0415) {
        this.val0415 = val0415;
    }

    /**
     * @return Val0420
     */
    public Double getVal0420() {
        return val0420;
    }

    /**
     * @param val0420
     */
    public void setVal0420(Double val0420) {
        this.val0420 = val0420;
    }

    /**
     * @return Val0425
     */
    public Double getVal0425() {
        return val0425;
    }

    /**
     * @param val0425
     */
    public void setVal0425(Double val0425) {
        this.val0425 = val0425;
    }

    /**
     * @return Val0430
     */
    public Double getVal0430() {
        return val0430;
    }

    /**
     * @param val0430
     */
    public void setVal0430(Double val0430) {
        this.val0430 = val0430;
    }

    /**
     * @return Val0435
     */
    public Double getVal0435() {
        return val0435;
    }

    /**
     * @param val0435
     */
    public void setVal0435(Double val0435) {
        this.val0435 = val0435;
    }

    /**
     * @return Val0440
     */
    public Double getVal0440() {
        return val0440;
    }

    /**
     * @param val0440
     */
    public void setVal0440(Double val0440) {
        this.val0440 = val0440;
    }

    /**
     * @return Val0445
     */
    public Double getVal0445() {
        return val0445;
    }

    /**
     * @param val0445
     */
    public void setVal0445(Double val0445) {
        this.val0445 = val0445;
    }

    /**
     * @return Val0450
     */
    public Double getVal0450() {
        return val0450;
    }

    /**
     * @param val0450
     */
    public void setVal0450(Double val0450) {
        this.val0450 = val0450;
    }

    /**
     * @return Val0455
     */
    public Double getVal0455() {
        return val0455;
    }

    /**
     * @param val0455
     */
    public void setVal0455(Double val0455) {
        this.val0455 = val0455;
    }

    /**
     * @return Val0500
     */
    public Double getVal0500() {
        return val0500;
    }

    /**
     * @param val0500
     */
    public void setVal0500(Double val0500) {
        this.val0500 = val0500;
    }

    /**
     * @return Val0505
     */
    public Double getVal0505() {
        return val0505;
    }

    /**
     * @param val0505
     */
    public void setVal0505(Double val0505) {
        this.val0505 = val0505;
    }

    /**
     * @return Val0510
     */
    public Double getVal0510() {
        return val0510;
    }

    /**
     * @param val0510
     */
    public void setVal0510(Double val0510) {
        this.val0510 = val0510;
    }

    /**
     * @return Val0515
     */
    public Double getVal0515() {
        return val0515;
    }

    /**
     * @param val0515
     */
    public void setVal0515(Double val0515) {
        this.val0515 = val0515;
    }

    /**
     * @return Val0520
     */
    public Double getVal0520() {
        return val0520;
    }

    /**
     * @param val0520
     */
    public void setVal0520(Double val0520) {
        this.val0520 = val0520;
    }

    /**
     * @return Val0525
     */
    public Double getVal0525() {
        return val0525;
    }

    /**
     * @param val0525
     */
    public void setVal0525(Double val0525) {
        this.val0525 = val0525;
    }

    /**
     * @return Val0530
     */
    public Double getVal0530() {
        return val0530;
    }

    /**
     * @param val0530
     */
    public void setVal0530(Double val0530) {
        this.val0530 = val0530;
    }

    /**
     * @return Val0535
     */
    public Double getVal0535() {
        return val0535;
    }

    /**
     * @param val0535
     */
    public void setVal0535(Double val0535) {
        this.val0535 = val0535;
    }

    /**
     * @return Val0540
     */
    public Double getVal0540() {
        return val0540;
    }

    /**
     * @param val0540
     */
    public void setVal0540(Double val0540) {
        this.val0540 = val0540;
    }

    /**
     * @return Val0545
     */
    public Double getVal0545() {
        return val0545;
    }

    /**
     * @param val0545
     */
    public void setVal0545(Double val0545) {
        this.val0545 = val0545;
    }

    /**
     * @return Val0550
     */
    public Double getVal0550() {
        return val0550;
    }

    /**
     * @param val0550
     */
    public void setVal0550(Double val0550) {
        this.val0550 = val0550;
    }

    /**
     * @return Val0555
     */
    public Double getVal0555() {
        return val0555;
    }

    /**
     * @param val0555
     */
    public void setVal0555(Double val0555) {
        this.val0555 = val0555;
    }

    /**
     * @return Val0600
     */
    public Double getVal0600() {
        return val0600;
    }

    /**
     * @param val0600
     */
    public void setVal0600(Double val0600) {
        this.val0600 = val0600;
    }

    /**
     * @return Val0605
     */
    public Double getVal0605() {
        return val0605;
    }

    /**
     * @param val0605
     */
    public void setVal0605(Double val0605) {
        this.val0605 = val0605;
    }

    /**
     * @return Val0610
     */
    public Double getVal0610() {
        return val0610;
    }

    /**
     * @param val0610
     */
    public void setVal0610(Double val0610) {
        this.val0610 = val0610;
    }

    /**
     * @return Val0615
     */
    public Double getVal0615() {
        return val0615;
    }

    /**
     * @param val0615
     */
    public void setVal0615(Double val0615) {
        this.val0615 = val0615;
    }

    /**
     * @return Val0620
     */
    public Double getVal0620() {
        return val0620;
    }

    /**
     * @param val0620
     */
    public void setVal0620(Double val0620) {
        this.val0620 = val0620;
    }

    /**
     * @return Val0625
     */
    public Double getVal0625() {
        return val0625;
    }

    /**
     * @param val0625
     */
    public void setVal0625(Double val0625) {
        this.val0625 = val0625;
    }

    /**
     * @return Val0630
     */
    public Double getVal0630() {
        return val0630;
    }

    /**
     * @param val0630
     */
    public void setVal0630(Double val0630) {
        this.val0630 = val0630;
    }

    /**
     * @return Val0635
     */
    public Double getVal0635() {
        return val0635;
    }

    /**
     * @param val0635
     */
    public void setVal0635(Double val0635) {
        this.val0635 = val0635;
    }

    /**
     * @return Val0640
     */
    public Double getVal0640() {
        return val0640;
    }

    /**
     * @param val0640
     */
    public void setVal0640(Double val0640) {
        this.val0640 = val0640;
    }

    /**
     * @return Val0645
     */
    public Double getVal0645() {
        return val0645;
    }

    /**
     * @param val0645
     */
    public void setVal0645(Double val0645) {
        this.val0645 = val0645;
    }

    /**
     * @return Val0650
     */
    public Double getVal0650() {
        return val0650;
    }

    /**
     * @param val0650
     */
    public void setVal0650(Double val0650) {
        this.val0650 = val0650;
    }

    /**
     * @return Val0655
     */
    public Double getVal0655() {
        return val0655;
    }

    /**
     * @param val0655
     */
    public void setVal0655(Double val0655) {
        this.val0655 = val0655;
    }

    /**
     * @return Val0700
     */
    public Double getVal0700() {
        return val0700;
    }

    /**
     * @param val0700
     */
    public void setVal0700(Double val0700) {
        this.val0700 = val0700;
    }

    /**
     * @return Val0705
     */
    public Double getVal0705() {
        return val0705;
    }

    /**
     * @param val0705
     */
    public void setVal0705(Double val0705) {
        this.val0705 = val0705;
    }

    /**
     * @return Val0710
     */
    public Double getVal0710() {
        return val0710;
    }

    /**
     * @param val0710
     */
    public void setVal0710(Double val0710) {
        this.val0710 = val0710;
    }

    /**
     * @return Val0715
     */
    public Double getVal0715() {
        return val0715;
    }

    /**
     * @param val0715
     */
    public void setVal0715(Double val0715) {
        this.val0715 = val0715;
    }

    /**
     * @return Val0720
     */
    public Double getVal0720() {
        return val0720;
    }

    /**
     * @param val0720
     */
    public void setVal0720(Double val0720) {
        this.val0720 = val0720;
    }

    /**
     * @return Val0725
     */
    public Double getVal0725() {
        return val0725;
    }

    /**
     * @param val0725
     */
    public void setVal0725(Double val0725) {
        this.val0725 = val0725;
    }

    /**
     * @return Val0730
     */
    public Double getVal0730() {
        return val0730;
    }

    /**
     * @param val0730
     */
    public void setVal0730(Double val0730) {
        this.val0730 = val0730;
    }

    /**
     * @return Val0735
     */
    public Double getVal0735() {
        return val0735;
    }

    /**
     * @param val0735
     */
    public void setVal0735(Double val0735) {
        this.val0735 = val0735;
    }

    /**
     * @return Val0740
     */
    public Double getVal0740() {
        return val0740;
    }

    /**
     * @param val0740
     */
    public void setVal0740(Double val0740) {
        this.val0740 = val0740;
    }

    /**
     * @return Val0745
     */
    public Double getVal0745() {
        return val0745;
    }

    /**
     * @param val0745
     */
    public void setVal0745(Double val0745) {
        this.val0745 = val0745;
    }

    /**
     * @return Val0750
     */
    public Double getVal0750() {
        return val0750;
    }

    /**
     * @param val0750
     */
    public void setVal0750(Double val0750) {
        this.val0750 = val0750;
    }

    /**
     * @return Val0755
     */
    public Double getVal0755() {
        return val0755;
    }

    /**
     * @param val0755
     */
    public void setVal0755(Double val0755) {
        this.val0755 = val0755;
    }

    /**
     * @return Val0800
     */
    public Double getVal0800() {
        return val0800;
    }

    /**
     * @param val0800
     */
    public void setVal0800(Double val0800) {
        this.val0800 = val0800;
    }

    /**
     * @return Val0805
     */
    public Double getVal0805() {
        return val0805;
    }

    /**
     * @param val0805
     */
    public void setVal0805(Double val0805) {
        this.val0805 = val0805;
    }

    /**
     * @return Val0810
     */
    public Double getVal0810() {
        return val0810;
    }

    /**
     * @param val0810
     */
    public void setVal0810(Double val0810) {
        this.val0810 = val0810;
    }

    /**
     * @return Val0815
     */
    public Double getVal0815() {
        return val0815;
    }

    /**
     * @param val0815
     */
    public void setVal0815(Double val0815) {
        this.val0815 = val0815;
    }

    /**
     * @return Val0820
     */
    public Double getVal0820() {
        return val0820;
    }

    /**
     * @param val0820
     */
    public void setVal0820(Double val0820) {
        this.val0820 = val0820;
    }

    /**
     * @return Val0825
     */
    public Double getVal0825() {
        return val0825;
    }

    /**
     * @param val0825
     */
    public void setVal0825(Double val0825) {
        this.val0825 = val0825;
    }

    /**
     * @return Val0830
     */
    public Double getVal0830() {
        return val0830;
    }

    /**
     * @param val0830
     */
    public void setVal0830(Double val0830) {
        this.val0830 = val0830;
    }

    /**
     * @return Val0835
     */
    public Double getVal0835() {
        return val0835;
    }

    /**
     * @param val0835
     */
    public void setVal0835(Double val0835) {
        this.val0835 = val0835;
    }

    /**
     * @return Val0840
     */
    public Double getVal0840() {
        return val0840;
    }

    /**
     * @param val0840
     */
    public void setVal0840(Double val0840) {
        this.val0840 = val0840;
    }

    /**
     * @return Val0845
     */
    public Double getVal0845() {
        return val0845;
    }

    /**
     * @param val0845
     */
    public void setVal0845(Double val0845) {
        this.val0845 = val0845;
    }

    /**
     * @return Val0850
     */
    public Double getVal0850() {
        return val0850;
    }

    /**
     * @param val0850
     */
    public void setVal0850(Double val0850) {
        this.val0850 = val0850;
    }

    /**
     * @return Val0855
     */
    public Double getVal0855() {
        return val0855;
    }

    /**
     * @param val0855
     */
    public void setVal0855(Double val0855) {
        this.val0855 = val0855;
    }

    /**
     * @return Val0900
     */
    public Double getVal0900() {
        return val0900;
    }

    /**
     * @param val0900
     */
    public void setVal0900(Double val0900) {
        this.val0900 = val0900;
    }

    /**
     * @return Val0905
     */
    public Double getVal0905() {
        return val0905;
    }

    /**
     * @param val0905
     */
    public void setVal0905(Double val0905) {
        this.val0905 = val0905;
    }

    /**
     * @return Val0910
     */
    public Double getVal0910() {
        return val0910;
    }

    /**
     * @param val0910
     */
    public void setVal0910(Double val0910) {
        this.val0910 = val0910;
    }

    /**
     * @return Val0915
     */
    public Double getVal0915() {
        return val0915;
    }

    /**
     * @param val0915
     */
    public void setVal0915(Double val0915) {
        this.val0915 = val0915;
    }

    /**
     * @return Val0920
     */
    public Double getVal0920() {
        return val0920;
    }

    /**
     * @param val0920
     */
    public void setVal0920(Double val0920) {
        this.val0920 = val0920;
    }

    /**
     * @return Val0925
     */
    public Double getVal0925() {
        return val0925;
    }

    /**
     * @param val0925
     */
    public void setVal0925(Double val0925) {
        this.val0925 = val0925;
    }

    /**
     * @return Val0930
     */
    public Double getVal0930() {
        return val0930;
    }

    /**
     * @param val0930
     */
    public void setVal0930(Double val0930) {
        this.val0930 = val0930;
    }

    /**
     * @return Val0935
     */
    public Double getVal0935() {
        return val0935;
    }

    /**
     * @param val0935
     */
    public void setVal0935(Double val0935) {
        this.val0935 = val0935;
    }

    /**
     * @return Val0940
     */
    public Double getVal0940() {
        return val0940;
    }

    /**
     * @param val0940
     */
    public void setVal0940(Double val0940) {
        this.val0940 = val0940;
    }

    /**
     * @return Val0945
     */
    public Double getVal0945() {
        return val0945;
    }

    /**
     * @param val0945
     */
    public void setVal0945(Double val0945) {
        this.val0945 = val0945;
    }

    /**
     * @return Val0950
     */
    public Double getVal0950() {
        return val0950;
    }

    /**
     * @param val0950
     */
    public void setVal0950(Double val0950) {
        this.val0950 = val0950;
    }

    /**
     * @return Val0955
     */
    public Double getVal0955() {
        return val0955;
    }

    /**
     * @param val0955
     */
    public void setVal0955(Double val0955) {
        this.val0955 = val0955;
    }

    /**
     * @return Val1000
     */
    public Double getVal1000() {
        return val1000;
    }

    /**
     * @param val1000
     */
    public void setVal1000(Double val1000) {
        this.val1000 = val1000;
    }

    /**
     * @return Val1005
     */
    public Double getVal1005() {
        return val1005;
    }

    /**
     * @param val1005
     */
    public void setVal1005(Double val1005) {
        this.val1005 = val1005;
    }

    /**
     * @return Val1010
     */
    public Double getVal1010() {
        return val1010;
    }

    /**
     * @param val1010
     */
    public void setVal1010(Double val1010) {
        this.val1010 = val1010;
    }

    /**
     * @return Val1015
     */
    public Double getVal1015() {
        return val1015;
    }

    /**
     * @param val1015
     */
    public void setVal1015(Double val1015) {
        this.val1015 = val1015;
    }

    /**
     * @return Val1020
     */
    public Double getVal1020() {
        return val1020;
    }

    /**
     * @param val1020
     */
    public void setVal1020(Double val1020) {
        this.val1020 = val1020;
    }

    /**
     * @return Val1025
     */
    public Double getVal1025() {
        return val1025;
    }

    /**
     * @param val1025
     */
    public void setVal1025(Double val1025) {
        this.val1025 = val1025;
    }

    /**
     * @return Val1030
     */
    public Double getVal1030() {
        return val1030;
    }

    /**
     * @param val1030
     */
    public void setVal1030(Double val1030) {
        this.val1030 = val1030;
    }

    /**
     * @return Val1035
     */
    public Double getVal1035() {
        return val1035;
    }

    /**
     * @param val1035
     */
    public void setVal1035(Double val1035) {
        this.val1035 = val1035;
    }

    /**
     * @return Val1040
     */
    public Double getVal1040() {
        return val1040;
    }

    /**
     * @param val1040
     */
    public void setVal1040(Double val1040) {
        this.val1040 = val1040;
    }

    /**
     * @return Val1045
     */
    public Double getVal1045() {
        return val1045;
    }

    /**
     * @param val1045
     */
    public void setVal1045(Double val1045) {
        this.val1045 = val1045;
    }

    /**
     * @return Val1050
     */
    public Double getVal1050() {
        return val1050;
    }

    /**
     * @param val1050
     */
    public void setVal1050(Double val1050) {
        this.val1050 = val1050;
    }

    /**
     * @return Val1055
     */
    public Double getVal1055() {
        return val1055;
    }

    /**
     * @param val1055
     */
    public void setVal1055(Double val1055) {
        this.val1055 = val1055;
    }

    /**
     * @return Val1100
     */
    public Double getVal1100() {
        return val1100;
    }

    /**
     * @param val1100
     */
    public void setVal1100(Double val1100) {
        this.val1100 = val1100;
    }

    /**
     * @return Val1105
     */
    public Double getVal1105() {
        return val1105;
    }

    /**
     * @param val1105
     */
    public void setVal1105(Double val1105) {
        this.val1105 = val1105;
    }

    /**
     * @return Val1110
     */
    public Double getVal1110() {
        return val1110;
    }

    /**
     * @param val1110
     */
    public void setVal1110(Double val1110) {
        this.val1110 = val1110;
    }

    /**
     * @return Val1115
     */
    public Double getVal1115() {
        return val1115;
    }

    /**
     * @param val1115
     */
    public void setVal1115(Double val1115) {
        this.val1115 = val1115;
    }

    /**
     * @return Val1120
     */
    public Double getVal1120() {
        return val1120;
    }

    /**
     * @param val1120
     */
    public void setVal1120(Double val1120) {
        this.val1120 = val1120;
    }

    /**
     * @return Val1125
     */
    public Double getVal1125() {
        return val1125;
    }

    /**
     * @param val1125
     */
    public void setVal1125(Double val1125) {
        this.val1125 = val1125;
    }

    /**
     * @return Val1130
     */
    public Double getVal1130() {
        return val1130;
    }

    /**
     * @param val1130
     */
    public void setVal1130(Double val1130) {
        this.val1130 = val1130;
    }

    /**
     * @return Val1135
     */
    public Double getVal1135() {
        return val1135;
    }

    /**
     * @param val1135
     */
    public void setVal1135(Double val1135) {
        this.val1135 = val1135;
    }

    /**
     * @return Val1140
     */
    public Double getVal1140() {
        return val1140;
    }

    /**
     * @param val1140
     */
    public void setVal1140(Double val1140) {
        this.val1140 = val1140;
    }

    /**
     * @return Val1145
     */
    public Double getVal1145() {
        return val1145;
    }

    /**
     * @param val1145
     */
    public void setVal1145(Double val1145) {
        this.val1145 = val1145;
    }

    /**
     * @return Val1150
     */
    public Double getVal1150() {
        return val1150;
    }

    /**
     * @param val1150
     */
    public void setVal1150(Double val1150) {
        this.val1150 = val1150;
    }

    /**
     * @return Val1155
     */
    public Double getVal1155() {
        return val1155;
    }

    /**
     * @param val1155
     */
    public void setVal1155(Double val1155) {
        this.val1155 = val1155;
    }

    /**
     * @return Val1200
     */
    public Double getVal1200() {
        return val1200;
    }

    /**
     * @param val1200
     */
    public void setVal1200(Double val1200) {
        this.val1200 = val1200;
    }

    /**
     * @return Val1205
     */
    public Double getVal1205() {
        return val1205;
    }

    /**
     * @param val1205
     */
    public void setVal1205(Double val1205) {
        this.val1205 = val1205;
    }

    /**
     * @return Val1210
     */
    public Double getVal1210() {
        return val1210;
    }

    /**
     * @param val1210
     */
    public void setVal1210(Double val1210) {
        this.val1210 = val1210;
    }

    /**
     * @return Val1215
     */
    public Double getVal1215() {
        return val1215;
    }

    /**
     * @param val1215
     */
    public void setVal1215(Double val1215) {
        this.val1215 = val1215;
    }

    /**
     * @return Val1220
     */
    public Double getVal1220() {
        return val1220;
    }

    /**
     * @param val1220
     */
    public void setVal1220(Double val1220) {
        this.val1220 = val1220;
    }

    /**
     * @return Val1225
     */
    public Double getVal1225() {
        return val1225;
    }

    /**
     * @param val1225
     */
    public void setVal1225(Double val1225) {
        this.val1225 = val1225;
    }

    /**
     * @return Val1230
     */
    public Double getVal1230() {
        return val1230;
    }

    /**
     * @param val1230
     */
    public void setVal1230(Double val1230) {
        this.val1230 = val1230;
    }

    /**
     * @return Val1235
     */
    public Double getVal1235() {
        return val1235;
    }

    /**
     * @param val1235
     */
    public void setVal1235(Double val1235) {
        this.val1235 = val1235;
    }

    /**
     * @return Val1240
     */
    public Double getVal1240() {
        return val1240;
    }

    /**
     * @param val1240
     */
    public void setVal1240(Double val1240) {
        this.val1240 = val1240;
    }

    /**
     * @return Val1245
     */
    public Double getVal1245() {
        return val1245;
    }

    /**
     * @param val1245
     */
    public void setVal1245(Double val1245) {
        this.val1245 = val1245;
    }

    /**
     * @return Val1250
     */
    public Double getVal1250() {
        return val1250;
    }

    /**
     * @param val1250
     */
    public void setVal1250(Double val1250) {
        this.val1250 = val1250;
    }

    /**
     * @return Val1255
     */
    public Double getVal1255() {
        return val1255;
    }

    /**
     * @param val1255
     */
    public void setVal1255(Double val1255) {
        this.val1255 = val1255;
    }

    /**
     * @return Val1300
     */
    public Double getVal1300() {
        return val1300;
    }

    /**
     * @param val1300
     */
    public void setVal1300(Double val1300) {
        this.val1300 = val1300;
    }

    /**
     * @return Val1305
     */
    public Double getVal1305() {
        return val1305;
    }

    /**
     * @param val1305
     */
    public void setVal1305(Double val1305) {
        this.val1305 = val1305;
    }

    /**
     * @return Val1310
     */
    public Double getVal1310() {
        return val1310;
    }

    /**
     * @param val1310
     */
    public void setVal1310(Double val1310) {
        this.val1310 = val1310;
    }

    /**
     * @return Val1315
     */
    public Double getVal1315() {
        return val1315;
    }

    /**
     * @param val1315
     */
    public void setVal1315(Double val1315) {
        this.val1315 = val1315;
    }

    /**
     * @return Val1320
     */
    public Double getVal1320() {
        return val1320;
    }

    /**
     * @param val1320
     */
    public void setVal1320(Double val1320) {
        this.val1320 = val1320;
    }

    /**
     * @return Val1325
     */
    public Double getVal1325() {
        return val1325;
    }

    /**
     * @param val1325
     */
    public void setVal1325(Double val1325) {
        this.val1325 = val1325;
    }

    /**
     * @return Val1330
     */
    public Double getVal1330() {
        return val1330;
    }

    /**
     * @param val1330
     */
    public void setVal1330(Double val1330) {
        this.val1330 = val1330;
    }

    /**
     * @return Val1335
     */
    public Double getVal1335() {
        return val1335;
    }

    /**
     * @param val1335
     */
    public void setVal1335(Double val1335) {
        this.val1335 = val1335;
    }

    /**
     * @return Val1340
     */
    public Double getVal1340() {
        return val1340;
    }

    /**
     * @param val1340
     */
    public void setVal1340(Double val1340) {
        this.val1340 = val1340;
    }

    /**
     * @return Val1345
     */
    public Double getVal1345() {
        return val1345;
    }

    /**
     * @param val1345
     */
    public void setVal1345(Double val1345) {
        this.val1345 = val1345;
    }

    /**
     * @return Val1350
     */
    public Double getVal1350() {
        return val1350;
    }

    /**
     * @param val1350
     */
    public void setVal1350(Double val1350) {
        this.val1350 = val1350;
    }

    /**
     * @return Val1355
     */
    public Double getVal1355() {
        return val1355;
    }

    /**
     * @param val1355
     */
    public void setVal1355(Double val1355) {
        this.val1355 = val1355;
    }

    /**
     * @return Val1400
     */
    public Double getVal1400() {
        return val1400;
    }

    /**
     * @param val1400
     */
    public void setVal1400(Double val1400) {
        this.val1400 = val1400;
    }

    /**
     * @return Val1405
     */
    public Double getVal1405() {
        return val1405;
    }

    /**
     * @param val1405
     */
    public void setVal1405(Double val1405) {
        this.val1405 = val1405;
    }

    /**
     * @return Val1410
     */
    public Double getVal1410() {
        return val1410;
    }

    /**
     * @param val1410
     */
    public void setVal1410(Double val1410) {
        this.val1410 = val1410;
    }

    /**
     * @return Val1415
     */
    public Double getVal1415() {
        return val1415;
    }

    /**
     * @param val1415
     */
    public void setVal1415(Double val1415) {
        this.val1415 = val1415;
    }

    /**
     * @return Val1420
     */
    public Double getVal1420() {
        return val1420;
    }

    /**
     * @param val1420
     */
    public void setVal1420(Double val1420) {
        this.val1420 = val1420;
    }

    /**
     * @return Val1425
     */
    public Double getVal1425() {
        return val1425;
    }

    /**
     * @param val1425
     */
    public void setVal1425(Double val1425) {
        this.val1425 = val1425;
    }

    /**
     * @return Val1430
     */
    public Double getVal1430() {
        return val1430;
    }

    /**
     * @param val1430
     */
    public void setVal1430(Double val1430) {
        this.val1430 = val1430;
    }

    /**
     * @return Val1435
     */
    public Double getVal1435() {
        return val1435;
    }

    /**
     * @param val1435
     */
    public void setVal1435(Double val1435) {
        this.val1435 = val1435;
    }

    /**
     * @return Val1440
     */
    public Double getVal1440() {
        return val1440;
    }

    /**
     * @param val1440
     */
    public void setVal1440(Double val1440) {
        this.val1440 = val1440;
    }

    /**
     * @return Val1445
     */
    public Double getVal1445() {
        return val1445;
    }

    /**
     * @param val1445
     */
    public void setVal1445(Double val1445) {
        this.val1445 = val1445;
    }

    /**
     * @return Val1450
     */
    public Double getVal1450() {
        return val1450;
    }

    /**
     * @param val1450
     */
    public void setVal1450(Double val1450) {
        this.val1450 = val1450;
    }

    /**
     * @return Val1455
     */
    public Double getVal1455() {
        return val1455;
    }

    /**
     * @param val1455
     */
    public void setVal1455(Double val1455) {
        this.val1455 = val1455;
    }

    /**
     * @return Val1500
     */
    public Double getVal1500() {
        return val1500;
    }

    /**
     * @param val1500
     */
    public void setVal1500(Double val1500) {
        this.val1500 = val1500;
    }

    /**
     * @return Val1505
     */
    public Double getVal1505() {
        return val1505;
    }

    /**
     * @param val1505
     */
    public void setVal1505(Double val1505) {
        this.val1505 = val1505;
    }

    /**
     * @return Val1510
     */
    public Double getVal1510() {
        return val1510;
    }

    /**
     * @param val1510
     */
    public void setVal1510(Double val1510) {
        this.val1510 = val1510;
    }

    /**
     * @return Val1515
     */
    public Double getVal1515() {
        return val1515;
    }

    /**
     * @param val1515
     */
    public void setVal1515(Double val1515) {
        this.val1515 = val1515;
    }

    /**
     * @return Val1520
     */
    public Double getVal1520() {
        return val1520;
    }

    /**
     * @param val1520
     */
    public void setVal1520(Double val1520) {
        this.val1520 = val1520;
    }

    /**
     * @return Val1525
     */
    public Double getVal1525() {
        return val1525;
    }

    /**
     * @param val1525
     */
    public void setVal1525(Double val1525) {
        this.val1525 = val1525;
    }

    /**
     * @return Val1530
     */
    public Double getVal1530() {
        return val1530;
    }

    /**
     * @param val1530
     */
    public void setVal1530(Double val1530) {
        this.val1530 = val1530;
    }

    /**
     * @return Val1535
     */
    public Double getVal1535() {
        return val1535;
    }

    /**
     * @param val1535
     */
    public void setVal1535(Double val1535) {
        this.val1535 = val1535;
    }

    /**
     * @return Val1540
     */
    public Double getVal1540() {
        return val1540;
    }

    /**
     * @param val1540
     */
    public void setVal1540(Double val1540) {
        this.val1540 = val1540;
    }

    /**
     * @return Val1545
     */
    public Double getVal1545() {
        return val1545;
    }

    /**
     * @param val1545
     */
    public void setVal1545(Double val1545) {
        this.val1545 = val1545;
    }

    /**
     * @return Val1550
     */
    public Double getVal1550() {
        return val1550;
    }

    /**
     * @param val1550
     */
    public void setVal1550(Double val1550) {
        this.val1550 = val1550;
    }

    /**
     * @return Val1555
     */
    public Double getVal1555() {
        return val1555;
    }

    /**
     * @param val1555
     */
    public void setVal1555(Double val1555) {
        this.val1555 = val1555;
    }

    /**
     * @return Val1600
     */
    public Double getVal1600() {
        return val1600;
    }

    /**
     * @param val1600
     */
    public void setVal1600(Double val1600) {
        this.val1600 = val1600;
    }

    /**
     * @return Val1605
     */
    public Double getVal1605() {
        return val1605;
    }

    /**
     * @param val1605
     */
    public void setVal1605(Double val1605) {
        this.val1605 = val1605;
    }

    /**
     * @return Val1610
     */
    public Double getVal1610() {
        return val1610;
    }

    /**
     * @param val1610
     */
    public void setVal1610(Double val1610) {
        this.val1610 = val1610;
    }

    /**
     * @return Val1615
     */
    public Double getVal1615() {
        return val1615;
    }

    /**
     * @param val1615
     */
    public void setVal1615(Double val1615) {
        this.val1615 = val1615;
    }

    /**
     * @return Val1620
     */
    public Double getVal1620() {
        return val1620;
    }

    /**
     * @param val1620
     */
    public void setVal1620(Double val1620) {
        this.val1620 = val1620;
    }

    /**
     * @return Val1625
     */
    public Double getVal1625() {
        return val1625;
    }

    /**
     * @param val1625
     */
    public void setVal1625(Double val1625) {
        this.val1625 = val1625;
    }

    /**
     * @return Val1630
     */
    public Double getVal1630() {
        return val1630;
    }

    /**
     * @param val1630
     */
    public void setVal1630(Double val1630) {
        this.val1630 = val1630;
    }

    /**
     * @return Val1635
     */
    public Double getVal1635() {
        return val1635;
    }

    /**
     * @param val1635
     */
    public void setVal1635(Double val1635) {
        this.val1635 = val1635;
    }

    /**
     * @return Val1640
     */
    public Double getVal1640() {
        return val1640;
    }

    /**
     * @param val1640
     */
    public void setVal1640(Double val1640) {
        this.val1640 = val1640;
    }

    /**
     * @return Val1645
     */
    public Double getVal1645() {
        return val1645;
    }

    /**
     * @param val1645
     */
    public void setVal1645(Double val1645) {
        this.val1645 = val1645;
    }

    /**
     * @return Val1650
     */
    public Double getVal1650() {
        return val1650;
    }

    /**
     * @param val1650
     */
    public void setVal1650(Double val1650) {
        this.val1650 = val1650;
    }

    /**
     * @return Val1655
     */
    public Double getVal1655() {
        return val1655;
    }

    /**
     * @param val1655
     */
    public void setVal1655(Double val1655) {
        this.val1655 = val1655;
    }

    /**
     * @return Val1700
     */
    public Double getVal1700() {
        return val1700;
    }

    /**
     * @param val1700
     */
    public void setVal1700(Double val1700) {
        this.val1700 = val1700;
    }

    /**
     * @return Val1705
     */
    public Double getVal1705() {
        return val1705;
    }

    /**
     * @param val1705
     */
    public void setVal1705(Double val1705) {
        this.val1705 = val1705;
    }

    /**
     * @return Val1710
     */
    public Double getVal1710() {
        return val1710;
    }

    /**
     * @param val1710
     */
    public void setVal1710(Double val1710) {
        this.val1710 = val1710;
    }

    /**
     * @return Val1715
     */
    public Double getVal1715() {
        return val1715;
    }

    /**
     * @param val1715
     */
    public void setVal1715(Double val1715) {
        this.val1715 = val1715;
    }

    /**
     * @return Val1720
     */
    public Double getVal1720() {
        return val1720;
    }

    /**
     * @param val1720
     */
    public void setVal1720(Double val1720) {
        this.val1720 = val1720;
    }

    /**
     * @return Val1725
     */
    public Double getVal1725() {
        return val1725;
    }

    /**
     * @param val1725
     */
    public void setVal1725(Double val1725) {
        this.val1725 = val1725;
    }

    /**
     * @return Val1730
     */
    public Double getVal1730() {
        return val1730;
    }

    /**
     * @param val1730
     */
    public void setVal1730(Double val1730) {
        this.val1730 = val1730;
    }

    /**
     * @return Val1735
     */
    public Double getVal1735() {
        return val1735;
    }

    /**
     * @param val1735
     */
    public void setVal1735(Double val1735) {
        this.val1735 = val1735;
    }

    /**
     * @return Val1740
     */
    public Double getVal1740() {
        return val1740;
    }

    /**
     * @param val1740
     */
    public void setVal1740(Double val1740) {
        this.val1740 = val1740;
    }

    /**
     * @return Val1745
     */
    public Double getVal1745() {
        return val1745;
    }

    /**
     * @param val1745
     */
    public void setVal1745(Double val1745) {
        this.val1745 = val1745;
    }

    /**
     * @return Val1750
     */
    public Double getVal1750() {
        return val1750;
    }

    /**
     * @param val1750
     */
    public void setVal1750(Double val1750) {
        this.val1750 = val1750;
    }

    /**
     * @return Val1755
     */
    public Double getVal1755() {
        return val1755;
    }

    /**
     * @param val1755
     */
    public void setVal1755(Double val1755) {
        this.val1755 = val1755;
    }

    /**
     * @return Val1800
     */
    public Double getVal1800() {
        return val1800;
    }

    /**
     * @param val1800
     */
    public void setVal1800(Double val1800) {
        this.val1800 = val1800;
    }

    /**
     * @return Val1805
     */
    public Double getVal1805() {
        return val1805;
    }

    /**
     * @param val1805
     */
    public void setVal1805(Double val1805) {
        this.val1805 = val1805;
    }

    /**
     * @return Val1810
     */
    public Double getVal1810() {
        return val1810;
    }

    /**
     * @param val1810
     */
    public void setVal1810(Double val1810) {
        this.val1810 = val1810;
    }

    /**
     * @return Val1815
     */
    public Double getVal1815() {
        return val1815;
    }

    /**
     * @param val1815
     */
    public void setVal1815(Double val1815) {
        this.val1815 = val1815;
    }

    /**
     * @return Val1820
     */
    public Double getVal1820() {
        return val1820;
    }

    /**
     * @param val1820
     */
    public void setVal1820(Double val1820) {
        this.val1820 = val1820;
    }

    /**
     * @return Val1825
     */
    public Double getVal1825() {
        return val1825;
    }

    /**
     * @param val1825
     */
    public void setVal1825(Double val1825) {
        this.val1825 = val1825;
    }

    /**
     * @return Val1830
     */
    public Double getVal1830() {
        return val1830;
    }

    /**
     * @param val1830
     */
    public void setVal1830(Double val1830) {
        this.val1830 = val1830;
    }

    /**
     * @return Val1835
     */
    public Double getVal1835() {
        return val1835;
    }

    /**
     * @param val1835
     */
    public void setVal1835(Double val1835) {
        this.val1835 = val1835;
    }

    /**
     * @return Val1840
     */
    public Double getVal1840() {
        return val1840;
    }

    /**
     * @param val1840
     */
    public void setVal1840(Double val1840) {
        this.val1840 = val1840;
    }

    /**
     * @return Val1845
     */
    public Double getVal1845() {
        return val1845;
    }

    /**
     * @param val1845
     */
    public void setVal1845(Double val1845) {
        this.val1845 = val1845;
    }

    /**
     * @return Val1850
     */
    public Double getVal1850() {
        return val1850;
    }

    /**
     * @param val1850
     */
    public void setVal1850(Double val1850) {
        this.val1850 = val1850;
    }

    /**
     * @return Val1855
     */
    public Double getVal1855() {
        return val1855;
    }

    /**
     * @param val1855
     */
    public void setVal1855(Double val1855) {
        this.val1855 = val1855;
    }

    /**
     * @return Val1900
     */
    public Double getVal1900() {
        return val1900;
    }

    /**
     * @param val1900
     */
    public void setVal1900(Double val1900) {
        this.val1900 = val1900;
    }

    /**
     * @return Val1905
     */
    public Double getVal1905() {
        return val1905;
    }

    /**
     * @param val1905
     */
    public void setVal1905(Double val1905) {
        this.val1905 = val1905;
    }

    /**
     * @return Val1910
     */
    public Double getVal1910() {
        return val1910;
    }

    /**
     * @param val1910
     */
    public void setVal1910(Double val1910) {
        this.val1910 = val1910;
    }

    /**
     * @return Val1915
     */
    public Double getVal1915() {
        return val1915;
    }

    /**
     * @param val1915
     */
    public void setVal1915(Double val1915) {
        this.val1915 = val1915;
    }

    /**
     * @return Val1920
     */
    public Double getVal1920() {
        return val1920;
    }

    /**
     * @param val1920
     */
    public void setVal1920(Double val1920) {
        this.val1920 = val1920;
    }

    /**
     * @return Val1925
     */
    public Double getVal1925() {
        return val1925;
    }

    /**
     * @param val1925
     */
    public void setVal1925(Double val1925) {
        this.val1925 = val1925;
    }

    /**
     * @return Val1930
     */
    public Double getVal1930() {
        return val1930;
    }

    /**
     * @param val1930
     */
    public void setVal1930(Double val1930) {
        this.val1930 = val1930;
    }

    /**
     * @return Val1935
     */
    public Double getVal1935() {
        return val1935;
    }

    /**
     * @param val1935
     */
    public void setVal1935(Double val1935) {
        this.val1935 = val1935;
    }

    /**
     * @return Val1940
     */
    public Double getVal1940() {
        return val1940;
    }

    /**
     * @param val1940
     */
    public void setVal1940(Double val1940) {
        this.val1940 = val1940;
    }

    /**
     * @return Val1945
     */
    public Double getVal1945() {
        return val1945;
    }

    /**
     * @param val1945
     */
    public void setVal1945(Double val1945) {
        this.val1945 = val1945;
    }

    /**
     * @return Val1950
     */
    public Double getVal1950() {
        return val1950;
    }

    /**
     * @param val1950
     */
    public void setVal1950(Double val1950) {
        this.val1950 = val1950;
    }

    /**
     * @return Val1955
     */
    public Double getVal1955() {
        return val1955;
    }

    /**
     * @param val1955
     */
    public void setVal1955(Double val1955) {
        this.val1955 = val1955;
    }

    /**
     * @return Val2000
     */
    public Double getVal2000() {
        return val2000;
    }

    /**
     * @param val2000
     */
    public void setVal2000(Double val2000) {
        this.val2000 = val2000;
    }

    /**
     * @return Val2005
     */
    public Double getVal2005() {
        return val2005;
    }

    /**
     * @param val2005
     */
    public void setVal2005(Double val2005) {
        this.val2005 = val2005;
    }

    /**
     * @return Val2010
     */
    public Double getVal2010() {
        return val2010;
    }

    /**
     * @param val2010
     */
    public void setVal2010(Double val2010) {
        this.val2010 = val2010;
    }

    /**
     * @return Val2015
     */
    public Double getVal2015() {
        return val2015;
    }

    /**
     * @param val2015
     */
    public void setVal2015(Double val2015) {
        this.val2015 = val2015;
    }

    /**
     * @return Val2020
     */
    public Double getVal2020() {
        return val2020;
    }

    /**
     * @param val2020
     */
    public void setVal2020(Double val2020) {
        this.val2020 = val2020;
    }

    /**
     * @return Val2025
     */
    public Double getVal2025() {
        return val2025;
    }

    /**
     * @param val2025
     */
    public void setVal2025(Double val2025) {
        this.val2025 = val2025;
    }

    /**
     * @return Val2030
     */
    public Double getVal2030() {
        return val2030;
    }

    /**
     * @param val2030
     */
    public void setVal2030(Double val2030) {
        this.val2030 = val2030;
    }

    /**
     * @return Val2035
     */
    public Double getVal2035() {
        return val2035;
    }

    /**
     * @param val2035
     */
    public void setVal2035(Double val2035) {
        this.val2035 = val2035;
    }

    /**
     * @return Val2040
     */
    public Double getVal2040() {
        return val2040;
    }

    /**
     * @param val2040
     */
    public void setVal2040(Double val2040) {
        this.val2040 = val2040;
    }

    /**
     * @return Val2045
     */
    public Double getVal2045() {
        return val2045;
    }

    /**
     * @param val2045
     */
    public void setVal2045(Double val2045) {
        this.val2045 = val2045;
    }

    /**
     * @return Val2050
     */
    public Double getVal2050() {
        return val2050;
    }

    /**
     * @param val2050
     */
    public void setVal2050(Double val2050) {
        this.val2050 = val2050;
    }

    /**
     * @return Val2055
     */
    public Double getVal2055() {
        return val2055;
    }

    /**
     * @param val2055
     */
    public void setVal2055(Double val2055) {
        this.val2055 = val2055;
    }

    /**
     * @return Val2100
     */
    public Double getVal2100() {
        return val2100;
    }

    /**
     * @param val2100
     */
    public void setVal2100(Double val2100) {
        this.val2100 = val2100;
    }

    /**
     * @return Val2105
     */
    public Double getVal2105() {
        return val2105;
    }

    /**
     * @param val2105
     */
    public void setVal2105(Double val2105) {
        this.val2105 = val2105;
    }

    /**
     * @return Val2110
     */
    public Double getVal2110() {
        return val2110;
    }

    /**
     * @param val2110
     */
    public void setVal2110(Double val2110) {
        this.val2110 = val2110;
    }

    /**
     * @return Val2115
     */
    public Double getVal2115() {
        return val2115;
    }

    /**
     * @param val2115
     */
    public void setVal2115(Double val2115) {
        this.val2115 = val2115;
    }

    /**
     * @return Val2120
     */
    public Double getVal2120() {
        return val2120;
    }

    /**
     * @param val2120
     */
    public void setVal2120(Double val2120) {
        this.val2120 = val2120;
    }

    /**
     * @return Val2125
     */
    public Double getVal2125() {
        return val2125;
    }

    /**
     * @param val2125
     */
    public void setVal2125(Double val2125) {
        this.val2125 = val2125;
    }

    /**
     * @return Val2130
     */
    public Double getVal2130() {
        return val2130;
    }

    /**
     * @param val2130
     */
    public void setVal2130(Double val2130) {
        this.val2130 = val2130;
    }

    /**
     * @return Val2135
     */
    public Double getVal2135() {
        return val2135;
    }

    /**
     * @param val2135
     */
    public void setVal2135(Double val2135) {
        this.val2135 = val2135;
    }

    /**
     * @return Val2140
     */
    public Double getVal2140() {
        return val2140;
    }

    /**
     * @param val2140
     */
    public void setVal2140(Double val2140) {
        this.val2140 = val2140;
    }

    /**
     * @return Val2145
     */
    public Double getVal2145() {
        return val2145;
    }

    /**
     * @param val2145
     */
    public void setVal2145(Double val2145) {
        this.val2145 = val2145;
    }

    /**
     * @return Val2150
     */
    public Double getVal2150() {
        return val2150;
    }

    /**
     * @param val2150
     */
    public void setVal2150(Double val2150) {
        this.val2150 = val2150;
    }

    /**
     * @return Val2155
     */
    public Double getVal2155() {
        return val2155;
    }

    /**
     * @param val2155
     */
    public void setVal2155(Double val2155) {
        this.val2155 = val2155;
    }

    /**
     * @return Val2200
     */
    public Double getVal2200() {
        return val2200;
    }

    /**
     * @param val2200
     */
    public void setVal2200(Double val2200) {
        this.val2200 = val2200;
    }

    /**
     * @return Val2205
     */
    public Double getVal2205() {
        return val2205;
    }

    /**
     * @param val2205
     */
    public void setVal2205(Double val2205) {
        this.val2205 = val2205;
    }

    /**
     * @return Val2210
     */
    public Double getVal2210() {
        return val2210;
    }

    /**
     * @param val2210
     */
    public void setVal2210(Double val2210) {
        this.val2210 = val2210;
    }

    /**
     * @return Val2215
     */
    public Double getVal2215() {
        return val2215;
    }

    /**
     * @param val2215
     */
    public void setVal2215(Double val2215) {
        this.val2215 = val2215;
    }

    /**
     * @return Val2220
     */
    public Double getVal2220() {
        return val2220;
    }

    /**
     * @param val2220
     */
    public void setVal2220(Double val2220) {
        this.val2220 = val2220;
    }

    /**
     * @return Val2225
     */
    public Double getVal2225() {
        return val2225;
    }

    /**
     * @param val2225
     */
    public void setVal2225(Double val2225) {
        this.val2225 = val2225;
    }

    /**
     * @return Val2230
     */
    public Double getVal2230() {
        return val2230;
    }

    /**
     * @param val2230
     */
    public void setVal2230(Double val2230) {
        this.val2230 = val2230;
    }

    /**
     * @return Val2235
     */
    public Double getVal2235() {
        return val2235;
    }

    /**
     * @param val2235
     */
    public void setVal2235(Double val2235) {
        this.val2235 = val2235;
    }

    /**
     * @return Val2240
     */
    public Double getVal2240() {
        return val2240;
    }

    /**
     * @param val2240
     */
    public void setVal2240(Double val2240) {
        this.val2240 = val2240;
    }

    /**
     * @return Val2245
     */
    public Double getVal2245() {
        return val2245;
    }

    /**
     * @param val2245
     */
    public void setVal2245(Double val2245) {
        this.val2245 = val2245;
    }

    /**
     * @return Val2250
     */
    public Double getVal2250() {
        return val2250;
    }

    /**
     * @param val2250
     */
    public void setVal2250(Double val2250) {
        this.val2250 = val2250;
    }

    /**
     * @return Val2255
     */
    public Double getVal2255() {
        return val2255;
    }

    /**
     * @param val2255
     */
    public void setVal2255(Double val2255) {
        this.val2255 = val2255;
    }

    /**
     * @return Val2300
     */
    public Double getVal2300() {
        return val2300;
    }

    /**
     * @param val2300
     */
    public void setVal2300(Double val2300) {
        this.val2300 = val2300;
    }

    /**
     * @return Val2305
     */
    public Double getVal2305() {
        return val2305;
    }

    /**
     * @param val2305
     */
    public void setVal2305(Double val2305) {
        this.val2305 = val2305;
    }

    /**
     * @return Val2310
     */
    public Double getVal2310() {
        return val2310;
    }

    /**
     * @param val2310
     */
    public void setVal2310(Double val2310) {
        this.val2310 = val2310;
    }

    /**
     * @return Val2315
     */
    public Double getVal2315() {
        return val2315;
    }

    /**
     * @param val2315
     */
    public void setVal2315(Double val2315) {
        this.val2315 = val2315;
    }

    /**
     * @return Val2320
     */
    public Double getVal2320() {
        return val2320;
    }

    /**
     * @param val2320
     */
    public void setVal2320(Double val2320) {
        this.val2320 = val2320;
    }

    /**
     * @return Val2325
     */
    public Double getVal2325() {
        return val2325;
    }

    /**
     * @param val2325
     */
    public void setVal2325(Double val2325) {
        this.val2325 = val2325;
    }

    /**
     * @return Val2330
     */
    public Double getVal2330() {
        return val2330;
    }

    /**
     * @param val2330
     */
    public void setVal2330(Double val2330) {
        this.val2330 = val2330;
    }

    /**
     * @return Val2335
     */
    public Double getVal2335() {
        return val2335;
    }

    /**
     * @param val2335
     */
    public void setVal2335(Double val2335) {
        this.val2335 = val2335;
    }

    /**
     * @return Val2340
     */
    public Double getVal2340() {
        return val2340;
    }

    /**
     * @param val2340
     */
    public void setVal2340(Double val2340) {
        this.val2340 = val2340;
    }

    /**
     * @return Val2345
     */
    public Double getVal2345() {
        return val2345;
    }

    /**
     * @param val2345
     */
    public void setVal2345(Double val2345) {
        this.val2345 = val2345;
    }

    /**
     * @return Val2350
     */
    public Double getVal2350() {
        return val2350;
    }

    /**
     * @param val2350
     */
    public void setVal2350(Double val2350) {
        this.val2350 = val2350;
    }

    /**
     * @return Val2355
     */
    public Double getVal2355() {
        return val2355;
    }

    /**
     * @param val2355
     */
    public void setVal2355(Double val2355) {
        this.val2355 = val2355;
    }

    /**
     * @return MaxVal
     */
    public Double getMaxval() {
        return maxval;
    }

    /**
     * @param maxval
     */
    public void setMaxval(Double maxval) {
        this.maxval = maxval;
    }

    /**
     * @return MinVal
     */
    public Double getMinval() {
        return minval;
    }

    /**
     * @param minval
     */
    public void setMinval(Double minval) {
        this.minval = minval;
    }

    /**
     * @return AvgVal
     */
    public Double getAvgval() {
        return avgval;
    }

    /**
     * @param avgval
     */
    public void setAvgval(Double avgval) {
        this.avgval = avgval;
    }

    /**
     * @return IntVal
     */
    public Double getIntval() {
        return intval;
    }

    /**
     * @param intval
     */
    public void setIntval(Double intval) {
        this.intval = intval;
    }

    /**
     * @return SumVal
     */
    public Double getSumval() {
        return sumval;
    }

    /**
     * @param sumval
     */
    public void setSumval(Double sumval) {
        this.sumval = sumval;
    }

	public void setDeviceids(List<String> deviceids) {
		this.deviceids = deviceids;
	}

	public List<String> getDeviceids() {
		return this.deviceids;
	}

	public Integer getColNum() {
		return colNum == null || colNum == 0 ? 288 : colNum;
	}

	public void setColNum(Integer colNum) {
		this.colNum = colNum;
	}

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	public String getMeasureType() {
		return measureType;
	}

	public void setMeasureType(String measureType) {
		this.measureType = measureType;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}


	public Device getDevice(){
//		return EnterpriseUtils.getDeviceTree(deviceid);
		return null;
	}

	public double max(){
		double m = -1;
		for(int i = 0; i < TimeSeriesUtils.CURVE_288COLUMN.length; i++){
			try {
				String val = BeanUtils.getProperty(this, TimeSeriesUtils.CURVE_288COLUMN[i]);
				if(val != null && !"".equals(val)){
					Double obj = Double.parseDouble(val);
					if(m == -1 || m < obj.doubleValue()){
						m = obj.doubleValue();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return m;
	}

	public double min(){
		double m = -1;
		for(int i = 0; i < TimeSeriesUtils.CURVE_288COLUMN.length; i++){
			try {
				String val = BeanUtils.getProperty(this, TimeSeriesUtils.CURVE_288COLUMN[i]);
				if(val != null && !"".equals(val)){
					Double obj = Double.parseDouble(val);
					if(m == -1 || m > obj.doubleValue()){
						m = obj.doubleValue();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return m;
	}

	public double sum(){
		double m = 0;
		for(int i = 0; i < TimeSeriesUtils.CURVE_288COLUMN.length; i++){
			try {
				String val = BeanUtils.getProperty(this, TimeSeriesUtils.CURVE_288COLUMN[i]);
				if(val != null && !"".equals(val)){
					Double obj = Double.parseDouble(val);
					m += obj.doubleValue();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return m;
	}

	public double avg(){
		double m = 0;
		int count = 0;
		for(int i = 0; i < TimeSeriesUtils.CURVE_288COLUMN.length; i++){
			try {
				String val = BeanUtils.getProperty(this, TimeSeriesUtils.CURVE_288COLUMN[i]);
				if(val != null && !"".equals(val)){
					Double obj = Double.parseDouble(val);
					m += obj.doubleValue();
					count++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return count == 0 ? 0 : Math.round((m/count) * 100) / 100;
	}

}

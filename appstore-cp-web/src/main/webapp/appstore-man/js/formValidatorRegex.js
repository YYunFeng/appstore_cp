var regexEnum =
{
    intege:"^-?[1-9]\\d*$",                    //整数
    intege1:"^[1-9]\\d*$",                    //正整数
    intege2:"^-[1-9]\\d*$",                    //负整数
    num:"^([+-]?)\\d*\\.?\\d+$",            //数字
    num1:"^[1-9]\\d*|0$",                    //正数（正整数 + 0）
    num2:"^-[1-9]\\d*|0$",                    //负数（负整数 + 0）
    decmal:"^([+-]?)\\d*\\.\\d+$",            //浮点数
    decmal1:"^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*$",    //正浮点数
    decmal2:"^-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*)$",//负浮点数
    decmal3:"^-?([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0)$",//浮点数
    decmal4:"^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0$",//非负浮点数（正浮点数 + 0）
    decmal5:"^(-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*))|0?.0+|0$",//非正浮点数（负浮点数 + 0）
    email:"/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/", //邮件
    color:"^[a-fA-F0-9]{6}$",                //颜色
    url:"^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$",    //url
    phoneAll:"(^(\\d{2,4}[-_－—]?)?\\d{3,8}([-_－—]?\\d{3,8})?([-_－—]?\\d{1,7})?$)|(^0?1[35]\\d{9}$)",//联系电话
    chinese:"^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$",                    //仅中文
    ascii:"^[\\x00-\\xFF]+$",                //仅ACSII字符
    zipcode:"^\\d{6}$",                        //邮编
    mobile:"^13[0-9]{9}|15[012356789][0-9]{8}|18[0256789][0-9]{8}|147[0-9]{8}$",                //手机
    ip4:"^(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)$",    //ip地址
    notempty:"^\\S+$",                        //非空
    picture:"(.*)\\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$",    //图片
    rar:"(.*)\\.(rar|zip|7zip|tgz)$",                                //压缩文件
    date:"^\\d{4}(\\-|\\/|\.)\\d{1,2}\\1\\d{1,2}$",                    //日期
    qq:"^[1-9]*[1-9][0-9]*$",                //QQ号码
    tel:"^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$",    //电话号码的函数(包括验证国内区号,国际区号,分机号)
    username:"^\\w+$",                        //用来用户注册。匹配由数字、26个英文字母或者下划线组成的字符串
    letter:"^[A-Za-z]+$",                    //字母
    letter_u:"^[A-Z]+$",                    //大写字母
    letter_l:"^[a-z]+$",                    //小写字母
    idcard:"^[1-9]([0-9]{14}|[0-9]{17})$"  //身份证

}

var aCity = {11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"}

function isCardID(sId) {
    var iSum = 0;
    var info = "";
    if (!/^\d{17}(\d|x)$/i.test(sId)) return "你输入的身份证长度或格式错误";
    sId = sId.replace(/x$/i, "a");
    if (aCity[parseInt(sId.substr(0, 2))] == null) return "你的身份证地区非法";
    sBirthday = sId.substr(6, 4) + "-" + Number(sId.substr(10, 2)) + "-" + Number(sId.substr(12, 2));
    var d = new Date(sBirthday.replace(/-/g, "/"));
    if (sBirthday != (d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate()))return "身份证上的出生日期非法";
    for (var i = 17; i >= 0; i --) iSum += (Math.pow(2, i) % 11) * parseInt(sId.charAt(17 - i), 11);
    if (iSum % 11 != 1) return "你输入的身份证号非法";
    return true;//aCity[parseInt(sId.substr(0,2))]+","+sBirthday+","+(sId.substr(16,1)%2?"男":"女")
}
function isPassport(str) {
    var reg = "(P/d{7})|G/d{8})";
    return reg.test(str)

}
//短时间，形如 (13:04:06)
function isTime(str) {
    var a = str.match(/^(\d{1,2})(:)?(\d{1,2})\2(\d{1,2})$/);
    if (a == null) {
        return false
    }
    if (a[1] > 24 || a[3] > 60 || a[4] > 60) {
        return false;
    }
    return true;
}

//短日期，形如 (2003-12-05)
function isDate(str) {
    var r = str.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
    if (r == null)return false;
    var d = new Date(r[1], r[3] - 1, r[4]);
    return (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3] && d.getDate() == r[4]);
}

//长时间，形如 (2003-12-05 13:04:06)
function isDateTime(str) {
    var reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/;
    var r = str.match(reg);
    if (r == null) return false;
    var d = new Date(r[1], r[3] - 1, r[4], r[5], r[6], r[7]);
    return (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3] && d.getDate() == r[4] && d.getHours() == r[5] && d.getMinutes() == r[6] && d.getSeconds() == r[7]);
}
//获取输入的字节数
function GetCharLength(str) {
    var iLength = 0;  //记录字符的字节数
    for (var i = 0; i < str.length; i++)  //遍历字符串中的每个字符
    {
        if (str.charCodeAt(i) > 255)   //如果当前字符的编码大于255
        {
            iLength += 2;    //所占字节数加2
        }
        else {
            iLength += 1;   //否则所占字节数加1
        }
    }
    return iLength;   //返回字符所占字节数
}
//中英文、数字、或者"_"
function IsDevAuthor(str, minLen, maxLen) {
    if (GetCharLength(str) > maxLen)
        return false;
    if (str != undefined && str.length < minLen)
        return false;

    var reg = /^[\u4e00-\u9fa5\d\_|a-zA-Z]+$/i;
    return reg.test(str);
}
function IsPassport(str) {
    var reg = /^\d+[x|X|\d]$/i;
    return reg.test(str);
}


// 对身份证的验证
function validateIdCard(obj) {
    var aCity = {11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙 江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖 北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西 藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国 外"};
    var iSum = 0;
    //var info = "";
    var strIDno = obj;
    var idCardLength = strIDno.length;
    if (!/^\d{17}(\d|x)$/i.test(strIDno) && !/^\d{15}$/i.test(strIDno))
        return 1; //非法身份证号

    if (aCity[parseInt(strIDno.substr(0, 2))] == null)
        return 2;// 非法地区

    // 15位身份证转换为18位
    if (idCardLength == 15) {
        sBirthday = "19" + strIDno.substr(6, 2) + "-" + Number(strIDno.substr(8, 2)) + "-" + Number(strIDno.substr(10, 2));
        var d = new Date(sBirthday.replace(/-/g, "/"))
        var dd = d.getFullYear().toString() + "-" + (d.getMonth() + 1) + "-" + d.getDate();
        if (sBirthday != dd)
            return 3; //非法生日
        strIDno = strIDno.substring(0, 6) + "19" + strIDno.substring(6, 15);
        strIDno = strIDno + GetVerifyBit(strIDno);
    }

    // 判断是否大于2078年，小于1900年
    var year = strIDno.substring(6, 10);
    if (year < 1900 || year > 2078)
        return 3;//非法生日

    //18位身份证处理

    //在后面的运算中x相当于数字10,所以转换成a
    strIDno = strIDno.replace(/x$/i, "a");

    sBirthday = strIDno.substr(6, 4) + "-" + Number(strIDno.substr(10, 2)) + "-" + Number(strIDno.substr(12, 2));
    var d = new Date(sBirthday.replace(/-/g, "/"))
    if (sBirthday != (d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate()))
        return 3; //非法生日
    // 身份证编码规范验证
    for (var i = 17; i >= 0; i --)
        iSum += (Math.pow(2, i) % 11) * parseInt(strIDno.charAt(17 - i), 11);
    if (iSum % 11 != 1)
        return 1;// 非法身份证号

    // 判断是否屏蔽身份证
    var words = new Array();
    words = new Array("11111119111111111", "12121219121212121");

    for (var k = 0; k < words.length; k++) {
        if (strIDno.indexOf(words[k]) != -1) {
            return 1;
        }
    }

    return 0;
}
/****************************

 Method : 进度框，多在联网时调用
 Author : lzl

 *****************************/

/**
 * 自定义提示框内容的提示框
 * 注 ： 此为开始调用，与closeTickAlert()函数一同使用
 */
function loadTickAlert(doc) {
    document.getElementById('inputBox').innerHTML = doc;
    document.getElementById('load').style.display = 'block';
}


/**
 * 关闭带文字的进度框效果。
 * 注 ： 此为结束时候调用loadTickAlert()、loadTickAlertX(doc)、loadTickAlertS(docArray)函数一同使用
 */

function closeTickAlert() {
    document.getElementById('inputBox').innerHTML = '';
    document.getElementById('load').style.display = 'none';
}
// date.js

/**
 * 将一个 Date 对象按照你传入的格式字符串（如 "yyyy-MM-dd hh:mm:ss"）格式化成对应的字符串形式。
 * @param {*} date
 * @param {*} fmt
 * @returns 
 */
export function formatDate(date, fmt) {
  if (/(y+)/.test(fmt)) {
    fmt = fmt.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length));
  }
  let o = {
    'M+': date.getMonth() + 1,
    'd+': date.getDate(),
    'h+': date.getHours(),
    'm+': date.getMinutes(),
    's+': date.getSeconds()
  };
  for (let k in o) {
    if (new RegExp(`(${k})`).test(fmt)) {
      let str = o[k] + '';
      fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? str : padLeftZero(str));
    }
  }
  return fmt;
}
// 用例
const now = new Date();
console.log(formatDate(now, 'yyyy-MM-dd'));           // 2025-04-16
console.log(formatDate(now, 'yyyy/MM/dd hh:mm:ss'));  // 2025/04/16 20:05:03
console.log(formatDate(now, 'yy-M-d h:m:s'));          // 25-4-16 8:5:3



/**
 * 
 * @param {*} dateStr 
 * @param {*} pattern 
 * @returns 
 */
export function formatDateStr(dateStr, pattern = 'yyyy-MM-dd hh:mm:ss') {
  if (!dateStr) return '';
  const date = new Date(dateStr);
  if (isNaN(date.getTime())) return ''; // 防止无效日期
  return formatDate(date, pattern);
}
// 用例
let ss = formatDateStr('2020-02-27T14:43:16.000+0000')
print(ss)



/**`
 * 数字字符串补零函数，如果数字是个位数（比如 3），返回两位字符串（比如 03）。
 * @param {*} str 
 * @returns 
 */
function padLeftZero(str) {
  return ('00' + str).substr(str.length);
}
// 用例
padLeftZero("3")   // "03"
padLeftZero("10")  // "10"

/**
 * 字符串转 Date 对象，将一个形如 "2025-04-16" 的日期字符串转换成 Date 对象。
 * 可选分隔符（默认是 -）
 * 自动处理 "04" → 4 这种情况
 * @param {*} dateStr 
 * @param {*} separator 
 * @returns 
 */
export function str2Date(dateStr, separator) {
  if (!separator) {
    separator = "-";
  }
  let dateArr = dateStr.split(separator);
  let year = parseInt(dateArr[0]);
  let month;
  //处理月份为04这样的情况
  if (dateArr[1].indexOf("0") == 0) {
    month = parseInt(dateArr[1].substring(1));
  } else {
    month = parseInt(dateArr[1]);
  }
  let day = parseInt(dateArr[2]);
  let date = new Date(year, month - 1, day);
  return date;
}
// 用例
const date1 = str2Date("2025-04-16");   // 默认分隔符为 "-"
console.log(date1);                     // Wed Apr 16 2025 00:00:00 GMT+0800 (中国标准时间)
const date2 = str2Date("2025/04/16", "/");
console.log(date2);                     // 同上，解析成功


// 搭配使用示例：字符串转日期 → 格式化
const dd = "2025-04-16";
const dateObj = str2Date(dd);                    // 转 Date 对象
const formatted = formatDate(dateObj, 'yyyy年MM月dd日'); // 再格式化
console.log(formatted); // "2025年04月16日"
